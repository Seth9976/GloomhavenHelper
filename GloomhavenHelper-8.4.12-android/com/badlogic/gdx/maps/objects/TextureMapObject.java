package com.badlogic.gdx.maps.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;

public class TextureMapObject extends MapObject {
    private float originX;
    private float originY;
    private float rotation;
    private float scaleX;
    private float scaleY;
    private TextureRegion textureRegion;
    private float x;
    private float y;

    public TextureMapObject() {
        this(null);
    }

    public TextureMapObject(TextureRegion textureRegion0) {
        this.x = 0.0f;
        this.y = 0.0f;
        this.originX = 0.0f;
        this.originY = 0.0f;
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.rotation = 0.0f;
        this.textureRegion = textureRegion0;
    }

    public float getOriginX() {
        return this.originX;
    }

    public float getOriginY() {
        return this.originY;
    }

    public float getRotation() {
        return this.rotation;
    }

    public float getScaleX() {
        return this.scaleX;
    }

    public float getScaleY() {
        return this.scaleY;
    }

    public TextureRegion getTextureRegion() {
        return this.textureRegion;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setOriginX(float f) {
        this.originX = f;
    }

    public void setOriginY(float f) {
        this.originY = f;
    }

    public void setRotation(float f) {
        this.rotation = f;
    }

    public void setScaleX(float f) {
        this.scaleX = f;
    }

    public void setScaleY(float f) {
        this.scaleY = f;
    }

    public void setTextureRegion(TextureRegion textureRegion0) {
        this.textureRegion = textureRegion0;
    }

    public void setX(float f) {
        this.x = f;
    }

    public void setY(float f) {
        this.y = f;
    }
}

