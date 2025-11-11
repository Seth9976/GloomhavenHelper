package com.hm.gloomhavenhelper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.SnapshotArray;
import com.hm.gloomhavenhelper.model.AttackModifier;
import com.hm.gloomhavenhelper.model.ElementState;
import com.hm.gloomhavenhelper.model.MonsterAbility;
import com.hm.gloomhavenhelper.model.MonsterAbilityDeck;
import com.hm.gloomhavenhelper.model.PlayerInit;
import com.hm.gloomhavenhelper.util.Menu;
import com.hm.gloomhavenhelper.util.Row;

public class GameState {
   public int round = 1;
   public int scenarioNumber = -1;
   public int scenarioLevel;
   public boolean solo;
   public boolean soloFH;
   public String edition = "Gloomhaven";
   public boolean trackStandees = true;
   public boolean abilityCards = true;
   public boolean randomStandees;
   public boolean elitesFirst = true;
   public boolean expireConditions;
   public boolean hideStats = true;
   public boolean calculateStats = true;
   public boolean canDraw = true;
   public boolean needsShuffle;
   public PlayerInit playerInit = PlayerInit.numpad;
   public final Array attackModifiers = new Array();
   public final Array attackModifiersDiscard = new Array();
   public ElementState fire = ElementState.inert;
   public ElementState ice = ElementState.inert;
   public ElementState air = ElementState.inert;
   public ElementState earth = ElementState.inert;
   public ElementState light = ElementState.inert;
   public ElementState dark = ElementState.inert;
   public IntMap abilityDecks = new IntMap();
   public IntArray removedAbilities = new IntArray();
   public int badOmen;
   public transient int changeNumber;
   public transient boolean ignoreChanges;

   public void apply(boolean animate) {
      App.gameThread();
      this.ignoreChanges = true;

      for (MonsterRow row : App.gloom.monsterRows) {
         row.boxes.sort();
         row.monstersGroup.getChildren().sort();
         row.monstersGroup.invalidate();
      }

      App.gloom.fireButton.setState(this.fire, animate);
      App.gloom.iceButton.setState(this.ice, animate);
      App.gloom.airButton.setState(this.air, animate);
      App.gloom.earthButton.setState(this.earth, animate);
      App.gloom.lightButton.setState(this.light, animate);
      App.gloom.darkButton.setState(this.dark, animate);
      AttackModifier attackModifier1 = this.attackModifier1();
      if (attackModifier1 == null) {
         App.gloom.attackImage1.setDrawable(null);
      } else {
         App.gloom.attackImage1.setDrawable(App.drawable("attack/" + attackModifier1, "attack/border"));
      }

      AttackModifier attackModifier2 = this.attackModifier2();
      if (attackModifier2 == null) {
         App.gloom.attackImage2.setDrawable(null);
      } else {
         App.gloom.attackImage2.setDrawable(App.drawable("attack/" + attackModifier2, "attack/border"));
      }

      SnapshotArray children = App.stage.getRoot().getChildren();

      for (int i = children.size - 1; i >= 0; i--) {
         Actor child = (Actor)children.get(i);
         if (child instanceof PlayerRowMenu || child instanceof MonsterBoxMenu || child instanceof MonsterAddMenu) {
            ((Menu)child).hide();
         } else if (child instanceof DeckDialog) {
            ((DeckDialog)child).hide();
         }
      }

      App.gloom.rows.act(9999.0F);
      Gdx.graphics.requestRendering();
      if (App.config.autoScroll) {
         Row turnRow = null;

         for (int j = App.gloom.rows.getChildren().size - 1; j >= 0; j--) {
            Row row = (Row)App.gloom.rows.getChild(j);
            if (row.turnComplete) {
               break;
            }

            turnRow = row;
         }

         if (turnRow != null) {
            App.root.validate();
            App.gloom.rowsScroll.scrollTo(turnRow.getX(), turnRow.getY(), turnRow.getWidth(), turnRow.getHeight(), false, true);
         }
      }

      this.ignoreChanges = false;
   }

   public AttackModifier attackModifier1() {
      return this.attackModifiersDiscard.size >= 1 ? (AttackModifier)this.attackModifiersDiscard.peek() : null;
   }

   public AttackModifier attackModifier2() {
      return this.attackModifiersDiscard.size >= 2 ? (AttackModifier)this.attackModifiersDiscard.get(this.attackModifiersDiscard.size - 2) : null;
   }

