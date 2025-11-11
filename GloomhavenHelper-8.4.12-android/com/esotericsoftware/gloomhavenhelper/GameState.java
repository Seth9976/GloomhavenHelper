package com.esotericsoftware.gloomhavenhelper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.SnapshotArray;
import com.esotericsoftware.gloomhavenhelper.model.AttackModifier;
import com.esotericsoftware.gloomhavenhelper.model.ElementState;
import com.esotericsoftware.gloomhavenhelper.model.MonsterAbility;
import com.esotericsoftware.gloomhavenhelper.model.MonsterAbilityDeck;
import com.esotericsoftware.gloomhavenhelper.model.PlayerInit;
import com.esotericsoftware.gloomhavenhelper.util.Menu;
import com.esotericsoftware.gloomhavenhelper.util.Row;

public class GameState {
    public boolean abilityCards;
    public IntMap abilityDecks;
    public ElementState air;
    public final Array attackModifiers;
    public final Array attackModifiersDiscard;
    public int badOmen;
    public boolean calculateStats;
    public boolean canDraw;
    public transient int changeNumber;
    public ElementState dark;
    public ElementState earth;
    public boolean elitesFirst;
    public boolean expireConditions;
    public ElementState fire;
    public boolean hideStats;
    public ElementState ice;
    public transient boolean ignoreChanges;
    public boolean jotl;
    public ElementState light;
    public boolean needsShuffle;
    public PlayerInit playerInit;
    public boolean randomStandees;
    public IntArray removedAbilities;
    public int round;
    public int scenarioLevel;
    public int scenarioNumber;
    public boolean solo;
    public boolean trackStandees;

    public GameState() {
        this.round = 1;
        this.scenarioNumber = -1;
        this.trackStandees = true;
        this.abilityCards = true;
        this.elitesFirst = true;
        this.hideStats = true;
        this.calculateStats = true;
        this.canDraw = true;
        this.playerInit = PlayerInit.numpad;
        this.attackModifiers = new Array();
        this.attackModifiersDiscard = new Array();
        this.fire = ElementState.inert;
        this.ice = ElementState.inert;
        this.air = ElementState.inert;
        this.earth = ElementState.inert;
        this.light = ElementState.inert;
        this.dark = ElementState.inert;
        this.abilityDecks = new IntMap();
        this.removedAbilities = new IntArray();
    }

    public void add(AttackModifier attackModifier0) {
        this.attackModifiers.add(attackModifier0);
    }

    public void addCurse() {
        int v = this.badOmen;
        if(v <= 0) {
            App.state.add(AttackModifier.curse);
            this.attackModifiers.shuffle();
            return;
        }
        this.badOmen = v - 1;
        this.attackModifiers.insert(Math.max(0, this.attackModifiers.size - 5), AttackModifier.curse);
        App.toast("Bad Omen placed the curse");
        App.toast("6th from the top.");
    }

    public void apply(boolean z) {
        this.ignoreChanges = true;
        for(Object object0: App.gloom.monsterRows) {
            ((MonsterRow)object0).boxes.sort();
            ((MonsterRow)object0).monstersGroup.getChildren().sort();
            ((MonsterRow)object0).monstersGroup.invalidate();
        }
        App.gloom.fireButton.setState(this.fire, z);
        App.gloom.iceButton.setState(this.ice, z);
        App.gloom.airButton.setState(this.air, z);
        App.gloom.earthButton.setState(this.earth, z);
        App.gloom.lightButton.setState(this.light, z);
        App.gloom.darkButton.setState(this.dark, z);
        AttackModifier attackModifier0 = this.attackModifier1();
        Row row0 = null;
        if(attackModifier0 == null) {
            App.gloom.attackImage1.setDrawable(null);
        }
        else {
            App.gloom.attackImage1.setDrawable(App.drawable(new String[]{"attack/" + attackModifier0, "attack/border"}));
        }
        AttackModifier attackModifier1 = this.attackModifier2();
        if(attackModifier1 == null) {
            App.gloom.attackImage2.setDrawable(null);
        }
        else {
            App.gloom.attackImage2.setDrawable(App.drawable(new String[]{"attack/" + attackModifier1, "attack/border"}));
        }
        SnapshotArray snapshotArray0 = App.stage.getRoot().getChildren();
        for(int v = snapshotArray0.size - 1; v >= 0; --v) {
            Actor actor0 = (Actor)snapshotArray0.get(v);
            if(actor0 instanceof PlayerRowMenu || actor0 instanceof MonsterBoxMenu || actor0 instanceof MonsterAddMenu) {
                ((Menu)actor0).hide();
            }
            else if(actor0 instanceof DeckDialog) {
                ((DeckDialog)actor0).hide();
            }
        }
        App.gloom.rows.act(9999.0f);
        Gdx.graphics.requestRendering();
        if(App.config.autoScroll) {
            int v1 = App.gloom.rows.getChildren().size - 1;
            while(v1 >= 0) {
                Row row1 = (Row)App.gloom.rows.getChild(v1);
                if(row1.turnComplete) {
                    break;
                }
                --v1;
                row0 = row1;
            }
            if(row0 != null) {
                App.root.validate();
                App.gloom.rowsScroll.scrollTo(row0.getX(), row0.getY(), row0.getWidth(), row0.getHeight(), false, true);
            }
        }
        this.ignoreChanges = false;
    }

