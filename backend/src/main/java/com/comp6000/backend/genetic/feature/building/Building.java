package com.comp6000.backend.genetic.feature.building;

import com.comp6000.backend.genetic.feature.floor.Floor;
import com.comp6000.backend.genetic.feature.wall.Perimeter;

public class Building {

  private final Floor floor;
  private final Perimeter perimeter;

  protected Building(Floor floor, Perimeter perimeter) {
    this.floor = floor;
    this.perimeter = perimeter;
  }

  public Floor getFloor() {
    return floor;
  }

  public Perimeter getPerimeter() {
    return perimeter;
  }
}
