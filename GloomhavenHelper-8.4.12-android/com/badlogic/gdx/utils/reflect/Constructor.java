package com.badlogic.gdx.utils.reflect;

import java.lang.reflect.InvocationTargetException;

public final class Constructor {
    private final java.lang.reflect.Constructor constructor;

    Constructor(java.lang.reflect.Constructor constructor0) {
        this.constructor = constructor0;
    }

    public Class getDeclaringClass() {
        return this.constructor.getDeclaringClass();
    }

    public Class[] getParameterTypes() {
        return this.constructor.getParameterTypes();
    }

    public boolean isAccessible() {
        return this.constructor.isAccessible();
    }

    public Object newInstance(Object[] arr_object) throws ReflectionException {
        try {
            return this.constructor.newInstance(arr_object);
        }
        catch(IllegalArgumentException illegalArgumentException0) {
            throw new ReflectionException("Illegal argument(s) supplied to constructor for class: " + this.getDeclaringClass().getName(), illegalArgumentException0);
        }
        catch(InstantiationException instantiationException0) {
            throw new ReflectionException("Could not instantiate instance of class: " + this.getDeclaringClass().getName(), instantiationException0);
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new ReflectionException("Could not instantiate instance of class: " + this.getDeclaringClass().getName(), illegalAccessException0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            throw new ReflectionException("Exception occurred in constructor for class: " + this.getDeclaringClass().getName(), invocationTargetException0);
        }
    }

    public void setAccessible(boolean z) {
        this.constructor.setAccessible(z);
    }
}

