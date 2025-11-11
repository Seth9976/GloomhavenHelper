package com.esotericsoftware.gloomhavenhelper;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array.ArrayIterator;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.SnapshotArray;
import com.esotericsoftware.gloomhavenhelper.model.Condition;
import com.esotericsoftware.gloomhavenhelper.model.Monster;
import com.esotericsoftware.gloomhavenhelper.model.MonsterAbility;
import com.esotericsoftware.gloomhavenhelper.model.MonsterAbilityDeck;
import com.esotericsoftware.gloomhavenhelper.model.MonsterData;
import com.esotericsoftware.gloomhavenhelper.model.MonsterType;
import com.esotericsoftware.gloomhavenhelper.model.SummonColor;
import com.esotericsoftware.gloomhavenhelper.util.Animator;
import com.esotericsoftware.gloomhavenhelper.util.Row;
import com.esotericsoftware.gloomhavenhelper.util.builders.ImageButtonBuilder;
import com.esotericsoftware.minlog.Log;

public class MonsterRow extends Row {
    public MonsterAbility ability;
    Ability3D ability1;
    Ability3D ability2;
    MonsterAbilityCard abilityCard;
    Stack abilityCardStack;
    Button addEliteButton;
    Button addNormalButton;
    public MonsterData data;
    float glowAlpha;
    public boolean hasElite;
    public boolean hasNormal;
    Image image;
    Stack imageStack;
    public int level;
    String levelString;
    private Label nameLabel;
    boolean oozeSplit;
    TextButton oozeSplitButton;
    Container oozeSplitContainer;
    private MonsterStatsCard statsTable;

