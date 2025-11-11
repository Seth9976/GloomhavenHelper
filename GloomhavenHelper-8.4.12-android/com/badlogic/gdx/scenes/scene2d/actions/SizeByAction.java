package com.badlogic.gdx.scenes.scene2d.actions;

public class SizeByAction extends RelativeTemporalAction {
    private float amountHeight;
    private float amountWidth;

    public float getAmountHeight() {
        return this.amountHeight;
    }

    public float getAmountWidth() {
        return this.amountWidth;
    }

    public void setAmount(float f, float f1) {
        this.amountWidth = f;
        this.amountHeight = f1;
    }

    public void setAmountHeight(float f) {
        this.amountHeight = f;
    }

    public void setAmountWidth(float f) {
        this.amountWidth = f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.RelativeTemporalAction
    protected void updateRelative(float f) {
        this.target.sizeBy(this.amountWidth * f, this.amountHeight * f);
    }
}

