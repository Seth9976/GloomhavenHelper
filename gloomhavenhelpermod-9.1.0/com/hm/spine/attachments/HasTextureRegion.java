package com.hm.spine.attachments;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Null;

public interface HasTextureRegion {
   String getPath();

   void setPath(String var1);

   TextureRegion getRegion();

   void setRegion(TextureRegion var1);

   void updateRegion();

   Color getColor();

   @Null
   Sequence getSequence();

   void setSequence(@Null Sequence var1);
}
