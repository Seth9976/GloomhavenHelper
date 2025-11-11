package com.hm.spine;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.StringBuilder;

public class AnimationState {
   static final Animation emptyAnimation = new Animation("<empty>", new Array(0), 0.0F);
   private static final int SUBSEQUENT = 0;
   private static final int FIRST = 1;
   private static final int HOLD_SUBSEQUENT = 2;
   private static final int HOLD_FIRST = 3;
   private static final int HOLD_MIX = 4;
   private static final int SETUP = 1;
   private static final int CURRENT = 2;
   private AnimationStateData data;
   final Array tracks = new Array();
   private final Array events = new Array();
   final SnapshotArray listeners = new SnapshotArray();
   private final AnimationState.EventQueue queue = new AnimationState.EventQueue();
   private final ObjectSet propertyIds = new ObjectSet();
   boolean animationsChanged;
   private float timeScale = 1.0F;
   private int unkeyedState;
   final Pool trackEntryPool;
   private static volatile int[] $SWITCH_TABLE$com$esotericsoftware$spine$Animation$MixBlend;

   public AnimationState() {
      this.trackEntryPool = new Pool() {
         @Override
         protected Object newObject() {
            return new AnimationState.TrackEntry();
         }
      };
   }

   public AnimationState(AnimationStateData data) {
      this.trackEntryPool = new Pool() {
         @Override
         protected Object newObject() {
            return new AnimationState.TrackEntry();
         }
      };
      if (data == null) {
         throw new IllegalArgumentException("data cannot be null.");
      } else {
         this.data = data;
      }
   }

   public void update(float delta) {
      delta *= this.timeScale;
      Object[] tracks = this.tracks.items;
      int i = 0;

      for (int n = this.tracks.size; i < n; i++) {
         AnimationState.TrackEntry current = (AnimationState.TrackEntry)tracks[i];
         if (current != null) {
            current.animationLast = current.nextAnimationLast;
            current.trackLast = current.nextTrackLast;
            float currentDelta = delta * current.timeScale;
            if (current.delay > 0.0F) {
               current.delay -= currentDelta;
               if (current.delay > 0.0F) {
                  continue;
               }

               currentDelta = -current.delay;
               current.delay = 0.0F;
            }

            AnimationState.TrackEntry next = current.next;
            if (next != null) {
               float nextTime = current.trackLast - next.delay;
               if (nextTime >= 0.0F) {
                  next.delay = 0.0F;
                  next.trackTime = next.trackTime + (current.timeScale == 0.0F ? 0.0F : (nextTime / current.timeScale + delta) * next.timeScale);
                  current.trackTime += currentDelta;
                  this.setCurrent(i, next, true);

                  while (next.mixingFrom != null) {
                     next.mixTime += delta;
                     next = next.mixingFrom;
                  }
                  continue;
               }
            } else if (current.trackLast >= current.trackEnd && current.mixingFrom == null) {
               tracks[i] = null;
               this.queue.end(current);
               this.clearNext(current);
               continue;
            }

            if (current.mixingFrom != null && this.updateMixingFrom(current, delta)) {
               AnimationState.TrackEntry from = current.mixingFrom;
               current.mixingFrom = null;
               if (from != null) {
                  from.mixingTo = null;
               }

               while (from != null) {
                  this.queue.end(from);
                  from = from.mixingFrom;
               }
            }

            current.trackTime += currentDelta;
         }
      }

      this.queue.drain();
   }

   private boolean updateMixingFrom(AnimationState.TrackEntry to, float delta) {
      AnimationState.TrackEntry from = to.mixingFrom;
      if (from == null) {
         return true;
      } else {
         boolean finished = this.updateMixingFrom(from, delta);
         from.animationLast = from.nextAnimationLast;
         from.trackLast = from.nextTrackLast;
         if (to.mixTime > 0.0F && to.mixTime >= to.mixDuration) {
            if (from.totalAlpha == 0.0F || to.mixDuration == 0.0F) {
               to.mixingFrom = from.mixingFrom;
               if (from.mixingFrom != null) {
                  from.mixingFrom.mixingTo = to;
               }

               to.interruptAlpha = from.interruptAlpha;
               this.queue.end(from);
            }

            return finished;
         } else {
            from.trackTime = from.trackTime + delta * from.timeScale;
            to.mixTime += delta;
            return false;
         }
      }
   }

