package com.comp6000.backend.greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.socket.WebSocketHandler;
import reactor.core.publisher.Mono;

@Component
public class GreetingHandler {

  public Mono<ServerResponse> hello(ServerRequest request) {
    return ServerResponse
        .ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(new Greeting("Hello, Spring!")));
  }

}
