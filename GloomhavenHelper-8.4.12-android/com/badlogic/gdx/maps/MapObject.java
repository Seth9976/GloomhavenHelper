package com.badlogic.gdx.maps;

import com.badlogic.gdx.graphics.Color;

public class MapObject {
    private Color color;
    private String name;
    private float opacity;
    private MapProperties properties;
    private boolean visible;

    public MapObject() {
        this.name = "";
        this.opacity = 1.0f;
        this.visible = true;
        this.properties = new MapProperties();
        this.color = Color.WHITE.cpy();
    }

    public Color getColor() {
        return this.color;
    }

    public String getName() {
        return this.name;
    }

    public float getOpacity() {
        return this.opacity;
    }

    public MapProperties getProperties() {
        return this.properties;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setColor(Color color0) {
        this.color = color0;
    }

    public void setName(String s) {
        this.name = s;
    }

    public void setOpacity(float f) {
        this.opacity = f;
    }

    public void setVisible(boolean z) {
        this.visible = z;
    }
}

