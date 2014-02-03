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
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
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
	
	public Player() {
		// TODO Auto-generated constructor stub
		Texture tex = new Texture(Gdx.files.internal("data/Ninja.png"));
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/Ninja.atlas"));
	
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
		
		body = world.createBody(def);
		fix = body.createFixture(fixdef);
		
		shape.dispose();
		
		
		
		
		
		
	}
	

}
