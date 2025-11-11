package com.hm.spine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectSet;
import com.hm.spine.attachments.Attachment;
import com.hm.spine.attachments.HasTextureRegion;
import com.hm.spine.attachments.Sequence;
import com.hm.spine.attachments.VertexAttachment;
import com.hm.spine.utils.SpineUtils;

public class Animation {
   final String name;
   Array timelines;
   final ObjectSet timelineIds;
   float duration;

   public Animation(String name, Array timelines, float duration) {
      if (name == null) {
         throw new IllegalArgumentException("name cannot be null.");
      } else {
         this.name = name;
         this.duration = duration;
         this.timelineIds = new ObjectSet(timelines.size);
         this.setTimelines(timelines);
      }
   }

   public Array getTimelines() {
      return this.timelines;
   }

   public void setTimelines(Array timelines) {
      if (timelines == null) {
         throw new IllegalArgumentException("timelines cannot be null.");
      } else {
         this.timelines = timelines;
         int n = timelines.size;
         this.timelineIds.clear(n);
         Object[] items = timelines.items;

         for (int i = 0; i < n; i++) {
            this.timelineIds.addAll(((Animation.Timeline)items[i]).getPropertyIds());
         }
      }
   }

   public boolean hasTimeline(String[] propertyIds) {
      for (String id : propertyIds) {
         if (this.timelineIds.contains(id)) {
            return true;
         }
      }

      return false;
   }

   public float getDuration() {
      return this.duration;
   }

   public void setDuration(float duration) {
      this.duration = duration;
   }

   public void apply(
      Skeleton skeleton, float lastTime, float time, boolean loop, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
   ) {
      if (skeleton == null) {
         throw new IllegalArgumentException("skeleton cannot be null.");
      } else {
         if (loop && this.duration != 0.0F) {
            time %= this.duration;
            if (lastTime > 0.0F) {
               lastTime %= this.duration;
            }
         }

         Object[] timelines = this.timelines.items;
         int i = 0;

         for (int n = this.timelines.size; i < n; i++) {
            ((Animation.Timeline)timelines[i]).apply(skeleton, lastTime, time, events, alpha, blend, direction);
         }
      }
   }

   public String getName() {
      return this.name;
   }

   @Override
   public String toString() {
      return this.name;
   }

   public static class AlphaTimeline extends Animation.CurveTimeline1 implements Animation.SlotTimeline {
      final int slotIndex;

      public AlphaTimeline(int frameCount, int bezierCount, int slotIndex) {
         super(frameCount, bezierCount, Animation.Property.alpha.ordinal() + "|" + slotIndex);
         this.slotIndex = slotIndex;
      }

      @Override
      public int getSlotIndex() {
         return this.slotIndex;
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         Slot slot = (Slot)skeleton.slots.get(this.slotIndex);
         if (slot.bone.active) {
            float[] frames = this.frames;
            Color color = slot.color;
            if (time < frames[0]) {
               Color setup = slot.data.color;
               switch (blend) {
                  case setup:
                     color.a = setup.a;
                     return;
                  case first:
                     color.a = color.a + (setup.a - color.a) * alpha;
               }
            } else {
               float a = this.getCurveValue(time);
               if (alpha == 1.0F) {
                  color.a = a;
               } else {
                  if (blend == Animation.MixBlend.setup) {
                     color.a = slot.data.color.a;
                  }

                  color.a = color.a + (a - color.a) * alpha;
               }
            }
         }
      }
   }

   public static class AttachmentTimeline extends Animation.Timeline implements Animation.SlotTimeline {
      final int slotIndex;
      final String[] attachmentNames;

      public AttachmentTimeline(int frameCount, int slotIndex) {
         super(frameCount, Animation.Property.attachment.ordinal() + "|" + slotIndex);
         this.slotIndex = slotIndex;
         this.attachmentNames = new String[frameCount];
      }

      @Override
      public int getFrameCount() {
         return this.frames.length;
      }

      @Override
      public int getSlotIndex() {
         return this.slotIndex;
      }

      public String[] getAttachmentNames() {
         return this.attachmentNames;
      }

      public void setFrame(int frame, float time, String attachmentName) {
         this.frames[frame] = time;
         this.attachmentNames[frame] = attachmentName;
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         Slot slot = (Slot)skeleton.slots.get(this.slotIndex);
         if (slot.bone.active) {
            if (direction == Animation.MixDirection.out) {
               if (blend == Animation.MixBlend.setup) {
                  this.setAttachment(skeleton, slot, slot.data.attachmentName);
               }
            } else if (!(time < this.frames[0])) {
               this.setAttachment(skeleton, slot, this.attachmentNames[search(this.frames, time)]);
            } else {
               if (blend == Animation.MixBlend.setup || blend == Animation.MixBlend.first) {
                  this.setAttachment(skeleton, slot, slot.data.attachmentName);
               }
            }
         }
      }

      private void setAttachment(Skeleton skeleton, Slot slot, String attachmentName) {
         slot.setAttachment(attachmentName == null ? null : skeleton.getAttachment(this.slotIndex, attachmentName));
      }
   }

   public interface BoneTimeline {
      int getBoneIndex();
   }

   public abstract static class CurveTimeline extends Animation.Timeline {
      public static final int LINEAR = 0;
      public static final int STEPPED = 1;
      public static final int BEZIER = 2;
      public static final int BEZIER_SIZE = 18;
      float[] curves;

      public CurveTimeline(int frameCount, int bezierCount, String... propertyIds) {
         super(frameCount, propertyIds);
         this.curves = new float[frameCount + bezierCount * 18];
         this.curves[frameCount - 1] = 1.0F;
      }

      public void setLinear(int frame) {
         this.curves[frame] = 0.0F;
      }

      public void setStepped(int frame) {
         this.curves[frame] = 1.0F;
      }

      public int getCurveType(int frame) {
         return (int)this.curves[frame];
      }

      public void shrink(int bezierCount) {
         int size = this.getFrameCount() + bezierCount * 18;
         if (this.curves.length > size) {
            float[] newCurves = new float[size];
            SpineUtils.arraycopy(this.curves, 0, newCurves, 0, size);
            this.curves = newCurves;
         }
      }

      public void setBezier(int bezier, int frame, int value, float time1, float value1, float cx1, float cy1, float cx2, float cy2, float time2, float value2) {
         float[] curves = this.curves;
         int i = this.getFrameCount() + bezier * 18;
         if (value == 0) {
            curves[frame] = 2 + i;
         }

         float tmpx = (time1 - cx1 * 2.0F + cx2) * 0.03F;
         float tmpy = (value1 - cy1 * 2.0F + cy2) * 0.03F;
         float dddx = ((cx1 - cx2) * 3.0F - time1 + time2) * 0.006F;
         float dddy = ((cy1 - cy2) * 3.0F - value1 + value2) * 0.006F;
         float ddx = tmpx * 2.0F + dddx;
         float ddy = tmpy * 2.0F + dddy;
         float dx = (cx1 - time1) * 0.3F + tmpx + dddx * 0.16666667F;
         float dy = (cy1 - value1) * 0.3F + tmpy + dddy * 0.16666667F;
         float x = time1 + dx;
         float y = value1 + dy;

         for (int n = i + 18; i < n; i += 2) {
            curves[i] = x;
            curves[i + 1] = y;
            dx += ddx;
            dy += ddy;
            ddx += dddx;
            ddy += dddy;
            x += dx;
            y += dy;
         }
      }

      public float getBezierValue(float time, int frameIndex, int valueOffset, int i) {
         float[] curves = this.curves;
         if (curves[i] > time) {
            float f1 = this.frames[frameIndex];
            float f2 = this.frames[frameIndex + valueOffset];
            return f2 + (time - f1) / (curves[i] - f1) * (curves[i + 1] - f2);
         } else {
            int n = i + 18;

            for (int var10 = i + 2; var10 < n; var10 += 2) {
               if (curves[var10] >= time) {
                  float f1 = curves[var10 - 2];
                  float f2 = curves[var10 - 1];
                  return f2 + (time - f1) / (curves[var10] - f1) * (curves[var10 + 1] - f2);
               }
            }

            frameIndex += this.getFrameEntries();
            float x = curves[n - 2];
            float y = curves[n - 1];
            return y + (time - x) / (this.frames[frameIndex] - x) * (this.frames[frameIndex + valueOffset] - y);
         }
      }
   }

   public abstract static class CurveTimeline1 extends Animation.CurveTimeline {
      public static final int ENTRIES = 2;
      static final int VALUE = 1;

      public CurveTimeline1(int frameCount, int bezierCount, String propertyId) {
         super(frameCount, bezierCount, propertyId);
      }

      @Override
      public int getFrameEntries() {
         return 2;
      }

      public void setFrame(int frame, float time, float value) {
         frame <<= 1;
         this.frames[frame] = time;
         this.frames[frame + 1] = value;
      }

      public float getCurveValue(float time) {
         float[] frames = this.frames;
         int i = frames.length - 2;

         for (int ii = 2; ii <= i; ii += 2) {
            if (frames[ii] > time) {
               i = ii - 2;
               break;
            }
         }

         int curveType = (int)this.curves[i >> 1];
         switch (curveType) {
            case 0:
               float before = frames[i];
               float value = frames[i + 1];
               return value + (time - before) / (frames[i + 2] - before) * (frames[i + 2 + 1] - value);
            case 1:
               return frames[i + 1];
            default:
               return this.getBezierValue(time, i, 1, curveType - 2);
         }
      }
   }

   public abstract static class CurveTimeline2 extends Animation.CurveTimeline {
      public static final int ENTRIES = 3;
      static final int VALUE1 = 1;
      static final int VALUE2 = 2;

