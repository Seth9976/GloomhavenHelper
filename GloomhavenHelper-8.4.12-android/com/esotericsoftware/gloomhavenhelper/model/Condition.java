package com.esotericsoftware.gloomhavenhelper.model;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public enum Condition {
    star,
    summonNew,
    summon,
    stun,
    immobilize,
    disarm,
    wound,
    muddle,
    poison,
    strengthen,
    invisible,
    regenerate,
    doom,
    hatchet;

    public transient Drawable drawable;
    public transient Drawable drawableMedium;
    public static Condition[] values;

    static {
        Condition.values = (Condition[])Condition.$VALUES.clone();
    }
}

