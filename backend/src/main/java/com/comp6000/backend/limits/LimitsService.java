package com.comp6000.backend.limits;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
class LimitsService {

  Map<String, Object> getLimits() {
    return Map.of(
        "seasons", getAvailableSeasons(),
        "building", getBuildingLimits()
    );
  }

  List<String> getAvailableSeasons() {
    return Arrays.stream(Limits.Season.values())
        .map(Limits.Season::name)
        .map(String::toLowerCase)
        .collect(Collectors.toList());
  }

  Limits.Building getBuildingLimits() {
    return new Limits.Building(new Limits.Building.Height(10, 40));
  }

}