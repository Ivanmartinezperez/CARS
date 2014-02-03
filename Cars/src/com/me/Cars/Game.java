package com.me.Cars;

import net.dermetfan.utils.libgdx.box2d.Box2DMapObjectParser;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class Game implements ApplicationListener {
	private World world;
	private Box2DDebugRenderer debug;
	private OrthogonalTiledMapRenderer mapRenderer;
	private OrthographicCamera camera;
	private float rotationSpeed;
	boolean Render;
	Stage Escenario;
	
	@Override
	public void create() {		
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		Render=true;
		
		rotationSpeed=0.5f;
		
		world = new World( new Vector2(0,-9.81f),true);
		debug = new Box2DDebugRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024/80, 512/80);
		
		// Inicializamos escenario
		Escenario = new Stage();
		Escenario.setCamera(camera);
		Player p = new Player();
		p.crear(world, Escenario);
		Escenario.addActor(p);
		
		
		Box2DMapObjectParser parser = new Box2DMapObjectParser(0.015625f);
		
		TiledMap map = new TmxMapLoader().load("maps/Mapa3.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(map,parser.getUnitScale());

		
		parser.load(world, map);
		
		
	
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		handleInput();
		
		camera.update();
		
		Escenario.act();
		Escenario.draw();
		
		
		
		world.step(1/60f, 8, 3);
		
		mapRenderer.setView(camera);
		if(Render)mapRenderer.render();
		debug.render(world, camera.combined);
	
				
	}
	
	private void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
                camera.zoom += 0.02;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
                camera.zoom -= 0.02;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                if (camera.position.x > 0)
                        camera.translate(-1, 0, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                if (camera.position.x < 1024)
                        camera.translate(1, 0, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                if (camera.position.y > 0)
                        camera.translate(0, -1, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
                if (camera.position.y < 1024)
                        camera.translate(0, 1, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
                camera.rotate(-rotationSpeed, 0, 0, 1);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.E)) {
                camera.rotate(rotationSpeed, 0, 0, 1);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.R)){
        	Render=!Render;
        }
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

