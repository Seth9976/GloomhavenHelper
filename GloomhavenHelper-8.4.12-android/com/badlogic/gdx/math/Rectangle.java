package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.NumberUtils;
import java.io.Serializable;

public class Rectangle implements Shape2D, Serializable {
    public float height;
    private static final long serialVersionUID = 0x4F909A54EA199076L;
    public static final Rectangle tmp;
    public static final Rectangle tmp2;
    public float width;
    public float x;
    public float y;

    static {
        Rectangle.tmp = new Rectangle();
        Rectangle.tmp2 = new Rectangle();
    }

    public Rectangle() {
    }

    public Rectangle(float f, float f1, float f2, float f3) {
        this.x = f;
        this.y = f1;
        this.width = f2;
        this.height = f3;
    }

    public Rectangle(Rectangle rectangle0) {
        this.x = rectangle0.x;
        this.y = rectangle0.y;
        this.width = rectangle0.width;
        this.height = rectangle0.height;
    }

    public float area() {
        return this.width * this.height;
    }

    @Override  // com.badlogic.gdx.math.Shape2D
    public boolean contains(float f, float f1) {
        return this.x <= f && this.x + this.width >= f && (this.y <= f1 && this.y + this.height >= f1);
    }

    public boolean contains(Circle circle0) {
        return circle0.x - circle0.radius >= this.x && circle0.x + circle0.radius <= this.x + this.width && circle0.y - circle0.radius >= this.y && circle0.y + circle0.radius <= this.y + this.height;
    }

    public boolean contains(Rectangle rectangle0) {
        float f = rectangle0.width + rectangle0.x;
        float f1 = rectangle0.height + rectangle0.y;
        return rectangle0.x > this.x && (rectangle0.x < this.x + this.width && f > this.x && f < this.x + this.width) && (rectangle0.y > this.y && (rectangle0.y < this.y + this.height && f1 > this.y && f1 < this.y + this.height));
    }

    @Override  // com.badlogic.gdx.math.Shape2D
    public boolean contains(Vector2 vector20) {
        return this.contains(vector20.x, vector20.y);
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
        if(NumberUtils.floatToRawIntBits(this.height) != NumberUtils.floatToRawIntBits(((Rectangle)object0).height)) {
            return false;
        }
        if(NumberUtils.floatToRawIntBits(this.width) != NumberUtils.floatToRawIntBits(((Rectangle)object0).width)) {
            return false;
        }
        return NumberUtils.floatToRawIntBits(this.x) == NumberUtils.floatToRawIntBits(((Rectangle)object0).x) ? NumberUtils.floatToRawIntBits(this.y) == NumberUtils.floatToRawIntBits(((Rectangle)object0).y) : false;
    }

    public Rectangle fitInside(Rectangle rectangle0) {
        float f = this.getAspectRatio();
        if(f < rectangle0.getAspectRatio()) {
            this.setSize(f * rectangle0.height, rectangle0.height);
        }
        else {
            this.setSize(rectangle0.width, rectangle0.width / f);
        }
        this.setPosition(rectangle0.x + rectangle0.width / 2.0f - this.width / 2.0f, rectangle0.y + rectangle0.height / 2.0f - this.height / 2.0f);
        return this;
    }

    public Rectangle fitOutside(Rectangle rectangle0) {
        float f = this.getAspectRatio();
        if(f > rectangle0.getAspectRatio()) {
            this.setSize(f * rectangle0.height, rectangle0.height);
        }
        else {
            this.setSize(rectangle0.width, rectangle0.width / f);
        }
        this.setPosition(rectangle0.x + rectangle0.width / 2.0f - this.width / 2.0f, rectangle0.y + rectangle0.height / 2.0f - this.height / 2.0f);
        return this;
    }

    public Rectangle fromString(String s) {
        int v = s.indexOf(44, 1);
        int v1 = s.indexOf(44, v + 1);
        int v2 = s.indexOf(44, v1 + 1);
        if(v != -1 && v1 != -1 && v2 != -1 && s.charAt(0) == 91 && s.charAt(s.length() - 1) == 93) {
            try {
                return this.set(Float.parseFloat(s.substring(1, v)), Float.parseFloat(s.substring(v + 1, v1)), Float.parseFloat(s.substring(v1 + 1, v2)), Float.parseFloat(s.substring(v2 + 1, s.length() - 1)));
            }
            catch(NumberFormatException unused_ex) {
            }
        }
        throw new GdxRuntimeException("Malformed Rectangle: " + s);
    }

