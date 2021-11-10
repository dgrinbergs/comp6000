package com.comp6000.backend.generation;

import com.comp6000.backend.builds.BuildDetails;
import com.comp6000.backend.builds.events.BuildEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class MockGenerationService implements GenerationService {

  private final ApplicationEventPublisher eventPublisher;

  private static final Logger LOGGER = LoggerFactory.getLogger(MockGenerationService.class);

  @Autowired
  public MockGenerationService(ApplicationEventPublisher eventPublisher) {
    this.eventPublisher = eventPublisher;
  }

  @Override
  public void handleBuildEvent(BuildEvent buildEvent) {
    LOGGER.info("Generating structure for build event.");
    var build = (BuildDetails) buildEvent.getSource();
    var generationDetails = new GenerationDetails(build.getUuid(), getSchematicForBuild());
    eventPublisher.publishEvent(new GenerationEvent(generationDetails));
  }

  @Override
  public String getSchematicForBuild() {
    try {
      Thread.sleep(ThreadLocalRandom.current().nextLong(3000,10000));
      return "/lighthouse.schematic";
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    return "";
  }
}
