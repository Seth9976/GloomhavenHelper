package com.hm.spine.attachments;

import com.badlogic.gdx.graphics.Color;

public class BoundingBoxAttachment extends VertexAttachment {
   final Color color = new Color(0.38F, 0.94F, 0.0F, 1.0F);

   public BoundingBoxAttachment(String name) {
      super(name);
   }

   protected BoundingBoxAttachment(BoundingBoxAttachment other) {
      super(other);
      this.color.set(other.color);
   }

   public Color getColor() {
      return this.color;
   }

   public BoundingBoxAttachment copy() {
      return new BoundingBoxAttachment(this);
   }
}
