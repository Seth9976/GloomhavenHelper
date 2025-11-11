package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pools;

public class Slider extends ProgressBar {
    public static class SliderStyle extends ProgressBarStyle {
        @Null
        public Drawable backgroundDown;
        @Null
        public Drawable backgroundOver;
        @Null
        public Drawable knobAfterDown;
        @Null
        public Drawable knobAfterOver;
        @Null
        public Drawable knobBeforeDown;
        @Null
        public Drawable knobBeforeOver;
        @Null
        public Drawable knobDown;
        @Null
        public Drawable knobOver;

        public SliderStyle() {
        }

        public SliderStyle(SliderStyle slider$SliderStyle0) {
            super(slider$SliderStyle0);
            this.backgroundOver = slider$SliderStyle0.backgroundOver;
            this.backgroundDown = slider$SliderStyle0.backgroundDown;
            this.knobOver = slider$SliderStyle0.knobOver;
            this.knobDown = slider$SliderStyle0.knobDown;
            this.knobBeforeOver = slider$SliderStyle0.knobBeforeOver;
            this.knobBeforeDown = slider$SliderStyle0.knobBeforeDown;
            this.knobAfterOver = slider$SliderStyle0.knobAfterOver;
            this.knobAfterDown = slider$SliderStyle0.knobAfterDown;
        }

        public SliderStyle(@Null Drawable drawable0, @Null Drawable drawable1) {
            super(drawable0, drawable1);
        }
    }

    int button;
    int draggingPointer;
    boolean mouseOver;
    private float[] snapValues;
    private float threshold;
    private Interpolation visualInterpolationInverse;

    public Slider(float f, float f1, float f2, boolean z, Skin skin0) {
        this(f, f1, f2, z, ((SliderStyle)skin0.get("default-" + (z ? "vertical" : "horizontal"), SliderStyle.class)));
    }

    public Slider(float f, float f1, float f2, boolean z, Skin skin0, String s) {
        this(f, f1, f2, z, ((SliderStyle)skin0.get(s, SliderStyle.class)));
    }

