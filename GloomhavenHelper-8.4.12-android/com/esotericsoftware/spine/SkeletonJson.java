package com.esotericsoftware.spine;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.SerializationException;
import com.esotericsoftware.spine.attachments.Attachment;
import com.esotericsoftware.spine.attachments.AttachmentLoader;
import com.esotericsoftware.spine.attachments.AttachmentType;
import com.esotericsoftware.spine.attachments.BoundingBoxAttachment;
import com.esotericsoftware.spine.attachments.ClippingAttachment;
import com.esotericsoftware.spine.attachments.MeshAttachment;
import com.esotericsoftware.spine.attachments.PathAttachment;
import com.esotericsoftware.spine.attachments.PointAttachment;
import com.esotericsoftware.spine.attachments.RegionAttachment;
import com.esotericsoftware.spine.attachments.Sequence.SequenceMode;
import com.esotericsoftware.spine.attachments.Sequence;
import com.esotericsoftware.spine.attachments.VertexAttachment;
import com.esotericsoftware.spine.utils.SpineUtils;
import java.io.InputStream;

public class SkeletonJson extends SkeletonLoader {
    static class LinkedMesh {
        boolean inheritTimelines;
        MeshAttachment mesh;
        String parent;
        String skin;
        int slotIndex;

        public LinkedMesh(MeshAttachment meshAttachment0, String s, int v, String s1, boolean z) {
            this.mesh = meshAttachment0;
            this.skin = s;
            this.slotIndex = v;
            this.parent = s1;
            this.inheritTimelines = z;
        }
    }

    public SkeletonJson(TextureAtlas textureAtlas0) {
        super(textureAtlas0);
    }

    public SkeletonJson(AttachmentLoader attachmentLoader0) {
        super(attachmentLoader0);
    }

