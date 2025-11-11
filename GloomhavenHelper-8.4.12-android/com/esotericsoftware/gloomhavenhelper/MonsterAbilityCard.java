package com.esotericsoftware.gloomhavenhelper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array.ArrayIterator;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.gloomhavenhelper.model.Condition;
import com.esotericsoftware.gloomhavenhelper.model.Line.LinePart;
import com.esotericsoftware.gloomhavenhelper.model.Line.RegionPart;
import com.esotericsoftware.gloomhavenhelper.model.Line;
import com.esotericsoftware.gloomhavenhelper.model.MonsterAbility;
import com.esotericsoftware.gloomhavenhelper.model.MonsterAbilityDeck;
import com.esotericsoftware.gloomhavenhelper.model.MonsterData;
import com.esotericsoftware.gloomhavenhelper.model.MonsterStats;
import com.esotericsoftware.gloomhavenhelper.model.MonsterType;
import com.esotericsoftware.gloomhavenhelper.util.Actor3D;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MonsterAbilityCard extends Actor {
    public static class Ability3D extends Actor3D {
        private final Actor actor;
        private final int deckID;
        private static final float flipDuration = 0.45f;
        private float flipTime;
        private boolean front;

        public Ability3D(int v, Actor actor0) {
            super(441, 209, 70.0f);
            this.deckID = v;
            this.actor = actor0;
        }

        @Override  // com.badlogic.gdx.scenes.scene2d.Actor
        public void act(float f) {
            this.flipTime -= f;
            if(this.flipTime < 0.0f) {
                this.remove();
                this.actor.setVisible(true);
                return;
            }
            Gdx.graphics.requestRendering();
        }

        @Override  // com.esotericsoftware.gloomhavenhelper.util.Actor3D
        public void draw(Batch batch0, float f) {
            this.setPosition(this.actor.getX(), this.actor.getY());
            Gdx.gl.glCullFace(0x404);
            Gdx.gl.glEnable(0xB44);
            super.draw(batch0, f);
            Gdx.gl.glDisable(0xB44);
        }

        public void start(boolean z) {
            this.front = z;
            this.flipTime = 0.45f;
        }

        @Override  // com.esotericsoftware.gloomhavenhelper.util.Actor3D
        protected void updateCamera(PerspectiveCamera perspectiveCamera0, float f, float f1, float f2, float f3) {
            float f4 = Interpolation.slowFast.apply(1.0f - this.flipTime / 0.45f);
            int v = 1;
            perspectiveCamera0.update(true);
            float f5 = f + f2 / 2.0f;
            float f6 = f1 + f3 / 2.0f;
            perspectiveCamera0.combined.translate(f5, f6, -perspectiveCamera0.position.z);
            if(f4 < 0.5f) {
                float f7 = f4 / 0.5f * 0.3f + 1.0f;
                perspectiveCamera0.combined.scale(f7, f7, f7);
            }
            else {
                float f8 = 1.3f - (f4 - 0.5f) / 0.5f * 0.3f;
                perspectiveCamera0.combined.scale(f8, f8, f8);
            }
            boolean z = App.state.getAbilityDeck(this.deckID).shownAbility != null;
            Matrix4 matrix40 = perspectiveCamera0.combined;
            Vector3 vector30 = App.v3.set(1.0f, 0.0f, 0.0f);
            if(z) {
                v = -1;
            }
            matrix40.rotate(vector30, f4 * 180.0f * ((float)v));
            if(this.front) {
                perspectiveCamera0.combined.scale(1.0f, -1.0f, 1.0f);
            }
            perspectiveCamera0.combined.translate(-f5, -f6, perspectiveCamera0.position.z);
        }
    }

    private final Array attackConditions;
    private final Drawable backDrawable;
    private final Drawable curseDrawable;
    public MonsterAbility forceAbility;
    private final Drawable frontDrawable;
    private static final GlyphLayout layout;
    final MonsterRow row;
    private final Drawable shuffleDrawable;
    public static final Pattern statsPattern;

    static {
        MonsterAbilityCard.statsPattern = Pattern.compile("!?\\^?%(move|attack|range|shield|retaliate)% ([+-]) ([0-9]+).*");
        MonsterAbilityCard.layout = new GlyphLayout();
    }

    public MonsterAbilityCard(MonsterRow monsterRow0, boolean z) {
        this.frontDrawable = App.skin.getDrawable("psd/monsterAbility-front");
        this.backDrawable = App.skin.getDrawable("psd/monsterAbility-back");
        this.shuffleDrawable = App.skin.getDrawable("abilities/shuffle");
        this.curseDrawable = App.skin.getDrawable("abilities/curse-medium");
        this.attackConditions = new Array();
        this.row = monsterRow0;
        this.setSize(441.0f, 209.0f);
        if(z) {
            this.addListener(new ClickListener() {
                @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent0, float f, float f1) {
                    new MonsterAbilityDeckDialog(monsterRow0).show();
                }
            });
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch0, float f) {
        Matcher matcher0;
        Line line1;
        boolean z3;
        boolean z2;
        MonsterAbilityDeck monsterAbilityDeck0 = App.state.getAbilityDeck(this.row.data.deckID);
        batch0.setColor(1.0f, 1.0f, 1.0f, f);
        if((monsterAbilityDeck0.shownAbility != null || this.forceAbility != null) && App.state.abilityCards) {
            float f3 = this.x(0.0f);
            float f4 = this.y(209.0f);
            this.frontDrawable.draw(batch0, f3, f4, 441.0f, 209.0f);
            MonsterAbility monsterAbility0 = this.forceAbility == null ? this.row.ability : this.forceAbility;
            if(monsterAbility0 == null) {
                return;
            }
            if(App.config.abilityNumbers) {
                App.plainSmallOutline.setColor(App.c(App.lightGray, f));
                App.plainSmallOutline.draw(batch0, monsterAbility0.number, this.x(8.0f), this.y(184.0f));
            }
            App.fancyExtraLargeOutlineNumbers.setColor(1.0f, 1.0f, 1.0f, f);
            App.fancyExtraLargeOutlineNumbers.draw(batch0, monsterAbility0.initiativeString, this.x(33.0f), this.y(18.0f), 0.0f, 1, false);
            boolean z = this.row.hasElite();
            boolean z1 = this.row.hasNormal();
            if(this.forceAbility == null || !App.state.trackStandees) {
                z2 = z;
                z3 = z1;
            }
            else {
                z2 = true;
                z3 = true;
            }
            float f5 = this.getX();
            float f6 = this.getY();
            Line.loadAbilityLines(monsterAbility0);
            boolean z4 = false;
            boolean z5 = false;
            for(Object object0: monsterAbility0.lines) {
                Line line0 = (Line)object0;
                if(!App.state.calculateStats || !z2 && !z3) {
                    line1 = line0;
                }
                else if(line0.line.equals("NORMAL:")) {
                    line1 = line0;
                    z4 = false;
                    z5 = true;
                }
                else if(line0.line.equals("ELITE:")) {
                    line1 = line0;
                    z4 = true;
                    z5 = false;
                }
                else {
                    try {
                        matcher0 = null;
                        matcher0 = MonsterAbilityCard.statsPattern.matcher(line0.line);
                    }
                    catch(Throwable unused_ex) {
                    }
                    if(matcher0 == null || !matcher0.matches()) {
                    label_61:
                        line1 = line0;
                    }
                    else {
                        String s = matcher0.group(1);
                        if(monsterAbility0.id != 0xAB || !s.equals("shield")) {
                            try {
                                int v = App.parseInt(matcher0.group(3));
                            }
                            catch(NumberFormatException unused_ex) {
                                continue;
                            }
                            if(this.drawCalculatedStat(batch0, f, f5, f6, s, v * (matcher0.group(2).charAt(0) == 45 ? -1 : 1), line0, z3 && !z4, z2 && !z5)) {
                                continue;
                            }
                            else {
                                line1 = line0;
                                goto label_62;
                            }
                        }
                        else {
                            line1 = line0;
                            goto label_67;
                        }
                        goto label_61;
                    }
                label_62:
                    if(line1.line.endsWith(" ")) {
                        z4 = false;
                        z5 = false;
                    }
                }
            label_67:
                line1.draw(batch0, f5, f6, this.row.data, f);
            }
            if(monsterAbility0.shuffle) {
                float f7 = this.x(400.0f);
                float f8 = this.y(196.0f);
                float f9 = this.shuffleDrawable.getMinWidth();
                float f10 = this.shuffleDrawable.getMinHeight();
                this.shuffleDrawable.draw(batch0, f7, f8, f9, f10);
            }
        }
        else {
            float f1 = this.x(0.0f);
            float f2 = this.y(209.0f);
            this.backDrawable.draw(batch0, f1, f2, 441.0f, 209.0f);
            if(App.state.abilityCards) {
                App.plainMediumOutline.setColor(App.c(App.lightGray, f));
                App.plainMediumOutline.draw(batch0, Integer.toString(monsterAbilityDeck0.abilities.size), this.getX() + 434.0f, this.getY() + 27.0f, 0.0f, 16, false);
            }
        }
    }

    private boolean drawCalculatedStat(Batch batch0, float f, float f1, float f2, String s, int v, Line line0, boolean z, boolean z1) {
        float f7;
        float f6;
        ((LinePart)line0.parts.get(0)).draw(batch0, line0, f1, f2, this.row.data, f);
        ((LinePart)line0.parts.get(1)).draw(batch0, line0, f1, f2, this.row.data, f);
        LinePart line$LinePart0 = (LinePart)line0.parts.get(2);
        float f3 = f1 + line0.x + line$LinePart0.x + 3.0f;
        float f4 = f2 + line0.y;
        try {
            if(!this.row.data.isBoss()) {
                int v1 = z ? this.statValue(s, MonsterType.normal, v) : 0;
                int v2 = z1 ? this.statValue(s, MonsterType.elite, v) : 0;
                if(z) {
                    float f5 = this.drawStat(s, MonsterType.normal, v1, batch0, line0.font, Color.WHITE, f3, f4, (z1 ? " /" : ""), f);
                    if(z1) {
                        f5 += 5.0f;
                    }
                    f6 = f5;
                }
                else {
                    f6 = f3;
                }
                f7 = z1 ? this.drawStat(s, MonsterType.elite, v2, batch0, line0.font, App.eliteGold, f6, f4, "", f) : f6;
            }
            else if(z) {
                int v3 = this.statValue(s, MonsterType.boss, v);
                f7 = this.drawStat(s, MonsterType.boss, v3, batch0, line0.font, Color.WHITE, f3, f4, "", f);
            }
            else {
                f7 = f3;
            }
            if(line0.parts.size > 3) {
                float f8 = f1 + line$LinePart0.x + (f7 - f3) + 5.0f;
                f8 = line0.parts.get(3) instanceof RegionPart ? f8 + 11.0f : f1 + line$LinePart0.x + (f7 - f3) + 5.0f;
                int v4 = line0.parts.size;
                float f9 = f8;
                for(int v5 = 3; v5 < v4; ++v5) {
                    Object object0 = line0.parts.get(v5);
                    ((LinePart)object0).draw(batch0, line0, Math.min(f9 - ((LinePart)object0).x, f1 - line0.x + 435.0f - ((LinePart)object0).x - ((LinePart)object0).width + ((LinePart)object0).space), f2, this.row.data, f);
                    f9 += ((LinePart)object0).width;
                }
            }
            return true;
        }
        catch(NumberFormatException unused_ex) {
            return false;
        }
    }

    private float drawStat(String s, MonsterType monsterType0, int v, Batch batch0, BitmapFont bitmapFont0, Color color0, float f, float f1, String s1, float f2) {
        int v2;
        MonsterStats monsterStats0 = this.row.data.stats[monsterType0.ordinal()][this.row.level];
        int v1 = 0;
        if(s.equals("attack")) {
            Line.loadAttributeLines(this.row.data, monsterStats0, monsterType0 == MonsterType.elite);
            for(Object object0: monsterStats0.attributeLines) {
                Line line0 = (Line)object0;
                if(line0.line.equals("^%wound%")) {
                    this.attackConditions.add(Condition.wound);
                }
                else if(line0.line.equals("^%disarm%")) {
                    this.attackConditions.add(Condition.disarm);
                }
                else if(line0.line.equals("^%immobilize%")) {
                    this.attackConditions.add(Condition.immobilize);
                }
                else if(line0.line.equals("^%poison%")) {
                    this.attackConditions.add(Condition.poison);
                }
                else if(line0.line.equals("^%muddle%")) {
                    this.attackConditions.add(Condition.muddle);
                }
                else if(line0.line.equals("^%curse%")) {
                    v1 = 1;
                }
            }
            v2 = v1;
        }
        else {
            v2 = 0;
        }
        bitmapFont0.setColor(App.c(color0, f2));
        float f3 = f + bitmapFont0.draw(batch0, Integer.toString(v), f, f1 + bitmapFont0.getCapHeight()).width;
        if(this.attackConditions.size > 0 || v2 != 0) {
            f3 += 2.0f;
        }
        for(Object object1: this.attackConditions) {
            ((Condition)object1).drawableMedium.draw(batch0, f3 + 2.0f, f1 - 6.0f, ((Condition)object1).drawableMedium.getMinWidth(), ((Condition)object1).drawableMedium.getMinHeight());
            f3 += 33.0f;
        }
        if(v2 != 0) {
            float f4 = this.curseDrawable.getMinWidth();
            float f5 = this.curseDrawable.getMinHeight();
            this.curseDrawable.draw(batch0, f3 + 2.0f, f1 - 6.0f, f4, f5);
            f3 += 33.0f;
        }
        if(this.attackConditions.size > 0 || v2 != 0) {
            f3 -= 4.0f;
        }
        if(s1 != null) {
            f3 = f3 - 1.0f + (bitmapFont0.draw(batch0, s1, f3 - 1.0f, f1 + bitmapFont0.getCapHeight()).width - 2.0f);
        }
        this.attackConditions.clear();
        return f3;
    }

    public int statValue(String s, MonsterType monsterType0, int v) {
        String s1;
        boolean z = true;
        int v1 = 0;
        MonsterStats monsterStats0 = this.row.data.stats[monsterType0.ordinal()][this.row.level];
        if(s.equals("attack")) {
            return v + monsterStats0.attack();
        }
        if(s.equals("move")) {
            s1 = monsterStats0.move;
        }
        else {
            if(s.equals("range")) {
                s1 = monsterStats0.range;
                goto label_36;
            }
            if(s.equals("shield")) {
                MonsterData monsterData0 = this.row.data;
                if(monsterType0 != MonsterType.elite) {
                    z = false;
                }
                s1 = null;
                Line.loadAttributeLines(monsterData0, monsterStats0, z);
                ArrayIterator array$ArrayIterator0 = monsterStats0.attributeLines.iterator();
                while(true) {
                    if(!array$ArrayIterator0.hasNext()) {
                        goto label_36;
                    }
                    Object object0 = array$ArrayIterator0.next();
                    Line line0 = (Line)object0;
                    if(!line0.line.startsWith("^%shield% ")) {
                        continue;
                    }
                    s1 = line0.line.substring(10);
                    goto label_36;
                }
            }
            if(!s.equals("retaliate")) {
                throw new RuntimeException();
            }
            MonsterData monsterData1 = this.row.data;
            if(monsterType0 != MonsterType.elite) {
                z = false;
            }
            s1 = null;
            Line.loadAttributeLines(monsterData1, monsterStats0, z);
            for(Object object1: monsterStats0.attributeLines) {
                Line line1 = (Line)object1;
                if(line1.line.startsWith("^%retaliate% ")) {
                    s1 = line1.line.substring(13);
                    break;
                }
            }
        }
    label_36:
        if(s1 != null) {
            v1 = App.parseInt(s1);
        }
        return v + v1;
    }

    private float x(float f) {
        return this.getX() + f;
    }

    private float y(float f) {
        return this.getY() + 209.0f - f;
    }
}

