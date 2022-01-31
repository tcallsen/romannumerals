package me.callsen.taylor.romannumerals.model;

/**
* POJO containing an error response code and message.
*/
public class ErrorResponse {
  private final int statusCode;
  private final String message;

  /**
  * Constructs an instance of this object containing the error response code and message. Used to 
  * generate the JSON response output by the API in error handling scenarios.
  *
  * @param statusCode HTTP status code describing the error scenario.
  * @param message describing the error scenario.
  */
  public ErrorResponse(int statusCode, String message) {
    this.statusCode = statusCode;
    this.message = message;
  }

  /**
  * Retrieves the status code for the error scenario.
  *
  * @returns HTTP status code representing the error scenario.
  */
  public int getStatusCode() {
    return statusCode;
  }

  /**
  * Retrieves the message for the error scenario.
  *
  * @returns text message representing the error scenario.
  */
  public String getMessage() {
    return message;
  }
  
}
