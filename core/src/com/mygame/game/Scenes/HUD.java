package com.mygame.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygame.game.BattleCITYbygdx;
import javafx.stage.Stage;

/**
 * Created by Sathianporn khamdee on 1/12/2559.
 */
public class HUD implements Disableable{
    public com.badlogic.gdx.scenes.scene2d.Stage Stage;
    private Viewport viewport;

    private  Integer Time;
    private float Count;
    private static Integer score;

    private static Label scoreLabel;
    private Label countdowLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label tankLabel;

    public HUD(SpriteBatch sb){
        Time = 120;
        Count = 0;
        score = 0;

        viewport = new FitViewport(BattleCITYbygdx.V_WIDTH,BattleCITYbygdx.V_HEIGHT, new OrthographicCamera());
        Stage = new com.badlogic.gdx.scenes.scene2d.Stage(viewport,sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countdowLabel = new Label(String.format("%03d",Time), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d",score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        tankLabel = new Label("TANK", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(tankLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdowLabel).expandX();

        Stage.addActor(table);
    }

    @Override
    public void setDisabled(boolean isDisabled) {
        Stage.dispose();
    }

    public  void update(float dt){
        Count += dt;
        if(Count >= 1){
            Time--;
            countdowLabel.setText(String.format("%03d",Time));
            Count = 0;
        }
    }

    public static void addScore(int value){
        score += value;
        scoreLabel.setText(String.format("%06d",score));
    }

    @Override
    public boolean isDisabled() {
        Stage.dispose();
        return false;
    }
}