    public MonsterRow(MonsterData monsterData0, int v) {
        this.levelString = "";
        if(monsterData0 == null) {
            throw new IllegalArgumentException("data cannot be null.");
        }
        this.data = monsterData0;
        int v1 = v > 7 ? 7 : v;
        this.level = v1;
        this.levelString = v1 + "";
        this.create();
        this.layoutUI();
        this.events();
        this.setTransform(true);
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Row
    public void act(float f) {
        super.act(f);
        if(!App.state.ignoreChanges) {
            this.glowAlpha = App.animate(this.glowAlpha, (this.isCurrentTurn() ? 1.0f : 0.0f), 0.2f, 2.0f, 2.0f, f);
        }
    }

    private void create() {
        this.image = new Image() {
            TextureRegion glowRegion;

            {
                this.glowRegion = App.skin.getRegion("monsters/monster-glow");
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.ui.Image
            public void draw(Batch batch0, float f) {
                super.draw(batch0, f);
                if(MonsterRow.this.glowAlpha != 0.0f) {
                    batch0.setColor(1.0f, 1.0f, 1.0f, MonsterRow.this.glowAlpha * f);
                    batch0.draw(this.glowRegion, this.getX() - 16.0f, this.getY() - 13.0f);
                }
            }
        };
        try {
            String s = this.data.english.replaceAll(" \\([^)]+\\)", "");
            if(s.startsWith("Common ")) {
                s = s.substring(7);
            }
            else if(s.startsWith("Basic ")) {
                s = s.substring(6);
            }
            if(Gdx.app.getType() == ApplicationType.Desktop) {
                FileHandle fileHandle0 = new FileHandle("monsters/" + s + ".png");
                if(fileHandle0.exists()) {
                    try {
                        this.image.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(fileHandle0))));
                    }
                    catch(RuntimeException runtimeException0) {
                        Log.error("ghh", "Unable to read image: " + fileHandle0, runtimeException0);
                        goto label_14;
                    }
                }
                else {
                label_14:
                    this.image.setDrawable(App.skin.getDrawable("monsters/" + s));
                }
            }
            else {
                this.image.setDrawable(App.skin.getDrawable("monsters/" + s));
            }
        }
        catch(RuntimeException unused_ex) {
        }
        this.nameLabel = new Label(this.data.display, App.skin, "fancyLargeOutline", Color.WHITE);
        this.nameLabel.setWrap(true);
        this.nameLabel.setAlignment(1);
        this.imageStack = new Stack() {
            @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
            public void draw(Batch batch0, float f) {
                MonsterRow.this.beginDesat(batch0);
                super.draw(batch0, f);
            }
        };
        ImageButtonBuilder imageButtonBuilder0 = App.imageButton().imageUp("psd/add", App.buttonGray).imageOver("psd/add").imageDisabled("psd/add", App.disabledGray).imageChecked("psd/add", Color.WHITE);
        this.addNormalButton = imageButtonBuilder0.create();
        this.addEliteButton = imageButtonBuilder0.create();
        this.abilityCard = new MonsterAbilityCard(this, true);
        this.abilityCardStack = new Stack(new Actor[]{this.abilityCard});
        this.oozeSplitButton = App.textButton("Split").create();
        this.oozeSplitButton.addListener(App.tooltip("Applies wound if needed, then if not stunned, applies 2 damage and spawns new Oozes."));
        this.oozeSplitButton.getLabelCell().padRight(10.0f).padTop(2.0f);
        this.oozeSplitContainer = new Container(this.oozeSplitButton).top().right();
        this.ability1 = new Ability3D(this.data.deckID, this.abilityCardStack);
        this.ability2 = new Ability3D(this.data.deckID, this.abilityCardStack);
        this.statsTable = new MonsterStatsCard(this);
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Row
    public float desat() {
        return this.isAlive() ? super.desat() : 1.0f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Table
    public void draw(Batch batch0, float f) {
        this.updateExtraButtons();
        HorizontalGroup horizontalGroup0 = App.state.trackStandees ? this.monstersGroup : null;
        boolean z = false;
        if(this.monstersCell.getActor() != horizontalGroup0) {
            this.monstersCell.setActor(horizontalGroup0);
            this.abilityCardStack.toFront();
            this.monstersGroup.toBack();
            if(App.state.trackStandees) {
                this.addNormalButton.setChecked(false);
                this.addEliteButton.setChecked(false);
            }
        }
        if(!App.state.trackStandees) {
            this.addNormalButton.setChecked(this.hasNormal);
            this.addEliteButton.setChecked(this.hasElite);
        }
        if(this.boxes.size >= this.data.count && App.state.trackStandees) {
            z = true;
        }
        this.addNormalButton.setDisabled(z);
        this.addEliteButton.setDisabled(z);
        super.draw(batch0, f);
        this.endDesat(batch0);
    }

    private void events() {
        this.dragStartActor = this.imageStack;
        this.imageStack.addListener(this.dragListener);
        this.imageStack.addListener(new ClickListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent0, float f, float f1) {
                if(!MonsterRow.this.isAlive()) {
                    return;
                }
                if(!App.state.canDraw) {
                    if(MonsterRow.this.turnComplete) {
                        MonsterRow.this.turnEndRevert();
                    }
                    else {
                        MonsterRow.this.turnEnd(false);
                    }
                    App.state.changed();
                }
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void touchDragged(InputEvent inputEvent0, float f, float f1, int v) {
                super.touchDragged(inputEvent0, f, f1, v);
                if(MonsterRow.this.dragListener.isDragging()) {
                    this.cancel();
                }
            }
        });
        this.addNormalButton.addListener(new InputListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean handle(Event event0) {
                event0.stop();
                return super.handle(event0);
            }
        });
        this.addNormalButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                if(!App.state.trackStandees) {
                    MonsterRow.this.hasNormal ^= true;
                    if(!App.state.canDraw && MonsterRow.this.getAbilityDeck().shownAbility == null) {
                        MonsterRow.this.showAbility();
                    }
                    App.state.changed();
                    return;
                }
                MonsterRow.this.addNormalButton.setChecked(false);
                IntArray intArray0 = MonsterRow.this.freeNumbers();
                if(intArray0.size == 1) {
                    int v = intArray0.first();
                    MonsterRow.this.addMonsterBox(v, MonsterRow.this.data, MonsterRow.this.level, false, false, SummonColor.blue, true);
                    App.state.changed();
                    return;
                }
                if(App.state.randomStandees) {
                    int v1 = intArray0.random();
                    MonsterRow.this.addMonsterBox(v1, MonsterRow.this.data, MonsterRow.this.level, false, false, SummonColor.blue, true);
                    App.state.changed();
                    return;
                }
                MonsterAddMenu monsterAddMenu0 = new MonsterAddMenu(MonsterRow.this, intArray0, false);
                monsterAddMenu0.setBackgroundOffset(2.0f, 5.0f, -24.0f, -27.0f);
                monsterAddMenu0.show(MonsterRow.this.addNormalButton, 19.0f, -22.0f, -41.0f, 0.0f, true);
            }
        });
        this.addEliteButton.addListener(new InputListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean handle(Event event0) {
                event0.stop();
                return super.handle(event0);
            }
        });
        this.addEliteButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                if(!App.state.trackStandees) {
                    MonsterRow.this.hasElite ^= true;
                    if(!App.state.canDraw && MonsterRow.this.getAbilityDeck().shownAbility == null) {
                        MonsterRow.this.showAbility();
                    }
                    App.state.changed();
                    return;
                }
                MonsterRow.this.addEliteButton.setChecked(false);
                IntArray intArray0 = MonsterRow.this.freeNumbers();
                if(intArray0.size == 1) {
                    int v = intArray0.first();
                    MonsterRow.this.addMonsterBox(v, MonsterRow.this.data, MonsterRow.this.level, true, false, SummonColor.blue, true);
                    App.state.changed();
                    return;
                }
                if(App.state.randomStandees) {
                    int v1 = intArray0.random();
                    MonsterRow.this.addMonsterBox(v1, MonsterRow.this.data, MonsterRow.this.level, true, false, SummonColor.blue, true);
                    App.state.changed();
                    return;
                }
                MonsterAddMenu monsterAddMenu0 = new MonsterAddMenu(MonsterRow.this, intArray0, true);
                monsterAddMenu0.setBackgroundOffset(24.0f, 5.0f, -2.0f, -27.0f);
                monsterAddMenu0.show(MonsterRow.this.addEliteButton, 41.0f, -22.0f, -41.0f, 0.0f, false);
            }
        });
        this.statsTable.setTouchable(Touchable.enabled);
        this.statsTable.addListener(new ClickListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent0, float f, float f1) {
                if(!App.state.trackStandees) {
                    new MonsterBlessCurseMenu(MonsterRow.this).show(MonsterRow.this.image, 0.0f, 0.0f, 0.0f, 0.0f, true);
                }
            }
        });
        this.oozeSplitButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                MonsterRow.this.oozeSplit = true;
                App.state.ignoreChanges = true;
                Array array0 = new Array(MonsterRow.this.boxes);
                boolean z = App.state.elitesFirst;
                App.state.elitesFirst = true;
                array0.sort();
                App.state.elitesFirst = z;
                for(Object object0: array0) {
                    MonsterBox monsterBox0 = (MonsterBox)object0;
                    boolean z1 = monsterBox0.monster.conditions.contains(Condition.wound, true);
                    boolean z2 = monsterBox0.monster.conditions.contains(Condition.regenerate, true);
                    if(z1 && z2) {
                        monsterBox0.flashIcon("wound");
                    }
                    else if(z2) {
                        monsterBox0.flashIcon("regenerate");
                    }
                    else if(z1) {
                        monsterBox0.flashIcon("wound");
                    }
                    if(!monsterBox0.monster.conditions.contains(Condition.stun, true)) {
                        monsterBox0.lastAnimateIcon = System.currentTimeMillis();
                        monsterBox0.hpAdjust.adjust((z1 ? -3 : -2));
                        monsterBox0.checkDead(false);
                        if(monsterBox0.monster.hp > 0) {
                            IntArray intArray0 = MonsterRow.this.freeNumbers();
                            if(!intArray0.isEmpty()) {
                                intArray0.shuffle();
                                int v = intArray0.removeIndex(0);
                                MonsterBox monsterBox1 = MonsterRow.this.addMonsterBox(v, MonsterRow.this.data, MonsterRow.this.level, false, true, SummonColor.blue, false);
                                monsterBox1.monster.hp = Math.min(monsterBox0.monster.hp, monsterBox1.monster.hpMax);
                            }
                        }
                    }
                    else if(z1) {
                        monsterBox0.hpAdjust.adjust(-1);
                    }
                }
                App.state.ignoreChanges = false;
                App.state.changed();
            }
        });
    }

    private void flipStore1() {
        this.desatDisabled = true;
        if(App.state.abilityCards) {
            this.ability1.update(App.stage.getBatch(), this.abilityCardStack, 1.0f);
            this.ability1.start(false);
        }
        this.addActor(this.ability1);
        this.addActor(this.ability2);
    }

    private void flipStore2() {
        this.updateExtraButtons();
        if(App.state.abilityCards) {
            this.ability2.update(App.stage.getBatch(), this.abilityCardStack, 1.0f);
            this.ability2.start(true);
        }
        this.abilityCardStack.setVisible(false);
        this.desatDisabled = false;
    }

    IntArray freeNumbers() {
        IntArray intArray0 = new IntArray();
        int v = this.boxes.size;
        int v1 = this.data.count;
        int v2 = 1;
        while(v2 <= v1) {
            int v3 = 0;
            while(true) {
                if(v3 < v) {
                    if(v2 == ((MonsterBox)this.boxes.get(v3)).monster.number) {
                        break;
                    }
                    else {
                        ++v3;
                        continue;
                    }
                }
                intArray0.add(v2);
                break;
            }
            ++v2;
        }
        return intArray0;
    }

    public MonsterAbilityDeck getAbilityDeck() {
        return App.state.getAbilityDeck(this.data.deckID);
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Row
    public int getInitiative() {
        if(!this.isAlive()) {
            return 101;
        }
        if(App.state.canDraw) {
            return 100;
        }
        if(this.ability == null) {
            return 100;
        }
        return App.state.abilityCards ? this.ability.initiative : 100;
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Row
    public int getLevel() {
        return this.level;
    }

    // 去混淆评级： 低(30)
    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Table
    public float getPrefHeight() {
        return !App.state.trackStandees || !MonsterRow.animatedHeight || !Animator.enabled || this.animateHeight == 0.0f ? super.getPrefHeight() : this.animateHeight;
    }

    public boolean hasElite() {
        if(!App.state.trackStandees) {
            return this.hasElite;
        }
        if(this.data.isBoss()) {
            return false;
        }
        for(Object object0: this.boxes) {
            if(((MonsterBox)object0).monster.type == MonsterType.elite) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Row
    public boolean hasExpiringConditions() {
        for(Object object0: this.boxes) {
            MonsterBox monsterBox0 = (MonsterBox)object0;
            for(int v = monsterBox0.monster.conditions.size - 1; v >= 0; --v) {
                switch(com.esotericsoftware.gloomhavenhelper.MonsterRow.10.$SwitchMap$com$esotericsoftware$gloomhavenhelper$model$Condition[((Condition)monsterBox0.monster.conditions.get(v)).ordinal()]) {
                    case 1: 
                    case 2: 
                    case 3: 
                    case 4: 
                    case 5: 
                    case 6: {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean hasNormal() {
        if(!App.state.trackStandees) {
            return this.hasNormal;
        }
        if(this.data.isBoss()) {
            return this.boxes.size > 0;
        }
        for(Object object0: this.boxes) {
            if(((MonsterBox)object0).monster.type == MonsterType.normal) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    // 去混淆评级： 低(40)
    @Override  // com.esotericsoftware.gloomhavenhelper.util.Row
    public boolean isAlive() {
        return App.state.trackStandees ? this.boxes.size > 0 : this.addNormalButton.isChecked() || this.addEliteButton.isChecked();
    }

    private void layoutUI() {
        this.statsTable.defaults().bottom();
        this.statsTable.add(this.addNormalButton).size(102.0f).expand().left();
        if(!this.data.isBoss()) {
            this.statsTable.add(this.addEliteButton).size(102.0f);
        }
        this.addNormalButton.padRight(23.0f).padTop(22.0f);
        this.addEliteButton.padLeft(23.0f).padTop(22.0f);
        this.imageStack.add(new Container(this.image).size(207.0f, 209.0f).top());
        Container container0 = new Container(this.nameLabel).bottom().fillX();
        if(App.config.isRussian() || this.data.english.equals("Manifestation of Corruption")) {
            container0.padLeft(-10.0f).padRight(-10.0f);
        }
        this.imageStack.add(container0);
        this.row().padBottom(-4.0f);
        this.add(this.imageStack).fill();
        this.add(this.abilityCardStack).padLeft(6.0f);
        this.add(this.statsTable).padLeft(6.0f).row();
        this.add();
        this.monstersCell = this.add(this.monstersGroup).colspan(2).pad(0.0f, 5.0f, 0.0f, 5.0f).fillX();
        this.abilityCardStack.toFront();
        this.monstersGroup.toBack();
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Row
    protected void monsterAdded() {
        int v;
        boolean z = true;
        ArrayIterator array$ArrayIterator0 = this.boxes.iterator();
        while(true) {
            v = 0;
            if(!array$ArrayIterator0.hasNext()) {
                break;
            }
            Object object0 = array$ArrayIterator0.next();
            if(!((MonsterBox)object0).monster.conditions.contains(Condition.summonNew, true)) {
                z = false;
                break;
            }
        }
        if(!z && !App.state.canDraw && this.getAbilityDeck().shownAbility == null) {
            this.showAbility();
            if(App.state.abilityCards) {
                int v1 = this.ability.initiative;
                SnapshotArray snapshotArray0 = App.gloom.rows.getChildren();
                snapshotArray0.removeValue(this, true);
                int v2 = snapshotArray0.size;
                int v3 = 0;
                while(true) {
                    if(v >= v2) {
                        v = v3;
                        break;
                    }
                    Row row0 = (Row)snapshotArray0.get(v);
                    if(row0.isAlive()) {
                        if(row0.getInitiative() > v1) {
                            break;
                        }
                        v3 = v + 1;
                    }
                    ++v;
                }
                snapshotArray0.insert(v, this);
            }
            Animator.storeChildren(App.gloom.rows, 0.5f);
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    public boolean remove() {
        this.getAbilityDeck().shownAbility = null;
        return super.remove();
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Row
    public void roundEnd() {
        MonsterAbilityDeck monsterAbilityDeck0 = this.getAbilityDeck();
        if(monsterAbilityDeck0.shownAbility != null) {
            this.flipStore1();
            int v = App.gloom.monsterRows.size;
            for(int v1 = 0; v1 < v; ++v1) {
                MonsterRow monsterRow0 = (MonsterRow)App.gloom.monsterRows.get(v1);
                if(monsterRow0 != this && monsterRow0.data.deckID == monsterAbilityDeck0.id) {
                    monsterRow0.flipStore1();
                }
            }
            monsterAbilityDeck0.shownAbility = null;
            this.flipStore2();
            int v2 = App.gloom.monsterRows.size;
            for(int v3 = 0; v3 < v2; ++v3) {
                MonsterRow monsterRow1 = (MonsterRow)App.gloom.monsterRows.get(v3);
                if(monsterRow1 != this && monsterRow1.data.deckID == monsterAbilityDeck0.id) {
                    monsterRow1.flipStore2();
                }
            }
        }
        if(monsterAbilityDeck0.shuffle) {
            monsterAbilityDeck0.abilities.clear();
            MonsterAbilityDeck monsterAbilityDeck1 = App.findMonsterAbilityDeck(this.data.deckID);
            monsterAbilityDeck0.abilities.addAll(monsterAbilityDeck1.abilities);
            App.state.removeAbilities(monsterAbilityDeck0.abilities);
            monsterAbilityDeck0.abilities.shuffle();
            monsterAbilityDeck0.abilitiesDiscard.clear();
            monsterAbilityDeck0.shuffle = false;
        }
        this.ability = null;
        super.roundEnd();
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Row
    public void setLevel(int v) {
        if(v > 7) {
            v = 7;
        }
        this.level = v;
        this.levelString = v + "";
        for(Object object0: this.boxes) {
            Monster monster0 = ((MonsterBox)object0).monster;
            int v1 = monster0.hpMax;
            monster0.stats = monster0.data.stats[monster0.type.ordinal()][v];
            monster0.hpMax = monster0.stats.hpMax();
            if(monster0.hp >= v1 || v1 == 0) {
                monster0.hp = monster0.hpMax;
            }
        }
        App.state.changed();
    }

    private void showAbility(boolean z, MonsterAbilityDeck monsterAbilityDeck0) {
        if(this.ability != null) {
            return;
        }
        if(monsterAbilityDeck0.shownAbility == null) {
            int v = App.gloom.monsterRows.size;
            for(int v1 = 0; v1 < v; ++v1) {
                MonsterRow monsterRow0 = (MonsterRow)App.gloom.monsterRows.get(v1);
                if(monsterRow0.data.deckID == monsterAbilityDeck0.id) {
                    monsterRow0.flipStore1();
                }
            }
        }
        if(monsterAbilityDeck0.shownAbility == null || this.data.isBoss()) {
            Array array0 = monsterAbilityDeck0.abilities;
            if(array0.size == 0) {
                array0.clear();
                array0.addAll(App.findMonsterAbilityDeck(monsterAbilityDeck0.id).abilities);
                App.state.removeAbilities(monsterAbilityDeck0.abilities);
                array0.shuffle();
                monsterAbilityDeck0.abilitiesDiscard.clear();
            }
            this.ability = (MonsterAbility)array0.pop();
            monsterAbilityDeck0.abilitiesDiscard.add(this.ability);
            if(!monsterAbilityDeck0.shuffle) {
                monsterAbilityDeck0.shuffle = this.ability.shuffle || array0.isEmpty();
            }
        }
        else {
            this.ability = monsterAbilityDeck0.shownAbility;
        }
        if(monsterAbilityDeck0.shownAbility == null) {
            monsterAbilityDeck0.shownAbility = this.ability;
            int v2 = App.gloom.monsterRows.size;
            for(int v3 = 0; v3 < v2; ++v3) {
                MonsterRow monsterRow1 = (MonsterRow)App.gloom.monsterRows.get(v3);
                if(monsterRow1.data.deckID == monsterAbilityDeck0.id) {
                    if(z && monsterRow1 != this) {
                        monsterRow1.showAbility(false, monsterAbilityDeck0);
                    }
                    monsterRow1.flipStore2();
                }
            }
        }
    }

    public void showAbility() {
        if(!this.isAlive()) {
            return;
        }
        this.showAbility(true, this.getAbilityDeck());
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public String toString() {
        return this.data.name;
    }

    private void updateExtraButtons() {
        boolean z = false;
        if(this.ability != null && (this.ability.id == 0x9C || this.ability.id == 0x9D || !App.state.abilityCards && this.ability.deck.name.equals("Ooze"))) {
            TextButton textButton0 = this.oozeSplitButton;
            if(this.oozeSplit || this.desat() != 0.0f) {
                z = true;
            }
            textButton0.setDisabled(z);
            this.abilityCardStack.addActor(this.oozeSplitContainer);
            return;
        }
        this.oozeSplit = false;
        Group group0 = this.oozeSplitContainer.getParent();
        Stack stack0 = this.abilityCardStack;
        if(group0 == stack0) {
            stack0.removeActor(this.oozeSplitContainer);
        }
    }
}

