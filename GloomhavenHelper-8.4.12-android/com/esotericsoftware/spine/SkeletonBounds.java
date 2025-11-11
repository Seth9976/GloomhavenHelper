package com.esotericsoftware.spine;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool;
import com.esotericsoftware.spine.attachments.Attachment;
import com.esotericsoftware.spine.attachments.BoundingBoxAttachment;

public class SkeletonBounds {
    private Array boundingBoxes;
    private float maxX;
    private float maxY;
    private float minX;
    private float minY;
    private Pool polygonPool;
    private Array polygons;

    public SkeletonBounds() {
        this.boundingBoxes = new Array();
        this.polygons = new Array();
        this.polygonPool = new Pool() {
            @Override  // com.badlogic.gdx.utils.Pool
            protected Object newObject() {
                return new FloatArray();
            }
        };
    }

    private void aabbCompute() {
        Object[] arr_object = this.polygons.items;
        int v = this.polygons.size;
        float f = 2147483648.0f;
        int v1 = 0;
        float f1 = 2147483648.0f;
        float f2 = -2147483648.0f;
        float f3 = -2147483648.0f;
        while(v1 < v) {
            FloatArray floatArray0 = (FloatArray)arr_object[v1];
            float[] arr_f = floatArray0.items;
            int v2 = floatArray0.size;
            float f4 = f;
            for(int v3 = 0; v3 < v2; v3 += 2) {
                float f5 = arr_f[v3];
                float f6 = arr_f[v3 + 1];
                f4 = Math.min(f4, f5);
                f1 = Math.min(f1, f6);
                f2 = Math.max(f2, f5);
                f3 = Math.max(f3, f6);
            }
            ++v1;
            f = f4;
        }
        this.minX = f;
        this.minY = f1;
        this.maxX = f2;
        this.maxY = f3;
    }

    public boolean aabbContainsPoint(float f, float f1) {
        return f >= this.minX && f <= this.maxX && f1 >= this.minY && f1 <= this.maxY;
    }

    public boolean aabbIntersectsSegment(float f, float f1, float f2, float f3) {
        float f4 = this.minX;
        float f5 = this.minY;
        float f6 = this.maxX;
        float f7 = this.maxY;
        if(f <= f4 && f2 <= f4 || f1 <= f5 && f3 <= f5 || f >= f6 && f2 >= f6 || f1 >= f7 && f3 >= f7) {
            return false;
        }
        float f8 = (f3 - f1) / (f2 - f);
        float f9 = (f4 - f) * f8 + f1;
        if(f9 > f5 && f9 < f7) {
            return true;
        }
        float f10 = (f6 - f) * f8 + f1;
        if(f10 > f5 && f10 < f7) {
            return true;
        }
        float f11 = (f5 - f1) / f8 + f;
        if(f11 > f4 && f11 < f6) {
            return true;
        }
        float f12 = (f7 - f1) / f8 + f;
        return f12 > f4 && f12 < f6;
    }

    public boolean aabbIntersectsSkeleton(SkeletonBounds skeletonBounds0) {
        if(skeletonBounds0 == null) {
            throw new IllegalArgumentException("bounds cannot be null.");
        }
        return this.minX < skeletonBounds0.maxX && this.maxX > skeletonBounds0.minX && this.minY < skeletonBounds0.maxY && this.maxY > skeletonBounds0.minY;
    }

