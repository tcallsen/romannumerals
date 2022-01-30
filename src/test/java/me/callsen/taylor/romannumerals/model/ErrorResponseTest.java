package me.callsen.taylor.romannumerals.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ErrorResponseTest {
  
  @Test
  public void accessorsTest() {
    ErrorResponse response = new ErrorResponse(500, "error-message");
    assertEquals(500, response.getStatusCode());
    assertEquals("error-message", response.getMessage());
  }

}
