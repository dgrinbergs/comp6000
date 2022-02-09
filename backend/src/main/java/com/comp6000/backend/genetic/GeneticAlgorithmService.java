package com.comp6000.backend.genetic;

import com.comp6000.backend.genetic.feature.bed.BedService;
import com.comp6000.backend.genetic.feature.building.Building;
import com.comp6000.backend.genetic.feature.building.BuildingFactory;
import com.comp6000.backend.genetic.feature.corner.CornerPerimeter;
import com.comp6000.backend.genetic.feature.corner.CornerService;
import com.comp6000.backend.genetic.feature.decor.DecorService;
import com.comp6000.backend.genetic.feature.door.DoorService;
import com.comp6000.backend.genetic.feature.doornum.DoorNumService;
import com.comp6000.backend.genetic.feature.floor.FloorService;
import com.comp6000.backend.genetic.feature.roof.RoofService;
import com.comp6000.backend.genetic.feature.wall.Perimeter;
import com.comp6000.backend.genetic.feature.wall.WallService;
import com.comp6000.backend.genetic.feature.window.WindowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class GeneticAlgorithmService {

  private final FloorService floorService;
  private final WallService wallService;
  private final WindowService windowService;
  private final RoofService roofService;
  private final DoorService doorService;
  private final CornerService cornerService;
  private final DoorNumService doornumService;
  private final BedService bedService;
  private final DecorService decorService;

  private final int SIZE = 10;

  @Autowired
  public GeneticAlgorithmService(FloorService floorService, WallService wallService, WindowService windowService, RoofService roofService, DoorService doorService, CornerService cornerService, DoorNumService doornumService, BedService bedService, DecorService decorService) {
    this.floorService = floorService;
    this.wallService = wallService;
    this.windowService = windowService;
    this.roofService = roofService;
    this.doorService = doorService;
    this.cornerService = cornerService;
    this.doornumService = doornumService;
    this.bedService = bedService;
    this.decorService = decorService;
  }

  public Population createInitialPopulation() {
    List<Building> buildings = new ArrayList<>(SIZE);

    for (int i = 0; i < SIZE; i++) {
      var floors = floorService.getFeatures();
      var randomFloor = floorService.getFeatures().get(
          ThreadLocalRandom.current().nextInt(0, floors.size())
      );

      var walls = wallService.getFeatures();
      var randomWall = wallService.getFeatures().get(
          ThreadLocalRandom.current().nextInt(0, walls.size())
      );

      var windows = windowService.getFeatures();
      var randomWindow = windowService.getFeatures().get(
          ThreadLocalRandom.current().nextInt(0, windows.size())
      );

      var roofs = roofService.getFeatures();
      var randomRoof = roofService.getFeatures().get(
              ThreadLocalRandom.current().nextInt(0, roofs.size())
      );

      var doors = doorService.getFeatures();
      var randomDoor = doorService.getFeatures().get(
              ThreadLocalRandom.current().nextInt(0, doors.size())
      );

      var corners = cornerService.getFeatures();
      var randomCorner = cornerService.getFeatures().get(
              ThreadLocalRandom.current().nextInt(0, corners.size())
      );

      var doornums = doornumService.getFeatures();
      var randomDoorNum = doornumService.getFeatures().get(
              ThreadLocalRandom.current().nextInt(0, doornums.size())
      );

      var beds = bedService.getFeatures();
      var randomBed = bedService.getFeatures().get(
              ThreadLocalRandom.current().nextInt(0, beds.size())
      );

      var decors = decorService.getFeatures();
      var randomDecor = decorService.getFeatures().get(
              ThreadLocalRandom.current().nextInt(0, decors.size())
      );

      var building = new BuildingFactory()
          .setFloor(randomFloor)
          .setPerimeter(new Perimeter(randomWall))
          .setWindow(randomWindow)
          .setRoof(randomRoof)
          .setDoor(randomDoor)
          .setCornerPerimeter(new CornerPerimeter(randomCorner))
          .setDoorNum(randomDoorNum)
          .setBed(randomBed)
          .setDecor(randomDecor)
          .build();

      buildings.add(building);
    }

    return new Population(
        UUID.randomUUID().toString(),
        buildings,
        new ArrayList<>()
    );
  }


  //compare their favourite build against the rest of the population


}
