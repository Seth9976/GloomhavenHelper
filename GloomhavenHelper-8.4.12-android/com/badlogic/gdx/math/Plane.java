package com.badlogic.gdx.math;

import java.io.Serializable;

public class Plane implements Serializable {
    public static enum PlaneSide {
        OnPlane,
        Back,
        Front;

    }

    public float d;
    public final Vector3 normal;
    private static final long serialVersionUID = 0xEEC85180FB107626L;

    public Plane() {
        this.normal = new Vector3();
        this.d = 0.0f;
    }

    public Plane(Vector3 vector30, float f) {
        this.normal = new Vector3();
        this.d = 0.0f;
        this.normal.set(vector30).nor();
        this.d = f;
    }

    public Plane(Vector3 vector30, Vector3 vector31) {
        this.normal = new Vector3();
        this.d = 0.0f;
        this.normal.set(vector30).nor();
        this.d = -this.normal.dot(vector31);
    }

    public Plane(Vector3 vector30, Vector3 vector31, Vector3 vector32) {
        this.normal = new Vector3();
        this.d = 0.0f;
        this.set(vector30, vector31, vector32);
    }

    public float distance(Vector3 vector30) {
        return this.normal.dot(vector30) + this.d;
    }

    public float getD() {
        return this.d;
    }

    public Vector3 getNormal() {
        return this.normal;
    }

    public boolean isFrontFacing(Vector3 vector30) {
        return this.normal.dot(vector30) <= 0.0f;
    }

    public void set(float f, float f1, float f2, float f3) {
        this.normal.set(f, f1, f2);
        this.d = f3;
    }

    public void set(float f, float f1, float f2, float f3, float f4, float f5) {
        this.normal.set(f3, f4, f5);
        this.d = -(f * f3 + f1 * f4 + f2 * f5);
    }

    public void set(Plane plane0) {
        this.normal.set(plane0.normal);
        this.d = plane0.d;
    }

    public void set(Vector3 vector30, Vector3 vector31) {
        this.normal.set(vector31);
        this.d = -vector30.dot(vector31);
    }

    public void set(Vector3 vector30, Vector3 vector31, Vector3 vector32) {
        this.normal.set(vector30).sub(vector31).crs(vector31.x - vector32.x, vector31.y - vector32.y, vector31.z - vector32.z).nor();
        this.d = -vector30.dot(this.normal);
    }

    public PlaneSide testPoint(float f, float f1, float f2) {
        float f3 = this.normal.dot(f, f1, f2) + this.d;
        if(f3 == 0.0f) {
            return PlaneSide.OnPlane;
        }
        return f3 < 0.0f ? PlaneSide.Back : PlaneSide.Front;
    }

    public PlaneSide testPoint(Vector3 vector30) {
        float f = this.normal.dot(vector30) + this.d;
        if(f == 0.0f) {
            return PlaneSide.OnPlane;
        }
        return f < 0.0f ? PlaneSide.Back : PlaneSide.Front;
    }

    // 去混淆评级： 低(40)
    @Override
    public String toString() {
        return "(0.0,0.0,0.0), " + this.d;
    }
}

