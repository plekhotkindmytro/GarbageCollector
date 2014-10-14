package com.garbagecollector.screens.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * @author Alexander Semenov
 */
public class GarbageHeapActor extends Actor {

    //limit to overflow garbage, should be >0 and <1
    public static final float OVERFLOW_LIMIT = 0.5f; // 50%
    TextureRegion texture;

    private int position;
    public GarbageHeapActor() {
        texture = new TextureRegion(new Texture(Gdx.files.internal("garbage.png")));
        //TODO get width from stage, not from screen
        setSize(texture.getRegionHeight()*Gdx.graphics.getDensity(), texture.getRegionHeight()*Gdx.graphics.getDensity());
        System.out.println("Heap width: " +getWidth()+", screenWidth "+ Gdx.graphics.getWidth());
        float scaleXY = getScale();
        setScale(scaleXY);
        System.out.println("scale: "+ scaleXY);

        setPosition(0, -getHeight()*scaleXY);
        setName("garbageHeap");
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
        if (detectOverflow()){
            getStage().getRoot().fire(new GameFinishEvent());
        }

    }

    private boolean detectOverflow() {
        return getTop()*getScaleY() > getStage().getHeight() * OVERFLOW_LIMIT;
    }
}
