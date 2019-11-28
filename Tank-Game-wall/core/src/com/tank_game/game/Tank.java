package com.tank_game.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class Tank extends ApplicationAdapter {
    private SpriteBatch batch = new SpriteBatch();

    public Texture tankImg;
    private Texture canonImg;
    public Texture bulletImg;
    public Texture explodeImg;
    public Rectangle collision;
    public float angle;
    public boolean dead;

    //tank's bullets array list
    public ArrayList<Bullet> bullets;

    int timer = 0; // For counting up
    int bullet_cooldown = 50; // To limit the amount of bullets a tank can shoot each unit time

    public Tank(String tankImgPath, String canonImgPath, String bulletImgPath, String explodeImgPath, int x, int y) {
        tankImg = new Texture(Gdx.files.internal(tankImgPath));
        canonImg = new Texture(Gdx.files.internal(canonImgPath));
        bulletImg = new Texture(Gdx.files.internal(bulletImgPath));
        explodeImg = new Texture(Gdx.files.internal(explodeImgPath));
        collision = new Rectangle();
        collision.x = x;
        collision.y = y;
        collision.width = 64;
        collision.height = 64;
        //initialize cannon
        angle = 0;
        //initialize bullets
        bullets = new ArrayList<Bullet>();
        //tank's status
        dead = false;
    }

    public void step() {
        movement();
        //mouse_point();
        bullet_movement();
        timer++;
        bullet_cooldown++;

        // Draws all the textures in the game
        batch.begin();

        if (!bullets.isEmpty()){
            for (Bullet bullet: bullets) {
                batch.draw(bulletImg, bullet.collision.x, bullet.collision.y, 10, 10);
            }
        }

        batch.draw(tankImg, collision.x, collision.y, 32, 32, 64, 64, 1, 1,
                angle, 0, 0, 64, 64, false, false);

        batch.draw(canonImg, collision.x + 24, collision.y + 24, 8, 8, 16, 64,
        1, 1, angle, 0, 0, 16, 64, false, false);

        if (dead){
            batch.draw(explodeImg, collision.x, collision.y, 64, 64);
        }

        batch.end();
    }

    public void movement() {
        // Keyboard Movement
        /*
        if(Gdx.input.isKeyPressed(Input.Keys.A)) collision.x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.D)) collision.x += 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.W)) collision.y += 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.S)) collision.y -= 200 * Gdx.graphics.getDeltaTime();
         */
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            angle += 120 * Gdx.graphics.getDeltaTime();
            angle %= 360;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            angle -= 120 * Gdx.graphics.getDeltaTime();
            if (angle<0){
                angle += 360;
            }
            angle %= 360;
        }

        float angle_temp = angle;
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            collision.x -= 200 * Math.sin(Math.toRadians(angle_temp)) * Gdx.graphics.getDeltaTime();
            collision.y += 200 * Math.cos(Math.toRadians(angle_temp)) * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            collision.x += 200 * Math.sin(Math.toRadians(angle)) * Gdx.graphics.getDeltaTime();
            collision.y -= 200 * Math.cos(Math.toRadians(angle)) * Gdx.graphics.getDeltaTime();
        }

        // Restricts the tank to go outside of the window
        if(collision.x < 0) collision.x = 0;
        if(collision.x > 800 - 64) collision.x = 800 - 64;
        if(collision.y < 0) collision.y = 0;
        if(collision.y > 800 - 64) collision.y = 800 - 64;
    }

    public void bullet_movement() {
        //update the location of all the bullet
        ArrayList<Bullet> new_bullets = new ArrayList<Bullet>();
        for (Bullet bullet: bullets){
            bullet.collision.x -= 300 * Math.sin(Math.toRadians(bullet.angle)) * Gdx.graphics.getDeltaTime();
            bullet.collision.y += 300 * Math.cos(Math.toRadians(bullet.angle)) * Gdx.graphics.getDeltaTime();
            System.out.println("angle :"+bullet.angle);
            System.out.println("x" + bullet.collision.x);
            System.out.println("y" + bullet.collision.y);
            if (bullet.collision.x < 800 && bullet.collision.x > 0 && bullet.collision.y < 800 && bullet.collision.y > 0){
                new_bullets.add(bullet);
            }
        }
        bullets = new_bullets;

        // Keyboard Shoot Bullet
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && bullet_cooldown >= 50){
            float angle_temp = angle;
            float x_temp = collision.x + 32 - 64 * (float)Math.sin(Math.toRadians(angle_temp));
            float y_temp = collision.y + 32 + 64 * (float)Math.cos(Math.toRadians(angle_temp));
            Bullet bullet = new Bullet(x_temp, y_temp , angle);
            bullets.add(bullet);
            System.out.println("angle: " + bullet.angle);
            bullet_cooldown = 0;
        }
    }


    /*public float mouse_point() {
        // Holds the mouse coordinates
        Vector3 mousePos = new Vector3();
        if (timer == 50) {
            System.out.println("x: " + collision.x + ", y: " + collision.y);
            System.out.println("Mouse x: " + Gdx.input.getX() + ", Mouse y: " + Gdx.input.getY());
            timer = 0;
        }

        float xDif = collision.x - Gdx.input.getX();
        float yDif = collision.y - Gdx.input.getY();
        double angle = Math.toDegrees(Math.atan(xDif / yDif));
        if (yDif <= 0) {
            angle += 180;
        }
        //change the angle range from -90-270, to 0-360.
        if (angle < 0) {
            angle = 360 + angle;
        }

        // System.out.println(angle);
        return (float)angle;
    }

     */
}
