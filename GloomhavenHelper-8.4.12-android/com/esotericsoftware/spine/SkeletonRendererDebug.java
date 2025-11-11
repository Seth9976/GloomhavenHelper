package com.esotericsoftware.spine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.esotericsoftware.spine.attachments.Attachment;
import com.esotericsoftware.spine.attachments.BoundingBoxAttachment;
import com.esotericsoftware.spine.attachments.ClippingAttachment;
import com.esotericsoftware.spine.attachments.MeshAttachment;
import com.esotericsoftware.spine.attachments.PathAttachment;
import com.esotericsoftware.spine.attachments.PointAttachment;
import com.esotericsoftware.spine.attachments.RegionAttachment;

public class SkeletonRendererDebug {
    public static final Color aabbColor;
    public static final Color attachmentLineColor;
    public static final Color boneLineColor;
    public static final Color boneOriginColor;
    private float boneWidth;
    private final SkeletonBounds bounds;
    private boolean drawBones;
    private boolean drawBoundingBoxes;
    private boolean drawClipping;
    private boolean drawMeshHull;
    private boolean drawMeshTriangles;
    private boolean drawPaths;
    private boolean drawPoints;
    private boolean drawRegionAttachments;
    private boolean premultipliedAlpha;
    private float scale;
    private final ShapeRenderer shapes;
    private final Vector2 temp1;
    private final Vector2 temp2;
    public static final Color triangleLineColor;
    private final FloatArray vertices;

    static {
        SkeletonRendererDebug.boneLineColor = Color.RED;
        SkeletonRendererDebug.boneOriginColor = Color.GREEN;
        SkeletonRendererDebug.attachmentLineColor = new Color(0.0f, 0.0f, 1.0f, 0.5f);
        SkeletonRendererDebug.triangleLineColor = new Color(1.0f, 0.64f, 0.0f, 0.5f);
        SkeletonRendererDebug.aabbColor = new Color(0.0f, 1.0f, 0.0f, 0.5f);
    }

    public SkeletonRendererDebug() {
        this.drawBones = true;
        this.drawRegionAttachments = true;
        this.drawBoundingBoxes = true;
        this.drawPoints = true;
        this.drawMeshHull = true;
        this.drawMeshTriangles = true;
        this.drawPaths = true;
        this.drawClipping = true;
        this.bounds = new SkeletonBounds();
        this.vertices = new FloatArray(0x20);
        this.scale = 1.0f;
        this.boneWidth = 2.0f;
        this.temp1 = new Vector2();
        this.temp2 = new Vector2();
        this.shapes = new ShapeRenderer();
    }

    public SkeletonRendererDebug(ShapeRenderer shapeRenderer0) {
        this.drawBones = true;
        this.drawRegionAttachments = true;
        this.drawBoundingBoxes = true;
        this.drawPoints = true;
        this.drawMeshHull = true;
        this.drawMeshTriangles = true;
        this.drawPaths = true;
        this.drawClipping = true;
        this.bounds = new SkeletonBounds();
        this.vertices = new FloatArray(0x20);
        this.scale = 1.0f;
        this.boneWidth = 2.0f;
        this.temp1 = new Vector2();
        this.temp2 = new Vector2();
        if(shapeRenderer0 == null) {
            throw new IllegalArgumentException("shapes cannot be null.");
        }
        this.shapes = shapeRenderer0;
    }

