package com.comp6000.backend.genetic.feedback;

import com.comp6000.backend.genetic.GeneticAlgorithmService;
import com.comp6000.backend.genetic.Population;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

  private final GeneticAlgorithmService geneticAlgorithmService;

  private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackController.class);

  @Autowired
  public FeedbackController(GeneticAlgorithmService geneticAlgorithmService) {
    this.geneticAlgorithmService = geneticAlgorithmService;
  }

  @CrossOrigin
  @PostMapping
  ResponseEntity<Population> submitFeedback(@RequestBody FeedbackRequest request) {
    LOGGER.info("Received feedback for {}", request.populationId());
    return ResponseEntity.ok(geneticAlgorithmService.createInitialPopulation());
  }

}
