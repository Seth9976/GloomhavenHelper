package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TiledDrawable extends TextureRegionDrawable {
    private final Color color;
    private float scale;

    public TiledDrawable() {
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.scale = 1.0f;
    }

    public TiledDrawable(TextureRegion textureRegion0) {
        super(textureRegion0);
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.scale = 1.0f;
    }

    public TiledDrawable(TextureRegionDrawable textureRegionDrawable0) {
        super(textureRegionDrawable0);
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.scale = 1.0f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
    public void draw(Batch batch0, float f, float f1, float f2, float f3) {
        float f16;
        float f4 = batch0.getPackedColor();
        batch0.setColor(batch0.getColor().mul(this.color));
        TextureRegion textureRegion0 = this.getRegion();
        float f5 = ((float)textureRegion0.getRegionWidth()) * this.scale;
        float f6 = ((float)textureRegion0.getRegionHeight()) * this.scale;
        int v = (int)(f2 / f5);
        int v1 = (int)(f3 / f6);
        float f7 = f2 - ((float)v) * f5;
        float f8 = f3 - ((float)v1) * f6;
        float f9 = f;
        float f10 = f1;
        int v2 = 0;
        while(v2 < v) {
            float f11 = f1;
            for(int v3 = 0; v3 < v1; ++v3) {
                batch0.draw(textureRegion0, f9, f11, f5, f6);
                f11 += f6;
            }
            f9 += f5;
            ++v2;
            f10 = f11;
        }
        Texture texture0 = textureRegion0.getTexture();
        float f12 = textureRegion0.getU();
        float f13 = textureRegion0.getV2();
        if(f7 > 0.0f) {
            float f14 = f12 + f7 / (((float)texture0.getWidth()) * this.scale);
            float f15 = textureRegion0.getV();
            f16 = f1;
            for(int v4 = 0; v4 < v1; ++v4) {
                batch0.draw(texture0, f9, f16, f7, f6, f12, f13, f14, f15);
                f16 += f6;
            }
            if(f8 > 0.0f) {
                batch0.draw(texture0, f9, f16, f7, f8, f12, f13, f14, f13 - f8 / (((float)texture0.getHeight()) * this.scale));
            }
        }
        else {
            f16 = f10;
        }
        if(f8 > 0.0f) {
            float f17 = textureRegion0.getU2();
            float f18 = f13 - f8 / (((float)texture0.getHeight()) * this.scale);
            float f19 = f;
            for(int v5 = 0; v5 < v; ++v5) {
                batch0.draw(texture0, f19, f16, f5, f8, f12, f13, f17, f18);
                f19 += f5;
            }
        }
        batch0.setPackedColor(f4);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
    public void draw(Batch batch0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        throw new UnsupportedOperationException();
    }

    public Color getColor() {
        return this.color;
    }

    public float getScale() {
        return this.scale;
    }

    public void setScale(float f) {
        this.scale = f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
    public Drawable tint(Color color0) {
        return this.tint(color0);
    }

    public TiledDrawable tint(Color color0) {
        TiledDrawable tiledDrawable0 = new TiledDrawable(this);
        tiledDrawable0.color.set(color0);
        tiledDrawable0.setLeftWidth(this.getLeftWidth());
        tiledDrawable0.setRightWidth(this.getRightWidth());
        tiledDrawable0.setTopHeight(this.getTopHeight());
        tiledDrawable0.setBottomHeight(this.getBottomHeight());
        return tiledDrawable0;
    }
}

