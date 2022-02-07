package com.comp6000.backend.genetic.feature;

import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.List;
import java.util.Map;

@ConstructorBinding
public class Floor implements Feature {

  private List<Map<String, String>> schematics;

  public Floor(List<Map<String, String>> schematics) {
    this.schematics = schematics;
  }

  @Override
  public List<Map<String, String>> getSchematics() {
    return schematics;
  }
}
