package com.mygame.game.Tool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.mygame.game.BattleCITYbygdx;
import com.mygame.game.Sprites.Enemy;
import com.mygame.game.Sprites.InteractiveTileObject;

/**
 * Created by Aspire on 13/12/2559.
 */
public class WorldContacListener implements ContactListener{
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
            case BattleCITYbygdx.bullet_BIT | BattleCITYbygdx.enemy_body_BIT :
                if (fixA.getFilterData().categoryBits == BattleCITYbygdx.enemy_body_BIT){
                    ((Enemy)fixA.getUserData()).hitOnBody();
                }else if (fixB.getFilterData().categoryBits == BattleCITYbygdx.enemy_BIT){
                ((Enemy)fixB.getUserData()).hitOnBody();
                break;
            }/*

            //case BattleCITYbygdx.enemy_body_BIT | BattleCITYbygdx.brick_BIT:
            case BattleCITYbygdx.enemy_BIT | BattleCITYbygdx.brick_BIT:
                if (fixA.getFilterData().categoryBits == BattleCITYbygdx.enemy_BIT){
                    ((Enemy)fixA.getUserData()).reversVelocity(true,false);
                }else
                    ((Enemy)fixB.getUserData()).reversVelocity(true,false);
                    break;
                    */

        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
