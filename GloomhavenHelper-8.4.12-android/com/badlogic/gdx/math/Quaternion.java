package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.NumberUtils;
import java.io.Serializable;

public class Quaternion implements Serializable {
    private static final long serialVersionUID = 0x95AB8CE18752A1F0L;
    private static Quaternion tmp1;
    private static Quaternion tmp2;
    public float w;
    public float x;
    public float y;
    public float z;

    static {
        Quaternion.tmp1 = new Quaternion(0.0f, 0.0f, 0.0f, 0.0f);
        Quaternion.tmp2 = new Quaternion(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public Quaternion() {
        this.idt();
    }

    public Quaternion(float f, float f1, float f2, float f3) {
        this.set(f, f1, f2, f3);
    }

    public Quaternion(Quaternion quaternion0) {
        this.set(quaternion0);
    }

    public Quaternion(Vector3 vector30, float f) {
        this.set(vector30, f);
    }

    public Quaternion add(float f, float f1, float f2, float f3) {
        this.x += f;
        this.y += f1;
        this.z += f2;
        this.w += f3;
        return this;
    }

    public Quaternion add(Quaternion quaternion0) {
        this.x += quaternion0.x;
        this.y += quaternion0.y;
        this.z += quaternion0.z;
        this.w += quaternion0.w;
        return this;
    }

    public Quaternion conjugate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        return this;
    }

    public Quaternion cpy() {
        return new Quaternion(this);
    }

    public static final float dot(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7) {
        return f * f4 + f1 * f5 + f2 * f6 + f3 * f7;
    }

    public float dot(float f, float f1, float f2, float f3) {
        return this.x * f + this.y * f1 + this.z * f2 + this.w * f3;
    }

    public float dot(Quaternion quaternion0) {
        return this.x * quaternion0.x + this.y * quaternion0.y + this.z * quaternion0.z + this.w * quaternion0.w;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof Quaternion ? NumberUtils.floatToRawIntBits(this.w) == NumberUtils.floatToRawIntBits(((Quaternion)object0).w) && NumberUtils.floatToRawIntBits(this.x) == NumberUtils.floatToRawIntBits(((Quaternion)object0).x) && NumberUtils.floatToRawIntBits(this.y) == NumberUtils.floatToRawIntBits(((Quaternion)object0).y) && NumberUtils.floatToRawIntBits(this.z) == NumberUtils.floatToRawIntBits(((Quaternion)object0).z) : false;
    }

    public Quaternion exp(float f) {
        float f1 = this.len();
        float f2 = (float)Math.pow(f1, f);
        float f3 = (float)Math.acos(this.w / f1);
        float f4 = ((double)Math.abs(f3)) < 0.001 ? f2 * f / f1 : ((float)(((double)f2) * Math.sin(f * f3) / (((double)f1) * Math.sin(f3))));
        this.w = (float)(((double)f2) * Math.cos(f * f3));
        this.x *= f4;
        this.y *= f4;
        this.z *= f4;
        this.nor();
        return this;
    }

    public float getAngle() {
        return this.getAngleRad() * 57.295776f;
    }

    public float getAngleAround(float f, float f1, float f2) {
        return this.getAngleAroundRad(f, f1, f2) * 57.295776f;
    }

    public float getAngleAround(Vector3 vector30) {
        return this.getAngleAround(vector30.x, vector30.y, vector30.z);
    }

    public float getAngleAroundRad(float f, float f1, float f2) {
        float f3 = this.x * f + this.y * f1 + this.z * f2;
        float f4 = f * f3 * (f * f3) + f1 * f3 * (f1 * f3) + f2 * f3 * (f2 * f3) + this.w * this.w;
        if(!MathUtils.isZero(f4)) {
            return f3 < 0.0f ? ((float)(Math.acos(MathUtils.clamp(((float)(((double)(-this.w)) / Math.sqrt(f4))), -1.0f, 1.0f)) * 2.0)) : ((float)(Math.acos(MathUtils.clamp(((float)(((double)this.w) / Math.sqrt(f4))), -1.0f, 1.0f)) * 2.0));
        }
        return 0.0f;
    }

    public float getAngleAroundRad(Vector3 vector30) {
        return this.getAngleAroundRad(vector30.x, vector30.y, vector30.z);
    }

    public float getAngleRad() {
        return (float)(Math.acos((this.w > 1.0f ? this.w / this.len() : this.w)) * 2.0);
    }

    public float getAxisAngle(Vector3 vector30) {
        return this.getAxisAngleRad(vector30) * 57.295776f;
    }

    public float getAxisAngleRad(Vector3 vector30) {
        if(this.w > 1.0f) {
            this.nor();
        }
        double f = Math.acos(this.w);
        double f1 = Math.sqrt(1.0f - this.w * this.w);
        if(f1 < 0.000001) {
            vector30.x = this.x;
            vector30.y = this.y;
            vector30.z = this.z;
            return (float)(f * 2.0);
        }
        vector30.x = (float)(((double)this.x) / f1);
        vector30.y = (float)(((double)this.y) / f1);
        vector30.z = (float)(((double)this.z) / f1);
        return (float)(f * 2.0);
    }

    public int getGimbalPole() {
        float f = this.y * this.x + this.z * this.w;
        if(f > 0.499f) {
            return 1;
        }
        return f < -0.499f ? -1 : 0;
    }

    public float getPitch() {
        return this.getPitchRad() * 57.295776f;
    }

    public float getPitchRad() {
        int v = this.getGimbalPole();
        return v == 0 ? ((float)Math.asin(MathUtils.clamp((this.w * this.x - this.z * this.y) * 2.0f, -1.0f, 1.0f))) : ((float)v) * 1.570796f;
    }

    public float getRoll() {
        return this.getRollRad() * 57.295776f;
    }

    public float getRollRad() {
        int v = this.getGimbalPole();
        return v == 0 ? MathUtils.atan2((this.w * this.z + this.y * this.x) * 2.0f, 1.0f - (this.x * this.x + this.z * this.z) * 2.0f) : ((float)v) * 2.0f * MathUtils.atan2(this.y, this.w);
    }

    public void getSwingTwist(float f, float f1, float f2, Quaternion quaternion0, Quaternion quaternion1) {
        float f3 = this.x * f + this.y * f1 + this.z * f2;
        quaternion1.set(f * f3, f1 * f3, f2 * f3, this.w).nor();
        if(f3 < 0.0f) {
            quaternion1.mul(-1.0f);
        }
        quaternion0.set(quaternion1).conjugate().mulLeft(this);
    }

    public void getSwingTwist(Vector3 vector30, Quaternion quaternion0, Quaternion quaternion1) {
        this.getSwingTwist(vector30.x, vector30.y, vector30.z, quaternion0, quaternion1);
    }

    public float getYaw() {
        return this.getYawRad() * 57.295776f;
    }

    public float getYawRad() {
        return this.getGimbalPole() == 0 ? MathUtils.atan2((this.w * this.y + this.z * this.x) * 2.0f, 1.0f - (this.y * this.y + this.x * this.x) * 2.0f) : 0.0f;
    }

    @Override
    public int hashCode() {
        return (((NumberUtils.floatToRawIntBits(this.w) + 0x1F) * 0x1F + NumberUtils.floatToRawIntBits(this.x)) * 0x1F + NumberUtils.floatToRawIntBits(this.y)) * 0x1F + NumberUtils.floatToRawIntBits(this.z);
    }

    public Quaternion idt() {
        return this.set(0.0f, 0.0f, 0.0f, 1.0f);
    }

    // 去混淆评级： 低(40)
    public boolean isIdentity() {
        return MathUtils.isZero(this.x) && MathUtils.isZero(this.y) && MathUtils.isZero(this.z) && MathUtils.isEqual(this.w, 1.0f);
    }

    // 去混淆评级： 低(40)
    public boolean isIdentity(float f) {
        return MathUtils.isZero(this.x, f) && MathUtils.isZero(this.y, f) && MathUtils.isZero(this.z, f) && MathUtils.isEqual(this.w, 1.0f, f);
    }

    public static final float len(float f, float f1, float f2, float f3) {
        return (float)Math.sqrt(f * f + f1 * f1 + f2 * f2 + f3 * f3);
    }

    public float len() {
        return (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w);
    }

    public static final float len2(float f, float f1, float f2, float f3) [...] // Inlined contents

    public float len2() {
        return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
    }

    public Quaternion mul(float f) {
        this.x *= f;
        this.y *= f;
        this.z *= f;
        this.w *= f;
        return this;
    }

    public Quaternion mul(float f, float f1, float f2, float f3) {
        float f4 = this.w * f1 + this.y * f3 + this.z * f - this.x * f2;
        float f5 = this.w * f2 + this.z * f3 + this.x * f1 - this.y * f;
        float f6 = this.w * f3 - this.x * f - this.y * f1 - this.z * f2;
        this.x = this.w * f + this.x * f3 + this.y * f2 - this.z * f1;
        this.y = f4;
        this.z = f5;
        this.w = f6;
        return this;
    }

    public Quaternion mul(Quaternion quaternion0) {
        float f = this.w * quaternion0.y + this.y * quaternion0.w + this.z * quaternion0.x - this.x * quaternion0.z;
        float f1 = this.w * quaternion0.z + this.z * quaternion0.w + this.x * quaternion0.y - this.y * quaternion0.x;
        float f2 = this.w * quaternion0.w - this.x * quaternion0.x - this.y * quaternion0.y - this.z * quaternion0.z;
        this.x = this.w * quaternion0.x + this.x * quaternion0.w + this.y * quaternion0.z - this.z * quaternion0.y;
        this.y = f;
        this.z = f1;
        this.w = f2;
        return this;
    }

    public Quaternion mulLeft(float f, float f1, float f2, float f3) {
        float f4 = f3 * this.y + f1 * this.w + f2 * this.x - f * this.z;
        float f5 = f3 * this.z + f2 * this.w + f * this.y - f1 * this.x;
        float f6 = f3 * this.w - f * this.x - f1 * this.y - f2 * this.z;
        this.x = f3 * this.x + f * this.w + f1 * this.z - f2 * this.y;
        this.y = f4;
        this.z = f5;
        this.w = f6;
        return this;
    }

    public Quaternion mulLeft(Quaternion quaternion0) {
        float f = quaternion0.w * this.y + quaternion0.y * this.w + quaternion0.z * this.x - quaternion0.x * this.z;
        float f1 = quaternion0.w * this.z + quaternion0.z * this.w + quaternion0.x * this.y - quaternion0.y * this.x;
        float f2 = quaternion0.w * this.w - quaternion0.x * this.x - quaternion0.y * this.y - quaternion0.z * this.z;
        this.x = quaternion0.w * this.x + quaternion0.x * this.w + quaternion0.y * this.z - quaternion0.z * this.y;
        this.y = f;
        this.z = f1;
        this.w = f2;
        return this;
    }

    public Quaternion nor() {
        float f = this.len2();
        if(f != 0.0f && !MathUtils.isEqual(f, 1.0f)) {
            float f1 = (float)Math.sqrt(f);
            this.w /= f1;
            this.x /= f1;
            this.y /= f1;
            this.z /= f1;
        }
        return this;
    }

    public Quaternion set(float f, float f1, float f2, float f3) {
        this.x = f;
        this.y = f1;
        this.z = f2;
        this.w = f3;
        return this;
    }

    public Quaternion set(Quaternion quaternion0) {
        return this.set(quaternion0.x, quaternion0.y, quaternion0.z, quaternion0.w);
    }

    public Quaternion set(Vector3 vector30, float f) {
        return this.setFromAxis(vector30.x, vector30.y, vector30.z, f);
    }

    public Quaternion setEulerAngles(float f, float f1, float f2) {
        return this.setEulerAnglesRad(f * 0.017453f, f1 * 0.017453f, f2 * 0.017453f);
    }

    public Quaternion setEulerAnglesRad(float f, float f1, float f2) {
        float f3 = (float)Math.sin(f2 * 0.5f);
        float f4 = (float)Math.cos(f2 * 0.5f);
        float f5 = (float)Math.sin(f1 * 0.5f);
        float f6 = (float)Math.cos(f1 * 0.5f);
        float f7 = (float)Math.sin(f * 0.5f);
        float f8 = (float)Math.cos(f * 0.5f);
        float f9 = f8 * f5;
        float f10 = f7 * f6;
        float f11 = f8 * f6;
        float f12 = f7 * f5;
        this.x = f9 * f4 + f10 * f3;
        this.y = f10 * f4 - f9 * f3;
        this.z = f11 * f3 - f12 * f4;
        this.w = f11 * f4 + f12 * f3;
        return this;
    }

    public Quaternion setFromAxes(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        return this.setFromAxes(false, f, f1, f2, f3, f4, f5, f6, f7, f8);
    }

    public Quaternion setFromAxes(boolean z, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if(z) {
            float f9 = Vector3.len(f, f1, f2);
            float f10 = Vector3.len(f3, f4, f5);
            float f11 = Vector3.len(f6, f7, f8);
            f *= 1.0f / f9;
            f1 *= 1.0f / f9;
            f2 *= 1.0f / f9;
            f3 *= 1.0f / f10;
            f4 *= 1.0f / f10;
            f5 *= 1.0f / f10;
            f6 *= 1.0f / f11;
            f7 *= 1.0f / f11;
            f8 *= 1.0f / f11;
        }
        float f12 = f + f4 + f8;
        if(f12 >= 0.0f) {
            float f13 = (float)Math.sqrt(f12 + 1.0f);
            this.w = f13 * 0.5f;
            this.x = (f7 - f5) * (0.5f / f13);
            this.y = (f2 - f6) * (0.5f / f13);
            this.z = (f3 - f1) * (0.5f / f13);
            return this;
        }
        if(f > f4 && f > f8) {
            float f14 = (float)Math.sqrt(((double)f) + 1.0 - ((double)f4) - ((double)f8));
            this.x = f14 * 0.5f;
            this.y = (f3 + f1) * (0.5f / f14);
            this.z = (f2 + f6) * (0.5f / f14);
            this.w = (f7 - f5) * (0.5f / f14);
            return this;
        }
        if(f4 > f8) {
            float f15 = (float)Math.sqrt(((double)f4) + 1.0 - ((double)f) - ((double)f8));
            this.y = f15 * 0.5f;
            this.x = (f3 + f1) * (0.5f / f15);
            this.z = (f7 + f5) * (0.5f / f15);
            this.w = (f2 - f6) * (0.5f / f15);
            return this;
        }
        float f16 = (float)Math.sqrt(((double)f8) + 1.0 - ((double)f) - ((double)f4));
        this.z = f16 * 0.5f;
        this.x = (f2 + f6) * (0.5f / f16);
        this.y = (f7 + f5) * (0.5f / f16);
        this.w = (f3 - f1) * (0.5f / f16);
        return this;
    }

    public Quaternion setFromAxis(float f, float f1, float f2, float f3) {
        return this.setFromAxisRad(f, f1, f2, f3 * 0.017453f);
    }

    public Quaternion setFromAxis(Vector3 vector30, float f) {
        return this.setFromAxis(vector30.x, vector30.y, vector30.z, f);
    }

    public Quaternion setFromAxisRad(float f, float f1, float f2, float f3) {
        float f4 = Vector3.len(f, f1, f2);
        if(f4 == 0.0f) {
            return this.idt();
        }
        float f5 = f3 < 0.0f ? 6.283185f - -f3 % 6.283185f : f3 % 6.283185f;
        float f6 = (float)Math.sin(f5 / 2.0f);
        return this.set(f * (1.0f / f4) * f6, f1 * (1.0f / f4) * f6, 1.0f / f4 * f2 * f6, ((float)Math.cos(f5 / 2.0f))).nor();
    }

    public Quaternion setFromAxisRad(Vector3 vector30, float f) {
        return this.setFromAxisRad(vector30.x, vector30.y, vector30.z, f);
    }

    public Quaternion setFromCross(float f, float f1, float f2, float f3, float f4, float f5) {
        return this.setFromAxisRad(f1 * f5 - f2 * f4, f2 * f3 - f5 * f, f * f4 - f1 * f3, ((float)Math.acos(MathUtils.clamp(f * f3 + f1 * f4 + f2 * f5, -1.0f, 1.0f))));
    }

    public Quaternion setFromCross(Vector3 vector30, Vector3 vector31) {
        return this.setFromAxisRad(vector30.y * vector31.z - vector30.z * vector31.y, vector30.z * vector31.x - vector30.x * vector31.z, vector30.x * vector31.y - vector30.y * vector31.x, ((float)Math.acos(MathUtils.clamp(vector30.dot(vector31), -1.0f, 1.0f))));
    }

    public Quaternion setFromMatrix(Matrix3 matrix30) {
        return this.setFromMatrix(false, matrix30);
    }

    public Quaternion setFromMatrix(Matrix4 matrix40) {
        return this.setFromMatrix(false, matrix40);
    }

    public Quaternion setFromMatrix(boolean z, Matrix3 matrix30) {
        return this.setFromAxes(z, matrix30.val[0], matrix30.val[3], matrix30.val[6], matrix30.val[1], matrix30.val[4], matrix30.val[7], matrix30.val[2], matrix30.val[5], matrix30.val[8]);
    }

    public Quaternion setFromMatrix(boolean z, Matrix4 matrix40) {
        return this.setFromAxes(z, matrix40.val[0], matrix40.val[4], matrix40.val[8], matrix40.val[1], matrix40.val[5], matrix40.val[9], matrix40.val[2], matrix40.val[6], matrix40.val[10]);
    }

    public Quaternion slerp(Quaternion quaternion0, float f) {
        float f1 = this.x * quaternion0.x + this.y * quaternion0.y + this.z * quaternion0.z + this.w * quaternion0.w;
        float f2 = f1 < 0.0f ? -f1 : f1;
        float f3 = 1.0f - f;
        if(((double)(1.0f - f2)) > 0.1) {
            float f4 = (float)Math.acos(f2);
            float f5 = 1.0f / ((float)Math.sin(f4));
            f3 = ((float)Math.sin(f3 * f4)) * f5;
            f = ((float)Math.sin(f * f4)) * f5;
        }
        if(f1 < 0.0f) {
            f = -f;
        }
        this.x = this.x * f3 + quaternion0.x * f;
        this.y = this.y * f3 + quaternion0.y * f;
        this.z = this.z * f3 + quaternion0.z * f;
        this.w = f3 * this.w + f * quaternion0.w;
        return this;
    }

    public Quaternion slerp(Quaternion[] arr_quaternion) {
        float f = 1.0f / ((float)arr_quaternion.length);
        this.set(arr_quaternion[0]).exp(f);
        for(int v = 1; v < arr_quaternion.length; ++v) {
            this.mul(Quaternion.tmp1.set(arr_quaternion[v]).exp(f));
        }
        this.nor();
        return this;
    }

    public Quaternion slerp(Quaternion[] arr_quaternion, float[] arr_f) {
        this.set(arr_quaternion[0]).exp(arr_f[0]);
        for(int v = 1; v < arr_quaternion.length; ++v) {
            this.mul(Quaternion.tmp1.set(arr_quaternion[v]).exp(arr_f[v]));
        }
        this.nor();
        return this;
    }

    public void toMatrix(float[] arr_f) {
        float f = this.x * this.x;
        float f1 = this.x * this.y;
        float f2 = this.x * this.z;
        float f3 = this.x * this.w;
        float f4 = this.y * this.y;
        float f5 = this.y * this.z;
        float f6 = this.y * this.w;
        float f7 = this.z * this.z;
        float f8 = this.z * this.w;
        arr_f[0] = 1.0f - (f4 + f7) * 2.0f;
        arr_f[4] = (f1 - f8) * 2.0f;
        arr_f[8] = (f2 + f6) * 2.0f;
        arr_f[12] = 0.0f;
        arr_f[1] = (f1 + f8) * 2.0f;
        arr_f[5] = 1.0f - (f7 + f) * 2.0f;
        arr_f[9] = (f5 - f3) * 2.0f;
        arr_f[13] = 0.0f;
        arr_f[2] = (f2 - f6) * 2.0f;
        arr_f[6] = (f5 + f3) * 2.0f;
        arr_f[10] = 1.0f - (f + f4) * 2.0f;
        arr_f[14] = 0.0f;
        arr_f[3] = 0.0f;
        arr_f[7] = 0.0f;
        arr_f[11] = 0.0f;
        arr_f[15] = 1.0f;
    }

    @Override
    public String toString() [...] // 潜在的解密器

    public Vector3 transform(Vector3 vector30) {
        Quaternion.tmp2.set(this);
        Quaternion.tmp2.conjugate();
        Quaternion.tmp2.mulLeft(Quaternion.tmp1.set(vector30.x, vector30.y, vector30.z, 0.0f)).mulLeft(this);
        vector30.x = Quaternion.tmp2.x;
        vector30.y = Quaternion.tmp2.y;
        vector30.z = Quaternion.tmp2.z;
        return vector30;
    }
}

