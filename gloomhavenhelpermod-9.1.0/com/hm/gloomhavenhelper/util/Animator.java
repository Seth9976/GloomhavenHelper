package com.hm.gloomhavenhelper.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.SnapshotArray;
import com.hm.gloomhavenhelper.App;

public class Animator {
   public static boolean enabled = true;
   private Actor actor;
   public final Vector2 target = new Vector2();
   public float delay;
   public boolean animating;
   float minSpeed;
   float maxSpeed;
   float distanceForMaxSpeed;

   public Animator(Actor actor, float minSpeed, float maxSpeed, float distanceForMaxSpeed) {
      this.actor = actor;
      this.minSpeed = minSpeed;
      this.maxSpeed = maxSpeed;
      this.distanceForMaxSpeed = distanceForMaxSpeed;
   }

   public void store(float delay) {
      this.delay = Math.max(delay, this.delay);
      this.target.set(this.actor.getX(), this.actor.getY());
      this.animating = true;
   }

   public void update() {
      if (enabled) {
         if (this.animating) {
            float delta = Math.min(Gdx.graphics.getDeltaTime(), 0.033333335F);
            float x = this.actor.getX();
            float y = this.actor.getY();
            if (x == this.target.x && y == this.target.y) {
               this.finish(true);
            } else {
               this.delay -= delta;
               if (this.delay <= 0.0F) {
                  App.animate(App.v2.set(x, y), this.target, this.minSpeed, this.maxSpeed, this.distanceForMaxSpeed, delta);
                  this.actor.setPosition(App.v2.x, App.v2.y);
               }
            }

            Gdx.graphics.requestRendering();
         }
      }
   }

   public void finish(boolean invalidate) {
      this.animating = false;
      this.delay = 0.0F;
      this.actor.setPosition(this.target.x, this.target.y);
      if (invalidate && !childrenAnimating(this.actor.getParent())) {
         ((WidgetGroup)this.actor.getParent()).invalidate();
      }
   }

   public static void updateChildren(WidgetGroup parent) {
      SnapshotArray children = parent.getChildren();
      int i = 0;

      for (int n = children.size; i < n; i++) {
         Actor child = (Actor)children.get(i);
         if (child instanceof Animator.HasAnimator) {
            Animator animator = ((Animator.HasAnimator)child).getAnimator();
            animator.update();
         }
      }
   }

   public static void storeChildren(WidgetGroup parent, float delay) {
      SnapshotArray children = parent.getChildren();
      int n = children.size;
      FloatArray before = new FloatArray(n * 2);

      for (int i = 0; i < n; i++) {
         Actor child = (Actor)children.get(i);
         if (child instanceof Animator.HasAnimator) {
            before.add(child.getX());
            before.add(child.getY());
         }
      }

      enabled = false;
      float oldWidth = parent.getWidth();
      float oldHeight = parent.getHeight();
      parent.invalidateHierarchy();
      App.root.validate();
      int j = 0;

      for (int b = 0; j < n; b += 2) {
         Actor child = (Actor)children.get(j);
         if (child instanceof Animator.HasAnimator) {
            Animator animator = ((Animator.HasAnimator)child).getAnimator();
            animator.store(delay);
         }

         j++;
      }

      parent.setSize(oldWidth, oldHeight);
      parent.invalidateHierarchy();
      App.root.validate();
      enabled = true;
      float offsetHeight = parent.getHeight() - oldHeight;
      int k = 0;

      for (int m = 0; k < n; m += 2) {
         Actor child = (Actor)children.get(k);
         if (child instanceof Animator.HasAnimator) {
            child.setX(before.get(m));
            child.setY(before.get(m + 1) + offsetHeight);
         }

         k++;
      }
   }

   public static boolean childrenAnimating(Group parent) {
      SnapshotArray children = parent.getChildren();
      int i = 0;

      for (int n = children.size; i < n; i++) {
         Actor child = (Actor)children.get(i);
         if (child instanceof Animator.HasAnimator) {
            Animator animator = ((Animator.HasAnimator)child).getAnimator();
            if (animator.animating) {
               return true;
            }
         }
      }

      return false;
   }

   public interface HasAnimator {
      Animator getAnimator();
   }
}
