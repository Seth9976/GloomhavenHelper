package com.esotericsoftware.gloomhavenhelper.model;

import com.esotericsoftware.gloomhavenhelper.Edition;

public class MonsterData {
    public int count;
    public int deckID;
    public String display;
    public Edition edition;
    public String english;
    public boolean flying;
    public boolean hidden;
    public int id;
    public String name;
    private static int nextID = -1;
    public final MonsterStats[][] stats;

    static {
    }

    public MonsterData() {
        int v = MonsterData.nextID;
        MonsterData.nextID = v - 1;
        this.id = v;
        this.deckID = -1;
        this.stats = new MonsterStats[3][8];
        for(int v1 = 0; v1 < 3; ++v1) {
            this.stats[v1] = new MonsterStats[8];
        }
    }

    public boolean isBoss() {
        return this.stats[MonsterType.boss.ordinal()][0] != null;
    }

    public static void reset() {
        MonsterData.nextID = -1;
    }
}

