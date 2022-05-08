package com.exception;

public class PlateauInputException extends Exception {
	public PlateauInputException(String error) {
		super(error);
	}
	
	public PlateauInputException(String errorMessage, Throwable error) {
		super(errorMessage, error);
	}
}
