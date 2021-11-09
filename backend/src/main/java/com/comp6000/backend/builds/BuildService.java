package com.comp6000.backend.builds;

import com.comp6000.backend.builds.events.BuildCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.comp6000.backend.builds.BuildStatus.CREATED;

@Service
public class BuildService {

  private final ApplicationEventPublisher eventPublisher;
  private final Map<BuildDetails, BuildStatus> builds;

  private static final Logger LOGGER = LoggerFactory.getLogger(BuildService.class);

  @Autowired
  public BuildService(ApplicationEventPublisher eventPublisher) {
    this.eventPublisher = eventPublisher;
    this.builds = new ConcurrentHashMap<>();
  }

  Mono<BuildDetails> saveBuildDetails(BuildDetails buildDetails) {
    var newBuildDetails = new BuildDetails();
    newBuildDetails.setSeason(buildDetails.getSeason());
    newBuildDetails.setBuildingProperties(buildDetails.getBuildingProperties());

    this.builds.put(newBuildDetails, CREATED);
    this.eventPublisher.publishEvent(new BuildCreatedEvent(newBuildDetails));

    LOGGER.info("New build details received. {} total", builds.size());

    return Mono.just(newBuildDetails);
  }

}
