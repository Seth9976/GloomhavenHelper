package com.hm.spine;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.OrderedSet;
import com.hm.spine.attachments.Attachment;
import com.hm.spine.attachments.MeshAttachment;

public class Skin {
   final String name;
   final OrderedSet attachments = new OrderedSet();
   final Array bones = new Array(0);
   final Array constraints = new Array(0);
   private final Skin.SkinEntry lookup = new Skin.SkinEntry(0, "", null);

   public Skin(String name) {
      if (name == null) {
         throw new IllegalArgumentException("name cannot be null.");
      } else {
         this.name = name;
         this.attachments.orderedItems().ordered = false;
      }
   }

   public void setAttachment(int slotIndex, String name, Attachment attachment) {
      if (attachment == null) {
         throw new IllegalArgumentException("attachment cannot be null.");
      } else {
         Skin.SkinEntry entry = new Skin.SkinEntry(slotIndex, name, attachment);
         if (!this.attachments.add(entry)) {
            ((Skin.SkinEntry)this.attachments.get(entry)).attachment = attachment;
         }
      }
   }

   public void addSkin(Skin skin) {
      if (skin == null) {
         throw new IllegalArgumentException("skin cannot be null.");
      } else {
         for (BoneData data : skin.bones) {
            if (!this.bones.contains(data, true)) {
               this.bones.add(data);
            }
         }

         for (ConstraintData datax : skin.constraints) {
            if (!this.constraints.contains(datax, true)) {
               this.constraints.add(datax);
            }
         }

         for (Skin.SkinEntry entry : skin.attachments.orderedItems()) {
            this.setAttachment(entry.slotIndex, entry.name, entry.attachment);
         }
      }
   }

   public void copySkin(Skin skin) {
      if (skin == null) {
         throw new IllegalArgumentException("skin cannot be null.");
      } else {
         for (BoneData data : skin.bones) {
            if (!this.bones.contains(data, true)) {
               this.bones.add(data);
            }
         }

         for (ConstraintData datax : skin.constraints) {
            if (!this.constraints.contains(datax, true)) {
               this.constraints.add(datax);
            }
         }

         for (Skin.SkinEntry entry : skin.attachments.orderedItems()) {
            if (entry.attachment instanceof MeshAttachment) {
               this.setAttachment(entry.slotIndex, entry.name, ((MeshAttachment)entry.attachment).newLinkedMesh());
            } else {
               this.setAttachment(entry.slotIndex, entry.name, entry.attachment != null ? entry.attachment.copy() : null);
            }
         }
      }
   }

   @Null
   public Attachment getAttachment(int slotIndex, String name) {
      this.lookup.set(slotIndex, name);
      Skin.SkinEntry entry = (Skin.SkinEntry)this.attachments.get(this.lookup);
      return entry != null ? entry.attachment : null;
   }

   public void removeAttachment(int slotIndex, String name) {
      this.lookup.set(slotIndex, name);
      this.attachments.remove(this.lookup);
   }

   public Array getAttachments() {
      return this.attachments.orderedItems();
   }

   public void getAttachments(int slotIndex, Array attachments) {
      if (slotIndex < 0) {
         throw new IllegalArgumentException("slotIndex must be >= 0.");
      } else if (attachments == null) {
         throw new IllegalArgumentException("attachments cannot be null.");
      } else {
         for (Skin.SkinEntry entry : this.attachments.orderedItems()) {
            if (entry.slotIndex == slotIndex) {
               attachments.add(entry);
            }
         }
      }
   }

   public void clear() {
      this.attachments.clear(1024);
      this.bones.clear();
      this.constraints.clear();
   }

   public Array getBones() {
      return this.bones;
   }

   public Array getConstraints() {
      return this.constraints;
   }

   public String getName() {
      return this.name;
   }

   @Override
   public String toString() {
      return this.name;
   }

   void attachAll(Skeleton skeleton, Skin oldSkin) {
      Object[] slots = skeleton.slots.items;

      for (Skin.SkinEntry entry : oldSkin.attachments.orderedItems()) {
         int slotIndex = entry.slotIndex;
         Slot slot = (Slot)slots[slotIndex];
         if (slot.attachment == entry.attachment) {
            Attachment attachment = this.getAttachment(slotIndex, entry.name);
            if (attachment != null) {
               slot.setAttachment(attachment);
            }
         }
      }
   }

   public static class SkinEntry {
      int slotIndex;
      String name;
      @Null
      Attachment attachment;
      private int hashCode;

      SkinEntry(int slotIndex, String name, @Null Attachment attachment) {
         this.set(slotIndex, name);
         this.attachment = attachment;
      }

      void set(int slotIndex, String name) {
         if (slotIndex < 0) {
            throw new IllegalArgumentException("slotIndex must be >= 0.");
         } else if (name == null) {
            throw new IllegalArgumentException("name cannot be null.");
         } else {
            this.slotIndex = slotIndex;
            this.name = name;
            this.hashCode = name.hashCode() + slotIndex * 37;
         }
      }

      public int getSlotIndex() {
         return this.slotIndex;
      }

      public String getName() {
         return this.name;
      }

      public Attachment getAttachment() {
         return this.attachment;
      }

      @Override
      public int hashCode() {
         return this.hashCode;
      }

      @Override
      public boolean equals(Object object) {
         if (object == null) {
            return false;
         } else {
            Skin.SkinEntry other = (Skin.SkinEntry)object;
            return this.slotIndex != other.slotIndex ? false : this.name.equals(other.name);
         }
      }

      @Override
      public String toString() {
         return this.slotIndex + ":" + this.name;
      }
   }
}
