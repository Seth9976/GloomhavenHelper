package com.esotericsoftware.spine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.NumberUtils;
import com.badlogic.gdx.utils.ShortArray;
import com.esotericsoftware.spine.attachments.Attachment;
import com.esotericsoftware.spine.attachments.ClippingAttachment;
import com.esotericsoftware.spine.attachments.MeshAttachment;
import com.esotericsoftware.spine.attachments.RegionAttachment;
import com.esotericsoftware.spine.attachments.SkeletonAttachment;
import com.esotericsoftware.spine.utils.SkeletonClipping;
import com.esotericsoftware.spine.utils.TwoColorPolygonBatch;

public class SkeletonRenderer {
    public interface VertexEffect {
        void begin(Skeleton arg1);

        void end();

        void transform(Vector2 arg1, Vector2 arg2, Color arg3, Color arg4);
    }

    private final SkeletonClipping clipper;
    private boolean pmaBlendModes;
    private boolean pmaColors;
    private static final short[] quadTriangles;
    private final Vector2 temp;
    private final Vector2 temp2;
    private final Color temp3;
    private final Color temp4;
    private final Color temp5;
    private final Color temp6;
    @Null
    private VertexEffect vertexEffect;
    private final FloatArray vertices;

    static {
        SkeletonRenderer.quadTriangles = new short[]{0, 1, 2, 2, 3, 0};
    }

    public SkeletonRenderer() {
        this.vertices = new FloatArray(0x20);
        this.clipper = new SkeletonClipping();
        this.temp = new Vector2();
        this.temp2 = new Vector2();
        this.temp3 = new Color();
        this.temp4 = new Color();
        this.temp5 = new Color();
        this.temp6 = new Color();
    }

    private void applyVertexEffect(float[] arr_f, int v, int v1, float f, float f1) {
        Vector2 vector20 = this.temp;
        Vector2 vector21 = this.temp2;
        Color color0 = this.temp3;
        Color color1 = this.temp4;
        Color color2 = this.temp5;
        Color color3 = this.temp6;
        VertexEffect skeletonRenderer$VertexEffect0 = this.vertexEffect;
        color0.set(NumberUtils.floatToIntColor(f));
        color1.set(NumberUtils.floatToIntColor(f1));
        int v2 = 0;
        if(v1 == 5) {
            while(v2 < v) {
                vector20.x = arr_f[v2];
                vector20.y = arr_f[v2 + 1];
                vector21.x = arr_f[v2 + 3];
                vector21.y = arr_f[v2 + 4];
                color2.set(color0);
                color3.set(color1);
                skeletonRenderer$VertexEffect0.transform(vector20, vector21, color2, color3);
                arr_f[v2] = vector20.x;
                arr_f[v2 + 1] = vector20.y;
                arr_f[v2 + 2] = color2.toFloatBits();
                arr_f[v2 + 3] = vector21.x;
                arr_f[v2 + 4] = vector21.y;
                v2 += 5;
            }
            return;
        }
        while(v2 < v) {
            vector20.x = arr_f[v2];
            vector20.y = arr_f[v2 + 1];
            vector21.x = arr_f[v2 + 4];
            vector21.y = arr_f[v2 + 5];
            color2.set(color0);
            color3.set(color1);
            skeletonRenderer$VertexEffect0.transform(vector20, vector21, color2, color3);
            arr_f[v2] = vector20.x;
            arr_f[v2 + 1] = vector20.y;
            arr_f[v2 + 2] = color2.toFloatBits();
            arr_f[v2 + 3] = color3.toFloatBits();
            arr_f[v2 + 4] = vector21.x;
            arr_f[v2 + 5] = vector21.y;
            v2 += v1;
        }
    }

