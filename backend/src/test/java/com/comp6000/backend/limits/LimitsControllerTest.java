package com.comp6000.backend.limits;

import com.comp6000.backend.AbstractControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LimitsControllerTest extends AbstractControllerTest {

  @MockBean
  private LimitsService limitsService;

  @BeforeEach
  void setUp() {
    when(limitsService.getLimits()).thenReturn(Map.of("key", "value"));
  }

  @Test
  void getLimits() throws Exception {
    var response = mockMvc.perform(get("/api/limits")).andExpect(status().isOk()).andReturn();
    assertThat(response.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);

    var result = parseResponse(response.getResponse().getContentAsString());
    assertThat(result).hasSize(1);
    assertThat(result).containsEntry("key", "value");

    verify(limitsService).getLimits();
  }
}