    private void readAnimation(JsonValue jsonValue0, String s, SkeletonData skeletonData0) {
        float f116;
        int[] arr_v;
        JsonValue jsonValue65;
        JsonValue jsonValue64;
        Attachment attachment3;
        JsonValue jsonValue63;
        JsonValue jsonValue62;
        JsonValue jsonValue61;
        SlotData slotData7;
        JsonValue jsonValue60;
        JsonValue jsonValue59;
        Attachment attachment2;
        float[] arr_f3;
        int v29;
        DeformTimeline animation$DeformTimeline1;
        int v28;
        Skin skin1;
        JsonValue jsonValue56;
        SlotData slotData6;
        JsonValue jsonValue55;
        float[] arr_f1;
        JsonValue jsonValue47;
        PathConstraintData pathConstraintData2;
        int v20;
        JsonValue jsonValue46;
        PathConstraintMixTimeline animation$PathConstraintMixTimeline1;
        JsonValue jsonValue45;
        float f112;
        JsonValue jsonValue42;
        PathConstraintData pathConstraintData1;
        int v16;
        JsonValue jsonValue41;
        JsonValue jsonValue37;
        TransformConstraintTimeline animation$TransformConstraintTimeline1;
        JsonValue jsonValue36;
        float f100;
        float f99;
        float f78;
        JsonValue jsonValue31;
        IkConstraintTimeline animation$IkConstraintTimeline1;
        JsonValue jsonValue30;
        float f70;
        RGB2Timeline animation$RGB2Timeline1;
        JsonValue jsonValue22;
        JsonValue jsonValue21;
        JsonValue jsonValue18;
        RGBA2Timeline animation$RGBA2Timeline1;
        RGBTimeline animation$RGBTimeline1;
        SlotData slotData4;
        JsonValue jsonValue15;
        JsonValue jsonValue11;
        float f15;
        JsonValue jsonValue10;
        RGBATimeline animation$RGBATimeline1;
        SlotData slotData2;
        JsonValue jsonValue9;
        JsonValue jsonValue5;
        float f1;
        String s2;
        SlotData slotData1;
        JsonValue jsonValue4;
        float f = this.scale;
        Array array0 = new Array();
        for(JsonValue jsonValue1 = jsonValue0.getChild("slots"); true; jsonValue1 = jsonValue1.next) {
            String s1 = null;
            if(jsonValue1 == null) {
                break;
            }
            SlotData slotData0 = skeletonData0.findSlot(jsonValue1.name);
            if(slotData0 == null) {
                throw new SerializationException("Slot not found: " + jsonValue1.name);
            }
            JsonValue jsonValue2 = jsonValue1.child;
            while(jsonValue2 != null) {
                JsonValue jsonValue3 = jsonValue2.child;
                if(jsonValue3 == null) {
                    jsonValue4 = jsonValue2;
                    slotData1 = slotData0;
                    s2 = s1;
                    f1 = f;
                    jsonValue5 = jsonValue1;
                }
                else {
                    int v = jsonValue2.size;
                    String s3 = jsonValue2.name;
                    if(s3.equals("attachment")) {
                        AttachmentTimeline animation$AttachmentTimeline0 = new AttachmentTimeline(v, slotData0.index);
                        for(int v1 = 0; jsonValue3 != null; ++v1) {
                            animation$AttachmentTimeline0.setFrame(v1, jsonValue3.getFloat("time", 0.0f), jsonValue3.getString("name", s1));
                            jsonValue3 = jsonValue3.next;
                        }
                        array0.add(animation$AttachmentTimeline0);
                        jsonValue4 = jsonValue2;
                        slotData1 = slotData0;
                        s2 = s1;
                        f1 = f;
                        jsonValue5 = jsonValue1;
                    }
                    else {
                        JsonValue jsonValue6 = jsonValue1;
                        if(s3.equals("rgba")) {
                            RGBATimeline animation$RGBATimeline0 = new RGBATimeline(v, v << 2, slotData0.index);
                            float f2 = jsonValue3.getFloat("time", 0.0f);
                            String s4 = jsonValue3.getString("color");
                            float f3 = ((float)Integer.parseInt(s4.substring(0, 2), 16)) / 255.0f;
                            float f4 = ((float)Integer.parseInt(s4.substring(2, 4), 16)) / 255.0f;
                            float f5 = ((float)Integer.parseInt(s4.substring(4, 6), 16)) / 255.0f;
                            float f6 = ((float)Integer.parseInt(s4.substring(6, 8), 16)) / 255.0f;
                            float f7 = f4;
                            float f8 = f2;
                            int v2 = 0;
                            int v3 = 0;
                            float f9 = f3;
                            while(true) {
                                animation$RGBATimeline0.setFrame(v3, f8, f9, f7, f5, f6);
                                JsonValue jsonValue7 = jsonValue3.next;
                                if(jsonValue7 == null) {
                                    break;
                                }
                                float f10 = jsonValue7.getFloat("time", 0.0f);
                                String s5 = jsonValue7.getString("color");
                                float f11 = ((float)Integer.parseInt(s5.substring(0, 2), 16)) / 255.0f;
                                float f12 = ((float)Integer.parseInt(s5.substring(2, 4), 16)) / 255.0f;
                                float f13 = ((float)Integer.parseInt(s5.substring(4, 6), 16)) / 255.0f;
                                float f14 = ((float)Integer.parseInt(s5.substring(6, 8), 16)) / 255.0f;
                                JsonValue jsonValue8 = jsonValue3.get("curve");
                                if(jsonValue8 == null) {
                                    slotData2 = slotData0;
                                    animation$RGBATimeline1 = animation$RGBATimeline0;
                                    f15 = f;
                                    jsonValue9 = jsonValue2;
                                    jsonValue11 = jsonValue6;
                                    jsonValue10 = jsonValue7;
                                }
                                else {
                                    jsonValue9 = jsonValue2;
                                    slotData2 = slotData0;
                                    animation$RGBATimeline1 = animation$RGBATimeline0;
                                    jsonValue10 = jsonValue7;
                                    f15 = f;
                                    jsonValue11 = jsonValue6;
                                    v2 = this.readCurve(jsonValue8, animation$RGBATimeline1, this.readCurve(jsonValue8, animation$RGBATimeline1, this.readCurve(jsonValue8, animation$RGBATimeline1, this.readCurve(jsonValue8, animation$RGBATimeline0, v2, v3, 0, f8, f10, f9, f11, 1.0f), v3, 1, f8, f10, f7, f12, 1.0f), v3, 2, f8, f10, f5, f13, 1.0f), v3, 3, f8, f10, f6, f14, 1.0f);
                                }
                                ++v3;
                                jsonValue6 = jsonValue11;
                                animation$RGBATimeline0 = animation$RGBATimeline1;
                                f5 = f13;
                                jsonValue3 = jsonValue10;
                                f6 = f14;
                                f8 = f10;
                                f9 = f11;
                                f = f15;
                                f7 = f12;
                                jsonValue2 = jsonValue9;
                                slotData0 = slotData2;
                            }
                            animation$RGBATimeline0.shrink(v2);
                            array0.add(animation$RGBATimeline0);
                            jsonValue4 = jsonValue2;
                            slotData1 = slotData0;
                            f1 = f;
                            jsonValue5 = jsonValue6;
                            s2 = null;
                        }
                        else {
                            JsonValue jsonValue12 = jsonValue2;
                            s2 = s1;
                            f1 = f;
                            jsonValue5 = jsonValue6;
                            if(s3.equals("rgb")) {
                                SlotData slotData3 = slotData0;
                                RGBTimeline animation$RGBTimeline0 = new RGBTimeline(v, v * 3, slotData3.index);
                                float f16 = jsonValue3.getFloat("time", 0.0f);
                                String s6 = jsonValue3.getString("color");
                                float f17 = ((float)Integer.parseInt(s6.substring(0, 2), 16)) / 255.0f;
                                float f18 = ((float)Integer.parseInt(s6.substring(2, 4), 16)) / 255.0f;
                                float f19 = f16;
                                float f20 = ((float)Integer.parseInt(s6.substring(4, 6), 16)) / 255.0f;
                                float f21 = f17;
                                float f22 = f18;
                                int v4 = 0;
                                int v5 = 0;
                                while(true) {
                                    animation$RGBTimeline0.setFrame(v5, f19, f21, f22, f20);
                                    JsonValue jsonValue13 = jsonValue3.next;
                                    if(jsonValue13 == null) {
                                        break;
                                    }
                                    float f23 = jsonValue13.getFloat("time", 0.0f);
                                    String s7 = jsonValue13.getString("color");
                                    float f24 = ((float)Integer.parseInt(s7.substring(0, 2), 16)) / 255.0f;
                                    float f25 = ((float)Integer.parseInt(s7.substring(2, 4), 16)) / 255.0f;
                                    float f26 = ((float)Integer.parseInt(s7.substring(4, 6), 16)) / 255.0f;
                                    JsonValue jsonValue14 = jsonValue3.get("curve");
                                    if(jsonValue14 == null) {
                                        jsonValue15 = jsonValue13;
                                        slotData4 = slotData3;
                                        animation$RGBTimeline1 = animation$RGBTimeline0;
                                    }
                                    else {
                                        jsonValue15 = jsonValue13;
                                        slotData4 = slotData3;
                                        animation$RGBTimeline1 = animation$RGBTimeline0;
                                        v4 = this.readCurve(jsonValue14, animation$RGBTimeline1, this.readCurve(jsonValue14, animation$RGBTimeline1, this.readCurve(jsonValue14, animation$RGBTimeline0, v4, v5, 0, f19, f23, f21, f24, 1.0f), v5, 1, f19, f23, f22, f25, 1.0f), v5, 2, f19, f23, f20, f26, 1.0f);
                                    }
                                    ++v5;
                                    slotData3 = slotData4;
                                    animation$RGBTimeline0 = animation$RGBTimeline1;
                                    f19 = f23;
                                    f21 = f24;
                                    f22 = f25;
                                    f20 = f26;
                                    jsonValue3 = jsonValue15;
                                }
                                animation$RGBTimeline0.shrink(v4);
                                array0.add(animation$RGBTimeline0);
                                slotData1 = slotData3;
                            }
                            else {
                                slotData1 = slotData0;
                                if(s3.equals("alpha")) {
                                    array0.add(this.readTimeline(jsonValue3, new AlphaTimeline(v, v, slotData1.index), 0.0f, 1.0f));
                                }
                                else if(s3.equals("rgba2")) {
                                    RGBA2Timeline animation$RGBA2Timeline0 = new RGBA2Timeline(v, v * 7, slotData1.index);
                                    float f27 = jsonValue3.getFloat("time", 0.0f);
                                    String s8 = jsonValue3.getString("light");
                                    float f28 = ((float)Integer.parseInt(s8.substring(0, 2), 16)) / 255.0f;
                                    float f29 = ((float)Integer.parseInt(s8.substring(2, 4), 16)) / 255.0f;
                                    float f30 = ((float)Integer.parseInt(s8.substring(4, 6), 16)) / 255.0f;
                                    float f31 = ((float)Integer.parseInt(s8.substring(6, 8), 16)) / 255.0f;
                                    String s9 = jsonValue3.getString("dark");
                                    float f32 = ((float)Integer.parseInt(s9.substring(0, 2), 16)) / 255.0f;
                                    float f33 = ((float)Integer.parseInt(s9.substring(2, 4), 16)) / 255.0f;
                                    float f34 = f31;
                                    float f35 = ((float)Integer.parseInt(s9.substring(4, 6), 16)) / 255.0f;
                                    float f36 = f28;
                                    float f37 = f30;
                                    float f38 = f29;
                                    float f39 = f32;
                                    int v6 = 0;
                                    float f40 = f27;
                                    int v7 = 0;
                                    while(true) {
                                        animation$RGBA2Timeline0.setFrame(v7, f40, f36, f38, f37, f34, f39, f33, f35);
                                        JsonValue jsonValue16 = jsonValue3.next;
                                        if(jsonValue16 == null) {
                                            break;
                                        }
                                        float f41 = jsonValue16.getFloat("time", 0.0f);
                                        String s10 = jsonValue16.getString("light");
                                        float f42 = ((float)Integer.parseInt(s10.substring(0, 2), 16)) / 255.0f;
                                        float f43 = ((float)Integer.parseInt(s10.substring(2, 4), 16)) / 255.0f;
                                        float f44 = ((float)Integer.parseInt(s10.substring(4, 6), 16)) / 255.0f;
                                        float f45 = ((float)Integer.parseInt(s10.substring(6, 8), 16)) / 255.0f;
                                        String s11 = jsonValue16.getString("dark");
                                        float f46 = ((float)Integer.parseInt(s11.substring(0, 2), 16)) / 255.0f;
                                        float f47 = ((float)Integer.parseInt(s11.substring(2, 4), 16)) / 255.0f;
                                        float f48 = ((float)Integer.parseInt(s11.substring(4, 6), 16)) / 255.0f;
                                        JsonValue jsonValue17 = jsonValue3.get("curve");
                                        if(jsonValue17 == null) {
                                            animation$RGBA2Timeline1 = animation$RGBA2Timeline0;
                                            jsonValue18 = jsonValue16;
                                        }
                                        else {
                                            animation$RGBA2Timeline1 = animation$RGBA2Timeline0;
                                            jsonValue18 = jsonValue16;
                                            v6 = this.readCurve(jsonValue17, animation$RGBA2Timeline1, this.readCurve(jsonValue17, animation$RGBA2Timeline1, this.readCurve(jsonValue17, animation$RGBA2Timeline1, this.readCurve(jsonValue17, animation$RGBA2Timeline1, this.readCurve(jsonValue17, animation$RGBA2Timeline1, this.readCurve(jsonValue17, animation$RGBA2Timeline1, this.readCurve(jsonValue17, animation$RGBA2Timeline0, v6, v7, 0, f40, f41, f36, f42, 1.0f), v7, 1, f40, f41, f38, f43, 1.0f), v7, 2, f40, f41, f37, f44, 1.0f), v7, 3, f40, f41, f34, f45, 1.0f), v7, 4, f40, f41, f39, f46, 1.0f), v7, 5, f40, f41, f33, f47, 1.0f), v7, 6, f40, f41, f35, f48, 1.0f);
                                        }
                                        ++v7;
                                        f40 = f41;
                                        f36 = f42;
                                        f38 = f43;
                                        f37 = f44;
                                        f34 = f45;
                                        f39 = f46;
                                        f33 = f47;
                                        f35 = f48;
                                        animation$RGBA2Timeline0 = animation$RGBA2Timeline1;
                                        jsonValue3 = jsonValue18;
                                    }
                                    animation$RGBA2Timeline0.shrink(v6);
                                    array0.add(animation$RGBA2Timeline0);
                                }
                                else {
                                    if(!s3.equals("rgb2")) {
                                        throw new RuntimeException("Invalid timeline type for a slot: " + s3 + " (" + jsonValue5.name + ")");
                                    }
                                    RGB2Timeline animation$RGB2Timeline0 = new RGB2Timeline(v, v * 6, slotData1.index);
                                    float f49 = jsonValue3.getFloat("time", 0.0f);
                                    String s12 = jsonValue3.getString("light");
                                    float f50 = ((float)Integer.parseInt(s12.substring(0, 2), 16)) / 255.0f;
                                    float f51 = ((float)Integer.parseInt(s12.substring(2, 4), 16)) / 255.0f;
                                    float f52 = ((float)Integer.parseInt(s12.substring(4, 6), 16)) / 255.0f;
                                    String s13 = jsonValue3.getString("dark");
                                    float f53 = ((float)Integer.parseInt(s13.substring(0, 2), 16)) / 255.0f;
                                    float f54 = ((float)Integer.parseInt(s13.substring(2, 4), 16)) / 255.0f;
                                    float f55 = f49;
                                    float f56 = f52;
                                    float f57 = ((float)Integer.parseInt(s13.substring(4, 6), 16)) / 255.0f;
                                    float f58 = f51;
                                    float f59 = f53;
                                    float f60 = f54;
                                    int v8 = 0;
                                    int v9 = 0;
                                    float f61 = f50;
                                    while(true) {
                                        animation$RGB2Timeline0.setFrame(v9, f55, f61, f58, f56, f59, f60, f57);
                                        JsonValue jsonValue19 = jsonValue3.next;
                                        if(jsonValue19 == null) {
                                            break;
                                        }
                                        float f62 = jsonValue19.getFloat("time", 0.0f);
                                        String s14 = jsonValue19.getString("light");
                                        float f63 = ((float)Integer.parseInt(s14.substring(0, 2), 16)) / 255.0f;
                                        float f64 = ((float)Integer.parseInt(s14.substring(2, 4), 16)) / 255.0f;
                                        float f65 = ((float)Integer.parseInt(s14.substring(4, 6), 16)) / 255.0f;
                                        String s15 = jsonValue19.getString("dark");
                                        float f66 = ((float)Integer.parseInt(s15.substring(0, 2), 16)) / 255.0f;
                                        float f67 = ((float)Integer.parseInt(s15.substring(2, 4), 16)) / 255.0f;
                                        float f68 = ((float)Integer.parseInt(s15.substring(4, 6), 16)) / 255.0f;
                                        JsonValue jsonValue20 = jsonValue3.get("curve");
                                        if(jsonValue20 == null) {
                                            jsonValue21 = jsonValue12;
                                            jsonValue22 = jsonValue19;
                                            animation$RGB2Timeline1 = animation$RGB2Timeline0;
                                        }
                                        else {
                                            jsonValue21 = jsonValue12;
                                            jsonValue22 = jsonValue19;
                                            animation$RGB2Timeline1 = animation$RGB2Timeline0;
                                            v8 = this.readCurve(jsonValue20, animation$RGB2Timeline1, this.readCurve(jsonValue20, animation$RGB2Timeline1, this.readCurve(jsonValue20, animation$RGB2Timeline1, this.readCurve(jsonValue20, animation$RGB2Timeline1, this.readCurve(jsonValue20, animation$RGB2Timeline1, this.readCurve(jsonValue20, animation$RGB2Timeline0, v8, v9, 0, f55, f62, f61, f63, 1.0f), v9, 1, f55, f62, f58, f64, 1.0f), v9, 2, f55, f62, f56, f65, 1.0f), v9, 3, f55, f62, f59, f66, 1.0f), v9, 4, f55, f62, f60, f67, 1.0f), v9, 5, f55, f62, f57, f68, 1.0f);
                                        }
                                        ++v9;
                                        f57 = f68;
                                        f55 = f62;
                                        f61 = f63;
                                        f58 = f64;
                                        f56 = f65;
                                        f59 = f66;
                                        f60 = f67;
                                        jsonValue3 = jsonValue22;
                                        jsonValue12 = jsonValue21;
                                        animation$RGB2Timeline0 = animation$RGB2Timeline1;
                                    }
                                    animation$RGB2Timeline0.shrink(v8);
                                    array0.add(animation$RGB2Timeline0);
                                }
                            }
                            jsonValue4 = jsonValue12;
                        }
                    }
                }
                jsonValue2 = jsonValue4.next;
                slotData0 = slotData1;
                jsonValue1 = jsonValue5;
                s1 = s2;
                f = f1;
            }
        }
        float f69 = f;
        for(JsonValue jsonValue23 = jsonValue0.getChild("bones"); jsonValue23 != null; jsonValue23 = jsonValue23.next) {
            BoneData boneData0 = skeletonData0.findBone(jsonValue23.name);
            if(boneData0 == null) {
                throw new SerializationException("Bone not found: " + jsonValue23.name);
            }
            JsonValue jsonValue24 = jsonValue23.child;
            while(jsonValue24 != null) {
                JsonValue jsonValue25 = jsonValue24.child;
                if(jsonValue25 == null) {
                    f70 = f69;
                }
                else {
                    int v10 = jsonValue24.size;
                    String s16 = jsonValue24.name;
                    if(s16.equals("rotate")) {
                        array0.add(this.readTimeline(jsonValue25, new RotateTimeline(v10, v10, boneData0.index), 0.0f, 1.0f));
                        f70 = f69;
                    }
                    else if(s16.equals("translate")) {
                        array0.add(this.readTimeline(jsonValue25, new TranslateTimeline(v10, v10 << 1, boneData0.index), "x", "y", 0.0f, f69));
                        f70 = f69;
                    }
                    else if(s16.equals("translatex")) {
                        f70 = f69;
                        array0.add(this.readTimeline(jsonValue25, new TranslateXTimeline(v10, v10, boneData0.index), 0.0f, f70));
                    }
                    else {
                        f70 = f69;
                        if(s16.equals("translatey")) {
                            array0.add(this.readTimeline(jsonValue25, new TranslateYTimeline(v10, v10, boneData0.index), 0.0f, f70));
                        }
                        else if(s16.equals("scale")) {
                            array0.add(this.readTimeline(jsonValue25, new ScaleTimeline(v10, v10 << 1, boneData0.index), "x", "y", 1.0f, 1.0f));
                        }
                        else if(s16.equals("scalex")) {
                            array0.add(this.readTimeline(jsonValue25, new ScaleXTimeline(v10, v10, boneData0.index), 1.0f, 1.0f));
                        }
                        else if(s16.equals("scaley")) {
                            array0.add(this.readTimeline(jsonValue25, new ScaleYTimeline(v10, v10, boneData0.index), 1.0f, 1.0f));
                        }
                        else if(s16.equals("shear")) {
                            array0.add(this.readTimeline(jsonValue25, new ShearTimeline(v10, v10 << 1, boneData0.index), "x", "y", 0.0f, 1.0f));
                        }
                        else if(s16.equals("shearx")) {
                            array0.add(this.readTimeline(jsonValue25, new ShearXTimeline(v10, v10, boneData0.index), 0.0f, 1.0f));
                        }
                        else {
                            if(!s16.equals("sheary")) {
                                throw new RuntimeException("Invalid timeline type for a bone: " + s16 + " (" + jsonValue23.name + ")");
                            }
                            array0.add(this.readTimeline(jsonValue25, new ShearYTimeline(v10, v10, boneData0.index), 0.0f, 1.0f));
                        }
                    }
                }
                jsonValue24 = jsonValue24.next;
                f69 = f70;
            }
        }
        float f71 = f69;
        for(JsonValue jsonValue26 = jsonValue0.getChild("ik"); jsonValue26 != null; jsonValue26 = jsonValue26.next) {
            JsonValue jsonValue27 = jsonValue26.child;
            if(jsonValue27 != null) {
                IkConstraintData ikConstraintData0 = skeletonData0.findIkConstraint(jsonValue26.name);
                IkConstraintTimeline animation$IkConstraintTimeline0 = new IkConstraintTimeline(jsonValue26.size, jsonValue26.size << 1, skeletonData0.getIkConstraints().indexOf(ikConstraintData0, true));
                float f72 = jsonValue27.getFloat("time", 0.0f);
                float f73 = jsonValue27.getFloat("mix", 1.0f);
                float f74 = jsonValue27.getFloat("softness", 0.0f) * f71;
                int v11 = 0;
                int v12 = 0;
                while(true) {
                    animation$IkConstraintTimeline0.setFrame(v12, f72, f73, f74, (jsonValue27.getBoolean("bendPositive", true) ? 1 : -1), jsonValue27.getBoolean("compress", false), jsonValue27.getBoolean("stretch", false));
                    JsonValue jsonValue28 = jsonValue27.next;
                    if(jsonValue28 == null) {
                        break;
                    }
                    float f75 = jsonValue28.getFloat("time", 0.0f);
                    float f76 = jsonValue28.getFloat("mix", 1.0f);
                    float f77 = jsonValue28.getFloat("softness", 0.0f) * f71;
                    JsonValue jsonValue29 = jsonValue27.get("curve");
                    if(jsonValue29 == null) {
                        jsonValue30 = jsonValue28;
                        animation$IkConstraintTimeline1 = animation$IkConstraintTimeline0;
                        jsonValue31 = jsonValue26;
                        f78 = f71;
                    }
                    else {
                        jsonValue30 = jsonValue28;
                        animation$IkConstraintTimeline1 = animation$IkConstraintTimeline0;
                        jsonValue31 = jsonValue26;
                        f78 = f71;
                        v11 = this.readCurve(jsonValue29, animation$IkConstraintTimeline1, this.readCurve(jsonValue29, animation$IkConstraintTimeline0, v11, v12, 0, f72, f75, f73, f76, 1.0f), v12, 1, f72, f75, f74, f77, f78);
                    }
                    ++v12;
                    jsonValue26 = jsonValue31;
                    f72 = f75;
                    f73 = f76;
                    f74 = f77;
                    animation$IkConstraintTimeline0 = animation$IkConstraintTimeline1;
                    jsonValue27 = jsonValue30;
                    f71 = f78;
                }
                animation$IkConstraintTimeline0.shrink(v11);
                array0.add(animation$IkConstraintTimeline0);
            }
        }
        for(JsonValue jsonValue32 = jsonValue0.getChild("transform"); jsonValue32 != null; jsonValue32 = jsonValue32.next) {
            JsonValue jsonValue33 = jsonValue32.child;
            if(jsonValue33 != null) {
                TransformConstraintData transformConstraintData0 = skeletonData0.findTransformConstraint(jsonValue32.name);
                TransformConstraintTimeline animation$TransformConstraintTimeline0 = new TransformConstraintTimeline(jsonValue32.size, jsonValue32.size * 6, skeletonData0.getTransformConstraints().indexOf(transformConstraintData0, true));
                float f79 = jsonValue33.getFloat("time", 0.0f);
                float f80 = jsonValue33.getFloat("mixRotate", 1.0f);
                float f81 = jsonValue33.getFloat("mixX", 1.0f);
                float f82 = jsonValue33.getFloat("mixY", f81);
                float f83 = jsonValue33.getFloat("mixScaleX", 1.0f);
                float f84 = jsonValue33.getFloat("mixScaleY", f83);
                float f85 = jsonValue33.getFloat("mixShearY", 1.0f);
                float f86 = f79;
                float f87 = f80;
                float f88 = f81;
                float f89 = f82;
                float f90 = f83;
                float f91 = f84;
                int v13 = 0;
                int v14 = 0;
                while(true) {
                    animation$TransformConstraintTimeline0.setFrame(v14, f86, f87, f88, f89, f90, f91, f85);
                    JsonValue jsonValue34 = jsonValue33.next;
                    if(jsonValue34 == null) {
                        break;
                    }
                    float f92 = jsonValue34.getFloat("time", 0.0f);
                    float f93 = jsonValue34.getFloat("mixRotate", 1.0f);
                    float f94 = jsonValue34.getFloat("mixX", 1.0f);
                    float f95 = jsonValue34.getFloat("mixY", f94);
                    float f96 = jsonValue34.getFloat("mixScaleX", 1.0f);
                    float f97 = jsonValue34.getFloat("mixScaleY", f96);
                    float f98 = jsonValue34.getFloat("mixShearY", 1.0f);
                    JsonValue jsonValue35 = jsonValue33.get("curve");
                    if(jsonValue35 == null) {
                        f99 = f96;
                        f100 = f94;
                        jsonValue36 = jsonValue34;
                        animation$TransformConstraintTimeline1 = animation$TransformConstraintTimeline0;
                        jsonValue37 = jsonValue32;
                    }
                    else {
                        f99 = f96;
                        f100 = f94;
                        jsonValue36 = jsonValue34;
                        animation$TransformConstraintTimeline1 = animation$TransformConstraintTimeline0;
                        jsonValue37 = jsonValue32;
                        v13 = this.readCurve(jsonValue35, animation$TransformConstraintTimeline1, this.readCurve(jsonValue35, animation$TransformConstraintTimeline1, this.readCurve(jsonValue35, animation$TransformConstraintTimeline1, this.readCurve(jsonValue35, animation$TransformConstraintTimeline1, this.readCurve(jsonValue35, animation$TransformConstraintTimeline1, this.readCurve(jsonValue35, animation$TransformConstraintTimeline0, v13, v14, 0, f86, f92, f87, f93, 1.0f), v14, 1, f86, f92, f88, f100, 1.0f), v14, 2, f86, f92, f89, f95, 1.0f), v14, 3, f86, f92, f90, f99, 1.0f), v14, 4, f86, f92, f91, f97, 1.0f), v14, 5, f86, f92, f85, f98, 1.0f);
                    }
                    ++v14;
                    f86 = f92;
                    f87 = f93;
                    f89 = f95;
                    f91 = f97;
                    f88 = f100;
                    f90 = f99;
                    jsonValue33 = jsonValue36;
                    animation$TransformConstraintTimeline0 = animation$TransformConstraintTimeline1;
                    jsonValue32 = jsonValue37;
                }
                animation$TransformConstraintTimeline0.shrink(v13);
                array0.add(animation$TransformConstraintTimeline0);
            }
        }
        JsonValue jsonValue38 = jsonValue0.getChild("path");
        while(jsonValue38 != null) {
            PathConstraintData pathConstraintData0 = skeletonData0.findPathConstraint(jsonValue38.name);
            if(pathConstraintData0 == null) {
                throw new SerializationException("Path constraint not found: " + jsonValue38.name);
            }
            int v15 = skeletonData0.pathConstraints.indexOf(pathConstraintData0, true);
            JsonValue jsonValue39 = jsonValue38.child;
            while(jsonValue39 != null) {
                JsonValue jsonValue40 = jsonValue39.child;
                if(jsonValue40 != null) {
                    int v17 = jsonValue39.size;
                    String s17 = jsonValue39.name;
                    if(s17.equals("position")) {
                        array0.add(this.readTimeline(jsonValue40, new PathConstraintPositionTimeline(v17, v17, v15), 0.0f, (pathConstraintData0.positionMode == PositionMode.fixed ? f71 : 1.0f)));
                    }
                    else if(s17.equals("spacing")) {
                        array0.add(this.readTimeline(jsonValue40, new PathConstraintSpacingTimeline(v17, v17, v15), 0.0f, (pathConstraintData0.spacingMode == SpacingMode.length || pathConstraintData0.spacingMode == SpacingMode.fixed ? f71 : 1.0f)));
                    }
                    else if(s17.equals("mix")) {
                        PathConstraintMixTimeline animation$PathConstraintMixTimeline0 = new PathConstraintMixTimeline(v17, v17 * 3, v15);
                        float f101 = jsonValue40.getFloat("time", 0.0f);
                        float f102 = jsonValue40.getFloat("mixRotate", 1.0f);
                        float f103 = jsonValue40.getFloat("mixX", 1.0f);
                        float f104 = f101;
                        float f105 = f102;
                        float f106 = jsonValue40.getFloat("mixY", f103);
                        float f107 = f103;
                        int v18 = 0;
                        int v19 = 0;
                        while(true) {
                            animation$PathConstraintMixTimeline0.setFrame(v19, f104, f105, f107, f106);
                            JsonValue jsonValue43 = jsonValue40.next;
                            if(jsonValue43 == null) {
                                break;
                            }
                            float f108 = jsonValue43.getFloat("time", 0.0f);
                            float f109 = jsonValue43.getFloat("mixRotate", 1.0f);
                            float f110 = jsonValue43.getFloat("mixX", 1.0f);
                            float f111 = jsonValue43.getFloat("mixY", f110);
                            JsonValue jsonValue44 = jsonValue40.get("curve");
                            if(jsonValue44 == null) {
                                f112 = f110;
                                jsonValue45 = jsonValue43;
                                animation$PathConstraintMixTimeline1 = animation$PathConstraintMixTimeline0;
                                jsonValue46 = jsonValue39;
                                v20 = v15;
                                pathConstraintData2 = pathConstraintData0;
                                jsonValue47 = jsonValue38;
                            }
                            else {
                                f112 = f110;
                                jsonValue45 = jsonValue43;
                                animation$PathConstraintMixTimeline1 = animation$PathConstraintMixTimeline0;
                                jsonValue46 = jsonValue39;
                                v20 = v15;
                                pathConstraintData2 = pathConstraintData0;
                                jsonValue47 = jsonValue38;
                                v18 = this.readCurve(jsonValue44, animation$PathConstraintMixTimeline1, this.readCurve(jsonValue44, animation$PathConstraintMixTimeline1, this.readCurve(jsonValue44, animation$PathConstraintMixTimeline0, v18, v19, 0, f104, f108, f105, f109, 1.0f), v19, 1, f104, f108, f107, f112, 1.0f), v19, 2, f104, f108, f106, f111, 1.0f);
                            }
                            ++v19;
                            jsonValue38 = jsonValue47;
                            jsonValue39 = jsonValue46;
                            pathConstraintData0 = pathConstraintData2;
                            f104 = f108;
                            f105 = f109;
                            f106 = f111;
                            animation$PathConstraintMixTimeline0 = animation$PathConstraintMixTimeline1;
                            f107 = f112;
                            jsonValue40 = jsonValue45;
                            v15 = v20;
                        }
                        animation$PathConstraintMixTimeline0.shrink(v18);
                        array0.add(animation$PathConstraintMixTimeline0);
                        jsonValue41 = jsonValue39;
                        v16 = v15;
                        pathConstraintData1 = pathConstraintData0;
                        jsonValue42 = jsonValue38;
                        goto label_512;
                    }
                }
                jsonValue41 = jsonValue39;
                v16 = v15;
                pathConstraintData1 = pathConstraintData0;
                jsonValue42 = jsonValue38;
            label_512:
                jsonValue39 = jsonValue41.next;
                jsonValue38 = jsonValue42;
                pathConstraintData0 = pathConstraintData1;
                v15 = v16;
            }
            jsonValue38 = jsonValue38.next;
        }
        for(JsonValue jsonValue48 = jsonValue0.getChild("attachments"); jsonValue48 != null; jsonValue48 = jsonValue48.next) {
            Skin skin0 = skeletonData0.findSkin(jsonValue48.name);
            if(skin0 == null) {
                throw new SerializationException("Skin not found: " + jsonValue48.name);
            }
            for(JsonValue jsonValue49 = jsonValue48.child; jsonValue49 != null; jsonValue49 = jsonValue49.next) {
                SlotData slotData5 = skeletonData0.findSlot(jsonValue49.name);
                if(slotData5 == null) {
                    throw new SerializationException("Slot not found: " + jsonValue49.name);
                }
                for(JsonValue jsonValue50 = jsonValue49.child; jsonValue50 != null; jsonValue50 = jsonValue50.next) {
                    Attachment attachment0 = skin0.getAttachment(slotData5.index, jsonValue50.name);
                    if(attachment0 == null) {
                        throw new SerializationException("Timeline attachment not found: " + jsonValue50.name);
                    }
                    JsonValue jsonValue51 = jsonValue50.child;
                    while(jsonValue51 != null) {
                        JsonValue jsonValue52 = jsonValue51.child;
                        int v21 = jsonValue51.size;
                        String s18 = jsonValue51.name;
                        if(s18.equals("deform")) {
                            boolean z = ((VertexAttachment)attachment0).getBones() != null;
                            float[] arr_f = ((VertexAttachment)attachment0).getVertices();
                            int v22 = z ? arr_f.length / 3 << 1 : arr_f.length;
                            JsonValue jsonValue53 = jsonValue51;
                            DeformTimeline animation$DeformTimeline0 = new DeformTimeline(v21, v21, slotData5.index, ((VertexAttachment)attachment0));
                            float f113 = jsonValue52.getFloat("time", 0.0f);
                            Attachment attachment1 = attachment0;
                            int v23 = 0;
                            int v24 = 0;
                            while(true) {
                                JsonValue jsonValue54 = jsonValue52.get("vertices");
                                if(jsonValue54 == null) {
                                    arr_f1 = z ? new float[v22] : arr_f;
                                    jsonValue55 = jsonValue50;
                                    slotData6 = slotData5;
                                    jsonValue56 = jsonValue49;
                                    skin1 = skin0;
                                }
                                else {
                                    jsonValue55 = jsonValue50;
                                    float[] arr_f2 = new float[v22];
                                    slotData6 = slotData5;
                                    jsonValue56 = jsonValue49;
                                    int v25 = jsonValue52.getInt("offset", 0);
                                    skin1 = skin0;
                                    SpineUtils.arraycopy(jsonValue54.asFloatArray(), 0, arr_f2, v25, jsonValue54.size);
                                    if(f71 != 1.0f) {
                                        int v26 = jsonValue54.size + v25;
                                        while(v25 < v26) {
                                            arr_f2[v25] *= f71;
                                            ++v25;
                                        }
                                    }
                                    if(!z) {
                                        for(int v27 = 0; v27 < v22; ++v27) {
                                            arr_f2[v27] += arr_f[v27];
                                        }
                                    }
                                    arr_f1 = arr_f2;
                                }
                                animation$DeformTimeline0.setFrame(v24, f113, arr_f1);
                                JsonValue jsonValue57 = jsonValue52.next;
                                if(jsonValue57 == null) {
                                    break;
                                }
                                float f114 = jsonValue57.getFloat("time", 0.0f);
                                JsonValue jsonValue58 = jsonValue52.get("curve");
                                if(jsonValue58 == null) {
                                    v28 = v24;
                                    animation$DeformTimeline1 = animation$DeformTimeline0;
                                    v29 = v22;
                                    arr_f3 = arr_f;
                                    jsonValue59 = jsonValue53;
                                    attachment2 = attachment1;
                                    jsonValue60 = jsonValue55;
                                    slotData7 = slotData6;
                                    jsonValue61 = jsonValue57;
                                    jsonValue62 = jsonValue56;
                                }
                                else {
                                    v28 = v24;
                                    animation$DeformTimeline1 = animation$DeformTimeline0;
                                    v29 = v22;
                                    arr_f3 = arr_f;
                                    attachment2 = attachment1;
                                    jsonValue59 = jsonValue53;
                                    jsonValue60 = jsonValue55;
                                    slotData7 = slotData6;
                                    jsonValue61 = jsonValue57;
                                    jsonValue62 = jsonValue56;
                                    v23 = this.readCurve(jsonValue58, animation$DeformTimeline0, v23, v28, 0, f113, f114, 0.0f, 1.0f, 1.0f);
                                }
                                v24 = v28 + 1;
                                attachment1 = attachment2;
                                jsonValue49 = jsonValue62;
                                jsonValue52 = jsonValue61;
                                skin0 = skin1;
                                f113 = f114;
                                animation$DeformTimeline0 = animation$DeformTimeline1;
                                v22 = v29;
                                arr_f = arr_f3;
                                jsonValue50 = jsonValue60;
                                jsonValue53 = jsonValue59;
                                slotData5 = slotData7;
                            }
                            animation$DeformTimeline0.shrink(v23);
                            array0.add(animation$DeformTimeline0);
                            jsonValue63 = jsonValue53;
                            attachment3 = attachment1;
                            jsonValue64 = jsonValue55;
                            slotData5 = slotData6;
                            jsonValue65 = jsonValue56;
                        }
                        else {
                            attachment3 = attachment0;
                            jsonValue64 = jsonValue50;
                            skin1 = skin0;
                            jsonValue65 = jsonValue49;
                            if(s18.equals("sequence")) {
                                SequenceTimeline animation$SequenceTimeline0 = new SequenceTimeline(v21, slotData5.index, attachment3);
                                float f115 = 0.0f;
                                for(int v30 = 0; jsonValue52 != null; ++v30) {
                                    f115 = jsonValue52.getFloat("delay", f115);
                                    animation$SequenceTimeline0.setFrame(v30, jsonValue52.getFloat("time", 0.0f), SequenceMode.valueOf(jsonValue52.getString("mode", "hold")), jsonValue52.getInt("index", 0), f115);
                                    jsonValue52 = jsonValue52.next;
                                }
                                array0.add(animation$SequenceTimeline0);
                            }
                            jsonValue63 = jsonValue51;
                        }
                        jsonValue51 = jsonValue63.next;
                        attachment0 = attachment3;
                        jsonValue49 = jsonValue65;
                        skin0 = skin1;
                        jsonValue50 = jsonValue64;
                    }
                }
            }
        }
        JsonValue jsonValue66 = jsonValue0.get("drawOrder");
        if(jsonValue66 != null) {
            DrawOrderTimeline animation$DrawOrderTimeline0 = new DrawOrderTimeline(jsonValue66.size);
            int v31 = skeletonData0.slots.size;
            JsonValue jsonValue67 = jsonValue66.child;
            for(int v32 = 0; jsonValue67 != null; ++v32) {
                JsonValue jsonValue68 = jsonValue67.get("offsets");
                if(jsonValue68 == null) {
                    arr_v = null;
                }
                else {
                    arr_v = new int[v31];
                    int v33 = v31 - 1;
                    for(int v34 = v33; v34 >= 0; --v34) {
                        arr_v[v34] = -1;
                    }
                    int[] arr_v1 = new int[v31 - jsonValue68.size];
                    JsonValue jsonValue69 = jsonValue68.child;
                    int v35 = 0;
                    int v36 = 0;
                    while(jsonValue69 != null) {
                        SlotData slotData8 = skeletonData0.findSlot(jsonValue69.getString("slot"));
                        if(slotData8 == null) {
                            throw new SerializationException("Slot not found: " + jsonValue69.getString("slot"));
                        }
                        while(v35 != slotData8.index) {
                            arr_v1[v36] = v35;
                            ++v36;
                            ++v35;
                        }
                        arr_v[jsonValue69.getInt("offset") + v35] = v35;
                        jsonValue69 = jsonValue69.next;
                        ++v35;
                    }
                    while(v35 < v31) {
                        arr_v1[v36] = v35;
                        ++v36;
                        ++v35;
                    }
                    while(v33 >= 0) {
                        if(arr_v[v33] == -1) {
                            --v36;
                            arr_v[v33] = arr_v1[v36];
                        }
                        --v33;
                    }
                }
                animation$DrawOrderTimeline0.setFrame(v32, jsonValue67.getFloat("time", 0.0f), arr_v);
                jsonValue67 = jsonValue67.next;
            }
            array0.add(animation$DrawOrderTimeline0);
        }
        JsonValue jsonValue70 = jsonValue0.get("events");
        if(jsonValue70 == null) {
            f116 = 0.0f;
        }
        else {
            EventTimeline animation$EventTimeline0 = new EventTimeline(jsonValue70.size);
            JsonValue jsonValue71 = jsonValue70.child;
            for(int v37 = 0; jsonValue71 != null; ++v37) {
                EventData eventData0 = skeletonData0.findEvent(jsonValue71.getString("name"));
                if(eventData0 == null) {
                    throw new SerializationException("Event not found: " + jsonValue71.getString("name"));
                }
                Event event0 = new Event(jsonValue71.getFloat("time", 0.0f), eventData0);
                event0.intValue = jsonValue71.getInt("int", eventData0.intValue);
                event0.floatValue = jsonValue71.getFloat("float", eventData0.floatValue);
                event0.stringValue = jsonValue71.getString("string", eventData0.stringValue);
                if(event0.getData().audioPath != null) {
                    event0.volume = jsonValue71.getFloat("volume", eventData0.volume);
                    event0.balance = jsonValue71.getFloat("balance", eventData0.balance);
                }
                animation$EventTimeline0.setFrame(v37, event0);
                jsonValue71 = jsonValue71.next;
            }
            f116 = 0.0f;
            array0.add(animation$EventTimeline0);
        }
        array0.shrink();
        Object[] arr_object = array0.items;
        int v38 = array0.size;
        for(int v39 = 0; v39 < v38; ++v39) {
            f116 = Math.max(f116, ((Timeline)arr_object[v39]).getDuration());
        }
        Animation animation0 = new Animation(s, array0, f116);
        skeletonData0.animations.add(animation0);
    }

