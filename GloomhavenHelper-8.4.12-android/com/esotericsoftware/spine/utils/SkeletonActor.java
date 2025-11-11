package com.esotericsoftware.spine.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonRenderer;

public class SkeletonActor extends Actor {
    private SkeletonRenderer renderer;
    private boolean resetBlendFunction;
    private Skeleton skeleton;
    AnimationState state;

    public SkeletonActor() {
        this.resetBlendFunction = true;
    }

    public SkeletonActor(SkeletonRenderer skeletonRenderer0, Skeleton skeleton0, AnimationState animationState0) {
        this.resetBlendFunction = true;
        this.renderer = skeletonRenderer0;
        this.skeleton = skeleton0;
        this.state = animationState0;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    public void act(float f) {
        this.state.update(f);
        this.state.apply(this.skeleton);
        super.act(f);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch0, float f) {
        int v = batch0.getBlendSrcFunc();
        int v1 = batch0.getBlendDstFunc();
        int v2 = batch0.getBlendSrcFuncAlpha();
        int v3 = batch0.getBlendDstFuncAlpha();
        Color color0 = this.skeleton.getColor();
        float f1 = color0.a;
        Color color1 = this.skeleton.getColor();
        color1.a *= f;
        this.skeleton.setPosition(this.getX(), this.getY());
        this.skeleton.updateWorldTransform();
        this.renderer.draw(batch0, this.skeleton);
        if(this.resetBlendFunction) {
            batch0.setBlendFunctionSeparate(v, v1, v2, v3);
        }
        color0.a = f1;
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
}

