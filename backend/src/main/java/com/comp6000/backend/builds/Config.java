package com.comp6000.backend.builds;

import com.comp6000.backend.genetic.feature.Building;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

@Configuration
public class Config {

  @Bean
  public Flux<Building> buildingFlux() {
    return Flux.empty();
  }

}
