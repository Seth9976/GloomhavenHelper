package com.esotericsoftware.spine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Null;

public class SlotData {
    @Null
    String attachmentName;
    BlendMode blendMode;
    final BoneData boneData;
    final Color color;
    @Null
    Color darkColor;
    final int index;
    final String name;

    public SlotData(int v, String s, BoneData boneData0) {
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        if(v < 0) {
            throw new IllegalArgumentException("index must be >= 0.");
        }
        if(s == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        if(boneData0 == null) {
            throw new IllegalArgumentException("boneData cannot be null.");
        }
        this.index = v;
        this.name = s;
        this.boneData = boneData0;
    }

    @Null
    public String getAttachmentName() {
        return this.attachmentName;
    }

    public BlendMode getBlendMode() {
        return this.blendMode;
    }

    public BoneData getBoneData() {
        return this.boneData;
    }

    public Color getColor() {
        return this.color;
    }

    @Null
    public Color getDarkColor() {
        return this.darkColor;
    }

    public int getIndex() {
        return this.index;
    }

    public String getName() {
        return this.name;
    }

    public void setAttachmentName(@Null String s) {
        this.attachmentName = s;
    }

    public void setBlendMode(BlendMode blendMode0) {
        if(blendMode0 == null) {
            throw new IllegalArgumentException("blendMode cannot be null.");
        }
        this.blendMode = blendMode0;
    }

    public void setDarkColor(@Null Color color0) {
        this.darkColor = color0;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

