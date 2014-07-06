package com.jakes.clicker;

import com.badlogic.gdx.graphics.Color;

/**
 * Stores all constants for the game.  Dimensions, flags, animation durations, keys, colors etc.
 * This is where you go to make minor tweaks 
 * @author Jake
 *
 */
public class Consts {
	/** Flags **/
	public static final boolean DEBUG = true;
	public static final boolean LOGIN_ENABLED = true;
	
	/** Sprites **/
	public static final String SPRITE_ICON = "icon.png";
	public static final String SPRITE_PROGRESS = "progress.png";
	public static final String SPRITE_STAR = "coin.png";
	public static final String SPRITE_STORE = "store.png";
	public static final String SPRITE_STORE_PRESSED = "store_pressed.png";
	public static final String SPRITE_INSTRUCTION = "instructions_01.png";
	public static final String SPRITE_UPGRADE_TIME = "up_time.png";
	public static final String SPRITE_UPGRADE_QUANTITY = "up_quantity.png";

	/** Dimensions */
	public static final float WIDTH = 1080f;
	public static final float HEIGHT = 1920f;
	//TODO: Refactor (WIDTH_OBJECT over OBJECT_WIDTH?)
	public static final float ICON_WIDTH = 256f;
	public static final float ICON_HEIGHT = 256f;
	public static final float STAR_WIDTH = 128f;
	public static final float STAR_HEIGHT = 128f;
	public static final float STAR_OFFSET = 32f;
	public static final float INSTRUCTION_OFFSET = 128f;
	public static final float STORE_WIDTH = 128f;
	public static final float STORE_HEIGHT = 128f;
	public static final float UPGRADE_COST_VERTICAL_OFFSET = 512f;
	public static final float UPGRADE_ITEM_WIDTH = 256f;
	public static final float UPGRADE_ITEM_HEIGHT = 128f;
	public static final float PROGRESS_HEIGHT = 128f;
	
	public static final int FONT_SIZE_LARGE = 128;
	public static final int FONT_SIZE_MEDIUM = 96;
	public static final int FONT_SIZE_SMALL = 48;
	
	/** Positions **/
	// Almost all objects in the scene are relatively placed, so very few objects need an explicit position
	public static final float PROGRESS_X = 0f;
	public static final float PROGRESS_Y = 0f;

	/** Animation **/
	public static final float TWEEN_DURATION_INSTRUCTION = 1f;
	public static final float TWEEN_DURATION_STORE_ITEM = 1f;
	
	/** Utilities */
	public static final int SECONDS_TO_MILLIS = 1000;
	
	/** GPGS information */
	public static final String LEADERBOARD = "CgkI26mezaMFEAIQAg ";
	public static final String ACHIEVE_FAILURE = "CgkI26mezaMFEAIQAQ";
	
	/** Defaults */
	public static final float DEFAULT_TIMER         = 60f;
	public static final int   DEFAULT_QUANTITY      = 1;
	public static final int   DEFAULT_LUCK_QUANTITY = 1;
	public static final float DEFAULT_LUCK          = 0.01f;
	public static final int   DEFAULT_SCORE         = 1;
	public static final int   DEFAULT_UPGRADE_COST  = 1;

	/** Upgrades */
	public static final int   UPGRADE_QUANTITY = 1;
	public static final int   UPGRADE_LUCK_QUANTITY = 1;
	public static final float UPGRADE_LUCK = 0.05f;
	public static final float UPGRADE_TIMER = 0.85f;
	public static final float UPGRADE_MULTIPLIER = 1.5f;

	/** Overflow amounts */
	public static final int CURRENCY_01 = 1;
	public static final int CURRENCY_02 = 1000;
	public static final int CURRENCY_03 = 1000000;
	public static final int CURRENCY_04 = 100000000;

	/** Preference strings */
	public static final String PREF_GLOBAL = "dontclick";
	public static final String PREF_TIME = "time";
	public static final String PREF_SCORE = "score";
	public static final String PREF_SCORE_TIMER = "timer";
	public static final String PREF_QUANTITY = "quantity";
	public static final String PREF_LUCK_QUANTITY = "luck_quantity";
	public static final String PREF_LUCK = "luck";
	public static final String PREF_UPGRADE_COST = "up_cost";

	/** Colors */
	public static final Color COLOR_BACKGROUND = Color.valueOf("eeeeee");
	public static final Color COLOR_CURRENCY_01 = Color.valueOf("f1c40f");
	public static final Color COLOR_CURRENCY_02 = Color.valueOf("2ecc71");
	public static final Color COLOR_CURRENCY_03 = Color.valueOf("1abc9c");
	public static final Color COLOR_CURRENCY_04 = Color.valueOf("e74c3c");
}
