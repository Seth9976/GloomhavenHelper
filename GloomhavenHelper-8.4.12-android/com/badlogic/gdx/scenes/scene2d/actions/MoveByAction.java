package com.badlogic.gdx.scenes.scene2d.actions;

public class MoveByAction extends RelativeTemporalAction {
    private float amountX;
    private float amountY;

    public float getAmountX() {
        return this.amountX;
    }

    public float getAmountY() {
        return this.amountY;
    }

    public void setAmount(float f, float f1) {
        this.amountX = f;
        this.amountY = f1;
    }

    public void setAmountX(float f) {
        this.amountX = f;
    }

    public void setAmountY(float f) {
        this.amountY = f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.RelativeTemporalAction
    protected void updateRelative(float f) {
        this.target.moveBy(this.amountX * f, this.amountY * f);
    }
}

