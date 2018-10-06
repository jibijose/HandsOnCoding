package com.test.puzzle2;

public class Main {
	public static void main(String[] args) {
		if (new Sleeper().enter(new Dream()) != 0) {
			// The goal is to reach this line
			System.out.println("Am I still dreaming?");
		}
	}
}