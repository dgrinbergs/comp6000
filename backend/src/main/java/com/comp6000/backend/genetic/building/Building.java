package com.comp6000.backend.genetic.building;

import com.comp6000.backend.genetic.feature.Feature;
import com.comp6000.backend.genetic.feature.Floor;

public class Building {

  private final Feature floor;

  protected Building(Floor floor) {
    this.floor = floor;
  }

  public Feature getFloor() {
    return floor;
  }
}
