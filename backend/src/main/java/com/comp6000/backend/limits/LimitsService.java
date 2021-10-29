package com.comp6000.backend.limits;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class LimitsService {

  List<Seasons> getAvailableSeasons() {
    return List.of(Seasons.values());
  }

}