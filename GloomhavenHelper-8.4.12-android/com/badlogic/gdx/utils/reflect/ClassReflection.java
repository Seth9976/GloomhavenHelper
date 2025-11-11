package com.badlogic.gdx.utils.reflect;

import java.lang.reflect.Modifier;

public final class ClassReflection {
    public static Class forName(String s) throws ReflectionException {
        try {
            return Class.forName(s);
        }
        catch(ClassNotFoundException classNotFoundException0) {
            throw new ReflectionException("Class not found: " + s, classNotFoundException0);
        }
    }

    public static Annotation getAnnotation(Class class0, Class class1) {
        java.lang.annotation.Annotation annotation0 = class0.getAnnotation(class1);
        return annotation0 == null ? null : new Annotation(annotation0);
    }

    public static Annotation[] getAnnotations(Class class0) {
        java.lang.annotation.Annotation[] arr_annotation = class0.getAnnotations();
        Annotation[] arr_annotation1 = new Annotation[arr_annotation.length];
        for(int v = 0; v < arr_annotation.length; ++v) {
            arr_annotation1[v] = new Annotation(arr_annotation[v]);
        }
        return arr_annotation1;
    }

    public static Class getComponentType(Class class0) {
        return class0.getComponentType();
    }

    public static Constructor getConstructor(Class class0, Class[] arr_class) throws ReflectionException {
        try {
            return new Constructor(class0.getConstructor(arr_class));
        }
        catch(SecurityException securityException0) {
            throw new ReflectionException("Security violation occurred while getting constructor for class: \'" + class0.getName() + "\'.", securityException0);
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            throw new ReflectionException("Constructor not found for class: " + class0.getName(), noSuchMethodException0);
        }
    }

    public static Constructor[] getConstructors(Class class0) {
        java.lang.reflect.Constructor[] arr_constructor = class0.getConstructors();
        Constructor[] arr_constructor1 = new Constructor[arr_constructor.length];
        for(int v = 0; v < arr_constructor.length; ++v) {
            arr_constructor1[v] = new Constructor(arr_constructor[v]);
        }
        return arr_constructor1;
    }

    public static Annotation getDeclaredAnnotation(Class class0, Class class1) {
        java.lang.annotation.Annotation[] arr_annotation = class0.getDeclaredAnnotations();
        for(int v = 0; v < arr_annotation.length; ++v) {
            java.lang.annotation.Annotation annotation0 = arr_annotation[v];
            if(annotation0.annotationType().equals(class1)) {
                return new Annotation(annotation0);
            }
        }
        return null;
    }

    public static Annotation[] getDeclaredAnnotations(Class class0) {
        java.lang.annotation.Annotation[] arr_annotation = class0.getDeclaredAnnotations();
        Annotation[] arr_annotation1 = new Annotation[arr_annotation.length];
        for(int v = 0; v < arr_annotation.length; ++v) {
            arr_annotation1[v] = new Annotation(arr_annotation[v]);
        }
        return arr_annotation1;
    }

    public static Constructor getDeclaredConstructor(Class class0, Class[] arr_class) throws ReflectionException {
        try {
            return new Constructor(class0.getDeclaredConstructor(arr_class));
        }
        catch(SecurityException securityException0) {
            throw new ReflectionException("Security violation while getting constructor for class: " + class0.getName(), securityException0);
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            throw new ReflectionException("Constructor not found for class: " + class0.getName(), noSuchMethodException0);
        }
    }

    public static Field getDeclaredField(Class class0, String s) throws ReflectionException {
        try {
            return new Field(class0.getDeclaredField(s));
        }
        catch(SecurityException securityException0) {
            throw new ReflectionException("Security violation while getting field: " + s + ", for class: " + class0.getName(), securityException0);
        }
        catch(NoSuchFieldException noSuchFieldException0) {
            throw new ReflectionException("Field not found: " + s + ", for class: " + class0.getName(), noSuchFieldException0);
        }
    }

    public static Field[] getDeclaredFields(Class class0) {
        java.lang.reflect.Field[] arr_field = class0.getDeclaredFields();
        Field[] arr_field1 = new Field[arr_field.length];
        for(int v = 0; v < arr_field.length; ++v) {
            arr_field1[v] = new Field(arr_field[v]);
        }
        return arr_field1;
    }

