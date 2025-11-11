package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Cullable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.Null;

public class Container extends WidgetGroup {
   @Null
   private Actor actor;
   private Value minWidth;
   private Value minHeight;
   private Value prefWidth;
   private Value prefHeight;
   private Value maxWidth;
   private Value maxHeight;
   private Value padTop;
   private Value padLeft;
   private Value padBottom;
   private Value padRight;
   private float fillX;
   private float fillY;
   private int align;
   @Null
   private Drawable background;
   private boolean clip;
   private boolean round;

   public Container() {
      this.minWidth = Value.minWidth;
      this.minHeight = Value.minHeight;
      this.prefWidth = Value.prefWidth;
      this.prefHeight = Value.prefHeight;
      this.maxWidth = Value.zero;
      this.maxHeight = Value.zero;
      this.padTop = Value.zero;
      this.padLeft = Value.zero;
      this.padBottom = Value.zero;
      this.padRight = Value.zero;
      this.round = true;
      this.setTouchable(Touchable.childrenOnly);
      this.setTransform(false);
   }

   public Container(@Null Actor actor) {
      this();
      this.setActor(actor);
   }

   @Override
   public void draw(Batch batch, float parentAlpha) {
      this.validate();
      if (this.isTransform()) {
         this.applyTransform(batch, this.computeTransform());
         this.drawBackground(batch, parentAlpha, 0.0F, 0.0F);
         if (this.clip) {
            batch.flush();
            float padLeft = this.padLeft.get(this);
            float padBottom = this.padBottom.get(this);
            if (this.clipBegin(padLeft, padBottom, this.getWidth() - padLeft - this.padRight.get(this), this.getHeight() - padBottom - this.padTop.get(this))) {
               this.drawChildren(batch, parentAlpha);
               batch.flush();
               this.clipEnd();
            }
         } else {
            this.drawChildren(batch, parentAlpha);
         }

         this.resetTransform(batch);
      } else {
         this.drawBackground(batch, parentAlpha, this.getX(), this.getY());
         super.draw(batch, parentAlpha);
      }
   }

   protected void drawBackground(Batch batch, float parentAlpha, float x, float y) {
      if (this.background != null) {
         Color color = this.getColor();
         batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
         this.background.draw(batch, x, y, this.getWidth(), this.getHeight());
      }
   }

   public void setBackground(@Null Drawable background) {
      this.setBackground(background, true);
   }

   public void setBackground(@Null Drawable background, boolean adjustPadding) {
      if (this.background != background) {
         this.background = background;
         if (adjustPadding) {
            if (background == null) {
               this.pad(Value.zero);
            } else {
               this.pad(background.getTopHeight(), background.getLeftWidth(), background.getBottomHeight(), background.getRightWidth());
            }

            this.invalidate();
         }
      }
   }

   public Container background(@Null Drawable background) {
      this.setBackground(background);
      return this;
   }

   @Null
   public Drawable getBackground() {
      return this.background;
   }

   @Override
   public void layout() {
      if (this.actor != null) {
         float padLeft = this.padLeft.get(this);
         float padBottom = this.padBottom.get(this);
         float containerWidth = this.getWidth() - padLeft - this.padRight.get(this);
         float containerHeight = this.getHeight() - padBottom - this.padTop.get(this);
         float minWidth = this.minWidth.get(this.actor);
         float minHeight = this.minHeight.get(this.actor);
         float prefWidth = this.prefWidth.get(this.actor);
         float prefHeight = this.prefHeight.get(this.actor);
         float maxWidth = this.maxWidth.get(this.actor);
         float maxHeight = this.maxHeight.get(this.actor);
         float width;
         if (this.fillX > 0.0F) {
            width = containerWidth * this.fillX;
         } else {
            width = Math.min(prefWidth, containerWidth);
         }

         if (width < minWidth) {
            width = minWidth;
         }

         if (maxWidth > 0.0F && width > maxWidth) {
            width = maxWidth;
         }

         float height;
         if (this.fillY > 0.0F) {
            height = containerHeight * this.fillY;
         } else {
            height = Math.min(prefHeight, containerHeight);
         }

         if (height < minHeight) {
            height = minHeight;
         }

         if (maxHeight > 0.0F && height > maxHeight) {
            height = maxHeight;
         }

         float x = padLeft;
         if ((this.align & 16) != 0) {
            x = padLeft + (containerWidth - width);
         } else if ((this.align & 8) == 0) {
            x = padLeft + (containerWidth - width) / 2.0F;
         }

         float y = padBottom;
         if ((this.align & 2) != 0) {
            y = padBottom + (containerHeight - height);
         } else if ((this.align & 4) == 0) {
            y = padBottom + (containerHeight - height) / 2.0F;
         }

         if (this.round) {
            x = Math.round(x);
            y = Math.round(y);
            width = Math.round(width);
            height = Math.round(height);
         }

         this.actor.setBounds(x, y, width, height);
         if (this.actor instanceof Layout) {
            ((Layout)this.actor).validate();
         }
      }
   }

