package com.comp6000.backend.builds;

import com.comp6000.backend.genetic.GeneticAlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BuildsController {

  private final BuildService buildService;
  private final GeneticAlgorithmService geneticAlgorithmService;

  @Autowired
  public BuildsController(BuildService buildService, GeneticAlgorithmService geneticAlgorithmService) {
    this.buildService = buildService;
    this.geneticAlgorithmService = geneticAlgorithmService;
  }

  @PostMapping("/api/builds")
  @CrossOrigin
  ResponseEntity<Map<String, Object>> createBuild(@RequestBody BuildDetails buildDetails) {
    return ResponseEntity.ok(Map.of(
        "buildDetails", buildService.saveBuildDetails(buildDetails),
        "initialPopulation", geneticAlgorithmService.createInitialPopulation()
    ));
  }
}