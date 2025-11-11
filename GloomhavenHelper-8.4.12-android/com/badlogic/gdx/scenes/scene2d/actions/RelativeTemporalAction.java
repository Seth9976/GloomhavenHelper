package com.badlogic.gdx.scenes.scene2d.actions;

public abstract class RelativeTemporalAction extends TemporalAction {
    private float lastPercent;

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        this.lastPercent = 0.0f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void update(float f) {
        this.updateRelative(f - this.lastPercent);
        this.lastPercent = f;
    }

    protected abstract void updateRelative(float arg1);
}

