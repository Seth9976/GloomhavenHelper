package com.hm.spine;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.hm.spine.attachments.AtlasAttachmentLoader;
import com.hm.spine.attachments.AttachmentLoader;
import java.io.InputStream;

public abstract class SkeletonLoader {
   final AttachmentLoader attachmentLoader;
   float scale = 1.0F;
   final Array linkedMeshes = new Array();

   public SkeletonLoader(TextureAtlas atlas) {
      this.attachmentLoader = new AtlasAttachmentLoader(atlas);
   }

   public SkeletonLoader(AttachmentLoader attachmentLoader) {
      if (attachmentLoader == null) {
         throw new IllegalArgumentException("attachmentLoader cannot be null.");
      } else {
         this.attachmentLoader = attachmentLoader;
      }
   }

   public float getScale() {
      return this.scale;
   }

   public void setScale(float scale) {
      if (scale == 0.0F) {
         throw new IllegalArgumentException("scale cannot be 0.");
      } else {
         this.scale = scale;
      }
   }

   public abstract SkeletonData readSkeletonData(FileHandle var1);

   public abstract SkeletonData readSkeletonData(InputStream var1);
}
