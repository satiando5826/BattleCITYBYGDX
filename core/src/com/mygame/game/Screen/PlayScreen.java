package com.mygame.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygame.game.BattleCITYbygdx;
import com.mygame.game.Scenes.HUD;
import com.mygame.game.Sprites.Bullet;
import com.mygame.game.Sprites.Tank;
import com.mygame.game.Tool.B2WorldCreator;
import com.mygame.game.Tool.WorldContacListener;

/**
 * Created by Aspire on 22/11/2559.
 */
public class PlayScreen implements Screen {

    private BattleCITYbygdx game;
    private TextureAtlas atlas;

    private OrthographicCamera gamecamera;
    private Viewport gamePort;
    private HUD hud;

    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    private Tank player;
    private Bullet b1;      //only 3 bullet per screen ?
    private Bullet b2;
    private Bullet b3;

    private float directionx = 0;  //from last direction input or may be used from vector of tank
    private float directiony = 0;


    public PlayScreen(BattleCITYbygdx game){
        atlas = new TextureAtlas("Tank.pack");

        this.game = game;
        //texture = new Texture("badlogic.jpg");
        gamecamera = new OrthographicCamera();
        gamePort = new FitViewport(BattleCITYbygdx.V_WIDTH / BattleCITYbygdx.PPM,BattleCITYbygdx.V_HEIGHT / BattleCITYbygdx.PPM,gamecamera);   //////type of view may be fix it later (3)
        hud = new HUD(game.batch);

        maploader = new TmxMapLoader();
        map = maploader.load("Stage-1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/ BattleCITYbygdx.PPM);


        gamecamera.position.set(gamePort.getScreenWidth()+1.6f,gamePort.getScreenHeight()+1.8f,0);

        world = new World(new Vector2(0, 0), true);
        b2dr =  new Box2DDebugRenderer();



        new B2WorldCreator(world, map);

        player = new Tank(world, this);

        world.setContactListener(new WorldContacListener());
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }


    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && player.b2body.getLinearVelocity().y<=1){
            player.b2body.applyLinearImpulse(new Vector2(0,0.01f), player.b2body.getWorldCenter(), true);
            if(directiony >= 3){
            }else {
                directiony += 1.5f;
            }
            if(directionx !=0 ){
                directionx = 0;
            }

        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <=1){
            player.b2body.applyLinearImpulse(new Vector2(0.01f,0), player.b2body.getWorldCenter(), true);
            if(directionx >= 3){
            }else {
                directionx += 1.5f;
            }
            if(directiony !=0 ){
                directiony = 0;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >=-1){
            player.b2body.applyLinearImpulse(new Vector2(-0.01f,0), player.b2body.getWorldCenter(), true);
            if(directionx <= -3){
            }else {
                directionx += -1.5f;
            }
            if(directiony !=0 ){
                directiony = 0;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && player.b2body.getLinearVelocity().y >=-1){
            player.b2body.applyLinearImpulse(new Vector2(0,-0.01f), player.b2body.getWorldCenter(), true);
            if(directiony <= -3){
            }else {
                directiony += -1.5f;
            }
            if(directionx !=0 ){
                directionx = 0;
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.S)){  //shot bullet

                b3 = new Bullet(world,player,directionx);
             //   b3.b2body.applyLinearImpulse(new Vector2(directionx,directiony).add(player.b2body.getLinearVelocity().setLength(0.5f)), player.b2body.getWorldCenter(), true);
                b3.b2body.applyLinearImpulse(new Vector2(player.b2body.getLinearVelocity().x,player.b2body.getLinearVelocity().y).setLength(2f), player.b2body.getWorldCenter(), true);
        }
    }

    public void update(float dt){
        handleInput(dt);

        world.step(1/60f, 6,2);

        player.update(dt);

        gamecamera.update();
        renderer.setView(gamecamera);

        hud.update(dt);
    }

    @Override
    public void render(float delta){
        update(delta);

        Gdx.gl.glClearColor(50f,0f,10f,10f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        //box2ddebug
        b2dr.render(world, gamecamera.combined);

        game.batch.setProjectionMatrix(gamecamera.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.Stage.getCamera().combined);
        hud.Stage.draw();



    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
    }

    public TiledMap getMap(){

        return map;
    }

    public World getWorld(){
        return world;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.setDisabled(true);
    }
}
