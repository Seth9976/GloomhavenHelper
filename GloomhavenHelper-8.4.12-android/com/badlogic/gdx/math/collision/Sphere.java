package com.badlogic.gdx.math.collision;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.NumberUtils;
import java.io.Serializable;

public class Sphere implements Serializable {
    private static final float PI_4_3 = 4.18879f;
    public final Vector3 center;
    public float radius;
    private static final long serialVersionUID = 0xA5F8597D3669A784L;

    public Sphere(Vector3 vector30, float f) {
        this.center = new Vector3(vector30);
        this.radius = f;
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return this == object0 ? true : object0 != null && object0.getClass() == this.getClass() && (this.radius == ((Sphere)object0).radius && this.center.equals(((Sphere)object0).center));
    }

    @Override
    public int hashCode() {
        return (this.center.hashCode() + 71) * 71 + NumberUtils.floatToRawIntBits(this.radius);
    }

    public boolean overlaps(Sphere sphere0) {
        return this.center.dst2(sphere0.center) < (this.radius + sphere0.radius) * (this.radius + sphere0.radius);
    }

    public float surfaceArea() {
        return 12.566371f * this.radius * this.radius;
    }

    public float volume() {
        return 4.18879f * this.radius * this.radius * this.radius;
    }
}

