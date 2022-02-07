package com.comp6000.backend.genetic.feature.floor;

import com.comp6000.backend.genetic.feature.FeatureService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@ConfigurationProperties(prefix = "features")
public class FloorService implements FeatureService<Floor> {

  private List<Floor> floors;

  public void setFloors(List<Floor> floors) {
    this.floors = floors;
  }

  @Override
  public List<Floor> getFeatures() {
    return floors;
  }
}
