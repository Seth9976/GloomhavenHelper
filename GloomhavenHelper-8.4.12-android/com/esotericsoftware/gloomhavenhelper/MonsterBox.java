package com.esotericsoftware.gloomhavenhelper;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout.GlyphRun;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.gloomhavenhelper.model.Condition;
import com.esotericsoftware.gloomhavenhelper.model.Monster;
import com.esotericsoftware.gloomhavenhelper.model.MonsterType;
import com.esotericsoftware.gloomhavenhelper.util.Animator.HasAnimator;
import com.esotericsoftware.gloomhavenhelper.util.Animator;
import com.esotericsoftware.gloomhavenhelper.util.HPAdjust;
import com.esotericsoftware.gloomhavenhelper.util.Row;

public class MonsterBox extends Actor implements HasAnimator, Comparable {
    public final Animator animator;
    private Drawable bgDrawable;
    private static final float conditionSpacing = 26.0f;
    private NinePatch conditionsPatch;
    private float conditionsRight;
    private float conditionsRightTarget;
    HPAdjust hpAdjust;
    private float hpPercent;
    private TextureRegion hpRegion;
    long lastAnimateIcon;
    MonsterBoxMenu menu;
    public final Monster monster;
    public final Row row;
    private final float scale;
    private Drawable summonXDrawable;

