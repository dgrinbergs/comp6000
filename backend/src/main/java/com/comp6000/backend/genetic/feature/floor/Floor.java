package com.comp6000.backend.genetic.feature.floor;

import com.comp6000.backend.genetic.feature.Feature;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
public class Floor implements Feature {

  private final String name;
  private final String path;

  public Floor(String name, String path) {
    this.name = name;
    this.path = path;
  }

  public String getName() {
    return name;
  }

  public String getPath() {
    return path;
  }
}
