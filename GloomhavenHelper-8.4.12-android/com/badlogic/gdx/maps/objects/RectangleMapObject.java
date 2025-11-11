package com.badlogic.gdx.maps.objects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;

public class RectangleMapObject extends MapObject {
    private Rectangle rectangle;

    public RectangleMapObject() {
        this(0.0f, 0.0f, 1.0f, 1.0f);
    }

    public RectangleMapObject(float f, float f1, float f2, float f3) {
        this.rectangle = new Rectangle(f, f1, f2, f3);
    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }
}

