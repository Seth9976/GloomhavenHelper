package com.badlogic.gdx.maps;

import com.badlogic.gdx.utils.ObjectMap;
import java.util.Iterator;

public class MapProperties {
    private ObjectMap properties;

    public MapProperties() {
        this.properties = new ObjectMap();
    }

    public void clear() {
        this.properties.clear();
    }

    public boolean containsKey(String s) {
        return this.properties.containsKey(s);
    }

    public Object get(String s) {
        return this.properties.get(s);
    }

    public Object get(String s, Class class0) {
        return this.get(s);
    }

    public Object get(String s, Object object0, Class class0) {
        Object object1 = this.get(s);
        return object1 == null ? object0 : object1;
    }

    public Iterator getKeys() {
        return this.properties.keys();
    }

    public Iterator getValues() {
        return this.properties.values();
    }

    public void put(String s, Object object0) {
        this.properties.put(s, object0);
    }

    public void putAll(MapProperties mapProperties0) {
        this.properties.putAll(mapProperties0.properties);
    }

    public void remove(String s) {
        this.properties.remove(s);
    }
}

