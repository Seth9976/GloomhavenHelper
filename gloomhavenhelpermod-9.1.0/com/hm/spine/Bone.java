package com.hm.spine;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.hm.spine.utils.SpineUtils;

public class Bone implements Updatable {
   final BoneData data;
   final Skeleton skeleton;
   @Null
   final Bone parent;
   final Array children = new Array();
   float x;
   float y;
   float rotation;
   float scaleX;
   float scaleY;
   float shearX;
   float shearY;
   float ax;
   float ay;
   float arotation;
   float ascaleX;
   float ascaleY;
   float ashearX;
   float ashearY;
   float a;
   float b;
   float worldX;
   float c;
   float d;
   float worldY;
   boolean sorted;
   boolean active;

   public Bone(BoneData data, Skeleton skeleton, @Null Bone parent) {
      if (data == null) {
         throw new IllegalArgumentException("data cannot be null.");
      } else if (skeleton == null) {
         throw new IllegalArgumentException("skeleton cannot be null.");
      } else {
         this.data = data;
         this.skeleton = skeleton;
         this.parent = parent;
         this.setToSetupPose();
      }
   }

   public Bone(Bone bone, Skeleton skeleton, @Null Bone parent) {
      if (bone == null) {
         throw new IllegalArgumentException("bone cannot be null.");
      } else if (skeleton == null) {
         throw new IllegalArgumentException("skeleton cannot be null.");
      } else {
         this.skeleton = skeleton;
         this.parent = parent;
         this.data = bone.data;
         this.x = bone.x;
         this.y = bone.y;
         this.rotation = bone.rotation;
         this.scaleX = bone.scaleX;
         this.scaleY = bone.scaleY;
         this.shearX = bone.shearX;
         this.shearY = bone.shearY;
      }
   }

   @Override
   public void update() {
      this.updateWorldTransform(this.ax, this.ay, this.arotation, this.ascaleX, this.ascaleY, this.ashearX, this.ashearY);
   }

   public void updateWorldTransform() {
      this.updateWorldTransform(this.x, this.y, this.rotation, this.scaleX, this.scaleY, this.shearX, this.shearY);
   }

   public void updateWorldTransform(float x, float y, float rotation, float scaleX, float scaleY, float shearX, float shearY) {
      this.ax = x;
      this.ay = y;
      this.arotation = rotation;
      this.ascaleX = scaleX;
      this.ascaleY = scaleY;
      this.ashearX = shearX;
      this.ashearY = shearY;
      Bone parent = this.parent;
      if (parent == null) {
         Skeleton skeleton = this.skeleton;
         float f10 = rotation + 90.0F + shearY;
         float sx = skeleton.scaleX;
         float sy = skeleton.scaleY;
         this.a = SpineUtils.cosDeg(rotation + shearX) * scaleX * sx;
         this.b = SpineUtils.cosDeg(f10) * scaleY * sx;
         this.c = SpineUtils.sinDeg(rotation + shearX) * scaleX * sy;
         this.d = SpineUtils.sinDeg(f10) * scaleY * sy;
         this.worldX = x * sx + skeleton.x;
         this.worldY = y * sy + skeleton.y;
      } else {
         float pa = parent.a;
         float pb = parent.b;
         float pc = parent.c;
         float pd = parent.d;
         this.worldX = pa * x + pb * y + parent.worldX;
         this.worldY = pc * x + pd * y + parent.worldY;
         switch (this.data.transformMode) {
            case normal: {
               float rotationY = rotation + 90.0F + shearY;
               float la = SpineUtils.cosDeg(rotation + shearX) * scaleX;
               float lb = SpineUtils.cosDeg(rotationY) * scaleY;
               float lc = SpineUtils.sinDeg(rotation + shearX) * scaleX;
               float ld = SpineUtils.sinDeg(rotationY) * scaleY;
               this.a = pa * la + pb * lc;
               this.b = pa * lb + pb * ld;
               this.c = pc * la + pd * lc;
               this.d = pc * lb + pd * ld;
               return;
            }
            case onlyTranslation: {
               float rotationY = rotation + 90.0F + shearY;
               this.a = SpineUtils.cosDeg(rotation + shearX) * scaleX;
               this.b = SpineUtils.cosDeg(rotationY) * scaleY;
               this.c = SpineUtils.sinDeg(rotation + shearX) * scaleX;
               this.d = SpineUtils.sinDeg(rotationY) * scaleY;
               break;
            }
            case noRotationOrReflection:
               float s = pa * pa + pc * pc;
               float prx;
               if (s > 1.0E-4F) {
                  s = Math.abs(pa * pd - pb * pc) / s;
                  pa /= this.skeleton.scaleX;
                  pc /= this.skeleton.scaleY;
                  pb = pc * s;
                  pd = pa * s;
                  prx = SpineUtils.atan2(pc, pa) * (180.0F / (float)Math.PI);
               } else {
                  pa = 0.0F;
                  pc = 0.0F;
                  prx = 90.0F - SpineUtils.atan2(pd, pb) * (180.0F / (float)Math.PI);
               }

               float rx = rotation + shearX - prx;
               float ry = rotation + shearY - prx + 90.0F;
               float f2 = SpineUtils.cosDeg(rx) * scaleX;
               float f3 = SpineUtils.cosDeg(ry) * scaleY;
               float f4 = SpineUtils.sinDeg(rx) * scaleX;
               float f5 = SpineUtils.sinDeg(ry) * scaleY;
               this.a = pa * f2 - pb * f4;
               this.b = pa * f3 - pb * f5;
               this.c = pc * f2 + pd * f4;
               this.d = pc * f3 + pd * f5;
               break;
            case noScale:
            case noScaleOrReflection:
               float cos = SpineUtils.cosDeg(rotation);
               float sin = SpineUtils.sinDeg(rotation);
               float za = (pa * cos + pb * sin) / this.skeleton.scaleX;
               float zc = (pc * cos + pd * sin) / this.skeleton.scaleY;
               float f1 = (float)Math.sqrt(za * za + zc * zc);
               if (f1 > 1.0E-5F) {
                  f1 = 1.0F / f1;
               }

               za *= f1;
               zc *= f1;
               f1 = (float)Math.sqrt(za * za + zc * zc);
               if (this.data.transformMode == BoneData.TransformMode.noScale
                  && pa * pd - pb * pc < 0.0F != (this.skeleton.scaleX < 0.0F ^ this.skeleton.scaleY < 0.0F)) {
                  f1 = -f1;
               }

               float r = (float) (Math.PI / 2) + SpineUtils.atan2(zc, za);
               float zb = SpineUtils.cos(r) * f1;
               float zd = SpineUtils.sin(r) * f1;
               float f6 = SpineUtils.cosDeg(shearX) * scaleX;
               float f7 = SpineUtils.cosDeg(90.0F + shearY) * scaleY;
               float f8 = SpineUtils.sinDeg(shearX) * scaleX;
               float f9 = SpineUtils.sinDeg(90.0F + shearY) * scaleY;
               this.a = za * f6 + zb * f8;
               this.b = za * f7 + zb * f9;
               this.c = zc * f6 + zd * f8;
               this.d = zc * f7 + zd * f9;
         }

         this.a = this.a * this.skeleton.scaleX;
         this.b = this.b * this.skeleton.scaleX;
         this.c = this.c * this.skeleton.scaleY;
         this.d = this.d * this.skeleton.scaleY;
      }
   }

