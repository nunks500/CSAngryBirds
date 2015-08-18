package com.testing;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet {
	int X,Y,facing;
	Gun gun;
	Sprite ak47t,bazukat,glockt;
	
	public Bullet(int x, int y,Gun gun,int facing) {
		// TODO Auto-generated constructor stub
		X = x;
		Y=y;
		this.gun=gun;
		this.facing = facing;
		ak47t = new Sprite(new Texture("ak47t.png"));
		glockt = new Sprite(new Texture("glockt.png"));
		bazukat = new Sprite(new Texture("bazukat.png"));
		
	}
	
	public void draw(SpriteBatch batch){
		if(gun instanceof m4a1){
			batch.draw(ak47t,X,Y);
		}
		else if(gun instanceof ak47){
			batch.draw(ak47t,X,Y);
		}
		else if(gun instanceof glock){
			batch.draw(glockt,X,Y);
		}
		else {
			batch.draw(bazukat,X,Y);
}
	}
	
	public void update(){
		if(facing == 0 ){
			Y -= 10;
			
		}
		else if(facing == 1 ){
			Y += 10;
			
		}
		else if(facing == 2 ){
			X -= 10;
			
		}
		else if(facing == 3 ){
			X += 10;
			
		}
	}
	public int getX(){
		return X;
	}
	
	public int getY(){
		return Y;
	}
	
	public Sprite corresponding(Gun gun){
		if(gun instanceof m4a1){
			return ak47t;
		}
		else if(gun instanceof ak47){
			return ak47t;
		}
		else if(gun instanceof glock){
			return glockt;
		}
		else {
			return bazukat;
	}
	}
	
}
