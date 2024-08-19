package com.codeaddi.scheduler_service.testUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class TestUtils {

  ObjectMapper mapper = new ObjectMapper();

  public String convertToJson(Object object) {
    mapper.registerModule(new JavaTimeModule());

    try {
      return mapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
