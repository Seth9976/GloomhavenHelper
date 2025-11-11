package com.badlogic.gdx.utils.reflect;

import java.lang.reflect.Array;

public final class ArrayReflection {
    public static Object get(Object object0, int v) {
        return Array.get(object0, v);
    }

    public static int getLength(Object object0) {
        return Array.getLength(object0);
    }

    public static Object newInstance(Class class0, int v) {
        return Array.newInstance(class0, v);
    }

    public static void set(Object object0, int v, Object object1) {
        Array.set(object0, v, object1);
    }
}