    @Null
    public BoundingBoxAttachment containsPoint(float f, float f1) {
        Object[] arr_object = this.polygons.items;
        int v = this.polygons.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(this.containsPoint(((FloatArray)arr_object[v1]), f, f1)) {
                return (BoundingBoxAttachment)this.boundingBoxes.get(v1);
            }
        }
        return null;
    }

    public boolean containsPoint(FloatArray floatArray0, float f, float f1) {
        if(floatArray0 == null) {
            throw new IllegalArgumentException("polygon cannot be null.");
        }
        float[] arr_f = floatArray0.items;
        int v = floatArray0.size;
        boolean z = false;
        int v1 = v - 2;
        for(int v2 = 0; v2 < v; v2 += 2) {
            float f2 = arr_f[v2 + 1];
            float f3 = arr_f[v1 + 1];
            if(f2 < f1 && f3 >= f1 || f3 < f1 && f2 >= f1) {
                float f4 = arr_f[v2];
                if(f4 + (f1 - f2) / (f3 - f2) * (arr_f[v1] - f4) < f) {
                    z = !z;
                }
            }
            v1 = v2;
        }
        return z;
    }

    public Array getBoundingBoxes() {
        return this.boundingBoxes;
    }

    public float getHeight() {
        return this.maxY - this.minY;
    }

    public float getMaxX() {
        return this.maxX;
    }

    public float getMaxY() {
        return this.maxY;
    }

    public float getMinX() {
        return this.minX;
    }

    public float getMinY() {
        return this.minY;
    }

    @Null
    public FloatArray getPolygon(BoundingBoxAttachment boundingBoxAttachment0) {
        if(boundingBoxAttachment0 == null) {
            throw new IllegalArgumentException("boundingBox cannot be null.");
        }
        int v = this.boundingBoxes.indexOf(boundingBoxAttachment0, true);
        return v == -1 ? null : ((FloatArray)this.polygons.get(v));
    }

    public Array getPolygons() {
        return this.polygons;
    }

    public float getWidth() {
        return this.maxX - this.minX;
    }

    @Null
    public BoundingBoxAttachment intersectsSegment(float f, float f1, float f2, float f3) {
        Object[] arr_object = this.polygons.items;
        int v = this.polygons.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(this.intersectsSegment(((FloatArray)arr_object[v1]), f, f1, f2, f3)) {
                return (BoundingBoxAttachment)this.boundingBoxes.get(v1);
            }
        }
        return null;
    }

    public boolean intersectsSegment(FloatArray floatArray0, float f, float f1, float f2, float f3) {
        if(floatArray0 == null) {
            throw new IllegalArgumentException("polygon cannot be null.");
        }
        float[] arr_f = floatArray0.items;
        int v = floatArray0.size;
        float f4 = f - f2;
        float f5 = f1 - f3;
        float f6 = f * f3 - f1 * f2;
        float f7 = arr_f[v - 2];
        float f8 = arr_f[v - 1];
        float f9 = f7;
        int v1 = 0;
        while(v1 < v) {
            float f10 = arr_f[v1];
            float f11 = arr_f[v1 + 1];
            float f12 = f9 * f11 - f8 * f10;
            float f13 = f9 - f10;
            float f14 = f8 - f11;
            float f15 = f4 * f14 - f5 * f13;
            float f16 = (f13 * f6 - f4 * f12) / f15;
            if((f16 >= f9 && f16 <= f10 || f16 >= f10 && f16 <= f9) && (f16 >= f && f16 <= f2 || f16 >= f2 && f16 <= f)) {
                float f17 = (f14 * f6 - f12 * f5) / f15;
                if((f17 >= f8 && f17 <= f11 || f17 >= f11 && f17 <= f8) && (f17 >= f1 && f17 <= f3 || f17 >= f3 && f17 <= f1)) {
                    return true;
                }
            }
            v1 += 2;
            f9 = f10;
            f8 = f11;
        }
        return false;
    }

    public void update(Skeleton skeleton0, boolean z) {
        if(skeleton0 == null) {
            throw new IllegalArgumentException("skeleton cannot be null.");
        }
        Array array0 = this.boundingBoxes;
        Array array1 = this.polygons;
        Object[] arr_object = skeleton0.slots.items;
        int v = skeleton0.slots.size;
        array0.clear();
        this.polygonPool.freeAll(array1);
        array1.clear();
        for(int v1 = 0; v1 < v; ++v1) {
            Slot slot0 = (Slot)arr_object[v1];
            if(slot0.bone.active) {
                Attachment attachment0 = slot0.attachment;
                if(attachment0 instanceof BoundingBoxAttachment) {
                    array0.add(((BoundingBoxAttachment)attachment0));
                    FloatArray floatArray0 = (FloatArray)this.polygonPool.obtain();
                    array1.add(floatArray0);
                    ((BoundingBoxAttachment)attachment0).computeWorldVertices(slot0, 0, ((BoundingBoxAttachment)attachment0).getWorldVerticesLength(), floatArray0.setSize(((BoundingBoxAttachment)attachment0).getWorldVerticesLength()), 0, 2);
                }
            }
        }
        if(z) {
            this.aabbCompute();
            return;
        }
        this.minX = -2147483648.0f;
        this.minY = -2147483648.0f;
        this.maxX = 2147483648.0f;
        this.maxY = 2147483648.0f;
    }
}

