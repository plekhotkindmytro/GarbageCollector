package com.garbagecollector.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * @author Alexander Semenov
 */
public class GarbageHeapActor extends Actor {

    TextureRegion texture;

    private int position;
    public GarbageHeapActor() {
        texture = new TextureRegion(new Texture(Gdx.files.internal("garbage.png")));
        setSize(texture.getRegionWidth(), texture.getRegionHeight());
        setPosition(0, -texture.getRegionHeight());
        setName("garbageHeap");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
