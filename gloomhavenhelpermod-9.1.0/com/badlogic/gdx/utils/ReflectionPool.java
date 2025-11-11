package com.badlogic.gdx.utils;

import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Constructor;
import com.badlogic.gdx.utils.reflect.ReflectionException;

public class ReflectionPool extends Pool {
   private final Constructor constructor;

   public ReflectionPool(Class type) {
      this(type, 16, Integer.MAX_VALUE);
   }

   public ReflectionPool(Class type, int initialCapacity) {
      this(type, initialCapacity, Integer.MAX_VALUE);
   }

   public ReflectionPool(Class type, int initialCapacity, int max) {
      super(initialCapacity, max);
      this.constructor = this.findConstructor(type);
      if (this.constructor == null) {
         throw new RuntimeException("Class cannot be created (missing no-arg constructor): " + type.getName());
      }
   }

   @Null
   private Constructor findConstructor(Class type) {
      try {
         return ClassReflection.getConstructor(type, (Class[])null);
      } catch (Exception var5) {
         try {
            Constructor constructor = ClassReflection.getDeclaredConstructor(type, (Class[])null);
            constructor.setAccessible(true);
            return constructor;
         } catch (ReflectionException var4) {
            return null;
         }
      }
   }

   @Override
   protected Object newObject() {
      try {
         return this.constructor.newInstance((Object[])null);
      } catch (Exception var2) {
         throw new GdxRuntimeException("Unable to create new instance: " + this.constructor.getDeclaringClass().getName(), var2);
      }
   }
}
