package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.SnapshotArray;

public class VerticalGroup extends WidgetGroup {
    private int align;
    private int columnAlign;
    private FloatArray columnSizes;
    private boolean expand;
    private float fill;
    private float lastPrefWidth;
    private float padBottom;
    private float padLeft;
    private float padRight;
    private float padTop;
    private float prefHeight;
    private float prefWidth;
    private boolean reverse;
    private boolean round;
    private boolean sizeInvalid;
    private float space;
    private boolean wrap;
    private float wrapSpace;

    public VerticalGroup() {
        this.sizeInvalid = true;
        this.align = 2;
        this.round = true;
        this.setTouchable(Touchable.childrenOnly);
    }

    public VerticalGroup align(int v) {
        this.align = v;
        return this;
    }

    public VerticalGroup bottom() {
        this.align &= -3;
        return this;
    }

    public VerticalGroup center() {
        this.align = 1;
        return this;
    }

    public VerticalGroup columnAlign(int v) {
        this.columnAlign = v;
        return this;
    }

    public VerticalGroup columnBottom() {
        this.columnAlign &= -3;
        return this;
    }

    public VerticalGroup columnCenter() {
        this.columnAlign = 1;
        return this;
    }

    public VerticalGroup columnLeft() {
        this.columnAlign &= -17;
        return this;
    }

    public VerticalGroup columnRight() {
        this.columnAlign &= -9;
        return this;
    }

    public VerticalGroup columnTop() {
        this.columnAlign &= -5;
        return this;
    }

