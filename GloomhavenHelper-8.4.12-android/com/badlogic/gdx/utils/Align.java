package com.badlogic.gdx.utils;

public class Align {
    public static final int bottom = 4;
    public static final int bottomLeft = 12;
    public static final int bottomRight = 20;
    public static final int center = 1;
    public static final int left = 8;
    public static final int right = 16;
    public static final int top = 2;
    public static final int topLeft = 10;
    public static final int topRight = 18;

    public static final boolean isBottom(int v) {
        return (v & 4) != 0;
    }

    public static final boolean isCenterHorizontal(int v) {
        return (v & 8) == 0 && (v & 16) == 0;
    }

    public static final boolean isCenterVertical(int v) {
        return (v & 2) == 0 && (v & 4) == 0;
    }

    public static final boolean isLeft(int v) {
        return (v & 8) != 0;
    }

    public static final boolean isRight(int v) {
        return (v & 16) != 0;
    }

    public static final boolean isTop(int v) {
        return (v & 2) != 0;
    }

    public static String toString(int v) {
        StringBuilder stringBuilder0 = new StringBuilder(13);
        if((v & 2) != 0) {
            stringBuilder0.append("top,");
        }
        else if((v & 4) == 0) {
            stringBuilder0.append("center,");
        }
        else {
            stringBuilder0.append("bottom,");
        }
        if((v & 8) != 0) {
            stringBuilder0.append("left");
            return "";
        }
        if((v & 16) != 0) {
            stringBuilder0.append("right");
            return "";
        }
        stringBuilder0.append("center");
        return "";
    }
}

