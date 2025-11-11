package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.Serializable;

public final class Affine2 implements Serializable {
    public float m00;
    public float m01;
    public float m02;
    public float m10;
    public float m11;
    public float m12;
    private static final long serialVersionUID = 0x15285B8A50DAE163L;

    public Affine2() {
        this.m00 = 1.0f;
        this.m01 = 0.0f;
        this.m02 = 0.0f;
        this.m10 = 0.0f;
        this.m11 = 1.0f;
        this.m12 = 0.0f;
    }

    public Affine2(Affine2 affine20) {
        this.m00 = 1.0f;
        this.m01 = 0.0f;
        this.m02 = 0.0f;
        this.m10 = 0.0f;
        this.m11 = 1.0f;
        this.m12 = 0.0f;
        this.set(affine20);
    }

    public void applyTo(Vector2 vector20) {
        float f = vector20.x;
        vector20.x = this.m00 * f + this.m01 * vector20.y + this.m02;
        vector20.y = this.m10 * f + this.m11 * vector20.y + this.m12;
    }

    public float det() {
        return this.m00 * this.m11 - this.m01 * this.m10;
    }

    public Vector2 getTranslation(Vector2 vector20) {
        vector20.x = this.m02;
        vector20.y = this.m12;
        return vector20;
    }

    public Affine2 idt() {
        this.m00 = 1.0f;
        this.m01 = 0.0f;
        this.m02 = 0.0f;
        this.m10 = 0.0f;
        this.m11 = 1.0f;
        this.m12 = 0.0f;
        return this;
    }

    public Affine2 inv() {
        float f = this.det();
        if(f == 0.0f) {
            throw new GdxRuntimeException("Can\'t invert a singular affine matrix");
        }
        float f1 = -this.m01;
        float f2 = this.m01 * this.m12 - this.m11 * this.m02;
        float f3 = -this.m10;
        float f4 = this.m00;
        float f5 = this.m10 * this.m02 - this.m12 * f4;
        this.m00 = this.m11 * (1.0f / f);
        this.m01 = f1 * (1.0f / f);
        this.m02 = f2 * (1.0f / f);
        this.m10 = f3 * (1.0f / f);
        this.m11 = f4 * (1.0f / f);
        this.m12 = 1.0f / f * f5;
        return this;
    }

    public boolean isIdt() {
        return this.m00 == 1.0f && this.m02 == 0.0f && this.m12 == 0.0f && this.m11 == 1.0f && this.m01 == 0.0f && this.m10 == 0.0f;
    }

    public boolean isTranslation() {
        return this.m00 == 1.0f && this.m11 == 1.0f && this.m01 == 0.0f && this.m10 == 0.0f;
    }

    public Affine2 mul(Affine2 affine20) {
        float f = this.m00 * affine20.m01 + this.m01 * affine20.m11;
        float f1 = this.m00 * affine20.m02 + this.m01 * affine20.m12 + this.m02;
        float f2 = affine20.m00 * this.m10 + affine20.m10 * this.m11;
        float f3 = affine20.m01 * this.m10 + affine20.m11 * this.m11;
        float f4 = this.m10 * affine20.m02 + this.m11 * affine20.m12 + this.m12;
        this.m00 = this.m00 * affine20.m00 + this.m01 * affine20.m10;
        this.m01 = f;
        this.m02 = f1;
        this.m10 = f2;
        this.m11 = f3;
        this.m12 = f4;
        return this;
    }

    public Affine2 preMul(Affine2 affine20) {
        float f = affine20.m00 * this.m01 + affine20.m01 * this.m11;
        float f1 = affine20.m00 * this.m02 + affine20.m01 * this.m12 + affine20.m02;
        float f2 = this.m00 * affine20.m10 + this.m10 * affine20.m11;
        float f3 = this.m01 * affine20.m10 + this.m11 * affine20.m11;
        float f4 = affine20.m10 * this.m02 + affine20.m11 * this.m12 + affine20.m12;
        this.m00 = affine20.m00 * this.m00 + affine20.m01 * this.m10;
        this.m01 = f;
        this.m02 = f1;
        this.m10 = f2;
        this.m11 = f3;
        this.m12 = f4;
        return this;
    }

    public Affine2 preRotate(float f) {
        if(f == 0.0f) {
            return this;
        }
        float f1 = MathUtils.cosDeg(f);
        float f2 = MathUtils.sinDeg(f);
        float f3 = this.m00 * f2 + this.m10 * f1;
        float f4 = this.m01 * f2 + this.m11 * f1;
        float f5 = f2 * this.m02 + f1 * this.m12;
        this.m00 = f1 * this.m00 - f2 * this.m10;
        this.m01 = f1 * this.m01 - f2 * this.m11;
        this.m02 = f1 * this.m02 - f2 * this.m12;
        this.m10 = f3;
        this.m11 = f4;
        this.m12 = f5;
        return this;
    }

