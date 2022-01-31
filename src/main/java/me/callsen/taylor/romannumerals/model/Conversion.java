package me.callsen.taylor.romannumerals.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
* POJO containing input and output values used in the deciamal to Roman Numerals conversion.
*/
public class Conversion {
  
  private final int inputValue;
  private final String outputValue;

  /**
  * Constructs an instance of this object containing the input and output values used
  * in the represented conversion. Used to generate the JSON response output by the
  * conversion API.
  *
  * @param inputValue decimal number supplied to the conversion service.
  * @param outputValue resulting Roman Numeral returned from the conversion service.
  */
  public Conversion(int inputValue, String outputValue) {
    this.inputValue = inputValue;
    this.outputValue = outputValue;
  }

  /**
  * Retrieves the input value from this conversion.
  *
  * @returns decimal number supplied to the conversion service, in String form.
  */
  @JsonProperty("input")
  public String getInputValue() {
    return String.valueOf(inputValue);
  }

  /**
  * Retrieves the output value from this conversion.
  *
  * @returns resulting Roman Numeral returned from the conversion service.
  */
  @JsonProperty("output")
  public String getOutputValue() {
    return outputValue;
  }

}