    private void computeSize() {
        float f9;
        float f7;
        int v2;
        float f6;
        float f5;
        float f4;
        int v = 0;
        this.sizeInvalid = false;
        SnapshotArray snapshotArray0 = this.getChildren();
        int v1 = snapshotArray0.size;
        this.prefWidth = 0.0f;
        if(this.wrap) {
            this.prefHeight = 0.0f;
            FloatArray floatArray0 = this.columnSizes;
            if(floatArray0 == null) {
                this.columnSizes = new FloatArray();
            }
            else {
                floatArray0.clear();
            }
            FloatArray floatArray1 = this.columnSizes;
            float f = this.space;
            float f1 = this.wrapSpace;
            float f2 = this.padTop + this.padBottom;
            float f3 = this.getHeight() - f2;
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
                    float f8 = ((Layout)actor0).getPrefHeight();
                    f9 = f8 <= f3 ? f8 : Math.max(f3, ((Layout)actor0).getMinHeight());
                }
                else {
                    f7 = actor0.getWidth();
                    f9 = actor0.getHeight();
                }
                float f10 = f9 + (f4 > 0.0f ? f : 0.0f);
                if(f4 + f10 <= f3 || f4 <= 0.0f) {
                    f9 = f10;
                }
                else {
                    floatArray1.add(f4);
                    floatArray1.add(f5);
                    this.prefHeight = Math.max(this.prefHeight, f4 + f2);
                    if(f6 > 0.0f) {
                        f6 += f1;
                    }
                    f6 += f5;
                    f4 = 0.0f;
                    f5 = 0.0f;
                }
                f4 += f9;
                f5 = Math.max(f5, f7);
                v += v2;
            }
            floatArray1.add(f4);
            floatArray1.add(f5);
            this.prefHeight = Math.max(this.prefHeight, f4 + f2);
            if(f6 > 0.0f) {
                f6 += f1;
            }
            this.prefWidth = Math.max(this.prefWidth, f6 + f5);
        }
        else {
            this.prefHeight = this.padTop + this.padBottom + this.space * ((float)(v1 - 1));
            while(v < v1) {
                Actor actor1 = (Actor)snapshotArray0.get(v);
                if(actor1 instanceof Layout) {
                    this.prefWidth = Math.max(this.prefWidth, ((Layout)actor1).getPrefWidth());
                    this.prefHeight += ((Layout)actor1).getPrefHeight();
                }
                else {
                    this.prefWidth = Math.max(this.prefWidth, actor1.getWidth());
                    this.prefHeight += actor1.getHeight();
                }
                ++v;
            }
        }
        this.prefWidth += this.padLeft + this.padRight;
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

    public VerticalGroup expand() {
        this.expand = true;
        return this;
    }

    public VerticalGroup expand(boolean z) {
        this.expand = z;
        return this;
    }

    public VerticalGroup fill() {
        this.fill = 1.0f;
        return this;
    }

    public VerticalGroup fill(float f) {
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
        if(this.wrap) {
            return 0.0f;
        }
        if(this.sizeInvalid) {
            this.computeSize();
        }
        return this.prefHeight;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getPrefWidth() {
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

    public float getWrapSpace() {
        return this.wrapSpace;
    }

    public VerticalGroup grow() {
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
        boolean z1;
        float f9;
        float f8;
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
        float f1 = this.padLeft;
        float f2 = this.fill;
        float f3 = (this.expand ? this.getWidth() : this.prefWidth) - f1 - this.padRight;
        float f4 = this.prefHeight - this.padTop + f;
        if((v & 2) != 0) {
            f4 += this.getHeight() - this.prefHeight;
        }
        else if((v & 4) == 0) {
            f4 += (this.getHeight() - this.prefHeight) / 2.0f;
        }
        if((v & 8) == 0) {
            f1 = (v & 16) == 0 ? f1 + (this.getWidth() - f1 - this.padRight - f3) / 2.0f : this.getWidth() - this.padRight - f3;
        }
        int v1 = this.columnAlign;
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
                f5 = f3 * f2;
            }
            if(layout0 != null) {
                f5 = Math.max(f5, layout0.getMinWidth());
                float f7 = layout0.getMaxWidth();
                if(f7 > 0.0f && f5 > f7) {
                    f5 = f7;
                }
            }
            if((v1 & 16) != 0) {
                f8 = f3 - f5 + f1;
                f9 = f6;
            }
            else if((v1 & 8) == 0) {
                f8 = (f3 - f5) / 2.0f + f1;
                f9 = f6;
            }
            else {
                f9 = f6;
                f8 = f1;
            }
            f4 -= f9 + f;
            if(z) {
                z1 = true;
                actor0.setBounds(((float)Math.round(f8)), ((float)Math.round(f4)), ((float)Math.round(f5)), ((float)Math.round(f9)));
            }
            else {
                z1 = false;
                actor0.setBounds(f8, f4, f5, f9);
            }
            if(layout0 != null) {
                layout0.validate();
            }
            v2 += v4;
            z = z1;
        }
    }

    private void layoutWrapped() {
        FloatArray floatArray1;
        float f20;
        float f17;
        float f15;
        float f14;
        int v5;
        int v4;
        float f = this.getPrefWidth();
        if(f != this.lastPrefWidth) {
            this.lastPrefWidth = f;
            this.invalidateHierarchy();
        }
        int v = this.align;
        boolean z = this.round;
        float f1 = this.space;
        float f2 = this.padLeft;
        float f3 = this.fill;
        float f4 = this.wrapSpace;
        float f5 = this.prefHeight - this.padTop - this.padBottom;
        float f6 = this.getHeight();
        float f7 = this.prefHeight - this.padTop + f1;
        if((v & 16) != 0) {
            f2 += this.getWidth() - f;
        }
        else if((v & 8) == 0) {
            f2 += (this.getWidth() - f) / 2.0f;
        }
        if((v & 2) != 0) {
            f7 += f6 - this.prefHeight;
        }
        else if((v & 4) == 0) {
            f7 += (f6 - this.prefHeight) / 2.0f;
        }
        float f8 = f6 - this.padTop;
        int v1 = this.columnAlign;
        FloatArray floatArray0 = this.columnSizes;
        SnapshotArray snapshotArray0 = this.getChildren();
        int v2 = snapshotArray0.size;
        int v3 = -1;
        if(this.reverse) {
            v4 = v2 - 1;
            v5 = -1;
        }
        else {
            v3 = v2;
            v4 = 0;
            v5 = 1;
        }
        float f9 = f2;
        float f10 = 0.0f;
        int v6 = 0;
        float f11 = 0.0f;
        while(v4 != v3) {
            Actor actor0 = (Actor)snapshotArray0.get(v4);
            Layout layout0 = null;
            if(actor0 instanceof Layout) {
                layout0 = (Layout)actor0;
                float f12 = layout0.getPrefWidth();
                float f13 = layout0.getPrefHeight();
                if(f13 > f8) {
                    f14 = f12;
                    f13 = Math.max(f8, layout0.getMinHeight());
                }
                else {
                    f14 = f12;
                }
                f15 = f13;
            }
            else {
                f14 = actor0.getWidth();
                f15 = actor0.getHeight();
            }
            float f16 = f10;
            if(f10 - f15 - f1 < this.padBottom || v6 == 0) {
                int v7 = Math.min(v6, floatArray0.size - 2);
                if((v1 & 4) == 0) {
                    f17 = (v1 & 2) == 0 ? f7 - (f5 - floatArray0.get(v7)) / 2.0f : f7;
                }
                else {
                    f17 = f7 - (f5 - floatArray0.get(v7));
                }
                if(v7 > 0) {
                    f9 = f9 + f4 + f11;
                }
                f11 = floatArray0.get(v7 + 1);
                f16 = f17;
                v6 = v7 + 2;
            }
            if(f3 > 0.0f) {
                f14 = f11 * f3;
            }
            float f18 = f14;
            if(layout0 != null) {
                f18 = Math.max(f18, layout0.getMinWidth());
                float f19 = layout0.getMaxWidth();
                if(f19 > 0.0f && f18 > f19) {
                    f18 = f19;
                }
            }
            if((v1 & 16) == 0) {
                f20 = (v1 & 8) == 0 ? f9 + (f11 - f18) / 2.0f : f9;
            }
            else {
                f20 = f9 + (f11 - f18);
            }
            float f21 = f16 - (f15 + f1);
            if(z) {
                floatArray1 = floatArray0;
                actor0.setBounds(((float)Math.round(f20)), ((float)Math.round(f21)), ((float)Math.round(f18)), ((float)Math.round(f15)));
            }
            else {
                floatArray1 = floatArray0;
                actor0.setBounds(f20, f21, f18, f15);
            }
            if(layout0 != null) {
                layout0.validate();
            }
            v4 += v5;
            f10 = f21;
            floatArray0 = floatArray1;
        }
    }

    public VerticalGroup left() {
        this.align &= -17;
        return this;
    }

    public VerticalGroup pad(float f) {
        this.padTop = f;
        this.padLeft = f;
        this.padBottom = f;
        this.padRight = f;
        return this;
    }

    public VerticalGroup pad(float f, float f1, float f2, float f3) {
        this.padTop = f;
        this.padLeft = f1;
        this.padBottom = f2;
        this.padRight = f3;
        return this;
    }

    public VerticalGroup padBottom(float f) {
        this.padBottom = f;
        return this;
    }

    public VerticalGroup padLeft(float f) {
        this.padLeft = f;
        return this;
    }

    public VerticalGroup padRight(float f) {
        this.padRight = f;
        return this;
    }

    public VerticalGroup padTop(float f) {
        this.padTop = f;
        return this;
    }

    public VerticalGroup reverse() {
        this.reverse = true;
        return this;
    }

    public VerticalGroup reverse(boolean z) {
        this.reverse = z;
        return this;
    }

    public VerticalGroup right() {
        this.align &= -9;
        return this;
    }

    public void setRound(boolean z) {
        this.round = z;
    }

    public VerticalGroup space(float f) {
        this.space = f;
        return this;
    }

    public VerticalGroup top() {
        this.align &= -5;
        return this;
    }

    public VerticalGroup wrap() {
        this.wrap = true;
        return this;
    }

    public VerticalGroup wrap(boolean z) {
        this.wrap = z;
        return this;
    }

    public VerticalGroup wrapSpace(float f) {
        this.wrapSpace = f;
        return this;
    }
}

