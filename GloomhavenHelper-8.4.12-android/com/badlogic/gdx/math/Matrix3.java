package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.Serializable;

public class Matrix3 implements Serializable {
    public static final int M00 = 0;
    public static final int M01 = 3;
    public static final int M02 = 6;
    public static final int M10 = 1;
    public static final int M11 = 4;
    public static final int M12 = 7;
    public static final int M20 = 2;
    public static final int M21 = 5;
    public static final int M22 = 8;
    private static final long serialVersionUID = 0x6DBD5498495B94ACL;
    private float[] tmp;
    public float[] val;

    public Matrix3() {
        this.val = new float[9];
        this.tmp = new float[9];
        this.idt();
    }

    public Matrix3(Matrix3 matrix30) {
        this.val = new float[9];
        this.tmp = new float[9];
        this.set(matrix30);
    }

    public Matrix3(float[] arr_f) {
        this.val = new float[9];
        this.tmp = new float[9];
        this.set(arr_f);
    }

    public float det() {
        return this.val[0] * this.val[4] * this.val[8] + this.val[3] * this.val[7] * this.val[2] + this.val[6] * this.val[1] * this.val[5] - this.val[0] * this.val[7] * this.val[5] - this.val[3] * this.val[1] * this.val[8] - this.val[6] * this.val[4] * this.val[2];
    }

    public float getRotation() {
        return ((float)Math.atan2(this.val[1], this.val[0])) * 57.295776f;
    }

    public float getRotationRad() {
        return (float)Math.atan2(this.val[1], this.val[0]);
    }

    public Vector2 getScale(Vector2 vector20) {
        float[] arr_f = this.val;
        vector20.x = (float)Math.sqrt(arr_f[0] * arr_f[0] + arr_f[3] * arr_f[3]);
        vector20.y = (float)Math.sqrt(arr_f[1] * arr_f[1] + arr_f[4] * arr_f[4]);
        return vector20;
    }

    public Vector2 getTranslation(Vector2 vector20) {
        float[] arr_f = this.val;
        vector20.x = arr_f[6];
        vector20.y = arr_f[7];
        return vector20;
    }

    public float[] getValues() {
        return this.val;
    }

    public Matrix3 idt() {
        float[] arr_f = this.val;
        arr_f[0] = 1.0f;
        arr_f[1] = 0.0f;
        arr_f[2] = 0.0f;
        arr_f[3] = 0.0f;
        arr_f[4] = 1.0f;
        arr_f[5] = 0.0f;
        arr_f[6] = 0.0f;
        arr_f[7] = 0.0f;
        arr_f[8] = 1.0f;
        return this;
    }

    public Matrix3 inv() {
        float f = this.det();
        if(f == 0.0f) {
            throw new GdxRuntimeException("Can\'t invert a singular matrix");
        }
        float[] arr_f = this.tmp;
        float[] arr_f1 = this.val;
        arr_f[0] = arr_f1[4] * arr_f1[8] - arr_f1[5] * arr_f1[7];
        arr_f[1] = arr_f1[2] * arr_f1[7] - arr_f1[1] * arr_f1[8];
        arr_f[2] = arr_f1[1] * arr_f1[5] - arr_f1[2] * arr_f1[4];
        arr_f[3] = arr_f1[5] * arr_f1[6] - arr_f1[3] * arr_f1[8];
        arr_f[4] = arr_f1[0] * arr_f1[8] - arr_f1[2] * arr_f1[6];
        arr_f[5] = arr_f1[2] * arr_f1[3] - arr_f1[0] * arr_f1[5];
        arr_f[6] = arr_f1[3] * arr_f1[7] - arr_f1[4] * arr_f1[6];
        arr_f[7] = arr_f1[1] * arr_f1[6] - arr_f1[0] * arr_f1[7];
        arr_f[8] = arr_f1[0] * arr_f1[4] - arr_f1[1] * arr_f1[3];
        arr_f1[0] = arr_f[0] * (1.0f / f);
        arr_f1[1] = arr_f[1] * (1.0f / f);
        arr_f1[2] = arr_f[2] * (1.0f / f);
        arr_f1[3] = arr_f[3] * (1.0f / f);
        arr_f1[4] = arr_f[4] * (1.0f / f);
        arr_f1[5] = arr_f[5] * (1.0f / f);
        arr_f1[6] = arr_f[6] * (1.0f / f);
        arr_f1[7] = arr_f[7] * (1.0f / f);
        arr_f1[8] = 1.0f / f * arr_f[8];
        return this;
    }

