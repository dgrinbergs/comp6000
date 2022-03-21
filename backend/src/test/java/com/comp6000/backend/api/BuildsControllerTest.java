package com.comp6000.backend.api;

import com.comp6000.backend.genetic.GeneticAlgorithmService;
import com.comp6000.backend.genetic.Population;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@WebFluxTest(BuildsController.class)
public class BuildsControllerTest {

  @Autowired
  protected WebTestClient webClient;

  private static final String BASE_URL = "/api/builds";

  @MockBean
  private GeneticAlgorithmService geneticAlgorithmService;

  @Test
  void createBuild() {
    webClient.post()
        .uri(BASE_URL)
        .exchange()
        .expectStatus().isOk()
        .expectBody(Population.class);

    verify(geneticAlgorithmService).createInitialPopulation();
  }

  @Test
  void sendDone() {
    var request = new BuildsController.DoneRequest("id");
    webClient.post()
        .uri(BASE_URL + "/done")
        .bodyValue(request)
        .exchange()
        .expectStatus().isOk();

    verify(geneticAlgorithmService).signalDone(request.buildingId());
  }

  @Test
  void sendDoneNoBody() {
    webClient.post()
        .uri(BASE_URL + "/done")
        .exchange()
        .expectStatus().isBadRequest();

    verify(geneticAlgorithmService, never()).signalDone(any());
  }

}
