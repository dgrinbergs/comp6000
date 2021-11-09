package com.comp6000.backend.greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

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

}
