package com.comp6000.backend.builds.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class BuildEventWebSocketHandler implements WebSocketHandler {

  private final ObjectMapper objectMapper;
  private final Flux<BuildEvent> buildEventFlux;

  public BuildEventWebSocketHandler(ObjectMapper objectMapper, Flux<BuildEvent> buildEventFlux) {
    this.objectMapper = objectMapper;
    this.buildEventFlux = buildEventFlux;
  }

  @Override
  public Mono<Void> handle(WebSocketSession session) {
    var messageFlux = buildEventFlux.map(event -> {
      try {
        return objectMapper.writeValueAsString(event.getSource());
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }
    }).map(session::textMessage);
    return session.send(messageFlux);
  }
}
