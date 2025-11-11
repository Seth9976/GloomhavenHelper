package com.badlogic.gdx.maps.objects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Polyline;

public class PolylineMapObject extends MapObject {
    private Polyline polyline;

    public PolylineMapObject() {
        this(new float[0]);
    }

    public PolylineMapObject(Polyline polyline0) {
        this.polyline = polyline0;
    }

    public PolylineMapObject(float[] arr_f) {
        this.polyline = new Polyline(arr_f);
    }

    public Polyline getPolyline() {
        return this.polyline;
    }

    public void setPolyline(Polyline polyline0) {
        this.polyline = polyline0;
    }
}

