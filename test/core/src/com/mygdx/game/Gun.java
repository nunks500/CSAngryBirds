package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

abstract public class Gun {
	int X;
	int Y;
	int power;
	Sprite gun;
	int pickedup;
	Rectangle area;
	
	abstract public void draw(SpriteBatch game,int facing);
	abstract public int getX();
	abstract public int getY();
	abstract public Sprite getsprite();
	
}
