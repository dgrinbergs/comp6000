package com.comp6000.backend.generation.events;

import com.comp6000.backend.generation.GenerationDetails;
import org.springframework.context.ApplicationEvent;

public final class GenerationEvent extends ApplicationEvent {
  public GenerationEvent(GenerationDetails generationDetails) {
    super(generationDetails);
  }
}
