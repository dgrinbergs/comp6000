package com.comp6000.backend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

  @GetMapping
  @CrossOrigin
  ResponseEntity<Void> backendAvailable() {
    return ResponseEntity.ok().build();
  }

}
