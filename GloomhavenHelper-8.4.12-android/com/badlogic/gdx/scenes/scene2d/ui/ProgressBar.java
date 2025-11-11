package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pools;

public class ProgressBar extends Widget implements Disableable {
    public static class ProgressBarStyle {
        @Null
        public Drawable background;
        @Null
        public Drawable disabledBackground;
        @Null
        public Drawable disabledKnob;
        @Null
        public Drawable disabledKnobAfter;
        @Null
        public Drawable disabledKnobBefore;
        @Null
        public Drawable knob;
        @Null
        public Drawable knobAfter;
        @Null
        public Drawable knobBefore;

        public ProgressBarStyle() {
        }

        public ProgressBarStyle(ProgressBarStyle progressBar$ProgressBarStyle0) {
            this.background = progressBar$ProgressBarStyle0.background;
            this.disabledBackground = progressBar$ProgressBarStyle0.disabledBackground;
            this.knob = progressBar$ProgressBarStyle0.knob;
            this.disabledKnob = progressBar$ProgressBarStyle0.disabledKnob;
            this.knobBefore = progressBar$ProgressBarStyle0.knobBefore;
            this.disabledKnobBefore = progressBar$ProgressBarStyle0.disabledKnobBefore;
            this.knobAfter = progressBar$ProgressBarStyle0.knobAfter;
            this.disabledKnobAfter = progressBar$ProgressBarStyle0.disabledKnobAfter;
        }

        public ProgressBarStyle(@Null Drawable drawable0, @Null Drawable drawable1) {
            this.background = drawable0;
            this.knob = drawable1;
        }
    }

    private float animateDuration;
    private float animateFromValue;
    private Interpolation animateInterpolation;
    private float animateTime;
    boolean disabled;
    float max;
    float min;
    float position;
    private boolean programmaticChangeEvents;
    private boolean round;
    float stepSize;
    private ProgressBarStyle style;
    private float value;
    final boolean vertical;
    private Interpolation visualInterpolation;

    public ProgressBar(float f, float f1, float f2, boolean z, ProgressBarStyle progressBar$ProgressBarStyle0) {
        this.animateInterpolation = Interpolation.linear;
        this.visualInterpolation = Interpolation.linear;
        this.round = true;
        this.programmaticChangeEvents = true;
        if(f > f1) {
            throw new IllegalArgumentException("max must be > min. min,max: " + f + ", " + f1);
        }
        if(f2 <= 0.0f) {
            throw new IllegalArgumentException("stepSize must be > 0: " + f2);
        }
        this.setStyle(progressBar$ProgressBarStyle0);
        this.min = f;
        this.max = f1;
        this.stepSize = f2;
        this.vertical = z;
        this.value = f;
        this.setSize(this.getPrefWidth(), this.getPrefHeight());
    }

    public ProgressBar(float f, float f1, float f2, boolean z, Skin skin0) {
        this(f, f1, f2, z, ((ProgressBarStyle)skin0.get("default-" + (z ? "vertical" : "horizontal"), ProgressBarStyle.class)));
    }

