package com.hm.gloomhavenhelper.model;

import com.badlogic.gdx.utils.Array;
import com.hm.gloomhavenhelper.App;

public class Monster {
   public int number;
   public MonsterType type;
   public SummonColor summonColor = SummonColor.blue;
   public int hp;
   public boolean isNew = true;
   public int hpMax;
   public final Array conditions = new Array();
   public final Array expiredConditions = new Array();
   public final Array currentTurnConditions = new Array();
   public int summonMove;
   public int summonAttack;
   public int summonRange;
   public transient MonsterData data;
   public transient MonsterStats stats;

   public Monster() {
   }

   public Monster(MonsterData data, MonsterType type, int level, int number) {
      this.data = data;
      this.type = type;
      this.number = number;
      if (data != App.summonData) {
         MonsterStats[] typeStats = data.stats[type.ordinal()];
         if (level >= typeStats.length) {
            level = typeStats.length - 1;
         }

         this.stats = typeStats[level];
         if (this.stats == null) {
            throw new IllegalArgumentException("No stats available: " + data.name + ", " + type + ", " + level);
         }

         this.hp = this.hpMax = this.stats.hpMax();
      } else {
         this.stats = App.summonStats;
         this.hp = this.hpMax = level * 2;
      }
   }
}