   @Override
   public void setCullingArea(Rectangle cullingArea) {
      super.setCullingArea(cullingArea);
      if (this.fillX == 1.0F && this.fillY == 1.0F && this.actor instanceof Cullable) {
         ((Cullable)this.actor).setCullingArea(cullingArea);
      }
   }

   public void setActor(@Null Actor actor) {
      if (actor == this) {
         throw new IllegalArgumentException("actor cannot be the Container.");
      } else if (actor != this.actor) {
         if (this.actor != null) {
            super.removeActor(this.actor);
         }

         this.actor = actor;
         if (actor != null) {
            super.addActor(actor);
         }
      }
   }

   @Null
   public Actor getActor() {
      return this.actor;
   }

   @Deprecated
   @Override
   public void addActor(Actor actor) {
      throw new UnsupportedOperationException("Use Container#setActor.");
   }

   @Deprecated
   @Override
   public void addActorAt(int index, Actor actor) {
      throw new UnsupportedOperationException("Use Container#setActor.");
   }

   @Deprecated
   @Override
   public void addActorBefore(Actor actorBefore, Actor actor) {
      throw new UnsupportedOperationException("Use Container#setActor.");
   }

   @Deprecated
   @Override
   public void addActorAfter(Actor actorAfter, Actor actor) {
      throw new UnsupportedOperationException("Use Container#setActor.");
   }

   @Override
   public boolean removeActor(Actor actor) {
      if (actor == null) {
         throw new IllegalArgumentException("actor cannot be null.");
      } else if (actor != this.actor) {
         return false;
      } else {
         this.setActor(null);
         return true;
      }
   }

   @Override
   public boolean removeActor(Actor actor, boolean unfocus) {
      if (actor == null) {
         throw new IllegalArgumentException("actor cannot be null.");
      } else if (actor != this.actor) {
         return false;
      } else {
         this.actor = null;
         return super.removeActor(actor, unfocus);
      }
   }

   @Override
   public Actor removeActorAt(int index, boolean unfocus) {
      Actor actor = super.removeActorAt(index, unfocus);
      if (actor == this.actor) {
         this.actor = null;
      }

      return actor;
   }

   public Container size(Value size) {
      if (size == null) {
         throw new IllegalArgumentException("size cannot be null.");
      } else {
         this.minWidth = size;
         this.minHeight = size;
         this.prefWidth = size;
         this.prefHeight = size;
         this.maxWidth = size;
         this.maxHeight = size;
         return this;
      }
   }

   public Container size(Value width, Value height) {
      if (width == null) {
         throw new IllegalArgumentException("width cannot be null.");
      } else if (height == null) {
         throw new IllegalArgumentException("height cannot be null.");
      } else {
         this.minWidth = width;
         this.minHeight = height;
         this.prefWidth = width;
         this.prefHeight = height;
         this.maxWidth = width;
         this.maxHeight = height;
         return this;
      }
   }

   public Container size(float size) {
      this.size(Value.Fixed.valueOf(size));
      return this;
   }

   public Container size(float width, float height) {
      this.size(Value.Fixed.valueOf(width), Value.Fixed.valueOf(height));
      return this;
   }

   public Container width(Value width) {
      if (width == null) {
         throw new IllegalArgumentException("width cannot be null.");
      } else {
         this.minWidth = width;
         this.prefWidth = width;
         this.maxWidth = width;
         return this;
      }
   }

   public Container width(float width) {
      this.width(Value.Fixed.valueOf(width));
      return this;
   }

   public Container height(Value height) {
      if (height == null) {
         throw new IllegalArgumentException("height cannot be null.");
      } else {
         this.minHeight = height;
         this.prefHeight = height;
         this.maxHeight = height;
         return this;
      }
   }

