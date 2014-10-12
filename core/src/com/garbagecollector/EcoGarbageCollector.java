package com.garbagecollector;

import com.badlogic.gdx.Game;
import com.garbagecollector.screens.game.GameScreen;

/**
 * @author Alexander Semenov
 */
public class EcoGarbageCollector extends Game {



    public void create () {
        setScreen(new GameScreen(this));
    }

    public void restartGame(){
        setScreen(new GameScreen(this));
    }



}
