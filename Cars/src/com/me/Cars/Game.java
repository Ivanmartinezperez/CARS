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
import com.badlogic.gdx.physics.box2d.Body;
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
	Array<Body> cuerpos;
	Player p;
	
	@Override
	public void create() {		
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		Render=true;
		
		rotationSpeed=0.5f;
		
		cuerpos = new Array<Body>();
		world = new World( new Vector2(0,-9.81f),true);
		debug = new Box2DDebugRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024/80, 512/80);
		
		// Inicializamos escenario
		Escenario = new Stage();
		Escenario.setCamera(camera);
		p = new Player();
		Escenario.getSpriteBatch().begin();
		p.crear(world, Escenario);
		Escenario.getSpriteBatch().end();
		Escenario.addActor(p);
		
		world.getBodies(cuerpos);
		
		
		
		Box2DMapObjectParser parser = new Box2DMapObjectParser(0.015625f);
		
		
		TiledMap map = new TmxMapLoader().load("maps/Mapa3.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(map,parser.getUnitScale());

		
		parser.load(world, map);
		
		System.out.println(parser.getBodies().size);
		
		
	
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//Player
		CheckJump();
		handleInput();
		
		//Camara
		camera.position.set(cuerpos.get(0).getPosition().x,cuerpos.get(0).getPosition().y,0);
		camera.update();
		
		//renderiza las texturas
		mapRenderer.setView(camera);
		if(Render)mapRenderer.render();
				
		//Renderiza los objetos
		debug.render(world, camera.combined);
		
		//Escenario
		Escenario.getSpriteBatch().begin();
		p.pintate(Escenario.getSpriteBatch(),world);
		Escenario.getSpriteBatch().enableBlending();
		Escenario.getSpriteBatch().end();
		Escenario.act();
		Escenario.draw();
		
		
		
		world.step(1/60f, 8, 3);
		
		
		System.out.println(cuerpos.get(0).getLinearVelocity().x +" " + cuerpos.get(0).getLinearVelocity().y);
	
				
	}
	
	private void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
                camera.zoom += 0.02;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
                camera.zoom -= 0.02;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                for (int i = 0; i < cuerpos.size; i++) {
					cuerpos.get(i).applyForceToCenter(-100, 0, true);
				}
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
        	for (int i = 0; i < cuerpos.size; i++) {
				cuerpos.get(i).applyForceToCenter(100, 0, true);
			}
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
        	for (int i = 0; i < cuerpos.size; i++) {
				cuerpos.get(i).applyForceToCenter(0, 0, true);
			}
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
        	if(!p.isJumping()){
        		for (int i = 0; i < cuerpos.size; i++) {
        			cuerpos.get(i).applyLinearImpulse(0, 70, cuerpos.get(0).getPosition().x, cuerpos.get(0).getPosition().y, true);
        		}
        		System.out.println(cuerpos.size);
        	}
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
	
	private void CheckJump(){
		if(cuerpos.get(0).getLinearVelocity().y == 0){
			p.Jump(false);
		}
		else p.Jump(true);
	}
}