    public void draw(Batch batch0, Skeleton skeleton0) {
        float f4;
        Object[] arr_object1;
        int v3;
        int v2;
        Slot slot2;
        RegionAttachment regionAttachment0;
        Slot slot1;
        BlendMode blendMode2;
        float f8;
        float f7;
        if(batch0 instanceof TwoColorPolygonBatch) {
            this.draw(((TwoColorPolygonBatch)batch0), skeleton0);
            return;
        }
        if(batch0 instanceof PolygonSpriteBatch) {
            this.draw(((PolygonSpriteBatch)batch0), skeleton0);
            return;
        }
        if(batch0 == null) {
            throw new IllegalArgumentException("batch cannot be null.");
        }
        if(skeleton0 == null) {
            throw new IllegalArgumentException("skeleton cannot be null.");
        }
        VertexEffect skeletonRenderer$VertexEffect0 = this.vertexEffect;
        if(skeletonRenderer$VertexEffect0 != null) {
            skeletonRenderer$VertexEffect0.begin(skeleton0);
        }
        boolean z = this.pmaColors;
        boolean z1 = this.pmaBlendModes;
        BlendMode blendMode0 = null;
        float[] arr_f = this.vertices.items;
        float f = skeleton0.color.r;
        float f1 = skeleton0.color.g;
        float f2 = skeleton0.color.b;
        float f3 = skeleton0.color.a;
        Object[] arr_object = skeleton0.drawOrder.items;
        int v = skeleton0.drawOrder.size;
        int v1 = 0;
        while(v1 < v) {
            Slot slot0 = (Slot)arr_object[v1];
            if(slot0.bone.active) {
                Attachment attachment0 = slot0.attachment;
                v2 = v1;
                if(attachment0 instanceof RegionAttachment) {
                    v3 = v;
                    ((RegionAttachment)attachment0).computeWorldVertices(slot0, arr_f, 0, 5);
                    Color color0 = ((RegionAttachment)attachment0).getColor();
                    Color color1 = slot0.getColor();
                    arr_object1 = arr_object;
                    f4 = f3;
                    float f5 = 255.0f;
                    float f6 = color1.a * f3 * color0.a * 255.0f;
                    if(z) {
                        f5 = f6;
                        f7 = f5;
                    }
                    else {
                        f7 = f6;
                    }
                    BlendMode blendMode1 = slot0.data.getBlendMode();
                    if(blendMode1 == blendMode0) {
                        blendMode2 = blendMode0;
                        f8 = f7;
                    }
                    else {
                        if(blendMode1 != BlendMode.additive || !z) {
                            f8 = f7;
                        }
                        else {
                            blendMode1 = BlendMode.normal;
                            f8 = 0.0f;
                        }
                        blendMode1.apply(batch0, z1);
                        blendMode2 = blendMode1;
                    }
                    float f9 = NumberUtils.intToFloatColor(((int)(color1.b * f2 * color0.b * f5)) << 16 | ((int)f8) << 24 | ((int)(color1.g * f1 * color0.g * f5)) << 8 | ((int)(color1.r * f * color0.r * f5)));
                    float[] arr_f1 = ((RegionAttachment)attachment0).getUVs();
                    int v5 = 0;
                    for(int v4 = 2; v5 < 8; v4 += 5) {
                        arr_f[v4] = f9;
                        arr_f[v4 + 1] = arr_f1[v5];
                        arr_f[v4 + 2] = arr_f1[v5 + 1];
                        v5 += 2;
                    }
                    if(skeletonRenderer$VertexEffect0 == null) {
                        regionAttachment0 = (RegionAttachment)attachment0;
                        slot1 = slot0;
                    }
                    else {
                        slot1 = slot0;
                        regionAttachment0 = (RegionAttachment)attachment0;
                        this.applyVertexEffect(arr_f, 20, 5, f9, 0.0f);
                    }
                    batch0.draw(regionAttachment0.getRegion().getTexture(), arr_f, 0, 20);
                    blendMode0 = blendMode2;
                    slot2 = slot1;
                }
                else {
                    v3 = v;
                    arr_object1 = arr_object;
                    f4 = f3;
                    if(attachment0 instanceof ClippingAttachment) {
                        this.clipper.clipStart(slot0, ((ClippingAttachment)attachment0));
                        goto label_94;
                    }
                    else {
                        slot2 = slot0;
                        if(attachment0 instanceof MeshAttachment) {
                            throw new RuntimeException(batch0.getClass().getSimpleName() + " cannot render meshes, PolygonSpriteBatch or TwoColorPolygonBatch is required.");
                        }
                        if(attachment0 instanceof SkeletonAttachment) {
                            Skeleton skeleton1 = ((SkeletonAttachment)attachment0).getSkeleton();
                            if(skeleton1 != null) {
                                this.draw(batch0, skeleton1);
                            }
                        }
                    }
                }
                this.clipper.clipEnd(slot2);
            }
            else {
                this.clipper.clipEnd(slot0);
                v2 = v1;
                v3 = v;
                arr_object1 = arr_object;
                f4 = f3;
            }
        label_94:
            v1 = v2 + 1;
            v = v3;
            arr_object = arr_object1;
            f3 = f4;
        }
        this.clipper.clipEnd();
        if(skeletonRenderer$VertexEffect0 != null) {
            skeletonRenderer$VertexEffect0.end();
        }
    }

