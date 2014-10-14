package com.garbagecollector.screens.game.garbage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Alexander Semenov
 */
public enum GarbageType {
    PAPER("p1.png", "p2.png","p3.png", "p4.png"),
    PLASTICS("pl1.png", "pl2.png","pl3.png"),
    CAT("cat1.png","cat2.png","cat3.png","cat4.png"),
    OTHER("o1.png","o2.png","o3.png"),
    DANGER("d1.png");

    private final String[] images;
    private GarbageType(final String... images) {
        this.images = images;
    }
    private static final List<GarbageType> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static GarbageType randomGarbage()  {
        final GarbageType garbageType = VALUES.get(RANDOM.nextInt(SIZE));
        return garbageType;
    }

    public static String randomImageByType(GarbageType garbageType)  {
        final int imageIndex = RANDOM.nextInt(garbageType.images.length);
        return garbageType.images[imageIndex];
    }
}
