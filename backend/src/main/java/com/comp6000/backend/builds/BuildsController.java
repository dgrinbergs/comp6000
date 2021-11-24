package com.comp6000.backend.builds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildsController {

  private final BuildService buildService;

  @Autowired
  public BuildsController(BuildService buildService) {
    this.buildService = buildService;
  }

  @PostMapping("/api/builds")
  @CrossOrigin
  ResponseEntity<BuildDetails> createBuild(@RequestBody BuildDetails buildDetails) {
    return ResponseEntity.ok(buildService.saveBuildDetails(buildDetails));
  }
}