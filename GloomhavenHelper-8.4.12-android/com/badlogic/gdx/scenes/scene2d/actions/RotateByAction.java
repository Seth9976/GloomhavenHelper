package com.badlogic.gdx.scenes.scene2d.actions;

public class RotateByAction extends RelativeTemporalAction {
    private float amount;

    public float getAmount() {
        return this.amount;
    }

    public void setAmount(float f) {
        this.amount = f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.RelativeTemporalAction
    protected void updateRelative(float f) {
        this.target.rotateBy(this.amount * f);
    }
}

