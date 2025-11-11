package com.badlogic.gdx.utils.reflect;

public final class Annotation {
    private java.lang.annotation.Annotation annotation;

    Annotation(java.lang.annotation.Annotation annotation0) {
        this.annotation = annotation0;
    }

    // 去混淆评级： 低(20)
    public java.lang.annotation.Annotation getAnnotation(Class class0) {
        return this.annotation.annotationType().equals(class0) ? this.annotation : null;
    }

    public Class getAnnotationType() {
        return this.annotation.annotationType();
    }
}

