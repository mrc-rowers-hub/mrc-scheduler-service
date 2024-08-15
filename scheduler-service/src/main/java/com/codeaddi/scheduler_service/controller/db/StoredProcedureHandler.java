package com.codeaddi.scheduler_service.controller.db;

import com.codeaddi.scheduler_service.model.repository.UpcomingSessionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class StoredProcedureHandler {

    @Autowired
    UpcomingSessionsRepository upcomingSessionsRepository;

    @Transactional
    public void updateUpcomingSessions(Date startDate) {
        upcomingSessionsRepository.callInsertUpcomingSessions((java.sql.Date) startDate);
    }

    @Transactional
    public void initAddFourWeeksOfUpcomingSessions() {
        upcomingSessionsRepository.initUpcomingFourWeeks();
    }


}
