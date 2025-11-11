package com.esotericsoftware.spine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectSet;
import com.esotericsoftware.spine.attachments.Attachment;
import com.esotericsoftware.spine.attachments.HasTextureRegion;
import com.esotericsoftware.spine.attachments.Sequence.SequenceMode;
import com.esotericsoftware.spine.attachments.VertexAttachment;
import com.esotericsoftware.spine.utils.SpineUtils;

public class Animation {
    public static class AlphaTimeline extends CurveTimeline1 implements SlotTimeline {
        final int slotIndex;

        public AlphaTimeline(int v, int v1, int v2) {
            super(v, v1, Property.alpha.ordinal() + "|" + v2);
            this.slotIndex = v2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            Slot slot0 = (Slot)skeleton0.slots.get(this.slotIndex);
            if(!slot0.bone.active) {
                return;
            }
            Color color0 = slot0.color;
            if(f1 < this.frames[0]) {
                Color color1 = slot0.data.color;
                switch(animation$MixBlend0) {
                    case once: {
                        color0.a = color1.a;
                        return;
                    }
                    case loop: {
                        color0.a += (color1.a - color0.a) * f2;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            float f3 = this.getCurveValue(f1);
            if(f2 == 1.0f) {
                color0.a = f3;
                return;
            }
            if(animation$MixBlend0 == MixBlend.setup) {
                color0.a = slot0.data.color.a;
            }
            color0.a += (f3 - color0.a) * f2;
        }

        @Override  // com.esotericsoftware.spine.Animation$SlotTimeline
        public int getSlotIndex() {
            return this.slotIndex;
        }
    }

    public static class AttachmentTimeline extends Timeline implements SlotTimeline {
        final String[] attachmentNames;
        final int slotIndex;

        public AttachmentTimeline(int v, int v1) {
            super(v, new String[]{Property.attachment.ordinal() + "|" + v1});
            this.slotIndex = v1;
            this.attachmentNames = new String[v];
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            Slot slot0 = (Slot)skeleton0.slots.get(this.slotIndex);
            if(!slot0.bone.active) {
                return;
            }
            if(animation$MixDirection0 == MixDirection.out) {
                if(animation$MixBlend0 == MixBlend.setup) {
                    this.setAttachment(skeleton0, slot0, slot0.data.attachmentName);
                }
                return;
            }
            if(f1 < this.frames[0]) {
                if(animation$MixBlend0 == MixBlend.setup || animation$MixBlend0 == MixBlend.first) {
                    this.setAttachment(skeleton0, slot0, slot0.data.attachmentName);
                }
                return;
            }
            this.setAttachment(skeleton0, slot0, this.attachmentNames[AttachmentTimeline.search(this.frames, f1)]);
        }

        public String[] getAttachmentNames() {
            return this.attachmentNames;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public int getFrameCount() {
            return this.frames.length;
        }

        @Override  // com.esotericsoftware.spine.Animation$SlotTimeline
        public int getSlotIndex() {
            return this.slotIndex;
        }

        private void setAttachment(Skeleton skeleton0, Slot slot0, String s) {
            slot0.setAttachment((s == null ? null : skeleton0.getAttachment(this.slotIndex, s)));
        }

        public void setFrame(int v, float f, String s) {
            this.frames[v] = f;
            this.attachmentNames[v] = s;
        }
    }

    public interface BoneTimeline {
        int getBoneIndex();
    }

    public static abstract class CurveTimeline1 extends CurveTimeline {
        public static final int ENTRIES = 2;
        static final int VALUE = 1;

        public CurveTimeline1(int v, int v1, String s) {
            super(v, v1, new String[]{s});
        }

        public float getCurveValue(float f) {
            float[] arr_f = this.frames;
            int v = arr_f.length - 2;
            for(int v1 = 2; v1 <= v; v1 += 2) {
                if(arr_f[v1] > f) {
                    v = v1 - 2;
                    break;
                }
            }
            int v2 = (int)this.curves[v >> 1];
            switch(v2) {
                case 0: {
                    float f1 = arr_f[v];
                    float f2 = arr_f[v + 1];
                    return f2 + (f - f1) / (arr_f[v + 2] - f1) * (arr_f[v + 3] - f2);
                }
                case 1: {
                    return arr_f[v + 1];
                }
                default: {
                    return this.getBezierValue(f, v, 1, v2 - 2);
                }
            }
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public int getFrameEntries() {
            return 2;
        }

        public void setFrame(int v, float f, float f1) {
            this.frames[v << 1] = f;
            this.frames[(v << 1) + 1] = f1;
        }
    }

    public static abstract class CurveTimeline2 extends CurveTimeline {
        public static final int ENTRIES = 3;
        static final int VALUE1 = 1;
        static final int VALUE2 = 2;

        public CurveTimeline2(int v, int v1, String s, String s1) {
            super(v, v1, new String[]{s, s1});
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public int getFrameEntries() {
            return 3;
        }

        public void setFrame(int v, float f, float f1, float f2) {
            this.frames[v * 3] = f;
            this.frames[v * 3 + 1] = f1;
            this.frames[v * 3 + 2] = f2;
        }
    }

    public static abstract class CurveTimeline extends Timeline {
        public static final int BEZIER = 2;
        public static final int BEZIER_SIZE = 18;
        public static final int LINEAR = 0;
        public static final int STEPPED = 1;
        float[] curves;

        public CurveTimeline(int v, int v1, String[] arr_s) {
            super(v, arr_s);
            this.curves = new float[v1 * 18 + v];
            this.curves[v - 1] = 1.0f;
        }

        public float getBezierValue(float f, int v, int v1, int v2) {
            float[] arr_f = this.curves;
            if(arr_f[v2] > f) {
                float f1 = this.frames[v];
                float f2 = this.frames[v + v1];
                return f2 + (f - f1) / (arr_f[v2] - f1) * (arr_f[v2 + 1] - f2);
            }
            int v3 = v2 + 18;
            while(true) {
                v2 += 2;
                if(v2 >= v3) {
                    break;
                }
                if(arr_f[v2] >= f) {
                    float f3 = arr_f[v2 - 2];
                    float f4 = arr_f[v2 - 1];
                    return f4 + (f - f3) / (arr_f[v2] - f3) * (arr_f[v2 + 1] - f4);
                }
            }
            int v4 = this.getFrameEntries();
            float f5 = arr_f[v3 - 2];
            float f6 = arr_f[v3 - 1];
            return f6 + (f - f5) / (this.frames[v + v4] - f5) * (this.frames[v + v4 + v1] - f6);
        }

        public int getCurveType(int v) {
            return (int)this.curves[v];
        }

        public void setBezier(int v, int v1, int v2, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7) {
            float[] arr_f = this.curves;
            int v3 = this.getFrameCount() + v * 18;
            if(v2 == 0) {
                arr_f[v1] = (float)(v3 + 2);
            }
            float f8 = (f - f2 * 2.0f + f4) * 0.03f;
            float f9 = (f1 - f3 * 2.0f + f5) * 0.03f;
            float f10 = ((f2 - f4) * 3.0f - f + f6) * 0.006f;
            float f11 = ((f3 - f5) * 3.0f - f1 + f7) * 0.006f;
            float f12 = f8 * 2.0f + f10;
            float f13 = 2.0f * f9 + f11;
            float f14 = (f2 - f) * 0.3f + f8 + f10 * 0.166667f;
            float f15 = (f3 - f1) * 0.3f + f9 + 0.166667f * f11;
            float f16 = f + f14;
            float f17 = f1 + f15;
            int v4 = v3 + 18;
            while(v3 < v4) {
                arr_f[v3] = f16;
                arr_f[v3 + 1] = f17;
                f14 += f12;
                f15 += f13;
                f12 += f10;
                f13 += f11;
                f16 += f14;
                f17 += f15;
                v3 += 2;
            }
        }

        public void setLinear(int v) {
            this.curves[v] = 0.0f;
        }

        public void setStepped(int v) {
            this.curves[v] = 1.0f;
        }

        public void shrink(int v) {
            int v1 = this.getFrameCount() + v * 18;
            float[] arr_f = this.curves;
            if(arr_f.length > v1) {
                float[] arr_f1 = new float[v1];
                SpineUtils.arraycopy(arr_f, 0, arr_f1, 0, v1);
                this.curves = arr_f1;
            }
        }
    }

    public static class DeformTimeline extends CurveTimeline implements SlotTimeline {
        final VertexAttachment attachment;
        final int slotIndex;
        private final float[][] vertices;

        public DeformTimeline(int v, int v1, int v2, VertexAttachment vertexAttachment0) {
            super(v, v1, new String[]{Property.deform.ordinal() + "|" + v2 + "|" + vertexAttachment0.getId()});
            this.slotIndex = v2;
            this.attachment = vertexAttachment0;
            this.vertices = new float[v][];
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            Slot slot0 = (Slot)skeleton0.slots.get(this.slotIndex);
            if(!slot0.bone.active) {
                return;
            }
            Attachment attachment0 = slot0.attachment;
            if(attachment0 instanceof VertexAttachment && ((VertexAttachment)attachment0).getTimelineAttachment() == this.attachment) {
                FloatArray floatArray0 = slot0.deform;
                if(floatArray0.size == 0) {
                    animation$MixBlend0 = MixBlend.setup;
                }
                float[][] arr2_f = this.vertices;
                int v = 0;
                int v1 = arr2_f[0].length;
                float[] arr_f = this.frames;
                if(f1 < arr_f[0]) {
                    switch(animation$MixBlend0) {
                        case once: {
                            floatArray0.clear();
                            return;
                        }
                        case loop: {
                            if(f2 == 1.0f) {
                                floatArray0.clear();
                                return;
                            }
                            float[] arr_f1 = floatArray0.setSize(v1);
                            if(((VertexAttachment)attachment0).getBones() == null) {
                                float[] arr_f2 = ((VertexAttachment)attachment0).getVertices();
                                while(v < v1) {
                                    arr_f1[v] += (arr_f2[v] - arr_f1[v]) * f2;
                                    ++v;
                                }
                                return;
                            }
                            while(v < v1) {
                                arr_f1[v] *= 1.0f - f2;
                                ++v;
                            }
                            return;
                        }
                        default: {
                            return;
                        }
                    }
                }
                float[] arr_f3 = floatArray0.setSize(v1);
                if(f1 >= arr_f[arr_f.length - 1]) {
                    float[] arr_f4 = arr2_f[arr_f.length - 1];
                    if(f2 == 1.0f) {
                        if(animation$MixBlend0 == MixBlend.add) {
                            if(((VertexAttachment)attachment0).getBones() == null) {
                                float[] arr_f5 = ((VertexAttachment)attachment0).getVertices();
                                while(v < v1) {
                                    arr_f3[v] += arr_f4[v] - arr_f5[v];
                                    ++v;
                                }
                                return;
                            }
                            while(v < v1) {
                                arr_f3[v] += arr_f4[v];
                                ++v;
                            }
                            return;
                        }
                        SpineUtils.arraycopy(arr_f4, 0, arr_f3, 0, v1);
                        return;
                    }
                    switch(animation$MixBlend0) {
                        case once: {
                            if(((VertexAttachment)attachment0).getBones() == null) {
                                float[] arr_f6 = ((VertexAttachment)attachment0).getVertices();
                                while(v < v1) {
                                    float f3 = arr_f6[v];
                                    arr_f3[v] = f3 + (arr_f4[v] - f3) * f2;
                                    ++v;
                                }
                                return;
                            }
                            while(v < v1) {
                                arr_f3[v] = arr_f4[v] * f2;
                                ++v;
                            }
                            return;
                        }
                        case loop: 
                        case pingpong: {
                            while(v < v1) {
                                arr_f3[v] += (arr_f4[v] - arr_f3[v]) * f2;
                                ++v;
                            }
                            return;
                        }
                        case onceReverse: {
                            if(((VertexAttachment)attachment0).getBones() == null) {
                                float[] arr_f7 = ((VertexAttachment)attachment0).getVertices();
                                while(v < v1) {
                                    arr_f3[v] += (arr_f4[v] - arr_f7[v]) * f2;
                                    ++v;
                                }
                                return;
                            }
                            while(v < v1) {
                                arr_f3[v] += arr_f4[v] * f2;
                                ++v;
                            }
                            return;
                        }
                        default: {
                            return;
                        }
                    }
                }
                int v2 = DeformTimeline.search(arr_f, f1);
                float f4 = this.getCurvePercent(f1, v2);
                float[] arr_f8 = arr2_f[v2];
                float[] arr_f9 = arr2_f[v2 + 1];
                if(f2 == 1.0f) {
                    if(animation$MixBlend0 == MixBlend.add) {
                        if(((VertexAttachment)attachment0).getBones() == null) {
                            float[] arr_f10 = ((VertexAttachment)attachment0).getVertices();
                            while(v < v1) {
                                float f5 = arr_f8[v];
                                arr_f3[v] += f5 + (arr_f9[v] - f5) * f4 - arr_f10[v];
                                ++v;
                            }
                            return;
                        }
                        while(v < v1) {
                            float f6 = arr_f8[v];
                            arr_f3[v] += f6 + (arr_f9[v] - f6) * f4;
                            ++v;
                        }
                        return;
                    }
                    while(v < v1) {
                        float f7 = arr_f8[v];
                        arr_f3[v] = f7 + (arr_f9[v] - f7) * f4;
                        ++v;
                    }
                    return;
                }
                switch(animation$MixBlend0) {
                    case once: {
                        if(((VertexAttachment)attachment0).getBones() == null) {
                            float[] arr_f11 = ((VertexAttachment)attachment0).getVertices();
                            while(v < v1) {
                                float f8 = arr_f8[v];
                                float f9 = arr_f11[v];
                                arr_f3[v] = f9 + (f8 + (arr_f9[v] - f8) * f4 - f9) * f2;
                                ++v;
                            }
                            return;
                        }
                        while(v < v1) {
                            float f10 = arr_f8[v];
                            arr_f3[v] = (f10 + (arr_f9[v] - f10) * f4) * f2;
                            ++v;
                        }
                        return;
                    }
                    case loop: 
                    case pingpong: {
                        while(v < v1) {
                            float f11 = arr_f8[v];
                            arr_f3[v] += (f11 + (arr_f9[v] - f11) * f4 - arr_f3[v]) * f2;
                            ++v;
                        }
                        return;
                    }
                    case onceReverse: {
                        if(((VertexAttachment)attachment0).getBones() == null) {
                            float[] arr_f12 = ((VertexAttachment)attachment0).getVertices();
                            while(v < v1) {
                                float f12 = arr_f8[v];
                                arr_f3[v] += (f12 + (arr_f9[v] - f12) * f4 - arr_f12[v]) * f2;
                                ++v;
                            }
                            return;
                        }
                        while(v < v1) {
                            float f13 = arr_f8[v];
                            arr_f3[v] += (f13 + (arr_f9[v] - f13) * f4) * f2;
                            ++v;
                        }
                    }
                }
            }
        }

        public VertexAttachment getAttachment() {
            return this.attachment;
        }

        private float getCurvePercent(float f, int v) {
            float[] arr_f = this.curves;
            int v1 = (int)arr_f[v];
            switch(v1) {
                case 0: {
                    float f6 = this.frames[v];
                    float[] arr_f2 = this.frames;
                    return (f - f6) / (arr_f2[v + this.getFrameEntries()] - f6);
                }
                case 1: {
                    return 0.0f;
                }
                default: {
                    int v2 = v1 - 2;
                    if(arr_f[v2] > f) {
                        float f1 = this.frames[v];
                        return arr_f[v2 + 1] * (f - f1) / (arr_f[v2] - f1);
                    }
                    int v3 = v2 + 18;
                    while(true) {
                        v2 += 2;
                        if(v2 >= v3) {
                            break;
                        }
                        if(arr_f[v2] >= f) {
                            float f2 = arr_f[v2 - 2];
                            float f3 = arr_f[v2 - 1];
                            return f3 + (f - f2) / (arr_f[v2] - f2) * (arr_f[v2 + 1] - f3);
                        }
                    }
                    float f4 = arr_f[v3 - 2];
                    float f5 = arr_f[v3 - 1];
                    float[] arr_f1 = this.frames;
                    return f5 + (1.0f - f5) * (f - f4) / (arr_f1[v + this.getFrameEntries()] - f4);
                }
            }
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public int getFrameCount() {
            return this.frames.length;
        }

        @Override  // com.esotericsoftware.spine.Animation$SlotTimeline
        public int getSlotIndex() {
            return this.slotIndex;
        }

        public float[][] getVertices() {
            return this.vertices;
        }

        @Override  // com.esotericsoftware.spine.Animation$CurveTimeline
        public void setBezier(int v, int v1, int v2, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7) {
            float[] arr_f = this.curves;
            int v3 = this.getFrameCount() + v * 18;
            if(v2 == 0) {
                arr_f[v1] = (float)(v3 + 2);
            }
            float f8 = (f - f2 * 2.0f + f4) * 0.03f;
            float f9 = 0.03f * f5 - 0.06f * f3;
            float f10 = ((f2 - f4) * 3.0f - f + f6) * 0.006f;
            float f11 = (f3 - f5 + 0.333333f) * 0.018f;
            float f12 = f8 * 2.0f + f10;
            float f13 = (f2 - f) * 0.3f + f8 + f10 * 0.166667f;
            float f14 = f3 * 0.3f + f9 + 0.166667f * f11;
            float f15 = f + f13;
            int v4 = v3 + 18;
            float f16 = 2.0f * f9 + f11;
            float f17 = f14;
            while(v3 < v4) {
                arr_f[v3] = f15;
                arr_f[v3 + 1] = f14;
                f13 += f12;
                f17 += f16;
                f12 += f10;
                f16 += f11;
                f15 += f13;
                f14 += f17;
                v3 += 2;
            }
        }

        public void setFrame(int v, float f, float[] arr_f) {
            this.frames[v] = f;
            this.vertices[v] = arr_f;
        }
    }

    public static class DrawOrderTimeline extends Timeline {
        private final int[][] drawOrders;
        private static final String[] propertyIds;

        static {
            DrawOrderTimeline.propertyIds = new String[]{"13"};
        }

        public DrawOrderTimeline(int v) {
            super(v, DrawOrderTimeline.propertyIds);
            this.drawOrders = new int[v][];
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            if(animation$MixDirection0 == MixDirection.out) {
                if(animation$MixBlend0 == MixBlend.setup) {
                    SpineUtils.arraycopy(skeleton0.slots.items, 0, skeleton0.drawOrder.items, 0, skeleton0.slots.size);
                }
                return;
            }
            if(f1 < this.frames[0]) {
                if(animation$MixBlend0 == MixBlend.setup || animation$MixBlend0 == MixBlend.first) {
                    SpineUtils.arraycopy(skeleton0.slots.items, 0, skeleton0.drawOrder.items, 0, skeleton0.slots.size);
                }
                return;
            }
            int[] arr_v = this.drawOrders[DrawOrderTimeline.search(this.frames, f1)];
            if(arr_v == null) {
                SpineUtils.arraycopy(skeleton0.slots.items, 0, skeleton0.drawOrder.items, 0, skeleton0.slots.size);
                return;
            }
            Object[] arr_object = skeleton0.slots.items;
            Object[] arr_object1 = skeleton0.drawOrder.items;
            for(int v = 0; v < arr_v.length; ++v) {
                arr_object1[v] = arr_object[arr_v[v]];
            }
        }

        public int[][] getDrawOrders() {
            return this.drawOrders;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public int getFrameCount() {
            return this.frames.length;
        }

        public void setFrame(int v, float f, @Null int[] arr_v) {
            this.frames[v] = f;
            this.drawOrders[v] = arr_v;
        }
    }

    public static class EventTimeline extends Timeline {
        private final Event[] events;
        private static final String[] propertyIds;

        static {
            EventTimeline.propertyIds = new String[]{"12"};
        }

        public EventTimeline(int v) {
            super(v, EventTimeline.propertyIds);
            this.events = new Event[v];
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            int v;
            float f3;
            if(array0 == null) {
                return;
            }
            float[] arr_f = this.frames;
            if(f > f1) {
                this.apply(skeleton0, f, 2147483648.0f, array0, f2, animation$MixBlend0, animation$MixDirection0);
                f3 = -1.0f;
            }
            else {
                if(f >= arr_f[arr_f.length - 1]) {
                    return;
                }
                f3 = f;
            }
            if(f1 < arr_f[0]) {
                return;
            }
            if(f3 < arr_f[0]) {
                v = 0;
            }
            else {
                v = EventTimeline.search(arr_f, f3) + 1;
                float f4 = arr_f[v];
                while(v > 0 && arr_f[v - 1] == f4) {
                    --v;
                }
            }
            while(v < arr_f.length && f1 >= arr_f[v]) {
                array0.add(this.events[v]);
                ++v;
            }
        }

        public Event[] getEvents() {
            return this.events;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public int getFrameCount() {
            return this.frames.length;
        }

        public void setFrame(int v, Event event0) {
            this.frames[v] = event0.time;
            this.events[v] = event0;
        }
    }

    public static class IkConstraintTimeline extends CurveTimeline {
        private static final int BEND_DIRECTION = 3;
        private static final int COMPRESS = 4;
        public static final int ENTRIES = 6;
        private static final int MIX = 1;
        private static final int SOFTNESS = 2;
        private static final int STRETCH = 5;
        final int ikConstraintIndex;

        public IkConstraintTimeline(int v, int v1, int v2) {
            super(v, v1, new String[]{Property.ikConstraint.ordinal() + "|" + v2});
            this.ikConstraintIndex = v2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            float f4;
            float f3;
            IkConstraint ikConstraint0 = (IkConstraint)skeleton0.ikConstraints.get(this.ikConstraintIndex);
            if(!ikConstraint0.active) {
                return;
            }
            float[] arr_f = this.frames;
            if(f1 < arr_f[0]) {
                switch(animation$MixBlend0) {
                    case once: {
                        ikConstraint0.mix = ikConstraint0.data.mix;
                        ikConstraint0.softness = ikConstraint0.data.softness;
                        ikConstraint0.bendDirection = ikConstraint0.data.bendDirection;
                        ikConstraint0.compress = ikConstraint0.data.compress;
                        ikConstraint0.stretch = ikConstraint0.data.stretch;
                        return;
                    }
                    case loop: {
                        ikConstraint0.mix += (ikConstraint0.data.mix - ikConstraint0.mix) * f2;
                        ikConstraint0.softness += (ikConstraint0.data.softness - ikConstraint0.softness) * f2;
                        ikConstraint0.bendDirection = ikConstraint0.data.bendDirection;
                        ikConstraint0.compress = ikConstraint0.data.compress;
                        ikConstraint0.stretch = ikConstraint0.data.stretch;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            int v = IkConstraintTimeline.search(arr_f, f1, 6);
            int v1 = (int)this.curves[v / 6];
            boolean z = true;
            switch(v1) {
                case 0: {
                    float f5 = arr_f[v];
                    float f6 = arr_f[v + 1];
                    float f7 = arr_f[v + 2];
                    float f8 = (f1 - f5) / (arr_f[v + 6] - f5);
                    f3 = (arr_f[v + 7] - f6) * f8 + f6;
                    f4 = f7 + (arr_f[v + 8] - f7) * f8;
                    break;
                }
                case 1: {
                    float f9 = arr_f[v + 1];
                    f4 = arr_f[v + 2];
                    f3 = f9;
                    break;
                }
                default: {
                    f3 = this.getBezierValue(f1, v, 1, v1 - 2);
                    f4 = this.getBezierValue(f1, v, 2, v1 + 16);
                }
            }
            if(animation$MixBlend0 == MixBlend.setup) {
                ikConstraint0.mix = ikConstraint0.data.mix + (f3 - ikConstraint0.data.mix) * f2;
                ikConstraint0.softness = ikConstraint0.data.softness + (f4 - ikConstraint0.data.softness) * f2;
                if(animation$MixDirection0 == MixDirection.out) {
                    ikConstraint0.bendDirection = ikConstraint0.data.bendDirection;
                    ikConstraint0.compress = ikConstraint0.data.compress;
                    ikConstraint0.stretch = ikConstraint0.data.stretch;
                    return;
                }
                ikConstraint0.bendDirection = (int)arr_f[v + 3];
                ikConstraint0.compress = arr_f[v + 4] != 0.0f;
                if(arr_f[v + 5] == 0.0f) {
                    z = false;
                }
                ikConstraint0.stretch = z;
                return;
            }
            ikConstraint0.mix += (f3 - ikConstraint0.mix) * f2;
            ikConstraint0.softness += (f4 - ikConstraint0.softness) * f2;
            if(animation$MixDirection0 == MixDirection.in) {
                ikConstraint0.bendDirection = (int)arr_f[v + 3];
                ikConstraint0.compress = arr_f[v + 4] != 0.0f;
                if(arr_f[v + 5] == 0.0f) {
                    z = false;
                }
                ikConstraint0.stretch = z;
            }
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public int getFrameEntries() {
            return 6;
        }

        public int getIkConstraintIndex() {
            return this.ikConstraintIndex;
        }

        public void setFrame(int v, float f, float f1, float f2, int v1, boolean z, boolean z1) {
            this.frames[v * 6] = f;
            this.frames[v * 6 + 1] = f1;
            this.frames[v * 6 + 2] = f2;
            this.frames[v * 6 + 3] = (float)v1;
            float f3 = 1.0f;
            this.frames[v * 6 + 4] = z ? 1.0f : 0.0f;
            float[] arr_f = this.frames;
            if(!z1) {
                f3 = 0.0f;
            }
            arr_f[v * 6 + 5] = f3;
        }
    }

    public static enum MixBlend {
        setup,
        first,
        replace,
        add;

    }

    public static enum MixDirection {
        in,
        out;

    }

    public static class PathConstraintMixTimeline extends CurveTimeline {
        public static final int ENTRIES = 4;
        private static final int ROTATE = 1;
        private static final int X = 2;
        private static final int Y = 3;
        final int pathConstraintIndex;

        public PathConstraintMixTimeline(int v, int v1, int v2) {
            super(v, v1, new String[]{Property.pathConstraintMix.ordinal() + "|" + v2});
            this.pathConstraintIndex = v2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            float f5;
            float f4;
            float f3;
            PathConstraint pathConstraint0 = (PathConstraint)skeleton0.pathConstraints.get(this.pathConstraintIndex);
            if(!pathConstraint0.active) {
                return;
            }
            float[] arr_f = this.frames;
            if(f1 < arr_f[0]) {
                PathConstraintData pathConstraintData0 = pathConstraint0.data;
                switch(animation$MixBlend0) {
                    case once: {
                        pathConstraint0.mixRotate = pathConstraintData0.mixRotate;
                        pathConstraint0.mixX = pathConstraintData0.mixX;
                        pathConstraint0.mixY = pathConstraintData0.mixY;
                        return;
                    }
                    case loop: {
                        pathConstraint0.mixRotate += (pathConstraintData0.mixRotate - pathConstraint0.mixRotate) * f2;
                        pathConstraint0.mixX += (pathConstraintData0.mixX - pathConstraint0.mixX) * f2;
                        pathConstraint0.mixY += (pathConstraintData0.mixY - pathConstraint0.mixY) * f2;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            int v = PathConstraintMixTimeline.search(arr_f, f1, 4);
            int v1 = (int)this.curves[v >> 2];
            switch(v1) {
                case 0: {
                    float f6 = arr_f[v];
                    float f7 = arr_f[v + 1];
                    float f8 = arr_f[v + 2];
                    float f9 = arr_f[v + 3];
                    float f10 = (f1 - f6) / (arr_f[v + 4] - f6);
                    float f11 = (arr_f[v + 5] - f7) * f10 + f7;
                    float f12 = (arr_f[v + 6] - f8) * f10 + f8;
                    f5 = (arr_f[v + 7] - f9) * f10 + f9;
                    f3 = f11;
                    f4 = f12;
                    break;
                }
                case 1: {
                    float f13 = arr_f[v + 1];
                    f4 = arr_f[v + 2];
                    f5 = arr_f[v + 3];
                    f3 = f13;
                    break;
                }
                default: {
                    f3 = this.getBezierValue(f1, v, 1, v1 - 2);
                    f4 = this.getBezierValue(f1, v, 2, v1 + 16);
                    f5 = this.getBezierValue(f1, v, 3, v1 + 34);
                }
            }
            if(animation$MixBlend0 == MixBlend.setup) {
                pathConstraint0.mixRotate = pathConstraint0.data.mixRotate + (f3 - pathConstraint0.data.mixRotate) * f2;
                pathConstraint0.mixX = pathConstraint0.data.mixX + (f4 - pathConstraint0.data.mixX) * f2;
                pathConstraint0.mixY = pathConstraint0.data.mixY + (f5 - pathConstraint0.data.mixY) * f2;
                return;
            }
            pathConstraint0.mixRotate += (f3 - pathConstraint0.mixRotate) * f2;
            pathConstraint0.mixX += (f4 - pathConstraint0.mixX) * f2;
            pathConstraint0.mixY += (f5 - pathConstraint0.mixY) * f2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public int getFrameEntries() {
            return 4;
        }

        public int getPathConstraintIndex() {
            return this.pathConstraintIndex;
        }

        public void setFrame(int v, float f, float f1, float f2, float f3) {
            this.frames[v << 2] = f;
            this.frames[(v << 2) + 1] = f1;
            this.frames[(v << 2) + 2] = f2;
            this.frames[(v << 2) + 3] = f3;
        }
    }

    public static class PathConstraintPositionTimeline extends CurveTimeline1 {
        final int pathConstraintIndex;

        public PathConstraintPositionTimeline(int v, int v1, int v2) {
            super(v, v1, Property.pathConstraintPosition.ordinal() + "|" + v2);
            this.pathConstraintIndex = v2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            PathConstraint pathConstraint0 = (PathConstraint)skeleton0.pathConstraints.get(this.pathConstraintIndex);
            if(!pathConstraint0.active) {
                return;
            }
            if(f1 < this.frames[0]) {
                switch(animation$MixBlend0) {
                    case once: {
                        pathConstraint0.position = pathConstraint0.data.position;
                        return;
                    }
                    case loop: {
                        pathConstraint0.position += (pathConstraint0.data.position - pathConstraint0.position) * f2;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            float f3 = this.getCurveValue(f1);
            if(animation$MixBlend0 == MixBlend.setup) {
                pathConstraint0.position = pathConstraint0.data.position + (f3 - pathConstraint0.data.position) * f2;
                return;
            }
            pathConstraint0.position += (f3 - pathConstraint0.position) * f2;
        }

        public int getPathConstraintIndex() {
            return this.pathConstraintIndex;
        }
    }

    public static class PathConstraintSpacingTimeline extends CurveTimeline1 {
        final int pathConstraintIndex;

        public PathConstraintSpacingTimeline(int v, int v1, int v2) {
            super(v, v1, Property.pathConstraintSpacing.ordinal() + "|" + v2);
            this.pathConstraintIndex = v2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            PathConstraint pathConstraint0 = (PathConstraint)skeleton0.pathConstraints.get(this.pathConstraintIndex);
            if(!pathConstraint0.active) {
                return;
            }
            if(f1 < this.frames[0]) {
                switch(animation$MixBlend0) {
                    case once: {
                        pathConstraint0.spacing = pathConstraint0.data.spacing;
                        return;
                    }
                    case loop: {
                        pathConstraint0.spacing += (pathConstraint0.data.spacing - pathConstraint0.spacing) * f2;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            float f3 = this.getCurveValue(f1);
            if(animation$MixBlend0 == MixBlend.setup) {
                pathConstraint0.spacing = pathConstraint0.data.spacing + (f3 - pathConstraint0.data.spacing) * f2;
                return;
            }
            pathConstraint0.spacing += (f3 - pathConstraint0.spacing) * f2;
        }

        public int getPathConstraintIndex() {
            return this.pathConstraintIndex;
        }
    }

    static enum Property {
        rotate,
        x,
        y,
        scaleX,
        scaleY,
        shearX,
        shearY,
        rgb,
        alpha,
        rgb2,
        attachment,
        deform,
        event,
        drawOrder,
        ikConstraint,
        transformConstraint,
        pathConstraintPosition,
        pathConstraintSpacing,
        pathConstraintMix,
        sequence;

    }

    public static class RGB2Timeline extends CurveTimeline implements SlotTimeline {
        private static final int B = 3;
        private static final int B2 = 6;
        public static final int ENTRIES = 7;
        private static final int G = 2;
        private static final int G2 = 5;
        private static final int R = 1;
        private static final int R2 = 4;
        final int slotIndex;

        public RGB2Timeline(int v, int v1, int v2) {
            super(v, v1, new String[]{Property.rgb.ordinal() + "|" + v2, Property.rgb2.ordinal() + "|" + v2});
            this.slotIndex = v2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            float f9;
            float f8;
            float f7;
            float f6;
            float f5;
            float f4;
            Slot slot0 = (Slot)skeleton0.slots.get(this.slotIndex);
            if(!slot0.bone.active) {
                return;
            }
            float[] arr_f = this.frames;
            Color color0 = slot0.color;
            Color color1 = slot0.darkColor;
            if(f1 < arr_f[0]) {
                Color color2 = slot0.data.color;
                Color color3 = slot0.data.darkColor;
                switch(animation$MixBlend0) {
                    case once: {
                        color0.r = color2.r;
                        color0.g = color2.g;
                        color0.b = color2.b;
                        color1.r = color3.r;
                        color1.g = color3.g;
                        color1.b = color3.b;
                        return;
                    }
                    case loop: {
                        color0.r += (color2.r - color0.r) * f2;
                        color0.g += (color2.g - color0.g) * f2;
                        color0.b += (color2.b - color0.b) * f2;
                        color1.r += (color3.r - color1.r) * f2;
                        color1.g += (color3.g - color1.g) * f2;
                        color1.b += (color3.b - color1.b) * f2;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            int v = RGB2Timeline.search(arr_f, f1, 7);
            int v1 = (int)this.curves[v / 7];
            switch(v1) {
                case 0: {
                    float f10 = arr_f[v];
                    float f11 = arr_f[v + 1];
                    float f12 = arr_f[v + 2];
                    float f13 = arr_f[v + 3];
                    float f14 = arr_f[v + 4];
                    float f15 = arr_f[v + 5];
                    float f16 = arr_f[v + 6];
                    float f17 = (f1 - f10) / (arr_f[v + 7] - f10);
                    float f18 = (arr_f[v + 8] - f11) * f17 + f11;
                    float f19 = (arr_f[v + 9] - f12) * f17 + f12;
                    float f20 = (arr_f[v + 10] - f13) * f17 + f13;
                    float f21 = (arr_f[v + 11] - f14) * f17 + f14;
                    float f22 = (arr_f[v + 12] - f15) * f17 + f15;
                    f8 = (arr_f[v + 13] - f16) * f17 + f16;
                    f9 = f18;
                    f4 = f19;
                    f5 = f20;
                    f6 = f21;
                    f7 = f22;
                    break;
                }
                case 1: {
                    f9 = arr_f[v + 1];
                    f4 = arr_f[v + 2];
                    f5 = arr_f[v + 3];
                    f6 = arr_f[v + 4];
                    f7 = arr_f[v + 5];
                    f8 = arr_f[v + 6];
                    break;
                }
                default: {
                    float f3 = this.getBezierValue(f1, v, 1, v1 - 2);
                    f4 = this.getBezierValue(f1, v, 2, v1 + 16);
                    f5 = this.getBezierValue(f1, v, 3, v1 + 34);
                    f6 = this.getBezierValue(f1, v, 4, v1 + 52);
                    f7 = this.getBezierValue(f1, v, 5, v1 + 70);
                    f8 = this.getBezierValue(f1, v, 6, v1 + 88);
                    f9 = f3;
                }
            }
            if(f2 == 1.0f) {
                color0.r = f9;
                color0.g = f4;
                color0.b = f5;
                color1.r = f6;
                color1.g = f7;
                color1.b = f8;
                return;
            }
            if(animation$MixBlend0 == MixBlend.setup) {
                color0.r = slot0.data.color.r;
                color0.g = slot0.data.color.g;
                color0.b = slot0.data.color.b;
                color1.r = slot0.data.darkColor.r;
                color1.g = slot0.data.darkColor.g;
                color1.b = slot0.data.darkColor.b;
            }
            color0.r += (f9 - color0.r) * f2;
            color0.g += (f4 - color0.g) * f2;
            color0.b += (f5 - color0.b) * f2;
            color1.r += (f6 - color1.r) * f2;
            color1.g += (f7 - color1.g) * f2;
            color1.b += (f8 - color1.b) * f2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public int getFrameEntries() {
            return 7;
        }

        @Override  // com.esotericsoftware.spine.Animation$SlotTimeline
        public int getSlotIndex() {
            return this.slotIndex;
        }

        public void setFrame(int v, float f, float f1, float f2, float f3, float f4, float f5, float f6) {
            this.frames[v * 7] = f;
            this.frames[v * 7 + 1] = f1;
            this.frames[v * 7 + 2] = f2;
            this.frames[v * 7 + 3] = f3;
            this.frames[v * 7 + 4] = f4;
            this.frames[v * 7 + 5] = f5;
            this.frames[v * 7 + 6] = f6;
        }
    }

    public static class RGBA2Timeline extends CurveTimeline implements SlotTimeline {
        private static final int A = 4;
        private static final int B = 3;
        private static final int B2 = 7;
        public static final int ENTRIES = 8;
        private static final int G = 2;
        private static final int G2 = 6;
        private static final int R = 1;
        private static final int R2 = 5;
        final int slotIndex;

        public RGBA2Timeline(int v, int v1, int v2) {
            super(v, v1, new String[]{Property.rgb.ordinal() + "|" + v2, Property.alpha.ordinal() + "|" + v2, Property.rgb2.ordinal() + "|" + v2});
            this.slotIndex = v2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            float f10;
            float f9;
            float f8;
            float f7;
            float f6;
            float f5;
            float f4;
            Slot slot0 = (Slot)skeleton0.slots.get(this.slotIndex);
            if(!slot0.bone.active) {
                return;
            }
            float[] arr_f = this.frames;
            Color color0 = slot0.color;
            Color color1 = slot0.darkColor;
            if(f1 < arr_f[0]) {
                Color color2 = slot0.data.color;
                Color color3 = slot0.data.darkColor;
                switch(animation$MixBlend0) {
                    case once: {
                        color0.set(color2);
                        color1.r = color3.r;
                        color1.g = color3.g;
                        color1.b = color3.b;
                        return;
                    }
                    case loop: {
                        color0.add((color2.r - color0.r) * f2, (color2.g - color0.g) * f2, (color2.b - color0.b) * f2, (color2.a - color0.a) * f2);
                        color1.r += (color3.r - color1.r) * f2;
                        color1.g += (color3.g - color1.g) * f2;
                        color1.b += (color3.b - color1.b) * f2;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            int v = RGBA2Timeline.search(arr_f, f1, 8);
            int v1 = (int)this.curves[v >> 3];
            switch(v1) {
                case 0: {
                    float f11 = arr_f[v];
                    float f12 = arr_f[v + 1];
                    float f13 = arr_f[v + 2];
                    float f14 = arr_f[v + 3];
                    float f15 = arr_f[v + 4];
                    float f16 = arr_f[v + 5];
                    float f17 = arr_f[v + 6];
                    float f18 = arr_f[v + 7];
                    float f19 = (f1 - f11) / (arr_f[v + 8] - f11);
                    float f20 = (arr_f[v + 9] - f12) * f19 + f12;
                    float f21 = (arr_f[v + 10] - f13) * f19 + f13;
                    float f22 = (arr_f[v + 11] - f14) * f19 + f14;
                    float f23 = (arr_f[v + 12] - f15) * f19 + f15;
                    float f24 = (arr_f[v + 13] - f16) * f19 + f16;
                    float f25 = (arr_f[v + 14] - f17) * f19 + f17;
                    f9 = f18 + (arr_f[v + 15] - f18) * f19;
                    f10 = f20;
                    f4 = f21;
                    f5 = f22;
                    f6 = f23;
                    f7 = f24;
                    f8 = f25;
                    break;
                }
                case 1: {
                    f10 = arr_f[v + 1];
                    f4 = arr_f[v + 2];
                    f5 = arr_f[v + 3];
                    f6 = arr_f[v + 4];
                    f7 = arr_f[v + 5];
                    f8 = arr_f[v + 6];
                    f9 = arr_f[v + 7];
                    break;
                }
                default: {
                    float f3 = this.getBezierValue(f1, v, 1, v1 - 2);
                    f4 = this.getBezierValue(f1, v, 2, v1 + 16);
                    f5 = this.getBezierValue(f1, v, 3, v1 + 34);
                    f6 = this.getBezierValue(f1, v, 4, v1 + 52);
                    f7 = this.getBezierValue(f1, v, 5, v1 + 70);
                    f8 = this.getBezierValue(f1, v, 6, v1 + 88);
                    f9 = this.getBezierValue(f1, v, 7, v1 + 106);
                    f10 = f3;
                }
            }
            if(f2 == 1.0f) {
                color0.set(f10, f4, f5, f6);
                color1.r = f7;
                color1.g = f8;
                color1.b = f9;
                return;
            }
            if(animation$MixBlend0 == MixBlend.setup) {
                color0.set(slot0.data.color);
                color1.r = slot0.data.darkColor.r;
                color1.g = slot0.data.darkColor.g;
                color1.b = slot0.data.darkColor.b;
            }
            color0.add((f10 - color0.r) * f2, (f4 - color0.g) * f2, (f5 - color0.b) * f2, (f6 - color0.a) * f2);
            color1.r += (f7 - color1.r) * f2;
            color1.g += (f8 - color1.g) * f2;
            color1.b += (f9 - color1.b) * f2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public int getFrameEntries() {
            return 8;
        }

        @Override  // com.esotericsoftware.spine.Animation$SlotTimeline
        public int getSlotIndex() {
            return this.slotIndex;
        }

        public void setFrame(int v, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7) {
            this.frames[v << 3] = f;
            this.frames[(v << 3) + 1] = f1;
            this.frames[(v << 3) + 2] = f2;
            this.frames[(v << 3) + 3] = f3;
            this.frames[(v << 3) + 4] = f4;
            this.frames[(v << 3) + 5] = f5;
            this.frames[(v << 3) + 6] = f6;
            this.frames[(v << 3) + 7] = f7;
        }
    }

    public static class RGBATimeline extends CurveTimeline implements SlotTimeline {
        private static final int A = 4;
        private static final int B = 3;
        public static final int ENTRIES = 5;
        private static final int G = 2;
        private static final int R = 1;
        final int slotIndex;

        public RGBATimeline(int v, int v1, int v2) {
            super(v, v1, new String[]{Property.rgb.ordinal() + "|" + v2, Property.alpha.ordinal() + "|" + v2});
            this.slotIndex = v2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            float f7;
            float f6;
            float f4;
            float f3;
            Slot slot0 = (Slot)skeleton0.slots.get(this.slotIndex);
            if(!slot0.bone.active) {
                return;
            }
            float[] arr_f = this.frames;
            Color color0 = slot0.color;
            if(f1 < arr_f[0]) {
                Color color1 = slot0.data.color;
                switch(animation$MixBlend0) {
                    case once: {
                        color0.set(color1);
                        return;
                    }
                    case loop: {
                        color0.add((color1.r - color0.r) * f2, (color1.g - color0.g) * f2, (color1.b - color0.b) * f2, (color1.a - color0.a) * f2);
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            int v = RGBATimeline.search(arr_f, f1, 5);
            int v1 = (int)this.curves[v / 5];
            switch(v1) {
                case 0: {
                    float f8 = arr_f[v];
                    float f9 = arr_f[v + 1];
                    float f10 = arr_f[v + 2];
                    float f11 = arr_f[v + 3];
                    float f12 = arr_f[v + 4];
                    float f13 = (f1 - f8) / (arr_f[v + 5] - f8);
                    float f14 = (arr_f[v + 6] - f9) * f13 + f9;
                    float f15 = (arr_f[v + 7] - f10) * f13 + f10;
                    float f16 = (arr_f[v + 8] - f11) * f13 + f11;
                    f6 = (arr_f[v + 9] - f12) * f13 + f12;
                    f3 = f14;
                    f4 = f15;
                    f7 = f16;
                    break;
                }
                case 1: {
                    float f17 = arr_f[v + 1];
                    f4 = arr_f[v + 2];
                    f7 = arr_f[v + 3];
                    f6 = arr_f[v + 4];
                    f3 = f17;
                    break;
                }
                default: {
                    f3 = this.getBezierValue(f1, v, 1, v1 - 2);
                    f4 = this.getBezierValue(f1, v, 2, v1 + 16);
                    float f5 = this.getBezierValue(f1, v, 3, v1 + 34);
                    f6 = this.getBezierValue(f1, v, 4, v1 + 52);
                    f7 = f5;
                }
            }
            if(f2 == 1.0f) {
                color0.set(f3, f4, f7, f6);
                return;
            }
            if(animation$MixBlend0 == MixBlend.setup) {
                color0.set(slot0.data.color);
            }
            color0.add((f3 - color0.r) * f2, (f4 - color0.g) * f2, (f7 - color0.b) * f2, (f6 - color0.a) * f2);
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public int getFrameEntries() {
            return 5;
        }

        @Override  // com.esotericsoftware.spine.Animation$SlotTimeline
        public int getSlotIndex() {
            return this.slotIndex;
        }

        public void setFrame(int v, float f, float f1, float f2, float f3, float f4) {
            this.frames[v * 5] = f;
            this.frames[v * 5 + 1] = f1;
            this.frames[v * 5 + 2] = f2;
            this.frames[v * 5 + 3] = f3;
            this.frames[v * 5 + 4] = f4;
        }
    }

    public static class RGBTimeline extends CurveTimeline implements SlotTimeline {
        private static final int B = 3;
        public static final int ENTRIES = 4;
        private static final int G = 2;
        private static final int R = 1;
        final int slotIndex;

        public RGBTimeline(int v, int v1, int v2) {
            super(v, v1, new String[]{Property.rgb.ordinal() + "|" + v2});
            this.slotIndex = v2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            float f5;
            float f4;
            float f3;
            Slot slot0 = (Slot)skeleton0.slots.get(this.slotIndex);
            if(!slot0.bone.active) {
                return;
            }
            float[] arr_f = this.frames;
            Color color0 = slot0.color;
            if(f1 < arr_f[0]) {
                Color color1 = slot0.data.color;
                switch(animation$MixBlend0) {
                    case once: {
                        color0.r = color1.r;
                        color0.g = color1.g;
                        color0.b = color1.b;
                        return;
                    }
                    case loop: {
                        color0.r += (color1.r - color0.r) * f2;
                        color0.g += (color1.g - color0.g) * f2;
                        color0.b += (color1.b - color0.b) * f2;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            int v = RGBTimeline.search(arr_f, f1, 4);
            int v1 = (int)this.curves[v >> 2];
            switch(v1) {
                case 0: {
                    float f6 = arr_f[v];
                    float f7 = arr_f[v + 1];
                    float f8 = arr_f[v + 2];
                    float f9 = arr_f[v + 3];
                    float f10 = (f1 - f6) / (arr_f[v + 4] - f6);
                    float f11 = (arr_f[v + 5] - f7) * f10 + f7;
                    float f12 = (arr_f[v + 6] - f8) * f10 + f8;
                    f5 = (arr_f[v + 7] - f9) * f10 + f9;
                    f3 = f11;
                    f4 = f12;
                    break;
                }
                case 1: {
                    float f13 = arr_f[v + 1];
                    f4 = arr_f[v + 2];
                    f5 = arr_f[v + 3];
                    f3 = f13;
                    break;
                }
                default: {
                    f3 = this.getBezierValue(f1, v, 1, v1 - 2);
                    f4 = this.getBezierValue(f1, v, 2, v1 + 16);
                    f5 = this.getBezierValue(f1, v, 3, v1 + 34);
                }
            }
            if(f2 == 1.0f) {
                color0.r = f3;
                color0.g = f4;
                color0.b = f5;
                return;
            }
            if(animation$MixBlend0 == MixBlend.setup) {
                color0.r = slot0.data.color.r;
                color0.g = slot0.data.color.g;
                color0.b = slot0.data.color.b;
            }
            color0.r += (f3 - color0.r) * f2;
            color0.g += (f4 - color0.g) * f2;
            color0.b += (f5 - color0.b) * f2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public int getFrameEntries() {
            return 4;
        }

        @Override  // com.esotericsoftware.spine.Animation$SlotTimeline
        public int getSlotIndex() {
            return this.slotIndex;
        }

        public void setFrame(int v, float f, float f1, float f2, float f3) {
            this.frames[v << 2] = f;
            this.frames[(v << 2) + 1] = f1;
            this.frames[(v << 2) + 2] = f2;
            this.frames[(v << 2) + 3] = f3;
        }
    }

    public static class RotateTimeline extends CurveTimeline1 implements BoneTimeline {
        final int boneIndex;

        public RotateTimeline(int v, int v1, int v2) {
            super(v, v1, Property.rotate.ordinal() + "|" + v2);
            this.boneIndex = v2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            Bone bone0 = (Bone)skeleton0.bones.get(this.boneIndex);
            if(!bone0.active) {
                return;
            }
            if(f1 < this.frames[0]) {
                switch(animation$MixBlend0) {
                    case once: {
                        bone0.rotation = bone0.data.rotation;
                        return;
                    }
                    case loop: {
                        bone0.rotation += (bone0.data.rotation - bone0.rotation) * f2;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            float f3 = this.getCurveValue(f1);
            switch(animation$MixBlend0) {
                case once: {
                    bone0.rotation = bone0.data.rotation + f3 * f2;
                    return;
                }
                case loop: 
                case pingpong: {
                    f3 += bone0.data.rotation - bone0.rotation;
                    break;
                }
                case onceReverse: {
                    break;
                }
                default: {
                    return;
                }
            }
            bone0.rotation += f3 * f2;
        }

        @Override  // com.esotericsoftware.spine.Animation$BoneTimeline
        public int getBoneIndex() {
            return this.boneIndex;
        }
    }

    public static class ScaleTimeline extends CurveTimeline2 implements BoneTimeline {
        final int boneIndex;

        public ScaleTimeline(int v, int v1, int v2) {
            super(v, v1, Property.scaleX.ordinal() + "|" + v2, Property.scaleY.ordinal() + "|" + v2);
            this.boneIndex = v2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            float f4;
            float f3;
            Bone bone0 = (Bone)skeleton0.bones.get(this.boneIndex);
            if(!bone0.active) {
                return;
            }
            float[] arr_f = this.frames;
            if(f1 < arr_f[0]) {
                switch(animation$MixBlend0) {
                    case once: {
                        bone0.scaleX = bone0.data.scaleX;
                        bone0.scaleY = bone0.data.scaleY;
                        return;
                    }
                    case loop: {
                        bone0.scaleX += (bone0.data.scaleX - bone0.scaleX) * f2;
                        bone0.scaleY += (bone0.data.scaleY - bone0.scaleY) * f2;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            int v = ScaleTimeline.search(arr_f, f1, 3);
            int v1 = (int)this.curves[v / 3];
            switch(v1) {
                case 0: {
                    float f5 = arr_f[v];
                    float f6 = arr_f[v + 1];
                    float f7 = arr_f[v + 2];
                    float f8 = (f1 - f5) / (arr_f[v + 3] - f5);
                    float f9 = (arr_f[v + 4] - f6) * f8 + f6;
                    f4 = (arr_f[v + 5] - f7) * f8 + f7;
                    f3 = f9;
                    break;
                }
                case 1: {
                    float f10 = arr_f[v + 1];
                    f4 = arr_f[v + 2];
                    f3 = f10;
                    break;
                }
                default: {
                    f3 = this.getBezierValue(f1, v, 1, v1 - 2);
                    f4 = this.getBezierValue(f1, v, 2, v1 + 16);
                }
            }
            float f11 = f3 * bone0.data.scaleX;
            float f12 = f4 * bone0.data.scaleY;
            if(f2 == 1.0f) {
                if(animation$MixBlend0 == MixBlend.add) {
                    bone0.scaleX += f11 - bone0.data.scaleX;
                    bone0.scaleY += f12 - bone0.data.scaleY;
                    return;
                }
                bone0.scaleX = f11;
                bone0.scaleY = f12;
                return;
            }
            if(animation$MixDirection0 == MixDirection.out) {
                switch(animation$MixBlend0) {
                    case once: {
                        bone0.scaleX = bone0.data.scaleX + (Math.abs(f11) * Math.signum(bone0.data.scaleX) - bone0.data.scaleX) * f2;
                        bone0.scaleY = bone0.data.scaleY + (Math.abs(f12) * Math.signum(bone0.data.scaleY) - bone0.data.scaleY) * f2;
                        return;
                    }
                    case loop: 
                    case pingpong: {
                        bone0.scaleX += (Math.abs(f11) * Math.signum(bone0.scaleX) - bone0.scaleX) * f2;
                        bone0.scaleY += (Math.abs(f12) * Math.signum(bone0.scaleY) - bone0.scaleY) * f2;
                        return;
                    }
                    case onceReverse: {
                        bone0.scaleX = (f11 - bone0.data.scaleX) * f2;
                        bone0.scaleY = (f12 - bone0.data.scaleY) * f2;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            switch(animation$MixBlend0) {
                case once: {
                    float f13 = Math.abs(bone0.data.scaleX);
                    float f14 = Math.signum(f11);
                    float f15 = Math.abs(bone0.data.scaleY);
                    float f16 = Math.signum(f12);
                    bone0.scaleX = f13 * f14 + (f11 - f13 * f14) * f2;
                    bone0.scaleY = f15 * f16 + (f12 - f15 * f16) * f2;
                    return;
                }
                case loop: 
                case pingpong: {
                    float f17 = Math.abs(bone0.scaleX);
                    float f18 = Math.signum(f11);
                    float f19 = Math.abs(bone0.scaleY);
                    float f20 = Math.signum(f12);
                    bone0.scaleX = f17 * f18 + (f11 - f17 * f18) * f2;
                    bone0.scaleY = f19 * f20 + (f12 - f19 * f20) * f2;
                    return;
                }
                case onceReverse: {
                    bone0.scaleX += (f11 - bone0.data.scaleX) * f2;
                    bone0.scaleY += (f12 - bone0.data.scaleY) * f2;
                }
            }
        }

        @Override  // com.esotericsoftware.spine.Animation$BoneTimeline
        public int getBoneIndex() {
            return this.boneIndex;
        }
    }

    public static class ScaleXTimeline extends CurveTimeline1 implements BoneTimeline {
        final int boneIndex;

        public ScaleXTimeline(int v, int v1, int v2) {
            super(v, v1, Property.scaleX.ordinal() + "|" + v2);
            this.boneIndex = v2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            Bone bone0 = (Bone)skeleton0.bones.get(this.boneIndex);
            if(!bone0.active) {
                return;
            }
            if(f1 < this.frames[0]) {
                switch(animation$MixBlend0) {
                    case once: {
                        bone0.scaleX = bone0.data.scaleX;
                        return;
                    }
                    case loop: {
                        bone0.scaleX += (bone0.data.scaleX - bone0.scaleX) * f2;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            float f3 = this.getCurveValue(f1) * bone0.data.scaleX;
            if(f2 == 1.0f) {
                if(animation$MixBlend0 == MixBlend.add) {
                    bone0.scaleX += f3 - bone0.data.scaleX;
                    return;
                }
                bone0.scaleX = f3;
                return;
            }
            if(animation$MixDirection0 == MixDirection.out) {
                switch(animation$MixBlend0) {
                    case once: {
                        bone0.scaleX = bone0.data.scaleX + (Math.abs(f3) * Math.signum(bone0.data.scaleX) - bone0.data.scaleX) * f2;
                        return;
                    }
                    case loop: 
                    case pingpong: {
                        bone0.scaleX += (Math.abs(f3) * Math.signum(bone0.scaleX) - bone0.scaleX) * f2;
                        return;
                    }
                    case onceReverse: {
                        bone0.scaleX = (f3 - bone0.data.scaleX) * f2;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            switch(animation$MixBlend0) {
                case once: {
                    float f4 = Math.abs(bone0.data.scaleX);
                    float f5 = Math.signum(f3);
                    bone0.scaleX = f4 * f5 + (f3 - f4 * f5) * f2;
                    return;
                }
                case loop: 
                case pingpong: {
                    float f6 = Math.abs(bone0.scaleX);
                    float f7 = Math.signum(f3);
                    bone0.scaleX = f6 * f7 + (f3 - f6 * f7) * f2;
                    return;
                }
                case onceReverse: {
                    bone0.scaleX += (f3 - bone0.data.scaleX) * f2;
                }
            }
        }

        @Override  // com.esotericsoftware.spine.Animation$BoneTimeline
        public int getBoneIndex() {
            return this.boneIndex;
        }
    }

    public static class ScaleYTimeline extends CurveTimeline1 implements BoneTimeline {
        final int boneIndex;

        public ScaleYTimeline(int v, int v1, int v2) {
            super(v, v1, Property.scaleY.ordinal() + "|" + v2);
            this.boneIndex = v2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            Bone bone0 = (Bone)skeleton0.bones.get(this.boneIndex);
            if(!bone0.active) {
                return;
            }
            if(f1 < this.frames[0]) {
                switch(animation$MixBlend0) {
                    case once: {
                        bone0.scaleY = bone0.data.scaleY;
                        return;
                    }
                    case loop: {
                        bone0.scaleY += (bone0.data.scaleY - bone0.scaleY) * f2;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            float f3 = this.getCurveValue(f1) * bone0.data.scaleY;
            if(f2 == 1.0f) {
                if(animation$MixBlend0 == MixBlend.add) {
                    bone0.scaleY += f3 - bone0.data.scaleY;
                    return;
                }
                bone0.scaleY = f3;
                return;
            }
            if(animation$MixDirection0 == MixDirection.out) {
                switch(animation$MixBlend0) {
                    case once: {
                        bone0.scaleY = bone0.data.scaleY + (Math.abs(f3) * Math.signum(bone0.data.scaleY) - bone0.data.scaleY) * f2;
                        return;
                    }
                    case loop: 
                    case pingpong: {
                        bone0.scaleY += (Math.abs(f3) * Math.signum(bone0.scaleY) - bone0.scaleY) * f2;
                        return;
                    }
                    case onceReverse: {
                        bone0.scaleY = (f3 - bone0.data.scaleY) * f2;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            switch(animation$MixBlend0) {
                case once: {
                    float f4 = Math.abs(bone0.data.scaleY);
                    float f5 = Math.signum(f3);
                    bone0.scaleY = f4 * f5 + (f3 - f4 * f5) * f2;
                    return;
                }
                case loop: 
                case pingpong: {
                    float f6 = Math.abs(bone0.scaleY);
                    float f7 = Math.signum(f3);
                    bone0.scaleY = f6 * f7 + (f3 - f6 * f7) * f2;
                    return;
                }
                case onceReverse: {
                    bone0.scaleY += (f3 - bone0.data.scaleY) * f2;
                }
            }
        }

        @Override  // com.esotericsoftware.spine.Animation$BoneTimeline
        public int getBoneIndex() {
            return this.boneIndex;
        }
    }

    public static class SequenceTimeline extends Timeline implements SlotTimeline {
        private static final int DELAY = 2;
        public static final int ENTRIES = 3;
        private static final int MODE = 1;
        final HasTextureRegion attachment;
        final int slotIndex;

        public SequenceTimeline(int v, int v1, Attachment attachment0) {
            super(v, new String[]{Property.sequence.ordinal() + "|" + v1 + "|" + ((HasTextureRegion)attachment0).getSequence().getId()});
            this.slotIndex = v1;
            this.attachment = (HasTextureRegion)attachment0;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            Slot slot0 = (Slot)skeleton0.slots.get(this.slotIndex);
            if(!slot0.bone.active) {
                return;
            }
            if(slot0.attachment != this.attachment && (!(slot0.attachment instanceof VertexAttachment) || ((VertexAttachment)slot0.attachment).getTimelineAttachment() != this.attachment)) {
                return;
            }
            float[] arr_f = this.frames;
            if(f1 < arr_f[0]) {
                if(animation$MixBlend0 == MixBlend.setup || animation$MixBlend0 == MixBlend.first) {
                    slot0.setSequenceIndex(-1);
                }
                return;
            }
            int v = SequenceTimeline.search(arr_f, f1, 3);
            float f3 = arr_f[v];
            int v1 = (int)arr_f[v + 1];
            float f4 = arr_f[v + 2];
            int v2 = v1 >> 4;
            TextureRegion[] arr_textureRegion = this.attachment.getSequence().getRegions();
            SequenceMode sequence$SequenceMode0 = SequenceMode.values[v1 & 15];
            if(sequence$SequenceMode0 != SequenceMode.hold) {
                v2 = (int)(((float)v2) + ((f1 - f3) / f4 + 0.00001f));
                switch(sequence$SequenceMode0) {
                    case once: {
                        v2 = Math.min(arr_textureRegion.length - 1, v2);
                        break;
                    }
                    case loop: {
                        v2 %= arr_textureRegion.length;
                        break;
                    }
                    case pingpong: {
                        int v3 = (arr_textureRegion.length << 1) - 2;
                        v2 %= v3;
                        if(v2 >= arr_textureRegion.length) {
                            v2 = v3 - v2;
                        }
                        break;
                    }
                    case onceReverse: {
                        v2 = Math.max(arr_textureRegion.length - 1 - v2, 0);
                        break;
                    }
                    case loopReverse: {
                        v2 = arr_textureRegion.length - 1 - v2 % arr_textureRegion.length;
                    }
                }
            }
            slot0.setSequenceIndex(v2);
        }

        public Attachment getAttachment() {
            return (Attachment)this.attachment;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public int getFrameEntries() {
            return 3;
        }

        @Override  // com.esotericsoftware.spine.Animation$SlotTimeline
        public int getSlotIndex() {
            return this.slotIndex;
        }

        public void setFrame(int v, float f, SequenceMode sequence$SequenceMode0, int v1, float f1) {
            this.frames[v * 3] = f;
            this.frames[v * 3 + 1] = (float)(sequence$SequenceMode0.ordinal() | v1 << 4);
            this.frames[v * 3 + 2] = f1;
        }
    }

    public static class ShearTimeline extends CurveTimeline2 implements BoneTimeline {
        final int boneIndex;

        public ShearTimeline(int v, int v1, int v2) {
            super(v, v1, Property.shearX.ordinal() + "|" + v2, Property.shearY.ordinal() + "|" + v2);
            this.boneIndex = v2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            float f4;
            float f3;
            Bone bone0 = (Bone)skeleton0.bones.get(this.boneIndex);
            if(!bone0.active) {
                return;
            }
            float[] arr_f = this.frames;
            if(f1 < arr_f[0]) {
                switch(animation$MixBlend0) {
                    case once: {
                        bone0.shearX = bone0.data.shearX;
                        bone0.shearY = bone0.data.shearY;
                        return;
                    }
                    case loop: {
                        bone0.shearX += (bone0.data.shearX - bone0.shearX) * f2;
                        bone0.shearY += (bone0.data.shearY - bone0.shearY) * f2;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            int v = ShearTimeline.search(arr_f, f1, 3);
            int v1 = (int)this.curves[v / 3];
            switch(v1) {
                case 0: {
                    float f5 = arr_f[v];
                    float f6 = arr_f[v + 1];
                    float f7 = arr_f[v + 2];
                    float f8 = (f1 - f5) / (arr_f[v + 3] - f5);
                    float f9 = (arr_f[v + 4] - f6) * f8 + f6;
                    f4 = (arr_f[v + 5] - f7) * f8 + f7;
                    f3 = f9;
                    break;
                }
                case 1: {
                    float f10 = arr_f[v + 1];
                    f4 = arr_f[v + 2];
                    f3 = f10;
                    break;
                }
                default: {
                    f3 = this.getBezierValue(f1, v, 1, v1 - 2);
                    f4 = this.getBezierValue(f1, v, 2, v1 + 16);
                }
            }
            switch(animation$MixBlend0) {
                case once: {
                    bone0.shearX = bone0.data.shearX + f3 * f2;
                    bone0.shearY = bone0.data.shearY + f4 * f2;
                    return;
                }
                case loop: 
                case pingpong: {
                    bone0.shearX += (bone0.data.shearX + f3 - bone0.shearX) * f2;
                    bone0.shearY += (bone0.data.shearY + f4 - bone0.shearY) * f2;
                    return;
                }
                case onceReverse: {
                    bone0.shearX += f3 * f2;
                    bone0.shearY += f4 * f2;
                }
            }
        }

        @Override  // com.esotericsoftware.spine.Animation$BoneTimeline
        public int getBoneIndex() {
            return this.boneIndex;
        }
    }

    public static class ShearXTimeline extends CurveTimeline1 implements BoneTimeline {
        final int boneIndex;

        public ShearXTimeline(int v, int v1, int v2) {
            super(v, v1, Property.shearX.ordinal() + "|" + v2);
            this.boneIndex = v2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            Bone bone0 = (Bone)skeleton0.bones.get(this.boneIndex);
            if(!bone0.active) {
                return;
            }
            if(f1 < this.frames[0]) {
                switch(animation$MixBlend0) {
                    case once: {
                        bone0.shearX = bone0.data.shearX;
                        return;
                    }
                    case loop: {
                        bone0.shearX += (bone0.data.shearX - bone0.shearX) * f2;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            float f3 = this.getCurveValue(f1);
            switch(animation$MixBlend0) {
                case once: {
                    bone0.shearX = bone0.data.shearX + f3 * f2;
                    return;
                }
                case loop: 
                case pingpong: {
                    bone0.shearX += (bone0.data.shearX + f3 - bone0.shearX) * f2;
                    return;
                }
                case onceReverse: {
                    bone0.shearX += f3 * f2;
                }
            }
        }

        @Override  // com.esotericsoftware.spine.Animation$BoneTimeline
        public int getBoneIndex() {
            return this.boneIndex;
        }
    }

    public static class ShearYTimeline extends CurveTimeline1 implements BoneTimeline {
        final int boneIndex;

        public ShearYTimeline(int v, int v1, int v2) {
            super(v, v1, Property.shearY.ordinal() + "|" + v2);
            this.boneIndex = v2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            Bone bone0 = (Bone)skeleton0.bones.get(this.boneIndex);
            if(!bone0.active) {
                return;
            }
            if(f1 < this.frames[0]) {
                switch(animation$MixBlend0) {
                    case once: {
                        bone0.shearY = bone0.data.shearY;
                        return;
                    }
                    case loop: {
                        bone0.shearY += (bone0.data.shearY - bone0.shearY) * f2;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            float f3 = this.getCurveValue(f1);
            switch(animation$MixBlend0) {
                case once: {
                    bone0.shearY = bone0.data.shearY + f3 * f2;
                    return;
                }
                case loop: 
                case pingpong: {
                    bone0.shearY += (bone0.data.shearY + f3 - bone0.shearY) * f2;
                    return;
                }
                case onceReverse: {
                    bone0.shearY += f3 * f2;
                }
            }
        }

        @Override  // com.esotericsoftware.spine.Animation$BoneTimeline
        public int getBoneIndex() {
            return this.boneIndex;
        }
    }

    public interface SlotTimeline {
        int getSlotIndex();
    }

    public static abstract class Timeline {
        final float[] frames;
        private final String[] propertyIds;

        public Timeline(int v, String[] arr_s) {
            if(arr_s == null) {
                throw new IllegalArgumentException("propertyIds cannot be null.");
            }
            this.propertyIds = arr_s;
            this.frames = new float[v * this.getFrameEntries()];
        }

        public abstract void apply(Skeleton arg1, float arg2, float arg3, @Null Array arg4, float arg5, MixBlend arg6, MixDirection arg7);

        public float getDuration() {
            return this.frames[this.frames.length - this.getFrameEntries()];
        }

        public int getFrameCount() {
            return this.frames.length / this.getFrameEntries();
        }

        public int getFrameEntries() {
            return 1;
        }

        public float[] getFrames() {
            return this.frames;
        }

        public String[] getPropertyIds() {
            return this.propertyIds;
        }

        static int search(float[] arr_f, float f) {
            for(int v = 1; v < arr_f.length; ++v) {
                if(arr_f[v] > f) {
                    return v - 1;
                }
            }
            return arr_f.length - 1;
        }

        static int search(float[] arr_f, float f, int v) {
            for(int v1 = v; v1 < arr_f.length; v1 += v) {
                if(arr_f[v1] > f) {
                    return v1 - v;
                }
            }
            return arr_f.length - v;
        }
    }

    public static class TransformConstraintTimeline extends CurveTimeline {
        public static final int ENTRIES = 7;
        private static final int ROTATE = 1;
        private static final int SCALEX = 4;
        private static final int SCALEY = 5;
        private static final int SHEARY = 6;
        private static final int X = 2;
        private static final int Y = 3;
        final int transformConstraintIndex;

        public TransformConstraintTimeline(int v, int v1, int v2) {
            super(v, v1, new String[]{Property.transformConstraint.ordinal() + "|" + v2});
            this.transformConstraintIndex = v2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            float f9;
            float f8;
            float f7;
            float f6;
            float f5;
            float f4;
            TransformConstraint transformConstraint0 = (TransformConstraint)skeleton0.transformConstraints.get(this.transformConstraintIndex);
            if(!transformConstraint0.active) {
                return;
            }
            float[] arr_f = this.frames;
            if(f1 < arr_f[0]) {
                TransformConstraintData transformConstraintData0 = transformConstraint0.data;
                switch(animation$MixBlend0) {
                    case once: {
                        transformConstraint0.mixRotate = transformConstraintData0.mixRotate;
                        transformConstraint0.mixX = transformConstraintData0.mixX;
                        transformConstraint0.mixY = transformConstraintData0.mixY;
                        transformConstraint0.mixScaleX = transformConstraintData0.mixScaleX;
                        transformConstraint0.mixScaleY = transformConstraintData0.mixScaleY;
                        transformConstraint0.mixShearY = transformConstraintData0.mixShearY;
                        return;
                    }
                    case loop: {
                        transformConstraint0.mixRotate += (transformConstraintData0.mixRotate - transformConstraint0.mixRotate) * f2;
                        transformConstraint0.mixX += (transformConstraintData0.mixX - transformConstraint0.mixX) * f2;
                        transformConstraint0.mixY += (transformConstraintData0.mixY - transformConstraint0.mixY) * f2;
                        transformConstraint0.mixScaleX += (transformConstraintData0.mixScaleX - transformConstraint0.mixScaleX) * f2;
                        transformConstraint0.mixScaleY += (transformConstraintData0.mixScaleY - transformConstraint0.mixScaleY) * f2;
                        transformConstraint0.mixShearY += (transformConstraintData0.mixShearY - transformConstraint0.mixShearY) * f2;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            int v = TransformConstraintTimeline.search(arr_f, f1, 7);
            int v1 = (int)this.curves[v / 7];
            switch(v1) {
                case 0: {
                    float f10 = arr_f[v];
                    float f11 = arr_f[v + 1];
                    float f12 = arr_f[v + 2];
                    float f13 = arr_f[v + 3];
                    float f14 = arr_f[v + 4];
                    float f15 = arr_f[v + 5];
                    float f16 = arr_f[v + 6];
                    float f17 = (f1 - f10) / (arr_f[v + 7] - f10);
                    float f18 = (arr_f[v + 8] - f11) * f17 + f11;
                    float f19 = (arr_f[v + 9] - f12) * f17 + f12;
                    f5 = (arr_f[v + 10] - f13) * f17 + f13;
                    f6 = (arr_f[v + 11] - f14) * f17 + f14;
                    f7 = (arr_f[v + 12] - f15) * f17 + f15;
                    f8 = (arr_f[v + 13] - f16) * f17 + f16;
                    f9 = f18;
                    f4 = f19;
                    break;
                }
                case 1: {
                    f9 = arr_f[v + 1];
                    f4 = arr_f[v + 2];
                    float f20 = arr_f[v + 3];
                    float f21 = arr_f[v + 4];
                    float f22 = arr_f[v + 5];
                    f8 = arr_f[v + 6];
                    f7 = f22;
                    f6 = f21;
                    f5 = f20;
                    break;
                }
                default: {
                    float f3 = this.getBezierValue(f1, v, 1, v1 - 2);
                    f4 = this.getBezierValue(f1, v, 2, v1 + 16);
                    f5 = this.getBezierValue(f1, v, 3, v1 + 34);
                    f6 = this.getBezierValue(f1, v, 4, v1 + 52);
                    f7 = this.getBezierValue(f1, v, 5, v1 + 70);
                    f8 = this.getBezierValue(f1, v, 6, v1 + 88);
                    f9 = f3;
                }
            }
            if(animation$MixBlend0 == MixBlend.setup) {
                transformConstraint0.mixRotate = transformConstraint0.data.mixRotate + (f9 - transformConstraint0.data.mixRotate) * f2;
                transformConstraint0.mixX = transformConstraint0.data.mixX + (f4 - transformConstraint0.data.mixX) * f2;
                transformConstraint0.mixY = transformConstraint0.data.mixY + (f5 - transformConstraint0.data.mixY) * f2;
                transformConstraint0.mixScaleX = transformConstraint0.data.mixScaleX + (f6 - transformConstraint0.data.mixScaleX) * f2;
                transformConstraint0.mixScaleY = transformConstraint0.data.mixScaleY + (f7 - transformConstraint0.data.mixScaleY) * f2;
                transformConstraint0.mixShearY = transformConstraint0.data.mixShearY + (f8 - transformConstraint0.data.mixShearY) * f2;
                return;
            }
            transformConstraint0.mixRotate += (f9 - transformConstraint0.mixRotate) * f2;
            transformConstraint0.mixX += (f4 - transformConstraint0.mixX) * f2;
            transformConstraint0.mixY += (f5 - transformConstraint0.mixY) * f2;
            transformConstraint0.mixScaleX += (f6 - transformConstraint0.mixScaleX) * f2;
            transformConstraint0.mixScaleY += (f7 - transformConstraint0.mixScaleY) * f2;
            transformConstraint0.mixShearY += (f8 - transformConstraint0.mixShearY) * f2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public int getFrameEntries() {
            return 7;
        }

        public int getTransformConstraintIndex() {
            return this.transformConstraintIndex;
        }

        public void setFrame(int v, float f, float f1, float f2, float f3, float f4, float f5, float f6) {
            this.frames[v * 7] = f;
            this.frames[v * 7 + 1] = f1;
            this.frames[v * 7 + 2] = f2;
            this.frames[v * 7 + 3] = f3;
            this.frames[v * 7 + 4] = f4;
            this.frames[v * 7 + 5] = f5;
            this.frames[v * 7 + 6] = f6;
        }
    }

    public static class TranslateTimeline extends CurveTimeline2 implements BoneTimeline {
        final int boneIndex;

        public TranslateTimeline(int v, int v1, int v2) {
            super(v, v1, Property.x.ordinal() + "|" + v2, Property.y.ordinal() + "|" + v2);
            this.boneIndex = v2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            float f4;
            float f3;
            Bone bone0 = (Bone)skeleton0.bones.get(this.boneIndex);
            if(!bone0.active) {
                return;
            }
            float[] arr_f = this.frames;
            if(f1 < arr_f[0]) {
                switch(animation$MixBlend0) {
                    case once: {
                        bone0.x = bone0.data.x;
                        bone0.y = bone0.data.y;
                        return;
                    }
                    case loop: {
                        bone0.x += (bone0.data.x - bone0.x) * f2;
                        bone0.y += (bone0.data.y - bone0.y) * f2;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            int v = TranslateTimeline.search(arr_f, f1, 3);
            int v1 = (int)this.curves[v / 3];
            switch(v1) {
                case 0: {
                    float f5 = arr_f[v];
                    float f6 = arr_f[v + 1];
                    float f7 = arr_f[v + 2];
                    float f8 = (f1 - f5) / (arr_f[v + 3] - f5);
                    float f9 = (arr_f[v + 4] - f6) * f8 + f6;
                    f4 = (arr_f[v + 5] - f7) * f8 + f7;
                    f3 = f9;
                    break;
                }
                case 1: {
                    float f10 = arr_f[v + 1];
                    f4 = arr_f[v + 2];
                    f3 = f10;
                    break;
                }
                default: {
                    f3 = this.getBezierValue(f1, v, 1, v1 - 2);
                    f4 = this.getBezierValue(f1, v, 2, v1 + 16);
                }
            }
            switch(animation$MixBlend0) {
                case once: {
                    bone0.x = bone0.data.x + f3 * f2;
                    bone0.y = bone0.data.y + f4 * f2;
                    return;
                }
                case loop: 
                case pingpong: {
                    bone0.x += (bone0.data.x + f3 - bone0.x) * f2;
                    bone0.y += (bone0.data.y + f4 - bone0.y) * f2;
                    return;
                }
                case onceReverse: {
                    bone0.x += f3 * f2;
                    bone0.y += f4 * f2;
                }
            }
        }

        @Override  // com.esotericsoftware.spine.Animation$BoneTimeline
        public int getBoneIndex() {
            return this.boneIndex;
        }
    }

    public static class TranslateXTimeline extends CurveTimeline1 implements BoneTimeline {
        final int boneIndex;

        public TranslateXTimeline(int v, int v1, int v2) {
            super(v, v1, Property.x.ordinal() + "|" + v2);
            this.boneIndex = v2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            Bone bone0 = (Bone)skeleton0.bones.get(this.boneIndex);
            if(!bone0.active) {
                return;
            }
            if(f1 < this.frames[0]) {
                switch(animation$MixBlend0) {
                    case once: {
                        bone0.x = bone0.data.x;
                        return;
                    }
                    case loop: {
                        bone0.x += (bone0.data.x - bone0.x) * f2;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            float f3 = this.getCurveValue(f1);
            switch(animation$MixBlend0) {
                case once: {
                    bone0.x = bone0.data.x + f3 * f2;
                    return;
                }
                case loop: 
                case pingpong: {
                    bone0.x += (bone0.data.x + f3 - bone0.x) * f2;
                    return;
                }
                case onceReverse: {
                    bone0.x += f3 * f2;
                }
            }
        }

        @Override  // com.esotericsoftware.spine.Animation$BoneTimeline
        public int getBoneIndex() {
            return this.boneIndex;
        }
    }

    public static class TranslateYTimeline extends CurveTimeline1 implements BoneTimeline {
        final int boneIndex;

        public TranslateYTimeline(int v, int v1, int v2) {
            super(v, v1, Property.y.ordinal() + "|" + v2);
            this.boneIndex = v2;
        }

        @Override  // com.esotericsoftware.spine.Animation$Timeline
        public void apply(Skeleton skeleton0, float f, float f1, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
            Bone bone0 = (Bone)skeleton0.bones.get(this.boneIndex);
            if(!bone0.active) {
                return;
            }
            if(f1 < this.frames[0]) {
                switch(animation$MixBlend0) {
                    case once: {
                        bone0.y = bone0.data.y;
                        return;
                    }
                    case loop: {
                        bone0.y += (bone0.data.y - bone0.y) * f2;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            float f3 = this.getCurveValue(f1);
            switch(animation$MixBlend0) {
                case once: {
                    bone0.y = bone0.data.y + f3 * f2;
                    return;
                }
                case loop: 
                case pingpong: {
                    bone0.y += (bone0.data.y + f3 - bone0.y) * f2;
                    return;
                }
                case onceReverse: {
                    bone0.y += f3 * f2;
                }
            }
        }

        @Override  // com.esotericsoftware.spine.Animation$BoneTimeline
        public int getBoneIndex() {
            return this.boneIndex;
        }
    }

    float duration;
    final String name;
    final ObjectSet timelineIds;
    Array timelines;

    public Animation(String s, Array array0, float f) {
        if(s == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        this.name = s;
        this.duration = f;
        this.timelineIds = new ObjectSet(array0.size);
        this.setTimelines(array0);
    }

    public void apply(Skeleton skeleton0, float f, float f1, boolean z, @Null Array array0, float f2, MixBlend animation$MixBlend0, MixDirection animation$MixDirection0) {
        float f5;
        float f4;
        if(skeleton0 == null) {
            throw new IllegalArgumentException("skeleton cannot be null.");
        }
        if(z) {
            float f3 = this.duration;
            if(f3 == 0.0f) {
                f4 = f;
                f5 = f1;
            }
            else {
                f4 = f > 0.0f ? f % f3 : f;
                f5 = f1 % f3;
            }
        }
        else {
            f4 = f;
            f5 = f1;
        }
        Object[] arr_object = this.timelines.items;
        int v = this.timelines.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((Timeline)arr_object[v1]).apply(skeleton0, f4, f5, array0, f2, animation$MixBlend0, animation$MixDirection0);
        }
    }

    public float getDuration() {
        return this.duration;
    }

    public String getName() {
        return this.name;
    }

    public Array getTimelines() {
        return this.timelines;
    }

    public boolean hasTimeline(String[] arr_s) {
        for(int v = 0; v < arr_s.length; ++v) {
            if(this.timelineIds.contains(arr_s[v])) {
                return true;
            }
        }
        return false;
    }

    public void setDuration(float f) {
        this.duration = f;
    }

    public void setTimelines(Array array0) {
        if(array0 == null) {
            throw new IllegalArgumentException("timelines cannot be null.");
        }
        this.timelines = array0;
        int v = array0.size;
        this.timelineIds.clear(v);
        Object[] arr_object = array0.items;
        for(int v1 = 0; v1 < v; ++v1) {
            String[] arr_s = ((Timeline)arr_object[v1]).getPropertyIds();
            this.timelineIds.addAll(arr_s);
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}

