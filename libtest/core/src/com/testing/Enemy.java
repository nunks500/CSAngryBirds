package com.testing;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy extends Entity{
	int X,Y,tipo,Health,facing;
	Sprite t1r,t1l,t1f,t1b;
	
	Enemy(int[][] map,ArrayList<Wall> paredes){
		Boolean check = false;
		Collision col = new Collision(paredes);
		Random z = new Random();
		int tipoz = z.nextInt(10);
		if(tipoz < 6){
			Health = 100;
			tipo = 1; 
		}
		
		else if (tipoz == 6 || tipoz == 7){
			Health = 500;
			tipo = 2;
		}
		else if(tipoz == 8){
			Health = 700;
			tipo = 3;
		}
		else{
			Health = 1500;
			tipo= 4;
		}
		
		facing = 3;
		t1r = new Sprite(new Texture("t1r.png"));
		t1l = new Sprite(new Texture("t1l.png"));
		t1b = new Sprite(new Texture("t1b.png"));
		t1f = new Sprite(new Texture("t1f.png"));
		
		while(check == false){
			Random y = new Random();
			Y = y.nextInt(Gdx.graphics.getHeight());
			Random x = new Random();
			X = x.nextInt(Gdx.graphics.getWidth());
			if(X < (map.length*46)-23 && !col.enemywall(this))//Checking collision of the gun with the walls and keeps them inside map
			check=true;
			}
		
	}
	@Override
	void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		if(tipo == 1){
			if(facing == 0){
				batch.draw(t1f,X,Y);
			}
			else if(facing == 1){
				batch.draw(t1b,X,Y);
			}

			else if(facing == 2){
				batch.draw(t1l,X,Y);
			}

			else if(facing == 3){
				batch.draw(t1r,X,Y);
			}
			
		}
		
	}
	
	public Sprite getSprite(){
	//	if(tipo == 1)
			return t1r;
		
	}
	
	public int getX(){
		return X;
	}
	
	public int getY(){
		return Y;
	}
	
	public void setX(int x){
		X=x;
	}
	
	public void setY(int y){
		Y=y;
	}
	public void setface(int facing){
		this.facing = facing;
	}
	
	public int getface(){
		return facing;
	}

}