    private static void mul(float[] arr_f, float[] arr_f1) {
        float f = arr_f[0] * arr_f1[0] + arr_f[3] * arr_f1[1] + arr_f[6] * arr_f1[2];
        float f1 = arr_f[0] * arr_f1[3] + arr_f[3] * arr_f1[4] + arr_f[6] * arr_f1[5];
        float f2 = arr_f[0] * arr_f1[6] + arr_f[3] * arr_f1[7] + arr_f[6] * arr_f1[8];
        float f3 = arr_f[1] * arr_f1[0] + arr_f[4] * arr_f1[1] + arr_f[7] * arr_f1[2];
        float f4 = arr_f[1] * arr_f1[3] + arr_f[4] * arr_f1[4] + arr_f[7] * arr_f1[5];
        float f5 = arr_f[1] * arr_f1[6] + arr_f[4] * arr_f1[7] + arr_f[7] * arr_f1[8];
        float f6 = arr_f[2] * arr_f1[0] + arr_f[5] * arr_f1[1] + arr_f[8] * arr_f1[2];
        float f7 = arr_f[2] * arr_f1[3] + arr_f[5] * arr_f1[4] + arr_f[8] * arr_f1[5];
        float f8 = arr_f[2] * arr_f1[6] + arr_f[5] * arr_f1[7] + arr_f[8] * arr_f1[8];
        arr_f[0] = f;
        arr_f[1] = f3;
        arr_f[2] = f6;
        arr_f[3] = f1;
        arr_f[4] = f4;
        arr_f[5] = f7;
        arr_f[6] = f2;
        arr_f[7] = f5;
        arr_f[8] = f8;
    }

    public Matrix3 mul(Matrix3 matrix30) {
        float[] arr_f = this.val;
        float[] arr_f1 = matrix30.val;
        float f = arr_f[0] * arr_f1[0] + arr_f[3] * arr_f1[1] + arr_f[6] * arr_f1[2];
        float f1 = arr_f[0] * arr_f1[3] + arr_f[3] * arr_f1[4] + arr_f[6] * arr_f1[5];
        float f2 = arr_f[0] * arr_f1[6] + arr_f[3] * arr_f1[7] + arr_f[6] * arr_f1[8];
        float f3 = arr_f[1] * arr_f1[0] + arr_f[4] * arr_f1[1] + arr_f[7] * arr_f1[2];
        float f4 = arr_f[1] * arr_f1[3] + arr_f[4] * arr_f1[4] + arr_f[7] * arr_f1[5];
        float f5 = arr_f[1] * arr_f1[6] + arr_f[4] * arr_f1[7] + arr_f[7] * arr_f1[8];
        float f6 = arr_f[2] * arr_f1[0] + arr_f[5] * arr_f1[1] + arr_f[8] * arr_f1[2];
        float f7 = arr_f[2] * arr_f1[3] + arr_f[5] * arr_f1[4] + arr_f[8] * arr_f1[5];
        float f8 = arr_f[2] * arr_f1[6] + arr_f[5] * arr_f1[7] + arr_f[8] * arr_f1[8];
        arr_f[0] = f;
        arr_f[1] = f3;
        arr_f[2] = f6;
        arr_f[3] = f1;
        arr_f[4] = f4;
        arr_f[5] = f7;
        arr_f[6] = f2;
        arr_f[7] = f5;
        arr_f[8] = f8;
        return this;
    }

