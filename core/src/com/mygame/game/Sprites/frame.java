package com.mygame.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygame.game.BattleCITYbygdx;

/**
 * Created by Aspire on 14/12/2559.
 */
public class frame extends InteractiveTileObject{
    public frame(World world, TiledMap map, Rectangle bounds){
        super(world,map,bounds);
        fixture.setUserData(this);
        setCategoryFilter(BattleCITYbygdx.frame_BIT);
    }

    @Override
    public void onBullethit() {
        Gdx.app.log("frame","collision");
    }

}