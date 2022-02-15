package com.comp6000.backend.api;

import com.comp6000.backend.genetic.GeneticAlgorithmService;
import com.comp6000.backend.genetic.Population;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/builds")
class BuildsController {

  private final GeneticAlgorithmService geneticAlgorithmService;

  @Autowired
  BuildsController(GeneticAlgorithmService geneticAlgorithmService) {
    this.geneticAlgorithmService = geneticAlgorithmService;
  }

  @CrossOrigin
  @PostMapping
  Mono<Population> createBuild() {
    return geneticAlgorithmService.createInitialPopulation();
  }
}