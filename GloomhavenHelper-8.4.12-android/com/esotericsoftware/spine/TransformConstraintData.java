package com.esotericsoftware.spine;

import com.badlogic.gdx.utils.Array;

public class TransformConstraintData extends ConstraintData {
    final Array bones;
    boolean local;
    float mixRotate;
    float mixScaleX;
    float mixScaleY;
    float mixShearY;
    float mixX;
    float mixY;
    float offsetRotation;
    float offsetScaleX;
    float offsetScaleY;
    float offsetShearY;
    float offsetX;
    float offsetY;
    boolean relative;
    BoneData target;

    public TransformConstraintData(String s) {
        super(s);
        this.bones = new Array();
    }

    public Array getBones() {
        return this.bones;
    }

    public boolean getLocal() {
        return this.local;
    }

    public float getMixRotate() {
        return this.mixRotate;
    }

    public float getMixScaleX() {
        return this.mixScaleX;
    }

    public float getMixScaleY() {
        return this.mixScaleY;
    }

    public float getMixShearY() {
        return this.mixShearY;
    }

    public float getMixX() {
        return this.mixX;
    }

    public float getMixY() {
        return this.mixY;
    }

    public float getOffsetRotation() {
        return this.offsetRotation;
    }

    public float getOffsetScaleX() {
        return this.offsetScaleX;
    }

    public float getOffsetScaleY() {
        return this.offsetScaleY;
    }

    public float getOffsetShearY() {
        return this.offsetShearY;
    }

    public float getOffsetX() {
        return this.offsetX;
    }

    public float getOffsetY() {
        return this.offsetY;
    }

    public boolean getRelative() {
        return this.relative;
    }

    public BoneData getTarget() {
        return this.target;
    }

    public void setLocal(boolean z) {
        this.local = z;
    }

    public void setMixRotate(float f) {
        this.mixRotate = f;
    }

    public void setMixScaleX(float f) {
        this.mixScaleX = f;
    }

    public void setMixScaleY(float f) {
        this.mixScaleY = f;
    }

    public void setMixShearY(float f) {
        this.mixShearY = f;
    }

    public void setMixX(float f) {
        this.mixX = f;
    }

    public void setMixY(float f) {
        this.mixY = f;
    }

    public void setOffsetRotation(float f) {
        this.offsetRotation = f;
    }

    public void setOffsetScaleX(float f) {
        this.offsetScaleX = f;
    }

    public void setOffsetScaleY(float f) {
        this.offsetScaleY = f;
    }

    public void setOffsetShearY(float f) {
        this.offsetShearY = f;
    }

    public void setOffsetX(float f) {
        this.offsetX = f;
    }

    public void setOffsetY(float f) {
        this.offsetY = f;
    }

    public void setRelative(boolean z) {
        this.relative = z;
    }

    public void setTarget(BoneData boneData0) {
        if(boneData0 == null) {
            throw new IllegalArgumentException("target cannot be null.");
        }
        this.target = boneData0;
    }
}

