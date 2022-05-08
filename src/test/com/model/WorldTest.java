package com.model;

import com.exception.PlateauInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WorldTest {


    @Test
    void should_get_base_results_given_base_scenario() {
        World world = getBaseWorld();
        String expectedFirstPosition = "1 3 N";
        String expectedSecondPosition = "5 1 E";
        Assertions.assertEquals(expectedFirstPosition, world.getAllRoverPositions().get(0));
        Assertions.assertEquals(expectedSecondPosition, world.getAllRoverPositions().get(1));

    }

    private World getBaseWorld() {
        World world = new World();
        try {
            world.addPlateau("5 5");
            world.addRover("1 2 N", "LMLMLMLMM");
            world.addRover("3 3 E", "MMRMMRMRRM");
            world.runAllRoverCommands();
        } catch (PlateauInputException e) {
            e.printStackTrace();
        }
        return world;
    }
}