    public static Method getDeclaredMethod(Class class0, String s, Class[] arr_class) throws ReflectionException {
        try {
            return new Method(class0.getDeclaredMethod(s, arr_class));
        }
        catch(SecurityException securityException0) {
            throw new ReflectionException("Security violation while getting method: " + s + ", for class: " + class0.getName(), securityException0);
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            throw new ReflectionException("Method not found: " + s + ", for class: " + class0.getName(), noSuchMethodException0);
        }
    }

    public static Method[] getDeclaredMethods(Class class0) {
        java.lang.reflect.Method[] arr_method = class0.getDeclaredMethods();
        Method[] arr_method1 = new Method[arr_method.length];
        for(int v = 0; v < arr_method.length; ++v) {
            arr_method1[v] = new Method(arr_method[v]);
        }
        return arr_method1;
    }

    public static Object[] getEnumConstants(Class class0) {
        return class0.getEnumConstants();
    }

    public static Field getField(Class class0, String s) throws ReflectionException {
        try {
            return new Field(class0.getField(s));
        }
        catch(SecurityException securityException0) {
            throw new ReflectionException("Security violation while getting field: " + s + ", for class: " + class0.getName(), securityException0);
        }
        catch(NoSuchFieldException noSuchFieldException0) {
            throw new ReflectionException("Field not found: " + s + ", for class: " + class0.getName(), noSuchFieldException0);
        }
    }

    public static Field[] getFields(Class class0) {
        java.lang.reflect.Field[] arr_field = class0.getFields();
        Field[] arr_field1 = new Field[arr_field.length];
        for(int v = 0; v < arr_field.length; ++v) {
            arr_field1[v] = new Field(arr_field[v]);
        }
        return arr_field1;
    }

    public static Class[] getInterfaces(Class class0) {
        return class0.getInterfaces();
    }

    public static Method getMethod(Class class0, String s, Class[] arr_class) throws ReflectionException {
        try {
            return new Method(class0.getMethod(s, arr_class));
        }
        catch(SecurityException securityException0) {
            throw new ReflectionException("Security violation while getting method: " + s + ", for class: " + class0.getName(), securityException0);
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            throw new ReflectionException("Method not found: " + s + ", for class: " + class0.getName(), noSuchMethodException0);
        }
    }

    public static Method[] getMethods(Class class0) {
        java.lang.reflect.Method[] arr_method = class0.getMethods();
        Method[] arr_method1 = new Method[arr_method.length];
        for(int v = 0; v < arr_method.length; ++v) {
            arr_method1[v] = new Method(arr_method[v]);
        }
        return arr_method1;
    }

    public static String getSimpleName(Class class0) {
        return class0.getSimpleName();
    }

    public static boolean isAbstract(Class class0) {
        return Modifier.isAbstract(class0.getModifiers());
    }

    public static boolean isAnnotation(Class class0) {
        return class0.isAnnotation();
    }

    public static boolean isAnnotationPresent(Class class0, Class class1) {
        return class0.isAnnotationPresent(class1);
    }

    public static boolean isArray(Class class0) {
        return class0.isArray();
    }

    public static boolean isAssignableFrom(Class class0, Class class1) {
        return class0.isAssignableFrom(class1);
    }

    public static boolean isEnum(Class class0) {
        return class0.isEnum();
    }

    public static boolean isInstance(Class class0, Object object0) {
        return class0.isInstance(object0);
    }

    public static boolean isInterface(Class class0) {
        return class0.isInterface();
    }

    public static boolean isMemberClass(Class class0) {
        return class0.isMemberClass();
    }

    public static boolean isPrimitive(Class class0) {
        return class0.isPrimitive();
    }

    public static boolean isStaticClass(Class class0) {
        return Modifier.isStatic(class0.getModifiers());
    }

    public static Object newInstance(Class class0) throws ReflectionException {
        try {
            return class0.newInstance();
        }
        catch(InstantiationException instantiationException0) {
            throw new ReflectionException("Could not instantiate instance of class: " + class0.getName(), instantiationException0);
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new ReflectionException("Could not instantiate instance of class: " + class0.getName(), illegalAccessException0);
        }
    }
}