    public AttackModifier attackModifier1() {
        return this.attackModifiersDiscard.size < 1 ? null : ((AttackModifier)this.attackModifiersDiscard.peek());
    }

    public AttackModifier attackModifier2() {
        return this.attackModifiersDiscard.size < 2 ? null : ((AttackModifier)this.attackModifiersDiscard.get(this.attackModifiersDiscard.size - 2));
    }

    public void changed() {
        this.changed(true, null);
    }

    public void changed(boolean z, byte[] arr_b) {
        if(this.ignoreChanges) {
            return;
        }
        this.fire = App.gloom.fireButton.elementState;
        this.ice = App.gloom.iceButton.elementState;
        this.air = App.gloom.airButton.elementState;
        this.earth = App.gloom.earthButton.elementState;
        this.light = App.gloom.lightButton.elementState;
        this.dark = App.gloom.darkButton.elementState;
        App.game.saveState(z, arr_b);
    }

    public int count(AttackModifier attackModifier0, boolean z) {
        int v = 0;
        for(Object object0: this.attackModifiers) {
            if(((AttackModifier)object0) == attackModifier0) {
                ++v;
            }
        }
        if(z) {
            for(Object object1: this.attackModifiersDiscard) {
                if(((AttackModifier)object1) == attackModifier0) {
                    ++v;
                }
            }
        }
        return v;
    }

    public MonsterAbilityDeck getAbilityDeck(int v) {
        MonsterAbilityDeck monsterAbilityDeck0 = (MonsterAbilityDeck)this.abilityDecks.get(v);
        if(monsterAbilityDeck0 == null) {
            monsterAbilityDeck0 = new MonsterAbilityDeck(App.findMonsterAbilityDeck(v));
            this.removeAbilities(monsterAbilityDeck0.abilities);
            monsterAbilityDeck0.abilities.shuffle();
            this.abilityDecks.put(v, monsterAbilityDeck0);
        }
        return monsterAbilityDeck0;
    }

    public void remove(AttackModifier attackModifier0) {
        int v = this.attackModifiers.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(this.attackModifiers.get(v1) == attackModifier0) {
                this.attackModifiers.removeIndex(v1);
                return;
            }
        }
        int v2 = this.attackModifiersDiscard.size;
        for(int v3 = 0; v3 < v2; ++v3) {
            if(this.attackModifiersDiscard.get(v3) == attackModifier0) {
                this.attackModifiersDiscard.removeIndex(v3);
                App.gloom.attackImage1.setDrawable(null);
                App.gloom.attackImage2.setDrawable(null);
                if(this.attackModifiersDiscard.size >= 1) {
                    App.gloom.attackImage1.setDrawable(App.drawable(new String[]{"attack/" + this.attackModifiersDiscard.peek(), "attack/border"}));
                    if(this.attackModifiersDiscard.size >= 2) {
                        App.gloom.attackImage2.setDrawable(App.drawable(new String[]{"attack/" + this.attackModifiersDiscard.get(this.attackModifiersDiscard.size - 2), "attack/border"}));
                    }
                }
                return;
            }
        }
    }

    public void removeAbilities(Array array0) {
        if(App.state.removedAbilities.isEmpty()) {
            return;
        }
        for(int v = array0.size - 1; v >= 0; --v) {
            if(App.state.removedAbilities.contains(((MonsterAbility)array0.get(v)).id)) {
                array0.removeIndex(v);
            }
        }
    }

    public void shuffleAttackModifiers(boolean z) {
        Array array0 = new Array();
        int v = this.count(AttackModifier.curse, false);
        for(int v1 = 0; v1 < v; ++v1) {
            array0.add(AttackModifier.curse);
        }
        int v2 = this.count(AttackModifier.bless, false);
        for(int v3 = 0; v3 < v2; ++v3) {
            array0.add(AttackModifier.bless);
        }
        int v4 = this.count(AttackModifier.minus1, true);
        for(int v5 = 0; v5 < v4 - 5; ++v5) {
            array0.add(AttackModifier.minus1);
        }
        this.attackModifiers.clear();
        this.attackModifiers.addAll(array0);
        this.attackModifiers.addAll(AttackModifier.baseAttackModifiers);
        this.attackModifiers.shuffle();
        this.attackModifiersDiscard.clear();
        this.needsShuffle = false;
    }
}

