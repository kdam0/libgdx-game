package com.me.mygdxgame;

import net.kumar.gdxGame.screens.mainMenu;

import com.badlogic.gdx.Game;


public class MyGdxGame extends Game {
	
	public static final String TITLE = "My-Lib-Gdx-Game" , VERSION = "0.0.0.0.A";
	
	
	@Override
	public void create() {		
		//splash screen
		setScreen(new mainMenu());
			
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {	
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
