package com.esotericsoftware.spine;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.SnapshotArray;

public class AnimationState {
    public static abstract class AnimationStateAdapter implements AnimationStateListener {
        @Override  // com.esotericsoftware.spine.AnimationState$AnimationStateListener
        public void complete(TrackEntry animationState$TrackEntry0) {
        }

        @Override  // com.esotericsoftware.spine.AnimationState$AnimationStateListener
        public void dispose(TrackEntry animationState$TrackEntry0) {
        }

        @Override  // com.esotericsoftware.spine.AnimationState$AnimationStateListener
        public void end(TrackEntry animationState$TrackEntry0) {
        }

        @Override  // com.esotericsoftware.spine.AnimationState$AnimationStateListener
        public void event(TrackEntry animationState$TrackEntry0, Event event0) {
        }

        @Override  // com.esotericsoftware.spine.AnimationState$AnimationStateListener
        public void interrupt(TrackEntry animationState$TrackEntry0) {
        }

        @Override  // com.esotericsoftware.spine.AnimationState$AnimationStateListener
        public void start(TrackEntry animationState$TrackEntry0) {
        }
    }

    public interface AnimationStateListener {
        void complete(TrackEntry arg1);

        void dispose(TrackEntry arg1);

        void end(TrackEntry arg1);

        void event(TrackEntry arg1, Event arg2);

        void interrupt(TrackEntry arg1);

        void start(TrackEntry arg1);
    }

    class EventQueue {
        boolean drainDisabled;
        private final Array objects;

        EventQueue() {
            this.objects = new Array();
        }

        void clear() {
            this.objects.clear();
        }

        void complete(TrackEntry animationState$TrackEntry0) {
            this.objects.add(EventType.complete);
            this.objects.add(animationState$TrackEntry0);
        }

        void dispose(TrackEntry animationState$TrackEntry0) {
            this.objects.add(EventType.dispose);
            this.objects.add(animationState$TrackEntry0);
        }

        void drain() {
            if(this.drainDisabled) {
                return;
            }
            this.drainDisabled = true;
            SnapshotArray snapshotArray0 = AnimationState.this.listeners;
            int v = 0;
            while(v < this.objects.size) {
                EventType animationState$EventType0 = (EventType)this.objects.get(v);
                TrackEntry animationState$TrackEntry0 = (TrackEntry)this.objects.get(v + 1);
                int v1 = snapshotArray0.size;
                Object[] arr_object = snapshotArray0.begin();
                switch(animationState$EventType0) {
                    case start: {
                        if(animationState$TrackEntry0.listener != null) {
                            animationState$TrackEntry0.listener.start(animationState$TrackEntry0);
                        }
                        for(int v2 = 0; v2 < v1; ++v2) {
                            ((AnimationStateListener)arr_object[v2]).start(animationState$TrackEntry0);
                        }
                        break;
                    }
                    case interrupt: {
                        if(animationState$TrackEntry0.listener != null) {
                            animationState$TrackEntry0.listener.interrupt(animationState$TrackEntry0);
                        }
                        for(int v3 = 0; v3 < v1; ++v3) {
                            ((AnimationStateListener)arr_object[v3]).interrupt(animationState$TrackEntry0);
                        }
                        break;
                    }
                    case end: {
                        if(animationState$TrackEntry0.listener != null) {
                            animationState$TrackEntry0.listener.end(animationState$TrackEntry0);
                        }
                        int v4 = 0;
                        while(true) {
                            if(v4 >= v1) {
                                goto label_35;
                            }
                            ((AnimationStateListener)arr_object[v4]).end(animationState$TrackEntry0);
                            ++v4;
                        }
                    }
                    case dispose: {
                    label_35:
                        if(animationState$TrackEntry0.listener != null) {
                            animationState$TrackEntry0.listener.dispose(animationState$TrackEntry0);
                        }
                        for(int v5 = 0; v5 < v1; ++v5) {
                            ((AnimationStateListener)arr_object[v5]).dispose(animationState$TrackEntry0);
                        }
                        AnimationState.this.trackEntryPool.free(animationState$TrackEntry0);
                        break;
                    }
                    case complete: {
                        if(animationState$TrackEntry0.listener != null) {
                            animationState$TrackEntry0.listener.complete(animationState$TrackEntry0);
                        }
                        for(int v6 = 0; v6 < v1; ++v6) {
                            ((AnimationStateListener)arr_object[v6]).complete(animationState$TrackEntry0);
                        }
                        break;
                    }
                    case event: {
                        Event event0 = (Event)this.objects.get(v + 2);
                        if(animationState$TrackEntry0.listener != null) {
                            animationState$TrackEntry0.listener.event(animationState$TrackEntry0, event0);
                        }
                        for(int v7 = 0; v7 < v1; ++v7) {
                            ((AnimationStateListener)arr_object[v7]).event(animationState$TrackEntry0, event0);
                        }
                        ++v;
                    }
                }
                snapshotArray0.end();
                v += 2;
            }
            this.clear();
            this.drainDisabled = false;
        }

        void end(TrackEntry animationState$TrackEntry0) {
            this.objects.add(EventType.end);
            this.objects.add(animationState$TrackEntry0);
            AnimationState.this.animationsChanged = true;
        }

        void event(TrackEntry animationState$TrackEntry0, Event event0) {
            this.objects.add(EventType.event);
            this.objects.add(animationState$TrackEntry0);
            this.objects.add(event0);
        }

        void interrupt(TrackEntry animationState$TrackEntry0) {
            this.objects.add(EventType.interrupt);
            this.objects.add(animationState$TrackEntry0);
        }

        void start(TrackEntry animationState$TrackEntry0) {
            this.objects.add(EventType.start);
            this.objects.add(animationState$TrackEntry0);
            AnimationState.this.animationsChanged = true;
        }
    }

    static enum EventType {
        start,
        interrupt,
        end,
        dispose,
        complete,
        event;

    }

    public static class TrackEntry implements Poolable {
        float alpha;
        Animation animation;
        float animationEnd;
        float animationLast;
        float animationStart;
        float attachmentThreshold;
        float delay;
        float drawOrderThreshold;
        float eventThreshold;
        boolean holdPrevious;
        float interruptAlpha;
        @Null
        AnimationStateListener listener;
        boolean loop;
        MixBlend mixBlend;
        float mixDuration;
        float mixTime;
        @Null
        TrackEntry mixingFrom;
        @Null
        TrackEntry mixingTo;
        @Null
        TrackEntry next;
        float nextAnimationLast;
        float nextTrackLast;
        @Null
        TrackEntry previous;
        boolean reverse;
        float timeScale;
        final Array timelineHoldMix;
        final IntArray timelineMode;
        final FloatArray timelinesRotation;
        float totalAlpha;
        float trackEnd;
        int trackIndex;
        float trackLast;
        float trackTime;

