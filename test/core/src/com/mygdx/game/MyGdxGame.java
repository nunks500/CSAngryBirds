package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.prism.image.ViewPort;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Sprite img;
	String CurrentState;
	OrthographicCamera camera;
	Viewport viewport;
	final float gamewidth = 640;
	final float gameheight = 480;
	
	@Override
	public void create () {
		CurrentState = "Menu";
		//while(CurrentState == "Menu"){
		batch = new SpriteBatch();
		img = new Sprite(new Texture("Menu.jpg"));
		img.setSize(1f,1f * img.getHeight() / img.getWidth() ); //Descomentar para fullscreen parcial
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(gameheight,gameheight,camera);
		viewport.apply();
		//}
		//camera = new OrthographicCamera(1680,1200);
		//this.camera.position.set(1680/2, 1200/2, 0f); Outro modo
		
		
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
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
}
