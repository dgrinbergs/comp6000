package com.comp6000.backend.builds;

import com.comp6000.backend.builds.events.BuildCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BuildService {

  private final ApplicationEventPublisher eventPublisher;
  private final List<BuildDetails> builds;

  @Autowired
  public BuildService(ApplicationEventPublisher eventPublisher) {
    this.eventPublisher = eventPublisher;
    this.builds = Collections.synchronizedList(new ArrayList<>());
  }

  BuildDetails saveBuildDetails(BuildDetails buildDetails) {
    var newBuildDetails = new BuildDetails();
    newBuildDetails.setSeason(buildDetails.getSeason());
    newBuildDetails.setBuildingProperties(buildDetails.getBuildingProperties());

    this.builds.add(newBuildDetails);
    this.eventPublisher.publishEvent(new BuildCreatedEvent(newBuildDetails));
    return newBuildDetails;
  }

}
