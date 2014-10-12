package com.garbagecollector.garbage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.garbagecollector.screens.game.GarbageCollectorActor;

/**
 * Created by dmytroplekhotkin on 10/12/14.
 */
public class GarbageActor extends Actor{

    public static final int SPEED = 200;
    public static final int HEAP_SPEED = 20;
    TextureRegion texture;
    private final GarbageType type;


    public GarbageActor() {
        type = GarbageType.randomGarbage();
        texture = new TextureRegion(new Texture(Gdx.files.internal(GarbageType.randomImageByType(type))));
        setSize(texture.getRegionWidth()*Gdx.graphics.getDensity(), texture.getRegionHeight()*Gdx.graphics.getDensity());
    }

    public GarbageType getGarbageType() {
        return type;
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
            Group root = getStage().getRoot();
            Actor actor = root.findActor("garbageHeap");
            actor.addAction(Actions.moveBy(0, HEAP_SPEED,1));

            remove();
            System.out.println("Removing garbage: " + this);

        } else if(detectCollision()) {
            remove();
        } else moveDown();
    }

    private boolean detectCollision() {
        GarbageCollectorActor bucket = getStage().getRoot().findActor(GarbageCollectorActor.NAME);
        boolean inBucketX =  !(getX() > bucket.getRight()  ||  getRight() < bucket.getX());
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
