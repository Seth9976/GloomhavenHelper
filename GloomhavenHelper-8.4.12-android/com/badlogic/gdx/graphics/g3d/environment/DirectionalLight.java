package com.badlogic.gdx.graphics.g3d.environment;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;

public class DirectionalLight extends BaseLight {
    public final Vector3 direction;

    public DirectionalLight() {
        this.direction = new Vector3();
    }

    // 去混淆评级： 低(30)
    public boolean equals(DirectionalLight directionalLight0) {
        return directionalLight0 != null && (directionalLight0 == this || this.color.equals(directionalLight0.color) && this.direction.equals(directionalLight0.direction));
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof DirectionalLight && this.equals(((DirectionalLight)object0));
    }

    public DirectionalLight set(float f, float f1, float f2, float f3, float f4, float f5) {
        this.color.set(f, f1, f2, 1.0f);
        this.direction.set(f3, f4, f5).nor();
        return this;
    }

    public DirectionalLight set(float f, float f1, float f2, Vector3 vector30) {
        this.color.set(f, f1, f2, 1.0f);
        if(vector30 != null) {
            this.direction.set(vector30).nor();
        }
        return this;
    }

    public DirectionalLight set(Color color0, float f, float f1, float f2) {
        if(color0 != null) {
            this.color.set(color0);
        }
        this.direction.set(f, f1, f2).nor();
        return this;
    }

    public DirectionalLight set(Color color0, Vector3 vector30) {
        if(color0 != null) {
            this.color.set(color0);
        }
        if(vector30 != null) {
            this.direction.set(vector30).nor();
        }
        return this;
    }

    public DirectionalLight set(DirectionalLight directionalLight0) {
        return this.set(directionalLight0.color, directionalLight0.direction);
    }

    public DirectionalLight setDirection(float f, float f1, float f2) {
        this.direction.set(f, f1, f2);
        return this;
    }

    public DirectionalLight setDirection(Vector3 vector30) {
        this.direction.set(vector30);
        return this;
    }
}

