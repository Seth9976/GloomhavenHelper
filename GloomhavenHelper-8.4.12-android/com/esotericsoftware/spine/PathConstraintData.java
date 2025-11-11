package com.esotericsoftware.spine;

import com.badlogic.gdx.utils.Array;

public class PathConstraintData extends ConstraintData {
    public static enum PositionMode {
        fixed,
        percent;

        public static final PositionMode[] values;

        static {
            PositionMode.values = (PositionMode[])PositionMode.$VALUES.clone();
        }
    }

    public static enum RotateMode {
        tangent,
        chain,
        chainScale;

        public static final RotateMode[] values;

        static {
            RotateMode.values = (RotateMode[])RotateMode.$VALUES.clone();
        }
    }

    public static enum SpacingMode {
        length,
        fixed,
        percent,
        proportional;

        public static final SpacingMode[] values;

        static {
            SpacingMode.values = (SpacingMode[])SpacingMode.$VALUES.clone();
        }
    }

    final Array bones;
    float mixRotate;
    float mixX;
    float mixY;
    float offsetRotation;
    float position;
    PositionMode positionMode;
    RotateMode rotateMode;
    float spacing;
    SpacingMode spacingMode;
    SlotData target;

    public PathConstraintData(String s) {
        super(s);
        this.bones = new Array();
    }

    public Array getBones() {
        return this.bones;
    }

    public float getMixRotate() {
        return this.mixRotate;
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

    public float getPosition() {
        return this.position;
    }

    public PositionMode getPositionMode() {
        return this.positionMode;
    }

    public RotateMode getRotateMode() {
        return this.rotateMode;
    }

    public float getSpacing() {
        return this.spacing;
    }

    public SpacingMode getSpacingMode() {
        return this.spacingMode;
    }

    public SlotData getTarget() {
        return this.target;
    }

    public void setMixRotate(float f) {
        this.mixRotate = f;
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

    public void setPosition(float f) {
        this.position = f;
    }

    public void setPositionMode(PositionMode pathConstraintData$PositionMode0) {
        if(pathConstraintData$PositionMode0 == null) {
            throw new IllegalArgumentException("positionMode cannot be null.");
        }
        this.positionMode = pathConstraintData$PositionMode0;
    }

    public void setRotateMode(RotateMode pathConstraintData$RotateMode0) {
        if(pathConstraintData$RotateMode0 == null) {
            throw new IllegalArgumentException("rotateMode cannot be null.");
        }
        this.rotateMode = pathConstraintData$RotateMode0;
    }

    public void setSpacing(float f) {
        this.spacing = f;
    }

    public void setSpacingMode(SpacingMode pathConstraintData$SpacingMode0) {
        if(pathConstraintData$SpacingMode0 == null) {
            throw new IllegalArgumentException("spacingMode cannot be null.");
        }
        this.spacingMode = pathConstraintData$SpacingMode0;
    }

    public void setTarget(SlotData slotData0) {
        if(slotData0 == null) {
            throw new IllegalArgumentException("target cannot be null.");
        }
        this.target = slotData0;
    }
}

