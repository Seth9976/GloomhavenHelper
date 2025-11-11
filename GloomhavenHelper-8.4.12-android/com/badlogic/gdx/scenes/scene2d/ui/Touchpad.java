package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pools;

public class Touchpad extends Widget {
    public static class TouchpadStyle {
        @Null
        public Drawable background;
        @Null
        public Drawable knob;

        public TouchpadStyle() {
        }

        public TouchpadStyle(TouchpadStyle touchpad$TouchpadStyle0) {
            this.background = touchpad$TouchpadStyle0.background;
            this.knob = touchpad$TouchpadStyle0.knob;
        }

        public TouchpadStyle(@Null Drawable drawable0, @Null Drawable drawable1) {
            this.background = drawable0;
            this.knob = drawable1;
        }
    }

    private final Circle deadzoneBounds;
    private float deadzoneRadius;
    private final Circle knobBounds;
    private final Vector2 knobPercent;
    private final Vector2 knobPosition;
    boolean resetOnTouchUp;
    private TouchpadStyle style;
    private final Circle touchBounds;
    boolean touched;

    public Touchpad(float f, Skin skin0) {
        this(f, ((TouchpadStyle)skin0.get(TouchpadStyle.class)));
    }

    public Touchpad(float f, Skin skin0, String s) {
        this(f, ((TouchpadStyle)skin0.get(s, TouchpadStyle.class)));
    }

    public Touchpad(float f, TouchpadStyle touchpad$TouchpadStyle0) {
        this.resetOnTouchUp = true;
        this.knobBounds = new Circle(0.0f, 0.0f, 0.0f);
        this.touchBounds = new Circle(0.0f, 0.0f, 0.0f);
        this.deadzoneBounds = new Circle(0.0f, 0.0f, 0.0f);
        this.knobPosition = new Vector2();
        this.knobPercent = new Vector2();
        if(f < 0.0f) {
            throw new IllegalArgumentException("deadzoneRadius must be > 0");
        }
        this.deadzoneRadius = f;
        float f1 = this.getWidth();
        float f2 = this.getHeight();
        this.knobPosition.set(f1 / 2.0f, f2 / 2.0f);
        this.setStyle(touchpad$TouchpadStyle0);
        this.setSize(this.getPrefWidth(), this.getPrefHeight());
        this.addListener(new InputListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                if(Touchpad.this.touched) {
                    return false;
                }
                Touchpad.this.touched = true;
                Touchpad.this.calculatePositionAndValue(f, f1, false);
                return true;
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchDragged(InputEvent inputEvent0, float f, float f1, int v) {
                Touchpad.this.calculatePositionAndValue(f, f1, false);
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchUp(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                Touchpad.this.touched = false;
                Touchpad.this.calculatePositionAndValue(f, f1, Touchpad.this.resetOnTouchUp);
            }
        });
    }

    void calculatePositionAndValue(float f, float f1, boolean z) {
        float f2 = this.knobPosition.x;
        float f3 = this.knobPosition.y;
        float f4 = this.knobPercent.x;
        float f5 = this.knobPercent.y;
        float f6 = this.knobBounds.x;
        float f7 = this.knobBounds.y;
        this.knobPosition.set(f6, f7);
        this.knobPercent.set(0.0f, 0.0f);
        if(!z && !this.deadzoneBounds.contains(f, f1)) {
            this.knobPercent.set((f - f6) / this.knobBounds.radius, (f1 - f7) / this.knobBounds.radius);
            float f8 = this.knobPercent.len();
            if(f8 > 1.0f) {
                this.knobPercent.scl(1.0f / f8);
            }
            if(this.knobBounds.contains(f, f1)) {
                this.knobPosition.set(f, f1);
            }
            else {
                this.knobPosition.set(this.knobPercent).nor().scl(this.knobBounds.radius).add(this.knobBounds.x, this.knobBounds.y);
            }
        }
        if(f4 != this.knobPercent.x || f5 != this.knobPercent.y) {
            ChangeEvent changeListener$ChangeEvent0 = (ChangeEvent)Pools.obtain(ChangeEvent.class);
            if(this.fire(changeListener$ChangeEvent0)) {
                this.knobPercent.set(f4, f5);
                this.knobPosition.set(f2, f3);
            }
            Pools.free(changeListener$ChangeEvent0);
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public void draw(Batch batch0, float f) {
        this.validate();
        Color color0 = this.getColor();
        batch0.setColor(color0.r, color0.g, color0.b, color0.a * f);
        float f1 = this.getX();
        float f2 = this.getY();
        float f3 = this.getWidth();
        float f4 = this.getHeight();
        Drawable drawable0 = this.style.background;
        if(drawable0 != null) {
            drawable0.draw(batch0, f1, f2, f3, f4);
        }
        Drawable drawable1 = this.style.knob;
        if(drawable1 != null) {
            drawable1.draw(batch0, f1 + (this.knobPosition.x - drawable1.getMinWidth() / 2.0f), f2 + (this.knobPosition.y - drawable1.getMinHeight() / 2.0f), drawable1.getMinWidth(), drawable1.getMinHeight());
        }
    }

    public float getKnobPercentX() {
        return this.knobPercent.x;
    }

    public float getKnobPercentY() {
        return this.knobPercent.y;
    }

    public float getKnobX() {
        return this.knobPosition.x;
    }

    public float getKnobY() {
        return this.knobPosition.y;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public float getPrefHeight() {
        return this.style.background == null ? 0.0f : this.style.background.getMinHeight();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public float getPrefWidth() {
        return this.style.background == null ? 0.0f : this.style.background.getMinWidth();
    }

    public boolean getResetOnTouchUp() {
        return this.resetOnTouchUp;
    }

    public TouchpadStyle getStyle() {
        return this.style;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    public Actor hit(float f, float f1, boolean z) {
        if(z && this.getTouchable() != Touchable.enabled) {
            return null;
        }
        if(!this.isVisible()) {
            return null;
        }
        return this.touchBounds.contains(f, f1) ? this : null;
    }

    public boolean isTouched() {
        return this.touched;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public void layout() {
        float f = this.getWidth();
        float f1 = this.getHeight();
        float f2 = Math.min(f / 2.0f, f1 / 2.0f);
        this.touchBounds.set(f / 2.0f, f1 / 2.0f, f2);
        if(this.style.knob != null) {
            f2 -= Math.max(this.style.knob.getMinWidth(), this.style.knob.getMinHeight()) / 2.0f;
        }
        this.knobBounds.set(f / 2.0f, f1 / 2.0f, f2);
        this.deadzoneBounds.set(f / 2.0f, f1 / 2.0f, this.deadzoneRadius);
        this.knobPosition.set(f / 2.0f, f1 / 2.0f);
        this.knobPercent.set(0.0f, 0.0f);
    }

    public void setDeadzone(float f) {
        if(f < 0.0f) {
            throw new IllegalArgumentException("deadzoneRadius must be > 0");
        }
        this.deadzoneRadius = f;
        this.invalidate();
    }

    public void setResetOnTouchUp(boolean z) {
        this.resetOnTouchUp = z;
    }

    public void setStyle(TouchpadStyle touchpad$TouchpadStyle0) {
        if(touchpad$TouchpadStyle0 == null) {
            throw new IllegalArgumentException("style cannot be null");
        }
        this.style = touchpad$TouchpadStyle0;
        this.invalidateHierarchy();
    }
}

