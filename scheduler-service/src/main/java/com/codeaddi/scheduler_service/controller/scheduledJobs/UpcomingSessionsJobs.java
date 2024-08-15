package com.codeaddi.scheduler_service.controller.scheduledJobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UpcomingSessionsJobs {

    @Autowired
    UpcomingSessionsService upcomingSessionsService;

//    @Scheduled(cron = "58 22 * * 6") // Every Saturday at 10:59pm
//    public void moveOldSessionsToPastAvailability() {
//        System.out.println("Task executed at " + new java.util.Date());
//        // Your code here
//    }
//
@Scheduled(cron = "0 0 23 * * SAT") // Every Saturday at 11 PM
    public void addNewUpcomingSessions() {
        upcomingSessionsService.addNewWeekOfUpcomingSessions();
    }

}
