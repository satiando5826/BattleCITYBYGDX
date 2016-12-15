package com.mygame.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.game.Screen.PlayScreen;

public class BattleCITYbygdx extends Game {
	public static	final  int V_WIDTH = 400;
	public static	final  int V_HEIGHT = 470;
	public static	final  float PPM = 100;

	public static final short DEFAULT_BIT = 1;
	public static final short tank_BIT = 2;
	public static final short bullet_BIT = 4;
	public static final short brick_BIT = 8;
	public static final short metal_BIT = 16;
	public static final short DESTROYED_BIT = 32;
	public static final short OBJECT_BIT = 64;
	public static final short enemy_BIT = 128;
	public static final short forest_BIT= 256;
	public static final short water_BIT = 512;
	public static final short base_BIT = 1024;
	public static final short frame_BIT = 2048;
	public static final short enemy_body_BIT = 4096;
		public String mapstring = "Stage-1.tmx";


	public SpriteBatch batch;

	public static AssetManager manager;


	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("audio/music/TouhouV2.ogg", Music.class);
		manager.load("audio/music/FFXV.ogg", Music.class);
		manager.load("audio/music/Bandicoot1.ogg", Music.class);
		manager.load("audio/music/Bandicoot3.ogg", Music.class);
		manager.load("audio/music/MetalSlug.ogg", Music.class);
		manager.load("audio/music/Mistery.ogg", Music.class);
		manager.load("audio/sound/TankfireV2.wav", Sound.class);
		manager.finishLoading();

		setScreen(new PlayScreen(this,mapstring));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
