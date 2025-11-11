package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.NumberUtils;
import java.io.Serializable;

public class Circle implements Shape2D, Serializable {
    public float radius;
    public float x;
    public float y;

    public Circle() {
    }

    public Circle(float f, float f1, float f2) {
        this.x = f;
        this.y = f1;
        this.radius = f2;
    }

    public Circle(Circle circle0) {
        this.x = circle0.x;
        this.y = circle0.y;
        this.radius = circle0.radius;
    }

    public Circle(Vector2 vector20, float f) {
        this.x = vector20.x;
        this.y = vector20.y;
        this.radius = f;
    }

    public Circle(Vector2 vector20, Vector2 vector21) {
        this.x = vector20.x;
        this.y = vector20.y;
        this.radius = Vector2.len(vector20.x - vector21.x, vector20.y - vector21.y);
    }

    public float area() {
        return this.radius * this.radius * 3.141593f;
    }

    public float circumference() {
        return this.radius * 6.283185f;
    }

    @Override  // com.badlogic.gdx.math.Shape2D
    public boolean contains(float f, float f1) {
        float f2 = this.x - f;
        float f3 = this.y - f1;
        return f2 * f2 + f3 * f3 <= this.radius * this.radius;
    }

    public boolean contains(Circle circle0) {
        float f = this.radius;
        float f1 = circle0.radius;
        float f2 = f - f1;
        if(f2 < 0.0f) {
            return false;
        }
        float f3 = this.x - circle0.x;
        float f4 = this.y - circle0.y;
        float f5 = f3 * f3 + f4 * f4;
        return f2 * f2 >= f5 && f5 < (f + f1) * (f + f1);
    }

    @Override  // com.badlogic.gdx.math.Shape2D
    public boolean contains(Vector2 vector20) {
        float f = this.x - vector20.x;
        float f1 = this.y - vector20.y;
        return f * f + f1 * f1 <= this.radius * this.radius;
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 == this ? true : object0 != null && object0.getClass() == this.getClass() && (this.x == ((Circle)object0).x && this.y == ((Circle)object0).y && this.radius == ((Circle)object0).radius);
    }

    @Override
    public int hashCode() {
        return ((NumberUtils.floatToRawIntBits(this.radius) + 41) * 41 + NumberUtils.floatToRawIntBits(this.x)) * 41 + NumberUtils.floatToRawIntBits(this.y);
    }

    public boolean overlaps(Circle circle0) {
        float f = this.x - circle0.x;
        float f1 = this.y - circle0.y;
        float f2 = this.radius + circle0.radius;
        return f * f + f1 * f1 < f2 * f2;
    }

    public void set(float f, float f1, float f2) {
        this.x = f;
        this.y = f1;
        this.radius = f2;
    }

    public void set(Circle circle0) {
        this.x = circle0.x;
        this.y = circle0.y;
        this.radius = circle0.radius;
    }

    public void set(Vector2 vector20, float f) {
        this.x = vector20.x;
        this.y = vector20.y;
        this.radius = f;
    }

    public void set(Vector2 vector20, Vector2 vector21) {
        this.x = vector20.x;
        this.y = vector20.y;
        this.radius = Vector2.len(vector20.x - vector21.x, vector20.y - vector21.y);
    }

    public void setPosition(float f, float f1) {
        this.x = f;
        this.y = f1;
    }

    public void setPosition(Vector2 vector20) {
        this.x = vector20.x;
        this.y = vector20.y;
    }

    public void setRadius(float f) {
        this.radius = f;
    }

    public void setX(float f) {
        this.x = f;
    }

    public void setY(float f) {
        this.y = f;
    }

    @Override
    public String toString() {
        return this.x + "," + this.y + "," + this.radius;
    }
}

