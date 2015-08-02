package com.testing;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ak47 extends Gun{
	int X=0;
	int Y = Gdx.graphics.getHeight();
	int actualY=0;
	int power;
	Sprite ak47b,ak47f,ak47l,ak47r,ak47r2,ak47bs,ak47fs,ak47ls,ak47rs;
	int pickedup;
	Rectangle area;
	Boolean shooting,androidspace;
	float time;

	
	ak47(){
		Random y = new Random();
		actualY = y.nextInt(Gdx.graphics.getHeight());
		Random x = new Random();
		X = x.nextInt(Gdx.graphics.getWidth());
		ak47b = new Sprite(new Texture("ak47back.png"));
		ak47f = new Sprite(new Texture("ak47front.png"));
		ak47l = new Sprite(new Texture("ak47left.png"));
		ak47r = new Sprite(new Texture("ak47right.png"));
		ak47bs = new Sprite(new Texture("ak47backshoot.png"));
		ak47fs = new Sprite(new Texture("ak47frontshoot.png"));
		ak47ls = new Sprite(new Texture("ak47leftshoot.png"));
		ak47rs = new Sprite(new Texture("ak47rightshoot.png"));
		shooting = false;
		pickedup = 0;
		time = -1;
		androidspace=false;
	}
	


	public void draw(SpriteBatch game,int facing){
		update();
		switch(pickedup){
		case 1:
			if(facing == 0 ){
				if(animaçaoarma())
				{
					game.draw(ak47fs,X,Y-15);
				}
					else
				game.draw(ak47f,X,Y-20);
			}
			else if(facing == 1 ){
				if(animaçaoarma())
				{
					game.draw(ak47bs,X,Y + 5);
				}
					else
				game.draw(ak47b,X,Y);
			}
			else if(facing == 2 ){
				if(animaçaoarma())
				{
					game.draw(ak47ls,X - 25,Y);
				}
					else
				game.draw(ak47l,X - 25,Y);
			}
			else if(facing == 3 ){
				if(animaçaoarma())
				{
					game.draw(ak47rs,X,Y);
				}
					else
				game.draw(ak47r,X,Y);
			}
			break;
		case 0:
			game.draw(ak47r,X,Y);
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
		return ak47r;
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
		return ak47f;
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
			if(Gdx.input.isKeyPressed(Input.Keys.SPACE) || androidspace)
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
				androidspace = false;
				time=-1;
				return false;
			}
		}
		return false;

	}
	
	public Boolean getandroidspace(){
		return androidspace;
	}
	
	public void setandroidspace(Boolean android){
		androidspace = android;
	}


}
