package com.comp6000.backend.genetic;

import com.comp6000.backend.grpc.BackendGrpcServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class GeneticAlgorithmService {

  private final BackendGrpcServiceImpl grpcService;

  private static final int SIZE = 10;

  private static final Logger LOGGER = LoggerFactory.getLogger(GeneticAlgorithmService.class);

  @Autowired
  public GeneticAlgorithmService(BackendGrpcServiceImpl grpcService) {
    this.grpcService = grpcService;
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
          UUID.randomUUID(),
          cornerBlock, floorBlock, roofBlock, wallBlock, bedBlock, doorBlock, windowBlock
      );

      buildings.add(building);
    }

    LOGGER.info("Sending building {}", buildings.get(0).id());
    grpcService.consumeBuilding(buildings.get(0));
    LOGGER.info("Sent building");

    return Mono.just(new Population(
        UUID.randomUUID().toString(),
        buildings,
        List.of()
    ));
  }

  //TODO: compare users favourite builds against the rest of the population

}
