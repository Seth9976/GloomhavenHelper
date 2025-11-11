package com.hm.spine.attachments;

import com.badlogic.gdx.utils.Null;
import com.hm.spine.Skin;

public interface AttachmentLoader {
   @Null
   RegionAttachment newRegionAttachment(Skin var1, String var2, String var3, @Null Sequence var4);

   @Null
   MeshAttachment newMeshAttachment(Skin var1, String var2, String var3, @Null Sequence var4);

   @Null
   BoundingBoxAttachment newBoundingBoxAttachment(Skin var1, String var2);

   @Null
   ClippingAttachment newClippingAttachment(Skin var1, String var2);

   @Null
   PathAttachment newPathAttachment(Skin var1, String var2);

   @Null
   PointAttachment newPointAttachment(Skin var1, String var2);
}
