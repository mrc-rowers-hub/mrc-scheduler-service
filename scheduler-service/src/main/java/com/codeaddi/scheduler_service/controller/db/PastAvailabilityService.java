package com.codeaddi.scheduler_service.controller.db;

import com.codeaddi.scheduler_service.model.repository.sessions.PastSessionsAvailabilityRepository;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.PastSessionAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PastAvailabilityService {
    @Autowired
    PastSessionsAvailabilityRepository pastSessionsAvailabilityRepository;

    public List<PastSessionAvailability> getAllPastAvailability(){
    return pastSessionsAvailabilityRepository.findAll();
    }
}
