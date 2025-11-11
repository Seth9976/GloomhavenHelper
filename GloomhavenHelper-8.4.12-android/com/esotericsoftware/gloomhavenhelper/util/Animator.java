package com.esotericsoftware.gloomhavenhelper.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.SnapshotArray;
import com.esotericsoftware.gloomhavenhelper.App;

public class Animator {
    public interface HasAnimator {
        Animator getAnimator();
    }

    private Actor actor;
    public boolean animating;
    public float delay;
    float distanceForMaxSpeed;
    public static boolean enabled = true;
    float maxSpeed;
    float minSpeed;
    public final Vector2 target;

    static {
    }

    public Animator(Actor actor0, float f, float f1, float f2) {
        this.target = new Vector2();
        this.actor = actor0;
        this.minSpeed = f;
        this.maxSpeed = f1;
        this.distanceForMaxSpeed = f2;
    }

    public static boolean childrenAnimating(Group group0) {
        SnapshotArray snapshotArray0 = group0.getChildren();
        int v = snapshotArray0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            Actor actor0 = (Actor)snapshotArray0.get(v1);
            if(actor0 instanceof HasAnimator && ((HasAnimator)actor0).getAnimator().animating) {
                return true;
            }
        }
        return false;
    }

    public void finish(boolean z) {
        this.animating = false;
        this.delay = 0.0f;
        this.actor.setPosition(this.target.x, this.target.y);
        if(z && !Animator.childrenAnimating(this.actor.getParent())) {
            ((WidgetGroup)this.actor.getParent()).invalidate();
        }
    }

    public void store(float f) {
        this.delay = Math.max(f, this.delay);
        this.target.set(this.actor.getX(), this.actor.getY());
        this.animating = true;
    }

    public static void storeChildren(WidgetGroup widgetGroup0, float f) {
        SnapshotArray snapshotArray0 = widgetGroup0.getChildren();
        int v = snapshotArray0.size;
        FloatArray floatArray0 = new FloatArray(v * 2);
        int v1 = 0;
        for(int v2 = 0; v2 < v; ++v2) {
            Actor actor0 = (Actor)snapshotArray0.get(v2);
            if(actor0 instanceof HasAnimator) {
                floatArray0.add(actor0.getX());
                floatArray0.add(actor0.getY());
            }
        }
        Animator.enabled = false;
        float f1 = widgetGroup0.getWidth();
        float f2 = widgetGroup0.getHeight();
        widgetGroup0.invalidateHierarchy();
        App.root.validate();
        for(int v3 = 0; v3 < v; ++v3) {
            Actor actor1 = (Actor)snapshotArray0.get(v3);
            if(actor1 instanceof HasAnimator) {
                ((HasAnimator)actor1).getAnimator().store(f);
            }
        }
        widgetGroup0.setSize(f1, f2);
        widgetGroup0.invalidateHierarchy();
        App.root.validate();
        Animator.enabled = true;
        float f3 = widgetGroup0.getHeight();
        for(int v4 = 0; v1 < v; v4 += 2) {
            Actor actor2 = (Actor)snapshotArray0.get(v1);
            if(actor2 instanceof HasAnimator) {
                actor2.setX(floatArray0.get(v4));
                actor2.setY(floatArray0.get(v4 + 1) + (f3 - f2));
            }
            ++v1;
        }
    }

    public void update() {
        if(!Animator.enabled) {
            return;
        }
        if(!this.animating) {
            return;
        }
        float f = Math.min(Gdx.graphics.getDeltaTime(), 0.033333f);
        float f1 = this.actor.getX();
        float f2 = this.actor.getY();
        if(f1 != this.target.x || f2 != this.target.y) {
            this.delay -= f;
            if(this.delay <= 0.0f) {
                App.animate(App.v2.set(f1, f2), this.target, this.minSpeed, this.maxSpeed, this.distanceForMaxSpeed, f);
                this.actor.setPosition(App.v2.x, App.v2.y);
            }
        }
        else {
            this.finish(true);
        }
        Gdx.graphics.requestRendering();
    }

    public static void updateChildren(WidgetGroup widgetGroup0) {
        SnapshotArray snapshotArray0 = widgetGroup0.getChildren();
        int v = snapshotArray0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            Actor actor0 = (Actor)snapshotArray0.get(v1);
            if(actor0 instanceof HasAnimator) {
                ((HasAnimator)actor0).getAnimator().update();
            }
        }
    }
}

