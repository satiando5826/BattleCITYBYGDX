package com.mygame.game.Tool;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.mygame.game.BattleCITYbygdx;
import com.mygame.game.Sprites.*;

/**
 * Created by Aspire on 8/12/2559.
 */
public class B2WorldCreator {
    public B2WorldCreator(World world, TiledMap map){

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //Metal wall
        for(MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

           new metal(world,map,rect);
        }


        //forest
        for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new forest(world,map,rect);
        }

        //water
        for(MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new water(world,map,rect);
        }

        //Base
        for(MapObject object : map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Base(world,map,rect);
        }

        //Brick
        for(MapObject object : map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Brick(world,map,rect);
        }

     //   Stage frame
        for(MapObject object : map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new frame(world,map,rect);
         /*   bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth()/2/BattleCITYbygdx.PPM, rect.getY() + rect.getHeight()/2/BattleCITYbygdx.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2/BattleCITYbygdx.PPM,rect.getHeight()/2/BattleCITYbygdx.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
            */
        }
    }
}
