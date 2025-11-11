package com.esotericsoftware.gloomhavenhelper.model;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.gloomhavenhelper.Edition;

public class Scenario {
    public Edition edition;
    public final Array monsters;
    public String name;
    public final Array special;

    public Scenario() {
        this.monsters = new Array();
        this.special = new Array();
    }
}

