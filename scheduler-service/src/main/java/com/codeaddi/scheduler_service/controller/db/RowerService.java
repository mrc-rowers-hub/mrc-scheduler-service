package com.codeaddi.scheduler_service.controller.db;

import com.codeaddi.scheduler_service.model.repository.sessions.RowersRepository;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.Rower;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RowerService {
  @Autowired RowersRepository rowersRepository;

  public List<Rower> getAllRowers() {
    return rowersRepository.findAll();
  }
}