   public boolean apply(Skeleton skeleton) {
      if (skeleton == null) {
         throw new IllegalArgumentException("skeleton cannot be null.");
      } else {
         if (this.animationsChanged) {
            this.animationsChanged();
         }

         Array events = this.events;
         boolean applied = false;
         Object[] tracks = this.tracks.items;
         int i = 0;

         for (int n = this.tracks.size; i < n; i++) {
            AnimationState.TrackEntry current = (AnimationState.TrackEntry)tracks[i];
            if (current != null && current.delay <= 0.0F) {
               applied = true;
               Animation.MixBlend blend = i == 0 ? Animation.MixBlend.first : current.mixBlend;
               float mix = current.alpha;
               if (current.mixingFrom != null) {
                  mix *= this.applyMixingFrom(current, skeleton, blend);
               } else if (current.trackTime >= current.trackEnd && current.next == null) {
                  mix = 0.0F;
               }

               float animationLast = current.animationLast;
               float applyTime;
               float animationTime = applyTime = current.getAnimationTime();
               Array applyEvents = events;
               if (current.reverse) {
                  applyTime = current.animation.duration - applyTime;
                  applyEvents = null;
               }

               int timelineCount = current.animation.timelines.size;
               Object[] timelines = current.animation.timelines.items;
               if ((i != 0 || mix != 1.0F) && blend != Animation.MixBlend.add) {
                  int[] timelineMode = current.timelineMode.items;
                  boolean firstFrame = current.timelinesRotation.size != timelineCount << 1;
                  if (firstFrame) {
                     current.timelinesRotation.setSize(timelineCount << 1);
                  }

                  float[] timelinesRotation = current.timelinesRotation.items;

                  for (int ii2 = 0; ii2 < timelineCount; ii2++) {
                     Animation.Timeline timeline2 = (Animation.Timeline)timelines[ii2];
                     Animation.MixBlend timelineBlend = timelineMode[ii2] == 0 ? blend : Animation.MixBlend.setup;
                     if (timeline2 instanceof Animation.RotateTimeline) {
                        this.applyRotateTimeline(
                           (Animation.RotateTimeline)timeline2, skeleton, applyTime, mix, timelineBlend, timelinesRotation, ii2 << 1, firstFrame
                        );
                     } else if (timeline2 instanceof Animation.AttachmentTimeline) {
                        this.applyAttachmentTimeline((Animation.AttachmentTimeline)timeline2, skeleton, applyTime, blend, true);
                     } else {
                        timeline2.apply(skeleton, animationLast, applyTime, applyEvents, mix, timelineBlend, Animation.MixDirection.in);
                     }
                  }
               } else {
                  for (Object timeline : timelines) {
                     if (timeline instanceof Animation.AttachmentTimeline) {
                        this.applyAttachmentTimeline((Animation.AttachmentTimeline)timeline, skeleton, applyTime, blend, true);
                     } else {
                        ((Animation.Timeline)timeline).apply(skeleton, animationLast, applyTime, applyEvents, mix, blend, Animation.MixDirection.in);
                     }
                  }
               }

               this.queueEvents(current, animationTime);
               events.clear();
               current.nextAnimationLast = animationTime;
               current.nextTrackLast = current.trackTime;
            }
         }

         i = this.unkeyedState + 1;
         Object[] slots = skeleton.slots.items;
         int j = 0;

         for (int n2 = skeleton.slots.size; j < n2; j++) {
            Slot slot = (Slot)slots[j];
            if (slot.attachmentState == i) {
               String attachmentName = slot.data.attachmentName;
               slot.setAttachment(attachmentName == null ? null : skeleton.getAttachment(slot.data.index, attachmentName));
            }
         }

         this.unkeyedState += 2;
         this.queue.drain();
         return applied;
      }
   }

   private float applyMixingFrom(AnimationState.TrackEntry to, Skeleton skeleton, Animation.MixBlend blend) {
      AnimationState.TrackEntry from = to.mixingFrom;
      if (from.mixingFrom != null) {
         this.applyMixingFrom(from, skeleton, blend);
      }

      float mix;
      if (to.mixDuration == 0.0F) {
         mix = 1.0F;
         if (blend == Animation.MixBlend.first) {
            blend = Animation.MixBlend.setup;
         }
      } else {
         mix = to.mixTime / to.mixDuration;
         if (mix > 1.0F) {
            mix = 1.0F;
         }

         if (blend != Animation.MixBlend.first) {
            blend = from.mixBlend;
         }
      }

      boolean attachments = mix < from.attachmentThreshold;
      boolean drawOrder = mix < from.drawOrderThreshold;
      int timelineCount = from.animation.timelines.size;
      Object[] timelines = from.animation.timelines.items;
      float alphaHold = from.alpha * to.interruptAlpha;
      float alphaMix = alphaHold * (1.0F - mix);
      float animationLast = from.animationLast;
      float applyTime;
      float animationTime = applyTime = from.getAnimationTime();
      Array events = null;
      if (from.reverse) {
         applyTime = from.animation.duration - applyTime;
      } else if (mix < from.eventThreshold) {
         events = this.events;
      }

      if (blend == Animation.MixBlend.add) {
         for (int i = 0; i < timelineCount; i++) {
            ((Animation.Timeline)timelines[i]).apply(skeleton, animationLast, applyTime, events, alphaMix, blend, Animation.MixDirection.out);
         }
      } else {
         int[] timelineMode = from.timelineMode.items;
         Object[] timelineHoldMix = from.timelineHoldMix.items;
         boolean firstFrame = from.timelinesRotation.size != timelineCount << 1;
         if (firstFrame) {
            from.timelinesRotation.setSize(timelineCount << 1);
         }

         float[] timelinesRotation = from.timelinesRotation.items;
         from.totalAlpha = 0.0F;

         for (int j = 0; j < timelineCount; j++) {
            Animation.Timeline timeline = (Animation.Timeline)timelines[j];
            Animation.MixDirection direction = Animation.MixDirection.out;
            Animation.MixBlend timelineBlend = null;
            float alpha = 0.0F;
            switch (timelineMode[j]) {
               case 0:
                  if (!drawOrder && timeline instanceof Animation.DrawOrderTimeline) {
                     continue;
                  }

                  timelineBlend = blend;
                  alpha = alphaMix;
                  break;
               case 1:
                  timelineBlend = Animation.MixBlend.setup;
                  alpha = alphaMix;
                  break;
               case 2:
                  timelineBlend = blend;
                  alpha = alphaHold;
                  break;
               case 3:
                  timelineBlend = Animation.MixBlend.setup;
                  alpha = alphaHold;
                  break;
               default:
                  timelineBlend = Animation.MixBlend.setup;
                  AnimationState.TrackEntry holdMix = (AnimationState.TrackEntry)timelineHoldMix[j];
                  alpha = alphaHold * Math.max(0.0F, 1.0F - holdMix.mixTime / holdMix.mixDuration);
            }

            from.totalAlpha += alpha;
            if (timeline instanceof Animation.RotateTimeline) {
               this.applyRotateTimeline((Animation.RotateTimeline)timeline, skeleton, applyTime, alpha, timelineBlend, timelinesRotation, j << 1, firstFrame);
            } else if (timeline instanceof Animation.AttachmentTimeline) {
               this.applyAttachmentTimeline((Animation.AttachmentTimeline)timeline, skeleton, applyTime, timelineBlend, attachments);
            } else {
               if (drawOrder && timeline instanceof Animation.DrawOrderTimeline && timelineBlend == Animation.MixBlend.setup) {
                  direction = Animation.MixDirection.in;
               }

               timeline.apply(skeleton, animationLast, applyTime, events, alpha, timelineBlend, direction);
            }
         }
      }

      if (to.mixDuration > 0.0F) {
         this.queueEvents(from, animationTime);
      }

      this.events.clear();
      from.nextAnimationLast = animationTime;
      from.nextTrackLast = from.trackTime;
      return mix;
   }

