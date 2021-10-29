package com.comp6000.backend.limits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/limits")
class LimitsController {

  private final LimitsService limitsService;

  @Autowired
  LimitsController(LimitsService limitsService) {
    this.limitsService = limitsService;
  }

  @GetMapping
  LimitsResponse getLimits() {
    return new LimitsResponse(limitsService.getAvailableSeasons());
  }
}