    public void draw(PolygonSpriteBatch polygonSpriteBatch0, Skeleton skeleton0) {
        Vector2 vector23;
        Object[] arr_object1;
        int v3;
        Color color16;
        int v10;
        Color color15;
        short[] arr_v3;
        Color color13;
        Slot slot1;
        ShortArray shortArray1;
        float f11;
        BlendMode blendMode2;
        float f10;
        Color color11;
        Color color10;
        float f7;
        int v9;
        Vector2 vector25;
        Object[] arr_object2;
        int v8;
        int v7;
        short[] arr_v2;
        Color color9;
        Vector2 vector24;
        Color color8;
        VertexEffect skeletonRenderer$VertexEffect1;
        boolean z3;
        boolean z2;
        float f6;
        float f5;
        float f4;
        Texture texture0;
        int v5;
        Color color7;
        short[] arr_v1;
        int v4;
        if(polygonSpriteBatch0 == null) {
            throw new IllegalArgumentException("batch cannot be null.");
        }
        if(skeleton0 == null) {
            throw new IllegalArgumentException("skeleton cannot be null.");
        }
        Vector2 vector20 = this.temp;
        Vector2 vector21 = this.temp2;
        Color color0 = this.temp3;
        Color color1 = this.temp4;
        Color color2 = this.temp5;
        Color color3 = this.temp6;
        VertexEffect skeletonRenderer$VertexEffect0 = this.vertexEffect;
        if(skeletonRenderer$VertexEffect0 != null) {
            skeletonRenderer$VertexEffect0.begin(skeleton0);
        }
        boolean z = this.pmaColors;
        boolean z1 = this.pmaBlendModes;
        float f = skeleton0.color.r;
        float f1 = skeleton0.color.g;
        float f2 = skeleton0.color.b;
        float f3 = skeleton0.color.a;
        Color color4 = color3;
        Object[] arr_object = skeleton0.drawOrder.items;
        int v = skeleton0.drawOrder.size;
        Vector2 vector22 = vector21;
        Color color5 = color2;
        Color color6 = null;
        BlendMode blendMode0 = null;
        float[] arr_f = null;
        float[] arr_f1 = null;
        short[] arr_v = null;
        int v1 = 0;
        int v2 = 0;
        while(v1 < v) {
            Slot slot0 = (Slot)arr_object[v1];
            if(slot0.bone.active) {
                if(this.clipper.isClipping()) {
                    v3 = v;
                    v4 = 2;
                }
                else {
                    v4 = 5;
                    v3 = v;
                }
                Attachment attachment0 = slot0.attachment;
                arr_object1 = arr_object;
                if(attachment0 instanceof RegionAttachment) {
                    float[] arr_f2 = this.vertices.items;
                    vector23 = vector20;
                    ((RegionAttachment)attachment0).computeWorldVertices(slot0, arr_f2, 0, v4);
                    arr_v1 = SkeletonRenderer.quadTriangles;
                    arr_f1 = ((RegionAttachment)attachment0).getUVs();
                    color7 = ((RegionAttachment)attachment0).getColor();
                    arr_f = arr_f2;
                    v5 = v4 << 2;
                    texture0 = ((RegionAttachment)attachment0).getRegion().getTexture();
                }
                else {
                    vector23 = vector20;
                    if(attachment0 instanceof MeshAttachment) {
                        int v6 = ((MeshAttachment)attachment0).getWorldVerticesLength();
                        v5 = (v6 >> 1) * v4;
                        float[] arr_f3 = this.vertices.setSize(v5);
                        ((MeshAttachment)attachment0).computeWorldVertices(slot0, 0, v6, arr_f3, 0, v4);
                        arr_v1 = ((MeshAttachment)attachment0).getTriangles();
                        texture0 = ((MeshAttachment)attachment0).getRegion().getTexture();
                        arr_f1 = ((MeshAttachment)attachment0).getUVs();
                        arr_f = arr_f3;
                        color7 = ((MeshAttachment)attachment0).getColor();
                    }
                    else if(attachment0 instanceof ClippingAttachment) {
                        this.clipper.clipStart(slot0, ((ClippingAttachment)attachment0));
                    label_70:
                        f4 = f2;
                        f5 = f1;
                        f6 = f;
                        z2 = z1;
                        z3 = z;
                        skeletonRenderer$VertexEffect1 = skeletonRenderer$VertexEffect0;
                        color8 = color4;
                        vector24 = vector22;
                        color9 = color5;
                        arr_v2 = arr_v;
                        v7 = v2;
                        v8 = v3;
                        arr_object2 = arr_object1;
                        vector25 = vector23;
                        v9 = v1;
                        f7 = f3;
                        color10 = color1;
                        color11 = color0;
                        goto label_236;
                    }
                    else {
                        if(attachment0 instanceof SkeletonAttachment) {
                            Skeleton skeleton1 = ((SkeletonAttachment)attachment0).getSkeleton();
                            if(skeleton1 != null) {
                                this.draw(polygonSpriteBatch0, skeleton1);
                            }
                        }
                        color7 = color6;
                        arr_v1 = arr_v;
                        v5 = v2;
                        texture0 = null;
                    }
                }
                if(texture0 == null) {
                    f7 = f3;
                    f4 = f2;
                    f5 = f1;
                    f6 = f;
                    z2 = z1;
                    z3 = z;
                    skeletonRenderer$VertexEffect1 = skeletonRenderer$VertexEffect0;
                    v7 = v5;
                    slot1 = slot0;
                    color10 = color1;
                    color11 = color0;
                    arr_v3 = arr_v1;
                    color8 = color4;
                    vector24 = vector22;
                    color9 = color5;
                    v8 = v3;
                    arr_object2 = arr_object1;
                    vector25 = vector23;
                    v9 = v1;
                    color13 = color7;
                }
                else {
                    Color color12 = slot0.getColor();
                    f7 = f3;
                    float f8 = 255.0f;
                    float f9 = color12.a * f3 * color7.a * 255.0f;
                    if(z) {
                        f8 = f9;
                        f10 = f8;
                    }
                    else {
                        f10 = f9;
                    }
                    BlendMode blendMode1 = slot0.data.getBlendMode();
                    if(blendMode1 == blendMode0) {
                        f11 = f10;
                    }
                    else {
                        if(blendMode1 != BlendMode.additive || !z) {
                            blendMode2 = blendMode1;
                            f11 = f10;
                        }
                        else {
                            blendMode2 = BlendMode.normal;
                            f11 = 0.0f;
                        }
                        blendMode2.apply(polygonSpriteBatch0, z1);
                        blendMode0 = blendMode2;
                    }
                    f4 = f2;
                    float f12 = NumberUtils.intToFloatColor(((int)(color12.r * f * color7.r * f8)) | (((int)(color12.b * f2 * color7.b * f8)) << 16 | ((int)f11) << 24 | ((int)(color12.g * f1 * color7.g * f8)) << 8));
                    if(this.clipper.isClipping()) {
                        this.clipper.clipTriangles(arr_f, v5, arr_v1, arr_v1.length, arr_f1, f12, 0.0f, false);
                        FloatArray floatArray0 = this.clipper.getClippedVertices();
                        ShortArray shortArray0 = this.clipper.getClippedTriangles();
                        if(skeletonRenderer$VertexEffect0 == null) {
                            shortArray1 = shortArray0;
                            f5 = f1;
                            f6 = f;
                            z2 = z1;
                            v8 = v3;
                        }
                        else {
                            v8 = v3;
                            shortArray1 = shortArray0;
                            f5 = f1;
                            f6 = f;
                            z2 = z1;
                            this.applyVertexEffect(floatArray0.items, floatArray0.size, 5, f12, 0.0f);
                        }
                        z3 = z;
                        skeletonRenderer$VertexEffect1 = skeletonRenderer$VertexEffect0;
                        arr_object2 = arr_object1;
                        color9 = color5;
                        slot1 = slot0;
                        v9 = v1;
                        vector24 = vector22;
                        color13 = color7;
                        vector25 = vector23;
                        polygonSpriteBatch0.draw(texture0, floatArray0.items, 0, floatArray0.size, shortArray1.items, 0, shortArray1.size);
                        color8 = color4;
                        v7 = v5;
                        color10 = color1;
                        color11 = color0;
                        arr_v3 = arr_v1;
                    }
                    else {
                        f5 = f1;
                        f6 = f;
                        z2 = z1;
                        z3 = z;
                        skeletonRenderer$VertexEffect1 = skeletonRenderer$VertexEffect0;
                        Color color14 = color4;
                        vector24 = vector22;
                        color9 = color5;
                        slot1 = slot0;
                        v8 = v3;
                        arr_object2 = arr_object1;
                        vector25 = vector23;
                        v9 = v1;
                        color13 = color7;
                        if(skeletonRenderer$VertexEffect1 == null) {
                            color16 = color14;
                            v10 = v5;
                            color10 = color1;
                            color15 = color0;
                            int v13 = 2;
                            for(int v14 = 0; v13 < v10; v14 += 2) {
                                arr_f[v13] = f12;
                                arr_f[v13 + 1] = arr_f1[v14];
                                arr_f[v13 + 2] = arr_f1[v14 + 1];
                                v13 += 5;
                            }
                        }
                        else {
                            color15 = color0;
                            color15.set(NumberUtils.floatToIntColor(f12));
                            color10 = color1;
                            color10.set(0);
                            v10 = v5;
                            int v11 = 0;
                            for(int v12 = 0; v11 < v10; v12 += 2) {
                                vector25.x = arr_f[v11];
                                vector25.y = arr_f[v11 + 1];
                                color9.set(color15);
                                color14.set(color10);
                                vector24.x = arr_f1[v12];
                                vector24.y = arr_f1[v12 + 1];
                                skeletonRenderer$VertexEffect1.transform(vector25, vector24, color9, color14);
                                arr_f[v11] = vector25.x;
                                arr_f[v11 + 1] = vector25.y;
                                arr_f[v11 + 2] = color9.toFloatBits();
                                arr_f[v11 + 3] = vector24.x;
                                arr_f[v11 + 4] = vector24.y;
                                v11 += 5;
                            }
                            color16 = color14;
                        }
                        arr_v3 = arr_v1;
                        color8 = color16;
                        v7 = v10;
                        color11 = color15;
                        polygonSpriteBatch0.draw(texture0, arr_f, 0, v10, arr_v3, 0, arr_v1.length);
                    }
                }
                this.clipper.clipEnd(slot1);
                arr_v2 = arr_v3;
                color6 = color13;
            }
            else {
                this.clipper.clipEnd(slot0);
                v3 = v;
                arr_object1 = arr_object;
                vector23 = vector20;
                goto label_70;
            }
        label_236:
            v1 = v9 + 1;
            color5 = color9;
            color1 = color10;
            vector22 = vector24;
            vector20 = vector25;
            skeletonRenderer$VertexEffect0 = skeletonRenderer$VertexEffect1;
            arr_object = arr_object2;
            color0 = color11;
            f3 = f7;
            f2 = f4;
            color4 = color8;
            v2 = v7;
            v = v8;
            f1 = f5;
            z1 = z2;
            arr_v = arr_v2;
            z = z3;
            f = f6;
        }
        this.clipper.clipEnd();
        if(skeletonRenderer$VertexEffect0 != null) {
            skeletonRenderer$VertexEffect0.end();
        }
    }

