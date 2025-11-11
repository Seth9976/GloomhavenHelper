package com.esotericsoftware.spine.attachments;

import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.spine.Skin;

public interface AttachmentLoader {
    @Null
    BoundingBoxAttachment newBoundingBoxAttachment(Skin arg1, String arg2);

    @Null
    ClippingAttachment newClippingAttachment(Skin arg1, String arg2);

    @Null
    MeshAttachment newMeshAttachment(Skin arg1, String arg2, String arg3, @Null Sequence arg4);

    @Null
    PathAttachment newPathAttachment(Skin arg1, String arg2);

    @Null
    PointAttachment newPointAttachment(Skin arg1, String arg2);

    @Null
    RegionAttachment newRegionAttachment(Skin arg1, String arg2, String arg3, @Null Sequence arg4);
}