    public ProgressBar(float f, float f1, float f2, boolean z, Skin skin0, String s) {
        this(f, f1, f2, z, ((ProgressBarStyle)skin0.get(s, ProgressBarStyle.class)));
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    public void act(float f) {
        super.act(f);
        float f1 = this.animateTime;
        if(f1 > 0.0f) {
            this.animateTime = f1 - f;
            Stage stage0 = this.getStage();
            if(stage0 != null && stage0.getActionsRequestRendering()) {
                Gdx.graphics.requestRendering();
            }
        }
    }

    protected float clamp(float f) {
        return MathUtils.clamp(f, this.min, this.max);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public void draw(Batch batch0, float f) {
        float f31;
        float f30;
        float f29;
        float f28;
        float f23;
        float f22;
        float f20;
        float f19;
        float f18;
        float f17;
        float f16;
        float f11;
        float f10;
        float f8;
        Drawable drawable0 = this.style.knob;
        Drawable drawable1 = this.getKnobDrawable();
        Drawable drawable2 = this.getBackgroundDrawable();
        Drawable drawable3 = this.getKnobBeforeDrawable();
        Drawable drawable4 = this.getKnobAfterDrawable();
        Color color0 = this.getColor();
        float f1 = this.getX();
        float f2 = this.getY();
        float f3 = this.getWidth();
        float f4 = this.getHeight();
        float f5 = 0.0f;
        float f6 = drawable0 == null ? 0.0f : drawable0.getMinHeight();
        float f7 = drawable0 == null ? 0.0f : drawable0.getMinWidth();
        this.getVisualPercent();
        batch0.setColor(color0.r, color0.g, color0.b, color0.a * f);
        if(this.vertical) {
            if(drawable2 == null) {
                f10 = f4;
                f8 = 0.0f;
                f11 = 0.0f;
            }
            else {
                if(this.round) {
                    drawable2.draw(batch0, ((float)Math.round((f3 - drawable2.getMinWidth()) * 0.5f + f1)), f2, ((float)Math.round(drawable2.getMinWidth())), f4);
                }
                else {
                    drawable2.draw(batch0, f1 + (f3 - drawable2.getMinWidth()) * 0.5f, f2, drawable2.getMinWidth(), f4);
                }
                f8 = drawable2.getTopHeight();
                float f9 = drawable2.getBottomHeight();
                f10 = f4 - (f8 + f9);
                f11 = f9;
            }
            if(drawable0 == null) {
                if(drawable3 != null) {
                    f5 = drawable3.getMinHeight() * 0.5f;
                }
                this.position = Math.min(f10 - f5, this.position);
            }
            else {
                f5 = f6 * 0.5f;
                this.position = Math.min(f10 - f6, this.position) + f11;
            }
            this.position = Math.max(f11, this.position);
            if(drawable3 != null) {
                if(this.round) {
                    drawable3.draw(batch0, ((float)Math.round((f3 - drawable3.getMinWidth()) * 0.5f + f1)), ((float)Math.round(f8 + f2)), ((float)Math.round(drawable3.getMinWidth())), ((float)Math.round(this.position + f5)));
                }
                else {
                    drawable3.draw(batch0, f1 + (f3 - drawable3.getMinWidth()) * 0.5f, f2 + f8, drawable3.getMinWidth(), this.position + f5);
                }
            }
            if(drawable4 != null) {
                if(this.round) {
                    drawable4.draw(batch0, ((float)Math.round((f3 - drawable4.getMinWidth()) * 0.5f + f1)), ((float)Math.round(this.position + f2 + f5)), ((float)Math.round(drawable4.getMinWidth())), ((float)Math.round(f4 - this.position - f5 - f11)));
                }
                else {
                    drawable4.draw(batch0, f1 + (f3 - drawable4.getMinWidth()) * 0.5f, this.position + f2 + f5, drawable4.getMinWidth(), f4 - this.position - f5 - f11);
                }
            }
            if(drawable1 != null) {
                float f12 = drawable1.getMinWidth();
                float f13 = drawable1.getMinHeight();
                float f14 = f1 + (f3 - f12) * 0.5f;
                float f15 = f2 + ((f6 - f13) * 0.5f + this.position);
                if(this.round) {
                    f16 = (float)Math.round(f14);
                    f17 = (float)Math.round(f15);
                    f18 = (float)Math.round(f12);
                    f19 = (float)Math.round(f13);
                }
                else {
                    f18 = f12;
                    f19 = f13;
                    f16 = f14;
                    f17 = f15;
                }
                drawable1.draw(batch0, f16, f17, f18, f19);
            }
        }
        else {
            if(drawable2 == null) {
                f22 = f3;
                f20 = 0.0f;
                f23 = 0.0f;
            }
            else {
                if(this.round) {
                    drawable2.draw(batch0, f1, ((float)Math.round((f4 - drawable2.getMinHeight()) * 0.5f + f2)), f3, ((float)Math.round(drawable2.getMinHeight())));
                }
                else {
                    drawable2.draw(batch0, f1, f2 + (f4 - drawable2.getMinHeight()) * 0.5f, f3, drawable2.getMinHeight());
                }
                f20 = drawable2.getLeftWidth();
                float f21 = drawable2.getRightWidth();
                f22 = f3 - (f20 + f21);
                f23 = f21;
            }
            if(drawable0 == null) {
                if(drawable3 != null) {
                    f5 = drawable3.getMinWidth() * 0.5f;
                }
                this.position = Math.min(f22 - f5, this.position);
            }
            else {
                f5 = f7 * 0.5f;
                this.position = Math.min(f22 - f7, this.position) + f20;
            }
            this.position = Math.max(f20, this.position);
            if(drawable3 != null) {
                if(this.round) {
                    drawable3.draw(batch0, ((float)Math.round(f20 + f1)), ((float)Math.round((f4 - drawable3.getMinHeight()) * 0.5f + f2)), ((float)Math.round(this.position + f5)), ((float)Math.round(drawable3.getMinHeight())));
                }
                else {
                    drawable3.draw(batch0, f1 + f20, f2 + (f4 - drawable3.getMinHeight()) * 0.5f, this.position + f5, drawable3.getMinHeight());
                }
            }
            if(drawable4 != null) {
                if(this.round) {
                    drawable4.draw(batch0, ((float)Math.round(this.position + f1 + f5)), ((float)Math.round((f4 - drawable4.getMinHeight()) * 0.5f + f2)), ((float)Math.round(f3 - this.position - f5 - f23)), ((float)Math.round(drawable4.getMinHeight())));
                }
                else {
                    drawable4.draw(batch0, this.position + f1 + f5, f2 + (f4 - drawable4.getMinHeight()) * 0.5f, f3 - this.position - f5 - f23, drawable4.getMinHeight());
                }
            }
            if(drawable1 != null) {
                float f24 = drawable1.getMinWidth();
                float f25 = drawable1.getMinHeight();
                float f26 = f1 + ((f7 - f24) * 0.5f + this.position);
                float f27 = f2 + (f4 - f25) * 0.5f;
                if(this.round) {
                    f28 = (float)Math.round(f26);
                    f29 = (float)Math.round(f27);
                    f30 = (float)Math.round(f24);
                    f31 = (float)Math.round(f25);
                }
                else {
                    f30 = f24;
                    f31 = f25;
                    f28 = f26;
                    f29 = f27;
                }
                drawable1.draw(batch0, f28, f29, f30, f31);
            }
        }
    }

    @Null
    protected Drawable getBackgroundDrawable() {
        return !this.disabled || this.style.disabledBackground == null ? this.style.background : this.style.disabledBackground;
    }

    protected Drawable getKnobAfterDrawable() {
        return !this.disabled || this.style.disabledKnobAfter == null ? this.style.knobAfter : this.style.disabledKnobAfter;
    }

    protected Drawable getKnobBeforeDrawable() {
        return !this.disabled || this.style.disabledKnobBefore == null ? this.style.knobBefore : this.style.disabledKnobBefore;
    }

    @Null
    protected Drawable getKnobDrawable() {
        return !this.disabled || this.style.disabledKnob == null ? this.style.knob : this.style.disabledKnob;
    }

    protected float getKnobPosition() {
        return this.position;
    }

    public float getMaxValue() {
        return this.max;
    }

    public float getMinValue() {
        return this.min;
    }

    public float getPercent() {
        return this.min == this.max ? 0.0f : (this.value - this.min) / (this.max - this.min);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public float getPrefHeight() {
        if(this.vertical) {
            return 140.0f;
        }
        Drawable drawable0 = this.style.knob;
        Drawable drawable1 = this.getBackgroundDrawable();
        float f = 0.0f;
        float f1 = drawable0 == null ? 0.0f : drawable0.getMinHeight();
        if(drawable1 != null) {
            f = drawable1.getMinHeight();
        }
        return Math.max(f1, f);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public float getPrefWidth() {
        if(this.vertical) {
            Drawable drawable0 = this.style.knob;
            Drawable drawable1 = this.getBackgroundDrawable();
            float f = 0.0f;
            float f1 = drawable0 == null ? 0.0f : drawable0.getMinWidth();
            if(drawable1 != null) {
                f = drawable1.getMinWidth();
            }
            return Math.max(f1, f);
        }
        return 140.0f;
    }

    public float getStepSize() {
        return this.stepSize;
    }

    public ProgressBarStyle getStyle() {
        return this.style;
    }

    public float getValue() {
        return this.value;
    }

    public float getVisualPercent() {
        return this.min == this.max ? 0.0f : this.visualInterpolation.apply((this.getVisualValue() - this.min) / (this.max - this.min));
    }

    public float getVisualValue() {
        return this.animateTime > 0.0f ? this.animateInterpolation.apply(this.animateFromValue, this.value, 1.0f - this.animateTime / this.animateDuration) : this.value;
    }

    public boolean isAnimating() {
        return this.animateTime > 0.0f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public boolean isDisabled() {
        return this.disabled;
    }

    public boolean isVertical() {
        return this.vertical;
    }

    protected float round(float f) {
        return ((float)Math.round(f / this.stepSize)) * this.stepSize;
    }

    public void setAnimateDuration(float f) {
        this.animateDuration = f;
    }

    public void setAnimateInterpolation(Interpolation interpolation0) {
        if(interpolation0 == null) {
            throw new IllegalArgumentException("animateInterpolation cannot be null.");
        }
        this.animateInterpolation = interpolation0;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public void setDisabled(boolean z) {
        this.disabled = z;
    }

    public void setProgrammaticChangeEvents(boolean z) {
        this.programmaticChangeEvents = z;
    }

    public void setRange(float f, float f1) {
        if(f > f1) {
            throw new IllegalArgumentException("min must be <= max: " + f + " <= " + f1);
        }
        this.min = f;
        this.max = f1;
        float f2 = this.value;
        if(f2 < f) {
            this.setValue(f);
            return;
        }
        if(f2 > f1) {
            this.setValue(f1);
        }
    }

    public void setRound(boolean z) {
        this.round = z;
    }

    public void setStepSize(float f) {
        if(f <= 0.0f) {
            throw new IllegalArgumentException("steps must be > 0: " + f);
        }
        this.stepSize = f;
    }

    public void setStyle(ProgressBarStyle progressBar$ProgressBarStyle0) {
        if(progressBar$ProgressBarStyle0 == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = progressBar$ProgressBarStyle0;
        this.invalidateHierarchy();
    }

    public boolean setValue(float f) {
        float f1 = this.clamp(this.round(f));
        float f2 = this.value;
        if(f1 == f2) {
            return false;
        }
        float f3 = this.getVisualValue();
        this.value = f1;
        if(this.programmaticChangeEvents) {
            ChangeEvent changeListener$ChangeEvent0 = (ChangeEvent)Pools.obtain(ChangeEvent.class);
            boolean z = this.fire(changeListener$ChangeEvent0);
            Pools.free(changeListener$ChangeEvent0);
            if(z) {
                this.value = f2;
                return false;
            }
        }
        float f4 = this.animateDuration;
        if(f4 > 0.0f) {
            this.animateFromValue = f3;
            this.animateTime = f4;
        }
        return true;
    }

    public void setVisualInterpolation(Interpolation interpolation0) {
        this.visualInterpolation = interpolation0;
    }

    public void updateVisualValue() {
        this.animateTime = 0.0f;
    }
}

