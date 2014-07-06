package com.jakes.clicker;

import java.util.ArrayList;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.TimeUtils;
import com.jakes.clicker.tests.Tests;

/**
 * Model for the game.  Holds most dynamic data (Constants are held in Consts class) and instances of objects.
 * @author Jake
 */
public class GameModel {
	OrthographicCamera cam;
	Sprite icon, instruction, progress, storeButton;
	Sprite up_time, up_quantity;
	ArrayList<Sprite> sprites, store, upgradeCost, pointsIcons;
	Color bgColor = Consts.COLOR_BACKGROUND;
	BitmapFont font_large, font_medium, font_small;
	boolean paused = false;
	ServiceHelper services;
	TweenManager tm;
	boolean storeOpen = false;
	boolean animatingStore = false;
	
	public void create(ServiceHelper services){
		this.services = services;
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		tm = new TweenManager();
		
		if(Gdx.app.getType() == ApplicationType.Android && Consts.LOGIN_ENABLED){
			services.login();
		}
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Consts.WIDTH, Consts.HEIGHT);
		
		sprites = new ArrayList<Sprite>();
		store = new ArrayList<Sprite>();
		upgradeCost = new ArrayList<Sprite>();
		pointsIcons = new ArrayList<Sprite>();
		
		createSprites();
	
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("bebas.ttf"));
		FreeTypeFontParameter params = new FreeTypeFontParameter();
		params.size = Consts.FONT_SIZE_LARGE;
		params.genMipMaps = true;
		params.magFilter = TextureFilter.Linear;
		params.minFilter = TextureFilter.Linear;
		font_large = generator.generateFont(params);
		font_large.setColor(Color.BLACK);
		params.size = Consts.FONT_SIZE_MEDIUM;
		font_medium = generator.generateFont(params);
		font_medium.setColor(Color.BLACK);
		params.size = Consts.FONT_SIZE_SMALL;
		generator = new FreeTypeFontGenerator(Gdx.files.internal("bebas_bold.ttf"));
		font_small = generator.generateFont(params);
		font_small.setColor(Color.BLACK);
		generator.dispose();		
		
		if(Consts.DEBUG){
			new Tests(this);
		}
		
	}
	
	public void createSprites(){
		createStatics();
		createStore();
		createUpgradeView();
	}
	
	public void createStatics(){
		Texture img = new Texture(Consts.SPRITE_ICON);
		icon = new Sprite(img);
		icon.setSize(Consts.ICON_WIDTH, Consts.ICON_HEIGHT);
		icon.setPosition(cam.viewportWidth/2f - icon.getWidth()/2f, cam.viewportHeight/2f - icon.getHeight()/2f);
		sprites.add(icon);
		
		img = new Texture(Consts.SPRITE_STAR);
		Sprite pointsIcon1 = new Sprite(img);
		pointsIcon1.setSize(Consts.STAR_WIDTH, Consts.STAR_HEIGHT);
		pointsIcon1.setColor(Consts.COLOR_CURRENCY_01);
		pointsIcon1.setPosition(5f, Consts.STAR_HEIGHT + Consts.STAR_OFFSET);
		pointsIcons.add(pointsIcon1);	
		Sprite pointsIcon2 = new Sprite(img);
		pointsIcon2.setSize(Consts.STAR_WIDTH, Consts.STAR_HEIGHT);
		pointsIcon2.setColor(Consts.COLOR_CURRENCY_02);
		pointsIcon2.setPosition(pointsIcon1.getX(),  pointsIcon1.getY() + Consts.STAR_HEIGHT + Consts.STAR_OFFSET);
		pointsIcons.add(pointsIcon2);
		Sprite pointsIcon3 = new Sprite(img);
		pointsIcon3.setSize(Consts.STAR_WIDTH, Consts.STAR_HEIGHT);
		pointsIcon3.setColor(Consts.COLOR_CURRENCY_03);
		pointsIcon3.setPosition(pointsIcon2.getX(),  pointsIcon2.getY() + Consts.STAR_HEIGHT + Consts.STAR_OFFSET);
		pointsIcons.add(pointsIcon3);
		Sprite pointsIcon4 = new Sprite(img);
		pointsIcon4.setSize(Consts.STAR_WIDTH, Consts.STAR_HEIGHT);
		pointsIcon4.setColor(Consts.COLOR_CURRENCY_04);
		pointsIcon4.setPosition(pointsIcon3.getX(), pointsIcon3.getY() + Consts.STAR_HEIGHT + Consts.STAR_OFFSET);
		pointsIcons.add(pointsIcon4);
		sprites.addAll(pointsIcons);

		img = new Texture(Consts.SPRITE_INSTRUCTION);
		instruction = new Sprite(img);
		instruction.setPosition(icon.getX() - icon.getWidth()/2f, icon.getY() + Consts.INSTRUCTION_OFFSET);
		Tween.to(instruction, SpriteAccessor.POS_Y, Consts.TWEEN_DURATION_INSTRUCTION).targetRelative(Consts.INSTRUCTION_OFFSET/2f).ease(TweenEquations.easeOutSine).repeatYoyo(Tween.INFINITY, 0f).start(tm);
		sprites.add(instruction);
		
		img = new Texture(Consts.SPRITE_STORE);
		storeButton = new Sprite(img);
		storeButton.setSize(Consts.STORE_WIDTH, Consts.STORE_HEIGHT);
		storeButton.setPosition(cam.viewportWidth - Consts.STORE_WIDTH, Consts.STAR_HEIGHT*2f + Consts.STAR_OFFSET);
		sprites.add(storeButton);
		
		img = new Texture(Consts.SPRITE_PROGRESS);
		progress = new Sprite(img);
		progress.setSize(cam.viewportWidth, Consts.PROGRESS_HEIGHT);
		progress.setPosition(Consts.PROGRESS_X, Consts.PROGRESS_Y);
		sprites.add(progress);
		
	}
	
	public void createUpgradeView(){
		Texture img = new Texture(Consts.SPRITE_STAR);
		for(int i = 0; i < 4; i++){
			Sprite s = new Sprite(img);
			s.setSize(Consts.STAR_WIDTH, Consts.STAR_HEIGHT);
			s.setPosition(Consts.STAR_OFFSET + (Consts.STAR_WIDTH*2*i), cam.viewportHeight - Consts.UPGRADE_COST_VERTICAL_OFFSET);
			switch(i){
			case 0:
				s.setColor(Consts.COLOR_CURRENCY_01);
				break;
			case 1:
				s.setColor(Consts.COLOR_CURRENCY_02);
				break;
			case 2:
				s.setColor(Consts.COLOR_CURRENCY_03);
				break;
			case 3:
				s.setColor(Consts.COLOR_CURRENCY_04);
				break;
			}
			upgradeCost.add(s);
		}
	}
	
	public void createStore(){
		Texture img = new Texture(Consts.SPRITE_UPGRADE_TIME);
		up_time = new Sprite(img);
		up_time.setSize(Consts.UPGRADE_ITEM_WIDTH, Consts.UPGRADE_ITEM_HEIGHT);
		up_time.setPosition(cam.viewportWidth - Consts.STAR_OFFSET, storeButton.getY() + Consts.UPGRADE_ITEM_HEIGHT * 2f);
		
		img = new Texture(Consts.SPRITE_UPGRADE_QUANTITY);
		up_quantity = new Sprite(img);
		up_quantity.setSize(Consts.UPGRADE_ITEM_WIDTH, Consts.UPGRADE_ITEM_HEIGHT);
		up_quantity.setPosition(cam.viewportWidth - Consts.STAR_OFFSET, up_time.getY() + up_time.getHeight() + Consts.STAR_OFFSET);

		store.add(up_time);
		store.add(up_quantity);
		
		sprites.addAll(store);
	}
	
	public void clear(){
		//TODO: Losing animation/effect
		services.unlockAchievement(Consts.ACHIEVE_FAILURE);
		Settings.setTime(TimeUtils.millis());
		Settings.setScore(Consts.DEFAULT_SCORE);
		Settings.setQuantity(Consts.DEFAULT_QUANTITY);
		Settings.setScoreTimer(Consts.DEFAULT_TIMER);
		Settings.setLuck(Consts.DEFAULT_LUCK);
		Settings.setLuckQuantity(Consts.DEFAULT_LUCK_QUANTITY);
		Settings.setUpgradeCost(Consts.DEFAULT_UPGRADE_COST);
		paused = true;
	}
	
	public void toggleStore(){
		if(!animatingStore){
			if(storeOpen){
				closeStore();
			} else{
				openStore();
			}
		}
	}
	
	public void openStore(){
		animatingStore = true;
		Texture pressed = new Texture(Consts.SPRITE_STORE_PRESSED);
		storeButton.setTexture(pressed);
		for(Sprite button : store){
			Tween.to(button, SpriteAccessor.POS_X, Consts.TWEEN_DURATION_STORE_ITEM).targetRelative(-Consts.UPGRADE_ITEM_WIDTH + Consts.STAR_OFFSET*2f).ease(TweenEquations.easeOutBack).setCallback(openCallback).start(tm);
		}
	}

	public void closeStore(){
		animatingStore = true;
		if(storeOpen){
			Texture def = new Texture(Consts.SPRITE_STORE);
			storeButton.setTexture(def);
			for(Sprite button : store){
				Tween.to(button, SpriteAccessor.POS_X, Consts.TWEEN_DURATION_STORE_ITEM).targetRelative(Consts.UPGRADE_ITEM_WIDTH - Consts.STAR_OFFSET*2f).ease(TweenEquations.easeOutQuint).setCallback(closeCallback).start(tm);
			}
		}
	}
	
	
	public TweenCallback openCallback = new TweenCallback(){
		@Override
		public void onEvent(int arg0, BaseTween<?> arg1) {
			storeOpen = true;
			animatingStore = false;
		}
	};
	
	public TweenCallback closeCallback = new TweenCallback(){

		@Override
		public void onEvent(int arg0, BaseTween<?> arg1) {
			storeOpen = false;		
			animatingStore = false;
		}
		
	};
	
	public void setProgress(float amt){
		progress.setSize(amt, Consts.PROGRESS_HEIGHT);
	}
	
	public boolean isStoreOpen(){
		return storeOpen;
	}
	
	public BitmapFont getLargeFont(){
		return font_large;
	}
	
	public BitmapFont getMediumFont(){
		return font_medium;
	}
	
	public BitmapFont getSmallFont(){
		return font_small;
	}
	
	public OrthographicCamera getCamera(){
		return cam;
	}
	
	public TweenManager getTweenManager(){
		return tm;
	}
	
	public Sprite getIconSprite(){
		return icon;
	}
	
	public Sprite getUpgradeTimeSprite(){
		return up_time;
	}
	
	public Sprite getUpgradeQuantitySprite(){
		return up_quantity;
	}
	
	public Sprite getStoreButtonSprite(){
		return storeButton;
	}
	
	public ArrayList<Sprite> getSprites(){
		return sprites;
	}
	
	public ArrayList<Sprite> getStoreView(){
		return store;
	}
	
	public ArrayList<Sprite> getPointIcons(){
		return pointsIcons;
	}
	
	public ArrayList<Sprite> getUpgradeView(){
		return upgradeCost;
	}
	
	/**
	 * Calculates the number of each subset of currency given a total sum of points
	 * @param points 
	 *  	Input is total number of points accrued
	 * @return 
	 * 		Returns an array with four integers - the number of each type of currency 
	 */
	public int[] getCurrency(int points){
		//FIXME: Could be done far better, this is a hack-ish solution and not optimal
		int curr4,curr3,curr2,curr1;
		curr4=curr3=curr2=curr1=0;
		int[] curr = new int[4];
		
		if(points > 0){
			if(Consts.CURRENCY_04 % points > 0){
				while(points > Consts.CURRENCY_04){
					curr4++;
					points -= Consts.CURRENCY_04;
				}
			}
			
			if(Consts.CURRENCY_03 % points > 0){
				while(points > Consts.CURRENCY_03){
					curr3++;
					points -= Consts.CURRENCY_03;
				}
			}
			
			if(Consts.CURRENCY_02 % points> 0){
				while(points > Consts.CURRENCY_02){
					curr2++;
					points -= Consts.CURRENCY_02;
				}
			}
			
			if(Consts.CURRENCY_01 % points > 0){
				while(points > Consts.CURRENCY_01){
					points -= Consts.CURRENCY_01;
					curr1++;
				}
			}
			
			if(points == 1){
				curr1++;
				points = 0;
			}
		}
		
		curr[0] = curr1;
		curr[1] = curr2;
		curr[2] = curr3;
		curr[3] = curr4;
		
		return curr;
	}

	public void dispose(){
		
	}
}
