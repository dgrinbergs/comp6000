package com.comp6000.backend.limits;

enum Seasons {

  SUMMER("Summer"),
  AUTUMN("Autumn"),
  WINTER("Winter"),
  SPRING("Spring")
  ;

  private final String displayName;

  Seasons(String displayName) {
    this.displayName = displayName;
  }

  String getDisplayName() {
    return displayName;
  }
}