    public Matrix3 mulLeft(Matrix3 matrix30) {
        float[] arr_f = this.val;
        float[] arr_f1 = matrix30.val;
        float f = arr_f1[0] * arr_f[0] + arr_f1[3] * arr_f[1] + arr_f1[6] * arr_f[2];
        float f1 = arr_f1[0] * arr_f[3] + arr_f1[3] * arr_f[4] + arr_f1[6] * arr_f[5];
        float f2 = arr_f1[0] * arr_f[6] + arr_f1[3] * arr_f[7] + arr_f1[6] * arr_f[8];
        float f3 = arr_f1[1] * arr_f[0] + arr_f1[4] * arr_f[1] + arr_f1[7] * arr_f[2];
        float f4 = arr_f1[1] * arr_f[3] + arr_f1[4] * arr_f[4] + arr_f1[7] * arr_f[5];
        float f5 = arr_f1[1] * arr_f[6] + arr_f1[4] * arr_f[7] + arr_f1[7] * arr_f[8];
        float f6 = arr_f1[2] * arr_f[0] + arr_f1[5] * arr_f[1] + arr_f1[8] * arr_f[2];
        float f7 = arr_f1[2] * arr_f[3] + arr_f1[5] * arr_f[4] + arr_f1[8] * arr_f[5];
        float f8 = arr_f1[2] * arr_f[6] + arr_f1[5] * arr_f[7] + arr_f1[8] * arr_f[8];
        arr_f[0] = f;
        arr_f[1] = f3;
        arr_f[2] = f6;
        arr_f[3] = f1;
        arr_f[4] = f4;
        arr_f[5] = f7;
        arr_f[6] = f2;
        arr_f[7] = f5;
        arr_f[8] = f8;
        return this;
    }

    public Matrix3 rotate(float f) {
        return this.rotateRad(f * 0.017453f);
    }

    public Matrix3 rotateRad(float f) {
        if(f == 0.0f) {
            return this;
        }
        float f1 = (float)Math.cos(f);
        float f2 = (float)Math.sin(f);
        float[] arr_f = this.tmp;
        arr_f[0] = f1;
        arr_f[1] = f2;
        arr_f[2] = 0.0f;
        arr_f[3] = -f2;
        arr_f[4] = f1;
        arr_f[5] = 0.0f;
        arr_f[6] = 0.0f;
        arr_f[7] = 0.0f;
        arr_f[8] = 1.0f;
        Matrix3.mul(this.val, arr_f);
        return this;
    }

    public Matrix3 scale(float f, float f1) {
        float[] arr_f = this.tmp;
        arr_f[0] = f;
        arr_f[1] = 0.0f;
        arr_f[2] = 0.0f;
        arr_f[3] = 0.0f;
        arr_f[4] = f1;
        arr_f[5] = 0.0f;
        arr_f[6] = 0.0f;
        arr_f[7] = 0.0f;
        arr_f[8] = 1.0f;
        Matrix3.mul(this.val, arr_f);
        return this;
    }

    public Matrix3 scale(Vector2 vector20) {
        float[] arr_f = this.tmp;
        arr_f[0] = vector20.x;
        arr_f[1] = 0.0f;
        arr_f[2] = 0.0f;
        arr_f[3] = 0.0f;
        arr_f[4] = vector20.y;
        arr_f[5] = 0.0f;
        arr_f[6] = 0.0f;
        arr_f[7] = 0.0f;
        arr_f[8] = 1.0f;
        Matrix3.mul(this.val, arr_f);
        return this;
    }

    public Matrix3 scl(float f) {
        float[] arr_f = this.val;
        arr_f[0] *= f;
        arr_f[4] *= f;
        return this;
    }

