package com.comp6000.backend.genetic.feature;

import java.util.Map;

public class Building {

  private final String buildingId;
  private final Map<Feature, Material> features;

  public Building(String buildingId, Map<Feature, Material> features) {
    this.buildingId = buildingId;
    this.features = features;
  }

  public String getBuildingId() {
    return buildingId;
  }

  public Map<Feature, Material> getFeatures() {
    return features;
  }
}
