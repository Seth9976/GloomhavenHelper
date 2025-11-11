package com.badlogic.gdx.scenes.scene2d.actions;

public class SizeToAction extends TemporalAction {
    private float endHeight;
    private float endWidth;
    private float startHeight;
    private float startWidth;

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        this.startWidth = this.target.getWidth();
        this.startHeight = this.target.getHeight();
    }

    public float getHeight() {
        return this.endHeight;
    }

    public float getWidth() {
        return this.endWidth;
    }

    public void setHeight(float f) {
        this.endHeight = f;
    }

    public void setSize(float f, float f1) {
        this.endWidth = f;
        this.endHeight = f1;
    }

    public void setWidth(float f) {
        this.endWidth = f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void update(float f) {
        float f2;
        float f1;
        switch(f) {
            case 0: {
                f2 = this.startWidth;
                f1 = this.startHeight;
                break;
            }
            case 0x3F800000: {
                f2 = this.endWidth;
                f1 = this.endHeight;
                break;
            }
            default: {
                f1 = this.startHeight + (this.endHeight - this.startHeight) * f;
                f2 = this.startWidth + (this.endWidth - this.startWidth) * f;
            }
        }
        this.target.setSize(f2, f1);
    }
}

