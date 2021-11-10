package com.comp6000.backend.configuration;

import com.comp6000.backend.builds.events.BuildEvent;
import com.comp6000.backend.builds.events.BuildEventPublisher;
import com.comp6000.backend.generation.GenerationEvent;
import com.comp6000.backend.generation.GenerationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
class BeanConfiguration {

  @Bean
  Executor executor() {
    return Executors.newSingleThreadExecutor();
  }

  @Bean
  Flux<BuildEvent> buildEventFlux(BuildEventPublisher eventPublisher) {
    return Flux.create(eventPublisher).share();
  }

  @Bean
  Flux<GenerationEvent> generationEventFlux(GenerationEventPublisher eventPublisher) {
    return Flux.create(eventPublisher).share();
  }
}
