package com.garbagecollector.garbage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.TimeUtils;
import com.garbagecollector.GarbageCollector;

import java.util.Random;

/**
 * Created by dmytroplekhotkin on 10/12/14.
 */
public class GarbageActor extends Actor{

    public static final int SPEED = 200;
    TextureRegion texture;
//    final GarbageType type;

    public GarbageActor() {


//        type = GarbageType.randomGarbage();
        texture = new TextureRegion(new Texture(Gdx.files.internal(GarbageType.randomGarbage())));
        setSize(texture.getRegionWidth()*Gdx.graphics.getDensity(), texture.getRegionHeight()*Gdx.graphics.getDensity());
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
        if (detectFall()){
            remove();
            System.out.println("Removing garbage: " + this);
        } else if(detectCollision()) {
            remove();
        } else moveDown();
    }

    private boolean detectCollision() {
        GarbageCollector bucket = getStage().getRoot().findActor(GarbageCollector.NAME);
        boolean inBucketX =  getX() > bucket.getX()  &&  getRight() < bucket.getRight();
        if (bucket.getTop() >= getY() && inBucketX){
            System.out.println("collision!");
            return true;
        }
        return false;
    }

    private void moveDown() {
        addAction(Actions.moveBy(0, -getStage().getHeight() / SPEED));
    }

    private boolean detectFall() {
        return getY() + getHeight() < 0;
    }

}
