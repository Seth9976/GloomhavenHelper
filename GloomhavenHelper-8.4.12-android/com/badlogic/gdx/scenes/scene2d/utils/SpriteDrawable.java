package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasSprite;

public class SpriteDrawable extends BaseDrawable implements TransformDrawable {
    private Sprite sprite;

    public SpriteDrawable() {
    }

    public SpriteDrawable(Sprite sprite0) {
        this.setSprite(sprite0);
    }

    public SpriteDrawable(SpriteDrawable spriteDrawable0) {
        super(spriteDrawable0);
        this.setSprite(spriteDrawable0.sprite);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable, com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public void draw(Batch batch0, float f, float f1, float f2, float f3) {
        Color color0 = this.sprite.getColor();
        this.sprite.setColor(color0.mul(batch0.getColor()));
        this.sprite.setRotation(0.0f);
        this.sprite.setScale(1.0f, 1.0f);
        this.sprite.setBounds(f, f1, f2, f3);
        this.sprite.draw(batch0);
        this.sprite.setPackedColor(color0.toFloatBits());
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable
    public void draw(Batch batch0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        Color color0 = this.sprite.getColor();
        this.sprite.setColor(color0.mul(batch0.getColor()));
        this.sprite.setOrigin(f2, f3);
        this.sprite.setRotation(f8);
        this.sprite.setScale(f6, f7);
        this.sprite.setBounds(f, f1, f4, f5);
        this.sprite.draw(batch0);
        this.sprite.setPackedColor(color0.toFloatBits());
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public void setSprite(Sprite sprite0) {
        this.sprite = sprite0;
        this.setMinWidth(sprite0.getWidth());
        this.setMinHeight(sprite0.getHeight());
    }

    public SpriteDrawable tint(Color color0) {
        Sprite sprite0 = this.sprite;
        Sprite sprite1 = sprite0 instanceof AtlasSprite ? new AtlasSprite(((AtlasSprite)sprite0)) : new Sprite(sprite0);
        sprite1.setColor(color0);
        sprite1.setSize(this.getMinWidth(), this.getMinHeight());
        SpriteDrawable spriteDrawable0 = new SpriteDrawable(sprite1);
        spriteDrawable0.setLeftWidth(this.getLeftWidth());
        spriteDrawable0.setRightWidth(this.getRightWidth());
        spriteDrawable0.setTopHeight(this.getTopHeight());
        spriteDrawable0.setBottomHeight(this.getBottomHeight());
        return spriteDrawable0;
    }
}

