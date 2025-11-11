package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.Gdx;

public final class UIUtils {
    public static boolean isAndroid;
    public static boolean isIos;
    public static boolean isLinux;
    public static boolean isMac;
    public static boolean isWindows;

    // 去混淆评级： 低(36)
    static {
        UIUtils.isAndroid = true;
        UIUtils.isMac = false;
        UIUtils.isWindows = false;
        UIUtils.isLinux = false;
        UIUtils.isIos = false;
    }

    // 去混淆评级： 低(20)
    public static boolean alt() {
        return Gdx.input.isKeyPressed(57) || Gdx.input.isKeyPressed(58);
    }

    public static boolean alt(int v) {
        return v == 57 || v == 58;
    }

    // 去混淆评级： 低(40)
    public static boolean ctrl() {
        return UIUtils.isMac ? Gdx.input.isKeyPressed(0x3F) : Gdx.input.isKeyPressed(0x81) || Gdx.input.isKeyPressed(130);
    }

    // 去混淆评级： 低(20)
    public static boolean ctrl(int v) {
        return UIUtils.isMac ? v == 0x3F : v == 0x81 || v == 130;
    }

    public static boolean left() {
        return Gdx.input.isButtonPressed(0);
    }

    public static boolean left(int v) {
        return v == 0;
    }

    public static boolean middle() {
        return Gdx.input.isButtonPressed(2);
    }

    public static boolean middle(int v) {
        return v == 2;
    }

    public static boolean right() {
        return Gdx.input.isButtonPressed(1);
    }

    public static boolean right(int v) {
        return v == 1;
    }

    // 去混淆评级： 低(20)
    public static boolean shift() {
        return Gdx.input.isKeyPressed(59) || Gdx.input.isKeyPressed(60);
    }

    public static boolean shift(int v) {
        return v == 59 || v == 60;
    }
}

