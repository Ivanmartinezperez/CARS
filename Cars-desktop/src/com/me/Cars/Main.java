package com.me.Cars;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {

		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Cars";
		cfg.useGL20 = false;
		cfg.width = 480;
		cfg.height = 320;
		String hola = "HELLO";
		
		new LwjglApplication(new Game(), cfg);
	}
}