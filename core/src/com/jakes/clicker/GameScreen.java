package com.jakes.clicker;

import com.badlogic.gdx.Screen;

/**
 * Simple screen which handles the lifecycle of the application.  Holds the instances of our model, controller, and view.
 * @author Jake
 */
public class GameScreen implements Screen {
	GameController controller;
	GameModel model;
	GameView view;
	MainGame game;
	
	public GameScreen(MainGame game){
		this.game = game;
		model = new GameModel();
		controller = new GameController(model);
		view = new GameView(model);
	}

	@Override
	public void show() {
		model.create(game.services);
	}

	@Override
	public void render(float delta) {
		view.render();
		controller.update();
	}

	@Override
	public void resize(int width, int height) {
		//TODO: Implement dynamic scaling - right now it's pretty firmly set in a 9:16 ratio
	}


	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		model.dispose();
		view.dispose();
		controller.dispose();
	}
}
