package com.badlogic.gdx.maps.objects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Circle;

public class CircleMapObject extends MapObject {
    private Circle circle;

    public CircleMapObject() {
        this(0.0f, 0.0f, 1.0f);
    }

    public CircleMapObject(float f, float f1, float f2) {
        this.circle = new Circle(f, f1, f2);
    }

    public Circle getCircle() {
        return this.circle;
    }
}

