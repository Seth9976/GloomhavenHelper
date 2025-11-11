package com.hm.spine.utils;

import com.badlogic.gdx.utils.Pool;
import com.hm.spine.Skeleton;
import com.hm.spine.SkeletonData;

public class SkeletonPool extends Pool {
   private SkeletonData skeletonData;

   public SkeletonPool(SkeletonData skeletonData) {
      this.skeletonData = skeletonData;
   }

   public SkeletonPool(SkeletonData skeletonData, int initialCapacity) {
      super(initialCapacity);
      this.skeletonData = skeletonData;
   }

   public SkeletonPool(SkeletonData skeletonData, int initialCapacity, int max) {
      super(initialCapacity, max);
      this.skeletonData = skeletonData;
   }

   protected Skeleton newObject() {
      return new Skeleton(this.skeletonData);
   }
}
