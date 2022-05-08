package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.exception.PlateauInputException;
import com.exception.RoverInputException;

public class World {
	private Plateau plateau;
	private List<Rover> rovers = new ArrayList<>();
	private List<String> commandsAsString = new ArrayList<>();
	
	public void addPlateau(String coordinatesAsString) throws PlateauInputException {
		plateau = new Plateau();
		plateau.initializePlateau(coordinatesAsString);
	}
	
	public void addRover(String roverInput, String commandAsString) {
		Rover rover = new Rover();
		Set<Coordinates> existingRoverCoordinates = rovers.stream()
				.map(r -> r.getCoordinates()).collect(Collectors.toSet());
		try {
			rover.initializeRover(roverInput, existingRoverCoordinates);
			rovers.add(rover);
			commandsAsString.add(commandAsString);
		} catch (RoverInputException e) {
			e.printStackTrace();
		}
	}
	
	
}
