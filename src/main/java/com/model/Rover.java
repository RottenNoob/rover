package com.model;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.exception.CoordinateException;
import com.exception.RoverInputException;

public class Rover {
	private long roverId;
	private CardinalDirection facingDirection;
	private Coordinates coordinates;
	
	public long getRoverId() {
		return roverId;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}
	
	public void initializeRover(long roverId, String textInput, Set<Coordinates> existingItemsCoordinates, Plateau plateau)
			throws RoverInputException {
		this.roverId = roverId;
		List<String> inputList = Arrays.asList(textInput.split(" "));
		if (inputList.size() != 3) {
			throw new RoverInputException("The input : " + textInput + " is not a valid Rover input.");
		}
		try {
			coordinates = new Coordinates(inputList.get(0), inputList.get(1));
			if (plateau == null) {
				String errorMessage = "Cannot initialize a rover outside of a plateau.";
				throw new RoverInputException(errorMessage);
			}
			if (existingItemsCoordinates.contains(coordinates)) {
				String errorMessage = inputList.get(0) + " " + inputList.get(1) + " coordinates are already occupied.";
				throw new RoverInputException(errorMessage);
			}
			if (!plateau.isValidCoordinates(coordinates)) {
				String errorMessage = inputList.get(0) + " " + inputList.get(1) + " coordinates are out of bond.";
				throw new RoverInputException(errorMessage);
			}
		} catch (CoordinateException e) {
			String errorMessage = inputList.get(0) + " " + inputList.get(1) + " are not valid coordinates.";
			throw new RoverInputException(errorMessage, e);
		}
		try {
			facingDirection = CardinalDirection.valueOf(inputList.get(2));
		} catch (IllegalArgumentException error) {
			throw new RoverInputException("The input : " + inputList.get(2) + " is not a valid Cardinal Direction.");
		}
	}
	
	public String getPositionAsString() {
		if (facingDirection != null && coordinates != null) {
			return coordinates.toString() + " " + facingDirection.name();
		}
		return "";
	}
	
	public void applyCommandString(String roverCommandString, Plateau plateau, Set<Coordinates> existingItemsCoordinates)
			throws RoverInputException {
		try {
			RoverCommand roverCommand = RoverCommand.valueOf(roverCommandString);
			applyCommand(roverCommand, plateau, existingItemsCoordinates);
		} catch (IllegalArgumentException error) {
			throw new RoverInputException(roverCommandString + " is not a valid Rover Command.");
		} catch (CoordinateException e) {
			throw new RoverInputException(roverCommandString + " tried to move to an illegal position.");
		}
	}
	
	private void applyCommand(RoverCommand roverCommand, Plateau plateau, Set<Coordinates> existingItemsCoordinates) throws CoordinateException {
		switch(roverCommand) {
		case L :
			facingDirection = CardinalDirection.getLeft(facingDirection);
			break;
		case R :
			facingDirection = CardinalDirection.getRight(facingDirection);
			break;
		case M :
			moveRover(plateau, existingItemsCoordinates);
			break;
			default:
		}
	}
	
	private void moveRover(Plateau plateau, Set<Coordinates> existingItemsCoordinates) throws CoordinateException {
		int xMove = coordinates.getX();
		int yMove = coordinates.getY();
		switch(facingDirection) {
		case N :
			yMove++;
			break;
		case E :
			xMove++;
			break;
		case S :
			yMove--;
			break;
		case W :
			xMove--;
			break;
			default:
		}
		Coordinates potentialCoordinates = new Coordinates(xMove, yMove);
		if (plateau.isValidCoordinates(potentialCoordinates)
				&& !existingItemsCoordinates.contains(potentialCoordinates)) {
			coordinates.setX(xMove);
			coordinates.setY(yMove);
		}
	}
}
