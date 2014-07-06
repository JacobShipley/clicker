package com.jakes.clicker;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Simple TweenAccessor that exposes Sprite properties
 * @author Jake
 */
public class SpriteAccessor implements TweenAccessor<Sprite>{
	public static final int POS_X = 0;
	public static final int POS_Y = 1;
	
	@Override
	public int getValues(Sprite target, int type, float[] args) {
		switch(type){
		case POS_X:
			args[0] = target.getX();
			return 1;
		case POS_Y:
			args[0] = target.getY();
			return 1;
		}
		return 0;
	}

	@Override
	public void setValues(Sprite target, int type, float[] args) {
		switch(type){
		case POS_X:
			target.setX(args[0]);
			break;
		case POS_Y:
			target.setY(args[0]);
			break;
		}
	}
	
}