      public CurveTimeline2(int frameCount, int bezierCount, String propertyId1, String propertyId2) {
         super(frameCount, bezierCount, propertyId1, propertyId2);
      }

      @Override
      public int getFrameEntries() {
         return 3;
      }

      public void setFrame(int frame, float time, float value1, float value2) {
         frame *= 3;
         this.frames[frame] = time;
         this.frames[frame + 1] = value1;
         this.frames[frame + 2] = value2;
      }
   }

   public static class DeformTimeline extends Animation.CurveTimeline implements Animation.SlotTimeline {
      final int slotIndex;
      final VertexAttachment attachment;
      private final float[][] vertices;

      public DeformTimeline(int frameCount, int bezierCount, int slotIndex, VertexAttachment attachment) {
         super(frameCount, bezierCount, Animation.Property.deform.ordinal() + "|" + slotIndex + "|" + attachment.getId());
         this.slotIndex = slotIndex;
         this.attachment = attachment;
         this.vertices = new float[frameCount][];
      }

      @Override
      public int getFrameCount() {
         return this.frames.length;
      }

      @Override
      public int getSlotIndex() {
         return this.slotIndex;
      }

      public VertexAttachment getAttachment() {
         return this.attachment;
      }

      public float[][] getVertices() {
         return this.vertices;
      }

      public void setFrame(int frame, float time, float[] vertices) {
         this.frames[frame] = time;
         this.vertices[frame] = vertices;
      }

      @Override
      public void setBezier(int bezier, int frame, int value, float time1, float value1, float cx1, float cy1, float cx2, float cy2, float time2, float value2) {
         float[] curves = this.curves;
         int i = this.getFrameCount() + bezier * 18;
         if (value == 0) {
            curves[frame] = 2 + i;
         }

         float tmpx = (time1 - cx1 * 2.0F + cx2) * 0.03F;
         float tmpy = cy2 * 0.03F - cy1 * 0.06F;
         float dddx = ((cx1 - cx2) * 3.0F - time1 + time2) * 0.006F;
         float dddy = (cy1 - cy2 + 0.33333334F) * 0.018F;
         float ddx = tmpx * 2.0F + dddx;
         float ddy = tmpy * 2.0F + dddy;
         float dx = (cx1 - time1) * 0.3F + tmpx + dddx * 0.16666667F;
         float dy = cy1 * 0.3F + tmpy + dddy * 0.16666667F;
         float x = time1 + dx;
         float y = dy;

         for (int n = i + 18; i < n; i += 2) {
            curves[i] = x;
            curves[i + 1] = y;
            dx += ddx;
            dy += ddy;
            ddx += dddx;
            ddy += dddy;
            x += dx;
            y += dy;
         }
      }

