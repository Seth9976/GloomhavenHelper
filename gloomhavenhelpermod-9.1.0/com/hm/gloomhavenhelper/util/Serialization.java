package com.hm.gloomhavenhelper.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.SnapshotArray;
import com.hm.gloomhavenhelper.App;
import com.hm.gloomhavenhelper.GameState;
import com.hm.gloomhavenhelper.MonsterBox;
import com.hm.gloomhavenhelper.MonsterRow;
import com.hm.gloomhavenhelper.PlayerRow;
import com.hm.gloomhavenhelper.Rows;
import com.hm.gloomhavenhelper.model.AttackModifier;
import com.hm.gloomhavenhelper.model.CharacterClass;
import com.hm.gloomhavenhelper.model.Condition;
import com.hm.gloomhavenhelper.model.ElementState;
import com.hm.gloomhavenhelper.model.Monster;
import com.hm.gloomhavenhelper.model.MonsterAbility;
import com.hm.gloomhavenhelper.model.MonsterAbilityDeck;
import com.hm.gloomhavenhelper.model.MonsterData;
import com.hm.gloomhavenhelper.model.MonsterType;
import com.hm.gloomhavenhelper.model.Player;
import com.hm.gloomhavenhelper.model.PlayerInit;
import com.hm.gloomhavenhelper.model.SummonColor;

public class Serialization {
   public static final Input input = new Input(8192);
   public static Output lastOutput = new Output(8192);
   public static Output output = new Output(8192);

   public static void write(Output output, GameState state) {
      output.writeInt(state.round, true);
      output.writeInt(state.scenarioNumber, true);
      output.writeInt(state.scenarioLevel, true);
      output.writeBoolean(state.trackStandees);
      output.writeBoolean(state.abilityCards);
      output.writeBoolean(state.randomStandees);
      output.writeBoolean(state.elitesFirst);
      output.writeBoolean(state.expireConditions);
      output.writeBoolean(state.solo);
      output.writeBoolean(state.soloFH);
      output.writeBoolean(state.hideStats);
      output.writeBoolean(state.calculateStats);
      output.writeBoolean(state.canDraw);
      output.writeBoolean(state.needsShuffle);
      writeEnum(output, state.playerInit);
      writeEnumArray(output, state.attackModifiers);
      writeEnumArray(output, state.attackModifiersDiscard);
      writeEnum(output, state.fire);
      writeEnum(output, state.ice);
      writeEnum(output, state.air);
      writeEnum(output, state.earth);
      writeEnum(output, state.light);
      writeEnum(output, state.dark);
      writeIntArray(output, state.removedAbilities, true);
      output.writeInt(state.badOmen, true);
      output.writeString(state.edition);
      output.writeInt(state.abilityDecks.size, true);

      for (MonsterAbilityDeck deck : state.abilityDecks.values()) {
         output.writeInt(deck.id, true);
         output.writeBoolean(deck.shuffle);
         writeMonsterAbility(output, deck.shownAbility);
         int j = 0;

         for (int k = writeArrayStart(output, deck.abilities); j < k; j++) {
            output.writeInt(((MonsterAbility)deck.abilities.get(j)).id, true);
         }

         j = 0;

         for (int var10 = writeArrayStart(output, deck.abilitiesDiscard); j < var10; j++) {
            output.writeInt(((MonsterAbility)deck.abilitiesDiscard.get(j)).id, true);
         }
      }

      SnapshotArray children = App.gloom.rows.getChildren();
      int i = 0;

      for (int n = writeArrayStart(output, children); i < n; i++) {
         Actor actor = (Actor)children.get(i);
         if (actor instanceof PlayerRow) {
            output.writeBoolean(true);
            writePlayerRow(output, (PlayerRow)actor);
         } else {
            output.writeBoolean(false);
            writeMonsterRow(output, (MonsterRow)actor);
         }
      }
   }

