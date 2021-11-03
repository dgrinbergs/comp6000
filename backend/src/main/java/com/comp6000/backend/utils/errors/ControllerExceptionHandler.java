package com.comp6000.backend.utils.errors;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

  private final DefaultErrorAttributes defaultErrorAttributes;
  private final ErrorAttributeOptions errorAttributeOptions;

  public ControllerExceptionHandler(DefaultErrorAttributes defaultErrorAttributes) {
    this.defaultErrorAttributes = defaultErrorAttributes;
    this.errorAttributeOptions = ErrorAttributeOptions.defaults().including(ErrorAttributeOptions.Include.MESSAGE);
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({ValidationException.class, ConstraintViolationException.class})
  Map<String, Object> exceptionHandler(WebRequest webRequest, HttpServletRequest servletRequest, ValidationException validationException) {
    webRequest.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.BAD_REQUEST.value(), RequestAttributes.SCOPE_REQUEST);
    webRequest.setAttribute(RequestDispatcher.ERROR_EXCEPTION, validationException, RequestAttributes.SCOPE_REQUEST);
    webRequest.setAttribute(RequestDispatcher.ERROR_REQUEST_URI, servletRequest.getRequestURI(), RequestAttributes.SCOPE_REQUEST);
    return defaultErrorAttributes.getErrorAttributes(webRequest, errorAttributeOptions);
  }
}
