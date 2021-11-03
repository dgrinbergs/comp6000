package com.comp6000.backend.builds;

import com.comp6000.backend.limits.Limits;
import com.comp6000.backend.limits.LimitsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuildValidatorTest {

  @Mock
  private LimitsService limitsService;

  private BuildValidator buildValidator;

  @BeforeEach
  void setUp() {
    buildValidator = new BuildValidator(limitsService);
  }

  @Test
  void supports() {
    assertThat(buildValidator.supports(BuildRequest.class)).isTrue();
  }

  @Test
  void supports_object() {
    assertThat(buildValidator.supports(Object.class)).isFalse();
  }

  @Test
  void validate() {
    var validSeasons = Arrays.stream(Limits.Season.values())
        .map(Limits.Season::name)
        .map(String::toLowerCase)
        .collect(Collectors.toList());
    when(limitsService.getAvailableSeasons()).thenReturn(validSeasons);

    var validBuilding = new Limits.Building(new Limits.Building.Height(10, 40));
    when(limitsService.getBuildingLimits()).thenReturn(validBuilding);

    var buildRequest = new BuildRequest();
    buildRequest.setSeason("summer");
    buildRequest.setBuilding(new BuildRequest.Building(20));
    var errors = new BeanPropertyBindingResult(buildRequest, "buildRequest");

    buildValidator.validate(buildRequest, errors);

    assertThat(errors.hasErrors()).isFalse();

    verify(limitsService).getBuildingLimits();
    verify(limitsService).getAvailableSeasons();
  }

  @Test
  void validate_invalidSeason() {
    var validSeasons = Arrays.stream(Limits.Season.values())
        .map(Limits.Season::name)
        .map(String::toLowerCase)
        .collect(Collectors.toList());
    when(limitsService.getAvailableSeasons()).thenReturn(validSeasons);

    var validBuilding = new Limits.Building(new Limits.Building.Height(10, 40));
    when(limitsService.getBuildingLimits()).thenReturn(validBuilding);

    var buildRequest = new BuildRequest();
    buildRequest.setSeason("summer2");
    buildRequest.setBuilding(new BuildRequest.Building(20));
    var errors = new BeanPropertyBindingResult(buildRequest, "buildRequest");

    buildValidator.validate(buildRequest, errors);

    assertThat(errors.getAllErrors()).hasSize(1);

    var fieldError = (FieldError) errors.getAllErrors().get(0);
    assertThat(fieldError.getField()).isEqualTo("season");
    assertThat(fieldError.getRejectedValue()).isEqualTo("summer2");
    assertThat(fieldError.getCode()).isEqualTo("invalid season provided");

    verify(limitsService).getBuildingLimits();
    verify(limitsService).getAvailableSeasons();
  }

  @Test
  void validate_heightTooLow() {
    var validSeasons = Arrays.stream(Limits.Season.values())
        .map(Limits.Season::name)
        .map(String::toLowerCase)
        .collect(Collectors.toList());
    when(limitsService.getAvailableSeasons()).thenReturn(validSeasons);

    var validBuilding = new Limits.Building(new Limits.Building.Height(10, 40));
    when(limitsService.getBuildingLimits()).thenReturn(validBuilding);

    var buildRequest = new BuildRequest();
    buildRequest.setSeason("summer");
    buildRequest.setBuilding(new BuildRequest.Building(1));
    var errors = new BeanPropertyBindingResult(buildRequest, "buildRequest");

    buildValidator.validate(buildRequest, errors);

    assertThat(errors.getAllErrors()).hasSize(1);

    var fieldError = (FieldError) errors.getAllErrors().get(0);
    assertThat(fieldError.getField()).isEqualTo("building.height");
    assertThat(fieldError.getRejectedValue()).isEqualTo(1);
    assertThat(fieldError.getCode()).isEqualTo("building height is too low");

    verify(limitsService).getBuildingLimits();
    verify(limitsService).getAvailableSeasons();
  }

  @Test
  void validate_heightTooHigh() {
    var validSeasons = Arrays.stream(Limits.Season.values())
        .map(Limits.Season::name)
        .map(String::toLowerCase)
        .collect(Collectors.toList());
    when(limitsService.getAvailableSeasons()).thenReturn(validSeasons);

    var validBuilding = new Limits.Building(new Limits.Building.Height(10, 40));
    when(limitsService.getBuildingLimits()).thenReturn(validBuilding);

    var buildRequest = new BuildRequest();
    buildRequest.setSeason("summer");
    buildRequest.setBuilding(new BuildRequest.Building(100));
    var errors = new BeanPropertyBindingResult(buildRequest, "buildRequest");

    buildValidator.validate(buildRequest, errors);

    assertThat(errors.getAllErrors()).hasSize(1);

    var fieldError = (FieldError) errors.getAllErrors().get(0);
    assertThat(fieldError.getField()).isEqualTo("building.height");
    assertThat(fieldError.getRejectedValue()).isEqualTo(100);
    assertThat(fieldError.getCode()).isEqualTo("building height is too high");

    verify(limitsService).getBuildingLimits();
    verify(limitsService).getAvailableSeasons();
  }
}