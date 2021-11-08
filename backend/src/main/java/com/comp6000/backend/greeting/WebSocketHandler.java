package com.comp6000.backend.greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

@Component
public class WebSocketHandler implements org.springframework.web.reactive.socket.WebSocketHandler {

  private final EventService eventService;

  @Autowired
  public WebSocketHandler(EventService eventService) {
    this.eventService = eventService;
  }

  @Override
  public Mono<Void> handle(WebSocketSession session) {
    return session.send(eventService.eventProducer().map(session::textMessage));
  }
}
