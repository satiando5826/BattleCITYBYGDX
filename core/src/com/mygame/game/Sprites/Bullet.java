package com.mygame.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.mygame.game.BattleCITYbygdx;

/**
 * Created by Aspire on 9/12/2559.
 */
public class Bullet extends Sprite{           //Don't khow  this correct
    public World world;
    public Body b2body;
    private float stateTime;
    private boolean setToDestroy;
    private boolean destroyed;
    public int firecount=0;

    private float time;
    protected Fixture fixture;

    public Bullet(World world, Tank tank, float direction){
        this.world = world;
        defineBullet(tank,direction);
    }

    public void defineBullet(Tank tank,float direction){   //position x,y of tank direction enum     troble is how to get tank position
        BodyDef bdef = new BodyDef();
        if(tank.b2body.getLinearVelocity().x >0){
            bdef.position.set(tank.b2body.getPosition().add(1f/BattleCITYbygdx.PPM,0));
        }else   bdef.position.set(tank.b2body.getPosition().add(-1f/BattleCITYbygdx.PPM,0));
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(1.3f/BattleCITYbygdx.PPM);
        fdef.filter.categoryBits = BattleCITYbygdx.bullet_BIT;
        fdef.filter.maskBits = BattleCITYbygdx.brick_BIT |
                BattleCITYbygdx.metal_BIT |
                BattleCITYbygdx.water_BIT |
                BattleCITYbygdx.enemy_BIT |
                BattleCITYbygdx.bullet_BIT|
                BattleCITYbygdx.enemy_body_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef);

        CircleShape bull = new CircleShape();
        bull.setRadius(1.4f/BattleCITYbygdx.PPM);
        fdef.shape = bull;
        fdef.isSensor = true;
        fdef.restitution = 0.5f;
        fdef.filter.categoryBits = BattleCITYbygdx.bullet_BIT;
        b2body.createFixture(fdef).setUserData("Bull");

       // b2body.createFixture(fdef).setUserData("Bull");
        fixture = b2body.createFixture(fdef);
        time = stateTime;

    }
    public void setCategoryFilter(short filterBIT){
        Filter filter = new Filter();
        filter.categoryBits = filterBIT;
        fixture.setFilterData(filter);
    }
    public void update(float dt) {
        stateTime += dt;
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            firecount += 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.R)) {
            firecount = 0;
            System.out.println("reload");
        }
            if (this.time <= stateTime - 60 * dt)
            {  if(firecount >0) {
                firecount--;
                world.destroyBody(b2body);
                // Gdx.app.log("A", "collision");
                }
            }
        //b2body.destroyFixture(fixture);
        //b2body.setLinearVelocity(0,0);
    //    if (setToDestroy && !destroyed){
      //      //world.destroyBody(b2body);
     //       b2body.setLinearVelocity(0,0);
      //      destroyed = true;
      //      //b2body.destroyFixture(fixture);
     //   }
    }
    public void hitOnBody() {
        setToDestroy = true;
        //b2body.setLinearVelocity(0,0);
        //System.out.println("GG hit");
    }

}