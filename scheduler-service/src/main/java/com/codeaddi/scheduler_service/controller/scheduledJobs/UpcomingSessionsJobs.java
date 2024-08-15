package com.codeaddi.scheduler_service.controller.scheduledJobs;

import com.codeaddi.scheduler_service.controller.db.UpcomingSessionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UpcomingSessionsJobs {

  @Autowired UpcomingSessionsService upcomingSessionsService;

  @Scheduled(cron = "0 0 23 * * SAT") // Every Saturday at 11 PM
  public void addNewUpcomingSessions() {
    upcomingSessionsService.addNewWeekOfUpcomingSessions();
  }
}
