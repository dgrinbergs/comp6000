package com.comp6000.backend.genetic;

import com.comp6000.backend.genetic.feature.Building;

import java.util.List;

public class Population {

  private final String populationId;
  private final List<Building> buildings;
  private final List<String> selected;

  public Population(String populationId, List<Building> buildings, List<String> selected) {
    this.populationId = populationId;
    this.buildings = buildings;
    this.selected = selected;
  }

  public String getPopulationId() {
    return populationId;
  }

  public List<Building> getBuildings() {
    return buildings;
  }

  public List<String> getSelected() {
    return selected;
  }
}
