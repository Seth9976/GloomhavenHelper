package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasSprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureRegionDrawable extends BaseDrawable implements TransformDrawable {
    private TextureRegion region;

    public TextureRegionDrawable() {
    }

    public TextureRegionDrawable(Texture texture0) {
        this.setRegion(new TextureRegion(texture0));
    }

    public TextureRegionDrawable(TextureRegion textureRegion0) {
        this.setRegion(textureRegion0);
    }

    public TextureRegionDrawable(TextureRegionDrawable textureRegionDrawable0) {
        super(textureRegionDrawable0);
        this.setRegion(textureRegionDrawable0.region);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable, com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public void draw(Batch batch0, float f, float f1, float f2, float f3) {
        batch0.draw(this.region, f, f1, f2, f3);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable
    public void draw(Batch batch0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        batch0.draw(this.region, f, f1, f2, f3, f4, f5, f6, f7, f8);
    }

    public TextureRegion getRegion() {
        return this.region;
    }

    public void setRegion(TextureRegion textureRegion0) {
        this.region = textureRegion0;
        if(textureRegion0 != null) {
            this.setMinWidth(((float)textureRegion0.getRegionWidth()));
            this.setMinHeight(((float)textureRegion0.getRegionHeight()));
        }
    }

    public Drawable tint(Color color0) {
        TextureRegion textureRegion0 = this.region;
        Sprite sprite0 = textureRegion0 instanceof AtlasRegion ? new AtlasSprite(((AtlasRegion)textureRegion0)) : new Sprite(textureRegion0);
        sprite0.setColor(color0);
        sprite0.setSize(this.getMinWidth(), this.getMinHeight());
        Drawable drawable0 = new SpriteDrawable(sprite0);
        ((SpriteDrawable)drawable0).setLeftWidth(this.getLeftWidth());
        ((SpriteDrawable)drawable0).setRightWidth(this.getRightWidth());
        ((SpriteDrawable)drawable0).setTopHeight(this.getTopHeight());
        ((SpriteDrawable)drawable0).setBottomHeight(this.getBottomHeight());
        return drawable0;
    }
}

