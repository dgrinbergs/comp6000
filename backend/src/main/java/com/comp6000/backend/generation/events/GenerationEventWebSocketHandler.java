package com.comp6000.backend.generation.events;

import com.comp6000.backend.generation.GenerationEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class GenerationEventWebSocketHandler implements WebSocketHandler {

  private final ObjectMapper objectMapper;
  private final Flux<GenerationEvent> generationEventFlux;

  @Autowired
  public GenerationEventWebSocketHandler(ObjectMapper objectMapper, Flux<GenerationEvent> generationEventFlux) {
    this.objectMapper = objectMapper;
    this.generationEventFlux = generationEventFlux;
  }

  @Override
  public Mono<Void> handle(WebSocketSession session) {
    var messageFlux = generationEventFlux.map(event -> {
      try {
        return objectMapper.writeValueAsString(event.getSource());
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }
    }).map(session::textMessage);
    return session.send(messageFlux);
  }
}
