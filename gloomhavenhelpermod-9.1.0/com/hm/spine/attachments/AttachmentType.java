package com.hm.spine.attachments;

public enum AttachmentType {
   region,
   boundingbox,
   mesh,
   linkedmesh,
   path,
   point,
   clipping,
   sequence;

   public static final AttachmentType[] values = values();
}
