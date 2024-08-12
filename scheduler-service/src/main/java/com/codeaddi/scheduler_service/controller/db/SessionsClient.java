package com.codeaddi.scheduler_service.controller.db;

import com.codeaddi.scheduler_service.model.repository.Session;
import com.codeaddi.scheduler_service.model.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionsClient {

    @Autowired
    private SessionRepository sessionRepository;

    public List<Session> getAllSessions(){
        return sessionRepository.findAll();
    }
}
