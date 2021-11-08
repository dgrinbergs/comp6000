package com.comp6000.backend.greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
public class SSEController {

  private final EventService eventService;

  @Autowired
  public SSEController(EventService eventService) {
    this.eventService = eventService;
  }

  @GetMapping(value = "/sse/time", produces = TEXT_EVENT_STREAM_VALUE)
  public Flux<String> timestampStream() {
    return eventService.eventProducer();
  }

}
