package com.comp6000.backend.builds;

import com.comp6000.backend.utils.errors.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/builds")
@Validated
class BuildController {

  private final BuildService buildService;
  private final BuildValidator buildValidator;
  private final ErrorService errorService;

  @Autowired
  public BuildController(BuildService buildService, BuildValidator validator, ErrorService errorService) {
    this.buildService = buildService;
    this.buildValidator = validator;
    this.errorService = errorService;
  }

  @GetMapping
  ResponseEntity<Map<UUID, BuildRequest>> getAllBuilds() {
    return ResponseEntity.ok(buildService.getBuilds());
  }

  @GetMapping("/{uuid}")
  ResponseEntity<BuildRequest> getBuild(@PathVariable("uuid") UUID uuid) {
    return ResponseEntity.ok(buildService.getBuild(uuid));
  }

  @PostMapping
  ResponseEntity<?> submitBuild(@Valid @RequestBody BuildRequest buildRequest, BindingResult bindingResult) {
    buildValidator.validate(buildRequest, bindingResult);
    if (bindingResult.hasErrors()) {
      return ResponseEntity.badRequest().body(Map.of("errors", errorService.getErrorDetails(bindingResult)));
    }

    var buildId = buildService.saveBuild(buildRequest);
    return ResponseEntity.ok(
        Map.of("build", Map.of("id", buildId))
    );
  }

}
