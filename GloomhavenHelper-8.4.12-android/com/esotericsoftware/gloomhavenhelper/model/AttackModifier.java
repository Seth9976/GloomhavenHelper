package com.esotericsoftware.gloomhavenhelper.model;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.gloomhavenhelper.util.Card;

public enum AttackModifier implements Card {
    plus0,
    plus1,
    plus2,
    minus1,
    minus2,
    nullAttack,
    doubleAttack,
    bless,
    curse;

    public static final Array baseAttackModifiers;
    public static final AttackModifier[] values;

    static {
        AttackModifier.baseAttackModifiers = new Array(new AttackModifier[]{AttackModifier.plus0, AttackModifier.plus0, AttackModifier.plus0, AttackModifier.plus0, AttackModifier.plus0, AttackModifier.plus0, AttackModifier.plus1, AttackModifier.plus1, AttackModifier.plus1, AttackModifier.plus1, AttackModifier.plus1, AttackModifier.minus1, AttackModifier.minus1, AttackModifier.minus1, AttackModifier.minus1, AttackModifier.minus1, AttackModifier.plus2, AttackModifier.minus2, AttackModifier.nullAttack, AttackModifier.doubleAttack});
        AttackModifier.values = (AttackModifier[])AttackModifier.$VALUES.clone();
    }
}

