package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.NumberUtils;
import java.io.Serializable;

public class Vector3 implements Vector, Serializable {
    public static final Vector3 X = null;
    public static final Vector3 Y = null;
    public static final Vector3 Z = null;
    public static final Vector3 Zero = null;
    private static final long serialVersionUID = 0x354A9D4DC2F0CBEAL;
    private static final Matrix4 tmpMat;
    public float x;
    public float y;
    public float z;

    static {
        Vector3.X = new Vector3(1.0f, 0.0f, 0.0f);
        Vector3.Y = new Vector3(0.0f, 1.0f, 0.0f);
        Vector3.Z = new Vector3(0.0f, 0.0f, 1.0f);
        Vector3.Zero = new Vector3(0.0f, 0.0f, 0.0f);
        Vector3.tmpMat = new Matrix4();
    }

    public Vector3() {
    }

    public Vector3(float f, float f1, float f2) {
        this.set(f, f1, f2);
    }

    public Vector3(Vector2 vector20, float f) {
        this.set(vector20.x, vector20.y, f);
    }

    public Vector3(Vector3 vector30) {
        this.set(vector30);
    }

    public Vector3(float[] arr_f) {
        this.set(arr_f[0], arr_f[1], arr_f[2]);
    }

    public Vector3 add(float f) {
        return this.set(this.x + f, this.y + f, this.z + f);
    }

    public Vector3 add(float f, float f1, float f2) {
        return this.set(this.x + f, this.y + f1, this.z + f2);
    }

