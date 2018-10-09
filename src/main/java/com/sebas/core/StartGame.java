package com.sebas.core;

public class StartGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start!");
		Match match = new Match("white");
		match.startGame();
	}

}
