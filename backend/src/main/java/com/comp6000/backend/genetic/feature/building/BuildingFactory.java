package com.comp6000.backend.genetic.feature.building;

import com.comp6000.backend.genetic.feature.bed.Bed;
import com.comp6000.backend.genetic.feature.corner.CornerPerimeter;
import com.comp6000.backend.genetic.feature.decor.Decor;
import com.comp6000.backend.genetic.feature.door.Door;
import com.comp6000.backend.genetic.feature.doornum.DoorNum;
import com.comp6000.backend.genetic.feature.floor.Floor;
import com.comp6000.backend.genetic.feature.roof.Roof;
import com.comp6000.backend.genetic.feature.wall.Perimeter;
import com.comp6000.backend.genetic.feature.window.Window;

import java.util.UUID;

public class BuildingFactory {
  //each feature
  private Floor floor;
  private Perimeter perimeter;
  private Window window;
  private Roof roof;
  private Door door;
  private CornerPerimeter cornerperimeter;
  private DoorNum doornum;
  private Bed bed;
  private Decor decor;

  public BuildingFactory setFloor(Floor floor) {
    this.floor = floor;
    return this;
  }

  public BuildingFactory setPerimeter(Perimeter perimeter) {
    this.perimeter = perimeter;
    return this;
  }

  public BuildingFactory setWindow(Window window) {
    this.window = window;
    return this;
  }

  public BuildingFactory setRoof(Roof roof) {
    this.roof = roof;
    return this;
  }

  public BuildingFactory setDoor(Door door) {
    this.door = door;
    return this;
  }

  public BuildingFactory setCornerPerimeter(CornerPerimeter cornerperimeter) {
    this.cornerperimeter = cornerperimeter;
    return this;
  }

  public BuildingFactory setDoorNum(DoorNum doornum) {
    this.doornum = doornum;
    return this;
  }

  public BuildingFactory setBed(Bed bed) {
    this.bed = bed;
    return this;
  }

  public BuildingFactory setDecor(Decor decor) {
    this.decor = decor;
    return this;
  }

  public Building build() {
    return new Building(UUID.randomUUID().toString(), floor, perimeter, window, roof, door, cornerperimeter, doornum, bed, decor);
  }

}
