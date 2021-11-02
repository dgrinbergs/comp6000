package com.comp6000.backend.builds;

import com.comp6000.backend.AbstractControllerTest;
import com.comp6000.backend.builds.exceptions.BuildNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BuildControllerTest extends AbstractControllerTest {

  @MockBean
  private BuildValidator buildValidator;

  @MockBean
  private BuildService buildService;

  @BeforeEach
  void setUp() {

  }

  @Test
  void getAllBuilds() throws Exception {
    var buildRequest = new BuildRequest();
    buildRequest.setSeason("summer");
    buildRequest.setBuilding(new BuildRequest.Building(20));
    var buildUUID = UUID.randomUUID();
    when(buildService.getBuilds()).thenReturn(Map.of(buildUUID, buildRequest));

    var response = mockMvc.perform(get("/builds")).andExpect(status().isOk()).andReturn();
    assertThat(response.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);

    var result = parseResponse(response.getResponse().getContentAsString());
    assertThat(result).containsKey(buildUUID.toString());

    var data = (Map<String, Object>) result.get(buildUUID.toString());
    assertThat(data).containsKeys("season", "building");

    var resultSeason = data.get("season");
    assertThat(resultSeason).isInstanceOf(String.class);
    assertThat((String) resultSeason).isEqualTo("summer");

    var resultBuilding = (Map<String, Integer>) data.get("building");
    assertThat(resultBuilding).hasSize(1);
    assertThat(resultBuilding).containsKey("height");
    assertThat(resultBuilding.get("height")).isEqualTo(20);

    verify(buildService).getBuilds();
    verify(buildValidator, never()).validate(any(), any());
  }

  @Test
  void getAllBuilds_noBuildsAvailable() throws Exception {
    when(buildService.getBuilds()).thenReturn(Map.of());

    var response = mockMvc.perform(get("/builds")).andExpect(status().isOk()).andReturn();
    assertThat(response.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);

    var result = parseResponse(response.getResponse().getContentAsString());
    assertThat(result).isEmpty();

    verify(buildService).getBuilds();
    verify(buildValidator, never()).validate(any(), any());
  }

  @Test
  void getBuild() throws Exception {
    var buildRequest = new BuildRequest();
    buildRequest.setSeason("summer");
    buildRequest.setBuilding(new BuildRequest.Building(20));
    var buildUUID = UUID.randomUUID();
    when(buildService.getBuild(buildUUID)).thenReturn(buildRequest);

    var response = mockMvc.perform(get("/builds/" + buildUUID)).andExpect(status().isOk()).andReturn();
    assertThat(response.getResponse().getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);

    var result = parseResponse(response.getResponse().getContentAsString());
    assertThat(result).containsKeys("season", "building");

    var resultSeason = result.get("season");
    assertThat(resultSeason).isInstanceOf(String.class);
    assertThat((String) resultSeason).isEqualTo("summer");

    var resultBuilding = (Map<String, Integer>) result.get("building");
    assertThat(resultBuilding).hasSize(1);
    assertThat(resultBuilding).containsKey("height");
    assertThat(resultBuilding.get("height")).isEqualTo(20);


    verify(buildService).getBuild(buildUUID);
    verify(buildValidator, never()).validate(any(), any());
  }

  @Test
  void getBuild_buildNotFound() throws Exception {
    var exceptionMessage = "Build not found";
    var buildUUID = UUID.randomUUID();
    when(buildService.getBuild(buildUUID)).thenThrow(new BuildNotFoundException(exceptionMessage));

    var response = mockMvc.perform(get("/builds/" + buildUUID)).andExpect(status().isNotFound()).andReturn();
    var error = response.getResolvedException();
    assertThat(error).isInstanceOf(BuildNotFoundException.class);
    assertThat(error).hasMessage(exceptionMessage);

    verify(buildService).getBuild(buildUUID);
    verify(buildValidator, never()).validate(any(), any());
  }
}