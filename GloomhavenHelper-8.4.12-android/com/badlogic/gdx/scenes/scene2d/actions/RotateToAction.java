package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.math.MathUtils;

public class RotateToAction extends TemporalAction {
    private float end;
    private float start;
    private boolean useShortestDirection;

    public RotateToAction() {
        this.useShortestDirection = false;
    }

    public RotateToAction(boolean z) {
        this.useShortestDirection = z;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        this.start = this.target.getRotation();
    }

    public float getRotation() {
        return this.end;
    }

    public boolean isUseShortestDirection() {
        return this.useShortestDirection;
    }

    public void setRotation(float f) {
        this.end = f;
    }

    public void setUseShortestDirection(boolean z) {
        this.useShortestDirection = z;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void update(float f) {
        float f1;
        switch(f) {
            case 0: {
                f1 = this.start;
                break;
            }
            case 0x3F800000: {
                f1 = this.end;
                break;
            }
            default: {
                f1 = this.useShortestDirection ? MathUtils.lerpAngleDeg(this.start, this.end, f) : this.start + (this.end - this.start) * f;
            }
        }
        this.target.setRotation(f1);
    }
}

