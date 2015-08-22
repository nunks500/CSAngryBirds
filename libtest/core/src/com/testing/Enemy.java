package com.testing;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy extends Entity{
	int X,Y,tipo,Health,facing,t4X,t4Y;
	Sprite t1r,t1l,t1f,t1b,t2r,t2l,t3r,tem,t3,exo,exo2,t4l,t4r;
	float time,additional = 0,additional2=0,timerfort4;
	int explode = 0;
	Boolean trade = false;
	Enemy(int[][] map,ArrayList<Wall> paredes){
		Boolean check = false;
		Boolean check2 = false;
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
			time=System.nanoTime();
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
		t2r = new Sprite(new Texture("t2r.png"));
		t2l = new Sprite(new Texture("t2l.png"));
		t3r = new Sprite(new Texture("tem.png"));
		t3 = new Sprite(new Texture("t3.png"));
		tem = new Sprite(new Texture("t3r.png"));
		exo = new Sprite(new Texture("exo.png"));
		exo2 = new Sprite(new Texture("exo2.png"));
		t4r = new Sprite(new Texture("t4r.png"));
		t4l = new Sprite(new Texture("t4l.png"));
		if(tipo == 4){
			while(check2 == false){
				Random y = new Random();
				t4Y = y.nextInt(Gdx.graphics.getHeight());
				Random x = new Random();
				t4X = x.nextInt(Gdx.graphics.getWidth());
				if(t4X < (map.length*46)-23 && !col.enemywall2(this))//Checking collision of the gun with the walls and keeps them inside map
				check2=true;
				}
		}
		while(check == false){
			Random y = new Random();
			Y = y.nextInt(Gdx.graphics.getHeight());
			Random x = new Random();
			X = x.nextInt(Gdx.graphics.getWidth());
			if(X < (map.length*46)-23 && !col.enemywall(this))//Checking collision of the gun with the walls and keeps them inside map
			check=true;
			}
		timerfort4 = System.nanoTime();
		
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
		else if (tipo == 2){
			if(facing == 0){
				
				batch.draw(t2r,X,Y);
			}
			else if(facing == 1){
				batch.draw(t2r,X,Y);
			}

			else if(facing == 2){
				batch.draw(t2l,X,Y);
			}

			else if(facing == 3){
				batch.draw(t2r,X,Y);
			}
			
		}
		else if(tipo == 3){
			 if(((System.nanoTime()-time)/1000000)>4000 && (System.nanoTime()-time)/1000000 < 5500){
			if(((System.nanoTime()-time)/1000000)>(4000 + additional)){
				batch.draw(exo2,X,Y);
				additional += 50;
				explode = 1;
			}
			else if(((System.nanoTime()-time)/1000000)>(4000 + additional2)){
				batch.draw(exo,X,Y);
			additional2 +=50;
		explode = 1;
			}
			}
		
		else if(((System.nanoTime()-time)/1000000)>3000 && (System.nanoTime()-time)/1000000 < 5500)
				batch.draw(t3r,X,Y);
			else if(((System.nanoTime()-time)/1000000)>2000 && (System.nanoTime()-time)/1000000 < 5500)
				batch.draw(tem,X,Y);
	
			else if(((System.nanoTime()-time)/1000000)>1000 && (System.nanoTime()-time)/1000000 < 5500)
				batch.draw(t3,X,Y);
				
				else if(((System.nanoTime()-time)/1000000)>4000 && (System.nanoTime()-time)/1000000 > 5500)
					explode = 2;
		}
		else if(tipo == 4){
	if(facing == 0){
				
				batch.draw(t4r,X,Y);
			}
			else if(facing == 1){
				batch.draw(t4r,X,Y);
			}

			else if(facing == 2){
				batch.draw(t4l,X,Y);
			}

			else if(facing == 3){
				batch.draw(t4r,X,Y);
			}
		}
		}
		

	
	public Sprite getSprite(){
		if(tipo == 1)
			return t1r;
		else if(tipo == 2)
			return t2r;
		else if(tipo == 3)
			return t3r;
		else
			return t4r;
		
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
	
	public void setHealth(int Health){
		this.Health = Health;
	}
	
	public int getHealth(){
		return Health;
	}
	
	public int getTipo(){
		return tipo;
	}
	
	public int getexplode(){
		return explode;
	}
	
	public int gett4x(){
		return t4X;
	}
	public int gett4y(){
		return t4Y;
	}
	
	public void settimer(float timer)
	{
		timerfort4 = timer;
	}
	public float gettimer()
	{
		return timerfort4;
	}
}
