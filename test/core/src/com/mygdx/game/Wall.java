package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

 abstract public class Wall {
	int X;
	int Y;
	Sprite Wall;
	Boolean breakable;
	
	abstract Boolean getbreak();
	abstract int getX();
	abstract int getY();
	abstract Sprite getsprite();
	
	
}
