package com.garbagecollector.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.garbagecollector.EcoGarbageCollector;
import com.garbagecollector.GarbageCollector;
import com.garbagecollector.GarbageType;

/**
 * @author Alexander Semenov
 */
public class GameScreen implements Screen {

    final EcoGarbageCollector game;

    private BackgroundActor backgroundActor;

    private Stage stage;
    private GarbageCollector collector;

    public GameScreen(final EcoGarbageCollector game) {
        this.game = game;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        initBackground();
        initBucket();
    }

    private void initBackground() {
        //adding background
        backgroundActor = new BackgroundActor();
        stage.addActor(backgroundActor);
    }

    private void initBucket() {
        //bucket
        collector = new GarbageCollector(GarbageType.PAPER);
        float density = Gdx.graphics.getDensity();
        collector.setSize(50f * density, 50f * density);
        collector.setX(Gdx.graphics.getWidth() / 2 - collector.getWidth() / 2);

        stage.addActor(collector);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
