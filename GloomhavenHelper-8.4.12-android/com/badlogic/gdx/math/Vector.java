package com.badlogic.gdx.math;

public interface Vector {
    Vector add(Vector arg1);

    Vector clamp(float arg1, float arg2);

    Vector cpy();

    float dot(Vector arg1);

    float dst(Vector arg1);

    float dst2(Vector arg1);

    boolean epsilonEquals(Vector arg1, float arg2);

    boolean hasOppositeDirection(Vector arg1);

    boolean hasSameDirection(Vector arg1);

    Vector interpolate(Vector arg1, float arg2, Interpolation arg3);

    boolean isCollinear(Vector arg1);

    boolean isCollinear(Vector arg1, float arg2);

    boolean isCollinearOpposite(Vector arg1);

    boolean isCollinearOpposite(Vector arg1, float arg2);

    boolean isOnLine(Vector arg1);

    boolean isOnLine(Vector arg1, float arg2);

    boolean isPerpendicular(Vector arg1);

    boolean isPerpendicular(Vector arg1, float arg2);

    boolean isUnit();

    boolean isUnit(float arg1);

    boolean isZero();

    boolean isZero(float arg1);

    float len();

    float len2();

    Vector lerp(Vector arg1, float arg2);

    Vector limit(float arg1);

    Vector limit2(float arg1);

    Vector mulAdd(Vector arg1, float arg2);

    Vector mulAdd(Vector arg1, Vector arg2);

    Vector nor();

    Vector scl(float arg1);

    Vector scl(Vector arg1);

    Vector set(Vector arg1);

    Vector setLength(float arg1);

    Vector setLength2(float arg1);

    Vector setToRandomDirection();

    Vector setZero();

    Vector sub(Vector arg1);
}

