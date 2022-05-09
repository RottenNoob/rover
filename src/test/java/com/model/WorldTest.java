package com.model;

import com.exception.PlateauInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WorldTest {


    @Test
    void should_get_base_results_given_base_scenario() {
        World world = new World();
        try {
            world.addPlateau("5 5");
            world.addRover("1 2 N", "LMLMLMLMM");
            world.addRover("3 3 E", "MMRMMRMRRM");
            world.runAllRoverCommands();
        } catch (PlateauInputException e) {
            e.printStackTrace();
        }
        String expectedFirstPosition = "1 3 N";
        String expectedSecondPosition = "5 1 E";
        Assertions.assertEquals(expectedFirstPosition, world.getAllRoverPositions().get(0));
        Assertions.assertEquals(expectedSecondPosition, world.getAllRoverPositions().get(1));

    }

    @Test
    void rovers_should_not_move_given_it_is_trying_to_move_out_of_bonds() {
        World world = new World();
        try {
            world.addPlateau("4 4");
            world.addRover("4 4 N", "MMRMM");
            world.addRover("0 0 S", "MMMRMMM");
            world.runAllRoverCommands();
        } catch (PlateauInputException e) {
            e.printStackTrace();
        }

        String expectedFirstPosition = "4 4 E";
        String expectedSecondPosition = "0 0 W";
        Assertions.assertEquals(expectedFirstPosition, world.getAllRoverPositions().get(0));
        Assertions.assertEquals(expectedSecondPosition, world.getAllRoverPositions().get(1));
    }

    @Test
    void rovers_should_not_move_given_it_is_trying_to_move_on_an_occupied_position() {
        World world = new World();
        try {
            world.addPlateau("6 4");
            world.addRover("3 3 N", "MMMM");
            world.addRover("3 4 S", "MMMM");
            world.runAllRoverCommands();
        } catch (PlateauInputException e) {
            e.printStackTrace();
        }

        String expectedFirstPosition = "3 3 N";
        String expectedSecondPosition = "3 4 S";
        Assertions.assertEquals(expectedFirstPosition, world.getAllRoverPositions().get(0));
        Assertions.assertEquals(expectedSecondPosition, world.getAllRoverPositions().get(1));
    }
}