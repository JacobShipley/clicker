package com.jakes.clicker.tests;

import com.badlogic.gdx.Gdx;
import com.jakes.clicker.GameModel;

public class Tests {
	GameModel game;
	
	public Tests(GameModel gameModel){
		this.game = gameModel;
		
		// Run all tests on instantiation - could probably be done better
		testCurrency();
	}
	
	public void testCurrency(){
		int input = 303010059;
		int[] result = game.getCurrency(input);
		int[] expected = new int[4];
		expected[0] = 59;
		expected[1] = 10;
		expected[2] = 3;
		expected[3] = 3;
		Gdx.app.log("TEST", "Input: " + input);
		for(int i = 0; i < expected.length; i++){
			Gdx.app.log("TEST", "Output (Expected): " + expected[i]);
		}
		for(int i = 0; i < result.length; i++){
			Gdx.app.log("TEST", "Output (Actual): " + result[i]);
		}
	}
}
