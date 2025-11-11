package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Null;

public class FloatAction extends TemporalAction {
    private float end;
    private float start;
    private float value;

    public FloatAction() {
        this.start = 0.0f;
        this.end = 1.0f;
    }

    public FloatAction(float f, float f1) {
        this.start = f;
        this.end = f1;
    }

    public FloatAction(float f, float f1, float f2) {
        super(f2);
        this.start = f;
        this.end = f1;
    }

    public FloatAction(float f, float f1, float f2, @Null Interpolation interpolation0) {
        super(f2, interpolation0);
        this.start = f;
        this.end = f1;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        this.value = this.start;
    }

    public float getEnd() {
        return this.end;
    }

    public float getStart() {
        return this.start;
    }

    public float getValue() {
        return this.value;
    }

    public void setEnd(float f) {
        this.end = f;
    }

    public void setStart(float f) {
        this.start = f;
    }

    public void setValue(float f) {
        this.value = f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void update(float f) {
        switch(f) {
            case 0: {
                this.value = this.start;
                return;
            }
            case 0x3F800000: {
                this.value = this.end;
                return;
            }
            default: {
                this.value = this.start + (this.end - this.start) * f;
            }
        }
    }
}

