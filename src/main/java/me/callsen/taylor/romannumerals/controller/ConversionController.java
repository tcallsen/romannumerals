package me.callsen.taylor.romannumerals.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import me.callsen.taylor.romannumerals.model.Conversion;
import me.callsen.taylor.romannumerals.model.ErrorResponse;

@RestController
@Validated
public class ConversionController {
  
  @GetMapping("/romannumeral")
  public Conversion hello(@RequestParam(value = "query", required = true) @Min(1) @Max(255) int inputValue) {
    return new Conversion(inputValue, "N/A");
  }

  @ExceptionHandler({ ConstraintViolationException.class, MethodArgumentTypeMismatchException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleInvalidRequestParameter() {
      return new ErrorResponse(400, "query parameter 'query' must be an integer between 1 and 255");
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleMissingRequestParameter() {
      return new ErrorResponse(400, "query parameter 'query' is required");
  }

}
