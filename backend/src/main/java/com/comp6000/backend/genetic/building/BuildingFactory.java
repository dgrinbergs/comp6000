package com.comp6000.backend.genetic.building;

import com.comp6000.backend.genetic.feature.Floor;

public class BuildingFactory {

  private Floor floor;

  public BuildingFactory setFloor(Floor floor) {
    this.floor = floor;
    return this;
  }

  public Building build() {
    return new Building(this.floor);
  }

}
