package com.badlogic.gdx.math;

import java.io.Serializable;

public class Matrix4 implements Serializable {
    public static final int M00 = 0;
    public static final int M01 = 4;
    public static final int M02 = 8;
    public static final int M03 = 12;
    public static final int M10 = 1;
    public static final int M11 = 5;
    public static final int M12 = 9;
    public static final int M13 = 13;
    public static final int M20 = 2;
    public static final int M21 = 6;
    public static final int M22 = 10;
    public static final int M23 = 14;
    public static final int M30 = 3;
    public static final int M31 = 7;
    public static final int M32 = 11;
    public static final int M33 = 15;
    static final Vector3 l_vex = null;
    static final Vector3 l_vey = null;
    static final Vector3 l_vez = null;
    static final Quaternion quat = null;
    static final Quaternion quat2 = null;
    static final Vector3 right = null;
    private static final long serialVersionUID = 0xDA48F2F1E5EC641FL;
    static final Vector3 tmpForward;
    static final Matrix4 tmpMat;
    static final Vector3 tmpUp;
    static final Vector3 tmpVec;
    public final float[] val;

    static {
        Matrix4.quat = new Quaternion();
        Matrix4.quat2 = new Quaternion();
        Matrix4.l_vez = new Vector3();
        Matrix4.l_vex = new Vector3();
        Matrix4.l_vey = new Vector3();
        Matrix4.tmpVec = new Vector3();
        Matrix4.tmpMat = new Matrix4();
        Matrix4.right = new Vector3();
        Matrix4.tmpForward = new Vector3();
        Matrix4.tmpUp = new Vector3();
    }

    public Matrix4() {
        this.val = new float[16];
        this.val[0] = 1.0f;
        this.val[5] = 1.0f;
        this.val[10] = 1.0f;
        this.val[15] = 1.0f;
    }

    public Matrix4(Matrix4 matrix40) {
        this.val = new float[16];
        this.set(matrix40);
    }

    public Matrix4(Quaternion quaternion0) {
        this.val = new float[16];
        this.set(quaternion0);
    }

    public Matrix4(Vector3 vector30, Quaternion quaternion0, Vector3 vector31) {
        this.val = new float[16];
        this.set(vector30, quaternion0, vector31);
    }

    public Matrix4(float[] arr_f) {
        this.val = new float[16];
        this.set(arr_f);
    }

    public Matrix4 avg(Matrix4 matrix40, float f) {
        this.getScale(Matrix4.tmpVec);
        matrix40.getScale(Matrix4.tmpForward);
        this.getRotation(Matrix4.quat);
        matrix40.getRotation(Matrix4.quat2);
        this.getTranslation(Matrix4.tmpUp);
        matrix40.getTranslation(Matrix4.right);
        this.setToScaling(Matrix4.tmpVec.scl(f).add(Matrix4.tmpForward.scl(1.0f - f)));
        this.rotate(Matrix4.quat.slerp(Matrix4.quat2, 1.0f - f));
        this.setTranslation(Matrix4.tmpUp.scl(f).add(Matrix4.right.scl(1.0f - f)));
        return this;
    }

    public Matrix4 avg(Matrix4[] arr_matrix4) {
        float f = 1.0f / ((float)arr_matrix4.length);
        Vector3 vector30 = arr_matrix4[0].getScale(Matrix4.tmpUp).scl(f);
        Matrix4.tmpVec.set(vector30);
        Quaternion quaternion0 = arr_matrix4[0].getRotation(Matrix4.quat2).exp(f);
        Matrix4.quat.set(quaternion0);
        Vector3 vector31 = arr_matrix4[0].getTranslation(Matrix4.tmpUp).scl(f);
        Matrix4.tmpForward.set(vector31);
        for(int v = 1; v < arr_matrix4.length; ++v) {
            Vector3 vector32 = arr_matrix4[v].getScale(Matrix4.tmpUp).scl(f);
            Matrix4.tmpVec.add(vector32);
            Quaternion quaternion1 = arr_matrix4[v].getRotation(Matrix4.quat2).exp(f);
            Matrix4.quat.mul(quaternion1);
            Vector3 vector33 = arr_matrix4[v].getTranslation(Matrix4.tmpUp).scl(f);
            Matrix4.tmpForward.add(vector33);
        }
        Matrix4.quat.nor();
        this.setToScaling(Matrix4.tmpVec);
        this.rotate(Matrix4.quat);
        this.setTranslation(Matrix4.tmpForward);
        return this;
    }

    public Matrix4 avg(Matrix4[] arr_matrix4, float[] arr_f) {
        Vector3 vector30 = arr_matrix4[0].getScale(Matrix4.tmpUp).scl(arr_f[0]);
        Matrix4.tmpVec.set(vector30);
        Quaternion quaternion0 = arr_matrix4[0].getRotation(Matrix4.quat2).exp(arr_f[0]);
        Matrix4.quat.set(quaternion0);
        Vector3 vector31 = arr_matrix4[0].getTranslation(Matrix4.tmpUp).scl(arr_f[0]);
        Matrix4.tmpForward.set(vector31);
        for(int v = 1; v < arr_matrix4.length; ++v) {
            Vector3 vector32 = arr_matrix4[v].getScale(Matrix4.tmpUp).scl(arr_f[v]);
            Matrix4.tmpVec.add(vector32);
            Quaternion quaternion1 = arr_matrix4[v].getRotation(Matrix4.quat2).exp(arr_f[v]);
            Matrix4.quat.mul(quaternion1);
            Vector3 vector33 = arr_matrix4[v].getTranslation(Matrix4.tmpUp).scl(arr_f[v]);
            Matrix4.tmpForward.add(vector33);
        }
        Matrix4.quat.nor();
        this.setToScaling(Matrix4.tmpVec);
        this.rotate(Matrix4.quat);
        this.setTranslation(Matrix4.tmpForward);
        return this;
    }

    public Matrix4 cpy() {
        return new Matrix4(this);
    }

    public static float det(float[] arr_f) {
        return arr_f[3] * arr_f[6] * arr_f[9] * arr_f[12] - arr_f[2] * arr_f[7] * arr_f[9] * arr_f[12] - arr_f[3] * arr_f[5] * arr_f[10] * arr_f[12] + arr_f[1] * arr_f[7] * arr_f[10] * arr_f[12] + arr_f[2] * arr_f[5] * arr_f[11] * arr_f[12] - arr_f[1] * arr_f[6] * arr_f[11] * arr_f[12] - arr_f[3] * arr_f[6] * arr_f[8] * arr_f[13] + arr_f[2] * arr_f[7] * arr_f[8] * arr_f[13] + arr_f[3] * arr_f[4] * arr_f[10] * arr_f[13] - arr_f[0] * arr_f[7] * arr_f[10] * arr_f[13] - arr_f[2] * arr_f[4] * arr_f[11] * arr_f[13] + arr_f[0] * arr_f[6] * arr_f[11] * arr_f[13] + arr_f[3] * arr_f[5] * arr_f[8] * arr_f[14] - arr_f[1] * arr_f[7] * arr_f[8] * arr_f[14] - arr_f[3] * arr_f[4] * arr_f[9] * arr_f[14] + arr_f[0] * arr_f[7] * arr_f[9] * arr_f[14] + arr_f[1] * arr_f[4] * arr_f[11] * arr_f[14] - arr_f[0] * arr_f[5] * arr_f[11] * arr_f[14] - arr_f[2] * arr_f[5] * arr_f[8] * arr_f[15] + arr_f[1] * arr_f[6] * arr_f[8] * arr_f[15] + arr_f[2] * arr_f[4] * arr_f[9] * arr_f[15] - arr_f[0] * arr_f[6] * arr_f[9] * arr_f[15] - arr_f[1] * arr_f[4] * arr_f[10] * arr_f[15] + arr_f[0] * arr_f[5] * arr_f[10] * arr_f[15];
    }

