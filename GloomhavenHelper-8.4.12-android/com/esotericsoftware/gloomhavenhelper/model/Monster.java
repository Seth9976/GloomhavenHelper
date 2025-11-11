package com.esotericsoftware.gloomhavenhelper.model;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.gloomhavenhelper.App;

public class Monster {
    public final Array conditions;
    public final Array currentTurnConditions;
    public transient MonsterData data;
    public final Array expiredConditions;
    public int hp;
    public int hpMax;
    public boolean isNew;
    public int number;
    public transient MonsterStats stats;
    public int summonAttack;
    public SummonColor summonColor;
    public int summonMove;
    public int summonRange;
    public MonsterType type;

    public Monster() {
        this.summonColor = SummonColor.blue;
        this.isNew = true;
        this.conditions = new Array();
        this.expiredConditions = new Array();
        this.currentTurnConditions = new Array();
    }

    public Monster(MonsterData monsterData0, MonsterType monsterType0, int v, int v1) {
        this.summonColor = SummonColor.blue;
        this.isNew = true;
        this.conditions = new Array();
        this.expiredConditions = new Array();
        this.currentTurnConditions = new Array();
        this.data = monsterData0;
        this.type = monsterType0;
        this.number = v1;
        if(monsterData0 != App.summonData) {
            MonsterStats[] arr_monsterStats = monsterData0.stats[monsterType0.ordinal()];
            if(v >= arr_monsterStats.length) {
                v = arr_monsterStats.length - 1;
            }
            this.stats = arr_monsterStats[v];
            MonsterStats monsterStats0 = this.stats;
            if(monsterStats0 == null) {
                throw new IllegalArgumentException("No stats available: " + monsterData0.name + ", " + monsterType0 + ", " + v);
            }
            int v2 = monsterStats0.hpMax();
            this.hpMax = v2;
            this.hp = v2;
            return;
        }
        this.stats = App.summonStats;
        this.hpMax = v * 2;
        this.hp = v * 2;
    }
}