        public TrackEntry() {
            this.mixBlend = MixBlend.replace;
            this.timelineMode = new IntArray();
            this.timelineHoldMix = new Array();
            this.timelinesRotation = new FloatArray();
        }

        public float getAlpha() {
            return this.alpha;
        }

        public Animation getAnimation() {
            return this.animation;
        }

        public float getAnimationEnd() {
            return this.animationEnd;
        }

        public float getAnimationLast() {
            return this.animationLast;
        }

        public float getAnimationStart() {
            return this.animationStart;
        }

        public float getAnimationTime() {
            if(this.loop) {
                float f = this.animationEnd - this.animationStart;
                return f == 0.0f ? this.animationStart : this.trackTime % f + this.animationStart;
            }
            float f1 = this.trackTime + this.animationStart;
            return this.animationEnd >= this.animation.duration ? f1 : Math.min(f1, this.animationEnd);
        }

        public float getAttachmentThreshold() {
            return this.attachmentThreshold;
        }

        public float getDelay() {
            return this.delay;
        }

        public float getDrawOrderThreshold() {
            return this.drawOrderThreshold;
        }

        public float getEventThreshold() {
            return this.eventThreshold;
        }

        public boolean getHoldPrevious() {
            return this.holdPrevious;
        }

        @Null
        public AnimationStateListener getListener() {
            return this.listener;
        }

        public boolean getLoop() {
            return this.loop;
        }

        public MixBlend getMixBlend() {
            return this.mixBlend;
        }

        public float getMixDuration() {
            return this.mixDuration;
        }

        public float getMixTime() {
            return this.mixTime;
        }

        @Null
        public TrackEntry getMixingFrom() {
            return this.mixingFrom;
        }

        @Null
        public TrackEntry getMixingTo() {
            return this.mixingTo;
        }

        @Null
        public TrackEntry getNext() {
            return this.next;
        }

        @Null
        public TrackEntry getPrevious() {
            return this.previous;
        }

        public boolean getReverse() {
            return this.reverse;
        }

        public float getTimeScale() {
            return this.timeScale;
        }

        public float getTrackComplete() {
            float f = this.animationEnd - this.animationStart;
            if(f != 0.0f) {
                if(this.loop) {
                    return f * ((float)(((int)(this.trackTime / f)) + 1));
                }
                return this.trackTime < f ? f : this.trackTime;
            }
            return this.trackTime;
        }

        public float getTrackEnd() {
            return this.trackEnd;
        }

        public int getTrackIndex() {
            return this.trackIndex;
        }

        public float getTrackTime() {
            return this.trackTime;
        }

        public boolean isComplete() {
            return this.trackTime >= this.animationEnd - this.animationStart;
        }

        public boolean isEmptyAnimation() {
            return this.animation == AnimationState.emptyAnimation;
        }

        @Override  // com.badlogic.gdx.utils.Pool$Poolable
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

        public void resetRotationDirections() {
            this.timelinesRotation.clear();
        }

        public void setAlpha(float f) {
            this.alpha = f;
        }

        public void setAnimation(Animation animation0) {
            if(animation0 == null) {
                throw new IllegalArgumentException("animation cannot be null.");
            }
            this.animation = animation0;
        }

        public void setAnimationEnd(float f) {
            this.animationEnd = f;
        }

        public void setAnimationLast(float f) {
            this.animationLast = f;
            this.nextAnimationLast = f;
        }

        public void setAnimationStart(float f) {
            this.animationStart = f;
        }

        public void setAttachmentThreshold(float f) {
            this.attachmentThreshold = f;
        }

        public void setDelay(float f) {
            this.delay = f;
        }

        public void setDrawOrderThreshold(float f) {
            this.drawOrderThreshold = f;
        }

        public void setEventThreshold(float f) {
            this.eventThreshold = f;
        }

        public void setHoldPrevious(boolean z) {
            this.holdPrevious = z;
        }

        public void setListener(@Null AnimationStateListener animationState$AnimationStateListener0) {
            this.listener = animationState$AnimationStateListener0;
        }

        public void setLoop(boolean z) {
            this.loop = z;
        }

        public void setMixBlend(MixBlend animation$MixBlend0) {
            if(animation$MixBlend0 == null) {
                throw new IllegalArgumentException("mixBlend cannot be null.");
            }
            this.mixBlend = animation$MixBlend0;
        }

        public void setMixDuration(float f) {
            this.mixDuration = f;
        }

        public void setMixTime(float f) {
            this.mixTime = f;
        }

        public void setReverse(boolean z) {
            this.reverse = z;
        }

        public void setTimeScale(float f) {
            this.timeScale = f;
        }

        public void setTrackEnd(float f) {
            this.trackEnd = f;
        }

        public void setTrackTime(float f) {
            this.trackTime = f;
        }

        @Override
        public String toString() [...] // 潜在的解密器
    }

    private static final int CURRENT = 2;
    private static final int FIRST = 1;
    private static final int HOLD_FIRST = 3;
    private static final int HOLD_MIX = 4;
    private static final int HOLD_SUBSEQUENT = 2;
    private static final int SETUP = 1;
    private static final int SUBSEQUENT;
    boolean animationsChanged;
    private AnimationStateData data;
    static final Animation emptyAnimation;
    private final Array events;
    final SnapshotArray listeners;
    private final ObjectSet propertyIds;
    private final EventQueue queue;
    private float timeScale;
    final Pool trackEntryPool;
    final Array tracks;
    private int unkeyedState;

    static {
        AnimationState.emptyAnimation = new Animation("<empty>", new Array(0), 0.0f);
    }

    public AnimationState() {
        this.tracks = new Array();
        this.events = new Array();
        this.listeners = new SnapshotArray();
        this.queue = new EventQueue(this);
        this.propertyIds = new ObjectSet();
        this.timeScale = 1.0f;
        this.trackEntryPool = new Pool() {
            @Override  // com.badlogic.gdx.utils.Pool
            protected Object newObject() {
                return new TrackEntry();
            }
        };
    }

