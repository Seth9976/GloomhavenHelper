package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.reflect.ClassReflection;

public class BaseDrawable implements Drawable {
    private float bottomHeight;
    private float leftWidth;
    private float minHeight;
    private float minWidth;
    @Null
    private String name;
    private float rightWidth;
    private float topHeight;

    public BaseDrawable() {
    }

    public BaseDrawable(Drawable drawable0) {
        if(drawable0 instanceof BaseDrawable) {
            this.name = ((BaseDrawable)drawable0).getName();
        }
        this.leftWidth = drawable0.getLeftWidth();
        this.rightWidth = drawable0.getRightWidth();
        this.topHeight = drawable0.getTopHeight();
        this.bottomHeight = drawable0.getBottomHeight();
        this.minWidth = drawable0.getMinWidth();
        this.minHeight = drawable0.getMinHeight();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public void draw(Batch batch0, float f, float f1, float f2, float f3) {
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public float getBottomHeight() {
        return this.bottomHeight;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public float getLeftWidth() {
        return this.leftWidth;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public float getMinHeight() {
        return this.minHeight;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public float getMinWidth() {
        return this.minWidth;
    }

    @Null
    public String getName() {
        return this.name;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public float getRightWidth() {
        return this.rightWidth;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public float getTopHeight() {
        return this.topHeight;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public void setBottomHeight(float f) {
        this.bottomHeight = f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public void setLeftWidth(float f) {
        this.leftWidth = f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public void setMinHeight(float f) {
        this.minHeight = f;
    }

    public void setMinSize(float f, float f1) {
        this.setMinWidth(f);
        this.setMinHeight(f1);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public void setMinWidth(float f) {
        this.minWidth = f;
    }

    public void setName(@Null String s) {
        this.name = s;
    }

    public void setPadding(float f, float f1, float f2, float f3) {
        this.setTopHeight(f);
        this.setLeftWidth(f1);
        this.setBottomHeight(f2);
        this.setRightWidth(f3);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public void setRightWidth(float f) {
        this.rightWidth = f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public void setTopHeight(float f) {
        this.topHeight = f;
    }

    @Override
    @Null
    public String toString() {
        return this.name == null ? ClassReflection.getSimpleName(this.getClass()) : this.name;
    }
}

