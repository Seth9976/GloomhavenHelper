package com.esotericsoftware.gloomhavenhelper.model;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.gloomhavenhelper.PlayerRow;

public class Player {
    public CharacterClass characterClass;
    public final Array conditions;
    public final Array currentTurnConditions;
    public boolean exhausted;
    public final Array expiredConditions;
    public int hp;
    public int hpMax;
    public int init;
    public transient String initString;
    public int level;
    public int loot;
    public String name;
    public int xp;

    public Player() {
        this.conditions = new Array();
        this.expiredConditions = new Array();
        this.currentTurnConditions = new Array();
    }

    public void init(int v) {
        this.init = v;
        this.initString = Integer.toString(v);
        if(v == 0) {
            PlayerRow.localInit.remove(this.characterClass);
        }
    }
}

