package com.jakes.clicker;

import com.badlogic.gdx.Gdx;

/**
 * Helper class that has a public enumerated type and a method which handles the logic of purchasing an upgrade
 * @author Jake
 */
public class Upgrade {
	public enum UpgradeType{
		TIME, QUANTITY, LUCK, LUCK_QUANTITY
	}
	
	public static void purchase(UpgradeType type){
		if(Settings.getScore() >= Math.floor(Settings.getUpgradeCost())){
			switch(type){
			case TIME:
				Settings.setScoreTimer(Settings.getScoreTimer() * Consts.UPGRADE_TIMER);
				break;
			case QUANTITY:
				Settings.setQuantity(Settings.getQuantity() + Consts.UPGRADE_QUANTITY);
			case LUCK:
				Settings.setLuck(Settings.getLuck() + Consts.UPGRADE_LUCK);
				break;
			case LUCK_QUANTITY:
				Settings.setLuckQuantity(Settings.getLuckQuantity() + Consts.UPGRADE_LUCK_QUANTITY);
				break;
			default:
				break;
			}
			Settings.setScore((int) (Settings.getScore() - Math.floor(Settings.getUpgradeCost())));
			Settings.setUpgradeCost((Settings.getUpgradeCost() * Consts.UPGRADE_MULTIPLIER));
			if(Consts.DEBUG){
				Gdx.app.log("DEBUG", "New upgrade cost: " + Settings.getUpgradeCost());
			}
		}
	}
}
