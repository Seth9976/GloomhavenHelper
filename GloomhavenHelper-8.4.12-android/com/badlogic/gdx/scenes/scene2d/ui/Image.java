package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Scaling;

public class Image extends Widget {
    private int align;
    private Drawable drawable;
    private float imageHeight;
    private float imageWidth;
    private float imageX;
    private float imageY;
    private Scaling scaling;

    public Image() {
        this(null);
    }

    public Image(Texture texture0) {
        this(new TextureRegionDrawable(new TextureRegion(texture0)));
    }

    public Image(@Null NinePatch ninePatch0) {
        this(new NinePatchDrawable(ninePatch0), Scaling.stretch, 1);
    }

    public Image(@Null TextureRegion textureRegion0) {
        this(new TextureRegionDrawable(textureRegion0), Scaling.stretch, 1);
    }

    public Image(Skin skin0, String s) {
        this(skin0.getDrawable(s), Scaling.stretch, 1);
    }

    public Image(@Null Drawable drawable0) {
        this(drawable0, Scaling.stretch, 1);
    }

    public Image(@Null Drawable drawable0, Scaling scaling0) {
        this(drawable0, scaling0, 1);
    }

    public Image(@Null Drawable drawable0, Scaling scaling0, int v) {
        this.align = 1;
        this.setDrawable(drawable0);
        this.scaling = scaling0;
        this.align = v;
        this.setSize(this.getPrefWidth(), this.getPrefHeight());
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public void draw(Batch batch0, float f) {
        this.validate();
        Color color0 = this.getColor();
        batch0.setColor(color0.r, color0.g, color0.b, color0.a * f);
        float f1 = this.getX();
        float f2 = this.getY();
        float f3 = this.getScaleX();
        float f4 = this.getScaleY();
        if(this.drawable instanceof TransformDrawable) {
            float f5 = this.getRotation();
            if(f3 != 1.0f || f4 != 1.0f || f5 != 0.0f) {
                ((TransformDrawable)this.drawable).draw(batch0, f1 + this.imageX, f2 + this.imageY, this.getOriginX() - this.imageX, this.getOriginY() - this.imageY, this.imageWidth, this.imageHeight, f3, f4, f5);
                return;
            }
        }
        Drawable drawable0 = this.drawable;
        if(drawable0 != null) {
            drawable0.draw(batch0, f1 + this.imageX, f2 + this.imageY, this.imageWidth * f3, this.imageHeight * f4);
        }
    }

    public int getAlign() {
        return this.align;
    }

    @Null
    public Drawable getDrawable() {
        return this.drawable;
    }

    public float getImageHeight() {
        return this.imageHeight;
    }

    public float getImageWidth() {
        return this.imageWidth;
    }

    public float getImageX() {
        return this.imageX;
    }

    public float getImageY() {
        return this.imageY;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public float getMinHeight() {
        return 0.0f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public float getMinWidth() {
        return 0.0f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public float getPrefHeight() {
        return this.drawable == null ? 0.0f : this.drawable.getMinHeight();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public float getPrefWidth() {
        return this.drawable == null ? 0.0f : this.drawable.getMinWidth();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public void layout() {
        Drawable drawable0 = this.drawable;
        if(drawable0 == null) {
            return;
        }
        float f = drawable0.getMinWidth();
        float f1 = this.drawable.getMinHeight();
        float f2 = this.getWidth();
        float f3 = this.getHeight();
        Vector2 vector20 = this.scaling.apply(f, f1, f2, f3);
        this.imageWidth = vector20.x;
        this.imageHeight = vector20.y;
        int v = this.align;
        if((v & 8) != 0) {
            this.imageX = 0.0f;
        }
        else if((v & 16) == 0) {
            this.imageX = (float)(((int)(f2 / 2.0f - this.imageWidth / 2.0f)));
        }
        else {
            this.imageX = (float)(((int)(f2 - this.imageWidth)));
        }
        int v1 = this.align;
        if((v1 & 2) != 0) {
            this.imageY = (float)(((int)(f3 - this.imageHeight)));
            return;
        }
        if((v1 & 4) != 0) {
            this.imageY = 0.0f;
            return;
        }
        this.imageY = (float)(((int)(f3 / 2.0f - this.imageHeight / 2.0f)));
    }

    public void setAlign(int v) {
        this.align = v;
        this.invalidate();
    }

    public void setDrawable(Skin skin0, String s) {
        this.setDrawable(skin0.getDrawable(s));
    }

    public void setDrawable(@Null Drawable drawable0) {
        if(this.drawable == drawable0) {
            return;
        }
        if(drawable0 == null) {
            this.invalidateHierarchy();
        }
        else if(this.getPrefWidth() != drawable0.getMinWidth() || this.getPrefHeight() != drawable0.getMinHeight()) {
            this.invalidateHierarchy();
        }
        this.drawable = drawable0;
    }

    public void setScaling(Scaling scaling0) {
        if(scaling0 == null) {
            throw new IllegalArgumentException("scaling cannot be null.");
        }
        this.scaling = scaling0;
        this.invalidate();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    public String toString() {
        String s = this.getName();
        if(s != null) {
            return s;
        }
        String s1 = this.getClass().getName();
        int v = s1.lastIndexOf(46);
        if(v != -1) {
            s1 = s1.substring(v + 1);
        }
        return (s1.indexOf(36) == -1 ? "" : "Image ") + s1 + ": " + this.drawable;
    }
}

