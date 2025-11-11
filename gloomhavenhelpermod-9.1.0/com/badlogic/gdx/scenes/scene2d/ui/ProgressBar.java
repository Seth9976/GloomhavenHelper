package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pools;

public class ProgressBar extends Widget implements Disableable {
   private ProgressBar.ProgressBarStyle style;
   float min;
   float max;
   float stepSize;
   private float value;
   private float animateFromValue;
   float position;
   final boolean vertical;
   private float animateDuration;
   private float animateTime;
   private Interpolation animateInterpolation = Interpolation.linear;
   private Interpolation visualInterpolation = Interpolation.linear;
   boolean disabled;
   private boolean round = true;
   private boolean programmaticChangeEvents = true;

   public ProgressBar(float min, float max, float stepSize, boolean vertical, Skin skin) {
      this(
         min,
         max,
         stepSize,
         vertical,
         (ProgressBar.ProgressBarStyle)skin.get("default-" + (vertical ? "vertical" : "horizontal"), ProgressBar.ProgressBarStyle.class)
      );
   }

   public ProgressBar(float min, float max, float stepSize, boolean vertical, Skin skin, String styleName) {
      this(min, max, stepSize, vertical, (ProgressBar.ProgressBarStyle)skin.get(styleName, ProgressBar.ProgressBarStyle.class));
   }

   public ProgressBar(float min, float max, float stepSize, boolean vertical, ProgressBar.ProgressBarStyle style) {
      if (min > max) {
         throw new IllegalArgumentException("max must be > min. min,max: " + min + ", " + max);
      } else if (stepSize <= 0.0F) {
         throw new IllegalArgumentException("stepSize must be > 0: " + stepSize);
      } else {
         this.setStyle(style);
         this.min = min;
         this.max = max;
         this.stepSize = stepSize;
         this.vertical = vertical;
         this.value = min;
         this.setSize(this.getPrefWidth(), this.getPrefHeight());
      }
   }

   public void setStyle(ProgressBar.ProgressBarStyle style) {
      if (style == null) {
         throw new IllegalArgumentException("style cannot be null.");
      } else {
         this.style = style;
         this.invalidateHierarchy();
      }
   }

   public ProgressBar.ProgressBarStyle getStyle() {
      return this.style;
   }

   @Override
   public void act(float delta) {
      super.act(delta);
      if (this.animateTime > 0.0F) {
         this.animateTime -= delta;
         Stage stage = this.getStage();
         if (stage != null && stage.getActionsRequestRendering()) {
            Gdx.graphics.requestRendering();
         }
      }
   }

