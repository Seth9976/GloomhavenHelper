package com.hm.spine.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.hm.spine.AnimationState;
import com.hm.spine.AnimationStateData;
import com.hm.spine.Skeleton;
import com.hm.spine.SkeletonData;
import com.hm.spine.SkeletonRenderer;

public class SkeletonActorPool extends Pool {
   private SkeletonRenderer renderer;
   SkeletonData skeletonData;
   AnimationStateData stateData;
   private final Pool skeletonPool;
   private final Pool statePool;
   private final Array obtained;

   public SkeletonActorPool(SkeletonRenderer renderer, SkeletonData skeletonData, AnimationStateData stateData) {
      this(renderer, skeletonData, stateData, 16, Integer.MAX_VALUE);
   }

   public SkeletonActorPool(SkeletonRenderer renderer, SkeletonData skeletonData, AnimationStateData stateData, int initialCapacity, int max) {
      super(initialCapacity, max);
      this.renderer = renderer;
      this.skeletonData = skeletonData;
      this.stateData = stateData;
      this.obtained = new Array(false, initialCapacity);
      this.skeletonPool = new Pool(initialCapacity, max) {
         protected Skeleton newObject() {
            return new Skeleton(SkeletonActorPool.this.skeletonData);
         }

         protected void reset(Skeleton skeleton) {
            skeleton.setColor(Color.WHITE);
            skeleton.setScale(1.0F, 1.0F);
            skeleton.setSkin(SkeletonActorPool.this.skeletonData.getDefaultSkin().getName());
            skeleton.setSkin(SkeletonActorPool.this.skeletonData.getDefaultSkin());
            skeleton.setToSetupPose();
         }
      };
      this.statePool = new Pool(initialCapacity, max) {
         protected AnimationState newObject() {
            return new AnimationState(SkeletonActorPool.this.stateData);
         }

         protected void reset(AnimationState state) {
            state.clearTracks();
            state.clearListeners();
         }
      };
   }

   public void freeComplete() {
      Object[] obtained = this.obtained.items;

      label24:
      for (int i = this.obtained.size - 1; i >= 0; i--) {
         SkeletonActor actor = (SkeletonActor)obtained[i];
         Array tracks = actor.state.getTracks();
         int ii = 0;

         for (int nn = tracks.size; ii < nn; ii++) {
            if (tracks.get(ii) != null) {
               continue label24;
            }
         }

         this.free(actor);
      }
   }

   protected SkeletonActor newObject() {
      SkeletonActor actor = new SkeletonActor();
      actor.setRenderer(this.renderer);
      return actor;
   }

   public SkeletonActor obtain() {
      SkeletonActor actor = (SkeletonActor)super.obtain();
      actor.setSkeleton((Skeleton)this.skeletonPool.obtain());
      actor.setAnimationState((AnimationState)this.statePool.obtain());
      this.obtained.add(actor);
      return actor;
   }

   protected void reset(SkeletonActor actor) {
      actor.remove();
      this.obtained.removeValue(actor, true);
      this.skeletonPool.free(actor.getSkeleton());
      this.statePool.free(actor.getAnimationState());
   }

   public Array getObtained() {
      return this.obtained;
   }
}
