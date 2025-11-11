package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Null;

public class IntAction extends TemporalAction {
    private int end;
    private int start;
    private int value;

    public IntAction() {
        this.start = 0;
        this.end = 1;
    }

    public IntAction(int v, int v1) {
        this.start = v;
        this.end = v1;
    }

    public IntAction(int v, int v1, float f) {
        super(f);
        this.start = v;
        this.end = v1;
    }

    public IntAction(int v, int v1, float f, @Null Interpolation interpolation0) {
        super(f, interpolation0);
        this.start = v;
        this.end = v1;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        this.value = this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public int getStart() {
        return this.start;
    }

    public int getValue() {
        return this.value;
    }

    public void setEnd(int v) {
        this.end = v;
    }

    public void setStart(int v) {
        this.start = v;
    }

    public void setValue(int v) {
        this.value = v;
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
                this.value = (int)(((float)this.start) + ((float)(this.end - this.start)) * f);
            }
        }
    }
}

