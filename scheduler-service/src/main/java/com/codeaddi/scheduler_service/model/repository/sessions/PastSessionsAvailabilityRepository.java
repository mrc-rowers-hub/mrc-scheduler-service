package com.codeaddi.scheduler_service.model.repository.sessions;

import com.codeaddi.scheduler_service.model.repository.sessions.entities.PastSessionAvailability;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.UpcomingSessionAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PastSessionsAvailabilityRepository extends JpaRepository<PastSessionAvailability, Long> {


}

