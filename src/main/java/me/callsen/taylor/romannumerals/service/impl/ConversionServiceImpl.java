package me.callsen.taylor.romannumerals.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import me.callsen.taylor.romannumerals.model.Conversion;
import me.callsen.taylor.romannumerals.service.ConversionService;

@Service
public class ConversionServiceImpl implements ConversionService {

  Map<Integer, String> decimalToNumeralMapping  = new LinkedHashMap<Integer, String>() {{
    put(1000, "M");
    put(900, "CM");
    put(500, "D");
    put(400, "CD");
    put(100, "C");
    put(90, "XC");
    put(50, "L");
    put(40, "XL");
    put(10, "X");
    put(9, "IX");
    put(5, "V");
    put(4, "IV");
    put(1, "I");
  }};

  @Override
  public Conversion convertDecimalToNumeral(int inputValue) {
    
    int workingValue = inputValue;
    String outputValue = "";
    
    for (Map.Entry<Integer, String> entry : decimalToNumeralMapping.entrySet()) {
      int decimalKey = entry.getKey();
      if (workingValue >= decimalKey) {

        String numeralValue = entry.getValue();
        while (decimalKey <= workingValue) {
          workingValue -= decimalKey;
          outputValue += numeralValue;
        }

      }
    }

    return new Conversion(inputValue, outputValue);
  }
  
}
