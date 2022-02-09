package com.comp6000.backend.genetic.feature.floor;

import com.comp6000.backend.genetic.feature.Feature;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
public class Floor implements Feature {

  private final String displayName;

  private final String name;

  @JsonIgnore
  private final String path;

  public Floor(String displayName, String name, String path) {
    this.displayName = displayName;
    this.name = name;
    this.path = path;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getName() {
    return name;
  }

  public String getPath() {
    return path;
  }
}
