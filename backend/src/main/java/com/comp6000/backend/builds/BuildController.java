package com.comp6000.backend.builds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("builds")
@Validated
class BuildController {

  private final BuildValidator validator;
  private final BuildService buildService;

  @Autowired
  public BuildController(BuildValidator validator, BuildService buildService) {
    this.validator = validator;
    this.buildService = buildService;
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
    validator.validate(buildRequest, bindingResult);
    if (bindingResult.hasErrors()) {
      var errors = bindingResult.getFieldErrors().stream().map(FieldError::getCode).collect(Collectors.toList());
      return ResponseEntity.badRequest().body(errors);
    }

    var buildId = buildService.saveBuild(buildRequest);
    return ResponseEntity.ok(buildId);
  }

}
