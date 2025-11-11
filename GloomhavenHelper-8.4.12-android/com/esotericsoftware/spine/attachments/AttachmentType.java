package com.esotericsoftware.spine.attachments;

public enum AttachmentType {
    region,
    boundingbox,
    mesh,
    linkedmesh,
    path,
    point,
    clipping,
    sequence;

    public static final AttachmentType[] values;

    static {
        AttachmentType.values = (AttachmentType[])AttachmentType.$VALUES.clone();
    }
}

