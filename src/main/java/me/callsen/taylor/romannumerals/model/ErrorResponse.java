package me.callsen.taylor.romannumerals.model;

public class ErrorResponse {
  private final int statusCode;
	private final String message;

	public ErrorResponse(int statusCode, String message) {
		this.statusCode = statusCode;
    this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getOutputValue() {
		return message;
	}
}
