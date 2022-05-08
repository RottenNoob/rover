package com.model;

public enum RoverCommand {
	L("Left"),
	R("Right"),
	M("Move");

	public final String value;
	
	RoverCommand(String value) {
		this.value = value;
	}
}
