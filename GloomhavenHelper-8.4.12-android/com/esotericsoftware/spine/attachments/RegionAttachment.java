package com.esotericsoftware.spine.attachments;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.spine.Bone;
import com.esotericsoftware.spine.Slot;
import com.esotericsoftware.spine.utils.SpineUtils;

public class RegionAttachment extends Attachment implements HasTextureRegion {
    public static final int BLX = 0;
    public static final int BLY = 1;
    public static final int BRX = 6;
    public static final int BRY = 7;
    public static final int ULX = 2;
    public static final int ULY = 3;
    public static final int URX = 4;
    public static final int URY = 5;
    private final Color color;
    private float height;
    private final float[] offset;
    private String path;
    private TextureRegion region;
    private float rotation;
    private float scaleX;
    private float scaleY;
    @Null
    private Sequence sequence;
    private final float[] uvs;
    private float width;
    private float x;
    private float y;

    protected RegionAttachment(RegionAttachment regionAttachment0) {
        super(regionAttachment0);
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.uvs = new float[8];
        this.offset = new float[8];
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.region = regionAttachment0.region;
        this.path = regionAttachment0.path;
        this.x = regionAttachment0.x;
        this.y = regionAttachment0.y;
        this.scaleX = regionAttachment0.scaleX;
        this.scaleY = regionAttachment0.scaleY;
        this.rotation = regionAttachment0.rotation;
        this.width = regionAttachment0.width;
        this.height = regionAttachment0.height;
        SpineUtils.arraycopy(regionAttachment0.uvs, 0, this.uvs, 0, 8);
        SpineUtils.arraycopy(regionAttachment0.offset, 0, this.offset, 0, 8);
        this.color.set(regionAttachment0.color);
        this.sequence = regionAttachment0.sequence == null ? null : new Sequence(regionAttachment0.sequence);
    }