    public Affine2 preRotateRad(float f) {
        if(f == 0.0f) {
            return this;
        }
        float f1 = MathUtils.cos(f);
        float f2 = MathUtils.sin(f);
        float f3 = this.m00 * f2 + this.m10 * f1;
        float f4 = this.m01 * f2 + this.m11 * f1;
        float f5 = f2 * this.m02 + f1 * this.m12;
        this.m00 = f1 * this.m00 - f2 * this.m10;
        this.m01 = f1 * this.m01 - f2 * this.m11;
        this.m02 = f1 * this.m02 - f2 * this.m12;
        this.m10 = f3;
        this.m11 = f4;
        this.m12 = f5;
        return this;
    }

    public Affine2 preScale(float f, float f1) {
        this.m00 *= f;
        this.m01 *= f;
        this.m02 *= f;
        this.m10 *= f1;
        this.m11 *= f1;
        this.m12 *= f1;
        return this;
    }

    public Affine2 preScale(Vector2 vector20) {
        return this.preScale(vector20.x, vector20.y);
    }

    public Affine2 preShear(float f, float f1) {
        float f2 = this.m10 + this.m00 * f1;
        float f3 = this.m11 + this.m01 * f1;
        float f4 = this.m12 + f1 * this.m02;
        this.m00 = f * this.m10 + this.m00;
        this.m01 = f * this.m11 + this.m01;
        this.m02 = f * this.m12 + this.m02;
        this.m10 = f2;
        this.m11 = f3;
        this.m12 = f4;
        return this;
    }

    public Affine2 preShear(Vector2 vector20) {
        return this.preShear(vector20.x, vector20.y);
    }

    public Affine2 preTranslate(float f, float f1) {
        this.m02 += f;
        this.m12 += f1;
        return this;
    }

    public Affine2 preTranslate(Vector2 vector20) {
        return this.preTranslate(vector20.x, vector20.y);
    }

    public Affine2 rotate(float f) {
        if(f == 0.0f) {
            return this;
        }
        float f1 = MathUtils.cosDeg(f);
        float f2 = MathUtils.sinDeg(f);
        float f3 = this.m00 * -f2 + this.m01 * f1;
        float f4 = this.m10 * -f2 + this.m11 * f1;
        this.m00 = this.m00 * f1 + this.m01 * f2;
        this.m01 = f3;
        this.m10 = this.m10 * f1 + f2 * this.m11;
        this.m11 = f4;
        return this;
    }

    public Affine2 rotateRad(float f) {
        if(f == 0.0f) {
            return this;
        }
        float f1 = MathUtils.cos(f);
        float f2 = MathUtils.sin(f);
        float f3 = this.m00 * -f2 + this.m01 * f1;
        float f4 = this.m10 * -f2 + this.m11 * f1;
        this.m00 = this.m00 * f1 + this.m01 * f2;
        this.m01 = f3;
        this.m10 = this.m10 * f1 + f2 * this.m11;
        this.m11 = f4;
        return this;
    }

    public Affine2 scale(float f, float f1) {
        this.m00 *= f;
        this.m01 *= f1;
        this.m10 *= f;
        this.m11 *= f1;
        return this;
    }

    public Affine2 scale(Vector2 vector20) {
        return this.scale(vector20.x, vector20.y);
    }

    public Affine2 set(Affine2 affine20) {
        this.m00 = affine20.m00;
        this.m01 = affine20.m01;
        this.m02 = affine20.m02;
        this.m10 = affine20.m10;
        this.m11 = affine20.m11;
        this.m12 = affine20.m12;
        return this;
    }

    public Affine2 set(Matrix3 matrix30) {
        float[] arr_f = matrix30.val;
        this.m00 = arr_f[0];
        this.m01 = arr_f[3];
        this.m02 = arr_f[6];
        this.m10 = arr_f[1];
        this.m11 = arr_f[4];
        this.m12 = arr_f[7];
        return this;
    }

    public Affine2 set(Matrix4 matrix40) {
        this.m00 = matrix40.val[0];
        this.m01 = matrix40.val[4];
        this.m02 = matrix40.val[12];
        this.m10 = matrix40.val[1];
        this.m11 = matrix40.val[5];
        this.m12 = matrix40.val[13];
        return this;
    }

    public Affine2 setToProduct(Affine2 affine20, Affine2 affine21) {
        this.m00 = affine20.m00 * affine21.m00 + affine20.m01 * affine21.m10;
        this.m01 = affine21.m01 * affine20.m00 + affine20.m01 * affine21.m11;
        this.m02 = affine20.m00 * affine21.m02 + affine20.m01 * affine21.m12 + affine20.m02;
        this.m10 = affine20.m10 * affine21.m00 + affine21.m10 * affine20.m11;
        this.m11 = affine21.m01 * affine20.m10 + affine20.m11 * affine21.m11;
        this.m12 = affine20.m10 * affine21.m02 + affine20.m11 * affine21.m12 + affine20.m12;
        return this;
    }

    public Affine2 setToRotation(float f) {
        float f1 = MathUtils.cosDeg(f);
        float f2 = MathUtils.sinDeg(f);
        this.m00 = f1;
        this.m01 = -f2;
        this.m02 = 0.0f;
        this.m10 = f2;
        this.m11 = f1;
        this.m12 = 0.0f;
        return this;
    }

