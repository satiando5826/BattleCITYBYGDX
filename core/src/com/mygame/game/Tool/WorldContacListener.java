package com.mygame.game.Tool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.mygame.game.BattleCITYbygdx;
import com.mygame.game.Sprites.Bullet;
import com.mygame.game.Sprites.Enemy;
import com.mygame.game.Sprites.InteractiveTileObject;

/**
 * Created by Aspire on 13/12/2559.
 */
public class WorldContacListener implements ContactListener{
    private float stateTime=0f;

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        if(fixA.getUserData() == "Bull" || fixB.getUserData() == "Bull"){
            Fixture bull = fixA.getUserData() == "Bull" ? fixA:fixB;
            Fixture object = bull == fixA ? fixB : fixA;

            if(object.getUserData() instanceof InteractiveTileObject){
                ((InteractiveTileObject) object.getUserData()).onBullethit();
            }
        }
        switch (cDef){
            case BattleCITYbygdx.enemy_body_BIT | BattleCITYbygdx.bullet_BIT:
                if (fixA.getFilterData().categoryBits == BattleCITYbygdx.enemy_body_BIT){
                    Gdx.app.log("A","collision");
                    ((Enemy)fixA.getUserData()).hitOnBody();
                    Gdx.app.log("aa","collision");
                    //((Bullet)fixB.getUserData()).hitOnBody();
                    Gdx.app.log("aaa","collision");

                }else if (fixB.getFilterData().categoryBits == BattleCITYbygdx.enemy_BIT){
                    Gdx.app.log("B","collision");
                    ((Enemy)fixB.getUserData()).hitOnBody();
                 //   ((Bullet)fixA.getUserData()).hitOnBody();
                }
               // else {
               //     Gdx.app.log("error","collision");
               //     ((Bullet)fixA.getUserData()).hitOnBody();
               // }
        switch (cDef) {
            case BattleCITYbygdx.bullet_BIT | BattleCITYbygdx.enemy_body_BIT:
                if (fixA.getFilterData().categoryBits == BattleCITYbygdx.enemy_body_BIT) {
                    ((Enemy) fixA.getUserData()).hitOnBody();
                } else if (fixB.getFilterData().categoryBits == BattleCITYbygdx.enemy_BIT) {
                    ((Enemy) fixB.getUserData()).hitOnBody();
                }
                /*else if (fixA.getFilterData().categoryBits == BattleCITYbygdx.brick_BIT){
                    ((Enemy) fixB.getUserData()).reversVelocity(false,true);
                }*/break;


            case BattleCITYbygdx.enemy_BIT | BattleCITYbygdx.brick_BIT:                  //ชนกับ brick ก่อนถึงเข้าเงื่อนไข
                if (fixA.getFilterData().categoryBits == BattleCITYbygdx.enemy_BIT){
                    ((Enemy)fixA.getUserData()).reversVelocity(true,false);
                }else{
                    //if(stateTime==0f) {
                        ((Enemy) fixB.getUserData()).reversVelocity(true, false);
                        //stateTime=1;
                    //}
                    //else {
                        //stateTime = 0f;
                    //}

                    break;
                }


        }

    }

    @Override
    public void endContact(Contact contact) {
     /*   Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
        Gdx.app.log("ee","collision");
        switch (cDef) {
            case BattleCITYbygdx.enemy_body_BIT | BattleCITYbygdx.bullet_BIT:
                if (fixA.getFilterData().categoryBits == BattleCITYbygdx.enemy_body_BIT) {
                    if (fixB.getFilterData().categoryBits == BattleCITYbygdx.bullet_BIT) {
                        Gdx.app.log("fixB","collision");
                        ((Bullet) fixB.getUserData()).hitOnBody();

                    } else if (fixB.getFilterData().categoryBits == BattleCITYbygdx.bullet_BIT) {
                        ((Bullet) fixB.getUserData()).hitOnBody();
                    }
                }

        }*/
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
