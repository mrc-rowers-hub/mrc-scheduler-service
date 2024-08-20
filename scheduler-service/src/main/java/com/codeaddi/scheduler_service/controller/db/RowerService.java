package com.codeaddi.scheduler_service.controller.db;

import com.codeaddi.scheduler_service.model.repository.sessions.RowersRepository;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.Rower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RowerService {
    @Autowired
    RowersRepository rowersRepository;

    public List<Rower> getAllRowers(){
       return  rowersRepository.findAll();
    }
}
