package com.hm.spine.attachments;

import com.badlogic.gdx.utils.Null;
import com.hm.spine.Skeleton;

public class SkeletonAttachment extends Attachment {
   @Null
   private Skeleton skeleton;

   public SkeletonAttachment(String name) {
      super(name);
   }

   protected SkeletonAttachment(SkeletonAttachment other) {
      super(other);
      this.skeleton = other.skeleton;
   }

   @Null
   public Skeleton getSkeleton() {
      return this.skeleton;
   }

   public void setSkeleton(@Null Skeleton skeleton) {
      this.skeleton = skeleton;
   }

   public SkeletonAttachment copy() {
      return new SkeletonAttachment(this);
   }
}
