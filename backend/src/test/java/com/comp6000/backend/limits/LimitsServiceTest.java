package com.comp6000.backend.limits;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LimitsServiceTest {

  private LimitsService limitsService;

  private final Integer buildingHeightMax = 40;
  private final Integer buildingHeightMin = 10;

  @BeforeEach
  void setUp() {
    limitsService = new LimitsService(buildingHeightMin, buildingHeightMax);
  }

  @Test
  void getLimits() {
    var limits = limitsService.getLimits();
    assertThat(limits).hasSize(2);
    assertThat(limits).containsKeys("seasons", "building");

    var seasons = (List<String>) limits.get("seasons");
    assertThat(seasons).containsExactlyInAnyOrder("summer", "winter", "spring", "autumn");

    var building = (Limits.Building) limits.get("building");
    assertThat(building.height().min()).isEqualTo(buildingHeightMin);
    assertThat(building.height().max()).isEqualTo(buildingHeightMax);
  }

  @Test
  void getAvailableSeasons() {
    var availableSeasons = limitsService.getAvailableSeasons();
    assertThat(availableSeasons).containsExactlyInAnyOrder("summer", "winter", "spring", "autumn");
  }

  @Test
  void getBuildingLimits() {
    var building = limitsService.getBuildingLimits();
    assertThat(building.height().min()).isEqualTo(buildingHeightMin);
    assertThat(building.height().max()).isEqualTo(buildingHeightMax);
  }
}