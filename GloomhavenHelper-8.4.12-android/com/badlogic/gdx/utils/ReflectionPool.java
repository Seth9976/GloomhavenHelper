package com.badlogic.gdx.utils;

import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Constructor;
import com.badlogic.gdx.utils.reflect.ReflectionException;

public class ReflectionPool extends Pool {
    private final Constructor constructor;

    public ReflectionPool(Class class0) {
        this(class0, 16, 0x7FFFFFFF);
    }

    public ReflectionPool(Class class0, int v) {
        this(class0, v, 0x7FFFFFFF);
    }

    public ReflectionPool(Class class0, int v, int v1) {
        super(v, v1);
        this.constructor = this.findConstructor(class0);
        if(this.constructor == null) {
            throw new RuntimeException("Class cannot be created (missing no-arg constructor): " + class0.getName());
        }
    }

    @Null
    private Constructor findConstructor(Class class0) {
        try {
            return ClassReflection.getConstructor(class0, null);
        }
        catch(Exception unused_ex) {
            try {
                Constructor constructor0 = ClassReflection.getDeclaredConstructor(class0, null);
                constructor0.setAccessible(true);
                return constructor0;
            }
            catch(ReflectionException unused_ex) {
                return null;
            }
        }
    }

    @Override  // com.badlogic.gdx.utils.Pool
    protected Object newObject() {
        try {
            return this.constructor.newInstance(null);
        }
        catch(Exception exception0) {
            throw new GdxRuntimeException("Unable to create new instance: " + this.constructor.getDeclaringClass().getName(), exception0);
        }
    }
}

