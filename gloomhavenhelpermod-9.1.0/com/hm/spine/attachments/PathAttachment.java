package com.hm.spine.attachments;

import com.badlogic.gdx.graphics.Color;
import com.hm.spine.utils.SpineUtils;

public class PathAttachment extends VertexAttachment {
   float[] lengths;
   boolean closed;
   boolean constantSpeed;
   final Color color = new Color(1.0F, 0.5F, 0.0F, 1.0F);

   public PathAttachment(String name) {
      super(name);
   }

   protected PathAttachment(PathAttachment other) {
      super(other);
      this.lengths = new float[other.lengths.length];
      SpineUtils.arraycopy(other.lengths, 0, this.lengths, 0, this.lengths.length);
      this.closed = other.closed;
      this.constantSpeed = other.constantSpeed;
      this.color.set(other.color);
   }

   public boolean getClosed() {
      return this.closed;
   }

   public void setClosed(boolean closed) {
      this.closed = closed;
   }

   public boolean getConstantSpeed() {
      return this.constantSpeed;
   }

   public void setConstantSpeed(boolean constantSpeed) {
      this.constantSpeed = constantSpeed;
   }

   public float[] getLengths() {
      return this.lengths;
   }

   public void setLengths(float[] lengths) {
      this.lengths = lengths;
   }

   public Color getColor() {
      return this.color;
   }

   public PathAttachment copy() {
      return new PathAttachment(this);
   }
}
