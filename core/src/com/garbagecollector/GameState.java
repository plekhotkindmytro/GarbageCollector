package com.garbagecollector;

/**
 * @author Alexander Semenov
 */
public class GameState {

    private int dropCount;
    private int scoreCount;

    public int getDropCount() {
        return dropCount;
    }

    public int getScoreCount() {
        return scoreCount;
    }

    public void incScore(){
        scoreCount++;
    }

    public void incDrop(){
        dropCount++;
    }

    public void resetGame(){
        dropCount = 0;
        scoreCount = 0;
    }
}
