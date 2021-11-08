package com.comp6000.backend.greeting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
public class SSEController {

  @GetMapping(value = "/sse/time", produces = TEXT_EVENT_STREAM_VALUE)
  public Flux<String> timestampStream() {
    return Flux.interval(Duration.ofSeconds(1)).map(l -> LocalTime.now().toString());
  }

}
