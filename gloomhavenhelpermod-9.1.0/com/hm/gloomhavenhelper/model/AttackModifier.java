package com.hm.gloomhavenhelper.model;

import com.badlogic.gdx.utils.Array;
import com.hm.gloomhavenhelper.util.Card;

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

   public static final Array baseAttackModifiers = Array.with(
      plus0,
      plus0,
      plus0,
      plus0,
      plus0,
      plus0,
      plus1,
      plus1,
      plus1,
      plus1,
      plus1,
      minus1,
      minus1,
      minus1,
      minus1,
      minus1,
      plus2,
      minus2,
      nullAttack,
      doubleAttack
   );
   public static final AttackModifier[] values = values();
}
