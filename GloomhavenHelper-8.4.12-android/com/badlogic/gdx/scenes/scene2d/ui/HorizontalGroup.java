package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.SnapshotArray;

public class HorizontalGroup extends WidgetGroup {
    private int align;
    private boolean expand;
    private float fill;
    private float lastPrefHeight;
    private float padBottom;
    private float padLeft;
    private float padRight;
    private float padTop;
    private float prefHeight;
    private float prefWidth;
    private boolean reverse;
    private boolean round;
    private int rowAlign;
    private FloatArray rowSizes;
    private boolean sizeInvalid;
    private float space;
    private boolean wrap;
    private boolean wrapReverse;
    private float wrapSpace;

    public HorizontalGroup() {
        this.sizeInvalid = true;
        this.align = 8;
        this.round = true;
        this.setTouchable(Touchable.childrenOnly);
    }

    public HorizontalGroup align(int v) {
        this.align = v;
        return this;
    }

    public HorizontalGroup bottom() {
        this.align &= -3;
        return this;
    }

    public HorizontalGroup center() {
        this.align = 1;
        return this;
    }

    private void computeSize() {
        float f8;
        float f7;
        int v2;
        float f6;
        float f5;
        float f4;
        int v = 0;
        this.sizeInvalid = false;
        SnapshotArray snapshotArray0 = this.getChildren();
        int v1 = snapshotArray0.size;
        this.prefHeight = 0.0f;
        if(this.wrap) {
            this.prefWidth = 0.0f;
            FloatArray floatArray0 = this.rowSizes;
            if(floatArray0 == null) {
                this.rowSizes = new FloatArray();
            }
            else {
                floatArray0.clear();
            }
            FloatArray floatArray1 = this.rowSizes;
            float f = this.space;
            float f1 = this.wrapSpace;
            float f2 = this.padLeft + this.padRight;
            float f3 = this.getWidth() - f2;
            if(this.reverse) {
                v = v1 - 1;
                v1 = -1;
                f4 = 0.0f;
                f5 = 0.0f;
                f6 = 0.0f;
                v2 = -1;
            }
            else {
                f4 = 0.0f;
                f5 = 0.0f;
                f6 = 0.0f;
                v2 = 1;
            }
            while(v != v1) {
                Actor actor0 = (Actor)snapshotArray0.get(v);
                if(actor0 instanceof Layout) {
                    f7 = ((Layout)actor0).getPrefWidth();
                    if(f7 > f3) {
                        f7 = Math.max(f3, ((Layout)actor0).getMinWidth());
                    }
                    f8 = ((Layout)actor0).getPrefHeight();
                }
                else {
                    f7 = actor0.getWidth();
                    f8 = actor0.getHeight();
                }
                float f9 = f7 + (f4 > 0.0f ? f : 0.0f);
                if(f4 + f9 <= f3 || f4 <= 0.0f) {
                    f7 = f9;
                }
                else {
                    floatArray1.add(f4);
                    floatArray1.add(f5);
                    this.prefWidth = Math.max(this.prefWidth, f4 + f2);
                    if(f6 > 0.0f) {
                        f6 += f1;
                    }
                    f6 += f5;
                    f4 = 0.0f;
                    f5 = 0.0f;
                }
                f4 += f7;
                f5 = Math.max(f5, f8);
                v += v2;
            }
            floatArray1.add(f4);
            floatArray1.add(f5);
            this.prefWidth = Math.max(this.prefWidth, f4 + f2);
            if(f6 > 0.0f) {
                f6 += f1;
            }
            this.prefHeight = Math.max(this.prefHeight, f6 + f5);
        }
        else {
            this.prefWidth = this.padLeft + this.padRight + this.space * ((float)(v1 - 1));
            while(v < v1) {
                Actor actor1 = (Actor)snapshotArray0.get(v);
                if(actor1 instanceof Layout) {
                    this.prefWidth += ((Layout)actor1).getPrefWidth();
                    this.prefHeight = Math.max(this.prefHeight, ((Layout)actor1).getPrefHeight());
                }
                else {
                    this.prefWidth += actor1.getWidth();
                    this.prefHeight = Math.max(this.prefHeight, actor1.getHeight());
                }
                ++v;
            }
        }
        this.prefHeight += this.padTop + this.padBottom;
        if(this.round) {
            this.prefWidth = (float)Math.round(this.prefWidth);
            this.prefHeight = (float)Math.round(this.prefHeight);
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    protected void drawDebugBounds(ShapeRenderer shapeRenderer0) {
        super.drawDebugBounds(shapeRenderer0);
        if(!this.getDebug()) {
            return;
        }
        shapeRenderer0.set(ShapeType.Line);
        if(this.getStage() != null) {
            shapeRenderer0.setColor(this.getStage().getDebugColor());
        }
        shapeRenderer0.rect(this.getX() + this.padLeft, this.getY() + this.padBottom, this.getOriginX(), this.getOriginY(), this.getWidth() - this.padLeft - this.padRight, this.getHeight() - this.padBottom - this.padTop, this.getScaleX(), this.getScaleY(), this.getRotation());
    }

    public HorizontalGroup expand() {
        this.expand = true;
        return this;
    }

    public HorizontalGroup expand(boolean z) {
        this.expand = z;
        return this;
    }

    public HorizontalGroup fill() {
        this.fill = 1.0f;
        return this;
    }

    public HorizontalGroup fill(float f) {
        this.fill = f;
        return this;
    }

    public int getAlign() {
        return this.align;
    }

    public boolean getExpand() {
        return this.expand;
    }

    public float getFill() {
        return this.fill;
    }

    public float getPadBottom() {
        return this.padBottom;
    }

    public float getPadLeft() {
        return this.padLeft;
    }

    public float getPadRight() {
        return this.padRight;
    }

    public float getPadTop() {
        return this.padTop;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getPrefHeight() {
        if(this.sizeInvalid) {
            this.computeSize();
        }
        return this.prefHeight;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getPrefWidth() {
        if(this.wrap) {
            return 0.0f;
        }
        if(this.sizeInvalid) {
            this.computeSize();
        }
        return this.prefWidth;
    }

    public boolean getReverse() {
        return this.reverse;
    }

    public float getSpace() {
        return this.space;
    }

    public boolean getWrap() {
        return this.wrap;
    }

    public boolean getWrapReverse() {
        return this.wrapReverse;
    }

    public float getWrapSpace() {
        return this.wrapSpace;
    }

    public HorizontalGroup grow() {
        this.expand = true;
        this.fill = 1.0f;
        return this;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public void invalidate() {
        super.invalidate();
        this.sizeInvalid = true;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public void layout() {
        int v5;
        boolean z1;
        float f9;
        float f6;
        float f5;
        if(this.sizeInvalid) {
            this.computeSize();
        }
        if(this.wrap) {
            this.layoutWrapped();
            return;
        }
        boolean z = this.round;
        int v = this.align;
        float f = this.space;
        float f1 = this.padBottom;
        float f2 = this.fill;
        float f3 = (this.expand ? this.getHeight() : this.prefHeight) - this.padTop - f1;
        float f4 = this.padLeft;
        if((v & 16) != 0) {
            f4 += this.getWidth() - this.prefWidth;
        }
        else if((v & 8) == 0) {
            f4 += (this.getWidth() - this.prefWidth) / 2.0f;
        }
        if((v & 4) == 0) {
            f1 = (v & 2) == 0 ? f1 + (this.getHeight() - f1 - this.padTop - f3) / 2.0f : this.getHeight() - this.padTop - f3;
        }
        int v1 = this.rowAlign;
        SnapshotArray snapshotArray0 = this.getChildren();
        int v2 = 0;
        int v3 = snapshotArray0.size;
        int v4 = -1;
        if(this.reverse) {
            v2 = v3 - 1;
            v3 = -1;
        }
        else {
            v4 = 1;
        }
        while(v2 != v3) {
            Actor actor0 = (Actor)snapshotArray0.get(v2);
            Layout layout0 = null;
            if(actor0 instanceof Layout) {
                layout0 = (Layout)actor0;
                f5 = layout0.getPrefWidth();
                f6 = layout0.getPrefHeight();
            }
            else {
                f5 = actor0.getWidth();
                f6 = actor0.getHeight();
            }
            if(f2 > 0.0f) {
                f6 = f3 * f2;
            }
            float f7 = f6;
            if(layout0 != null) {
                f7 = Math.max(f7, layout0.getMinHeight());
                float f8 = layout0.getMaxHeight();
                if(f8 > 0.0f && f7 > f8) {
                    f7 = f8;
                }
            }
            if((v1 & 2) == 0) {
                f9 = (v1 & 4) == 0 ? (f3 - f7) / 2.0f + f1 : f1;
            }
            else {
                f9 = f3 - f7 + f1;
            }
            if(z) {
                z1 = true;
                v5 = v1;
                actor0.setBounds(((float)Math.round(f4)), ((float)Math.round(f9)), ((float)Math.round(f5)), ((float)Math.round(f7)));
            }
            else {
                z1 = false;
                v5 = v1;
                actor0.setBounds(f4, f9, f5, f7);
            }
            f4 += f5 + f;
            if(layout0 != null) {
                layout0.validate();
            }
            v2 += v4;
            z = z1;
            v1 = v5;
        }
    }

    private void layoutWrapped() {
        boolean z1;
        int v7;
        float f21;
        float f19;
        float f16;
        float f15;
        float f17;
        float f14;
        float f13;
        int v4;
        int v3;
        float f = this.getPrefHeight();
        if(f != this.lastPrefHeight) {
            this.lastPrefHeight = f;
            this.invalidateHierarchy();
        }
        int v = this.align;
        boolean z = this.round;
        float f1 = this.space;
        float f2 = this.fill;
        float f3 = this.wrapSpace;
        float f4 = this.prefWidth - this.padLeft - this.padRight;
        float f5 = f - this.padTop;
        float f6 = this.getWidth();
        float f7 = this.padLeft;
        float f8 = -1.0f;
        if((v & 2) != 0) {
            f5 += this.getHeight() - f;
        }
        else if((v & 4) == 0) {
            f5 += (this.getHeight() - f) / 2.0f;
        }
        if(this.wrapReverse) {
            f5 -= f + this.rowSizes.get(1);
            f8 = 1.0f;
        }
        if((v & 16) != 0) {
            f7 += f6 - this.prefWidth;
        }
        else if((v & 8) == 0) {
            f7 += (f6 - this.prefWidth) / 2.0f;
        }
        float f9 = f6 - this.padRight;
        int v1 = this.rowAlign;
        FloatArray floatArray0 = this.rowSizes;
        SnapshotArray snapshotArray0 = this.getChildren();
        int v2 = snapshotArray0.size;
        if(this.reverse) {
            v3 = v2 - 1;
            v2 = -1;
            v4 = -1;
        }
        else {
            v3 = 0;
            v4 = 1;
        }
        float f10 = f5;
        float f11 = 0.0f;
        int v5 = 0;
        float f12 = 0.0f;
        while(v3 != v2) {
            Actor actor0 = (Actor)snapshotArray0.get(v3);
            Layout layout0 = null;
            if(actor0 instanceof Layout) {
                layout0 = (Layout)actor0;
                f13 = layout0.getPrefWidth();
                if(f13 > f9) {
                    f13 = Math.max(f9, layout0.getMinWidth());
                }
                f14 = layout0.getPrefHeight();
            }
            else {
                f13 = actor0.getWidth();
                f14 = actor0.getHeight();
            }
            if(f11 + f13 > f9 || v5 == 0) {
                int v6 = Math.min(v5, floatArray0.size - 2);
                if((v1 & 16) == 0) {
                    f17 = (v1 & 8) == 0 ? (f4 - floatArray0.get(v6)) / 2.0f + f7 : f7;
                }
                else {
                    f17 = f4 - floatArray0.get(v6) + f7;
                }
                f15 = f4;
                f16 = floatArray0.get(v6 + 1);
                if(v6 > 0) {
                    f10 += f3 * f8;
                }
                f10 += f16 * f8;
                v5 = v6 + 2;
                f11 = f17;
            }
            else {
                f15 = f4;
                f16 = f12;
            }
            if(f2 > 0.0f) {
                f14 = f16 * f2;
            }
            float f18 = f14;
            if(layout0 == null) {
                f19 = f2;
            }
            else {
                f19 = f2;
                f18 = Math.max(f18, layout0.getMinHeight());
                float f20 = layout0.getMaxHeight();
                if(f20 > 0.0f && f18 > f20) {
                    f18 = f20;
                }
            }
            if((v1 & 2) == 0) {
                f21 = (v1 & 4) == 0 ? f10 + (f16 - f18) / 2.0f : f10;
            }
            else {
                f21 = f10 + (f16 - f18);
            }
            if(z) {
                v7 = v1;
                z1 = true;
                actor0.setBounds(((float)Math.round(f11)), ((float)Math.round(f21)), ((float)Math.round(f13)), ((float)Math.round(f18)));
            }
            else {
                v7 = v1;
                z1 = false;
                actor0.setBounds(f11, f21, f13, f18);
            }
            f11 += f13 + f1;
            if(layout0 != null) {
                layout0.validate();
            }
            v3 += v4;
            f2 = f19;
            v1 = v7;
            z = z1;
            f12 = f16;
            f4 = f15;
        }
    }

    public HorizontalGroup left() {
        this.align &= -17;
        return this;
    }

    public HorizontalGroup pad(float f) {
        this.padTop = f;
        this.padLeft = f;
        this.padBottom = f;
        this.padRight = f;
        return this;
    }

    public HorizontalGroup pad(float f, float f1, float f2, float f3) {
        this.padTop = f;
        this.padLeft = f1;
        this.padBottom = f2;
        this.padRight = f3;
        return this;
    }

    public HorizontalGroup padBottom(float f) {
        this.padBottom = f;
        return this;
    }

    public HorizontalGroup padLeft(float f) {
        this.padLeft = f;
        return this;
    }

    public HorizontalGroup padRight(float f) {
        this.padRight = f;
        return this;
    }

    public HorizontalGroup padTop(float f) {
        this.padTop = f;
        return this;
    }

    public HorizontalGroup reverse() {
        this.reverse = true;
        return this;
    }

    public HorizontalGroup reverse(boolean z) {
        this.reverse = z;
        return this;
    }

    public HorizontalGroup right() {
        this.align &= -9;
        return this;
    }

    public HorizontalGroup rowAlign(int v) {
        this.rowAlign = v;
        return this;
    }

    public HorizontalGroup rowBottom() {
        this.rowAlign &= -3;
        return this;
    }

    public HorizontalGroup rowCenter() {
        this.rowAlign = 1;
        return this;
    }

    public HorizontalGroup rowLeft() {
        this.rowAlign &= -17;
        return this;
    }

    public HorizontalGroup rowRight() {
        this.rowAlign &= -9;
        return this;
    }

    public HorizontalGroup rowTop() {
        this.rowAlign &= -5;
        return this;
    }

    public void setRound(boolean z) {
        this.round = z;
    }

    public HorizontalGroup space(float f) {
        this.space = f;
        return this;
    }

    public HorizontalGroup top() {
        this.align &= -5;
        return this;
    }

    public HorizontalGroup wrap() {
        this.wrap = true;
        return this;
    }

    public HorizontalGroup wrap(boolean z) {
        this.wrap = z;
        return this;
    }

    public HorizontalGroup wrapReverse() {
        this.wrapReverse = true;
        return this;
    }

    public HorizontalGroup wrapReverse(boolean z) {
        this.wrapReverse = z;
        return this;
    }

    public HorizontalGroup wrapSpace(float f) {
        this.wrapSpace = f;
        return this;
    }
}

