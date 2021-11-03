package com.comp6000.backend.limits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("limits")
class LimitsController {

  private final LimitsService limitsService;

  @Autowired
  LimitsController(LimitsService limitsService) {
    this.limitsService = limitsService;
  }

  @GetMapping
  ResponseEntity<Map<String, Object>> getLimits() {
    return ResponseEntity.ok(limitsService.getLimits());
  }
}


