package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.CheeseGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = CheeseGame.TITLE;
		config.width = CheeseGame.V_WIDTH * CheeseGame.SCALE;
		config.height = CheeseGame.V_HEIGHT * CheeseGame.SCALE;

		new LwjglApplication(new CheeseGame(), config);
	}
}
