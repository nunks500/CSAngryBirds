package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class glock extends Gun{
	int X=0;
	int Y = Gdx.graphics.getHeight();
	int actualY=0;
	int power;
	Sprite glockb,glockf,glockl,glockr;
	int pickedup;
	Rectangle area;

	
	glock(){
		Random y = new Random();
		actualY = y.nextInt(Gdx.graphics.getHeight());
		Random x = new Random();
		X = x.nextInt(Gdx.graphics.getWidth());
		glockb = new Sprite(new Texture("glockback.png"));
		glockf = new Sprite(new Texture("glockfront.png"));
		glockl = new Sprite(new Texture("glockleft.png"));
		glockr = new Sprite(new Texture("glockright.png"));
		pickedup = 0;
	}
	


	public void draw(SpriteBatch game,int facing){
		update();
		switch(pickedup){
		case 1:
			if(facing == 0 ){
				game.draw(glockf,X,Y);
			}
			else if(facing == 1 ){
				game.draw(glockb,X,Y);
			}
			else if(facing == 2 ){
				game.draw(glockl,X,Y);
			}
			else if(facing == 3 ){
				game.draw(glockr,X,Y);
			}
			break;
		case 0:
			game.draw(glockr,X,Y);
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
		return glockr;
	}
	


}
