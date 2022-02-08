package com.comp6000.backend.genetic.feature.building;

import com.comp6000.backend.genetic.feature.floor.Floor;
import com.comp6000.backend.genetic.feature.wall.Perimeter;

import java.util.UUID;

public class Building {

  private final UUID uuid;
  private final Floor floor;
  private final Perimeter perimeter;

  protected Building(UUID uuid, Floor floor, Perimeter perimeter) {
    this.uuid = uuid;
    this.floor = floor;
    this.perimeter = perimeter;
  }

  public UUID getUuid() {
    return uuid;
  }

  public Floor getFloor() {
    return floor;
  }

  public Perimeter getPerimeter() {
    return perimeter;
  }
}
