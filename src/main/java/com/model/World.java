package com.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.exception.PlateauInputException;
import com.exception.RoverInputException;

public class World {
	private Plateau plateau;
	private List<Rover> rovers = new ArrayList<>();
	private Map<Long, String> commandsAsString = new HashMap<>();
	private long idIncrement = 0;
	
	public void initializeWorldFromFile(Path path) throws IOException, PlateauInputException {
		BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));
		String plateauCoordinates = reader.readLine();
		addPlateau(plateauCoordinates);
		String roverInput = null;
		String commandAsString = null;
		String line;
		while ((line = reader.readLine()) != null) {
			if (roverInput == null) {
				roverInput = line;
			} else if (commandAsString == null) {
				commandAsString = line;
				addRover(roverInput, commandAsString);
				roverInput = null;
				commandAsString = null;
			}
		}
	    reader.close();
	}
	
	public void addPlateau(String coordinatesAsString) throws PlateauInputException {
		plateau = new Plateau();
		plateau.initializePlateau(coordinatesAsString);
	}
	
	public void addRover(String roverInput, String commandAsString) {
		Rover rover = new Rover();
		Set<Coordinates> existingRoverCoordinates = rovers.stream()
				.map(r -> r.getCoordinates()).collect(Collectors.toSet());
		try {
			rover.initializeRover(idIncrement, roverInput, existingRoverCoordinates, plateau);
			rovers.add(rover);
			commandsAsString.put(idIncrement, commandAsString);
			idIncrement++;
		} catch (RoverInputException e) {
			e.printStackTrace();
		}
	}
	
	public void runAllRoverCommands() {
		for (Rover rover:rovers) {
			Set<Coordinates> existingRoverCoordinates = rovers.stream().filter(r -> !rover.getCoordinates().equals(r.getCoordinates()))
					.map(r -> r.getCoordinates()).collect(Collectors.toSet());
			String roverCommands = commandsAsString.get(rover.getRoverId());
			for (int i = 0; i < roverCommands.length(); i++) {
				String command = String.valueOf(roverCommands.charAt(i));
				try {
					rover.applyCommandString(command, plateau, existingRoverCoordinates);
				} catch (RoverInputException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public List<String> getAllRoverPositions() {
		return rovers.stream().map(r -> r.getPositionAsString()).collect(Collectors.toList());
	}
}
