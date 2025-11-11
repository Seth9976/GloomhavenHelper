package com.badlogic.gdx.graphics.g3d.environment;

import com.badlogic.gdx.graphics.Color;

public abstract class BaseLight {
    public final Color color;

    public BaseLight() {
        this.color = new Color(0.0f, 0.0f, 0.0f, 1.0f);
    }

    public BaseLight setColor(float f, float f1, float f2, float f3) {
        this.color.set(f, f1, f2, f3);
        return this;
    }

    public BaseLight setColor(Color color0) {
        this.color.set(color0);
        return this;
    }
}

