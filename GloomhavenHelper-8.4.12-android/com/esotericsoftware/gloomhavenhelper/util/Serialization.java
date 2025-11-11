package com.esotericsoftware.gloomhavenhelper.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.SnapshotArray;
import com.esotericsoftware.gloomhavenhelper.App;
import com.esotericsoftware.gloomhavenhelper.GameState;
import com.esotericsoftware.gloomhavenhelper.MonsterBox;
import com.esotericsoftware.gloomhavenhelper.MonsterRow;
import com.esotericsoftware.gloomhavenhelper.PlayerRow;
import com.esotericsoftware.gloomhavenhelper.Rows;
import com.esotericsoftware.gloomhavenhelper.model.AttackModifier;
import com.esotericsoftware.gloomhavenhelper.model.CharacterClass;
import com.esotericsoftware.gloomhavenhelper.model.Condition;
import com.esotericsoftware.gloomhavenhelper.model.ElementState;
import com.esotericsoftware.gloomhavenhelper.model.Monster;
import com.esotericsoftware.gloomhavenhelper.model.MonsterAbility;
import com.esotericsoftware.gloomhavenhelper.model.MonsterAbilityDeck;
import com.esotericsoftware.gloomhavenhelper.model.MonsterData;
import com.esotericsoftware.gloomhavenhelper.model.MonsterType;
import com.esotericsoftware.gloomhavenhelper.model.Player;
import com.esotericsoftware.gloomhavenhelper.model.PlayerInit;
import com.esotericsoftware.gloomhavenhelper.model.SummonColor;
import java.util.Iterator;

public class Serialization {
    public static final Input input;
    public static Output lastOutput;
    public static Output output;

    static {
        Serialization.input = new Input(0x2000);
        Serialization.lastOutput = new Output(0x2000);
        Serialization.output = new Output(0x2000);
    }

    public static GameState read(Input input0) {
        GameState gameState0 = new GameState();
        gameState0.round = input0.readInt(true);
        gameState0.scenarioNumber = input0.readInt(true);
        gameState0.scenarioLevel = input0.readInt(true);
        gameState0.trackStandees = input0.readBoolean();
        gameState0.abilityCards = input0.readBoolean();
        gameState0.randomStandees = input0.readBoolean();
        gameState0.elitesFirst = input0.readBoolean();
        gameState0.expireConditions = input0.readBoolean();
        gameState0.solo = input0.readBoolean();
        gameState0.hideStats = input0.readBoolean();
        gameState0.calculateStats = input0.readBoolean();
        gameState0.canDraw = input0.readBoolean();
        gameState0.needsShuffle = input0.readBoolean();
        gameState0.playerInit = (PlayerInit)Serialization.readEnum(input0, PlayerInit.values);
        Serialization.readEnumArray(input0, gameState0.attackModifiers, AttackModifier.values);
        Serialization.readEnumArray(input0, gameState0.attackModifiersDiscard, AttackModifier.values);
        gameState0.fire = (ElementState)Serialization.readEnum(input0, ElementState.values);
        gameState0.ice = (ElementState)Serialization.readEnum(input0, ElementState.values);
        gameState0.air = (ElementState)Serialization.readEnum(input0, ElementState.values);
        gameState0.earth = (ElementState)Serialization.readEnum(input0, ElementState.values);
        gameState0.light = (ElementState)Serialization.readEnum(input0, ElementState.values);
        gameState0.dark = (ElementState)Serialization.readEnum(input0, ElementState.values);
        Serialization.readIntArray(input0, gameState0.removedAbilities, true);
        gameState0.badOmen = input0.readInt(true);
        gameState0.jotl = input0.readBoolean();
        int v = input0.readInt(true);
        for(int v2 = 0; v2 < v; ++v2) {
            MonsterAbilityDeck monsterAbilityDeck0 = new MonsterAbilityDeck(App.findMonsterAbilityDeck(input0.readInt(true)));
            gameState0.abilityDecks.put(monsterAbilityDeck0.id, monsterAbilityDeck0);
            monsterAbilityDeck0.shuffle = input0.readBoolean();
            monsterAbilityDeck0.shownAbility = Serialization.readMonsterAbility(input0, monsterAbilityDeck0);
            monsterAbilityDeck0.abilities.clear();
            int v3 = input0.readInt(true);
            for(int v4 = 0; v4 < v3; ++v4) {
                int v5 = input0.readInt(true);
                Object object0 = App.monsterAbilities.get(v5);
                monsterAbilityDeck0.abilities.add(object0);
            }
            int v6 = input0.readInt(true);
            for(int v7 = 0; v7 < v6; ++v7) {
                int v8 = input0.readInt(true);
                Object object1 = App.monsterAbilities.get(v8);
                monsterAbilityDeck0.abilitiesDiscard.add(object1);
            }
        }
        Rows rows0 = App.gloom.rows;
        rows0.clearChildren();
        App.gloom.playerRows.clear();
        App.gloom.monsterRows.clear();
        int v9 = input0.readInt(true);
        for(int v1 = 0; v1 < v9; ++v1) {
            if(input0.readBoolean()) {
                PlayerRow playerRow0 = Serialization.readPlayerRow(input0);
                rows0.addActor(playerRow0);
                App.gloom.playerRows.add(playerRow0);
            }
            else {
                MonsterRow monsterRow0 = Serialization.readMonsterRow(input0);
                rows0.addActor(monsterRow0);
                App.gloom.monsterRows.add(monsterRow0);
            }
        }
        return gameState0;
    }