    public float det() {
        return this.val[3] * this.val[6] * this.val[9] * this.val[12] - this.val[2] * this.val[7] * this.val[9] * this.val[12] - this.val[3] * this.val[5] * this.val[10] * this.val[12] + this.val[1] * this.val[7] * this.val[10] * this.val[12] + this.val[2] * this.val[5] * this.val[11] * this.val[12] - this.val[1] * this.val[6] * this.val[11] * this.val[12] - this.val[3] * this.val[6] * this.val[8] * this.val[13] + this.val[2] * this.val[7] * this.val[8] * this.val[13] + this.val[3] * this.val[4] * this.val[10] * this.val[13] - this.val[0] * this.val[7] * this.val[10] * this.val[13] - this.val[2] * this.val[4] * this.val[11] * this.val[13] + this.val[0] * this.val[6] * this.val[11] * this.val[13] + this.val[3] * this.val[5] * this.val[8] * this.val[14] - this.val[1] * this.val[7] * this.val[8] * this.val[14] - this.val[3] * this.val[4] * this.val[9] * this.val[14] + this.val[0] * this.val[7] * this.val[9] * this.val[14] + this.val[1] * this.val[4] * this.val[11] * this.val[14] - this.val[0] * this.val[5] * this.val[11] * this.val[14] - this.val[2] * this.val[5] * this.val[8] * this.val[15] + this.val[1] * this.val[6] * this.val[8] * this.val[15] + this.val[2] * this.val[4] * this.val[9] * this.val[15] - this.val[0] * this.val[6] * this.val[9] * this.val[15] - this.val[1] * this.val[4] * this.val[10] * this.val[15] + this.val[0] * this.val[5] * this.val[10] * this.val[15];
    }

    public float det3x3() {
        return this.val[0] * this.val[5] * this.val[10] + this.val[4] * this.val[9] * this.val[2] + this.val[8] * this.val[1] * this.val[6] - this.val[0] * this.val[9] * this.val[6] - this.val[4] * this.val[1] * this.val[10] - this.val[8] * this.val[5] * this.val[2];
    }

    public void extract4x3Matrix(float[] arr_f) {
        arr_f[0] = this.val[0];
        arr_f[1] = this.val[1];
        arr_f[2] = this.val[2];
        arr_f[3] = this.val[4];
        arr_f[4] = this.val[5];
        arr_f[5] = this.val[6];
        arr_f[6] = this.val[8];
        arr_f[7] = this.val[9];
        arr_f[8] = this.val[10];
        arr_f[9] = this.val[12];
        arr_f[10] = this.val[13];
        arr_f[11] = this.val[14];
    }

    public Quaternion getRotation(Quaternion quaternion0) {
        return quaternion0.setFromMatrix(this);
    }

    public Quaternion getRotation(Quaternion quaternion0, boolean z) {
        return quaternion0.setFromMatrix(z, this);
    }

    public Vector3 getScale(Vector3 vector30) {
        return vector30.set(this.getScaleX(), this.getScaleY(), this.getScaleZ());
    }

    // 去混淆评级： 低(20)
    public float getScaleX() {
        return !MathUtils.isZero(this.val[4]) || !MathUtils.isZero(this.val[8]) ? ((float)Math.sqrt(this.getScaleXSquared())) : Math.abs(this.val[0]);
    }

    public float getScaleXSquared() {
        return this.val[0] * this.val[0] + this.val[4] * this.val[4] + this.val[8] * this.val[8];
    }

    // 去混淆评级： 低(20)
    public float getScaleY() {
        return !MathUtils.isZero(this.val[1]) || !MathUtils.isZero(this.val[9]) ? ((float)Math.sqrt(this.getScaleYSquared())) : Math.abs(this.val[5]);
    }

    public float getScaleYSquared() {
        return this.val[1] * this.val[1] + this.val[5] * this.val[5] + this.val[9] * this.val[9];
    }

    // 去混淆评级： 低(20)
    public float getScaleZ() {
        return !MathUtils.isZero(this.val[2]) || !MathUtils.isZero(this.val[6]) ? ((float)Math.sqrt(this.getScaleZSquared())) : Math.abs(this.val[10]);
    }

    public float getScaleZSquared() {
        return this.val[2] * this.val[2] + this.val[6] * this.val[6] + this.val[10] * this.val[10];
    }

    public Vector3 getTranslation(Vector3 vector30) {
        vector30.x = this.val[12];
        vector30.y = this.val[13];
        vector30.z = this.val[14];
        return vector30;
    }

    public float[] getValues() {
        return this.val;
    }

    // 去混淆评级： 中等(90)
    public boolean hasRotationOrScaling() {
        return !MathUtils.isEqual(this.val[0], 1.0f) || !MathUtils.isEqual(this.val[5], 1.0f) || !MathUtils.isEqual(this.val[10], 1.0f) || !MathUtils.isZero(this.val[4]) || !MathUtils.isZero(this.val[8]) || !MathUtils.isZero(this.val[1]) || !MathUtils.isZero(this.val[9]) || !MathUtils.isZero(this.val[2]) || !MathUtils.isZero(this.val[6]);
    }

    public Matrix4 idt() {
        this.val[0] = 1.0f;
        this.val[4] = 0.0f;
        this.val[8] = 0.0f;
        this.val[12] = 0.0f;
        this.val[1] = 0.0f;
        this.val[5] = 1.0f;
        this.val[9] = 0.0f;
        this.val[13] = 0.0f;
        this.val[2] = 0.0f;
        this.val[6] = 0.0f;
        this.val[10] = 1.0f;
        this.val[14] = 0.0f;
        this.val[3] = 0.0f;
        this.val[7] = 0.0f;
        this.val[11] = 0.0f;
        this.val[15] = 1.0f;
        return this;
    }

