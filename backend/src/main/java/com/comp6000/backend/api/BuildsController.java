package com.comp6000.backend.api;

import com.comp6000.backend.genetic.GeneticAlgorithmService;
import com.comp6000.backend.genetic.Population;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

  @CrossOrigin
  @PostMapping("done")
  Mono<Void> signalDone(@RequestBody DoneRequest doneRequest) {
    geneticAlgorithmService.signalDone(doneRequest.buildingId());
    return Mono.empty();
  }

  private record DoneRequest(
      String buildingId
  ){}

}