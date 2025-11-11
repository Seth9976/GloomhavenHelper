package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;

public class NinePatchDrawable extends BaseDrawable implements TransformDrawable {
    private NinePatch patch;

    public NinePatchDrawable() {
    }

    public NinePatchDrawable(NinePatch ninePatch0) {
        this.setPatch(ninePatch0);
    }

    public NinePatchDrawable(NinePatchDrawable ninePatchDrawable0) {
        super(ninePatchDrawable0);
        this.patch = ninePatchDrawable0.patch;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable, com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public void draw(Batch batch0, float f, float f1, float f2, float f3) {
        this.patch.draw(batch0, f, f1, f2, f3);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable
    public void draw(Batch batch0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        this.patch.draw(batch0, f, f1, f2, f3, f4, f5, f6, f7, f8);
    }

    public NinePatch getPatch() {
        return this.patch;
    }

    public void setPatch(NinePatch ninePatch0) {
        this.patch = ninePatch0;
        if(ninePatch0 != null) {
            this.setMinWidth(ninePatch0.getTotalWidth());
            this.setMinHeight(ninePatch0.getTotalHeight());
            this.setTopHeight(ninePatch0.getPadTop());
            this.setRightWidth(ninePatch0.getPadRight());
            this.setBottomHeight(ninePatch0.getPadBottom());
            this.setLeftWidth(ninePatch0.getPadLeft());
        }
    }

    public NinePatchDrawable tint(Color color0) {
        NinePatchDrawable ninePatchDrawable0 = new NinePatchDrawable(this);
        ninePatchDrawable0.patch = new NinePatch(ninePatchDrawable0.getPatch(), color0);
        return ninePatchDrawable0;
    }
}