    public static boolean inv(float[] arr_f) {
        float f = Matrix4.det(arr_f);
        if(f == 0.0f) {
            return false;
        }
        float f1 = arr_f[9] * arr_f[14] * arr_f[7] - arr_f[13] * arr_f[10] * arr_f[7] + arr_f[13] * arr_f[6] * arr_f[11] - arr_f[5] * arr_f[14] * arr_f[11] - arr_f[9] * arr_f[6] * arr_f[15] + arr_f[5] * arr_f[10] * arr_f[15];
        float f2 = arr_f[12] * arr_f[10] * arr_f[7] - arr_f[8] * arr_f[14] * arr_f[7] - arr_f[12] * arr_f[6] * arr_f[11] + arr_f[4] * arr_f[14] * arr_f[11] + arr_f[8] * arr_f[6] * arr_f[15] - arr_f[4] * arr_f[10] * arr_f[15];
        float f3 = arr_f[8] * arr_f[13] * arr_f[7] - arr_f[12] * arr_f[9] * arr_f[7] + arr_f[12] * arr_f[5] * arr_f[11] - arr_f[4] * arr_f[13] * arr_f[11] - arr_f[8] * arr_f[5] * arr_f[15] + arr_f[4] * arr_f[9] * arr_f[15];
        float f4 = arr_f[12] * arr_f[9] * arr_f[6] - arr_f[8] * arr_f[13] * arr_f[6] - arr_f[12] * arr_f[5] * arr_f[10] + arr_f[4] * arr_f[13] * arr_f[10] + arr_f[8] * arr_f[5] * arr_f[14] - arr_f[4] * arr_f[9] * arr_f[14];
        float f5 = arr_f[13] * arr_f[10] * arr_f[3] - arr_f[9] * arr_f[14] * arr_f[3] - arr_f[13] * arr_f[2] * arr_f[11] + arr_f[1] * arr_f[14] * arr_f[11] + arr_f[9] * arr_f[2] * arr_f[15] - arr_f[1] * arr_f[10] * arr_f[15];
        float f6 = arr_f[8] * arr_f[14] * arr_f[3] - arr_f[12] * arr_f[10] * arr_f[3] + arr_f[12] * arr_f[2] * arr_f[11] - arr_f[0] * arr_f[14] * arr_f[11] - arr_f[8] * arr_f[2] * arr_f[15] + arr_f[0] * arr_f[10] * arr_f[15];
        float f7 = arr_f[12] * arr_f[9] * arr_f[3] - arr_f[8] * arr_f[13] * arr_f[3] - arr_f[12] * arr_f[1] * arr_f[11] + arr_f[0] * arr_f[13] * arr_f[11] + arr_f[8] * arr_f[1] * arr_f[15] - arr_f[0] * arr_f[9] * arr_f[15];
        float f8 = arr_f[8] * arr_f[13] * arr_f[2] - arr_f[12] * arr_f[9] * arr_f[2] + arr_f[12] * arr_f[1] * arr_f[10] - arr_f[0] * arr_f[13] * arr_f[10] - arr_f[8] * arr_f[1] * arr_f[14] + arr_f[0] * arr_f[9] * arr_f[14];
        float f9 = arr_f[5] * arr_f[14] * arr_f[3] - arr_f[13] * arr_f[6] * arr_f[3] + arr_f[13] * arr_f[2] * arr_f[7] - arr_f[1] * arr_f[14] * arr_f[7] - arr_f[5] * arr_f[2] * arr_f[15] + arr_f[1] * arr_f[6] * arr_f[15];
        float f10 = arr_f[12] * arr_f[6] * arr_f[3] - arr_f[4] * arr_f[14] * arr_f[3] - arr_f[12] * arr_f[2] * arr_f[7] + arr_f[0] * arr_f[14] * arr_f[7] + arr_f[4] * arr_f[2] * arr_f[15] - arr_f[0] * arr_f[6] * arr_f[15];
        float f11 = arr_f[4] * arr_f[13] * arr_f[3] - arr_f[12] * arr_f[5] * arr_f[3] + arr_f[12] * arr_f[1] * arr_f[7] - arr_f[0] * arr_f[13] * arr_f[7] - arr_f[4] * arr_f[1] * arr_f[15] + arr_f[0] * arr_f[5] * arr_f[15];
        float f12 = arr_f[12] * arr_f[5] * arr_f[2] - arr_f[4] * arr_f[13] * arr_f[2] - arr_f[12] * arr_f[1] * arr_f[6] + arr_f[0] * arr_f[13] * arr_f[6] + arr_f[4] * arr_f[1] * arr_f[14] - arr_f[0] * arr_f[5] * arr_f[14];
        float f13 = arr_f[9] * arr_f[6] * arr_f[3] - arr_f[5] * arr_f[10] * arr_f[3] - arr_f[9] * arr_f[2] * arr_f[7] + arr_f[1] * arr_f[10] * arr_f[7] + arr_f[5] * arr_f[2] * arr_f[11] - arr_f[1] * arr_f[6] * arr_f[11];
        float f14 = arr_f[4] * arr_f[10] * arr_f[3] - arr_f[8] * arr_f[6] * arr_f[3] + arr_f[8] * arr_f[2] * arr_f[7] - arr_f[0] * arr_f[10] * arr_f[7] - arr_f[4] * arr_f[2] * arr_f[11] + arr_f[0] * arr_f[6] * arr_f[11];
        float f15 = arr_f[8] * arr_f[5] * arr_f[3] - arr_f[4] * arr_f[9] * arr_f[3] - arr_f[8] * arr_f[1] * arr_f[7] + arr_f[0] * arr_f[9] * arr_f[7] + arr_f[4] * arr_f[1] * arr_f[11] - arr_f[0] * arr_f[5] * arr_f[11];
        float f16 = arr_f[4] * arr_f[9] * arr_f[2] - arr_f[8] * arr_f[5] * arr_f[2] + arr_f[8] * arr_f[1] * arr_f[6] - arr_f[0] * arr_f[9] * arr_f[6] - arr_f[4] * arr_f[1] * arr_f[10] + arr_f[0] * arr_f[5] * arr_f[10];
        arr_f[0] = f1 * (1.0f / f);
        arr_f[1] = f5 * (1.0f / f);
        arr_f[2] = f9 * (1.0f / f);
        arr_f[3] = f13 * (1.0f / f);
        arr_f[4] = f2 * (1.0f / f);
        arr_f[5] = f6 * (1.0f / f);
        arr_f[6] = f10 * (1.0f / f);
        arr_f[7] = f14 * (1.0f / f);
        arr_f[8] = f3 * (1.0f / f);
        arr_f[9] = f7 * (1.0f / f);
        arr_f[10] = f11 * (1.0f / f);
        arr_f[11] = f15 * (1.0f / f);
        arr_f[12] = f4 * (1.0f / f);
        arr_f[13] = f8 * (1.0f / f);
        arr_f[14] = f12 * (1.0f / f);
        arr_f[15] = f16 * (1.0f / f);
        return true;
    }

