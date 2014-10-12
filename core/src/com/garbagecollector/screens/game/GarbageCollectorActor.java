package com.garbagecollector.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.garbagecollector.garbage.GarbageActor;
import com.garbagecollector.garbage.GarbageType;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * @author Alexander Semenov
 */
public class GarbageCollectorActor extends Actor {

    public static final float ACCELERATION_MULTIPLIER = 4f;
    private static final float ACCELERATION_DURATION_MULTIPLIER = 4f;
    /**
     * Name needed to find bucket in stage
     */
    public static final String NAME = "collector";
    private TextureRegion bucketImage;
    private GarbageType type;

    public GarbageCollectorActor(GarbageType type) {
        this.type = type;
        Texture texture = new Texture(Gdx.files.internal("bucket_yellow.png"));
        bucketImage = new TextureRegion(texture);
        setName(NAME);
    }

    public GarbageType getType() {
        return type;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(bucketImage, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    @Override
    public void act(float delta) {
        boolean isPortrait = Gdx.graphics.getHeight() > Gdx.graphics.getWidth();
        float accelerometerDelta = isPortrait ? Gdx.input.getAccelerometerX(): Gdx.input.getAccelerometerY();
//        System.out.println("accelerometerX:  " + accelerometerDelta+", delta: "+delta);
        //calculating new position of bucket
        float posDelta = accelerometerDelta *ACCELERATION_MULTIPLIER* Gdx.graphics.getDensity();
        float newPos = isPortrait?(getX() - posDelta): (getX() + posDelta);
        float maxPoint = getStage().getWidth() - getWidth();

        //preventing move of bucket outside screen
        if (newPos < 0) newPos = 0;
        else if (newPos > maxPoint) newPos = maxPoint;

        addAction(Actions.moveTo(newPos, 0, delta* ACCELERATION_DURATION_MULTIPLIER, Interpolation.bounce));

        super.act(delta);
    }

    public void onCatchGarbage(GarbageActor actor){

    }
}
