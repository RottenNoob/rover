package com.model;

import java.util.Arrays;
import java.util.List;

public enum CardinalDirection {
	N("North"),
	W("West"),
	S("South"),
	E("East");

	public final String value;
	
	
	private	
	
	
	CardinalDirection(String value) {
		this.value = value;
	}
	
	public static CardinalDirection getLeft(CardinalDirection cardinalDirection) {
		List<CardinalDirection> orderedCardinalDirections = getOrderedCardinalDirections();
		int position = orderedCardinalDirections.indexOf(cardinalDirection);
		if (position == 0) {
			return orderedCardinalDirections.get(orderedCardinalDirections.size() - 1);
		}
		return orderedCardinalDirections.get(position - 1);
	}
	
	public static CardinalDirection getRight(CardinalDirection cardinalDirection) {
		List<CardinalDirection> orderedCardinalDirections = getOrderedCardinalDirections();
		int position = orderedCardinalDirections.indexOf(cardinalDirection);
		if (position == orderedCardinalDirections.size() - 1) {
			return orderedCardinalDirections.get(0);
		}
		return orderedCardinalDirections.get(position + 1);
	}
	
	private static List<CardinalDirection> getOrderedCardinalDirections() {
		return Arrays.asList(N, E, S, W);
	}
}
