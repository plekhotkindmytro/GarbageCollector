package com.garbagecollector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.garbagecollector.garbage.GarbageType;

/**
 * @author Alexander Semenov
 */
public class GarbageCollector extends Actor {

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
        super.act(delta);
        boolean isPortrait = Gdx.graphics.getHeight() > Gdx.graphics.getWidth();
        float accelerometerDelta = isPortrait ? Gdx.input.getAccelerometerX(): Gdx.input.getAccelerometerY();
        System.out.println("accelerometerX:  " + accelerometerDelta+", isPortrait "+isPortrait);
        //calculating new position of bucket
        float posDelta = accelerometerDelta * 6f * Gdx.graphics.getDensity();
        float newPos = isPortrait?(getX() - posDelta): (getX() + posDelta);
        float maxPoint = getStage().getWidth() - getWidth();

        //preventing move of bucket outside screen
        if (newPos < 0) setX(0);
        else if (newPos > maxPoint) setX(maxPoint);
        else setX(newPos);
    }
}