    public void draw(TwoColorPolygonBatch twoColorPolygonBatch0, Skeleton skeleton0) {
        Vector2 vector23;
        Object[] arr_object1;
        int v3;
        Vector2 vector27;
        Color color17;
        Color color16;
        int v10;
        Color color14;
        Slot slot1;
        ShortArray shortArray1;
        float f12;
        float f10;
        Color color11;
        float f7;
        int v9;
        Vector2 vector25;
        Object[] arr_object2;
        int v8;
        short[] arr_v3;
        Color color10;
        Vector2 vector24;
        Color color9;
        Color color8;
        VertexEffect skeletonRenderer$VertexEffect1;
        boolean z3;
        boolean z2;
        float f6;
        float f5;
        float f4;
        Texture texture0;
        int v5;
        short[] arr_v1;
        Color color7;
        int v4;
        if(twoColorPolygonBatch0 == null) {
            throw new IllegalArgumentException("batch cannot be null.");
        }
        if(skeleton0 == null) {
            throw new IllegalArgumentException("skeleton cannot be null.");
        }
        Vector2 vector20 = this.temp;
        Vector2 vector21 = this.temp2;
        Color color0 = this.temp3;
        Color color1 = this.temp4;
        Color color2 = this.temp5;
        Color color3 = this.temp6;
        VertexEffect skeletonRenderer$VertexEffect0 = this.vertexEffect;
        if(skeletonRenderer$VertexEffect0 != null) {
            skeletonRenderer$VertexEffect0.begin(skeleton0);
        }
        boolean z = this.pmaColors;
        boolean z1 = this.pmaBlendModes;
        twoColorPolygonBatch0.setPremultipliedAlpha(z);
        float f = skeleton0.color.r;
        float f1 = skeleton0.color.g;
        float f2 = skeleton0.color.b;
        float f3 = skeleton0.color.a;
        Color color4 = color3;
        Object[] arr_object = skeleton0.drawOrder.items;
        int v = skeleton0.drawOrder.size;
        Vector2 vector22 = vector21;
        Color color5 = color2;
        Color color6 = null;
        BlendMode blendMode0 = null;
        float[] arr_f = null;
        short[] arr_v = null;
        float[] arr_f1 = null;
        int v1 = 0;
        int v2 = 0;
        while(v1 < v) {
            Slot slot0 = (Slot)arr_object[v1];
            if(slot0.bone.active) {
                if(this.clipper.isClipping()) {
                    v3 = v;
                    v4 = 2;
                }
                else {
                    v4 = 6;
                    v3 = v;
                }
                Attachment attachment0 = slot0.attachment;
                arr_object1 = arr_object;
                if(attachment0 instanceof RegionAttachment) {
                    float[] arr_f2 = this.vertices.items;
                    vector23 = vector20;
                    ((RegionAttachment)attachment0).computeWorldVertices(slot0, arr_f2, 0, v4);
                    arr_f1 = ((RegionAttachment)attachment0).getUVs();
                    color7 = ((RegionAttachment)attachment0).getColor();
                    arr_f = arr_f2;
                    arr_v1 = SkeletonRenderer.quadTriangles;
                    v5 = v4 << 2;
                    texture0 = ((RegionAttachment)attachment0).getRegion().getTexture();
                }
                else {
                    vector23 = vector20;
                    if(attachment0 instanceof MeshAttachment) {
                        int v6 = ((MeshAttachment)attachment0).getWorldVerticesLength();
                        int v7 = (v6 >> 1) * v4;
                        float[] arr_f3 = this.vertices.setSize(v7);
                        ((MeshAttachment)attachment0).computeWorldVertices(slot0, 0, v6, arr_f3, 0, v4);
                        short[] arr_v2 = ((MeshAttachment)attachment0).getTriangles();
                        texture0 = ((MeshAttachment)attachment0).getRegion().getTexture();
                        arr_f1 = ((MeshAttachment)attachment0).getUVs();
                        arr_f = arr_f3;
                        color7 = ((MeshAttachment)attachment0).getColor();
                        v5 = v7;
                        arr_v1 = arr_v2;
                    }
                    else if(attachment0 instanceof ClippingAttachment) {
                        this.clipper.clipStart(slot0, ((ClippingAttachment)attachment0));
                    label_73:
                        f4 = f2;
                        f5 = f1;
                        f6 = f;
                        z2 = z1;
                        z3 = z;
                        skeletonRenderer$VertexEffect1 = skeletonRenderer$VertexEffect0;
                        color8 = color0;
                        color9 = color4;
                        vector24 = vector22;
                        color10 = color5;
                        arr_v3 = arr_v;
                        v8 = v3;
                        arr_object2 = arr_object1;
                        vector25 = vector23;
                        v9 = v1;
                        f7 = f3;
                        color11 = color1;
                        goto label_244;
                    }
                    else {
                        if(attachment0 instanceof SkeletonAttachment) {
                            Skeleton skeleton1 = ((SkeletonAttachment)attachment0).getSkeleton();
                            if(skeleton1 != null) {
                                this.draw(twoColorPolygonBatch0, skeleton1);
                            }
                        }
                        color7 = color6;
                        v5 = v2;
                        arr_v1 = arr_v;
                        texture0 = null;
                    }
                }
                if(texture0 == null) {
                    f7 = f3;
                    f4 = f2;
                    f5 = f1;
                    f6 = f;
                    z2 = z1;
                    z3 = z;
                    skeletonRenderer$VertexEffect1 = skeletonRenderer$VertexEffect0;
                    arr_v3 = arr_v1;
                    slot1 = slot0;
                    color11 = color1;
                    color8 = color0;
                    v10 = v5;
                    color14 = color7;
                    color9 = color4;
                    vector24 = vector22;
                    color10 = color5;
                    v8 = v3;
                    arr_object2 = arr_object1;
                    vector25 = vector23;
                    v9 = v1;
                }
                else {
                    Color color12 = slot0.getColor();
                    f7 = f3;
                    float f8 = 255.0f;
                    float f9 = color12.a * f3 * color7.a * 255.0f;
                    if(z) {
                        f8 = f9;
                        f10 = f8;
                    }
                    else {
                        f10 = f9;
                    }
                    BlendMode blendMode1 = slot0.data.getBlendMode();
                    float f11 = 0.0f;
                    if(blendMode1 != blendMode0) {
                        if(blendMode1 == BlendMode.additive && z) {
                            blendMode1 = BlendMode.normal;
                            f10 = 0.0f;
                        }
                        blendMode1.apply(twoColorPolygonBatch0, z1);
                        blendMode0 = blendMode1;
                    }
                    f12 = f10;
                    float f13 = color7.r * f * f8;
                    f6 = f;
                    float f14 = color7.g * f1 * f8;
                    f5 = f1;
                    float f15 = color7.b * f2 * f8;
                    float f16 = NumberUtils.intToFloatColor(((int)(color12.r * f13)) | (((int)f12) << 24 | ((int)(color12.b * f15)) << 16 | ((int)(color12.g * f14)) << 8));
                    Color color13 = slot0.getDarkColor();
                    if(color13 != null) {
                        f11 = NumberUtils.intToFloatColor(((int)(f13 * color13.r)) | (((int)(f15 * color13.b)) << 16 | ((int)(f14 * color13.g)) << 8));
                    }
                    if(this.clipper.isClipping()) {
                        this.clipper.clipTriangles(arr_f, v5, arr_v1, arr_v1.length, arr_f1, f16, f11, true);
                        FloatArray floatArray0 = this.clipper.getClippedVertices();
                        ShortArray shortArray0 = this.clipper.getClippedTriangles();
                        if(skeletonRenderer$VertexEffect0 == null) {
                            f4 = f2;
                            shortArray1 = shortArray0;
                            z2 = z1;
                            v8 = v3;
                        }
                        else {
                            v8 = v3;
                            f4 = f2;
                            shortArray1 = shortArray0;
                            z2 = z1;
                            this.applyVertexEffect(floatArray0.items, floatArray0.size, 6, f16, f11);
                        }
                        z3 = z;
                        skeletonRenderer$VertexEffect1 = skeletonRenderer$VertexEffect0;
                        arr_object2 = arr_object1;
                        slot1 = slot0;
                        color10 = color5;
                        v9 = v1;
                        color11 = color1;
                        color14 = color7;
                        vector25 = vector23;
                        twoColorPolygonBatch0.drawTwoColor(texture0, floatArray0.items, 0, floatArray0.size, shortArray1.items, 0, shortArray1.size);
                        color9 = color4;
                        arr_v3 = arr_v1;
                        color8 = color0;
                        vector24 = vector22;
                        v10 = v5;
                    }
                    else {
                        f4 = f2;
                        z2 = z1;
                        z3 = z;
                        skeletonRenderer$VertexEffect1 = skeletonRenderer$VertexEffect0;
                        slot1 = slot0;
                        Color color15 = color4;
                        Vector2 vector26 = vector22;
                        color10 = color5;
                        color11 = color1;
                        v8 = v3;
                        arr_object2 = arr_object1;
                        vector25 = vector23;
                        v9 = v1;
                        color14 = color7;
                        if(skeletonRenderer$VertexEffect1 == null) {
                            color17 = color15;
                            color16 = color0;
                            vector27 = vector26;
                            v10 = v5;
                            int v13 = 2;
                            for(int v14 = 0; v13 < v10; v14 += 2) {
                                arr_f[v13] = f16;
                                arr_f[v13 + 1] = f11;
                                arr_f[v13 + 2] = arr_f1[v14];
                                arr_f[v13 + 3] = arr_f1[v14 + 1];
                                v13 += 6;
                            }
                        }
                        else {
                            color16 = color0;
                            color16.set(NumberUtils.floatToIntColor(f16));
                            color11.set(NumberUtils.floatToIntColor(f11));
                            v10 = v5;
                            int v11 = 0;
                            for(int v12 = 0; v11 < v10; v12 += 2) {
                                vector25.x = arr_f[v11];
                                vector25.y = arr_f[v11 + 1];
                                color10.set(color16);
                                color15.set(color11);
                                vector26.x = arr_f1[v12];
                                vector26.y = arr_f1[v12 + 1];
                                skeletonRenderer$VertexEffect1.transform(vector25, vector26, color10, color15);
                                arr_f[v11] = vector25.x;
                                arr_f[v11 + 1] = vector25.y;
                                arr_f[v11 + 2] = color10.toFloatBits();
                                arr_f[v11 + 3] = color15.toFloatBits();
                                arr_f[v11 + 4] = vector26.x;
                                arr_f[v11 + 5] = vector26.y;
                                v11 += 6;
                            }
                            color17 = color15;
                            vector27 = vector26;
                        }
                        arr_v3 = arr_v1;
                        vector24 = vector27;
                        color9 = color17;
                        color8 = color16;
                        twoColorPolygonBatch0.drawTwoColor(texture0, arr_f, 0, v10, arr_v3, 0, arr_v1.length);
                    }
                }
                this.clipper.clipEnd(slot1);
                color6 = color14;
                v2 = v10;
            }
            else {
                this.clipper.clipEnd(slot0);
                v3 = v;
                arr_object1 = arr_object;
                vector23 = vector20;
                goto label_73;
            }
        label_244:
            v1 = v9 + 1;
            color5 = color10;
            color1 = color11;
            vector20 = vector25;
            z = z3;
            skeletonRenderer$VertexEffect0 = skeletonRenderer$VertexEffect1;
            arr_object = arr_object2;
            f3 = f7;
            color0 = color8;
            f = f6;
            arr_v = arr_v3;
            vector22 = vector24;
            f1 = f5;
            z1 = z2;
            v = v8;
            f2 = f4;
            color4 = color9;
        }
        this.clipper.clipEnd();
        if(skeletonRenderer$VertexEffect0 != null) {
            skeletonRenderer$VertexEffect0.end();
        }
    }

    public boolean getPremultipliedAlphaBlendModes() {
        return this.pmaBlendModes;
    }

    public boolean getPremultipliedAlphaColors() {
        return this.pmaColors;
    }

    @Null
    public VertexEffect getVertexEffect() {
        return this.vertexEffect;
    }

    public void setPremultipliedAlpha(boolean z) {
        this.pmaColors = z;
        this.pmaBlendModes = z;
    }

    public void setPremultipliedAlphaBlendModes(boolean z) {
        this.pmaBlendModes = z;
    }

    public void setPremultipliedAlphaColors(boolean z) {
        this.pmaColors = z;
    }

    public void setVertexEffect(@Null VertexEffect skeletonRenderer$VertexEffect0) {
        this.vertexEffect = skeletonRenderer$VertexEffect0;
    }
}

