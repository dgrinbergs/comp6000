package com.comp6000.backend.genetic;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "ga.features")
public class Test {

  private final List<Schematic> floor;

  public Test(List<Schematic> floor) {
    this.floor = floor;
  }

  public List<Schematic> getFloor() {
    return floor;
  }
}
