package com.comp6000.backend.genetic;

import com.comp6000.backend.genetic.building.Building;
import com.comp6000.backend.genetic.building.BuildingFactory;
import com.comp6000.backend.genetic.feature.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GA {

  private List<Building> initialPopulation;

  @Autowired
  public GA(FeatureService featureService) {

    for(int i = 0; i < 5; i++) {

      var floors = featureService.getFloorSchematics();

      var building = new BuildingFactory()
          .setFloor(featureService.getRandomFloorSchematic())
          .build();
    }

  }

}
