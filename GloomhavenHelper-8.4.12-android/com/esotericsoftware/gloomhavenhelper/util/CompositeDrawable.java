package com.esotericsoftware.gloomhavenhelper.util;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;

public class CompositeDrawable extends BaseDrawable implements TransformDrawable {
    private Drawable[] drawables;

    public CompositeDrawable(Drawable[] arr_drawable) {
        this.drawables = arr_drawable;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable, com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public void draw(Batch batch0, float f, float f1, float f2, float f3) {
        Drawable[] arr_drawable = this.drawables;
        for(int v = 0; v < arr_drawable.length; ++v) {
            arr_drawable[v].draw(batch0, f, f1, f2, f3);
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable
    public void draw(Batch batch0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        Drawable[] arr_drawable = this.drawables;
        for(int v = 0; v < arr_drawable.length; ++v) {
            ((TransformDrawable)arr_drawable[v]).draw(batch0, f, f1, f2, f3, f4, f5, f6, f7, f8);
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable, com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public float getMinHeight() {
        return this.drawables[0].getMinHeight();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable, com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public float getMinWidth() {
        return this.drawables[0].getMinWidth();
    }
}

