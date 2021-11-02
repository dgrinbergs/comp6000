package com.comp6000.backend.builds;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
class BuildValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return clazz.equals(BuildRequest.class);
  }

  @Override
  public void validate(Object target, Errors errors) {
    var x = 1;
  }
}
