package com.mygame.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygame.game.BattleCITYbygdx;

/**
 * Created by Aspire on 14/12/2559.
 */
public class forest extends InteractiveTileObject {
    public forest(World world, TiledMap map, Rectangle bounds){
        super(world,map,bounds);
        fixture.setUserData(this);
        setCategoryFilter(BattleCITYbygdx.forest_BIT);

    }

    @Override
    public void onBullethit() {

    }
}