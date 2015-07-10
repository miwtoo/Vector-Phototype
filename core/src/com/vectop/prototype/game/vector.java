package com.vectop.prototype.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Rectangle;

public class vector extends ApplicationAdapter {

	SpriteBatch batch;

	TiledMap map;
	OrthogonalTiledMapRenderer mapRenderer;

	OrthographicCamera camera;

	Texture imageHero;
	TextureRegion[][] regions;

	Animation stan;

	Rectangle Hero;

	TextureRegion currentFrame;
	float stateTime;


	@Override
	public void create () {
		batch = new SpriteBatch();

		map = new TmxMapLoader().load("untitled.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(map);

		imageHero = new Texture(Gdx.files.internal("warrior_m.png"));

		regions = TextureRegion.split(imageHero,imageHero.getWidth()/3,imageHero.getHeight()/4);
		stan = new Animation(1,regions[1][0],regions[1][1],regions[1][2]);

		Hero = new Rectangle();

		Hero.x = 10;
		Hero.y = 150;


		camera = new OrthographicCamera();
		camera.setToOrtho(false, 640, 640);
		camera.update();

		stateTime = 0f;

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		float deltaTime = Gdx.graphics.getDeltaTime();
		stateTime += deltaTime;
		currentFrame = stan.getKeyFrame(stateTime,true);

		mapRenderer.setView(camera);
		mapRenderer.render();

		batch.begin();
		batch.draw(regions[1][0], Hero.x, Hero.y);
		batch.end();

		if(Gdx.input.isKeyPressed(Keys.LEFT)){Hero.x -= 80*deltaTime; }
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){Hero.x += 80*deltaTime;}

		//System.out.println(currentFrame);
		//if(Hero.overlaps(mapRenderer)){};
		//Hero.y -= 200*deltaTime;
	}
}
