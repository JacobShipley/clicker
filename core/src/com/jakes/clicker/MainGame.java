package com.jakes.clicker;

import com.badlogic.gdx.Game;

/**
 * Main game class.  Handles the switching of screens and holds the instance of the ServiceHelper
 * @author Jake
 *
 */
public class MainGame extends Game {
	ServiceHelper services;
	
	public MainGame(ServiceHelper services) {
		this.services = services;
	}

	@Override
	public void create () {
		setScreen(new GameScreen(this));
	}
}
