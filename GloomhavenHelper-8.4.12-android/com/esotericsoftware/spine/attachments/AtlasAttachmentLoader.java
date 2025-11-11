package com.esotericsoftware.spine.attachments;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.spine.Skin;

public class AtlasAttachmentLoader implements AttachmentLoader {
    private TextureAtlas atlas;

    public AtlasAttachmentLoader(TextureAtlas textureAtlas0) {
        if(textureAtlas0 == null) {
            throw new IllegalArgumentException("atlas cannot be null.");
        }
        this.atlas = textureAtlas0;
    }

    private void loadSequence(String s, String s1, Sequence sequence0) {
        TextureRegion[] arr_textureRegion = sequence0.getRegions();
        for(int v = 0; v < arr_textureRegion.length; ++v) {
            String s2 = sequence0.getPath(s1, v);
            arr_textureRegion[v] = this.atlas.findRegion(s2);
            if(arr_textureRegion[v] == null) {
                throw new RuntimeException("Region not found in atlas: " + s2 + " (sequence: " + s + ")");
            }
        }
    }

    @Override  // com.esotericsoftware.spine.attachments.AttachmentLoader
    public BoundingBoxAttachment newBoundingBoxAttachment(Skin skin0, String s) {
        return new BoundingBoxAttachment(s);
    }

    @Override  // com.esotericsoftware.spine.attachments.AttachmentLoader
    public ClippingAttachment newClippingAttachment(Skin skin0, String s) {
        return new ClippingAttachment(s);
    }

    @Override  // com.esotericsoftware.spine.attachments.AttachmentLoader
    public MeshAttachment newMeshAttachment(Skin skin0, String s, String s1, @Null Sequence sequence0) {
        MeshAttachment meshAttachment0 = new MeshAttachment(s);
        if(sequence0 != null) {
            this.loadSequence(s, s1, sequence0);
            return meshAttachment0;
        }
        AtlasRegion textureAtlas$AtlasRegion0 = this.atlas.findRegion(s1);
        if(textureAtlas$AtlasRegion0 == null) {
            throw new RuntimeException("Region not found in atlas: " + s1 + " (mesh attachment: " + s + ")");
        }
        meshAttachment0.setRegion(textureAtlas$AtlasRegion0);
        return meshAttachment0;
    }

    @Override  // com.esotericsoftware.spine.attachments.AttachmentLoader
    public PathAttachment newPathAttachment(Skin skin0, String s) {
        return new PathAttachment(s);
    }

    @Override  // com.esotericsoftware.spine.attachments.AttachmentLoader
    public PointAttachment newPointAttachment(Skin skin0, String s) {
        return new PointAttachment(s);
    }

    @Override  // com.esotericsoftware.spine.attachments.AttachmentLoader
    public RegionAttachment newRegionAttachment(Skin skin0, String s, String s1, @Null Sequence sequence0) {
        RegionAttachment regionAttachment0 = new RegionAttachment(s);
        if(sequence0 != null) {
            this.loadSequence(s, s1, sequence0);
            return regionAttachment0;
        }
        AtlasRegion textureAtlas$AtlasRegion0 = this.atlas.findRegion(s1);
        if(textureAtlas$AtlasRegion0 == null) {
            throw new RuntimeException("Region not found in atlas: " + s1 + " (region attachment: " + s + ")");
        }
        regionAttachment0.setRegion(textureAtlas$AtlasRegion0);
        return regionAttachment0;
    }
}

