package com.codeaddi.scheduler_service.controller.db;

import com.codeaddi.scheduler_service.model.repository.Session;
import com.codeaddi.scheduler_service.model.repository.SessionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SessionsService {

    @Autowired
    private SessionRepository sessionRepository;

    public List<Session> getAllSessions(){
        return sessionRepository.findAll();
    }

    public Session findById(Long id){
        return sessionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void replaceSession(Session newSession){
        Session existingSession = findById(newSession.getId());

        log.info("Deleting session: {}", existingSession.toString());

        sessionRepository.deleteById(existingSession.getId());

        log.info("Adding new session: {}", newSession);
        sessionRepository.save(newSession);
    }

    public void addSession(Session newSession){
        log.info("Adding new session: {}", newSession);

        sessionRepository.save(newSession);
    }


}
