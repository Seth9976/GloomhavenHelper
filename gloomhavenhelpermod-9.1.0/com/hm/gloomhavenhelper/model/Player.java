package com.hm.gloomhavenhelper.model;

import com.badlogic.gdx.utils.Array;
import com.hm.gloomhavenhelper.PlayerRow;

public class Player {
   public String name;
   public CharacterClass characterClass;
   public int xp;
   public int hp;
   public final Array conditions = new Array();
   public int level;
   public int loot;
   public int init;
   public int hpMax;
   public final Array expiredConditions = new Array();
   public final Array currentTurnConditions = new Array();
   public boolean exhausted;
   public transient String initString;

   public void init(int init) {
      this.init = init;
      this.initString = Integer.toString(init);
      if (init == 0) {
         PlayerRow.localInit.remove(this.characterClass);
      }
   }
}
