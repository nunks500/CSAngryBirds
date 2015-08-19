package com.testing;


import java.util.ArrayList;
import java.util.Random;


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
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Testing extends ApplicationAdapter implements GestureDetector.GestureListener{

	SpriteBatch batch,game;
	Rectangle wall1,chara;
	Sprite img,img2,porco,pigshoot,exp10,exp1,exp2,exp3,porco2,tl,ml,bl;
	String CurrentState;
	OrthographicCamera camera;
	Viewport viewport;
	final float gamewidth = 640;
	final float gameheight = 480;
	int CurrentOption=0,start=0,exit=70,porcoposinit=-50,index=0,nextone=0,index2=0,birdframes=0,androidenter=0;
	long previoustime; //identifies where the user is(which option)
	float time,porctime,testx,testy;
	Animation bird;
	private ArrayList<Sprite> animat = new ArrayList<Sprite>();
	private ArrayList<Wall> paredes = new ArrayList<Wall>();
	private ArrayList<Gun> guns = new ArrayList<Gun>();
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	Player player1;
	private int[][] map = {{1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,0,0,0,0,0,0,0,0,0,0,0,0,1},{1,0,0,0,0,0,0,0,0,0,0,0,0,1},{1,0,0,0,0,0,0,0,0,0,0,0,0,1},{1,0,0,0,0,0,0,0,0,0,0,0,0,1},{1,0,0,0,0,0,0,0,0,0,0,0,0,1},{1,0,0,0,0,0,0,0,0,0,0,0,0,1},{1,0,0,0,0,0,0,0,0,0,0,0,0,1},{1,0,0,0,0,0,0,0,0,0,0,0,0,1},{1,0,0,0,0,0,0,0,0,0,0,0,0,1},{1,0,0,0,0,0,0,0,0,0,0,0,0,1},{1,0,0,0,0,0,0,0,0,0,0,0,0,1},{1,0,0,0,0,0,0,0,0,0,0,0,0,1},{1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
	Collision collision,armaz;
	GestureDetector gest;
	



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
		previoustime=System.nanoTime();
		time=System.nanoTime();
		time=porctime;
		 gest = new GestureDetector(this);
		 Gdx.input.setInputProcessor(gest);
		
		



	}
	private void updatebird() { //should prolly just create a class but need to fix asap
		// TODO Auto-generated method stub
		if((System.nanoTime()-time)/1000000 > 2000)
		{
			if(birdframes==10){
			birdframes=0;
			}
			else{
			birdframes++;
			}
			time = System.nanoTime();
		}
	}
	
	private void updateporc() { //should prolly just create a class but need to fix asap
		// TODO Auto-generated method stub
		if((System.nanoTime()-porctime)/1000000 > 200 && porcoposinit <= 100)
		{
			porcoposinit +=2;
			porctime = System.nanoTime();
		}
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
			if((Gdx.input.isKeyPressed(Input.Keys.ENTER)|| androidenter == 1) && CurrentOption == exit)
				Gdx.app.exit();
			else if((Gdx.input.isKeyPressed(Input.Keys.ENTER) || androidenter == 1) && CurrentOption == start)
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
			dead(game,guns);
			drawbullets(game,guns,player1);
			generateenemies(map);
			drawenemies(game);
			game.end();
		}

	}


	private void drawenemies(SpriteBatch batch) {
		// TODO Auto-generated method stub
		for(int i=0;i<enemies.size();i++)
			enemies.get(i).draw(batch);
		
	}
	private void generateenemies(int[][] map) {
		// TODO Auto-generated method stub
		Random y = new Random();
		int k = y.nextInt(100);
		if(k == 7){
		Enemy enemy = new Enemy(map,paredes);
		enemies.add(enemy);
		}
	}
	private void dead(SpriteBatch game2, ArrayList<Gun> guns2) {
		// TODO Auto-generated method stub
		Collision collision = new Collision(paredes);
		if(player1.getgun()!=null){
			for(int i=0;i<player1.getgun().getbullets().size();i++){
				
				if(collision.shoots(player1.getgun().getbullets().get(i),player1.getgun())){
					player1.getgun().getbullets().remove(i);
				}
			}
		}
	}
	private void drawbullets(SpriteBatch game, ArrayList<Gun> guns, Player player) {
		// TODO Auto-generated method stub~
		
		if(player1.getgun()!=null){
			for(int i=0;i<player1.getgun().getbullets().size();i++){
				player1.getgun().getbullets().get(i).update();
				player1.getgun().getbullets().get(i).draw(game);
			}
		}
		
		
	}
	private void equipped(SpriteBatch game2, ArrayList<Gun> guns, Player player1) {
		// TODO Auto-generated method stub
		Collision collision = new Collision(paredes,player1);
		 index2 = collision.pegou(guns); //index of gun -equals 20 if didnt found it
		
		 if(index2 != 20){
			 player1.setGun(guns.get(index2));
			 player1.getgun().setpickedup(1);
			guns.remove(index2);
		
			//guns.get(index2).setX(player1.getX());
			//guns.get(index2).setY(player1.getY());
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
		temp =  random2.nextInt(500);
		if(temp == 150 &&  guns.size() < 5){
			Random tipoarma = new Random();
			int arma = tipoarma.nextInt(10);
			if(arma <= 5){
				Boolean check = false;
				glock glock = null;
				while(check == false){
				glock= new glock();
				Collision armaz = new Collision(paredes);
				if(!armaz.overlapping(glock) && glock.getX() < (map.length*46)-23)//Checking collision of the gun with the walls and keeps them inside map
				check=true;
				
				}
				guns.add(glock);
				
			}
			
			else if(arma == 6 || arma == 7){
				Boolean check = false;
				m4a1 m4a1 = null;
				while(check == false){
				m4a1= new m4a1();
				Collision armaz = new Collision(paredes);
				if(!armaz.overlapping(m4a1) && m4a1.getX() < (map.length*46)-23)//Checking collision of the gun with the walls
				check=true;
				}
				guns.add(m4a1);
				
			}
			
			else if(arma == 8){
				Boolean check = false;
				ak47 ak = null;
				while(check == false){
				ak= new ak47();
				Collision armaz = new Collision(paredes);
				if(!armaz.overlapping(ak) && ak.getX() < (map.length*46)-23 )//Checking collision of the gun with the walls
				check=true;
				}
				guns.add(ak);
			}
			
			else if(arma == 9){
				Boolean check = false;
				Bazuka bazuka = null;
				while(check == false){
				bazuka= new Bazuka();
				Collision armaz = new Collision(paredes);
				if(!armaz.overlapping(bazuka) && bazuka.getX() < (map.length*46)-23)//Checking collision of the gun with the walls
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
		updatebird();
		updateporc();
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && CurrentOption != exit)
			CurrentOption=exit;
		else if(Gdx.input.isKeyPressed(Input.Keys.UP) && CurrentOption!=start)
			CurrentOption=start;
	
			
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.draw(img2,20,270-CurrentOption);
		batch.draw(bird.getKeyFrame(birdframes),400,250);
		batch.draw(porco,porcoposinit,50);
		if(porcoposinit > 100){
			porco = porco2;
			batch.draw(pigshoot,170,90);
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
		if(batch!=null)
		batch.dispose();
		if(game!=null)
		game.dispose();
		player1 = null;
		paredes.clear();
		guns.clear();
		animat.clear();
		collision = null;
		gest.reset();
		camera = null;
		bird = null;
		armaz = null;
	}
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		Vector2 newPoints = new Vector2(x,y);
		newPoints = viewport.unproject(newPoints.set(x,y));
		testx = newPoints.x;
		testy = newPoints.y;
		Rectangle tap = new Rectangle(newPoints.x,newPoints.y,1,1);
		
		
		if(CurrentState.equals("Menu")){
			if(CurrentOption == exit){
				Rectangle exit = new Rectangle(20,270-CurrentOption,img2.getWidth() + 70,img2.getHeight());
			
				if(exit.overlaps(tap)){
					androidenter=1;
				}
				else
				CurrentOption = start;
			}
			else
			{
				Rectangle start = new Rectangle(20,270-CurrentOption,img2.getWidth() + 70,img2.getHeight());
				if(start.overlaps(tap)){
					androidenter=1;
				}
				else
				CurrentOption = exit;
			}
		}
		else if(CurrentState.equals("Game")){
			if(player1.getgun() != null)
			player1.getgun().setandroidspace(true);
		}
		return false;
	}
	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		
		return false;
	}
	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
			player1.updatefacingandroid(deltaX,deltaY);
		return false;
	}
	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}
	

	
}

