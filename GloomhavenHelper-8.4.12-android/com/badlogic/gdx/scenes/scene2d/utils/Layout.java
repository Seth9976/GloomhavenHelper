package com.badlogic.gdx.scenes.scene2d.utils;

public interface Layout {
    float getMaxHeight();

    float getMaxWidth();

    float getMinHeight();

    float getMinWidth();

    float getPrefHeight();

    float getPrefWidth();

    void invalidate();

    void invalidateHierarchy();

    void layout();

    void pack();

    void setFillParent(boolean arg1);

    void setLayoutEnabled(boolean arg1);

    void validate();
}