      private float getCurvePercent(float time, int frame) {
         float[] curves = this.curves;
         int i = (int)curves[frame];
         switch (i) {
            case 0:
               float x = this.frames[frame];
               return (time - x) / (this.frames[frame + this.getFrameEntries()] - x);
            case 1:
               return 0.0F;
            default:
               i -= 2;
               if (curves[i] > time) {
                  float xx = this.frames[frame];
                  return curves[i + 1] * (time - xx) / (curves[i] - xx);
               } else {
                  int n = i + 18;

                  for (int var11 = i + 2; var11 < n; var11 += 2) {
                     if (curves[var11] >= time) {
                        float f2 = curves[var11 - 2];
                        float f3 = curves[var11 - 1];
                        return f3 + (time - f2) / (curves[var11] - f2) * (curves[var11 + 1] - f3);
                     }
                  }

                  float f1 = curves[n - 2];
                  float y = curves[n - 1];
                  return y + (1.0F - y) * (time - f1) / (this.frames[frame + this.getFrameEntries()] - f1);
               }
         }
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         Slot slot = (Slot)skeleton.slots.get(this.slotIndex);
         if (slot.bone.active) {
            Attachment slotAttachment = slot.attachment;
            if (slotAttachment instanceof VertexAttachment && ((VertexAttachment)slotAttachment).getTimelineAttachment() == this.attachment) {
               FloatArray deformArray = slot.deform;
               if (deformArray.size == 0) {
                  blend = Animation.MixBlend.setup;
               }

               float[][] vertices = this.vertices;
               int vertexCount = vertices[0].length;
               float[] frames = this.frames;
               if (time < frames[0]) {
                  switch (blend) {
                     case setup:
                        deformArray.clear();
                        return;
                     case first:
                        if (alpha == 1.0F) {
                           deformArray.clear();
                           return;
                        } else {
                           float[] arrayOfFloat = deformArray.setSize(vertexCount);
                           VertexAttachment vertexAttachment = (VertexAttachment)slotAttachment;
                           if (vertexAttachment.getBones() == null) {
                              float[] setupVertices = vertexAttachment.getVertices();

                              for (int j = 0; j < vertexCount; j++) {
                                 arrayOfFloat[j] += (setupVertices[j] - arrayOfFloat[j]) * alpha;
                              }
                           } else {
                              alpha = 1.0F - alpha;

                              for (int i = 0; i < vertexCount; i++) {
                                 arrayOfFloat[i] *= alpha;
                              }
                           }
                        }
                  }
               } else {
                  float[] deform = deformArray.setSize(vertexCount);
                  if (time >= frames[frames.length - 1]) {
                     float[] lastVertices = vertices[frames.length - 1];
                     if (alpha == 1.0F) {
                        if (blend == Animation.MixBlend.add) {
                           VertexAttachment vertexAttachment = (VertexAttachment)slotAttachment;
                           if (vertexAttachment.getBones() == null) {
                              float[] setupVertices = vertexAttachment.getVertices();

                              for (int i = 0; i < vertexCount; i++) {
                                 deform[i] = deform[i] + lastVertices[i] - setupVertices[i];
                              }
                           } else {
                              for (int i = 0; i < vertexCount; i++) {
                                 deform[i] += lastVertices[i];
                              }
                           }
                        } else {
                           SpineUtils.arraycopy(lastVertices, 0, deform, 0, vertexCount);
                        }
                     } else {
                        switch (blend) {
                           case setup:
                              VertexAttachment vertexAttachment1 = (VertexAttachment)slotAttachment;
                              if (vertexAttachment1.getBones() == null) {
                                 float[] setupVertices = vertexAttachment1.getVertices();

                                 for (int k = 0; k < vertexCount; k++) {
                                    float setup = setupVertices[k];
                                    deform[k] = setup + (lastVertices[k] - setup) * alpha;
                                 }
                              } else {
                                 for (int j = 0; j < vertexCount; j++) {
                                    deform[j] = lastVertices[j] * alpha;
                                 }
                              }
                              break;
                           case first:
                           case replace:
                              for (int i = 0; i < vertexCount; i++) {
                                 deform[i] += (lastVertices[i] - deform[i]) * alpha;
                              }
                              break;
                           case add:
                              VertexAttachment vertexAttachment = (VertexAttachment)slotAttachment;
                              if (vertexAttachment.getBones() == null) {
                                 float[] setupVertices = vertexAttachment.getVertices();

                                 for (int k = 0; k < vertexCount; k++) {
                                    deform[k] += (lastVertices[k] - setupVertices[k]) * alpha;
                                 }
                              } else {
                                 for (int j = 0; j < vertexCount; j++) {
                                    deform[j] += lastVertices[j] * alpha;
                                 }
                              }
                        }
                     }
                  } else {
                     int frame = search(frames, time);
                     float percent = this.getCurvePercent(time, frame);
                     float[] prevVertices = vertices[frame];
                     float[] nextVertices = vertices[frame + 1];
                     if (alpha == 1.0F) {
                        if (blend == Animation.MixBlend.add) {
                           VertexAttachment vertexAttachmentx = (VertexAttachment)slotAttachment;
                           if (vertexAttachmentx.getBones() == null) {
                              float[] setupVertices = vertexAttachmentx.getVertices();

                              for (int i = 0; i < vertexCount; i++) {
                                 float prev = prevVertices[i];
                                 deform[i] = deform[i] + prev + (nextVertices[i] - prev) * percent - setupVertices[i];
                              }
                           } else {
                              for (int i = 0; i < vertexCount; i++) {
                                 float prev = prevVertices[i];
                                 deform[i] = deform[i] + prev + (nextVertices[i] - prev) * percent;
                              }
                           }
                        } else {
                           for (int i = 0; i < vertexCount; i++) {
                              float prev = prevVertices[i];
                              deform[i] = prev + (nextVertices[i] - prev) * percent;
                           }
                        }
                     } else {
                        switch (blend) {
                           case setup:
                              VertexAttachment vertexAttachment1 = (VertexAttachment)slotAttachment;
                              if (vertexAttachment1.getBones() == null) {
                                 float[] setupVertices = vertexAttachment1.getVertices();

                                 for (int k = 0; k < vertexCount; k++) {
                                    float prev = prevVertices[k];
                                    float setup = setupVertices[k];
                                    deform[k] = setup + (prev + (nextVertices[k] - prev) * percent - setup) * alpha;
                                 }
                              } else {
                                 for (int j = 0; j < vertexCount; j++) {
                                    float prev = prevVertices[j];
                                    deform[j] = (prev + (nextVertices[j] - prev) * percent) * alpha;
                                 }
                              }
                              break;
                           case first:
                           case replace:
                              for (int i = 0; i < vertexCount; i++) {
                                 float prev = prevVertices[i];
                                 deform[i] += (prev + (nextVertices[i] - prev) * percent - deform[i]) * alpha;
                              }
                              break;
                           case add:
                              VertexAttachment vertexAttachmentx = (VertexAttachment)slotAttachment;
                              if (vertexAttachmentx.getBones() == null) {
                                 float[] setupVertices = vertexAttachmentx.getVertices();

                                 for (int k = 0; k < vertexCount; k++) {
                                    float prev = prevVertices[k];
                                    deform[k] += (prev + (nextVertices[k] - prev) * percent - setupVertices[k]) * alpha;
                                 }
                              } else {
                                 for (int j = 0; j < vertexCount; j++) {
                                    float prev = prevVertices[j];
                                    deform[j] += (prev + (nextVertices[j] - prev) * percent) * alpha;
                                 }
                              }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public static class DrawOrderTimeline extends Animation.Timeline {
      private static final String[] propertyIds = new String[]{Integer.toString(Animation.Property.drawOrder.ordinal())};
      private final int[][] drawOrders;

      public DrawOrderTimeline(int frameCount) {
         super(frameCount, propertyIds);
         this.drawOrders = new int[frameCount][];
      }

      @Override
      public int getFrameCount() {
         return this.frames.length;
      }

      public int[][] getDrawOrders() {
         return this.drawOrders;
      }

      public void setFrame(int frame, float time, @Null int[] drawOrder) {
         this.frames[frame] = time;
         this.drawOrders[frame] = drawOrder;
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         if (direction == Animation.MixDirection.out) {
            if (blend == Animation.MixBlend.setup) {
               SpineUtils.arraycopy(skeleton.slots.items, 0, skeleton.drawOrder.items, 0, skeleton.slots.size);
            }
         } else if (time < this.frames[0]) {
            if (blend == Animation.MixBlend.setup || blend == Animation.MixBlend.first) {
               SpineUtils.arraycopy(skeleton.slots.items, 0, skeleton.drawOrder.items, 0, skeleton.slots.size);
            }
         } else {
            int[] drawOrderToSetupIndex = this.drawOrders[search(this.frames, time)];
            if (drawOrderToSetupIndex == null) {
               SpineUtils.arraycopy(skeleton.slots.items, 0, skeleton.drawOrder.items, 0, skeleton.slots.size);
            } else {
               Object[] slots = skeleton.slots.items;
               Object[] drawOrder = skeleton.drawOrder.items;
               int i = 0;

               for (int n = drawOrderToSetupIndex.length; i < n; i++) {
                  drawOrder[i] = slots[drawOrderToSetupIndex[i]];
               }
            }
         }
      }
   }

   public static class EventTimeline extends Animation.Timeline {
      private static final String[] propertyIds = new String[]{Integer.toString(Animation.Property.event.ordinal())};
      private final Event[] events;

      public EventTimeline(int frameCount) {
         super(frameCount, propertyIds);
         this.events = new Event[frameCount];
      }

      @Override
      public int getFrameCount() {
         return this.frames.length;
      }

      public Event[] getEvents() {
         return this.events;
      }

      public void setFrame(int frame, Event event) {
         this.frames[frame] = event.time;
         this.events[frame] = event;
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array firedEvents, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         if (firedEvents != null) {
            float[] frames = this.frames;
            int frameCount = frames.length;
            if (lastTime > time) {
               this.apply(skeleton, lastTime, 2.1474836E9F, firedEvents, alpha, blend, direction);
               lastTime = -1.0F;
            } else if (lastTime >= frames[frameCount - 1]) {
               return;
            }

            if (!(time < frames[0])) {
               int i;
               if (lastTime < frames[0]) {
                  i = 0;
               } else {
                  i = search(frames, lastTime) + 1;
                  float frameTime = frames[i];

                  while (i > 0 && frames[i - 1] == frameTime) {
                     i--;
                  }
               }

               while (i < frameCount && time >= frames[i]) {
                  firedEvents.add(this.events[i]);
                  i++;
               }
            }
         }
      }
   }

   public static class IkConstraintTimeline extends Animation.CurveTimeline {
      public static final int ENTRIES = 6;
      private static final int MIX = 1;
      private static final int SOFTNESS = 2;
      private static final int BEND_DIRECTION = 3;
      private static final int COMPRESS = 4;
      private static final int STRETCH = 5;
      final int ikConstraintIndex;

      public IkConstraintTimeline(int frameCount, int bezierCount, int ikConstraintIndex) {
         super(frameCount, bezierCount, Animation.Property.ikConstraint.ordinal() + "|" + ikConstraintIndex);
         this.ikConstraintIndex = ikConstraintIndex;
      }

      @Override
      public int getFrameEntries() {
         return 6;
      }

      public int getIkConstraintIndex() {
         return this.ikConstraintIndex;
      }

      public void setFrame(int frame, float time, float mix, float softness, int bendDirection, boolean compress, boolean stretch) {
         frame *= 6;
         this.frames[frame] = time;
         this.frames[frame + 1] = mix;
         this.frames[frame + 2] = softness;
         this.frames[frame + 3] = bendDirection;
         this.frames[frame + 4] = compress ? 1 : 0;
         this.frames[frame + 5] = stretch ? 1 : 0;
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         IkConstraint constraint = (IkConstraint)skeleton.ikConstraints.get(this.ikConstraintIndex);
         if (constraint.active) {
            float[] frames = this.frames;
            if (time < frames[0]) {
               switch (blend) {
                  case setup:
                     constraint.mix = constraint.data.mix;
                     constraint.softness = constraint.data.softness;
                     constraint.bendDirection = constraint.data.bendDirection;
                     constraint.compress = constraint.data.compress;
                     constraint.stretch = constraint.data.stretch;
                     return;
                  case first:
                     constraint.mix = constraint.mix + (constraint.data.mix - constraint.mix) * alpha;
                     constraint.softness = constraint.softness + (constraint.data.softness - constraint.softness) * alpha;
                     constraint.bendDirection = constraint.data.bendDirection;
                     constraint.compress = constraint.data.compress;
                     constraint.stretch = constraint.data.stretch;
               }
            } else {
               int i = search(frames, time, 6);
               int curveType = (int)this.curves[i / 6];
               float mix;
               float softness;
               switch (curveType) {
                  case 0:
                     float before = frames[i];
                     mix = frames[i + 1];
                     softness = frames[i + 2];
                     float t = (time - before) / (frames[i + 6] - before);
                     mix += (frames[i + 6 + 1] - mix) * t;
                     softness += (frames[i + 6 + 2] - softness) * t;
                     break;
                  case 1:
                     mix = frames[i + 1];
                     softness = frames[i + 2];
                     break;
                  default:
                     mix = this.getBezierValue(time, i, 1, curveType - 2);
                     softness = this.getBezierValue(time, i, 2, curveType + 18 - 2);
               }

               if (blend == Animation.MixBlend.setup) {
                  constraint.mix = constraint.data.mix + (mix - constraint.data.mix) * alpha;
                  constraint.softness = constraint.data.softness + (softness - constraint.data.softness) * alpha;
                  if (direction == Animation.MixDirection.out) {
                     constraint.bendDirection = constraint.data.bendDirection;
                     constraint.compress = constraint.data.compress;
                     constraint.stretch = constraint.data.stretch;
                  } else {
                     constraint.bendDirection = (int)frames[i + 3];
                     constraint.compress = frames[i + 4] != 0.0F;
                     constraint.stretch = frames[i + 5] != 0.0F;
                  }
               } else {
                  constraint.mix = constraint.mix + (mix - constraint.mix) * alpha;
                  constraint.softness = constraint.softness + (softness - constraint.softness) * alpha;
                  if (direction == Animation.MixDirection.in) {
                     constraint.bendDirection = (int)frames[i + 3];
                     constraint.compress = frames[i + 4] != 0.0F;
                     constraint.stretch = frames[i + 5] != 0.0F;
                  }
               }
            }
         }
      }
   }

   public static enum MixBlend {
      setup,
      first,
      replace,
      add;
   }

   public static enum MixDirection {
      in,
      out;
   }

   public static class PathConstraintMixTimeline extends Animation.CurveTimeline {
      public static final int ENTRIES = 4;
      private static final int ROTATE = 1;
      private static final int X = 2;
      private static final int Y = 3;
      final int pathConstraintIndex;

      public PathConstraintMixTimeline(int frameCount, int bezierCount, int pathConstraintIndex) {
         super(frameCount, bezierCount, Animation.Property.pathConstraintMix.ordinal() + "|" + pathConstraintIndex);
         this.pathConstraintIndex = pathConstraintIndex;
      }

      @Override
      public int getFrameEntries() {
         return 4;
      }

      public int getPathConstraintIndex() {
         return this.pathConstraintIndex;
      }

      public void setFrame(int frame, float time, float mixRotate, float mixX, float mixY) {
         frame <<= 2;
         this.frames[frame] = time;
         this.frames[frame + 1] = mixRotate;
         this.frames[frame + 2] = mixX;
         this.frames[frame + 3] = mixY;
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         PathConstraint constraint = (PathConstraint)skeleton.pathConstraints.get(this.pathConstraintIndex);
         if (constraint.active) {
            float[] frames = this.frames;
            if (time < frames[0]) {
               PathConstraintData data = constraint.data;
               switch (blend) {
                  case setup:
                     constraint.mixRotate = data.mixRotate;
                     constraint.mixX = data.mixX;
                     constraint.mixY = data.mixY;
                     return;
                  case first:
                     constraint.mixRotate = constraint.mixRotate + (data.mixRotate - constraint.mixRotate) * alpha;
                     constraint.mixX = constraint.mixX + (data.mixX - constraint.mixX) * alpha;
                     constraint.mixY = constraint.mixY + (data.mixY - constraint.mixY) * alpha;
               }
            } else {
               int i = search(frames, time, 4);
               int curveType = (int)this.curves[i >> 2];
               float rotate;
               float x;
               float y;
               switch (curveType) {
                  case 0:
                     float before = frames[i];
                     rotate = frames[i + 1];
                     x = frames[i + 2];
                     y = frames[i + 3];
                     float t = (time - before) / (frames[i + 4] - before);
                     rotate += (frames[i + 4 + 1] - rotate) * t;
                     x += (frames[i + 4 + 2] - x) * t;
                     y += (frames[i + 4 + 3] - y) * t;
                     break;
                  case 1:
                     rotate = frames[i + 1];
                     x = frames[i + 2];
                     y = frames[i + 3];
                     break;
                  default:
                     rotate = this.getBezierValue(time, i, 1, curveType - 2);
                     x = this.getBezierValue(time, i, 2, curveType + 18 - 2);
                     y = this.getBezierValue(time, i, 3, curveType + 36 - 2);
               }

               if (blend == Animation.MixBlend.setup) {
                  PathConstraintData data = constraint.data;
                  constraint.mixRotate = data.mixRotate + (rotate - data.mixRotate) * alpha;
                  constraint.mixX = data.mixX + (x - data.mixX) * alpha;
                  constraint.mixY = data.mixY + (y - data.mixY) * alpha;
               } else {
                  constraint.mixRotate = constraint.mixRotate + (rotate - constraint.mixRotate) * alpha;
                  constraint.mixX = constraint.mixX + (x - constraint.mixX) * alpha;
                  constraint.mixY = constraint.mixY + (y - constraint.mixY) * alpha;
               }
            }
         }
      }
   }

   public static class PathConstraintPositionTimeline extends Animation.CurveTimeline1 {
      final int pathConstraintIndex;

      public PathConstraintPositionTimeline(int frameCount, int bezierCount, int pathConstraintIndex) {
         super(frameCount, bezierCount, Animation.Property.pathConstraintPosition.ordinal() + "|" + pathConstraintIndex);
         this.pathConstraintIndex = pathConstraintIndex;
      }

      public int getPathConstraintIndex() {
         return this.pathConstraintIndex;
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         PathConstraint constraint = (PathConstraint)skeleton.pathConstraints.get(this.pathConstraintIndex);
         if (constraint.active) {
            if (time < this.frames[0]) {
               switch (blend) {
                  case setup:
                     constraint.position = constraint.data.position;
                     return;
                  case first:
                     constraint.position = constraint.position + (constraint.data.position - constraint.position) * alpha;
               }
            } else {
               float position = this.getCurveValue(time);
               if (blend == Animation.MixBlend.setup) {
                  constraint.position = constraint.data.position + (position - constraint.data.position) * alpha;
               } else {
                  constraint.position = constraint.position + (position - constraint.position) * alpha;
               }
            }
         }
      }
   }

   public static class PathConstraintSpacingTimeline extends Animation.CurveTimeline1 {
      final int pathConstraintIndex;

      public PathConstraintSpacingTimeline(int frameCount, int bezierCount, int pathConstraintIndex) {
         super(frameCount, bezierCount, Animation.Property.pathConstraintSpacing.ordinal() + "|" + pathConstraintIndex);
         this.pathConstraintIndex = pathConstraintIndex;
      }

      public int getPathConstraintIndex() {
         return this.pathConstraintIndex;
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         PathConstraint constraint = (PathConstraint)skeleton.pathConstraints.get(this.pathConstraintIndex);
         if (constraint.active) {
            if (time < this.frames[0]) {
               switch (blend) {
                  case setup:
                     constraint.spacing = constraint.data.spacing;
                     return;
                  case first:
                     constraint.spacing = constraint.spacing + (constraint.data.spacing - constraint.spacing) * alpha;
               }
            } else {
               float spacing = this.getCurveValue(time);
               if (blend == Animation.MixBlend.setup) {
                  constraint.spacing = constraint.data.spacing + (spacing - constraint.data.spacing) * alpha;
               } else {
                  constraint.spacing = constraint.spacing + (spacing - constraint.spacing) * alpha;
               }
            }
         }
      }
   }

   private static enum Property {
      rotate,
      x,
      y,
      scaleX,
      scaleY,
      shearX,
      shearY,
      rgb,
      alpha,
      rgb2,
      attachment,
      deform,
      event,
      drawOrder,
      ikConstraint,
      transformConstraint,
      pathConstraintPosition,
      pathConstraintSpacing,
      pathConstraintMix,
      sequence;
   }

   public static class RGB2Timeline extends Animation.CurveTimeline implements Animation.SlotTimeline {
      public static final int ENTRIES = 7;
      private static final int R = 1;
      private static final int G = 2;
      private static final int B = 3;
      private static final int R2 = 4;
      private static final int G2 = 5;
      private static final int B2 = 6;
      final int slotIndex;

      public RGB2Timeline(int frameCount, int bezierCount, int slotIndex) {
         super(frameCount, bezierCount, Animation.Property.rgb.ordinal() + "|" + slotIndex, Animation.Property.rgb2.ordinal() + "|" + slotIndex);
         this.slotIndex = slotIndex;
      }

      @Override
      public int getFrameEntries() {
         return 7;
      }

      @Override
      public int getSlotIndex() {
         return this.slotIndex;
      }

      public void setFrame(int frame, float time, float r, float g, float b, float r2, float g2, float b2) {
         frame *= 7;
         this.frames[frame] = time;
         this.frames[frame + 1] = r;
         this.frames[frame + 2] = g;
         this.frames[frame + 3] = b;
         this.frames[frame + 4] = r2;
         this.frames[frame + 5] = g2;
         this.frames[frame + 6] = b2;
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         Slot slot = (Slot)skeleton.slots.get(this.slotIndex);
         if (slot.bone.active) {
            float[] frames = this.frames;
            Color light = slot.color;
            Color dark = slot.darkColor;
            if (time < frames[0]) {
               Color setupLight = slot.data.color;
               Color setupDark = slot.data.darkColor;
               switch (blend) {
                  case setup:
                     light.r = setupLight.r;
                     light.g = setupLight.g;
                     light.b = setupLight.b;
                     dark.r = setupDark.r;
                     dark.g = setupDark.g;
                     dark.b = setupDark.b;
                     return;
                  case first:
                     light.r = light.r + (setupLight.r - light.r) * alpha;
                     light.g = light.g + (setupLight.g - light.g) * alpha;
                     light.b = light.b + (setupLight.b - light.b) * alpha;
                     dark.r = dark.r + (setupDark.r - dark.r) * alpha;
                     dark.g = dark.g + (setupDark.g - dark.g) * alpha;
                     dark.b = dark.b + (setupDark.b - dark.b) * alpha;
               }
            } else {
               int i = search(frames, time, 7);
               int curveType = (int)this.curves[i / 7];
               float r;
               float g;
               float b;
               float r2;
               float g2;
               float b2;
               switch (curveType) {
                  case 0:
                     float before = frames[i];
                     r = frames[i + 1];
                     g = frames[i + 2];
                     b = frames[i + 3];
                     r2 = frames[i + 4];
                     g2 = frames[i + 5];
                     b2 = frames[i + 6];
                     float t = (time - before) / (frames[i + 7] - before);
                     r += (frames[i + 7 + 1] - r) * t;
                     g += (frames[i + 7 + 2] - g) * t;
                     b += (frames[i + 7 + 3] - b) * t;
                     r2 += (frames[i + 7 + 4] - r2) * t;
                     g2 += (frames[i + 7 + 5] - g2) * t;
                     b2 += (frames[i + 7 + 6] - b2) * t;
                     break;
                  case 1:
                     r = frames[i + 1];
                     g = frames[i + 2];
                     b = frames[i + 3];
                     r2 = frames[i + 4];
                     g2 = frames[i + 5];
                     b2 = frames[i + 6];
                     break;
                  default:
                     r = this.getBezierValue(time, i, 1, curveType - 2);
                     g = this.getBezierValue(time, i, 2, curveType + 18 - 2);
                     b = this.getBezierValue(time, i, 3, curveType + 36 - 2);
                     r2 = this.getBezierValue(time, i, 4, curveType + 54 - 2);
                     g2 = this.getBezierValue(time, i, 5, curveType + 72 - 2);
                     b2 = this.getBezierValue(time, i, 6, curveType + 90 - 2);
               }

               if (alpha == 1.0F) {
                  light.r = r;
                  light.g = g;
                  light.b = b;
                  dark.r = r2;
                  dark.g = g2;
                  dark.b = b2;
               } else {
                  if (blend == Animation.MixBlend.setup) {
                     Color setupLight = slot.data.color;
                     Color setupDark = slot.data.darkColor;
                     light.r = setupLight.r;
                     light.g = setupLight.g;
                     light.b = setupLight.b;
                     dark.r = setupDark.r;
                     dark.g = setupDark.g;
                     dark.b = setupDark.b;
                  }

                  light.r = light.r + (r - light.r) * alpha;
                  light.g = light.g + (g - light.g) * alpha;
                  light.b = light.b + (b - light.b) * alpha;
                  dark.r = dark.r + (r2 - dark.r) * alpha;
                  dark.g = dark.g + (g2 - dark.g) * alpha;
                  dark.b = dark.b + (b2 - dark.b) * alpha;
               }
            }
         }
      }
   }

   public static class RGBA2Timeline extends Animation.CurveTimeline implements Animation.SlotTimeline {
      public static final int ENTRIES = 8;
      private static final int R = 1;
      private static final int G = 2;
      private static final int B = 3;
      private static final int A = 4;
      private static final int R2 = 5;
      private static final int G2 = 6;
      private static final int B2 = 7;
      final int slotIndex;

      public RGBA2Timeline(int frameCount, int bezierCount, int slotIndex) {
         super(
            frameCount,
            bezierCount,
            Animation.Property.rgb.ordinal() + "|" + slotIndex,
            Animation.Property.alpha.ordinal() + "|" + slotIndex,
            Animation.Property.rgb2.ordinal() + "|" + slotIndex
         );
         this.slotIndex = slotIndex;
      }

      @Override
      public int getFrameEntries() {
         return 8;
      }

      @Override
      public int getSlotIndex() {
         return this.slotIndex;
      }

      public void setFrame(int frame, float time, float r, float g, float b, float a, float r2, float g2, float b2) {
         frame <<= 3;
         this.frames[frame] = time;
         this.frames[frame + 1] = r;
         this.frames[frame + 2] = g;
         this.frames[frame + 3] = b;
         this.frames[frame + 4] = a;
         this.frames[frame + 5] = r2;
         this.frames[frame + 6] = g2;
         this.frames[frame + 7] = b2;
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         Slot slot = (Slot)skeleton.slots.get(this.slotIndex);
         if (slot.bone.active) {
            float[] frames = this.frames;
            Color light = slot.color;
            Color dark = slot.darkColor;
            if (time < frames[0]) {
               Color setupLight = slot.data.color;
               Color setupDark = slot.data.darkColor;
               switch (blend) {
                  case setup:
                     light.set(setupLight);
                     dark.r = setupDark.r;
                     dark.g = setupDark.g;
                     dark.b = setupDark.b;
                     return;
                  case first:
                     light.add(
                        (setupLight.r - light.r) * alpha, (setupLight.g - light.g) * alpha, (setupLight.b - light.b) * alpha, (setupLight.a - light.a) * alpha
                     );
                     dark.r = dark.r + (setupDark.r - dark.r) * alpha;
                     dark.g = dark.g + (setupDark.g - dark.g) * alpha;
                     dark.b = dark.b + (setupDark.b - dark.b) * alpha;
               }
            } else {
               int i = search(frames, time, 8);
               int curveType = (int)this.curves[i >> 3];
               float r;
               float g;
               float b;
               float a;
               float r2;
               float g2;
               float b2;
               switch (curveType) {
                  case 0:
                     float before = frames[i];
                     r = frames[i + 1];
                     g = frames[i + 2];
                     b = frames[i + 3];
                     a = frames[i + 4];
                     r2 = frames[i + 5];
                     g2 = frames[i + 6];
                     b2 = frames[i + 7];
                     float t = (time - before) / (frames[i + 8] - before);
                     r += (frames[i + 8 + 1] - r) * t;
                     g += (frames[i + 8 + 2] - g) * t;
                     b += (frames[i + 8 + 3] - b) * t;
                     a += (frames[i + 8 + 4] - a) * t;
                     r2 += (frames[i + 8 + 5] - r2) * t;
                     g2 += (frames[i + 8 + 6] - g2) * t;
                     b2 += (frames[i + 8 + 7] - b2) * t;
                     break;
                  case 1:
                     r = frames[i + 1];
                     g = frames[i + 2];
                     b = frames[i + 3];
                     a = frames[i + 4];
                     r2 = frames[i + 5];
                     g2 = frames[i + 6];
                     b2 = frames[i + 7];
                     break;
                  default:
                     r = this.getBezierValue(time, i, 1, curveType - 2);
                     g = this.getBezierValue(time, i, 2, curveType + 18 - 2);
                     b = this.getBezierValue(time, i, 3, curveType + 36 - 2);
                     a = this.getBezierValue(time, i, 4, curveType + 54 - 2);
                     r2 = this.getBezierValue(time, i, 5, curveType + 72 - 2);
                     g2 = this.getBezierValue(time, i, 6, curveType + 90 - 2);
                     b2 = this.getBezierValue(time, i, 7, curveType + 108 - 2);
               }

               if (alpha == 1.0F) {
                  light.set(r, g, b, a);
                  dark.r = r2;
                  dark.g = g2;
                  dark.b = b2;
               } else {
                  if (blend == Animation.MixBlend.setup) {
                     light.set(slot.data.color);
                     Color setupDark = slot.data.darkColor;
                     dark.r = setupDark.r;
                     dark.g = setupDark.g;
                     dark.b = setupDark.b;
                  }

                  light.add((r - light.r) * alpha, (g - light.g) * alpha, (b - light.b) * alpha, (a - light.a) * alpha);
                  dark.r = dark.r + (r2 - dark.r) * alpha;
                  dark.g = dark.g + (g2 - dark.g) * alpha;
                  dark.b = dark.b + (b2 - dark.b) * alpha;
               }
            }
         }
      }
   }

   public static class RGBATimeline extends Animation.CurveTimeline implements Animation.SlotTimeline {
      public static final int ENTRIES = 5;
      private static final int R = 1;
      private static final int G = 2;
      private static final int B = 3;
      private static final int A = 4;
      final int slotIndex;

      public RGBATimeline(int frameCount, int bezierCount, int slotIndex) {
         super(frameCount, bezierCount, Animation.Property.rgb.ordinal() + "|" + slotIndex, Animation.Property.alpha.ordinal() + "|" + slotIndex);
         this.slotIndex = slotIndex;
      }

      @Override
      public int getFrameEntries() {
         return 5;
      }

      @Override
      public int getSlotIndex() {
         return this.slotIndex;
      }

      public void setFrame(int frame, float time, float r, float g, float b, float a) {
         frame *= 5;
         this.frames[frame] = time;
         this.frames[frame + 1] = r;
         this.frames[frame + 2] = g;
         this.frames[frame + 3] = b;
         this.frames[frame + 4] = a;
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         Slot slot = (Slot)skeleton.slots.get(this.slotIndex);
         if (slot.bone.active) {
            float[] frames = this.frames;
            Color color = slot.color;
            if (time < frames[0]) {
               Color setup = slot.data.color;
               switch (blend) {
                  case setup:
                     color.set(setup);
                     return;
                  case first:
                     color.add((setup.r - color.r) * alpha, (setup.g - color.g) * alpha, (setup.b - color.b) * alpha, (setup.a - color.a) * alpha);
               }
            } else {
               int i = search(frames, time, 5);
               int curveType = (int)this.curves[i / 5];
               float r;
               float g;
               float b;
               float a;
               switch (curveType) {
                  case 0:
                     float before = frames[i];
                     r = frames[i + 1];
                     g = frames[i + 2];
                     b = frames[i + 3];
                     a = frames[i + 4];
                     float t = (time - before) / (frames[i + 5] - before);
                     r += (frames[i + 5 + 1] - r) * t;
                     g += (frames[i + 5 + 2] - g) * t;
                     b += (frames[i + 5 + 3] - b) * t;
                     a += (frames[i + 5 + 4] - a) * t;
                     break;
                  case 1:
                     r = frames[i + 1];
                     g = frames[i + 2];
                     b = frames[i + 3];
                     a = frames[i + 4];
                     break;
                  default:
                     r = this.getBezierValue(time, i, 1, curveType - 2);
                     g = this.getBezierValue(time, i, 2, curveType + 18 - 2);
                     b = this.getBezierValue(time, i, 3, curveType + 36 - 2);
                     a = this.getBezierValue(time, i, 4, curveType + 54 - 2);
               }

               if (alpha == 1.0F) {
                  color.set(r, g, b, a);
               } else {
                  if (blend == Animation.MixBlend.setup) {
                     color.set(slot.data.color);
                  }

                  color.add((r - color.r) * alpha, (g - color.g) * alpha, (b - color.b) * alpha, (a - color.a) * alpha);
               }
            }
         }
      }
   }

   public static class RGBTimeline extends Animation.CurveTimeline implements Animation.SlotTimeline {
      public static final int ENTRIES = 4;
      private static final int R = 1;
      private static final int G = 2;
      private static final int B = 3;
      final int slotIndex;

      public RGBTimeline(int frameCount, int bezierCount, int slotIndex) {
         super(frameCount, bezierCount, Animation.Property.rgb.ordinal() + "|" + slotIndex);
         this.slotIndex = slotIndex;
      }

      @Override
      public int getFrameEntries() {
         return 4;
      }

      @Override
      public int getSlotIndex() {
         return this.slotIndex;
      }

      public void setFrame(int frame, float time, float r, float g, float b) {
         frame <<= 2;
         this.frames[frame] = time;
         this.frames[frame + 1] = r;
         this.frames[frame + 2] = g;
         this.frames[frame + 3] = b;
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         Slot slot = (Slot)skeleton.slots.get(this.slotIndex);
         if (slot.bone.active) {
            float[] frames = this.frames;
            Color color = slot.color;
            if (time < frames[0]) {
               Color setup = slot.data.color;
               switch (blend) {
                  case setup:
                     color.r = setup.r;
                     color.g = setup.g;
                     color.b = setup.b;
                     return;
                  case first:
                     color.r = color.r + (setup.r - color.r) * alpha;
                     color.g = color.g + (setup.g - color.g) * alpha;
                     color.b = color.b + (setup.b - color.b) * alpha;
               }
            } else {
               int i = search(frames, time, 4);
               int curveType = (int)this.curves[i >> 2];
               float r;
               float g;
               float b;
               switch (curveType) {
                  case 0:
                     float before = frames[i];
                     r = frames[i + 1];
                     g = frames[i + 2];
                     b = frames[i + 3];
                     float t = (time - before) / (frames[i + 4] - before);
                     r += (frames[i + 4 + 1] - r) * t;
                     g += (frames[i + 4 + 2] - g) * t;
                     b += (frames[i + 4 + 3] - b) * t;
                     break;
                  case 1:
                     r = frames[i + 1];
                     g = frames[i + 2];
                     b = frames[i + 3];
                     break;
                  default:
                     r = this.getBezierValue(time, i, 1, curveType - 2);
                     g = this.getBezierValue(time, i, 2, curveType + 18 - 2);
                     b = this.getBezierValue(time, i, 3, curveType + 36 - 2);
               }

               if (alpha == 1.0F) {
                  color.r = r;
                  color.g = g;
                  color.b = b;
               } else {
                  if (blend == Animation.MixBlend.setup) {
                     Color setup = slot.data.color;
                     color.r = setup.r;
                     color.g = setup.g;
                     color.b = setup.b;
                  }

                  color.r = color.r + (r - color.r) * alpha;
                  color.g = color.g + (g - color.g) * alpha;
                  color.b = color.b + (b - color.b) * alpha;
               }
            }
         }
      }
   }

   public static class RotateTimeline extends Animation.CurveTimeline1 implements Animation.BoneTimeline {
      final int boneIndex;

      public RotateTimeline(int frameCount, int bezierCount, int boneIndex) {
         super(frameCount, bezierCount, Animation.Property.rotate.ordinal() + "|" + boneIndex);
         this.boneIndex = boneIndex;
      }

      @Override
      public int getBoneIndex() {
         return this.boneIndex;
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         Bone bone = (Bone)skeleton.bones.get(this.boneIndex);
         if (bone.active) {
            if (time < this.frames[0]) {
               switch (blend) {
                  case setup:
                     bone.rotation = bone.data.rotation;
                     return;
                  case first:
                     bone.rotation = bone.rotation + (bone.data.rotation - bone.rotation) * alpha;
               }
            } else {
               float r = this.getCurveValue(time);
               switch (blend) {
                  case setup:
                     bone.rotation = bone.data.rotation + r * alpha;
                     break;
                  case first:
                  case replace:
                     r += bone.data.rotation - bone.rotation;
                  case add:
                     bone.rotation += r * alpha;
               }
            }
         }
      }
   }

   public static class ScaleTimeline extends Animation.CurveTimeline2 implements Animation.BoneTimeline {
      final int boneIndex;

      public ScaleTimeline(int frameCount, int bezierCount, int boneIndex) {
         super(frameCount, bezierCount, Animation.Property.scaleX.ordinal() + "|" + boneIndex, Animation.Property.scaleY.ordinal() + "|" + boneIndex);
         this.boneIndex = boneIndex;
      }

      @Override
      public int getBoneIndex() {
         return this.boneIndex;
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         Bone bone = (Bone)skeleton.bones.get(this.boneIndex);
         if (bone.active) {
            float[] frames = this.frames;
            if (time < frames[0]) {
               switch (blend) {
                  case setup:
                     bone.scaleX = bone.data.scaleX;
                     bone.scaleY = bone.data.scaleY;
                     return;
                  case first:
                     bone.scaleX = bone.scaleX + (bone.data.scaleX - bone.scaleX) * alpha;
                     bone.scaleY = bone.scaleY + (bone.data.scaleY - bone.scaleY) * alpha;
               }
            } else {
               int i = search(frames, time, 3);
               int curveType = (int)this.curves[i / 3];
               float x;
               float y;
               switch (curveType) {
                  case 0:
                     float before = frames[i];
                     x = frames[i + 1];
                     y = frames[i + 2];
                     float t = (time - before) / (frames[i + 3] - before);
                     x += (frames[i + 3 + 1] - x) * t;
                     y += (frames[i + 3 + 2] - y) * t;
                     break;
                  case 1:
                     x = frames[i + 1];
                     y = frames[i + 2];
                     break;
                  default:
                     x = this.getBezierValue(time, i, 1, curveType - 2);
                     y = this.getBezierValue(time, i, 2, curveType + 18 - 2);
               }

               x *= bone.data.scaleX;
               y *= bone.data.scaleY;
               if (alpha == 1.0F) {
                  if (blend == Animation.MixBlend.add) {
                     bone.scaleX = bone.scaleX + (x - bone.data.scaleX);
                     bone.scaleY = bone.scaleY + (y - bone.data.scaleY);
                  } else {
                     bone.scaleX = x;
                     bone.scaleY = y;
                  }
               } else if (direction == Animation.MixDirection.out) {
                  switch (blend) {
                     case setup: {
                        float bx = bone.data.scaleX;
                        float by = bone.data.scaleY;
                        bone.scaleX = bx + (Math.abs(x) * Math.signum(bx) - bx) * alpha;
                        bone.scaleY = by + (Math.abs(y) * Math.signum(by) - by) * alpha;
                        break;
                     }
                     case first:
                     case replace: {
                        float bx = bone.scaleX;
                        float by = bone.scaleY;
                        bone.scaleX = bx + (Math.abs(x) * Math.signum(bx) - bx) * alpha;
                        bone.scaleY = by + (Math.abs(y) * Math.signum(by) - by) * alpha;
                        break;
                     }
                     case add:
                        bone.scaleX = (x - bone.data.scaleX) * alpha;
                        bone.scaleY = (y - bone.data.scaleY) * alpha;
                  }
               } else {
                  switch (blend) {
                     case setup: {
                        float bx = Math.abs(bone.data.scaleX) * Math.signum(x);
                        float by = Math.abs(bone.data.scaleY) * Math.signum(y);
                        bone.scaleX = bx + (x - bx) * alpha;
                        bone.scaleY = by + (y - by) * alpha;
                        break;
                     }
                     case first:
                     case replace: {
                        float bx = Math.abs(bone.scaleX) * Math.signum(x);
                        float by = Math.abs(bone.scaleY) * Math.signum(y);
                        bone.scaleX = bx + (x - bx) * alpha;
                        bone.scaleY = by + (y - by) * alpha;
                        break;
                     }
                     case add:
                        bone.scaleX = bone.scaleX + (x - bone.data.scaleX) * alpha;
                        bone.scaleY = bone.scaleY + (y - bone.data.scaleY) * alpha;
                  }
               }
            }
         }
      }
   }

   public static class ScaleXTimeline extends Animation.CurveTimeline1 implements Animation.BoneTimeline {
      final int boneIndex;

      public ScaleXTimeline(int frameCount, int bezierCount, int boneIndex) {
         super(frameCount, bezierCount, Animation.Property.scaleX.ordinal() + "|" + boneIndex);
         this.boneIndex = boneIndex;
      }

      @Override
      public int getBoneIndex() {
         return this.boneIndex;
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         Bone bone = (Bone)skeleton.bones.get(this.boneIndex);
         if (bone.active) {
            float[] frames = this.frames;
            if (time < frames[0]) {
               switch (blend) {
                  case setup:
                     bone.scaleX = bone.data.scaleX;
                     return;
                  case first:
                     bone.scaleX = bone.scaleX + (bone.data.scaleX - bone.scaleX) * alpha;
               }
            } else {
               float x = this.getCurveValue(time) * bone.data.scaleX;
               if (alpha == 1.0F) {
                  if (blend == Animation.MixBlend.add) {
                     bone.scaleX = bone.scaleX + (x - bone.data.scaleX);
                  } else {
                     bone.scaleX = x;
                  }
               } else if (direction == Animation.MixDirection.out) {
                  switch (blend) {
                     case setup: {
                        float bx = bone.data.scaleX;
                        bone.scaleX = bx + (Math.abs(x) * Math.signum(bx) - bx) * alpha;
                        break;
                     }
                     case first:
                     case replace: {
                        float bx = bone.scaleX;
                        bone.scaleX = bx + (Math.abs(x) * Math.signum(bx) - bx) * alpha;
                        break;
                     }
                     case add:
                        bone.scaleX = (x - bone.data.scaleX) * alpha;
                  }
               } else {
                  switch (blend) {
                     case setup: {
                        float bx = Math.abs(bone.data.scaleX) * Math.signum(x);
                        bone.scaleX = bx + (x - bx) * alpha;
                        break;
                     }
                     case first:
                     case replace: {
                        float bx = Math.abs(bone.scaleX) * Math.signum(x);
                        bone.scaleX = bx + (x - bx) * alpha;
                        break;
                     }
                     case add:
                        bone.scaleX = bone.scaleX + (x - bone.data.scaleX) * alpha;
                  }
               }
            }
         }
      }
   }

   public static class ScaleYTimeline extends Animation.CurveTimeline1 implements Animation.BoneTimeline {
      final int boneIndex;

      public ScaleYTimeline(int frameCount, int bezierCount, int boneIndex) {
         super(frameCount, bezierCount, Animation.Property.scaleY.ordinal() + "|" + boneIndex);
         this.boneIndex = boneIndex;
      }

      @Override
      public int getBoneIndex() {
         return this.boneIndex;
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         Bone bone = (Bone)skeleton.bones.get(this.boneIndex);
         if (bone.active) {
            float[] frames = this.frames;
            if (time < frames[0]) {
               switch (blend) {
                  case setup:
                     bone.scaleY = bone.data.scaleY;
                     return;
                  case first:
                     bone.scaleY = bone.scaleY + (bone.data.scaleY - bone.scaleY) * alpha;
               }
            } else {
               float y = this.getCurveValue(time) * bone.data.scaleY;
               if (alpha == 1.0F) {
                  if (blend == Animation.MixBlend.add) {
                     bone.scaleY = bone.scaleY + (y - bone.data.scaleY);
                  } else {
                     bone.scaleY = y;
                  }
               } else if (direction == Animation.MixDirection.out) {
                  switch (blend) {
                     case setup: {
                        float by = bone.data.scaleY;
                        bone.scaleY = by + (Math.abs(y) * Math.signum(by) - by) * alpha;
                        break;
                     }
                     case first:
                     case replace: {
                        float by = bone.scaleY;
                        bone.scaleY = by + (Math.abs(y) * Math.signum(by) - by) * alpha;
                        break;
                     }
                     case add:
                        bone.scaleY = (y - bone.data.scaleY) * alpha;
                  }
               } else {
                  switch (blend) {
                     case setup: {
                        float by = Math.abs(bone.data.scaleY) * Math.signum(y);
                        bone.scaleY = by + (y - by) * alpha;
                        break;
                     }
                     case first:
                     case replace: {
                        float by = Math.abs(bone.scaleY) * Math.signum(y);
                        bone.scaleY = by + (y - by) * alpha;
                        break;
                     }
                     case add:
                        bone.scaleY = bone.scaleY + (y - bone.data.scaleY) * alpha;
                  }
               }
            }
         }
      }
   }

   public static class SequenceTimeline extends Animation.Timeline implements Animation.SlotTimeline {
      public static final int ENTRIES = 3;
      private static final int MODE = 1;
      private static final int DELAY = 2;
      final int slotIndex;
      final HasTextureRegion attachment;

      public SequenceTimeline(int frameCount, int slotIndex, Attachment attachment) {
         super(frameCount, Animation.Property.sequence.ordinal() + "|" + slotIndex + "|" + ((HasTextureRegion)attachment).getSequence().getId());
         this.slotIndex = slotIndex;
         this.attachment = (HasTextureRegion)attachment;
      }

      @Override
      public int getFrameEntries() {
         return 3;
      }

      @Override
      public int getSlotIndex() {
         return this.slotIndex;
      }

      public Attachment getAttachment() {
         return (Attachment)this.attachment;
      }

      public void setFrame(int frame, float time, Sequence.SequenceMode mode, int index, float delay) {
         frame *= 3;
         this.frames[frame] = time;
         this.frames[frame + 1] = mode.ordinal() | index << 4;
         this.frames[frame + 2] = delay;
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         Slot slot = (Slot)skeleton.slots.get(this.slotIndex);
         if (slot.bone.active) {
            Attachment slotAttachment = slot.attachment;
            if (slotAttachment == this.attachment
               || slotAttachment instanceof VertexAttachment && ((VertexAttachment)slotAttachment).getTimelineAttachment() == this.attachment) {
               float[] frames = this.frames;
               if (!(time < frames[0])) {
                  int i = search(frames, time, 3);
                  float before = frames[i];
                  int modeAndIndex = (int)frames[i + 1];
                  float delay = frames[i + 2];
                  int index = modeAndIndex >> 4;
                  int count = this.attachment.getSequence().getRegions().length;
                  Sequence.SequenceMode mode = Sequence.SequenceMode.values[modeAndIndex & 15];
                  if (mode != Sequence.SequenceMode.hold) {
                     index = (int)(index + (time - before) / delay + 1.0E-5F);
                     switch (mode) {
                        case once:
                           index = Math.min(count - 1, index);
                           break;
                        case loop:
                           index %= count;
                           break;
                        case pingpong:
                           int n = (count << 1) - 2;
                           index %= n;
                           if (index >= count) {
                              index = n - index;
                           }
                           break;
                        case onceReverse:
                           index = Math.max(count - 1 - index, 0);
                           break;
                        case loopReverse:
                           index = count - 1 - index % count;
                     }
                  }

                  slot.setSequenceIndex(index);
               } else {
                  if (blend == Animation.MixBlend.setup || blend == Animation.MixBlend.first) {
                     slot.setSequenceIndex(-1);
                  }
               }
            }
         }
      }
   }

   public static class ShearTimeline extends Animation.CurveTimeline2 implements Animation.BoneTimeline {
      final int boneIndex;

      public ShearTimeline(int frameCount, int bezierCount, int boneIndex) {
         super(frameCount, bezierCount, Animation.Property.shearX.ordinal() + "|" + boneIndex, Animation.Property.shearY.ordinal() + "|" + boneIndex);
         this.boneIndex = boneIndex;
      }

      @Override
      public int getBoneIndex() {
         return this.boneIndex;
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         Bone bone = (Bone)skeleton.bones.get(this.boneIndex);
         if (bone.active) {
            float[] frames = this.frames;
            if (time < frames[0]) {
               switch (blend) {
                  case setup:
                     bone.shearX = bone.data.shearX;
                     bone.shearY = bone.data.shearY;
                     return;
                  case first:
                     bone.shearX = bone.shearX + (bone.data.shearX - bone.shearX) * alpha;
                     bone.shearY = bone.shearY + (bone.data.shearY - bone.shearY) * alpha;
               }
            } else {
               int i = search(frames, time, 3);
               int curveType = (int)this.curves[i / 3];
               float x;
               float y;
               switch (curveType) {
                  case 0:
                     float before = frames[i];
                     x = frames[i + 1];
                     y = frames[i + 2];
                     float t = (time - before) / (frames[i + 3] - before);
                     x += (frames[i + 3 + 1] - x) * t;
                     y += (frames[i + 3 + 2] - y) * t;
                     break;
                  case 1:
                     x = frames[i + 1];
                     y = frames[i + 2];
                     break;
                  default:
                     x = this.getBezierValue(time, i, 1, curveType - 2);
                     y = this.getBezierValue(time, i, 2, curveType + 18 - 2);
               }

               switch (blend) {
                  case setup:
                     bone.shearX = bone.data.shearX + x * alpha;
                     bone.shearY = bone.data.shearY + y * alpha;
                     break;
                  case first:
                  case replace:
                     bone.shearX = bone.shearX + (bone.data.shearX + x - bone.shearX) * alpha;
                     bone.shearY = bone.shearY + (bone.data.shearY + y - bone.shearY) * alpha;
                     break;
                  case add:
                     bone.shearX += x * alpha;
                     bone.shearY += y * alpha;
               }
            }
         }
      }
   }

   public static class ShearXTimeline extends Animation.CurveTimeline1 implements Animation.BoneTimeline {
      final int boneIndex;

      public ShearXTimeline(int frameCount, int bezierCount, int boneIndex) {
         super(frameCount, bezierCount, Animation.Property.shearX.ordinal() + "|" + boneIndex);
         this.boneIndex = boneIndex;
      }

      @Override
      public int getBoneIndex() {
         return this.boneIndex;
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         Bone bone = (Bone)skeleton.bones.get(this.boneIndex);
         if (bone.active) {
            float[] frames = this.frames;
            if (time < frames[0]) {
               switch (blend) {
                  case setup:
                     bone.shearX = bone.data.shearX;
                     return;
                  case first:
                     bone.shearX = bone.shearX + (bone.data.shearX - bone.shearX) * alpha;
               }
            } else {
               float x = this.getCurveValue(time);
               switch (blend) {
                  case setup:
                     bone.shearX = bone.data.shearX + x * alpha;
                     break;
                  case first:
                  case replace:
                     bone.shearX = bone.shearX + (bone.data.shearX + x - bone.shearX) * alpha;
                     break;
                  case add:
                     bone.shearX += x * alpha;
               }
            }
         }
      }
   }

   public static class ShearYTimeline extends Animation.CurveTimeline1 implements Animation.BoneTimeline {
      final int boneIndex;

      public ShearYTimeline(int frameCount, int bezierCount, int boneIndex) {
         super(frameCount, bezierCount, Animation.Property.shearY.ordinal() + "|" + boneIndex);
         this.boneIndex = boneIndex;
      }

      @Override
      public int getBoneIndex() {
         return this.boneIndex;
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         Bone bone = (Bone)skeleton.bones.get(this.boneIndex);
         if (bone.active) {
            float[] frames = this.frames;
            if (time < frames[0]) {
               switch (blend) {
                  case setup:
                     bone.shearY = bone.data.shearY;
                     return;
                  case first:
                     bone.shearY = bone.shearY + (bone.data.shearY - bone.shearY) * alpha;
               }
            } else {
               float y = this.getCurveValue(time);
               switch (blend) {
                  case setup:
                     bone.shearY = bone.data.shearY + y * alpha;
                     break;
                  case first:
                  case replace:
                     bone.shearY = bone.shearY + (bone.data.shearY + y - bone.shearY) * alpha;
                     break;
                  case add:
                     bone.shearY += y * alpha;
               }
            }
         }
      }
   }

   public interface SlotTimeline {
      int getSlotIndex();
   }

   public abstract static class Timeline {
      private final String[] propertyIds;
      final float[] frames;

      public Timeline(int frameCount, String... propertyIds) {
         if (propertyIds == null) {
            throw new IllegalArgumentException("propertyIds cannot be null.");
         } else {
            this.propertyIds = propertyIds;
            this.frames = new float[frameCount * this.getFrameEntries()];
         }
      }

      public String[] getPropertyIds() {
         return this.propertyIds;
      }

      public float[] getFrames() {
         return this.frames;
      }

      public int getFrameEntries() {
         return 1;
      }

      public int getFrameCount() {
         return this.frames.length / this.getFrameEntries();
      }

      public float getDuration() {
         return this.frames[this.frames.length - this.getFrameEntries()];
      }

      public abstract void apply(Skeleton var1, float var2, float var3, @Null Array var4, float var5, Animation.MixBlend var6, Animation.MixDirection var7);

      static int search(float[] frames, float time) {
         int n = frames.length;

         for (int i = 1; i < n; i++) {
            if (frames[i] > time) {
               return i - 1;
            }
         }

         return n - 1;
      }

      static int search(float[] frames, float time, int step) {
         int n = frames.length;

         for (int i = step; i < n; i += step) {
            if (frames[i] > time) {
               return i - step;
            }
         }

         return n - step;
      }
   }

   public static class TransformConstraintTimeline extends Animation.CurveTimeline {
      public static final int ENTRIES = 7;
      private static final int ROTATE = 1;
      private static final int X = 2;
      private static final int Y = 3;
      private static final int SCALEX = 4;
      private static final int SCALEY = 5;
      private static final int SHEARY = 6;
      final int transformConstraintIndex;

      public TransformConstraintTimeline(int frameCount, int bezierCount, int transformConstraintIndex) {
         super(frameCount, bezierCount, Animation.Property.transformConstraint.ordinal() + "|" + transformConstraintIndex);
         this.transformConstraintIndex = transformConstraintIndex;
      }

      @Override
      public int getFrameEntries() {
         return 7;
      }

      public int getTransformConstraintIndex() {
         return this.transformConstraintIndex;
      }

      public void setFrame(int frame, float time, float mixRotate, float mixX, float mixY, float mixScaleX, float mixScaleY, float mixShearY) {
         frame *= 7;
         this.frames[frame] = time;
         this.frames[frame + 1] = mixRotate;
         this.frames[frame + 2] = mixX;
         this.frames[frame + 3] = mixY;
         this.frames[frame + 4] = mixScaleX;
         this.frames[frame + 5] = mixScaleY;
         this.frames[frame + 6] = mixShearY;
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         TransformConstraint constraint = (TransformConstraint)skeleton.transformConstraints.get(this.transformConstraintIndex);
         if (constraint.active) {
            float[] frames = this.frames;
            if (time < frames[0]) {
               TransformConstraintData data = constraint.data;
               switch (blend) {
                  case setup:
                     constraint.mixRotate = data.mixRotate;
                     constraint.mixX = data.mixX;
                     constraint.mixY = data.mixY;
                     constraint.mixScaleX = data.mixScaleX;
                     constraint.mixScaleY = data.mixScaleY;
                     constraint.mixShearY = data.mixShearY;
                     return;
                  case first:
                     constraint.mixRotate = constraint.mixRotate + (data.mixRotate - constraint.mixRotate) * alpha;
                     constraint.mixX = constraint.mixX + (data.mixX - constraint.mixX) * alpha;
                     constraint.mixY = constraint.mixY + (data.mixY - constraint.mixY) * alpha;
                     constraint.mixScaleX = constraint.mixScaleX + (data.mixScaleX - constraint.mixScaleX) * alpha;
                     constraint.mixScaleY = constraint.mixScaleY + (data.mixScaleY - constraint.mixScaleY) * alpha;
                     constraint.mixShearY = constraint.mixShearY + (data.mixShearY - constraint.mixShearY) * alpha;
               }
            } else {
               int i = search(frames, time, 7);
               int curveType = (int)this.curves[i / 7];
               float rotate;
               float x;
               float y;
               float scaleX;
               float scaleY;
               float shearY;
               switch (curveType) {
                  case 0:
                     float before = frames[i];
                     rotate = frames[i + 1];
                     x = frames[i + 2];
                     y = frames[i + 3];
                     scaleX = frames[i + 4];
                     scaleY = frames[i + 5];
                     shearY = frames[i + 6];
                     float t = (time - before) / (frames[i + 7] - before);
                     rotate += (frames[i + 7 + 1] - rotate) * t;
                     x += (frames[i + 7 + 2] - x) * t;
                     y += (frames[i + 7 + 3] - y) * t;
                     scaleX += (frames[i + 7 + 4] - scaleX) * t;
                     scaleY += (frames[i + 7 + 5] - scaleY) * t;
                     shearY += (frames[i + 7 + 6] - shearY) * t;
                     break;
                  case 1:
                     rotate = frames[i + 1];
                     x = frames[i + 2];
                     y = frames[i + 3];
                     scaleX = frames[i + 4];
                     scaleY = frames[i + 5];
                     shearY = frames[i + 6];
                     break;
                  default:
                     rotate = this.getBezierValue(time, i, 1, curveType - 2);
                     x = this.getBezierValue(time, i, 2, curveType + 18 - 2);
                     y = this.getBezierValue(time, i, 3, curveType + 36 - 2);
                     scaleX = this.getBezierValue(time, i, 4, curveType + 54 - 2);
                     scaleY = this.getBezierValue(time, i, 5, curveType + 72 - 2);
                     shearY = this.getBezierValue(time, i, 6, curveType + 90 - 2);
               }

               if (blend == Animation.MixBlend.setup) {
                  TransformConstraintData data = constraint.data;
                  constraint.mixRotate = data.mixRotate + (rotate - data.mixRotate) * alpha;
                  constraint.mixX = data.mixX + (x - data.mixX) * alpha;
                  constraint.mixY = data.mixY + (y - data.mixY) * alpha;
                  constraint.mixScaleX = data.mixScaleX + (scaleX - data.mixScaleX) * alpha;
                  constraint.mixScaleY = data.mixScaleY + (scaleY - data.mixScaleY) * alpha;
                  constraint.mixShearY = data.mixShearY + (shearY - data.mixShearY) * alpha;
               } else {
                  constraint.mixRotate = constraint.mixRotate + (rotate - constraint.mixRotate) * alpha;
                  constraint.mixX = constraint.mixX + (x - constraint.mixX) * alpha;
                  constraint.mixY = constraint.mixY + (y - constraint.mixY) * alpha;
                  constraint.mixScaleX = constraint.mixScaleX + (scaleX - constraint.mixScaleX) * alpha;
                  constraint.mixScaleY = constraint.mixScaleY + (scaleY - constraint.mixScaleY) * alpha;
                  constraint.mixShearY = constraint.mixShearY + (shearY - constraint.mixShearY) * alpha;
               }
            }
         }
      }
   }

   public static class TranslateTimeline extends Animation.CurveTimeline2 implements Animation.BoneTimeline {
      final int boneIndex;

      public TranslateTimeline(int frameCount, int bezierCount, int boneIndex) {
         super(frameCount, bezierCount, Animation.Property.x.ordinal() + "|" + boneIndex, Animation.Property.y.ordinal() + "|" + boneIndex);
         this.boneIndex = boneIndex;
      }

      @Override
      public int getBoneIndex() {
         return this.boneIndex;
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         Bone bone = (Bone)skeleton.bones.get(this.boneIndex);
         if (bone.active) {
            float[] frames = this.frames;
            if (time < frames[0]) {
               switch (blend) {
                  case setup:
                     bone.x = bone.data.x;
                     bone.y = bone.data.y;
                     return;
                  case first:
                     bone.x = bone.x + (bone.data.x - bone.x) * alpha;
                     bone.y = bone.y + (bone.data.y - bone.y) * alpha;
               }
            } else {
               int i = search(frames, time, 3);
               int curveType = (int)this.curves[i / 3];
               float x;
               float y;
               switch (curveType) {
                  case 0:
                     float before = frames[i];
                     x = frames[i + 1];
                     y = frames[i + 2];
                     float t = (time - before) / (frames[i + 3] - before);
                     x += (frames[i + 3 + 1] - x) * t;
                     y += (frames[i + 3 + 2] - y) * t;
                     break;
                  case 1:
                     x = frames[i + 1];
                     y = frames[i + 2];
                     break;
                  default:
                     x = this.getBezierValue(time, i, 1, curveType - 2);
                     y = this.getBezierValue(time, i, 2, curveType + 18 - 2);
               }

               switch (blend) {
                  case setup:
                     bone.x = bone.data.x + x * alpha;
                     bone.y = bone.data.y + y * alpha;
                     break;
                  case first:
                  case replace:
                     bone.x = bone.x + (bone.data.x + x - bone.x) * alpha;
                     bone.y = bone.y + (bone.data.y + y - bone.y) * alpha;
                     break;
                  case add:
                     bone.x += x * alpha;
                     bone.y += y * alpha;
               }
            }
         }
      }
   }

   public static class TranslateXTimeline extends Animation.CurveTimeline1 implements Animation.BoneTimeline {
      final int boneIndex;

      public TranslateXTimeline(int frameCount, int bezierCount, int boneIndex) {
         super(frameCount, bezierCount, Animation.Property.x.ordinal() + "|" + boneIndex);
         this.boneIndex = boneIndex;
      }

      @Override
      public int getBoneIndex() {
         return this.boneIndex;
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         Bone bone = (Bone)skeleton.bones.get(this.boneIndex);
         if (bone.active) {
            float[] frames = this.frames;
            if (time < frames[0]) {
               switch (blend) {
                  case setup:
                     bone.x = bone.data.x;
                     return;
                  case first:
                     bone.x = bone.x + (bone.data.x - bone.x) * alpha;
               }
            } else {
               float x = this.getCurveValue(time);
               switch (blend) {
                  case setup:
                     bone.x = bone.data.x + x * alpha;
                     break;
                  case first:
                  case replace:
                     bone.x = bone.x + (bone.data.x + x - bone.x) * alpha;
                     break;
                  case add:
                     bone.x += x * alpha;
               }
            }
         }
      }
   }

   public static class TranslateYTimeline extends Animation.CurveTimeline1 implements Animation.BoneTimeline {
      final int boneIndex;

      public TranslateYTimeline(int frameCount, int bezierCount, int boneIndex) {
         super(frameCount, bezierCount, Animation.Property.y.ordinal() + "|" + boneIndex);
         this.boneIndex = boneIndex;
      }

      @Override
      public int getBoneIndex() {
         return this.boneIndex;
      }

      @Override
      public void apply(
         Skeleton skeleton, float lastTime, float time, @Null Array events, float alpha, Animation.MixBlend blend, Animation.MixDirection direction
      ) {
         Bone bone = (Bone)skeleton.bones.get(this.boneIndex);
         if (bone.active) {
            float[] frames = this.frames;
            if (time < frames[0]) {
               switch (blend) {
                  case setup:
                     bone.y = bone.data.y;
                     return;
                  case first:
                     bone.y = bone.y + (bone.data.y - bone.y) * alpha;
               }
            } else {
               float y = this.getCurveValue(time);
               switch (blend) {
                  case setup:
                     bone.y = bone.data.y + y * alpha;
                     break;
                  case first:
                  case replace:
                     bone.y = bone.y + (bone.data.y + y - bone.y) * alpha;
                     break;
                  case add:
                     bone.y += y * alpha;
               }
            }
         }
      }
   }
}
