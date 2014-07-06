package com.jakes.clicker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.jakes.clicker.Upgrade.UpgradeType;

/**
 * Controller for the game.  Handles all logic for manipulating data within the model.
 * @author Jake
 */
public class GameController extends TouchInput {
	GameModel game;
	Vector3 touchPoint = new Vector3();

	public GameController(GameModel game){
		this.game = game;
		Gdx.input.setInputProcessor(this);
	}
	
	public void update(){
		// Make sure we're tracking time while game isn't played
		//TODO: Doesn't account for tampering - probably not even necessary
		if(TimeUtils.timeSinceMillis(Settings.getTime()) >= Settings.getScoreTimer() * Consts.SECONDS_TO_MILLIS){
			int overdraft = (int) (TimeUtils.timeSinceMillis(Settings.getTime()) / (Settings.getScoreTimer() * Consts.SECONDS_TO_MILLIS));
			Settings.setScore(Settings.getScore() + (Settings.getQuantity() * overdraft));
			Settings.setTime(TimeUtils.millis());
			float luckerDog = (float) Math.random();
			if(luckerDog <= Settings.getLuck()){
				Settings.setScore(Settings.getScore() + Settings.getLuckQuantity());
			}
		}
		game.getTweenManager().update(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		game.getCamera().unproject(touchPoint.set(screenX, screenY, 0));
		if(game.getIconSprite().getBoundingRectangle().contains(touchPoint.x, touchPoint.y)){
			game.clear();
		}
		if(game.getStoreButtonSprite().getBoundingRectangle().contains(touchPoint.x, touchPoint.y)){
			game.toggleStore();
		}
		if(game.isStoreOpen()){
			for(Sprite s : game.getStoreView()){
				if(s.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)){
					if(s.equals(game.getUpgradeTimeSprite())){
						Upgrade.purchase(UpgradeType.TIME);
					}
					if(s.equals(game.getUpgradeQuantitySprite())){
						Upgrade.purchase(UpgradeType.QUANTITY);
					}
				}
			}
		}
		return false;
	}
	
	public void dispose(){
		
	}
}