   public static GameState read(Input input) {
      GameState state = new GameState();
      state.round = input.readInt(true);
      state.scenarioNumber = input.readInt(true);
      state.scenarioLevel = input.readInt(true);
      state.trackStandees = input.readBoolean();
      state.abilityCards = input.readBoolean();
      state.randomStandees = input.readBoolean();
      state.elitesFirst = input.readBoolean();
      state.expireConditions = input.readBoolean();
      state.solo = input.readBoolean();
      state.soloFH = input.readBoolean();
      state.hideStats = input.readBoolean();
      state.calculateStats = input.readBoolean();
      state.canDraw = input.readBoolean();
      state.needsShuffle = input.readBoolean();
      state.playerInit = (PlayerInit)readEnum(input, PlayerInit.values);
      readEnumArray(input, state.attackModifiers, AttackModifier.values);
      readEnumArray(input, state.attackModifiersDiscard, AttackModifier.values);
      state.fire = (ElementState)readEnum(input, ElementState.values);
      state.ice = (ElementState)readEnum(input, ElementState.values);
      state.air = (ElementState)readEnum(input, ElementState.values);
      state.earth = (ElementState)readEnum(input, ElementState.values);
      state.light = (ElementState)readEnum(input, ElementState.values);
      state.dark = (ElementState)readEnum(input, ElementState.values);
      readIntArray(input, state.removedAbilities, true);
      state.badOmen = input.readInt(true);
      state.edition = input.readString();
      int i = 0;

      for (int n = input.readInt(true); i < n; i++) {
         MonsterAbilityDeck deck = new MonsterAbilityDeck(App.findMonsterAbilityDeck(input.readInt(true)));
         state.abilityDecks.put(deck.id, deck);
         deck.shuffle = input.readBoolean();
         deck.shownAbility = readMonsterAbility(input, deck);
         deck.abilities.clear();
         int ii = 0;

         for (int nn = input.readInt(true); ii < nn; ii++) {
            deck.abilities.add(App.monsterAbilities.get(input.readInt(true)));
         }

         ii = 0;

         for (int var13 = input.readInt(true); ii < var13; ii++) {
            deck.abilitiesDiscard.add(App.monsterAbilities.get(input.readInt(true)));
         }
      }

      Rows rows = App.gloom.rows;
      rows.clearChildren();
      App.gloom.playerRows.clear();
      App.gloom.monsterRows.clear();
      int j = 0;

      for (int k = input.readInt(true); j < k; j++) {
         if (input.readBoolean()) {
            PlayerRow row = readPlayerRow(input);
            rows.addActor(row);
            App.gloom.playerRows.add(row);
         } else {
            MonsterRow row = readMonsterRow(input);
            rows.addActor(row);
            App.gloom.monsterRows.add(row);
         }
      }

      return state;
   }

   private static void writePlayerRow(Output output, PlayerRow row) {
      Player player = row.player;
      if (player.name.equals(player.characterClass.toString())) {
         output.writeString((String)null);
      } else {
         output.writeString(player.name);
      }

      output.writeString(player.characterClass.asString());
      output.writeInt(player.xp, true);
      output.writeInt(player.hp, true);
      output.writeInt(player.hpMax, true);
      output.writeInt(player.level, true);
      output.writeInt(player.loot, true);
      output.writeInt(player.init, true);
      writeEnumArray(output, player.conditions);
      writeEnumArray(output, player.expiredConditions);
      writeEnumArray(output, player.currentTurnConditions);
      output.writeBoolean(player.exhausted);
      writeRow(output, row);
   }

   private static PlayerRow readPlayerRow(Input input) {
      Player player = new Player();
      player.name = input.readString();
      String className = input.readString();

      for (CharacterClass charclass : App.allClasses) {
         if (charclass.asString().equals(className)) {
            player.characterClass = charclass;
         }
      }

      if (player.name == null) {
         player.name = player.characterClass.name;
      }

      player.xp = input.readInt(true);
      player.hp = input.readInt(true);
      player.hpMax = input.readInt(true);
      player.level = input.readInt(true);
      player.loot = input.readInt(true);
      player.init(input.readInt(true));
      readEnumArray(input, player.conditions, Condition.values);
      readEnumArray(input, player.expiredConditions, Condition.values);
      readEnumArray(input, player.currentTurnConditions, Condition.values);
      player.exhausted = input.readBoolean();
      PlayerRow row = new PlayerRow(player);
      readRow(input, row);
      return row;
   }

   private static void writeMonsterRow(Output output, MonsterRow row) {
      output.writeInt(-(row.data.id + 1), true);
      output.writeInt(row.level, true);
      output.writeBoolean(row.hasNormal);
      output.writeBoolean(row.hasElite);
      writeMonsterAbility(output, row.ability);
      writeRow(output, row);
   }

   private static MonsterRow readMonsterRow(Input input) {
      MonsterData data = (MonsterData)App.monsterDatas.get(input.readInt(true));
      MonsterRow row = new MonsterRow(data, input.readInt(true));
      row.hasNormal = input.readBoolean();
      row.hasElite = input.readBoolean();
      row.ability = readMonsterAbility(input, App.findMonsterAbilityDeck(data.deckID));
      readRow(input, row);
      return row;
   }

   private static void writeMonsterAbility(Output output, MonsterAbility ability) {
      output.writeInt(ability == null ? 0 : ability.id + 1, true);
   }

