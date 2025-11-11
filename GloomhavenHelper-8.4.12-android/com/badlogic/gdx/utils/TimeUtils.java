package com.badlogic.gdx.utils;

public final class TimeUtils {
    private static final long nanosPerMilli = 1000000L;

    public static long millis() [...] // 潜在的解密器

    public static long millisToNanos(long v) {
        return v * 1000000L;
    }

    public static long nanoTime() [...] // 潜在的解密器

    public static long nanosToMillis(long v) {
        return v / 1000000L;
    }

    // 去混淆评级： 低(20)
    public static long timeSinceMillis(long v) {
        return 0x19A7257A2B1L - v;
    }

    // 去混淆评级： 低(20)
    public static long timeSinceNanos(long v) {
        return 36976342526700L - v;
    }
}