    public Matrix4 inv() {
        float[] arr_f = this.val;
        float f = arr_f[3] * arr_f[6] * arr_f[9] * arr_f[12] - arr_f[2] * arr_f[7] * arr_f[9] * arr_f[12] - arr_f[3] * arr_f[5] * arr_f[10] * arr_f[12] + arr_f[1] * arr_f[7] * arr_f[10] * arr_f[12] + arr_f[2] * arr_f[5] * arr_f[11] * arr_f[12] - arr_f[1] * arr_f[6] * arr_f[11] * arr_f[12] - arr_f[3] * arr_f[6] * arr_f[8] * arr_f[13] + arr_f[2] * arr_f[7] * arr_f[8] * arr_f[13] + arr_f[3] * arr_f[4] * arr_f[10] * arr_f[13] - arr_f[0] * arr_f[7] * arr_f[10] * arr_f[13] - arr_f[2] * arr_f[4] * arr_f[11] * arr_f[13] + arr_f[0] * arr_f[6] * arr_f[11] * arr_f[13] + arr_f[3] * arr_f[5] * arr_f[8] * arr_f[14] - arr_f[1] * arr_f[7] * arr_f[8] * arr_f[14] - arr_f[3] * arr_f[4] * arr_f[9] * arr_f[14] + arr_f[0] * arr_f[7] * arr_f[9] * arr_f[14] + arr_f[1] * arr_f[4] * arr_f[11] * arr_f[14] - arr_f[0] * arr_f[5] * arr_f[11] * arr_f[14] - arr_f[2] * arr_f[5] * arr_f[8] * arr_f[15] + arr_f[1] * arr_f[6] * arr_f[8] * arr_f[15] + arr_f[2] * arr_f[4] * arr_f[9] * arr_f[15] - arr_f[0] * arr_f[6] * arr_f[9] * arr_f[15] - arr_f[1] * arr_f[4] * arr_f[10] * arr_f[15] + arr_f[0] * arr_f[5] * arr_f[10] * arr_f[15];
        if(f == 0.0f) {
            throw new RuntimeException("non-invertible matrix");
        }
        float f1 = arr_f[9] * arr_f[14] * arr_f[7] - arr_f[13] * arr_f[10] * arr_f[7] + arr_f[13] * arr_f[6] * arr_f[11] - arr_f[5] * arr_f[14] * arr_f[11] - arr_f[9] * arr_f[6] * arr_f[15] + arr_f[5] * arr_f[10] * arr_f[15];
        float f2 = arr_f[12] * arr_f[10] * arr_f[7] - arr_f[8] * arr_f[14] * arr_f[7] - arr_f[12] * arr_f[6] * arr_f[11] + arr_f[4] * arr_f[14] * arr_f[11] + arr_f[8] * arr_f[6] * arr_f[15] - arr_f[4] * arr_f[10] * arr_f[15];
        float f3 = arr_f[8] * arr_f[13] * arr_f[7] - arr_f[12] * arr_f[9] * arr_f[7] + arr_f[12] * arr_f[5] * arr_f[11] - arr_f[4] * arr_f[13] * arr_f[11] - arr_f[8] * arr_f[5] * arr_f[15] + arr_f[4] * arr_f[9] * arr_f[15];
        float f4 = arr_f[12] * arr_f[9] * arr_f[6] - arr_f[8] * arr_f[13] * arr_f[6] - arr_f[12] * arr_f[5] * arr_f[10] + arr_f[4] * arr_f[13] * arr_f[10] + arr_f[8] * arr_f[5] * arr_f[14] - arr_f[4] * arr_f[9] * arr_f[14];
        float f5 = arr_f[13] * arr_f[10] * arr_f[3] - arr_f[9] * arr_f[14] * arr_f[3] - arr_f[13] * arr_f[2] * arr_f[11] + arr_f[1] * arr_f[14] * arr_f[11] + arr_f[9] * arr_f[2] * arr_f[15] - arr_f[1] * arr_f[10] * arr_f[15];
        float f6 = arr_f[8] * arr_f[14] * arr_f[3] - arr_f[12] * arr_f[10] * arr_f[3] + arr_f[12] * arr_f[2] * arr_f[11] - arr_f[0] * arr_f[14] * arr_f[11] - arr_f[8] * arr_f[2] * arr_f[15] + arr_f[0] * arr_f[10] * arr_f[15];
        float f7 = arr_f[12] * arr_f[9] * arr_f[3] - arr_f[8] * arr_f[13] * arr_f[3] - arr_f[12] * arr_f[1] * arr_f[11] + arr_f[0] * arr_f[13] * arr_f[11] + arr_f[8] * arr_f[1] * arr_f[15] - arr_f[0] * arr_f[9] * arr_f[15];
        float f8 = arr_f[8] * arr_f[13] * arr_f[2] - arr_f[12] * arr_f[9] * arr_f[2] + arr_f[12] * arr_f[1] * arr_f[10] - arr_f[0] * arr_f[13] * arr_f[10] - arr_f[8] * arr_f[1] * arr_f[14] + arr_f[0] * arr_f[9] * arr_f[14];
        float f9 = arr_f[5] * arr_f[14] * arr_f[3] - arr_f[13] * arr_f[6] * arr_f[3] + arr_f[13] * arr_f[2] * arr_f[7] - arr_f[1] * arr_f[14] * arr_f[7] - arr_f[5] * arr_f[2] * arr_f[15] + arr_f[1] * arr_f[6] * arr_f[15];
        float f10 = arr_f[12] * arr_f[6] * arr_f[3] - arr_f[4] * arr_f[14] * arr_f[3] - arr_f[12] * arr_f[2] * arr_f[7] + arr_f[0] * arr_f[14] * arr_f[7] + arr_f[4] * arr_f[2] * arr_f[15] - arr_f[0] * arr_f[6] * arr_f[15];
        float f11 = arr_f[4] * arr_f[13] * arr_f[3] - arr_f[12] * arr_f[5] * arr_f[3] + arr_f[12] * arr_f[1] * arr_f[7] - arr_f[0] * arr_f[13] * arr_f[7] - arr_f[4] * arr_f[1] * arr_f[15] + arr_f[0] * arr_f[5] * arr_f[15];
        float f12 = arr_f[12] * arr_f[5] * arr_f[2] - arr_f[4] * arr_f[13] * arr_f[2] - arr_f[12] * arr_f[1] * arr_f[6] + arr_f[0] * arr_f[13] * arr_f[6] + arr_f[4] * arr_f[1] * arr_f[14] - arr_f[0] * arr_f[5] * arr_f[14];
        float f13 = arr_f[9] * arr_f[6] * arr_f[3] - arr_f[5] * arr_f[10] * arr_f[3] - arr_f[9] * arr_f[2] * arr_f[7] + arr_f[1] * arr_f[10] * arr_f[7] + arr_f[5] * arr_f[2] * arr_f[11] - arr_f[1] * arr_f[6] * arr_f[11];
        float f14 = arr_f[4] * arr_f[10] * arr_f[3] - arr_f[8] * arr_f[6] * arr_f[3] + arr_f[8] * arr_f[2] * arr_f[7] - arr_f[0] * arr_f[10] * arr_f[7] - arr_f[4] * arr_f[2] * arr_f[11] + arr_f[0] * arr_f[6] * arr_f[11];
        float f15 = arr_f[8] * arr_f[5] * arr_f[3] - arr_f[4] * arr_f[9] * arr_f[3] - arr_f[8] * arr_f[1] * arr_f[7] + arr_f[0] * arr_f[9] * arr_f[7] + arr_f[4] * arr_f[1] * arr_f[11] - arr_f[0] * arr_f[5] * arr_f[11];
        float f16 = arr_f[4] * arr_f[9] * arr_f[2] - arr_f[8] * arr_f[5] * arr_f[2] + arr_f[8] * arr_f[1] * arr_f[6] - arr_f[0] * arr_f[9] * arr_f[6] - arr_f[4] * arr_f[1] * arr_f[10] + arr_f[0] * arr_f[5] * arr_f[10];
        arr_f[0] = f1 * (1.0f / f);
        arr_f[1] = f5 * (1.0f / f);
        arr_f[2] = f9 * (1.0f / f);
        arr_f[3] = f13 * (1.0f / f);
        arr_f[4] = f2 * (1.0f / f);
        arr_f[5] = f6 * (1.0f / f);
        arr_f[6] = f10 * (1.0f / f);
        arr_f[7] = f14 * (1.0f / f);
        arr_f[8] = f3 * (1.0f / f);
        arr_f[9] = f7 * (1.0f / f);
        arr_f[10] = f11 * (1.0f / f);
        arr_f[11] = f15 * (1.0f / f);
        arr_f[12] = f4 * (1.0f / f);
        arr_f[13] = f8 * (1.0f / f);
        arr_f[14] = f12 * (1.0f / f);
        arr_f[15] = f16 * (1.0f / f);
        return this;
    }

    public Matrix4 lerp(Matrix4 matrix40, float f) {
        for(int v = 0; v < 16; ++v) {
            this.val[v] = this.val[v] * (1.0f - f) + matrix40.val[v] * f;
        }
        return this;
    }

