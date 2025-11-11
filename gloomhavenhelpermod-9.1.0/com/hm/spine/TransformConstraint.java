package com.hm.spine;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.hm.spine.utils.SpineUtils;

public class TransformConstraint implements Updatable {
   final TransformConstraintData data;
   final Array bones;
   Bone target;
   float mixRotate;
   float mixX;
   float mixY;
   float mixScaleX;
   float mixScaleY;
   float mixShearY;
   boolean active;
   final Vector2 temp = new Vector2();

   public TransformConstraint(TransformConstraintData data, Skeleton skeleton) {
      if (data == null) {
         throw new IllegalArgumentException("data cannot be null.");
      } else if (skeleton == null) {
         throw new IllegalArgumentException("skeleton cannot be null.");
      } else {
         this.data = data;
         this.mixRotate = data.mixRotate;
         this.mixX = data.mixX;
         this.mixY = data.mixY;
         this.mixScaleX = data.mixScaleX;
         this.mixScaleY = data.mixScaleY;
         this.mixShearY = data.mixShearY;
         this.bones = new Array(data.bones.size);

         for (BoneData boneData : data.bones) {
            this.bones.add(skeleton.bones.get(boneData.index));
         }

         this.target = (Bone)skeleton.bones.get(data.target.index);
      }
   }

   public TransformConstraint(TransformConstraint constraint, Skeleton skeleton) {
      if (constraint == null) {
         throw new IllegalArgumentException("constraint cannot be null.");
      } else if (skeleton == null) {
         throw new IllegalArgumentException("skeleton cannot be null.");
      } else {
         this.data = constraint.data;
         this.bones = new Array(constraint.bones.size);

         for (Bone bone : constraint.bones) {
            this.bones.add(skeleton.bones.get(bone.data.index));
         }

         this.target = (Bone)skeleton.bones.get(constraint.target.data.index);
         this.mixRotate = constraint.mixRotate;
         this.mixX = constraint.mixX;
         this.mixY = constraint.mixY;
         this.mixScaleX = constraint.mixScaleX;
         this.mixScaleY = constraint.mixScaleY;
         this.mixShearY = constraint.mixShearY;
      }
   }

   public void setToSetupPose() {
      TransformConstraintData data = this.data;
      this.mixRotate = data.mixRotate;
      this.mixX = data.mixX;
      this.mixY = data.mixY;
      this.mixScaleX = data.mixScaleX;
      this.mixScaleY = data.mixScaleY;
      this.mixShearY = data.mixShearY;
   }

   @Override
   public void update() {
      if (this.mixRotate != 0.0F || this.mixX != 0.0F || this.mixY != 0.0F || this.mixScaleX != 0.0F || this.mixScaleX != 0.0F || this.mixShearY != 0.0F) {
         if (this.data.local) {
            if (this.data.relative) {
               this.applyRelativeLocal();
            } else {
               this.applyAbsoluteLocal();
            }
         } else if (this.data.relative) {
            this.applyRelativeWorld();
         } else {
            this.applyAbsoluteWorld();
         }
      }
   }

