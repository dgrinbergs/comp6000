package com.comp6000.backend.genetic.feature.wall;

import com.comp6000.backend.genetic.feature.FeatureService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConfigurationProperties(prefix = "features")
public class WallService implements FeatureService<Wall> {

  private List<Wall> walls;

  public void setWalls(List<Wall> walls) {
    this.walls = walls;
  }

  @Override
  public List<Wall> getFeatures() {
    return walls;
  }
}
