package com.model;

import java.util.Arrays;
import java.util.List;

import com.exception.CoordinateException;
import com.exception.PlateauInputException;

public class Plateau {
	private Coordinates upperRightCorner;
	
	public void initializePlateau(String textInput) throws PlateauInputException {
		List<String> inputList = Arrays.asList(textInput.split(" "));
		if (inputList.size() != 2) {
			throw new PlateauInputException(textInput + " is not a valid Plateau input.");
		}
		try {
			upperRightCorner = new Coordinates(inputList.get(0), inputList.get(1));
		} catch (CoordinateException e) {
			String errorMessage = textInput + " is not a valid Plateau coordinates.";
			throw new PlateauInputException(errorMessage, e);
		}
	}
	
	public boolean isValidCoordinates(Coordinates coordinates) {
		return isValidCoordinates(coordinates.getX(), coordinates.getY());
	}
	
	private boolean isValidCoordinates(int x, int y) {
		return x>= 0 && y >= 0
				&& x <= upperRightCorner.getX()
				&& y <= upperRightCorner.getY();
	}
}
