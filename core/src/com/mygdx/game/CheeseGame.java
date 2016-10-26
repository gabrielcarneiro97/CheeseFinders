package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import handlers.Contents;
import handlers.GameInputs;
import handlers.GameInputsProcessor;
import handlers.GameStateManager;

public class CheeseGame extends ApplicationAdapter {

	public static final String TITLE = "Cheese Finders";
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 300;
	public static final int SCALE = 2;
	public static final float STEP = 1/60f;

	private float accum;
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private OrthographicCamera hudCam;

	public static Contents res;

	private GameStateManager gsm;

	@Override
	public void create () {

		res = new Contents();
		res.loadTexture("desktop/assets/animations/leftWlk.png", "leftWlk");
		res.loadTexture("desktop/assets/animations/rightWlk.png", "rightWlk");
		res.loadTexture("desktop/assets/animations/animationCheese.png", "animationCheese");
		res.loadTexture("desktop/assets/rope.png", "rope");
		res.loadTexture("desktop/assets/maps/cheese.png", "cheese");
		res.loadTexture("desktop/assets/BtnD.png", "btnD");
		res.loadTexture("desktop/assets/BtnS.png", "btnS");
		res.loadTexture("desktop/assets/BtnFaseD.png", "btnFaseD");
		res.loadTexture("desktop/assets/BtnFaseS.png", "btnFaseS");
		res.loadTexture("desktop/assets/FundoMenu.png", "fundoMenu");
		res.loadTexture("desktop/assets/LogoCheese.png", "logo");


		Gdx.input.setInputProcessor(new GameInputsProcessor());

		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, V_WIDTH, V_HEIGHT);

		gsm = new GameStateManager(this);

	}

	@Override
	public void render () {
		accum += Gdx.graphics.getDeltaTime();
		while(accum  >= STEP){
			accum -= STEP;
			gsm.update(STEP);
			gsm.render();
			GameInputs.update();
		}


	}
	
	@Override
	public void dispose () {
		getBatch().dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public OrthographicCamera getCam() {
		return cam;
	}

	public void setCam(OrthographicCamera cam) {
		this.cam = cam;
	}

	public OrthographicCamera getHudCam() {
		return hudCam;
	}

	public void setHudCam(OrthographicCamera hudCam) {
		this.hudCam = hudCam;
	}
}
