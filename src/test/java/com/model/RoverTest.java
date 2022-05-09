package com.model;

import com.exception.CoordinateException;
import com.exception.PlateauInputException;
import com.exception.RoverInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RoverTest {
    @Test
    void rover_initialization_should_fail_given_another_rover_is_already_in_position() throws CoordinateException, PlateauInputException {
        Rover rover = new Rover();
        Set<Coordinates> existingCoordinates = new HashSet<>();
        existingCoordinates.add(new Coordinates(2,3));
        Plateau plateau = new Plateau();
        plateau.initializePlateau("6 6");
        RoverInputException exception = Assertions.assertThrows(RoverInputException.class,
                () -> rover.initializeRover(0, "2 3 S", existingCoordinates, plateau));
        Assertions.assertEquals("2 3 coordinates are already occupied.", exception.getMessage());
    }

    @Test
    void rover_initialization_should_fail_given_it_is_out_of_bonds() throws CoordinateException, PlateauInputException {
        Rover rover = new Rover();
        Set<Coordinates> existingCoordinates = new HashSet<>();
        existingCoordinates.add(new Coordinates(2,3));
        Plateau plateau = new Plateau();
        plateau.initializePlateau("6 6");
        RoverInputException exception = Assertions.assertThrows(RoverInputException.class,
                () -> rover.initializeRover(0, "7 3 S", existingCoordinates, plateau));
        Assertions.assertEquals("7 3 coordinates are out of bond.", exception.getMessage());
    }
}