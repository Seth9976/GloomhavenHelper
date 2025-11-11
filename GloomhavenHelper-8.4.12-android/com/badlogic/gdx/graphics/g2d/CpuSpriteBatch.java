package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class CpuSpriteBatch extends SpriteBatch {
    private final Affine2 adjustAffine;
    private boolean adjustNeeded;
    private boolean haveIdentityRealMatrix;
    private final Affine2 tmpAffine;
    private final Matrix4 virtualMatrix;

    public CpuSpriteBatch() {
        this(1000);
    }

    public CpuSpriteBatch(int v) {
        this(v, null);
    }

    public CpuSpriteBatch(int v, ShaderProgram shaderProgram0) {
        super(v, shaderProgram0);
        this.virtualMatrix = new Matrix4();
        this.adjustAffine = new Affine2();
        this.haveIdentityRealMatrix = true;
        this.tmpAffine = new Affine2();
    }

    private static boolean checkEqual(Matrix4 matrix40, Affine2 affine20) {
        float[] arr_f = matrix40.getValues();
        return arr_f[0] == affine20.m00 && arr_f[1] == affine20.m10 && arr_f[4] == affine20.m01 && arr_f[5] == affine20.m11 && arr_f[12] == affine20.m02 && arr_f[13] == affine20.m12;
    }

    private static boolean checkEqual(Matrix4 matrix40, Matrix4 matrix41) {
        return matrix40 == matrix41 ? true : matrix40.val[0] == matrix41.val[0] && matrix40.val[1] == matrix41.val[1] && matrix40.val[4] == matrix41.val[4] && matrix40.val[5] == matrix41.val[5] && matrix40.val[12] == matrix41.val[12] && matrix40.val[13] == matrix41.val[13];
    }

    private static boolean checkIdt(Matrix4 matrix40) {
        float[] arr_f = matrix40.getValues();
        return arr_f[0] == 1.0f && arr_f[1] == 0.0f && arr_f[4] == 0.0f && arr_f[5] == 1.0f && arr_f[12] == 0.0f && arr_f[13] == 0.0f;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.SpriteBatch
    public void draw(Texture texture0, float f, float f1) {
        if(!this.adjustNeeded) {
            super.draw(texture0, f, f1);
            return;
        }
        this.drawAdjusted(texture0, f, f1, 0.0f, 0.0f, ((float)texture0.getWidth()), ((float)texture0.getHeight()), 1.0f, 1.0f, 0.0f, 0, 1, 1, 0, false, false);
    }

    @Override  // com.badlogic.gdx.graphics.g2d.SpriteBatch
    public void draw(Texture texture0, float f, float f1, float f2, float f3) {
        if(!this.adjustNeeded) {
            super.draw(texture0, f, f1, f2, f3);
            return;
        }
        this.drawAdjusted(texture0, f, f1, 0.0f, 0.0f, f2, f3, 1.0f, 1.0f, 0.0f, 0, 1, 1, 0, false, false);
    }

    @Override  // com.badlogic.gdx.graphics.g2d.SpriteBatch
    public void draw(Texture texture0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7) {
        if(!this.adjustNeeded) {
            super.draw(texture0, f, f1, f2, f3, f4, f5, f6, f7);
            return;
        }
        this.drawAdjustedUV(texture0, f, f1, 0.0f, 0.0f, f2, f3, 1.0f, 1.0f, 0.0f, f4, f5, f6, f7, false, false);
    }

    @Override  // com.badlogic.gdx.graphics.g2d.SpriteBatch
    public void draw(Texture texture0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int v, int v1, int v2, int v3, boolean z, boolean z1) {
        if(!this.adjustNeeded) {
            super.draw(texture0, f, f1, f2, f3, f4, f5, f6, f7, f8, v, v1, v2, v3, z, z1);
            return;
        }
        this.drawAdjusted(texture0, f, f1, f2, f3, f4, f5, f6, f7, f8, v, v1, v2, v3, z, z1);
    }

    @Override  // com.badlogic.gdx.graphics.g2d.SpriteBatch
    public void draw(Texture texture0, float f, float f1, float f2, float f3, int v, int v1, int v2, int v3, boolean z, boolean z1) {
        if(!this.adjustNeeded) {
            super.draw(texture0, f, f1, f2, f3, v, v1, v2, v3, z, z1);
            return;
        }
        this.drawAdjusted(texture0, f, f1, 0.0f, 0.0f, f2, f3, 1.0f, 1.0f, 0.0f, v, v1, v2, v3, z, z1);
    }

    @Override  // com.badlogic.gdx.graphics.g2d.SpriteBatch
    public void draw(Texture texture0, float f, float f1, int v, int v1, int v2, int v3) {
        if(!this.adjustNeeded) {
            super.draw(texture0, f, f1, v, v1, v2, v3);
            return;
        }
        this.drawAdjusted(texture0, f, f1, 0.0f, 0.0f, ((float)v2), ((float)v3), 1.0f, 1.0f, 0.0f, v, v1, v2, v3, false, false);
    }

    @Override  // com.badlogic.gdx.graphics.g2d.SpriteBatch
    public void draw(Texture texture0, float[] arr_f, int v, int v1) {
        if(v1 % 20 != 0) {
            throw new GdxRuntimeException("invalid vertex count");
        }
        if(!this.adjustNeeded) {
            super.draw(texture0, arr_f, v, v1);
            return;
        }
        this.drawAdjusted(texture0, arr_f, v, v1);
    }

    @Override  // com.badlogic.gdx.graphics.g2d.SpriteBatch
    public void draw(TextureRegion textureRegion0, float f, float f1) {
        if(!this.adjustNeeded) {
            super.draw(textureRegion0, f, f1);
            return;
        }
        this.drawAdjusted(textureRegion0, f, f1, 0.0f, 0.0f, ((float)textureRegion0.getRegionWidth()), ((float)textureRegion0.getRegionHeight()), 1.0f, 1.0f, 0.0f);
    }

    @Override  // com.badlogic.gdx.graphics.g2d.SpriteBatch
    public void draw(TextureRegion textureRegion0, float f, float f1, float f2, float f3) {
        if(!this.adjustNeeded) {
            super.draw(textureRegion0, f, f1, f2, f3);
            return;
        }
        this.drawAdjusted(textureRegion0, f, f1, 0.0f, 0.0f, f2, f3, 1.0f, 1.0f, 0.0f);
    }

    @Override  // com.badlogic.gdx.graphics.g2d.SpriteBatch
    public void draw(TextureRegion textureRegion0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if(!this.adjustNeeded) {
            super.draw(textureRegion0, f, f1, f2, f3, f4, f5, f6, f7, f8);
            return;
        }
        this.drawAdjusted(textureRegion0, f, f1, f2, f3, f4, f5, f6, f7, f8);
    }

    @Override  // com.badlogic.gdx.graphics.g2d.SpriteBatch
    public void draw(TextureRegion textureRegion0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, boolean z) {
        if(!this.adjustNeeded) {
            super.draw(textureRegion0, f, f1, f2, f3, f4, f5, f6, f7, f8, z);
            return;
        }
        this.drawAdjusted(textureRegion0, f, f1, f2, f3, f4, f5, f6, f7, f8, z);
    }

    @Override  // com.badlogic.gdx.graphics.g2d.SpriteBatch
    public void draw(TextureRegion textureRegion0, float f, float f1, Affine2 affine20) {
        if(!this.adjustNeeded) {
            super.draw(textureRegion0, f, f1, affine20);
            return;
        }
        this.drawAdjusted(textureRegion0, f, f1, affine20);
    }

    private void drawAdjusted(Texture texture0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int v, int v1, int v2, int v3, boolean z, boolean z1) {
        float f9 = 1.0f / ((float)texture0.getWidth());
        float f10 = 1.0f / ((float)texture0.getHeight());
        this.drawAdjustedUV(texture0, f, f1, f2, f3, f4, f5, f6, f7, f8, ((float)v) * f9, ((float)(v1 + v3)) * f10, f9 * ((float)(v + v2)), f10 * ((float)v1), z, z1);
    }

    private void drawAdjusted(Texture texture0, float[] arr_f, int v, int v1) {
        if(!this.drawing) {
            throw new IllegalStateException("CpuSpriteBatch.begin must be called before draw.");
        }
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        Affine2 affine20 = this.adjustAffine;
        int v2 = Math.min(this.vertices.length - this.idx, v1);
        do {
            v1 -= v2;
            while(v2 > 0) {
                float f = arr_f[v];
                float f1 = arr_f[v + 1];
                this.vertices[this.idx] = affine20.m00 * f + affine20.m01 * f1 + affine20.m02;
                this.vertices[this.idx + 1] = affine20.m10 * f + affine20.m11 * f1 + affine20.m12;
                this.vertices[this.idx + 2] = arr_f[v + 2];
                this.vertices[this.idx + 3] = arr_f[v + 3];
                this.vertices[this.idx + 4] = arr_f[v + 4];
                this.idx += 5;
                v += 5;
                v2 -= 5;
            }
            if(v1 > 0) {
                super.flush();
                v2 = Math.min(this.vertices.length, v1);
            }
        }
        while(v1 > 0);
    }

    private void drawAdjusted(TextureRegion textureRegion0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        this.drawAdjustedUV(textureRegion0.texture, f, f1, f2, f3, f4, f5, f6, f7, f8, textureRegion0.u, textureRegion0.v2, textureRegion0.u2, textureRegion0.v, false, false);
    }

    private void drawAdjusted(TextureRegion textureRegion0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, boolean z) {
        float f42;
        float f41;
        float f40;
        float f39;
        float f38;
        float f37;
        float f36;
        float f35;
        float f28;
        float f27;
        float f26;
        float f24;
        float f21;
        if(!this.drawing) {
            throw new IllegalStateException("CpuSpriteBatch.begin must be called before draw.");
        }
        if(textureRegion0.texture != this.lastTexture) {
            this.switchTexture(textureRegion0.texture);
        }
        else if(this.idx == this.vertices.length) {
            super.flush();
        }
        float f9 = f + f2;
        float f10 = f1 + f3;
        float f11 = -f2;
        float f12 = -f3;
        float f13 = f4 - f2;
        float f14 = f5 - f3;
        if(f6 != 1.0f || f7 != 1.0f) {
            f11 *= f6;
            f12 *= f7;
            f13 *= f6;
            f14 *= f7;
        }
        if(f8 == 0.0f) {
            f24 = f13;
            f26 = f24;
            f27 = f14;
            f21 = f11;
            f28 = f12;
        }
        else {
            float f15 = MathUtils.cosDeg(f8);
            float f16 = MathUtils.sinDeg(f8);
            float f17 = f15 * f11;
            float f18 = f17 - f16 * f12;
            float f19 = f11 * f16;
            f12 = f12 * f15 + f19;
            float f20 = f16 * f14;
            f21 = f17 - f20;
            float f22 = f14 * f15;
            float f23 = f19 + f22;
            f24 = f15 * f13 - f20;
            float f25 = f22 + f16 * f13;
            f26 = f24 - f21 + f18;
            f27 = f25;
            f14 = f23;
            f11 = f18;
            f28 = f25 - (f23 - f12);
        }
        float f29 = f11 + f9;
        float f30 = f12 + f10;
        float f31 = f21 + f9;
        float f32 = f14 + f10;
        float f33 = f24 + f9;
        float f34 = f27 + f10;
        if(z) {
            f35 = textureRegion0.v2;
            f36 = textureRegion0.u;
            f37 = textureRegion0.v2;
            f38 = textureRegion0.u;
            f39 = textureRegion0.v;
            f40 = textureRegion0.v;
            f41 = textureRegion0.u2;
            f42 = textureRegion0.u2;
        }
        else {
            f35 = textureRegion0.v;
            f36 = textureRegion0.u2;
            f37 = textureRegion0.v;
            f38 = textureRegion0.u2;
            f39 = textureRegion0.v2;
            f40 = textureRegion0.v2;
            f41 = textureRegion0.u;
            f42 = textureRegion0.u;
        }
        float f43 = f28 + f10;
        float f44 = f26 + f9;
        this.vertices[this.idx] = this.adjustAffine.m00 * f29 + this.adjustAffine.m01 * f30 + this.adjustAffine.m02;
        this.vertices[this.idx + 1] = this.adjustAffine.m10 * f29 + this.adjustAffine.m11 * f30 + this.adjustAffine.m12;
        this.vertices[this.idx + 2] = this.colorPacked;
        this.vertices[this.idx + 3] = f42;
        this.vertices[this.idx + 4] = f35;
        this.vertices[this.idx + 5] = this.adjustAffine.m00 * f31 + this.adjustAffine.m01 * f32 + this.adjustAffine.m02;
        this.vertices[this.idx + 6] = this.adjustAffine.m10 * f31 + this.adjustAffine.m11 * f32 + this.adjustAffine.m12;
        this.vertices[this.idx + 7] = this.colorPacked;
        this.vertices[this.idx + 8] = f36;
        this.vertices[this.idx + 9] = f37;
        this.vertices[this.idx + 10] = this.adjustAffine.m00 * f33 + this.adjustAffine.m01 * f34 + this.adjustAffine.m02;
        this.vertices[this.idx + 11] = this.adjustAffine.m10 * f33 + this.adjustAffine.m11 * f34 + this.adjustAffine.m12;
        this.vertices[this.idx + 12] = this.colorPacked;
        this.vertices[this.idx + 13] = f38;
        this.vertices[this.idx + 14] = f39;
        this.vertices[this.idx + 15] = this.adjustAffine.m00 * f44 + this.adjustAffine.m01 * f43 + this.adjustAffine.m02;
        this.vertices[this.idx + 16] = this.adjustAffine.m10 * f44 + this.adjustAffine.m11 * f43 + this.adjustAffine.m12;
        this.vertices[this.idx + 17] = this.colorPacked;
        this.vertices[this.idx + 18] = f41;
        this.vertices[this.idx + 19] = f40;
        this.idx += 20;
    }

    private void drawAdjusted(TextureRegion textureRegion0, float f, float f1, Affine2 affine20) {
        if(!this.drawing) {
            throw new IllegalStateException("CpuSpriteBatch.begin must be called before draw.");
        }
        if(textureRegion0.texture != this.lastTexture) {
            this.switchTexture(textureRegion0.texture);
        }
        else if(this.idx == this.vertices.length) {
            super.flush();
        }
        float f2 = affine20.m02;
        float f3 = affine20.m12;
        float f4 = affine20.m01 * f1 + affine20.m02;
        float f5 = affine20.m11 * f1 + affine20.m12;
        float f6 = affine20.m00 * f + affine20.m01 * f1 + affine20.m02;
        float f7 = affine20.m10 * f + affine20.m11 * f1 + affine20.m12;
        float f8 = textureRegion0.u;
        float f9 = textureRegion0.v2;
        float f10 = textureRegion0.u2;
        float f11 = textureRegion0.v;
        float f12 = affine20.m10 * f + affine20.m12;
        float f13 = affine20.m00 * f + affine20.m02;
        this.vertices[this.idx] = this.adjustAffine.m00 * f2 + this.adjustAffine.m01 * f3 + this.adjustAffine.m02;
        this.vertices[this.idx + 1] = this.adjustAffine.m10 * f2 + this.adjustAffine.m11 * f3 + this.adjustAffine.m12;
        this.vertices[this.idx + 2] = this.colorPacked;
        this.vertices[this.idx + 3] = f8;
        this.vertices[this.idx + 4] = f9;
        this.vertices[this.idx + 5] = this.adjustAffine.m00 * f4 + this.adjustAffine.m01 * f5 + this.adjustAffine.m02;
        this.vertices[this.idx + 6] = this.adjustAffine.m10 * f4 + this.adjustAffine.m11 * f5 + this.adjustAffine.m12;
        this.vertices[this.idx + 7] = this.colorPacked;
        this.vertices[this.idx + 8] = f8;
        this.vertices[this.idx + 9] = f11;
        this.vertices[this.idx + 10] = this.adjustAffine.m00 * f6 + this.adjustAffine.m01 * f7 + this.adjustAffine.m02;
        this.vertices[this.idx + 11] = this.adjustAffine.m10 * f6 + this.adjustAffine.m11 * f7 + this.adjustAffine.m12;
        this.vertices[this.idx + 12] = this.colorPacked;
        this.vertices[this.idx + 13] = f10;
        this.vertices[this.idx + 14] = f11;
        this.vertices[this.idx + 15] = this.adjustAffine.m00 * f13 + this.adjustAffine.m01 * f12 + this.adjustAffine.m02;
        this.vertices[this.idx + 16] = this.adjustAffine.m10 * f13 + this.adjustAffine.m11 * f12 + this.adjustAffine.m12;
        this.vertices[this.idx + 17] = this.colorPacked;
        this.vertices[this.idx + 18] = f10;
        this.vertices[this.idx + 19] = f9;
        this.idx += 20;
    }

    private void drawAdjustedUV(Texture texture0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, boolean z, boolean z1) {
        float f42;
        float f41;
        float f40;
        float f39;
        float f32;
        float f31;
        float f30;
        float f25;
        if(!this.drawing) {
            throw new IllegalStateException("CpuSpriteBatch.begin must be called before draw.");
        }
        if(texture0 != this.lastTexture) {
            this.switchTexture(texture0);
        }
        else if(this.idx == this.vertices.length) {
            super.flush();
        }
        float f13 = f + f2;
        float f14 = f1 + f3;
        float f15 = -f2;
        float f16 = -f3;
        float f17 = f4 - f2;
        float f18 = f5 - f3;
        if(f6 != 1.0f || f7 != 1.0f) {
            f15 *= f6;
            f16 *= f7;
            f17 *= f6;
            f18 *= f7;
        }
        if(f8 == 0.0f) {
            f30 = f17;
            f31 = f18;
            f25 = f15;
            f32 = f16;
        }
        else {
            float f19 = MathUtils.cosDeg(f8);
            float f20 = MathUtils.sinDeg(f8);
            float f21 = f19 * f15;
            float f22 = f21 - f20 * f16;
            float f23 = f15 * f20;
            f16 = f16 * f19 + f23;
            float f24 = f20 * f18;
            f25 = f21 - f24;
            float f26 = f18 * f19;
            float f27 = f23 + f26;
            float f28 = f19 * f17 - f24;
            float f29 = f26 + f20 * f17;
            f30 = f28 - f25 + f22;
            f17 = f28;
            f31 = f29;
            f18 = f27;
            f15 = f22;
            f32 = f29 - (f27 - f16);
        }
        float f33 = f15 + f13;
        float f34 = f16 + f14;
        float f35 = f25 + f13;
        float f36 = f18 + f14;
        float f37 = f17 + f13;
        float f38 = f31 + f14;
        if(z) {
            f39 = f9;
            f40 = f11;
        }
        else {
            f40 = f9;
            f39 = f11;
        }
        if(z1) {
            f41 = f10;
            f42 = f12;
        }
        else {
            f42 = f10;
            f41 = f12;
        }
        float f43 = f32 + f14;
        float f44 = f30 + f13;
        this.vertices[this.idx] = this.adjustAffine.m00 * f33 + this.adjustAffine.m01 * f34 + this.adjustAffine.m02;
        this.vertices[this.idx + 1] = this.adjustAffine.m10 * f33 + this.adjustAffine.m11 * f34 + this.adjustAffine.m12;
        this.vertices[this.idx + 2] = this.colorPacked;
        this.vertices[this.idx + 3] = f40;
        this.vertices[this.idx + 4] = f42;
        this.vertices[this.idx + 5] = this.adjustAffine.m00 * f35 + this.adjustAffine.m01 * f36 + this.adjustAffine.m02;
        this.vertices[this.idx + 6] = this.adjustAffine.m10 * f35 + this.adjustAffine.m11 * f36 + this.adjustAffine.m12;
        this.vertices[this.idx + 7] = this.colorPacked;
        this.vertices[this.idx + 8] = f40;
        this.vertices[this.idx + 9] = f41;
        this.vertices[this.idx + 10] = this.adjustAffine.m00 * f37 + this.adjustAffine.m01 * f38 + this.adjustAffine.m02;
        this.vertices[this.idx + 11] = this.adjustAffine.m10 * f37 + this.adjustAffine.m11 * f38 + this.adjustAffine.m12;
        this.vertices[this.idx + 12] = this.colorPacked;
        this.vertices[this.idx + 13] = f39;
        this.vertices[this.idx + 14] = f41;
        this.vertices[this.idx + 15] = this.adjustAffine.m00 * f44 + this.adjustAffine.m01 * f43 + this.adjustAffine.m02;
        this.vertices[this.idx + 16] = this.adjustAffine.m10 * f44 + this.adjustAffine.m11 * f43 + this.adjustAffine.m12;
        this.vertices[this.idx + 17] = this.colorPacked;
        this.vertices[this.idx + 18] = f39;
        this.vertices[this.idx + 19] = f42;
        this.idx += 20;
    }

    public void flushAndSyncTransformMatrix() {
        this.flush();
        if(this.adjustNeeded) {
            this.haveIdentityRealMatrix = CpuSpriteBatch.checkIdt(this.virtualMatrix);
            if(!this.haveIdentityRealMatrix && this.virtualMatrix.det() == 0.0f) {
                throw new GdxRuntimeException("Transform matrix is singular, can\'t sync");
            }
            this.adjustNeeded = false;
            super.setTransformMatrix(this.virtualMatrix);
        }
    }

    // 去混淆评级： 低(20)
    @Override  // com.badlogic.gdx.graphics.g2d.SpriteBatch
    public Matrix4 getTransformMatrix() {
        return this.adjustNeeded ? this.virtualMatrix : super.getTransformMatrix();
    }

    public void setTransformMatrix(Affine2 affine20) {
        Matrix4 matrix40 = super.getTransformMatrix();
        if(CpuSpriteBatch.checkEqual(matrix40, affine20)) {
            this.adjustNeeded = false;
            return;
        }
        this.virtualMatrix.setAsAffine(affine20);
        if(this.isDrawing()) {
            this.adjustNeeded = true;
            if(this.haveIdentityRealMatrix) {
                this.adjustAffine.set(affine20);
                return;
            }
            this.adjustAffine.set(matrix40).inv().mul(affine20);
            return;
        }
        matrix40.setAsAffine(affine20);
        this.haveIdentityRealMatrix = CpuSpriteBatch.checkIdt(matrix40);
    }

    @Override  // com.badlogic.gdx.graphics.g2d.SpriteBatch
    public void setTransformMatrix(Matrix4 matrix40) {
        Matrix4 matrix41 = super.getTransformMatrix();
        if(CpuSpriteBatch.checkEqual(matrix41, matrix40)) {
            this.adjustNeeded = false;
            return;
        }
        if(this.isDrawing()) {
            this.virtualMatrix.setAsAffine(matrix40);
            this.adjustNeeded = true;
            if(this.haveIdentityRealMatrix) {
                this.adjustAffine.set(matrix40);
                return;
            }
            this.tmpAffine.set(matrix40);
            this.adjustAffine.set(matrix41).inv().mul(this.tmpAffine);
            return;
        }
        matrix41.setAsAffine(matrix40);
        this.haveIdentityRealMatrix = CpuSpriteBatch.checkIdt(matrix41);
    }
}

