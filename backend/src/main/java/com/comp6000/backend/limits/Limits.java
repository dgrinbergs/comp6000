package com.comp6000.backend.limits;

public class Limits {

  public enum Season {
    SUMMER, AUTUMN, WINTER, SPRING
  }

  record Building(Building.Height height) {
    record Height(int min, int max) {
    }
  }

}
