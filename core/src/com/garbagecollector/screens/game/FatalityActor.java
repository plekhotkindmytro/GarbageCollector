package com.garbagecollector.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;

/**
 * Created by dmytroplekhotkin on 10/12/14.
 */
public class FatalityActor extends Actor{
    public static final int CITY_COUNT = 7;
    TextureRegion texture;


    public FatalityActor() {
        texture = new TextureRegion(new Texture(Gdx.files.internal("fatality.png")));
        float scaleXY = getScale();
        setPosition(-getWidth()*scaleXY, -getHeight()*scaleXY);
    }

    private float getScale() {
        int screenWidth = Gdx.graphics.getWidth();
        return (screenWidth/getWidth());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
