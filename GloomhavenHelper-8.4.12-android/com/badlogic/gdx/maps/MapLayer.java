package com.badlogic.gdx.maps;

import com.badlogic.gdx.utils.GdxRuntimeException;

public class MapLayer {
    private String name;
    private MapObjects objects;
    private float offsetX;
    private float offsetY;
    private float opacity;
    private MapLayer parent;
    private MapProperties properties;
    private boolean renderOffsetDirty;
    private float renderOffsetX;
    private float renderOffsetY;
    private boolean visible;

    public MapLayer() {
        this.name = "";
        this.opacity = 1.0f;
        this.visible = true;
        this.renderOffsetDirty = true;
        this.objects = new MapObjects();
        this.properties = new MapProperties();
    }

    protected void calculateRenderOffsets() {
        MapLayer mapLayer0 = this.parent;
        if(mapLayer0 == null) {
            this.renderOffsetX = this.offsetX;
            this.renderOffsetY = this.offsetY;
        }
        else {
            mapLayer0.calculateRenderOffsets();
            this.renderOffsetX = this.parent.getRenderOffsetX() + this.offsetX;
            this.renderOffsetY = this.parent.getRenderOffsetY() + this.offsetY;
        }
        this.renderOffsetDirty = false;
    }

    public String getName() {
        return this.name;
    }

    public MapObjects getObjects() {
        return this.objects;
    }

    public float getOffsetX() {
        return this.offsetX;
    }

    public float getOffsetY() {
        return this.offsetY;
    }

    public float getOpacity() {
        return this.opacity;
    }

    public MapLayer getParent() {
        return this.parent;
    }

    public MapProperties getProperties() {
        return this.properties;
    }

    public float getRenderOffsetX() {
        if(this.renderOffsetDirty) {
            this.calculateRenderOffsets();
        }
        return this.renderOffsetX;
    }

    public float getRenderOffsetY() {
        if(this.renderOffsetDirty) {
            this.calculateRenderOffsets();
        }
        return this.renderOffsetY;
    }

    public void invalidateRenderOffset() {
        this.renderOffsetDirty = true;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setName(String s) {
        this.name = s;
    }

    public void setOffsetX(float f) {
        this.offsetX = f;
        this.invalidateRenderOffset();
    }

    public void setOffsetY(float f) {
        this.offsetY = f;
        this.invalidateRenderOffset();
    }

    public void setOpacity(float f) {
        this.opacity = f;
    }

    public void setParent(MapLayer mapLayer0) {
        if(mapLayer0 == this) {
            throw new GdxRuntimeException("Can\'t set self as the parent");
        }
        this.parent = mapLayer0;
    }

    public void setVisible(boolean z) {
        this.visible = z;
    }
}

