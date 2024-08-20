package com.codeaddi.scheduler_service.controller.db;

import com.codeaddi.scheduler_service.model.repository.sessions.UpcomingSessionsAvailabilityRepository;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.UpcomingSessionAvailability;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UpcomingAvailabilityService {

  @Autowired UpcomingSessionsAvailabilityRepository upcomingSessionsAvailabilityRepository;

  public List<UpcomingSessionAvailability> getAllAvailabilityForRower(Long rowerId) {
    return upcomingSessionsAvailabilityRepository.findUpcomingSessionAvailabilitiesByRowerId(
        rowerId);
  }
}
