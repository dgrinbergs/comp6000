package com.comp6000.backend.greeting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalTime;

@Component
public class WebSocketHandler implements org.springframework.web.reactive.socket.WebSocketHandler {

  private final Flux<String> eventFlux = Flux.interval(Duration.ofSeconds(1)).map(l -> LocalTime.now().toString());

  @Override
  public Mono<Void> handle(WebSocketSession session) {
    return session
        .send(eventFlux.map(session::textMessage))
        .and(session.receive().map(WebSocketMessage::getPayloadAsText).log());
  }
}
