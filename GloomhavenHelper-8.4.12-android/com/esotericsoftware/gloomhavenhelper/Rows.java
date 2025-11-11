package com.esotericsoftware.gloomhavenhelper;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.utils.SnapshotArray;
import com.esotericsoftware.gloomhavenhelper.util.Animator;
import com.esotericsoftware.gloomhavenhelper.util.Row;

public class Rows extends WidgetGroup {
    public int columns;
    public float maxSpaceX;
    public float minSpaceX;
    public float padBottom;
    public float padLeft;
    public float padRight;
    public float padTop;
    private float prefHeight;
    private boolean sizeInvalid;
    public float spaceX;
    public float spaceY;
    public float startX;

    public Rows() {
        this.padTop = 11.0f;
        this.padBottom = 13.0f;
        this.padLeft = 6.0f;
        this.padRight = 6.0f;
        this.minSpaceX = 7.0f;
        this.maxSpaceX = 80.0f;
        this.spaceY = 11.0f;
    }

    private void computeSize() {
        float f5;
        this.sizeInvalid = false;
        float f = App.stage.getWidth() - this.padLeft - this.padRight;
        int v = Math.max(1, ((int)Math.floor((f - this.minSpaceX) / 1101.0f)));
        this.spaceX = MathUtils.clamp(f - ((float)(this.columns * 1101)), this.minSpaceX, this.maxSpaceX);
        SnapshotArray snapshotArray0 = this.getChildren();
        int v1 = snapshotArray0.size;
        float f1 = App.stage.getHeight() - 240.0f - this.padTop - this.padBottom;
        Row.animatedHeight = false;
        for(float f2 = -1.0f; true; f2 = f1) {
            this.columns = 1;
            float f3 = f2;
            float f4 = 0.0f;
            f5 = 0.0f;
            boolean z = true;
            for(int v2 = 0; v2 < v1; ++v2) {
                Row row0 = (Row)snapshotArray0.get(v2);
                if(!App.config.hideMonsters || !(row0 instanceof MonsterRow)) {
                    float f6 = row0.getPrefHeight();
                    if(z) {
                        z = false;
                    }
                    else {
                        f4 += this.spaceY;
                        int v3 = this.columns;
                        if(v3 < v) {
                            float f7 = f4 + f6;
                            if(f7 > f1) {
                                this.columns = v3 + 1;
                                f3 = f7;
                                f4 = 0.0f;
                                z = true;
                            }
                        }
                    }
                    f4 += f6;
                    f5 = Math.max(f5, f4);
                }
            }
            if(this.columns <= 1 || f4 <= f1) {
                break;
            }
            f1 = f3;
        }
        Row.animatedHeight = true;
        this.prefHeight = f5 + this.padTop + this.padBottom;
        this.startX = (float)Math.round((f - (((float)(this.columns * 1101)) + this.spaceX * ((float)(this.columns - 1)))) / 2.0f + this.padLeft);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    protected void drawChildren(Batch batch0, float f) {
        int v;
        Animator.updateChildren(this);
        if(App.dragRow == null) {
            v = 0;
        }
        else {
            v = App.dragRow.getZIndex();
            if(this.columns > 1) {
                float f1 = App.dragRow.getX();
                float f2 = App.dragRow.getY();
                App.dragRow.setPosition(App.dragRow.finalX, App.dragRow.finalY);
                App.dragRow.draw(batch0, 0.25f * f);
                App.dragRow.setPosition(f1, f2);
            }
            App.dragRow.toFront();
        }
        super.drawChildren(batch0, f);
        if(App.dragRow != null && v != -1) {
            App.dragRow.setZIndex(v);
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getPrefHeight() {
        if(this.sizeInvalid) {
            this.computeSize();
        }
        return this.prefHeight;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public void invalidate() {
        if(!Animator.enabled || !Animator.childrenAnimating(this)) {
            super.invalidate();
            this.sizeInvalid = true;
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public void layout() {
        float f11;
        float f1;
        float f;
        if(this.sizeInvalid) {
            this.computeSize();
        }
        if(App.dragRow == null) {
            f = 0.0f;
            f1 = 0.0f;
        }
        else {
            f = App.dragRow.getX();
            f1 = App.dragRow.getY();
        }
        SnapshotArray snapshotArray0 = this.getChildren();
        float f2 = this.startX;
        float f3 = this.prefHeight - this.padTop;
        float f4 = this.getHeight() - this.prefHeight;
        int v = snapshotArray0.size;
        float f5 = f2;
        float f6 = f3;
        float f7 = 0.0f;
        boolean z = true;
        for(int v1 = 0; v1 < v; ++v1) {
            Row row0 = (Row)snapshotArray0.get(v1);
            if(!App.config.hideMonsters || !(row0 instanceof MonsterRow)) {
                float f8 = row0.getPrefHeight();
                MonsterRow.animatedHeight = false;
                float f9 = row0.getPrefHeight();
                MonsterRow.animatedHeight = true;
                if(z) {
                    f11 = f7;
                    z = false;
                }
                else {
                    f6 -= this.spaceY;
                    float f10 = this.spaceY + f7;
                    if(f10 + f9 <= this.prefHeight) {
                        f11 = f10;
                    }
                    else {
                        f5 += this.spaceX + 1101.0f;
                        f6 = f3;
                        f11 = 0.0f;
                    }
                }
                f6 -= f8;
                row0.setBounds(((float)Math.round(f5)), ((float)Math.round(f4 + f6)), 1101.0f, ((float)Math.round(f8)));
                row0.finalX = row0.getX();
                row0.finalY = row0.getY();
                row0.validate();
                f7 = f11 + f9;
            }
            else {
                row0.setBounds(0.0f, 0.0f, 1101.0f, 0.0f);
                row0.finalX = 0.0f;
                row0.finalY = 0.0f;
                row0.validate();
            }
        }
        if(App.dragRow != null) {
            App.dragRow.setPosition(f, f1);
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    protected void sizeChanged() {
        super.sizeChanged();
    }
}

