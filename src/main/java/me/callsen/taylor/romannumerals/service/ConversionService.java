package me.callsen.taylor.romannumerals.service;

import me.callsen.taylor.romannumerals.model.Conversion;

/**
* Performs the actual conversion from decimal numbers into Roman Numerals.
*/
public interface ConversionService {
  
  /**
  * Converts the supplied decminal number into Roman Numerals. Returns a {@link Conversion} POJO
  * containing the input and output values from the conversion.
  *
  * @param inputValue deciamal value that will be converted to Roman Numerals.
  * @return POJO containing the input and output values from the conversion.
  */
  Conversion convertDecimalToNumeral(int inputValue);

}
