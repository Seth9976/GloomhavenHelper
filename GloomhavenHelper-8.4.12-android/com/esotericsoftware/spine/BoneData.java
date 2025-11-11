package com.esotericsoftware.spine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Null;

public class BoneData {
    public static enum TransformMode {
        normal,
        onlyTranslation,
        noRotationOrReflection,
        noScale,
        noScaleOrReflection;

        public static final TransformMode[] values;

        static {
            TransformMode.values = (TransformMode[])TransformMode.$VALUES.clone();
        }
    }

    final Color color;
    final int index;
    float length;
    final String name;
    @Null
    final BoneData parent;
    float rotation;
    float scaleX;
    float scaleY;
    float shearX;
    float shearY;
    boolean skinRequired;
    TransformMode transformMode;
    float x;
    float y;

    public BoneData(int v, String s, @Null BoneData boneData0) {
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.transformMode = TransformMode.normal;
        this.color = new Color(0.61f, 0.61f, 0.61f, 1.0f);
        if(v < 0) {
            throw new IllegalArgumentException("index must be >= 0.");
        }
        if(s == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        this.index = v;
        this.name = s;
        this.parent = boneData0;
    }

    public BoneData(BoneData boneData0, @Null BoneData boneData1) {
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.transformMode = TransformMode.normal;
        this.color = new Color(0.61f, 0.61f, 0.61f, 1.0f);
        if(boneData0 == null) {
            throw new IllegalArgumentException("bone cannot be null.");
        }
        this.index = boneData0.index;
        this.name = boneData0.name;
        this.parent = boneData1;
        this.length = boneData0.length;
        this.x = boneData0.x;
        this.y = boneData0.y;
        this.rotation = boneData0.rotation;
        this.scaleX = boneData0.scaleX;
        this.scaleY = boneData0.scaleY;
        this.shearX = boneData0.shearX;
        this.shearY = boneData0.shearY;
    }

    public Color getColor() {
        return this.color;
    }

    public int getIndex() {
        return this.index;
    }

    public float getLength() {
        return this.length;
    }

    public String getName() {
        return this.name;
    }

    @Null
    public BoneData getParent() {
        return this.parent;
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

    public float getShearX() {
        return this.shearX;
    }

    public float getShearY() {
        return this.shearY;
    }

    public boolean getSkinRequired() {
        return this.skinRequired;
    }

    public TransformMode getTransformMode() {
        return this.transformMode;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setLength(float f) {
        this.length = f;
    }

    public void setPosition(float f, float f1) {
        this.x = f;
        this.y = f1;
    }

    public void setRotation(float f) {
        this.rotation = f;
    }

    public void setScale(float f, float f1) {
        this.scaleX = f;
        this.scaleY = f1;
    }

    public void setScaleX(float f) {
        this.scaleX = f;
    }

    public void setScaleY(float f) {
        this.scaleY = f;
    }

    public void setShearX(float f) {
        this.shearX = f;
    }

    public void setShearY(float f) {
        this.shearY = f;
    }

    public void setSkinRequired(boolean z) {
        this.skinRequired = z;
    }

    public void setTransformMode(TransformMode boneData$TransformMode0) {
        if(boneData$TransformMode0 == null) {
            throw new IllegalArgumentException("transformMode cannot be null.");
        }
        this.transformMode = boneData$TransformMode0;
    }

    public void setX(float f) {
        this.x = f;
    }

    public void setY(float f) {
        this.y = f;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

