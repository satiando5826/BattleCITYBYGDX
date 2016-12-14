package com.mygame.game.Sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.mygame.game.BattleCITYbygdx;
import com.mygame.game.Screen.PlayScreen;
import javafx.animation.Animation;

import java.util.ArrayList;

/**
 * Created by SatrarinSaejew on 12/14/2016 AD.
 */
public class AiTank extends Enemy {

    private float stateTime;
    private Animation walkAnimation;
    private ArrayList<TextureRegion>frames;

    public AiTank(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new ArrayList<TextureRegion>();
        for (int i=0; i < 2; i++){
            frames.add(new TextureRegion(screen.getAtlas().findRegion("AiTank"),i*16,0,16,16));
        walkAnimation = new Animation(0.4f,frames);
        stateTime = 0;
        setBounds(getX(),getY(),16/BattleCITYbygdx.PPM,16/BattleCITYbygdx.PPM);

        }
    }

    public void update(float dt){
        stateTime += dt;
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
        setRegion(walkAnimation.getkeyFrame(stateTime,true));
    }

    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(50 / BattleCITYbygdx.PPM,40/ BattleCITYbygdx.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5.5f/BattleCITYbygdx.PPM);

        fdef.filter.categoryBits = BattleCITYbygdx.ENEMY_BIT;
        fdef.filter.maskBits = BattleCITYbygdx.water_BIT |
                BattleCITYbygdx.GROUND_BIT |
                BattleCITYbygdx.OBJECT_BIT |
                BattleCITYbygdx.ENEMY_BIT |
                BattleCITYbygdx.brick_BIT |
                BattleCITYbygdx.tank_BIT |
                BattleCITYbygdx.metal_BIT;
        fdef.shape = shape;
        b2body.createFixture(fdef);

    }
}
