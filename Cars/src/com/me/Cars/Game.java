package com.me.Cars;

import java.util.Iterator;
import java.util.Set;

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
	private TextureAtlas atlas;
	private Sprite sprite;
	private float rot;
	private int animationElapsed;
	private Set<Texture> Ninja;
	private TextureRegion[] NinjaRun;
	private Animation run;
	
	 private static final int        FRAME_COLS = 9;         // #1
     private static final int        FRAME_ROWS = 1;         // #2
     
     Animation                       walkAnimation;          // #3
     Texture                         walkSheet;              // #4
     TextureRegion[]                 walkFrames;             // #5
     SpriteBatch                     spriteBatch;            // #6
     TextureRegion                   currentFrame;           // #7
     
     float stateTime;                  
	@Override
	public void create() {		
		/*float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		NinjaRun = new TextureRegion[9];
		atlas = new TextureAtlas(Gdx.files.internal("data/NinjaMove.txt"));
		TextureRegion[] NinjaRun = new TextureRegion[10];
		        for (int i = 1; i <= 9; i++) {
		            NinjaRun[i] = atlas.findRegion("Nin_"+i);
		        }

		
		run = new Animation(0.5f,NinjaRun);
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		
		//texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		//TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);
		
		//sprite = new Sprite(region);
		
		
		currentFrame=0;
		animationElapsed=0;*/
		
		walkSheet = new Texture(Gdx.files.internal("data/NinjaRun.png"));     // #9
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / 
FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);                                // #10
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
                for (int j = 0; j < FRAME_COLS; j++) {
                        walkFrames[index++] = tmp[i][j];
                }
        }
        walkAnimation = new Animation(0.025f, walkFrames);              // #11
        spriteBatch = new SpriteBatch();                                // #12
        stateTime = 0f;  
	}

	@Override
	public void dispose() {
		batch.dispose();
		atlas.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
	
		/*Gdx.app.log("LOOP", "The loop works fine: Value of currentFrame: " + currentFrame + animationElapsed);
		animationElapsed += Gdx.graphics.getDeltaTime();;

		TextureRegion Frame = run.getKeyFrame(animationElapsed);
		
		Gdx.app.log("Frame Number", ""+run.getKeyFrameIndex(animationElapsed));
				
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(Frame, 50, 50);
		batch.end();*/
		
		 Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);                                            // #14
         stateTime += Gdx.graphics.getDeltaTime();                       // #15
         currentFrame = walkAnimation.getKeyFrame(stateTime, true);      // #16
         spriteBatch.begin();
         Gdx.app.log("Frame Number", ""+walkAnimation.getKeyFrameIndex(animationElapsed));
         spriteBatch.draw(currentFrame, 50, 50);                         // #17
         spriteBatch.end();
				
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
