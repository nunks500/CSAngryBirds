package com.testing;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class glock extends Gun{
	int X=0;
	int Y = Gdx.graphics.getHeight();
	int actualY=0;
	int power;
	Sprite glockb,glockf,glockl,glockr,glockr2,glockbs,glockfs,glockls,glockrs;
	int pickedup;
	Rectangle area;
	Boolean shooting,androidspace;
	float time;
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	glock(){
		Random y = new Random();
		actualY = y.nextInt(Gdx.graphics.getHeight());
		Random x = new Random();
		X = x.nextInt(Gdx.graphics.getWidth());
		glockb = new Sprite(new Texture("glockback.png"));
		glockf = new Sprite(new Texture("glockfront.png"));
		glockl = new Sprite(new Texture("glockleft.png"));
		glockr = new Sprite(new Texture("glockright.png"));
		glockr2 =  new Sprite(new Texture("glockright2.png"));
		glockbs = new Sprite(new Texture("glockbackshoot.png"));
		glockfs = new Sprite(new Texture("glockfrontshoot.png"));
		glockls = new Sprite(new Texture("glockleftshoot.png"));
		glockrs = new Sprite(new Texture("glockrightshoot.png"));
		pickedup = 0;
		shooting=false;
		time = -1;
		androidspace = false;
		ammo = 50;
	}
	
	glock(int x,int y){
		X=x;
		Y=y;
		shooting=false;
		time = -1;
		androidspace = false;
	}
	


	public void draw(SpriteBatch game,int facing){
		update();
		switch(pickedup){
		case 1:
			if(facing == 0 ){
				
				if(animaçaoarma(facing))
				{
					game.draw(glockfs,X+32,Y-20);
				}
					else
				game.draw(glockf,X+32,Y-24);
			}
			else if(facing == 1 ){
				if(animaçaoarma(facing))
				{
					game.draw(glockbs,X+10,Y+20);
				}
					else
				game.draw(glockb,X+10,Y+15);
			}
			else if(facing == 2 ){
				if(animaçaoarma(facing))
				{
					game.draw(glockls,X-10,Y);
				}
					else
				game.draw(glockl,X-15,Y);
			}
			else if(facing == 3 ){
				if(animaçaoarma(facing))
				{
					game.draw(glockrs,X+15,Y);
				}
					else
				game.draw(glockr,X+15,Y);
			}
			break;
		case 0:
			game.draw(glockr2,X,Y);
			
			break;
		}
		
	}

	private void update() {
		// TODO Auto-generated method stub
		if(Y > actualY){
			Y--;
		}
		
	
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
		return glockf;
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
	
	public boolean animaçaoarma(int facing) {
		// TODO Auto-generated method stub
		if(time==-1){
			if((Gdx.input.isKeyPressed(Input.Keys.SPACE) || androidspace) && (ammo>0))
			{
				time=System.nanoTime();
				if(facing == 0){
				Bullet bullet = new Bullet(X + 40,Y,this,facing);
				bullets.add(bullet);
				ammo--;
				}
				else if(facing == 1){
					Bullet bullet = new Bullet(X+25,Y,this,facing);
					bullets.add(bullet);
					ammo--;
					} 
				else if(facing == 2){
					Bullet bullet = new Bullet(X,Y + 20,this,facing);
					bullets.add(bullet);
					ammo--;
					} 
				else if(facing == 3){
					Bullet bullet = new Bullet(X + 10,Y + 20,this,facing);
					bullets.add(bullet);
					ammo--;
					} 
				androidspace=false;
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

	public ArrayList<Bullet> getbullets(){
		return bullets;
	}

}