   private void applyAttachmentTimeline(Animation.AttachmentTimeline timeline, Skeleton skeleton, float time, Animation.MixBlend blend, boolean attachments) {
      Slot slot = (Slot)skeleton.slots.get(timeline.slotIndex);
      if (slot.bone.active) {
         if (time < timeline.frames[0]) {
            if (blend == Animation.MixBlend.setup || blend == Animation.MixBlend.first) {
               this.setAttachment(skeleton, slot, slot.data.attachmentName, attachments);
            }
         } else {
            this.setAttachment(skeleton, slot, timeline.attachmentNames[Animation.Timeline.search(timeline.frames, time)], attachments);
         }

         if (slot.attachmentState <= this.unkeyedState) {
            slot.attachmentState = this.unkeyedState + 1;
         }
      }
   }

   private void setAttachment(Skeleton skeleton, Slot slot, String attachmentName, boolean attachments) {
      slot.setAttachment(attachmentName == null ? null : skeleton.getAttachment(slot.data.index, attachmentName));
      if (attachments) {
         slot.attachmentState = this.unkeyedState + 2;
      }
   }

   private void applyRotateTimeline(
      Animation.RotateTimeline timeline,
      Skeleton skeleton,
      float time,
      float alpha,
      Animation.MixBlend blend,
      float[] timelinesRotation,
      int i,
      boolean firstFrame
   ) {
      if (firstFrame) {
         timelinesRotation[i] = 0.0F;
      }

      if (alpha == 1.0F) {
         timeline.apply(skeleton, 0.0F, time, null, 1.0F, blend, Animation.MixDirection.in);
      } else {
         Bone bone = (Bone)skeleton.bones.get(timeline.boneIndex);
         if (bone.active) {
            float[] frames = timeline.frames;
            float r1 = 0.0F;
            float r2 = 0.0F;
            if (time < frames[0]) {
               switch (blend) {
                  case setup:
                     bone.rotation = bone.data.rotation;
                  default:
                     return;
                  case first:
                     r1 = bone.rotation;
                     r2 = bone.data.rotation;
               }
            } else {
               r1 = blend == Animation.MixBlend.setup ? bone.data.rotation : bone.rotation;
               r2 = bone.data.rotation + timeline.getCurveValue(time);
            }

            float diff = r2 - r1;
            diff -= (16384 - (int)(16384.499999999996 - diff / 360.0F)) * 360;
            float total;
            if (diff == 0.0F) {
               total = timelinesRotation[i];
            } else {
               float lastTotal;
               float lastDiff;
               if (firstFrame) {
                  lastTotal = 0.0F;
                  lastDiff = diff;
               } else {
                  lastTotal = timelinesRotation[i];
                  lastDiff = timelinesRotation[i + 1];
               }

               boolean current = diff > 0.0F;
               boolean dir = lastTotal >= 0.0F;
               if (Math.signum(lastDiff) != Math.signum(diff) && Math.abs(lastDiff) <= 90.0F) {
                  if (Math.abs(lastTotal) > 180.0F) {
                     lastTotal += 360.0F * Math.signum(lastTotal);
                  }

                  dir = current;
               }

               total = diff + lastTotal - lastTotal % 360.0F;
               if (dir != current) {
                  total += 360.0F * Math.signum(lastTotal);
               }

               timelinesRotation[i] = total;
            }

            timelinesRotation[i + 1] = diff;
            bone.rotation = r1 + total * alpha;
         }
      }
   }