    public MonsterBox(Row row0, Monster monster0, float f) {
        this.animator = new Animator(this, 10.0f, 500.0f, 150.0f);
        this.row = row0;
        this.monster = monster0;
        this.scale = f;
        this.hpPercent = Math.min(1.0f, ((float)monster0.hp) / ((float)monster0.hpMax));
        this.create();
        this.layoutUI();
        this.events();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    public void act(float f) {
        super.act(f);
        float f1 = this.conditionsRight + 31.0f;
        if(f1 != this.getWidth()) {
            this.setWidth(f1);
            this.row.monstersGroup.invalidateHierarchy();
            Animator.storeChildren(this.row.monstersGroup, 0.0f);
        }
        this.hpPercent = App.animate(this.hpPercent, Math.min(1.0f, ((float)this.monster.hp) / ((float)this.monster.hpMax)), 0.2f, 2.0f, 2.0f, f);
        this.conditionsRightTarget = ((float)((this.monster.conditions.size - 1) / 2)) * 26.0f + 117.0f;
        if(this.monster.conditions.size == 0) {
            this.conditionsRightTarget -= 40.0f;
        }
        this.conditionsRight = App.animate(this.conditionsRight, this.conditionsRightTarget, 20.0f, 200.0f, 25.0f, f);
    }

    public void checkDead(boolean z) {
        if(this.monster.hp > 0) {
            return;
        }
        this.removeMonster(z);
        App.state.changed();
    }

    @Override
    public int compareTo(Object object0) {
        int v = 0;
        if(!(object0 instanceof MonsterBox)) {
            return 0;
        }
        if(this.row instanceof PlayerRow) {
            return 0;
        }
        if(App.state.elitesFirst) {
            int v1 = this.monster.type == MonsterType.elite ? 0 : 1;
            if(((MonsterBox)object0).monster.type != MonsterType.elite) {
                v = 1;
            }
            int v2 = v1 - v;
            if(v2 != 0) {
                return v2;
            }
        }
        int v3 = this.monster.summonColor.ordinal() - ((MonsterBox)object0).monster.summonColor.ordinal();
        return v3 == 0 ? this.monster.number - ((MonsterBox)object0).monster.number : v3;
    }

    private void create() {
        this.bgDrawable = App.skin.getDrawable("psd/monster-" + this.monster.type);
        this.summonXDrawable = App.skin.getDrawable("summon/x");
        this.hpRegion = App.skin.getRegion("psd/monster-hp");
        this.conditionsPatch = App.skin.getPatch("psd/conditions");
        this.menu = new MonsterBoxMenu(this);
        this.hpAdjust = new HPAdjust(this, "plainExtraLargeNumbers") {
            @Override  // com.esotericsoftware.gloomhavenhelper.util.HPAdjust
            protected void apply() {
                MonsterBox.this.checkDead(false);
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
            protected int getMax() {
                return MonsterBox.this.monster.hpMax;
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.HPAdjust
            protected void getPosition(Vector2 vector20) {
                Vector2 vector21 = vector20.set(104.0f, 11.0f);
                MonsterBox.this.localToStageCoordinates(vector21);
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
            protected int getValue() {
                return MonsterBox.this.monster.hp;
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.HPAdjust
            protected void setValue(int v) {
                if(MonsterBox.this.monster.hp != v) {
                    MonsterBox.this.hpChanged(this.extra + v - this.start);
                }
                MonsterBox.this.monster.hp = v;
                if(v > 0) {
                    App.state.changed();
                }
                super.setValue(v);
            }
        };
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch0, float f) {
        int v1;
        float f7;
        this.animator.update();
        boolean z = this.monster.hp <= 0 || this.getParent() == App.stage.getRoot();
        if(z) {
            batch0.setShader(App.desatShader);
            App.desatShader.setUniformf("u_desat", 1.0f);
        }
        float f1 = this.getX();
        float f2 = this.getY();
        float f3 = this.getColor().a * f;
        float f4 = this.monster.type == MonsterType.summon ? f2 + 7.0f : f2 - 4.0f;
        batch0.setColor(1.0f, 1.0f, 1.0f, f3);
        int v = this.monster.conditions.size - 1;
        float f5 = this.conditionsRight;
        if(f5 > 77.0f) {
            float f6 = ((float)(v / 2)) * 26.0f;
            f7 = f5 - f6;
            float f8 = this.conditionsRightTarget - f5;
            batch0.setColor(1.0f, 1.0f, 1.0f, f3);
            this.conditionsPatch.setColor(App.c(1.0f, 1.0f, 1.0f, f3));
            v1 = v;
            this.conditionsPatch.draw(batch0, f8 + (f1 + f7 - 47.0f), f4 + 10.0f, f6 + 67.0f - f8, 65.0f);
        }
        else {
            v1 = v;
            f7 = 0.0f;
        }
        this.bgDrawable.draw(batch0, f1, f4, this.bgDrawable.getMinWidth(), this.bgDrawable.getMinHeight());
        if(this.monster.type == MonsterType.summon) {
            this.monster.summonColor.drawable.draw(batch0, f1 + 38.0f, f4 + 56.0f, 36.0f, 36.0f);
            if(this.monster.isNew) {
                this.summonXDrawable.draw(batch0, f1 + 38.0f, f4 + 56.0f, 36.0f, 36.0f);
            }
        }
        int v2 = this.monster.hp;
        if(this.hpPercent > 0.0f) {
            int v3 = this.monster.hpMax;
            batch0.setColor(App.c(((v2 > 3 || this.hpPercent > 0.4f || v2 == v3) && this.hpPercent >= 0.3f ? App.healthGreen : App.healthRed), f3));
            this.hpRegion.setRegionWidth(((int)Math.max(MathUtils.clamp(this.hpPercent / (1.0f / ((float)v3)), 0.0f, 1.0f) * 8.0f, Math.round(this.hpPercent * 98.0f))));
            batch0.draw(this.hpRegion, f1 + 8.0f, f4 + 10.0f);
        }
        if(this.monster.type == MonsterType.boss) {
            App.fancyExtraLargeOutlineNumbers.setColor(App.c(App.bossRed, f3));
        }
        else if(this.monster.type == MonsterType.elite) {
            App.fancyExtraLargeOutlineNumbers.setColor(App.c(App.eliteGold, f3));
        }
        else {
            App.fancyExtraLargeOutlineNumbers.setColor(1.0f, 1.0f, 1.0f, f3);
        }
        float f9 = f1 + 20.0f;
        float f10 = f4 + 68.0f;
        int v4 = this.monster.number;
        if(v4 == 1) {
            f9 += 5.0f;
            --f10;
        }
        else if(v4 == 2) {
            ++f9;
        }
        else if(v4 == 7) {
            f9 += 2.0f;
        }
        else if(v4 >= 10) {
            f9 = v4 == 11 ? f9 - 1.0f : f9 - 5.0f;
            if(App.config.isRussian()) {
                f9 -= 4.0f;
            }
            --f10;
        }
        String s = Integer.toString(v4);
        if(v4 < 10 || !App.config.isRussian()) {
            App.fancyExtraLargeOutlineNumbers.draw(batch0, Integer.toString(v4), f9, f10);
        }
        else {
            GlyphLayout glyphLayout0 = new GlyphLayout();
            glyphLayout0.setText(App.fancyExtraLargeOutlineNumbers, s, 0, s.length(), App.fancyExtraLargeOutlineNumbers.getColor(), 0.0f, 8, false, null);
            ((GlyphRun)glyphLayout0.runs.first()).xAdvances.incr(1, -5.0f);
            App.fancyExtraLargeOutlineNumbers.draw(batch0, glyphLayout0, f9, f10);
        }
        BitmapFont bitmapFont0 = App.plainExtraLargeNumbers;
        float f11 = f1 + 58.0f;
        float f12 = f4 + 63.0f;
        if(App.config.isJapanese()) {
            f11 += 4.0f;
            f12 -= 5.0f;
        }
        if(v2 == 2 || v2 == 3) {
            ++f11;
        }
        if(v2 >= 100) {
            bitmapFont0 = App.plainLargeFixedNumbers;
            f11 += 2.0f;
            f12 -= 15.0f;
            if(v2 >= 200) {
                ++f11;
            }
            if(App.config.isRussian()) {
                f11 -= 2.0f;
            }
            if(App.config.isJapanese()) {
                f11 -= 7.0f;
            }
        }
        else if(v2 >= 20) {
            ++f11;
            if(App.config.isRussian()) {
                f11 += 2.0f;
            }
        }
        else if(v2 < 10) {
            f11 += 12.0f;
            switch(v2) {
                case 1: 
                case 3: {
                    --f11;
                    break;
                }
                case 8: 
                case 9: {
                    ++f11;
                }
            }
            if(App.config.isRussian()) {
                f11 += 3.0f;
            }
        }
        else if(App.config.isRussian()) {
            f11 += 5.0f;
        }
        bitmapFont0.setColor(1.0f, 1.0f, 1.0f, f3);
        bitmapFont0.draw(batch0, v2 + "", f11, f12);
        for(int v5 = 0; v5 <= v1; ++v5) {
            Condition condition0 = (Condition)this.monster.conditions.get(v1 - v5);
            float f13 = f7 + ((float)(v5 / 2)) * 26.0f;
            batch0.setColor(1.0f, 1.0f, 1.0f, Interpolation.slowFast.apply(Math.min(1.0f, (f13 - 75.0f) / 42.0f)) * f3);
            condition0.drawable.draw(batch0, f1 + f13, (v5 != v1 || v5 % 2 != 0 ? f4 + 25.0f + (v5 % 2 == 0 ? 19.0f : -19.0f) : f4 + 25.0f), condition0.drawable.getMinWidth(), condition0.drawable.getMinHeight());
        }
        if(z) {
            batch0.setShader(null);
        }
    }

    private void events() {
        this.addListener(new ClickListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent0, float f, float f1) {
                MonsterBox.this.showMenu();
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                inputEvent0.stop();
                return super.touchDown(inputEvent0, f, f1, v, v1);
            }
        });
    }

    public void flashIcon(String s) {
        this.row.animateIcon(this, "conditions/" + s + "-large", (s.equals("shield") ? 1.3f : 1.6f), 54.0f, 40.0f);
    }

    public void flashIcons(Array array0) {
        int v = array0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            String s = (String)array0.get(v1);
            if(v1 > 0) {
                SequenceAction sequenceAction0 = Actions.sequence(Actions.delay(((float)v1) * 0.4f), new Action() {
                    @Override  // com.badlogic.gdx.scenes.scene2d.Action
                    public boolean act(float f) {
                        MonsterBox.this.flashIcon(s);
                        return true;
                    }
                });
                this.row.addAction(sequenceAction0);
            }
            else {
                this.flashIcon(s);
            }
        }
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Animator$HasAnimator
    public Animator getAnimator() {
        return this.animator;
    }

    void hpChanged(int v) {
        long v1 = System.currentTimeMillis();
        if(v1 < this.lastAnimateIcon + 1000L) {
            return;
        }
        this.lastAnimateIcon = v1;
        Array array0 = new Array(2);
        if(this.monster.conditions.contains(Condition.poison, true)) {
            array0.add("poison");
        }
        if(v >= 0) {
            if(v > 0 && this.monster.conditions.contains(Condition.wound, true)) {
                array0.add("wound");
            }
        }
        else if(this.row instanceof MonsterRow && ((MonsterRow)this.row).abilityCard.statValue("shield", this.monster.type, 0) > 0) {
            array0.add("shield");
        }
        this.flashIcons(array0);
    }

    private void layoutUI() {
        if(this.monster.data.isBoss()) {
            this.setHeight(this.scale * 95.0f + 4.0f);
        }
        else {
            this.setHeight(this.scale * 90.0f + 4.0f);
        }
        float f = ((float)((this.monster.conditions.size - 1) / 2)) * 26.0f + 117.0f;
        this.conditionsRightTarget = f;
        this.conditionsRight = f;
        if(this.monster.conditions.size == 0) {
            this.conditionsRight -= 40.0f;
        }
        this.setWidth(this.conditionsRight + 5.0f + this.scale * 26.0f);
    }

    public void removeMonster(boolean z) {
        if(this.getParent() == null) {
            return;
        }
        if(this.getParent().getParent() == null) {
            return;
        }
        this.animator.animating = false;
        Animator.enabled = false;
        float f = this.row.getPrefHeight();
        float f1 = 0.0f;
        this.localToStageCoordinates(App.v2.set(0.0f, 0.0f));
        this.row.boxes.removeValue(this, true);
        App.stage.addActor(this);
        this.setPosition(App.v2.x, App.v2.y);
        float f2 = this.row.getPrefHeight();
        Animator.enabled = true;
        if(f != f2) {
            this.row.animateHeight = f;
            App.gloom.rows.addAction(Actions.sequence(new TemporalAction(0.5f) {
                @Override  // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
                protected void end() {
                    MonsterBox.this.row.animateHeight = 0.0f;
                    MonsterBox.this.row.invalidateHierarchy();
                }

                @Override  // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
                protected void update(float f) {
                    MonsterBox.this.row.animateHeight = Interpolation.fastSlow.apply(f, f2, f);
                    MonsterBox.this.row.invalidateHierarchy();
                }
            }));
        }
        Animator.storeChildren(this.row.monstersGroup, 0.0f);
        this.row.monstersGroup.invalidateHierarchy();
        App.root.validate();
        this.setTouchable(Touchable.disabled);
        if(z) {
            f1 = 0.2f;
        }
        this.addAction(Actions.sequence(Actions.delay(f1), Actions.parallel(Actions.fadeOut(1.1f, Interpolation.slowFast), Actions.moveTo(this.getX(), this.getY() - 60.0f, 1.5f, Interpolation.fastSlow)), Actions.removeActor()));
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    protected void setParent(Group group0) {
        super.setParent(group0);
        if(group0 == null) {
            this.hpAdjust.changeContainer.remove();
            return;
        }
        App.stage.addActor(this.hpAdjust.changeContainer);
    }

    public void showMenu() {
        this.localToAscendantCoordinates(App.gloom.rows, App.v2.set(0.0f, 0.0f));
        App.gloom.rowsScroll.scrollTo(0.0f, App.v2.y - 7.0f, 0.0f, this.getHeight() + 14.0f);
        this.menu.show(this, 3.0f, -15.0f, -3.0f, 0.0f, true);
        this.hpAdjust.changeContainer.clearActions();
        this.hpAdjust.changeContainer.setVisible(false);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    protected void sizeChanged() {
        Group group0 = this.getParent();
        if(group0 instanceof Layout) {
            ((Layout)group0).invalidate();
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    public String toString() {
        return this.monster.data.name + " #" + this.monster.number + ", " + this.monster.summonColor + " " + this.monster.summonColor.ordinal();
    }
}

