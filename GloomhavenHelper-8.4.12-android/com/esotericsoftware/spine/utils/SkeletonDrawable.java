package com.esotericsoftware.spine.utils;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonRenderer;

public class SkeletonDrawable extends BaseDrawable {
    private SkeletonRenderer renderer;
    private boolean resetBlendFunction;
    private Skeleton skeleton;
    AnimationState state;

    public SkeletonDrawable() {
        this.resetBlendFunction = true;
    }

    public SkeletonDrawable(SkeletonRenderer skeletonRenderer0, Skeleton skeleton0, AnimationState animationState0) {
        this.resetBlendFunction = true;
        this.renderer = skeletonRenderer0;
        this.skeleton = skeleton0;
        this.state = animationState0;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable
    public void draw(Batch batch0, float f, float f1, float f2, float f3) {
        int v = batch0.getBlendSrcFunc();
        int v1 = batch0.getBlendDstFunc();
        int v2 = batch0.getBlendSrcFuncAlpha();
        int v3 = batch0.getBlendDstFuncAlpha();
        this.skeleton.setPosition(f, f1);
        this.skeleton.updateWorldTransform();
        this.renderer.draw(batch0, this.skeleton);
        if(this.resetBlendFunction) {
            batch0.setBlendFunctionSeparate(v, v1, v2, v3);
        }
    }

    public AnimationState getAnimationState() {
        return this.state;
    }

    public SkeletonRenderer getRenderer() {
        return this.renderer;
    }

    public boolean getResetBlendFunction() {
        return this.resetBlendFunction;
    }

    public Skeleton getSkeleton() {
        return this.skeleton;
    }

    public void setAnimationState(AnimationState animationState0) {
        this.state = animationState0;
    }

    public void setRenderer(SkeletonRenderer skeletonRenderer0) {
        this.renderer = skeletonRenderer0;
    }

    public void setResetBlendFunction(boolean z) {
        this.resetBlendFunction = z;
    }

    public void setSkeleton(Skeleton skeleton0) {
        this.skeleton = skeleton0;
    }

    public void update(float f) {
        this.state.update(f);
        this.state.apply(this.skeleton);
    }
}

