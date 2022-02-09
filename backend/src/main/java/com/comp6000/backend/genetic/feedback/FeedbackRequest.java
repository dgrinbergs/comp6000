package com.comp6000.backend.genetic.feedback;

import java.util.List;

class FeedbackRequest {

  // The population which this feedback is for
  private final String populationId;

  // The buildings that the user has selected as their favourites
  private final List<String> selected;

  public FeedbackRequest(String populationId, List<String> selected) {
    this.populationId = populationId;
    this.selected = selected;
  }

  public String getPopulationId() {
    return populationId;
  }

  public List<String> getSelected() {
    return selected;
  }
}
