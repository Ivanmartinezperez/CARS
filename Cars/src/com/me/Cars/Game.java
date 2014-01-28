package com.me.Cars;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Game implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private TextureAtlas texture;
	private Sprite sprite;
	private float rot;
	private int animationElapsed;
	private Array<Sprite> Ninja;
	private int currentFrame;
	private Animation run;
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		texture = new TextureAtlas(Gdx.files.internal("data/NinjaMove.txt"));
		Ninja = texture.createSprites("Nin");
		
		run = new Animation(0.1f,Ninja);
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		
		//texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		//TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);
		
		//sprite = new Sprite(region);
		for(int i=0;i<Ninja.size;++i){
			sprite = Ninja.get(i);
			sprite.setSize(0.3f, 0.3f * sprite.getHeight() / sprite.getWidth());
			sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
			sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
		}
		
		currentFrame=0;
		animationElapsed=0;
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		float dt = Gdx.graphics.getDeltaTime();
	
		Gdx.app.log("LOOP", "The loop works fine: Value of currentFrame: " + currentFrame + animationElapsed);
		animationElapsed += dt;
		
		currentFrame = run.getKeyFrameIndex(animationElapsed);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		Ninja.get(currentFrame%7).draw(batch);
		++animationElapsed;
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
