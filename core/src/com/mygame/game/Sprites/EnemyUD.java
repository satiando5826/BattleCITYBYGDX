package com.mygame.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygame.game.Screen.PlayScreen;

/**
 * Created by SatrarinSaejew on 12/14/2016 AD.
 */
public abstract class EnemyUD extends Sprite {
    protected World world;
    protected PlayScreen screen;
    public Body b2body;
    public Vector2 velocityUD;


    public EnemyUD(PlayScreen screen, float x, float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x,y);
        defineEnemy();
        velocityUD = new Vector2(0,1);
    }


    protected abstract void defineEnemy();
    public abstract void update(float dt);
    public abstract void hitOnBody();

    public void reversVelocity(boolean x, boolean y){
        if (x){
            velocityUD.x = -velocityUD.x;
        }
        if (y){
            velocityUD.y = -velocityUD.y;
        }
    }
}
