package com.comp6000.backend.genetic.feature.building;

import com.comp6000.backend.genetic.feature.floor.Floor;
import com.comp6000.backend.genetic.feature.wall.Perimeter;

public class Building {

  private final String buildingId;
  private final Floor floor;
  private final Perimeter perimeter;

  protected Building(String buildingId, Floor floor, Perimeter perimeter) {
    this.buildingId = buildingId;
    this.floor = floor;
    this.perimeter = perimeter;
  }

  public String getBuildingId() {
    return buildingId;
  }

  public Floor getFloor() {
    return floor;
  }

  public Perimeter getPerimeter() {
    return perimeter;
  }
}