    public static void mul(float[] arr_f, float[] arr_f1) {
        float f = arr_f[0] * arr_f1[0] + arr_f[4] * arr_f1[1] + arr_f[8] * arr_f1[2] + arr_f[12] * arr_f1[3];
        float f1 = arr_f[0] * arr_f1[4] + arr_f[4] * arr_f1[5] + arr_f[8] * arr_f1[6] + arr_f[12] * arr_f1[7];
        float f2 = arr_f[0] * arr_f1[8] + arr_f[4] * arr_f1[9] + arr_f[8] * arr_f1[10] + arr_f[12] * arr_f1[11];
        float f3 = arr_f[0] * arr_f1[12] + arr_f[4] * arr_f1[13] + arr_f[8] * arr_f1[14] + arr_f[12] * arr_f1[15];
        float f4 = arr_f[1] * arr_f1[0] + arr_f[5] * arr_f1[1] + arr_f[9] * arr_f1[2] + arr_f[13] * arr_f1[3];
        float f5 = arr_f[1] * arr_f1[4] + arr_f[5] * arr_f1[5] + arr_f[9] * arr_f1[6] + arr_f[13] * arr_f1[7];
        float f6 = arr_f[1] * arr_f1[8] + arr_f[5] * arr_f1[9] + arr_f[9] * arr_f1[10] + arr_f[13] * arr_f1[11];
        float f7 = arr_f[1] * arr_f1[12] + arr_f[5] * arr_f1[13] + arr_f[9] * arr_f1[14] + arr_f[13] * arr_f1[15];
        float f8 = arr_f[2] * arr_f1[0] + arr_f[6] * arr_f1[1] + arr_f[10] * arr_f1[2] + arr_f[14] * arr_f1[3];
        float f9 = arr_f[2] * arr_f1[4] + arr_f[6] * arr_f1[5] + arr_f[10] * arr_f1[6] + arr_f[14] * arr_f1[7];
        float f10 = arr_f[2] * arr_f1[8] + arr_f[6] * arr_f1[9] + arr_f[10] * arr_f1[10] + arr_f[14] * arr_f1[11];
        float f11 = arr_f[2] * arr_f1[12] + arr_f[6] * arr_f1[13] + arr_f[10] * arr_f1[14] + arr_f[14] * arr_f1[15];
        float f12 = arr_f[3] * arr_f1[0] + arr_f[7] * arr_f1[1] + arr_f[11] * arr_f1[2] + arr_f[15] * arr_f1[3];
        float f13 = arr_f[3] * arr_f1[4] + arr_f[7] * arr_f1[5] + arr_f[11] * arr_f1[6] + arr_f[15] * arr_f1[7];
        float f14 = arr_f[3] * arr_f1[8] + arr_f[7] * arr_f1[9] + arr_f[11] * arr_f1[10] + arr_f[15] * arr_f1[11];
        float f15 = arr_f[3] * arr_f1[12] + arr_f[7] * arr_f1[13] + arr_f[11] * arr_f1[14] + arr_f[15] * arr_f1[15];
        arr_f[0] = f;
        arr_f[1] = f4;
        arr_f[2] = f8;
        arr_f[3] = f12;
        arr_f[4] = f1;
        arr_f[5] = f5;
        arr_f[6] = f9;
        arr_f[7] = f13;
        arr_f[8] = f2;
        arr_f[9] = f6;
        arr_f[10] = f10;
        arr_f[11] = f14;
        arr_f[12] = f3;
        arr_f[13] = f7;
        arr_f[14] = f11;
        arr_f[15] = f15;
    }

    public Matrix4 mul(Matrix4 matrix40) {
        Matrix4.mul(this.val, matrix40.val);
        return this;
    }

    public Matrix4 mulLeft(Matrix4 matrix40) {
        Matrix4.tmpMat.set(matrix40);
        Matrix4.mul(Matrix4.tmpMat.val, this.val);
        return this.set(Matrix4.tmpMat);
    }

    public static void mulVec(float[] arr_f, float[] arr_f1) {
        float f = arr_f1[0] * arr_f[0] + arr_f1[1] * arr_f[4] + arr_f1[2] * arr_f[8] + arr_f[12];
        float f1 = arr_f1[0] * arr_f[1] + arr_f1[1] * arr_f[5] + arr_f1[2] * arr_f[9] + arr_f[13];
        float f2 = arr_f1[0] * arr_f[2] + arr_f1[1] * arr_f[6] + arr_f1[2] * arr_f[10] + arr_f[14];
        arr_f1[0] = f;
        arr_f1[1] = f1;
        arr_f1[2] = f2;
    }

    public static native void mulVec(float[] arg0, float[] arg1, int arg2, int arg3, int arg4) {
    }

    public static void prj(float[] arr_f, float[] arr_f1) {
        float f = 1.0f / (arr_f1[0] * arr_f[3] + arr_f1[1] * arr_f[7] + arr_f1[2] * arr_f[11] + arr_f[15]);
        float f1 = (arr_f1[0] * arr_f[0] + arr_f1[1] * arr_f[4] + arr_f1[2] * arr_f[8] + arr_f[12]) * f;
        float f2 = (arr_f1[0] * arr_f[1] + arr_f1[1] * arr_f[5] + arr_f1[2] * arr_f[9] + arr_f[13]) * f;
        float f3 = (arr_f1[0] * arr_f[2] + arr_f1[1] * arr_f[6] + arr_f1[2] * arr_f[10] + arr_f[14]) * f;
        arr_f1[0] = f1;
        arr_f1[1] = f2;
        arr_f1[2] = f3;
    }

    public static native void prj(float[] arg0, float[] arg1, int arg2, int arg3, int arg4) {
    }

    public static void rot(float[] arr_f, float[] arr_f1) {
        float f = arr_f1[0] * arr_f[0] + arr_f1[1] * arr_f[4] + arr_f1[2] * arr_f[8];
        float f1 = arr_f1[0] * arr_f[1] + arr_f1[1] * arr_f[5] + arr_f1[2] * arr_f[9];
        float f2 = arr_f1[0] * arr_f[2] + arr_f1[1] * arr_f[6] + arr_f1[2] * arr_f[10];
        arr_f1[0] = f;
        arr_f1[1] = f1;
        arr_f1[2] = f2;
    }

    public static native void rot(float[] arg0, float[] arg1, int arg2, int arg3, int arg4) {
    }

    public Matrix4 rotate(float f, float f1, float f2, float f3) {
        if(f3 == 0.0f) {
            return this;
        }
        Matrix4.quat.setFromAxis(f, f1, f2, f3);
        return this.rotate(Matrix4.quat);
    }

    public Matrix4 rotate(Quaternion quaternion0) {
        float f = quaternion0.x * quaternion0.x;
        float f1 = quaternion0.x * quaternion0.y;
        float f2 = quaternion0.x * quaternion0.z;
        float f3 = quaternion0.x * quaternion0.w;
        float f4 = quaternion0.y * quaternion0.y;
        float f5 = quaternion0.y * quaternion0.z;
        float f6 = quaternion0.y * quaternion0.w;
        float f7 = quaternion0.z * quaternion0.z;
        float f8 = quaternion0.z * quaternion0.w;
        float f9 = 1.0f - (f4 + f7) * 2.0f;
        float f10 = (f1 - f8) * 2.0f;
        float f11 = (f2 + f6) * 2.0f;
        float f12 = (f1 + f8) * 2.0f;
        float f13 = 1.0f - (f7 + f) * 2.0f;
        float f14 = (f5 - f3) * 2.0f;
        float f15 = (f2 - f6) * 2.0f;
        float f16 = (f5 + f3) * 2.0f;
        float f17 = 1.0f - (f + f4) * 2.0f;
        float[] arr_f = this.val;
        float f18 = arr_f[0] * f9 + arr_f[4] * f12 + arr_f[8] * f15;
        float f19 = arr_f[0] * f10 + arr_f[4] * f13 + arr_f[8] * f16;
        float f20 = arr_f[0] * f11 + arr_f[4] * f14 + arr_f[8] * f17;
        float f21 = arr_f[1] * f9 + arr_f[5] * f12 + arr_f[9] * f15;
        float f22 = arr_f[1] * f10 + arr_f[5] * f13 + arr_f[9] * f16;
        float f23 = arr_f[1] * f11 + arr_f[5] * f14 + arr_f[9] * f17;
        float f24 = arr_f[2] * f9 + arr_f[6] * f12 + arr_f[10] * f15;
        float f25 = arr_f[2] * f10 + arr_f[6] * f13 + arr_f[10] * f16;
        float f26 = arr_f[2] * f11 + arr_f[6] * f14 + arr_f[10] * f17;
        float f27 = arr_f[3] * f9 + arr_f[7] * f12 + arr_f[11] * f15;
        float f28 = arr_f[3] * f10 + arr_f[7] * f13 + arr_f[11] * f16;
        float f29 = arr_f[3] * f11 + arr_f[7] * f14 + arr_f[11] * f17;
        arr_f[0] = f18;
        arr_f[1] = f21;
        arr_f[2] = f24;
        arr_f[3] = f27;
        arr_f[4] = f19;
        arr_f[5] = f22;
        arr_f[6] = f25;
        arr_f[7] = f28;
        arr_f[8] = f20;
        arr_f[9] = f23;
        arr_f[10] = f26;
        arr_f[11] = f29;
        return this;
    }

