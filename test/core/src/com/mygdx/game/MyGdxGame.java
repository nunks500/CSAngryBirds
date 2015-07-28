package com.mygdx.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.prism.image.ViewPort;

public class MyGdxGame extends ApplicationAdapter implements  ActionListener{
	SpriteBatch batch;
	Sprite img,img2,porco,pigshoot,exp10,exp1,exp2,exp3,porco2;
	String CurrentState;
	OrthographicCamera camera;
	Viewport viewport;
	final float gamewidth = 640;
	final float gameheight = 480;
	int CurrentOption=0,start=0,exit=70,time=0,porcoposinit=-50,index=0;
	long previoustime; //identifies where the user is(which option)
	Animation bird;
	Timer birdi= new Timer(2000,this);
	Timer porc= new Timer(200,this);
	private ArrayList<Sprite> animat = new ArrayList<Sprite>();
	
	@Override
	public void create () {
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
			CurrentState = "Game";
	
	}	
	
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
