package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.math.MathUtils;

public class RotateToAction extends TemporalAction {
   private float start;
   private float end;
   private boolean useShortestDirection = false;

   public RotateToAction() {
   }

   public RotateToAction(boolean useShortestDirection) {
      this.useShortestDirection = useShortestDirection;
   }

   @Override
   protected void begin() {
      this.start = this.target.getRotation();
   }

   @Override
   protected void update(float percent) {
      float rotation;
      if (percent == 0.0F) {
         rotation = this.start;
      } else if (percent == 1.0F) {
         rotation = this.end;
      } else if (this.useShortestDirection) {
         rotation = MathUtils.lerpAngleDeg(this.start, this.end, percent);
      } else {
         rotation = this.start + (this.end - this.start) * percent;
      }

      this.target.setRotation(rotation);
   }

   public float getRotation() {
      return this.end;
   }

   public void setRotation(float rotation) {
      this.end = rotation;
   }

   public boolean isUseShortestDirection() {
      return this.useShortestDirection;
   }

   public void setUseShortestDirection(boolean useShortestDirection) {
      this.useShortestDirection = useShortestDirection;
   }
}
