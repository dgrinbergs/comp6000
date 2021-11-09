package com.comp6000.backend.builds.events;

import com.comp6000.backend.builds.BuildDetails;

public class BuildCreatedEvent extends BuildEvent {
  public BuildCreatedEvent(BuildDetails buildDetails) {
    super(buildDetails);
  }
}
