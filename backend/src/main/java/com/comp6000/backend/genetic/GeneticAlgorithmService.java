package com.comp6000.backend.genetic;

import com.comp6000.backend.genetic.feature.Building;
import com.comp6000.backend.genetic.feature.Feature;
import com.comp6000.backend.genetic.feature.Material;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class GeneticAlgorithmService {

  private static final int SIZE = 10;

  public Population createInitialPopulation() {
    List<Building> buildings = new ArrayList<>(SIZE);

    for (int i = 0; i < SIZE; i++) {
      var features = new HashMap<Feature, Material>();
      for(var feature : Feature.values()) {
        features.put(feature, feature.getRandomMaterial());
      }

      var building = new Building(
          UUID.randomUUID().toString(),
          features
      );

      buildings.add(building);
    }

    return new Population(
        UUID.randomUUID().toString(),
        buildings,
        new ArrayList<>()
    );
  }

  //TODO: compare users favourite builds against the rest of the population

}
