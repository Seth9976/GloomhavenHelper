package com.hm.spine;

import com.badlogic.gdx.utils.Array;

public class PathConstraintData extends ConstraintData {
   final Array bones = new Array();
   SlotData target;
   PathConstraintData.PositionMode positionMode;
   PathConstraintData.SpacingMode spacingMode;
   PathConstraintData.RotateMode rotateMode;
   float offsetRotation;
   float position;
   float spacing;
   float mixRotate;
   float mixX;
   float mixY;

   public PathConstraintData(String name) {
      super(name);
   }

   public Array getBones() {
      return this.bones;
   }

   public SlotData getTarget() {
      return this.target;
   }

   public void setTarget(SlotData target) {
      if (target == null) {
         throw new IllegalArgumentException("target cannot be null.");
      } else {
         this.target = target;
      }
   }

   public PathConstraintData.PositionMode getPositionMode() {
      return this.positionMode;
   }

   public void setPositionMode(PathConstraintData.PositionMode positionMode) {
      if (positionMode == null) {
         throw new IllegalArgumentException("positionMode cannot be null.");
      } else {
         this.positionMode = positionMode;
      }
   }

   public PathConstraintData.SpacingMode getSpacingMode() {
      return this.spacingMode;
   }

   public void setSpacingMode(PathConstraintData.SpacingMode spacingMode) {
      if (spacingMode == null) {
         throw new IllegalArgumentException("spacingMode cannot be null.");
      } else {
         this.spacingMode = spacingMode;
      }
   }

   public PathConstraintData.RotateMode getRotateMode() {
      return this.rotateMode;
   }

   public void setRotateMode(PathConstraintData.RotateMode rotateMode) {
      if (rotateMode == null) {
         throw new IllegalArgumentException("rotateMode cannot be null.");
      } else {
         this.rotateMode = rotateMode;
      }
   }

   public float getOffsetRotation() {
      return this.offsetRotation;
   }

   public void setOffsetRotation(float offsetRotation) {
      this.offsetRotation = offsetRotation;
   }

   public float getPosition() {
      return this.position;
   }

   public void setPosition(float position) {
      this.position = position;
   }

   public float getSpacing() {
      return this.spacing;
   }

   public void setSpacing(float spacing) {
      this.spacing = spacing;
   }

   public float getMixRotate() {
      return this.mixRotate;
   }

   public void setMixRotate(float mixRotate) {
      this.mixRotate = mixRotate;
   }

   public float getMixX() {
      return this.mixX;
   }

   public void setMixX(float mixX) {
      this.mixX = mixX;
   }

   public float getMixY() {
      return this.mixY;
   }

   public void setMixY(float mixY) {
      this.mixY = mixY;
   }

   public static enum PositionMode {
      fixed,
      percent;

      public static final PathConstraintData.PositionMode[] values = values();
   }

   public static enum RotateMode {
      tangent,
      chain,
      chainScale;

      public static final PathConstraintData.RotateMode[] values = values();
   }

   public static enum SpacingMode {
      length,
      fixed,
      percent,
      proportional;

      public static final PathConstraintData.SpacingMode[] values = values();
   }
}
