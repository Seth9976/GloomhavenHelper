package com.badlogic.gdx.utils;

import com.badlogic.gdx.math.Vector2;

public abstract class Scaling {
    public static final Scaling fill;
    public static final Scaling fillX;
    public static final Scaling fillY;
    public static final Scaling fit;
    public static final Scaling none;
    public static final Scaling stretch;
    public static final Scaling stretchX;
    public static final Scaling stretchY;
    protected static final Vector2 temp;

    static {
        Scaling.temp = new Vector2();
        Scaling.fit = new Scaling() {
            @Override  // com.badlogic.gdx.utils.Scaling
            public Vector2 apply(float f, float f1, float f2, float f3) {
                float f4 = f3 / f2 > f1 / f ? f2 / f : f3 / f1;
                com.badlogic.gdx.utils.Scaling.1.temp.x = f * f4;
                com.badlogic.gdx.utils.Scaling.1.temp.y = f1 * f4;
                return com.badlogic.gdx.utils.Scaling.1.temp;
            }
        };
        Scaling.fill = new Scaling() {
            @Override  // com.badlogic.gdx.utils.Scaling
            public Vector2 apply(float f, float f1, float f2, float f3) {
                float f4 = f3 / f2 < f1 / f ? f2 / f : f3 / f1;
                com.badlogic.gdx.utils.Scaling.2.temp.x = f * f4;
                com.badlogic.gdx.utils.Scaling.2.temp.y = f1 * f4;
                return com.badlogic.gdx.utils.Scaling.2.temp;
            }
        };
        Scaling.fillX = new Scaling() {
            @Override  // com.badlogic.gdx.utils.Scaling
            public Vector2 apply(float f, float f1, float f2, float f3) {
                float f4 = f2 / f;
                com.badlogic.gdx.utils.Scaling.3.temp.x = f * f4;
                com.badlogic.gdx.utils.Scaling.3.temp.y = f1 * f4;
                return com.badlogic.gdx.utils.Scaling.3.temp;
            }
        };
        Scaling.fillY = new Scaling() {
            @Override  // com.badlogic.gdx.utils.Scaling
            public Vector2 apply(float f, float f1, float f2, float f3) {
                float f4 = f3 / f1;
                com.badlogic.gdx.utils.Scaling.4.temp.x = f * f4;
                com.badlogic.gdx.utils.Scaling.4.temp.y = f1 * f4;
                return com.badlogic.gdx.utils.Scaling.4.temp;
            }
        };
        Scaling.stretch = new Scaling() {
            @Override  // com.badlogic.gdx.utils.Scaling
            public Vector2 apply(float f, float f1, float f2, float f3) {
                com.badlogic.gdx.utils.Scaling.5.temp.x = f2;
                com.badlogic.gdx.utils.Scaling.5.temp.y = f3;
                return com.badlogic.gdx.utils.Scaling.5.temp;
            }
        };
        Scaling.stretchX = new Scaling() {
            @Override  // com.badlogic.gdx.utils.Scaling
            public Vector2 apply(float f, float f1, float f2, float f3) {
                com.badlogic.gdx.utils.Scaling.6.temp.x = f2;
                com.badlogic.gdx.utils.Scaling.6.temp.y = f1;
                return com.badlogic.gdx.utils.Scaling.6.temp;
            }
        };
        Scaling.stretchY = new Scaling() {
            @Override  // com.badlogic.gdx.utils.Scaling
            public Vector2 apply(float f, float f1, float f2, float f3) {
                com.badlogic.gdx.utils.Scaling.7.temp.x = f;
                com.badlogic.gdx.utils.Scaling.7.temp.y = f3;
                return com.badlogic.gdx.utils.Scaling.7.temp;
            }
        };
        Scaling.none = new Scaling() {
            @Override  // com.badlogic.gdx.utils.Scaling
            public Vector2 apply(float f, float f1, float f2, float f3) {
                com.badlogic.gdx.utils.Scaling.8.temp.x = f;
                com.badlogic.gdx.utils.Scaling.8.temp.y = f1;
                return com.badlogic.gdx.utils.Scaling.8.temp;
            }
        };
    }

    public abstract Vector2 apply(float arg1, float arg2, float arg3, float arg4);
}

