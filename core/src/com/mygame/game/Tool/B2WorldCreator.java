package com.mygame.game.Tool;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygame.game.BattleCITYbygdx;
import com.mygame.game.Screen.PlayScreen;
import com.mygame.game.Sprites.*;

/**
 * Created by Aspire on 8/12/2559.
 */
public class B2WorldCreator {
    public Array<AiTank> getAiTankss() {
        return aiTankss;
    }

    public Array<AiTank> getAiTankssUD() {
        return aiTankssUD;
    }

    private Array<AiTank> aiTankss;
    private Array<AiTank> aiTankssUD;

    public B2WorldCreator(PlayScreen screen){
        World world = screen.getWorld();
        TiledMap map = screen.getMap();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //Metal wall
        for(MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

           new metal(screen,rect);
        }


        //forest
        for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

          //  new forest(screen,rect);
        }

        //water
        for(MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new water(screen,rect);
        }

        //Base
        for(MapObject object : map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Base(screen,rect);
        }

        //Brick
        for(MapObject object : map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Brick(screen,rect);
        }

     //   Stage frame
        for(MapObject object : map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new frame(screen,rect);
            //fdef.filter.categoryBits = BattleCITYbygdx.water_BIT;                  //ชนเหี้ย
         /*   bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth()/2/BattleCITYbygdx.PPM, rect.getY() + rect.getHeight()/2/BattleCITYbygdx.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2/BattleCITYbygdx.PPM,rect.getHeight()/2/BattleCITYbygdx.PPM);
            fdef.shape = shape;

            body.createFixture(fdef);
            */
        }

        //create all aiTank
        aiTankss = new Array<AiTank>();
        for(MapObject object : map.getLayers().get(12).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            aiTankss.add(new AiTank(screen, rect.getX()/BattleCITYbygdx.PPM,rect.getY()/BattleCITYbygdx.PPM));
        }

        //create all aiTankUD
        aiTankssUD = new Array<AiTank>();
        for(MapObject object : map.getLayers().get(13).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            aiTankssUD.add(new AiTank(screen, rect.getX()/BattleCITYbygdx.PPM,rect.getY()/BattleCITYbygdx.PPM));
        }

    }
}
