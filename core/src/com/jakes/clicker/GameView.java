package com.jakes.clicker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * View for the game.  Handles all rendering of the data which is stored in the GameModel class
 * @author Jake
 */
public class GameView {
	GameModel game;
	SpriteBatch batch;
	
	public GameView(GameModel game){
		this.game = game;
		batch = new SpriteBatch();
	}
	
	public void render(){
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(Consts.COLOR_BACKGROUND.r, Consts.COLOR_BACKGROUND.g, Consts.COLOR_BACKGROUND.b, 1);
		batch.setProjectionMatrix(game.getCamera().combined);
		batch.begin();
		for(Sprite s : game.getSprites()){
			s.draw(batch);
		}
		if(game.isStoreOpen()){
			game.getLargeFont().draw(batch, "Upgrade Cost: ", game.getCamera().viewportWidth/2f - Consts.UPGRADE_COST_VERTICAL_OFFSET/2f, game.getCamera().viewportHeight - Consts.UPGRADE_COST_VERTICAL_OFFSET/2f);
			//ArrayList<Sprite> total = getCurrencySprites(Settings.getScore());
			for(int i = 0; i < game.getUpgradeView().size(); i++){
				Sprite s = game.getUpgradeView().get(i);
				s.draw(batch);
				game.getSmallFont().draw(batch, "x " + game.getCurrency((int) Math.floor(Settings.getUpgradeCost()))[i], s.getX() + s.getHeight(), s.getY() + Consts.STAR_OFFSET*2.5f);
			}
		}
		for(int i = 0; i < game.getPointIcons().size(); i++){
			Sprite pointsIcon = game.getPointIcons().get(i);
			game.getLargeFont().draw(batch, "x " + game.getCurrency(Settings.getScore())[i], pointsIcon.getX() + pointsIcon.getWidth(), pointsIcon.getY() + pointsIcon.getHeight() - Consts.STAR_OFFSET/2f);
		}
		game.setProgress(game.getCamera().viewportWidth * ((Settings.getScoreTimer() * Consts.SECONDS_TO_MILLIS - TimeUtils.timeSinceMillis(Settings.getTime()))/1000f)/Settings.getScoreTimer());
		batch.end();
	}
	
	public void dispose(){
		batch.dispose();
	}
}
