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
	abstract public int getotherY();
	abstract public Sprite getsprite();
	abstract public Sprite getsprite2();
	abstract public int getpickedup();
	abstract public void setpickedup(int pickedup);
	abstract public void setX(int X);
	abstract public void setY(int Y);

	
}
