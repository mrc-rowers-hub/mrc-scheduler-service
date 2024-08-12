package com.codeaddi.scheduler_service.controller;

import com.codeaddi.scheduler_service.model.repository.Session;
import com.codeaddi.scheduler_service.model.repository.SessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/sessions")
@RestController
@Slf4j
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping("/get_all_sessions")
    public ResponseEntity<List<Session>> getAllSessions(){
        log.info("Retrieving all sessions");
        return ResponseEntity.ok(sessionRepository.findAll());
    }

}
