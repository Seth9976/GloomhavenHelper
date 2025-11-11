package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface Drawable {
    void draw(Batch arg1, float arg2, float arg3, float arg4, float arg5);

    float getBottomHeight();

    float getLeftWidth();

    float getMinHeight();

    float getMinWidth();

    float getRightWidth();

    float getTopHeight();

    void setBottomHeight(float arg1);

    void setLeftWidth(float arg1);

    void setMinHeight(float arg1);

    void setMinWidth(float arg1);

    void setRightWidth(float arg1);

    void setTopHeight(float arg1);
}

