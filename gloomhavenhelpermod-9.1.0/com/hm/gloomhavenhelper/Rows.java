package com.hm.gloomhavenhelper;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.utils.SnapshotArray;
import com.hm.gloomhavenhelper.util.Animator;
import com.hm.gloomhavenhelper.util.Row;

public class Rows extends WidgetGroup {
   private boolean sizeInvalid;
   private float prefHeight;
   public float padTop = 11.0F;
   public float padBottom = 13.0F;
   public float padLeft = 6.0F;
   public float padRight = 6.0F;
   public float minSpaceX = 7.0F;
   public float maxSpaceX = 80.0F;
   public float spaceY = 11.0F;
   public float spaceX;
   public float startX;
   public int columns;

   @Override
   public void invalidate() {
      if (!Animator.enabled || !Animator.childrenAnimating(this)) {
         super.invalidate();
         this.sizeInvalid = true;
      }
   }

   private void computeSize() {
      this.sizeInvalid = false;
      float width = App.stage.getWidth() - this.padLeft - this.padRight;
      int maxColumns = Math.max(1, (int)Math.floor((width - this.minSpaceX) / 1101.0F));
      this.spaceX = MathUtils.clamp(width - 1101 * this.columns, this.minSpaceX, this.maxSpaceX);
      SnapshotArray children = this.getChildren();
      int n = children.size;
      float columnHeight = App.stage.getHeight() - 240.0F - this.padTop - this.padBottom;
      float nextColumnHeight = -1.0F;
      Row.animatedHeight = false;

      while (true) {
         float x = this.padLeft;
         float y = 0.0F;
         boolean first = true;
         this.columns = 1;
         float maxHeight = 0.0F;

         for (int i = 0; i < n; i++) {
            Row row = (Row)children.get(i);
            if (!App.config.hideMonsters || !(row instanceof MonsterRow)) {
               float height = row.getPrefHeight();
               if (!first) {
                  y += this.spaceY;
                  if (this.columns < maxColumns && y + height > columnHeight) {
                     this.columns++;
                     nextColumnHeight = y + height;
                     y = 0.0F;
                     first = true;
                     x += this.spaceX + 1101.0F;
                  }
               } else {
                  first = false;
               }

               y += height;
               maxHeight = Math.max(maxHeight, y);
            }
         }

         if (this.columns <= 1 || !(y > columnHeight)) {
            Row.animatedHeight = true;
            this.prefHeight = maxHeight + this.padTop + this.padBottom;
            this.startX = Math.round((width - 1101 * this.columns + this.spaceX * (this.columns - 1)) / 2.0F + this.padLeft);
            return;
         }

         columnHeight = nextColumnHeight;
      }
   }

   @Override
   public void layout() {
      if (this.sizeInvalid) {
         this.computeSize();
      }

      float dragX = 0.0F;
      float dragY = 0.0F;
      if (App.dragRow != null) {
         dragX = App.dragRow.getX();
         dragY = App.dragRow.getY();
      }

      SnapshotArray children = this.getChildren();
      float y = 0.0F;
      float x = this.startX;
      float startY = this.prefHeight - this.padTop;
      float layoutY = startY;
      float offsetY = this.getHeight() - this.prefHeight;
      boolean first = true;
      int i = 0;

      for (int n = children.size; i < n; i++) {
         Row row = (Row)children.get(i);
         if (App.config.hideMonsters && row instanceof MonsterRow) {
            row.setBounds(0.0F, 0.0F, 1101.0F, 0.0F);
            row.finalX = 0.0F;
            row.finalY = 0.0F;
            row.validate();
         } else {
            float layoutHeight = row.getPrefHeight();
            MonsterRow.animatedHeight = false;
            float height = row.getPrefHeight();
            MonsterRow.animatedHeight = true;
            if (!first) {
               layoutY -= this.spaceY;
               y += this.spaceY;
               if (y + height > this.prefHeight) {
                  layoutY = startY;
                  y = 0.0F;
                  x += this.spaceX + 1101.0F;
               }
            } else {
               first = false;
            }

            y += height;
            layoutY -= layoutHeight;
            row.setBounds(Math.round(x), Math.round(offsetY + layoutY), 1101.0F, Math.round(layoutHeight));
            row.finalX = row.getX();
            row.finalY = row.getY();
            row.validate();
         }
      }

      if (App.dragRow != null) {
         App.dragRow.setPosition(dragX, dragY);
      }
   }

   @Override
   protected void sizeChanged() {
      super.sizeChanged();
   }

   @Override
   public float getPrefHeight() {
      if (this.sizeInvalid) {
         this.computeSize();
      }

      return this.prefHeight;
   }

   @Override
   protected void drawChildren(Batch batch, float parentAlpha) {
      Animator.updateChildren(this);
      int index = 0;
      if (App.dragRow != null) {
         index = App.dragRow.getZIndex();
         if (this.columns > 1) {
            float x = App.dragRow.getX();
            float y = App.dragRow.getY();
            App.dragRow.setPosition(App.dragRow.finalX, App.dragRow.finalY);
            App.dragRow.draw(batch, parentAlpha * 0.25F);
            App.dragRow.setPosition(x, y);
         }

         App.dragRow.toFront();
      }

      super.drawChildren(batch, parentAlpha);
      if (App.dragRow != null && index != -1) {
         App.dragRow.setZIndex(index);
      }
   }
}
