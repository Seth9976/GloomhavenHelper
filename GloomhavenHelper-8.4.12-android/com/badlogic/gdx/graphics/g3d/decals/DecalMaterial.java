package com.badlogic.gdx.graphics.g3d.decals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DecalMaterial {
    public static final int NO_BLEND = -1;
    protected int dstBlendFactor;
    protected int srcBlendFactor;
    protected TextureRegion textureRegion;

    @Override
    public boolean equals(Object object0) {
        return object0 == null ? false : this.dstBlendFactor == ((DecalMaterial)object0).dstBlendFactor && this.srcBlendFactor == ((DecalMaterial)object0).srcBlendFactor && this.textureRegion.getTexture() == ((DecalMaterial)object0).textureRegion.getTexture();
    }

    public int getDstBlendFactor() {
        return this.dstBlendFactor;
    }

    public int getSrcBlendFactor() {
        return this.srcBlendFactor;
    }

    @Override
    public int hashCode() {
        return this.textureRegion.getTexture() == null ? this.srcBlendFactor * 0x1F + this.dstBlendFactor : (this.textureRegion.getTexture().hashCode() * 0x1F + this.srcBlendFactor) * 0x1F + this.dstBlendFactor;
    }

    public boolean isOpaque() {
        return this.srcBlendFactor == -1;
    }

    public void set() {
        this.textureRegion.getTexture().bind(0);
        if(!this.isOpaque()) {
            Gdx.gl.glBlendFunc(this.srcBlendFactor, this.dstBlendFactor);
        }
    }
}

