package com.comp6000.backend.greeting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class EventConfiguration {

  @Bean
  Executor executor() {
    return Executors.newSingleThreadExecutor();
  }
}