    public AnimationState(AnimationStateData animationStateData0) {
        this.tracks = new Array();
        this.events = new Array();
        this.listeners = new SnapshotArray();
        this.queue = new EventQueue(this);
        this.propertyIds = new ObjectSet();
        this.timeScale = 1.0f;
        this.trackEntryPool = new Pool() {
            @Override  // com.badlogic.gdx.utils.Pool
            protected Object newObject() {
                return new TrackEntry();
            }
        };
        if(animationStateData0 == null) {
            throw new IllegalArgumentException("data cannot be null.");
        }
        this.data = animationStateData0;
    }

    public TrackEntry addAnimation(int v, Animation animation0, boolean z, float f) {
        if(v < 0) {
            throw new IllegalArgumentException("trackIndex must be >= 0.");
        }
        if(animation0 == null) {
            throw new IllegalArgumentException("animation cannot be null.");
        }
        TrackEntry animationState$TrackEntry0 = this.expandToIndex(v);
        if(animationState$TrackEntry0 != null) {
            while(animationState$TrackEntry0.next != null) {
                animationState$TrackEntry0 = animationState$TrackEntry0.next;
            }
        }
        TrackEntry animationState$TrackEntry1 = this.trackEntry(v, animation0, z, animationState$TrackEntry0);
        if(animationState$TrackEntry0 == null) {
            this.setCurrent(v, animationState$TrackEntry1, true);
            this.queue.drain();
        }
        else {
            animationState$TrackEntry0.next = animationState$TrackEntry1;
            animationState$TrackEntry1.previous = animationState$TrackEntry0;
            if(f <= 0.0f) {
                f += animationState$TrackEntry0.getTrackComplete() - animationState$TrackEntry1.mixDuration;
            }
        }
        animationState$TrackEntry1.delay = f;
        return animationState$TrackEntry1;
    }

    public TrackEntry addAnimation(int v, String s, boolean z, float f) {
        Animation animation0 = this.data.skeletonData.findAnimation(s);
        if(animation0 == null) {
            throw new IllegalArgumentException("Animation not found: " + s);
        }
        return this.addAnimation(v, animation0, z, f);
    }

    public TrackEntry addEmptyAnimation(int v, float f, float f1) {
        TrackEntry animationState$TrackEntry0 = this.addAnimation(v, AnimationState.emptyAnimation, false, f1);
        if(f1 <= 0.0f) {
            animationState$TrackEntry0.delay += animationState$TrackEntry0.mixDuration - f;
        }
        animationState$TrackEntry0.mixDuration = f;
        animationState$TrackEntry0.trackEnd = f;
        return animationState$TrackEntry0;
    }

    public void addListener(AnimationStateListener animationState$AnimationStateListener0) {
        if(animationState$AnimationStateListener0 == null) {
            throw new IllegalArgumentException("listener cannot be null.");
        }
        this.listeners.add(animationState$AnimationStateListener0);
    }

    void animationsChanged() {
        this.animationsChanged = false;
        this.propertyIds.clear(0x800);
        int v1 = this.tracks.size;
        Object[] arr_object = this.tracks.items;
        for(int v = 0; v < v1; ++v) {
            TrackEntry animationState$TrackEntry0 = (TrackEntry)arr_object[v];
            if(animationState$TrackEntry0 != null) {
                while(animationState$TrackEntry0.mixingFrom != null) {
                    animationState$TrackEntry0 = animationState$TrackEntry0.mixingFrom;
                }
                while(true) {
                    if(animationState$TrackEntry0.mixingTo == null || animationState$TrackEntry0.mixBlend != MixBlend.add) {
                        this.computeHold(animationState$TrackEntry0);
                    }
                    TrackEntry animationState$TrackEntry1 = animationState$TrackEntry0.mixingTo;
                    if(animationState$TrackEntry1 == null) {
                        break;
                    }
                    animationState$TrackEntry0 = animationState$TrackEntry1;
                }
            }
        }
    }