   public Container height(float height) {
      this.height(Value.Fixed.valueOf(height));
      return this;
   }

   public Container minSize(Value size) {
      if (size == null) {
         throw new IllegalArgumentException("size cannot be null.");
      } else {
         this.minWidth = size;
         this.minHeight = size;
         return this;
      }
   }

   public Container minSize(Value width, Value height) {
      if (width == null) {
         throw new IllegalArgumentException("width cannot be null.");
      } else if (height == null) {
         throw new IllegalArgumentException("height cannot be null.");
      } else {
         this.minWidth = width;
         this.minHeight = height;
         return this;
      }
   }

   public Container minWidth(Value minWidth) {
      if (minWidth == null) {
         throw new IllegalArgumentException("minWidth cannot be null.");
      } else {
         this.minWidth = minWidth;
         return this;
      }
   }

   public Container minHeight(Value minHeight) {
      if (minHeight == null) {
         throw new IllegalArgumentException("minHeight cannot be null.");
      } else {
         this.minHeight = minHeight;
         return this;
      }
   }

   public Container minSize(float size) {
      this.minSize(Value.Fixed.valueOf(size));
      return this;
   }

   public Container minSize(float width, float height) {
      this.minSize(Value.Fixed.valueOf(width), Value.Fixed.valueOf(height));
      return this;
   }

   public Container minWidth(float minWidth) {
      this.minWidth = Value.Fixed.valueOf(minWidth);
      return this;
   }

   public Container minHeight(float minHeight) {
      this.minHeight = Value.Fixed.valueOf(minHeight);
      return this;
   }

   public Container prefSize(Value size) {
      if (size == null) {
         throw new IllegalArgumentException("size cannot be null.");
      } else {
         this.prefWidth = size;
         this.prefHeight = size;
         return this;
      }
   }

   public Container prefSize(Value width, Value height) {
      if (width == null) {
         throw new IllegalArgumentException("width cannot be null.");
      } else if (height == null) {
         throw new IllegalArgumentException("height cannot be null.");
      } else {
         this.prefWidth = width;
         this.prefHeight = height;
         return this;
      }
   }

   public Container prefWidth(Value prefWidth) {
      if (prefWidth == null) {
         throw new IllegalArgumentException("prefWidth cannot be null.");
      } else {
         this.prefWidth = prefWidth;
         return this;
      }
   }

   public Container prefHeight(Value prefHeight) {
      if (prefHeight == null) {
         throw new IllegalArgumentException("prefHeight cannot be null.");
      } else {
         this.prefHeight = prefHeight;
         return this;
      }
   }

   public Container prefSize(float width, float height) {
      this.prefSize(Value.Fixed.valueOf(width), Value.Fixed.valueOf(height));
      return this;
   }

   public Container prefSize(float size) {
      this.prefSize(Value.Fixed.valueOf(size));
      return this;
   }

   public Container prefWidth(float prefWidth) {
      this.prefWidth = Value.Fixed.valueOf(prefWidth);
      return this;
   }

   public Container prefHeight(float prefHeight) {
      this.prefHeight = Value.Fixed.valueOf(prefHeight);
      return this;
   }

   public Container maxSize(Value size) {
      if (size == null) {
         throw new IllegalArgumentException("size cannot be null.");
      } else {
         this.maxWidth = size;
         this.maxHeight = size;
         return this;
      }
   }

   public Container maxSize(Value width, Value height) {
      if (width == null) {
         throw new IllegalArgumentException("width cannot be null.");
      } else if (height == null) {
         throw new IllegalArgumentException("height cannot be null.");
      } else {
         this.maxWidth = width;
         this.maxHeight = height;
         return this;
      }
   }

   public Container maxWidth(Value maxWidth) {
      if (maxWidth == null) {
         throw new IllegalArgumentException("maxWidth cannot be null.");
      } else {
         this.maxWidth = maxWidth;
         return this;
      }
   }

   public Container maxHeight(Value maxHeight) {
      if (maxHeight == null) {
         throw new IllegalArgumentException("maxHeight cannot be null.");
      } else {
         this.maxHeight = maxHeight;
         return this;
      }
   }

   public Container maxSize(float size) {
      this.maxSize(Value.Fixed.valueOf(size));
      return this;
   }

