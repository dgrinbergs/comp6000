package com.comp6000.backend.greeting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
public class SSEController {

  private final Flux<GreetingEvent> greetingEventFlux;
  private final ObjectMapper objectMapper;

  @Autowired
  public SSEController(Flux<GreetingEvent> greetingEventFlux, ObjectMapper objectMapper) {
    this.greetingEventFlux = greetingEventFlux;
    this.objectMapper = objectMapper;
  }

  @GetMapping(value = "/sse/greetings", produces = TEXT_EVENT_STREAM_VALUE)
  public Flux<String> timestampStream() {
    return this.greetingEventFlux.map(event -> {
      try {
        return objectMapper.writeValueAsString(event.getSource());
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }
    });
  }

}
