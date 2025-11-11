package com.badlogic.gdx.maps;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import java.util.Iterator;

public class MapLayers implements Iterable {
    private Array layers;

    public MapLayers() {
        this.layers = new Array();
    }

    public void add(MapLayer mapLayer0) {
        this.layers.add(mapLayer0);
    }

    public MapLayer get(int v) {
        return (MapLayer)this.layers.get(v);
    }

    public MapLayer get(String s) {
        int v = this.layers.size;
        for(int v1 = 0; v1 < v; ++v1) {
            MapLayer mapLayer0 = (MapLayer)this.layers.get(v1);
            if(s.equals(mapLayer0.getName())) {
                return mapLayer0;
            }
        }
        return null;
    }

    public Array getByType(Class class0) {
        return this.getByType(class0, new Array());
    }

    public Array getByType(Class class0, Array array0) {
        array0.clear();
        int v = this.layers.size;
        for(int v1 = 0; v1 < v; ++v1) {
            MapLayer mapLayer0 = (MapLayer)this.layers.get(v1);
            if(ClassReflection.isInstance(class0, mapLayer0)) {
                array0.add(mapLayer0);
            }
        }
        return array0;
    }

    public int getCount() {
        return this.layers.size;
    }

    public int getIndex(MapLayer mapLayer0) {
        return this.layers.indexOf(mapLayer0, true);
    }

    public int getIndex(String s) {
        return this.getIndex(this.get(s));
    }

    @Override
    public Iterator iterator() {
        return this.layers.iterator();
    }

    public void remove(int v) {
        this.layers.removeIndex(v);
    }

    public void remove(MapLayer mapLayer0) {
        this.layers.removeValue(mapLayer0, true);
    }

    public int size() {
        return this.layers.size;
    }
}

