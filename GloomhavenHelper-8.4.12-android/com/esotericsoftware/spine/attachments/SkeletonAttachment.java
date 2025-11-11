package com.esotericsoftware.spine.attachments;

import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.spine.Skeleton;

public class SkeletonAttachment extends Attachment {
    @Null
    private Skeleton skeleton;

    protected SkeletonAttachment(SkeletonAttachment skeletonAttachment0) {
        super(skeletonAttachment0);
        this.skeleton = skeletonAttachment0.skeleton;
    }

    public SkeletonAttachment(String s) {
        super(s);
    }

    @Override  // com.esotericsoftware.spine.attachments.Attachment
    public Attachment copy() {
        return this.copy();
    }

    public SkeletonAttachment copy() {
        return new SkeletonAttachment(this);
    }

    @Null
    public Skeleton getSkeleton() {
        return this.skeleton;
    }

    public void setSkeleton(@Null Skeleton skeleton0) {
        this.skeleton = skeleton0;
    }
}

