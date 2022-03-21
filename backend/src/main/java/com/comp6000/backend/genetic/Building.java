package com.comp6000.backend.genetic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record Building(
  String id,
  @JsonIgnore Material cornerBlock,
  @JsonIgnore Material floorBlock,
  @JsonIgnore Material roofBlock,
  @JsonIgnore Material wallBlock,
  @JsonIgnore Material bedBlock,
  @JsonIgnore Material doorBlock,
  @JsonIgnore Material windowBlock
){

  @JsonProperty("features")
  public Map<String, Material> features() {
    return Map.of(
        "corner", cornerBlock,
        "floor", floorBlock,
        "roof", roofBlock,
        "wall", wallBlock,
        "bed", bedBlock,
        "door", doorBlock,
        "window", windowBlock
    );
  }

}
