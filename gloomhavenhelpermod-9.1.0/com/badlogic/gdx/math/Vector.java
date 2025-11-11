package com.badlogic.gdx.math;

public interface Vector {
   Vector cpy();

   float len();

   float len2();

   Vector limit(float var1);

   Vector limit2(float var1);

   Vector setLength(float var1);

   Vector setLength2(float var1);

   Vector clamp(float var1, float var2);

   Vector set(Vector var1);

   Vector sub(Vector var1);

   Vector nor();

   Vector add(Vector var1);

   float dot(Vector var1);

   Vector scl(float var1);

   Vector scl(Vector var1);

   float dst(Vector var1);

   float dst2(Vector var1);

   Vector lerp(Vector var1, float var2);

   Vector interpolate(Vector var1, float var2, Interpolation var3);

   Vector setToRandomDirection();

   boolean isUnit();

   boolean isUnit(float var1);

   boolean isZero();

   boolean isZero(float var1);

   boolean isOnLine(Vector var1, float var2);

   boolean isOnLine(Vector var1);

   boolean isCollinear(Vector var1, float var2);

   boolean isCollinear(Vector var1);

   boolean isCollinearOpposite(Vector var1, float var2);

   boolean isCollinearOpposite(Vector var1);

   boolean isPerpendicular(Vector var1);

   boolean isPerpendicular(Vector var1, float var2);

   boolean hasSameDirection(Vector var1);

   boolean hasOppositeDirection(Vector var1);

   boolean epsilonEquals(Vector var1, float var2);

   Vector mulAdd(Vector var1, float var2);

   Vector mulAdd(Vector var1, Vector var2);

   Vector setZero();
}