    public boolean apply(Skeleton skeleton0) {
        TrackEntry animationState$TrackEntry1;
        int v6;
        float f7;
        Object[] arr_object4;
        MixBlend animation$MixBlend1;
        float f6;
        float f5;
        Object[] arr_object3;
        int v5;
        Object[] arr_object2;
        int v4;
        TrackEntry animationState$TrackEntry2;
        int v11;
        int[] arr_v1;
        float[] arr_f1;
        int v10;
        Array array1;
        float f4;
        float f1;
        if(skeleton0 == null) {
            throw new IllegalArgumentException("skeleton cannot be null.");
        }
        if(this.animationsChanged) {
            this.animationsChanged();
        }
        Array array0 = this.events;
        Object[] arr_object = this.tracks.items;
        int v = this.tracks.size;
        boolean z = false;
        int v1 = 0;
        while(v1 < v) {
            TrackEntry animationState$TrackEntry0 = (TrackEntry)arr_object[v1];
            if(animationState$TrackEntry0 == null || animationState$TrackEntry0.delay > 0.0f) {
                arr_object4 = arr_object;
                v6 = v;
            }
            else {
                MixBlend animation$MixBlend0 = v1 == 0 ? MixBlend.first : animationState$TrackEntry0.mixBlend;
                float f = animationState$TrackEntry0.alpha;
                if(animationState$TrackEntry0.mixingFrom == null) {
                    f1 = animationState$TrackEntry0.trackTime < animationState$TrackEntry0.trackEnd || animationState$TrackEntry0.next != null ? f : 0.0f;
                }
                else {
                    f1 = f * this.applyMixingFrom(animationState$TrackEntry0, skeleton0, animation$MixBlend0);
                }
                float f2 = animationState$TrackEntry0.animationLast;
                float f3 = animationState$TrackEntry0.getAnimationTime();
                if(animationState$TrackEntry0.reverse) {
                    f4 = animationState$TrackEntry0.animation.duration - f3;
                    array1 = null;
                }
                else {
                    f4 = f3;
                    array1 = array0;
                }
                int v2 = animationState$TrackEntry0.animation.timelines.size;
                Object[] arr_object1 = animationState$TrackEntry0.animation.timelines.items;
                if((v1 != 0 || f1 != 1.0f) && animation$MixBlend0 != MixBlend.add) {
                    Object[] arr_object5 = arr_object1;
                    int v7 = v2;
                    float f8 = f2;
                    MixBlend animation$MixBlend2 = animation$MixBlend0;
                    arr_object4 = arr_object;
                    f7 = f3;
                    int[] arr_v = animationState$TrackEntry0.timelineMode.items;
                    int v8 = v7 << 1;
                    boolean z1 = animationState$TrackEntry0.timelinesRotation.size != v8;
                    if(z1) {
                        animationState$TrackEntry0.timelinesRotation.setSize(v8);
                    }
                    float[] arr_f = animationState$TrackEntry0.timelinesRotation.items;
                    int v9 = 0;
                    while(v9 < v7) {
                        Timeline animation$Timeline0 = (Timeline)arr_object5[v9];
                        MixBlend animation$MixBlend3 = arr_v[v9] == 0 ? animation$MixBlend2 : MixBlend.setup;
                        if(animation$Timeline0 instanceof RotateTimeline) {
                            v10 = v9;
                            arr_f1 = arr_f;
                            arr_v1 = arr_v;
                            v11 = v;
                            animationState$TrackEntry2 = animationState$TrackEntry0;
                            this.applyRotateTimeline(((RotateTimeline)animation$Timeline0), skeleton0, f4, f1, animation$MixBlend3, arr_f, v9 << 1, z1);
                        }
                        else {
                            v10 = v9;
                            arr_f1 = arr_f;
                            arr_v1 = arr_v;
                            v11 = v;
                            animationState$TrackEntry2 = animationState$TrackEntry0;
                            if(animation$Timeline0 instanceof AttachmentTimeline) {
                                this.applyAttachmentTimeline(((AttachmentTimeline)animation$Timeline0), skeleton0, f4, animation$MixBlend2, true);
                            }
                            else {
                                animation$Timeline0.apply(skeleton0, f8, f4, array1, f1, animation$MixBlend3, MixDirection.in);
                            }
                        }
                        v9 = v10 + 1;
                        animationState$TrackEntry0 = animationState$TrackEntry2;
                        v = v11;
                        arr_f = arr_f1;
                        arr_v = arr_v1;
                    }
                }
                else {
                    int v3 = 0;
                    while(v3 < v2) {
                        Object object0 = arr_object1[v3];
                        if(object0 instanceof AttachmentTimeline) {
                            v4 = v3;
                            arr_object2 = arr_object1;
                            v5 = v2;
                            arr_object3 = arr_object;
                            f5 = f3;
                            this.applyAttachmentTimeline(((AttachmentTimeline)object0), skeleton0, f4, animation$MixBlend0, true);
                            f6 = f2;
                            animation$MixBlend1 = animation$MixBlend0;
                        }
                        else {
                            v4 = v3;
                            arr_object2 = arr_object1;
                            v5 = v2;
                            arr_object3 = arr_object;
                            f5 = f3;
                            f6 = f2;
                            animation$MixBlend1 = animation$MixBlend0;
                            ((Timeline)object0).apply(skeleton0, f2, f4, array1, f1, animation$MixBlend0, MixDirection.in);
                        }
                        v3 = v4 + 1;
                        f3 = f5;
                        v2 = v5;
                        arr_object1 = arr_object2;
                        arr_object = arr_object3;
                        f2 = f6;
                        animation$MixBlend0 = animation$MixBlend1;
                    }
                    arr_object4 = arr_object;
                    f7 = f3;
                }
                v6 = v;
                animationState$TrackEntry1 = animationState$TrackEntry0;
                this.queueEvents(animationState$TrackEntry1, f7);
                array0.clear();
                animationState$TrackEntry1.nextAnimationLast = f7;
                animationState$TrackEntry1.nextTrackLast = animationState$TrackEntry1.trackTime;
                z = true;
            }
            ++v1;
            arr_object = arr_object4;
            v = v6;
        }
        int v12 = this.unkeyedState + 1;
        Object[] arr_object6 = skeleton0.slots.items;
        int v13 = skeleton0.slots.size;
        for(int v14 = 0; v14 < v13; ++v14) {
            Slot slot0 = (Slot)arr_object6[v14];
            if(slot0.attachmentState == v12) {
                String s = slot0.data.attachmentName;
                slot0.setAttachment((s == null ? null : skeleton0.getAttachment(slot0.data.index, s)));
            }
        }
        this.unkeyedState += 2;
        this.queue.drain();
        return z;
    }

    private void applyAttachmentTimeline(AttachmentTimeline animation$AttachmentTimeline0, Skeleton skeleton0, float f, MixBlend animation$MixBlend0, boolean z) {
        Slot slot0 = (Slot)skeleton0.slots.get(animation$AttachmentTimeline0.slotIndex);
        if(!slot0.bone.active) {
            return;
        }
        if(f >= animation$AttachmentTimeline0.frames[0]) {
            this.setAttachment(skeleton0, slot0, animation$AttachmentTimeline0.attachmentNames[Timeline.search(animation$AttachmentTimeline0.frames, f)], z);
        }
        else if(animation$MixBlend0 == MixBlend.setup || animation$MixBlend0 == MixBlend.first) {
            this.setAttachment(skeleton0, slot0, slot0.data.attachmentName, z);
        }
        int v = this.unkeyedState;
        if(slot0.attachmentState <= v) {
            slot0.attachmentState = v + 1;
        }
    }

