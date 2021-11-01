package com.comp6000.backend.limits;

class Limits {

  enum Seasons {
    SUMMER, AUTUMN, WINTER, SPRING
  }

  record Building(Building.Height height) {
    record Height(int min, int max) {
    }
  }

}
