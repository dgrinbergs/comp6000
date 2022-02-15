package com.comp6000.backend.genetic;

import java.util.List;

public class Population {

  private final List<Building> buildings;
  private final List<String> selected;

  public Population(List<Building> buildings, List<String> selected) {
    this.buildings = buildings;
    this.selected = selected;
  }

  public Population() {
    this.buildings = List.of();
    this.selected = List.of();
  }

  public List<Building> getBuildings() {
    return buildings;
  }

  public List<String> getSelected() {
    return selected;
  }
}