    private static Enum readEnum(Input input0, Enum[] arr_enum) {
        return arr_enum[input0.readInt(true)];
    }

    private static Array readEnumArray(Input input0, Array array0, Enum[] arr_enum) {
        int v = input0.readInt(true);
        array0.ensureCapacity(v);
        for(int v1 = 0; v1 < v; ++v1) {
            array0.add(Serialization.readEnum(input0, arr_enum));
        }
        return array0;
    }

    private static Enum readEnumOrNull(Input input0, Enum[] arr_enum) {
        int v = input0.readInt(true);
        return v == 0 ? null : arr_enum[v - 1];
    }

    private static IntArray readIntArray(Input input0, IntArray intArray0, boolean z) {
        int v = input0.readInt(true);
        intArray0.ensureCapacity(v);
        for(int v1 = 0; v1 < v; ++v1) {
            intArray0.add(input0.readInt(z));
        }
        return intArray0;
    }

    private static MonsterAbility readMonsterAbility(Input input0, MonsterAbilityDeck monsterAbilityDeck0) {
        int v = input0.readInt(true);
        if(v == 0) {
            return null;
        }
        Array array0 = monsterAbilityDeck0.abilities;
        int v2 = array0.size;
        for(int v1 = 0; v1 < v2; ++v1) {
            if(((MonsterAbility)array0.get(v1)).id == v - 1) {
                return (MonsterAbility)array0.get(v1);
            }
        }
        throw new RuntimeException("Ability not found: " + (v - 1));
    }

    private static MonsterRow readMonsterRow(Input input0) {
        int v = input0.readInt(true);
        MonsterData monsterData0 = (MonsterData)App.monsterDatas.get(v);
        MonsterRow monsterRow0 = new MonsterRow(monsterData0, input0.readInt(true));
        monsterRow0.hasNormal = input0.readBoolean();
        monsterRow0.hasElite = input0.readBoolean();
        monsterRow0.ability = Serialization.readMonsterAbility(input0, App.findMonsterAbilityDeck(monsterData0.deckID));
        Serialization.readRow(input0, monsterRow0);
        return monsterRow0;
    }

    private static PlayerRow readPlayerRow(Input input0) {
        Player player0 = new Player();
        player0.name = input0.readString();
        player0.characterClass = (CharacterClass)Serialization.readEnum(input0, CharacterClass.values);
        if(player0.name == null) {
            player0.name = player0.characterClass.toString();
        }
        player0.xp = input0.readInt(true);
        player0.hp = input0.readInt(true);
        player0.hpMax = input0.readInt(true);
        player0.level = input0.readInt(true);
        player0.loot = input0.readInt(true);
        player0.init(input0.readInt(true));
        Serialization.readEnumArray(input0, player0.conditions, Condition.values);
        Serialization.readEnumArray(input0, player0.expiredConditions, Condition.values);
        Serialization.readEnumArray(input0, player0.currentTurnConditions, Condition.values);
        player0.exhausted = input0.readBoolean();
        PlayerRow playerRow0 = new PlayerRow(player0);
        Serialization.readRow(input0, playerRow0);
        return playerRow0;
    }

