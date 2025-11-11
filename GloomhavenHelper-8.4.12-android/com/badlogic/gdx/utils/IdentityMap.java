package com.badlogic.gdx.utils;

public class IdentityMap extends ObjectMap {
    public IdentityMap() {
    }

    public IdentityMap(int v) {
        super(v);
    }

    public IdentityMap(int v, float f) {
        super(v, f);
    }

    public IdentityMap(IdentityMap identityMap0) {
        super(identityMap0);
    }

    @Override  // com.badlogic.gdx.utils.ObjectMap
    public int hashCode() {
        int v = this.size;
        Object[] arr_object = this.keyTable;
        Object[] arr_object1 = this.valueTable;
        for(int v1 = 0; v1 < arr_object.length; ++v1) {
            Object object0 = arr_object[v1];
            if(object0 != null) {
                v += System.identityHashCode(object0);
                Object object1 = arr_object1[v1];
                if(object1 != null) {
                    v += object1.hashCode();
                }
            }
        }
        return v;
    }

    @Override  // com.badlogic.gdx.utils.ObjectMap
    int locateKey(Object object0) {
        if(object0 != null) {
            Object[] arr_object = this.keyTable;
            for(int v = this.place(object0); true; v = v + 1 & this.mask) {
                Object object1 = arr_object[v];
                if(object1 == null) {
                    return -(v + 1);
                }
                if(object1 == object0) {
                    return v;
                }
            }
        }
        throw new IllegalArgumentException("key cannot be null.");
    }

    @Override  // com.badlogic.gdx.utils.ObjectMap
    protected int place(Object object0) {
        return (int)(((long)System.identityHashCode(object0)) * 0x9E3779B97F4A7C15L >>> this.shift);
    }
}

