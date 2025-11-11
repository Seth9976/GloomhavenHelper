package com.badlogic.gdx.math;

public interface Path {
    float approxLength(int arg1);

    float approximate(Object arg1);

    Object derivativeAt(Object arg1, float arg2);

    float locate(Object arg1);

    Object valueAt(Object arg1, float arg2);
}

