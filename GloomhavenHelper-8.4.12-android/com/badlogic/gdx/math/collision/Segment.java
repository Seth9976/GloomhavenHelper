package com.badlogic.gdx.math.collision;

import com.badlogic.gdx.math.Vector3;
import java.io.Serializable;

public class Segment implements Serializable {
    public final Vector3 a;
    public final Vector3 b;
    private static final long serialVersionUID = 0x260540AF3E7B07B2L;

    public Segment(float f, float f1, float f2, float f3, float f4, float f5) {
        this.a = new Vector3();
        this.b = new Vector3();
        this.a.set(f, f1, f2);
        this.b.set(f3, f4, f5);
    }

    public Segment(Vector3 vector30, Vector3 vector31) {
        this.a = new Vector3();
        this.b = new Vector3();
        this.a.set(vector30);
        this.b.set(vector31);
    }

    // 去混淆评级： 低(40)
    @Override
    public boolean equals(Object object0) {
        return object0 == this ? true : object0 != null && object0.getClass() == this.getClass() && (this.a.equals(((Segment)object0).a) && this.b.equals(((Segment)object0).b));
    }

    @Override
    public int hashCode() {
        return (this.a.hashCode() + 71) * 71 + this.b.hashCode();
    }

    public float len() {
        return this.a.dst(this.b);
    }

    public float len2() {
        return this.a.dst2(this.b);
    }
}

