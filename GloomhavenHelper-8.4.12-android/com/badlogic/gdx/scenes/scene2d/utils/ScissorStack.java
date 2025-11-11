package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.HdpiUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;

public class ScissorStack {
    private static Array scissors;
    static Vector3 tmp;
    static final Rectangle viewport;

    static {
        ScissorStack.scissors = new Array();
        ScissorStack.tmp = new Vector3();
        ScissorStack.viewport = new Rectangle();
    }

    public static void calculateScissors(Camera camera0, float f, float f1, float f2, float f3, Matrix4 matrix40, Rectangle rectangle0, Rectangle rectangle1) {
        ScissorStack.tmp.set(rectangle0.x, rectangle0.y, 0.0f);
        ScissorStack.tmp.mul(matrix40);
        camera0.project(ScissorStack.tmp, f, f1, f2, f3);
        rectangle1.x = ScissorStack.tmp.x;
        rectangle1.y = ScissorStack.tmp.y;
        ScissorStack.tmp.set(rectangle0.x + rectangle0.width, rectangle0.y + rectangle0.height, 0.0f);
        ScissorStack.tmp.mul(matrix40);
        camera0.project(ScissorStack.tmp, f, f1, f2, f3);
        rectangle1.width = ScissorStack.tmp.x - rectangle1.x;
        rectangle1.height = ScissorStack.tmp.y - rectangle1.y;
    }

    public static void calculateScissors(Camera camera0, Matrix4 matrix40, Rectangle rectangle0, Rectangle rectangle1) {
        ScissorStack.calculateScissors(camera0, 0.0f, 0.0f, ((float)Gdx.graphics.getWidth()), ((float)Gdx.graphics.getHeight()), matrix40, rectangle0, rectangle1);
    }

    private static void fix(Rectangle rectangle0) {
        rectangle0.x = (float)Math.round(rectangle0.x);
        rectangle0.y = (float)Math.round(rectangle0.y);
        rectangle0.width = (float)Math.round(rectangle0.width);
        rectangle0.height = (float)Math.round(rectangle0.height);
        if(rectangle0.width < 0.0f) {
            rectangle0.width = -rectangle0.width;
            rectangle0.x -= rectangle0.width;
        }
        if(rectangle0.height < 0.0f) {
            rectangle0.height = -rectangle0.height;
            rectangle0.y -= rectangle0.height;
        }
    }

    public static Rectangle getViewport() {
        if(ScissorStack.scissors.size == 0) {
            float f = (float)Gdx.graphics.getWidth();
            float f1 = (float)Gdx.graphics.getHeight();
            ScissorStack.viewport.set(0.0f, 0.0f, f, f1);
            return ScissorStack.viewport;
        }
        Rectangle rectangle0 = (Rectangle)ScissorStack.scissors.peek();
        ScissorStack.viewport.set(rectangle0);
        return ScissorStack.viewport;
    }

    @Null
    public static Rectangle peekScissors() {
        return ScissorStack.scissors.size == 0 ? null : ((Rectangle)ScissorStack.scissors.peek());
    }

    public static Rectangle popScissors() {
        Rectangle rectangle0 = (Rectangle)ScissorStack.scissors.pop();
        if(ScissorStack.scissors.size == 0) {
            Gdx.gl.glDisable(3089);
            return rectangle0;
        }
        Rectangle rectangle1 = (Rectangle)ScissorStack.scissors.peek();
        HdpiUtils.glScissor(((int)rectangle1.x), ((int)rectangle1.y), ((int)rectangle1.width), ((int)rectangle1.height));
        return rectangle0;
    }

    public static boolean pushScissors(Rectangle rectangle0) {
        ScissorStack.fix(rectangle0);
        if(ScissorStack.scissors.size == 0) {
            if(rectangle0.width >= 1.0f && rectangle0.height >= 1.0f) {
                Gdx.gl.glEnable(3089);
                ScissorStack.scissors.add(rectangle0);
                HdpiUtils.glScissor(((int)rectangle0.x), ((int)rectangle0.y), ((int)rectangle0.width), ((int)rectangle0.height));
                return true;
            }
            return false;
        }
        else {
            Rectangle rectangle1 = (Rectangle)ScissorStack.scissors.get(ScissorStack.scissors.size - 1);
            float f = Math.max(rectangle1.x, rectangle0.x);
            float f1 = Math.min(rectangle1.x + rectangle1.width, rectangle0.x + rectangle0.width) - f;
            if(f1 < 1.0f) {
                return false;
            }
            float f2 = Math.max(rectangle1.y, rectangle0.y);
            float f3 = Math.min(rectangle1.y + rectangle1.height, rectangle0.y + rectangle0.height) - f2;
            if(f3 < 1.0f) {
                return false;
            }
            rectangle0.x = f;
            rectangle0.y = f2;
            rectangle0.width = f1;
            rectangle0.height = Math.max(1.0f, f3);
        }
        ScissorStack.scissors.add(rectangle0);
        HdpiUtils.glScissor(((int)rectangle0.x), ((int)rectangle0.y), ((int)rectangle0.width), ((int)rectangle0.height));
        return true;
    }
}