   private void queueEvents(AnimationState.TrackEntry entry, float animationTime) {
      float animationStart = entry.animationStart;
      float animationEnd = entry.animationEnd;
      float duration = animationEnd - animationStart;
      float trackLastWrapped = entry.trackLast % duration;
      Object[] events = this.events.items;
      int i = 0;

      int n;
      for (n = this.events.size; i < n; i++) {
         Event event = (Event)events[i];
         if (event.time < trackLastWrapped) {
            break;
         }

         if (event.time <= animationEnd) {
            this.queue.event(entry, event);
         }
      }

      boolean complete;
      if (entry.loop) {
         complete = duration == 0.0F || trackLastWrapped > entry.trackTime % duration;
      } else {
         complete = animationTime >= animationEnd && entry.animationLast < animationEnd;
      }

      if (complete) {
         this.queue.complete(entry);
      }

      for (; i < n; i++) {
         Event event2 = (Event)events[i];
         if (event2.time >= animationStart) {
            this.queue.event(entry, event2);
         }
      }
   }

   public void clearTracks() {
      boolean oldDrainDisabled = this.queue.drainDisabled;
      this.queue.drainDisabled = true;
      int i = 0;

      for (int n = this.tracks.size; i < n; i++) {
         this.clearTrack(i);
      }

      this.tracks.clear();
      this.queue.drainDisabled = oldDrainDisabled;
      this.queue.drain();
   }

   public void clearTrack(int trackIndex) {
      if (trackIndex < 0) {
         throw new IllegalArgumentException("trackIndex must be >= 0.");
      } else if (trackIndex < this.tracks.size) {
         AnimationState.TrackEntry current = (AnimationState.TrackEntry)this.tracks.get(trackIndex);
         if (current != null) {
            this.queue.end(current);
            this.clearNext(current);
            AnimationState.TrackEntry entry = current;

            while (true) {
               AnimationState.TrackEntry from = entry.mixingFrom;
               if (from == null) {
                  this.tracks.set(current.trackIndex, null);
                  this.queue.drain();
                  return;
               }

               this.queue.end(from);
               entry.mixingFrom = null;
               entry.mixingTo = null;
               entry = from;
            }
         }
      }
   }

   private void setCurrent(int index, AnimationState.TrackEntry current, boolean interrupt) {
      AnimationState.TrackEntry from = this.expandToIndex(index);
      this.tracks.set(index, current);
      current.previous = null;
      if (from != null) {
         if (interrupt) {
            this.queue.interrupt(from);
         }

         current.mixingFrom = from;
         from.mixingTo = current;
         current.mixTime = 0.0F;
         if (from.mixingFrom != null && from.mixDuration > 0.0F) {
            current.interruptAlpha = current.interruptAlpha * Math.min(1.0F, from.mixTime / from.mixDuration);
         }

         from.timelinesRotation.clear();
      }

      this.queue.start(current);
   }

   public AnimationState.TrackEntry setAnimation(int trackIndex, String animationName, boolean loop) {
      Animation animation = this.data.skeletonData.findAnimation(animationName);
      if (animation == null) {
         throw new IllegalArgumentException("Animation not found: " + animationName);
      } else {
         return this.setAnimation(trackIndex, animation, loop);
      }
   }

   public AnimationState.TrackEntry setAnimation(int trackIndex, Animation animation, boolean loop) {
      if (trackIndex < 0) {
         throw new IllegalArgumentException("trackIndex must be >= 0.");
      } else if (animation == null) {
         throw new IllegalArgumentException("animation cannot be null.");
      } else {
         boolean interrupt = true;
         AnimationState.TrackEntry current = this.expandToIndex(trackIndex);
         if (current != null) {
            if (current.nextTrackLast == -1.0F) {
               this.tracks.set(trackIndex, current.mixingFrom);
               this.queue.interrupt(current);
               this.queue.end(current);
               this.clearNext(current);
               current = current.mixingFrom;
               interrupt = false;
            } else {
               this.clearNext(current);
            }
         }

         AnimationState.TrackEntry entry = this.trackEntry(trackIndex, animation, loop, current);
         this.setCurrent(trackIndex, entry, interrupt);
         this.queue.drain();
         return entry;
      }
   }

   public AnimationState.TrackEntry addAnimation(int trackIndex, String animationName, boolean loop, float delay) {
      Animation animation = this.data.skeletonData.findAnimation(animationName);
      if (animation == null) {
         throw new IllegalArgumentException("Animation not found: " + animationName);
      } else {
         return this.addAnimation(trackIndex, animation, loop, delay);
      }
   }

   public AnimationState.TrackEntry addAnimation(int trackIndex, Animation animation, boolean loop, float delay) {
      if (trackIndex < 0) {
         throw new IllegalArgumentException("trackIndex must be >= 0.");
      } else if (animation == null) {
         throw new IllegalArgumentException("animation cannot be null.");
      } else {
         AnimationState.TrackEntry last = this.expandToIndex(trackIndex);
         if (last != null) {
            while (last.next != null) {
               last = last.next;
            }
         }

         AnimationState.TrackEntry entry = this.trackEntry(trackIndex, animation, loop, last);
         if (last == null) {
            this.setCurrent(trackIndex, entry, true);
            this.queue.drain();
         } else {
            last.next = entry;
            entry.previous = last;
            if (delay <= 0.0F) {
               delay += last.getTrackComplete() - entry.mixDuration;
            }
         }

         entry.delay = delay;
         return entry;
      }
   }

