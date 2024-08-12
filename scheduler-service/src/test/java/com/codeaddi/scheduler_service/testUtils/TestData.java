package com.codeaddi.scheduler_service.testUtils;

import com.codeaddi.scheduler_service.model.enums.RowerLevel;
import com.codeaddi.scheduler_service.model.enums.SessionType;
import com.codeaddi.scheduler_service.model.enums.Squad;
import com.codeaddi.scheduler_service.model.repository.Session;

import java.time.LocalTime;
import java.util.List;

public class TestData {
    public static Session unknownSession = Session.builder().id(3L).day("TUESDAY").startTime(LocalTime.NOON).endTime(LocalTime.MIDNIGHT).squad(Squad.WOMENS).level(RowerLevel.NOVICE).sessionType(SessionType.ERG).build();
    public static  Session session1 = Session.builder().id(1L).day("MONDAY").startTime(LocalTime.NOON).endTime(LocalTime.MIDNIGHT).squad(Squad.DEVELOPMENT).level(RowerLevel.DEVELOPMENT).sessionType(SessionType.WATER).build();
    public static  Session session2 = Session.builder().id(2L).day("MONDAY").startTime(LocalTime.NOON).endTime(LocalTime.MIDNIGHT).squad(Squad.WOMENS).level(RowerLevel.NOVICE).sessionType(SessionType.ERG).build();
    public static List<Session> listOfSessions = List.of(session1, session2);
}
