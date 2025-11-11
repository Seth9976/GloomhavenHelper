package com.hm.spine;

import com.badlogic.gdx.utils.Array;

public class IkConstraintData extends ConstraintData {
   final Array bones = new Array();
   BoneData target;
   boolean compress;
   boolean stretch;
   int bendDirection = 1;
   boolean uniform;
   float mix = 1.0F;
   float softness;

   public IkConstraintData(String name) {
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

   public float getMix() {
      return this.mix;
   }

   public void setMix(float mix) {
      this.mix = mix;
   }

   public float getSoftness() {
      return this.softness;
   }

   public void setSoftness(float softness) {
      this.softness = softness;
   }

   public int getBendDirection() {
      return this.bendDirection;
   }

   public void setBendDirection(int bendDirection) {
      this.bendDirection = bendDirection;
   }

   public boolean getCompress() {
      return this.compress;
   }

   public void setCompress(boolean compress) {
      this.compress = compress;
   }

   public boolean getStretch() {
      return this.stretch;
   }

   public void setStretch(boolean stretch) {
      this.stretch = stretch;
   }

   public boolean getUniform() {
      return this.uniform;
   }

   public void setUniform(boolean uniform) {
      this.uniform = uniform;
   }
}
