package com.comp6000.backend.builds;

import com.comp6000.backend.AbstractControllerTest;
import com.comp6000.backend.builds.exceptions.BuildNotFoundException;
import com.comp6000.backend.utils.errors.ErrorService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.validation.BindingResult;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BuildControllerTest extends AbstractControllerTest {

  @MockBean
  private BuildValidator buildValidator;

  @MockBean
  private BuildService buildService;

  @MockBean
  private ErrorService errorService;

  private final String baseUrl = "/builds";

  @Test
  void getAllBuilds() throws Exception {
    var buildRequest = new BuildRequest();
    buildRequest.setSeason("summer");
    buildRequest.setBuilding(new BuildRequest.Building(20));
    var buildUUID = UUID.randomUUID();
    when(buildService.getBuilds()).thenReturn(Map.of(buildUUID, buildRequest));

    var result = mockMvc.perform(get(baseUrl)).andExpect(status().isOk()).andReturn();
    assertThat(result.getResponse().getContentType()).isEqualTo(APPLICATION_JSON_VALUE);

    var response = parseResponse(result.getResponse().getContentAsString());
    assertThat(response).containsKey(buildUUID.toString());

    var data = (Map<String, Object>) response.get(buildUUID.toString());
    assertThat(data).containsKeys("season", "building");

    var resultSeason = data.get("season");
    assertThat(resultSeason).isInstanceOf(String.class).isEqualTo("summer");

    var resultBuilding = (Map<String, Integer>) data.get("building");
    assertThat(resultBuilding).containsOnlyKeys("height");
    assertThat(resultBuilding.get("height")).isEqualTo(20);

    verify(buildService).getBuilds();
    verify(buildValidator, never()).validate(any(), any());
    verify(errorService, never()).getErrorDetails(any());
  }

  @Test
  void getAllBuilds_noBuildsAvailable() throws Exception {
    when(buildService.getBuilds()).thenReturn(Map.of());

    var result = mockMvc.perform(get(baseUrl)).andExpect(status().isOk()).andReturn();
    assertThat(result.getResponse().getContentType()).isEqualTo(APPLICATION_JSON_VALUE);

    var response = parseResponse(result.getResponse().getContentAsString());
    assertThat(response).isEmpty();

    verify(buildService).getBuilds();
    verify(buildValidator, never()).validate(any(), any());
    verify(errorService, never()).getErrorDetails(any());
  }

  @Test
  void getBuild() throws Exception {
    var buildRequest = new BuildRequest();
    buildRequest.setSeason("summer");
    buildRequest.setBuilding(new BuildRequest.Building(20));
    var buildUUID = UUID.randomUUID();
    when(buildService.getBuild(buildUUID)).thenReturn(buildRequest);

    var result = mockMvc.perform(get(baseUrl + "/" + buildUUID)).andExpect(status().isOk()).andReturn();
    assertThat(result.getResponse().getContentType()).isEqualTo(APPLICATION_JSON_VALUE);

    var response = parseResponse(result.getResponse().getContentAsString());
    assertThat(response).containsOnlyKeys("season", "building");

    var resultSeason = response.get("season");
    assertThat(resultSeason).isInstanceOf(String.class).isEqualTo("summer");

    var resultBuilding = (Map<String, Integer>) response.get("building");
    assertThat(resultBuilding).hasSize(1);
    assertThat(resultBuilding.get("height")).isEqualTo(20);


    verify(buildService).getBuild(buildUUID);
    verify(buildValidator, never()).validate(any(), any());
    verify(errorService, never()).getErrorDetails(any());
  }

  @Test
  void getBuild_buildNotFound() throws Exception {
    var exceptionMessage = "Build not found";
    var buildUUID = UUID.randomUUID();
    when(buildService.getBuild(buildUUID)).thenThrow(new BuildNotFoundException(exceptionMessage));

    var result = mockMvc.perform(get(baseUrl + "/" + buildUUID)).andExpect(status().isNotFound()).andReturn();
    var error = result.getResolvedException();
    assertThat(error).isInstanceOf(BuildNotFoundException.class).hasMessage(exceptionMessage);

    verify(buildService).getBuild(buildUUID);
    verify(buildValidator, never()).validate(any(), any());
    verify(errorService, never()).getErrorDetails(any());
  }

  @Test
  void submitBuild() throws Exception {
    var season = "summer";
    var buildHeight = 20;

    var buildRequest = new BuildRequest();
    buildRequest.setSeason(season);
    buildRequest.setBuilding(new BuildRequest.Building(buildHeight));

    var buildUUID = UUID.randomUUID();
    when(buildService.saveBuild(
        argThat(r -> r.getSeason().equals(season) && r.getBuilding().height().equals(buildHeight)))
    ).thenReturn(buildUUID);

    var result = mockMvc.perform(post(baseUrl)
        .content(stringify(buildRequest))
        .contentType(APPLICATION_JSON_VALUE)
    ).andExpect(status().isOk()).andReturn();
    assertThat(result.getResponse().getContentType()).isEqualTo(APPLICATION_JSON_VALUE);

    var response = parseResponse(result.getResponse().getContentAsString());
    assertThat(response).containsOnlyKeys("build");

    var build = (Map<String, String>) response.get("build");
    assertThat(build).containsEntry("id", buildUUID.toString());

    verify(buildValidator).validate(
        argThat(o -> {
          var br = (BuildRequest) o;
          return br.getSeason().equals(season) && br.getBuilding().height().equals(buildHeight);
        }),
        argThat(e -> !e.hasErrors())
    );

    verify(buildService).saveBuild(argThat(r ->
        r.getSeason().equals(season) && r.getBuilding().height().equals(buildHeight))
    );

    verify(errorService, never()).getErrorDetails(any());
  }

  @Test
  void submitBuild_invalidSeason() throws Exception {
    var season = "invalid season";
    var buildHeight = 20;

    var buildRequest = new BuildRequest();
    buildRequest.setSeason(season);
    buildRequest.setBuilding(new BuildRequest.Building(buildHeight));

    var validationMessage = "you must provide a valid season";

    doAnswer(x -> {
      var bindingResult = (BindingResult) x.getArgument(1);
      bindingResult.rejectValue("season", validationMessage);
      return null;
    }).when(buildValidator).validate(
        argThat(o -> {
          var br = (BuildRequest) o;
          return br.getSeason().equals(season) && br.getBuilding().height().equals(buildHeight);
        }),
        argThat(e -> !e.hasErrors())
    );

    when(errorService.getErrorDetails(argThat(BindingResult::hasErrors))).thenReturn(
        List.of(new ErrorService.ErrorDetail("season", season, validationMessage))
    );

    var result = mockMvc.perform(post(baseUrl)
        .content(stringify(buildRequest))
        .contentType(APPLICATION_JSON_VALUE)
    ).andExpect(status().isBadRequest()).andReturn();
    assertThat(result.getResponse().getContentType()).isEqualTo(APPLICATION_JSON_VALUE);

    var response = parseResponse(result.getResponse().getContentAsString());
    assertThat(response).containsOnlyKeys("errors");

    var errors = (List<Map<String, Object>>) response.get("errors");
    assertThat(errors).hasSize(1);

    var errorDetail = errors.get(0);
    assertThat(errorDetail).containsOnlyKeys("field", "message", "value");
    assertThat(errorDetail.get("field")).isEqualTo("season");
    assertThat(errorDetail.get("message")).isEqualTo(validationMessage);
    assertThat(errorDetail.get("value")).isEqualTo(season);

    verify(buildValidator).validate(
        argThat(o -> {
          var br = (BuildRequest) o;
          return br.getSeason().equals(season) && br.getBuilding().height().equals(buildHeight);
        }),
        any(BindingResult.class)
    );
    verify(errorService).getErrorDetails(argThat(BindingResult::hasErrors));
    verify(buildService, never()).saveBuild(any());
  }

  @Test
  void submitBuild_noSeason() throws Exception {
    var buildHeight = 20;
    var buildRequest = new BuildRequest();
    buildRequest.setBuilding(new BuildRequest.Building(buildHeight));

    var result = mockMvc.perform(post(baseUrl)
        .content(stringify(buildRequest))
        .contentType(APPLICATION_JSON_VALUE)
    ).andExpect(status().isBadRequest()).andReturn();
    assertThat(result.getResponse().getContentType()).isEqualTo(APPLICATION_JSON_VALUE);

    var response = result.getResolvedException();
    assertThat(response)
        .isInstanceOf(ConstraintViolationException.class)
        .hasMessage("submitBuild.buildRequest.season: must not be blank");

    verify(buildValidator, never()).validate(any(), any());
    verify(errorService, never()).getErrorDetails(any());
    verify(buildService, never()).saveBuild(any());
  }

  @Test
  void submitBuild_noBuilding() throws Exception {
    var season = "summer";
    var buildRequest = new BuildRequest();
    buildRequest.setSeason(season);

    var result = mockMvc.perform(post(baseUrl)
        .content(stringify(buildRequest))
        .contentType(APPLICATION_JSON_VALUE)
    ).andExpect(status().isBadRequest()).andReturn();
    assertThat(result.getResponse().getContentType()).isEqualTo(APPLICATION_JSON_VALUE);

    var response = result.getResolvedException();
    assertThat(response)
        .isInstanceOf(ConstraintViolationException.class)
        .hasMessage("submitBuild.buildRequest.building: must not be null");

    verify(buildValidator, never()).validate(any(), any());
    verify(errorService, never()).getErrorDetails(any());
    verify(buildService, never()).saveBuild(any());
  }

  @Test
  void submitBuild_noBuildingAndNoSeason() throws Exception {
    var buildRequest = new BuildRequest();

    var result = mockMvc.perform(post(baseUrl)
        .content(stringify(buildRequest))
        .contentType(APPLICATION_JSON_VALUE)
    ).andExpect(status().isBadRequest()).andReturn();
    assertThat(result.getResponse().getContentType()).isEqualTo(APPLICATION_JSON_VALUE);

    var response = result.getResolvedException();
    assertThat(response)
        .isInstanceOf(ConstraintViolationException.class)
        .hasMessageContaining("submitBuild.buildRequest.building: must not be null")
        .hasMessageContaining("submitBuild.buildRequest.season: must not be blank");

    verify(buildValidator, never()).validate(any(), any());
    verify(errorService, never()).getErrorDetails(any());
    verify(buildService, never()).saveBuild(any());
  }

  @Test
  void submitBuild_invalidBuildingHeight() throws Exception {
    var season = "summer";
    var buildHeight = 200;

    var buildRequest = new BuildRequest();
    buildRequest.setSeason(season);
    buildRequest.setBuilding(new BuildRequest.Building(buildHeight));

    var validationMessage = "building height is too high";

    doAnswer(x -> {
      var bindingResult = (BindingResult) x.getArgument(1);
      bindingResult.rejectValue("building.height", validationMessage);
      return null;
    }).when(buildValidator).validate(
        argThat(o -> {
          var br = (BuildRequest) o;
          return br.getSeason().equals(season) && br.getBuilding().height().equals(buildHeight);
        }),
        argThat(e -> !e.hasErrors())
    );

    when(errorService.getErrorDetails(argThat(BindingResult::hasErrors))).thenReturn(
        List.of(new ErrorService.ErrorDetail("building.height", buildHeight, validationMessage))
    );

    var result = mockMvc.perform(post(baseUrl)
        .content(stringify(buildRequest))
        .contentType(APPLICATION_JSON_VALUE)
    ).andExpect(status().isBadRequest()).andReturn();
    assertThat(result.getResponse().getContentType()).isEqualTo(APPLICATION_JSON_VALUE);

    var response = parseResponse(result.getResponse().getContentAsString());
    assertThat(response).containsOnlyKeys("errors");

    var errors = (List<Map<String, Object>>) response.get("errors");
    assertThat(errors).hasSize(1);

    var errorDetail = errors.get(0);
    assertThat(errorDetail).containsOnlyKeys("field", "message", "value");
    assertThat(errorDetail.get("field")).isEqualTo("building.height");
    assertThat(errorDetail.get("message")).isEqualTo(validationMessage);
    assertThat(errorDetail.get("value")).isEqualTo(buildHeight);

    verify(buildValidator).validate(
        argThat(o -> {
          var br = (BuildRequest) o;
          return br.getSeason().equals(season) && br.getBuilding().height().equals(buildHeight);
        }),
        any(BindingResult.class)
    );
    verify(errorService).getErrorDetails(argThat(BindingResult::hasErrors));
    verify(buildService, never()).saveBuild(any());
  }

  @Test
  void submitBuild_invalidBuildingHeightAndSeason() throws Exception {
    var season = "summer2";
    var buildHeight = 200;

    var buildRequest = new BuildRequest();
    buildRequest.setSeason(season);
    buildRequest.setBuilding(new BuildRequest.Building(buildHeight));

    var validationMessage = "building height is too high";

    doAnswer(x -> {
      var bindingResult = (BindingResult) x.getArgument(1);
      bindingResult.rejectValue("building.height", validationMessage);
      bindingResult.rejectValue("season", validationMessage);
      return null;
    }).when(buildValidator).validate(
        argThat(o -> {
          var br = (BuildRequest) o;
          return br.getSeason().equals(season) && br.getBuilding().height().equals(buildHeight);
        }),
        argThat(e -> !e.hasErrors())
    );

    when(errorService.getErrorDetails(argThat(BindingResult::hasErrors))).thenReturn(List.of(
        new ErrorService.ErrorDetail("building.height", buildHeight, validationMessage),
        new ErrorService.ErrorDetail("season", season, validationMessage)
    ));

    var result = mockMvc.perform(post(baseUrl)
        .content(stringify(buildRequest))
        .contentType(APPLICATION_JSON_VALUE)
    ).andExpect(status().isBadRequest()).andReturn();
    assertThat(result.getResponse().getContentType()).isEqualTo(APPLICATION_JSON_VALUE);

    var response = parseResponse(result.getResponse().getContentAsString());
    assertThat(response).containsOnlyKeys("errors");

    var errors = (List<Map<String, Object>>) response.get("errors");
    assertThat(errors).hasSize(2);

    var bulidingErrorDetails = errors.get(0);
    assertThat(bulidingErrorDetails).containsOnlyKeys("field", "message", "value");
    assertThat(bulidingErrorDetails.get("field")).isEqualTo("building.height");
    assertThat(bulidingErrorDetails.get("message")).isEqualTo(validationMessage);
    assertThat(bulidingErrorDetails.get("value")).isEqualTo(buildHeight);

    var seasonErrorDetails = errors.get(1);
    assertThat(seasonErrorDetails).containsOnlyKeys("field", "message", "value");
    assertThat(seasonErrorDetails.get("field")).isEqualTo("season");
    assertThat(seasonErrorDetails.get("message")).isEqualTo(validationMessage);
    assertThat(seasonErrorDetails.get("value")).isEqualTo(season);

    verify(buildValidator).validate(
        argThat(o -> {
          var br = (BuildRequest) o;
          return br.getSeason().equals(season) && br.getBuilding().height().equals(buildHeight);
        }),
        any(BindingResult.class)
    );
    verify(errorService).getErrorDetails(argThat(BindingResult::hasErrors));
    verify(buildService, never()).saveBuild(any());
  }
}