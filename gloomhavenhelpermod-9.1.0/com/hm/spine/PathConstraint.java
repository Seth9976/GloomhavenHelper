package com.hm.spine;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.hm.spine.attachments.Attachment;
import com.hm.spine.attachments.PathAttachment;
import java.util.Arrays;

public class PathConstraint implements Updatable {
   private static final int NONE = -1;
   private static final int BEFORE = -2;
   private static final int AFTER = -3;
   private static final float epsilon = 1.0E-5F;
   final PathConstraintData data;
   final Array bones;
   Slot target;
   float position;
   float spacing;
   float mixRotate;
   float mixX;
   float mixY;
   boolean active;
   private final FloatArray spaces = new FloatArray();
   private final FloatArray positions = new FloatArray();
   private final FloatArray world = new FloatArray();
   private final FloatArray curves = new FloatArray();
   private final FloatArray lengths = new FloatArray();
   private final float[] segments = new float[10];

   public PathConstraint(PathConstraintData data, Skeleton skeleton) {
      if (data == null) {
         throw new IllegalArgumentException("data cannot be null.");
      } else if (skeleton == null) {
         throw new IllegalArgumentException("skeleton cannot be null.");
      } else {
         this.data = data;
         this.bones = new Array(data.bones.size);

         for (BoneData boneData : data.bones) {
            this.bones.add(skeleton.bones.get(boneData.index));
         }

         this.target = (Slot)skeleton.slots.get(data.target.index);
         this.position = data.position;
         this.spacing = data.spacing;
         this.mixRotate = data.mixRotate;
         this.mixX = data.mixX;
         this.mixY = data.mixY;
      }
   }