    public Affine2 setToRotation(float f, float f1) {
        this.m00 = f;
        this.m01 = -f1;
        this.m02 = 0.0f;
        this.m10 = f1;
        this.m11 = f;
        this.m12 = 0.0f;
        return this;
    }

    public Affine2 setToRotationRad(float f) {
        float f1 = MathUtils.cos(f);
        float f2 = MathUtils.sin(f);
        this.m00 = f1;
        this.m01 = -f2;
        this.m02 = 0.0f;
        this.m10 = f2;
        this.m11 = f1;
        this.m12 = 0.0f;
        return this;
    }

    public Affine2 setToScaling(float f, float f1) {
        this.m00 = f;
        this.m01 = 0.0f;
        this.m02 = 0.0f;
        this.m10 = 0.0f;
        this.m11 = f1;
        this.m12 = 0.0f;
        return this;
    }

    public Affine2 setToScaling(Vector2 vector20) {
        return this.setToScaling(vector20.x, vector20.y);
    }

    public Affine2 setToShearing(float f, float f1) {
        this.m00 = 1.0f;
        this.m01 = f;
        this.m02 = 0.0f;
        this.m10 = f1;
        this.m11 = 1.0f;
        this.m12 = 0.0f;
        return this;
    }

    public Affine2 setToShearing(Vector2 vector20) {
        return this.setToShearing(vector20.x, vector20.y);
    }

    public Affine2 setToTranslation(float f, float f1) {
        this.m00 = 1.0f;
        this.m01 = 0.0f;
        this.m02 = f;
        this.m10 = 0.0f;
        this.m11 = 1.0f;
        this.m12 = f1;
        return this;
    }

    public Affine2 setToTranslation(Vector2 vector20) {
        return this.setToTranslation(vector20.x, vector20.y);
    }

    public Affine2 setToTrnRotRadScl(float f, float f1, float f2, float f3, float f4) {
        this.m02 = f;
        this.m12 = f1;
        if(f2 == 0.0f) {
            this.m00 = f3;
            this.m01 = 0.0f;
            this.m10 = 0.0f;
            this.m11 = f4;
            return this;
        }
        float f5 = MathUtils.sin(f2);
        float f6 = MathUtils.cos(f2);
        this.m00 = f6 * f3;
        this.m01 = -f5 * f4;
        this.m10 = f5 * f3;
        this.m11 = f6 * f4;
        return this;
    }

    public Affine2 setToTrnRotRadScl(Vector2 vector20, float f, Vector2 vector21) {
        return this.setToTrnRotRadScl(vector20.x, vector20.y, f, vector21.x, vector21.y);
    }

    public Affine2 setToTrnRotScl(float f, float f1, float f2, float f3, float f4) {
        this.m02 = f;
        this.m12 = f1;
        if(f2 == 0.0f) {
            this.m00 = f3;
            this.m01 = 0.0f;
            this.m10 = 0.0f;
            this.m11 = f4;
            return this;
        }
        float f5 = MathUtils.sinDeg(f2);
        float f6 = MathUtils.cosDeg(f2);
        this.m00 = f6 * f3;
        this.m01 = -f5 * f4;
        this.m10 = f5 * f3;
        this.m11 = f6 * f4;
        return this;
    }

    public Affine2 setToTrnRotScl(Vector2 vector20, float f, Vector2 vector21) {
        return this.setToTrnRotScl(vector20.x, vector20.y, f, vector21.x, vector21.y);
    }

    public Affine2 setToTrnScl(float f, float f1, float f2, float f3) {
        this.m00 = f2;
        this.m01 = 0.0f;
        this.m02 = f;
        this.m10 = 0.0f;
        this.m11 = f3;
        this.m12 = f1;
        return this;
    }

    public Affine2 setToTrnScl(Vector2 vector20, Vector2 vector21) {
        return this.setToTrnScl(vector20.x, vector20.y, vector21.x, vector21.y);
    }

    public Affine2 shear(float f, float f1) {
        float f2 = this.m01 + this.m00 * f;
        this.m00 = f1 * this.m01 + this.m00;
        this.m01 = f2;
        float f3 = this.m11 + f * this.m10;
        this.m10 = f1 * this.m11 + this.m10;
        this.m11 = f3;
        return this;
    }

    public Affine2 shear(Vector2 vector20) {
        return this.shear(vector20.x, vector20.y);
    }

    @Override
    public String toString() {
        return "[" + this.m00 + "|" + this.m01 + "|" + this.m02 + "]\n[" + this.m10 + "|" + this.m11 + "|" + this.m12 + "]\n[0.0|0.0|0.1]";
    }

    public Affine2 translate(float f, float f1) {
        this.m02 += this.m00 * f + this.m01 * f1;
        this.m12 += this.m10 * f + this.m11 * f1;
        return this;
    }

    public Affine2 translate(Vector2 vector20) {
        return this.translate(vector20.x, vector20.y);
    }
}

