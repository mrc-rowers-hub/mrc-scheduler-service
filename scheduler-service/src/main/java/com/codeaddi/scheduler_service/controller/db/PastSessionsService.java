package com.codeaddi.scheduler_service.controller.db;

import com.codeaddi.scheduler_service.model.repository.sessions.PastSessionsRepository;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.PastSession;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PastSessionsService {

  @Autowired PastSessionsRepository pastSessionsRepository;

  public List<PastSession> getAllPastSessions() {
    return pastSessionsRepository.findAll();
  }

  public List<PastSession> getAllPastSessionsNotYetOccurred() {
    return getAllPastSessions().stream()
        .filter(session -> !session.getDate().before(Date.from(Instant.now())))
        .toList();
  }
}
