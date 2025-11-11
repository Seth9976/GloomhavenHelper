package com.hm.spine;

import com.badlogic.gdx.utils.Array;
import com.hm.spine.utils.SpineUtils;

public class IkConstraint implements Updatable {
   final IkConstraintData data;
   final Array bones;
   Bone target;
   int bendDirection;
   boolean compress;
   boolean stretch;
   float mix = 1.0F;
   float softness;
   boolean active;
   private static volatile int[] $SWITCH_TABLE$com$esotericsoftware$spine$BoneData$TransformMode;

   public IkConstraint(IkConstraintData data, Skeleton skeleton) {
      if (data == null) {
         throw new IllegalArgumentException("data cannot be null.");
      } else if (skeleton == null) {
         throw new IllegalArgumentException("skeleton cannot be null.");
      } else {
         this.data = data;
         this.mix = data.mix;
         this.softness = data.softness;
         this.bendDirection = data.bendDirection;
         this.compress = data.compress;
         this.stretch = data.stretch;
         this.bones = new Array(data.bones.size);

         for (BoneData boneData : data.bones) {
            this.bones.add(skeleton.bones.get(boneData.index));
         }

         this.target = (Bone)skeleton.bones.get(data.target.index);
      }
   }

   public IkConstraint(IkConstraint constraint, Skeleton skeleton) {
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
         this.mix = constraint.mix;
         this.softness = constraint.softness;
         this.bendDirection = constraint.bendDirection;
         this.compress = constraint.compress;
         this.stretch = constraint.stretch;
      }
   }

   public void setToSetupPose() {
      IkConstraintData data = this.data;
      this.mix = data.mix;
      this.softness = data.softness;
      this.bendDirection = data.bendDirection;
      this.compress = data.compress;
      this.stretch = data.stretch;
   }

   @Override
   public void update() {
      if (this.mix != 0.0F) {
         Bone target = this.target;
         Object[] bones = this.bones.items;
         switch (this.bones.size) {
            case 1:
               apply((Bone)bones[0], target.worldX, target.worldY, this.compress, this.stretch, this.data.uniform, this.mix);
               break;
            case 2:
               apply((Bone)bones[0], (Bone)bones[1], target.worldX, target.worldY, this.bendDirection, this.stretch, this.data.uniform, this.softness, this.mix);
         }
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

   @Override
   public boolean isActive() {
      return this.active;
   }

   public IkConstraintData getData() {
      return this.data;
   }

   @Override
   public String toString() {
      return this.data.name;
   }

   public static void apply(Bone bone, float targetX, float targetY, boolean compress, boolean stretch, boolean uniform, float alpha) {
      if (bone == null) {
         throw new IllegalArgumentException("bone cannot be null.");
      } else {
         Bone p = bone.parent;
         float pa = p.a;
         float pb = p.b;
         float pc = p.c;
         float pd = p.d;
         float rotationIK = -bone.ashearX - bone.arotation;
         float tx = 0.0F;
         float ty = 0.0F;
         switch (bone.data.transformMode) {
            case onlyTranslation:
               tx = targetX - bone.worldX;
               ty = targetY - bone.worldY;
               break;
            case noRotationOrReflection:
               float s = Math.abs(pa * pd - pb * pc) / (pa * pa + pc * pc);
               float sa = pa / bone.skeleton.scaleX;
               float sc = pc / bone.skeleton.scaleY;
               pb = -sc * s * bone.skeleton.scaleX;
               pd = sa * s * bone.skeleton.scaleY;
               rotationIK += SpineUtils.atan2(sc, sa) * (180.0F / (float)Math.PI);
            default:
               float x = targetX - p.worldX;
               float y = targetY - p.worldY;
               float d = pa * pd - pb * pc;
               tx = (x * pd - y * pb) / d - bone.ax;
               ty = (y * pa - x * pc) / d - bone.ay;
         }

         rotationIK += SpineUtils.atan2(ty, tx) * (180.0F / (float)Math.PI);
         if (bone.ascaleX < 0.0F) {
            rotationIK += 180.0F;
         }

         if (rotationIK > 180.0F) {
            rotationIK -= 360.0F;
         } else if (rotationIK < -180.0F) {
            rotationIK += 360.0F;
         }

         float sx = bone.ascaleX;
         float sy = bone.ascaleY;
         if (compress || stretch) {
            switch (bone.data.transformMode) {
               case noScale:
               case noScaleOrReflection:
                  tx = targetX - bone.worldX;
                  ty = targetY - bone.worldY;
            }

            float b = bone.data.length * sx;
            float dd = (float)Math.sqrt(tx * tx + ty * ty);
            if (compress && dd < b || stretch && dd > b && b > 1.0E-4F) {
               float s2 = (dd / b - 1.0F) * alpha + 1.0F;
               sx *= s2;
               if (uniform) {
                  sy *= s2;
               }
            }
         }

         bone.updateWorldTransform(bone.ax, bone.ay, bone.arotation + rotationIK * alpha, sx, sy, bone.ashearX, bone.ashearY);
      }
   }

   public static void apply(Bone parent, Bone child, float targetX, float targetY, int bendDir, boolean stretch, boolean uniform, float softness, float alpha) {
      if (parent == null) {
         throw new IllegalArgumentException("parent cannot be null.");
      } else if (child == null) {
         throw new IllegalArgumentException("child cannot be null.");
      } else {
         float px = parent.ax;
         float py = parent.ay;
         float psx = parent.ascaleX;
         float psy = parent.ascaleY;
         float sx = psx;
         float sy = psy;
         float csx = child.ascaleX;
         int os1;
         int s2;
         if (psx < 0.0F) {
            psx = -psx;
            os1 = 180;
            s2 = -1;
         } else {
            os1 = 0;
            s2 = 1;
         }

         if (psy < 0.0F) {
            psy = -psy;
            s2 = -s2;
         }

         int os2;
         if (csx < 0.0F) {
            csx = -csx;
            os2 = 180;
         } else {
            os2 = 0;
         }

         float cx = child.ax;
         float a = parent.a;
         float b = parent.b;
         float c = parent.c;
         float d = parent.d;
         boolean u = Math.abs(psx - psy) <= 1.0E-4F;
         float cy;
         float cwx;
         float cwy;
         if (u && !stretch) {
            cy = child.ay;
            cwx = a * cx + b * cy + parent.worldX;
            cwy = c * cx + d * cy + parent.worldY;
         } else {
            cy = 0.0F;
            cwx = a * cx + parent.worldX;
            cwy = c * cx + parent.worldY;
         }

         Bone pp = parent.parent;
         a = pp.a;
         b = pp.b;
         c = pp.c;
         d = pp.d;
         float id = 1.0F / (a * d - b * c);
         float x = cwx - pp.worldX;
         float y = cwy - pp.worldY;
         float dx = (x * d - y * b) * id - px;
         float dy = (y * a - x * c) * id - py;
         float l1 = (float)Math.sqrt(dx * dx + dy * dy);
         float l2 = child.data.length * csx;
         if (l1 < 1.0E-4F) {
            apply(parent, targetX, targetY, false, stretch, false, alpha);
            child.updateWorldTransform(cx, cy, 0.0F, child.ascaleX, child.ascaleY, child.ashearX, child.ashearY);
         } else {
            x = targetX - pp.worldX;
            y = targetY - pp.worldY;
            float tx = (x * d - y * b) * id - px;
            float ty = (y * a - x * c) * id - py;
            float dd = tx * tx + ty * ty;
            if (softness != 0.0F) {
               softness *= psx * (csx + 1.0F) * 0.5F;
               float td = (float)Math.sqrt(dd);
               float sd = td - l1 - l2 * psx + softness;
               if (sd > 0.0F) {
                  float p = Math.min(1.0F, sd / (softness * 2.0F)) - 1.0F;
                  p = (sd - softness * (1.0F - p * p)) / td;
                  tx -= p * tx;
                  ty -= p * ty;
                  dd = tx * tx + ty * ty;
               }
            }

            float a2 = 0.0F;
            float a3 = 0.0F;
            if (u) {
               l2 *= psx;
               float cos = (dd - l1 * l1 - l2 * l2) / (2.0F * l1 * l2);
               if (cos < -1.0F) {
                  cos = -1.0F;
                  a2 = (float) Math.PI * bendDir;
               } else if (cos > 1.0F) {
                  cos = 1.0F;
                  a2 = 0.0F;
                  if (stretch) {
                     a = ((float)Math.sqrt(dd) / (l1 + l2) - 1.0F) * alpha + 1.0F;
                     sx *= a;
                     if (uniform) {
                        sy *= a;
                     }
                  }
               } else {
                  a2 = (float)Math.acos(cos) * bendDir;
               }

               a = l1 + l2 * cos;
               b = l2 * SpineUtils.sin(a2);
               a3 = SpineUtils.atan2(ty * a - tx * b, tx * a + ty * b);
            } else {
               label114: {
                  a = psx * l2;
                  b = psy * l2;
                  float aa = a * a;
                  float bb = b * b;
                  float ta = SpineUtils.atan2(ty, tx);
                  c = bb * l1 * l1 + aa * dd - aa * bb;
                  float c2 = -2.0F * bb * l1;
                  float c3 = bb - aa;
                  d = c2 * c2 - 4.0F * c3 * c;
                  if (d >= 0.0F) {
                     float q = (float)Math.sqrt(d);
                     if (c2 < 0.0F) {
                        q = -q;
                     }

                     q = -(c2 + q) * 0.5F;
                     float r0 = q / c3;
                     float r2 = c / q;
                     float r3 = Math.abs(r0) < Math.abs(r2) ? r0 : r2;
                     if (r3 * r3 <= dd) {
                        y = (float)Math.sqrt(dd - r3 * r3) * bendDir;
                        a3 = ta - SpineUtils.atan2(y, r3);
                        a2 = SpineUtils.atan2(y / psy, (r3 - l1) / psx);
                        break label114;
                     }
                  }

                  float minAngle = (float) Math.PI;
                  float minX = l1 - a;
                  float minDist = minX * minX;
                  float minY = 0.0F;
                  float maxAngle = 0.0F;
                  float maxX = l1 + a;
                  float maxDist = maxX * maxX;
                  float maxY = 0.0F;
                  c = -a * l1 / (aa - bb);
                  if (c >= -1.0F && c <= 1.0F) {
                     c = (float)Math.acos(c);
                     x = a * SpineUtils.cos(c) + l1;
                     y = b * SpineUtils.sin(c);
                     d = x * x + y * y;
                     if (d < minDist) {
                        minAngle = c;
                        minDist = d;
                        minX = x;
                        minY = y;
                     }

                     if (d > maxDist) {
                        maxAngle = c;
                        maxDist = d;
                        maxX = x;
                        maxY = y;
                     }
                  }

                  if (dd <= (minDist + maxDist) * 0.5F) {
                     a3 = ta - SpineUtils.atan2(minY * bendDir, minX);
                     a2 = minAngle * bendDir;
                  } else {
                     a3 = ta - SpineUtils.atan2(maxY * bendDir, maxX);
                     a2 = maxAngle * bendDir;
                  }
               }
            }

            float os3 = SpineUtils.atan2(cy, cx) * s2;
            float rotation = parent.arotation;
            a3 = (a3 - os3) * (180.0F / (float)Math.PI) + os1 - rotation;
            if (a3 > 180.0F) {
               a3 -= 360.0F;
            } else if (a3 < -180.0F) {
               a3 += 360.0F;
            }

            parent.updateWorldTransform(px, py, rotation + a3 * alpha, sx, sy, 0.0F, 0.0F);
            rotation = child.arotation;
            a2 = ((a2 + os3) * (180.0F / (float)Math.PI) - child.ashearX) * s2 + os2 - rotation;
            if (a2 > 180.0F) {
               a2 -= 360.0F;
            } else if (a2 < -180.0F) {
               a2 += 360.0F;
            }

            child.updateWorldTransform(cx, cy, rotation + a2 * alpha, child.ascaleX, child.ascaleY, child.ashearX, child.ashearY);
         }
      }
   }

   static int[] $SWITCH_TABLE$com$esotericsoftware$spine$BoneData$TransformMode() {
      int[] $switch_TABLE$com$esotericsoftware$spine$BoneData$TransformMode = $SWITCH_TABLE$com$esotericsoftware$spine$BoneData$TransformMode;
      if ($switch_TABLE$com$esotericsoftware$spine$BoneData$TransformMode != null) {
         return $switch_TABLE$com$esotericsoftware$spine$BoneData$TransformMode;
      } else {
         int[] $switch_TABLE$com$esotericsoftware$spine$BoneData$TransformMode2 = new int[BoneData.TransformMode.values().length];

         try {
            $switch_TABLE$com$esotericsoftware$spine$BoneData$TransformMode2[BoneData.TransformMode.noRotationOrReflection.ordinal()] = 3;
         } catch (NoSuchFieldError var7) {
         }

         try {
            $switch_TABLE$com$esotericsoftware$spine$BoneData$TransformMode2[BoneData.TransformMode.noScale.ordinal()] = 4;
         } catch (NoSuchFieldError var6) {
         }

         try {
            $switch_TABLE$com$esotericsoftware$spine$BoneData$TransformMode2[BoneData.TransformMode.noScaleOrReflection.ordinal()] = 5;
         } catch (NoSuchFieldError var5) {
         }

         try {
            $switch_TABLE$com$esotericsoftware$spine$BoneData$TransformMode2[BoneData.TransformMode.normal.ordinal()] = 1;
         } catch (NoSuchFieldError var4) {
         }

         try {
            $switch_TABLE$com$esotericsoftware$spine$BoneData$TransformMode2[BoneData.TransformMode.onlyTranslation.ordinal()] = 2;
         } catch (NoSuchFieldError var3) {
         }

         $SWITCH_TABLE$com$esotericsoftware$spine$BoneData$TransformMode = $switch_TABLE$com$esotericsoftware$spine$BoneData$TransformMode2;
         return $switch_TABLE$com$esotericsoftware$spine$BoneData$TransformMode2;
      }
   }
}
