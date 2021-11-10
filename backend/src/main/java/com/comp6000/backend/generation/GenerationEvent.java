package com.comp6000.backend.generation;

import org.springframework.context.ApplicationEvent;

public class GenerationEvent extends ApplicationEvent {
  public GenerationEvent(GenerationDetails generationDetails) {
    super(generationDetails);
  }
}
