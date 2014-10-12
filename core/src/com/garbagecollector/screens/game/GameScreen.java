package com.garbagecollector.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.garbagecollector.EcoGarbageCollector;
import com.garbagecollector.GameState;
import com.garbagecollector.garbage.GarbageActor;
import com.garbagecollector.garbage.GarbageType;


/**
 * @author Alexander Semenov
 */
public class GameScreen implements Screen {

    public static final int SPAWN_RATE = 3000;
    final EcoGarbageCollector game;
    final GameState state = new GameState();

    private BackgroundActor backgroundActor;
    private GarbageHeapActor garbageHeapActor;

    private GameStage stage;
    private GarbageCollectorActor collector;
    private long lastDropTime;
    private Label scoreLabel;

    public GameScreen(final EcoGarbageCollector game) {
        this.game = game;

        stage = new GameStage(new ScreenViewport(), this);
        Gdx.input.setInputProcessor(stage);
        initBackground();
        initGarbageHeap();
        initBucket();


        initScoreText();
    }

    public GameState getState() {
        return state;
    }

    private void initScoreText() {
//        WidgetGroup textGroup = new WidgetGroup();
//        textGroup.setSize(20, 100);
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();
        scoreLabel = new Label("Score: 0", style);
        scoreLabel.setFontScale(3f);
        scoreLabel.setScale(3f);
        stage.addCaptureListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                System.out.println("Handle:  "+event);
                if (event instanceof ScoreUpdatedEvent){
                    scoreLabel.setText("Score: "+state.getScoreCount());
                    return true;
                }
                return false;
            }
        });
        scoreLabel.setPosition(20, stage.getHeight()-scoreLabel.getHeight()*scoreLabel.getScaleY());
        stage.addActor(scoreLabel);
    }


    private void initBackground() {
        //adding background
        backgroundActor = new BackgroundActor();
        stage.addActor(backgroundActor);
    }

    private void initGarbageHeap() {
        garbageHeapActor = new GarbageHeapActor();
        stage.addActor(garbageHeapActor);
    }

    private void initBucket() {
        //bucket
        collector = new GarbageCollectorActor(GarbageType.PAPER);
        float density = Gdx.graphics.getDensity();
        collector.setSize(50f * density, 50f * density);
        collector.setX(Gdx.graphics.getWidth() / 2 - collector.getWidth() / 2);

        stage.addActor(collector);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(TimeUtils.millis() - lastDropTime > SPAWN_RATE) {
            GarbageActor actor = new GarbageActor();
            stage.addActor(actor);
            actor.setPosition(MathUtils.random(0, stage.getWidth() - actor.getWidth()), stage.getHeight());
            lastDropTime = TimeUtils.millis();
            //moving collector to front
            stage.getRoot().swapActor(actor, collector);
        }


        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        backgroundActor.setSize(width,height);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        game.dispose();
    }
}
