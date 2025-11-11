package com.badlogic.gdx.math;

public interface Path {
   Object derivativeAt(Object var1, float var2);

   Object valueAt(Object var1, float var2);

   float approximate(Object var1);

   float locate(Object var1);

   float approxLength(int var1);
}
