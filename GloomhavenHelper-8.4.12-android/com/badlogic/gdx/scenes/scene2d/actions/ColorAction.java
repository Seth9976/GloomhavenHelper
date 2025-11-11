package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Null;

public class ColorAction extends TemporalAction {
    @Null
    private Color color;
    private final Color end;
    private float startA;
    private float startB;
    private float startG;
    private float startR;

    public ColorAction() {
        this.end = new Color();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        if(this.color == null) {
            this.color = this.target.getColor();
        }
        this.startR = this.color.r;
        this.startG = this.color.g;
        this.startB = this.color.b;
        this.startA = this.color.a;
    }

    @Null
    public Color getColor() {
        return this.color;
    }

    public Color getEndColor() {
        return this.end;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    public void reset() {
        super.reset();
        this.color = null;
    }

    public void setColor(@Null Color color0) {
        this.color = color0;
    }

    public void setEndColor(Color color0) {
        this.end.set(color0);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void update(float f) {
        switch(f) {
            case 0: {
                this.color.set(this.startR, this.startG, this.startB, this.startA);
                return;
            }
            case 0x3F800000: {
                this.color.set(this.end);
                return;
            }
            default: {
                this.color.set(this.startR + (this.end.r - this.startR) * f, this.startG + (this.end.g - this.startG) * f, this.startB + (this.end.b - this.startB) * f, this.startA + (this.end.a - this.startA) * f);
            }
        }
    }
}

