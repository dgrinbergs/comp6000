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

  private static final ObjectMapper json = new ObjectMapper();

  private final Flux<String> eventFlux = Flux.generate(sink -> {
    var event = LocalTime.now().toString();
    try {
      sink.next(json.writeValueAsString(event));
    } catch (JsonProcessingException e) {
      sink.error(e);
    }
  });

  private final Flux<String> intervalFlux = Flux.interval(Duration.ofMillis(1000L))
      .zipWith(eventFlux, (time, event) -> event);

  @Override
  public Mono<Void> handle(WebSocketSession session) {
    return session
        .send(intervalFlux.map(session::textMessage))
        .and(session.receive().map(WebSocketMessage::getPayloadAsText).log());
  }
}