    public void draw(Skeleton skeleton0) {
        int v27;
        float f16;
        float f15;
        int v29;
        Color color1;
        int v17;
        int v3;
        int v2;
        if(skeleton0 == null) {
            throw new IllegalArgumentException("skeleton cannot be null.");
        }
        Gdx.gl.glEnable(3042);
        Gdx.gl.glBlendFunc((this.premultipliedAlpha ? 1 : 770), 0x303);
        ShapeRenderer shapeRenderer0 = this.shapes;
        Array array0 = skeleton0.getBones();
        Array array1 = skeleton0.getSlots();
        shapeRenderer0.begin(ShapeType.Filled);
        if(this.drawBones) {
            int v = array0.size;
            int v1 = 0;
            while(v1 < v) {
                Bone bone0 = (Bone)array0.get(v1);
                if(bone0.parent == null || !bone0.active) {
                    v2 = v1;
                    v3 = v;
                }
                else {
                    float f = bone0.data.length;
                    float f1 = this.boneWidth;
                    if(f == 0.0f) {
                        f1 /= 2.0f;
                        shapeRenderer0.setColor(SkeletonRendererDebug.boneOriginColor);
                        f = 8.0f;
                    }
                    else {
                        shapeRenderer0.setColor(SkeletonRendererDebug.boneLineColor);
                    }
                    v2 = v1;
                    v3 = v;
                    shapeRenderer0.rectLine(bone0.worldX, bone0.worldY, bone0.a * f + bone0.worldX, bone0.worldY + f * bone0.c, this.scale * f1);
                }
                v1 = v2 + 1;
                v = v3;
            }
            shapeRenderer0.x(skeleton0.getX(), skeleton0.getY(), this.scale * 4.0f);
        }
        if(this.drawPoints) {
            shapeRenderer0.setColor(SkeletonRendererDebug.boneOriginColor);
            int v4 = array1.size;
            for(int v5 = 0; v5 < v4; ++v5) {
                Slot slot0 = (Slot)array1.get(v5);
                Attachment attachment0 = slot0.attachment;
                if(attachment0 instanceof PointAttachment) {
                    ((PointAttachment)attachment0).computeWorldPosition(slot0.getBone(), this.temp1);
                    this.temp2.set(8.0f, 0.0f).rotate(((PointAttachment)attachment0).computeWorldRotation(slot0.getBone()));
                    shapeRenderer0.rectLine(this.temp1, this.temp2, this.boneWidth / 2.0f * this.scale);
                }
            }
        }
        shapeRenderer0.end();
        shapeRenderer0.begin(ShapeType.Line);
        if(this.drawRegionAttachments) {
            shapeRenderer0.setColor(SkeletonRendererDebug.attachmentLineColor);
            int v6 = array1.size;
            for(int v7 = 0; v7 < v6; ++v7) {
                Slot slot1 = (Slot)array1.get(v7);
                Attachment attachment1 = slot1.attachment;
                if(attachment1 instanceof RegionAttachment) {
                    float[] arr_f = this.vertices.items;
                    ((RegionAttachment)attachment1).computeWorldVertices(slot1, arr_f, 0, 2);
                    shapeRenderer0.line(arr_f[0], arr_f[1], arr_f[2], arr_f[3]);
                    shapeRenderer0.line(arr_f[2], arr_f[3], arr_f[4], arr_f[5]);
                    shapeRenderer0.line(arr_f[4], arr_f[5], arr_f[6], arr_f[7]);
                    shapeRenderer0.line(arr_f[6], arr_f[7], arr_f[0], arr_f[1]);
                }
            }
        }
        if(this.drawMeshHull || this.drawMeshTriangles) {
            int v8 = array1.size;
            for(int v9 = 0; v9 < v8; ++v9) {
                Slot slot2 = (Slot)array1.get(v9);
                Attachment attachment2 = slot2.attachment;
                if(attachment2 instanceof MeshAttachment) {
                    int v10 = ((MeshAttachment)attachment2).getWorldVerticesLength();
                    float[] arr_f1 = this.vertices.setSize(v10);
                    ((MeshAttachment)attachment2).computeWorldVertices(slot2, 0, ((MeshAttachment)attachment2).getWorldVerticesLength(), arr_f1, 0, 2);
                    short[] arr_v = ((MeshAttachment)attachment2).getTriangles();
                    int v11 = ((MeshAttachment)attachment2).getHullLength();
                    if(this.drawMeshTriangles) {
                        shapeRenderer0.setColor(SkeletonRendererDebug.triangleLineColor);
                        int v12 = arr_v.length;
                        for(int v13 = 0; v13 < v12; v13 += 3) {
                            int v14 = arr_v[v13] * 2;
                            int v15 = arr_v[v13 + 1] * 2;
                            int v16 = arr_v[v13 + 2] * 2;
                            shapeRenderer0.triangle(arr_f1[v14], arr_f1[v14 + 1], arr_f1[v15], arr_f1[v15 + 1], arr_f1[v16], arr_f1[v16 + 1]);
                        }
                    }
                    v17 = v11;
                    if(this.drawMeshHull && v17 > 0) {
                        shapeRenderer0.setColor(SkeletonRendererDebug.attachmentLineColor);
                        float f2 = arr_f1[v17 - 2];
                        float f3 = arr_f1[v17 - 1];
                        float f4 = f2;
                        int v18 = 0;
                        while(v18 < v17) {
                            float f5 = arr_f1[v18];
                            float f6 = arr_f1[v18 + 1];
                            shapeRenderer0.line(f5, f6, f4, f3);
                            v18 += 2;
                            f4 = f5;
                            f3 = f6;
                        }
                    }
                }
            }
        }
        if(this.drawBoundingBoxes) {
            this.bounds.update(skeleton0, true);
            shapeRenderer0.setColor(SkeletonRendererDebug.aabbColor);
            shapeRenderer0.rect(this.bounds.getMinX(), this.bounds.getMinY(), this.bounds.getWidth(), this.bounds.getHeight());
            Array array2 = this.bounds.getPolygons();
            Array array3 = this.bounds.getBoundingBoxes();
            int v19 = array2.size;
            for(int v20 = 0; v20 < v19; ++v20) {
                FloatArray floatArray0 = (FloatArray)array2.get(v20);
                shapeRenderer0.setColor(((BoundingBoxAttachment)array3.get(v20)).getColor());
                shapeRenderer0.polygon(floatArray0.items, 0, floatArray0.size);
            }
        }
        if(this.drawClipping) {
            int v21 = array1.size;
            for(int v22 = 0; v22 < v21; ++v22) {
                Slot slot3 = (Slot)array1.get(v22);
                Attachment attachment3 = slot3.attachment;
                if(attachment3 instanceof ClippingAttachment) {
                    int v23 = ((ClippingAttachment)attachment3).getWorldVerticesLength();
                    float[] arr_f2 = this.vertices.setSize(v23);
                    ((ClippingAttachment)attachment3).computeWorldVertices(slot3, 0, v23, arr_f2, 0, 2);
                    shapeRenderer0.setColor(((ClippingAttachment)attachment3).getColor());
                    for(int v24 = 2; v24 < v23; v24 += 2) {
                        shapeRenderer0.line(arr_f2[v24 - 2], arr_f2[v24 - 1], arr_f2[v24], arr_f2[v24 + 1]);
                    }
                    shapeRenderer0.line(arr_f2[0], arr_f2[1], arr_f2[v23 - 2], arr_f2[v23 - 1]);
                }
            }
        }
        if(this.drawPaths) {
            int v25 = array1.size;
            int v26 = 0;
            while(v26 < v25) {
                Slot slot4 = (Slot)array1.get(v26);
                Attachment attachment4 = slot4.attachment;
                if(attachment4 instanceof PathAttachment) {
                    int v28 = ((PathAttachment)attachment4).getWorldVerticesLength();
                    float[] arr_f3 = this.vertices.setSize(v28);
                    ((PathAttachment)attachment4).computeWorldVertices(slot4, 0, v28, arr_f3, 0, 2);
                    Color color0 = ((PathAttachment)attachment4).getColor();
                    float f7 = arr_f3[2];
                    float f8 = arr_f3[3];
                    if(((PathAttachment)attachment4).getClosed()) {
                        shapeRenderer0.setColor(color0);
                        float f9 = arr_f3[0];
                        float f10 = arr_f3[1];
                        float f11 = arr_f3[v28 - 2];
                        float f12 = arr_f3[v28 - 1];
                        float f13 = arr_f3[v28 - 4];
                        float f14 = arr_f3[v28 - 3];
                        v27 = v25;
                        color1 = color0;
                        v29 = v28;
                        shapeRenderer0.curve(f7, f8, f9, f10, f11, f12, f13, f14, 0x20);
                        shapeRenderer0.setColor(Color.LIGHT_GRAY);
                        f15 = f8;
                        f16 = f7;
                        shapeRenderer0.line(f16, f15, f9, f10);
                        shapeRenderer0.line(f13, f14, f11, f12);
                    }
                    else {
                        v27 = v25;
                        f15 = f8;
                        f16 = f7;
                        color1 = color0;
                        v29 = v28;
                    }
                    int v30 = v29 - 4;
                    float f17 = f16;
                    float f18 = f15;
                    int v31 = 4;
                    while(v31 < v30) {
                        float f19 = arr_f3[v31];
                        float f20 = arr_f3[v31 + 1];
                        float f21 = arr_f3[v31 + 2];
                        float f22 = arr_f3[v31 + 3];
                        float f23 = arr_f3[v31 + 4];
                        float f24 = arr_f3[v31 + 5];
                        shapeRenderer0.setColor(color1);
                        shapeRenderer0.curve(f17, f18, f19, f20, f21, f22, f23, f24, 0x20);
                        shapeRenderer0.setColor(Color.LIGHT_GRAY);
                        shapeRenderer0.line(f17, f18, f19, f20);
                        shapeRenderer0.line(f23, f24, f21, f22);
                        v31 += 6;
                        f17 = f23;
                        f18 = f24;
                    }
                }
                else {
                    v27 = v25;
                }
                ++v26;
                v25 = v27;
            }
        }
        shapeRenderer0.end();
        shapeRenderer0.begin(ShapeType.Filled);
        if(this.drawBones) {
            shapeRenderer0.setColor(SkeletonRendererDebug.boneOriginColor);
            int v32 = array0.size;
            for(int v33 = 0; v33 < v32; ++v33) {
                Bone bone1 = (Bone)array0.get(v33);
                if(bone1.active) {
                    shapeRenderer0.circle(bone1.worldX, bone1.worldY, this.scale * 3.0f, 8);
                }
            }
        }
        if(this.drawPoints) {
            shapeRenderer0.setColor(SkeletonRendererDebug.boneOriginColor);
            int v34 = array1.size;
            for(int v35 = 0; v35 < v34; ++v35) {
                Slot slot5 = (Slot)array1.get(v35);
                Attachment attachment5 = slot5.attachment;
                if(attachment5 instanceof PointAttachment) {
                    ((PointAttachment)attachment5).computeWorldPosition(slot5.getBone(), this.temp1);
                    shapeRenderer0.circle(this.temp1.x, this.temp1.y, this.scale * 3.0f, 8);
                }
            }
        }
        shapeRenderer0.end();
    }

    public ShapeRenderer getShapeRenderer() {
        return this.shapes;
    }

    public void setBones(boolean z) {
        this.drawBones = z;
    }

    public void setBoundingBoxes(boolean z) {
        this.drawBoundingBoxes = z;
    }

    public void setClipping(boolean z) {
        this.drawClipping = z;
    }

    public void setMeshHull(boolean z) {
        this.drawMeshHull = z;
    }

    public void setMeshTriangles(boolean z) {
        this.drawMeshTriangles = z;
    }

    public void setPaths(boolean z) {
        this.drawPaths = z;
    }

    public void setPoints(boolean z) {
        this.drawPoints = z;
    }

    public void setPremultipliedAlpha(boolean z) {
        this.premultipliedAlpha = z;
    }

    public void setRegionAttachments(boolean z) {
        this.drawRegionAttachments = z;
    }

    public void setScale(float f) {
        this.scale = f;
    }
}

