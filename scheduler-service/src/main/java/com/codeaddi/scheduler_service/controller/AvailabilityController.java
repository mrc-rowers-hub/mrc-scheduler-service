package com.codeaddi.scheduler_service.controller;

import com.codeaddi.scheduler_service.controller.db.*;
import com.codeaddi.scheduler_service.controller.mapper.SessionMapper;
import com.codeaddi.scheduler_service.model.http.inbound.AvailabilityDTO;
import com.codeaddi.scheduler_service.model.http.outbound.StandardResponse;
import com.codeaddi.scheduler_service.model.http.outbound.UpcomingAvailabilityDTO;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.PastSessionAvailability;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.UpcomingSessionAvailability;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/session_availability")
@RestController
@Slf4j
public class AvailabilityController {

  @Autowired UpcomingSessionsService upcomingSessionsService;
  @Autowired PastAvailabilityService pastAvailabilityService;
  @Autowired UpcomingAvailabilityService upcomingAvailabilityService;

  @Autowired private SessionsService sessionsService;
  @Autowired private AvailabilityService availabilityService;

  @GetMapping("/get_all_upcoming_sessions")
  public ResponseEntity<List<UpcomingAvailabilityDTO>> getAllUpcomingSessions() {
    List<UpcomingAvailabilityDTO> upcomingAvailabilityDTOS =
        SessionMapper.mapSessionsAndUpcomingAvailFromDBToDTOs(
            sessionsService.getAllSessions(), upcomingSessionsService.getAllUpcomingSessions());
    return ResponseEntity.ok(upcomingAvailabilityDTOS);
  }

  @PostMapping("/save_availability")
  public ResponseEntity<List<StandardResponse>> saveAvailability(
      @RequestBody List<AvailabilityDTO> availabilityData) {
    List<StandardResponse> responses = new ArrayList<>();

    for (AvailabilityDTO availabilityDTO : availabilityData) {
      StandardResponse standardResponse = availabilityService.saveAvailability(availabilityDTO);
      responses.add(standardResponse);
    }

    return ResponseEntity.ok(responses);
  }

  @GetMapping("/get_upcoming_availability")
  public ResponseEntity<List<UpcomingSessionAvailability>> getUpcomingAvailabilityForRower(
      @RequestParam Long rowerId) {
    log.info("Retrieving upcoming availability for rower {}", rowerId);
    return ResponseEntity.ok(upcomingAvailabilityService.getAllAvailabilityForRower(rowerId));
  }

  @GetMapping("/get_rowers_availability")
  public ResponseEntity<List<PastSessionAvailability>> getRowersAvailability() {
    log.info("Retrieving all rowers availability");
    return ResponseEntity.ok(pastAvailabilityService.getAllPastAvailability());
  }
}
