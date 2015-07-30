package com.mygdx.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.prism.image.ViewPort;

public class MyGdxGame extends ApplicationAdapter implements  ActionListener{

	SpriteBatch batch,game;
	Rectangle wall1,chara;
	Sprite img,img2,porco,pigshoot,exp10,exp1,exp2,exp3,porco2,tl,ml,bl;
	String CurrentState;
	OrthographicCamera camera;
	Viewport viewport;
	final float gamewidth = 640;
	final float gameheight = 480;
	int CurrentOption=0,start=0,exit=70,time=0,porcoposinit=-50,index=0,nextone=0,index2=0;
	long previoustime; //identifies where the user is(which option)
	Animation bird;
	Timer birdi= new Timer(2000,this);
	Timer porc= new Timer(200,this);
	private ArrayList<Sprite> animat = new ArrayList<Sprite>();
	private ArrayList<Wall> paredes = new ArrayList<Wall>();
	private ArrayList<Gun> guns = new ArrayList<Gun>();
	Player player1;
	private int[][] map = {{1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,0,0,0,0,0,0,0,0,0,0,0,0,1},{1,0,0,0,0,0,0,0,0,0,0,0,0,1},{1,0,0,0,0,0,0,0,0,0,0,0,0,1},{1,0,0,0,0,0,0,0,0,0,0,0,0,1},{1,0,0,0,0,0,0,0,0,0,0,0,0,1},{1,0,0,0,0,0,0,0,0,0,0,0,0,1},{1,0,0,0,0,0,0,0,0,0,0,0,0,1},{1,0,0,0,0,0,0,0,0,0,0,0,0,1},{1,0,0,0,0,0,0,0,0,0,0,0,0,1},{1,0,0,0,0,0,0,0,0,0,0,0,0,1},{1,0,0,0,0,0,0,0,0,0,0,0,0,1},{1,0,0,0,0,0,0,0,0,0,0,0,0,1},{1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
	Collision collision,armaz;



	@Override
	public void create () {
		mapget();
		CurrentState = "Menu";
		img = new Sprite(new Texture("Menu.jpg"));
		img2 = new Sprite(new Texture("SmallerTiro.png"));
		porco = new Sprite(new Texture("porco.png"));
		exp10=new Sprite(new Texture("exp-1.png"));
		exp1=new Sprite(new Texture("exp1.png"));
		exp2=new Sprite(new Texture("exp2.png"));
		exp3=new Sprite(new Texture("exp3.png"));
		pigshoot = new Sprite(new Texture("piece.png"));
		porco2 = new Sprite(new Texture("backporco.png"));
		animat.add(exp10);
		animat.add(exp1);
		animat.add(exp2);
		animat.add(exp3);
		batch = new SpriteBatch();
		img.setSize(1f,1f * img.getHeight() / img.getWidth() ); //Descomentar para fullscreen parcial
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(gameheight,gameheight,camera);
		viewport.apply();
		bird = new Animation(1/ 3f,new TextureRegion(new Texture("ini1.png")),new TextureRegion(new Texture("ini2.png")));
		//camera = new OrthographicCamera(1680,1200);
		//this.camera.position.set(1680/2, 1200/2, 0f); Outro modo
		porc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				porcoposinit += 2;   	
			}
		});
		previoustime=System.nanoTime();
		birdi.start();
		porc.start();


	}
	//Descomentar para fullscreen parcial
	@Override
	public void resize(int width,int height){
		viewport.update(width, height);
		camera.position.set(gamewidth/2,gameheight/2,0);
	}


	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();

		if(CurrentState.equals("Menu")){
			menushower();
			if(Gdx.input.isKeyPressed(Input.Keys.ENTER) && CurrentOption == exit)
				Gdx.app.exit();
			else if(Gdx.input.isKeyPressed(Input.Keys.ENTER) && CurrentOption == start)
			{
				CurrentState = "Game";
				cleansprites();
				player1 = new Player();
				game = new SpriteBatch();
			}

		}
		else if(CurrentState.equals("Game")){
			game.setProjectionMatrix(camera.combined);
			game.begin();
			collision = new Collision(paredes,player1);
			player1.setcol(collision);
			player1.draw(game);
			mapdraw(game);
			generateguns();
			drawguns(game,guns,player1);
			equipped(game,guns,player1);
			game.end();
		}

	}

	private void equipped(SpriteBatch game2, ArrayList<Gun> guns, Player player1) {
		// TODO Auto-generated method stub
		Collision collision = new Collision(paredes,player1);
		 index2 = collision.pegou(guns); //index of gun -equals 20 if didnt found it
		
		 if(index2 != 20){
			guns.get(index2).setpickedup(1);
			guns.get(index2).setX(player1.getX());
			guns.get(index2).setY(player1.getY());
		}
		
		
	}
	private void drawguns(SpriteBatch game2, ArrayList<Gun> guns2,Player player1) {
		// TODO Auto-generated method stub
		for(int i=0;i<guns.size();i++){
			guns.get(i).draw(game,player1.getfacing());
		}
		
	}
	private void generateguns() {
		// TODO Auto-generated method stub
		int temp;
		Random random2 = new Random();
		temp =  random2.nextInt(700);
		if(temp == 150 &&  guns.size() < 5){
			Random tipoarma = new Random();
			int arma = tipoarma.nextInt(10);
			if(arma <= 5){
				Boolean check = false;
				glock glock = null;
				System.out.println("Entrei");
				while(check == false){
				glock= new glock();
				Collision armaz = new Collision(paredes);
				if(!armaz.overlapping(glock))//Checking collision of the gun with the walls
				check=true;
				}
				guns.add(glock);
				
			}
			else if(arma == 6 || arma == 7){
				Boolean check = false;
				m4a1 m4a1 = null;
				System.out.println("Entrei");
				while(check == false){
				m4a1= new m4a1();
				Collision armaz = new Collision(paredes);
				if(!armaz.overlapping(m4a1))//Checking collision of the gun with the walls
				check=true;
				}
				guns.add(m4a1);
				
			}
			else if(arma == 8){
				Boolean check = false;
				ak47 ak = null;
				System.out.println("Entrei");
				while(check == false){
				ak= new ak47();
				Collision armaz = new Collision(paredes);
				if(!armaz.overlapping(ak))//Checking collision of the gun with the walls
				check=true;
				}
				guns.add(ak);
			}
			else if(arma == 9){
				Boolean check = false;
				Bazuka bazuka = null;
				System.out.println("Entrei");
				while(check == false){
				bazuka= new Bazuka();
				Collision armaz = new Collision(paredes);
				if(!armaz.overlapping(bazuka))//Checking collision of the gun with the walls
				check=true;
				}
				guns.add(bazuka);
			}
			
			
		}
		
		
		
	}
	public void mapget() {
		// TODO Auto-generated method stub
		for(int i=0;i<map.length;i++){
			for(int j=0;j<map[i].length;j++){				
				if(map[i][j] == 1){
					CementWall cime = new CementWall(i,j,map);
					paredes.add(cime);
				}

			}
		}

	}
	public void mapdraw(SpriteBatch game) {
		// TODO Auto-generated method stub
		for(int i=0;i<paredes.size();i++){
			game.draw(paredes.get(i).getsprite(),paredes.get(i).getX(),paredes.get(i).getY());
			//System.out.println(paredes.get(15).getX()+"|"+paredes.get(15).getY());
			//game.draw(paredes.get(15).getsprite(),paredes.get(15).getX(),paredes.get(15).getY());
		}


	}
	public void cleansprites() {
		// TODO Auto-generated method stub
		img.getTexture().dispose();
		img2.getTexture().dispose();
		porco.getTexture().dispose();
		pigshoot.getTexture().dispose();
		exp10.getTexture().dispose();
		exp1.getTexture().dispose();
		exp2.getTexture().dispose();
		exp3.getTexture().dispose();
		porco2.getTexture().dispose();



	}
	private void menushower() {
		// TODO Auto-generated method stub
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && CurrentOption != exit)
			CurrentOption=exit;
		else if(Gdx.input.isKeyPressed(Input.Keys.UP) && CurrentOption!=start)
			CurrentOption=start;

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.draw(img2,20,270-CurrentOption);
		batch.draw(bird.getKeyFrame(time),400,250);
		batch.draw(porco,porcoposinit,50);
		if(porcoposinit > 100){
			porco = porco2;
			batch.draw(pigshoot,170,90);
			porc.stop();
			batch.draw(animat.get(index),390,240);
			if((System.nanoTime() - previoustime)/1000000 > 50){
				if(index==3)index=0;
				else
					index++;

				previoustime = System.nanoTime();
			}
		}
		batch.end();

	}
	@Override
	public void dispose(){
		batch.dispose();
		if(game!=null)
		game.dispose();

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(time==10){
			time=0;
		}
		else
			time++;

	}

	
}