    private Attachment readAttachment(JsonValue jsonValue0, Skin skin0, int v, String s, SkeletonData skeletonData0) {
        float f = this.scale;
        String s1 = jsonValue0.getString("name", s);
        switch(com.esotericsoftware.spine.SkeletonJson.1.$SwitchMap$com$esotericsoftware$spine$attachments$AttachmentType[AttachmentType.valueOf(jsonValue0.getString("type", "region")).ordinal()]) {
            case 1: {
                String s2 = jsonValue0.getString("path", s1);
                Sequence sequence0 = this.readSequence(jsonValue0.get("sequence"));
                Attachment attachment0 = this.attachmentLoader.newRegionAttachment(skin0, s1, s2, sequence0);
                if(attachment0 == null) {
                    return null;
                }
                ((RegionAttachment)attachment0).setPath(s2);
                ((RegionAttachment)attachment0).setX(jsonValue0.getFloat("x", 0.0f) * f);
                ((RegionAttachment)attachment0).setY(jsonValue0.getFloat("y", 0.0f) * f);
                ((RegionAttachment)attachment0).setScaleX(jsonValue0.getFloat("scaleX", 1.0f));
                ((RegionAttachment)attachment0).setScaleY(jsonValue0.getFloat("scaleY", 1.0f));
                ((RegionAttachment)attachment0).setRotation(jsonValue0.getFloat("rotation", 0.0f));
                ((RegionAttachment)attachment0).setWidth(jsonValue0.getFloat("width") * f);
                ((RegionAttachment)attachment0).setHeight(jsonValue0.getFloat("height") * f);
                ((RegionAttachment)attachment0).setSequence(sequence0);
                String s3 = jsonValue0.getString("color", null);
                if(s3 != null) {
                    Color.valueOf(s3, ((RegionAttachment)attachment0).getColor());
                }
                if(((RegionAttachment)attachment0).getRegion() != null) {
                    ((RegionAttachment)attachment0).updateRegion();
                }
                return attachment0;
            }
            case 2: {
                Attachment attachment1 = this.attachmentLoader.newBoundingBoxAttachment(skin0, s1);
                if(attachment1 == null) {
                    return null;
                }
                this.readVertices(jsonValue0, ((VertexAttachment)attachment1), jsonValue0.getInt("vertexCount") << 1);
                String s4 = jsonValue0.getString("color", null);
                if(s4 != null) {
                    Color.valueOf(s4, ((BoundingBoxAttachment)attachment1).getColor());
                }
                return attachment1;
            }
            case 3: 
            case 4: {
                String s5 = jsonValue0.getString("path", s1);
                Sequence sequence1 = this.readSequence(jsonValue0.get("sequence"));
                Attachment attachment2 = this.attachmentLoader.newMeshAttachment(skin0, s1, s5, sequence1);
                if(attachment2 == null) {
                    return null;
                }
                ((MeshAttachment)attachment2).setPath(s5);
                String s6 = jsonValue0.getString("color", null);
                if(s6 != null) {
                    Color.valueOf(s6, ((MeshAttachment)attachment2).getColor());
                }
                ((MeshAttachment)attachment2).setWidth(jsonValue0.getFloat("width", 0.0f) * f);
                ((MeshAttachment)attachment2).setHeight(jsonValue0.getFloat("height", 0.0f) * f);
                ((MeshAttachment)attachment2).setSequence(sequence1);
                String s7 = jsonValue0.getString("parent", null);
                if(s7 != null) {
                    this.linkedMeshes.add(new LinkedMesh(((MeshAttachment)attachment2), jsonValue0.getString("skin", null), v, s7, jsonValue0.getBoolean("timelines", true)));
                    return attachment2;
                }
                float[] arr_f = jsonValue0.require("uvs").asFloatArray();
                this.readVertices(jsonValue0, ((VertexAttachment)attachment2), arr_f.length);
                ((MeshAttachment)attachment2).setTriangles(jsonValue0.require("triangles").asShortArray());
                ((MeshAttachment)attachment2).setRegionUVs(arr_f);
                if(((MeshAttachment)attachment2).getRegion() != null) {
                    ((MeshAttachment)attachment2).updateRegion();
                }
                if(jsonValue0.has("hull")) {
                    ((MeshAttachment)attachment2).setHullLength(jsonValue0.require("hull").asInt() << 1);
                }
                if(jsonValue0.has("edges")) {
                    ((MeshAttachment)attachment2).setEdges(jsonValue0.require("edges").asShortArray());
                }
                return attachment2;
            }
            case 5: {
                Attachment attachment3 = this.attachmentLoader.newPathAttachment(skin0, s1);
                if(attachment3 == null) {
                    return null;
                }
                ((PathAttachment)attachment3).setClosed(jsonValue0.getBoolean("closed", false));
                ((PathAttachment)attachment3).setConstantSpeed(jsonValue0.getBoolean("constantSpeed", true));
                int v2 = jsonValue0.getInt("vertexCount");
                this.readVertices(jsonValue0, ((VertexAttachment)attachment3), v2 << 1);
                float[] arr_f1 = new float[v2 / 3];
                JsonValue jsonValue1 = jsonValue0.require("lengths").child;
                for(int v1 = 0; jsonValue1 != null; ++v1) {
                    arr_f1[v1] = jsonValue1.asFloat() * f;
                    jsonValue1 = jsonValue1.next;
                }
                ((PathAttachment)attachment3).setLengths(arr_f1);
                String s8 = jsonValue0.getString("color", null);
                if(s8 != null) {
                    Color.valueOf(s8, ((PathAttachment)attachment3).getColor());
                }
                return attachment3;
            }
            case 6: {
                Attachment attachment4 = this.attachmentLoader.newPointAttachment(skin0, s1);
                if(attachment4 == null) {
                    return null;
                }
                ((PointAttachment)attachment4).setX(jsonValue0.getFloat("x", 0.0f) * f);
                ((PointAttachment)attachment4).setY(jsonValue0.getFloat("y", 0.0f) * f);
                ((PointAttachment)attachment4).setRotation(jsonValue0.getFloat("rotation", 0.0f));
                String s9 = jsonValue0.getString("color", null);
                if(s9 != null) {
                    Color.valueOf(s9, ((PointAttachment)attachment4).getColor());
                }
                return attachment4;
            }
            case 7: {
                Attachment attachment5 = this.attachmentLoader.newClippingAttachment(skin0, s1);
                if(attachment5 == null) {
                    return null;
                }
                String s10 = jsonValue0.getString("end", null);
                if(s10 != null) {
                    SlotData slotData0 = skeletonData0.findSlot(s10);
                    if(slotData0 == null) {
                        throw new SerializationException("Clipping end slot not found: " + s10);
                    }
                    ((ClippingAttachment)attachment5).setEndSlot(slotData0);
                }
                this.readVertices(jsonValue0, ((VertexAttachment)attachment5), jsonValue0.getInt("vertexCount") << 1);
                String s11 = jsonValue0.getString("color", null);
                if(s11 != null) {
                    Color.valueOf(s11, ((ClippingAttachment)attachment5).getColor());
                }
                return attachment5;
            }
            default: {
                return null;
            }
        }
    }