   private static MonsterAbility readMonsterAbility(Input input, MonsterAbilityDeck deck) {
      int abilityID = input.readInt(true);
      if (abilityID == 0) {
         return null;
      } else {
         abilityID--;
         Array abilities = deck.abilities;
         int i = 0;

         for (int n = abilities.size; i < n; i++) {
            if (((MonsterAbility)abilities.get(i)).id == abilityID) {
               return (MonsterAbility)abilities.get(i);
            }
         }

         throw new RuntimeException("Ability not found: " + abilityID);
      }
   }

   private static void writeRow(Output output, Row row) {
      output.writeBoolean(row.turnComplete);
      int i = 0;

      for (int n = writeArrayStart(output, row.boxes); i < n; i++) {
         Monster monster = ((MonsterBox)row.boxes.get(i)).monster;
         output.writeInt(monster.number, true);
         writeEnum(output, monster.type);
         if (monster.type == MonsterType.summon) {
            writeEnum(output, monster.summonColor);
            output.writeInt(monster.summonMove, true);
            output.writeInt(monster.summonAttack, true);
            output.writeInt(monster.summonRange, true);
         }

         output.writeBoolean(monster.isNew);
         output.writeInt(monster.hp, true);
         output.writeInt(monster.hpMax, true);
         writeEnumArray(output, monster.conditions);
         writeEnumArray(output, monster.expiredConditions);
         writeEnumArray(output, monster.currentTurnConditions);
      }
   }

   private static void readRow(Input input, Row row) {
      row.setTurnComplete(input.readBoolean(), false);
      int ii = 0;

      for (int nn = input.readInt(true); ii < nn; ii++) {
         Monster monster = new Monster();
         monster.number = input.readInt(true);
         monster.type = (MonsterType)readEnum(input, MonsterType.values);
         if (monster.type == MonsterType.summon) {
            monster.summonColor = (SummonColor)readEnum(input, SummonColor.values);
            monster.summonMove = input.readInt(true);
            monster.summonAttack = input.readInt(true);
            monster.summonRange = input.readInt(true);
         }

         monster.isNew = input.readBoolean();
         monster.hp = input.readInt(true);
         monster.hpMax = input.readInt(true);
         readEnumArray(input, monster.conditions, Condition.values);
         readEnumArray(input, monster.expiredConditions, Condition.values);
         readEnumArray(input, monster.currentTurnConditions, Condition.values);
         if (row instanceof MonsterRow) {
            MonsterRow monsterRow = (MonsterRow)row;
            monster.data = monsterRow.data;
            monster.stats = monsterRow.data.stats[monster.type.ordinal()][monsterRow.level];
         } else {
            monster.data = App.summonData;
            monster.stats = App.summonStats;
         }

         MonsterBox box = new MonsterBox(row, monster, row.monstersGroup.getScaleX());
         row.boxes.add(box);
         row.monstersGroup.addActor(box);
      }
   }

   private static void writeEnum(Output output, Enum value) {
      output.writeInt(value.ordinal(), true);
   }

   private static Enum readEnum(Input input, Enum[] values) {
      return values[input.readInt(true)];
   }

   private static void writeEnumOrNull(Output output, Enum value) {
      if (value == null) {
         output.writeByte(0);
      } else {
         output.writeInt(value.ordinal() + 1, true);
      }
   }

   private static Enum readEnumOrNull(Input input, Enum[] values) {
      int value = input.readInt(true);
      return value == 0 ? null : values[value - 1];
   }

   private static int writeArrayStart(Output output, Array array) {
      int size = array.size;
      output.writeInt(size, true);
      return size;
   }

   private static int writeIntArray(Output output, IntArray array, boolean optimizePositive) {
      int size = array.size;
      output.writeInt(size, true);
      int i = 0;

      for (int n = array.size; i < n; i++) {
         output.writeInt(array.get(i), optimizePositive);
      }

      return size;
   }

   private static IntArray readIntArray(Input input, IntArray array, boolean optimizePositive) {
      int length = input.readInt(true);
      array.ensureCapacity(length);

      for (int i = 0; i < length; i++) {
         array.add(input.readInt(optimizePositive));
      }

      return array;
   }

   private static void writeEnumArray(Output output, Array array) {
      int length = array.size;
      output.writeInt(length, true);
      if (length != 0) {
         int i = 0;

         for (int n = array.size; i < n; i++) {
            writeEnum(output, (Enum)array.get(i));
         }
      }
   }

   private static Array readEnumArray(Input input, Array array, Enum[] values) {
      int length = input.readInt(true);
      array.ensureCapacity(length);

      for (int i = 0; i < length; i++) {
         array.add(readEnum(input, values));
      }

      return array;
   }
}
