package net.kumar.gdxGame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Levels implements Screen {
	
	private Stage stage;
	private Table table;
	private TextureAtlas atlas;
	private Skin skin;
	private List list;
	private ScrollPane scrollPane;
	private TextButton play, back;
	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
//		Table.drawDebug(stage);
		
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		
		atlas = new TextureAtlas(Gdx.files.internal("ui/atlas.pack"));
		skin = new Skin(Gdx.files.internal("ui/menuSkin.json") , atlas);
		
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
//		table.debug();
		
		list = new List(new String[] {"one", "two", "three", "and so on", "one", "two", "three", 
				"and so on", "and so on", "and so on", "and so on", "and so on", "and so on", "and so on", 
				"and so on", "and so on", "and so on", "and so on", "and so on", "and so on", "and so on", "and so on", 
				"and so on", "and so on", "and so on", "and so on", "and so on"}, skin);
		
		scrollPane = new ScrollPane(list , skin);
		
		play = new TextButton("Play", skin);
		play.pad(15);
		back = new TextButton("Back", skin, "small");
		back.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				((Game) Gdx.app.getApplicationListener()).setScreen(new mainMenu());
			}
		});
		back.pad(10);
		
		
		//put stuff together
		table.add().width(table.getWidth()/3);
		table.add("SELECT LEVEL").width(table.getWidth()/3).center();
		table.add().width(table.getWidth()/3).row();
		table.add(scrollPane).left().expandY();
		table.add(play);
		table.add(back).bottom().right();
		stage.addActor(table);
		
		
		
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
