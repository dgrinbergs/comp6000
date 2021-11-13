package com.comp6000.backend.generation;

import java.util.UUID;

public class GenerationDetails {

  private final UUID buildId;
  private final String schematicUrl;

  public GenerationDetails(UUID buildId, String schematicUrl) {
    this.buildId = buildId;
    this.schematicUrl = schematicUrl;
  }

  public UUID getBuildId() {
    return buildId;
  }

  public String getSchematicUrl() {
    return schematicUrl;
  }
}
