package com.testing;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class m4a1 extends Gun{
	int X=0;
	int Y = Gdx.graphics.getHeight();
	int actualY=0;
	int power;
	Sprite m4a1b,m4a1f,m4a1l,m4a1r,m4a1ls,m4a1rs,m4a1fs,m4a1bs;
	int pickedup;
	Rectangle area;
	Boolean shooting,androidspace;
	float time;
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	m4a1(){
		Random y = new Random();
		actualY = y.nextInt(Gdx.graphics.getHeight());
		Random x = new Random();
		X = x.nextInt(Gdx.graphics.getWidth());
		m4a1b = new Sprite(new Texture("m4a1back.png"));
		m4a1f = new Sprite(new Texture("m4a1front.png"));
		m4a1l = new Sprite(new Texture("m4a1left.png"));
		m4a1r = new Sprite(new Texture("m4a1right.png"));
		m4a1bs = new Sprite(new Texture("m4a1backshoot.png"));
		m4a1fs = new Sprite(new Texture("m4a1frontshoot.png"));
		m4a1ls = new Sprite(new Texture("m4a1leftshoot.png"));
		m4a1rs = new Sprite(new Texture("m4a1rightshoot.png"));
		androidspace = false;
		pickedup = 0;
		shooting = false;
		time = -1;
	}
	@Override
	public void draw(SpriteBatch game, int facing) {
		// TODO Auto-generated method stub
		update();
		switch(pickedup){
		case 1:
			if(facing == 0){
				if(animaçaoarma(facing))
				{
					game.draw(m4a1fs,X-20,Y-35);
				}
					else
				game.draw(m4a1f,X-20,Y-40);
			}
			else if(facing == 1 ){
				if(animaçaoarma(facing))
				{
					game.draw(m4a1bs,X,Y-5);
				}
					else
				game.draw(m4a1b,X,Y);
			}
			else if(facing == 2 ){
				if(animaçaoarma(facing))
				{
					game.draw(m4a1ls,X-60,Y);
				}
					else
				game.draw(m4a1l,X-60,Y);
			}
			else if(facing == 3 ){
				if(animaçaoarma(facing))
				{
					game.draw(m4a1rs,X,Y);
				}
					else
				game.draw(m4a1r,X,Y);
			}
			break;
		case 0:
			game.draw(m4a1r,X,Y);
		}
		
	}
	
	public boolean animaçaoarma(int facing) {
		// TODO Auto-generated method stub
		if(time==-1){
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE) || androidspace)
			{ 
				time=System.nanoTime();
				if(facing == 0){
				Bullet bullet = new Bullet(X,Y,this,facing);
				bullets.add(bullet);
				}
				else if(facing == 1){
					Bullet bullet = new Bullet(X + 10,Y + 20,this,facing);
					bullets.add(bullet);
					}
				else if(facing == 2){
					Bullet bullet = new Bullet(X - 20,Y + 20,this,facing);
					bullets.add(bullet);
					}
				else if(facing == 3){
					Bullet bullet = new Bullet(X + 20,Y + 20,this,facing);
					bullets.add(bullet);
					}
				
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
	@Override
	public void setshooting(Boolean bol) {
		// TODO Auto-generated method stub
		shooting = bol;
	}
	
	public ArrayList<Bullet> getbullets(){
		return bullets;
	}

}
