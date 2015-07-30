package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bazuka extends Gun{
	int X=0;
	int Y = Gdx.graphics.getHeight();
	int actualY=0;
	int power;
	Sprite bazukab,bazukaf,bazukal,bazukar;
	int pickedup;
	Rectangle area;

	
	Bazuka(){
		Random y = new Random();
		actualY = y.nextInt(Gdx.graphics.getHeight());
		Random x = new Random();
		X = x.nextInt(Gdx.graphics.getWidth());
		bazukab = new Sprite(new Texture("bazukaback.png"));
		bazukaf = new Sprite(new Texture("bazukafront.png"));
		bazukal = new Sprite(new Texture("bazukaleft.png"));
		bazukar = new Sprite(new Texture("bazukaright.png"));
		pickedup = 0;
	}
	


	public void draw(SpriteBatch game,int facing){
		update();
		switch(pickedup){
		case 1:
			if(facing == 0 ){
				game.draw(bazukaf,X,Y);
			}
			else if(facing == 1 ){
				game.draw(bazukab,X,Y);
			}
			else if(facing == 2 ){
				game.draw(bazukal,X,Y);
			}
			else if(facing == 3 ){
				game.draw(bazukar,X,Y);
			}
			break;
		case 0:
			game.draw(bazukar,X,Y);
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
		return bazukar;
	}
	

}
