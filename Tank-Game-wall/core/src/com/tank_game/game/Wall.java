package com.tank_game.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Wall extends ApplicationAdapter {
    private SpriteBatch batch = new SpriteBatch();
    private String wallImgPath = "wall.jpg";
    public Texture wallImg = new Texture(Gdx.files.internal(wallImgPath));;
    public Rectangle collision;
    public float angle;

    public Wall(float x, float y, float angle) {
        collision = new Rectangle();
        collision.x = x;
        collision.y = y;
        this.angle = angle;
        collision.width = 16;
        collision.height = 64;
    }

    public void step(){
        batch.begin();
        batch.draw(wallImg, collision.x, collision.y, 8, 8, 16, 64,
                1, 1, angle, 0, 0, 16, 64, false, false);
        batch.end();
    }
}