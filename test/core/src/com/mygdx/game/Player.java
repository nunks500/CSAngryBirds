package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {
	int X;
	int Y;
	int idweapon;
	Sprite playerf,playerb,playerl,playerr;
	int facing;
	
	Player(){
		X=0;
		Y=0;
		idweapon=0;
		playerf = new Sprite(new Texture("pigfront.png"));
		playerb = new Sprite(new Texture("pigback.png"));
		playerl = new Sprite(new Texture("pigleft.png"));
		playerr = new Sprite(new Texture("pigright.png"));
		facing = 0;
	
		
	}
	
	void draw(SpriteBatch batch){
		updatefacing();
		if(facing == 0){
			batch.draw(playerf,X,Y);
		}
		else if(facing == 1){
			batch.draw(playerb,X,Y);
		}
		else if(facing == 2){
			batch.draw(playerl,X,Y);
		}
		else if(facing == 3){
			batch.draw(playerr,X,Y);
		}
		
	}

	private void updatefacing() {
		// TODO Auto-generated method stub
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			facing = 0;
			Y--;
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			facing = 1;
			Y++;
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			facing = 2;
			X--;
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			facing = 3;
			X++;
		}
	}
}
