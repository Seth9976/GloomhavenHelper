package com.badlogic.gdx.scenes.scene2d.actions;

public class MoveToAction extends TemporalAction {
    private int alignment;
    private float endX;
    private float endY;
    private float startX;
    private float startY;

    public MoveToAction() {
        this.alignment = 12;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        this.startX = this.target.getX(this.alignment);
        this.startY = this.target.getY(this.alignment);
    }

    public int getAlignment() {
        return this.alignment;
    }

    public float getStartX() {
        return this.startX;
    }

    public float getStartY() {
        return this.startY;
    }

    public float getX() {
        return this.endX;
    }

    public float getY() {
        return this.endY;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    public void reset() {
        super.reset();
        this.alignment = 12;
    }

    public void setAlignment(int v) {
        this.alignment = v;
    }

    public void setPosition(float f, float f1) {
        this.endX = f;
        this.endY = f1;
    }

    public void setPosition(float f, float f1, int v) {
        this.endX = f;
        this.endY = f1;
        this.alignment = v;
    }

    public void setStartPosition(float f, float f1) {
        this.startX = f;
        this.startY = f1;
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
        this.target.setPosition(f2, f1, this.alignment);
    }
}

