package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Cullable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.Null;

public class Container extends WidgetGroup {
    @Null
    private Actor actor;
    private int align;
    @Null
    private Drawable background;
    private boolean clip;
    private float fillX;
    private float fillY;
    private Value maxHeight;
    private Value maxWidth;
    private Value minHeight;
    private Value minWidth;
    private Value padBottom;
    private Value padLeft;
    private Value padRight;
    private Value padTop;
    private Value prefHeight;
    private Value prefWidth;
    private boolean round;

    public Container() {
        this.minWidth = Value.minWidth;
        this.minHeight = Value.minHeight;
        this.prefWidth = Value.prefWidth;
        this.prefHeight = Value.prefHeight;
        this.maxWidth = Value.zero;
        this.maxHeight = Value.zero;
        this.padTop = Value.zero;
        this.padLeft = Value.zero;
        this.padBottom = Value.zero;
        this.padRight = Value.zero;
        this.round = true;
        this.setTouchable(Touchable.childrenOnly);
        this.setTransform(false);
    }

    public Container(@Null Actor actor0) {
        this.setActor(actor0);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    @Deprecated
    public void addActor(Actor actor0) {
        throw new UnsupportedOperationException("Use Container#setActor.");
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    @Deprecated
    public void addActorAfter(Actor actor0, Actor actor1) {
        throw new UnsupportedOperationException("Use Container#setActor.");
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    @Deprecated
    public void addActorAt(int v, Actor actor0) {
        throw new UnsupportedOperationException("Use Container#setActor.");
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    @Deprecated
    public void addActorBefore(Actor actor0, Actor actor1) {
        throw new UnsupportedOperationException("Use Container#setActor.");
    }

    public Container align(int v) {
        this.align = v;
        return this;
    }

    public Container background(@Null Drawable drawable0) {
        this.setBackground(drawable0);
        return this;
    }

    public Container bottom() {
        this.align &= -3;
        return this;
    }

    public Container center() {
        this.align = 1;
        return this;
    }

    public Container clip() {
        this.setClip(true);
        return this;
    }

    public Container clip(boolean z) {
        this.setClip(z);
        return this;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public void draw(Batch batch0, float f) {
        this.validate();
        if(this.isTransform()) {
            this.applyTransform(batch0, this.computeTransform());
            this.drawBackground(batch0, f, 0.0f, 0.0f);
            if(this.clip) {
                batch0.flush();
                float f1 = this.padLeft.get(this);
                float f2 = this.padBottom.get(this);
                if(this.clipBegin(f1, f2, this.getWidth() - f1 - this.padRight.get(this), this.getHeight() - f2 - this.padTop.get(this))) {
                    this.drawChildren(batch0, f);
                    batch0.flush();
                    this.clipEnd();
                }
            }
            else {
                this.drawChildren(batch0, f);
            }
            this.resetTransform(batch0);
            return;
        }
        this.drawBackground(batch0, f, this.getX(), this.getY());
        super.draw(batch0, f);
    }

    protected void drawBackground(Batch batch0, float f, float f1, float f2) {
        if(this.background == null) {
            return;
        }
        Color color0 = this.getColor();
        batch0.setColor(color0.r, color0.g, color0.b, color0.a * f);
        this.background.draw(batch0, f1, f2, this.getWidth(), this.getHeight());
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public void drawDebug(ShapeRenderer shapeRenderer0) {
        this.validate();
        if(this.isTransform()) {
            this.applyTransform(shapeRenderer0, this.computeTransform());
            if(this.clip) {
                shapeRenderer0.flush();
                float f = this.padLeft.get(this);
                float f1 = this.padBottom.get(this);
                if((this.background == null ? this.clipBegin(0.0f, 0.0f, this.getWidth(), this.getHeight()) : this.clipBegin(f, f1, this.getWidth() - f - this.padRight.get(this), this.getHeight() - f1 - this.padTop.get(this)))) {
                    this.drawDebugChildren(shapeRenderer0);
                    this.clipEnd();
                }
            }
            else {
                this.drawDebugChildren(shapeRenderer0);
            }
            this.resetTransform(shapeRenderer0);
            return;
        }
        super.drawDebug(shapeRenderer0);
    }

    public Container fill() {
        this.fillX = 1.0f;
        this.fillY = 1.0f;
        return this;
    }

    public Container fill(float f, float f1) {
        this.fillX = f;
        this.fillY = f1;
        return this;
    }

    public Container fill(boolean z) {
        float f = 1.0f;
        this.fillX = z ? 1.0f : 0.0f;
        if(!z) {
            f = 0.0f;
        }
        this.fillY = f;
        return this;
    }

    public Container fill(boolean z, boolean z1) {
        float f = 1.0f;
        this.fillX = z ? 1.0f : 0.0f;
        if(!z1) {
            f = 0.0f;
        }
        this.fillY = f;
        return this;
    }

    public Container fillX() {
        this.fillX = 1.0f;
        return this;
    }

    public Container fillY() {
        this.fillY = 1.0f;
        return this;
    }

    @Null
    public Actor getActor() {
        return this.actor;
    }

    public int getAlign() {
        return this.align;
    }

    @Null
    public Drawable getBackground() {
        return this.background;
    }

    public boolean getClip() {
        return this.clip;
    }

    public float getFillX() {
        return this.fillX;
    }

    public float getFillY() {
        return this.fillY;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getMaxHeight() {
        float f = this.maxHeight.get(this.actor);
        return f > 0.0f ? f + (this.padTop.get(this) + this.padBottom.get(this)) : f;
    }

    public Value getMaxHeightValue() {
        return this.maxHeight;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getMaxWidth() {
        float f = this.maxWidth.get(this.actor);
        return f > 0.0f ? f + (this.padLeft.get(this) + this.padRight.get(this)) : f;
    }

    public Value getMaxWidthValue() {
        return this.maxWidth;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getMinHeight() {
        return this.minHeight.get(this.actor) + this.padTop.get(this) + this.padBottom.get(this);
    }

    public Value getMinHeightValue() {
        return this.minHeight;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getMinWidth() {
        return this.minWidth.get(this.actor) + this.padLeft.get(this) + this.padRight.get(this);
    }

    public float getPadBottom() {
        return this.padBottom.get(this);
    }

    public Value getPadBottomValue() {
        return this.padBottom;
    }

    public float getPadLeft() {
        return this.padLeft.get(this);
    }

    public Value getPadLeftValue() {
        return this.padLeft;
    }

    public float getPadRight() {
        return this.padRight.get(this);
    }

    public Value getPadRightValue() {
        return this.padRight;
    }

    public float getPadTop() {
        return this.padTop.get(this);
    }

    public Value getPadTopValue() {
        return this.padTop;
    }

    public float getPadX() {
        return this.padLeft.get(this) + this.padRight.get(this);
    }

    public float getPadY() {
        return this.padTop.get(this) + this.padBottom.get(this);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getPrefHeight() {
        float f = this.prefHeight.get(this.actor);
        Drawable drawable0 = this.background;
        if(drawable0 != null) {
            f = Math.max(f, drawable0.getMinHeight());
        }
        return Math.max(this.getMinHeight(), f + this.padTop.get(this) + this.padBottom.get(this));
    }

    public Value getPrefHeightValue() {
        return this.prefHeight;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getPrefWidth() {
        float f = this.prefWidth.get(this.actor);
        Drawable drawable0 = this.background;
        if(drawable0 != null) {
            f = Math.max(f, drawable0.getMinWidth());
        }
        return Math.max(this.getMinWidth(), f + this.padLeft.get(this) + this.padRight.get(this));
    }

    public Value getPrefWidthValue() {
        return this.prefWidth;
    }

    public Container height(float f) {
        this.height(Fixed.valueOf(f));
        return this;
    }

    public Container height(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("height cannot be null.");
        }
        this.minHeight = value0;
        this.prefHeight = value0;
        this.maxHeight = value0;
        return this;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    @Null
    public Actor hit(float f, float f1, boolean z) {
        if(this.clip) {
            if(z && this.getTouchable() == Touchable.disabled) {
                return null;
            }
            return f >= 0.0f && f < this.getWidth() && f1 >= 0.0f && f1 < this.getHeight() ? super.hit(f, f1, z) : null;
        }
        return super.hit(f, f1, z);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public void layout() {
        if(this.actor == null) {
            return;
        }
        float f = this.padLeft.get(this);
        float f1 = this.padBottom.get(this);
        float f2 = this.getWidth() - f - this.padRight.get(this);
        float f3 = this.getHeight() - f1 - this.padTop.get(this);
        float f4 = this.minWidth.get(this.actor);
        float f5 = this.minHeight.get(this.actor);
        float f6 = this.prefWidth.get(this.actor);
        float f7 = this.prefHeight.get(this.actor);
        float f8 = this.maxWidth.get(this.actor);
        float f9 = this.maxHeight.get(this.actor);
        float f10 = this.fillX > 0.0f ? this.fillX * f2 : Math.min(f6, f2);
        if(f10 >= f4) {
            f4 = f10;
        }
        if(f8 > 0.0f && f4 > f8) {
            f4 = f8;
        }
        float f11 = this.fillY > 0.0f ? this.fillY * f3 : Math.min(f7, f3);
        if(f11 >= f5) {
            f5 = f11;
        }
        if(f9 > 0.0f && f5 > f9) {
            f5 = f9;
        }
        int v = this.align;
        if((v & 16) != 0) {
            f += f2 - f4;
        }
        else if((v & 8) == 0) {
            f += (f2 - f4) / 2.0f;
        }
        int v1 = this.align;
        if((v1 & 2) != 0) {
            f1 += f3 - f5;
        }
        else if((v1 & 4) == 0) {
            f1 += (f3 - f5) / 2.0f;
        }
        if(this.round) {
            f = (float)Math.round(f);
            f1 = (float)Math.round(f1);
            f4 = (float)Math.round(f4);
            f5 = (float)Math.round(f5);
        }
        this.actor.setBounds(f, f1, f4, f5);
        Actor actor0 = this.actor;
        if(actor0 instanceof Layout) {
            ((Layout)actor0).validate();
        }
    }

    public Container left() {
        this.align &= -17;
        return this;
    }

    public Container maxHeight(float f) {
        this.maxHeight = Fixed.valueOf(f);
        return this;
    }

    public Container maxHeight(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("maxHeight cannot be null.");
        }
        this.maxHeight = value0;
        return this;
    }

    public Container maxSize(float f) {
        this.maxSize(Fixed.valueOf(f));
        return this;
    }

    public Container maxSize(float f, float f1) {
        this.maxSize(Fixed.valueOf(f), Fixed.valueOf(f1));
        return this;
    }

    public Container maxSize(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("size cannot be null.");
        }
        this.maxWidth = value0;
        this.maxHeight = value0;
        return this;
    }

    public Container maxSize(Value value0, Value value1) {
        if(value0 == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        if(value1 == null) {
            throw new IllegalArgumentException("height cannot be null.");
        }
        this.maxWidth = value0;
        this.maxHeight = value1;
        return this;
    }

    public Container maxWidth(float f) {
        this.maxWidth = Fixed.valueOf(f);
        return this;
    }

    public Container maxWidth(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("maxWidth cannot be null.");
        }
        this.maxWidth = value0;
        return this;
    }

    public Container minHeight(float f) {
        this.minHeight = Fixed.valueOf(f);
        return this;
    }

    public Container minHeight(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("minHeight cannot be null.");
        }
        this.minHeight = value0;
        return this;
    }

    public Container minSize(float f) {
        this.minSize(Fixed.valueOf(f));
        return this;
    }

    public Container minSize(float f, float f1) {
        this.minSize(Fixed.valueOf(f), Fixed.valueOf(f1));
        return this;
    }

    public Container minSize(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("size cannot be null.");
        }
        this.minWidth = value0;
        this.minHeight = value0;
        return this;
    }

    public Container minSize(Value value0, Value value1) {
        if(value0 == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        if(value1 == null) {
            throw new IllegalArgumentException("height cannot be null.");
        }
        this.minWidth = value0;
        this.minHeight = value1;
        return this;
    }

    public Container minWidth(float f) {
        this.minWidth = Fixed.valueOf(f);
        return this;
    }

    public Container minWidth(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("minWidth cannot be null.");
        }
        this.minWidth = value0;
        return this;
    }

    public Container pad(float f) {
        Fixed value$Fixed0 = Fixed.valueOf(f);
        this.padTop = value$Fixed0;
        this.padLeft = value$Fixed0;
        this.padBottom = value$Fixed0;
        this.padRight = value$Fixed0;
        return this;
    }

    public Container pad(float f, float f1, float f2, float f3) {
        this.padTop = Fixed.valueOf(f);
        this.padLeft = Fixed.valueOf(f1);
        this.padBottom = Fixed.valueOf(f2);
        this.padRight = Fixed.valueOf(f3);
        return this;
    }

    public Container pad(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("pad cannot be null.");
        }
        this.padTop = value0;
        this.padLeft = value0;
        this.padBottom = value0;
        this.padRight = value0;
        return this;
    }

    public Container pad(Value value0, Value value1, Value value2, Value value3) {
        if(value0 == null) {
            throw new IllegalArgumentException("top cannot be null.");
        }
        if(value1 == null) {
            throw new IllegalArgumentException("left cannot be null.");
        }
        if(value2 == null) {
            throw new IllegalArgumentException("bottom cannot be null.");
        }
        if(value3 == null) {
            throw new IllegalArgumentException("right cannot be null.");
        }
        this.padTop = value0;
        this.padLeft = value1;
        this.padBottom = value2;
        this.padRight = value3;
        return this;
    }

    public Container padBottom(float f) {
        this.padBottom = Fixed.valueOf(f);
        return this;
    }

    public Container padBottom(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("padBottom cannot be null.");
        }
        this.padBottom = value0;
        return this;
    }

    public Container padLeft(float f) {
        this.padLeft = Fixed.valueOf(f);
        return this;
    }

    public Container padLeft(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("padLeft cannot be null.");
        }
        this.padLeft = value0;
        return this;
    }

    public Container padRight(float f) {
        this.padRight = Fixed.valueOf(f);
        return this;
    }

    public Container padRight(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("padRight cannot be null.");
        }
        this.padRight = value0;
        return this;
    }

    public Container padTop(float f) {
        this.padTop = Fixed.valueOf(f);
        return this;
    }

    public Container padTop(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("padTop cannot be null.");
        }
        this.padTop = value0;
        return this;
    }

    public Container prefHeight(float f) {
        this.prefHeight = Fixed.valueOf(f);
        return this;
    }

    public Container prefHeight(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("prefHeight cannot be null.");
        }
        this.prefHeight = value0;
        return this;
    }

    public Container prefSize(float f) {
        this.prefSize(Fixed.valueOf(f));
        return this;
    }

    public Container prefSize(float f, float f1) {
        this.prefSize(Fixed.valueOf(f), Fixed.valueOf(f1));
        return this;
    }

    public Container prefSize(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("size cannot be null.");
        }
        this.prefWidth = value0;
        this.prefHeight = value0;
        return this;
    }

    public Container prefSize(Value value0, Value value1) {
        if(value0 == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        if(value1 == null) {
            throw new IllegalArgumentException("height cannot be null.");
        }
        this.prefWidth = value0;
        this.prefHeight = value1;
        return this;
    }

    public Container prefWidth(float f) {
        this.prefWidth = Fixed.valueOf(f);
        return this;
    }

    public Container prefWidth(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("prefWidth cannot be null.");
        }
        this.prefWidth = value0;
        return this;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public boolean removeActor(Actor actor0) {
        if(actor0 == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        if(actor0 != this.actor) {
            return false;
        }
        this.setActor(null);
        return true;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public boolean removeActor(Actor actor0, boolean z) {
        if(actor0 == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        if(actor0 != this.actor) {
            return false;
        }
        this.actor = null;
        return super.removeActor(actor0, z);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public Actor removeActorAt(int v, boolean z) {
        Actor actor0 = super.removeActorAt(v, z);
        if(actor0 == this.actor) {
            this.actor = null;
        }
        return actor0;
    }

    public Container right() {
        this.align &= -9;
        return this;
    }

    public void setActor(@Null Actor actor0) {
        if(actor0 == this) {
            throw new IllegalArgumentException("actor cannot be the Container.");
        }
        Actor actor1 = this.actor;
        if(actor0 == actor1) {
            return;
        }
        if(actor1 != null) {
            super.removeActor(actor1);
        }
        this.actor = actor0;
        if(actor0 != null) {
            super.addActor(actor0);
        }
    }

    public void setBackground(@Null Drawable drawable0) {
        this.setBackground(drawable0, true);
    }

    public void setBackground(@Null Drawable drawable0, boolean z) {
        if(this.background == drawable0) {
            return;
        }
        this.background = drawable0;
        if(z) {
            if(drawable0 == null) {
                this.pad(Value.zero);
            }
            else {
                this.pad(drawable0.getTopHeight(), drawable0.getLeftWidth(), drawable0.getBottomHeight(), drawable0.getRightWidth());
            }
            this.invalidate();
        }
    }

    public void setClip(boolean z) {
        this.clip = z;
        this.setTransform(z);
        this.invalidate();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public void setCullingArea(Rectangle rectangle0) {
        super.setCullingArea(rectangle0);
        if(this.fillX == 1.0f && this.fillY == 1.0f) {
            Actor actor0 = this.actor;
            if(actor0 instanceof Cullable) {
                ((Cullable)actor0).setCullingArea(rectangle0);
            }
        }
    }

    public void setRound(boolean z) {
        this.round = z;
    }

    public Container size(float f) {
        this.size(Fixed.valueOf(f));
        return this;
    }

    public Container size(float f, float f1) {
        this.size(Fixed.valueOf(f), Fixed.valueOf(f1));
        return this;
    }

    public Container size(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("size cannot be null.");
        }
        this.minWidth = value0;
        this.minHeight = value0;
        this.prefWidth = value0;
        this.prefHeight = value0;
        this.maxWidth = value0;
        this.maxHeight = value0;
        return this;
    }

    public Container size(Value value0, Value value1) {
        if(value0 == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        if(value1 == null) {
            throw new IllegalArgumentException("height cannot be null.");
        }
        this.minWidth = value0;
        this.minHeight = value1;
        this.prefWidth = value0;
        this.prefHeight = value1;
        this.maxWidth = value0;
        this.maxHeight = value1;
        return this;
    }

    public Container top() {
        this.align &= -5;
        return this;
    }

    public Container width(float f) {
        this.width(Fixed.valueOf(f));
        return this;
    }

    public Container width(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        this.minWidth = value0;
        this.prefWidth = value0;
        this.maxWidth = value0;
        return this;
    }
}

