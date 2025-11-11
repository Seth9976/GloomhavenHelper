package com.esotericsoftware.gloomhavenhelper;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.OrderedSet.OrderedSetIterator;
import com.esotericsoftware.gloomhavenhelper.model.Condition;
import com.esotericsoftware.gloomhavenhelper.model.Line.LinePart;
import com.esotericsoftware.gloomhavenhelper.model.Line.TextPart;
import com.esotericsoftware.gloomhavenhelper.model.Line;
import com.esotericsoftware.gloomhavenhelper.model.MonsterData;
import com.esotericsoftware.gloomhavenhelper.model.MonsterStats;
import com.esotericsoftware.gloomhavenhelper.model.MonsterType;
import com.esotericsoftware.gloomhavenhelper.util.Menu;

public class MonsterStatsCard extends Table {
    private final Drawable bossOverlay;
    final ClickListener clickListener;
    private final Drawable curseDrawable;
    private MonsterData data;
    private float eliteDim;
    private final Drawable eliteOverlay;
    private final Drawable flyingStatDrawable;
    private TextureRegion levelRegion;
    private float normalDim;
    private final Drawable normalOverlay;
    private final Drawable pullDrawable;
    private final Drawable pushDrawable;
    private final MonsterRow row;

    public MonsterStatsCard(MonsterRow monsterRow0) {
        super(App.skin);
        this.normalOverlay = App.skin.getDrawable("psd/monsterStats-normal-overlay");
        this.eliteOverlay = App.skin.getDrawable("psd/monsterStats-elite-overlay");
        this.bossOverlay = App.skin.getDrawable("psd/monsterStats-boss-overlay");
        this.flyingStatDrawable = App.skin.getDrawable("psd/flying-stat");
        this.curseDrawable = App.skin.getDrawable("abilities/curse-medium");
        this.pushDrawable = App.skin.getDrawable("abilities/push-medium");
        this.pullDrawable = App.skin.getDrawable("abilities/pull-medium");
        this.levelRegion = App.skin.getRegion("psd/level-white");
        this.normalDim = 1.0f;
        this.eliteDim = 1.0f;
        this.row = monsterRow0;
        this.data = monsterRow0.data;
        this.background("psd/monsterStats-" + (this.data.isBoss() ? "boss" : "normal"));
        this.setTouchable(Touchable.enabled);
        ClickListener clickListener0 = new ClickListener();
        this.clickListener = clickListener0;
        this.addListener(clickListener0);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public void act(float f) {
        float f2;
        boolean z = this.row.hasElite();
        boolean z1 = this.row.hasNormal();
        float f1 = 1.0f;
        if(this.clickListener.isPressed()) {
            z = true;
            z1 = true;
            f2 = 1.0f;
        }
        else if(App.state.hideStats) {
            f2 = 1.0f;
        }
        else {
            f2 = 0.0f;
            f1 = 0.0f;
        }
        if(Menu.menusShown == 0) {
            this.eliteDim = App.animate(this.eliteDim, (z ? 0.0f : f1), 0.25f, 2.0f, 0.25f, f);
            this.normalDim = App.animate(this.normalDim, (z1 ? 0.0f : f2), 0.25f, 2.0f, 0.25f, f);
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Table
    public void draw(Batch batch0, float f) {
        int v19;
        int v16;
        MonsterStats monsterStats2;
        float f16;
        int v11;
        int v10;
        int v9;
        float f13;
        MonsterStats monsterStats1;
        int v6;
        int v4;
        int v3;
        float f9;
        boolean z = batch0.getShader() == App.desatShader && this.clickListener.isPressed();
        if(z) {
            batch0.setShader(null);
        }
        super.draw(batch0, f);
        MonsterStatsCard.drawLevel(batch0, this.levelRegion, this.x(204.0f), this.y(202.0f), this.row.level, this.row.levelString, f);
        int v = App.config.language.equals("en") || App.config.language.equals("pl") || App.config.language.equals("cz") || App.config.language.equals("de") || App.config.language.equals("fr") || App.config.language.equals("it") || App.config.language.equals("es") || App.config.language.equals("pt") || App.config.language.equals("hu") ? 0 : -4;
        if(this.data.isBoss()) {
            if(this.data.flying) {
                float f1 = this.x(152.0f);
                float f2 = this.y(86.0f);
                this.flyingStatDrawable.draw(batch0, f1, f2, 37.0f, 40.0f);
            }
            float f3 = this.normalDim;
            if(f3 != 1.0f) {
                float f4 = (1.0f - f3) * f;
                batch0.setColor(1.0f, 1.0f, 1.0f, f4);
                MonsterStats monsterStats0 = this.data.stats[MonsterType.boss.ordinal()][this.row.level];
                float f5 = this.x(9.0f);
                float f6 = this.y(49.0f);
                OrderedSetIterator orderedSet$OrderedSetIterator0 = monsterStats0.immunities.iterator();
                float f7 = f5;
                float f8 = f6;
                int v1;
                for(v1 = 0; true; ++v1) {
                    f9 = 14.0f;
                    if(!orderedSet$OrderedSetIterator0.hasNext()) {
                        break;
                    }
                    Object object0 = orderedSet$OrderedSetIterator0.next();
                    ((Condition)object0).drawableMedium.draw(batch0, f7, f8, 28.0f, 28.0f);
                    if(v1 % 2 == 0) {
                        f7 += 14.0f;
                    }
                    if(v1 % 2 != 0) {
                        f9 = -28.0f;
                    }
                    f8 += f9;
                }
                if(monsterStats0.immunePush) {
                    this.pushDrawable.draw(batch0, f7, f8, 28.0f, 28.0f);
                    if(v1 % 2 == 0) {
                        f7 += 14.0f;
                    }
                    if(v1 % 2 != 0) {
                        f9 = -28.0f;
                    }
                    f8 += f9;
                }
                if(monsterStats0.immunePull) {
                    this.pullDrawable.draw(batch0, f7, f8, 28.0f, 28.0f);
                }
                if(monsterStats0.immuneCurse) {
                    this.curseDrawable.draw(batch0, f7, f8, 28.0f, 28.0f);
                }
                int v2 = 14;
                if(this.data.id == -37) {
                    v3 = 0xC2;
                    v2 = 11;
                    v4 = 18;
                }
                else {
                    switch(this.data.id) {
                        case -69: {
                            v3 = 0xBC;
                            v2 = 7;
                            v4 = 11;
                            break;
                        }
                        case -68: {
                            v3 = 0xBA;
                            v2 = 7;
                            v4 = 11;
                            break;
                        }
                        case -55: {
                            v3 = 0xBA;
                            v2 = 7;
                            v4 = 11;
                            break;
                        }
                        case -54: {
                            v3 = 0xB8;
                            v2 = 7;
                            v4 = 14;
                            break;
                        }
                        case 0xFFFFFFD1: {
                            v3 = 0xC1;
                            v2 = 7;
                            v4 = 12;
                            break;
                        }
                        case -46: {
                            v3 = 0xC0;
                            v2 = 7;
                            v4 = 41;
                            break;
                        }
                        case -45: {
                            v3 = 0xC3;
                            v2 = 6;
                            v4 = 14;
                            break;
                        }
                        case -43: {
                            v3 = 0xC4;
                            v2 = 11;
                            v4 = 20;
                            break;
                        }
                        case -42: {
                            v3 = 0xC6;
                            v4 = 35;
                            break;
                        }
                        case -41: {
                            v3 = 0xD4;
                            v2 = 9;
                            v4 = 16;
                            break;
                        }
                        case -40: {
                            v3 = 0xC5;
                            v2 = 15;
                            v4 = 57;
                            break;
                        }
                        case -39: {
                            v2 = 10;
                            v3 = 202;
                            v4 = 20;
                            break;
                        }
                        case -38: {
                            v3 = 0xC5;
                            v2 = 10;
                            v4 = -9;
                            break;
                        }
                        default: {
                            v3 = 202;
                            v2 = 15;
                            v4 = 20;
                        }
                    }
                }
                int v5 = 4;
                if(App.config.isRussian()) {
                    v3 = Math.min(0xC0, v3);
                    v2 = Math.min(7, v2);
                    v6 = v3 + 2;
                    v5 = 2;
                }
                else {
                    v6 = v3;
                }
                batch0.setColor(1.0f, 1.0f, 1.0f, f4);
                App.plainSmallOutline.setColor(1.0f, 1.0f, 1.0f, f4);
                float f10 = this.x(((float)(v3 + 22)));
                float f11 = this.y(((float)v2));
                Line.loadAttributeLines(this.data, monsterStats0, true);
                int v7 = monsterStats0.attributeLines.size;
                float f12 = 0.0f;
                for(int v8 = 0; v8 < v7; ++v8) {
                    Object object1 = monsterStats0.attributeLines.get(v8);
                    ((Line)object1).draw(batch0, f10, f11 - ((Line)object1).height, this.data, f4);
                    f12 += ((Line)object1).height;
                }
                if(monsterStats0.attributeLines.notEmpty()) {
                    f11 -= f12 + ((float)v4);
                }
                Array array0 = App.state.calculateStats ? monsterStats0.specialCalculated1 : monsterStats0.special1;
                if(array0.size > 0) {
                    monsterStats1 = monsterStats0;
                    f13 = f4;
                    v9 = v4;
                    v10 = v6;
                    v11 = v5;
                    App.plainSmallOutline.draw(batch0, "1:", this.x(((float)v6)), f11 + ((float)v5), 0.0f, 8, false);
                }
                else {
                    monsterStats1 = monsterStats0;
                    f13 = f4;
                    v9 = v4;
                    v10 = v6;
                    v11 = v5;
                }
                Line.loadSpecialLines(this.data, monsterStats1);
                int v12 = array0.size;
                float f14 = 0.0f;
                for(int v13 = 0; v13 < v12; ++v13) {
                    Line line0 = (Line)array0.get(v13);
                    if(v13 != 0) {
                        f14 += line0.spaceTop;
                    }
                    this.drawSpecialLine(line0, batch0, f10, f11, monsterStats1, f13);
                    f14 += line0.height;
                }
                float f15 = f11 - (f14 + ((float)v9));
                Array array1 = App.state.calculateStats ? monsterStats1.specialCalculated2 : monsterStats1.special2;
                if(array1.size > 0) {
                    f16 = f13;
                    monsterStats2 = monsterStats1;
                    App.plainSmallOutline.draw(batch0, "2:", this.x(((float)v10)), f15 + ((float)v11), 0.0f, 8, false);
                }
                else {
                    f16 = f13;
                    monsterStats2 = monsterStats1;
                }
                int v14 = array1.size;
                float f17 = 0.0f;
                for(int v15 = 0; v15 < v14; ++v15) {
                    Line line1 = (Line)array1.get(v15);
                    if(v15 != 0) {
                        f17 += line1.spaceTop;
                    }
                    this.drawSpecialLine(line1, batch0, f10, f15, monsterStats2, f16);
                    f17 = line1.height + f17;
                }
                Line line2 = App.state.calculateStats ? monsterStats2.notesCalculated : monsterStats2.notes;
                if(line2 != null) {
                    this.drawSpecialLine(line2, batch0, f10, f15 - (f17 + ((float)v9)), monsterStats2, f16);
                }
                App.plainLargeFixedNumbers.setColor(0.0f, 0.0f, 0.0f, f16);
                String s = !App.state.calculateStats || !monsterStats2.hpMax.contains("xC") ? monsterStats2.hpMax : Integer.toString(monsterStats2.hpMax());
                App.plainLargeFixedNumbers.draw(batch0, s, this.x(145.0f), this.y(((float)(v + 17))), 0.0f, 16, false);
                App.plainLargeFixedNumbers.draw(batch0, monsterStats2.move, this.x(145.0f), this.y(((float)(v + 58))), 0.0f, 16, false);
                String s1 = !App.state.calculateStats || !monsterStats2.attack.endsWith("C") ? monsterStats2.attack : Integer.toString(monsterStats2.attack());
                App.plainLargeFixedNumbers.draw(batch0, s1, this.x(145.0f), this.y(((float)(v + 100))), 0.0f, 16, false);
                App.plainLargeFixedNumbers.draw(batch0, monsterStats2.range, this.x(145.0f), this.y(((float)(v + 140))), 0.0f, 16, false);
            }
        }
        else {
            if(this.data.flying) {
                float f18 = this.x(202.0f);
                float f19 = this.y(82.0f);
                v16 = v;
                this.flyingStatDrawable.draw(batch0, f18, f19, 37.0f, 40.0f);
            }
            else {
                v16 = v;
            }
            float f20 = this.normalDim;
            if(f20 == 1.0f) {
                v19 = v16;
            }
            else {
                float f21 = (1.0f - f20) * f;
                batch0.setColor(1.0f, 1.0f, 1.0f, f21);
                MonsterStats monsterStats3 = this.data.stats[MonsterType.normal.ordinal()][this.row.level];
                App.plainSmall.setColor(0.0f, 0.0f, 0.0f, f21);
                float f22 = this.x(22.0f);
                float f23 = this.y(12.0f);
                Line.loadAttributeLines(this.data, monsterStats3, false);
                int v17 = monsterStats3.attributeLines.size;
                for(int v18 = 0; v18 < v17; ++v18) {
                    Line line3 = (Line)monsterStats3.attributeLines.get(v18);
                    line3.draw(batch0, f22, f23 - line3.height, this.data, f21);
                }
                App.plainLargeFixedNumbers.setColor(0.0f, 0.0f, 0.0f, f21);
                v19 = v16;
                App.plainLargeFixedNumbers.draw(batch0, monsterStats3.hpMax, this.x(182.0f), this.y(((float)(v16 + 15))), 0.0f, 1, false);
                App.plainLargeFixedNumbers.draw(batch0, monsterStats3.move, this.x(182.0f), this.y(((float)(v19 + 54))), 0.0f, 1, false);
                App.plainLargeFixedNumbers.draw(batch0, monsterStats3.attack, this.x(182.0f), this.y(((float)(v19 + 0x60))), 0.0f, 1, false);
                App.plainLargeFixedNumbers.draw(batch0, monsterStats3.range, this.x(182.0f), this.y(((float)(v19 + 0x8A))), 0.0f, 1, false);
            }
            float f24 = this.eliteDim;
            if(f24 != 1.0f) {
                float f25 = (1.0f - f24) * f;
                batch0.setColor(1.0f, 1.0f, 1.0f, f25);
                MonsterStats monsterStats4 = this.data.stats[MonsterType.elite.ordinal()][this.row.level];
                App.plainSmall.setColor(1.0f, 1.0f, 1.0f, f25);
                float f26 = this.x(289.0f);
                float f27 = this.y(12.0f);
                float f28 = this.x(437.0f);
                Line.loadAttributeLines(this.data, monsterStats4, true);
                int v20 = monsterStats4.attributeLines.size;
                float f29 = f26;
                for(int v21 = 0; v21 < v20; ++v21) {
                    Line line4 = (Line)monsterStats4.attributeLines.get(v21);
                    if(line4.width + f29 > f28) {
                        f29 = f28 - line4.width;
                    }
                }
                int v22 = monsterStats4.attributeLines.size;
                for(int v23 = 0; v23 < v22; ++v23) {
                    Line line5 = (Line)monsterStats4.attributeLines.get(v23);
                    line5.draw(batch0, f29, f27 - line5.height, this.data, f25);
                }
                float f30 = this.x(260.0f);
                App.plainLargeOutlineFixedNumbers.setColor(1.0f, 1.0f, 1.0f, f25);
                App.plainLargeOutlineFixedNumbers.draw(batch0, monsterStats4.hpMax, f30, this.y(((float)(v19 + 15))), 0.0f, 1, false);
                App.plainLargeOutlineFixedNumbers.draw(batch0, monsterStats4.move, f30, this.y(((float)(v19 + 54))), 0.0f, 1, false);
                App.plainLargeOutlineFixedNumbers.draw(batch0, monsterStats4.attack, f30, this.y(((float)(v19 + 0x60))), 0.0f, 1, false);
                App.plainLargeOutlineFixedNumbers.draw(batch0, monsterStats4.range, f30, this.y(((float)(v19 + 0x8A))), 0.0f, 1, false);
            }
            if(this.normalDim != 1.0f || this.eliteDim != 1.0f) {
                float f31 = this.eliteDim;
                if(f31 > 0.0f) {
                    batch0.setColor(0.0f, 0.0f, 0.0f, f31 * 0.4f * f);
                    float f32 = this.x(220.0f);
                    float f33 = this.y(182.0f);
                    this.eliteOverlay.draw(batch0, f32, f33, 219.0f, 181.0f);
                }
                float f34 = this.normalDim;
                if(f34 > 0.0f) {
                    batch0.setColor(0.0f, 0.0f, 0.0f, f34 * 0.4f * f);
                    float f35 = this.x(2.0f);
                    float f36 = this.y(182.0f);
                    this.normalOverlay.draw(batch0, f35, f36, 219.0f, 181.0f);
                }
            }
        }
        if(this.data.isBoss() || this.normalDim == 1.0f && this.eliteDim == 1.0f) {
            batch0.setColor(0.0f, 0.0f, 0.0f, this.normalDim * 0.4f * f);
            float f37 = this.x(2.0f);
            float f38 = this.y(182.0f);
            this.bossOverlay.draw(batch0, f37, f38, 437.0f, 181.0f);
        }
        if(z) {
            batch0.setShader(App.desatShader);
        }
    }

    public static void drawLevel(Batch batch0, TextureRegion textureRegion0, float f, float f1, int v, String s, float f2) {
        batch0.setColor(App.c(App.lightGray, f2));
        batch0.draw(textureRegion0, f, f1);
        App.plainSmall.setColor(0.0f, 0.0f, 0.0f, f2);
        App.plainSmall.draw(batch0, s, f + (v == 0 || v == 1 || v == 4 || v == 6 ? 15.0f : 16.0f), f1 + 16.0f, 0.0f, 1, false);
    }

    private void drawSpecialLine(Line line0, Batch batch0, float f, float f1, MonsterStats monsterStats0, float f2) {
        for(Object object0: line0.parts) {
            LinePart line$LinePart0 = (LinePart)object0;
            if(line$LinePart0 instanceof TextPart) {
                String s = ((TextPart)line$LinePart0).text;
                int v = s.indexOf("!!");
                int v1 = -1;
                if(v != -1) {
                    int v2 = monsterStats0.attack();
                    int v3 = Integer.parseInt(s.substring(v + 3, v + 4));
                    if(s.charAt(v + 2) != 45) {
                        v1 = 1;
                    }
                    line0.font.draw(batch0, s.substring(0, v) + (v2 + v3 * v1) + s.substring(v + 4), line0.x + f + line$LinePart0.x, f1 + line0.y + line$LinePart0.y + ((TextPart)line$LinePart0).layout.height);
                    continue;
                }
            }
            line$LinePart0.draw(batch0, line0, f, f1, this.data, f2);
        }
    }

    private float x(float f) {
        return this.getX() + f;
    }

    private float y(float f) {
        return this.getY() + 209.0f - f;
    }
}