    public RegionAttachment(String s) {
        super(s);
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.uvs = new float[8];
        this.offset = new float[8];
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void computeWorldVertices(Slot slot0, float[] arr_f, int v, int v1) {
        Sequence sequence0 = this.sequence;
        if(sequence0 != null) {
            sequence0.apply(slot0, this);
        }
        Bone bone0 = slot0.getBone();
        float f = bone0.getWorldX();
        float f1 = bone0.getWorldY();
        float f2 = bone0.getA();
        float f3 = bone0.getB();
        float f4 = bone0.getC();
        float f5 = bone0.getD();
        float f6 = this.offset[6];
        float f7 = this.offset[7];
        arr_f[v] = f6 * f2 + f7 * f3 + f;
        arr_f[v + 1] = f6 * f4 + f7 * f5 + f1;
        int v2 = v + v1;
        float f8 = this.offset[0];
        float f9 = this.offset[1];
        arr_f[v2] = f8 * f2 + f9 * f3 + f;
        arr_f[v2 + 1] = f8 * f4 + f9 * f5 + f1;
        int v3 = v2 + v1;
        float f10 = this.offset[2];
        float f11 = this.offset[3];
        arr_f[v3] = f10 * f2 + f11 * f3 + f;
        arr_f[v3 + 1] = f10 * f4 + f11 * f5 + f1;
        int v4 = v3 + v1;
        float f12 = this.offset[4];
        float f13 = this.offset[5];
        arr_f[v4] = f2 * f12 + f3 * f13 + f;
        arr_f[v4 + 1] = f12 * f4 + f13 * f5 + f1;
    }

    @Override  // com.esotericsoftware.spine.attachments.Attachment
    public Attachment copy() {
        return this.copy();
    }

    public RegionAttachment copy() {
        return new RegionAttachment(this);
    }

    @Override  // com.esotericsoftware.spine.attachments.HasTextureRegion
    public Color getColor() {
        return this.color;
    }

    public float getHeight() {
        return this.height;
    }

    public float[] getOffset() {
        return this.offset;
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

    public float getRotation() {
        return this.rotation;
    }

    public float getScaleX() {
        return this.scaleX;
    }

    public float getScaleY() {
        return this.scaleY;
    }

    @Override  // com.esotericsoftware.spine.attachments.HasTextureRegion
    @Null
    public Sequence getSequence() {
        return this.sequence;
    }

    public float[] getUVs() {
        return this.uvs;
    }

    public float getWidth() {
        return this.width;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setHeight(float f) {
        this.height = f;
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

    public void setRotation(float f) {
        this.rotation = f;
    }

    public void setScaleX(float f) {
        this.scaleX = f;
    }

    public void setScaleY(float f) {
        this.scaleY = f;
    }

    @Override  // com.esotericsoftware.spine.attachments.HasTextureRegion
    public void setSequence(@Null Sequence sequence0) {
        this.sequence = sequence0;
    }

    public void setWidth(float f) {
        this.width = f;
    }

    public void setX(float f) {
        this.x = f;
    }

    public void setY(float f) {
        this.y = f;
    }

    @Override  // com.esotericsoftware.spine.attachments.HasTextureRegion
    public void updateRegion() {
        boolean z;
        float f = this.getWidth();
        float f1 = this.getHeight();
        float f2 = f / 2.0f;
        float f3 = f1 / 2.0f;
        float f4 = -f2;
        float f5 = -f3;
        TextureRegion textureRegion0 = this.region;
        if(textureRegion0 instanceof AtlasRegion) {
            f4 += ((AtlasRegion)textureRegion0).offsetX / ((float)((AtlasRegion)textureRegion0).originalWidth) * f;
            f5 += ((AtlasRegion)textureRegion0).offsetY / ((float)((AtlasRegion)textureRegion0).originalHeight) * f1;
            if(((AtlasRegion)textureRegion0).degrees == 90) {
                f2 -= (((float)((AtlasRegion)textureRegion0).originalWidth) - ((AtlasRegion)textureRegion0).offsetX - ((float)((AtlasRegion)textureRegion0).packedHeight)) / ((float)((AtlasRegion)textureRegion0).originalWidth) * f;
                f3 -= (((float)((AtlasRegion)textureRegion0).originalHeight) - ((AtlasRegion)textureRegion0).offsetY - ((float)((AtlasRegion)textureRegion0).packedWidth)) / ((float)((AtlasRegion)textureRegion0).originalHeight) * f1;
                z = true;
            }
            else {
                f2 -= (((float)((AtlasRegion)textureRegion0).originalWidth) - ((AtlasRegion)textureRegion0).offsetX - ((float)((AtlasRegion)textureRegion0).packedWidth)) / ((float)((AtlasRegion)textureRegion0).originalWidth) * f;
                f3 -= (((float)((AtlasRegion)textureRegion0).originalHeight) - ((AtlasRegion)textureRegion0).offsetY - ((float)((AtlasRegion)textureRegion0).packedHeight)) / ((float)((AtlasRegion)textureRegion0).originalHeight) * f1;
                z = false;
            }
        }
        else {
            z = false;
        }
        float f6 = this.getScaleX();
        float f7 = this.getScaleY();
        float f8 = f4 * f6;
        float f9 = f5 * f7;
        float f10 = f2 * f6;
        float f11 = f3 * f7;
        float f12 = this.getRotation();
        float f13 = (float)Math.cos(f12 * 0.017453f);
        float f14 = (float)Math.sin(f12 * 0.017453f);
        float f15 = this.getX();
        float f16 = this.getY();
        float f17 = f8 * f13 + f15;
        float f18 = f8 * f14;
        float f19 = f9 * f13 + f16;
        float f20 = f9 * f14;
        float f21 = f10 * f13 + f15;
        float f22 = f10 * f14;
        float f23 = f13 * f11 + f16;
        float f24 = f11 * f14;
        this.offset[0] = f17 - f20;
        this.offset[1] = f19 + f18;
        this.offset[2] = f17 - f24;
        this.offset[3] = f18 + f23;
        this.offset[4] = f21 - f24;
        this.offset[5] = f23 + f22;
        this.offset[6] = f21 - f20;
        this.offset[7] = f19 + f22;
        float[] arr_f = this.uvs;
        if(z) {
            arr_f[4] = this.region.getU();
            arr_f[5] = this.region.getV2();
            arr_f[6] = this.region.getU();
            arr_f[7] = this.region.getV();
            arr_f[0] = this.region.getU2();
            arr_f[1] = this.region.getV();
            arr_f[2] = this.region.getU2();
            arr_f[3] = this.region.getV2();
            return;
        }
        arr_f[2] = this.region.getU();
        arr_f[3] = this.region.getV2();
        arr_f[4] = this.region.getU();
        arr_f[5] = this.region.getV();
        arr_f[6] = this.region.getU2();
        arr_f[7] = this.region.getV();
        arr_f[0] = this.region.getU2();
        arr_f[1] = this.region.getV2();
    }
}