   public AnimationState.TrackEntry setEmptyAnimation(int trackIndex, float mixDuration) {
      AnimationState.TrackEntry entry = this.setAnimation(trackIndex, emptyAnimation, false);
      entry.mixDuration = mixDuration;
      entry.trackEnd = mixDuration;
      return entry;
   }

   public AnimationState.TrackEntry addEmptyAnimation(int trackIndex, float mixDuration, float delay) {
      AnimationState.TrackEntry entry = this.addAnimation(trackIndex, emptyAnimation, false, delay);
      if (delay <= 0.0F) {
         entry.delay = entry.delay + (entry.mixDuration - mixDuration);
      }

      entry.mixDuration = mixDuration;
      entry.trackEnd = mixDuration;
      return entry;
   }

   public void setEmptyAnimations(float mixDuration) {
      boolean oldDrainDisabled = this.queue.drainDisabled;
      this.queue.drainDisabled = true;
      Object[] tracks = this.tracks.items;
      int i = 0;

      for (int n = this.tracks.size; i < n; i++) {
         AnimationState.TrackEntry current = (AnimationState.TrackEntry)tracks[i];
         if (current != null) {
            this.setEmptyAnimation(current.trackIndex, mixDuration);
         }
      }

      this.queue.drainDisabled = oldDrainDisabled;
      this.queue.drain();
   }

   private AnimationState.TrackEntry expandToIndex(int index) {
      if (index < this.tracks.size) {
         return (AnimationState.TrackEntry)this.tracks.get(index);
      } else {
         this.tracks.ensureCapacity(index - this.tracks.size + 1);
         this.tracks.size = index + 1;
         return null;
      }
   }

   private AnimationState.TrackEntry trackEntry(int trackIndex, Animation animation, boolean loop, @Null AnimationState.TrackEntry last) {
      AnimationState.TrackEntry entry = (AnimationState.TrackEntry)this.trackEntryPool.obtain();
      entry.trackIndex = trackIndex;
      entry.animation = animation;
      entry.loop = loop;
      entry.holdPrevious = false;
      entry.eventThreshold = 0.0F;
      entry.attachmentThreshold = 0.0F;
      entry.drawOrderThreshold = 0.0F;
      entry.animationStart = 0.0F;
      entry.animationEnd = animation.getDuration();
      entry.animationLast = -1.0F;
      entry.nextAnimationLast = -1.0F;
      entry.delay = 0.0F;
      entry.trackTime = 0.0F;
      entry.trackLast = -1.0F;
      entry.nextTrackLast = -1.0F;
      entry.trackEnd = Float.MAX_VALUE;
      entry.timeScale = 1.0F;
      entry.alpha = 1.0F;
      entry.interruptAlpha = 1.0F;
      entry.mixTime = 0.0F;
      entry.mixDuration = last == null ? 0.0F : this.data.getMix(last.animation, animation);
      entry.mixBlend = Animation.MixBlend.replace;
      return entry;
   }

   public void clearNext(AnimationState.TrackEntry entry) {
      for (AnimationState.TrackEntry next = entry.next; next != null; next = next.next) {
         this.queue.dispose(next);
      }

      entry.next = null;
   }

   void animationsChanged() {
      this.animationsChanged = false;
      this.propertyIds.clear(2048);
      int n = this.tracks.size;
      Object[] tracks = this.tracks.items;

      for (int i = 0; i < n; i++) {
         AnimationState.TrackEntry entry = (AnimationState.TrackEntry)tracks[i];
         if (entry != null) {
            while (entry.mixingFrom != null) {
               entry = entry.mixingFrom;
            }

            while (true) {
               if (entry.mixingTo == null || entry.mixBlend != Animation.MixBlend.add) {
                  this.computeHold(entry);
               }

               entry = entry.mixingTo;
               if (entry == null) {
                  break;
               }
            }
         }
      }
   }

   private void computeHold(AnimationState.TrackEntry entry) {
      AnimationState.TrackEntry to = entry.mixingTo;
      Object[] timelines = entry.animation.timelines.items;
      int timelinesCount = entry.animation.timelines.size;
      int[] timelineMode = entry.timelineMode.setSize(timelinesCount);
      entry.timelineHoldMix.clear();
      Object[] timelineHoldMix = entry.timelineHoldMix.setSize(timelinesCount);
      ObjectSet propertyIds = this.propertyIds;
      if (to != null && to.holdPrevious) {
         for (int i = 0; i < timelinesCount; i++) {
            timelineMode[i] = propertyIds.addAll(((Animation.Timeline)timelines[i]).getPropertyIds()) ? 3 : 2;
         }
      } else {
         label62:
         for (int i = 0; i < timelinesCount; i++) {
            Animation.Timeline timeline = (Animation.Timeline)timelines[i];
            String[] ids = timeline.getPropertyIds();
            if (!propertyIds.addAll(ids)) {
               timelineMode[i] = 0;
            } else if (to != null
               && !(timeline instanceof Animation.AttachmentTimeline)
               && !(timeline instanceof Animation.DrawOrderTimeline)
               && !(timeline instanceof Animation.EventTimeline)
               && to.animation.hasTimeline(ids)) {
               for (AnimationState.TrackEntry next = to.mixingTo; next != null; next = next.mixingTo) {
                  if (!next.animation.hasTimeline(ids)) {
                     if (next.mixDuration > 0.0F) {
                        timelineMode[i] = 4;
                        timelineHoldMix[i] = next;
                        continue label62;
                     }
                     break;
                  }
               }

               timelineMode[i] = 3;
            } else {
               timelineMode[i] = 1;
            }
         }
      }
   }