   @Override
   public void draw(Batch batch, float parentAlpha) {
      ProgressBar.ProgressBarStyle style = this.style;
      boolean disabled = this.disabled;
      Drawable knob = style.knob;
      Drawable currentKnob = this.getKnobDrawable();
      Drawable bg = this.getBackgroundDrawable();
      Drawable knobBefore = this.getKnobBeforeDrawable();
      Drawable knobAfter = this.getKnobAfterDrawable();
      Color color = this.getColor();
      float x = this.getX();
      float y = this.getY();
      float width = this.getWidth();
      float height = this.getHeight();
      float knobHeight = knob == null ? 0.0F : knob.getMinHeight();
      float knobWidth = knob == null ? 0.0F : knob.getMinWidth();
      float percent = this.getVisualPercent();
      batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
      if (this.vertical) {
         float positionHeight = height;
         float bgTopHeight = 0.0F;
         float bgBottomHeight = 0.0F;
         if (bg != null) {
            if (this.round) {
               bg.draw(batch, Math.round(x + (width - bg.getMinWidth()) * 0.5F), y, Math.round(bg.getMinWidth()), height);
            } else {
               bg.draw(batch, x + (width - bg.getMinWidth()) * 0.5F, y, bg.getMinWidth(), height);
            }

            bgTopHeight = bg.getTopHeight();
            bgBottomHeight = bg.getBottomHeight();
            positionHeight = height - (bgTopHeight + bgBottomHeight);
         }

         float knobHeightHalf = 0.0F;
         if (knob == null) {
            knobHeightHalf = knobBefore == null ? 0.0F : knobBefore.getMinHeight() * 0.5F;
            this.position = (positionHeight - knobHeightHalf) * percent;
            this.position = Math.min(positionHeight - knobHeightHalf, this.position);
         } else {
            knobHeightHalf = knobHeight * 0.5F;
            this.position = (positionHeight - knobHeight) * percent;
            this.position = Math.min(positionHeight - knobHeight, this.position) + bgBottomHeight;
         }

         this.position = Math.max(bgBottomHeight, this.position);
         if (knobBefore != null) {
            if (this.round) {
               knobBefore.draw(
                  batch,
                  Math.round(x + (width - knobBefore.getMinWidth()) * 0.5F),
                  Math.round(y + bgTopHeight),
                  Math.round(knobBefore.getMinWidth()),
                  Math.round(this.position + knobHeightHalf)
               );
            } else {
               knobBefore.draw(batch, x + (width - knobBefore.getMinWidth()) * 0.5F, y + bgTopHeight, knobBefore.getMinWidth(), this.position + knobHeightHalf);
            }
         }

         if (knobAfter != null) {
            if (this.round) {
               knobAfter.draw(
                  batch,
                  Math.round(x + (width - knobAfter.getMinWidth()) * 0.5F),
                  Math.round(y + this.position + knobHeightHalf),
                  Math.round(knobAfter.getMinWidth()),
                  Math.round(height - this.position - knobHeightHalf - bgBottomHeight)
               );
            } else {
               knobAfter.draw(
                  batch,
                  x + (width - knobAfter.getMinWidth()) * 0.5F,
                  y + this.position + knobHeightHalf,
                  knobAfter.getMinWidth(),
                  height - this.position - knobHeightHalf - bgBottomHeight
               );
            }
         }

         if (currentKnob != null) {
            float w = currentKnob.getMinWidth();
            float h = currentKnob.getMinHeight();
            x += (width - w) * 0.5F;
            y += (knobHeight - h) * 0.5F + this.position;
            if (this.round) {
               x = Math.round(x);
               y = Math.round(y);
               w = Math.round(w);
               h = Math.round(h);
            }

            currentKnob.draw(batch, x, y, w, h);
         }
      } else {
         float positionWidth = width;
         float bgLeftWidth = 0.0F;
         float bgRightWidth = 0.0F;
         if (bg != null) {
            if (this.round) {
               bg.draw(batch, x, Math.round(y + (height - bg.getMinHeight()) * 0.5F), width, Math.round(bg.getMinHeight()));
            } else {
               bg.draw(batch, x, y + (height - bg.getMinHeight()) * 0.5F, width, bg.getMinHeight());
            }

            bgLeftWidth = bg.getLeftWidth();
            bgRightWidth = bg.getRightWidth();
            positionWidth = width - (bgLeftWidth + bgRightWidth);
         }

         float knobWidthHalf = 0.0F;
         if (knob == null) {
            knobWidthHalf = knobBefore == null ? 0.0F : knobBefore.getMinWidth() * 0.5F;
            this.position = (positionWidth - knobWidthHalf) * percent;
            this.position = Math.min(positionWidth - knobWidthHalf, this.position);
         } else {
            knobWidthHalf = knobWidth * 0.5F;
            this.position = (positionWidth - knobWidth) * percent;
            this.position = Math.min(positionWidth - knobWidth, this.position) + bgLeftWidth;
         }

         this.position = Math.max(bgLeftWidth, this.position);
         if (knobBefore != null) {
            if (this.round) {
               knobBefore.draw(
                  batch,
                  Math.round(x + bgLeftWidth),
                  Math.round(y + (height - knobBefore.getMinHeight()) * 0.5F),
                  Math.round(this.position + knobWidthHalf),
                  Math.round(knobBefore.getMinHeight())
               );
            } else {
               knobBefore.draw(
                  batch, x + bgLeftWidth, y + (height - knobBefore.getMinHeight()) * 0.5F, this.position + knobWidthHalf, knobBefore.getMinHeight()
               );
            }
         }

         if (knobAfter != null) {
            if (this.round) {
               knobAfter.draw(
                  batch,
                  Math.round(x + this.position + knobWidthHalf),
                  Math.round(y + (height - knobAfter.getMinHeight()) * 0.5F),
                  Math.round(width - this.position - knobWidthHalf - bgRightWidth),
                  Math.round(knobAfter.getMinHeight())
               );
            } else {
               knobAfter.draw(
                  batch,
                  x + this.position + knobWidthHalf,
                  y + (height - knobAfter.getMinHeight()) * 0.5F,
                  width - this.position - knobWidthHalf - bgRightWidth,
                  knobAfter.getMinHeight()
               );
            }
         }

         if (currentKnob != null) {
            float w = currentKnob.getMinWidth();
            float h = currentKnob.getMinHeight();
            x += (knobWidth - w) * 0.5F + this.position;
            y += (height - h) * 0.5F;
            if (this.round) {
               x = Math.round(x);
               y = Math.round(y);
               w = Math.round(w);
               h = Math.round(h);
            }

            currentKnob.draw(batch, x, y, w, h);
         }
      }
   }

   public float getValue() {
      return this.value;
   }

   public float getVisualValue() {
      return this.animateTime > 0.0F
         ? this.animateInterpolation.apply(this.animateFromValue, this.value, 1.0F - this.animateTime / this.animateDuration)
         : this.value;
   }

   public void updateVisualValue() {
      this.animateTime = 0.0F;
   }

   public float getPercent() {
      return this.min == this.max ? 0.0F : (this.value - this.min) / (this.max - this.min);
   }

   public float getVisualPercent() {
      return this.min == this.max ? 0.0F : this.visualInterpolation.apply((this.getVisualValue() - this.min) / (this.max - this.min));
   }

   @Null
   protected Drawable getBackgroundDrawable() {
      return this.disabled && this.style.disabledBackground != null ? this.style.disabledBackground : this.style.background;
   }

