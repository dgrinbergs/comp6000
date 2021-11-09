package com.comp6000.backend.greeting;

import org.springframework.context.ApplicationEvent;

public class GreetingEvent extends ApplicationEvent {
  public GreetingEvent(String greeting) {
    super(greeting);
  }
}