    private static void readRow(Input input0, Row row0) {
        row0.setTurnComplete(input0.readBoolean(), false);
        int v1 = input0.readInt(true);
        for(int v = 0; v < v1; ++v) {
            Monster monster0 = new Monster();
            monster0.number = input0.readInt(true);
            monster0.type = (MonsterType)Serialization.readEnum(input0, MonsterType.values);
            if(monster0.type == MonsterType.summon) {
                monster0.summonColor = (SummonColor)Serialization.readEnum(input0, SummonColor.values);
                monster0.summonMove = input0.readInt(true);
                monster0.summonAttack = input0.readInt(true);
                monster0.summonRange = input0.readInt(true);
            }
            monster0.isNew = input0.readBoolean();
            monster0.hp = input0.readInt(true);
            monster0.hpMax = input0.readInt(true);
            Serialization.readEnumArray(input0, monster0.conditions, Condition.values);
            Serialization.readEnumArray(input0, monster0.expiredConditions, Condition.values);
            Serialization.readEnumArray(input0, monster0.currentTurnConditions, Condition.values);
            if(row0 instanceof MonsterRow) {
                monster0.data = ((MonsterRow)row0).data;
                monster0.stats = ((MonsterRow)row0).data.stats[monster0.type.ordinal()][((MonsterRow)row0).level];
            }
            else {
                monster0.data = App.summonData;
                monster0.stats = App.summonStats;
            }
            MonsterBox monsterBox0 = new MonsterBox(row0, monster0, row0.monstersGroup.getScaleX());
            row0.boxes.add(monsterBox0);
            row0.monstersGroup.addActor(monsterBox0);
        }
    }

    public static void write(Output output0, GameState gameState0) {
        output0.writeInt(gameState0.round, true);
        output0.writeInt(gameState0.scenarioNumber, true);
        output0.writeInt(gameState0.scenarioLevel, true);
        output0.writeBoolean(gameState0.trackStandees);
        output0.writeBoolean(gameState0.abilityCards);
        output0.writeBoolean(gameState0.randomStandees);
        output0.writeBoolean(gameState0.elitesFirst);
        output0.writeBoolean(gameState0.expireConditions);
        output0.writeBoolean(gameState0.solo);
        output0.writeBoolean(gameState0.hideStats);
        output0.writeBoolean(gameState0.calculateStats);
        output0.writeBoolean(gameState0.canDraw);
        output0.writeBoolean(gameState0.needsShuffle);
        Serialization.writeEnum(output0, gameState0.playerInit);
        Serialization.writeEnumArray(output0, gameState0.attackModifiers);
        Serialization.writeEnumArray(output0, gameState0.attackModifiersDiscard);
        Serialization.writeEnum(output0, gameState0.fire);
        Serialization.writeEnum(output0, gameState0.ice);
        Serialization.writeEnum(output0, gameState0.air);
        Serialization.writeEnum(output0, gameState0.earth);
        Serialization.writeEnum(output0, gameState0.light);
        Serialization.writeEnum(output0, gameState0.dark);
        Serialization.writeIntArray(output0, gameState0.removedAbilities, true);
        output0.writeInt(gameState0.badOmen, true);
        output0.writeBoolean(gameState0.jotl);
        output0.writeInt(gameState0.abilityDecks.size, true);
        Iterator iterator0 = gameState0.abilityDecks.values().iterator();
        while(true) {
            if(!iterator0.hasNext()) {
                break;
            }
            Object object0 = iterator0.next();
            MonsterAbilityDeck monsterAbilityDeck0 = (MonsterAbilityDeck)object0;
            output0.writeInt(monsterAbilityDeck0.id, true);
            output0.writeBoolean(monsterAbilityDeck0.shuffle);
            Serialization.writeMonsterAbility(output0, monsterAbilityDeck0.shownAbility);
            int v1 = Serialization.writeArrayStart(output0, monsterAbilityDeck0.abilities);
            for(int v2 = 0; v2 < v1; ++v2) {
                output0.writeInt(((MonsterAbility)monsterAbilityDeck0.abilities.get(v2)).id, true);
            }
            int v3 = Serialization.writeArrayStart(output0, monsterAbilityDeck0.abilitiesDiscard);
            for(int v = 0; v < v3; ++v) {
                output0.writeInt(((MonsterAbility)monsterAbilityDeck0.abilitiesDiscard.get(v)).id, true);
            }
        }
        SnapshotArray snapshotArray0 = App.gloom.rows.getChildren();
        int v4 = Serialization.writeArrayStart(output0, snapshotArray0);
        for(int v5 = 0; v5 < v4; ++v5) {
            Actor actor0 = (Actor)snapshotArray0.get(v5);
            if(actor0 instanceof PlayerRow) {
                output0.writeBoolean(true);
                Serialization.writePlayerRow(output0, ((PlayerRow)actor0));
            }
            else {
                output0.writeBoolean(false);
                Serialization.writeMonsterRow(output0, ((MonsterRow)actor0));
            }
        }
    }

