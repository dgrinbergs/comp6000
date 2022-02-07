package com.comp6000.backend.genetic.feature.wall;

public class Perimeter {

  private final Wall north;
  private final Wall east;
  private final Wall south;
  private final Wall west;

  public Perimeter(Wall wall) {
    this.north = wall;
    this.east = wall;
    this.south = wall;
    this.west = wall;
  }

  public Perimeter(Wall north, Wall east, Wall south, Wall west) {
    this.north = north;
    this.east = east;
    this.south = south;
    this.west = west;
  }

  public Wall getNorth() {
    return north;
  }

  public Wall getEast() {
    return east;
  }

  public Wall getSouth() {
    return south;
  }

  public Wall getWest() {
    return west;
  }
}
