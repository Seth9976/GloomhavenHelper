package com.badlogic.gdx.graphics;

import com.badlogic.gdx.utils.ObjectMap;

public final class Colors {
    private static final ObjectMap map;

    static {
        Colors.map = new ObjectMap();
        Colors.reset();
    }

    public static Color get(String s) {
        return (Color)Colors.map.get(s);
    }

    public static ObjectMap getColors() {
        return Colors.map;
    }

    public static Color put(String s, Color color0) {
        return (Color)Colors.map.put(s, color0);
    }

    public static void reset() {
        Colors.map.clear();
        Colors.map.put("CLEAR", Color.CLEAR);
        Colors.map.put("BLACK", Color.BLACK);
        Colors.map.put("WHITE", Color.WHITE);
        Colors.map.put("LIGHT_GRAY", Color.LIGHT_GRAY);
        Colors.map.put("GRAY", Color.GRAY);
        Colors.map.put("DARK_GRAY", Color.DARK_GRAY);
        Colors.map.put("BLUE", Color.BLUE);
        Colors.map.put("NAVY", Color.NAVY);
        Colors.map.put("ROYAL", Color.ROYAL);
        Colors.map.put("SLATE", Color.SLATE);
        Colors.map.put("SKY", Color.SKY);
        Colors.map.put("CYAN", Color.CYAN);
        Colors.map.put("TEAL", Color.TEAL);
        Colors.map.put("GREEN", Color.GREEN);
        Colors.map.put("CHARTREUSE", Color.CHARTREUSE);
        Colors.map.put("LIME", Color.LIME);
        Colors.map.put("FOREST", Color.FOREST);
        Colors.map.put("OLIVE", Color.OLIVE);
        Colors.map.put("YELLOW", Color.YELLOW);
        Colors.map.put("GOLD", Color.GOLD);
        Colors.map.put("GOLDENROD", Color.GOLDENROD);
        Colors.map.put("ORANGE", Color.ORANGE);
        Colors.map.put("BROWN", Color.BROWN);
        Colors.map.put("TAN", Color.TAN);
        Colors.map.put("FIREBRICK", Color.FIREBRICK);
        Colors.map.put("RED", Color.RED);
        Colors.map.put("SCARLET", Color.SCARLET);
        Colors.map.put("CORAL", Color.CORAL);
        Colors.map.put("SALMON", Color.SALMON);
        Colors.map.put("PINK", Color.PINK);
        Colors.map.put("MAGENTA", Color.MAGENTA);
        Colors.map.put("PURPLE", Color.PURPLE);
        Colors.map.put("VIOLET", Color.VIOLET);
        Colors.map.put("MAROON", Color.MAROON);
    }
}

