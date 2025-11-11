package com.badlogic.gdx.utils.reflect;

public final class Annotation {
   private java.lang.annotation.Annotation annotation;

   Annotation(java.lang.annotation.Annotation annotation) {
      this.annotation = annotation;
   }

   public java.lang.annotation.Annotation getAnnotation(Class annotationType) {
      return this.annotation.annotationType().equals(annotationType) ? this.annotation : null;
   }

   public Class getAnnotationType() {
      return this.annotation.annotationType();
   }
}
