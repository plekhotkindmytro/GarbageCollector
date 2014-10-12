package com.garbagecollector.garbage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Alexander Semenov
 */
public enum GarbageType {
    PAPER("paper1.png", "paper2.png","paper3.png", "paper4.png"),
    PLASTICS("plastics1.png", "plastics2.png","plastics3.png", "plastics4.png", "plastics5.png"),
    GLASS("glass1.png"),
    DANGER("danger1.png","danger2.png");

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