   public Container maxSize(float width, float height) {
      this.maxSize(Value.Fixed.valueOf(width), Value.Fixed.valueOf(height));
      return this;
   }

   public Container maxWidth(float maxWidth) {
      this.maxWidth = Value.Fixed.valueOf(maxWidth);
      return this;
   }

   public Container maxHeight(float maxHeight) {
      this.maxHeight = Value.Fixed.valueOf(maxHeight);
      return this;
   }

   public Container pad(Value pad) {
      if (pad == null) {
         throw new IllegalArgumentException("pad cannot be null.");
      } else {
         this.padTop = pad;
         this.padLeft = pad;
         this.padBottom = pad;
         this.padRight = pad;
         return this;
      }
   }

   public Container pad(Value top, Value left, Value bottom, Value right) {
      if (top == null) {
         throw new IllegalArgumentException("top cannot be null.");
      } else if (left == null) {
         throw new IllegalArgumentException("left cannot be null.");
      } else if (bottom == null) {
         throw new IllegalArgumentException("bottom cannot be null.");
      } else if (right == null) {
         throw new IllegalArgumentException("right cannot be null.");
      } else {
         this.padTop = top;
         this.padLeft = left;
         this.padBottom = bottom;
         this.padRight = right;
         return this;
      }
   }

   public Container padTop(Value padTop) {
      if (padTop == null) {
         throw new IllegalArgumentException("padTop cannot be null.");
      } else {
         this.padTop = padTop;
         return this;
      }
   }

   public Container padLeft(Value padLeft) {
      if (padLeft == null) {
         throw new IllegalArgumentException("padLeft cannot be null.");
      } else {
         this.padLeft = padLeft;
         return this;
      }
   }

   public Container padBottom(Value padBottom) {
      if (padBottom == null) {
         throw new IllegalArgumentException("padBottom cannot be null.");
      } else {
         this.padBottom = padBottom;
         return this;
      }
   }

   public Container padRight(Value padRight) {
      if (padRight == null) {
         throw new IllegalArgumentException("padRight cannot be null.");
      } else {
         this.padRight = padRight;
         return this;
      }
   }

   public Container pad(float pad) {
      Value value = Value.Fixed.valueOf(pad);
      this.padTop = value;
      this.padLeft = value;
      this.padBottom = value;
      this.padRight = value;
      return this;
   }

   public Container pad(float top, float left, float bottom, float right) {
      this.padTop = Value.Fixed.valueOf(top);
      this.padLeft = Value.Fixed.valueOf(left);
      this.padBottom = Value.Fixed.valueOf(bottom);
      this.padRight = Value.Fixed.valueOf(right);
      return this;
   }

   public Container padTop(float padTop) {
      this.padTop = Value.Fixed.valueOf(padTop);
      return this;
   }

   public Container padLeft(float padLeft) {
      this.padLeft = Value.Fixed.valueOf(padLeft);
      return this;
   }

   public Container padBottom(float padBottom) {
      this.padBottom = Value.Fixed.valueOf(padBottom);
      return this;
   }

   public Container padRight(float padRight) {
      this.padRight = Value.Fixed.valueOf(padRight);
      return this;
   }

   public Container fill() {
      this.fillX = 1.0F;
      this.fillY = 1.0F;
      return this;
   }

   public Container fillX() {
      this.fillX = 1.0F;
      return this;
   }

   public Container fillY() {
      this.fillY = 1.0F;
      return this;
   }

   public Container fill(float x, float y) {
      this.fillX = x;
      this.fillY = y;
      return this;
   }

   public Container fill(boolean x, boolean y) {
      this.fillX = x ? 1.0F : 0.0F;
      this.fillY = y ? 1.0F : 0.0F;
      return this;
   }

   public Container fill(boolean fill) {
      this.fillX = fill ? 1.0F : 0.0F;
      this.fillY = fill ? 1.0F : 0.0F;
      return this;
   }

   public Container align(int align) {
      this.align = align;
      return this;
   }

   public Container center() {
      this.align = 1;
      return this;
   }

   public Container top() {
      this.align |= 2;
      this.align &= -5;
      return this;
   }

   public Container left() {
      this.align |= 8;
      this.align &= -17;
      return this;
   }

   public Container bottom() {
      this.align |= 4;
      this.align &= -3;
      return this;
   }