   @Null
   public AnimationState.TrackEntry getCurrent(int trackIndex) {
      if (trackIndex < 0) {
         throw new IllegalArgumentException("trackIndex must be >= 0.");
      } else {
         return trackIndex >= this.tracks.size ? null : (AnimationState.TrackEntry)this.tracks.get(trackIndex);
      }
   }

   public void addListener(AnimationState.AnimationStateListener listener) {
      if (listener == null) {
         throw new IllegalArgumentException("listener cannot be null.");
      } else {
         this.listeners.add(listener);
      }
   }

   public void removeListener(AnimationState.AnimationStateListener listener) {
      this.listeners.removeValue(listener, true);
   }

   public void clearListeners() {
      this.listeners.clear();
   }

   public void clearListenerNotifications() {
      this.queue.clear();
   }

   public float getTimeScale() {
      return this.timeScale;
   }

   public void setTimeScale(float timeScale) {
      this.timeScale = timeScale;
   }

   public AnimationStateData getData() {
      return this.data;
   }

   public void setData(AnimationStateData data) {
      if (data == null) {
         throw new IllegalArgumentException("data cannot be null.");
      } else {
         this.data = data;
      }
   }

   public Array getTracks() {
      return this.tracks;
   }

   @Override
   public String toString() {
      StringBuilder buffer = new StringBuilder(64);
      Object[] tracks = this.tracks.items;
      int i = 0;

      for (int n = this.tracks.size; i < n; i++) {
         AnimationState.TrackEntry entry = (AnimationState.TrackEntry)tracks[i];
         if (entry != null) {
            if (buffer.length() > 0) {
               buffer.append(", ");
            }

            buffer.append(entry.toString());
         }
      }

      return buffer.length() == 0 ? "<none>" : buffer.toString();
   }

   static int[] $SWITCH_TABLE$com$esotericsoftware$spine$Animation$MixBlend() {
      int[] $switch_TABLE$com$esotericsoftware$spine$Animation$MixBlend = $SWITCH_TABLE$com$esotericsoftware$spine$Animation$MixBlend;
      if ($switch_TABLE$com$esotericsoftware$spine$Animation$MixBlend != null) {
         return $switch_TABLE$com$esotericsoftware$spine$Animation$MixBlend;
      } else {
         int[] $switch_TABLE$com$esotericsoftware$spine$Animation$MixBlend2 = new int[Animation.MixBlend.values().length];

         try {
            $switch_TABLE$com$esotericsoftware$spine$Animation$MixBlend2[Animation.MixBlend.add.ordinal()] = 4;
         } catch (NoSuchFieldError var6) {
         }

         try {
            $switch_TABLE$com$esotericsoftware$spine$Animation$MixBlend2[Animation.MixBlend.first.ordinal()] = 2;
         } catch (NoSuchFieldError var5) {
         }

         try {
            $switch_TABLE$com$esotericsoftware$spine$Animation$MixBlend2[Animation.MixBlend.replace.ordinal()] = 3;
         } catch (NoSuchFieldError var4) {
         }

         try {
            $switch_TABLE$com$esotericsoftware$spine$Animation$MixBlend2[Animation.MixBlend.setup.ordinal()] = 1;
         } catch (NoSuchFieldError var3) {
         }

         $SWITCH_TABLE$com$esotericsoftware$spine$Animation$MixBlend = $switch_TABLE$com$esotericsoftware$spine$Animation$MixBlend2;
         return $switch_TABLE$com$esotericsoftware$spine$Animation$MixBlend2;
      }
   }

   public abstract static class AnimationStateAdapter implements AnimationState.AnimationStateListener {
      @Override
      public void start(AnimationState.TrackEntry entry) {
      }

      @Override
      public void interrupt(AnimationState.TrackEntry entry) {
      }

      @Override
      public void end(AnimationState.TrackEntry entry) {
      }

      @Override
      public void dispose(AnimationState.TrackEntry entry) {
      }

      @Override
      public void complete(AnimationState.TrackEntry entry) {
      }

      @Override
      public void event(AnimationState.TrackEntry entry, Event event) {
      }
   }

   public interface AnimationStateListener {
      void start(AnimationState.TrackEntry var1);

      void interrupt(AnimationState.TrackEntry var1);

      void end(AnimationState.TrackEntry var1);

      void dispose(AnimationState.TrackEntry var1);

      void complete(AnimationState.TrackEntry var1);

      void event(AnimationState.TrackEntry var1, Event var2);
   }

