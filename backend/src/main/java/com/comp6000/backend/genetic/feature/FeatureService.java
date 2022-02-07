package com.comp6000.backend.genetic.feature;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
@ConfigurationProperties(prefix = "ga.features")
public class FeatureService {

  private Floor floor;

  public void setFloors(Floor floor) {
    this.floor = floor;
  }

  public Floor getFloorSchematics() {
    return floor;
  }

  public Floor getRandomFloorSchematic() {
    var randomIndex = ThreadLocalRandom.current().nextInt(0, floor.getSchematics().size());
    throw new RuntimeException();
  }
}
