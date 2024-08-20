package com.codeaddi.scheduler_service.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rowers")
@RestController
@Slf4j
public class RowerController {

    @GetMapping("/get_all")
    public void getAllRowers(){
        log.info("Retrieving all rowers");}
}
