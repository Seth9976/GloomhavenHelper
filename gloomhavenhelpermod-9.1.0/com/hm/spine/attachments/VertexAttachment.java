package com.hm.spine.attachments;

import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Null;
import com.hm.spine.Bone;
import com.hm.spine.Slot;
import com.hm.spine.utils.SpineUtils;

public abstract class VertexAttachment extends Attachment {
   private static int nextID;
   private final int id = nextID();
   @Null
   Attachment timelineAttachment = this;
   @Null
   int[] bones;
   float[] vertices;
   int worldVerticesLength;

   public VertexAttachment(String name) {
      super(name);
   }

   public VertexAttachment(VertexAttachment other) {
      super(other);
      this.timelineAttachment = other.timelineAttachment;
      if (other.bones != null) {
         this.bones = new int[other.bones.length];
         SpineUtils.arraycopy(other.bones, 0, this.bones, 0, this.bones.length);
      } else {
         this.bones = null;
      }

      if (other.vertices != null) {
         this.vertices = new float[other.vertices.length];
         SpineUtils.arraycopy(other.vertices, 0, this.vertices, 0, this.vertices.length);
      } else {
         this.vertices = null;
      }

      this.worldVerticesLength = other.worldVerticesLength;
   }

   public void computeWorldVertices(Slot slot, int start, int count, float[] worldVertices, int offset, int stride) {
      count = offset + (count >> 1) * stride;
      FloatArray deformArray = slot.getDeform();
      float[] vertices = this.vertices;
      int[] bones = this.bones;
      if (bones == null) {
         if (deformArray.size > 0) {
            vertices = deformArray.items;
         }

         Bone bone = slot.getBone();
         float x = bone.getWorldX();
         float y = bone.getWorldY();
         float a = bone.getA();
         float b = bone.getB();
         float c = bone.getC();
         float d = bone.getD();
         int j = start;

         for (int w = offset; w < count; w += stride) {
            float vx = vertices[j];
            float vy = vertices[j + 1];
            worldVertices[w] = vx * a + vy * b + x;
            worldVertices[w + 1] = vx * c + vy * d + y;
            j += 2;
         }
      } else {
         int v = 0;
         int skip = 0;

         for (int i = 0; i < start; i += 2) {
            int n = bones[v];
            v += n + 1;
            skip += n;
         }

         Object[] skeletonBones = slot.getSkeleton().getBones().items;
         if (deformArray.size == 0) {
            int w = offset;

            for (int b = skip * 3; w < count; w += stride) {
               float wx = 0.0F;
               float wy = 0.0F;
               int n = bones[v++];

               for (int var38 = n + v; v < var38; b += 3) {
                  Bone bone = (Bone)skeletonBones[bones[v]];
                  float vx = vertices[b];
                  float vy = vertices[b + 1];
                  float weight = vertices[b + 2];
                  wx += (vx * bone.getA() + vy * bone.getB() + bone.getWorldX()) * weight;
                  wy += (vx * bone.getC() + vy * bone.getD() + bone.getWorldY()) * weight;
                  v++;
               }

               worldVertices[w] = wx;
               worldVertices[w + 1] = wy;
            }
         } else {
            float[] deform = deformArray.items;
            int w = offset;
            int b = skip * 3;

            for (int f = skip << 1; w < count; w += stride) {
               float wx = 0.0F;
               float wy = 0.0F;
               int n = bones[v++];

               for (int var44 = n + v; v < var44; f += 2) {
                  Bone bone = (Bone)skeletonBones[bones[v]];
                  float vx = vertices[b] + deform[f];
                  float vy = vertices[b + 1] + deform[f + 1];
                  float weight = vertices[b + 2];
                  wx += (vx * bone.getA() + vy * bone.getB() + bone.getWorldX()) * weight;
                  wy += (vx * bone.getC() + vy * bone.getD() + bone.getWorldY()) * weight;
                  v++;
                  b += 3;
               }

               worldVertices[w] = wx;
               worldVertices[w + 1] = wy;
            }
         }
      }
   }

   @Null
   public int[] getBones() {
      return this.bones;
   }

   public void setBones(@Null int[] bones) {
      this.bones = bones;
   }

   public float[] getVertices() {
      return this.vertices;
   }

   public void setVertices(float[] vertices) {
      this.vertices = vertices;
   }

   public int getWorldVerticesLength() {
      return this.worldVerticesLength;
   }

   public void setWorldVerticesLength(int worldVerticesLength) {
      this.worldVerticesLength = worldVerticesLength;
   }

   @Null
   public Attachment getTimelineAttachment() {
      return this.timelineAttachment;
   }

   public void setTimelineAttachment(Attachment timelineAttachment) {
      this.timelineAttachment = timelineAttachment;
   }

   public int getId() {
      return this.id;
   }

   private static synchronized int nextID() {
      return nextID++;
   }
}
