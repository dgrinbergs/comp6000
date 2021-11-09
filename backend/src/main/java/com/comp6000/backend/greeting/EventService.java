package com.comp6000.backend.greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;

@Service
public class EventService {

  private final ApplicationEventPublisher eventPublisher;

  @Autowired
  public EventService(ApplicationEventPublisher eventPublisher) {
    this.eventPublisher = eventPublisher;
  }

  void saveGreeting(String greeting) {
    this.eventPublisher.publishEvent(new GreetingEvent(greeting));
  }

  Flux<String> eventProducer() {
    return Flux.interval(Duration.ofSeconds(1)).map(l -> LocalTime.now().toString());
  }

}
