package com.codeaddi.scheduler_service.controller;

import com.codeaddi.scheduler_service.controller.db.SessionsService;
import com.codeaddi.scheduler_service.controller.db.UpcomingSessionsService;
import com.codeaddi.scheduler_service.controller.mapper.SessionMapper;
import com.codeaddi.scheduler_service.model.http.StandardResponse;
import com.codeaddi.scheduler_service.model.http.UpcomingAvailabilityDTO;
import com.codeaddi.scheduler_service.model.http.enums.Status;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.Session;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/session_availability")
@RestController
@Slf4j
public class AvailabilityController {

    @Autowired
    UpcomingSessionsService upcomingSessionsService;

    @Autowired private SessionsService sessionsService;

    @GetMapping("/get_all_upcoming_sessions")
    public ResponseEntity<List<UpcomingAvailabilityDTO>> getAllUpcomingSessions(){
        List<UpcomingAvailabilityDTO> upcomingAvailabilityDTOS = SessionMapper.mapSessionsAndUpcomingAvailFromDBToDTOs(sessionsService.getAllSessions(), upcomingSessionsService.getAllUpcomingSessions());
        return ResponseEntity.ok(upcomingAvailabilityDTOS);
    }


}
