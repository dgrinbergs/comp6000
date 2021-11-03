package com.comp6000.backend.limits;

public class Limits {

  public enum Season {
    SUMMER, AUTUMN, WINTER, SPRING
  }

  public record Building(Building.Height height) {
    public record Height(Integer min, Integer max) {
    }
  }

}