    public Vector3 add(Vector3 vector30) {
        return this.add(vector30.x, vector30.y, vector30.z);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector add(Vector vector0) {
        return this.add(((Vector3)vector0));
    }

    public Vector3 clamp(float f, float f1) {
        float f2 = this.len2();
        if(f2 == 0.0f) {
            return this;
        }
        float f3 = f1 * f1;
        if(f2 > f3) {
            return this.scl(((float)Math.sqrt(f3 / f2)));
        }
        float f4 = f * f;
        return f2 < f4 ? this.scl(((float)Math.sqrt(f4 / f2))) : this;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector clamp(float f, float f1) {
        return this.clamp(f, f1);
    }

    public Vector3 cpy() {
        return new Vector3(this);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector cpy() {
        return this.cpy();
    }

    public Vector3 crs(float f, float f1, float f2) {
        return this.set(this.y * f2 - this.z * f1, this.z * f - f2 * this.x, this.x * f1 - this.y * f);
    }

    public Vector3 crs(Vector3 vector30) {
        return this.set(this.y * vector30.z - this.z * vector30.y, this.z * vector30.x - vector30.z * this.x, this.x * vector30.y - this.y * vector30.x);
    }

    public static float dot(float f, float f1, float f2, float f3, float f4, float f5) [...] // Inlined contents

    public float dot(float f, float f1, float f2) {
        return this.x * f + this.y * f1 + this.z * f2;
    }

    public float dot(Vector3 vector30) {
        return this.x * vector30.x + this.y * vector30.y + this.z * vector30.z;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public float dot(Vector vector0) {
        return this.dot(((Vector3)vector0));
    }

    public static float dst(float f, float f1, float f2, float f3, float f4, float f5) {
        return (float)Math.sqrt((f3 - f) * (f3 - f) + (f4 - f1) * (f4 - f1) + (f5 - f2) * (f5 - f2));
    }

    public float dst(float f, float f1, float f2) {
        float f3 = f - this.x;
        float f4 = f1 - this.y;
        float f5 = f2 - this.z;
        return (float)Math.sqrt(f3 * f3 + f4 * f4 + f5 * f5);
    }

    public float dst(Vector3 vector30) {
        float f = vector30.x - this.x;
        float f1 = vector30.y - this.y;
        float f2 = vector30.z - this.z;
        return (float)Math.sqrt(f * f + f1 * f1 + f2 * f2);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public float dst(Vector vector0) {
        return this.dst(((Vector3)vector0));
    }

    public static float dst2(float f, float f1, float f2, float f3, float f4, float f5) {
        return (f3 - f) * (f3 - f) + (f4 - f1) * (f4 - f1) + (f5 - f2) * (f5 - f2);
    }

    public float dst2(float f, float f1, float f2) {
        float f3 = f - this.x;
        float f4 = f1 - this.y;
        float f5 = f2 - this.z;
        return f3 * f3 + f4 * f4 + f5 * f5;
    }

    public float dst2(Vector3 vector30) {
        float f = vector30.x - this.x;
        float f1 = vector30.y - this.y;
        float f2 = vector30.z - this.z;
        return f * f + f1 * f1 + f2 * f2;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public float dst2(Vector vector0) {
        return this.dst2(((Vector3)vector0));
    }

    public boolean epsilonEquals(float f, float f1, float f2) {
        return this.epsilonEquals(f, f1, f2, 0.000001f);
    }

    public boolean epsilonEquals(float f, float f1, float f2, float f3) {
        if(Math.abs(f - this.x) > f3) {
            return false;
        }
        return Math.abs(f1 - this.y) > f3 ? false : Math.abs(f2 - this.z) <= f3;
    }

    public boolean epsilonEquals(Vector3 vector30) {
        return this.epsilonEquals(vector30, 0.000001f);
    }

    public boolean epsilonEquals(Vector3 vector30, float f) {
        if(vector30 == null) {
            return false;
        }
        if(Math.abs(vector30.x - this.x) > f) {
            return false;
        }
        return Math.abs(vector30.y - this.y) > f ? false : Math.abs(vector30.z - this.z) <= f;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean epsilonEquals(Vector vector0, float f) {
        return this.epsilonEquals(((Vector3)vector0), f);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(object0 == null) {
            return false;
        }
        if(this.getClass() != object0.getClass()) {
            return false;
        }
        if(NumberUtils.floatToIntBits(this.x) != NumberUtils.floatToIntBits(((Vector3)object0).x)) {
            return false;
        }
        return NumberUtils.floatToIntBits(this.y) == NumberUtils.floatToIntBits(((Vector3)object0).y) ? NumberUtils.floatToIntBits(this.z) == NumberUtils.floatToIntBits(((Vector3)object0).z) : false;
    }

    public Vector3 fromString(String s) {
        int v = s.indexOf(44, 1);
        int v1 = s.indexOf(44, v + 1);
        if(v != -1 && v1 != -1 && s.charAt(0) == 40 && s.charAt(s.length() - 1) == 41) {
            try {
                return this.set(Float.parseFloat(s.substring(1, v)), Float.parseFloat(s.substring(v + 1, v1)), Float.parseFloat(s.substring(v1 + 1, s.length() - 1)));
            }
            catch(NumberFormatException unused_ex) {
            }
        }
        throw new GdxRuntimeException("Malformed Vector3: " + s);
    }

    public boolean hasOppositeDirection(Vector3 vector30) {
        return this.dot(vector30) < 0.0f;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean hasOppositeDirection(Vector vector0) {
        return this.hasOppositeDirection(((Vector3)vector0));
    }

    public boolean hasSameDirection(Vector3 vector30) {
        return this.dot(vector30) > 0.0f;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean hasSameDirection(Vector vector0) {
        return this.hasSameDirection(((Vector3)vector0));
    }

    @Override
    public int hashCode() {
        return ((NumberUtils.floatToIntBits(this.x) + 0x1F) * 0x1F + NumberUtils.floatToIntBits(this.y)) * 0x1F + NumberUtils.floatToIntBits(this.z);
    }

    public boolean idt(Vector3 vector30) {
        return this.x == vector30.x && this.y == vector30.y && this.z == vector30.z;
    }

    public Vector3 interpolate(Vector3 vector30, float f, Interpolation interpolation0) {
        return this.lerp(vector30, interpolation0.apply(0.0f, 1.0f, f));
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector interpolate(Vector vector0, float f, Interpolation interpolation0) {
        return this.interpolate(((Vector3)vector0), f, interpolation0);
    }

    // 去混淆评级： 低(20)
    public boolean isCollinear(Vector3 vector30) {
        return this.isOnLine(vector30) && this.hasSameDirection(vector30);
    }

    // 去混淆评级： 低(20)
    public boolean isCollinear(Vector3 vector30, float f) {
        return this.isOnLine(vector30, f) && this.hasSameDirection(vector30);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean isCollinear(Vector vector0) {
        return this.isCollinear(((Vector3)vector0));
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean isCollinear(Vector vector0, float f) {
        return this.isCollinear(((Vector3)vector0), f);
    }

    // 去混淆评级： 低(20)
    public boolean isCollinearOpposite(Vector3 vector30) {
        return this.isOnLine(vector30) && this.hasOppositeDirection(vector30);
    }

    // 去混淆评级： 低(20)
    public boolean isCollinearOpposite(Vector3 vector30, float f) {
        return this.isOnLine(vector30, f) && this.hasOppositeDirection(vector30);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean isCollinearOpposite(Vector vector0) {
        return this.isCollinearOpposite(((Vector3)vector0));
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean isCollinearOpposite(Vector vector0, float f) {
        return this.isCollinearOpposite(((Vector3)vector0), f);
    }

    public boolean isOnLine(Vector3 vector30) {
        return (this.y * vector30.z - this.z * vector30.y) * (this.y * vector30.z - this.z * vector30.y) + (this.z * vector30.x - vector30.z * this.x) * (this.z * vector30.x - vector30.z * this.x) + (this.x * vector30.y - this.y * vector30.x) * (this.x * vector30.y - this.y * vector30.x) <= 0.000001f;
    }

    public boolean isOnLine(Vector3 vector30, float f) {
        return (this.y * vector30.z - this.z * vector30.y) * (this.y * vector30.z - this.z * vector30.y) + (this.z * vector30.x - vector30.z * this.x) * (this.z * vector30.x - vector30.z * this.x) + (this.x * vector30.y - this.y * vector30.x) * (this.x * vector30.y - this.y * vector30.x) <= f;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean isOnLine(Vector vector0) {
        return this.isOnLine(((Vector3)vector0));
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean isOnLine(Vector vector0, float f) {
        return this.isOnLine(((Vector3)vector0), f);
    }

    public boolean isPerpendicular(Vector3 vector30) {
        return MathUtils.isZero(this.dot(vector30));
    }

    public boolean isPerpendicular(Vector3 vector30, float f) {
        return MathUtils.isZero(this.dot(vector30), f);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean isPerpendicular(Vector vector0) {
        return this.isPerpendicular(((Vector3)vector0));
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean isPerpendicular(Vector vector0, float f) {
        return this.isPerpendicular(((Vector3)vector0), f);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean isUnit() {
        return this.isUnit(1.000000E-09f);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean isUnit(float f) {
        return Math.abs(this.len2() - 1.0f) < f;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean isZero() {
        return this.x == 0.0f && this.y == 0.0f && this.z == 0.0f;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean isZero(float f) {
        return this.len2() < f;
    }

    public static float len(float f, float f1, float f2) {
        return (float)Math.sqrt(f * f + f1 * f1 + f2 * f2);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public float len() {
        return (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public static float len2(float f, float f1, float f2) [...] // Inlined contents

    @Override  // com.badlogic.gdx.math.Vector
    public float len2() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    public Vector3 lerp(Vector3 vector30, float f) {
        this.x += (vector30.x - this.x) * f;
        this.y += (vector30.y - this.y) * f;
        this.z += f * (vector30.z - this.z);
        return this;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector lerp(Vector vector0, float f) {
        return this.lerp(((Vector3)vector0), f);
    }

    public Vector3 limit(float f) {
        return this.limit2(f * f);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector limit(float f) {
        return this.limit(f);
    }

    public Vector3 limit2(float f) {
        float f1 = this.len2();
        if(f1 > f) {
            this.scl(((float)Math.sqrt(f / f1)));
        }
        return this;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector limit2(float f) {
        return this.limit2(f);
    }

    public Vector3 mul(Matrix3 matrix30) {
        return this.set(matrix30.val[0] * this.x + matrix30.val[3] * this.y + matrix30.val[6] * this.z, matrix30.val[1] * this.x + matrix30.val[4] * this.y + matrix30.val[7] * this.z, this.x * matrix30.val[2] + this.y * matrix30.val[5] + this.z * matrix30.val[8]);
    }

    public Vector3 mul(Matrix4 matrix40) {
        return this.set(matrix40.val[0] * this.x + matrix40.val[4] * this.y + matrix40.val[8] * this.z + matrix40.val[12], matrix40.val[1] * this.x + matrix40.val[5] * this.y + matrix40.val[9] * this.z + matrix40.val[13], this.x * matrix40.val[2] + this.y * matrix40.val[6] + this.z * matrix40.val[10] + matrix40.val[14]);
    }

    public Vector3 mul(Quaternion quaternion0) {
        return quaternion0.transform(this);
    }

    public Vector3 mul4x3(float[] arr_f) {
        return this.set(arr_f[0] * this.x + arr_f[3] * this.y + arr_f[6] * this.z + arr_f[9], arr_f[1] * this.x + arr_f[4] * this.y + arr_f[7] * this.z + arr_f[10], this.x * arr_f[2] + this.y * arr_f[5] + this.z * arr_f[8] + arr_f[11]);
    }

    public Vector3 mulAdd(Vector3 vector30, float f) {
        this.x += vector30.x * f;
        this.y += vector30.y * f;
        this.z += vector30.z * f;
        return this;
    }

    public Vector3 mulAdd(Vector3 vector30, Vector3 vector31) {
        this.x += vector30.x * vector31.x;
        this.y += vector30.y * vector31.y;
        this.z += vector30.z * vector31.z;
        return this;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector mulAdd(Vector vector0, float f) {
        return this.mulAdd(((Vector3)vector0), f);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector mulAdd(Vector vector0, Vector vector1) {
        return this.mulAdd(((Vector3)vector0), ((Vector3)vector1));
    }

    public Vector3 nor() {
        float f = this.len2();
        return f == 0.0f || f == 1.0f ? this : this.scl(1.0f / ((float)Math.sqrt(f)));
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector nor() {
        return this.nor();
    }

    public Vector3 prj(Matrix4 matrix40) {
        float f = this.x;
        float f1 = this.y;
        float f2 = this.z;
        float f3 = 1.0f / (matrix40.val[3] * f + matrix40.val[7] * f1 + matrix40.val[11] * f2 + matrix40.val[15]);
        return this.set((matrix40.val[0] * f + matrix40.val[4] * f1 + matrix40.val[8] * f2 + matrix40.val[12]) * f3, (matrix40.val[1] * f + matrix40.val[5] * f1 + matrix40.val[9] * f2 + matrix40.val[13]) * f3, (f * matrix40.val[2] + f1 * matrix40.val[6] + f2 * matrix40.val[10] + matrix40.val[14]) * f3);
    }

    public Vector3 rot(Matrix4 matrix40) {
        return this.set(matrix40.val[0] * this.x + matrix40.val[4] * this.y + matrix40.val[8] * this.z, matrix40.val[1] * this.x + matrix40.val[5] * this.y + matrix40.val[9] * this.z, this.x * matrix40.val[2] + this.y * matrix40.val[6] + this.z * matrix40.val[10]);
    }

    public Vector3 rotate(float f, float f1, float f2, float f3) {
        return this.mul(Vector3.tmpMat.setToRotation(f1, f2, f3, f));
    }

    public Vector3 rotate(Vector3 vector30, float f) {
        Vector3.tmpMat.setToRotation(vector30, f);
        return this.mul(Vector3.tmpMat);
    }

    public Vector3 rotateRad(float f, float f1, float f2, float f3) {
        return this.mul(Vector3.tmpMat.setToRotationRad(f1, f2, f3, f));
    }

    public Vector3 rotateRad(Vector3 vector30, float f) {
        Vector3.tmpMat.setToRotationRad(vector30, f);
        return this.mul(Vector3.tmpMat);
    }

    public Vector3 scl(float f) {
        return this.set(this.x * f, this.y * f, this.z * f);
    }

    public Vector3 scl(float f, float f1, float f2) {
        return this.set(this.x * f, this.y * f1, this.z * f2);
    }

    public Vector3 scl(Vector3 vector30) {
        return this.set(this.x * vector30.x, this.y * vector30.y, this.z * vector30.z);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector scl(float f) {
        return this.scl(f);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector scl(Vector vector0) {
        return this.scl(((Vector3)vector0));
    }

    public Vector3 set(float f, float f1, float f2) {
        this.x = f;
        this.y = f1;
        this.z = f2;
        return this;
    }

    public Vector3 set(Vector2 vector20, float f) {
        return this.set(vector20.x, vector20.y, f);
    }

    public Vector3 set(Vector3 vector30) {
        return this.set(vector30.x, vector30.y, vector30.z);
    }

    public Vector3 set(float[] arr_f) {
        return this.set(arr_f[0], arr_f[1], arr_f[2]);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector set(Vector vector0) {
        return this.set(((Vector3)vector0));
    }

    public Vector3 setFromSpherical(float f, float f1) {
        float f2 = MathUtils.cos(f1);
        float f3 = MathUtils.sin(f1);
        return this.set(MathUtils.cos(f) * f3, MathUtils.sin(f) * f3, f2);
    }

    public Vector3 setLength(float f) {
        return this.setLength2(f * f);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector setLength(float f) {
        return this.setLength(f);
    }

    public Vector3 setLength2(float f) {
        float f1 = this.len2();
        return f1 == 0.0f || f1 == f ? this : this.scl(((float)Math.sqrt(f / f1)));
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector setLength2(float f) {
        return this.setLength2(f);
    }

    public Vector3 setToRandomDirection() {
        return this.setFromSpherical(MathUtils.random() * 6.283185f, ((float)Math.acos(MathUtils.random() * 2.0f - 1.0f)));
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector setToRandomDirection() {
        return this.setToRandomDirection();
    }

    public Vector3 setZero() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        return this;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector setZero() {
        return this.setZero();
    }

    public Vector3 slerp(Vector3 vector30, float f) {
        float f1 = this.dot(vector30);
        if(((double)f1) <= 0.9995 && ((double)f1) >= -0.9995) {
            double f2 = (double)(((float)Math.acos(f1)) * f);
            float f3 = vector30.x - this.x * f1;
            float f4 = vector30.y - this.y * f1;
            float f5 = vector30.z - this.z * f1;
            float f6 = f3 * f3 + f4 * f4 + f5 * f5;
            float f7 = ((float)Math.sin(f2)) * (f6 < 0.0001f ? 1.0f : 1.0f / ((float)Math.sqrt(f6)));
            return this.scl(((float)Math.cos(f2))).add(f3 * f7, f4 * f7, f5 * f7).nor();
        }
        return this.lerp(vector30, f);
    }

    public Vector3 sub(float f) {
        return this.set(this.x - f, this.y - f, this.z - f);
    }

    public Vector3 sub(float f, float f1, float f2) {
        return this.set(this.x - f, this.y - f1, this.z - f2);
    }

    public Vector3 sub(Vector3 vector30) {
        return this.sub(vector30.x, vector30.y, vector30.z);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector sub(Vector vector0) {
        return this.sub(((Vector3)vector0));
    }

    @Override
    public String toString() [...] // 潜在的解密器

    public Vector3 traMul(Matrix3 matrix30) {
        return this.set(matrix30.val[0] * this.x + matrix30.val[1] * this.y + matrix30.val[2] * this.z, matrix30.val[3] * this.x + matrix30.val[4] * this.y + matrix30.val[5] * this.z, this.x * matrix30.val[6] + this.y * matrix30.val[7] + this.z * matrix30.val[8]);
    }

    public Vector3 traMul(Matrix4 matrix40) {
        return this.set(matrix40.val[0] * this.x + matrix40.val[1] * this.y + matrix40.val[2] * this.z + matrix40.val[3], matrix40.val[4] * this.x + matrix40.val[5] * this.y + matrix40.val[6] * this.z + matrix40.val[7], this.x * matrix40.val[8] + this.y * matrix40.val[9] + this.z * matrix40.val[10] + matrix40.val[11]);
    }

    public Vector3 unrotate(Matrix4 matrix40) {
        return this.set(matrix40.val[0] * this.x + matrix40.val[1] * this.y + matrix40.val[2] * this.z, matrix40.val[4] * this.x + matrix40.val[5] * this.y + matrix40.val[6] * this.z, this.x * matrix40.val[8] + this.y * matrix40.val[9] + this.z * matrix40.val[10]);
    }

    public Vector3 untransform(Matrix4 matrix40) {
        this.x -= matrix40.val[12];
        this.y -= matrix40.val[12];
        this.z -= matrix40.val[12];
        return this.set(matrix40.val[0] * this.x + matrix40.val[1] * this.y + matrix40.val[2] * this.z, matrix40.val[4] * this.x + matrix40.val[5] * this.y + matrix40.val[6] * this.z, this.x * matrix40.val[8] + this.y * matrix40.val[9] + this.z * matrix40.val[10]);
    }
}

