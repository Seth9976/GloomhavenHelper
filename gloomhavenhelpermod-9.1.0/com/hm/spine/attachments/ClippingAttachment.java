package com.hm.spine.attachments;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Null;
import com.hm.spine.SlotData;

public class ClippingAttachment extends VertexAttachment {
   @Null
   SlotData endSlot;
   final Color color = new Color(0.2275F, 0.2275F, 0.8078F, 1.0F);

   public ClippingAttachment(String name) {
      super(name);
   }

   protected ClippingAttachment(ClippingAttachment other) {
      super(other);
      this.endSlot = other.endSlot;
      this.color.set(other.color);
   }

   @Null
   public SlotData getEndSlot() {
      return this.endSlot;
   }

   public void setEndSlot(@Null SlotData endSlot) {
      this.endSlot = endSlot;
   }

   public Color getColor() {
      return this.color;
   }

   public ClippingAttachment copy() {
      return new ClippingAttachment(this);
   }
}
