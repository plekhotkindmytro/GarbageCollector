package com.garbagecollector.screens.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * @author Alexander Semenov
 */
public class GameStage extends Stage {

    GameScreen gameScreen;

    public GameStage(Viewport viewport, GameScreen gameScreen ) {
        super(viewport);
        this.gameScreen = gameScreen;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }
}
