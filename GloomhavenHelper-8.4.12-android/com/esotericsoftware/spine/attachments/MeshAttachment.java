package com.esotericsoftware.spine.attachments;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.spine.Slot;
import com.esotericsoftware.spine.utils.SpineUtils;

public class MeshAttachment extends VertexAttachment implements HasTextureRegion {
    private final Color color;
    @Null
    private short[] edges;
    private float height;
    private int hullLength;
    @Null
    private MeshAttachment parentMesh;
    private String path;
    private TextureRegion region;
    private float[] regionUVs;
    @Null
    private Sequence sequence;
    private short[] triangles;
    private float[] uvs;
    private float width;

    protected MeshAttachment(MeshAttachment meshAttachment0) {
        super(meshAttachment0);
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        if(this.parentMesh != null) {
            throw new IllegalArgumentException("Use newLinkedMesh to copy a linked mesh.");
        }
        this.region = meshAttachment0.region;
        this.path = meshAttachment0.path;
        this.color.set(meshAttachment0.color);
        this.regionUVs = new float[meshAttachment0.regionUVs.length];
        SpineUtils.arraycopy(meshAttachment0.regionUVs, 0, this.regionUVs, 0, this.regionUVs.length);
        this.uvs = new float[meshAttachment0.uvs.length];
        SpineUtils.arraycopy(meshAttachment0.uvs, 0, this.uvs, 0, this.uvs.length);
        this.triangles = new short[meshAttachment0.triangles.length];
        SpineUtils.arraycopy(meshAttachment0.triangles, 0, this.triangles, 0, this.triangles.length);
        this.hullLength = meshAttachment0.hullLength;
        this.sequence = meshAttachment0.sequence == null ? null : new Sequence(meshAttachment0.sequence);
        short[] arr_v = meshAttachment0.edges;
        if(arr_v != null) {
            this.edges = new short[arr_v.length];
            SpineUtils.arraycopy(meshAttachment0.edges, 0, this.edges, 0, this.edges.length);
        }
        this.width = meshAttachment0.width;
        this.height = meshAttachment0.height;
    }