    int readCurve(JsonValue jsonValue0, CurveTimeline animation$CurveTimeline0, int v, int v1, int v2, float f, float f1, float f2, float f3, float f4) {
        if(jsonValue0.isString()) {
            if(jsonValue0.asString().equals("stepped")) {
                animation$CurveTimeline0.setStepped(v1);
            }
            return v;
        }
        JsonValue jsonValue1 = jsonValue0.get(v2 << 2);
        float f5 = jsonValue1.asFloat();
        JsonValue jsonValue2 = jsonValue1.next;
        float f6 = jsonValue2.asFloat();
        JsonValue jsonValue3 = jsonValue2.next;
        SkeletonJson.setBezier(animation$CurveTimeline0, v1, v2, v, f, f2, f5, f6 * f4, jsonValue3.asFloat(), jsonValue3.next.asFloat() * f4, f1, f3);
        return v + 1;
    }

    private Sequence readSequence(@Null JsonValue jsonValue0) {
        if(jsonValue0 == null) {
            return null;
        }
        Sequence sequence0 = new Sequence(jsonValue0.getInt("count"));
        sequence0.setStart(jsonValue0.getInt("start", 1));
        sequence0.setDigits(jsonValue0.getInt("digits", 0));
        sequence0.setSetupIndex(jsonValue0.getInt("setup", 0));
        return sequence0;
    }

