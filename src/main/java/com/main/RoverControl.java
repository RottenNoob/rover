package com.main;

import java.io.IOException;
import java.nio.file.Paths;

import com.exception.PlateauInputException;
import com.exception.RoverControlException;
import com.model.World;

public class RoverControl {

	public static void main(String[] args) throws PlateauInputException, RoverControlException, IOException {
		if (args.length != 1) {
			throw new RoverControlException("This program requires a single parameter.");
		}
		World world = new World();
		world.initializeWorldFromFile(Paths.get(args[0]));
		world.runAllRoverCommands();
		world.getAllRoverPositions().forEach(s -> System.out.println(s));

	}

}
