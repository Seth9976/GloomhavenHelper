package com.hm.spine.attachments;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hm.spine.Slot;
import com.hm.spine.utils.SpineUtils;

public class Sequence {
   private static int nextID;
   private final int id = nextID();
   private final TextureRegion[] regions;
   private int start;
   private int digits;
   private int setupIndex;

   public Sequence(int count) {
      this.regions = new TextureRegion[count];
   }

   protected Sequence(Sequence other) {
      this.regions = new TextureRegion[other.regions.length];
      SpineUtils.arraycopy(other.regions, 0, this.regions, 0, this.regions.length);
      this.start = other.start;
      this.digits = other.digits;
      this.setupIndex = other.setupIndex;
   }

   public void apply(Slot slot, HasTextureRegion attachment) {
      int index = slot.getSequenceIndex();
      if (index == -1) {
         index = this.setupIndex;
      }

      if (index >= this.regions.length) {
         index = this.regions.length - 1;
      }

      TextureRegion region = this.regions[index];
      if (attachment.getRegion() != region) {
         attachment.setRegion(region);
         attachment.updateRegion();
      }
   }

   public String getPath(String basePath, int index) {
      StringBuilder buffer = new StringBuilder(basePath.length() + this.digits);
      buffer.append(basePath);
      String frame = Integer.toString(this.start + index);

      for (int i = this.digits - frame.length(); i > 0; i--) {
         buffer.append('0');
      }

      buffer.append(frame);
      return buffer.toString();
   }

   public int getStart() {
      return this.start;
   }

   public void setStart(int start) {
      this.start = start;
   }

   public int getDigits() {
      return this.digits;
   }

   public void setDigits(int digits) {
      this.digits = digits;
   }

   public int getSetupIndex() {
      return this.setupIndex;
   }

   public void setSetupIndex(int index) {
      this.setupIndex = index;
   }

   public TextureRegion[] getRegions() {
      return this.regions;
   }

   public int getId() {
      return this.id;
   }

   private static synchronized int nextID() {
      return nextID++;
   }

   public static enum SequenceMode {
      hold,
      once,
      loop,
      pingpong,
      onceReverse,
      loopReverse,
      pingpongReverse;

      public static final Sequence.SequenceMode[] values = values();
   }
}
