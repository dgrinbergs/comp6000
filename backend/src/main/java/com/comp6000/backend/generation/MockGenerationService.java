package com.comp6000.backend.generation;

import com.comp6000.backend.builds.BuildDetails;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class MockGenerationService implements GenerationService{

  @Override
  public String generateSchematicForBuild(BuildDetails buildDetails) {
    try {
      Thread.sleep(ThreadLocalRandom.current().nextLong(5000)); //simulated AI waiting time
      return "/lighthouse.schematic";
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
