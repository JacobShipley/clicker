package com.jakes.clicker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Preferences helper class which handles the persistent data of the game
 * @author Jake
 */
public class Settings {
	public static Preferences prefs;
	public static final String prefString = Consts.PREF_GLOBAL;
	
	private static void load(){
		prefs = Gdx.app.getPreferences(prefString);
	}
	
	public static long getTime(){
		check();
		return prefs.getLong(Consts.PREF_TIME, TimeUtils.millis());
	}
	
	public static void setTime(long time){
		check();
		prefs.putLong(Consts.PREF_TIME, time);
		save();
	}
	
	public static int getScore(){
		check();
		return prefs.getInteger(Consts.PREF_SCORE, Consts.DEFAULT_SCORE);
	}
	
	public static void setScore(int score){
		check();
		prefs.putInteger(Consts.PREF_SCORE, score);
		save();
	}
	
	public static float getScoreTimer(){
		check();
		return prefs.getFloat(Consts.PREF_SCORE_TIMER, Consts.DEFAULT_TIMER);
	}
	
	public static void setScoreTimer(float timer){
		check();
		prefs.putFloat(Consts.PREF_SCORE_TIMER, timer);
		save();
	}
	
	public static int getQuantity(){
		check();
		return prefs.getInteger(Consts.PREF_QUANTITY, Consts.DEFAULT_QUANTITY);
	}
	
	public static void setQuantity(int amt){
		check();
		prefs.putInteger(Consts.PREF_QUANTITY, amt);
		save();
	}
	
	public static int getLuckQuantity(){
		check();
		return prefs.getInteger(Consts.PREF_LUCK_QUANTITY, Consts.DEFAULT_LUCK_QUANTITY);
	}
	
	public static void setLuckQuantity(int amt){
		check();
		prefs.putInteger(Consts.PREF_LUCK_QUANTITY, amt);
		save();
	}
	
	public static float getLuck(){
		check();
		return prefs.getFloat(Consts.PREF_LUCK, Consts.DEFAULT_LUCK);
	}
	
	public static void setLuck(float lucker){
		check();
		prefs.putFloat(Consts.PREF_LUCK, lucker);
		save();
	}
	
	private static void check(){
		if(prefs == null){
			load();
		}
	}
	
	private static void save(){
		prefs.flush();
	}

	public static void setUpgradeCost(float amt) {
		check();
		prefs.putFloat(Consts.PREF_UPGRADE_COST, amt);
		save();
	}
	
	public static float getUpgradeCost(){
		check();
		return prefs.getFloat(Consts.PREF_UPGRADE_COST, Consts.DEFAULT_UPGRADE_COST);
	}
}
