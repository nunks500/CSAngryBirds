package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class m4a1 extends Gun{
	int X=0;
	int Y = Gdx.graphics.getHeight();
	int actualY=0;
	int power;
	Sprite m4a1b,m4a1f,m4a1l,m4a1r;
	int pickedup;
	Rectangle area;
	
	m4a1(){
		Random y = new Random();
		actualY = y.nextInt(Gdx.graphics.getHeight());
		Random x = new Random();
		X = x.nextInt(Gdx.graphics.getWidth());
		m4a1b = new Sprite(new Texture("m4a1back.png"));
		m4a1f = new Sprite(new Texture("m4a1front.png"));
		m4a1l = new Sprite(new Texture("m4a1left.png"));
		m4a1r = new Sprite(new Texture("m4a1right.png"));
		pickedup = 0;
	}
	@Override
	public void draw(SpriteBatch game, int facing) {
		// TODO Auto-generated method stub
		update();
		switch(pickedup){
		case 1:
			if(facing == 0 ){
				game.draw(m4a1f,X,Y);
			}
			else if(facing == 1 ){
				game.draw(m4a1b,X,Y);
			}
			else if(facing == 2 ){
				game.draw(m4a1l,X,Y);
			}
			else if(facing == 3 ){
				game.draw(m4a1r,X,Y);
			}
			break;
		case 0:
			game.draw(m4a1r,X,Y);
		}
		
	}
	
	private void update() {
		// TODO Auto-generated method stub
		if(Y > actualY)
			Y--;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return X;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return actualY;
	}

	@Override
	public Sprite getsprite() {
		// TODO Auto-generated method stub
		return m4a1r;
	}
	@Override
	public int getpickedup() {
		// TODO Auto-generated method stub
		return pickedup;
	}
	@Override
	public void setpickedup(int pickedup) {
		// TODO Auto-generated method stub
		this.pickedup=pickedup;
	}
	

	@Override
	public void setX(int X) {
		// TODO Auto-generated method stub
		this.X=X;
	}



	@Override
	public void setY(int Y) {
		// TODO Auto-generated method stub
		this.Y=Y;
		actualY =Y;
	}
	@Override
	public Sprite getsprite2() {
		// TODO Auto-generated method stub
		return m4a1f;
	}
	
	@Override
	public int getotherY() {
		// TODO Auto-generated method stub
		return Y;
	}

}
