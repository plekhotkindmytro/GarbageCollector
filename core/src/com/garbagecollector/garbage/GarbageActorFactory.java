package com.garbagecollector.garbage;

import java.util.Random;

/**
 * Created by dmytroplekhotkin on 10/12/14.
 */
public class GarbageActorFactory {

    private static final Random random = new Random();

    public static GarbageActor createRandomGarbage() {
        final String image = GarbageType.randomGarbage();
        return new GarbageActor();
    }
}
