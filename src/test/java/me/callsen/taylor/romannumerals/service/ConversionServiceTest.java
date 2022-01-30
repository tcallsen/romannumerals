package me.callsen.taylor.romannumerals.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import me.callsen.taylor.romannumerals.model.Conversion;
import me.callsen.taylor.romannumerals.service.impl.ConversionServiceImpl;

public class ConversionServiceTest {

  private final static int MAX_CONVERSTION_VALUE = 255;

  private BufferedReader reader;

  private ConversionService service = new ConversionServiceImpl();

  @Test
  public void verifyConversion() throws IOException {

    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource("csv/decimal-roman-numeral-conversion-table.csv").getFile());
    reader = new BufferedReader(new FileReader(file));

    int lineCount = 1;
    String lineValue = "";
    while((lineValue = reader.readLine()) != null && lineCount <= MAX_CONVERSTION_VALUE) {
      
      String [] conversionTest = lineValue.trim().split(",");
      int decimalValue = Integer.parseInt(conversionTest[0]);
      String numeralValue = conversionTest[1].trim();

      Conversion conversionResult = service.convertDecimalToNumeral(decimalValue);

      System.out.println(String.format("confirming ConversionService for value %s", decimalValue));

      assertEquals(String.valueOf(decimalValue), conversionResult.getInputValue());
      assertEquals(numeralValue, conversionResult.getOutputValue());

      ++lineCount;
    }
  }

}
