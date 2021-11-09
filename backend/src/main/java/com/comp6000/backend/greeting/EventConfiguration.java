package com.comp6000.backend.greeting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class EventConfiguration {

  @Bean
  Executor executor() {
    return Executors.newSingleThreadExecutor();
  }

  @Bean
  Flux<GreetingEvent> greetingEventFlux(GreetingEventPublisher eventPublisher) {
    return Flux.create(eventPublisher).share();
  }
}
