package com.comp6000.backend.utils.errors;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ErrorService {

  public List<ErrorDetail> getErrorDetails(BindingResult bindingResult) {
    return bindingResult.getAllErrors()
        .stream()
        .map(e -> (FieldError) e)
        .map(e -> new ErrorDetail(e.getField(), e.getRejectedValue(), e.getCode()))
        .collect(Collectors.toList());
  }

  public record ErrorDetail(String field, Object value, String message) {
  }
}