   class EventQueue {
      private final Array objects = new Array();
      boolean drainDisabled;
      private volatile int[] $SWITCH_TABLE$com$esotericsoftware$spine$AnimationState$EventType;

      void start(AnimationState.TrackEntry entry) {
         this.objects.add(AnimationState.EventType.start);
         this.objects.add(entry);
         AnimationState.this.animationsChanged = true;
      }

      void interrupt(AnimationState.TrackEntry entry) {
         this.objects.add(AnimationState.EventType.interrupt);
         this.objects.add(entry);
      }

      void end(AnimationState.TrackEntry entry) {
         this.objects.add(AnimationState.EventType.end);
         this.objects.add(entry);
         AnimationState.this.animationsChanged = true;
      }

      void dispose(AnimationState.TrackEntry entry) {
         this.objects.add(AnimationState.EventType.dispose);
         this.objects.add(entry);
      }

      void complete(AnimationState.TrackEntry entry) {
         this.objects.add(AnimationState.EventType.complete);
         this.objects.add(entry);
      }

      void event(AnimationState.TrackEntry entry, Event event) {
         this.objects.add(AnimationState.EventType.event);
         this.objects.add(entry);
         this.objects.add(event);
      }

      void drain() {
         if (!this.drainDisabled) {
            this.drainDisabled = true;
            SnapshotArray listenersArray = AnimationState.this.listeners;

            for (int i = 0; i < this.objects.size; i += 2) {
               AnimationState.EventType type = (AnimationState.EventType)this.objects.get(i);
               AnimationState.TrackEntry entry = (AnimationState.TrackEntry)this.objects.get(i + 1);
               int listenersCount = listenersArray.size;
               Object[] listeners = listenersArray.begin();
               switch (type) {
                  case start:
                     if (entry.listener != null) {
                        entry.listener.start(entry);
                     }

                     for (int ii = 0; ii < listenersCount; ii++) {
                        ((AnimationState.AnimationStateListener)listeners[ii]).start(entry);
                     }
                     break;
                  case interrupt:
                     if (entry.listener != null) {
                        entry.listener.interrupt(entry);
                     }

                     for (int ii = 0; ii < listenersCount; ii++) {
                        ((AnimationState.AnimationStateListener)listeners[ii]).interrupt(entry);
                     }
                     break;
                  case end:
                     if (entry.listener != null) {
                        entry.listener.end(entry);
                     }

                     for (int ii = 0; ii < listenersCount; ii++) {
                        ((AnimationState.AnimationStateListener)listeners[ii]).end(entry);
                     }
                  case dispose:
                     if (entry.listener != null) {
                        entry.listener.dispose(entry);
                     }

                     for (int ii = 0; ii < listenersCount; ii++) {
                        ((AnimationState.AnimationStateListener)listeners[ii]).dispose(entry);
                     }

                     AnimationState.this.trackEntryPool.free(entry);
                     break;
                  case complete:
                     if (entry.listener != null) {
                        entry.listener.complete(entry);
                     }

                     for (int ii = 0; ii < listenersCount; ii++) {
                        ((AnimationState.AnimationStateListener)listeners[ii]).complete(entry);
                     }
                     break;
                  case event:
                     Event event = (Event)this.objects.get(i++ + 2);
                     if (entry.listener != null) {
                        entry.listener.event(entry, event);
                     }

                     for (int ii2 = 0; ii2 < listenersCount; ii2++) {
                        ((AnimationState.AnimationStateListener)listeners[ii2]).event(entry, event);
                     }
               }

               listenersArray.end();
            }

            this.clear();
            this.drainDisabled = false;
         }
      }

      void clear() {
         this.objects.clear();
      }
   }

   private static enum EventType {
      start("start", 0),
      interrupt("interrupt", 1),
      end("end", 2),
      dispose("dispose", 3),
      complete("complete", 4),
      event("event", 5);

      private static final AnimationState.EventType[] ENUM$VALUES = new AnimationState.EventType[]{start, interrupt, end, dispose, complete, event};

      private EventType(String name, int ordinal) {
      }
   }

   public static class TrackEntry implements Pool.Poolable {
      Animation animation;
      @Null
      AnimationState.TrackEntry previous;
      @Null
      AnimationState.TrackEntry next;
      @Null
      AnimationState.TrackEntry mixingFrom;
      @Null
      AnimationState.TrackEntry mixingTo;
      @Null
      AnimationState.AnimationStateListener listener;
      int trackIndex;
      boolean loop;
      boolean holdPrevious;
      boolean reverse;
      float eventThreshold;
      float attachmentThreshold;
      float drawOrderThreshold;
      float animationStart;
      float animationEnd;
      float animationLast;
      float nextAnimationLast;
      float delay;
      float trackTime;
      float trackLast;
      float nextTrackLast;
      float trackEnd;
      float timeScale;
      float alpha;
      float mixTime;
      float mixDuration;
      float interruptAlpha;
      float totalAlpha;
      Animation.MixBlend mixBlend = Animation.MixBlend.replace;
      final IntArray timelineMode = new IntArray();
      final Array timelineHoldMix = new Array();
      final FloatArray timelinesRotation = new FloatArray();

