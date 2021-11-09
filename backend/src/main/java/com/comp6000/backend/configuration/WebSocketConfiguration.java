package com.comp6000.backend.configuration;

import com.comp6000.backend.builds.events.BuildEventWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;

import java.util.Map;

@Configuration
public class WebSocketConfiguration {

  private final BuildEventWebSocketHandler buildEventWebSocketHandler;

  @Autowired
  public WebSocketConfiguration(BuildEventWebSocketHandler buildEventWebSocketHandler) {
    this.buildEventWebSocketHandler = buildEventWebSocketHandler;
  }

  @Bean
  HandlerMapping handlerMapping() {
    SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
    handlerMapping.setOrder(1);
    handlerMapping.setUrlMap(Map.of(
        "/ws/builds", buildEventWebSocketHandler
    ));
    return handlerMapping;
  }

}
