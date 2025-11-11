package com.hm.spine;

import com.badlogic.gdx.utils.Array;

public class TransformConstraintData extends ConstraintData {
   final Array bones = new Array();
   BoneData target;
   float mixRotate;
   float mixX;
   float mixY;
   float mixScaleX;
   float mixScaleY;
   float mixShearY;
   float offsetRotation;
   float offsetX;
   float offsetY;
   float offsetScaleX;
   float offsetScaleY;
   float offsetShearY;
   boolean relative;
   boolean local;

   public TransformConstraintData(String name) {
      super(name);
   }

   public Array getBones() {
      return this.bones;
   }

   public BoneData getTarget() {
      return this.target;
   }

   public void setTarget(BoneData target) {
      if (target == null) {
         throw new IllegalArgumentException("target cannot be null.");
      } else {
         this.target = target;
      }
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

   public float getMixScaleX() {
      return this.mixScaleX;
   }

   public void setMixScaleX(float mixScaleX) {
      this.mixScaleX = mixScaleX;
   }

   public float getMixScaleY() {
      return this.mixScaleY;
   }

   public void setMixScaleY(float mixScaleY) {
      this.mixScaleY = mixScaleY;
   }

   public float getMixShearY() {
      return this.mixShearY;
   }

   public void setMixShearY(float mixShearY) {
      this.mixShearY = mixShearY;
   }

   public float getOffsetRotation() {
      return this.offsetRotation;
   }

   public void setOffsetRotation(float offsetRotation) {
      this.offsetRotation = offsetRotation;
   }

   public float getOffsetX() {
      return this.offsetX;
   }

   public void setOffsetX(float offsetX) {
      this.offsetX = offsetX;
   }

   public float getOffsetY() {
      return this.offsetY;
   }

   public void setOffsetY(float offsetY) {
      this.offsetY = offsetY;
   }

   public float getOffsetScaleX() {
      return this.offsetScaleX;
   }

   public void setOffsetScaleX(float offsetScaleX) {
      this.offsetScaleX = offsetScaleX;
   }

   public float getOffsetScaleY() {
      return this.offsetScaleY;
   }

   public void setOffsetScaleY(float offsetScaleY) {
      this.offsetScaleY = offsetScaleY;
   }

   public float getOffsetShearY() {
      return this.offsetShearY;
   }

   public void setOffsetShearY(float offsetShearY) {
      this.offsetShearY = offsetShearY;
   }

   public boolean getRelative() {
      return this.relative;
   }

   public void setRelative(boolean relative) {
      this.relative = relative;
   }

   public boolean getLocal() {
      return this.local;
   }

   public void setLocal(boolean local) {
      this.local = local;
   }
}
