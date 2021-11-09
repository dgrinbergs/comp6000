package com.comp6000.backend.builds.events;

import org.springframework.context.ApplicationEvent;

public abstract class BuildEvent extends ApplicationEvent {
  public BuildEvent(Object source) {
    super(source);
  }
}