   public Container right() {
      this.align |= 16;
      this.align &= -9;
      return this;
   }

   @Override
   public float getMinWidth() {
      return this.minWidth.get(this.actor) + this.padLeft.get(this) + this.padRight.get(this);
   }

   public Value getMinHeightValue() {
      return this.minHeight;
   }

   @Override
   public float getMinHeight() {
      return this.minHeight.get(this.actor) + this.padTop.get(this) + this.padBottom.get(this);
   }

   public Value getPrefWidthValue() {
      return this.prefWidth;
   }

   @Override
   public float getPrefWidth() {
      float v = this.prefWidth.get(this.actor);
      if (this.background != null) {
         v = Math.max(v, this.background.getMinWidth());
      }

      return Math.max(this.getMinWidth(), v + this.padLeft.get(this) + this.padRight.get(this));
   }

   public Value getPrefHeightValue() {
      return this.prefHeight;
   }

   @Override
   public float getPrefHeight() {
      float v = this.prefHeight.get(this.actor);
      if (this.background != null) {
         v = Math.max(v, this.background.getMinHeight());
      }

      return Math.max(this.getMinHeight(), v + this.padTop.get(this) + this.padBottom.get(this));
   }

   public Value getMaxWidthValue() {
      return this.maxWidth;
   }

   @Override
   public float getMaxWidth() {
      float v = this.maxWidth.get(this.actor);
      if (v > 0.0F) {
         v += this.padLeft.get(this) + this.padRight.get(this);
      }

      return v;
   }

   public Value getMaxHeightValue() {
      return this.maxHeight;
   }

   @Override
   public float getMaxHeight() {
      float v = this.maxHeight.get(this.actor);
      if (v > 0.0F) {
         v += this.padTop.get(this) + this.padBottom.get(this);
      }

      return v;
   }

   public Value getPadTopValue() {
      return this.padTop;
   }

   public float getPadTop() {
      return this.padTop.get(this);
   }

   public Value getPadLeftValue() {
      return this.padLeft;
   }

   public float getPadLeft() {
      return this.padLeft.get(this);
   }

   public Value getPadBottomValue() {
      return this.padBottom;
   }

   public float getPadBottom() {
      return this.padBottom.get(this);
   }

   public Value getPadRightValue() {
      return this.padRight;
   }

   public float getPadRight() {
      return this.padRight.get(this);
   }

   public float getPadX() {
      return this.padLeft.get(this) + this.padRight.get(this);
   }

   public float getPadY() {
      return this.padTop.get(this) + this.padBottom.get(this);
   }

   public float getFillX() {
      return this.fillX;
   }

   public float getFillY() {
      return this.fillY;
   }

   public int getAlign() {
      return this.align;
   }

   public void setRound(boolean round) {
      this.round = round;
   }

   public Container clip() {
      this.setClip(true);
      return this;
   }

   public Container clip(boolean enabled) {
      this.setClip(enabled);
      return this;
   }

   public void setClip(boolean enabled) {
      this.clip = enabled;
      this.setTransform(enabled);
      this.invalidate();
   }

   public boolean getClip() {
      return this.clip;
   }

   @Null
   @Override
   public Actor hit(float x, float y, boolean touchable) {
      if (this.clip) {
         if (touchable && this.getTouchable() == Touchable.disabled) {
            return null;
         }

         if (x < 0.0F || x >= this.getWidth() || y < 0.0F || y >= this.getHeight()) {
            return null;
         }
      }

      return super.hit(x, y, touchable);
   }

   @Override
   public void drawDebug(ShapeRenderer shapes) {
      this.validate();
      if (this.isTransform()) {
         this.applyTransform(shapes, this.computeTransform());
         if (this.clip) {
            shapes.flush();
            float padLeft = this.padLeft.get(this);
            float padBottom = this.padBottom.get(this);
            boolean draw = this.background == null
               ? this.clipBegin(0.0F, 0.0F, this.getWidth(), this.getHeight())
               : this.clipBegin(padLeft, padBottom, this.getWidth() - padLeft - this.padRight.get(this), this.getHeight() - padBottom - this.padTop.get(this));
            if (draw) {
               this.drawDebugChildren(shapes);
               this.clipEnd();
            }
         } else {
            this.drawDebugChildren(shapes);
         }

         this.resetTransform(shapes);
      } else {
         super.drawDebug(shapes);
      }
   }
}
