package com.codeaddi.scheduler_service.controller;

import com.codeaddi.scheduler_service.controller.db.SessionsClient;
import com.codeaddi.scheduler_service.model.http.StandardResponse;
import com.codeaddi.scheduler_service.model.http.enums.Status;
import com.codeaddi.scheduler_service.model.repository.Session;
import com.codeaddi.scheduler_service.model.repository.SessionRepository;
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
    private SessionsClient sessionsClient;

    @GetMapping("/get_all_sessions")
    public ResponseEntity<List<Session>> getAllSessions(){
        log.info("Retrieving all sessions");
        return ResponseEntity.ok(sessionsClient.getAllSessions());
    }

    @PutMapping("/update_session")
    public ResponseEntity<StandardResponse> updateSession(@RequestBody Session newSession){
        log.info("UPDATE REQUEST RECEIVED");

        try{
            sessionsClient.replaceSession(newSession);
            return ResponseEntity.ok().body(StandardResponse.builder().status(Status.SUCCESS).message("Session replaced").build());
        } catch(EntityNotFoundException e){
            log.error("Session with id {} not found, creating new session", newSession.getId());
            sessionsClient.addSession(newSession);
            return ResponseEntity.badRequest().body(StandardResponse.builder().status(Status.SUCCESS_WITH_WARNING).message("Session not found, new session made").build());
        }
    }

// delete session

}
