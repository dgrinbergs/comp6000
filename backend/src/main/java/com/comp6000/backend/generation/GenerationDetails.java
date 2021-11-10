package com.comp6000.backend.generation;

import java.util.UUID;

public class GenerationDetails {

  private final UUID buildUuid;
  private final String schematicUrl;

  public GenerationDetails(UUID buildUuid, String schematicUrl) {
    this.buildUuid = buildUuid;
    this.schematicUrl = schematicUrl;
  }

  public UUID getBuildUuid() {
    return buildUuid;
  }

  public String getSchematicUrl() {
    return schematicUrl;
  }
}
