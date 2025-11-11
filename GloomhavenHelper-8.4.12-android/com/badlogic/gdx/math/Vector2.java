package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.NumberUtils;
import java.io.Serializable;

public class Vector2 implements Vector, Serializable {
    public static final Vector2 X = null;
    public static final Vector2 Y = null;
    public static final Vector2 Zero = null;
    private static final long serialVersionUID = 913902788239530931L;
    public float x;
    public float y;

    static {
        Vector2.X = new Vector2(1.0f, 0.0f);
        Vector2.Y = new Vector2(0.0f, 1.0f);
        Vector2.Zero = new Vector2(0.0f, 0.0f);
    }

    public Vector2() {
    }

    public Vector2(float f, float f1) {
        this.x = f;
        this.y = f1;
    }

    public Vector2(Vector2 vector20) {
        this.set(vector20);
    }

    public Vector2 add(float f, float f1) {
        this.x += f;
        this.y += f1;
        return this;
    }

    public Vector2 add(Vector2 vector20) {
        this.x += vector20.x;
        this.y += vector20.y;
        return this;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector add(Vector vector0) {
        return this.add(((Vector2)vector0));
    }

    @Deprecated
    public float angle() {
        float f = ((float)Math.atan2(this.y, this.x)) * 57.295776f;
        return f < 0.0f ? f + 360.0f : f;
    }

    @Deprecated
    public float angle(Vector2 vector20) {
        return ((float)Math.atan2(this.crs(vector20), this.dot(vector20))) * 57.295776f;
    }

    public float angleDeg() {
        float f = ((float)Math.atan2(this.y, this.x)) * 57.295776f;
        return f < 0.0f ? f + 360.0f : f;
    }

    public float angleDeg(Vector2 vector20) {
        float f = ((float)Math.atan2(vector20.crs(this), vector20.dot(this))) * 57.295776f;
        return f < 0.0f ? f + 360.0f : f;
    }

    public float angleRad() {
        return (float)Math.atan2(this.y, this.x);
    }

    public float angleRad(Vector2 vector20) {
        return (float)Math.atan2(vector20.crs(this), vector20.dot(this));
    }

    public Vector2 clamp(float f, float f1) {
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

    public Vector2 cpy() {
        return new Vector2(this);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector cpy() {
        return this.cpy();
    }

    public float crs(float f, float f1) {
        return this.x * f1 - this.y * f;
    }

    public float crs(Vector2 vector20) {
        return this.x * vector20.y - this.y * vector20.x;
    }

    public static float dot(float f, float f1, float f2, float f3) {
        return f * f2 + f1 * f3;
    }

    public float dot(float f, float f1) {
        return this.x * f + this.y * f1;
    }

    public float dot(Vector2 vector20) {
        return this.x * vector20.x + this.y * vector20.y;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public float dot(Vector vector0) {
        return this.dot(((Vector2)vector0));
    }

    public static float dst(float f, float f1, float f2, float f3) {
        return (float)Math.sqrt((f2 - f) * (f2 - f) + (f3 - f1) * (f3 - f1));
    }

    public float dst(float f, float f1) {
        float f2 = f - this.x;
        float f3 = f1 - this.y;
        return (float)Math.sqrt(f2 * f2 + f3 * f3);
    }

    public float dst(Vector2 vector20) {
        float f = vector20.x - this.x;
        float f1 = vector20.y - this.y;
        return (float)Math.sqrt(f * f + f1 * f1);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public float dst(Vector vector0) {
        return this.dst(((Vector2)vector0));
    }

    public static float dst2(float f, float f1, float f2, float f3) {
        return (f2 - f) * (f2 - f) + (f3 - f1) * (f3 - f1);
    }

    public float dst2(float f, float f1) {
        float f2 = f - this.x;
        float f3 = f1 - this.y;
        return f2 * f2 + f3 * f3;
    }

    public float dst2(Vector2 vector20) {
        float f = vector20.x - this.x;
        float f1 = vector20.y - this.y;
        return f * f + f1 * f1;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public float dst2(Vector vector0) {
        return this.dst2(((Vector2)vector0));
    }

    public boolean epsilonEquals(float f, float f1) {
        return this.epsilonEquals(f, f1, 0.000001f);
    }

    public boolean epsilonEquals(float f, float f1, float f2) {
        return Math.abs(f - this.x) > f2 ? false : Math.abs(f1 - this.y) <= f2;
    }

    public boolean epsilonEquals(Vector2 vector20) {
        return this.epsilonEquals(vector20, 0.000001f);
    }

    public boolean epsilonEquals(Vector2 vector20, float f) {
        if(vector20 == null) {
            return false;
        }
        return Math.abs(vector20.x - this.x) > f ? false : Math.abs(vector20.y - this.y) <= f;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean epsilonEquals(Vector vector0, float f) {
        return this.epsilonEquals(((Vector2)vector0), f);
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
        return NumberUtils.floatToIntBits(this.x) == NumberUtils.floatToIntBits(((Vector2)object0).x) ? NumberUtils.floatToIntBits(this.y) == NumberUtils.floatToIntBits(((Vector2)object0).y) : false;
    }

    public Vector2 fromString(String s) {
        int v = s.indexOf(44, 1);
        if(v != -1 && s.charAt(0) == 40 && s.charAt(s.length() - 1) == 41) {
            try {
                return this.set(Float.parseFloat(s.substring(1, v)), Float.parseFloat(s.substring(v + 1, s.length() - 1)));
            }
            catch(NumberFormatException unused_ex) {
            }
        }
        throw new GdxRuntimeException("Malformed Vector2: " + s);
    }

    public boolean hasOppositeDirection(Vector2 vector20) {
        return this.dot(vector20) < 0.0f;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean hasOppositeDirection(Vector vector0) {
        return this.hasOppositeDirection(((Vector2)vector0));
    }

    public boolean hasSameDirection(Vector2 vector20) {
        return this.dot(vector20) > 0.0f;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean hasSameDirection(Vector vector0) {
        return this.hasSameDirection(((Vector2)vector0));
    }

    @Override
    public int hashCode() {
        return (NumberUtils.floatToIntBits(this.x) + 0x1F) * 0x1F + NumberUtils.floatToIntBits(this.y);
    }

    public Vector2 interpolate(Vector2 vector20, float f, Interpolation interpolation0) {
        return this.lerp(vector20, interpolation0.apply(f));
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector interpolate(Vector vector0, float f, Interpolation interpolation0) {
        return this.interpolate(((Vector2)vector0), f, interpolation0);
    }

    public boolean isCollinear(Vector2 vector20) {
        return this.isOnLine(vector20) && this.dot(vector20) > 0.0f;
    }

    public boolean isCollinear(Vector2 vector20, float f) {
        return this.isOnLine(vector20, f) && this.dot(vector20) > 0.0f;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean isCollinear(Vector vector0) {
        return this.isCollinear(((Vector2)vector0));
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean isCollinear(Vector vector0, float f) {
        return this.isCollinear(((Vector2)vector0), f);
    }

    public boolean isCollinearOpposite(Vector2 vector20) {
        return this.isOnLine(vector20) && this.dot(vector20) < 0.0f;
    }

    public boolean isCollinearOpposite(Vector2 vector20, float f) {
        return this.isOnLine(vector20, f) && this.dot(vector20) < 0.0f;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean isCollinearOpposite(Vector vector0) {
        return this.isCollinearOpposite(((Vector2)vector0));
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean isCollinearOpposite(Vector vector0, float f) {
        return this.isCollinearOpposite(((Vector2)vector0), f);
    }

    public boolean isOnLine(Vector2 vector20) {
        return MathUtils.isZero(this.x * vector20.y - this.y * vector20.x);
    }

    public boolean isOnLine(Vector2 vector20, float f) {
        return MathUtils.isZero(this.x * vector20.y - this.y * vector20.x, f);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean isOnLine(Vector vector0) {
        return this.isOnLine(((Vector2)vector0));
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean isOnLine(Vector vector0, float f) {
        return this.isOnLine(((Vector2)vector0), f);
    }

    public boolean isPerpendicular(Vector2 vector20) {
        return MathUtils.isZero(this.dot(vector20));
    }

    public boolean isPerpendicular(Vector2 vector20, float f) {
        return MathUtils.isZero(this.dot(vector20), f);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean isPerpendicular(Vector vector0) {
        return this.isPerpendicular(((Vector2)vector0));
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean isPerpendicular(Vector vector0, float f) {
        return this.isPerpendicular(((Vector2)vector0), f);
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
        return this.x == 0.0f && this.y == 0.0f;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public boolean isZero(float f) {
        return this.len2() < f;
    }

    public static float len(float f, float f1) {
        return (float)Math.sqrt(f * f + f1 * f1);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public float len() {
        return (float)Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public static float len2(float f, float f1) {
        return f * f + f1 * f1;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public float len2() {
        return this.x * this.x + this.y * this.y;
    }

    public Vector2 lerp(Vector2 vector20, float f) {
        this.x = this.x * (1.0f - f) + vector20.x * f;
        this.y = this.y * (1.0f - f) + vector20.y * f;
        return this;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector lerp(Vector vector0, float f) {
        return this.lerp(((Vector2)vector0), f);
    }

    public Vector2 limit(float f) {
        return this.limit2(f * f);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector limit(float f) {
        return this.limit(f);
    }

    public Vector2 limit2(float f) {
        float f1 = this.len2();
        return f1 > f ? this.scl(((float)Math.sqrt(f / f1))) : this;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector limit2(float f) {
        return this.limit2(f);
    }

    public Vector2 mul(Matrix3 matrix30) {
        float f = this.x * matrix30.val[0] + this.y * matrix30.val[3] + matrix30.val[6];
        float f1 = this.x * matrix30.val[1] + this.y * matrix30.val[4];
        this.x = f;
        this.y = f1 + matrix30.val[7];
        return this;
    }

    public Vector2 mulAdd(Vector2 vector20, float f) {
        this.x += vector20.x * f;
        this.y += vector20.y * f;
        return this;
    }

    public Vector2 mulAdd(Vector2 vector20, Vector2 vector21) {
        this.x += vector20.x * vector21.x;
        this.y += vector20.y * vector21.y;
        return this;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector mulAdd(Vector vector0, float f) {
        return this.mulAdd(((Vector2)vector0), f);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector mulAdd(Vector vector0, Vector vector1) {
        return this.mulAdd(((Vector2)vector0), ((Vector2)vector1));
    }

    public Vector2 nor() {
        float f = this.len();
        if(f != 0.0f) {
            this.x /= f;
            this.y /= f;
        }
        return this;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector nor() {
        return this.nor();
    }

    @Deprecated
    public Vector2 rotate(float f) {
        return this.rotateRad(f * 0.017453f);
    }

    public Vector2 rotate90(int v) {
        float f = this.x;
        if(v >= 0) {
            this.x = -this.y;
            this.y = f;
            return this;
        }
        this.x = this.y;
        this.y = -f;
        return this;
    }

    @Deprecated
    public Vector2 rotateAround(Vector2 vector20, float f) {
        return this.sub(vector20).rotateDeg(f).add(vector20);
    }

    public Vector2 rotateAroundDeg(Vector2 vector20, float f) {
        return this.sub(vector20).rotateDeg(f).add(vector20);
    }

    public Vector2 rotateAroundRad(Vector2 vector20, float f) {
        return this.sub(vector20).rotateRad(f).add(vector20);
    }

    public Vector2 rotateDeg(float f) {
        return this.rotateRad(f * 0.017453f);
    }

    public Vector2 rotateRad(float f) {
        float f1 = (float)Math.cos(f);
        float f2 = (float)Math.sin(f);
        float f3 = this.x * f2 + this.y * f1;
        this.x = this.x * f1 - this.y * f2;
        this.y = f3;
        return this;
    }

    public Vector2 scl(float f) {
        this.x *= f;
        this.y *= f;
        return this;
    }

    public Vector2 scl(float f, float f1) {
        this.x *= f;
        this.y *= f1;
        return this;
    }

    public Vector2 scl(Vector2 vector20) {
        this.x *= vector20.x;
        this.y *= vector20.y;
        return this;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector scl(float f) {
        return this.scl(f);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector scl(Vector vector0) {
        return this.scl(((Vector2)vector0));
    }

    public Vector2 set(float f, float f1) {
        this.x = f;
        this.y = f1;
        return this;
    }

    public Vector2 set(Vector2 vector20) {
        this.x = vector20.x;
        this.y = vector20.y;
        return this;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector set(Vector vector0) {
        return this.set(((Vector2)vector0));
    }

    @Deprecated
    public Vector2 setAngle(float f) {
        return this.setAngleRad(f * 0.017453f);
    }

    public Vector2 setAngleDeg(float f) {
        return this.setAngleRad(f * 0.017453f);
    }

    public Vector2 setAngleRad(float f) {
        this.set(this.len(), 0.0f);
        this.rotateRad(f);
        return this;
    }

    public Vector2 setLength(float f) {
        return this.setLength2(f * f);
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector setLength(float f) {
        return this.setLength(f);
    }

    public Vector2 setLength2(float f) {
        float f1 = this.len2();
        return f1 == 0.0f || f1 == f ? this : this.scl(((float)Math.sqrt(f / f1)));
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector setLength2(float f) {
        return this.setLength2(f);
    }

    public Vector2 setToRandomDirection() {
        float f = MathUtils.random(0.0f, 6.283185f);
        return this.set(MathUtils.cos(f), MathUtils.sin(f));
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector setToRandomDirection() {
        return this.setToRandomDirection();
    }

    public Vector2 setZero() {
        this.x = 0.0f;
        this.y = 0.0f;
        return this;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector setZero() {
        return this.setZero();
    }

    public Vector2 sub(float f, float f1) {
        this.x -= f;
        this.y -= f1;
        return this;
    }

    public Vector2 sub(Vector2 vector20) {
        this.x -= vector20.x;
        this.y -= vector20.y;
        return this;
    }

    @Override  // com.badlogic.gdx.math.Vector
    public Vector sub(Vector vector0) {
        return this.sub(((Vector2)vector0));
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}

