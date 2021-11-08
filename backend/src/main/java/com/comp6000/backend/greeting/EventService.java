package com.comp6000.backend.greeting;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;

@Service
public class EventService {

  Flux<String> eventProducer() {
    return Flux.interval(Duration.ofSeconds(1)).map(l -> LocalTime.now().toString());
  }

}
