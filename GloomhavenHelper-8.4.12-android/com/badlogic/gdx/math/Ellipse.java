package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.NumberUtils;
import java.io.Serializable;

public class Ellipse implements Shape2D, Serializable {
    public float height;
    private static final long serialVersionUID = 7381533206532032099L;
    public float width;
    public float x;
    public float y;

    public Ellipse() {
    }

    public Ellipse(float f, float f1, float f2, float f3) {
        this.x = f;
        this.y = f1;
        this.width = f2;
        this.height = f3;
    }

    public Ellipse(Circle circle0) {
        this.x = circle0.x;
        this.y = circle0.y;
        this.width = circle0.radius * 2.0f;
        this.height = circle0.radius * 2.0f;
    }

    public Ellipse(Ellipse ellipse0) {
        this.x = ellipse0.x;
        this.y = ellipse0.y;
        this.width = ellipse0.width;
        this.height = ellipse0.height;
    }

    public Ellipse(Vector2 vector20, float f, float f1) {
        this.x = vector20.x;
        this.y = vector20.y;
        this.width = f;
        this.height = f1;
    }

    public Ellipse(Vector2 vector20, Vector2 vector21) {
        this.x = vector20.x;
        this.y = vector20.y;
        this.width = vector21.x;
        this.height = vector21.y;
    }

    public float area() {
        return this.width * this.height * 3.141593f / 4.0f;
    }

    public float circumference() {
        float f = this.width / 2.0f;
        float f1 = this.height / 2.0f;
        return f * 3.0f > f1 || f1 * 3.0f > f ? ((float)((((double)((f + f1) * 3.0f)) - Math.sqrt((f * 3.0f + f1) * (f + f1 * 3.0f))) * 3.141593)) : ((float)(Math.sqrt((f * f + f1 * f1) / 2.0f) * 6.283185));
    }

    @Override  // com.badlogic.gdx.math.Shape2D
    public boolean contains(float f, float f1) {
        float f2 = f - this.x;
        float f3 = f1 - this.y;
        return f2 * f2 / (this.width * 0.5f * this.width * 0.5f) + f3 * f3 / (this.height * 0.5f * this.height * 0.5f) <= 1.0f;
    }

    @Override  // com.badlogic.gdx.math.Shape2D
    public boolean contains(Vector2 vector20) {
        return this.contains(vector20.x, vector20.y);
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 == this ? true : object0 != null && object0.getClass() == this.getClass() && (this.x == ((Ellipse)object0).x && this.y == ((Ellipse)object0).y && this.width == ((Ellipse)object0).width && this.height == ((Ellipse)object0).height);
    }

    @Override
    public int hashCode() {
        return (((NumberUtils.floatToRawIntBits(this.height) + 53) * 53 + NumberUtils.floatToRawIntBits(this.width)) * 53 + NumberUtils.floatToRawIntBits(this.x)) * 53 + NumberUtils.floatToRawIntBits(this.y);
    }

    public void set(float f, float f1, float f2, float f3) {
        this.x = f;
        this.y = f1;
        this.width = f2;
        this.height = f3;
    }

    public void set(Circle circle0) {
        this.x = circle0.x;
        this.y = circle0.y;
        this.width = circle0.radius * 2.0f;
        this.height = circle0.radius * 2.0f;
    }

    public void set(Ellipse ellipse0) {
        this.x = ellipse0.x;
        this.y = ellipse0.y;
        this.width = ellipse0.width;
        this.height = ellipse0.height;
    }

    public void set(Vector2 vector20, Vector2 vector21) {
        this.x = vector20.x;
        this.y = vector20.y;
        this.width = vector21.x;
        this.height = vector21.y;
    }

    public Ellipse setPosition(float f, float f1) {
        this.x = f;
        this.y = f1;
        return this;
    }

    public Ellipse setPosition(Vector2 vector20) {
        this.x = vector20.x;
        this.y = vector20.y;
        return this;
    }

    public Ellipse setSize(float f, float f1) {
        this.width = f;
        this.height = f1;
        return this;
    }
}

