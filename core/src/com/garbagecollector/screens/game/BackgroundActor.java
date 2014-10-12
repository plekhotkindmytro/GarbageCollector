package com.garbagecollector.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;

/**
 * @author Alexander Semenov
 */
public class BackgroundActor extends Actor {

    public static final int CITY_COUNT = 7;
    TextureRegion texture;

    private static final Random random = new Random();
    public BackgroundActor() {
        texture = new TextureRegion(new Texture(Gdx.files.internal("city3.jpg")));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
