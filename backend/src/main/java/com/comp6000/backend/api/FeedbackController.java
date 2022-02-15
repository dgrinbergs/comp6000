package com.comp6000.backend.api;

import com.comp6000.backend.genetic.GeneticAlgorithmService;
import com.comp6000.backend.genetic.Population;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
class FeedbackController {

  private final GeneticAlgorithmService geneticAlgorithmService;

  private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackController.class);

  @Autowired
  FeedbackController(GeneticAlgorithmService geneticAlgorithmService) {
    this.geneticAlgorithmService = geneticAlgorithmService;
  }

  @CrossOrigin
  @PostMapping
  Mono<Population> submitFeedback(@RequestBody FeedbackRequest request) {
    LOGGER.info("Received feedback for {}", request.populationId());
    return geneticAlgorithmService.createInitialPopulation();
  }

  private record FeedbackRequest(
      String populationId, // The population which this feedback is for
      List<String> selected // The buildings that the user has selected as their favourites
  ) {
  }

}
