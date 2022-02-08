package com.comp6000.backend.genetic.feature.building;

import com.comp6000.backend.genetic.feature.bed.Bed;
import com.comp6000.backend.genetic.feature.corner.Corner;
import com.comp6000.backend.genetic.feature.decor.Decor;
import com.comp6000.backend.genetic.feature.door.Door;
import com.comp6000.backend.genetic.feature.doornum.DoorNum;
import com.comp6000.backend.genetic.feature.floor.Floor;
import com.comp6000.backend.genetic.feature.roof.Roof;
import com.comp6000.backend.genetic.feature.wall.Perimeter;
import com.comp6000.backend.genetic.feature.window.Window;

public class Building {

  private final String buildingId;
  private final Floor floor;
  private final Perimeter perimeter;
  private final Window window;
  private final Roof roof;
  private final Door door;
  private final Corner corner;
  private final DoorNum doornum;
  private final Bed bed;
  private final Decor decor;


  protected Building(String buildingId, Floor floor, Perimeter perimeter, Window window, Roof roof, Door door, Corner corner, DoorNum doornum, Bed bed, Decor decor) {
    this.buildingId = buildingId;
    this.floor = floor;
    this.perimeter = perimeter;
    this.window = window;
    this.roof = roof;
    this.door = door;
    this.corner = corner;
    this.doornum = doornum;
    this.bed = bed;
    this.decor = decor;
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

  public Window getWindow() { return window; }

  public Roof getRoof() { return roof; }

  public Door getDoor() { return door; }

  public Corner getCorner() { return corner; }

  public DoorNum getDoorNum() { return doornum; }

  public Bed getBed() { return bed; }

  public Decor getDecor() { return decor; }
}
