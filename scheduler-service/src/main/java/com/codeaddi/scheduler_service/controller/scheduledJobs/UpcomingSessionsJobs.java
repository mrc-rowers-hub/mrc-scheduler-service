package com.codeaddi.scheduler_service.controller.scheduledJobs;

import com.codeaddi.scheduler_service.controller.db.UpcomingSessionsService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class UpcomingSessionsJobs {

  @Autowired UpcomingSessionsService upcomingSessionsService;

  @PostConstruct
  public void init() { // performed upon startup
    // if now is saturday 8:01pm - weds 7:59pm, then perform the saturday one
    // otherwise, do the wednesday one
    LocalDateTime now = LocalDateTime.now();
    DayOfWeek dayOfWeek = now.getDayOfWeek();
    LocalTime time = now.toLocalTime();

    if ((dayOfWeek == DayOfWeek.WEDNESDAY && time.isAfter(LocalTime.of(20, 1))) ||
            (dayOfWeek.ordinal() < DayOfWeek.WEDNESDAY.ordinal())) {
      addNewUpcomingSessionsSaturday();
    } else {
      addNewUpcomingSessionsWednesday();
    }
  }

  @Scheduled(cron = "0 0 20 * * SAT")
  public void addNewUpcomingSessionsSaturday() {
    upcomingSessionsService.addNewWeekOfUpcomingSessions(DayOfWeek.SATURDAY);
  }

  @Scheduled(cron = "0 0 20 * * WED")
  public void addNewUpcomingSessionsWednesday() {
    upcomingSessionsService.addNewWeekOfUpcomingSessions(DayOfWeek.WEDNESDAY);
  }

}
