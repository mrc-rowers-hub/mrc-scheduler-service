package com.codeaddi.scheduler_service.controller.db;

import com.codeaddi.scheduler_service.model.repository.sessions.UpcomingSessionsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Slf4j
public class StoredProcedureHandler {

    @Autowired
    UpcomingSessionsRepository upcomingSessionsRepository;

    @Transactional
    public void updateUpcomingSessions(Date startDate) {
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());

        // Call the stored procedure with the converted date
        upcomingSessionsRepository.callInsertUpcomingSessions(sqlStartDate);

        log.info("Added upcoming sessions starting from " + sqlStartDate);

    }

    @Transactional
    public void initAddFourWeeksOfUpcomingSessions(Long sessionId) { // call this one on the add or update endpoint
        upcomingSessionsRepository.initUpcomingFourWeeksForSession(sessionId);
        log.info("Populated 4 weeks of upcoming availability for session {}", sessionId);
    }

    // need something to delete these too


}
