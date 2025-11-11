package com.badlogic.gdx.utils.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public final class Method {
    private final java.lang.reflect.Method method;

    Method(java.lang.reflect.Method method0) {
        this.method = method0;
    }

    public Annotation getDeclaredAnnotation(Class class0) {
        java.lang.annotation.Annotation[] arr_annotation = this.method.getDeclaredAnnotations();
        if(arr_annotation == null) {
            return null;
        }
        for(int v = 0; v < arr_annotation.length; ++v) {
            java.lang.annotation.Annotation annotation0 = arr_annotation[v];
            if(annotation0.annotationType().equals(class0)) {
                return new Annotation(annotation0);
            }
        }
        return null;
    }

    public Annotation[] getDeclaredAnnotations() {
        java.lang.annotation.Annotation[] arr_annotation = this.method.getDeclaredAnnotations();
        Annotation[] arr_annotation1 = new Annotation[arr_annotation.length];
        for(int v = 0; v < arr_annotation.length; ++v) {
            arr_annotation1[v] = new Annotation(arr_annotation[v]);
        }
        return arr_annotation1;
    }

    public Class getDeclaringClass() {
        return this.method.getDeclaringClass();
    }

    public String getName() {
        return this.method.getName();
    }

    public Class[] getParameterTypes() {
        return this.method.getParameterTypes();
    }

    public Class getReturnType() {
        return this.method.getReturnType();
    }

    public Object invoke(Object object0, Object[] arr_object) throws ReflectionException {
        try {
            return this.method.invoke(object0, arr_object);
        }
        catch(IllegalArgumentException illegalArgumentException0) {
            throw new ReflectionException("Illegal argument(s) supplied to method: " + this.getName(), illegalArgumentException0);
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new ReflectionException("Illegal access to method: " + this.getName(), illegalAccessException0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            throw new ReflectionException("Exception occurred in method: " + this.getName(), invocationTargetException0);
        }
    }

    public boolean isAbstract() {
        return Modifier.isAbstract(this.method.getModifiers());
    }

    public boolean isAccessible() {
        return this.method.isAccessible();
    }

    public boolean isAnnotationPresent(Class class0) {
        return this.method.isAnnotationPresent(class0);
    }

    // 去混淆评级： 低(30)
    public boolean isDefaultAccess() {
        return !this.isPrivate() && !this.isProtected() && !this.isPublic();
    }

    public boolean isFinal() {
        return Modifier.isFinal(this.method.getModifiers());
    }

    public boolean isNative() {
        return Modifier.isNative(this.method.getModifiers());
    }

    public boolean isPrivate() {
        return Modifier.isPrivate(this.method.getModifiers());
    }

    public boolean isProtected() {
        return Modifier.isProtected(this.method.getModifiers());
    }

    public boolean isPublic() {
        return Modifier.isPublic(this.method.getModifiers());
    }

    public boolean isStatic() {
        return Modifier.isStatic(this.method.getModifiers());
    }

    public boolean isVarArgs() {
        return this.method.isVarArgs();
    }

    public void setAccessible(boolean z) {
        this.method.setAccessible(z);
    }
}

