package com.hm.gloomhavenhelper.model;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.OrderedSet;
import com.hm.gloomhavenhelper.App;
import com.hm.gloomhavenhelper.PlayerRow;
import com.hm.gloomhavenhelper.util.FormulaEvaluator;

public class MonsterStats {
   public String hpMax;
   public String move;
   public String attack;
   public String range;
   public final Array attributeLines = new Array();
   public final Array attributeText = new Array();
   public boolean immuneCurse;
   public final OrderedSet immunities = new OrderedSet();
   public boolean immunePush;
   public boolean immunePull;
   public final Array specialText1 = new Array();
   public final Array specialText2 = new Array();
   public String notesText;
   public final Array special1 = new Array();
   public final Array special2 = new Array();
   public final Array specialCalculated1 = new Array();
   public final Array specialCalculated2 = new Array();
   public Line notes;
   public Line notesCalculated;

   public int hpMax() {
      Integer C = App.gloom.playerCount();
      Integer L = App.state.scenarioLevel;
      int hitpoints = FormulaEvaluator.getHitPoints(C, L, this.hpMax);
      if (this.hpMax.equals("Hollowpact")) {
         this.hpMax = "1";

         for (PlayerRow row : App.gloom.playerRows) {
            if (row.player.characterClass.name.equals("Hollowpact")) {
               return row.player.hpMax;
            }
         }
      }

      return hitpoints;
   }

   public int attack() {
      if (this.attack.equals("C")) {
         return App.gloom.playerCount();
      } else {
         return this.attack.endsWith("+C")
            ? App.parseInt(this.attack.substring(0, this.attack.length() - 2)) + App.gloom.playerCount()
            : App.parseInt(this.attack);
      }
   }
}
