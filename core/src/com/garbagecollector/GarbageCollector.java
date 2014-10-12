package com.garbagecollector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * @author Alexander Semenov
 */
public class GarbageCollector extends Actor {

    public static final float ACCELERATION_MULTIPLIER = 8f;
    private TextureRegion bucketImage;
    private GarbageType type;

    public GarbageCollector(GarbageType type) {
        this.type = type;
        Texture texture = new Texture(Gdx.files.internal("bucket_black.png"));
        bucketImage = new TextureRegion(texture);
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
        System.out.println("accelerometerX:  " + accelerometerDelta+", delta: "+delta);
        //calculating new position of bucket
        float posDelta = accelerometerDelta * Gdx.graphics.getDensity();
        float newPos = isPortrait?(getX() - posDelta): (getX() + posDelta);
        float maxPoint = getStage().getWidth() - getWidth();

        //preventing move of bucket outside screen
        if (newPos < 0) newPos = 0;
        else if (newPos > maxPoint) newPos = maxPoint;

        addAction(Actions.moveTo(newPos, 0, delta* ACCELERATION_MULTIPLIER, Interpolation.bounce));

        super.act(delta);
    }
}
