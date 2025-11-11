package com.hm.gloomhavenhelper.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.hm.gloomhavenhelper.App;

public abstract class DragAdjust extends DragListener {
   protected final Label label;
   public int start;
   public int extra;
   public int min;
   public boolean onlyHorizontal;
   private long dragStartTime;
   private boolean dragging;

   public DragAdjust(Label label, Actor... actors) {
      this.label = label;
      this.setTapSquareSize(33.0F);

      for (Actor actor : actors) {
         actor.addListener(this);
      }
   }

   protected int getMax() {
      return Integer.MAX_VALUE;
   }

   protected abstract int getValue();

   protected abstract void setValue(int var1);

   @Override
   public void touchDragged(InputEvent event, float x, float y, int pointer) {
      super.touchDragged(event, x, this.onlyHorizontal ? this.getTouchDownY() : y, pointer);
   }

   @Override
   public void dragStart(InputEvent event, float x, float y, int pointer) {
      this.dragStartTime = System.currentTimeMillis();
      App.stage.cancelTouchFocus(App.gloom.rowsScroll);
      this.start = this.getValue();
      if (this.label == null) {
         this.extra = 0;
      } else {
         this.extra = this.label.getText().length == 0 ? 0 : App.parseInt(this.label.getText().toString());
      }
   }

   @Override
   public void dragStop(InputEvent event, float x, float y, int pointer) {
      this.dragging = false;
   }

   @Override
   public void drag(InputEvent event, float x, float y, int pointer) {
      if (!this.dragging) {
         if (System.currentTimeMillis() - this.dragStartTime < 175L) {
            return;
         }

         this.dragging = true;
      }

      x = this.getDragX() - this.getDragStartX();
      y = this.getDragY() - this.getDragStartY();
      int amount = this.amount(x, y);
      int max = this.getMax();
      if (this.start + amount > max) {
         amount = max - this.start;
      }

      if (this.start + amount < this.min) {
         amount = this.min - this.start;
      }

      this.setValue(this.start + amount);
      amount += this.extra;
      if (this.label != null) {
         this.label.setText((amount >= 0 ? "+" : "") + amount);
      }
   }

   protected int amount(float x, float y) {
      return Math.round((Math.abs(x) > Math.abs(y) ? x : y) / 50.0F);
   }

   public void adjust(int amount) {
      this.start = this.getValue();
      this.extra = 0;
      int max = this.getMax();
      if (this.start + amount > max) {
         amount = max - this.start;
      }

      if (this.start + amount < this.min) {
         amount = this.min - this.start;
      }

      if (amount != -4) {
         if (amount != 0) {
            this.setValue(this.start + amount);
            amount += this.extra;
            if (this.label != null) {
               this.label.setText((amount >= 0 ? "+" : "") + amount);
            }
         }
      }
   }
}
