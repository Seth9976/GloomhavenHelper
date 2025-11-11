package com.esotericsoftware.gloomhavenhelper.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragScrollListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import com.esotericsoftware.gloomhavenhelper.App;
import com.esotericsoftware.gloomhavenhelper.MonsterBox;
import com.esotericsoftware.gloomhavenhelper.model.Condition;
import com.esotericsoftware.gloomhavenhelper.model.Monster;
import com.esotericsoftware.gloomhavenhelper.model.MonsterData;
import com.esotericsoftware.gloomhavenhelper.model.MonsterType;
import com.esotericsoftware.gloomhavenhelper.model.PlayerInit;
import com.esotericsoftware.gloomhavenhelper.model.SummonColor;

public abstract class Row extends Table implements HasAnimator, Comparable {
    public float animateHeight;
    public static boolean animatedHeight = true;
    public Animator animator;
    public final Array boxes;
    public boolean desatDisabled;
    public DragListener dragListener;
    public Actor dragStartActor;
    public float finalX;
    public float finalY;
    public Cell monstersCell;
    public HorizontalGroup monstersGroup;
    private static final float readyDuration = 0.5f;
    public float readyTime;
    public boolean turnComplete;

    static {
    }

    public Row() {
        super(App.skin);
        this.animator = new Animator(this, 250.0f, 3000.0f, 500.0f);
        this.boxes = new Array();
        this.dragListener = new DragListener() {
            DragScrollListener dragScroll;

            {
                this.dragScroll = new DragScrollListener(App.gloom.rowsScroll);
                this.dragScroll.setup(30.0f, 75.0f, 0.05f, 0.9f);
                this.setTapSquareSize(34.0f);
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.DragListener
            public void drag(InputEvent inputEvent0, float f, float f1, int v) {
                App.gloom.updateDrag(inputEvent0.getStageX(), inputEvent0.getStageY());
                this.dragScroll.drag(inputEvent0, f, f1, v);
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.DragListener
            public void dragStart(InputEvent inputEvent0, float f, float f1, int v) {
                if(App.dragRow != null) {
                    return;
                }
                if(App.state.canDraw) {
                    App.stage.cancelTouchFocus();
                    return;
                }
                App.dragging = true;
                App.dragRow = Row.this;
                App.lastSwapRow = null;
                inputEvent0.getListenerActor().localToAscendantCoordinates(App.dragRow, App.dragStart.set(f, f1));
                App.stage.cancelTouchFocusExcept(this, Row.this.dragStartActor);
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.DragListener
            public void dragStop(InputEvent inputEvent0, float f, float f1, int v) {
                this.dragScroll.dragStop(inputEvent0, f, f1, v);
                App.state.changed();
                App.dragging = false;
                MoveToAction moveToAction0 = Actions.moveTo(Row.this.finalX, Row.this.finalY, 0.25f, Interpolation.fastSlow);
                moveToAction0.setActor(Row.this);
                App.stage.addAction(Actions.sequence(moveToAction0, new Action() {
                    @Override  // com.badlogic.gdx.scenes.scene2d.Action
                    public boolean act(float f) {
                        if(!App.dragging) {
                            App.dragRow = null;
                            Animator.storeChildren(App.gloom.rows, 0.0f);
                        }
                        return true;
                    }
                }));
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.DragListener
            public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                inputEvent0.stop();
                return super.touchDown(inputEvent0, f, f1, v, v1);
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.DragListener
            public void touchDragged(InputEvent inputEvent0, float f, float f1, int v) {
                if((App.state.playerInit == PlayerInit.dragNumber || App.state.playerInit == PlayerInit.dragNumberRequired) && App.dragRow == null) {
                    f = this.getTouchDownX();
                }
                super.touchDragged(inputEvent0, f, f1, v);
            }
        };
        this.monstersGroup = new HorizontalGroup() {
            @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
            public void draw(Batch batch0, float f) {
                Animator.updateChildren(this);
                super.draw(batch0, f);
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup
            public void invalidate() {
                if(!Animator.enabled || !Animator.childrenAnimating(this)) {
                    super.invalidate();
                }
            }
        }.space(9.0f).wrap().wrapSpace(4.0f).rowAlign(8).bottom();
        this.top();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public void act(float f) {
        super.act(f);
        this.readyTime -= f;
    }

    public MonsterBox addMonsterBox(int v, MonsterData monsterData0, int v1, boolean z, boolean z1, SummonColor summonColor0, boolean z2) {
        MonsterType monsterType0;
        if(monsterData0 == App.summonData) {
            monsterType0 = MonsterType.summon;
        }
        else if(monsterData0.isBoss()) {
            monsterType0 = MonsterType.boss;
        }
        else {
            monsterType0 = z ? MonsterType.elite : MonsterType.normal;
        }
        MonsterBox monsterBox0 = new MonsterBox(this, new Monster(monsterData0, monsterType0, v1, v), this.monstersGroup.getScaleX());
        monsterBox0.monster.summonColor = summonColor0;
        if(z1) {
            monsterBox0.monster.conditions.add(Condition.summonNew);
        }
        if(App.state.scenarioNumber == 49 && z && monsterData0.english.equals("Ancient Artillery")) {
            monsterBox0.monster.hpMax *= 2;
            monsterBox0.monster.hp = monsterBox0.monster.hpMax;
            monsterBox0.monster.conditions.add(Condition.star);
        }
        if(App.state.scenarioNumber == 57 && z && monsterData0.english.equals("Harrower Infester")) {
            monsterBox0.monster.conditions.add(Condition.star);
        }
        if(App.state.scenarioNumber == 58 && z && monsterData0.english.equals("City Guard")) {
            monsterBox0.monster.conditions.add(Condition.star);
        }
        if(App.state.scenarioNumber == 67 && z && monsterData0.english.equals("Stone Golem")) {
            monsterBox0.monster.hpMax *= App.gloom.playerCount();
            monsterBox0.monster.hp = monsterBox0.monster.hpMax;
            monsterBox0.monster.conditions.add(Condition.star);
        }
        if(App.state.scenarioNumber == 110 && z) {
            if(monsterData0.english.equals("Vermling Shaman") || monsterData0.english.equals("City Guard") || monsterData0.english.equals("Savvas Lavaflow")) {
                monsterBox0.monster.hpMax = (int)Math.ceil(App.gloom.playerCount() * monsterBox0.monster.hpMax / 2);
                monsterBox0.monster.hp = monsterBox0.monster.hpMax;
                monsterBox0.monster.conditions.add(Condition.star);
            }
            else if(monsterData0.english.equals("Valrath Tracker")) {
                monsterBox0.monster.conditions.add(Condition.star);
            }
        }
        if(App.state.scenarioNumber == 0x6F && z && monsterData0.english.equals("Aesther Ashblade")) {
            monsterBox0.monster.hpMax *= App.gloom.playerCount();
            monsterBox0.monster.hp = monsterBox0.monster.hpMax;
            monsterBox0.monster.conditions.add(Condition.star);
        }
        monsterBox0.monster.conditions.sort();
        Animator.enabled = false;
        float f = this.getPrefHeight();
        this.boxes.add(monsterBox0);
        if(z2) {
            this.boxes.sort();
        }
        this.monstersGroup.addActor(monsterBox0);
        if(z2) {
            this.monstersGroup.getChildren().sort();
        }
        float f1 = this.getPrefHeight();
        Animator.enabled = true;
        if(f != f1) {
            this.animateHeight = f;
            App.gloom.rows.addAction(Actions.sequence(new TemporalAction(0.5f) {
                @Override  // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
                protected void end() {
                    Row.this.animateHeight = 0.0f;
                    Row.this.invalidateHierarchy();
                }

                @Override  // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
                protected void update(float f) {
                    Row.this.animateHeight = Interpolation.fastSlow.apply(f, f1, f);
                    Row.this.invalidateHierarchy();
                }
            }));
        }
        Animator.storeChildren(this.monstersGroup, 0.0f);
        this.monsterAdded();
        monsterBox0.animator.finish(false);
        monsterBox0.animator.store(0.0f);
        monsterBox0.getColor().a = 0.0f;
        monsterBox0.addAction(Actions.fadeIn(0.45f, Interpolation.fastSlow));
        monsterBox0.moveBy(0.0f, (monsterType0 == MonsterType.summon ? -80.0f : 80.0f));
        return monsterBox0;
    }

    public void animateIcon(Actor actor0, String s, float f, float f1, float f2) {
        Vector2 vector20 = actor0.localToStageCoordinates(App.v2.set(f1, f2));
        Container container0 = new Container(App.image(s));
        container0.setTouchable(Touchable.disabled);
        container0.setPosition(vector20.x, vector20.y);
        container0.setTransform(true);
        container0.setOrigin(container0.getWidth() / 2.0f, container0.getHeight() / 2.0f);
        container0.setScale(0.3f, 0.3f);
        container0.getColor().a = 0.0f;
        container0.addAction(Actions.sequence(Actions.parallel(Actions.fadeIn(0.2f), Actions.scaleTo(f, f, 0.4f, Interpolation.fastSlow)), Actions.parallel(Actions.sequence(Actions.delay(0.1f), Actions.fadeOut(0.3f)), Actions.scaleTo(1.0f, 1.0f, 0.4f, Interpolation.slowFast)), Actions.removeActor()));
        App.stage.addActor(container0);
    }

    public void beginDesat(Batch batch0) {
        if(this.readyTime > 0.0f) {
            Gdx.graphics.requestRendering();
        }
        float f = this.desat();
        if(f > 0.0f) {
            batch0.setShader(App.desatShader);
            App.desatShader.setUniformf("u_desat", (this.turnComplete ? Interpolation.fastSlow.apply(f) : Interpolation.slowFast.apply(f)));
        }
    }

    public int compareTo(Row row0) {
        return this.getInitiative() - row0.getInitiative();
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((Row)object0));
    }

    public float desat() {
        if(this.desatDisabled) {
            return 0.0f;
        }
        float f = Math.min(1.0f, 1.0f - this.readyTime / 0.5f);
        return this.turnComplete ? f : 1.0f - f;
    }

    public void endDesat(Batch batch0) {
        if(this.desat() > 0.0f) {
            batch0.setShader(null);
        }
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Animator$HasAnimator
    public Animator getAnimator() {
        return this.animator;
    }

    public abstract int getInitiative();

    public abstract int getLevel();

    public abstract boolean hasExpiringConditions();

    public abstract boolean isAlive();

    public boolean isCurrentTurn() {
        if(this.turnComplete) {
            return false;
        }
        if(!this.isAlive()) {
            return false;
        }
        SnapshotArray snapshotArray0 = App.gloom.rows.getChildren();
        int v = snapshotArray0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            Row row0 = (Row)snapshotArray0.get(v1);
            if(row0 == this) {
                return true;
            }
            if(!row0.turnComplete && row0.isAlive()) {
                return false;
            }
        }
        return false;
    }

    protected void monsterAdded() {
    }

    public void roundEnd() {
        this.setTurnComplete(false, true);
        for(Object object0: this.boxes) {
            MonsterBox monsterBox0 = (MonsterBox)object0;
            monsterBox0.animator.finish(false);
            monsterBox0.animator.store(0.0f);
            monsterBox0.monster.isNew = false;
            monsterBox0.monster.expiredConditions.clear();
            monsterBox0.monster.currentTurnConditions.clear();
            if(monsterBox0.monster.conditions.contains(Condition.summonNew, true)) {
                monsterBox0.monster.conditions.removeValue(Condition.summonNew, true);
                monsterBox0.monster.conditions.add(Condition.summon);
                monsterBox0.monster.conditions.sort();
            }
        }
        this.boxes.sort();
        this.monstersGroup.getChildren().sort();
        this.monstersGroup.invalidate();
        Animator.storeChildren(this.monstersGroup, 0.0f);
    }

    public abstract void setLevel(int arg1);

    public void setTurnComplete(boolean z, boolean z1) {
        if(this.turnComplete == z) {
            return;
        }
        this.turnComplete = z;
        if(z1) {
            this.readyTime = 0.5f;
            return;
        }
        this.readyTime = 0.0f;
    }

    public void turnEnd(boolean z) {
        if(this.turnComplete) {
            return;
        }
        SnapshotArray snapshotArray0 = App.gloom.rows.getChildren();
        if(!z) {
            int v1 = snapshotArray0.size;
            for(int v = 0; v < v1; ++v) {
                Row row0 = (Row)snapshotArray0.get(v);
                if(row0 == this) {
                    break;
                }
                if(!row0.turnComplete) {
                    row0.turnEnd(false);
                }
            }
            if(this.isAlive()) {
                this.setTurnComplete(true, true);
            }
        }
        if(this.turnComplete) {
            for(int v2 = this.getZIndex() + 1; v2 < snapshotArray0.size; ++v2) {
                Row row1 = (Row)snapshotArray0.get(v2);
                if(row1.isAlive() && !row1.turnComplete) {
                    row1.turnStart(z);
                    break;
                }
            }
        }
        if(App.state.expireConditions) {
            for(Object object0: this.boxes) {
                MonsterBox monsterBox0 = (MonsterBox)object0;
                for(int v3 = monsterBox0.monster.conditions.size - 1; v3 >= 0; --v3) {
                    Condition condition0 = (Condition)monsterBox0.monster.conditions.get(v3);
                    switch(com.esotericsoftware.gloomhavenhelper.util.Row.4.$SwitchMap$com$esotericsoftware$gloomhavenhelper$model$Condition[condition0.ordinal()]) {
                        case 1: 
                        case 2: 
                        case 3: 
                        case 4: 
                        case 5: 
                        case 6: {
                            if(!monsterBox0.monster.currentTurnConditions.contains(condition0, true)) {
                                monsterBox0.monster.conditions.removeIndex(v3);
                                monsterBox0.monster.expiredConditions.add(condition0);
                            }
                        }
                    }
                }
            }
        }
    }

    public void turnEndRevert() {
        if(!this.turnComplete) {
            return;
        }
        SnapshotArray snapshotArray0 = App.gloom.rows.getChildren();
        int v = this.getZIndex() + 1;
        int v1 = snapshotArray0.size;
        while(v < v1) {
            Row row0 = (Row)snapshotArray0.get(v);
            if(row0.turnComplete) {
                row0.turnEndRevert();
            }
            ++v;
        }
        if(this.turnComplete) {
            for(int v2 = this.getZIndex() + 1; v2 < snapshotArray0.size; ++v2) {
                Row row1 = (Row)snapshotArray0.get(v2);
                if(row1.isAlive() && !row1.turnComplete) {
                    row1.turnStartRevert();
                    break;
                }
            }
        }
        if(this.isAlive()) {
            this.setTurnComplete(false, true);
        }
        int v3 = this.getZIndex() + 1;
        int v4 = snapshotArray0.size;
        while(v3 < v4) {
            Row row2 = (Row)snapshotArray0.get(v3);
            if(row2.turnComplete) {
                row2.turnEndRevert();
            }
            ++v3;
        }
        Array array0 = new Array(2);
        for(Object object0: this.boxes) {
            array0.clear();
            if(((MonsterBox)object0).monster.conditions.contains(Condition.regenerate, true)) {
                array0.add("regenerate");
            }
            if(((MonsterBox)object0).monster.conditions.contains(Condition.wound, true)) {
                array0.add("wound");
            }
            ((MonsterBox)object0).flashIcons(array0);
            ((MonsterBox)object0).monster.conditions.addAll(((MonsterBox)object0).monster.expiredConditions);
            ((MonsterBox)object0).monster.conditions.sort();
            ((MonsterBox)object0).monster.expiredConditions.clear();
        }
    }

    public void turnStart(boolean z) {
        Array array0 = new Array(2);
        for(Object object0: this.boxes) {
            array0.clear();
            if(((MonsterBox)object0).monster.conditions.contains(Condition.regenerate, true)) {
                array0.add("regenerate");
            }
            if(((MonsterBox)object0).monster.conditions.contains(Condition.wound, true)) {
                array0.add("wound");
            }
            ((MonsterBox)object0).flashIcons(array0);
        }
        if(App.config.autoScroll) {
            App.gloom.rowsScroll.scrollTo(this.getX(), this.getY(), this.getWidth(), this.getHeight(), false, true);
        }
    }

    public void turnStartRevert() {
        if(App.config.autoScroll) {
            App.gloom.rowsScroll.scrollTo(this.getX(), this.getY(), this.getWidth(), this.getHeight(), false, true);
        }
    }
}