    public Matrix4 rotate(Vector3 vector30, float f) {
        if(f == 0.0f) {
            return this;
        }
        Matrix4.quat.set(vector30, f);
        return this.rotate(Matrix4.quat);
    }

    public Matrix4 rotate(Vector3 vector30, Vector3 vector31) {
        return this.rotate(Matrix4.quat.setFromCross(vector30, vector31));
    }

    public Matrix4 rotateRad(float f, float f1, float f2, float f3) {
        if(f3 == 0.0f) {
            return this;
        }
        Matrix4.quat.setFromAxisRad(f, f1, f2, f3);
        return this.rotate(Matrix4.quat);
    }

    public Matrix4 rotateRad(Vector3 vector30, float f) {
        if(f == 0.0f) {
            return this;
        }
        Matrix4.quat.setFromAxisRad(vector30, f);
        return this.rotate(Matrix4.quat);
    }

    public Matrix4 rotateTowardDirection(Vector3 vector30, Vector3 vector31) {
        Matrix4.l_vez.set(vector30).nor();
        Matrix4.l_vex.set(vector30).crs(vector31).nor();
        Matrix4.l_vey.set(Matrix4.l_vex).crs(Matrix4.l_vez).nor();
        float f = this.val[0] * Matrix4.l_vex.x + this.val[4] * Matrix4.l_vex.y + this.val[8] * Matrix4.l_vex.z;
        float f1 = this.val[0] * Matrix4.l_vey.x + this.val[4] * Matrix4.l_vey.y + this.val[8] * Matrix4.l_vey.z;
        float f2 = this.val[0] * -Matrix4.l_vez.x + this.val[4] * -Matrix4.l_vez.y + this.val[8] * -Matrix4.l_vez.z;
        float f3 = this.val[1] * Matrix4.l_vex.x + this.val[5] * Matrix4.l_vex.y + this.val[9] * Matrix4.l_vex.z;
        float f4 = this.val[1] * Matrix4.l_vey.x + this.val[5] * Matrix4.l_vey.y + this.val[9] * Matrix4.l_vey.z;
        float f5 = this.val[1] * -Matrix4.l_vez.x + this.val[5] * -Matrix4.l_vez.y + this.val[9] * -Matrix4.l_vez.z;
        float f6 = this.val[2] * Matrix4.l_vex.x + this.val[6] * Matrix4.l_vex.y + this.val[10] * Matrix4.l_vex.z;
        float f7 = this.val[2] * Matrix4.l_vey.x + this.val[6] * Matrix4.l_vey.y + this.val[10] * Matrix4.l_vey.z;
        float f8 = this.val[2] * -Matrix4.l_vez.x + this.val[6] * -Matrix4.l_vez.y + this.val[10] * -Matrix4.l_vez.z;
        float f9 = this.val[3] * Matrix4.l_vex.x + this.val[7] * Matrix4.l_vex.y + this.val[11] * Matrix4.l_vex.z;
        float f10 = this.val[3] * Matrix4.l_vey.x + this.val[7] * Matrix4.l_vey.y + this.val[11] * Matrix4.l_vey.z;
        float f11 = this.val[3] * -Matrix4.l_vez.x + this.val[7] * -Matrix4.l_vez.y + this.val[11] * -Matrix4.l_vez.z;
        this.val[0] = f;
        this.val[1] = f3;
        this.val[2] = f6;
        this.val[3] = f9;
        this.val[4] = f1;
        this.val[5] = f4;
        this.val[6] = f7;
        this.val[7] = f10;
        this.val[8] = f2;
        this.val[9] = f5;
        this.val[10] = f8;
        this.val[11] = f11;
        return this;
    }

    public Matrix4 rotateTowardTarget(Vector3 vector30, Vector3 vector31) {
        Matrix4.tmpVec.set(vector30.x - this.val[12], vector30.y - this.val[13], vector30.z - this.val[14]);
        return this.rotateTowardDirection(Matrix4.tmpVec, vector31);
    }

    public Matrix4 scale(float f, float f1, float f2) {
        this.val[0] *= f;
        this.val[4] *= f1;
        this.val[8] *= f2;
        this.val[1] *= f;
        this.val[5] *= f1;
        this.val[9] *= f2;
        this.val[2] *= f;
        this.val[6] *= f1;
        this.val[10] *= f2;
        this.val[3] *= f;
        this.val[7] *= f1;
        this.val[11] *= f2;
        return this;
    }

    public Matrix4 scl(float f) {
        this.val[0] *= f;
        this.val[5] *= f;
        this.val[10] *= f;
        return this;
    }

    public Matrix4 scl(float f, float f1, float f2) {
        this.val[0] *= f;
        this.val[5] *= f1;
        this.val[10] *= f2;
        return this;
    }

    public Matrix4 scl(Vector3 vector30) {
        this.val[0] *= vector30.x;
        this.val[5] *= vector30.y;
        this.val[10] *= vector30.z;
        return this;
    }

    public Matrix4 set(float f, float f1, float f2, float f3) {
        return this.set(0.0f, 0.0f, 0.0f, f, f1, f2, f3);
    }

    public Matrix4 set(float f, float f1, float f2, float f3, float f4, float f5, float f6) {
        float f7 = f6 * (f3 * 2.0f);
        float f8 = f6 * (f4 * 2.0f);
        float f9 = f6 * (2.0f * f5);
        float f10 = f3 * 2.0f * f3;
        float f11 = f3 * (f4 * 2.0f);
        float f12 = f3 * (2.0f * f5);
        float f13 = f4 * 2.0f * f4;
        float f14 = f4 * (2.0f * f5);
        float f15 = 2.0f * f5 * f5;
        this.val[0] = 1.0f - (f13 + f15);
        this.val[4] = f11 - f9;
        this.val[8] = f12 + f8;
        this.val[12] = f;
        this.val[1] = f11 + f9;
        this.val[5] = 1.0f - (f15 + f10);
        this.val[9] = f14 - f7;
        this.val[13] = f1;
        this.val[2] = f12 - f8;
        this.val[6] = f14 + f7;
        this.val[10] = 1.0f - (f10 + f13);
        this.val[14] = f2;
        this.val[3] = 0.0f;
        this.val[7] = 0.0f;
        this.val[11] = 0.0f;
        this.val[15] = 1.0f;
        return this;
    }

