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

/**
* Handles requests made to the Roman Numerals conversion endpoint, as well as request
* parameter validation and error handling.
*/
@RestController
@Validated
public class ConversionController {
    
  private ConversionService conversionService;
  
  /**
  * Constructs an instance of this controller, which validates requests and coordinates
  * with the {@link ConversionService}.
  *
  * @param conversionService used to perform decimal to Roman Numerals conversions.
  */
  public ConversionController(@Autowired ConversionService conversionService) {
    this.conversionService = conversionService;
  }

  /**
  * Handles GET requests to the Roman Numerals conversion endpoint. Validates request
  * parameters ensuring they are supplied, of the correct type, and enforces min and max constraints.
  *
  * @param inputValue deciamal value supplied to the {@link ConversionService} for conversion 
  *  to Roman Numerals. This a required parameter, with a set min and max value.
  * @return POJO containing the input and output values from the conversion.
  */
  @GetMapping(Constants.ROMAN_NUMERNAL_CONVERSION_ENDPOINT)
  public Conversion handleConvertRequest(@RequestParam(value = "query", required = true) @Min(Constants.ROMAN_NUMERNAL_CONVERSION_MIN_VALUE) @Max(Constants.ROMAN_NUMERNAL_CONVERSION_MAX_VALUE) int inputValue) {
    return conversionService.convertDecimalToNumeral(inputValue);
  }

  /**
  * Handles exception cases where the request does not contain the required request parameters.
  *
  * @return POJO containing an error response status code and message.
  */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleMissingRequestParameter() {
      return new ErrorResponse(400, "query parameter 'query' is required");
  }

  /**
  * Handles exception cases where request parameters are an invalid type, or ouside of the min and 
  * max range.
  *
  * @return POJO containing an error response status code and message.
  */
  @ExceptionHandler({ ConstraintViolationException.class, MethodArgumentTypeMismatchException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleInvalidRequestParameter() {
      return new ErrorResponse(400, String.format("query parameter 'query' must be an integer between %d and %d", Constants.ROMAN_NUMERNAL_CONVERSION_MIN_VALUE, Constants.ROMAN_NUMERNAL_CONVERSION_MAX_VALUE));
  }

}
