package com.codeaddi.scheduler_service.controller;


import com.codeaddi.scheduler_service.controller.db.RowerService;
import com.codeaddi.scheduler_service.model.repository.sessions.entities.Rower;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/rowers")
@RestController
@Slf4j
public class RowerController {
    @Autowired
    RowerService   rowerService;

    @GetMapping("/get_all")
    public ResponseEntity<List<Rower>> getAllRowers(){
        log.info("Retrieving all rowers");
        return ResponseEntity.ok(rowerService.getAllRowers());
    }
}