    public MeshAttachment(String s) {
        super(s);
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override  // com.esotericsoftware.spine.attachments.VertexAttachment
    public void computeWorldVertices(Slot slot0, int v, int v1, float[] arr_f, int v2, int v3) {
        Sequence sequence0 = this.sequence;
        if(sequence0 != null) {
            sequence0.apply(slot0, this);
        }
        super.computeWorldVertices(slot0, v, v1, arr_f, v2, v3);
    }

    @Override  // com.esotericsoftware.spine.attachments.Attachment
    public Attachment copy() {
        return this.copy();
    }

    public MeshAttachment copy() {
        return this.parentMesh == null ? new MeshAttachment(this) : this.newLinkedMesh();
    }

    @Override  // com.esotericsoftware.spine.attachments.HasTextureRegion
    public Color getColor() {
        return this.color;
    }

    @Null
    public short[] getEdges() {
        return this.edges;
    }

    public float getHeight() {
        return this.height;
    }

    public int getHullLength() {
        return this.hullLength;
    }

    @Null
    public MeshAttachment getParentMesh() {
        return this.parentMesh;
    }

    @Override  // com.esotericsoftware.spine.attachments.HasTextureRegion
    public String getPath() {
        return this.path;
    }

    @Override  // com.esotericsoftware.spine.attachments.HasTextureRegion
    @Null
    public TextureRegion getRegion() {
        return this.region;
    }

    public float[] getRegionUVs() {
        return this.regionUVs;
    }

    @Override  // com.esotericsoftware.spine.attachments.HasTextureRegion
    @Null
    public Sequence getSequence() {
        return this.sequence;
    }

    public short[] getTriangles() {
        return this.triangles;
    }

    public float[] getUVs() {
        return this.uvs;
    }

    public float getWidth() {
        return this.width;
    }

    public MeshAttachment newLinkedMesh() {
        MeshAttachment meshAttachment0 = new MeshAttachment(this.name);
        meshAttachment0.timelineAttachment = this.timelineAttachment;
        meshAttachment0.region = this.region;
        meshAttachment0.path = this.path;
        meshAttachment0.color.set(this.color);
        meshAttachment0.setParentMesh((this.parentMesh == null ? this : this.parentMesh));
        if(meshAttachment0.getRegion() != null) {
            meshAttachment0.updateRegion();
        }
        return meshAttachment0;
    }

    public void setEdges(short[] arr_v) {
        this.edges = arr_v;
    }

    public void setHeight(float f) {
        this.height = f;
    }

    public void setHullLength(int v) {
        this.hullLength = v;
    }

    public void setParentMesh(@Null MeshAttachment meshAttachment0) {
        this.parentMesh = meshAttachment0;
        if(meshAttachment0 != null) {
            this.bones = meshAttachment0.bones;
            this.vertices = meshAttachment0.vertices;
            this.regionUVs = meshAttachment0.regionUVs;
            this.triangles = meshAttachment0.triangles;
            this.hullLength = meshAttachment0.hullLength;
            this.worldVerticesLength = meshAttachment0.worldVerticesLength;
            this.edges = meshAttachment0.edges;
            this.width = meshAttachment0.width;
            this.height = meshAttachment0.height;
        }
    }

    @Override  // com.esotericsoftware.spine.attachments.HasTextureRegion
    public void setPath(String s) {
        this.path = s;
    }

    @Override  // com.esotericsoftware.spine.attachments.HasTextureRegion
    public void setRegion(TextureRegion textureRegion0) {
        if(textureRegion0 == null) {
            throw new IllegalArgumentException("region cannot be null.");
        }
        this.region = textureRegion0;
    }

    public void setRegionUVs(float[] arr_f) {
        this.regionUVs = arr_f;
    }

    @Override  // com.esotericsoftware.spine.attachments.HasTextureRegion
    public void setSequence(@Null Sequence sequence0) {
        this.sequence = sequence0;
    }

    public void setTriangles(short[] arr_v) {
        this.triangles = arr_v;
    }

    public void setUVs(float[] arr_f) {
        this.uvs = arr_f;
    }

    public void setWidth(float f) {
        this.width = f;
    }

    @Override  // com.esotericsoftware.spine.attachments.HasTextureRegion
    public void updateRegion() {
        float f19;
        float f18;
        float f17;
        float[] arr_f = this.regionUVs;
        if(this.uvs == null || this.uvs.length != arr_f.length) {
            this.uvs = new float[arr_f.length];
        }
        float[] arr_f1 = this.uvs;
        TextureRegion textureRegion0 = this.region;
        int v = 0;
        float f = 1.0f;
        if(textureRegion0 instanceof AtlasRegion) {
            float f1 = textureRegion0.getU();
            float f2 = this.region.getV();
            AtlasRegion textureAtlas$AtlasRegion0 = (AtlasRegion)this.region;
            float f3 = (float)textureAtlas$AtlasRegion0.getTexture().getWidth();
            float f4 = (float)textureAtlas$AtlasRegion0.getTexture().getHeight();
            switch(textureAtlas$AtlasRegion0.degrees) {
                case 90: {
                    float f5 = f1 - (((float)textureAtlas$AtlasRegion0.originalHeight) - textureAtlas$AtlasRegion0.offsetY - ((float)textureAtlas$AtlasRegion0.packedWidth)) / f3;
                    float f6 = f2 - (((float)textureAtlas$AtlasRegion0.originalWidth) - textureAtlas$AtlasRegion0.offsetX - ((float)textureAtlas$AtlasRegion0.packedHeight)) / f4;
                    float f7 = ((float)textureAtlas$AtlasRegion0.originalHeight) / f3;
                    float f8 = ((float)textureAtlas$AtlasRegion0.originalWidth) / f4;
                    while(v < arr_f1.length) {
                        arr_f1[v] = arr_f[v + 1] * f7 + f5;
                        arr_f1[v + 1] = (1.0f - arr_f[v]) * f8 + f6;
                        v += 2;
                    }
                    return;
                }
                case 180: {
                    float f9 = f1 - (((float)textureAtlas$AtlasRegion0.originalWidth) - textureAtlas$AtlasRegion0.offsetX - ((float)textureAtlas$AtlasRegion0.packedWidth)) / f3;
                    float f10 = f2 - textureAtlas$AtlasRegion0.offsetY / f4;
                    float f11 = ((float)textureAtlas$AtlasRegion0.originalWidth) / f3;
                    float f12 = ((float)textureAtlas$AtlasRegion0.originalHeight) / f4;
                    while(v < arr_f1.length) {
                        arr_f1[v] = (1.0f - arr_f[v]) * f11 + f9;
                        arr_f1[v + 1] = (1.0f - arr_f[v + 1]) * f12 + f10;
                        v += 2;
                    }
                    return;
                }
                case 270: {
                    float f13 = f1 - textureAtlas$AtlasRegion0.offsetY / f3;
                    float f14 = f2 - textureAtlas$AtlasRegion0.offsetX / f4;
                    float f15 = ((float)textureAtlas$AtlasRegion0.originalHeight) / f3;
                    float f16 = ((float)textureAtlas$AtlasRegion0.originalWidth) / f4;
                    while(v < arr_f1.length) {
                        arr_f1[v] = (1.0f - arr_f[v + 1]) * f15 + f13;
                        arr_f1[v + 1] = arr_f[v] * f16 + f14;
                        v += 2;
                    }
                    return;
                label_45:
                    f17 = f1 - textureAtlas$AtlasRegion0.offsetX / f3;
                    f18 = f2 - (((float)textureAtlas$AtlasRegion0.originalHeight) - textureAtlas$AtlasRegion0.offsetY - ((float)textureAtlas$AtlasRegion0.packedHeight)) / f4;
                    f = ((float)textureAtlas$AtlasRegion0.originalWidth) / f3;
                    f19 = ((float)textureAtlas$AtlasRegion0.originalHeight) / f4;
                    break;
                }
                default: {
                    goto label_45;
                }
            }
        }
        else if(textureRegion0 == null) {
            f17 = 0.0f;
            f18 = 0.0f;
            f19 = 1.0f;
        }
        else {
            f17 = textureRegion0.getU();
            f18 = this.region.getV();
            f = this.region.getU2() - f17;
            f19 = this.region.getV2() - f18;
        }
        while(v < arr_f1.length) {
            arr_f1[v] = arr_f[v] * f + f17;
            arr_f1[v + 1] = arr_f[v + 1] * f19 + f18;
            v += 2;
        }
    }
}

