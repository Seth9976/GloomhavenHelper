package com.badlogic.gdx.utils.reflect;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public final class Field {
    private final java.lang.reflect.Field field;

    Field(java.lang.reflect.Field field0) {
        this.field = field0;
    }

    public Object get(Object object0) throws ReflectionException {
        try {
            return this.field.get(object0);
        }
        catch(IllegalArgumentException illegalArgumentException0) {
            throw new ReflectionException("Object is not an instance of " + this.getDeclaringClass(), illegalArgumentException0);
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new ReflectionException("Illegal access to field: " + this.getName(), illegalAccessException0);
        }
    }

    public Annotation getDeclaredAnnotation(Class class0) {
        java.lang.annotation.Annotation[] arr_annotation = this.field.getDeclaredAnnotations();
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
        java.lang.annotation.Annotation[] arr_annotation = this.field.getDeclaredAnnotations();
        Annotation[] arr_annotation1 = new Annotation[arr_annotation.length];
        for(int v = 0; v < arr_annotation.length; ++v) {
            arr_annotation1[v] = new Annotation(arr_annotation[v]);
        }
        return arr_annotation1;
    }

    public Class getDeclaringClass() {
        return this.field.getDeclaringClass();
    }

    public Class getElementType(int v) {
        Type type0 = this.field.getGenericType();
        if(type0 instanceof ParameterizedType) {
            Type[] arr_type = ((ParameterizedType)type0).getActualTypeArguments();
            if(arr_type.length - 1 >= v) {
                Type type1 = arr_type[v];
                if(type1 instanceof Class) {
                    return (Class)type1;
                }
                if(type1 instanceof ParameterizedType) {
                    return (Class)((ParameterizedType)type1).getRawType();
                }
                if(type1 instanceof GenericArrayType) {
                    Type type2 = ((GenericArrayType)type1).getGenericComponentType();
                    return type2 instanceof Class ? ArrayReflection.newInstance(((Class)type2), 0).getClass() : null;
                }
            }
        }
        return null;
    }

    public String getName() {
        return this.field.getName();
    }

    public Class getType() {
        return this.field.getType();
    }

    public boolean isAccessible() {
        return this.field.isAccessible();
    }

    public boolean isAnnotationPresent(Class class0) {
        return this.field.isAnnotationPresent(class0);
    }

    // 去混淆评级： 低(30)
    public boolean isDefaultAccess() {
        return !this.isPrivate() && !this.isProtected() && !this.isPublic();
    }

    public boolean isFinal() {
        return Modifier.isFinal(this.field.getModifiers());
    }

    public boolean isPrivate() {
        return Modifier.isPrivate(this.field.getModifiers());
    }

    public boolean isProtected() {
        return Modifier.isProtected(this.field.getModifiers());
    }

    public boolean isPublic() {
        return Modifier.isPublic(this.field.getModifiers());
    }

    public boolean isStatic() {
        return Modifier.isStatic(this.field.getModifiers());
    }

    public boolean isSynthetic() {
        return this.field.isSynthetic();
    }

    public boolean isTransient() {
        return Modifier.isTransient(this.field.getModifiers());
    }

    public boolean isVolatile() {
        return Modifier.isVolatile(this.field.getModifiers());
    }

    public void set(Object object0, Object object1) throws ReflectionException {
        try {
            this.field.set(object0, object1);
        }
        catch(IllegalArgumentException illegalArgumentException0) {
            throw new ReflectionException("Argument not valid for field: " + this.getName(), illegalArgumentException0);
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new ReflectionException("Illegal access to field: " + this.getName(), illegalAccessException0);
        }
    }

    public void setAccessible(boolean z) {
        this.field.setAccessible(z);
    }
}

