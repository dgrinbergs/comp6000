package com.comp6000.backend.genetic.feedback;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

  @CrossOrigin
  @PostMapping
  ResponseEntity<String> submitFeedback() {
    return ResponseEntity.ok().build();
  }

}
