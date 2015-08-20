package com.testing;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
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
	
	public int pegou(ArrayList<Gun> gun){
		for(int i=0;i<gun.size();i++){
		Rectangle guna = new Rectangle((float)gun.get(i).getX(),(float)gun.get(i).getotherY(),gun.get(i).getsprite().getWidth(),gun.get(i).getsprite().getHeight());
		Rectangle character = new Rectangle((float)player.getX(),(float)player.getY(),player.SpriteRight().getWidth(),player.SpriteRight().getHeight());
		if(guna.overlaps(character))
			return i;
		
		}
		
			return 20;
	}
	
	public Boolean shoots(Bullet bullet,Gun gun){
		Sprite temp = bullet.corresponding(gun);
		Rectangle bulet = new Rectangle((float) bullet.getX(),(float) bullet.getY(),temp.getWidth(),temp.getHeight());
		for(int i=0;i<paredes.size();i++){
			Rectangle wall1 = new Rectangle(paredes.get(i).getX(),paredes.get(i).getY(),paredes.get(i).getsprite().getWidth(),paredes.get(i).getsprite().getHeight()-5);
			if(bulet.overlaps(wall1))
				return true;
		}
		
		 return false;
	}
	
	public Boolean enemywall(Enemy enemy){
		Sprite temp = enemy.getSprite();
		Rectangle enemi = new Rectangle((float) enemy.getX(),(float) enemy.getY(),temp.getWidth(),temp.getHeight());
		for(int i=0;i<paredes.size();i++){
			Rectangle wall1 = new Rectangle(paredes.get(i).getX(),paredes.get(i).getY(),paredes.get(i).getsprite().getWidth(),paredes.get(i).getsprite().getHeight()-5);
			if(enemi.overlaps(wall1))
				return true;
		}
		
		 return false;
	}
	
	public Boolean hit(Bullet bullet,Player player,Gun gun){
		Sprite temp = bullet.corresponding(gun);
		Rectangle bulet = new Rectangle((float) bullet.getX(),(float) bullet.getY(),temp.getWidth(),temp.getHeight());
		int id = player.getfacing();
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
		
		if(chara.overlaps(bulet))
			return true;
		else
			return false;
	}
	
	public Boolean hite(Bullet bullet, Enemy enemy,Gun gun){
		Sprite temp = bullet.corresponding(gun);
		Rectangle bulet = new Rectangle((float) bullet.getX(),(float) bullet.getY(),temp.getWidth(),temp.getHeight());
		Sprite temp2 = enemy.getSprite();
		Rectangle enemi = new Rectangle((float) enemy.getX(),(float) enemy.getY(),temp2.getWidth(),temp2.getHeight());
		if(bulet.overlaps(enemi))
			return true;
		else return false;
		
		
	}
		
}