   public PathConstraint(PathConstraint constraint, Skeleton skeleton) {
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

         this.target = (Slot)skeleton.slots.get(constraint.target.data.index);
         this.position = constraint.position;
         this.spacing = constraint.spacing;
         this.mixRotate = constraint.mixRotate;
         this.mixX = constraint.mixX;
         this.mixY = constraint.mixY;
      }
   }

   public void setToSetupPose() {
      PathConstraintData data = this.data;
      this.position = data.position;
      this.spacing = data.spacing;
      this.mixRotate = data.mixRotate;
      this.mixX = data.mixX;
      this.mixY = data.mixY;
   }

   @Override
   public void update() {
      Attachment attachment = this.target.attachment;
      if (attachment instanceof PathAttachment) {
         float mixRotate = this.mixRotate;
         float mixX = this.mixX;
         float mixY = this.mixY;
         if (mixRotate != 0.0F || mixX != 0.0F || mixY != 0.0F) {
            PathConstraintData data = this.data;
            boolean tangents = data.rotateMode == PathConstraintData.RotateMode.tangent;
            boolean scale = data.rotateMode == PathConstraintData.RotateMode.chainScale;
            int boneCount = this.bones.size;
            int spacesCount = tangents ? boneCount : boneCount + 1;
            Object[] bones = this.bones.items;
            float[] spaces = this.spaces.setSize(spacesCount);
            float[] lengths = scale ? this.lengths.setSize(boneCount) : null;
            float spacing = this.spacing;
            switch (data.spacingMode) {
               case percent:
                  if (scale) {
                     int i1 = 0;

                     for (int i2 = spacesCount - 1; i1 < i2; i1++) {
                        Bone bone = (Bone)bones[i1];
                        float setupLength = bone.data.length;
                        if (setupLength < 1.0E-5F) {
                           lengths[i1] = 0.0F;
                        } else {
                           float x = setupLength * bone.a;
                           float y = setupLength * bone.c;
                           lengths[i1] = (float)Math.sqrt(x * x + y * y);
                        }
                     }
                  }

                  Arrays.fill(spaces, 1, spacesCount, spacing);
                  break;
               case proportional:
                  float sum = 0.0F;
                  int i = 0;
                  int n = spacesCount - 1;

                  while (i < n) {
                     Bone bone = (Bone)bones[i];
                     float setupLength = bone.data.length;
                     if (setupLength < 1.0E-5F) {
                        if (scale) {
                           lengths[i] = 0.0F;
                        }

                        spaces[++i] = spacing;
                     } else {
                        float x = setupLength * bone.a;
                        float y = setupLength * bone.c;
                        float length = (float)Math.sqrt(x * x + y * y);
                        if (scale) {
                           lengths[i] = length;
                        }

                        spaces[++i] = length;
                        sum += length;
                     }
                  }

                  if (sum > 0.0F) {
                     sum = spacesCount / sum * spacing;

                     for (int var41 = 1; var41 < spacesCount; var41++) {
                        spaces[var41] *= sum;
                     }
                  }
                  break;
               default:
                  boolean lengthSpacing = data.spacingMode == PathConstraintData.SpacingMode.length;
                  int j = 0;
                  int k = spacesCount - 1;

                  while (j < k) {
                     Bone bone = (Bone)bones[j];
                     float setupLength = bone.data.length;
                     if (setupLength < 1.0E-5F) {
                        if (scale) {
                           lengths[j] = 0.0F;
                        }

                        spaces[++j] = spacing;
                     } else {
                        float x = setupLength * bone.a;
                        float y = setupLength * bone.c;
                        float length = (float)Math.sqrt(x * x + y * y);
                        if (scale) {
                           lengths[j] = length;
                        }

                        spaces[++j] = (lengthSpacing ? setupLength + spacing : spacing) * length / setupLength;
                     }
                  }
            }

            float[] positions = this.computeWorldPositions((PathAttachment)attachment, spacesCount, tangents);
            float boneX = positions[0];
            float boneY = positions[1];
            float offsetRotation = data.offsetRotation;
            boolean tip;
            if (offsetRotation == 0.0F) {
               tip = data.rotateMode == PathConstraintData.RotateMode.chain;
            } else {
               tip = false;
               Bone bone = this.target.bone;
               offsetRotation *= bone.a * bone.d - bone.b * bone.c > 0.0F ? (float) (Math.PI / 180.0) : (float) (-Math.PI / 180.0);
            }

            int m = 0;

            for (int p = 3; m < boneCount; p += 3) {
               Bone bone = (Bone)bones[m];
               bone.worldX = bone.worldX + (boneX - bone.worldX) * mixX;
               bone.worldY = bone.worldY + (boneY - bone.worldY) * mixY;
               float x = positions[p];
               float y = positions[p + 1];
               float dx = x - boneX;
               float dy = y - boneY;
               if (scale) {
                  float length = lengths[m];
                  if (length >= 1.0E-5F) {
                     float s = ((float)Math.sqrt(dx * dx + dy * dy) / length - 1.0F) * mixRotate + 1.0F;
                     bone.a *= s;
                     bone.c *= s;
                  }
               }

               boneX = x;
               boneY = y;
               if (mixRotate > 0.0F) {
                  float a = bone.a;
                  float b = bone.b;
                  float c = bone.c;
                  float d = bone.d;
                  float r;
                  if (tangents) {
                     r = positions[p - 1];
                  } else if (spaces[m + 1] < 1.0E-5F) {
                     r = positions[p + 2];
                  } else {
                     r = (float)Math.atan2(dy, dx);
                  }

                  r -= (float)Math.atan2(c, a);
                  if (tip) {
                     float f1 = (float)Math.cos(r);
                     float f2 = (float)Math.sin(r);
                     float length = bone.data.length;
                     boneX = x + (length * (f1 * a - f2 * c) - dx) * mixRotate;
                     boneY = y + (length * (f2 * a + f1 * c) - dy) * mixRotate;
                  } else {
                     r += offsetRotation;
                  }

                  if (r > (float) Math.PI) {
                     r -= (float) (Math.PI * 2);
                  } else if (r < (float) -Math.PI) {
                     r += (float) (Math.PI * 2);
                  }

                  r *= mixRotate;
                  float cos = (float)Math.cos(r);
                  float sin = (float)Math.sin(r);
                  bone.a = cos * a - sin * c;
                  bone.b = cos * b - sin * d;
                  bone.c = sin * a + cos * c;
                  bone.d = sin * b + cos * d;
               }

               bone.updateAppliedTransform();
               m++;
            }
         }
      }
   }

   float[] computeWorldPositions(PathAttachment path, int spacesCount, boolean tangents) {
      Slot target = this.target;
      float position = this.position;
      float[] spaces = this.spaces.items;
      float[] out = this.positions.setSize(spacesCount * 3 + 2);
      boolean closed = path.getClosed();
      int verticesLength = path.getWorldVerticesLength();
      int curveCount = verticesLength / 6;
      int prevCurve = -1;
      if (!path.getConstantSpeed()) {
         float[] lengths = path.getLengths();
         curveCount -= closed ? 1 : 2;
         float f1 = lengths[curveCount];
         if (this.data.positionMode == PathConstraintData.PositionMode.percent) {
            position *= f1;
         }

         float f2;
         switch (this.data.spacingMode) {
            case percent:
               f2 = f1;
               break;
            case proportional:
               f2 = f1 / spacesCount;
               break;
            default:
               f2 = 1.0F;
         }

         float[] world = this.world.setSize(8);
         int k = 0;
         int m = 0;

         for (int n = 0; k < spacesCount; m += 3) {
            label211: {
               float space = spaces[k] * f2;
               position += space;
               float p = position;
               if (closed) {
                  p = position % f1;
                  if (p < 0.0F) {
                     p += f1;
                  }

                  n = 0;
               } else {
                  if (position < 0.0F) {
                     if (prevCurve != -2) {
                        prevCurve = -2;
                        path.computeWorldVertices(target, 2, 4, world, 0, 2);
                     }

                     this.addBeforePosition(position, world, 0, out, m);
                     break label211;
                  }

                  if (position > f1) {
                     if (prevCurve != -3) {
                        prevCurve = -3;
                        path.computeWorldVertices(target, verticesLength - 6, 4, world, 0, 2);
                     }

                     this.addAfterPosition(position - f1, world, 0, out, m);
                     break label211;
                  }
               }

               while (true) {
                  float length = lengths[n];
                  if (!(p > length)) {
                     if (n == 0) {
                        p /= length;
                     } else {
                        float prev = lengths[n - 1];
                        p = (p - prev) / (length - prev);
                     }

                     if (n != prevCurve) {
                        prevCurve = n;
                        if (closed && n == curveCount) {
                           path.computeWorldVertices(target, verticesLength - 4, 4, world, 0, 2);
                           path.computeWorldVertices(target, 0, 4, world, 4, 2);
                        } else {
                           path.computeWorldVertices(target, n * 6 + 2, 8, world, 0, 2);
                        }
                     }

                     this.addCurvePosition(
                        p, world[0], world[1], world[2], world[3], world[4], world[5], world[6], world[7], out, m, tangents || k > 0 && !(space >= 1.0E-5F)
                     );
                     break;
                  }

                  n++;
               }
            }

            k++;
         }

         return out;
      } else {
         float[] world;
         if (closed) {
            verticesLength += 2;
            world = this.world.setSize(verticesLength);
            path.computeWorldVertices(target, 2, verticesLength - 4, world, 0, 2);
            path.computeWorldVertices(target, 0, 2, world, verticesLength - 4, 2);
            world[verticesLength - 2] = world[0];
            world[verticesLength - 1] = world[1];
         } else {
            curveCount--;
            verticesLength -= 4;
            world = this.world.setSize(verticesLength);
            path.computeWorldVertices(target, 2, verticesLength, world, 0, 2);
         }

         float[] curves = this.curves.setSize(curveCount);
         float pathLength = 0.0F;
         float x1 = world[0];
         float y1 = world[1];
         float cx1 = 0.0F;
         float cy1 = 0.0F;
         float cx2 = 0.0F;
         float cy2 = 0.0F;
         float x2 = 0.0F;
         float y2 = 0.0F;
         int i = 0;

         for (int w = 2; i < curveCount; w += 6) {
            cx1 = world[w];
            cy1 = world[w + 1];
            cx2 = world[w + 2];
            cy2 = world[w + 3];
            x2 = world[w + 4];
            y2 = world[w + 5];
            float tmpx = (x1 - cx1 * 2.0F + cx2) * 0.1875F;
            float tmpy = (y1 - cy1 * 2.0F + cy2) * 0.1875F;
            float dddfx = ((cx1 - cx2) * 3.0F - x1 + x2) * 0.09375F;
            float dddfy = ((cy1 - cy2) * 3.0F - y1 + y2) * 0.09375F;
            float ddfx = tmpx * 2.0F + dddfx;
            float ddfy = tmpy * 2.0F + dddfy;
            float dfx = (cx1 - x1) * 0.75F + tmpx + dddfx * 0.16666667F;
            float dfy = (cy1 - y1) * 0.75F + tmpy + dddfy * 0.16666667F;
            float var45 = pathLength + (float)Math.sqrt(dfx * dfx + dfy * dfy);
            dfx += ddfx;
            dfy += ddfy;
            ddfx += dddfx;
            ddfy += dddfy;
            float var46 = var45 + (float)Math.sqrt(dfx * dfx + dfy * dfy);
            dfx += ddfx;
            dfy += ddfy;
            float var47 = var46 + (float)Math.sqrt(dfx * dfx + dfy * dfy);
            dfx += ddfx + dddfx;
            dfy += ddfy + dddfy;
            pathLength = var47 + (float)Math.sqrt(dfx * dfx + dfy * dfy);
            curves[i] = pathLength;
            x1 = x2;
            y1 = y2;
            i++;
         }

         if (this.data.positionMode == PathConstraintData.PositionMode.percent) {
            position *= pathLength;
         }

         float multiplier;
         switch (this.data.spacingMode) {
            case percent:
               multiplier = pathLength;
               break;
            case proportional:
               multiplier = pathLength / spacesCount;
               break;
            default:
               multiplier = 1.0F;
         }

         float[] segments = this.segments;
         float curveLength = 0.0F;
         int j = 0;
         int o = 0;
         int curve = 0;

         for (int segment = 0; j < spacesCount; o += 3) {
            label212: {
               float space = spaces[j] * multiplier;
               position += space;
               float p = position;
               if (closed) {
                  p = position % pathLength;
                  if (p < 0.0F) {
                     p += pathLength;
                  }

                  curve = 0;
               } else {
                  if (position < 0.0F) {
                     this.addBeforePosition(position, world, 0, out, o);
                     break label212;
                  }

                  if (position > pathLength) {
                     this.addAfterPosition(position - pathLength, world, verticesLength - 4, out, o);
                     break label212;
                  }
               }

               label184:
               while (true) {
                  float length = curves[curve];
                  if (!(p > length)) {
                     if (curve == 0) {
                        p /= length;
                     } else {
                        float prev = curves[curve - 1];
                        p = (p - prev) / (length - prev);
                     }

                     if (curve != prevCurve) {
                        prevCurve = curve;
                        int ii = curve * 6;
                        x1 = world[ii];
                        y1 = world[ii + 1];
                        cx1 = world[ii + 2];
                        cy1 = world[ii + 3];
                        cx2 = world[ii + 4];
                        cy2 = world[ii + 5];
                        x2 = world[ii + 6];
                        y2 = world[ii + 7];
                        float tmpx = (x1 - cx1 * 2.0F + cx2) * 0.03F;
                        float tmpy = (y1 - cy1 * 2.0F + cy2) * 0.03F;
                        float dddfx = ((cx1 - cx2) * 3.0F - x1 + x2) * 0.006F;
                        float dddfy = ((cy1 - cy2) * 3.0F - y1 + y2) * 0.006F;
                        float ddfx = tmpx * 2.0F + dddfx;
                        float ddfy = tmpy * 2.0F + dddfy;
                        float dfx = (cx1 - x1) * 0.3F + tmpx + dddfx * 0.16666667F;
                        float dfy = (cy1 - y1) * 0.3F + tmpy + dddfy * 0.16666667F;
                        curveLength = (float)Math.sqrt(dfx * dfx + dfy * dfy);
                        segments[0] = curveLength;

                        for (int var78 = 1; var78 < 8; var78++) {
                           dfx += ddfx;
                           dfy += ddfy;
                           ddfx += dddfx;
                           ddfy += dddfy;
                           curveLength += (float)Math.sqrt(dfx * dfx + dfy * dfy);
                           segments[var78] = curveLength;
                        }

                        dfx += ddfx;
                        dfy += ddfy;
                        float var61 = curveLength + (float)Math.sqrt(dfx * dfx + dfy * dfy);
                        segments[8] = var61;
                        dfx += ddfx + dddfx;
                        dfy += ddfy + dddfy;
                        curveLength = var61 + (float)Math.sqrt(dfx * dfx + dfy * dfy);
                        segments[9] = curveLength;
                        segment = 0;
                     }

                     p *= curveLength;

                     while (true) {
                        length = segments[segment];
                        if (!(p > length)) {
                           if (segment == 0) {
                              p /= length;
                           } else {
                              float prev = segments[segment - 1];
                              p = segment + (p - prev) / (length - prev);
                           }

                           this.addCurvePosition(p * 0.1F, x1, y1, cx1, cy1, cx2, cy2, x2, y2, out, o, tangents || j > 0 && !(space >= 1.0E-5F));
                           break label184;
                        }

                        segment++;
                     }
                  }

                  curve++;
               }
            }

            j++;
         }

         return out;
      }
   }

   private void addBeforePosition(float p, float[] temp, int i, float[] out, int o) {
      float x1 = temp[i];
      float y1 = temp[i + 1];
      float dx = temp[i + 2] - x1;
      float dy = temp[i + 3] - y1;
      float r = (float)Math.atan2(dy, dx);
      out[o] = x1 + p * (float)Math.cos(r);
      out[o + 1] = y1 + p * (float)Math.sin(r);
      out[o + 2] = r;
   }

   private void addAfterPosition(float p, float[] temp, int i, float[] out, int o) {
      float x1 = temp[i + 2];
      float y1 = temp[i + 3];
      float dx = x1 - temp[i];
      float dy = y1 - temp[i + 1];
      float r = (float)Math.atan2(dy, dx);
      out[o] = x1 + p * (float)Math.cos(r);
      out[o + 1] = y1 + p * (float)Math.sin(r);
      out[o + 2] = r;
   }

   private void addCurvePosition(
      float p, float x1, float y1, float cx1, float cy1, float cx2, float cy2, float x2, float y2, float[] out, int o, boolean tangents
   ) {
      if (!(p < 1.0E-5F) && !Float.isNaN(p)) {
         float tt = p * p;
         float ttt = tt * p;
         float u = 1.0F - p;
         float uu = u * u;
         float uuu = uu * u;
         float ut = u * p;
         float ut3 = ut * 3.0F;
         float uut3 = u * ut3;
         float utt3 = ut3 * p;
         float x = x1 * uuu + cx1 * uut3 + cx2 * utt3 + x2 * ttt;
         float y = y1 * uuu + cy1 * uut3 + cy2 * utt3 + y2 * ttt;
         out[o] = x;
         out[o + 1] = y;
         if (tangents) {
            if (p < 0.001F) {
               out[o + 2] = (float)Math.atan2(cy1 - y1, cx1 - x1);
            } else {
               out[o + 2] = (float)Math.atan2(y - y1 * uu + cy1 * ut * 2.0F + cy2 * tt, x - x1 * uu + cx1 * ut * 2.0F + cx2 * tt);
            }
         }
      } else {
         out[o] = x1;
         out[o + 1] = y1;
         out[o + 2] = (float)Math.atan2(cy1 - y1, cx1 - x1);
      }
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

   public Array getBones() {
      return this.bones;
   }

   public Slot getTarget() {
      return this.target;
   }

   public void setTarget(Slot target) {
      if (target == null) {
         throw new IllegalArgumentException("target cannot be null.");
      } else {
         this.target = target;
      }
   }

   @Override
   public boolean isActive() {
      return this.active;
   }

   public PathConstraintData getData() {
      return this.data;
   }

   @Override
   public String toString() {
      return this.data.name;
   }
}
