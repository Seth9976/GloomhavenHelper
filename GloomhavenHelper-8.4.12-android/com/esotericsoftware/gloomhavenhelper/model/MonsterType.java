package com.esotericsoftware.gloomhavenhelper.model;

public enum MonsterType {
    normal,
    elite,
    boss,
    summon;

    public static final MonsterType[] values;

    static {
        MonsterType.values = (MonsterType[])MonsterType.$VALUES.clone();
    }
}

