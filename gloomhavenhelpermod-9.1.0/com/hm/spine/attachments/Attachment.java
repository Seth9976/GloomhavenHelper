package com.hm.spine.attachments;

public abstract class Attachment {
   final String name;

   public Attachment(String name) {
      if (name == null) {
         throw new IllegalArgumentException("name cannot be null.");
      } else {
         this.name = name;
      }
   }

   protected Attachment(Attachment other) {
      this.name = other.name;
   }

   public String getName() {
      return this.name;
   }

   @Override
   public String toString() {
      return this.name;
   }

   public abstract Attachment copy();
}
