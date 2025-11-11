package com.esotericsoftware.spine;

import com.badlogic.gdx.utils.Array;

public class IkConstraintData extends ConstraintData {
    int bendDirection;
    final Array bones;
    boolean compress;
    float mix;
    float softness;
    boolean stretch;
    BoneData target;
    boolean uniform;

    public IkConstraintData(String s) {
        super(s);
        this.bones = new Array();
        this.bendDirection = 1;
        this.mix = 1.0f;
    }

    public int getBendDirection() {
        return this.bendDirection;
    }

    public Array getBones() {
        return this.bones;
    }

    public boolean getCompress() {
        return this.compress;
    }

    public float getMix() {
        return this.mix;
    }

    public float getSoftness() {
        return this.softness;
    }

    public boolean getStretch() {
        return this.stretch;
    }

    public BoneData getTarget() {
        return this.target;
    }

    public boolean getUniform() {
        return this.uniform;
    }

    public void setBendDirection(int v) {
        this.bendDirection = v;
    }

    public void setCompress(boolean z) {
        this.compress = z;
    }

    public void setMix(float f) {
        this.mix = f;
    }

    public void setSoftness(float f) {
        this.softness = f;
    }

    public void setStretch(boolean z) {
        this.stretch = z;
    }

    public void setTarget(BoneData boneData0) {
        if(boneData0 == null) {
            throw new IllegalArgumentException("target cannot be null.");
        }
        this.target = boneData0;
    }

    public void setUniform(boolean z) {
        this.uniform = z;
    }
}