    private float applyMixingFrom(TrackEntry animationState$TrackEntry0, Skeleton skeleton0, MixBlend animation$MixBlend0) {
        float f12;
        MixDirection animation$MixDirection2;
        MixBlend animation$MixBlend4;
        MixBlend animation$MixBlend2;
        float f9;
        MixDirection animation$MixDirection1;
        float f8;
        int[] arr_v1;
        Object[] arr_object3;
        float[] arr_f1;
        int v4;
        MixBlend animation$MixBlend3;
        int v3;
        Object[] arr_object2;
        float f11;
        float f10;
        float f7;
        float f6;
        Array array1;
        float f;
        MixBlend animation$MixBlend1;
        TrackEntry animationState$TrackEntry1 = animationState$TrackEntry0.mixingFrom;
        if(animationState$TrackEntry1.mixingFrom != null) {
            this.applyMixingFrom(animationState$TrackEntry1, skeleton0, animation$MixBlend0);
        }
        if(animationState$TrackEntry0.mixDuration == 0.0f) {
            if(animation$MixBlend0 == MixBlend.first) {
                animation$MixBlend0 = MixBlend.setup;
            }
            animation$MixBlend1 = animation$MixBlend0;
            f = 1.0f;
        }
        else {
            float f1 = animationState$TrackEntry0.mixTime / animationState$TrackEntry0.mixDuration;
            if(f1 > 1.0f) {
                f1 = 1.0f;
            }
            if(animation$MixBlend0 != MixBlend.first) {
                animation$MixBlend0 = animationState$TrackEntry1.mixBlend;
            }
            animation$MixBlend1 = animation$MixBlend0;
            f = f1;
        }
        boolean z = f < animationState$TrackEntry1.attachmentThreshold;
        boolean z1 = f < animationState$TrackEntry1.drawOrderThreshold;
        int v1 = animationState$TrackEntry1.animation.timelines.size;
        Object[] arr_object = animationState$TrackEntry1.animation.timelines.items;
        float f2 = animationState$TrackEntry1.alpha * animationState$TrackEntry0.interruptAlpha;
        float f3 = f2 * (1.0f - f);
        float f4 = animationState$TrackEntry1.animationLast;
        float f5 = animationState$TrackEntry1.getAnimationTime();
        Array array0 = null;
        if(animationState$TrackEntry1.reverse) {
            array1 = null;
            f6 = animationState$TrackEntry1.animation.duration - f5;
        }
        else {
            if(f < animationState$TrackEntry1.eventThreshold) {
                array0 = this.events;
            }
            f6 = f5;
            array1 = array0;
        }
        if(animation$MixBlend1 == MixBlend.add) {
            for(int v = 0; v < v1; ++v) {
                ((Timeline)arr_object[v]).apply(skeleton0, f4, f6, array1, f3, animation$MixBlend1, MixDirection.out);
            }
        }
        else {
            int[] arr_v = animationState$TrackEntry1.timelineMode.items;
            Object[] arr_object1 = animationState$TrackEntry1.timelineHoldMix.items;
            boolean z2 = animationState$TrackEntry1.timelinesRotation.size != v1 << 1;
            if(z2) {
                animationState$TrackEntry1.timelinesRotation.setSize(v1 << 1);
            }
            float[] arr_f = animationState$TrackEntry1.timelinesRotation.items;
            animationState$TrackEntry1.totalAlpha = 0.0f;
            int v2 = 0;
            while(v2 < v1) {
                Timeline animation$Timeline0 = (Timeline)arr_object[v2];
                MixDirection animation$MixDirection0 = MixDirection.out;
                switch(arr_v[v2]) {
                    case 0: {
                        if(z1 || !(animation$Timeline0 instanceof DrawOrderTimeline)) {
                            f8 = f5;
                            animation$MixDirection1 = animation$MixDirection0;
                            animation$MixBlend2 = animation$MixBlend1;
                            f9 = f3;
                            break;
                        }
                        else {
                            f10 = f5;
                            f11 = f4;
                            arr_object2 = arr_object;
                            v3 = v1;
                            animation$MixBlend3 = animation$MixBlend1;
                            v4 = v2;
                            arr_f1 = arr_f;
                            arr_object3 = arr_object1;
                            arr_v1 = arr_v;
                            goto label_127;
                        }
                        goto label_75;
                    }
                    case 1: {
                    label_75:
                        f8 = f5;
                        animation$MixDirection1 = animation$MixDirection0;
                        animation$MixBlend2 = MixBlend.setup;
                        f9 = f3;
                        break;
                    }
                    case 2: {
                        f8 = f5;
                        animation$MixDirection1 = animation$MixDirection0;
                        animation$MixBlend2 = animation$MixBlend1;
                        f9 = f2;
                        break;
                    }
                    case 3: {
                        f8 = f5;
                        animation$MixDirection1 = animation$MixDirection0;
                        animation$MixBlend2 = MixBlend.setup;
                        f9 = f2;
                        break;
                    }
                    default: {
                        f8 = f5;
                        TrackEntry animationState$TrackEntry2 = (TrackEntry)arr_object1[v2];
                        animation$MixDirection1 = animation$MixDirection0;
                        f9 = Math.max(0.0f, 1.0f - animationState$TrackEntry2.mixTime / animationState$TrackEntry2.mixDuration) * f2;
                        animation$MixBlend2 = MixBlend.setup;
                    }
                }
                animationState$TrackEntry1.totalAlpha += f9;
                if(animation$Timeline0 instanceof RotateTimeline) {
                    f10 = f8;
                    f11 = f4;
                    arr_object2 = arr_object;
                    v3 = v1;
                    animation$MixBlend3 = animation$MixBlend1;
                    this.applyRotateTimeline(((RotateTimeline)animation$Timeline0), skeleton0, f6, f9, animation$MixBlend2, arr_f, v2 << 1, z2);
                    v4 = v2;
                    arr_f1 = arr_f;
                    arr_object3 = arr_object1;
                    arr_v1 = arr_v;
                }
                else {
                    f10 = f8;
                    f11 = f4;
                    arr_object2 = arr_object;
                    v3 = v1;
                    animation$MixBlend3 = animation$MixBlend1;
                    if(animation$Timeline0 instanceof AttachmentTimeline) {
                        this.applyAttachmentTimeline(((AttachmentTimeline)animation$Timeline0), skeleton0, f6, animation$MixBlend2, z);
                        v4 = v2;
                        arr_f1 = arr_f;
                        arr_object3 = arr_object1;
                        arr_v1 = arr_v;
                    }
                    else {
                        if(!z1 || !(animation$Timeline0 instanceof DrawOrderTimeline)) {
                            animation$MixBlend4 = animation$MixBlend2;
                        }
                        else {
                            animation$MixBlend4 = animation$MixBlend2;
                            if(animation$MixBlend4 == MixBlend.setup) {
                                animation$MixDirection2 = MixDirection.in;
                                v4 = v2;
                                goto label_123;
                            }
                        }
                        v4 = v2;
                        animation$MixDirection2 = animation$MixDirection1;
                    label_123:
                        arr_f1 = arr_f;
                        arr_object3 = arr_object1;
                        arr_v1 = arr_v;
                        animation$Timeline0.apply(skeleton0, f11, f6, array1, f9, animation$MixBlend4, animation$MixDirection2);
                    }
                }
            label_127:
                v2 = v4 + 1;
                arr_f = arr_f1;
                arr_object1 = arr_object3;
                arr_v = arr_v1;
                f5 = f10;
                f4 = f11;
                arr_object = arr_object2;
                v1 = v3;
                animation$MixBlend1 = animation$MixBlend3;
            }
        }
        f7 = f5;
        if(animationState$TrackEntry0.mixDuration > 0.0f) {
            f12 = f7;
            this.queueEvents(animationState$TrackEntry1, f12);
        }
        else {
            f12 = f7;
        }
        this.events.clear();
        animationState$TrackEntry1.nextAnimationLast = f12;
        animationState$TrackEntry1.nextTrackLast = animationState$TrackEntry1.trackTime;
        return f;
    }