    @Override  // com.esotericsoftware.spine.SkeletonLoader
    public SkeletonData readSkeletonData(FileHandle fileHandle0) {
        if(fileHandle0 == null) {
            throw new IllegalArgumentException("file cannot be null.");
        }
        SkeletonData skeletonData0 = this.readSkeletonData(new JsonReader().parse(fileHandle0));
        skeletonData0.name = fileHandle0.nameWithoutExtension();
        return skeletonData0;
    }

    public SkeletonData readSkeletonData(JsonValue jsonValue0) {
        BoneData boneData0;
        if(jsonValue0 == null) {
            throw new IllegalArgumentException("root cannot be null.");
        }
        float f = this.scale;
        SkeletonData skeletonData0 = new SkeletonData();
        JsonValue jsonValue1 = jsonValue0.get("skeleton");
        if(jsonValue1 != null) {
            skeletonData0.hash = jsonValue1.getString("hash", null);
            skeletonData0.version = jsonValue1.getString("spine", null);
            skeletonData0.x = jsonValue1.getFloat("x", 0.0f);
            skeletonData0.y = jsonValue1.getFloat("y", 0.0f);
            skeletonData0.width = jsonValue1.getFloat("width", 0.0f);
            skeletonData0.height = jsonValue1.getFloat("height", 0.0f);
            skeletonData0.fps = jsonValue1.getFloat("fps", 30.0f);
            skeletonData0.imagesPath = jsonValue1.getString("images", null);
            skeletonData0.audioPath = jsonValue1.getString("audio", null);
        }
        for(JsonValue jsonValue2 = jsonValue0.getChild("bones"); jsonValue2 != null; jsonValue2 = jsonValue2.next) {
            String s = jsonValue2.getString("parent", null);
            if(s == null) {
                boneData0 = null;
            }
            else {
                boneData0 = skeletonData0.findBone(s);
                if(boneData0 == null) {
                    throw new SerializationException("Parent bone not found: " + s);
                }
            }
            BoneData boneData1 = new BoneData(skeletonData0.bones.size, jsonValue2.getString("name"), boneData0);
            boneData1.length = jsonValue2.getFloat("length", 0.0f) * f;
            boneData1.x = jsonValue2.getFloat("x", 0.0f) * f;
            boneData1.y = jsonValue2.getFloat("y", 0.0f) * f;
            boneData1.rotation = jsonValue2.getFloat("rotation", 0.0f);
            boneData1.scaleX = jsonValue2.getFloat("scaleX", 1.0f);
            boneData1.scaleY = jsonValue2.getFloat("scaleY", 1.0f);
            boneData1.shearX = jsonValue2.getFloat("shearX", 0.0f);
            boneData1.shearY = jsonValue2.getFloat("shearY", 0.0f);
            boneData1.transformMode = TransformMode.valueOf(jsonValue2.getString("transform", "normal"));
            boneData1.skinRequired = jsonValue2.getBoolean("skin", false);
            String s1 = jsonValue2.getString("color", null);
            if(s1 != null) {
                Color.valueOf(s1, boneData1.getColor());
            }
            skeletonData0.bones.add(boneData1);
        }
        for(JsonValue jsonValue3 = jsonValue0.getChild("slots"); jsonValue3 != null; jsonValue3 = jsonValue3.next) {
            String s2 = jsonValue3.getString("name");
            String s3 = jsonValue3.getString("bone");
            BoneData boneData2 = skeletonData0.findBone(s3);
            if(boneData2 == null) {
                throw new SerializationException("Slot bone not found: " + s3);
            }
            SlotData slotData0 = new SlotData(skeletonData0.slots.size, s2, boneData2);
            String s4 = jsonValue3.getString("color", null);
            if(s4 != null) {
                Color.valueOf(s4, slotData0.getColor());
            }
            String s5 = jsonValue3.getString("dark", null);
            if(s5 != null) {
                slotData0.setDarkColor(Color.valueOf(s5));
            }
            slotData0.attachmentName = jsonValue3.getString("attachment", null);
            slotData0.blendMode = BlendMode.valueOf(jsonValue3.getString("blend", "normal"));
            skeletonData0.slots.add(slotData0);
        }
        for(JsonValue jsonValue4 = jsonValue0.getChild("ik"); jsonValue4 != null; jsonValue4 = jsonValue4.next) {
            IkConstraintData ikConstraintData0 = new IkConstraintData(jsonValue4.getString("name"));
            ikConstraintData0.order = jsonValue4.getInt("order", 0);
            ikConstraintData0.skinRequired = jsonValue4.getBoolean("skin", false);
            for(JsonValue jsonValue5 = jsonValue4.getChild("bones"); jsonValue5 != null; jsonValue5 = jsonValue5.next) {
                BoneData boneData3 = skeletonData0.findBone(jsonValue5.asString());
                if(boneData3 == null) {
                    throw new SerializationException("IK bone not found: " + jsonValue5);
                }
                ikConstraintData0.bones.add(boneData3);
            }
            String s6 = jsonValue4.getString("target");
            ikConstraintData0.target = skeletonData0.findBone(s6);
            if(ikConstraintData0.target == null) {
                throw new SerializationException("IK target bone not found: " + s6);
            }
            ikConstraintData0.mix = jsonValue4.getFloat("mix", 1.0f);
            ikConstraintData0.softness = jsonValue4.getFloat("softness", 0.0f) * f;
            ikConstraintData0.bendDirection = jsonValue4.getBoolean("bendPositive", true) ? 1 : -1;
            ikConstraintData0.compress = jsonValue4.getBoolean("compress", false);
            ikConstraintData0.stretch = jsonValue4.getBoolean("stretch", false);
            ikConstraintData0.uniform = jsonValue4.getBoolean("uniform", false);
            skeletonData0.ikConstraints.add(ikConstraintData0);
        }
        for(JsonValue jsonValue6 = jsonValue0.getChild("transform"); jsonValue6 != null; jsonValue6 = jsonValue6.next) {
            TransformConstraintData transformConstraintData0 = new TransformConstraintData(jsonValue6.getString("name"));
            transformConstraintData0.order = jsonValue6.getInt("order", 0);
            transformConstraintData0.skinRequired = jsonValue6.getBoolean("skin", false);
            for(JsonValue jsonValue7 = jsonValue6.getChild("bones"); jsonValue7 != null; jsonValue7 = jsonValue7.next) {
                BoneData boneData4 = skeletonData0.findBone(jsonValue7.asString());
                if(boneData4 == null) {
                    throw new SerializationException("Transform constraint bone not found: " + jsonValue7);
                }
                transformConstraintData0.bones.add(boneData4);
            }
            String s7 = jsonValue6.getString("target");
            transformConstraintData0.target = skeletonData0.findBone(s7);
            if(transformConstraintData0.target == null) {
                throw new SerializationException("Transform constraint target bone not found: " + s7);
            }
            transformConstraintData0.local = jsonValue6.getBoolean("local", false);
            transformConstraintData0.relative = jsonValue6.getBoolean("relative", false);
            transformConstraintData0.offsetRotation = jsonValue6.getFloat("rotation", 0.0f);
            transformConstraintData0.offsetX = jsonValue6.getFloat("x", 0.0f) * f;
            transformConstraintData0.offsetY = jsonValue6.getFloat("y", 0.0f) * f;
            transformConstraintData0.offsetScaleX = jsonValue6.getFloat("scaleX", 0.0f);
            transformConstraintData0.offsetScaleY = jsonValue6.getFloat("scaleY", 0.0f);
            transformConstraintData0.offsetShearY = jsonValue6.getFloat("shearY", 0.0f);
            transformConstraintData0.mixRotate = jsonValue6.getFloat("mixRotate", 1.0f);
            transformConstraintData0.mixX = jsonValue6.getFloat("mixX", 1.0f);
            transformConstraintData0.mixY = jsonValue6.getFloat("mixY", transformConstraintData0.mixX);
            transformConstraintData0.mixScaleX = jsonValue6.getFloat("mixScaleX", 1.0f);
            transformConstraintData0.mixScaleY = jsonValue6.getFloat("mixScaleY", transformConstraintData0.mixScaleX);
            transformConstraintData0.mixShearY = jsonValue6.getFloat("mixShearY", 1.0f);
            skeletonData0.transformConstraints.add(transformConstraintData0);
        }
        for(JsonValue jsonValue8 = jsonValue0.getChild("path"); jsonValue8 != null; jsonValue8 = jsonValue8.next) {
            PathConstraintData pathConstraintData0 = new PathConstraintData(jsonValue8.getString("name"));
            pathConstraintData0.order = jsonValue8.getInt("order", 0);
            pathConstraintData0.skinRequired = jsonValue8.getBoolean("skin", false);
            for(JsonValue jsonValue9 = jsonValue8.getChild("bones"); jsonValue9 != null; jsonValue9 = jsonValue9.next) {
                BoneData boneData5 = skeletonData0.findBone(jsonValue9.asString());
                if(boneData5 == null) {
                    throw new SerializationException("Path bone not found: " + jsonValue9);
                }
                pathConstraintData0.bones.add(boneData5);
            }
            String s8 = jsonValue8.getString("target");
            pathConstraintData0.target = skeletonData0.findSlot(s8);
            if(pathConstraintData0.target == null) {
                throw new SerializationException("Path target slot not found: " + s8);
            }
            pathConstraintData0.positionMode = PositionMode.valueOf(jsonValue8.getString("positionMode", "percent"));
            pathConstraintData0.spacingMode = SpacingMode.valueOf(jsonValue8.getString("spacingMode", "length"));
            pathConstraintData0.rotateMode = RotateMode.valueOf(jsonValue8.getString("rotateMode", "tangent"));
            pathConstraintData0.offsetRotation = jsonValue8.getFloat("rotation", 0.0f);
            pathConstraintData0.position = jsonValue8.getFloat("position", 0.0f);
            if(pathConstraintData0.positionMode == PositionMode.fixed) {
                pathConstraintData0.position *= f;
            }
            pathConstraintData0.spacing = jsonValue8.getFloat("spacing", 0.0f);
            if(pathConstraintData0.spacingMode == SpacingMode.length || pathConstraintData0.spacingMode == SpacingMode.fixed) {
                pathConstraintData0.spacing *= f;
            }
            pathConstraintData0.mixRotate = jsonValue8.getFloat("mixRotate", 1.0f);
            pathConstraintData0.mixX = jsonValue8.getFloat("mixX", 1.0f);
            pathConstraintData0.mixY = jsonValue8.getFloat("mixY", 1.0f);
            skeletonData0.pathConstraints.add(pathConstraintData0);
        }
        for(JsonValue jsonValue10 = jsonValue0.getChild("skins"); jsonValue10 != null; jsonValue10 = jsonValue10.next) {
            Skin skin0 = new Skin(jsonValue10.getString("name"));
            for(JsonValue jsonValue11 = jsonValue10.getChild("bones"); jsonValue11 != null; jsonValue11 = jsonValue11.next) {
                BoneData boneData6 = skeletonData0.findBone(jsonValue11.asString());
                if(boneData6 == null) {
                    throw new SerializationException("Skin bone not found: " + jsonValue11);
                }
                skin0.bones.add(boneData6);
            }
            skin0.bones.shrink();
            for(JsonValue jsonValue12 = jsonValue10.getChild("ik"); jsonValue12 != null; jsonValue12 = jsonValue12.next) {
                IkConstraintData ikConstraintData1 = skeletonData0.findIkConstraint(jsonValue12.asString());
                if(ikConstraintData1 == null) {
                    throw new SerializationException("Skin IK constraint not found: " + jsonValue12);
                }
                skin0.constraints.add(ikConstraintData1);
            }
            for(JsonValue jsonValue13 = jsonValue10.getChild("transform"); jsonValue13 != null; jsonValue13 = jsonValue13.next) {
                TransformConstraintData transformConstraintData1 = skeletonData0.findTransformConstraint(jsonValue13.asString());
                if(transformConstraintData1 == null) {
                    throw new SerializationException("Skin transform constraint not found: " + jsonValue13);
                }
                skin0.constraints.add(transformConstraintData1);
            }
            for(JsonValue jsonValue14 = jsonValue10.getChild("path"); jsonValue14 != null; jsonValue14 = jsonValue14.next) {
                PathConstraintData pathConstraintData1 = skeletonData0.findPathConstraint(jsonValue14.asString());
                if(pathConstraintData1 == null) {
                    throw new SerializationException("Skin path constraint not found: " + jsonValue14);
                }
                skin0.constraints.add(pathConstraintData1);
            }
            skin0.constraints.shrink();
            for(JsonValue jsonValue15 = jsonValue10.getChild("attachments"); jsonValue15 != null; jsonValue15 = jsonValue15.next) {
                SlotData slotData1 = skeletonData0.findSlot(jsonValue15.name);
                if(slotData1 == null) {
                    throw new SerializationException("Slot not found: " + jsonValue15.name);
                }
                for(JsonValue jsonValue16 = jsonValue15.child; jsonValue16 != null; jsonValue16 = jsonValue16.next) {
                    try {
                        Attachment attachment0 = this.readAttachment(jsonValue16, skin0, slotData1.index, jsonValue16.name, skeletonData0);
                        if(attachment0 != null) {
                            skin0.setAttachment(slotData1.index, jsonValue16.name, attachment0);
                        }
                    }
                    catch(Throwable throwable0) {
                        throw new SerializationException("Error reading attachment: " + jsonValue16.name + ", skin: " + skin0, throwable0);
                    }
                }
            }
            skeletonData0.skins.add(skin0);
            if(skin0.name.equals("default")) {
                skeletonData0.defaultSkin = skin0;
            }
        }
        Object[] arr_object = this.linkedMeshes.items;
        int v = this.linkedMeshes.size;
        for(int v1 = 0; v1 < v; ++v1) {
            LinkedMesh skeletonJson$LinkedMesh0 = (LinkedMesh)arr_object[v1];
            Skin skin1 = skeletonJson$LinkedMesh0.skin == null ? skeletonData0.getDefaultSkin() : skeletonData0.findSkin(skeletonJson$LinkedMesh0.skin);
            if(skin1 == null) {
                throw new SerializationException("Skin not found: " + skeletonJson$LinkedMesh0.skin);
            }
            Attachment attachment1 = skin1.getAttachment(skeletonJson$LinkedMesh0.slotIndex, skeletonJson$LinkedMesh0.parent);
            if(attachment1 == null) {
                throw new SerializationException("Parent mesh not found: " + skeletonJson$LinkedMesh0.parent);
            }
            MeshAttachment meshAttachment0 = skeletonJson$LinkedMesh0.mesh;
            VertexAttachment vertexAttachment0 = skeletonJson$LinkedMesh0.inheritTimelines ? ((VertexAttachment)attachment1) : skeletonJson$LinkedMesh0.mesh;
            meshAttachment0.setTimelineAttachment(vertexAttachment0);
            skeletonJson$LinkedMesh0.mesh.setParentMesh(((MeshAttachment)attachment1));
            if(skeletonJson$LinkedMesh0.mesh.getRegion() != null) {
                skeletonJson$LinkedMesh0.mesh.updateRegion();
            }
        }
        this.linkedMeshes.clear();
        for(JsonValue jsonValue17 = jsonValue0.getChild("events"); jsonValue17 != null; jsonValue17 = jsonValue17.next) {
            EventData eventData0 = new EventData(jsonValue17.name);
            eventData0.intValue = jsonValue17.getInt("int", 0);
            eventData0.floatValue = jsonValue17.getFloat("float", 0.0f);
            eventData0.stringValue = jsonValue17.getString("string", "");
            eventData0.audioPath = jsonValue17.getString("audio", null);
            if(eventData0.audioPath != null) {
                eventData0.volume = jsonValue17.getFloat("volume", 1.0f);
                eventData0.balance = jsonValue17.getFloat("balance", 0.0f);
            }
            skeletonData0.events.add(eventData0);
        }
        for(JsonValue jsonValue18 = jsonValue0.getChild("animations"); jsonValue18 != null; jsonValue18 = jsonValue18.next) {
            try {
                this.readAnimation(jsonValue18, jsonValue18.name, skeletonData0);
            }
            catch(Throwable throwable1) {
                throw new SerializationException("Error reading animation: " + jsonValue18.name, throwable1);
            }
        }
        skeletonData0.bones.shrink();
        skeletonData0.slots.shrink();
        skeletonData0.skins.shrink();
        skeletonData0.events.shrink();
        skeletonData0.animations.shrink();
        skeletonData0.ikConstraints.shrink();
        return skeletonData0;
    }

