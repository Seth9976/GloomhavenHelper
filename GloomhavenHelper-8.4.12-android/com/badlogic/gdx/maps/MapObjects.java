package com.badlogic.gdx.maps;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import java.util.Iterator;

public class MapObjects implements Iterable {
    private Array objects;

    public MapObjects() {
        this.objects = new Array();
    }

    public void add(MapObject mapObject0) {
        this.objects.add(mapObject0);
    }

    public MapObject get(int v) {
        return (MapObject)this.objects.get(v);
    }

    public MapObject get(String s) {
        int v = this.objects.size;
        for(int v1 = 0; v1 < v; ++v1) {
            MapObject mapObject0 = (MapObject)this.objects.get(v1);
            if(s.equals(mapObject0.getName())) {
                return mapObject0;
            }
        }
        return null;
    }

    public Array getByType(Class class0) {
        return this.getByType(class0, new Array());
    }

    public Array getByType(Class class0, Array array0) {
        array0.clear();
        int v = this.objects.size;
        for(int v1 = 0; v1 < v; ++v1) {
            MapObject mapObject0 = (MapObject)this.objects.get(v1);
            if(ClassReflection.isInstance(class0, mapObject0)) {
                array0.add(mapObject0);
            }
        }
        return array0;
    }

    public int getCount() {
        return this.objects.size;
    }

    public int getIndex(MapObject mapObject0) {
        return this.objects.indexOf(mapObject0, true);
    }

    public int getIndex(String s) {
        return this.getIndex(this.get(s));
    }

    @Override
    public Iterator iterator() {
        return this.objects.iterator();
    }

    public void remove(int v) {
        this.objects.removeIndex(v);
    }

    public void remove(MapObject mapObject0) {
        this.objects.removeValue(mapObject0, true);
    }
}

