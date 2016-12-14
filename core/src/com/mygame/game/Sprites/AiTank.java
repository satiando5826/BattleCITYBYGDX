package com.mygame.game.Sprites;

import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.mygame.game.BattleCITYbygdx;
import com.mygame.game.Screen.PlayScreen;
import javafx.animation.Animation;

/**
 * Created by SatrarinSaejew on 12/15/2016 AD.
 */
public class AiTank extends Enemy {
    private float stateTime;
    private com.badlogic.gdx.graphics.g2d.Animation walkAnimation;
    private Array<TextureRegion> frames;
    private boolean setToDestroy;
    private boolean destroyed;



    public AiTank(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(screen.getAtlas().findRegion("TG1_d1"),0,0,16,16));
        frames.add(new TextureRegion(screen.getAtlas().findRegion("TG1_d2"),0,0,16,16));
        walkAnimation = new com.badlogic.gdx.graphics.g2d.Animation(0.4f,frames);
        frames.clear();
        stateTime = 0;
        setBounds(getX(),getY(),16/BattleCITYbygdx.PPM,16/BattleCITYbygdx.PPM);
        setToDestroy = false;
        destroyed = false;
    }



    public void update(float dt){
        stateTime += dt;
        if (setToDestroy && !destroyed){
            world.destroyBody(b2body);
            destroyed = true;
            setRegion(new TextureRegion(screen.getAtlas().findRegion("bomb1"),0,0,16,16));
        }
        else if(!destroyed) {
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
            setRegion(walkAnimation.getKeyFrame(stateTime, true));
        }
    }

    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5.5f/BattleCITYbygdx.PPM);

        fdef.filter.categoryBits = BattleCITYbygdx.enemy_BIT;
        fdef.filter.maskBits = BattleCITYbygdx.water_BIT |
                BattleCITYbygdx.DEFAULT_BIT |
                BattleCITYbygdx.brick_BIT |
                BattleCITYbygdx.enemy_BIT |
                BattleCITYbygdx.OBJECT_BIT |
                BattleCITYbygdx.metal_BIT;


        fdef.shape = shape;
        b2body.createFixture(fdef);

        PolygonShape bodyAi = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-4,4/*ขนาดของกล่อง*/).scl(1/BattleCITYbygdx.PPM);
        vertice[1] = new Vector2(4,4).scl(1/BattleCITYbygdx.PPM);
        vertice[2] = new Vector2(-4,-4).scl(1/BattleCITYbygdx.PPM);
        vertice[3] = new Vector2(4,-4).scl(1/BattleCITYbygdx.PPM);
        bodyAi.set(vertice);

        fdef.shape = bodyAi;
        fdef.restitution = 0.5f;
        fdef.filter.categoryBits = BattleCITYbygdx.enemy_body_BIT;
        b2body.createFixture(fdef).setUserData(this);

    }

    @Override
    public void hitOnBody() {
        setToDestroy = true;
    }
}