    private static int writeArrayStart(Output output0, Array array0) {
        int v = array0.size;
        output0.writeInt(v, true);
        return v;
    }

    private static void writeEnum(Output output0, Enum enum0) {
        output0.writeInt(enum0.ordinal(), true);
    }

    private static void writeEnumArray(Output output0, Array array0) {
        int v = array0.size;
        output0.writeInt(v, true);
        if(v == 0) {
            return;
        }
        int v2 = array0.size;
        for(int v1 = 0; v1 < v2; ++v1) {
            Serialization.writeEnum(output0, ((Enum)array0.get(v1)));
        }
    }

    private static void writeEnumOrNull(Output output0, Enum enum0) {
        if(enum0 == null) {
            output0.writeByte(0);
            return;
        }
        output0.writeInt(enum0.ordinal() + 1, true);
    }

    private static int writeIntArray(Output output0, IntArray intArray0, boolean z) {
        int v = intArray0.size;
        output0.writeInt(v, true);
        int v1 = intArray0.size;
        for(int v2 = 0; v2 < v1; ++v2) {
            output0.writeInt(intArray0.get(v2), z);
        }
        return v;
    }

    private static void writeMonsterAbility(Output output0, MonsterAbility monsterAbility0) {
        output0.writeInt((monsterAbility0 == null ? 0 : monsterAbility0.id + 1), true);
    }

    private static void writeMonsterRow(Output output0, MonsterRow monsterRow0) {
        output0.writeInt(-(monsterRow0.data.id + 1), true);
        output0.writeInt(monsterRow0.level, true);
        output0.writeBoolean(monsterRow0.hasNormal);
        output0.writeBoolean(monsterRow0.hasElite);
        Serialization.writeMonsterAbility(output0, monsterRow0.ability);
        Serialization.writeRow(output0, monsterRow0);
    }

    private static void writePlayerRow(Output output0, PlayerRow playerRow0) {
        Player player0 = playerRow0.player;
        if(player0.name.equals(player0.characterClass.toString())) {
            output0.writeString(null);
        }
        else {
            output0.writeString(player0.name);
        }
        Serialization.writeEnum(output0, player0.characterClass);
        output0.writeInt(player0.xp, true);
        output0.writeInt(player0.hp, true);
        output0.writeInt(player0.hpMax, true);
        output0.writeInt(player0.level, true);
        output0.writeInt(player0.loot, true);
        output0.writeInt(player0.init, true);
        Serialization.writeEnumArray(output0, player0.conditions);
        Serialization.writeEnumArray(output0, player0.expiredConditions);
        Serialization.writeEnumArray(output0, player0.currentTurnConditions);
        output0.writeBoolean(player0.exhausted);
        Serialization.writeRow(output0, playerRow0);
    }

    private static void writeRow(Output output0, Row row0) {
        output0.writeBoolean(row0.turnComplete);
        int v = Serialization.writeArrayStart(output0, row0.boxes);
        for(int v1 = 0; v1 < v; ++v1) {
            Monster monster0 = ((MonsterBox)row0.boxes.get(v1)).monster;
            output0.writeInt(monster0.number, true);
            Serialization.writeEnum(output0, monster0.type);
            if(monster0.type == MonsterType.summon) {
                Serialization.writeEnum(output0, monster0.summonColor);
                output0.writeInt(monster0.summonMove, true);
                output0.writeInt(monster0.summonAttack, true);
                output0.writeInt(monster0.summonRange, true);
            }
            output0.writeBoolean(monster0.isNew);
            output0.writeInt(monster0.hp, true);
            output0.writeInt(monster0.hpMax, true);
            Serialization.writeEnumArray(output0, monster0.conditions);
            Serialization.writeEnumArray(output0, monster0.expiredConditions);
            Serialization.writeEnumArray(output0, monster0.currentTurnConditions);
        }
    }
}

