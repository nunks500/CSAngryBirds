package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
	Boolean notdone = true;
	Boolean shooting;
	float time;

	
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
		shooting =false;
		time = -1;
	}
	


	public void draw(SpriteBatch game,int facing){
		
		update();
		switch(pickedup){
		case 1:
			if(facing == 0 ){
				game.draw(bazukaf,X-10,Y-20);
			}
			else if(facing == 1 ){
				game.draw(bazukab,X-10,Y-20);
			}
			else if(facing == 2 ){
				game.draw(bazukal,X,Y);
			}
			else if(facing == 3 ){
				game.draw(bazukar,X-55,Y);
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
		actualY = Y;
	}
	
	@Override
	public Sprite getsprite2() {
		// TODO Auto-generated method stub
		return bazukaf;
	}
	@Override
	public int getotherY() {
		// TODO Auto-generated method stub
		return Y;
	}
	
	@Override
	public void setshooting(Boolean bol) {
		// TODO Auto-generated method stub
		shooting = bol;
	}
	public boolean animaçaoarma() {
		// TODO Auto-generated method stub
		if(time==-1){
			if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
			{
				time=System.nanoTime();
				return true;

			}
		}
		else{
			if((System.nanoTime()-time/1000000)<500){
				return true;
			}
			else {
				time=-1;
				return false;
			}
		}
		return false;

	}

}