    public Matrix3 scl(Vector2 vector20) {
        this.val[0] *= vector20.x;
        this.val[4] *= vector20.y;
        return this;
    }

    public Matrix3 scl(Vector3 vector30) {
        this.val[0] *= vector30.x;
        this.val[4] *= vector30.y;
        return this;
    }

    public Matrix3 set(Affine2 affine20) {
        float[] arr_f = this.val;
        arr_f[0] = affine20.m00;
        arr_f[1] = affine20.m10;
        arr_f[2] = 0.0f;
        arr_f[3] = affine20.m01;
        arr_f[4] = affine20.m11;
        arr_f[5] = 0.0f;
        arr_f[6] = affine20.m02;
        arr_f[7] = affine20.m12;
        arr_f[8] = 1.0f;
        return this;
    }

    public Matrix3 set(Matrix3 matrix30) {
        System.arraycopy(matrix30.val, 0, this.val, 0, this.val.length);
        return this;
    }

    public Matrix3 set(Matrix4 matrix40) {
        float[] arr_f = this.val;
        arr_f[0] = matrix40.val[0];
        arr_f[1] = matrix40.val[1];
        arr_f[2] = matrix40.val[2];
        arr_f[3] = matrix40.val[4];
        arr_f[4] = matrix40.val[5];
        arr_f[5] = matrix40.val[6];
        arr_f[6] = matrix40.val[8];
        arr_f[7] = matrix40.val[9];
        arr_f[8] = matrix40.val[10];
        return this;
    }

    public Matrix3 set(float[] arr_f) {
        System.arraycopy(arr_f, 0, this.val, 0, this.val.length);
        return this;
    }

    public Matrix3 setToRotation(float f) {
        return this.setToRotationRad(f * 0.017453f);
    }

    public Matrix3 setToRotation(Vector3 vector30, float f) {
        return this.setToRotation(vector30, MathUtils.cosDeg(f), MathUtils.sinDeg(f));
    }

    public Matrix3 setToRotation(Vector3 vector30, float f, float f1) {
        float[] arr_f = this.val;
        arr_f[0] = vector30.x * (1.0f - f) * vector30.x + f;
        arr_f[3] = vector30.x * (1.0f - f) * vector30.y - vector30.z * f1;
        arr_f[6] = vector30.z * (1.0f - f) * vector30.x + vector30.y * f1;
        arr_f[1] = vector30.x * (1.0f - f) * vector30.y + vector30.z * f1;
        arr_f[4] = vector30.y * (1.0f - f) * vector30.y + f;
        arr_f[7] = vector30.y * (1.0f - f) * vector30.z - vector30.x * f1;
        arr_f[2] = vector30.z * (1.0f - f) * vector30.x - vector30.y * f1;
        arr_f[5] = vector30.y * (1.0f - f) * vector30.z + vector30.x * f1;
        arr_f[8] = (1.0f - f) * vector30.z * vector30.z + f;
        return this;
    }

    public Matrix3 setToRotationRad(float f) {
        float f1 = (float)Math.cos(f);
        float f2 = (float)Math.sin(f);
        float[] arr_f = this.val;
        arr_f[0] = f1;
        arr_f[1] = f2;
        arr_f[2] = 0.0f;
        arr_f[3] = -f2;
        arr_f[4] = f1;
        arr_f[5] = 0.0f;
        arr_f[6] = 0.0f;
        arr_f[7] = 0.0f;
        arr_f[8] = 1.0f;
        return this;
    }

    public Matrix3 setToScaling(float f, float f1) {
        float[] arr_f = this.val;
        arr_f[0] = f;
        arr_f[1] = 0.0f;
        arr_f[2] = 0.0f;
        arr_f[3] = 0.0f;
        arr_f[4] = f1;
        arr_f[5] = 0.0f;
        arr_f[6] = 0.0f;
        arr_f[7] = 0.0f;
        arr_f[8] = 1.0f;
        return this;
    }

