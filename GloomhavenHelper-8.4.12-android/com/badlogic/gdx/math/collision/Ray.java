package com.badlogic.gdx.math.collision;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import java.io.Serializable;

public class Ray implements Serializable {
    public final Vector3 direction;
    public final Vector3 origin;
    private static final long serialVersionUID = -620692054835390878L;
    static Vector3 tmp;

    static {
        Ray.tmp = new Vector3();
    }

    public Ray() {
        this.origin = new Vector3();
        this.direction = new Vector3();
    }

    public Ray(Vector3 vector30, Vector3 vector31) {
        this.origin = new Vector3();
        this.direction = new Vector3();
        this.origin.set(vector30);
        this.direction.set(vector31).nor();
    }

    public Ray cpy() {
        return new Ray(this.origin, this.direction);
    }

    // 去混淆评级： 低(40)
    @Override
    public boolean equals(Object object0) {
        return object0 == this ? true : object0 != null && object0.getClass() == this.getClass() && (this.direction.equals(((Ray)object0).direction) && this.origin.equals(((Ray)object0).origin));
    }

    public Vector3 getEndPoint(Vector3 vector30, float f) {
        return vector30.set(this.direction).scl(f).add(this.origin);
    }

    @Override
    public int hashCode() {
        return (this.direction.hashCode() + 73) * 73 + this.origin.hashCode();
    }

    public Ray mul(Matrix4 matrix40) {
        Ray.tmp.set(this.origin).add(this.direction);
        Ray.tmp.mul(matrix40);
        this.origin.mul(matrix40);
        Vector3 vector30 = Ray.tmp.sub(this.origin);
        this.direction.set(vector30).nor();
        return this;
    }

    public Ray set(float f, float f1, float f2, float f3, float f4, float f5) {
        this.origin.set(f, f1, f2);
        this.direction.set(f3, f4, f5).nor();
        return this;
    }

    public Ray set(Vector3 vector30, Vector3 vector31) {
        this.origin.set(vector30);
        this.direction.set(vector31).nor();
        return this;
    }

    public Ray set(Ray ray0) {
        this.origin.set(ray0.origin);
        this.direction.set(ray0.direction).nor();
        return this;
    }

    @Override
    public String toString() {
        return "ray [" + this.origin + ":" + this.direction + "]";
    }
}