   @Null
   protected Drawable getKnobDrawable() {
      return this.disabled && this.style.disabledKnob != null ? this.style.disabledKnob : this.style.knob;
   }

   protected Drawable getKnobBeforeDrawable() {
      return this.disabled && this.style.disabledKnobBefore != null ? this.style.disabledKnobBefore : this.style.knobBefore;
   }

   protected Drawable getKnobAfterDrawable() {
      return this.disabled && this.style.disabledKnobAfter != null ? this.style.disabledKnobAfter : this.style.knobAfter;
   }

   protected float getKnobPosition() {
      return this.position;
   }

   public boolean setValue(float value) {
      value = this.clamp(this.round(value));
      float oldValue = this.value;
      if (value == oldValue) {
         return false;
      } else {
         float oldVisualValue = this.getVisualValue();
         this.value = value;
         if (this.programmaticChangeEvents) {
            ChangeListener.ChangeEvent changeEvent = (ChangeListener.ChangeEvent)Pools.obtain(ChangeListener.ChangeEvent.class);
            boolean cancelled = this.fire(changeEvent);
            Pools.free(changeEvent);
            if (cancelled) {
               this.value = oldValue;
               return false;
            }
         }

         if (this.animateDuration > 0.0F) {
            this.animateFromValue = oldVisualValue;
            this.animateTime = this.animateDuration;
         }

         return true;
      }
   }

   protected float round(float value) {
      return Math.round(value / this.stepSize) * this.stepSize;
   }

   protected float clamp(float value) {
      return MathUtils.clamp(value, this.min, this.max);
   }

   public void setRange(float min, float max) {
      if (min > max) {
         throw new IllegalArgumentException("min must be <= max: " + min + " <= " + max);
      } else {
         this.min = min;
         this.max = max;
         if (this.value < min) {
            this.setValue(min);
         } else if (this.value > max) {
            this.setValue(max);
         }
      }
   }

   public void setStepSize(float stepSize) {
      if (stepSize <= 0.0F) {
         throw new IllegalArgumentException("steps must be > 0: " + stepSize);
      } else {
         this.stepSize = stepSize;
      }
   }

   @Override
   public float getPrefWidth() {
      if (this.vertical) {
         Drawable knob = this.style.knob;
         Drawable bg = this.getBackgroundDrawable();
         return Math.max(knob == null ? 0.0F : knob.getMinWidth(), bg == null ? 0.0F : bg.getMinWidth());
      } else {
         return 140.0F;
      }
   }

   @Override
   public float getPrefHeight() {
      if (this.vertical) {
         return 140.0F;
      } else {
         Drawable knob = this.style.knob;
         Drawable bg = this.getBackgroundDrawable();
         return Math.max(knob == null ? 0.0F : knob.getMinHeight(), bg == null ? 0.0F : bg.getMinHeight());
      }
   }

   public float getMinValue() {
      return this.min;
   }

   public float getMaxValue() {
      return this.max;
   }

   public float getStepSize() {
      return this.stepSize;
   }

   public void setAnimateDuration(float duration) {
      this.animateDuration = duration;
   }

   public void setAnimateInterpolation(Interpolation animateInterpolation) {
      if (animateInterpolation == null) {
         throw new IllegalArgumentException("animateInterpolation cannot be null.");
      } else {
         this.animateInterpolation = animateInterpolation;
      }
   }

   public void setVisualInterpolation(Interpolation interpolation) {
      this.visualInterpolation = interpolation;
   }

   public void setRound(boolean round) {
      this.round = round;
   }

   @Override
   public void setDisabled(boolean disabled) {
      this.disabled = disabled;
   }

   public boolean isAnimating() {
      return this.animateTime > 0.0F;
   }

   @Override
   public boolean isDisabled() {
      return this.disabled;
   }

   public boolean isVertical() {
      return this.vertical;
   }

   public void setProgrammaticChangeEvents(boolean programmaticChangeEvents) {
      this.programmaticChangeEvents = programmaticChangeEvents;
   }

   public static class ProgressBarStyle {
      @Null
      public Drawable background;
      @Null
      public Drawable disabledBackground;
      @Null
      public Drawable knob;
      @Null
      public Drawable disabledKnob;
      @Null
      public Drawable knobBefore;
      @Null
      public Drawable disabledKnobBefore;
      @Null
      public Drawable knobAfter;
      @Null
      public Drawable disabledKnobAfter;

      public ProgressBarStyle() {
      }

      public ProgressBarStyle(@Null Drawable background, @Null Drawable knob) {
         this.background = background;
         this.knob = knob;
      }

      public ProgressBarStyle(ProgressBar.ProgressBarStyle style) {
         this.background = style.background;
         this.disabledBackground = style.disabledBackground;
         this.knob = style.knob;
         this.disabledKnob = style.disabledKnob;
         this.knobBefore = style.knobBefore;
         this.disabledKnobBefore = style.disabledKnobBefore;
         this.knobAfter = style.knobAfter;
         this.disabledKnobAfter = style.disabledKnobAfter;
      }
   }
}
