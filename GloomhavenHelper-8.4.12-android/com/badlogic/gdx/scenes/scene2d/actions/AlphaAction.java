package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Null;

public class AlphaAction extends TemporalAction {
    @Null
    private Color color;
    private float end;
    private float start;

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        if(this.color == null) {
            this.color = this.target.getColor();
        }
        this.start = this.color.a;
    }

    public float getAlpha() {
        return this.end;
    }

    @Null
    public Color getColor() {
        return this.color;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    public void reset() {
        super.reset();
        this.color = null;
    }

    public void setAlpha(float f) {
        this.end = f;
    }

    public void setColor(@Null Color color0) {
        this.color = color0;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void update(float f) {
        switch(f) {
            case 0: {
                this.color.a = this.start;
                return;
            }
            case 0x3F800000: {
                this.color.a = this.end;
                return;
            }
            default: {
                this.color.a = this.start + (this.end - this.start) * f;
            }
        }
    }
}

