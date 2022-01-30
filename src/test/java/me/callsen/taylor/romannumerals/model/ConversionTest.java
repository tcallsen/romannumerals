package me.callsen.taylor.romannumerals.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ConversionTest {
  
  @Test
  public void accessorsTest() {
    Conversion conversion = new Conversion(5, "output-value");
    assertEquals("5", conversion.getInputValue());
    assertEquals("output-value", conversion.getOutputValue());
  }

}
