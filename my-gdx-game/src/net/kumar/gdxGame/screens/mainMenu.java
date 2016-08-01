package net.kumar.gdxGame.screens;

import net.kumar.myGdx.tween.actorAcessor;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.mygdxgame.MyGdxGame;

public class mainMenu implements Screen {
	
	private Stage stage; //done
	private Skin skin; //done
	private TextureAtlas atlas; //done
	private Table table; //done
	private TextButton buttonExit, buttonPlay;
	private Label heading;
	private TweenManager tweenManager;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		tweenManager.update(delta);
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
		table.invalidateHierarchy();
		table.setSize(width, height);
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		atlas = new TextureAtlas("ui/atlas.pack");
		skin = new Skin(Gdx.files.internal("ui/menuSkin.json") , atlas);
		
		
		table = new Table();
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		//createing heading
		heading = new Label(MyGdxGame.TITLE , skin);
		heading.setFontScale(2);
		
		//button functionality
		
		buttonPlay = new TextButton("Play" , skin);
		buttonPlay.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new Levels());
			}
		});
		buttonPlay.pad(15);
		
		buttonExit = new TextButton("Exit", skin);
		buttonExit.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		buttonExit.pad(22);
		
		//putting stuff together
		table.add(heading);
		table.getCell(heading).spaceBottom(100);
		table.row();
		table.add(buttonPlay);
		table.getCell(buttonPlay).spaceBottom(10);
		table.row();
		table.add(buttonExit);
		stage.addActor(table);
		
		//createig animations
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new actorAcessor());
		
		
		// heading flashing colors animation
		Timeline.createSequence().beginSequence()
		.push(Tween.to(heading, actorAcessor.RGB, 0.5f).target(0,0,1))
		.push(Tween.to(heading, actorAcessor.RGB, 0.5f).target(0,1,0))
		.push(Tween.to(heading, actorAcessor.RGB, 0.5f).target(1,0,0))
		.push(Tween.to(heading, actorAcessor.RGB, 0.5f).target(1,1,0))
		.push(Tween.to(heading, actorAcessor.RGB, 0.5f).target(0,1,1))
		.push(Tween.to(heading, actorAcessor.RGB, 0.5f).target(1,0,1))
		.push(Tween.to(heading, actorAcessor.RGB, 0.5f).target(1,1,1))
		.end().repeat(Tween.INFINITY, 0).start(tweenManager);
		
		//heading and buttons fade in.
		Timeline.createSequence().beginSequence()
		.push(Tween.set(buttonPlay, actorAcessor.ALPHA).target(0))
		.push(Tween.set(buttonExit, actorAcessor.ALPHA).target(0))
		.push(Tween.from(heading, actorAcessor.ALPHA, 0.25f).target(0))
		.push(Tween.to(buttonPlay, actorAcessor.ALPHA, 0.25f).target(1))
		.push(Tween.to(buttonExit, actorAcessor.ALPHA, 0.25f).target(1))
		.end().start(tweenManager);
		
		//table fate in
		Tween.from(table, actorAcessor.ALPHA, 0.5f).target(0);
		Tween.from(table, actorAcessor.Y, 0.5f).target(Gdx.graphics.getHeight()/8).start(tweenManager);
		
		
		
		
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		stage.dispose();
		atlas.dispose();
		skin.dispose();
		
	}

}
