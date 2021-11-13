package com.comp6000.backend.configuration;

import com.comp6000.backend.generation.events.GenerationEventWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;

import java.util.Map;

@Configuration
public class WebSocketConfiguration {

  private final GenerationEventWebSocketHandler generationEventWebSocketHandler;

  @Autowired
  public WebSocketConfiguration(GenerationEventWebSocketHandler generationEventWebSocketHandler) {
    this.generationEventWebSocketHandler = generationEventWebSocketHandler;
  }

  @Bean
  HandlerMapping handlerMapping() {
    SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
    handlerMapping.setOrder(1);
    handlerMapping.setUrlMap(Map.of(
        "/ws/schematics", generationEventWebSocketHandler
    ));
    return handlerMapping;
  }

}
