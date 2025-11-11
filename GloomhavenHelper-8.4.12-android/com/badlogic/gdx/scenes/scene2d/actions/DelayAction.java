package com.badlogic.gdx.scenes.scene2d.actions;

public class DelayAction extends DelegateAction {
    private float duration;
    private float time;

    public DelayAction() {
    }

    public DelayAction(float f) {
        this.duration = f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.DelegateAction
    protected boolean delegate(float f) {
        float f1 = this.time;
        float f2 = this.duration;
        if(f1 < f2) {
            this.time = f1 + f;
            float f3 = this.time;
            if(f3 < f2) {
                return false;
            }
            f = f3 - f2;
        }
        return this.action == null ? true : this.action.act(f);
    }

    public void finish() {
        this.time = this.duration;
    }

    public float getDuration() {
        return this.duration;
    }

    public float getTime() {
        return this.time;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.DelegateAction
    public void restart() {
        super.restart();
        this.time = 0.0f;
    }

    public void setDuration(float f) {
        this.duration = f;
    }

    public void setTime(float f) {
        this.time = f;
    }
}

