package com.badlogic.gdx;

public abstract class AbstractGraphics implements Graphics {
    @Override  // com.badlogic.gdx.Graphics
    public float getBackBufferScale() {
        return ((float)this.getBackBufferWidth()) / ((float)this.getWidth());
    }

    @Override  // com.badlogic.gdx.Graphics
    public float getDensity() {
        return this.getPpiX() / 160.0f;
    }

    @Override  // com.badlogic.gdx.Graphics
    public float getRawDeltaTime() {
        return this.getDeltaTime();
    }
}

