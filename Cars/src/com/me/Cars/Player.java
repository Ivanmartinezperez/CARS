package com.me.Cars;

import net.dermetfan.utils.libgdx.graphics.AnimatedBox2DSprite;
import net.dermetfan.utils.libgdx.graphics.AnimatedSprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class Player extends Actor {

	Body body;
	Fixture fix;
	Array<AtlasRegion> ninjarun;
	AtlasRegion test;
	Array<Animation> myanimations;
	AnimatedBox2DSprite bicho;
	private boolean jumping;
	
	public Player() {
		// TODO Auto-generated constructor stub
		Texture tex = new Texture(Gdx.files.internal("data/Ninja.png"));
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/Ninja.atlas"));
		
		jumping=false;
	
		//atlas.addRegion("Ninja", tex, 316, 8, 142, 104);
		
		ninjarun = atlas.findRegions("Ninja");
		System.out.println(ninjarun.size);
		myanimations = new Array<Animation>();
		myanimations.add(new Animation(1/3f,ninjarun));
		myanimations.get(0).setPlayMode(Animation.LOOP);
		AnimatedSprite ap = new AnimatedSprite(myanimations.get(0));
		bicho = new AnimatedBox2DSprite(ap);
		
		
	}
	
	public void crear(World world, Stage stage){
		//Body Definition
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(new Vector2(3,4));
		
		//shape
		CircleShape shape = new CircleShape();
		shape.setRadius(0.15f);
		
		//Fixture Definition
		FixtureDef fixdef = new FixtureDef();
		fixdef.shape = shape;
		fixdef.density = 2.5f;
		fixdef.friction = 0.5f;
		fixdef.restitution = 1f;
		
		//body = world.createBody(def);
		//fix = body.createFixture(fixdef);
		
		//shape.dispose();
		
		// Caja def
		
		BodyDef cajaboddef = new BodyDef();
		cajaboddef.type = BodyType.DynamicBody;
		cajaboddef.position.set(new Vector2(4,4));
		
		//shape
		PolygonShape square = new PolygonShape();
		square.setAsBox(0.5f, 1f);
		
		//square fixture
		FixtureDef squarefix = new FixtureDef();
		squarefix.density = 5f;
		squarefix.friction = 0.2f;
		squarefix.restitution = 0;
		squarefix.shape = square;
		
		body = world.createBody(cajaboddef);
		body.createFixture(squarefix);
		body.setFixedRotation(true);
		
		
		
		
		
		
		
		
		
		
		
	}
	
	public boolean isJumping(){
		return jumping;
	}
	
	public void Jump(boolean is){
		jumping = is;
	}
	

}
