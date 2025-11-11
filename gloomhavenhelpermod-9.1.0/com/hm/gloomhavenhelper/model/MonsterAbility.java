package com.hm.gloomhavenhelper.model;

import com.badlogic.gdx.utils.Array;
import com.hm.gloomhavenhelper.util.Card;
import java.util.ArrayList;
import java.util.List;

public class MonsterAbility implements Card {
   private static int nextID;
   public int id;
   public String number;
   public String title;
   public boolean shuffle;
   public int initiative;
   public String initiativeString;
   public final Array text;
   public final Array lines;
   public transient MonsterAbilityDeck deck;
   public MonsterAbility.CardLayout cardLayout;

   public MonsterAbility() {
      this.id = nextID++;
      this.title = "";
      this.text = new Array();
      this.lines = new Array();
   }

   public static void reset() {
      nextID = 0;
   }

   public static class CardLayout {
      public String nr;
      public List lines = new ArrayList();
   }

   public static class LinesLayout {
      public int index;
      public String type;
      public Integer xOffset;
      public Integer yOffset;
      public Integer heightOffset;
      public Integer spaceTopOffset;
   }
}
