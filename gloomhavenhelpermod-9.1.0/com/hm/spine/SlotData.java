package com.hm.spine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Null;

public class SlotData {
   final int index;
   final String name;
   final BoneData boneData;
   final Color color = new Color(1.0F, 1.0F, 1.0F, 1.0F);
   @Null
   Color darkColor;
   @Null
   String attachmentName;
   BlendMode blendMode;

   public SlotData(int index, String name, BoneData boneData) {
      if (index < 0) {
         throw new IllegalArgumentException("index must be >= 0.");
      } else if (name == null) {
         throw new IllegalArgumentException("name cannot be null.");
      } else if (boneData == null) {
         throw new IllegalArgumentException("boneData cannot be null.");
      } else {
         this.index = index;
         this.name = name;
         this.boneData = boneData;
      }
   }

   public int getIndex() {
      return this.index;
   }

   public String getName() {
      return this.name;
   }

   public BoneData getBoneData() {
      return this.boneData;
   }

   public Color getColor() {
      return this.color;
   }

   @Null
   public Color getDarkColor() {
      return this.darkColor;
   }

   public void setDarkColor(@Null Color darkColor) {
      this.darkColor = darkColor;
   }

   public void setAttachmentName(@Null String attachmentName) {
      this.attachmentName = attachmentName;
   }

   @Null
   public String getAttachmentName() {
      return this.attachmentName;
   }

   public BlendMode getBlendMode() {
      return this.blendMode;
   }

   public void setBlendMode(BlendMode blendMode) {
      if (blendMode == null) {
         throw new IllegalArgumentException("blendMode cannot be null.");
      } else {
         this.blendMode = blendMode;
      }
   }

   @Override
   public String toString() {
      return this.name;
   }
}
