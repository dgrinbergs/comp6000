package com.comp6000.backend.builds;

import com.comp6000.backend.builds.events.BuildEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
public class BuildsController {

  private final BuildService buildService;
  private final Flux<BuildEvent> buildEventFlux;
  private final ObjectMapper objectMapper;

  @Autowired
  public BuildsController(BuildService buildService, Flux<BuildEvent> buildEventFlux, ObjectMapper objectMapper) {
    this.buildService = buildService;
    this.buildEventFlux = buildEventFlux;
    this.objectMapper = objectMapper;
  }

  @PostMapping("/api/builds")
  Mono<BuildDetails> createBuild(@RequestBody BuildDetails buildDetails) {
    return buildService.saveBuildDetails(buildDetails);
  }

  @GetMapping(value = "/stream/builds", produces = TEXT_EVENT_STREAM_VALUE)
  public Flux<String> buildEventStream() {
    return this.buildEventFlux.map(event -> {
      try {
        return objectMapper.writeValueAsString(event.getSource());
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }
    });
  }
}