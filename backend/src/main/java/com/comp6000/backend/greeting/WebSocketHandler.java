package com.comp6000.backend.greeting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class WebSocketHandler implements org.springframework.web.reactive.socket.WebSocketHandler {

  private final ObjectMapper objectMapper;
  private final Flux<GreetingEvent> greetingEventFlux;

  @Autowired
  public WebSocketHandler(GreetingEventPublisher eventPublisher, ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
    this.greetingEventFlux = Flux.create(eventPublisher).share();
  }

  @Override
  public Mono<Void> handle(WebSocketSession session) {
    var messageFlux = greetingEventFlux.map(event -> {
      try {
        return objectMapper.writeValueAsString(event.getSource());
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }
    }).map(session::textMessage);
    return session.send(messageFlux);
  }
}
