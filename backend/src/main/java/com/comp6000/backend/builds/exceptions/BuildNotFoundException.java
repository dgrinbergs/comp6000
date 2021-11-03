package com.comp6000.backend.builds.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BuildNotFoundException extends RuntimeException {

  public BuildNotFoundException(String message) {
    super(message);
  }

}
