package com.tank_game.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Game extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Tank player;
	private Tank2 player2;
	private Maps maps;
	private ArrayList<Wall> map;
	private int map_num = 1;

	public void create () {
		// Loads camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 800);

		// Create Batch
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);

		// Creates Player
		player = new Tank("tank.jpg", "canon.jpg","bullet.jpg", "explode.jpg", 200, 400);
		player2 =  new Tank2("tank.jpg", "canon.jpg","bullet.jpg", "explode.jpg", 600, 400);

		// Creates Maps
		maps = new Maps();
		map = maps.getMap(map_num);
	}

	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Esc to exit
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) System.exit(0);

		//draw the walls
		for (Wall wall: map){
			wall.step();
		}

		// Check if either tank is dead
		for (Bullet bullet: player.bullets){
			if (player2.collision.overlaps(bullet.collision)){
				player2.dead = true;
			}
			break;
		}
		for (Bullet bullet: player2.bullets){
			if (player.collision.overlaps(bullet.collision)){
				player.dead = true;
			}
			break;
		}

		// Runs through the players actions
		player.step();
		player2.step();

		// Updates the camera
		camera.update();
	}

	public void dispose () {
	}
}
