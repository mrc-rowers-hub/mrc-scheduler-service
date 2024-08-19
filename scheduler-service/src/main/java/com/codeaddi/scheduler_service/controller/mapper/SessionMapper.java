package com.codeaddi.scheduler_service.controller.mapper;

import com.codeaddi.scheduler_service.model.http.outbound.UpcomingAvailabilityDTO;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.Session;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.UpcomingSession;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionMapper {

  public static List<UpcomingAvailabilityDTO> mapSessionsAndUpcomingAvailFromDBToDTOs(
      List<Session> sessions, List<UpcomingSession> upcomingSessionList) {
    List<UpcomingAvailabilityDTO> upcomingAvailabilityDTOS = new ArrayList<>();
    for (UpcomingSession upcomingSession : upcomingSessionList) {
      try {
        Session session =
            sessions.stream()
                .filter(session1 -> session1.getId().equals(upcomingSession.getSessionId()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        upcomingAvailabilityDTOS.add(
            UpcomingAvailabilityDTO.builder()
                .upcomingSessionId(upcomingSession.getUpcomingSessionId())
                .date(upcomingSession.getDate())
                .startTime(session.getStartTime())
                .endTime(session.getEndTime())
                .squad(session.getSquad())
                .level(session.getLevel())
                .sessionType(session.getSessionType())
                .build());
      } catch (IllegalArgumentException e) {
        log.info("Session not found for id {}", upcomingSession.getUpcomingSessionId());
        throw new RuntimeException(e);
      }
    }
    return upcomingAvailabilityDTOS;
  }
}
