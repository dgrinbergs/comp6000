package com.comp6000.backend.genetic.feedback;

import java.util.List;

public class FeedbackRequest {

  private final String buildUuid;
  private final List<String> selectedBuildings;

  public FeedbackRequest(String buildUuid, List<String> selectedBuildings) {
    this.buildUuid = buildUuid;
    this.selectedBuildings = selectedBuildings;
  }

  public String getBuildUuid() {
    return buildUuid;
  }

  public List<String> getSelectedBuildings() {
    return selectedBuildings;
  }
}
