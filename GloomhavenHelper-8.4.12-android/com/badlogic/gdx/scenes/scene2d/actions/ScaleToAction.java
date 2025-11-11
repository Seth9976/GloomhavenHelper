package com.badlogic.gdx.scenes.scene2d.actions;

public class ScaleToAction extends TemporalAction {
    private float endX;
    private float endY;
    private float startX;
    private float startY;

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        this.startX = this.target.getScaleX();
        this.startY = this.target.getScaleY();
    }

    public float getX() {
        return this.endX;
    }

    public float getY() {
        return this.endY;
    }

    public void setScale(float f) {
        this.endX = f;
        this.endY = f;
    }

    public void setScale(float f, float f1) {
        this.endX = f;
        this.endY = f1;
    }

    public void setX(float f) {
        this.endX = f;
    }

    public void setY(float f) {
        this.endY = f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void update(float f) {
        float f2;
        float f1;
        switch(f) {
            case 0: {
                f2 = this.startX;
                f1 = this.startY;
                break;
            }
            case 0x3F800000: {
                f2 = this.endX;
                f1 = this.endY;
                break;
            }
            default: {
                f1 = this.startY + (this.endY - this.startY) * f;
                f2 = this.startX + (this.endX - this.startX) * f;
            }
        }
        this.target.setScale(f2, f1);
    }
}