    @Override  // com.esotericsoftware.spine.SkeletonLoader
    public SkeletonData readSkeletonData(InputStream inputStream0) {
        if(inputStream0 == null) {
            throw new IllegalArgumentException("dataInput cannot be null.");
        }
        return this.readSkeletonData(new JsonReader().parse(inputStream0));
    }

    private Timeline readTimeline(JsonValue jsonValue0, CurveTimeline1 animation$CurveTimeline10, float f, float f1) {
        int v = 0;
        float f2 = jsonValue0.getFloat("time", 0.0f);
        float f3 = jsonValue0.getFloat("value", f) * f1;
        int v1 = 0;
        while(true) {
            animation$CurveTimeline10.setFrame(v1, f2, f3);
            JsonValue jsonValue1 = jsonValue0.next;
            if(jsonValue1 == null) {
                break;
            }
            float f4 = jsonValue1.getFloat("time", 0.0f);
            float f5 = jsonValue1.getFloat("value", f) * f1;
            JsonValue jsonValue2 = jsonValue0.get("curve");
            if(jsonValue2 != null) {
                v = this.readCurve(jsonValue2, animation$CurveTimeline10, v, v1, 0, f2, f4, f3, f5, f1);
            }
            ++v1;
            jsonValue0 = jsonValue1;
            f2 = f4;
            f3 = f5;
        }
        animation$CurveTimeline10.shrink(v);
        return animation$CurveTimeline10;
    }

