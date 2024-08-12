package com.codeaddi.scheduler_service.testUtils;

import com.codeaddi.scheduler_service.model.repository.Session;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;

public class TestUtils {

    ObjectMapper mapper = new ObjectMapper();

    public String convertSessionsToJson(List<Session> sessions)  {
        mapper.registerModule(new JavaTimeModule());

        try {
            return mapper.writeValueAsString(sessions);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
