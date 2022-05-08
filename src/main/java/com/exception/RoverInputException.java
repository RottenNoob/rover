package com.exception;

public class RoverInputException extends Exception {
	public RoverInputException(String error) {
		super(error);
	}
	
	public RoverInputException(String errorMessage, Throwable error) {
		super(errorMessage, error);
	}
}
