package com.comp6000.backend.builds.events;

import com.comp6000.backend.builds.BuildDetails;
import org.springframework.context.ApplicationEvent;

public abstract class BuildEvent extends ApplicationEvent {
  public BuildEvent(BuildDetails source) {
    super(source);
  }
}
