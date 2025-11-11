package com.esotericsoftware.spine.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonRenderer;

public class SkeletonActorPool extends Pool {
    private final Array obtained;
    private SkeletonRenderer renderer;
    SkeletonData skeletonData;
    private final Pool skeletonPool;
    AnimationStateData stateData;
    private final Pool statePool;

    public SkeletonActorPool(SkeletonRenderer skeletonRenderer0, SkeletonData skeletonData0, AnimationStateData animationStateData0) {
        this(skeletonRenderer0, skeletonData0, animationStateData0, 16, 0x7FFFFFFF);
    }

    public SkeletonActorPool(SkeletonRenderer skeletonRenderer0, SkeletonData skeletonData0, AnimationStateData animationStateData0, int v, int v1) {
        super(v, v1);
        this.renderer = skeletonRenderer0;
        this.skeletonData = skeletonData0;
        this.stateData = animationStateData0;
        this.obtained = new Array(false, v);
        this.skeletonPool = new Pool(v, v1) {
            protected Skeleton newObject() {
                return new Skeleton(SkeletonActorPool.this.skeletonData);
            }

            @Override  // com.badlogic.gdx.utils.Pool
            protected Object newObject() {
                return this.newObject();
            }

            protected void reset(Skeleton skeleton0) {
                skeleton0.setColor(Color.WHITE);
                skeleton0.setScale(1.0f, 1.0f);
                skeleton0.setSkin(null);
                skeleton0.setSkin(SkeletonActorPool.this.skeletonData.getDefaultSkin());
                skeleton0.setToSetupPose();
            }

            @Override  // com.badlogic.gdx.utils.Pool
            protected void reset(Object object0) {
                this.reset(((Skeleton)object0));
            }
        };
        this.statePool = new Pool(v, v1) {
            protected AnimationState newObject() {
                return new AnimationState(SkeletonActorPool.this.stateData);
            }

            @Override  // com.badlogic.gdx.utils.Pool
            protected Object newObject() {
                return this.newObject();
            }

            protected void reset(AnimationState animationState0) {
                animationState0.clearTracks();
                animationState0.clearListeners();
            }

            @Override  // com.badlogic.gdx.utils.Pool
            protected void reset(Object object0) {
                this.reset(((AnimationState)object0));
            }
        };
    }

    public void freeComplete() {
        Object[] arr_object = this.obtained.items;
        int v = this.obtained.size - 1;
        while(v >= 0) {
            SkeletonActor skeletonActor0 = (SkeletonActor)arr_object[v];
            Array array0 = skeletonActor0.state.getTracks();
            int v1 = 0;
            int v2 = array0.size;
            while(true) {
                if(v1 < v2) {
                    if(array0.get(v1) == null) {
                        ++v1;
                        continue;
                    }
                    else {
                        break;
                    }
                }
                this.free(skeletonActor0);
                break;
            }
            --v;
        }
    }

    public Array getObtained() {
        return this.obtained;
    }

    protected SkeletonActor newObject() {
        SkeletonActor skeletonActor0 = new SkeletonActor();
        skeletonActor0.setRenderer(this.renderer);
        return skeletonActor0;
    }

    @Override  // com.badlogic.gdx.utils.Pool
    protected Object newObject() {
        return this.newObject();
    }

    public SkeletonActor obtain() {
        SkeletonActor skeletonActor0 = (SkeletonActor)super.obtain();
        skeletonActor0.setSkeleton(((Skeleton)this.skeletonPool.obtain()));
        skeletonActor0.setAnimationState(((AnimationState)this.statePool.obtain()));
        this.obtained.add(skeletonActor0);
        return skeletonActor0;
    }

    @Override  // com.badlogic.gdx.utils.Pool
    public Object obtain() {
        return this.obtain();
    }

    protected void reset(SkeletonActor skeletonActor0) {
        skeletonActor0.remove();
        this.obtained.removeValue(skeletonActor0, true);
        this.skeletonPool.free(skeletonActor0.getSkeleton());
        this.statePool.free(skeletonActor0.getAnimationState());
    }

    @Override  // com.badlogic.gdx.utils.Pool
    protected void reset(Object object0) {
        this.reset(((SkeletonActor)object0));
    }
}

