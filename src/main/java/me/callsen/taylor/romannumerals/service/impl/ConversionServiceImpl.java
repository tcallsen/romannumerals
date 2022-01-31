package me.callsen.taylor.romannumerals.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import me.callsen.taylor.romannumerals.model.Conversion;
import me.callsen.taylor.romannumerals.service.ConversionService;

/**
* Performs the actual conversion from decimal numbers into Roman Numerals.
*/
@Service
public class ConversionServiceImpl implements ConversionService {

  // defines decimal to Numeral mapping used in descending order; contains "subtractive notation" elements
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

  /**
  * {@inheritDoc}
  */
  @Override
  public Conversion convertDecimalToNumeral(int inputValue) {
    
    String outputValue = "";

    // input value is set as the "working value", which will be iterated over and eventually reduced to 0 
    //  during conversion
    int workingValue = inputValue;
    
    // loop through each of the Numeral values, starting with the largest; this ensures the largest
    //  Numerals are used to represent a number (reducing the size of the Roman Numeral representation)
    for (Map.Entry<Integer, String> entry : decimalToNumeralMapping.entrySet()) {
      int decimalKey = entry.getKey();

      // if the working value (or input value, on the first iteration) is larger than the Numeral, perform
      //  some additional steps
      if (workingValue >= decimalKey) {

        String numeralValue = entry.getValue();

        // each time the Numeral value can be divided into the working value, reduce the working value by the
        //  Numeral value, and append the Roman Numeral to the output value
        while (decimalKey <= workingValue) {
          workingValue -= decimalKey;
          outputValue += numeralValue;
        }

      }
    }

    // assemble and return a {@link Conversion} object with the input and output values
    return new Conversion(inputValue, outputValue);

  }
  
}
