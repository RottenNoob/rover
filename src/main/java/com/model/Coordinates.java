package com.model;

import com.exception.CoordinateException;

public class Coordinates {
	private int x;
	private int y;
	
	public Coordinates(String xString, String yString) throws CoordinateException {
		this.x = getCoordinateFromString(xString);
		this.y = getCoordinateFromString(yString);
	}
	
	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return x + " " + y;
	}
	
	private int getCoordinateFromString(String inputNumber) throws CoordinateException {
		try {
			return Integer.parseInt(inputNumber);
		} catch (NumberFormatException error) {
			throw new CoordinateException("The input : " + inputNumber + " is not a valid coordinate.");
		}
	}
}
