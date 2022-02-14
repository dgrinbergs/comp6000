package com.comp6000.backend.builds;

import com.comp6000.backend.genetic.GeneticAlgorithmService;
import com.comp6000.backend.genetic.Population;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/builds")
public class BuildsController {

  private final GeneticAlgorithmService geneticAlgorithmService;

  @Autowired
  public BuildsController(GeneticAlgorithmService geneticAlgorithmService) {
    this.geneticAlgorithmService = geneticAlgorithmService;
  }

  @CrossOrigin
  @PostMapping
  ResponseEntity<Population> createBuild() {
    return ResponseEntity.ok(geneticAlgorithmService.createInitialPopulation());
  }
}