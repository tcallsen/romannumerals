package me.callsen.taylor.romannumerals.service;

import me.callsen.taylor.romannumerals.model.Conversion;

public interface ConversionService {
  
  Conversion convertDecimalToNumeral(int inputValue);

}
