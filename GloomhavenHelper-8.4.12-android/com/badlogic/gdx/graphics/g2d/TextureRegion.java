package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Texture;

public class TextureRegion {
    int regionHeight;
    int regionWidth;
    Texture texture;
    float u;
    float u2;
    float v;
    float v2;

    public TextureRegion() {
    }

    public TextureRegion(Texture texture0) {
        if(texture0 == null) {
            throw new IllegalArgumentException("texture cannot be null.");
        }
        this.texture = texture0;
        this.setRegion(0, 0, texture0.getWidth(), texture0.getHeight());
    }

    public TextureRegion(Texture texture0, float f, float f1, float f2, float f3) {
        this.texture = texture0;
        this.setRegion(f, f1, f2, f3);
    }

    public TextureRegion(Texture texture0, int v, int v1) {
        this.texture = texture0;
        this.setRegion(0, 0, v, v1);
    }

    public TextureRegion(Texture texture0, int v, int v1, int v2, int v3) {
        this.texture = texture0;
        this.setRegion(v, v1, v2, v3);
    }

    public TextureRegion(TextureRegion textureRegion0) {
        this.setRegion(textureRegion0);
    }

    public TextureRegion(TextureRegion textureRegion0, int v, int v1, int v2, int v3) {
        this.setRegion(textureRegion0, v, v1, v2, v3);
    }

    public void flip(boolean z, boolean z1) {
        if(z) {
            float f = this.u;
            this.u = this.u2;
            this.u2 = f;
        }
        if(z1) {
            float f1 = this.v;
            this.v = this.v2;
            this.v2 = f1;
        }
    }

    public int getRegionHeight() {
        return this.regionHeight;
    }

    public int getRegionWidth() {
        return this.regionWidth;
    }

    public int getRegionX() {
        return Math.round(this.u * ((float)this.texture.getWidth()));
    }

    public int getRegionY() {
        return Math.round(this.v * ((float)this.texture.getHeight()));
    }

    public Texture getTexture() {
        return this.texture;
    }

    public float getU() {
        return this.u;
    }

    public float getU2() {
        return this.u2;
    }

    public float getV() {
        return this.v;
    }

    public float getV2() {
        return this.v2;
    }

    public boolean isFlipX() {
        return this.u > this.u2;
    }

    public boolean isFlipY() {
        return this.v > this.v2;
    }

    public void scroll(float f, float f1) {
        if(f != 0.0f) {
            float f2 = (this.u2 - this.u) * ((float)this.texture.getWidth());
            this.u = (this.u + f) % 1.0f;
            this.u2 = this.u + f2 / ((float)this.texture.getWidth());
        }
        if(f1 != 0.0f) {
            float f3 = (this.v2 - this.v) * ((float)this.texture.getHeight());
            this.v = (this.v + f1) % 1.0f;
            this.v2 = this.v + f3 / ((float)this.texture.getHeight());
        }
    }

    public void setRegion(float f, float f1, float f2, float f3) {
        int v = this.texture.getWidth();
        int v1 = this.texture.getHeight();
        this.regionWidth = Math.round(Math.abs(f2 - f) * ((float)v));
        this.regionHeight = Math.round(Math.abs(f3 - f1) * ((float)v1));
        if(this.regionWidth == 1 && this.regionHeight == 1) {
            f += 0.25f / ((float)v);
            f2 -= 0.25f / ((float)v);
            f1 += 0.25f / ((float)v1);
            f3 -= 0.25f / ((float)v1);
        }
        this.u = f;
        this.v = f1;
        this.u2 = f2;
        this.v2 = f3;
    }

    public void setRegion(int v, int v1, int v2, int v3) {
        float f = 1.0f / ((float)this.texture.getWidth());
        float f1 = 1.0f / ((float)this.texture.getHeight());
        this.setRegion(((float)v) * f, ((float)v1) * f1, ((float)(v + v2)) * f, ((float)(v1 + v3)) * f1);
        this.regionWidth = Math.abs(v2);
        this.regionHeight = Math.abs(v3);
    }

    public void setRegion(Texture texture0) {
        this.texture = texture0;
        this.setRegion(0, 0, texture0.getWidth(), texture0.getHeight());
    }

    public void setRegion(TextureRegion textureRegion0) {
        this.texture = textureRegion0.texture;
        this.setRegion(textureRegion0.u, textureRegion0.v, textureRegion0.u2, textureRegion0.v2);
    }

    public void setRegion(TextureRegion textureRegion0, int v, int v1, int v2, int v3) {
        this.texture = textureRegion0.texture;
        this.setRegion(textureRegion0.getRegionX() + v, textureRegion0.getRegionY() + v1, v2, v3);
    }

    public void setRegionHeight(int v) {
        if(this.isFlipY()) {
            this.setV(this.v2 + ((float)v) / ((float)this.texture.getHeight()));
            return;
        }
        this.setV2(this.v + ((float)v) / ((float)this.texture.getHeight()));
    }

    public void setRegionWidth(int v) {
        if(this.isFlipX()) {
            this.setU(this.u2 + ((float)v) / ((float)this.texture.getWidth()));
            return;
        }
        this.setU2(this.u + ((float)v) / ((float)this.texture.getWidth()));
    }

    public void setRegionX(int v) {
        this.setU(((float)v) / ((float)this.texture.getWidth()));
    }

    public void setRegionY(int v) {
        this.setV(((float)v) / ((float)this.texture.getHeight()));
    }

    public void setTexture(Texture texture0) {
        this.texture = texture0;
    }

    public void setU(float f) {
        this.u = f;
        this.regionWidth = Math.round(Math.abs(this.u2 - f) * ((float)this.texture.getWidth()));
    }

    public void setU2(float f) {
        this.u2 = f;
        this.regionWidth = Math.round(Math.abs(f - this.u) * ((float)this.texture.getWidth()));
    }

    public void setV(float f) {
        this.v = f;
        this.regionHeight = Math.round(Math.abs(this.v2 - f) * ((float)this.texture.getHeight()));
    }

    public void setV2(float f) {
        this.v2 = f;
        this.regionHeight = Math.round(Math.abs(f - this.v) * ((float)this.texture.getHeight()));
    }

    public static TextureRegion[][] split(Texture texture0, int v, int v1) {
        return new TextureRegion(texture0).split(v, v1);
    }

    public TextureRegion[][] split(int v, int v1) {
        int v2 = this.getRegionX();
        int v3 = this.getRegionY();
        int v4 = this.regionWidth;
        int v5 = this.regionHeight / v1;
        int v6 = v4 / v;
        Object object0 = new TextureRegion[v5][v6];
        int v7 = v3;
        int v8 = 0;
        while(v8 < v5) {
            int v9 = v2;
            int v10 = 0;
            while(v10 < v6) {
                TextureRegion[] arr_textureRegion = ((TextureRegion[][])object0)[v8];
                arr_textureRegion[v10] = new TextureRegion(this.texture, v9, v7, v, v1);
                ++v10;
                v9 += v;
            }
            ++v8;
            v7 += v1;
        }
        return (TextureRegion[][])object0;
    }
}

