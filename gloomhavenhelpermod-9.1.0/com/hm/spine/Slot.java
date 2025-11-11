package com.hm.spine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Null;
import com.hm.spine.attachments.Attachment;
import com.hm.spine.attachments.VertexAttachment;

public class Slot {
   final SlotData data;
   final Bone bone;
   final Color color = new Color();
   @Null
   final Color darkColor;
   FloatArray deform = new FloatArray();
   @Null
   Attachment attachment;
   int sequenceIndex;
   int attachmentState;

   public Slot(SlotData data, Bone bone) {
      if (data == null) {
         throw new IllegalArgumentException("data cannot be null.");
      } else if (bone == null) {
         throw new IllegalArgumentException("bone cannot be null.");
      } else {
         this.data = data;
         this.bone = bone;
         this.darkColor = data.darkColor == null ? null : new Color();
         this.setToSetupPose();
      }
   }

   public Slot(Slot slot, Bone bone) {
      if (slot == null) {
         throw new IllegalArgumentException("slot cannot be null.");
      } else if (bone == null) {
         throw new IllegalArgumentException("bone cannot be null.");
      } else {
         this.data = slot.data;
         this.bone = bone;
         this.color.set(slot.color);
         this.darkColor = slot.darkColor == null ? null : new Color(slot.darkColor);
         this.attachment = slot.attachment;
         this.sequenceIndex = slot.sequenceIndex;
         this.deform.addAll(slot.deform);
      }
   }

   public SlotData getData() {
      return this.data;
   }

   public Bone getBone() {
      return this.bone;
   }

   public Skeleton getSkeleton() {
      return this.bone.skeleton;
   }

   public Color getColor() {
      return this.color;
   }

   @Null
   public Color getDarkColor() {
      return this.darkColor;
   }

   @Null
   public Attachment getAttachment() {
      return this.attachment;
   }

   public void setAttachment(@Null Attachment attachment) {
      if (this.attachment != attachment) {
         if (!(attachment instanceof VertexAttachment)
            || !(this.attachment instanceof VertexAttachment)
            || ((VertexAttachment)attachment).getTimelineAttachment() != ((VertexAttachment)this.attachment).getTimelineAttachment()) {
            this.deform.clear();
         }

         this.attachment = attachment;
         this.sequenceIndex = -1;
      }
   }

   public int getSequenceIndex() {
      return this.sequenceIndex;
   }

   public void setSequenceIndex(int sequenceIndex) {
      this.sequenceIndex = sequenceIndex;
   }

   public FloatArray getDeform() {
      return this.deform;
   }

   public void setDeform(FloatArray deform) {
      if (deform == null) {
         throw new IllegalArgumentException("deform cannot be null.");
      } else {
         this.deform = deform;
      }
   }

   public void setToSetupPose() {
      this.color.set(this.data.color);
      if (this.darkColor != null) {
         this.darkColor.set(this.data.darkColor);
      }

      if (this.data.attachmentName == null) {
         this.setAttachment(null);
      } else {
         this.attachment = null;
         this.setAttachment(this.bone.skeleton.getAttachment(this.data.index, this.data.attachmentName));
      }
   }

   @Override
   public String toString() {
      return this.data.name;
   }
}
