package com.codeaddi.scheduler_service.controller;

import com.codeaddi.scheduler_service.controller.db.SessionsService;
import com.codeaddi.scheduler_service.model.http.StandardResponse;
import com.codeaddi.scheduler_service.model.http.enums.Status;
import com.codeaddi.scheduler_service.model.repository.Session;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/standard_sessions")
@RestController
@Slf4j
public class SessionController {

    @Autowired
    private SessionsService sessionsService;

    @GetMapping("/get_all_sessions")
    public ResponseEntity<?> getAllSessions(){
        log.info("Retrieving all sessions");

        List<Session> allSessions = sessionsService.getAllSessions();

        if(allSessions.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(sessionsService.getAllSessions());
        }
    }

    @PutMapping("/update_session")
    public ResponseEntity<StandardResponse> updateSession(@RequestBody Session newSession){
        log.info("UPDATE REQUEST RECEIVED");

        try{
            sessionsService.replaceSession(newSession);
            return ResponseEntity.ok().body(StandardResponse.builder().status(Status.SUCCESS).message("Session replaced").build());
        } catch(EntityNotFoundException e){
            log.error("Session with id {} not found, creating new session", newSession.getId());
            sessionsService.addSession(newSession);
            return ResponseEntity.ok().body(StandardResponse.builder().status(Status.SUCCESS_WITH_WARNING).message("Session not found, new session made").build());
        }
    }

// delete session

}
