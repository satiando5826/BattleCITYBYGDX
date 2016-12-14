package com.mygame.game.Tool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.mygame.game.Sprites.InteractiveTileObject;

/**
 * Created by Aspire on 13/12/2559.
 */
public class WorldContacListener implements ContactListener{
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA.getUserData() == "Bull" || fixB.getUserData() == "Bull"){
            Fixture bull = fixA.getUserData() == "Bull" ? fixA:fixB;
            Fixture object = bull == fixA ? fixB : fixA;

            if(object.getUserData() instanceof InteractiveTileObject){
                ((InteractiveTileObject) object.getUserData()).onBullethit();
            }
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
