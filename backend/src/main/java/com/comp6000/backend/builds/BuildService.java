package com.comp6000.backend.builds;

import com.comp6000.backend.builds.events.BuildCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BuildService {

  private final ApplicationEventPublisher eventPublisher;

  @Autowired
  public BuildService(ApplicationEventPublisher eventPublisher) {
    this.eventPublisher = eventPublisher;
  }

  Mono<BuildDetails> saveBuildDetails(BuildDetails buildDetails) {
    var newBuildDetails = new BuildDetails();
    newBuildDetails.setSeason(buildDetails.getSeason());
    newBuildDetails.setBuildingProperties(buildDetails.getBuildingProperties());

    this.eventPublisher.publishEvent(new BuildCreatedEvent(newBuildDetails));

    return Mono.just(newBuildDetails);
  }

}