   public void shuffleAttackModifiers(boolean useBadOmen) {
      Array extra = new Array();
      int i = 0;

      for (int n = this.count(AttackModifier.curse, false); i < n; i++) {
         extra.add(AttackModifier.curse);
      }

      i = 0;

      for (int var7 = this.count(AttackModifier.bless, false); i < var7; i++) {
         extra.add(AttackModifier.bless);
      }

      i = 0;

      for (int var8 = this.count(AttackModifier.minus1, true) - 5; i < var8; i++) {
         extra.add(AttackModifier.minus1);
      }

      this.attackModifiers.clear();
      this.attackModifiers.addAll(extra);
      this.attackModifiers.addAll(AttackModifier.baseAttackModifiers);
      this.attackModifiers.shuffle();
      this.attackModifiersDiscard.clear();
      this.needsShuffle = false;
   }

   public void addCurse() {
      if (this.badOmen <= 0) {
         App.state.add(AttackModifier.curse);
         this.attackModifiers.shuffle();
      } else {
         this.badOmen--;
         this.attackModifiers.insert(Math.max(0, this.attackModifiers.size - 6 + 1), AttackModifier.curse);
         App.toast("Bad Omen placed the curse");
         App.toast("6th from the top.");
      }
   }

   public void add(AttackModifier attackModifier) {
      this.attackModifiers.add(attackModifier);
   }

   public void remove(AttackModifier attackModifier) {
      int i = 0;

      for (int n = this.attackModifiers.size; i < n; i++) {
         if (this.attackModifiers.get(i) == attackModifier) {
            this.attackModifiers.removeIndex(i);
            return;
         }
      }

      i = 0;

      for (int var5 = this.attackModifiersDiscard.size; i < var5; i++) {
         if (this.attackModifiersDiscard.get(i) == attackModifier) {
            this.attackModifiersDiscard.removeIndex(i);
            App.gloom.attackImage1.setDrawable(null);
            App.gloom.attackImage2.setDrawable(null);
            if (this.attackModifiersDiscard.size >= 1) {
               App.gloom.attackImage1.setDrawable(App.drawable("attack/" + this.attackModifiersDiscard.peek(), "attack/border"));
               if (this.attackModifiersDiscard.size >= 2) {
                  App.gloom
                     .attackImage2
                     .setDrawable(App.drawable("attack/" + this.attackModifiersDiscard.get(this.attackModifiersDiscard.size - 2), "attack/border"));
               }
            }

            return;
         }
      }
   }

   public int count(AttackModifier attackModifier, boolean includeDiscard) {
      int count = 0;

      for (AttackModifier other : this.attackModifiers) {
         if (other == attackModifier) {
            count++;
         }
      }

      if (includeDiscard) {
         for (AttackModifier otherx : this.attackModifiersDiscard) {
            if (otherx == attackModifier) {
               count++;
            }
         }
      }

      return count;
   }

   public MonsterAbilityDeck getAbilityDeck(int deckID) {
      MonsterAbilityDeck deck = (MonsterAbilityDeck)this.abilityDecks.get(deckID);
      if (deck == null) {
         deck = new MonsterAbilityDeck(App.findMonsterAbilityDeck(deckID));
         this.removeAbilities(deck.abilities);
         deck.abilities.shuffle();
         this.abilityDecks.put(deckID, deck);
      }

      return deck;
   }

   public void removeAbilities(Array abilities) {
      if (!App.state.removedAbilities.isEmpty()) {
         for (int i = abilities.size - 1; i >= 0; i--) {
            if (App.state.removedAbilities.contains(((MonsterAbility)abilities.get(i)).id)) {
               abilities.removeIndex(i);
            }
         }
      }
   }

   public void changed() {
      this.changed(true, null);
   }

   public void changed(boolean storeUndo, byte[] animate) {
      App.gameThread();
      if (!this.ignoreChanges) {
         this.fire = App.gloom.fireButton.elementState;
         this.ice = App.gloom.iceButton.elementState;
         this.air = App.gloom.airButton.elementState;
         this.earth = App.gloom.earthButton.elementState;
         this.light = App.gloom.lightButton.elementState;
         this.dark = App.gloom.darkButton.elementState;
         App.game.saveState(storeUndo, animate);
      }
   }
}