    public float getAspectRatio() {
        return this.height == 0.0f ? NaNf : this.width / this.height;
    }

    public Vector2 getCenter(Vector2 vector20) {
        vector20.x = this.x + this.width / 2.0f;
        vector20.y = this.y + this.height / 2.0f;
        return vector20;
    }

    public float getHeight() {
        return this.height;
    }

    public Vector2 getPosition(Vector2 vector20) {
        return vector20.set(this.x, this.y);
    }

    public Vector2 getSize(Vector2 vector20) {
        return vector20.set(this.width, this.height);
    }

    public float getWidth() {
        return this.width;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    @Override
    public int hashCode() {
        return (((NumberUtils.floatToRawIntBits(this.height) + 0x1F) * 0x1F + NumberUtils.floatToRawIntBits(this.width)) * 0x1F + NumberUtils.floatToRawIntBits(this.x)) * 0x1F + NumberUtils.floatToRawIntBits(this.y);
    }

    public Rectangle merge(float f, float f1) {
        float f2 = Math.min(this.x, f);
        float f3 = Math.max(this.x + this.width, f);
        this.x = f2;
        this.width = f3 - f2;
        float f4 = Math.min(this.y, f1);
        float f5 = Math.max(this.y + this.height, f1);
        this.y = f4;
        this.height = f5 - f4;
        return this;
    }

    public Rectangle merge(Rectangle rectangle0) {
        float f = Math.min(this.x, rectangle0.x);
        float f1 = Math.max(this.x + this.width, rectangle0.x + rectangle0.width);
        this.x = f;
        this.width = f1 - f;
        float f2 = Math.min(this.y, rectangle0.y);
        float f3 = Math.max(this.y + this.height, rectangle0.y + rectangle0.height);
        this.y = f2;
        this.height = f3 - f2;
        return this;
    }

    public Rectangle merge(Vector2 vector20) {
        return this.merge(vector20.x, vector20.y);
    }

    public Rectangle merge(Vector2[] arr_vector2) {
        float f = this.x;
        float f1 = this.width + f;
        float f2 = this.y;
        float f3 = this.height + f2;
        for(int v = 0; v < arr_vector2.length; ++v) {
            Vector2 vector20 = arr_vector2[v];
            f = Math.min(f, vector20.x);
            f1 = Math.max(f1, vector20.x);
            f2 = Math.min(f2, vector20.y);
            f3 = Math.max(f3, vector20.y);
        }
        this.x = f;
        this.width = f1 - f;
        this.y = f2;
        this.height = f3 - f2;
        return this;
    }

    public boolean overlaps(Rectangle rectangle0) {
        return this.x < rectangle0.width + rectangle0.x && this.x + this.width > rectangle0.x && (this.y < rectangle0.height + rectangle0.y && this.y + this.height > rectangle0.y);
    }

    public float perimeter() {
        return (this.width + this.height) * 2.0f;
    }

    public Rectangle set(float f, float f1, float f2, float f3) {
        this.x = f;
        this.y = f1;
        this.width = f2;
        this.height = f3;
        return this;
    }

    public Rectangle set(Rectangle rectangle0) {
        this.x = rectangle0.x;
        this.y = rectangle0.y;
        this.width = rectangle0.width;
        this.height = rectangle0.height;
        return this;
    }

    public Rectangle setCenter(float f, float f1) {
        this.setPosition(f - this.width / 2.0f, f1 - this.height / 2.0f);
        return this;
    }

    public Rectangle setCenter(Vector2 vector20) {
        this.setPosition(vector20.x - this.width / 2.0f, vector20.y - this.height / 2.0f);
        return this;
    }

    public Rectangle setHeight(float f) {
        this.height = f;
        return this;
    }

    public Rectangle setPosition(float f, float f1) {
        this.x = f;
        this.y = f1;
        return this;
    }

    public Rectangle setPosition(Vector2 vector20) {
        this.x = vector20.x;
        this.y = vector20.y;
        return this;
    }

    public Rectangle setSize(float f) {
        this.width = f;
        this.height = f;
        return this;
    }

    public Rectangle setSize(float f, float f1) {
        this.width = f;
        this.height = f1;
        return this;
    }

    public Rectangle setWidth(float f) {
        this.width = f;
        return this;
    }

    public Rectangle setX(float f) {
        this.x = f;
        return this;
    }

    public Rectangle setY(float f) {
        this.y = f;
        return this;
    }

    @Override
    public String toString() {
        return "[" + this.x + "," + this.y + "," + this.width + "," + this.height + "]";
    }
}