    private Timeline readTimeline(JsonValue jsonValue0, CurveTimeline2 animation$CurveTimeline20, String s, String s1, float f, float f1) {
        int v2;
        JsonValue jsonValue3;
        float f2 = jsonValue0.getFloat("time", 0.0f);
        float f3 = jsonValue0.getFloat(s, f) * f1;
        float f4 = jsonValue0.getFloat(s1, f) * f1;
        int v = 0;
        int v1 = 0;
        while(true) {
            animation$CurveTimeline20.setFrame(v1, f2, f3, f4);
            JsonValue jsonValue1 = jsonValue0.next;
            if(jsonValue1 == null) {
                break;
            }
            float f5 = jsonValue1.getFloat("time", 0.0f);
            float f6 = jsonValue1.getFloat(s, f) * f1;
            float f7 = jsonValue1.getFloat(s1, f) * f1;
            JsonValue jsonValue2 = jsonValue0.get("curve");
            if(jsonValue2 == null) {
                jsonValue3 = jsonValue1;
                v2 = v1;
            }
            else {
                jsonValue3 = jsonValue1;
                v2 = v1;
                v = this.readCurve(jsonValue2, animation$CurveTimeline20, this.readCurve(jsonValue2, animation$CurveTimeline20, v, v1, 0, f2, f5, f3, f6, f1), v2, 1, f2, f5, f4, f7, f1);
            }
            v1 = v2 + 1;
            f2 = f5;
            f3 = f6;
            f4 = f7;
            jsonValue0 = jsonValue3;
        }
        animation$CurveTimeline20.shrink(v);
        return animation$CurveTimeline20;
    }

