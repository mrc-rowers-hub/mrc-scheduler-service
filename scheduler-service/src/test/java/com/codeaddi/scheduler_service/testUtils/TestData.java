package com.codeaddi.scheduler_service.testUtils;

import com.codeaddi.scheduler_service.model.enums.RowerLevel;
import com.codeaddi.scheduler_service.model.enums.SessionType;
import com.codeaddi.scheduler_service.model.enums.Squad;
import com.codeaddi.scheduler_service.model.http.inbound.AvailabilityDTO;
import com.codeaddi.scheduler_service.model.http.outbound.StandardResponse;
import com.codeaddi.scheduler_service.model.http.outbound.enums.Status;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.Rower;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.Session;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.UpcomingSessionAvailability;
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

  public static Long rowerId = 2L;
  public static Long upcomingSessionId = 1L;
  public static AvailabilityDTO availabilityDTORowerAvailable =
      AvailabilityDTO.builder()
          .availability(true)
          .sessionId(upcomingSessionId)
          .rowerId(rowerId)
          .build();

  public static AvailabilityDTO availabilityDTORowerUnavailable =
      AvailabilityDTO.builder()
          .availability(false)
          .sessionId(upcomingSessionId)
          .rowerId(rowerId)
          .build();
  public static UpcomingSessionAvailability existingAvailability =
      UpcomingSessionAvailability.builder().rowerId(2L).upcomingSessionId(1L).build();
  public static StandardResponse standardResponseAvailabilityAdded =
      StandardResponse.builder()
          .id(TestData.availabilityDTORowerAvailable.getRowerId().toString())
          .status(Status.SUCCESS)
          .message("Availability added")
          .build();
  public static StandardResponse standardResponseAvailabilityNotUpdated =
      StandardResponse.builder()
          .id(TestData.availabilityDTORowerAvailable.getRowerId().toString())
          .status(Status.SUCCESS)
          .message("Availability update - no action, already available")
          .build();
  public static StandardResponse standardResponseUnvailabilityNotAdded =
          StandardResponse.builder()
                  .id(TestData.availabilityDTORowerAvailable.getRowerId().toString())
                  .status(Status.SUCCESS)
                  .message("Rower unavailable - no availability saved")
                  .build();
  public static StandardResponse standardResponseAvailabilityUpdatedToUnavailable =
      StandardResponse.builder()
          .id(TestData.availabilityDTORowerAvailable.getRowerId().toString())
          .status(Status.SUCCESS)
          .message("Availability update - removed")
          .build();
  public static Rower rower1 = Rower.builder().name("Joe Bloggs").squad(Squad.MENS).level(RowerLevel.INTERMEDIATE).build();

}
