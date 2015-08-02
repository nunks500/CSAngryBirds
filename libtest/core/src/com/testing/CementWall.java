package com.testing;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class CementWall extends Wall{
	
	int X;
	int Y;
	Sprite Wall;
	Boolean breakable;
	
	CementWall(int X,int Y,int[][] map){
		this.X=X;
		this.Y=Y;
		selectsprite(X,Y,map);
	}
	private void selectsprite(int x2, int y2,int[][] map) {
		// TODO Auto-generated method stub
		
		if(x2== 0 && y2 == 0)
		{
			Wall = new Sprite(new Texture("tl.png"));
			X=0;
			Y=Gdx.graphics.getHeight() - 20;
		}
		
		else if(x2 !=0 && y2 ==0 && x2!=(map.length-1)){
			Wall = new Sprite(new Texture("ml.png"));
			Y=Gdx.graphics.getHeight()-20-(x2*46);
			X=0;
			
		}
		else if(x2 == map.length-1 && y2 == 0){
			X=0;
			Y=0;
			Wall = new Sprite(new Texture("bl.png"));
			
		}
		else if (x2 ==0 && y2 == map.length-1){
		
			X=(map.length*46)-46;
			Y=Gdx.graphics.getHeight()-20;
			Wall = new Sprite(new Texture("tr.png"));
			
		}
		else if (x2 ==map.length-1 && y2 == map.length-1)
		{
			X=(map.length*46)-46;
			Y=0;
			Wall = new Sprite(new Texture("br.png"));
		}
		else if (y2 == map.length-1 && x2!=0 && x2!= map.length-1)
		{
		
			X=(map.length*46)-23;
			Y=Gdx.graphics.getHeight() - (x2*46)-20;
			Wall = new Sprite(new Texture("mr.png"));
		}
		else if(x2==map.length-1 && y2!=0 && y2!= map.length-1){
			Wall = new Sprite(new Texture("bm.png"));
			Y=0;
			X=46*y2;
			
		}
		else{
			X =46*y2;
			Y = 460;
			Wall = new Sprite(new Texture("bm.png"));
		}
		
		
	}
	@Override
	Boolean getbreak() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	int getX() {
		// TODO Auto-generated method stub
		return X;
	}
	int getY() {
		// TODO Auto-generated method stub
		return Y;
	}
	@Override
	Sprite getsprite() {
		// TODO Auto-generated method stub
		return Wall;
	}

}
