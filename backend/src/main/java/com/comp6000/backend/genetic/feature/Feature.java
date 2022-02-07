package com.comp6000.backend.genetic.feature;

import java.util.List;
import java.util.Map;

/**
 * This class represents a feature of a building. This can be a floor, a wall, windows etc.
 */
public interface Feature {

  // A feature can have many variants. For example different wall materials
  List<Map<String, String>> getSchematics();

}