   private void applyAbsoluteWorld() {
      float mixRotate = this.mixRotate;
      float mixX = this.mixX;
      float mixY = this.mixY;
      float mixScaleX = this.mixScaleX;
      float mixScaleY = this.mixScaleY;
      float mixShearY = this.mixShearY;
      boolean translate = mixX != 0.0F || mixY != 0.0F;
      Bone target = this.target;
      float ta = target.a;
      float tb = target.b;
      float tc = target.c;
      float td = target.d;
      float degRadReflect = ta * td - tb * tc > 0.0F ? (float) (Math.PI / 180.0) : (float) (-Math.PI / 180.0);
      float offsetRotation = this.data.offsetRotation * degRadReflect;
      float offsetShearY = this.data.offsetShearY * degRadReflect;
      Object[] bones = this.bones.items;
      int i = 0;

      for (int n = this.bones.size; i < n; i++) {
         Bone bone = (Bone)bones[i];
         if (mixRotate != 0.0F) {
            float a = bone.a;
            float b = bone.b;
            float c = bone.c;
            float d = bone.d;
            float r = SpineUtils.atan2(tc, ta) - SpineUtils.atan2(c, a) + offsetRotation;
            if (r > (float) Math.PI) {
               r -= (float) (Math.PI * 2);
            } else if (r < (float) -Math.PI) {
               r += (float) (Math.PI * 2);
            }

            r *= mixRotate;
            float cos = SpineUtils.cos(r);
            float sin = SpineUtils.sin(r);
            bone.a = cos * a - sin * c;
            bone.b = cos * b - sin * d;
            bone.c = sin * a + cos * c;
            bone.d = sin * b + cos * d;
         }

         if (translate) {
            Vector2 temp = this.temp;
            target.localToWorld(temp.set(this.data.offsetX, this.data.offsetY));
            bone.worldX = bone.worldX + (temp.x - bone.worldX) * mixX;
            bone.worldY = bone.worldY + (temp.y - bone.worldY) * mixY;
         }

         if (mixScaleX != 0.0F) {
            float s = (float)Math.sqrt(bone.a * bone.a + bone.c * bone.c);
            if (s != 0.0F) {
               s = (s + ((float)Math.sqrt(ta * ta + tc * tc) - s + this.data.offsetScaleX) * mixScaleX) / s;
            }

            bone.a *= s;
            bone.c *= s;
         }

         if (mixScaleY != 0.0F) {
            float s = (float)Math.sqrt(bone.b * bone.b + bone.d * bone.d);
            if (s != 0.0F) {
               s = (s + ((float)Math.sqrt(tb * tb + td * td) - s + this.data.offsetScaleY) * mixScaleY) / s;
            }

            bone.b *= s;
            bone.d *= s;
         }

         if (mixShearY > 0.0F) {
            float b = bone.b;
            float d = bone.d;
            float by = SpineUtils.atan2(d, b);
            float r = SpineUtils.atan2(td, tb) - SpineUtils.atan2(tc, ta) - by - SpineUtils.atan2(bone.c, bone.a);
            if (r > (float) Math.PI) {
               r -= (float) (Math.PI * 2);
            } else if (r < (float) -Math.PI) {
               r += (float) (Math.PI * 2);
            }

            r = by + (r + offsetShearY) * mixShearY;
            float s = (float)Math.sqrt(b * b + d * d);
            bone.b = SpineUtils.cos(r) * s;
            bone.d = SpineUtils.sin(r) * s;
         }

         bone.updateAppliedTransform();
      }
   }

   private void applyRelativeWorld() {
      float mixRotate = this.mixRotate;
      float mixX = this.mixX;
      float mixY = this.mixY;
      float mixScaleX = this.mixScaleX;
      float mixScaleY = this.mixScaleY;
      float mixShearY = this.mixShearY;
      boolean translate = mixX != 0.0F || mixY != 0.0F;
      Bone target = this.target;
      float ta = target.a;
      float tb = target.b;
      float tc = target.c;
      float td = target.d;
      float degRadReflect = ta * td - tb * tc > 0.0F ? (float) (Math.PI / 180.0) : (float) (-Math.PI / 180.0);
      float offsetRotation = this.data.offsetRotation * degRadReflect;
      float offsetShearY = this.data.offsetShearY * degRadReflect;
      Object[] bones = this.bones.items;
      int i = 0;

      for (int n = this.bones.size; i < n; i++) {
         Bone bone = (Bone)bones[i];
         if (mixRotate != 0.0F) {
            float a = bone.a;
            float b = bone.b;
            float c = bone.c;
            float d = bone.d;
            float r = SpineUtils.atan2(tc, ta) + offsetRotation;
            if (r > (float) Math.PI) {
               r -= (float) (Math.PI * 2);
            } else if (r < (float) -Math.PI) {
               r += (float) (Math.PI * 2);
            }

            r *= mixRotate;
            float cos = SpineUtils.cos(r);
            float sin = SpineUtils.sin(r);
            bone.a = cos * a - sin * c;
            bone.b = cos * b - sin * d;
            bone.c = sin * a + cos * c;
            bone.d = sin * b + cos * d;
         }

         if (translate) {
            Vector2 temp = this.temp;
            target.localToWorld(temp.set(this.data.offsetX, this.data.offsetY));
            bone.worldX = bone.worldX + temp.x * mixX;
            bone.worldY = bone.worldY + temp.y * mixY;
         }

         if (mixScaleX != 0.0F) {
            float s = ((float)Math.sqrt(ta * ta + tc * tc) - 1.0F + this.data.offsetScaleX) * mixScaleX + 1.0F;
            bone.a *= s;
            bone.c *= s;
         }

         if (mixScaleY != 0.0F) {
            float s = ((float)Math.sqrt(tb * tb + td * td) - 1.0F + this.data.offsetScaleY) * mixScaleY + 1.0F;
            bone.b *= s;
            bone.d *= s;
         }

         if (mixShearY > 0.0F) {
            float r = SpineUtils.atan2(td, tb) - SpineUtils.atan2(tc, ta);
            if (r > (float) Math.PI) {
               r -= (float) (Math.PI * 2);
            } else if (r < (float) -Math.PI) {
               r += (float) (Math.PI * 2);
            }

            float b = bone.b;
            float d = bone.d;
            r = SpineUtils.atan2(d, b) + (r - (float) (Math.PI / 2) + offsetShearY) * mixShearY;
            float s = (float)Math.sqrt(b * b + d * d);
            bone.b = SpineUtils.cos(r) * s;
            bone.d = SpineUtils.sin(r) * s;
         }

         bone.updateAppliedTransform();
      }
   }