    public Matrix4 set(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        float f10 = f6 * (f3 * 2.0f);
        float f11 = f6 * (f4 * 2.0f);
        float f12 = f6 * (2.0f * f5);
        float f13 = f3 * 2.0f * f3;
        float f14 = f3 * (f4 * 2.0f);
        float f15 = f3 * (2.0f * f5);
        float f16 = f4 * 2.0f * f4;
        float f17 = f4 * (2.0f * f5);
        float f18 = 2.0f * f5 * f5;
        this.val[0] = (1.0f - (f16 + f18)) * f7;
        this.val[4] = (f14 - f12) * f8;
        this.val[8] = (f15 + f11) * f9;
        this.val[12] = f;
        this.val[1] = f7 * (f14 + f12);
        this.val[5] = (1.0f - (f18 + f13)) * f8;
        this.val[9] = (f17 - f10) * f9;
        this.val[13] = f1;
        this.val[2] = f7 * (f15 - f11);
        this.val[6] = f8 * (f17 + f10);
        this.val[10] = (1.0f - (f13 + f16)) * f9;
        this.val[14] = f2;
        this.val[3] = 0.0f;
        this.val[7] = 0.0f;
        this.val[11] = 0.0f;
        this.val[15] = 1.0f;
        return this;
    }

    public Matrix4 set(Affine2 affine20) {
        this.val[0] = affine20.m00;
        this.val[1] = affine20.m10;
        this.val[2] = 0.0f;
        this.val[3] = 0.0f;
        this.val[4] = affine20.m01;
        this.val[5] = affine20.m11;
        this.val[6] = 0.0f;
        this.val[7] = 0.0f;
        this.val[8] = 0.0f;
        this.val[9] = 0.0f;
        this.val[10] = 1.0f;
        this.val[11] = 0.0f;
        this.val[12] = affine20.m02;
        this.val[13] = affine20.m12;
        this.val[14] = 0.0f;
        this.val[15] = 1.0f;
        return this;
    }

    public Matrix4 set(Matrix3 matrix30) {
        this.val[0] = matrix30.val[0];
        this.val[1] = matrix30.val[1];
        this.val[2] = matrix30.val[2];
        this.val[3] = 0.0f;
        this.val[4] = matrix30.val[3];
        this.val[5] = matrix30.val[4];
        this.val[6] = matrix30.val[5];
        this.val[7] = 0.0f;
        this.val[8] = 0.0f;
        this.val[9] = 0.0f;
        this.val[10] = 1.0f;
        this.val[11] = 0.0f;
        this.val[12] = matrix30.val[6];
        this.val[13] = matrix30.val[7];
        this.val[14] = 0.0f;
        this.val[15] = matrix30.val[8];
        return this;
    }

    public Matrix4 set(Matrix4 matrix40) {
        return this.set(matrix40.val);
    }

    public Matrix4 set(Quaternion quaternion0) {
        return this.set(quaternion0.x, quaternion0.y, quaternion0.z, quaternion0.w);
    }

    public Matrix4 set(Vector3 vector30, Quaternion quaternion0) {
        return this.set(vector30.x, vector30.y, vector30.z, quaternion0.x, quaternion0.y, quaternion0.z, quaternion0.w);
    }

    public Matrix4 set(Vector3 vector30, Quaternion quaternion0, Vector3 vector31) {
        return this.set(vector30.x, vector30.y, vector30.z, quaternion0.x, quaternion0.y, quaternion0.z, quaternion0.w, vector31.x, vector31.y, vector31.z);
    }

    public Matrix4 set(Vector3 vector30, Vector3 vector31, Vector3 vector32, Vector3 vector33) {
        this.val[0] = vector30.x;
        this.val[4] = vector30.y;
        this.val[8] = vector30.z;
        this.val[1] = vector31.x;
        this.val[5] = vector31.y;
        this.val[9] = vector31.z;
        this.val[2] = vector32.x;
        this.val[6] = vector32.y;
        this.val[10] = vector32.z;
        this.val[12] = vector33.x;
        this.val[13] = vector33.y;
        this.val[14] = vector33.z;
        this.val[3] = 0.0f;
        this.val[7] = 0.0f;
        this.val[11] = 0.0f;
        this.val[15] = 1.0f;
        return this;
    }

    public Matrix4 set(float[] arr_f) {
        System.arraycopy(arr_f, 0, this.val, 0, this.val.length);
        return this;
    }

    public Matrix4 setAsAffine(Affine2 affine20) {
        this.val[0] = affine20.m00;
        this.val[1] = affine20.m10;
        this.val[4] = affine20.m01;
        this.val[5] = affine20.m11;
        this.val[12] = affine20.m02;
        this.val[13] = affine20.m12;
        return this;
    }

    public Matrix4 setAsAffine(Matrix4 matrix40) {
        this.val[0] = matrix40.val[0];
        this.val[1] = matrix40.val[1];
        this.val[4] = matrix40.val[4];
        this.val[5] = matrix40.val[5];
        this.val[12] = matrix40.val[12];
        this.val[13] = matrix40.val[13];
        return this;
    }

    public Matrix4 setFromEulerAngles(float f, float f1, float f2) {
        Matrix4.quat.setEulerAngles(f, f1, f2);
        return this.set(Matrix4.quat);
    }

    public Matrix4 setFromEulerAnglesRad(float f, float f1, float f2) {
        Matrix4.quat.setEulerAnglesRad(f, f1, f2);
        return this.set(Matrix4.quat);
    }

    public Matrix4 setToLookAt(Vector3 vector30, Vector3 vector31) {
        Matrix4.l_vez.set(vector30).nor();
        Matrix4.l_vex.set(vector30).crs(vector31).nor();
        Matrix4.l_vey.set(Matrix4.l_vex).crs(Matrix4.l_vez).nor();
        this.idt();
        this.val[0] = Matrix4.l_vex.x;
        this.val[4] = Matrix4.l_vex.y;
        this.val[8] = Matrix4.l_vex.z;
        this.val[1] = Matrix4.l_vey.x;
        this.val[5] = Matrix4.l_vey.y;
        this.val[9] = Matrix4.l_vey.z;
        this.val[2] = -Matrix4.l_vez.x;
        this.val[6] = -Matrix4.l_vez.y;
        this.val[10] = -Matrix4.l_vez.z;
        return this;
    }

    public Matrix4 setToLookAt(Vector3 vector30, Vector3 vector31, Vector3 vector32) {
        Matrix4.tmpVec.set(vector31).sub(vector30);
        this.setToLookAt(Matrix4.tmpVec, vector32);
        this.mul(Matrix4.tmpMat.setToTranslation(-vector30.x, -vector30.y, -vector30.z));
        return this;
    }

    public Matrix4 setToOrtho(float f, float f1, float f2, float f3, float f4, float f5) {
        float f6 = f1 - f;
        float f7 = f3 - f2;
        float f8 = f5 - f4;
        this.val[0] = 2.0f / f6;
        this.val[1] = 0.0f;
        this.val[2] = 0.0f;
        this.val[3] = 0.0f;
        this.val[4] = 0.0f;
        this.val[5] = 2.0f / f7;
        this.val[6] = 0.0f;
        this.val[7] = 0.0f;
        this.val[8] = 0.0f;
        this.val[9] = 0.0f;
        this.val[10] = -2.0f / f8;
        this.val[11] = 0.0f;
        this.val[12] = -(f1 + f) / f6;
        this.val[13] = -(f3 + f2) / f7;
        this.val[14] = -(f5 + f4) / f8;
        this.val[15] = 1.0f;
        return this;
    }

    public Matrix4 setToOrtho2D(float f, float f1, float f2, float f3) {
        this.setToOrtho(f, f + f2, f1, f1 + f3, 0.0f, 1.0f);
        return this;
    }

    public Matrix4 setToOrtho2D(float f, float f1, float f2, float f3, float f4, float f5) {
        this.setToOrtho(f, f + f2, f1, f1 + f3, f4, f5);
        return this;
    }

