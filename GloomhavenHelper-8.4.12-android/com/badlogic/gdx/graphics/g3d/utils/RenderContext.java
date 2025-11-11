package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.Gdx;

public class RenderContext {
    private int blendDFactor;
    private int blendSFactor;
    private boolean blending;
    private int cullFace;
    private int depthFunc;
    private boolean depthMask;
    private float depthRangeFar;
    private float depthRangeNear;
    public final TextureBinder textureBinder;

    public RenderContext(TextureBinder textureBinder0) {
        this.textureBinder = textureBinder0;
    }

    public void begin() {
        Gdx.gl.glDisable(0xB71);
        this.depthFunc = 0;
        Gdx.gl.glDepthMask(true);
        this.depthMask = true;
        Gdx.gl.glDisable(3042);
        this.blending = false;
        Gdx.gl.glDisable(0xB44);
        this.blendDFactor = 0;
        this.blendSFactor = 0;
        this.cullFace = 0;
        this.textureBinder.begin();
    }

    public void end() {
        if(this.depthFunc != 0) {
            Gdx.gl.glDisable(0xB71);
        }
        if(!this.depthMask) {
            Gdx.gl.glDepthMask(true);
        }
        if(this.blending) {
            Gdx.gl.glDisable(3042);
        }
        if(this.cullFace > 0) {
            Gdx.gl.glDisable(0xB44);
        }
        this.textureBinder.end();
    }

    public void setBlending(boolean z, int v, int v1) {
        if(z != this.blending) {
            this.blending = z;
            if(z) {
                Gdx.gl.glEnable(3042);
            }
            else {
                Gdx.gl.glDisable(3042);
            }
        }
        if(z && (this.blendSFactor != v || this.blendDFactor != v1)) {
            Gdx.gl.glBlendFunc(v, v1);
            this.blendSFactor = v;
            this.blendDFactor = v1;
        }
    }

    public void setCullFace(int v) {
        if(v != this.cullFace) {
            this.cullFace = v;
            if(v != 0x404 && v != 0x405 && v != 0x408) {
                Gdx.gl.glDisable(0xB44);
                return;
            }
            Gdx.gl.glEnable(0xB44);
            Gdx.gl.glCullFace(v);
        }
    }

    public void setDepthMask(boolean z) {
        if(this.depthMask != z) {
            this.depthMask = z;
            Gdx.gl.glDepthMask(z);
        }
    }

    public void setDepthTest(int v) {
        this.setDepthTest(v, 0.0f, 1.0f);
    }

    public void setDepthTest(int v, float f, float f1) {
        boolean z = true;
        boolean z1 = this.depthFunc != 0;
        if(v == 0) {
            z = false;
        }
        if(this.depthFunc != v) {
            this.depthFunc = v;
            if(z) {
                Gdx.gl.glEnable(0xB71);
                Gdx.gl.glDepthFunc(v);
            }
            else {
                Gdx.gl.glDisable(0xB71);
            }
        }
        if(z) {
            if(!z1 || this.depthFunc != v) {
                this.depthFunc = v;
                Gdx.gl.glDepthFunc(v);
            }
            if(!z1 || this.depthRangeNear != f || this.depthRangeFar != f1) {
                this.depthRangeNear = f;
                this.depthRangeFar = f1;
                Gdx.gl.glDepthRangef(f, f1);
            }
        }
    }
}

