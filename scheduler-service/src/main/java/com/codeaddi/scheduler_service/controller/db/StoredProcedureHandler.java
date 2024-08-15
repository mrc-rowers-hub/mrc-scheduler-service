package com.codeaddi.scheduler_service.controller.db;

import com.codeaddi.scheduler_service.model.repository.sessions.UpcomingSessionsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class StoredProcedureHandler {

  @Autowired UpcomingSessionsRepository upcomingSessionsRepository;

  @Transactional
  public void updateUpcomingSessions(java.sql.Date startDate) {

    upcomingSessionsRepository.callInsertUpcomingSessions(startDate);

    log.info("Added upcoming sessions starting from " + startDate);
  }

  @Transactional
  public void initAddFourWeeksOfUpcomingSessions(Long sessionId) {
    upcomingSessionsRepository.initUpcomingFourWeeksForSession(sessionId);
    log.info("Populated 4 weeks of upcoming availability for session {}", sessionId);
  }
}
