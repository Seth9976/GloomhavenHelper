package com.esotericsoftware.gloomhavenhelper.model;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.gloomhavenhelper.Edition;

public class MonsterAbilityDeck {
    public final Array abilities;
    public final Array abilitiesDiscard;
    public transient Edition edition;
    public final transient int id;
    public transient String name;
    private static int nextID;
    public MonsterAbility shownAbility;
    public boolean shuffle;

    public MonsterAbilityDeck() {
        this.abilities = new Array();
        this.abilitiesDiscard = new Array();
        int v = MonsterAbilityDeck.nextID;
        MonsterAbilityDeck.nextID = v + 1;
        this.id = v;
    }

    public MonsterAbilityDeck(MonsterAbilityDeck monsterAbilityDeck0) {
        this.abilities = new Array();
        this.abilitiesDiscard = new Array();
        this.id = monsterAbilityDeck0.id;
        this.name = monsterAbilityDeck0.name;
        this.edition = monsterAbilityDeck0.edition;
        this.shuffle = monsterAbilityDeck0.shuffle;
        this.shownAbility = monsterAbilityDeck0.shownAbility;
        this.abilities.addAll(monsterAbilityDeck0.abilities);
    }

    public static void reset() {
        MonsterAbilityDeck.nextID = 0;
    }
}

