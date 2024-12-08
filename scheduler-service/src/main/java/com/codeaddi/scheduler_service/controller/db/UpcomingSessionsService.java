package com.codeaddi.scheduler_service.controller.db;

import com.codeaddi.scheduler_service.model.repository.sessions.PastSessionsAvailabilityRepository;
import com.codeaddi.scheduler_service.model.repository.sessions.PastSessionsRepository;
import com.codeaddi.scheduler_service.model.repository.sessions.UpcomingSessionsAvailabilityRepository;
import com.codeaddi.scheduler_service.model.repository.sessions.UpcomingSessionsRepository;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.PastSession;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.PastSessionAvailability;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.UpcomingSession;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.UpcomingSessionAvailability;

import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UpcomingSessionsService {

  @Autowired UpcomingSessionsRepository upcomingSessionsRepository;

  @Autowired UpcomingSessionsAvailabilityRepository upcomingSessionsAvailabilityRepository;

  @Autowired PastSessionsAvailabilityRepository pastSessionsAvailabilityRepository;

  @Autowired PastSessionsRepository pastSessionsRepository;

  @Autowired StoredProcedureHandler storedProcedureHandler;

  @Transactional
  public void addNewWeekOfUpcomingSessions(DayOfWeek dayOfWeek) {
    // if day of week = saturday, then move everything up to & including next friday at midnight
    // if day of the week = Wednesday, move everything up to & including sunday at midnight
    java.sql.Date dateUntil = getUpcomingDate(dayOfWeek);
    List<UpcomingSession> oldSessions = moveOldSessions(dateUntil);
    moveOldAvailabilities(oldSessions);

    storedProcedureHandler.updateUpcomingSessions(dateUntil);
  }

  public List<UpcomingSession> getAllUpcomingSessions() {
    return upcomingSessionsRepository.findAll();
  }

  private List<UpcomingSession> moveOldSessions(java.sql.Date dateUntil) {
    List<UpcomingSession> pastSessions =
        upcomingSessionsRepository.findAllByDateBefore(dateUntil);

    for (UpcomingSession upcomingSession : pastSessions) {
      Long upcomingSessionId = upcomingSession.getUpcomingSessionId();
      PastSession pastSession =
          PastSession.builder()
              .upcomingSessionId(upcomingSessionId)
              .sessionId(upcomingSession.getSessionId())
              .date(upcomingSession.getDate())
              .build();
      pastSessionsRepository.save(pastSession);
      log.info("Upcoming session {} moved to past sessions", upcomingSessionId);

      upcomingSessionsRepository.delete(upcomingSession);
      log.info("Removed upcoming session {} from upcoming sessions", upcomingSessionId);
    }

    return pastSessions;
  }

  private void moveOldAvailabilities(List<UpcomingSession> pastSessions) {
    for (UpcomingSession upcomingSession : pastSessions) {
      Long upcomingSessionId = upcomingSession.getUpcomingSessionId();

      List<UpcomingSessionAvailability> upcomingSessionAvailabilities =
          upcomingSessionsAvailabilityRepository
              .findUpcomingSessionAvailabilitiesByUpcomingSessionId(upcomingSessionId);

      for (UpcomingSessionAvailability upcomingSessionAvailability :
          upcomingSessionAvailabilities) {
        PastSessionAvailability pastSessionAvailability =
            PastSessionAvailability.builder()
                .upcomingSessionId(upcomingSessionAvailability.getUpcomingSessionId())
                .rowerId(upcomingSessionAvailability.getRowerId())
                .build();
        pastSessionsAvailabilityRepository.save(pastSessionAvailability);
        log.info(
            "Upcoming session availability {} moved to past sessions",
            upcomingSessionAvailability.getId());
      }

      upcomingSessionsAvailabilityRepository.deleteByUpcomingSessionId(upcomingSessionId);
      log.info(
          "Removed upcoming availabilities {} from upcoming availabilities", upcomingSessionId);
    }
  }

  public void initAddFourWeeksOfUpcomingSessions(Long sessionId) {
    storedProcedureHandler.initAddFourWeeksOfUpcomingSessions(sessionId);
  }

  public void removeUpcomingSessionsForRemovedSession(Long sessionId) {
    Long upcomingSessionIdToRemove =
        upcomingSessionsRepository.findAllBySessionId(sessionId).stream()
            .findAny()
            .get()
            .getSessionId();

    upcomingSessionsRepository.deleteBySessionId(sessionId);
    log.info("Deleting upcoming sessions for removed ID {}", sessionId);

    upcomingSessionsAvailabilityRepository.deleteByUpcomingSessionId(upcomingSessionIdToRemove);
  }

  private java.sql.Date getUpcomingDate(DayOfWeek dayOfWeek) {
    LocalDate today = LocalDate.now();
    LocalDate targetDate;

    switch (dayOfWeek) {
      case SATURDAY:
        // Get next Friday relative to today
        targetDate = today.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        break;
      case WEDNESDAY:
        // Get next Sunday relative to today
        targetDate = today.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        break;
      default:
        // Throw exception for unsupported DayOfWeek
        throw new IllegalArgumentException("Unsupported DayOfWeek: " + dayOfWeek);
    }

    return java.sql.Date.valueOf(targetDate);
  }
}
