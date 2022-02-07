package com.comp6000.backend.genetic.feature.building;

import com.comp6000.backend.genetic.feature.floor.Floor;
import com.comp6000.backend.genetic.feature.wall.Perimeter;

public class BuildingFactory {

  private Floor floor;
  private Perimeter perimeter;

  public BuildingFactory setFloor(Floor floor) {
    this.floor = floor;
    return this;
  }

  public BuildingFactory setPerimeter(Perimeter perimeter) {
    this.perimeter = perimeter;
    return this;
  }

  public Building build() {
    return new Building(floor, perimeter);
  }

}
