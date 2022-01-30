package me.callsen.taylor.romannumerals.model;

public class Conversion {
  
  private final int inputValue;
	private final String outputValue;

	public Conversion(int inputValue, String outputValue) {
		this.inputValue = inputValue;
    this.outputValue = outputValue;
	}

	public String getInputValue() {
		return String.valueOf(inputValue);
	}

	public String getOutputValue() {
		return outputValue;
	}

}
