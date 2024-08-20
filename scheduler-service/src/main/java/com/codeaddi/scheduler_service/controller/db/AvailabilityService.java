package com.codeaddi.scheduler_service.controller.db;

import com.codeaddi.scheduler_service.model.http.inbound.AvailabilityDTO;
import com.codeaddi.scheduler_service.model.http.outbound.StandardResponse;
import com.codeaddi.scheduler_service.model.http.outbound.enums.Status;
import com.codeaddi.scheduler_service.model.repository.sessions.UpcomingSessionsAvailabilityRepository;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.UpcomingSessionAvailability;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AvailabilityService {
  @Autowired UpcomingSessionsAvailabilityRepository upcomingSessionsAvailabilityRepository;

  @Transactional
  public StandardResponse saveAvailability(AvailabilityDTO availabilityDTO) {
    String responseMessage;

    if (!rowerExists()) {
      responseMessage = "Rower does not exist in DB";
      log.info(
          "Session ID: {}, RowerID: {}, Message: {}",
          availabilityDTO.getSessionId(),
          availabilityDTO.getRowerId(),
          responseMessage);

      return StandardResponse.builder()
          .id(availabilityDTO.getRowerId().toString())
          .status(Status.REJECTED)
          .message("Rower does not exist in DB")
          .build();
    }

    Map<Boolean, String> updateMadeAndResponseMessage =
        getResponseMessageForAvailability(availabilityDTO);

    boolean updateAvailability = updateMadeAndResponseMessage.containsKey(Boolean.TRUE);

    if (updateAvailability) {
      responseMessage = updateMadeAndResponseMessage.get(Boolean.TRUE);
    } else {
      if (availabilityDTO.isAvailability()) {
        UpcomingSessionAvailability availability =
            UpcomingSessionAvailability.builder()
                .upcomingSessionId(availabilityDTO.getSessionId())
                .rowerId(availabilityDTO.getRowerId())
                .build();
        upcomingSessionsAvailabilityRepository.save(availability);
        responseMessage = "Availability added";
      } else {
        responseMessage = "Rower unavailable - no availability saved";
      }
    }

    log.info(
        "Session ID: {}, RowerID: {}, Message: {}",
        availabilityDTO.getSessionId(),
        availabilityDTO.getRowerId(),
        responseMessage);

    return StandardResponse.builder()
        .id(availabilityDTO.getRowerId().toString())
        .status(Status.SUCCESS)
        .message(responseMessage)
        .id(availabilityDTO.getSessionId().toString())
        .build();
  }

  private Map<Boolean, String> getResponseMessageForAvailability(AvailabilityDTO availabilityDTO) {
    UpcomingSessionAvailability existingAvailability =
        upcomingSessionsAvailabilityRepository
            .findUpcomingSessionAvailabilitiesByRowerIdAndUpcomingSessionId(
                availabilityDTO.getRowerId(), availabilityDTO.getSessionId());

    if (existingAvailability != null) {
      boolean isAvailable = availabilityDTO.isAvailability();

      if (!isAvailable) {
        upcomingSessionsAvailabilityRepository.delete(existingAvailability);
      }

      return Map.of(Boolean.TRUE, getUpdatedResponseMessage(isAvailable));

    } else {
      return Map.of(Boolean.FALSE, "null");
    }
  }

  private String getUpdatedResponseMessage(boolean updating) {
    if (updating) {
      return "Availability update - no action, already available";
    } else {
      return "Availability update - removed";
    }
  }

  private boolean rowerExists() {
    // rower DB - check the rower exists
    // Todo - update in mrc-scheduler-service #28
    return true;
  }
}
