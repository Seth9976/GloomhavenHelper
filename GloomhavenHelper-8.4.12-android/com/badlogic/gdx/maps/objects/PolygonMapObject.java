package com.badlogic.gdx.maps.objects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Polygon;

public class PolygonMapObject extends MapObject {
    private Polygon polygon;

    public PolygonMapObject() {
        this(new float[0]);
    }

    public PolygonMapObject(Polygon polygon0) {
        this.polygon = polygon0;
    }

    public PolygonMapObject(float[] arr_f) {
        this.polygon = new Polygon(arr_f);
    }

    public Polygon getPolygon() {
        return this.polygon;
    }

    public void setPolygon(Polygon polygon0) {
        this.polygon = polygon0;
    }
}