   public void setToSetupPose() {
      BoneData data = this.data;
      this.x = data.x;
      this.y = data.y;
      this.rotation = data.rotation;
      this.scaleX = data.scaleX;
      this.scaleY = data.scaleY;
      this.shearX = data.shearX;
      this.shearY = data.shearY;
   }

   public BoneData getData() {
      return this.data;
   }

   public Skeleton getSkeleton() {
      return this.skeleton;
   }

   @Null
   public Bone getParent() {
      return this.parent;
   }

   public Array getChildren() {
      return this.children;
   }

   @Override
   public boolean isActive() {
      return this.active;
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

   public void setScale(float scale) {
      this.scaleX = scale;
      this.scaleY = scale;
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

   public float getAX() {
      return this.ax;
   }

   public void setAX(float ax) {
      this.ax = ax;
   }

   public float getAY() {
      return this.ay;
   }

   public void setAY(float ay) {
      this.ay = ay;
   }

   public float getARotation() {
      return this.arotation;
   }

   public void setARotation(float arotation) {
      this.arotation = arotation;
   }

   public float getAScaleX() {
      return this.ascaleX;
   }

   public void setAScaleX(float ascaleX) {
      this.ascaleX = ascaleX;
   }

   public float getAScaleY() {
      return this.ascaleY;
   }

   public void setAScaleY(float ascaleY) {
      this.ascaleY = ascaleY;
   }

   public float getAShearX() {
      return this.ashearX;
   }

   public void setAShearX(float ashearX) {
      this.ashearX = ashearX;
   }

   public float getAShearY() {
      return this.ashearY;
   }

   public void setAShearY(float ashearY) {
      this.ashearY = ashearY;
   }

   public void updateAppliedTransform() {
      Bone parent = this.parent;
      if (parent == null) {
         this.ax = this.worldX - this.skeleton.x;
         this.ay = this.worldY - this.skeleton.y;
         float a = this.a;
         float b = this.b;
         float c = this.c;
         float d = this.d;
         this.arotation = SpineUtils.atan2(c, a) * (180.0F / (float)Math.PI);
         this.ascaleX = (float)Math.sqrt(a * a + c * c);
         this.ascaleY = (float)Math.sqrt(b * b + d * d);
         this.ashearX = 0.0F;
         this.ashearY = SpineUtils.atan2(a * b + c * d, a * d - b * c) * (180.0F / (float)Math.PI);
      } else {
         float pa = parent.a;
         float pb = parent.b;
         float pc = parent.c;
         float pd = parent.d;
         float pid = 1.0F / (pa * pd - pb * pc);
         float dx = this.worldX - parent.worldX;
         float dy = this.worldY - parent.worldY;
         this.ax = dx * pd * pid - dy * pb * pid;
         this.ay = dy * pa * pid - dx * pc * pid;
         float ia = pid * pd;
         float id = pid * pa;
         float ib = pid * pb;
         float ic = pid * pc;
         float ra = ia * this.a - ib * this.c;
         float rb = ia * this.b - ib * this.d;
         float rc = id * this.c - ic * this.a;
         float rd = id * this.d - ic * this.b;
         this.ashearX = 0.0F;
         this.ascaleX = (float)Math.sqrt(ra * ra + rc * rc);
         if (this.ascaleX > 1.0E-4F) {
            float det = ra * rd - rb * rc;
            this.ascaleY = det / this.ascaleX;
            this.ashearY = SpineUtils.atan2(ra * rb + rc * rd, det) * (180.0F / (float)Math.PI);
            this.arotation = SpineUtils.atan2(rc, ra) * (180.0F / (float)Math.PI);
         } else {
            this.ascaleX = 0.0F;
            this.ascaleY = (float)Math.sqrt(rb * rb + rd * rd);
            this.ashearY = 0.0F;
            this.arotation = 90.0F - SpineUtils.atan2(rd, rb) * (180.0F / (float)Math.PI);
         }
      }
   }

   public float getA() {
      return this.a;
   }

   public void setA(float a) {
      this.a = a;
   }

   public float getB() {
      return this.b;
   }

   public void setB(float b) {
      this.b = b;
   }

   public float getC() {
      return this.c;
   }

   public void setC(float c) {
      this.c = c;
   }

   public float getD() {
      return this.d;
   }

   public void setD(float d) {
      this.d = d;
   }

   public float getWorldX() {
      return this.worldX;
   }

   public void setWorldX(float worldX) {
      this.worldX = worldX;
   }

   public float getWorldY() {
      return this.worldY;
   }

   public void setWorldY(float worldY) {
      this.worldY = worldY;
   }

   public float getWorldRotationX() {
      return SpineUtils.atan2(this.c, this.a) * (180.0F / (float)Math.PI);
   }

   public float getWorldRotationY() {
      return SpineUtils.atan2(this.d, this.b) * (180.0F / (float)Math.PI);
   }

   public float getWorldScaleX() {
      return (float)Math.sqrt(this.a * this.a + this.c * this.c);
   }

   public float getWorldScaleY() {
      return (float)Math.sqrt(this.b * this.b + this.d * this.d);
   }

   public Matrix3 getWorldTransform(Matrix3 worldTransform) {
      if (worldTransform == null) {
         throw new IllegalArgumentException("worldTransform cannot be null.");
      } else {
         float[] val = worldTransform.val;
         val[0] = this.a;
         val[3] = this.b;
         val[1] = this.c;
         val[4] = this.d;
         val[6] = this.worldX;
         val[7] = this.worldY;
         val[2] = 0.0F;
         val[5] = 0.0F;
         val[8] = 1.0F;
         return worldTransform;
      }
   }

   public Vector2 worldToLocal(Vector2 world) {
      if (world == null) {
         throw new IllegalArgumentException("world cannot be null.");
      } else {
         float det = this.a * this.d - this.b * this.c;
         float x = world.x - this.worldX;
         float y = world.y - this.worldY;
         world.x = (x * this.d - y * this.b) / det;
         world.y = (y * this.a - x * this.c) / det;
         return world;
      }
   }

   public Vector2 localToWorld(Vector2 local) {
      if (local == null) {
         throw new IllegalArgumentException("local cannot be null.");
      } else {
         float x = local.x;
         float y = local.y;
         local.x = x * this.a + y * this.b + this.worldX;
         local.y = x * this.c + y * this.d + this.worldY;
         return local;
      }
   }

   public float worldToLocalRotation(float worldRotation) {
      float sin = SpineUtils.sinDeg(worldRotation);
      float cos = SpineUtils.cosDeg(worldRotation);
      return SpineUtils.atan2(this.a * sin - this.c * cos, this.d * cos - this.b * sin) * (180.0F / (float)Math.PI) + this.rotation - this.shearX;
   }

   public float localToWorldRotation(float localRotation) {
      localRotation -= this.rotation - this.shearX;
      float sin = SpineUtils.sinDeg(localRotation);
      float cos = SpineUtils.cosDeg(localRotation);
      return SpineUtils.atan2(cos * this.c + sin * this.d, cos * this.a + sin * this.b) * (180.0F / (float)Math.PI);
   }

   public void rotateWorld(float degrees) {
      float cos = SpineUtils.cosDeg(degrees);
      float sin = SpineUtils.sinDeg(degrees);
      this.a = cos * this.a - sin * this.c;
      this.b = cos * this.b - sin * this.d;
      this.c = sin * this.a + cos * this.c;
      this.d = sin * this.b + cos * this.d;
   }

   @Override
   public String toString() {
      return this.data.name;
   }
}
