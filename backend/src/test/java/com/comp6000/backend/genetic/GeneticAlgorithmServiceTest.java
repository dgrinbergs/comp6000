package com.comp6000.backend.genetic;

import com.comp6000.backend.grpc.BackendGrpcServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GeneticAlgorithmServiceTest {

  private BackendGrpcServiceImpl grpcService;

  private GeneticAlgorithmService geneticAlgorithmService;

  @BeforeEach
  void setUp() {
    this.grpcService = mock(BackendGrpcServiceImpl.class);
    this.geneticAlgorithmService = new GeneticAlgorithmService(grpcService);
  }

  @Test
  void createInitialPopulation() {
    var initialPopulation = geneticAlgorithmService.createInitialPopulation().block();
    assertThat(initialPopulation).isNotNull();
    assertThat(initialPopulation.getBuildings()).isNotNull();
    assertThat(initialPopulation.getSelected()).isNotNull();

    var selectedBuildings = initialPopulation.getSelected();
    assertThat(selectedBuildings).isEmpty();

    var buildings = initialPopulation.getBuildings();
    assertThat(buildings).hasSize(10);
    assertThat(buildings).allMatch(building -> building.features().size() == 7);
    assertThat(buildings).allMatch(building ->
        building.features().containsKey("corner") &&
            building.features().containsKey("floor") &&
            building.features().containsKey("roof") &&
            building.features().containsKey("wall") &&
            building.features().containsKey("bed") &&
            building.features().containsKey("door") &&
            building.features().containsKey("window"));

    assertThat(geneticAlgorithmService.currentPopulation).isEqualTo(initialPopulation);
  }

  @Test
  void acceptFeedback() {
    var initialPopulation = geneticAlgorithmService.createInitialPopulation().block();
    assertThat(initialPopulation).isNotNull();
    assertThat(initialPopulation.getBuildings()).isNotNull();

    var buildingIds = initialPopulation.getBuildings().stream().map(Building::id).collect(Collectors.toList());
    var selected = buildingIds.subList(0, 3);

    var newPopulation = geneticAlgorithmService.acceptFeedback(selected).block();
    assertThat(newPopulation).isNotNull();

    assertThat(newPopulation.getBuildings()).hasSize(10);
    assertThat(newPopulation.getSelected()).isEmpty();

    assertThat(newPopulation.getBuildings()).anyMatch(building -> building.equals(initialPopulation.getBuildings().get(0)));
    assertThat(newPopulation.getBuildings()).anyMatch(building -> building.equals(initialPopulation.getBuildings().get(1)));
    assertThat(newPopulation.getBuildings()).anyMatch(building -> building.equals(initialPopulation.getBuildings().get(2)));
  }

  @Test
  void acceptFeedbackNoSelectedBuildings() {
    assertThrows(
        ResponseStatusException.class,
        () -> geneticAlgorithmService.acceptFeedback(Collections.emptyList()).block(),
        "You must select at least 3 buildings"
    );
  }

  @Test
  void signalDone() {
    var population = geneticAlgorithmService.createInitialPopulation().block();
    assertThat(population).isNotNull();
    assertThat(population.getBuildings()).hasSize(10);

    var building = population.getBuildings().get(0);
    geneticAlgorithmService.signalDone(building.id());
    verify(grpcService).consumeBuilding(building);
  }

  @Test
  void signalDoneNonExistentBuilding() {
    var nonExistentBuildingId = "test";
    assertThrows(NoSuchElementException.class, () -> geneticAlgorithmService.signalDone(nonExistentBuildingId));
  }

}
