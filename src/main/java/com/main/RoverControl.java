package com.main;

import com.exception.PlateauInputException;
import com.model.World;

public class RoverControl {

	public static void main(String[] args) throws PlateauInputException {
		World world = new World();
		world.addPlateau("5 5");
		world.addRover("1 2 N", "LMLMLMLMM");
		world.addRover("3 3 E", "MMRMMRMRRM");
		world.runAllRoverCommands();
		world.getAllRoverPositions().forEach(s -> System.out.println(s));

	}

}
