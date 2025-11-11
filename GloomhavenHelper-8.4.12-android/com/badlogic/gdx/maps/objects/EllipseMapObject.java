package com.badlogic.gdx.maps.objects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Ellipse;

public class EllipseMapObject extends MapObject {
    private Ellipse ellipse;

    public EllipseMapObject() {
        this(0.0f, 0.0f, 1.0f, 1.0f);
    }

    public EllipseMapObject(float f, float f1, float f2, float f3) {
        this.ellipse = new Ellipse(f, f1, f2, f3);
    }

    public Ellipse getEllipse() {
        return this.ellipse;
    }
}

