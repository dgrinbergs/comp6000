package com.comp6000.backend.genetic;

import com.comp6000.backend.genetic.feature.building.Building;
import com.comp6000.backend.genetic.feature.building.BuildingFactory;
import com.comp6000.backend.genetic.feature.floor.FloorService;
import com.comp6000.backend.genetic.feature.wall.Perimeter;
import com.comp6000.backend.genetic.feature.wall.WallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class GeneticAlgorithmService {

  private final FloorService floorService;
  private final WallService wallService;

  private final int SIZE = 10;

  @Autowired
  public GeneticAlgorithmService(FloorService floorService, WallService wallService) {
    this.floorService = floorService;
    this.wallService = wallService;
  }

  public List<Building> createInitialPopulation() {
    List<Building> population = new ArrayList<>();

    for(int i = 0; i < SIZE; i++) {
      var floors = floorService.getFeatures();
      var randomFloor = floorService.getFeatures().get(
          ThreadLocalRandom.current().nextInt(0, floors.size())
      );

      var walls = wallService.getFeatures();
      var randomWall = wallService.getFeatures().get(
          ThreadLocalRandom.current().nextInt(0, walls.size())
      );

      var building = new BuildingFactory()
          .setFloor(randomFloor)
          .setPerimeter(new Perimeter(randomWall))
          .build();

      population.add(building);
    }
    return population;
  }



  //compare their favourite build against the rest of the population


}
