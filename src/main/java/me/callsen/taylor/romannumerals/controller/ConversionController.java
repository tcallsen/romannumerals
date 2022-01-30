package me.callsen.taylor.romannumerals.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import me.callsen.taylor.romannumerals.config.Constants;
import me.callsen.taylor.romannumerals.model.Conversion;
import me.callsen.taylor.romannumerals.model.ErrorResponse;
import me.callsen.taylor.romannumerals.service.ConversionService;

@RestController
@Validated
public class ConversionController {
    
	private ConversionService conversionService;
  
  public ConversionController(@Autowired ConversionService conversionService) {
    this.conversionService = conversionService;
  }

  @GetMapping(Constants.ROMAN_NUMERNAL_CONVERSION_ENDPOINT)
  public Conversion handleConvertRequest(@RequestParam(value = "query", required = true) @Min(Constants.ROMAN_NUMERNAL_CONVERSION_MIN_VALUE) @Max(Constants.ROMAN_NUMERNAL_CONVERSION_MAX_VALUE) int inputValue) {
    return conversionService.convertDecimalToNumeral(inputValue);
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleMissingRequestParameter() {
      return new ErrorResponse(400, "query parameter 'query' is required");
  }

  @ExceptionHandler({ ConstraintViolationException.class, MethodArgumentTypeMismatchException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleInvalidRequestParameter() {
      return new ErrorResponse(400, String.format("query parameter 'query' must be an integer between %d and %d", Constants.ROMAN_NUMERNAL_CONVERSION_MIN_VALUE, Constants.ROMAN_NUMERNAL_CONVERSION_MAX_VALUE));
  }

}
