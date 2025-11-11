package com.badlogic.gdx.scenes.scene2d.actions;

public class TimeScaleAction extends DelegateAction {
    private float scale;

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.DelegateAction
    protected boolean delegate(float f) {
        return this.action == null ? true : this.action.act(f * this.scale);
    }

    public float getScale() {
        return this.scale;
    }

    public void setScale(float f) {
        this.scale = f;
    }
}

