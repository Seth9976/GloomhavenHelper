package com.esotericsoftware.spine.attachments;

import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.spine.Bone;
import com.esotericsoftware.spine.Slot;
import com.esotericsoftware.spine.utils.SpineUtils;

public abstract class VertexAttachment extends Attachment {
    @Null
    int[] bones;
    private final int id;
    private static int nextID;
    @Null
    Attachment timelineAttachment;
    float[] vertices;
    int worldVerticesLength;

    public VertexAttachment(VertexAttachment vertexAttachment0) {
        super(vertexAttachment0);
        this.id = VertexAttachment.nextID();
        this.timelineAttachment = vertexAttachment0.timelineAttachment;
        int[] arr_v = vertexAttachment0.bones;
        if(arr_v == null) {
            this.bones = null;
        }
        else {
            this.bones = new int[arr_v.length];
            SpineUtils.arraycopy(vertexAttachment0.bones, 0, this.bones, 0, this.bones.length);
        }
        float[] arr_f = vertexAttachment0.vertices;
        if(arr_f == null) {
            this.vertices = null;
        }
        else {
            this.vertices = new float[arr_f.length];
            SpineUtils.arraycopy(vertexAttachment0.vertices, 0, this.vertices, 0, this.vertices.length);
        }
        this.worldVerticesLength = vertexAttachment0.worldVerticesLength;
    }

    public VertexAttachment(String s) {
        super(s);
        this.id = VertexAttachment.nextID();
        this.timelineAttachment = this;
    }

    public void computeWorldVertices(Slot slot0, int v, int v1, float[] arr_f, int v2, int v3) {
        int v4 = v2 + (v1 >> 1) * v3;
        FloatArray floatArray0 = slot0.getDeform();
        float[] arr_f1 = this.vertices;
        int[] arr_v = this.bones;
        if(arr_v == null) {
            if(floatArray0.size > 0) {
                arr_f1 = floatArray0.items;
            }
            Bone bone0 = slot0.getBone();
            float f = bone0.getWorldX();
            float f1 = bone0.getWorldY();
            float f2 = bone0.getA();
            float f3 = bone0.getB();
            float f4 = bone0.getC();
            float f5 = bone0.getD();
            int v5 = v;
            for(int v6 = v2; v6 < v4; v6 += v3) {
                float f6 = arr_f1[v5];
                float f7 = arr_f1[v5 + 1];
                arr_f[v6] = f6 * f2 + f7 * f3 + f;
                arr_f[v6 + 1] = f6 * f4 + f7 * f5 + f1;
                v5 += 2;
            }
            return;
        }
        int v8 = 0;
        int v9 = 0;
        for(int v7 = 0; v7 < v; v7 += 2) {
            int v10 = arr_v[v8];
            v8 += v10 + 1;
            v9 += v10;
        }
        Object[] arr_object = slot0.getSkeleton().getBones().items;
        if(floatArray0.size == 0) {
            int v11 = v9 * 3;
            int v12 = v2;
            while(v12 < v4) {
                int v13 = v8 + 1;
                int v14 = arr_v[v8] + v13;
                int v15 = v11;
                float f8 = 0.0f;
                float f9 = 0.0f;
                while(v13 < v14) {
                    Bone bone1 = (Bone)arr_object[arr_v[v13]];
                    float f10 = arr_f1[v15];
                    float f11 = arr_f1[v15 + 1];
                    float f12 = arr_f1[v15 + 2];
                    f8 += (bone1.getA() * f10 + bone1.getB() * f11 + bone1.getWorldX()) * f12;
                    f9 += (f10 * bone1.getC() + f11 * bone1.getD() + bone1.getWorldY()) * f12;
                    ++v13;
                    v15 += 3;
                }
                arr_f[v12] = f8;
                arr_f[v12 + 1] = f9;
                v12 += v3;
                v8 = v13;
                v11 = v15;
            }
            return;
        }
        float[] arr_f2 = floatArray0.items;
        int v16 = v9 << 1;
        int v17 = v9 * 3;
        int v18 = v2;
        while(v18 < v4) {
            int v19 = v8 + 1;
            int v20 = arr_v[v8] + v19;
            int v21 = v17;
            int v22 = v16;
            float f13 = 0.0f;
            float f14 = 0.0f;
            while(v19 < v20) {
                Bone bone2 = (Bone)arr_object[arr_v[v19]];
                float f15 = arr_f1[v21] + arr_f2[v22];
                float f16 = arr_f1[v21 + 1] + arr_f2[v22 + 1];
                float f17 = arr_f1[v21 + 2];
                f13 += (bone2.getA() * f15 + bone2.getB() * f16 + bone2.getWorldX()) * f17;
                f14 += (f15 * bone2.getC() + f16 * bone2.getD() + bone2.getWorldY()) * f17;
                ++v19;
                v21 += 3;
                v22 += 2;
            }
            arr_f[v18] = f13;
            arr_f[v18 + 1] = f14;
            v18 += v3;
            v8 = v19;
            v17 = v21;
            v16 = v22;
        }
    }

    @Null
    public int[] getBones() {
        return this.bones;
    }

    public int getId() {
        return this.id;
    }

    @Null
    public Attachment getTimelineAttachment() {
        return this.timelineAttachment;
    }

    public float[] getVertices() {
        return this.vertices;
    }

    public int getWorldVerticesLength() {
        return this.worldVerticesLength;
    }

    private static int nextID() {
        int v;
        synchronized(VertexAttachment.class) {
            v = VertexAttachment.nextID;
            VertexAttachment.nextID = v + 1;
        }
        return v;
    }

    public void setBones(@Null int[] arr_v) {
        this.bones = arr_v;
    }

    public void setTimelineAttachment(Attachment attachment0) {
        this.timelineAttachment = attachment0;
    }

    public void setVertices(float[] arr_f) {
        this.vertices = arr_f;
    }

    public void setWorldVerticesLength(int v) {
        this.worldVerticesLength = v;
    }
}