    private void applyRotateTimeline(RotateTimeline animation$RotateTimeline0, Skeleton skeleton0, float f, float f1, MixBlend animation$MixBlend0, float[] arr_f, int v, boolean z) {
        float f8;
        float f7;
        float f6;
        float f3;
        float f2;
        if(z) {
            arr_f[v] = 0.0f;
        }
        if(f1 == 1.0f) {
            animation$RotateTimeline0.apply(skeleton0, 0.0f, f, null, 1.0f, animation$MixBlend0, MixDirection.in);
            return;
        }
        int v1 = 0;
        Bone bone0 = (Bone)skeleton0.bones.get(animation$RotateTimeline0.boneIndex);
        if(!bone0.active) {
            return;
        }
        if(f < animation$RotateTimeline0.frames[0]) {
            switch(animation$MixBlend0) {
                case start: {
                    bone0.rotation = bone0.data.rotation;
                    return;
                }
                case interrupt: {
                    f2 = bone0.rotation;
                    f3 = bone0.data.rotation;
                    break;
                }
                default: {
                    return;
                }
            }
        }
        else {
            float f4 = animation$MixBlend0 == MixBlend.setup ? bone0.data.rotation : bone0.rotation;
            f3 = bone0.data.rotation + animation$RotateTimeline0.getCurveValue(f);
            f2 = f4;
        }
        float f5 = f3 - f2 - ((float)((0x4000 - ((int)(16384.5 - ((double)((f3 - f2) / 360.0f))))) * 360));
        if(f5 == 0.0f) {
            f6 = arr_f[v];
        }
        else {
            if(z) {
                f7 = f5;
                f8 = 0.0f;
            }
            else {
                f8 = arr_f[v];
                f7 = arr_f[v + 1];
            }
            int v2 = f5 > 0.0f ? 1 : 0;
            if(f8 >= 0.0f) {
                v1 = 1;
            }
            if(Math.signum(f7) != Math.signum(f5) && Math.abs(f7) <= 90.0f) {
                if(Math.abs(f8) > 180.0f) {
                    f8 += Math.signum(f8) * 360.0f;
                }
                v1 = v2;
            }
            f6 = v1 == v2 ? f5 + f8 - f8 % 360.0f : f5 + f8 - f8 % 360.0f + Math.signum(f8) * 360.0f;
            arr_f[v] = f6;
        }
        arr_f[v + 1] = f5;
        bone0.rotation = f2 + f6 * f1;
    }

    public void clearListenerNotifications() {
        this.queue.clear();
    }

    public void clearListeners() {
        this.listeners.clear();
    }

    public void clearNext(TrackEntry animationState$TrackEntry0) {
        for(TrackEntry animationState$TrackEntry1 = animationState$TrackEntry0.next; animationState$TrackEntry1 != null; animationState$TrackEntry1 = animationState$TrackEntry1.next) {
            this.queue.dispose(animationState$TrackEntry1);
        }
        animationState$TrackEntry0.next = null;
    }