      @Override
      public void reset() {
         this.previous = null;
         this.next = null;
         this.mixingFrom = null;
         this.mixingTo = null;
         this.animation = null;
         this.listener = null;
         this.timelineMode.clear();
         this.timelineHoldMix.clear();
         this.timelinesRotation.clear();
      }

      public int getTrackIndex() {
         return this.trackIndex;
      }

      public Animation getAnimation() {
         return this.animation;
      }

      public void setAnimation(Animation animation) {
         if (animation == null) {
            throw new IllegalArgumentException("animation cannot be null.");
         } else {
            this.animation = animation;
         }
      }

      public boolean getLoop() {
         return this.loop;
      }

      public void setLoop(boolean loop) {
         this.loop = loop;
      }

      public float getDelay() {
         return this.delay;
      }

      public void setDelay(float delay) {
         this.delay = delay;
      }

      public float getTrackTime() {
         return this.trackTime;
      }

      public void setTrackTime(float trackTime) {
         this.trackTime = trackTime;
      }

      public float getTrackEnd() {
         return this.trackEnd;
      }

      public void setTrackEnd(float trackEnd) {
         this.trackEnd = trackEnd;
      }

      public float getTrackComplete() {
         float duration = this.animationEnd - this.animationStart;
         if (duration != 0.0F) {
            if (this.loop) {
               return duration * (1 + (int)(this.trackTime / duration));
            }

            if (this.trackTime < duration) {
               return duration;
            }
         }

         return this.trackTime;
      }

      public float getAnimationStart() {
         return this.animationStart;
      }

      public void setAnimationStart(float animationStart) {
         this.animationStart = animationStart;
      }

      public float getAnimationEnd() {
         return this.animationEnd;
      }

      public void setAnimationEnd(float animationEnd) {
         this.animationEnd = animationEnd;
      }

      public float getAnimationLast() {
         return this.animationLast;
      }

      public void setAnimationLast(float animationLast) {
         this.animationLast = animationLast;
         this.nextAnimationLast = animationLast;
      }

      public float getAnimationTime() {
         if (!this.loop) {
            float animationTime = this.trackTime + this.animationStart;
            return this.animationEnd >= this.animation.duration ? animationTime : Math.min(animationTime, this.animationEnd);
         } else {
            float duration = this.animationEnd - this.animationStart;
            return duration == 0.0F ? this.animationStart : this.trackTime % duration + this.animationStart;
         }
      }

      public float getTimeScale() {
         return this.timeScale;
      }

      public void setTimeScale(float timeScale) {
         this.timeScale = timeScale;
      }

      @Null
      public AnimationState.AnimationStateListener getListener() {
         return this.listener;
      }

      public void setListener(@Null AnimationState.AnimationStateListener listener) {
         this.listener = listener;
      }

      public float getAlpha() {
         return this.alpha;
      }

      public void setAlpha(float alpha) {
         this.alpha = alpha;
      }

      public float getEventThreshold() {
         return this.eventThreshold;
      }

      public void setEventThreshold(float eventThreshold) {
         this.eventThreshold = eventThreshold;
      }

      public float getAttachmentThreshold() {
         return this.attachmentThreshold;
      }

      public void setAttachmentThreshold(float attachmentThreshold) {
         this.attachmentThreshold = attachmentThreshold;
      }

      public float getDrawOrderThreshold() {
         return this.drawOrderThreshold;
      }

      public void setDrawOrderThreshold(float drawOrderThreshold) {
         this.drawOrderThreshold = drawOrderThreshold;
      }

      @Null
      public AnimationState.TrackEntry getNext() {
         return this.next;
      }

      @Null
      public AnimationState.TrackEntry getPrevious() {
         return this.previous;
      }

      public boolean isComplete() {
         return this.trackTime >= this.animationEnd - this.animationStart;
      }

      public float getMixTime() {
         return this.mixTime;
      }

      public void setMixTime(float mixTime) {
         this.mixTime = mixTime;
      }

      public float getMixDuration() {
         return this.mixDuration;
      }

      public void setMixDuration(float mixDuration) {
         this.mixDuration = mixDuration;
      }

      public Animation.MixBlend getMixBlend() {
         return this.mixBlend;
      }

      public void setMixBlend(Animation.MixBlend mixBlend) {
         if (mixBlend == null) {
            throw new IllegalArgumentException("mixBlend cannot be null.");
         } else {
            this.mixBlend = mixBlend;
         }
      }

      @Null
      public AnimationState.TrackEntry getMixingFrom() {
         return this.mixingFrom;
      }

      @Null
      public AnimationState.TrackEntry getMixingTo() {
         return this.mixingTo;
      }

      public void setHoldPrevious(boolean holdPrevious) {
         this.holdPrevious = holdPrevious;
      }

      public boolean getHoldPrevious() {
         return this.holdPrevious;
      }

      public void resetRotationDirections() {
         this.timelinesRotation.clear();
      }

      public void setReverse(boolean reverse) {
         this.reverse = reverse;
      }

      public boolean getReverse() {
         return this.reverse;
      }

      public boolean isEmptyAnimation() {
         return this.animation == AnimationState.emptyAnimation;
      }

      @Override
      public String toString() {
         return this.animation == null ? "<none>" : this.animation.name;
      }
   }
}
