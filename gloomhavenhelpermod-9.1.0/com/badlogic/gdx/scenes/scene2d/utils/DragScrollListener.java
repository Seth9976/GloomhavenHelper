package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.utils.Timer;

public class DragScrollListener extends DragListener {
   static final Vector2 tmpCoords = new Vector2();
   private ScrollPane scroll;
   private Timer.Task scrollUp;
   private Timer.Task scrollDown;
   Interpolation interpolation = Interpolation.exp5In;
   float minSpeed = 15.0F;
   float maxSpeed = 75.0F;
   float tickSecs = 0.05F;
   long startTime;
   long rampTime = 1750L;
   float padTop;
   float padBottom;

   public DragScrollListener(final ScrollPane scroll) {
      this.scroll = scroll;
      this.scrollUp = new Timer.Task() {
         @Override
         public void run() {
            DragScrollListener.this.scroll(scroll.getScrollY() - DragScrollListener.this.getScrollPixels());
         }
      };
      this.scrollDown = new Timer.Task() {
         @Override
         public void run() {
            DragScrollListener.this.scroll(scroll.getScrollY() + DragScrollListener.this.getScrollPixels());
         }
      };
   }

   public void setup(float minSpeedPixels, float maxSpeedPixels, float tickSecs, float rampSecs) {
      this.minSpeed = minSpeedPixels;
      this.maxSpeed = maxSpeedPixels;
      this.tickSecs = tickSecs;
      this.rampTime = (long)(rampSecs * 1000.0F);
   }

   float getScrollPixels() {
      return this.interpolation
         .apply(this.minSpeed, this.maxSpeed, Math.min(1.0F, (float)(System.currentTimeMillis() - this.startTime) / (float)this.rampTime));
   }

   @Override
   public void drag(InputEvent event, float x, float y, int pointer) {
      event.getListenerActor().localToActorCoordinates(this.scroll, tmpCoords.set(x, y));
      if (this.isAbove(tmpCoords.y)) {
         this.scrollDown.cancel();
         if (!this.scrollUp.isScheduled()) {
            this.startTime = System.currentTimeMillis();
            Timer.schedule(this.scrollUp, this.tickSecs, this.tickSecs);
         }
      } else if (this.isBelow(tmpCoords.y)) {
         this.scrollUp.cancel();
         if (!this.scrollDown.isScheduled()) {
            this.startTime = System.currentTimeMillis();
            Timer.schedule(this.scrollDown, this.tickSecs, this.tickSecs);
         }
      } else {
         this.scrollUp.cancel();
         this.scrollDown.cancel();
      }
   }

   @Override
   public void dragStop(InputEvent event, float x, float y, int pointer) {
      this.scrollUp.cancel();
      this.scrollDown.cancel();
   }

   protected boolean isAbove(float y) {
      return y >= this.scroll.getHeight() - this.padTop;
   }

   protected boolean isBelow(float y) {
      return y < this.padBottom;
   }

   protected void scroll(float y) {
      this.scroll.setScrollY(y);
   }

   public void setPadding(float padTop, float padBottom) {
      this.padTop = padTop;
      this.padBottom = padBottom;
   }
}