    private void readVertices(JsonValue jsonValue0, VertexAttachment vertexAttachment0, int v) {
        vertexAttachment0.setWorldVerticesLength(v);
        float[] arr_f = jsonValue0.require("vertices").asFloatArray();
        int v1 = 0;
        if(v == arr_f.length) {
            if(this.scale != 1.0f) {
                while(v1 < arr_f.length) {
                    arr_f[v1] *= this.scale;
                    ++v1;
                }
            }
            vertexAttachment0.setVertices(arr_f);
            return;
        }
        FloatArray floatArray0 = new FloatArray(v * 9);
        IntArray intArray0 = new IntArray(v * 3);
        while(v1 < arr_f.length) {
            int v2 = v1 + 1;
            int v3 = (int)arr_f[v1];
            intArray0.add(v3);
            int v4 = (v3 << 2) + v2;
            while(v2 < v4) {
                intArray0.add(((int)arr_f[v2]));
                floatArray0.add(arr_f[v2 + 1] * this.scale);
                floatArray0.add(arr_f[v2 + 2] * this.scale);
                floatArray0.add(arr_f[v2 + 3]);
                v2 += 4;
            }
            v1 = v2;
        }
        vertexAttachment0.setBones(intArray0.toArray());
        vertexAttachment0.setVertices(floatArray0.toArray());
    }

    static void setBezier(CurveTimeline animation$CurveTimeline0, int v, int v1, int v2, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7) {
        animation$CurveTimeline0.setBezier(v2, v, v1, f, f1, f2, f3, f4, f5, f6, f7);
    }
}

