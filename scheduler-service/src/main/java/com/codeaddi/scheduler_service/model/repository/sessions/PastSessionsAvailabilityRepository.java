package com.codeaddi.scheduler_service.model.repository.sessions;

import com.codeaddi.scheduler_service.model.repository.sessions.entities.PastSessionAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PastSessionsAvailabilityRepository
    extends JpaRepository<PastSessionAvailability, Long> {}
