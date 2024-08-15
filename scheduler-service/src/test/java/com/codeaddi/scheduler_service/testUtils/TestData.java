package com.codeaddi.scheduler_service.testUtils;

import com.codeaddi.scheduler_service.model.enums.RowerLevel;
import com.codeaddi.scheduler_service.model.enums.SessionType;
import com.codeaddi.scheduler_service.model.enums.Squad;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.Session;
import java.time.LocalTime;
import java.util.List;

public class TestData {
  public static Long validId = 1L;
  public static Long unknownId = 5L;
  public static Session unknownSession =
      Session.builder()
          .id(3L)
          .day("TUESDAY")
          .startTime(LocalTime.NOON)
          .endTime(LocalTime.MIDNIGHT)
          .squad(Squad.WOMENS)
          .level(RowerLevel.NOVICE)
          .sessionType(SessionType.ERG)
          .build();
  public static Session validSession =
      Session.builder()
          .id(validId)
          .day("MONDAY")
          .startTime(LocalTime.NOON)
          .endTime(LocalTime.MIDNIGHT)
          .squad(Squad.DEVELOPMENT)
          .level(RowerLevel.DEVELOPMENT)
          .sessionType(SessionType.WATER)
          .build();
  public static Session validSessionReplacement =
      Session.builder()
          .id(validId)
          .day("MONDAY")
          .startTime(LocalTime.NOON)
          .endTime(LocalTime.MIDNIGHT)
          .squad(Squad.WOMENS)
          .level(RowerLevel.NOVICE)
          .sessionType(SessionType.WATER)
          .build();
  public static Session session2 =
      Session.builder()
          .id(2L)
          .day("MONDAY")
          .startTime(LocalTime.NOON)
          .endTime(LocalTime.MIDNIGHT)
          .squad(Squad.WOMENS)
          .level(RowerLevel.NOVICE)
          .sessionType(SessionType.ERG)
          .build();
  public static List<Session> listOfSessions = List.of(validSession, session2);
}
