package com.badlogic.gdx.graphics.g3d.environment;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;

public class PointLight extends BaseLight {
    public float intensity;
    public final Vector3 position;

    public PointLight() {
        this.position = new Vector3();
    }

    // 去混淆评级： 低(30)
    public boolean equals(PointLight pointLight0) {
        return pointLight0 != null && (pointLight0 == this || this.color.equals(pointLight0.color) && this.position.equals(pointLight0.position) && this.intensity == pointLight0.intensity);
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof PointLight && this.equals(((PointLight)object0));
    }

    public PointLight set(float f, float f1, float f2, float f3, float f4, float f5, float f6) {
        this.color.set(f, f1, f2, 1.0f);
        this.position.set(f3, f4, f5);
        this.intensity = f6;
        return this;
    }

    public PointLight set(float f, float f1, float f2, Vector3 vector30, float f3) {
        this.color.set(f, f1, f2, 1.0f);
        if(vector30 != null) {
            this.position.set(vector30);
        }
        this.intensity = f3;
        return this;
    }

    public PointLight set(Color color0, float f, float f1, float f2, float f3) {
        if(color0 != null) {
            this.color.set(color0);
        }
        this.position.set(f, f1, f2);
        this.intensity = f3;
        return this;
    }

    public PointLight set(Color color0, Vector3 vector30, float f) {
        if(color0 != null) {
            this.color.set(color0);
        }
        if(vector30 != null) {
            this.position.set(vector30);
        }
        this.intensity = f;
        return this;
    }

    public PointLight set(PointLight pointLight0) {
        return this.set(pointLight0.color, pointLight0.position, pointLight0.intensity);
    }

    public PointLight setIntensity(float f) {
        this.intensity = f;
        return this;
    }

    public PointLight setPosition(float f, float f1, float f2) {
        this.position.set(f, f1, f2);
        return this;
    }

    public PointLight setPosition(Vector3 vector30) {
        this.position.set(vector30);
        return this;
    }
}

