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

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@WebFluxTest(FeedbackController.class)
public class FeedbackControllerTest {

  @Autowired
  protected WebTestClient webClient;

  @MockBean
  private GeneticAlgorithmService geneticAlgorithmService;

  private static final String BASE_URL = "/api/feedback";

  @Test
  void submitFeedback() {
    var selected = List.of("first", "second", "third");
    var request = new FeedbackController.FeedbackRequest(selected);

    webClient.post()
        .uri(BASE_URL)
        .bodyValue(request)
        .exchange()
        .expectStatus().isOk()
        .expectBody(Population.class);

    verify(geneticAlgorithmService).acceptFeedback(selected);
  }

}
