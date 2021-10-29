package com.comp6000.backend.limits;

import java.util.List;
import java.util.stream.Collectors;

class LimitsResponse {

  private List<String> seasons;

  public LimitsResponse() {
  }

  public LimitsResponse(List<Seasons> seasons) {
    this.seasons = seasons.stream().map(Seasons::getDisplayName).collect(Collectors.toList());
  }

  public List<String> getSeasons() {
    return seasons;
  }

  public void setSeasons(List<String> seasons) {
    this.seasons = seasons;
  }
}
