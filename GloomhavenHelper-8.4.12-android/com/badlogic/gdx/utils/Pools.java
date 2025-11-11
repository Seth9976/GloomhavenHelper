package com.badlogic.gdx.utils;

public class Pools {
    private static final ObjectMap typePools;

    static {
        Pools.typePools = new ObjectMap();
    }

    public static void free(Object object0) {
        if(object0 == null) {
            throw new IllegalArgumentException("object cannot be null.");
        }
        Class class0 = object0.getClass();
        Pool pool0 = (Pool)Pools.typePools.get(class0);
        if(pool0 == null) {
            return;
        }
        pool0.free(object0);
    }

    public static void freeAll(Array array0) {
        Pools.freeAll(array0, false);
    }

    public static void freeAll(Array array0, boolean z) {
        if(array0 == null) {
            throw new IllegalArgumentException("objects cannot be null.");
        }
        int v = 0;
        int v1 = array0.size;
        Pool pool0 = null;
        while(v < v1) {
            Object object0 = array0.get(v);
            if(object0 != null) {
                if(pool0 == null) {
                    Class class0 = object0.getClass();
                    pool0 = (Pool)Pools.typePools.get(class0);
                    if(pool0 != null) {
                        goto label_11;
                    }
                }
                else {
                label_11:
                    pool0.free(object0);
                    if(!z) {
                        pool0 = null;
                    }
                }
            }
            ++v;
        }
    }

    public static Pool get(Class class0) {
        return Pools.get(class0, 100);
    }

    public static Pool get(Class class0, int v) {
        Pool pool0 = (Pool)Pools.typePools.get(class0);
        if(pool0 == null) {
            pool0 = new ReflectionPool(class0, 4, v);
            Pools.typePools.put(class0, pool0);
        }
        return pool0;
    }

    public static Object obtain(Class class0) {
        return Pools.get(class0).obtain();
    }

    public static void set(Class class0, Pool pool0) {
        Pools.typePools.put(class0, pool0);
    }
}

