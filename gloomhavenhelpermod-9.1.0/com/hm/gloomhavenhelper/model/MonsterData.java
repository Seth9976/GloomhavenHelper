package com.hm.gloomhavenhelper.model;

public class MonsterData {
   private static int nextID = -1;
   public int id = nextID--;
   public String edition;
   public String name;
   public int deckID = -1;
   public String english;
   public String display;
   public int count;
   public final MonsterStats[][] stats = new MonsterStats[3][8];
   public boolean flying;
   public boolean hidden;
   public String gfx;

   public MonsterData() {
      for (int i = 0; i < 3; i++) {
         this.stats[i] = new MonsterStats[8];
      }
   }

   public boolean treatLikeABoss() {
      return this.stats[MonsterType.boss.ordinal()][0] != null ? this.stats[MonsterType.boss.ordinal()][0].special1.size != 0 : false;
   }

   public boolean isBoss() {
      return this.stats[MonsterType.boss.ordinal()][0] != null;
   }

   public static void reset() {
      nextID = -1;
   }
}
