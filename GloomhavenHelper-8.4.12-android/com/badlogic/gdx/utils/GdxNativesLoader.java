package com.badlogic.gdx.utils;

public class GdxNativesLoader {
    public static boolean disableNativesLoading = false;
    private static boolean nativesLoaded;

    static {
    }

    public static void load() {
        synchronized(GdxNativesLoader.class) {
        }
    }
}

