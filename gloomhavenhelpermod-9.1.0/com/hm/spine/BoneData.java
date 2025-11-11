package com.hm.spine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Null;

public class BoneData {
   final int index;
   final String name;
   @Null
   final BoneData parent;
   float length;
   float x;
   float y;
   float rotation;
   float scaleX = 1.0F;
   float scaleY = 1.0F;
   float shearX;
   BoneData.TransformMode transformMode = BoneData.TransformMode.normal;
   float shearY;
   boolean skinRequired;
   final Color color = new Color(0.61F, 0.61F, 0.61F, 1.0F);

   public BoneData(int index, String name, @Null BoneData parent) {
      if (index < 0) {
         throw new IllegalArgumentException("index must be >= 0.");
      } else if (name == null) {
         throw new IllegalArgumentException("name cannot be null.");
      } else {
         this.index = index;
         this.name = name;
         this.parent = parent;
      }
   }

   public BoneData(BoneData bone, @Null BoneData parent) {
      if (bone == null) {
         throw new IllegalArgumentException("bone cannot be null.");
      } else {
         this.index = bone.index;
         this.name = bone.name;
         this.parent = parent;
         this.length = bone.length;
         this.x = bone.x;
         this.y = bone.y;
         this.rotation = bone.rotation;
         this.scaleX = bone.scaleX;
         this.scaleY = bone.scaleY;
         this.shearX = bone.shearX;
         this.shearY = bone.shearY;
      }
   }

   public int getIndex() {
      return this.index;
   }

   public String getName() {
      return this.name;
   }

   @Null
   public BoneData getParent() {
      return this.parent;
   }

   public float getLength() {
      return this.length;
   }

   public void setLength(float length) {
      this.length = length;
   }

   public float getX() {
      return this.x;
   }

   public void setX(float x) {
      this.x = x;
   }

   public float getY() {
      return this.y;
   }

   public void setY(float y) {
      this.y = y;
   }

   public void setPosition(float x, float y) {
      this.x = x;
      this.y = y;
   }

   public float getRotation() {
      return this.rotation;
   }

   public void setRotation(float rotation) {
      this.rotation = rotation;
   }

   public float getScaleX() {
      return this.scaleX;
   }

   public void setScaleX(float scaleX) {
      this.scaleX = scaleX;
   }

   public float getScaleY() {
      return this.scaleY;
   }

   public void setScaleY(float scaleY) {
      this.scaleY = scaleY;
   }

   public void setScale(float scaleX, float scaleY) {
      this.scaleX = scaleX;
      this.scaleY = scaleY;
   }

   public float getShearX() {
      return this.shearX;
   }

   public void setShearX(float shearX) {
      this.shearX = shearX;
   }

   public float getShearY() {
      return this.shearY;
   }

   public void setShearY(float shearY) {
      this.shearY = shearY;
   }

   public BoneData.TransformMode getTransformMode() {
      return this.transformMode;
   }

   public void setTransformMode(BoneData.TransformMode transformMode) {
      if (transformMode == null) {
         throw new IllegalArgumentException("transformMode cannot be null.");
      } else {
         this.transformMode = transformMode;
      }
   }

   public boolean getSkinRequired() {
      return this.skinRequired;
   }

   public void setSkinRequired(boolean skinRequired) {
      this.skinRequired = skinRequired;
   }

   public Color getColor() {
      return this.color;
   }

   @Override
   public String toString() {
      return this.name;
   }

   public static enum TransformMode {
      normal,
      onlyTranslation,
      noRotationOrReflection,
      noScale,
      noScaleOrReflection;

      public static final BoneData.TransformMode[] values = values();
   }
}
