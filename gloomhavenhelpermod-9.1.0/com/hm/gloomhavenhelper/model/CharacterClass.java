package com.hm.gloomhavenhelper.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.JsonValue;
import com.hm.gloomhavenhelper.App;
import java.util.ArrayList;

public class CharacterClass {
   public String name;
   public ArrayList hitpoints;
   public String edition;
   public boolean hidden;
   public ArrayList summons;
   public Color color;
   public boolean nonPlayer = false;

   public int hpMax(int level) {
      return this.name.equals("Objective") ? 6 + App.gloom.playerCount() * level : (Integer)this.hitpoints.get(level - 1);
   }

   public String asString() {
      return this.name.replaceAll(" ", "");
   }

   @Override
   public String toString() {
      return this.name.replaceAll(" ", "");
   }

   public void loadData(JsonValue data) {
      this.name = data.getString("name");
      this.edition = data.getString("edition", "Gloomhaven");
      if (data.get("color").isString()) {
         long x = Long.parseLong(data.getString("color", "ffffffff"), 16);
         int y = (int)x;
         this.color = new Color(y);
      } else {
         this.color = new Color(data.getInt("color", -1));
      }

      this.hidden = data.getBoolean("hidden", false);
      int[] hp = data.get("hitpoints").asIntArray();
      this.nonPlayer = data.getBoolean("nonPlayer", false);
      this.hitpoints = new ArrayList();

      for (int i = 0; i < hp.length; i++) {
         int hitpoint = hp[i];
         this.hitpoints.add(hitpoint);
      }
   }

   public static class SummonData {
      int level;
      int hitpoints;
      int move;
      int attack;
      int range;
      String name;
      String graphicsId;
      int standees;
   }
}
