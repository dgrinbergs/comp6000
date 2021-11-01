package com.comp6000.backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractControllerTest {

  @Autowired
  protected MockMvc mockMvc;

  private final ObjectMapper objectMapper;

  public AbstractControllerTest() {
    objectMapper = new ObjectMapper();
  }

  protected Map<String, Object> parseResponse(String response) {
    try {
      return objectMapper.readValue(response, new TypeReference<>() {});
    } catch(JsonProcessingException e) {
      throw new RuntimeException(String.format("Could not parse response: %s", response));
    }
  }

}
