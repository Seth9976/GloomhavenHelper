package com.esotericsoftware.gloomhavenhelper.model;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.gloomhavenhelper.util.Card;

public class MonsterAbility implements Card {
    public transient MonsterAbilityDeck deck;
    public int id;
    public int initiative;
    public String initiativeString;
    public final Array lines;
    private static int nextID;
    public String number;
    public boolean shuffle;
    public final Array text;

    public MonsterAbility() {
        int v = MonsterAbility.nextID;
        MonsterAbility.nextID = v + 1;
        this.id = v;
        this.text = new Array();
        this.lines = new Array();
    }

    public static void reset() {
        MonsterAbility.nextID = 0;
    }
}

