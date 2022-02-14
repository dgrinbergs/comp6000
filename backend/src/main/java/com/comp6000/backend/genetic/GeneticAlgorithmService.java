package com.comp6000.backend.genetic;

import com.comp6000.backend.genetic.feature.Building;
import com.comp6000.backend.genetic.feature.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class GeneticAlgorithmService {

  private static final int SIZE = 10;

  private final Flux<Building> buildingFlux;

  @Autowired
  public GeneticAlgorithmService(Flux<Building> buildingFlux) {
    this.buildingFlux = buildingFlux;
  }

  public Population createInitialPopulation() {
    List<Building> buildings = new ArrayList<>(SIZE);

    for (int i = 0; i < SIZE; i++) {
      var regularBlocks = Material.regularBlocks();
      var glassBlocks = Material.glassBlocks();
      var doorBlocks = Material.doorBlocks();
      var bedBlocks = Material.bedBlocks();

      var random = ThreadLocalRandom.current();

      var cornerBlock = regularBlocks.get(random.nextInt(regularBlocks.size()));
      var floorBlock = regularBlocks.get(random.nextInt(regularBlocks.size()));
      var roofBlock = regularBlocks.get(random.nextInt(regularBlocks.size()));
      var wallBlock = regularBlocks.get(random.nextInt(regularBlocks.size()));
      var bedBlock = bedBlocks.get(random.nextInt(bedBlocks.size()));
      var doorBlock = doorBlocks.get(random.nextInt(doorBlocks.size()));
      var windowBlock = glassBlocks.get(random.nextInt(glassBlocks.size()));

      var building = new Building(
          UUID.randomUUID(),
          cornerBlock, floorBlock, roofBlock, wallBlock, bedBlock, doorBlock, windowBlock
      );

      buildings.add(building);
    }

    publishBuilding(buildings.get(0)); //TODO remove this.

    return new Population(
        UUID.randomUUID().toString(),
        buildings,
        new ArrayList<>()
    );

  }

  private void publishBuilding(Building building) {
    Flux.concat(buildingFlux, Flux.just(building));
  }

  //TODO: compare users favourite builds against the rest of the population

}
