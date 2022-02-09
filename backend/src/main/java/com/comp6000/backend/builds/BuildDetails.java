package com.comp6000.backend.builds;

import java.util.UUID;

public final class BuildDetails {

  private final UUID buildId;

  public BuildDetails() {
    this.buildId = UUID.randomUUID();
  }

  public UUID getBuildId() {
    return buildId;
  }
}
