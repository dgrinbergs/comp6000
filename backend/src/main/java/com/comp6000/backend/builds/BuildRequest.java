package com.comp6000.backend.builds;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BuildRequest {

  @NotBlank
  private String season;

  @NotNull
  private Building building;

  public String getSeason() {
    return season;
  }

  public void setSeason(String season) {
    this.season = season;
  }

  public Building getBuilding() {
    return building;
  }

  public void setBuilding(Building building) {
    this.building = building;
  }

  public record Building(int height) {}
}
