package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Collision {

	private ArrayList<Wall> paredes;
	private Player player;
	Rectangle chara;
	public Collision(ArrayList<Wall> paredes, Player player1) {
		// TODO Auto-generated constructor stub
		this.paredes = paredes;
		player = player1;
	}
	public Collision(ArrayList<Wall> paredes2) {
		// TODO Auto-generated constructor stub
		paredes=paredes2;
	}
	public boolean detect(int id) {
		// TODO Auto-generated method stub
		Boolean detected = false;
		if(id==0){
		 chara = new Rectangle(player.getX(),player.getY()-5,51,46);
		}
		else if(id==1){
		 chara = new Rectangle(player.getX(),player.getY() + 5,51,46);
		}
		else if(id==2){
			chara = new Rectangle(player.getX() - 5,player.getY(),51,46);
			}
		else if(id==3){
			 chara = new Rectangle(player.getX() + 5,player.getY(),51,46);
			}
		
	//	Rectangle wall1 = new Rectangle(0,0,23,Gdx.graphics.getHeight());
		for(int i=0;i<paredes.size();i++){
			Rectangle wall1 = new Rectangle(paredes.get(i).getX(),paredes.get(i).getY(),paredes.get(i).getsprite().getWidth(),paredes.get(i).getsprite().getHeight()-5);
			if(chara.overlaps(wall1))
				return true;
		}
		
		 return false;
	}
	
	public boolean overlapping(Gun gun){
		Rectangle guna = new Rectangle((float)gun.getX(),(float)gun.getY(),gun.getsprite().getWidth(),gun.getsprite().getHeight());
		for(int i=0;i<paredes.size();i++){
			Rectangle wall1 = new Rectangle(paredes.get(i).getX(),paredes.get(i).getY(),paredes.get(i).getsprite().getWidth(),paredes.get(i).getsprite().getHeight()-5);
			if(guna.overlaps(wall1))
				return true;
		}
		
		 return false;
	}
		
}
