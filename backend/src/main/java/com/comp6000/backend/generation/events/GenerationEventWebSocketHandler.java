package com.comp6000.backend.generation.events;

import com.comp6000.backend.builds.BuildDetails;
import com.comp6000.backend.builds.events.BuildEvent;
import com.comp6000.backend.generation.GenerationDetails;
import com.comp6000.backend.generation.GenerationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class GenerationEventWebSocketHandler implements WebSocketHandler {

  private final ObjectMapper objectMapper;
  private final Flux<BuildEvent> buildEventFlux;
  private final GenerationService generationService;

  @Autowired
  public GenerationEventWebSocketHandler(ObjectMapper objectMapper, Flux<BuildEvent> buildEventFlux, GenerationService generationService) {
    this.objectMapper = objectMapper;
    this.buildEventFlux = buildEventFlux;
    this.generationService = generationService;
  }

  /**
   * Invoked when a new WebSocket connection is established, and allows handling of the session.
   *
   * @param session the session to handle
   * @return indicates when application handling of the session is complete.
   */
  @Override
  public Mono<Void> handle(WebSocketSession session) {
    var messageFlux = buildEventFlux
        .map(event -> {
          try {
            var buildDetails = (BuildDetails) event.getSource();
            var schematic = generationService.generateSchematicForBuild(buildDetails);
            return objectMapper.writeValueAsString(new GenerationDetails(
                buildDetails.getUuid(),
                schematic
            ));
          } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
          }
        })
        .delayElements(Duration.ofMillis(ThreadLocalRandom.current().nextLong(5000)))
        .map(session::textMessage);
    return session.send(messageFlux);
  }
}
