package com.esotericsoftware.gloomhavenhelper.model;

public enum PlayerInit {
    dragOrder,
    dragNumber,
    dragNumberRequired,
    numpad;

    public static final PlayerInit[] values;

    static {
        PlayerInit.values = (PlayerInit[])PlayerInit.$VALUES.clone();
    }
}

