package com.esotericsoftware.spine;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DataInput;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
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
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class SkeletonBinary extends SkeletonLoader {
    static class SkeletonInput extends DataInput {
        private char[] chars;
        String[] strings;

        public SkeletonInput(FileHandle fileHandle0) {
            super(fileHandle0.read(0x200));
            this.chars = new char[0x20];
        }

        public SkeletonInput(InputStream inputStream0) {
            super(inputStream0);
            this.chars = new char[0x20];
        }

        @Override  // com.badlogic.gdx.utils.DataInput
        public String readString() throws IOException {
            int v = this.readInt(true);
            switch(v) {
                case 0: {
                    return null;
                }
                case 1: {
                    return "";
                }
                default: {
                    if(this.chars.length < v - 1) {
                        this.chars = new char[v - 1];
                    }
                    char[] arr_c = this.chars;
                    int v1 = 0;
                    int v2 = 0;
                    while(v1 < v - 1) {
                        int v3 = this.read();
                        if(v3 >> 4 == -1) {
                            throw new EOFException();
                        }
                        switch(v3 >> 4) {
                            case 12: 
                            case 13: {
                                arr_c[v2] = (char)((v3 & 0x1F) << 6 | this.read() & 0x3F);
                                v1 += 2;
                                ++v2;
                                break;
                            }
                            case 14: {
                                arr_c[v2] = (char)((v3 & 15) << 12 | (this.read() & 0x3F) << 6 | this.read() & 0x3F);
                                v1 += 3;
                                ++v2;
                                break;
                            }
                            default: {
                                arr_c[v2] = (char)v3;
                                ++v1;
                                ++v2;
                            }
                        }
                    }
                    return new String(arr_c, 0, v2);
                }
            }
        }

        @Null
        public String readStringRef() throws IOException {
            int v = this.readInt(true);
            return v == 0 ? null : this.strings[v - 1];
        }
    }

    static class Vertices {
        int[] bones;
        float[] vertices;

    }

    public static final int ATTACHMENT_DEFORM = 0;
    public static final int ATTACHMENT_SEQUENCE = 1;
    public static final int BONE_ROTATE = 0;
    public static final int BONE_SCALE = 4;
    public static final int BONE_SCALEX = 5;
    public static final int BONE_SCALEY = 6;
    public static final int BONE_SHEAR = 7;
    public static final int BONE_SHEARX = 8;
    public static final int BONE_SHEARY = 9;
    public static final int BONE_TRANSLATE = 1;
    public static final int BONE_TRANSLATEX = 2;
    public static final int BONE_TRANSLATEY = 3;
    public static final int CURVE_BEZIER = 2;
    public static final int CURVE_LINEAR = 0;
    public static final int CURVE_STEPPED = 1;
    public static final int PATH_MIX = 2;
    public static final int PATH_POSITION = 0;
    public static final int PATH_SPACING = 1;
    public static final int SLOT_ALPHA = 5;
    public static final int SLOT_ATTACHMENT = 0;
    public static final int SLOT_RGB = 2;
    public static final int SLOT_RGB2 = 4;
    public static final int SLOT_RGBA = 1;
    public static final int SLOT_RGBA2 = 3;

    public SkeletonBinary(TextureAtlas textureAtlas0) {
        super(textureAtlas0);
    }

    public SkeletonBinary(AttachmentLoader attachmentLoader0) {
        super(attachmentLoader0);
    }

    private Animation readAnimation(SkeletonInput skeletonBinary$SkeletonInput0, String s, SkeletonData skeletonData0) throws IOException {
        int v134;
        int v105;
        Skin skin1;
        int v104;
        int v103;
        int v102;
        int v101;
        DeformTimeline animation$DeformTimeline1;
        Skin skin2;
        int v118;
        int v117;
        float[] arr_f3;
        int v116;
        float[] arr_f1;
        int v106;
        int v80;
        int v79;
        int v78;
        PathConstraintData pathConstraintData1;
        int v77;
        int v76;
        int v89;
        int v88;
        int v87;
        PathConstraintData pathConstraintData2;
        int v86;
        int v85;
        PathConstraintMixTimeline animation$PathConstraintMixTimeline1;
        int v84;
        int v70;
        int v69;
        int v68;
        TransformConstraintTimeline animation$TransformConstraintTimeline1;
        int v67;
        int v59;
        int v58;
        int v57;
        IkConstraintTimeline animation$IkConstraintTimeline1;
        int v56;
        float f59;
        float f1;
        int v12;
        int v11;
        int v10;
        int v9;
        int v8;
        float f57;
        float f56;
        int v40;
        int v39;
        int v38;
        int v37;
        int v36;
        int v35;
        AlphaTimeline animation$AlphaTimeline1;
        int v34;
        int v30;
        float f51;
        RGBA2Timeline animation$RGBA2Timeline1;
        int v25;
        RGBTimeline animation$RGBTimeline1;
        int v21;
        RGBATimeline animation$RGBATimeline1;
        int v17;
        Array array0 = new Array(skeletonBinary$SkeletonInput0.readInt(true));
        float f = this.scale;
        int v = skeletonBinary$SkeletonInput0.readInt(true);
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = skeletonBinary$SkeletonInput0.readInt(true);
            int v3 = skeletonBinary$SkeletonInput0.readInt(true);
            int v4 = 0;
            while(v4 < v3) {
                int v5 = skeletonBinary$SkeletonInput0.readByte();
                int v6 = skeletonBinary$SkeletonInput0.readInt(true);
                int v7 = v6 - 1;
                switch(v5) {
                    case 0: {
                        v8 = v4;
                        v9 = v3;
                        v10 = v2;
                        v11 = v1;
                        v12 = v;
                        f1 = f;
                        AttachmentTimeline animation$AttachmentTimeline0 = new AttachmentTimeline(v6, v10);
                        for(int v13 = 0; v13 < v6; ++v13) {
                            animation$AttachmentTimeline0.setFrame(v13, skeletonBinary$SkeletonInput0.readFloat(), skeletonBinary$SkeletonInput0.readStringRef());
                        }
                        array0.add(animation$AttachmentTimeline0);
                        break;
                    }
                    case 1: {
                        int v14 = v7;
                        v8 = v4;
                        v9 = v3;
                        v10 = v2;
                        v11 = v1;
                        v12 = v;
                        f1 = f;
                        RGBATimeline animation$RGBATimeline0 = new RGBATimeline(v6, skeletonBinary$SkeletonInput0.readInt(true), v10);
                        float f2 = skeletonBinary$SkeletonInput0.readFloat();
                        float f3 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                        float f4 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                        float f5 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                        float f6 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                        int v15 = 0;
                        int v16 = 0;
                        while(true) {
                            animation$RGBATimeline0.setFrame(v16, f2, f3, f4, f5, f6);
                            if(v16 == v14) {
                                break;
                            }
                            float f7 = skeletonBinary$SkeletonInput0.readFloat();
                            float f8 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                            float f9 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                            float f10 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                            float f11 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                            switch(skeletonBinary$SkeletonInput0.readByte()) {
                                case 1: {
                                    v17 = v16;
                                    animation$RGBATimeline1 = animation$RGBATimeline0;
                                    animation$RGBATimeline1.setStepped(v17);
                                    break;
                                }
                                case 2: {
                                    this.setBezier(skeletonBinary$SkeletonInput0, animation$RGBATimeline0, v15, v16, 0, f2, f7, f3, f8, 1.0f);
                                    this.setBezier(skeletonBinary$SkeletonInput0, animation$RGBATimeline0, v15 + 1, v16, 1, f2, f7, f4, f9, 1.0f);
                                    this.setBezier(skeletonBinary$SkeletonInput0, animation$RGBATimeline0, v15 + 2, v16, 2, f2, f7, f5, f10, 1.0f);
                                    this.setBezier(skeletonBinary$SkeletonInput0, animation$RGBATimeline0, v15 + 3, v16, 3, f2, f7, f6, f11, 1.0f);
                                    v15 += 4;
                                    v17 = v16;
                                    animation$RGBATimeline1 = animation$RGBATimeline0;
                                    break;
                                }
                                default: {
                                    v17 = v16;
                                    animation$RGBATimeline1 = animation$RGBATimeline0;
                                }
                            }
                            v16 = v17 + 1;
                            animation$RGBATimeline0 = animation$RGBATimeline1;
                            f2 = f7;
                            f3 = f8;
                            f4 = f9;
                            f5 = f10;
                            f6 = f11;
                        }
                        array0.add(animation$RGBATimeline0);
                        break;
                    }
                    case 2: {
                        int v18 = v7;
                        v8 = v4;
                        v9 = v3;
                        v10 = v2;
                        v11 = v1;
                        v12 = v;
                        f1 = f;
                        RGBTimeline animation$RGBTimeline0 = new RGBTimeline(v6, skeletonBinary$SkeletonInput0.readInt(true), v10);
                        float f12 = skeletonBinary$SkeletonInput0.readFloat();
                        float f13 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                        float f14 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                        float f15 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                        int v19 = 0;
                        int v20 = 0;
                        while(true) {
                            animation$RGBTimeline0.setFrame(v20, f12, f13, f14, f15);
                            if(v20 == v18) {
                                break;
                            }
                            float f16 = skeletonBinary$SkeletonInput0.readFloat();
                            float f17 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                            float f18 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                            float f19 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                            switch(skeletonBinary$SkeletonInput0.readByte()) {
                                case 1: {
                                    v21 = v20;
                                    animation$RGBTimeline1 = animation$RGBTimeline0;
                                    animation$RGBTimeline1.setStepped(v21);
                                    break;
                                }
                                case 2: {
                                    this.setBezier(skeletonBinary$SkeletonInput0, animation$RGBTimeline0, v19, v20, 0, f12, f16, f13, f17, 1.0f);
                                    this.setBezier(skeletonBinary$SkeletonInput0, animation$RGBTimeline0, v19 + 1, v20, 1, f12, f16, f14, f18, 1.0f);
                                    this.setBezier(skeletonBinary$SkeletonInput0, animation$RGBTimeline0, v19 + 2, v20, 2, f12, f16, f15, f19, 1.0f);
                                    v19 += 3;
                                    v21 = v20;
                                    animation$RGBTimeline1 = animation$RGBTimeline0;
                                    break;
                                }
                                default: {
                                    v21 = v20;
                                    animation$RGBTimeline1 = animation$RGBTimeline0;
                                }
                            }
                            v20 = v21 + 1;
                            animation$RGBTimeline0 = animation$RGBTimeline1;
                            f12 = f16;
                            f13 = f17;
                            f14 = f18;
                            f15 = f19;
                        }
                        array0.add(animation$RGBTimeline0);
                        break;
                    }
                    case 3: {
                        int v22 = v7;
                        v8 = v4;
                        v9 = v3;
                        v10 = v2;
                        v11 = v1;
                        v12 = v;
                        f1 = f;
                        RGBA2Timeline animation$RGBA2Timeline0 = new RGBA2Timeline(v6, skeletonBinary$SkeletonInput0.readInt(true), v10);
                        float f20 = skeletonBinary$SkeletonInput0.readFloat();
                        float f21 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                        float f22 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                        float f23 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                        float f24 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                        float f25 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                        float f26 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                        float f27 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                        int v23 = 0;
                        int v24 = 0;
                        while(true) {
                            animation$RGBA2Timeline0.setFrame(v24, f20, f21, f22, f23, f24, f25, f26, f27);
                            if(v24 == v22) {
                                break;
                            }
                            float f28 = skeletonBinary$SkeletonInput0.readFloat();
                            float f29 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                            float f30 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                            float f31 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                            float f32 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                            float f33 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                            float f34 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                            float f35 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                            switch(skeletonBinary$SkeletonInput0.readByte()) {
                                case 1: {
                                    v25 = v24;
                                    animation$RGBA2Timeline1 = animation$RGBA2Timeline0;
                                    animation$RGBA2Timeline1.setStepped(v25);
                                    break;
                                }
                                case 2: {
                                    this.setBezier(skeletonBinary$SkeletonInput0, animation$RGBA2Timeline0, v23, v24, 0, f20, f28, f21, f29, 1.0f);
                                    this.setBezier(skeletonBinary$SkeletonInput0, animation$RGBA2Timeline0, v23 + 1, v24, 1, f20, f28, f22, f30, 1.0f);
                                    this.setBezier(skeletonBinary$SkeletonInput0, animation$RGBA2Timeline0, v23 + 2, v24, 2, f20, f28, f23, f31, 1.0f);
                                    this.setBezier(skeletonBinary$SkeletonInput0, animation$RGBA2Timeline0, v23 + 3, v24, 3, f20, f28, f24, f32, 1.0f);
                                    this.setBezier(skeletonBinary$SkeletonInput0, animation$RGBA2Timeline0, v23 + 4, v24, 4, f20, f28, f25, f33, 1.0f);
                                    this.setBezier(skeletonBinary$SkeletonInput0, animation$RGBA2Timeline0, v23 + 5, v24, 5, f20, f28, f26, f34, 1.0f);
                                    this.setBezier(skeletonBinary$SkeletonInput0, animation$RGBA2Timeline0, v23 + 6, v24, 6, f20, f28, f27, f35, 1.0f);
                                    v23 += 7;
                                    v25 = v24;
                                    animation$RGBA2Timeline1 = animation$RGBA2Timeline0;
                                    break;
                                }
                                default: {
                                    v25 = v24;
                                    animation$RGBA2Timeline1 = animation$RGBA2Timeline0;
                                }
                            }
                            v24 = v25 + 1;
                            animation$RGBA2Timeline0 = animation$RGBA2Timeline1;
                            f20 = f28;
                            f21 = f29;
                            f22 = f30;
                            f23 = f31;
                            f24 = f32;
                            f25 = f33;
                            f26 = f34;
                            f27 = f35;
                        }
                        array0.add(animation$RGBA2Timeline0);
                        break;
                    }
                    case 4: {
                        int v26 = v7;
                        v8 = v4;
                        v9 = v3;
                        v11 = v1;
                        v12 = v;
                        float f36 = f;
                        int v27 = v2;
                        RGB2Timeline animation$RGB2Timeline0 = new RGB2Timeline(v6, skeletonBinary$SkeletonInput0.readInt(true), v27);
                        float f37 = skeletonBinary$SkeletonInput0.readFloat();
                        float f38 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                        float f39 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                        float f40 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                        float f41 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                        float f42 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                        float f43 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                        int v28 = 0;
                        int v29 = 0;
                        while(true) {
                            v10 = v27;
                            animation$RGB2Timeline0.setFrame(v28, f37, f38, f39, f40, f41, f42, f43);
                            if(v28 == v26) {
                                break;
                            }
                            float f44 = skeletonBinary$SkeletonInput0.readFloat();
                            float f45 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                            float f46 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                            float f47 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                            float f48 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                            float f49 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                            float f50 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                            switch(skeletonBinary$SkeletonInput0.readByte()) {
                                case 1: {
                                    f51 = f36;
                                    v30 = v26;
                                    animation$RGB2Timeline0.setStepped(v28);
                                    break;
                                }
                                case 2: {
                                    f51 = f36;
                                    v30 = v26;
                                    this.setBezier(skeletonBinary$SkeletonInput0, animation$RGB2Timeline0, v29, v28, 0, f37, f44, f38, f45, 1.0f);
                                    this.setBezier(skeletonBinary$SkeletonInput0, animation$RGB2Timeline0, v29 + 1, v28, 1, f37, f44, f39, f46, 1.0f);
                                    this.setBezier(skeletonBinary$SkeletonInput0, animation$RGB2Timeline0, v29 + 2, v28, 2, f37, f44, f40, f47, 1.0f);
                                    int v31 = v29 + 4;
                                    this.setBezier(skeletonBinary$SkeletonInput0, animation$RGB2Timeline0, v29 + 3, v28, 3, f37, f44, f41, f48, 1.0f);
                                    this.setBezier(skeletonBinary$SkeletonInput0, animation$RGB2Timeline0, v31, v28, 4, f37, f44, f42, f49, 1.0f);
                                    v29 = v31 + 2;
                                    this.setBezier(skeletonBinary$SkeletonInput0, animation$RGB2Timeline0, v31 + 1, v28, 5, f37, f44, f43, f50, 1.0f);
                                    break;
                                }
                                default: {
                                    f51 = f36;
                                    v30 = v26;
                                }
                            }
                            ++v28;
                            v27 = v10;
                            f37 = f44;
                            f38 = f45;
                            f39 = f46;
                            f40 = f47;
                            f41 = f48;
                            f42 = f49;
                            f43 = f50;
                            v26 = v30;
                            f36 = f51;
                        }
                        array0.add(animation$RGB2Timeline0);
                        f1 = f36;
                        break;
                    }
                    case 5: {
                        AlphaTimeline animation$AlphaTimeline0 = new AlphaTimeline(v6, skeletonBinary$SkeletonInput0.readInt(true), v2);
                        float f52 = skeletonBinary$SkeletonInput0.readFloat();
                        float f53 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                        int v32 = 0;
                        int v33 = 0;
                        while(true) {
                            animation$AlphaTimeline0.setFrame(v32, f52, f53);
                            if(v32 == v7) {
                                break;
                            }
                            float f54 = skeletonBinary$SkeletonInput0.readFloat();
                            float f55 = ((float)skeletonBinary$SkeletonInput0.read()) / 255.0f;
                            switch(skeletonBinary$SkeletonInput0.readByte()) {
                                case 1: {
                                    v34 = v32;
                                    animation$AlphaTimeline1 = animation$AlphaTimeline0;
                                    v35 = v7;
                                    v36 = v4;
                                    v37 = v3;
                                    v38 = v2;
                                    v39 = v1;
                                    v40 = v;
                                    f56 = f55;
                                    f57 = f;
                                    animation$AlphaTimeline1.setStepped(v34);
                                    break;
                                }
                                case 2: {
                                    v35 = v7;
                                    v36 = v4;
                                    v37 = v3;
                                    v38 = v2;
                                    v39 = v1;
                                    v40 = v;
                                    f56 = f55;
                                    f57 = f;
                                    this.setBezier(skeletonBinary$SkeletonInput0, animation$AlphaTimeline0, v33, v32, 0, f52, f54, f53, f55, 1.0f);
                                    ++v33;
                                    v34 = v32;
                                    animation$AlphaTimeline1 = animation$AlphaTimeline0;
                                    break;
                                }
                                default: {
                                    v34 = v32;
                                    animation$AlphaTimeline1 = animation$AlphaTimeline0;
                                    v35 = v7;
                                    v36 = v4;
                                    v37 = v3;
                                    v38 = v2;
                                    v39 = v1;
                                    v40 = v;
                                    f56 = f55;
                                    f57 = f;
                                }
                            }
                            v32 = v34 + 1;
                            animation$AlphaTimeline0 = animation$AlphaTimeline1;
                            f = f57;
                            v = v40;
                            f52 = f54;
                            v1 = v39;
                            f53 = f56;
                            v7 = v35;
                            v4 = v36;
                            v3 = v37;
                            v2 = v38;
                        }
                        array0.add(animation$AlphaTimeline0);
                        v8 = v4;
                        v9 = v3;
                        v10 = v2;
                        v11 = v1;
                        v12 = v;
                        f1 = f;
                        break;
                    }
                    default: {
                        v8 = v4;
                        v9 = v3;
                        v10 = v2;
                        v11 = v1;
                        v12 = v;
                        f1 = f;
                    }
                }
                v4 = v8 + 1;
                v2 = v10;
                v = v12;
                v1 = v11;
                v3 = v9;
                f = f1;
            }
        }
        float f58 = f;
        int v41 = skeletonBinary$SkeletonInput0.readInt(true);
        for(int v42 = 0; v42 < v41; ++v42) {
            int v43 = skeletonBinary$SkeletonInput0.readInt(true);
            int v44 = skeletonBinary$SkeletonInput0.readInt(true);
            int v45 = 0;
            while(v45 < v44) {
                int v46 = skeletonBinary$SkeletonInput0.readByte();
                int v47 = skeletonBinary$SkeletonInput0.readInt(true);
                int v48 = skeletonBinary$SkeletonInput0.readInt(true);
                switch(v46) {
                    case 0: {
                        f59 = f58;
                        array0.add(this.readTimeline(skeletonBinary$SkeletonInput0, new RotateTimeline(v47, v48, v43), 1.0f));
                        break;
                    }
                    case 1: {
                        f59 = f58;
                        array0.add(this.readTimeline(skeletonBinary$SkeletonInput0, new TranslateTimeline(v47, v48, v43), f59));
                        break;
                    }
                    case 2: {
                        f59 = f58;
                        array0.add(this.readTimeline(skeletonBinary$SkeletonInput0, new TranslateXTimeline(v47, v48, v43), f59));
                        break;
                    }
                    case 3: {
                        f59 = f58;
                        array0.add(this.readTimeline(skeletonBinary$SkeletonInput0, new TranslateYTimeline(v47, v48, v43), f59));
                        break;
                    }
                    case 4: {
                        array0.add(this.readTimeline(skeletonBinary$SkeletonInput0, new ScaleTimeline(v47, v48, v43), 1.0f));
                        f59 = f58;
                        break;
                    }
                    case 5: {
                        array0.add(this.readTimeline(skeletonBinary$SkeletonInput0, new ScaleXTimeline(v47, v48, v43), 1.0f));
                        f59 = f58;
                        break;
                    }
                    case 6: {
                        array0.add(this.readTimeline(skeletonBinary$SkeletonInput0, new ScaleYTimeline(v47, v48, v43), 1.0f));
                        f59 = f58;
                        break;
                    }
                    case 7: {
                        array0.add(this.readTimeline(skeletonBinary$SkeletonInput0, new ShearTimeline(v47, v48, v43), 1.0f));
                        f59 = f58;
                        break;
                    }
                    case 8: {
                        array0.add(this.readTimeline(skeletonBinary$SkeletonInput0, new ShearXTimeline(v47, v48, v43), 1.0f));
                        f59 = f58;
                        break;
                    }
                    case 9: {
                        array0.add(this.readTimeline(skeletonBinary$SkeletonInput0, new ShearYTimeline(v47, v48, v43), 1.0f));
                        f59 = f58;
                        break;
                    }
                    default: {
                        f59 = f58;
                    }
                }
                ++v45;
                f58 = f59;
            }
        }
        int v49 = skeletonBinary$SkeletonInput0.readInt(true);
        for(int v50 = 0; v50 < v49; ++v50) {
            int v51 = skeletonBinary$SkeletonInput0.readInt(true);
            int v52 = skeletonBinary$SkeletonInput0.readInt(true);
            int v53 = v52 - 1;
            IkConstraintTimeline animation$IkConstraintTimeline0 = new IkConstraintTimeline(v52, skeletonBinary$SkeletonInput0.readInt(true), v51);
            float f60 = skeletonBinary$SkeletonInput0.readFloat();
            float f61 = skeletonBinary$SkeletonInput0.readFloat();
            float f62 = skeletonBinary$SkeletonInput0.readFloat() * f58;
            int v54 = 0;
            int v55 = 0;
            while(true) {
                animation$IkConstraintTimeline0.setFrame(v55, f60, f61, f62, skeletonBinary$SkeletonInput0.readByte(), skeletonBinary$SkeletonInput0.readBoolean(), skeletonBinary$SkeletonInput0.readBoolean());
                if(v55 == v53) {
                    break;
                }
                float f63 = skeletonBinary$SkeletonInput0.readFloat();
                float f64 = skeletonBinary$SkeletonInput0.readFloat();
                float f65 = skeletonBinary$SkeletonInput0.readFloat() * f58;
                switch(skeletonBinary$SkeletonInput0.readByte()) {
                    case 1: {
                        v56 = v55;
                        animation$IkConstraintTimeline1 = animation$IkConstraintTimeline0;
                        v57 = v53;
                        v58 = v50;
                        v59 = v49;
                        animation$IkConstraintTimeline1.setStepped(v56);
                        break;
                    }
                    case 2: {
                        v57 = v53;
                        v58 = v50;
                        v59 = v49;
                        this.setBezier(skeletonBinary$SkeletonInput0, animation$IkConstraintTimeline0, v54, v55, 0, f60, f63, f61, f64, 1.0f);
                        this.setBezier(skeletonBinary$SkeletonInput0, animation$IkConstraintTimeline0, v54 + 1, v55, 1, f60, f63, f62, f65, f58);
                        v54 += 2;
                        v56 = v55;
                        animation$IkConstraintTimeline1 = animation$IkConstraintTimeline0;
                        break;
                    }
                    default: {
                        v56 = v55;
                        animation$IkConstraintTimeline1 = animation$IkConstraintTimeline0;
                        v57 = v53;
                        v58 = v50;
                        v59 = v49;
                    }
                }
                v55 = v56 + 1;
                animation$IkConstraintTimeline0 = animation$IkConstraintTimeline1;
                v50 = v58;
                f60 = f63;
                f61 = f64;
                f62 = f65;
                v53 = v57;
                v49 = v59;
            }
            array0.add(animation$IkConstraintTimeline0);
        }
        int v60 = skeletonBinary$SkeletonInput0.readInt(true);
        for(int v61 = 0; v61 < v60; ++v61) {
            int v62 = skeletonBinary$SkeletonInput0.readInt(true);
            int v63 = skeletonBinary$SkeletonInput0.readInt(true);
            int v64 = v63 - 1;
            TransformConstraintTimeline animation$TransformConstraintTimeline0 = new TransformConstraintTimeline(v63, skeletonBinary$SkeletonInput0.readInt(true), v62);
            float f66 = skeletonBinary$SkeletonInput0.readFloat();
            float f67 = skeletonBinary$SkeletonInput0.readFloat();
            float f68 = skeletonBinary$SkeletonInput0.readFloat();
            float f69 = skeletonBinary$SkeletonInput0.readFloat();
            float f70 = skeletonBinary$SkeletonInput0.readFloat();
            float f71 = skeletonBinary$SkeletonInput0.readFloat();
            float f72 = skeletonBinary$SkeletonInput0.readFloat();
            int v65 = 0;
            int v66 = 0;
            while(true) {
                animation$TransformConstraintTimeline0.setFrame(v66, f66, f67, f68, f69, f70, f71, f72);
                if(v66 == v64) {
                    break;
                }
                float f73 = skeletonBinary$SkeletonInput0.readFloat();
                float f74 = skeletonBinary$SkeletonInput0.readFloat();
                float f75 = skeletonBinary$SkeletonInput0.readFloat();
                float f76 = skeletonBinary$SkeletonInput0.readFloat();
                float f77 = skeletonBinary$SkeletonInput0.readFloat();
                float f78 = skeletonBinary$SkeletonInput0.readFloat();
                float f79 = skeletonBinary$SkeletonInput0.readFloat();
                switch(skeletonBinary$SkeletonInput0.readByte()) {
                    case 1: {
                        v67 = v66;
                        animation$TransformConstraintTimeline1 = animation$TransformConstraintTimeline0;
                        v68 = v64;
                        v69 = v61;
                        v70 = v60;
                        animation$TransformConstraintTimeline1.setStepped(v67);
                        break;
                    }
                    case 2: {
                        v68 = v64;
                        v69 = v61;
                        v70 = v60;
                        this.setBezier(skeletonBinary$SkeletonInput0, animation$TransformConstraintTimeline0, v65, v66, 0, f66, f73, f67, f74, 1.0f);
                        this.setBezier(skeletonBinary$SkeletonInput0, animation$TransformConstraintTimeline0, v65 + 1, v66, 1, f66, f73, f68, f75, 1.0f);
                        this.setBezier(skeletonBinary$SkeletonInput0, animation$TransformConstraintTimeline0, v65 + 2, v66, 2, f66, f73, f69, f76, 1.0f);
                        this.setBezier(skeletonBinary$SkeletonInput0, animation$TransformConstraintTimeline0, v65 + 3, v66, 3, f66, f73, f70, f77, 1.0f);
                        this.setBezier(skeletonBinary$SkeletonInput0, animation$TransformConstraintTimeline0, v65 + 4, v66, 4, f66, f73, f71, f78, 1.0f);
                        this.setBezier(skeletonBinary$SkeletonInput0, animation$TransformConstraintTimeline0, v65 + 5, v66, 5, f66, f73, f72, f79, 1.0f);
                        v65 += 6;
                        v67 = v66;
                        animation$TransformConstraintTimeline1 = animation$TransformConstraintTimeline0;
                        break;
                    }
                    default: {
                        v67 = v66;
                        animation$TransformConstraintTimeline1 = animation$TransformConstraintTimeline0;
                        v68 = v64;
                        v69 = v61;
                        v70 = v60;
                    }
                }
                v66 = v67 + 1;
                animation$TransformConstraintTimeline0 = animation$TransformConstraintTimeline1;
                v61 = v69;
                f66 = f73;
                f67 = f74;
                f68 = f75;
                f69 = f76;
                f70 = f77;
                f71 = f78;
                f72 = f79;
                v64 = v68;
                v60 = v70;
            }
            array0.add(animation$TransformConstraintTimeline0);
        }
        int v71 = skeletonBinary$SkeletonInput0.readInt(true);
        for(int v72 = 0; v72 < v71; ++v72) {
            int v73 = skeletonBinary$SkeletonInput0.readInt(true);
            PathConstraintData pathConstraintData0 = (PathConstraintData)skeletonData0.pathConstraints.get(v73);
            int v74 = skeletonBinary$SkeletonInput0.readInt(true);
            int v75 = 0;
            while(v75 < v74) {
                switch(skeletonBinary$SkeletonInput0.readByte()) {
                    case 0: {
                        v76 = v75;
                        v77 = v74;
                        pathConstraintData1 = pathConstraintData0;
                        v78 = v73;
                        v79 = v72;
                        v80 = v71;
                        array0.add(this.readTimeline(skeletonBinary$SkeletonInput0, new PathConstraintPositionTimeline(skeletonBinary$SkeletonInput0.readInt(true), skeletonBinary$SkeletonInput0.readInt(true), v78), (pathConstraintData1.positionMode == PositionMode.fixed ? f58 : 1.0f)));
                        break;
                    }
                    case 1: {
                        v76 = v75;
                        v77 = v74;
                        v79 = v72;
                        v80 = v71;
                        v78 = v73;
                        pathConstraintData1 = pathConstraintData0;
                        array0.add(this.readTimeline(skeletonBinary$SkeletonInput0, new PathConstraintSpacingTimeline(skeletonBinary$SkeletonInput0.readInt(true), skeletonBinary$SkeletonInput0.readInt(true), v78), (pathConstraintData1.spacingMode == SpacingMode.length || pathConstraintData1.spacingMode == SpacingMode.fixed ? f58 : 1.0f)));
                        break;
                    }
                    case 2: {
                        PathConstraintMixTimeline animation$PathConstraintMixTimeline0 = new PathConstraintMixTimeline(skeletonBinary$SkeletonInput0.readInt(true), skeletonBinary$SkeletonInput0.readInt(true), v73);
                        float f80 = skeletonBinary$SkeletonInput0.readFloat();
                        float f81 = skeletonBinary$SkeletonInput0.readFloat();
                        float f82 = skeletonBinary$SkeletonInput0.readFloat();
                        float f83 = skeletonBinary$SkeletonInput0.readFloat();
                        int v81 = animation$PathConstraintMixTimeline0.getFrameCount();
                        float f84 = f82;
                        float f85 = f83;
                        int v82 = 0;
                        float f86 = f80;
                        float f87 = f81;
                        int v83 = 0;
                        while(true) {
                            animation$PathConstraintMixTimeline0.setFrame(v83, f86, f87, f84, f85);
                            if(v83 == v81 - 1) {
                                break;
                            }
                            float f88 = skeletonBinary$SkeletonInput0.readFloat();
                            float f89 = skeletonBinary$SkeletonInput0.readFloat();
                            float f90 = skeletonBinary$SkeletonInput0.readFloat();
                            float f91 = skeletonBinary$SkeletonInput0.readFloat();
                            switch(skeletonBinary$SkeletonInput0.readByte()) {
                                case 1: {
                                    v84 = v83;
                                    animation$PathConstraintMixTimeline1 = animation$PathConstraintMixTimeline0;
                                    v85 = v75;
                                    v86 = v74;
                                    pathConstraintData2 = pathConstraintData0;
                                    v87 = v73;
                                    v88 = v72;
                                    v89 = v71;
                                    animation$PathConstraintMixTimeline1.setStepped(v84);
                                    break;
                                }
                                case 2: {
                                    v85 = v75;
                                    v86 = v74;
                                    pathConstraintData2 = pathConstraintData0;
                                    v87 = v73;
                                    v88 = v72;
                                    v89 = v71;
                                    this.setBezier(skeletonBinary$SkeletonInput0, animation$PathConstraintMixTimeline0, v82, v83, 0, f86, f88, f87, f89, 1.0f);
                                    this.setBezier(skeletonBinary$SkeletonInput0, animation$PathConstraintMixTimeline0, v82 + 1, v83, 1, f86, f88, f84, f90, 1.0f);
                                    this.setBezier(skeletonBinary$SkeletonInput0, animation$PathConstraintMixTimeline0, v82 + 2, v83, 2, f86, f88, f85, f91, 1.0f);
                                    v82 += 3;
                                    v84 = v83;
                                    animation$PathConstraintMixTimeline1 = animation$PathConstraintMixTimeline0;
                                    break;
                                }
                                default: {
                                    v84 = v83;
                                    animation$PathConstraintMixTimeline1 = animation$PathConstraintMixTimeline0;
                                    v85 = v75;
                                    v86 = v74;
                                    pathConstraintData2 = pathConstraintData0;
                                    v87 = v73;
                                    v88 = v72;
                                    v89 = v71;
                                }
                            }
                            v83 = v84 + 1;
                            animation$PathConstraintMixTimeline0 = animation$PathConstraintMixTimeline1;
                            v72 = v88;
                            f86 = f88;
                            f87 = f89;
                            f84 = f90;
                            f85 = f91;
                            v71 = v89;
                            v75 = v85;
                            v74 = v86;
                            pathConstraintData0 = pathConstraintData2;
                            v73 = v87;
                        }
                        array0.add(animation$PathConstraintMixTimeline0);
                        v76 = v75;
                        v77 = v74;
                        pathConstraintData1 = pathConstraintData0;
                        v78 = v73;
                        v79 = v72;
                        v80 = v71;
                        break;
                    }
                    default: {
                        v76 = v75;
                        v77 = v74;
                        pathConstraintData1 = pathConstraintData0;
                        v78 = v73;
                        v79 = v72;
                        v80 = v71;
                    }
                }
                v75 = v76 + 1;
                v73 = v78;
                pathConstraintData0 = pathConstraintData1;
                v72 = v79;
                v71 = v80;
                v74 = v77;
            }
        }
        int v90 = skeletonBinary$SkeletonInput0.readInt(true);
        for(int v91 = 0; v91 < v90; ++v91) {
            int v92 = skeletonBinary$SkeletonInput0.readInt(true);
            Skin skin0 = (Skin)skeletonData0.skins.get(v92);
            int v93 = skeletonBinary$SkeletonInput0.readInt(true);
            for(int v94 = 0; v94 < v93; ++v94) {
                int v95 = skeletonBinary$SkeletonInput0.readInt(true);
                int v96 = skeletonBinary$SkeletonInput0.readInt(true);
                int v97 = 0;
                while(v97 < v96) {
                    String s1 = skeletonBinary$SkeletonInput0.readStringRef();
                    Attachment attachment0 = skin0.getAttachment(v95, s1);
                    if(attachment0 == null) {
                        throw new SerializationException("Timeline attachment not found: " + s1);
                    }
                    int v98 = skeletonBinary$SkeletonInput0.readByte();
                    int v99 = skeletonBinary$SkeletonInput0.readInt(true);
                    int v100 = v99 - 1;
                    switch(v98) {
                        case 0: {
                            boolean z = ((VertexAttachment)attachment0).getBones() != null;
                            float[] arr_f = ((VertexAttachment)attachment0).getVertices();
                            if(z) {
                                v101 = v96;
                                v103 = v94;
                                v106 = arr_f.length / 3 << 1;
                            }
                            else {
                                v101 = v96;
                                v103 = v94;
                                v106 = arr_f.length;
                            }
                            v105 = v91;
                            v104 = v93;
                            DeformTimeline animation$DeformTimeline0 = new DeformTimeline(v99, skeletonBinary$SkeletonInput0.readInt(true), v95, ((VertexAttachment)attachment0));
                            float f92 = skeletonBinary$SkeletonInput0.readFloat();
                            int v107 = 0;
                            int v108 = 0;
                            while(true) {
                                int v109 = skeletonBinary$SkeletonInput0.readInt(true);
                                if(v109 == 0) {
                                    arr_f1 = z ? new float[v106] : arr_f;
                                    v102 = v95;
                                }
                                else {
                                    v102 = v95;
                                    float[] arr_f2 = new float[v106];
                                    int v110 = skeletonBinary$SkeletonInput0.readInt(true);
                                    int v111 = v109 + v110;
                                    if(f58 == 1.0f) {
                                        for(int v112 = v110; v112 < v111; ++v112) {
                                            arr_f2[v112] = skeletonBinary$SkeletonInput0.readFloat();
                                        }
                                    }
                                    else {
                                        for(int v113 = v110; v113 < v111; ++v113) {
                                            arr_f2[v113] = skeletonBinary$SkeletonInput0.readFloat() * f58;
                                        }
                                    }
                                    if(!z) {
                                        int v114 = arr_f2.length;
                                        for(int v115 = 0; v115 < v114; ++v115) {
                                            arr_f2[v115] += arr_f[v115];
                                        }
                                    }
                                    arr_f1 = arr_f2;
                                }
                                animation$DeformTimeline0.setFrame(v107, f92, arr_f1);
                                if(v107 == v100) {
                                    break;
                                }
                                float f93 = skeletonBinary$SkeletonInput0.readFloat();
                                switch(skeletonBinary$SkeletonInput0.readByte()) {
                                    case 1: {
                                        v116 = v100;
                                        arr_f3 = arr_f;
                                        v117 = v107;
                                        v118 = v106;
                                        skin2 = skin0;
                                        animation$DeformTimeline1 = animation$DeformTimeline0;
                                        animation$DeformTimeline1.setStepped(v117);
                                        break;
                                    }
                                    case 2: {
                                        v116 = v100;
                                        arr_f3 = arr_f;
                                        v118 = v106;
                                        skin2 = skin0;
                                        animation$DeformTimeline1 = animation$DeformTimeline0;
                                        this.setBezier(skeletonBinary$SkeletonInput0, animation$DeformTimeline0, v108, v107, 0, f92, f93, 0.0f, 1.0f, 1.0f);
                                        ++v108;
                                        v117 = v107;
                                        break;
                                    }
                                    default: {
                                        v116 = v100;
                                        arr_f3 = arr_f;
                                        v117 = v107;
                                        v118 = v106;
                                        skin2 = skin0;
                                        animation$DeformTimeline1 = animation$DeformTimeline0;
                                    }
                                }
                                v107 = v117 + 1;
                                animation$DeformTimeline0 = animation$DeformTimeline1;
                                v95 = v102;
                                f92 = f93;
                                v100 = v116;
                                arr_f = arr_f3;
                                v106 = v118;
                                skin0 = skin2;
                            }
                            array0.add(animation$DeformTimeline0);
                            skin1 = skin0;
                            break;
                        }
                        case 1: {
                            SequenceTimeline animation$SequenceTimeline0 = new SequenceTimeline(v99, v95, attachment0);
                            for(int v119 = 0; v119 < v99; ++v119) {
                                float f94 = skeletonBinary$SkeletonInput0.readFloat();
                                int v120 = skeletonBinary$SkeletonInput0.readInt();
                                animation$SequenceTimeline0.setFrame(v119, f94, SequenceMode.values[v120 & 15], v120 >> 4, skeletonBinary$SkeletonInput0.readFloat());
                            }
                            array0.add(animation$SequenceTimeline0);
                            v101 = v96;
                            v102 = v95;
                            v103 = v94;
                            v104 = v93;
                            skin1 = skin0;
                            v105 = v91;
                            break;
                        }
                        default: {
                            v101 = v96;
                            v102 = v95;
                            v103 = v94;
                            v104 = v93;
                            skin1 = skin0;
                            v105 = v91;
                        }
                    }
                    ++v97;
                    v96 = v101;
                    v94 = v103;
                    v91 = v105;
                    v93 = v104;
                    v95 = v102;
                    skin0 = skin1;
                }
            }
        }
        int v121 = skeletonBinary$SkeletonInput0.readInt(true);
        if(v121 > 0) {
            DrawOrderTimeline animation$DrawOrderTimeline0 = new DrawOrderTimeline(v121);
            int v122 = skeletonData0.slots.size;
            for(int v123 = 0; v123 < v121; ++v123) {
                float f95 = skeletonBinary$SkeletonInput0.readFloat();
                int v124 = skeletonBinary$SkeletonInput0.readInt(true);
                int[] arr_v = new int[v122];
                int v125 = v122 - 1;
                for(int v126 = v125; v126 >= 0; --v126) {
                    arr_v[v126] = -1;
                }
                int[] arr_v1 = new int[v122 - v124];
                int v127 = 0;
                int v128 = 0;
                int v129;
                for(v129 = 0; v127 < v124; ++v129) {
                    int v130 = skeletonBinary$SkeletonInput0.readInt(true);
                    while(v129 != v130) {
                        arr_v1[v128] = v129;
                        ++v128;
                        ++v129;
                    }
                    arr_v[skeletonBinary$SkeletonInput0.readInt(true) + v129] = v129;
                    ++v127;
                }
                while(v129 < v122) {
                    arr_v1[v128] = v129;
                    ++v128;
                    ++v129;
                }
                while(v125 >= 0) {
                    if(arr_v[v125] == -1) {
                        --v128;
                        arr_v[v125] = arr_v1[v128];
                    }
                    --v125;
                }
                animation$DrawOrderTimeline0.setFrame(v123, f95, arr_v);
            }
            array0.add(animation$DrawOrderTimeline0);
        }
        int v131 = skeletonBinary$SkeletonInput0.readInt(true);
        if(v131 > 0) {
            EventTimeline animation$EventTimeline0 = new EventTimeline(v131);
            for(int v132 = 0; v132 < v131; ++v132) {
                float f96 = skeletonBinary$SkeletonInput0.readFloat();
                int v133 = skeletonBinary$SkeletonInput0.readInt(true);
                EventData eventData0 = (EventData)skeletonData0.events.get(v133);
                Event event0 = new Event(f96, eventData0);
                event0.intValue = skeletonBinary$SkeletonInput0.readInt(false);
                event0.floatValue = skeletonBinary$SkeletonInput0.readFloat();
                event0.stringValue = skeletonBinary$SkeletonInput0.readBoolean() ? skeletonBinary$SkeletonInput0.readString() : eventData0.stringValue;
                if(event0.getData().audioPath != null) {
                    event0.volume = skeletonBinary$SkeletonInput0.readFloat();
                    event0.balance = skeletonBinary$SkeletonInput0.readFloat();
                }
                animation$EventTimeline0.setFrame(v132, event0);
            }
            v134 = 0;
            array0.add(animation$EventTimeline0);
        }
        else {
            v134 = 0;
        }
        float f97 = 0.0f;
        Object[] arr_object = array0.items;
        int v135 = array0.size;
        while(v134 < v135) {
            f97 = Math.max(f97, ((Timeline)arr_object[v134]).getDuration());
            ++v134;
        }
        return new Animation(s, array0, f97);
    }

    private Attachment readAttachment(SkeletonInput skeletonBinary$SkeletonInput0, SkeletonData skeletonData0, Skin skin0, int v, String s, boolean z) throws IOException {
        float f11;
        float f10;
        float f9;
        short[] arr_v1;
        float f = this.scale;
        String s1 = skeletonBinary$SkeletonInput0.readStringRef();
        if(s1 == null) {
            s1 = s;
        }
        float f1 = 0.0f;
        int v1 = 0;
        switch(com.esotericsoftware.spine.SkeletonBinary.1.$SwitchMap$com$esotericsoftware$spine$attachments$AttachmentType[AttachmentType.values[skeletonBinary$SkeletonInput0.readByte()].ordinal()]) {
            case 1: {
                String s2 = skeletonBinary$SkeletonInput0.readStringRef();
                float f2 = skeletonBinary$SkeletonInput0.readFloat();
                float f3 = skeletonBinary$SkeletonInput0.readFloat();
                float f4 = skeletonBinary$SkeletonInput0.readFloat();
                float f5 = skeletonBinary$SkeletonInput0.readFloat();
                float f6 = skeletonBinary$SkeletonInput0.readFloat();
                float f7 = skeletonBinary$SkeletonInput0.readFloat();
                float f8 = skeletonBinary$SkeletonInput0.readFloat();
                int v2 = skeletonBinary$SkeletonInput0.readInt();
                Sequence sequence0 = this.readSequence(skeletonBinary$SkeletonInput0);
                if(s2 == null) {
                    s2 = s1;
                }
                Attachment attachment0 = this.attachmentLoader.newRegionAttachment(skin0, s1, s2, sequence0);
                if(attachment0 == null) {
                    return null;
                }
                ((RegionAttachment)attachment0).setPath(s2);
                ((RegionAttachment)attachment0).setX(f3 * f);
                ((RegionAttachment)attachment0).setY(f4 * f);
                ((RegionAttachment)attachment0).setScaleX(f5);
                ((RegionAttachment)attachment0).setScaleY(f6);
                ((RegionAttachment)attachment0).setRotation(f2);
                ((RegionAttachment)attachment0).setWidth(f7 * f);
                ((RegionAttachment)attachment0).setHeight(f8 * f);
                Color.rgba8888ToColor(((RegionAttachment)attachment0).getColor(), v2);
                ((RegionAttachment)attachment0).setSequence(sequence0);
                if(sequence0 == null) {
                    ((RegionAttachment)attachment0).updateRegion();
                }
                return attachment0;
            }
            case 2: {
                int v3 = skeletonBinary$SkeletonInput0.readInt(true);
                Vertices skeletonBinary$Vertices0 = this.readVertices(skeletonBinary$SkeletonInput0, v3);
                if(z) {
                    v1 = skeletonBinary$SkeletonInput0.readInt();
                }
                Attachment attachment1 = this.attachmentLoader.newBoundingBoxAttachment(skin0, s1);
                if(attachment1 == null) {
                    return null;
                }
                ((BoundingBoxAttachment)attachment1).setWorldVerticesLength(v3 << 1);
                ((BoundingBoxAttachment)attachment1).setVertices(skeletonBinary$Vertices0.vertices);
                ((BoundingBoxAttachment)attachment1).setBones(skeletonBinary$Vertices0.bones);
                if(z) {
                    Color.rgba8888ToColor(((BoundingBoxAttachment)attachment1).getColor(), v1);
                }
                return attachment1;
            }
            case 3: {
                String s3 = skeletonBinary$SkeletonInput0.readStringRef();
                int v4 = skeletonBinary$SkeletonInput0.readInt();
                int v5 = skeletonBinary$SkeletonInput0.readInt(true);
                float[] arr_f = this.readFloatArray(skeletonBinary$SkeletonInput0, v5 << 1, 1.0f);
                short[] arr_v = this.readShortArray(skeletonBinary$SkeletonInput0);
                Vertices skeletonBinary$Vertices1 = this.readVertices(skeletonBinary$SkeletonInput0, v5);
                int v6 = skeletonBinary$SkeletonInput0.readInt(true);
                Sequence sequence1 = this.readSequence(skeletonBinary$SkeletonInput0);
                if(z) {
                    arr_v1 = this.readShortArray(skeletonBinary$SkeletonInput0);
                    f9 = skeletonBinary$SkeletonInput0.readFloat();
                    f10 = skeletonBinary$SkeletonInput0.readFloat();
                }
                else {
                    arr_v1 = null;
                    f10 = 0.0f;
                    f9 = 0.0f;
                }
                if(s3 == null) {
                    s3 = s1;
                }
                Attachment attachment2 = this.attachmentLoader.newMeshAttachment(skin0, s1, s3, sequence1);
                if(attachment2 == null) {
                    return null;
                }
                ((MeshAttachment)attachment2).setPath(s3);
                Color.rgba8888ToColor(((MeshAttachment)attachment2).getColor(), v4);
                ((MeshAttachment)attachment2).setBones(skeletonBinary$Vertices1.bones);
                ((MeshAttachment)attachment2).setVertices(skeletonBinary$Vertices1.vertices);
                ((MeshAttachment)attachment2).setWorldVerticesLength(v5 << 1);
                ((MeshAttachment)attachment2).setTriangles(arr_v);
                ((MeshAttachment)attachment2).setRegionUVs(arr_f);
                if(sequence1 == null) {
                    ((MeshAttachment)attachment2).updateRegion();
                }
                ((MeshAttachment)attachment2).setHullLength(v6 << 1);
                ((MeshAttachment)attachment2).setSequence(sequence1);
                if(z) {
                    ((MeshAttachment)attachment2).setEdges(arr_v1);
                    ((MeshAttachment)attachment2).setWidth(f9 * f);
                    ((MeshAttachment)attachment2).setHeight(f10 * f);
                }
                return attachment2;
            }
            case 4: {
                String s4 = skeletonBinary$SkeletonInput0.readStringRef();
                int v7 = skeletonBinary$SkeletonInput0.readInt();
                String s5 = skeletonBinary$SkeletonInput0.readStringRef();
                String s6 = skeletonBinary$SkeletonInput0.readStringRef();
                boolean z1 = skeletonBinary$SkeletonInput0.readBoolean();
                Sequence sequence2 = this.readSequence(skeletonBinary$SkeletonInput0);
                if(z) {
                    f1 = skeletonBinary$SkeletonInput0.readFloat();
                    f11 = skeletonBinary$SkeletonInput0.readFloat();
                }
                else {
                    f11 = 0.0f;
                }
                if(s4 == null) {
                    s4 = s1;
                }
                Attachment attachment3 = this.attachmentLoader.newMeshAttachment(skin0, s1, s4, sequence2);
                if(attachment3 == null) {
                    return null;
                }
                ((MeshAttachment)attachment3).setPath(s4);
                Color.rgba8888ToColor(((MeshAttachment)attachment3).getColor(), v7);
                ((MeshAttachment)attachment3).setSequence(sequence2);
                if(z) {
                    ((MeshAttachment)attachment3).setWidth(f1 * f);
                    ((MeshAttachment)attachment3).setHeight(f11 * f);
                }
                this.linkedMeshes.add(new LinkedMesh(((MeshAttachment)attachment3), s5, v, s6, z1));
                return attachment3;
            }
            case 5: {
                boolean z2 = skeletonBinary$SkeletonInput0.readBoolean();
                boolean z3 = skeletonBinary$SkeletonInput0.readBoolean();
                int v8 = skeletonBinary$SkeletonInput0.readInt(true);
                Vertices skeletonBinary$Vertices2 = this.readVertices(skeletonBinary$SkeletonInput0, v8);
                float[] arr_f1 = new float[v8 / 3];
                for(int v9 = 0; v9 < arr_f1.length; ++v9) {
                    arr_f1[v9] = skeletonBinary$SkeletonInput0.readFloat() * f;
                }
                if(z) {
                    v1 = skeletonBinary$SkeletonInput0.readInt();
                }
                Attachment attachment4 = this.attachmentLoader.newPathAttachment(skin0, s1);
                if(attachment4 == null) {
                    return null;
                }
                ((PathAttachment)attachment4).setClosed(z2);
                ((PathAttachment)attachment4).setConstantSpeed(z3);
                ((PathAttachment)attachment4).setWorldVerticesLength(v8 << 1);
                ((PathAttachment)attachment4).setVertices(skeletonBinary$Vertices2.vertices);
                ((PathAttachment)attachment4).setBones(skeletonBinary$Vertices2.bones);
                ((PathAttachment)attachment4).setLengths(arr_f1);
                if(z) {
                    Color.rgba8888ToColor(((PathAttachment)attachment4).getColor(), v1);
                }
                return attachment4;
            }
            case 6: {
                float f12 = skeletonBinary$SkeletonInput0.readFloat();
                float f13 = skeletonBinary$SkeletonInput0.readFloat();
                float f14 = skeletonBinary$SkeletonInput0.readFloat();
                if(z) {
                    v1 = skeletonBinary$SkeletonInput0.readInt();
                }
                Attachment attachment5 = this.attachmentLoader.newPointAttachment(skin0, s1);
                if(attachment5 == null) {
                    return null;
                }
                ((PointAttachment)attachment5).setX(f13 * f);
                ((PointAttachment)attachment5).setY(f14 * f);
                ((PointAttachment)attachment5).setRotation(f12);
                if(z) {
                    Color.rgba8888ToColor(((PointAttachment)attachment5).getColor(), v1);
                }
                return attachment5;
            }
            case 7: {
                int v10 = skeletonBinary$SkeletonInput0.readInt(true);
                int v11 = skeletonBinary$SkeletonInput0.readInt(true);
                Vertices skeletonBinary$Vertices3 = this.readVertices(skeletonBinary$SkeletonInput0, v11);
                if(z) {
                    v1 = skeletonBinary$SkeletonInput0.readInt();
                }
                Attachment attachment6 = this.attachmentLoader.newClippingAttachment(skin0, s1);
                if(attachment6 == null) {
                    return null;
                }
                ((ClippingAttachment)attachment6).setEndSlot(((SlotData)skeletonData0.slots.get(v10)));
                ((ClippingAttachment)attachment6).setWorldVerticesLength(v11 << 1);
                ((ClippingAttachment)attachment6).setVertices(skeletonBinary$Vertices3.vertices);
                ((ClippingAttachment)attachment6).setBones(skeletonBinary$Vertices3.bones);
                if(z) {
                    Color.rgba8888ToColor(((ClippingAttachment)attachment6).getColor(), v1);
                }
                return attachment6;
            }
            default: {
                return null;
            }
        }
    }

    private float[] readFloatArray(SkeletonInput skeletonBinary$SkeletonInput0, int v, float f) throws IOException {
        float[] arr_f = new float[v];
        int v1 = 0;
        if(f == 1.0f) {
            while(v1 < v) {
                arr_f[v1] = skeletonBinary$SkeletonInput0.readFloat();
                ++v1;
            }
            return arr_f;
        }
        while(v1 < v) {
            arr_f[v1] = skeletonBinary$SkeletonInput0.readFloat() * f;
            ++v1;
        }
        return arr_f;
    }

    private Sequence readSequence(SkeletonInput skeletonBinary$SkeletonInput0) throws IOException {
        if(!skeletonBinary$SkeletonInput0.readBoolean()) {
            return null;
        }
        Sequence sequence0 = new Sequence(skeletonBinary$SkeletonInput0.readInt(true));
        sequence0.setStart(skeletonBinary$SkeletonInput0.readInt(true));
        sequence0.setDigits(skeletonBinary$SkeletonInput0.readInt(true));
        sequence0.setSetupIndex(skeletonBinary$SkeletonInput0.readInt(true));
        return sequence0;
    }

    private short[] readShortArray(SkeletonInput skeletonBinary$SkeletonInput0) throws IOException {
        int v = skeletonBinary$SkeletonInput0.readInt(true);
        short[] arr_v = new short[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_v[v1] = skeletonBinary$SkeletonInput0.readShort();
        }
        return arr_v;
    }

    @Override  // com.esotericsoftware.spine.SkeletonLoader
    public SkeletonData readSkeletonData(FileHandle fileHandle0) {
        if(fileHandle0 == null) {
            throw new IllegalArgumentException("file cannot be null.");
        }
        SkeletonData skeletonData0 = this.readSkeletonData(fileHandle0.read());
        skeletonData0.name = fileHandle0.nameWithoutExtension();
        return skeletonData0;
    }

    @Override  // com.esotericsoftware.spine.SkeletonLoader
    public SkeletonData readSkeletonData(InputStream inputStream0) {
        if(inputStream0 == null) {
            throw new IllegalArgumentException("dataInput cannot be null.");
        }
        float f = this.scale;
        SkeletonInput skeletonBinary$SkeletonInput0 = new SkeletonInput(inputStream0);
        SkeletonData skeletonData0 = new SkeletonData();
        try {
            long v1 = skeletonBinary$SkeletonInput0.readLong();
            skeletonData0.hash = v1 == 0L ? null : Long.toString(v1);
            skeletonData0.version = skeletonBinary$SkeletonInput0.readString();
            if(skeletonData0.version.isEmpty()) {
                skeletonData0.version = null;
            }
            skeletonData0.x = skeletonBinary$SkeletonInput0.readFloat();
            skeletonData0.y = skeletonBinary$SkeletonInput0.readFloat();
            skeletonData0.width = skeletonBinary$SkeletonInput0.readFloat();
            skeletonData0.height = skeletonBinary$SkeletonInput0.readFloat();
            boolean z = skeletonBinary$SkeletonInput0.readBoolean();
            if(z) {
                skeletonData0.fps = skeletonBinary$SkeletonInput0.readFloat();
                skeletonData0.imagesPath = skeletonBinary$SkeletonInput0.readString();
                if(skeletonData0.imagesPath.isEmpty()) {
                    skeletonData0.imagesPath = null;
                }
                skeletonData0.audioPath = skeletonBinary$SkeletonInput0.readString();
                if(skeletonData0.audioPath.isEmpty()) {
                    skeletonData0.audioPath = null;
                }
            }
            int v2 = skeletonBinary$SkeletonInput0.readInt(true);
            String[] arr_s = new String[v2];
            skeletonBinary$SkeletonInput0.strings = arr_s;
            for(int v3 = 0; v3 < v2; ++v3) {
                arr_s[v3] = skeletonBinary$SkeletonInput0.readString();
            }
            int v4 = skeletonBinary$SkeletonInput0.readInt(true);
            Object[] arr_object = skeletonData0.bones.setSize(v4);
            for(int v5 = 0; v5 < v4; ++v5) {
                BoneData boneData0 = new BoneData(v5, skeletonBinary$SkeletonInput0.readString(), (v5 == 0 ? null : ((BoneData)arr_object[skeletonBinary$SkeletonInput0.readInt(true)])));
                boneData0.rotation = skeletonBinary$SkeletonInput0.readFloat();
                boneData0.x = skeletonBinary$SkeletonInput0.readFloat() * f;
                boneData0.y = skeletonBinary$SkeletonInput0.readFloat() * f;
                boneData0.scaleX = skeletonBinary$SkeletonInput0.readFloat();
                boneData0.scaleY = skeletonBinary$SkeletonInput0.readFloat();
                boneData0.shearX = skeletonBinary$SkeletonInput0.readFloat();
                boneData0.shearY = skeletonBinary$SkeletonInput0.readFloat();
                boneData0.length = skeletonBinary$SkeletonInput0.readFloat() * f;
                boneData0.transformMode = TransformMode.values[skeletonBinary$SkeletonInput0.readInt(true)];
                boneData0.skinRequired = skeletonBinary$SkeletonInput0.readBoolean();
                if(z) {
                    int v6 = skeletonBinary$SkeletonInput0.readInt();
                    Color.rgba8888ToColor(boneData0.color, v6);
                }
                arr_object[v5] = boneData0;
            }
            int v7 = skeletonBinary$SkeletonInput0.readInt(true);
            Object[] arr_object1 = skeletonData0.slots.setSize(v7);
            for(int v8 = 0; v8 < v7; ++v8) {
                SlotData slotData0 = new SlotData(v8, skeletonBinary$SkeletonInput0.readString(), ((BoneData)arr_object[skeletonBinary$SkeletonInput0.readInt(true)]));
                int v9 = skeletonBinary$SkeletonInput0.readInt();
                Color.rgba8888ToColor(slotData0.color, v9);
                int v10 = skeletonBinary$SkeletonInput0.readInt();
                if(v10 != -1) {
                    Color color0 = new Color();
                    slotData0.darkColor = color0;
                    Color.rgb888ToColor(color0, v10);
                }
                slotData0.attachmentName = skeletonBinary$SkeletonInput0.readStringRef();
                slotData0.blendMode = BlendMode.values[skeletonBinary$SkeletonInput0.readInt(true)];
                arr_object1[v8] = slotData0;
            }
            int v11 = skeletonBinary$SkeletonInput0.readInt(true);
            Object[] arr_object2 = skeletonData0.ikConstraints.setSize(v11);
            for(int v12 = 0; v12 < v11; ++v12) {
                IkConstraintData ikConstraintData0 = new IkConstraintData(skeletonBinary$SkeletonInput0.readString());
                ikConstraintData0.order = skeletonBinary$SkeletonInput0.readInt(true);
                ikConstraintData0.skinRequired = skeletonBinary$SkeletonInput0.readBoolean();
                int v13 = skeletonBinary$SkeletonInput0.readInt(true);
                Object[] arr_object3 = ikConstraintData0.bones.setSize(v13);
                for(int v14 = 0; v14 < v13; ++v14) {
                    arr_object3[v14] = arr_object[skeletonBinary$SkeletonInput0.readInt(true)];
                }
                ikConstraintData0.target = (BoneData)arr_object[skeletonBinary$SkeletonInput0.readInt(true)];
                ikConstraintData0.mix = skeletonBinary$SkeletonInput0.readFloat();
                ikConstraintData0.softness = skeletonBinary$SkeletonInput0.readFloat() * f;
                ikConstraintData0.bendDirection = skeletonBinary$SkeletonInput0.readByte();
                ikConstraintData0.compress = skeletonBinary$SkeletonInput0.readBoolean();
                ikConstraintData0.stretch = skeletonBinary$SkeletonInput0.readBoolean();
                ikConstraintData0.uniform = skeletonBinary$SkeletonInput0.readBoolean();
                arr_object2[v12] = ikConstraintData0;
            }
            int v15 = skeletonBinary$SkeletonInput0.readInt(true);
            Object[] arr_object4 = skeletonData0.transformConstraints.setSize(v15);
            for(int v16 = 0; v16 < v15; ++v16) {
                TransformConstraintData transformConstraintData0 = new TransformConstraintData(skeletonBinary$SkeletonInput0.readString());
                transformConstraintData0.order = skeletonBinary$SkeletonInput0.readInt(true);
                transformConstraintData0.skinRequired = skeletonBinary$SkeletonInput0.readBoolean();
                int v17 = skeletonBinary$SkeletonInput0.readInt(true);
                Object[] arr_object5 = transformConstraintData0.bones.setSize(v17);
                for(int v18 = 0; v18 < v17; ++v18) {
                    arr_object5[v18] = arr_object[skeletonBinary$SkeletonInput0.readInt(true)];
                }
                transformConstraintData0.target = (BoneData)arr_object[skeletonBinary$SkeletonInput0.readInt(true)];
                transformConstraintData0.local = skeletonBinary$SkeletonInput0.readBoolean();
                transformConstraintData0.relative = skeletonBinary$SkeletonInput0.readBoolean();
                transformConstraintData0.offsetRotation = skeletonBinary$SkeletonInput0.readFloat();
                transformConstraintData0.offsetX = skeletonBinary$SkeletonInput0.readFloat() * f;
                transformConstraintData0.offsetY = skeletonBinary$SkeletonInput0.readFloat() * f;
                transformConstraintData0.offsetScaleX = skeletonBinary$SkeletonInput0.readFloat();
                transformConstraintData0.offsetScaleY = skeletonBinary$SkeletonInput0.readFloat();
                transformConstraintData0.offsetShearY = skeletonBinary$SkeletonInput0.readFloat();
                transformConstraintData0.mixRotate = skeletonBinary$SkeletonInput0.readFloat();
                transformConstraintData0.mixX = skeletonBinary$SkeletonInput0.readFloat();
                transformConstraintData0.mixY = skeletonBinary$SkeletonInput0.readFloat();
                transformConstraintData0.mixScaleX = skeletonBinary$SkeletonInput0.readFloat();
                transformConstraintData0.mixScaleY = skeletonBinary$SkeletonInput0.readFloat();
                transformConstraintData0.mixShearY = skeletonBinary$SkeletonInput0.readFloat();
                arr_object4[v16] = transformConstraintData0;
            }
            int v19 = skeletonBinary$SkeletonInput0.readInt(true);
            Object[] arr_object6 = skeletonData0.pathConstraints.setSize(v19);
            for(int v20 = 0; v20 < v19; ++v20) {
                PathConstraintData pathConstraintData0 = new PathConstraintData(skeletonBinary$SkeletonInput0.readString());
                pathConstraintData0.order = skeletonBinary$SkeletonInput0.readInt(true);
                pathConstraintData0.skinRequired = skeletonBinary$SkeletonInput0.readBoolean();
                int v21 = skeletonBinary$SkeletonInput0.readInt(true);
                Object[] arr_object7 = pathConstraintData0.bones.setSize(v21);
                for(int v22 = 0; v22 < v21; ++v22) {
                    arr_object7[v22] = arr_object[skeletonBinary$SkeletonInput0.readInt(true)];
                }
                pathConstraintData0.target = (SlotData)arr_object1[skeletonBinary$SkeletonInput0.readInt(true)];
                pathConstraintData0.positionMode = PositionMode.values[skeletonBinary$SkeletonInput0.readInt(true)];
                pathConstraintData0.spacingMode = SpacingMode.values[skeletonBinary$SkeletonInput0.readInt(true)];
                pathConstraintData0.rotateMode = RotateMode.values[skeletonBinary$SkeletonInput0.readInt(true)];
                pathConstraintData0.offsetRotation = skeletonBinary$SkeletonInput0.readFloat();
                pathConstraintData0.position = skeletonBinary$SkeletonInput0.readFloat();
                if(pathConstraintData0.positionMode == PositionMode.fixed) {
                    pathConstraintData0.position *= f;
                }
                pathConstraintData0.spacing = skeletonBinary$SkeletonInput0.readFloat();
                if(pathConstraintData0.spacingMode == SpacingMode.length || pathConstraintData0.spacingMode == SpacingMode.fixed) {
                    pathConstraintData0.spacing *= f;
                }
                pathConstraintData0.mixRotate = skeletonBinary$SkeletonInput0.readFloat();
                pathConstraintData0.mixX = skeletonBinary$SkeletonInput0.readFloat();
                pathConstraintData0.mixY = skeletonBinary$SkeletonInput0.readFloat();
                arr_object6[v20] = pathConstraintData0;
            }
            Skin skin0 = this.readSkin(skeletonBinary$SkeletonInput0, skeletonData0, true, z);
            if(skin0 != null) {
                skeletonData0.defaultSkin = skin0;
                skeletonData0.skins.add(skin0);
            }
            int v23 = skeletonData0.skins.size;
            int v24 = skeletonBinary$SkeletonInput0.readInt(true) + v23;
            Object[] arr_object8 = skeletonData0.skins.setSize(v24);
            while(v23 < v24) {
                arr_object8[v23] = this.readSkin(skeletonBinary$SkeletonInput0, skeletonData0, false, z);
                ++v23;
            }
            int v25 = this.linkedMeshes.size;
            Object[] arr_object9 = this.linkedMeshes.items;
            for(int v26 = 0; v26 < v25; ++v26) {
                LinkedMesh skeletonJson$LinkedMesh0 = (LinkedMesh)arr_object9[v26];
                Skin skin1 = skeletonJson$LinkedMesh0.skin == null ? skeletonData0.getDefaultSkin() : skeletonData0.findSkin(skeletonJson$LinkedMesh0.skin);
                if(skin1 == null) {
                    throw new SerializationException("Skin not found: " + skeletonJson$LinkedMesh0.skin);
                }
                Attachment attachment0 = skin1.getAttachment(skeletonJson$LinkedMesh0.slotIndex, skeletonJson$LinkedMesh0.parent);
                if(attachment0 == null) {
                    throw new SerializationException("Parent mesh not found: " + skeletonJson$LinkedMesh0.parent);
                }
                MeshAttachment meshAttachment0 = skeletonJson$LinkedMesh0.mesh;
                VertexAttachment vertexAttachment0 = skeletonJson$LinkedMesh0.inheritTimelines ? ((VertexAttachment)attachment0) : skeletonJson$LinkedMesh0.mesh;
                meshAttachment0.setTimelineAttachment(vertexAttachment0);
                skeletonJson$LinkedMesh0.mesh.setParentMesh(((MeshAttachment)attachment0));
                if(skeletonJson$LinkedMesh0.mesh.getSequence() == null) {
                    skeletonJson$LinkedMesh0.mesh.updateRegion();
                }
            }
            this.linkedMeshes.clear();
            int v27 = skeletonBinary$SkeletonInput0.readInt(true);
            Object[] arr_object10 = skeletonData0.events.setSize(v27);
            for(int v28 = 0; v28 < v27; ++v28) {
                EventData eventData0 = new EventData(skeletonBinary$SkeletonInput0.readStringRef());
                eventData0.intValue = skeletonBinary$SkeletonInput0.readInt(false);
                eventData0.floatValue = skeletonBinary$SkeletonInput0.readFloat();
                eventData0.stringValue = skeletonBinary$SkeletonInput0.readString();
                eventData0.audioPath = skeletonBinary$SkeletonInput0.readString();
                if(eventData0.audioPath != null) {
                    eventData0.volume = skeletonBinary$SkeletonInput0.readFloat();
                    eventData0.balance = skeletonBinary$SkeletonInput0.readFloat();
                }
                arr_object10[v28] = eventData0;
            }
            int v30 = skeletonBinary$SkeletonInput0.readInt(true);
            Object[] arr_object11 = skeletonData0.animations.setSize(v30);
            for(int v29 = 0; v29 < v30; ++v29) {
                arr_object11[v29] = this.readAnimation(skeletonBinary$SkeletonInput0, skeletonBinary$SkeletonInput0.readString(), skeletonData0);
            }
        }
        catch(IOException iOException0) {
            throw new SerializationException("Error reading skeleton file.", iOException0);
        }
        finally {
            skeletonBinary$SkeletonInput0.close();
        }
        try {
        }
        catch(IOException unused_ex) {
        }
        return skeletonData0;
    }

    @Null
    private Skin readSkin(SkeletonInput skeletonBinary$SkeletonInput0, SkeletonData skeletonData0, boolean z, boolean z1) throws IOException {
        Skin skin0;
        int v1;
        if(z) {
            int v = skeletonBinary$SkeletonInput0.readInt(true);
            if(v == 0) {
                return null;
            }
            v1 = v;
            skin0 = new Skin("default");
        }
        else {
            Skin skin1 = new Skin(skeletonBinary$SkeletonInput0.readStringRef());
            int v2 = skeletonBinary$SkeletonInput0.readInt(true);
            Object[] arr_object = skin1.bones.setSize(v2);
            Object[] arr_object1 = skeletonData0.bones.items;
            int v3 = skin1.bones.size;
            for(int v4 = 0; v4 < v3; ++v4) {
                arr_object[v4] = arr_object1[skeletonBinary$SkeletonInput0.readInt(true)];
            }
            Object[] arr_object2 = skeletonData0.ikConstraints.items;
            int v5 = skeletonBinary$SkeletonInput0.readInt(true);
            for(int v6 = 0; v6 < v5; ++v6) {
                int v7 = skeletonBinary$SkeletonInput0.readInt(true);
                skin1.constraints.add(((ConstraintData)arr_object2[v7]));
            }
            Object[] arr_object3 = skeletonData0.transformConstraints.items;
            int v8 = skeletonBinary$SkeletonInput0.readInt(true);
            for(int v9 = 0; v9 < v8; ++v9) {
                int v10 = skeletonBinary$SkeletonInput0.readInt(true);
                skin1.constraints.add(((ConstraintData)arr_object3[v10]));
            }
            Object[] arr_object4 = skeletonData0.pathConstraints.items;
            int v11 = skeletonBinary$SkeletonInput0.readInt(true);
            for(int v12 = 0; v12 < v11; ++v12) {
                int v13 = skeletonBinary$SkeletonInput0.readInt(true);
                skin1.constraints.add(((ConstraintData)arr_object4[v13]));
            }
            skin1.constraints.shrink();
            skin0 = skin1;
            v1 = skeletonBinary$SkeletonInput0.readInt(true);
        }
        for(int v14 = 0; v14 < v1; ++v14) {
            int v15 = skeletonBinary$SkeletonInput0.readInt(true);
            int v16 = skeletonBinary$SkeletonInput0.readInt(true);
            for(int v17 = 0; v17 < v16; ++v17) {
                String s = skeletonBinary$SkeletonInput0.readStringRef();
                String s1 = s;
                Attachment attachment0 = this.readAttachment(skeletonBinary$SkeletonInput0, skeletonData0, skin0, v15, s, z1);
                if(attachment0 != null) {
                    skin0.setAttachment(v15, s1, attachment0);
                }
            }
        }
        return skin0;
    }

    private Timeline readTimeline(SkeletonInput skeletonBinary$SkeletonInput0, CurveTimeline1 animation$CurveTimeline10, float f) throws IOException {
        float f1 = skeletonBinary$SkeletonInput0.readFloat();
        float f2 = skeletonBinary$SkeletonInput0.readFloat();
        int v = animation$CurveTimeline10.getFrameCount();
        float f3 = f1;
        float f4 = f2 * f;
        int v1 = 0;
        int v2 = 0;
        while(true) {
            animation$CurveTimeline10.setFrame(v2, f3, f4);
            if(v2 == v - 1) {
                break;
            }
            float f5 = skeletonBinary$SkeletonInput0.readFloat();
            float f6 = skeletonBinary$SkeletonInput0.readFloat() * f;
            switch(skeletonBinary$SkeletonInput0.readByte()) {
                case 1: {
                    animation$CurveTimeline10.setStepped(v2);
                    break;
                }
                case 2: {
                    this.setBezier(skeletonBinary$SkeletonInput0, animation$CurveTimeline10, v1, v2, 0, f3, f5, f4, f6, f);
                    ++v1;
                }
            }
            ++v2;
            f3 = f5;
            f4 = f6;
        }
        return animation$CurveTimeline10;
    }

    private Timeline readTimeline(SkeletonInput skeletonBinary$SkeletonInput0, CurveTimeline2 animation$CurveTimeline20, float f) throws IOException {
        float f1 = skeletonBinary$SkeletonInput0.readFloat();
        float f2 = skeletonBinary$SkeletonInput0.readFloat();
        float f3 = skeletonBinary$SkeletonInput0.readFloat();
        int v = animation$CurveTimeline20.getFrameCount();
        int v1 = 0;
        float f4 = f1;
        float f5 = f2 * f;
        float f6 = f3 * f;
        int v2 = 0;
        while(true) {
            animation$CurveTimeline20.setFrame(v2, f4, f5, f6);
            if(v2 == v - 1) {
                break;
            }
            float f7 = skeletonBinary$SkeletonInput0.readFloat();
            float f8 = skeletonBinary$SkeletonInput0.readFloat() * f;
            float f9 = skeletonBinary$SkeletonInput0.readFloat() * f;
            switch(skeletonBinary$SkeletonInput0.readByte()) {
                case 1: {
                    animation$CurveTimeline20.setStepped(v2);
                    break;
                }
                case 2: {
                    this.setBezier(skeletonBinary$SkeletonInput0, animation$CurveTimeline20, v1, v2, 0, f4, f7, f5, f8, f);
                    this.setBezier(skeletonBinary$SkeletonInput0, animation$CurveTimeline20, v1 + 1, v2, 1, f4, f7, f6, f9, f);
                    v1 += 2;
                }
            }
            ++v2;
            f4 = f7;
            f5 = f8;
            f6 = f9;
        }
        return animation$CurveTimeline20;
    }

    private Vertices readVertices(SkeletonInput skeletonBinary$SkeletonInput0, int v) throws IOException {
        float f = this.scale;
        Vertices skeletonBinary$Vertices0 = new Vertices();
        if(!skeletonBinary$SkeletonInput0.readBoolean()) {
            skeletonBinary$Vertices0.vertices = this.readFloatArray(skeletonBinary$SkeletonInput0, v << 1, f);
            return skeletonBinary$Vertices0;
        }
        int v1 = (v << 1) * 3;
        FloatArray floatArray0 = new FloatArray(v1 * 3);
        IntArray intArray0 = new IntArray(v1);
        for(int v2 = 0; v2 < v; ++v2) {
            int v3 = skeletonBinary$SkeletonInput0.readInt(true);
            intArray0.add(v3);
            for(int v4 = 0; v4 < v3; ++v4) {
                intArray0.add(skeletonBinary$SkeletonInput0.readInt(true));
                floatArray0.add(skeletonBinary$SkeletonInput0.readFloat() * f);
                floatArray0.add(skeletonBinary$SkeletonInput0.readFloat() * f);
                floatArray0.add(skeletonBinary$SkeletonInput0.readFloat());
            }
        }
        skeletonBinary$Vertices0.vertices = floatArray0.toArray();
        skeletonBinary$Vertices0.bones = intArray0.toArray();
        return skeletonBinary$Vertices0;
    }

    void setBezier(SkeletonInput skeletonBinary$SkeletonInput0, CurveTimeline animation$CurveTimeline0, int v, int v1, int v2, float f, float f1, float f2, float f3, float f4) throws IOException {
        animation$CurveTimeline0.setBezier(v, v1, v2, f, f2, skeletonBinary$SkeletonInput0.readFloat(), skeletonBinary$SkeletonInput0.readFloat() * f4, skeletonBinary$SkeletonInput0.readFloat(), skeletonBinary$SkeletonInput0.readFloat() * f4, f1, f3);
    }
}

