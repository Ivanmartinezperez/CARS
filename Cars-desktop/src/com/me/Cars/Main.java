package com.me.Cars;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {

		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Cars";
		cfg.useGL20 = false;
		cfg.width = 1024;
		cfg.height = 512;
		
		
		new LwjglApplication(new Game(), cfg);
	}
}