    public Slider(float f, float f1, float f2, boolean z, SliderStyle slider$SliderStyle0) {
        super(f, f1, f2, z, slider$SliderStyle0);
        this.button = -1;
        this.draggingPointer = -1;
        this.visualInterpolationInverse = Interpolation.linear;
        this.addListener(new InputListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public void enter(InputEvent inputEvent0, float f, float f1, int v, @Null Actor actor0) {
                if(v == -1) {
                    Slider.this.mouseOver = true;
                }
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public void exit(InputEvent inputEvent0, float f, float f1, int v, @Null Actor actor0) {
                if(v == -1) {
                    Slider.this.mouseOver = false;
                }
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                if(Slider.this.disabled) {
                    return false;
                }
                if(Slider.this.button != -1 && Slider.this.button != v1) {
                    return false;
                }
                if(Slider.this.draggingPointer != -1) {
                    return false;
                }
                Slider.this.draggingPointer = v;
                Slider.this.calculatePositionAndValue(f, f1);
                return true;
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchDragged(InputEvent inputEvent0, float f, float f1, int v) {
                Slider.this.calculatePositionAndValue(f, f1);
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchUp(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                if(v != Slider.this.draggingPointer) {
                    return;
                }
                Slider.this.draggingPointer = -1;
                if(inputEvent0.isTouchFocusCancel() || !Slider.this.calculatePositionAndValue(f, f1)) {
                    ChangeEvent changeListener$ChangeEvent0 = (ChangeEvent)Pools.obtain(ChangeEvent.class);
                    Slider.this.fire(changeListener$ChangeEvent0);
                    Pools.free(changeListener$ChangeEvent0);
                }
            }
        });
    }

    boolean calculatePositionAndValue(float f, float f1) {
        float f10;
        Drawable drawable0 = this.getStyle().knob;
        Drawable drawable1 = this.getBackgroundDrawable();
        float f2 = this.position;
        float f3 = this.getMinValue();
        float f4 = this.getMaxValue();
        if(this.vertical) {
            float f5 = this.getHeight();
            float f6 = drawable1.getTopHeight();
            float f7 = drawable1.getBottomHeight();
            float f8 = drawable0 == null ? 0.0f : drawable0.getMinHeight();
            this.position = f1 - drawable1.getBottomHeight() - 0.5f * f8;
            float f9 = f5 - f6 - f7 - f8;
            f10 = f3 + (f4 - f3) * this.visualInterpolationInverse.apply(this.position / f9);
            drawable1.getBottomHeight();
            this.position = Math.min(f9, this.position);
        }
        else {
            float f11 = this.getWidth();
            float f12 = drawable1.getLeftWidth();
            float f13 = drawable1.getRightWidth();
            float f14 = drawable0 == null ? 0.0f : drawable0.getMinWidth();
            this.position = f - drawable1.getLeftWidth() - 0.5f * f14;
            float f15 = f11 - f12 - f13 - f14;
            f10 = f3 + (f4 - f3) * this.visualInterpolationInverse.apply(this.position / f15);
            drawable1.getLeftWidth();
            this.position = Math.min(f15, this.position);
        }
        float f16 = Gdx.input.isKeyPressed(59) || Gdx.input.isKeyPressed(60) ? f10 : this.snap(f10);
        boolean z = this.setValue(f16);
        if(f16 == f10) {
            this.position = f2;
        }
        return z;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.ProgressBar
    @Null
    protected Drawable getBackgroundDrawable() {
        SliderStyle slider$SliderStyle0 = (SliderStyle)super.getStyle();
        if(this.disabled && slider$SliderStyle0.disabledBackground != null) {
            return slider$SliderStyle0.disabledBackground;
        }
        if(this.isDragging() && slider$SliderStyle0.backgroundDown != null) {
            return slider$SliderStyle0.backgroundDown;
        }
        return !this.mouseOver || slider$SliderStyle0.backgroundOver == null ? slider$SliderStyle0.background : slider$SliderStyle0.backgroundOver;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.ProgressBar
    protected Drawable getKnobAfterDrawable() {
        SliderStyle slider$SliderStyle0 = (SliderStyle)super.getStyle();
        if(this.disabled && slider$SliderStyle0.disabledKnobAfter != null) {
            return slider$SliderStyle0.disabledKnobAfter;
        }
        if(this.isDragging() && slider$SliderStyle0.knobAfterDown != null) {
            return slider$SliderStyle0.knobAfterDown;
        }
        return !this.mouseOver || slider$SliderStyle0.knobAfterOver == null ? slider$SliderStyle0.knobAfter : slider$SliderStyle0.knobAfterOver;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.ProgressBar
    protected Drawable getKnobBeforeDrawable() {
        SliderStyle slider$SliderStyle0 = (SliderStyle)super.getStyle();
        if(this.disabled && slider$SliderStyle0.disabledKnobBefore != null) {
            return slider$SliderStyle0.disabledKnobBefore;
        }
        if(this.isDragging() && slider$SliderStyle0.knobBeforeDown != null) {
            return slider$SliderStyle0.knobBeforeDown;
        }
        return !this.mouseOver || slider$SliderStyle0.knobBeforeOver == null ? slider$SliderStyle0.knobBefore : slider$SliderStyle0.knobBeforeOver;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.ProgressBar
    @Null
    protected Drawable getKnobDrawable() {
        SliderStyle slider$SliderStyle0 = (SliderStyle)super.getStyle();
        if(this.disabled && slider$SliderStyle0.disabledKnob != null) {
            return slider$SliderStyle0.disabledKnob;
        }
        if(this.isDragging() && slider$SliderStyle0.knobDown != null) {
            return slider$SliderStyle0.knobDown;
        }
        return !this.mouseOver || slider$SliderStyle0.knobOver == null ? slider$SliderStyle0.knob : slider$SliderStyle0.knobOver;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.ProgressBar
    public ProgressBarStyle getStyle() {
        return this.getStyle();
    }

    public SliderStyle getStyle() {
        return (SliderStyle)super.getStyle();
    }

    public boolean isDragging() {
        return this.draggingPointer != -1;
    }

    public boolean isOver() {
        return this.mouseOver;
    }

    public void setButton(int v) {
        this.button = v;
    }

    public void setSnapToValues(@Null float[] arr_f, float f) {
        this.snapValues = arr_f;
        this.threshold = f;
    }

    public void setVisualInterpolationInverse(Interpolation interpolation0) {
        this.visualInterpolationInverse = interpolation0;
    }

    public void setVisualPercent(float f) {
        this.setValue(this.min + (this.max - this.min) * this.visualInterpolationInverse.apply(f));
    }

    protected float snap(float f) {
        if(this.snapValues != null && this.snapValues.length != 0) {
            float f1 = -1.0f;
            float f2 = 0.0f;
            for(int v = 0; true; ++v) {
                float[] arr_f = this.snapValues;
                if(v >= arr_f.length) {
                    break;
                }
                float f3 = arr_f[v];
                float f4 = Math.abs(f - f3);
                if(f4 <= this.threshold && (f1 == -1.0f || f4 < f1)) {
                    f2 = f3;
                    f1 = f4;
                }
            }
            return f1 == -1.0f ? f : f2;
        }
        return f;
    }
}

