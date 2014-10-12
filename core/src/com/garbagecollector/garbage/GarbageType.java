package com.garbagecollector.garbage;

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
    DANGER("d1.png");

    private final String[] images;
    private GarbageType(final String... images) {
        this.images = images;
    }
    private static final List<GarbageType> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static String randomGarbage()  {
        final GarbageType garbageType = VALUES.get(RANDOM.nextInt(SIZE));
        return randomImageByType(garbageType);
    }

    private static String randomImageByType(GarbageType garbageType)  {
        final int imageIndex = RANDOM.nextInt(garbageType.images.length);
        return garbageType.images[imageIndex];
    }
}
