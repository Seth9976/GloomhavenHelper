package com.esotericsoftware.spine.utils;

import com.badlogic.gdx.utils.Pool;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;

public class SkeletonPool extends Pool {
    private SkeletonData skeletonData;

    public SkeletonPool(SkeletonData skeletonData0) {
        this.skeletonData = skeletonData0;
    }

    public SkeletonPool(SkeletonData skeletonData0, int v) {
        super(v);
        this.skeletonData = skeletonData0;
    }

    public SkeletonPool(SkeletonData skeletonData0, int v, int v1) {
        super(v, v1);
        this.skeletonData = skeletonData0;
    }

    protected Skeleton newObject() {
        return new Skeleton(this.skeletonData);
    }

    @Override  // com.badlogic.gdx.utils.Pool
    protected Object newObject() {
        return this.newObject();
    }
}