    public Matrix3 setToScaling(Vector2 vector20) {
        float[] arr_f = this.val;
        arr_f[0] = vector20.x;
        arr_f[1] = 0.0f;
        arr_f[2] = 0.0f;
        arr_f[3] = 0.0f;
        arr_f[4] = vector20.y;
        arr_f[5] = 0.0f;
        arr_f[6] = 0.0f;
        arr_f[7] = 0.0f;
        arr_f[8] = 1.0f;
        return this;
    }

    public Matrix3 setToTranslation(float f, float f1) {
        float[] arr_f = this.val;
        arr_f[0] = 1.0f;
        arr_f[1] = 0.0f;
        arr_f[2] = 0.0f;
        arr_f[3] = 0.0f;
        arr_f[4] = 1.0f;
        arr_f[5] = 0.0f;
        arr_f[6] = f;
        arr_f[7] = f1;
        arr_f[8] = 1.0f;
        return this;
    }

    public Matrix3 setToTranslation(Vector2 vector20) {
        float[] arr_f = this.val;
        arr_f[0] = 1.0f;
        arr_f[1] = 0.0f;
        arr_f[2] = 0.0f;
        arr_f[3] = 0.0f;
        arr_f[4] = 1.0f;
        arr_f[5] = 0.0f;
        arr_f[6] = vector20.x;
        arr_f[7] = vector20.y;
        arr_f[8] = 1.0f;
        return this;
    }

    @Override
    public String toString() {
        return "[" + this.val[0] + "|" + this.val[3] + "|" + this.val[6] + "]\n[" + this.val[1] + "|" + this.val[4] + "|" + this.val[7] + "]\n[" + this.val[2] + "|" + this.val[5] + "|" + this.val[8] + "]";
    }

    public Matrix3 translate(float f, float f1) {
        float[] arr_f = this.val;
        float[] arr_f1 = this.tmp;
        arr_f1[0] = 1.0f;
        arr_f1[1] = 0.0f;
        arr_f1[2] = 0.0f;
        arr_f1[3] = 0.0f;
        arr_f1[4] = 1.0f;
        arr_f1[5] = 0.0f;
        arr_f1[6] = f;
        arr_f1[7] = f1;
        arr_f1[8] = 1.0f;
        Matrix3.mul(arr_f, arr_f1);
        return this;
    }

    public Matrix3 translate(Vector2 vector20) {
        float[] arr_f = this.val;
        float[] arr_f1 = this.tmp;
        arr_f1[0] = 1.0f;
        arr_f1[1] = 0.0f;
        arr_f1[2] = 0.0f;
        arr_f1[3] = 0.0f;
        arr_f1[4] = 1.0f;
        arr_f1[5] = 0.0f;
        arr_f1[6] = vector20.x;
        this.tmp[7] = vector20.y;
        float[] arr_f2 = this.tmp;
        arr_f2[8] = 1.0f;
        Matrix3.mul(arr_f, arr_f2);
        return this;
    }

    public Matrix3 transpose() {
        float[] arr_f = this.val;
        float f = arr_f[1];
        float f1 = arr_f[2];
        float f2 = arr_f[3];
        float f3 = arr_f[5];
        float f4 = arr_f[6];
        float f5 = arr_f[7];
        arr_f[3] = f;
        arr_f[6] = f1;
        arr_f[1] = f2;
        arr_f[7] = f3;
        arr_f[2] = f4;
        arr_f[5] = f5;
        return this;
    }

    public Matrix3 trn(float f, float f1) {
        float[] arr_f = this.val;
        arr_f[6] += f;
        arr_f[7] += f1;
        return this;
    }

    public Matrix3 trn(Vector2 vector20) {
        this.val[6] += vector20.x;
        this.val[7] += vector20.y;
        return this;
    }

    public Matrix3 trn(Vector3 vector30) {
        this.val[6] += vector30.x;
        this.val[7] += vector30.y;
        return this;
    }
}

