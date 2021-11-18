package com.comp6000.backend.builds;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public final class BuildDetails {

  private final UUID uuid;

  private String season;

  @JsonProperty("building")
  private BuildingProperties buildingProperties;

  public BuildDetails() {
    this.uuid = UUID.randomUUID();
  }

  public UUID getUuid() {
    return uuid;
  }

  public String getSeason() {
    return season;
  }

  public void setSeason(String season) {
    this.season = season;
  }

  public BuildingProperties getBuildingProperties() {
    return buildingProperties;
  }

  public void setBuildingProperties(BuildingProperties buildingProperties) {
    this.buildingProperties = buildingProperties;
  }
}
