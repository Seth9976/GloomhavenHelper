package com.badlogic.gdx.graphics.g3d.environment;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class SpotLight extends BaseLight {
    public float cutoffAngle;
    public final Vector3 direction;
    public float exponent;
    public float intensity;
    public final Vector3 position;

    public SpotLight() {
        this.position = new Vector3();
        this.direction = new Vector3();
    }

    // 去混淆评级： 中等(70)
    public boolean equals(SpotLight spotLight0) {
        return spotLight0 != null && (spotLight0 == this || this.color.equals(spotLight0.color) && this.position.equals(spotLight0.position) && this.direction.equals(spotLight0.direction) && MathUtils.isEqual(this.intensity, spotLight0.intensity) && MathUtils.isEqual(this.cutoffAngle, spotLight0.cutoffAngle) && MathUtils.isEqual(this.exponent, spotLight0.exponent));
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof SpotLight && this.equals(((SpotLight)object0));
    }

    public SpotLight set(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11) {
        this.color.set(f, f1, f2, 1.0f);
        this.position.set(f3, f4, f5);
        this.direction.set(f6, f7, f8).nor();
        this.intensity = f9;
        this.cutoffAngle = f10;
        this.exponent = f11;
        return this;
    }

    public SpotLight set(float f, float f1, float f2, Vector3 vector30, Vector3 vector31, float f3, float f4, float f5) {
        this.color.set(f, f1, f2, 1.0f);
        if(vector30 != null) {
            this.position.set(vector30);
        }
        if(vector31 != null) {
            this.direction.set(vector31).nor();
        }
        this.intensity = f3;
        this.cutoffAngle = f4;
        this.exponent = f5;
        return this;
    }

    public SpotLight set(Color color0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if(color0 != null) {
            this.color.set(color0);
        }
        this.position.set(f, f1, f2);
        this.direction.set(f3, f4, f5).nor();
        this.intensity = f6;
        this.cutoffAngle = f7;
        this.exponent = f8;
        return this;
    }

    public SpotLight set(Color color0, Vector3 vector30, Vector3 vector31, float f, float f1, float f2) {
        if(color0 != null) {
            this.color.set(color0);
        }
        if(vector30 != null) {
            this.position.set(vector30);
        }
        if(vector31 != null) {
            this.direction.set(vector31).nor();
        }
        this.intensity = f;
        this.cutoffAngle = f1;
        this.exponent = f2;
        return this;
    }

    public SpotLight set(SpotLight spotLight0) {
        return this.set(spotLight0.color, spotLight0.position, spotLight0.direction, spotLight0.intensity, spotLight0.cutoffAngle, spotLight0.exponent);
    }

    public SpotLight setCutoffAngle(float f) {
        this.cutoffAngle = f;
        return this;
    }

    public SpotLight setDirection(float f, float f1, float f2) {
        this.direction.set(f, f1, f2);
        return this;
    }

    public SpotLight setDirection(Vector3 vector30) {
        this.direction.set(vector30);
        return this;
    }

    public SpotLight setExponent(float f) {
        this.exponent = f;
        return this;
    }

    public SpotLight setIntensity(float f) {
        this.intensity = f;
        return this;
    }

    public SpotLight setPosition(float f, float f1, float f2) {
        this.position.set(f, f1, f2);
        return this;
    }

    public SpotLight setPosition(Vector3 vector30) {
        this.position.set(vector30);
        return this;
    }

    public SpotLight setTarget(Vector3 vector30) {
        this.direction.set(vector30).sub(this.position).nor();
        return this;
    }
}