   private void applyAbsoluteLocal() {
      float mixRotate = this.mixRotate;
      float mixX = this.mixX;
      float mixY = this.mixY;
      float mixScaleX = this.mixScaleX;
      float mixScaleY = this.mixScaleY;
      float mixShearY = this.mixShearY;
      Bone target = this.target;
      Object[] bones = this.bones.items;
      int i = 0;

      for (int n = this.bones.size; i < n; i++) {
         Bone bone = (Bone)bones[i];
         float rotation = bone.arotation;
         if (mixRotate != 0.0F) {
            float r = target.arotation - rotation + this.data.offsetRotation;
            r -= (16384 - (int)(16384.499999999996 - r / 360.0F)) * 360;
            rotation += r * mixRotate;
         }

         float x = bone.ax;
         float y = bone.ay;
         x += (target.ax - x + this.data.offsetX) * mixX;
         y += (target.ay - y + this.data.offsetY) * mixY;
         float scaleX = bone.ascaleX;
         float scaleY = bone.ascaleY;
         if (mixScaleX != 0.0F && scaleX != 0.0F) {
            scaleX = (scaleX + (target.ascaleX - scaleX + this.data.offsetScaleX) * mixScaleX) / scaleX;
         }

         if (mixScaleY != 0.0F && scaleY != 0.0F) {
            scaleY = (scaleY + (target.ascaleY - scaleY + this.data.offsetScaleY) * mixScaleY) / scaleY;
         }

         float shearY = bone.ashearY;
         if (mixShearY != 0.0F) {
            float r = target.ashearY - shearY + this.data.offsetShearY;
            r -= (16384 - (int)(16384.499999999996 - r / 360.0F)) * 360;
            shearY += r * mixShearY;
         }

         bone.updateWorldTransform(x, y, rotation, scaleX, scaleY, bone.ashearX, shearY);
      }
   }

   private void applyRelativeLocal() {
      float mixRotate = this.mixRotate;
      float mixX = this.mixX;
      float mixY = this.mixY;
      float mixScaleX = this.mixScaleX;
      float mixScaleY = this.mixScaleY;
      float mixShearY = this.mixShearY;
      Bone target = this.target;
      Object[] bones = this.bones.items;
      int i = 0;

      for (int n = this.bones.size; i < n; i++) {
         Bone bone = (Bone)bones[i];
         float rotation = bone.arotation + (target.arotation + this.data.offsetRotation) * mixRotate;
         float x = bone.ax + (target.ax + this.data.offsetX) * mixX;
         float y = bone.ay + (target.ay + this.data.offsetY) * mixY;
         float scaleX = bone.ascaleX * ((target.ascaleX - 1.0F + this.data.offsetScaleX) * mixScaleX + 1.0F);
         float scaleY = bone.ascaleY * ((target.ascaleY - 1.0F + this.data.offsetScaleY) * mixScaleY + 1.0F);
         float shearY = bone.ashearY + (target.ashearY + this.data.offsetShearY) * mixShearY;
         bone.updateWorldTransform(x, y, rotation, scaleX, scaleY, bone.ashearX, shearY);
      }
   }

   public Array getBones() {
      return this.bones;
   }

   public Bone getTarget() {
      return this.target;
   }

   public void setTarget(Bone target) {
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

   @Override
   public boolean isActive() {
      return this.active;
   }

   public TransformConstraintData getData() {
      return this.data;
   }

   @Override
   public String toString() {
      return this.data.name;
   }
}
