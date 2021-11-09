package com.comp6000.backend.greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/greeting")
class GreetingController {

  private final EventService eventService;

  @Autowired
  public GreetingController(EventService eventService) {
    this.eventService = eventService;
  }

  @PostMapping
  Mono<String> receiveGreeting(@RequestBody Map<String, String> body) {
    var message = body.get("message");
    eventService.saveGreeting(message);
    return Mono.just(message);
  }

}
