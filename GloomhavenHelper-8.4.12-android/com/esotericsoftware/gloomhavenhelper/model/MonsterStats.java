package com.esotericsoftware.gloomhavenhelper.model;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.OrderedSet;
import com.esotericsoftware.gloomhavenhelper.App;

public class MonsterStats {
    public String attack;
    public final Array attributeLines;
    public final Array attributeText;
    public String hpMax;
    public boolean immuneCurse;
    public boolean immunePull;
    public boolean immunePush;
    public final OrderedSet immunities;
    public String move;
    public Line notes;
    public Line notesCalculated;
    public String notesText;
    public String range;
    public final Array special1;
    public final Array special2;
    public final Array specialCalculated1;
    public final Array specialCalculated2;
    public final Array specialText1;
    public final Array specialText2;

    public MonsterStats() {
        this.attributeLines = new Array();
        this.attributeText = new Array();
        this.immunities = new OrderedSet();
        this.specialText1 = new Array();
        this.specialText2 = new Array();
        this.special1 = new Array();
        this.special2 = new Array();
        this.specialCalculated1 = new Array();
        this.specialCalculated2 = new Array();
    }

    public int attack() {
        if(this.attack.equals("C")) {
            return App.gloom.playerCount();
        }
        return this.attack.endsWith("+C") ? App.parseInt(this.attack.substring(0, this.attack.length() - 2)) + App.gloom.playerCount() : App.parseInt(this.attack);
    }

    public int hpMax() {
        if(this.hpMax.endsWith("xC")) {
            int v = App.parseInt(this.hpMax.substring(0, this.hpMax.length() - 2));
            int v1 = App.gloom.playerCount();
            return v1 == 0 ? v : v1 * v;
        }
        if(this.hpMax.endsWith("xC/2")) {
            int v2 = App.parseInt(this.hpMax.substring(0, this.hpMax.length() - 4));
            int v3 = App.gloom.playerCount();
            return v3 == 0 ? v2 : ((int)Math.ceil(((float)(v3 * v2)) / 2.0f));
        }
        return App.parseInt(this.hpMax);
    }
}

