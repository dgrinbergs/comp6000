package com.comp6000.backend.genetic;

import com.comp6000.backend.grpc.BackendGrpcServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class GeneticAlgorithmService {

  private static final int SIZE = 10;

  private static final Logger LOGGER = LoggerFactory.getLogger(GeneticAlgorithmService.class);

  private Population currentPopulation;

  private final BackendGrpcServiceImpl grpcService;

  @Autowired
  public GeneticAlgorithmService(BackendGrpcServiceImpl grpcService) {
    this.grpcService = grpcService;
    currentPopulation = new Population();
  }

  public Mono<Population> createInitialPopulation() {
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
          UUID.randomUUID().toString(),
          cornerBlock, floorBlock, roofBlock, wallBlock, bedBlock, doorBlock, windowBlock
      );

      buildings.add(building);
    }

    var population = new Population(
        buildings,
        List.of()
    );

    currentPopulation = population;

    return Mono.just(population);
  }

  public Mono<Population> acceptFeedback(List<String> selectedBuildings) {
    var currentBuildings = this.currentPopulation.getBuildings();
    var newBuildings = currentBuildings.stream()
        .filter(building -> selectedBuildings.contains(building.id()))
        .collect(Collectors.toCollection(ArrayList::new)
        );

    var random = ThreadLocalRandom.current();

    while (newBuildings.size() < SIZE) {

      // tournament selection
      var tournament = new ArrayList<Building>();

      while (tournament.size() < SIZE) {
        var parentA = currentBuildings.get(random.nextInt(currentBuildings.size()));
        var parentB = currentBuildings.get(random.nextInt(currentBuildings.size()));

        if (selectedBuildings.contains(parentA.id())) {
          tournament.add(parentA);
          continue;
        } else if (selectedBuildings.contains(parentB.id())) {
          tournament.add(parentB);
          continue;
        }

        // catches the case if both parents are the same
        // or neither of the parents are elite
        tournament.add(parentA);
      }

      // crossover
      var parentA = tournament.get(random.nextInt(tournament.size()));
      var parentB = tournament.get(random.nextInt(tournament.size()));

      var childA = new Building(
          UUID.randomUUID().toString(),
          random.nextBoolean() ? parentA.cornerBlock() : parentB.cornerBlock(),
          random.nextBoolean() ? parentA.floorBlock() : parentB.floorBlock(),
          random.nextBoolean() ? parentA.roofBlock() : parentB.roofBlock(),
          random.nextBoolean() ? parentA.wallBlock() : parentB.wallBlock(),
          random.nextBoolean() ? parentA.bedBlock() : parentB.bedBlock(),
          random.nextBoolean() ? parentA.doorBlock() : parentB.doorBlock(),
          random.nextBoolean() ? parentA.windowBlock() : parentB.windowBlock()
      );

      newBuildings.add(childA);

      if (newBuildings.size() < SIZE) {
        var childB = new Building(
            UUID.randomUUID().toString(),
            childA.cornerBlock().equals(parentA.cornerBlock()) ? parentB.cornerBlock() : parentA.cornerBlock(),
            childA.floorBlock().equals(parentA.floorBlock()) ? parentB.floorBlock() : parentA.floorBlock(),
            childA.roofBlock().equals(parentA.roofBlock()) ? parentB.roofBlock() : parentA.roofBlock(),
            childA.wallBlock().equals(parentA.wallBlock()) ? parentB.wallBlock() : parentA.wallBlock(),
            childA.bedBlock().equals(parentA.bedBlock()) ? parentB.bedBlock() : parentA.bedBlock(),
            childA.doorBlock().equals(parentA.doorBlock()) ? parentB.doorBlock() : parentA.doorBlock(),
            childA.windowBlock().equals(parentA.windowBlock()) ? parentB.windowBlock() : parentA.windowBlock()
        );

        newBuildings.add(childB);
      }
    }

    Collections.shuffle(newBuildings);

    currentPopulation = new Population(newBuildings, List.of());
    return Mono.just(currentPopulation);
  }

  public void signalDone(String buildingId) {
    LOGGER.info("Sending building {}", buildingId);

    var building = currentPopulation.getBuildings()
        .stream()
        .filter(b -> b.id().equals(buildingId))
        .findFirst()
        .orElseThrow();

    grpcService.consumeBuilding(building);
    LOGGER.info("Sent building: {}", building.id());
  }

}
