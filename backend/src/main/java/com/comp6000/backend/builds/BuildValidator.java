package com.comp6000.backend.builds;

import com.comp6000.backend.limits.LimitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
class BuildValidator implements Validator {

  private final LimitsService limitsService;

  @Autowired
  public BuildValidator(LimitsService limitsService) {
    this.limitsService = limitsService;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return clazz.equals(BuildRequest.class);
  }

  @Override
  public void validate(Object target, Errors errors) {
    var buildRequest = (BuildRequest) target;
    validateSeason(buildRequest.getSeason(), errors);
    validateBuilding(buildRequest.getBuilding(), errors);
  }

  void validateSeason(String season, Errors errors) {
    if (!limitsService.getAvailableSeasons().contains(season)) {
      errors.rejectValue("season", "invalid season provided");
    }
  }

  void validateBuilding(BuildRequest.Building building, Errors errors) {
    var buildingLimits = limitsService.getBuildingLimits();
    if (building.height() > buildingLimits.height().max()) {
      errors.rejectValue("building.height", "building height is too high");
    } else if (building.height() < buildingLimits.height().min()) {
      errors.rejectValue("building.height", "building height is too low");
    }
  }
}
