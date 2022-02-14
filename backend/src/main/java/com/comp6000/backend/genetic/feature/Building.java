package com.comp6000.backend.genetic.feature;

import java.util.UUID;

public record Building(
  UUID id,
  Material cornerBlock,
  Material floorBlock,
  Material roofBlock,
  Material wallBlock,
  Material bedBlock,
  Material doorBlock,
  Material windowBlock
){}
