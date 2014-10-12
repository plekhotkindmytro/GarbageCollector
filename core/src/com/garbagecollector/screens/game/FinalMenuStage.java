package com.garbagecollector.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Ui Stage for final screen
 *
 * @author Alexander Semenov
 */
public class FinalMenuStage extends Stage {

    GameScreen gameScreen;
    private Label scoreLabel;

    public FinalMenuStage(Viewport viewport, GameScreen gameScreen ) {
        super(viewport);
        this.gameScreen = gameScreen;
        init();
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    /**
     * inits stage actors
     */
    public void init(){
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();
        scoreLabel = new Label("City drown in garbage :) ", style);
        scoreLabel.setWidth(getWidth() - getWidth() / 10);
        scoreLabel.setFontScale(2f * Gdx.graphics.getDensity());
        scoreLabel.setScale(2f * Gdx.graphics.getDensity());
        scoreLabel.setWrap(true);
        scoreLabel.setAlignment(Align.center);
        scoreLabel.setColor(1, 0, 0, 1);
        scoreLabel.setX(getWidth() / 2 - scoreLabel.getWidth() / 2);
        scoreLabel.setY(getHeight() / 2 - scoreLabel.getHeight() / 2);
        addActor(scoreLabel);

        initTryAgain();

    }

    private void initTryAgain() {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = new BitmapFont();
        style.font.setScale(2f * Gdx.graphics.getDensity());
        TextButton tryAgain = new TextButton("TRY AGAIN", style);
        tryAgain.setX(getWidth() / 2 - tryAgain.getWidth()/2);
        tryAgain.setY(scoreLabel.getY() - tryAgain.getHeight()*Gdx.graphics.getDensity() - 50f*Gdx.graphics.getDensity() );

        tryAgain.setScale(2f * Gdx.graphics.getDensity());

        tryAgain.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("button clicked");
                getGameScreen().restart();
            }
        });

        addActor(tryAgain);

    }
}