    public Matrix4 setToProjection(float f, float f1, float f2, float f3) {
        this.idt();
        double f4 = Math.tan(((double)f2) * 0.017453 / 2.0);
        float f5 = f - f1;
        this.val[0] = ((float)(1.0 / f4)) / f3;
        this.val[1] = 0.0f;
        this.val[2] = 0.0f;
        this.val[3] = 0.0f;
        this.val[4] = 0.0f;
        this.val[5] = (float)(1.0 / f4);
        this.val[6] = 0.0f;
        this.val[7] = 0.0f;
        this.val[8] = 0.0f;
        this.val[9] = 0.0f;
        this.val[10] = (f1 + f) / f5;
        this.val[11] = -1.0f;
        this.val[12] = 0.0f;
        this.val[13] = 0.0f;
        this.val[14] = f1 * 2.0f * f / f5;
        this.val[15] = 0.0f;
        return this;
    }

    public Matrix4 setToProjection(float f, float f1, float f2, float f3, float f4, float f5) {
        float f6 = f1 - f;
        float f7 = f3 - f2;
        float f8 = f4 - f5;
        this.val[0] = f4 * 2.0f / f6;
        this.val[1] = 0.0f;
        this.val[2] = 0.0f;
        this.val[3] = 0.0f;
        this.val[4] = 0.0f;
        this.val[5] = f4 * 2.0f / f7;
        this.val[6] = 0.0f;
        this.val[7] = 0.0f;
        this.val[8] = (f1 + f) / f6;
        this.val[9] = (f3 + f2) / f7;
        this.val[10] = (f5 + f4) / f8;
        this.val[11] = -1.0f;
        this.val[12] = 0.0f;
        this.val[13] = 0.0f;
        this.val[14] = f5 * 2.0f * f4 / f8;
        this.val[15] = 0.0f;
        return this;
    }

    public Matrix4 setToRotation(float f, float f1, float f2, float f3) {
        if(f3 == 0.0f) {
            this.idt();
            return this;
        }
        return this.set(Matrix4.quat.setFromAxis(f, f1, f2, f3));
    }

    public Matrix4 setToRotation(float f, float f1, float f2, float f3, float f4, float f5) {
        return this.set(Matrix4.quat.setFromCross(f, f1, f2, f3, f4, f5));
    }

    public Matrix4 setToRotation(Vector3 vector30, float f) {
        if(f == 0.0f) {
            this.idt();
            return this;
        }
        return this.set(Matrix4.quat.set(vector30, f));
    }

    public Matrix4 setToRotation(Vector3 vector30, Vector3 vector31) {
        return this.set(Matrix4.quat.setFromCross(vector30, vector31));
    }

    public Matrix4 setToRotationRad(float f, float f1, float f2, float f3) {
        if(f3 == 0.0f) {
            this.idt();
            return this;
        }
        return this.set(Matrix4.quat.setFromAxisRad(f, f1, f2, f3));
    }

    public Matrix4 setToRotationRad(Vector3 vector30, float f) {
        if(f == 0.0f) {
            this.idt();
            return this;
        }
        return this.set(Matrix4.quat.setFromAxisRad(vector30, f));
    }

    public Matrix4 setToScaling(float f, float f1, float f2) {
        this.idt();
        this.val[0] = f;
        this.val[5] = f1;
        this.val[10] = f2;
        return this;
    }

    public Matrix4 setToScaling(Vector3 vector30) {
        this.idt();
        this.val[0] = vector30.x;
        this.val[5] = vector30.y;
        this.val[10] = vector30.z;
        return this;
    }

    public Matrix4 setToTranslation(float f, float f1, float f2) {
        this.idt();
        this.val[12] = f;
        this.val[13] = f1;
        this.val[14] = f2;
        return this;
    }

    public Matrix4 setToTranslation(Vector3 vector30) {
        this.idt();
        this.val[12] = vector30.x;
        this.val[13] = vector30.y;
        this.val[14] = vector30.z;
        return this;
    }

    public Matrix4 setToTranslationAndScaling(float f, float f1, float f2, float f3, float f4, float f5) {
        this.idt();
        this.val[12] = f;
        this.val[13] = f1;
        this.val[14] = f2;
        this.val[0] = f3;
        this.val[5] = f4;
        this.val[10] = f5;
        return this;
    }

    public Matrix4 setToTranslationAndScaling(Vector3 vector30, Vector3 vector31) {
        this.idt();
        this.val[12] = vector30.x;
        this.val[13] = vector30.y;
        this.val[14] = vector30.z;
        this.val[0] = vector31.x;
        this.val[5] = vector31.y;
        this.val[10] = vector31.z;
        return this;
    }

    public Matrix4 setToWorld(Vector3 vector30, Vector3 vector31, Vector3 vector32) {
        Matrix4.tmpForward.set(vector31).nor();
        Matrix4.right.set(Matrix4.tmpForward).crs(vector32).nor();
        Matrix4.tmpUp.set(Matrix4.right).crs(Matrix4.tmpForward).nor();
        Vector3 vector33 = Matrix4.tmpForward.scl(-1.0f);
        this.set(Matrix4.right, Matrix4.tmpUp, vector33, vector30);
        return this;
    }

    public Matrix4 setTranslation(float f, float f1, float f2) {
        this.val[12] = f;
        this.val[13] = f1;
        this.val[14] = f2;
        return this;
    }

    public Matrix4 setTranslation(Vector3 vector30) {
        this.val[12] = vector30.x;
        this.val[13] = vector30.y;
        this.val[14] = vector30.z;
        return this;
    }

    public Matrix4 toNormalMatrix() {
        this.val[12] = 0.0f;
        this.val[13] = 0.0f;
        this.val[14] = 0.0f;
        return this.inv().tra();
    }

    @Override
    public String toString() {
        return "[" + this.val[0] + "|" + this.val[4] + "|" + this.val[8] + "|" + this.val[12] + "]\n[" + this.val[1] + "|" + this.val[5] + "|" + this.val[9] + "|" + this.val[13] + "]\n[" + this.val[2] + "|" + this.val[6] + "|" + this.val[10] + "|" + this.val[14] + "]\n[" + this.val[3] + "|" + this.val[7] + "|" + this.val[11] + "|" + this.val[15] + "]\n";
    }

    public Matrix4 tra() {
        float f = this.val[4];
        float f1 = this.val[8];
        float f2 = this.val[12];
        float f3 = this.val[9];
        float f4 = this.val[13];
        float f5 = this.val[14];
        this.val[4] = this.val[1];
        this.val[8] = this.val[2];
        this.val[12] = this.val[3];
        this.val[1] = f;
        this.val[9] = this.val[6];
        this.val[13] = this.val[7];
        this.val[2] = f1;
        this.val[6] = f3;
        this.val[14] = this.val[11];
        this.val[3] = f2;
        this.val[7] = f4;
        this.val[11] = f5;
        return this;
    }

    public Matrix4 translate(float f, float f1, float f2) {
        this.val[12] += this.val[0] * f + this.val[4] * f1 + this.val[8] * f2;
        this.val[13] += this.val[1] * f + this.val[5] * f1 + this.val[9] * f2;
        this.val[14] += this.val[2] * f + this.val[6] * f1 + this.val[10] * f2;
        this.val[15] += this.val[3] * f + this.val[7] * f1 + this.val[11] * f2;
        return this;
    }

    public Matrix4 translate(Vector3 vector30) {
        return this.translate(vector30.x, vector30.y, vector30.z);
    }

    public Matrix4 trn(float f, float f1, float f2) {
        this.val[12] += f;
        this.val[13] += f1;
        this.val[14] += f2;
        return this;
    }

    public Matrix4 trn(Vector3 vector30) {
        this.val[12] += vector30.x;
        this.val[13] += vector30.y;
        this.val[14] += vector30.z;
        return this;
    }
}

