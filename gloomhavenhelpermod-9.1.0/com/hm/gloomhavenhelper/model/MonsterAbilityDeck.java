package com.hm.gloomhavenhelper.model;

import com.badlogic.gdx.utils.Array;

public class MonsterAbilityDeck {
   private static int nextID;
   public final transient int id;
   public transient String name;
   public transient String edition;
   public boolean shuffle;
   public MonsterAbility shownAbility;
   public final Array abilities = new Array();
   public final Array abilitiesDiscard = new Array();

   public MonsterAbilityDeck() {
      this.id = nextID++;
   }

   public MonsterAbilityDeck(MonsterAbilityDeck other) {
      this.id = other.id;
      this.name = other.name;
      this.edition = other.edition;
      this.shuffle = other.shuffle;
      this.shownAbility = other.shownAbility;
      this.abilities.addAll(other.abilities);
   }

   public static void reset() {
      nextID = 0;
   }
}
