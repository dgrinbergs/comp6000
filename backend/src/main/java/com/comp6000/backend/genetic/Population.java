package com.comp6000.backend.genetic;

import java.util.List;

public record Population(
    String populationId,
    List<Building> buildings,
    List<String> selected
) {
}