    public void clearTrack(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("trackIndex must be >= 0.");
        }
        if(v >= this.tracks.size) {
            return;
        }
        TrackEntry animationState$TrackEntry0 = (TrackEntry)this.tracks.get(v);
        if(animationState$TrackEntry0 == null) {
            return;
        }
        this.queue.end(animationState$TrackEntry0);
        this.clearNext(animationState$TrackEntry0);
        TrackEntry animationState$TrackEntry1 = animationState$TrackEntry0;
        TrackEntry animationState$TrackEntry2;
        while((animationState$TrackEntry2 = animationState$TrackEntry1.mixingFrom) != null) {
            this.queue.end(animationState$TrackEntry2);
            animationState$TrackEntry1.mixingFrom = null;
            animationState$TrackEntry1.mixingTo = null;
            animationState$TrackEntry1 = animationState$TrackEntry2;
        }
        this.tracks.set(animationState$TrackEntry0.trackIndex, null);
        this.queue.drain();
    }

    public void clearTracks() {
        boolean z = this.queue.drainDisabled;
        this.queue.drainDisabled = true;
        int v = this.tracks.size;
        for(int v1 = 0; v1 < v; ++v1) {
            this.clearTrack(v1);
        }
        this.tracks.clear();
        this.queue.drainDisabled = z;
        this.queue.drain();
    }

    private void computeHold(TrackEntry animationState$TrackEntry0) {
        TrackEntry animationState$TrackEntry1 = animationState$TrackEntry0.mixingTo;
        Object[] arr_object = animationState$TrackEntry0.animation.timelines.items;
        int v = animationState$TrackEntry0.animation.timelines.size;
        int[] arr_v = animationState$TrackEntry0.timelineMode.setSize(v);
        animationState$TrackEntry0.timelineHoldMix.clear();
        Object[] arr_object1 = animationState$TrackEntry0.timelineHoldMix.setSize(v);
        ObjectSet objectSet0 = this.propertyIds;
        if(animationState$TrackEntry1 != null && animationState$TrackEntry1.holdPrevious) {
            for(int v1 = 0; v1 < v; ++v1) {
                arr_v[v1] = objectSet0.addAll(((Timeline)arr_object[v1]).getPropertyIds()) ? 3 : 2;
            }
            return;
        }
        int v2 = 0;
        while(v2 < v) {
            Timeline animation$Timeline0 = (Timeline)arr_object[v2];
            String[] arr_s = animation$Timeline0.getPropertyIds();
            if(!objectSet0.addAll(arr_s)) {
                arr_v[v2] = 0;
            }
            else if(animationState$TrackEntry1 == null || animation$Timeline0 instanceof AttachmentTimeline || animation$Timeline0 instanceof DrawOrderTimeline || animation$Timeline0 instanceof EventTimeline || !animationState$TrackEntry1.animation.hasTimeline(arr_s)) {
                arr_v[v2] = 1;
            }
            else {
                TrackEntry animationState$TrackEntry2 = animationState$TrackEntry1.mixingTo;
                while(animationState$TrackEntry2 != null) {
                    if(animationState$TrackEntry2.animation.hasTimeline(arr_s)) {
                        animationState$TrackEntry2 = animationState$TrackEntry2.mixingTo;
                        continue;
                    }
                    if(animationState$TrackEntry2.mixDuration <= 0.0f) {
                        break;
                    }
                    arr_v[v2] = 4;
                    arr_object1[v2] = animationState$TrackEntry2;
                    goto label_34;
                }
                arr_v[v2] = 3;
            }
        label_34:
            ++v2;
        }
    }

    private TrackEntry expandToIndex(int v) {
        if(v < this.tracks.size) {
            return (TrackEntry)this.tracks.get(v);
        }
        this.tracks.ensureCapacity(v - this.tracks.size + 1);
        this.tracks.size = v + 1;
        return null;
    }

    @Null
    public TrackEntry getCurrent(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("trackIndex must be >= 0.");
        }
        return v < this.tracks.size ? ((TrackEntry)this.tracks.get(v)) : null;
    }

    public AnimationStateData getData() {
        return this.data;
    }

    public float getTimeScale() {
        return this.timeScale;
    }

    public Array getTracks() {
        return this.tracks;
    }

    private void queueEvents(TrackEntry animationState$TrackEntry0, float f) {
        float f1 = animationState$TrackEntry0.animationStart;
        float f2 = animationState$TrackEntry0.animationEnd;
        float f3 = f2 - f1;
        float f4 = animationState$TrackEntry0.trackLast % f3;
        Object[] arr_object = this.events.items;
        int v = this.events.size;
        boolean z = false;
        int v1;
        for(v1 = 0; v1 < v; ++v1) {
            Event event0 = (Event)arr_object[v1];
            if(event0.time < f4) {
                break;
            }
            if(event0.time <= f2) {
                this.queue.event(animationState$TrackEntry0, event0);
            }
        }
        if(!animationState$TrackEntry0.loop) {
            if(f >= f2 && animationState$TrackEntry0.animationLast < f2) {
                z = true;
            }
        }
        else if(f3 == 0.0f || f4 > animationState$TrackEntry0.trackTime % f3) {
            z = true;
        }
        if(z) {
            this.queue.complete(animationState$TrackEntry0);
        }
        while(v1 < v) {
            Event event1 = (Event)arr_object[v1];
            if(event1.time >= f1) {
                this.queue.event(animationState$TrackEntry0, event1);
            }
            ++v1;
        }
    }

    public void removeListener(AnimationStateListener animationState$AnimationStateListener0) {
        this.listeners.removeValue(animationState$AnimationStateListener0, true);
    }

    public TrackEntry setAnimation(int v, Animation animation0, boolean z) {
        if(v < 0) {
            throw new IllegalArgumentException("trackIndex must be >= 0.");
        }
        if(animation0 == null) {
            throw new IllegalArgumentException("animation cannot be null.");
        }
        boolean z1 = true;
        TrackEntry animationState$TrackEntry0 = this.expandToIndex(v);
        if(animationState$TrackEntry0 != null) {
            if(animationState$TrackEntry0.nextTrackLast == -1.0f) {
                this.tracks.set(v, animationState$TrackEntry0.mixingFrom);
                this.queue.interrupt(animationState$TrackEntry0);
                this.queue.end(animationState$TrackEntry0);
                this.clearNext(animationState$TrackEntry0);
                animationState$TrackEntry0 = animationState$TrackEntry0.mixingFrom;
                z1 = false;
            }
            else {
                this.clearNext(animationState$TrackEntry0);
            }
        }
        TrackEntry animationState$TrackEntry1 = this.trackEntry(v, animation0, z, animationState$TrackEntry0);
        this.setCurrent(v, animationState$TrackEntry1, z1);
        this.queue.drain();
        return animationState$TrackEntry1;
    }

    public TrackEntry setAnimation(int v, String s, boolean z) {
        Animation animation0 = this.data.skeletonData.findAnimation(s);
        if(animation0 == null) {
            throw new IllegalArgumentException("Animation not found: " + s);
        }
        return this.setAnimation(v, animation0, z);
    }

    private void setAttachment(Skeleton skeleton0, Slot slot0, String s, boolean z) {
        slot0.setAttachment((s == null ? null : skeleton0.getAttachment(slot0.data.index, s)));
        if(z) {
            slot0.attachmentState = this.unkeyedState + 2;
        }
    }

    private void setCurrent(int v, TrackEntry animationState$TrackEntry0, boolean z) {
        TrackEntry animationState$TrackEntry1 = this.expandToIndex(v);
        this.tracks.set(v, animationState$TrackEntry0);
        animationState$TrackEntry0.previous = null;
        if(animationState$TrackEntry1 != null) {
            if(z) {
                this.queue.interrupt(animationState$TrackEntry1);
            }
            animationState$TrackEntry0.mixingFrom = animationState$TrackEntry1;
            animationState$TrackEntry1.mixingTo = animationState$TrackEntry0;
            animationState$TrackEntry0.mixTime = 0.0f;
            if(animationState$TrackEntry1.mixingFrom != null && animationState$TrackEntry1.mixDuration > 0.0f) {
                animationState$TrackEntry0.interruptAlpha *= Math.min(1.0f, animationState$TrackEntry1.mixTime / animationState$TrackEntry1.mixDuration);
            }
            animationState$TrackEntry1.timelinesRotation.clear();
        }
        this.queue.start(animationState$TrackEntry0);
    }

    public void setData(AnimationStateData animationStateData0) {
        if(animationStateData0 == null) {
            throw new IllegalArgumentException("data cannot be null.");
        }
        this.data = animationStateData0;
    }

    public TrackEntry setEmptyAnimation(int v, float f) {
        TrackEntry animationState$TrackEntry0 = this.setAnimation(v, AnimationState.emptyAnimation, false);
        animationState$TrackEntry0.mixDuration = f;
        animationState$TrackEntry0.trackEnd = f;
        return animationState$TrackEntry0;
    }

    public void setEmptyAnimations(float f) {
        boolean z = this.queue.drainDisabled;
        this.queue.drainDisabled = true;
        Object[] arr_object = this.tracks.items;
        int v = this.tracks.size;
        for(int v1 = 0; v1 < v; ++v1) {
            TrackEntry animationState$TrackEntry0 = (TrackEntry)arr_object[v1];
            if(animationState$TrackEntry0 != null) {
                this.setEmptyAnimation(animationState$TrackEntry0.trackIndex, f);
            }
        }
        this.queue.drainDisabled = z;
        this.queue.drain();
    }

    public void setTimeScale(float f) {
        this.timeScale = f;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder(0x40);
        Object[] arr_object = this.tracks.items;
        int v = this.tracks.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(((TrackEntry)arr_object[v1]) != null) {
                if(stringBuilder0.length() > 0) {
                    stringBuilder0.append(", ");
                }
                stringBuilder0.append("<none>");
            }
        }
        return stringBuilder0.length() == 0 ? "<none>" : stringBuilder0.toString();
    }

    private TrackEntry trackEntry(int v, Animation animation0, boolean z, @Null TrackEntry animationState$TrackEntry0) {
        TrackEntry animationState$TrackEntry1 = (TrackEntry)this.trackEntryPool.obtain();
        animationState$TrackEntry1.trackIndex = v;
        animationState$TrackEntry1.animation = animation0;
        animationState$TrackEntry1.loop = z;
        animationState$TrackEntry1.holdPrevious = false;
        float f = 0.0f;
        animationState$TrackEntry1.eventThreshold = 0.0f;
        animationState$TrackEntry1.attachmentThreshold = 0.0f;
        animationState$TrackEntry1.drawOrderThreshold = 0.0f;
        animationState$TrackEntry1.animationStart = 0.0f;
        animationState$TrackEntry1.animationEnd = animation0.getDuration();
        animationState$TrackEntry1.animationLast = -1.0f;
        animationState$TrackEntry1.nextAnimationLast = -1.0f;
        animationState$TrackEntry1.delay = 0.0f;
        animationState$TrackEntry1.trackTime = 0.0f;
        animationState$TrackEntry1.trackLast = -1.0f;
        animationState$TrackEntry1.nextTrackLast = -1.0f;
        animationState$TrackEntry1.trackEnd = 3.402823E+38f;
        animationState$TrackEntry1.timeScale = 1.0f;
        animationState$TrackEntry1.alpha = 1.0f;
        animationState$TrackEntry1.interruptAlpha = 1.0f;
        animationState$TrackEntry1.mixTime = 0.0f;
        if(animationState$TrackEntry0 != null) {
            f = this.data.getMix(animationState$TrackEntry0.animation, animation0);
        }
        animationState$TrackEntry1.mixDuration = f;
        animationState$TrackEntry1.mixBlend = MixBlend.replace;
        return animationState$TrackEntry1;
    }

    public void update(float f) {
        float f1 = f * this.timeScale;
        Object[] arr_object = this.tracks.items;
        int v = this.tracks.size;
        int v1 = 0;
        while(v1 < v) {
            TrackEntry animationState$TrackEntry0 = (TrackEntry)arr_object[v1];
            if(animationState$TrackEntry0 != null) {
                animationState$TrackEntry0.animationLast = animationState$TrackEntry0.nextAnimationLast;
                animationState$TrackEntry0.trackLast = animationState$TrackEntry0.nextTrackLast;
                float f2 = animationState$TrackEntry0.timeScale * f1;
                float f3 = 0.0f;
                if(animationState$TrackEntry0.delay > 0.0f) {
                    animationState$TrackEntry0.delay -= f2;
                    if(animationState$TrackEntry0.delay <= 0.0f) {
                        f2 = -animationState$TrackEntry0.delay;
                        animationState$TrackEntry0.delay = 0.0f;
                        goto label_16;
                    }
                }
                else {
                label_16:
                    TrackEntry animationState$TrackEntry1 = animationState$TrackEntry0.next;
                    if(animationState$TrackEntry1 != null) {
                        float f4 = animationState$TrackEntry0.trackLast - animationState$TrackEntry1.delay;
                        if(f4 >= 0.0f) {
                            animationState$TrackEntry1.delay = 0.0f;
                            float f5 = animationState$TrackEntry1.trackTime;
                            if(animationState$TrackEntry0.timeScale != 0.0f) {
                                f3 = animationState$TrackEntry1.timeScale * (f4 / animationState$TrackEntry0.timeScale + f1);
                            }
                            animationState$TrackEntry1.trackTime = f5 + f3;
                            animationState$TrackEntry0.trackTime += f2;
                            this.setCurrent(v1, animationState$TrackEntry1, true);
                            while(animationState$TrackEntry1.mixingFrom != null) {
                                animationState$TrackEntry1.mixTime += f1;
                                animationState$TrackEntry1 = animationState$TrackEntry1.mixingFrom;
                            }
                            goto label_47;
                        }
                    }
                    else if(animationState$TrackEntry0.trackLast >= animationState$TrackEntry0.trackEnd && animationState$TrackEntry0.mixingFrom == null) {
                        arr_object[v1] = null;
                        this.queue.end(animationState$TrackEntry0);
                        this.clearNext(animationState$TrackEntry0);
                        goto label_47;
                    }
                    if(animationState$TrackEntry0.mixingFrom != null && this.updateMixingFrom(animationState$TrackEntry0, f1)) {
                        TrackEntry animationState$TrackEntry2 = animationState$TrackEntry0.mixingFrom;
                        animationState$TrackEntry0.mixingFrom = null;
                        if(animationState$TrackEntry2 != null) {
                            animationState$TrackEntry2.mixingTo = null;
                        }
                        while(animationState$TrackEntry2 != null) {
                            this.queue.end(animationState$TrackEntry2);
                            animationState$TrackEntry2 = animationState$TrackEntry2.mixingFrom;
                        }
                    }
                    animationState$TrackEntry0.trackTime += f2;
                }
            }
        label_47:
            ++v1;
        }
        this.queue.drain();
    }

    private boolean updateMixingFrom(TrackEntry animationState$TrackEntry0, float f) {
        TrackEntry animationState$TrackEntry1 = animationState$TrackEntry0.mixingFrom;
        if(animationState$TrackEntry1 == null) {
            return true;
        }
        boolean z = this.updateMixingFrom(animationState$TrackEntry1, f);
        animationState$TrackEntry1.animationLast = animationState$TrackEntry1.nextAnimationLast;
        animationState$TrackEntry1.trackLast = animationState$TrackEntry1.nextTrackLast;
        if(animationState$TrackEntry0.mixTime > 0.0f && animationState$TrackEntry0.mixTime >= animationState$TrackEntry0.mixDuration) {
            if(animationState$TrackEntry1.totalAlpha == 0.0f || animationState$TrackEntry0.mixDuration == 0.0f) {
                animationState$TrackEntry0.mixingFrom = animationState$TrackEntry1.mixingFrom;
                if(animationState$TrackEntry1.mixingFrom != null) {
                    animationState$TrackEntry1.mixingFrom.mixingTo = animationState$TrackEntry0;
                }
                animationState$TrackEntry0.interruptAlpha = animationState$TrackEntry1.interruptAlpha;
                this.queue.end(animationState$TrackEntry1);
            }
            return z;
        }
        animationState$TrackEntry1.trackTime += animationState$TrackEntry1.timeScale * f;
        animationState$TrackEntry0.mixTime += f;
        return false;
    }
}

