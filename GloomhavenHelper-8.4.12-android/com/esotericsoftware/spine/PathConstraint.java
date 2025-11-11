package com.esotericsoftware.spine;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.esotericsoftware.spine.attachments.Attachment;
import com.esotericsoftware.spine.attachments.PathAttachment;
import java.util.Arrays;

public class PathConstraint implements Updatable {
    private static final int AFTER = -3;
    private static final int BEFORE = -2;
    private static final int NONE = -1;
    boolean active;
    final Array bones;
    private final FloatArray curves;
    final PathConstraintData data;
    private static final float epsilon = 0.00001f;
    private final FloatArray lengths;
    float mixRotate;
    float mixX;
    float mixY;
    float position;
    private final FloatArray positions;
    private final float[] segments;
    private final FloatArray spaces;
    float spacing;
    Slot target;
    private final FloatArray world;

    public PathConstraint(PathConstraint pathConstraint0, Skeleton skeleton0) {
        this.spaces = new FloatArray();
        this.positions = new FloatArray();
        this.world = new FloatArray();
        this.curves = new FloatArray();
        this.lengths = new FloatArray();
        this.segments = new float[10];
        if(pathConstraint0 == null) {
            throw new IllegalArgumentException("constraint cannot be null.");
        }
        if(skeleton0 == null) {
            throw new IllegalArgumentException("skeleton cannot be null.");
        }
        this.data = pathConstraint0.data;
        this.bones = new Array(pathConstraint0.bones.size);
        for(Object object0: pathConstraint0.bones) {
            Object object1 = skeleton0.bones.get(((Bone)object0).data.index);
            this.bones.add(object1);
        }
        this.target = (Slot)skeleton0.slots.get(pathConstraint0.target.data.index);
        this.position = pathConstraint0.position;
        this.spacing = pathConstraint0.spacing;
        this.mixRotate = pathConstraint0.mixRotate;
        this.mixX = pathConstraint0.mixX;
        this.mixY = pathConstraint0.mixY;
    }

    public PathConstraint(PathConstraintData pathConstraintData0, Skeleton skeleton0) {
        this.spaces = new FloatArray();
        this.positions = new FloatArray();
        this.world = new FloatArray();
        this.curves = new FloatArray();
        this.lengths = new FloatArray();
        this.segments = new float[10];
        if(pathConstraintData0 == null) {
            throw new IllegalArgumentException("data cannot be null.");
        }
        if(skeleton0 == null) {
            throw new IllegalArgumentException("skeleton cannot be null.");
        }
        this.data = pathConstraintData0;
        this.bones = new Array(pathConstraintData0.bones.size);
        for(Object object0: pathConstraintData0.bones) {
            Object object1 = skeleton0.bones.get(((BoneData)object0).index);
            this.bones.add(object1);
        }
        this.target = (Slot)skeleton0.slots.get(pathConstraintData0.target.index);
        this.position = pathConstraintData0.position;
        this.spacing = pathConstraintData0.spacing;
        this.mixRotate = pathConstraintData0.mixRotate;
        this.mixX = pathConstraintData0.mixX;
        this.mixY = pathConstraintData0.mixY;
    }

    private void addAfterPosition(float f, float[] arr_f, int v, float[] arr_f1, int v1) {
        float f1 = arr_f[v + 2];
        float f2 = arr_f[v + 3];
        float f3 = (float)Math.atan2(f2 - arr_f[v + 1], f1 - arr_f[v]);
        arr_f1[v1] = f1 + ((float)Math.cos(f3)) * f;
        arr_f1[v1 + 1] = f2 + f * ((float)Math.sin(f3));
        arr_f1[v1 + 2] = f3;
    }

    private void addBeforePosition(float f, float[] arr_f, int v, float[] arr_f1, int v1) {
        float f1 = arr_f[v];
        float f2 = arr_f[v + 1];
        float f3 = (float)Math.atan2(arr_f[v + 3] - f2, arr_f[v + 2] - f1);
        arr_f1[v1] = f1 + ((float)Math.cos(f3)) * f;
        arr_f1[v1 + 1] = f2 + f * ((float)Math.sin(f3));
        arr_f1[v1 + 2] = f3;
    }

    private void addCurvePosition(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float[] arr_f, int v, boolean z) {
        if(f >= 0.00001f && !Float.isNaN(f)) {
            float f9 = f * f;
            float f10 = f9 * f;
            float f11 = (1.0f - f) * (1.0f - f);
            float f12 = f11 * (1.0f - f);
            float f13 = (1.0f - f) * f;
            float f14 = (1.0f - f) * (3.0f * f13);
            float f15 = 3.0f * f13 * f;
            float f16 = f1 * f12 + f3 * f14 + f5 * f15 + f7 * f10;
            float f17 = f12 * f2 + f14 * f4 + f6 * f15 + f10 * f8;
            arr_f[v] = f16;
            arr_f[v + 1] = f17;
            if(z) {
                if(f < 0.001f) {
                    arr_f[v + 2] = (float)Math.atan2(f4 - f2, f3 - f1);
                    return;
                }
                arr_f[v + 2] = (float)Math.atan2(f17 - (f2 * f11 + f4 * f13 * 2.0f + f6 * f9), f16 - (f1 * f11 + f3 * f13 * 2.0f + f9 * f5));
            }
            return;
        }
        arr_f[v] = f1;
        arr_f[v + 1] = f2;
        arr_f[v + 2] = (float)Math.atan2(f4 - f2, f3 - f1);
    }

    float[] computeWorldPositions(PathAttachment pathAttachment0, int v, boolean z) {
        float f82;
        float f81;
        float f79;
        int v22;
        float f78;
        float f71;
        float f68;
        float f50;
        float f49;
        int v20;
        float[] arr_f9;
        int v19;
        float f48;
        float f36;
        float[] arr_f5;
        int v12;
        int v11;
        float f7;
        float f6;
        int v10;
        int v9;
        float f5;
        float f2;
        Slot slot0 = this.target;
        float f = this.position;
        float[] arr_f = this.spaces.items;
        int v1 = 2;
        float[] arr_f1 = this.positions.setSize(v * 3 + 2);
        boolean z1 = pathAttachment0.getClosed();
        int v2 = pathAttachment0.getWorldVerticesLength();
        int v3 = v2 / 6;
        if(!pathAttachment0.getConstantSpeed()) {
            float[] arr_f2 = pathAttachment0.getLengths();
            int v4 = v3 - (z1 ? 1 : 2);
            float f1 = arr_f2[v4];
            if(this.data.positionMode == PositionMode.percent) {
                f *= f1;
            }
            switch(com.esotericsoftware.spine.PathConstraint.1.$SwitchMap$com$esotericsoftware$spine$PathConstraintData$SpacingMode[this.data.spacingMode.ordinal()]) {
                case 1: {
                    f2 = f1;
                    break;
                }
                case 2: {
                    f2 = f1 / ((float)v);
                    break;
                }
                default: {
                    f2 = 1.0f;
                }
            }
            float[] arr_f3 = this.world.setSize(8);
            int v5 = 0;
            int v6 = -1;
            int v7 = 0;
            int v8 = 0;
            while(v7 < v) {
                float f3 = arr_f[v7] * f2;
                float f4 = f + f3;
                if(z1) {
                    f5 = f4 % f1;
                    if(f5 < 0.0f) {
                        f5 += f1;
                    }
                    v5 = 0;
                }
                else if(f4 < 0.0f) {
                    if(v6 != -2) {
                        v6 = -2;
                        pathAttachment0.computeWorldVertices(slot0, 2, 4, arr_f3, 0, 2);
                    }
                    this.addBeforePosition(f4, arr_f3, 0, arr_f1, v8);
                    v9 = v4;
                    v10 = v7;
                    goto label_73;
                }
                else if(f4 > f1) {
                    if(v6 != -3) {
                        v6 = -3;
                        pathAttachment0.computeWorldVertices(slot0, v2 - 6, 4, arr_f3, 0, 2);
                    }
                    this.addAfterPosition(f4 - f1, arr_f3, 0, arr_f1, v8);
                    v9 = v4;
                    v10 = v7;
                    goto label_73;
                }
                else {
                    f5 = f4;
                }
                while(true) {
                    f6 = arr_f2[v5];
                    if(f5 <= f6) {
                        break;
                    }
                    ++v5;
                }
                if(v5 == 0) {
                    f7 = f5 / f6;
                }
                else {
                    float f8 = arr_f2[v5 - 1];
                    f7 = (f5 - f8) / (f6 - f8);
                }
                if(v5 == v6) {
                    v11 = v6;
                }
                else {
                    if(!z1 || v5 != v4) {
                        pathAttachment0.computeWorldVertices(slot0, v5 * 6 + 2, 8, arr_f3, 0, 2);
                    }
                    else {
                        pathAttachment0.computeWorldVertices(slot0, v2 - 4, 4, arr_f3, 0, 2);
                        pathAttachment0.computeWorldVertices(slot0, 0, 4, arr_f3, 4, 2);
                    }
                    v11 = v5;
                }
                v9 = v4;
                v10 = v7;
                this.addCurvePosition(f7, arr_f3[0], arr_f3[1], arr_f3[2], arr_f3[3], arr_f3[4], arr_f3[5], arr_f3[6], arr_f3[7], arr_f1, v8, z || v7 > 0 && f3 < 0.00001f);
                v6 = v11;
            label_73:
                v7 = v10 + 1;
                v8 += 3;
                f = f4;
                v4 = v9;
            }
            return arr_f1;
        }
        if(z1) {
            float[] arr_f4 = this.world.setSize(v2 + 2);
            pathAttachment0.computeWorldVertices(slot0, 2, v2 - 2, arr_f4, 0, 2);
            pathAttachment0.computeWorldVertices(slot0, 0, 2, arr_f4, v2 - 2, 2);
            arr_f4[v2] = arr_f4[0];
            arr_f4[v2 + 1] = arr_f4[1];
            v12 = v2 + 2;
            arr_f5 = arr_f4;
        }
        else {
            --v3;
            float[] arr_f6 = this.world.setSize(v2 - 4);
            pathAttachment0.computeWorldVertices(slot0, 2, v2 - 4, arr_f6, 0, 2);
            v12 = v2 - 4;
            arr_f5 = arr_f6;
        }
        float[] arr_f7 = this.curves.setSize(v3);
        float f9 = arr_f5[0];
        float f10 = arr_f5[1];
        float f11 = 0.0f;
        float f12 = 0.0f;
        float f13 = 0.0f;
        float f14 = 0.0f;
        float f15 = 0.0f;
        float f16 = 0.0f;
        float f17 = 0.0f;
        float f18 = f9;
        int v13 = 0;
        while(v13 < v3) {
            f11 = arr_f5[v1];
            f12 = arr_f5[v1 + 1];
            f13 = arr_f5[v1 + 2];
            f14 = arr_f5[v1 + 3];
            f15 = arr_f5[v1 + 4];
            float f19 = arr_f5[v1 + 5];
            float f20 = (f18 - f11 * 2.0f + f13) * 0.1875f;
            float f21 = (f10 - f12 * 2.0f + f14) * 0.1875f;
            float f22 = ((f11 - f13) * 3.0f - f18 + f15) * 0.09375f;
            float f23 = ((f12 - f14) * 3.0f - f10 + f19) * 0.09375f;
            float f24 = f20 * 2.0f + f22;
            float f25 = 2.0f * f21 + f23;
            float f26 = (f11 - f18) * 0.75f + f20 + f22 * 0.166667f;
            float f27 = (f12 - f10) * 0.75f + f21 + 0.166667f * f23;
            float f28 = f26 + f24;
            float f29 = f27 + f25;
            float f30 = f24 + f22;
            float f31 = f25 + f23;
            float f32 = f28 + f30;
            float f33 = f29 + f31;
            float f34 = f32 + (f30 + f22);
            float f35 = f33 + (f31 + f23);
            f17 = f17 + ((float)Math.sqrt(f26 * f26 + f27 * f27)) + ((float)Math.sqrt(f28 * f28 + f29 * f29)) + ((float)Math.sqrt(f32 * f32 + f33 * f33)) + ((float)Math.sqrt(f34 * f34 + f35 * f35));
            arr_f7[v13] = f17;
            ++v13;
            v1 += 6;
            f10 = f19;
            f16 = f10;
            f18 = f15;
        }
        if(this.data.positionMode == PositionMode.percent) {
            f *= f17;
        }
        switch(com.esotericsoftware.spine.PathConstraint.1.$SwitchMap$com$esotericsoftware$spine$PathConstraintData$SpacingMode[this.data.spacingMode.ordinal()]) {
            case 1: {
                f36 = f17;
                break;
            }
            case 2: {
                f36 = f17 / ((float)v);
                break;
            }
            default: {
                f36 = 1.0f;
            }
        }
        float[] arr_f8 = this.segments;
        float f37 = f18;
        float f38 = f10;
        float f39 = f11;
        float f40 = f12;
        float f41 = f13;
        float f42 = f14;
        float f43 = f15;
        float f44 = f16;
        int v14 = 0;
        int v15 = -1;
        float f45 = 0.0f;
        int v16 = 0;
        int v17 = 0;
        int v18 = 0;
        while(v17 < v) {
            float f46 = arr_f[v17] * f36;
            float f47 = f + f46;
            if(z1) {
                f48 = f47 % f17;
                if(f48 < 0.0f) {
                    f48 += f17;
                }
                v19 = 0;
            }
            else if(f47 < 0.0f) {
                this.addBeforePosition(f47, arr_f5, 0, arr_f1, v18);
                arr_f9 = arr_f8;
                v20 = v17;
                goto label_263;
            }
            else if(f47 > f17) {
                this.addAfterPosition(f47 - f17, arr_f5, v12 - 4, arr_f1, v18);
                arr_f9 = arr_f8;
                v20 = v17;
                goto label_263;
            }
            else {
                v19 = v14;
                f48 = f47;
            }
            while(true) {
                f49 = arr_f7[v19];
                if(f48 <= f49) {
                    break;
                }
                ++v19;
            }
            if(v19 == 0) {
                f50 = f48 / f49;
            }
            else {
                float f51 = arr_f7[v19 - 1];
                f50 = (f48 - f51) / (f49 - f51);
            }
            if(v19 == v15) {
                v22 = v15;
                f79 = f44;
                f71 = f38;
                f68 = f37;
                f78 = f45;
            }
            else {
                float f52 = arr_f5[v19 * 6];
                float f53 = arr_f5[v19 * 6 + 1];
                float f54 = arr_f5[v19 * 6 + 2];
                float f55 = arr_f5[v19 * 6 + 3];
                float f56 = arr_f5[v19 * 6 + 4];
                float f57 = arr_f5[v19 * 6 + 5];
                float f58 = arr_f5[v19 * 6 + 6];
                float f59 = arr_f5[v19 * 6 + 7];
                float f60 = (f52 - f54 * 2.0f + f56) * 0.03f;
                float f61 = (f53 - f55 * 2.0f + f57) * 0.03f;
                float f62 = ((f54 - f56) * 3.0f - f52 + f58) * 0.006f;
                float f63 = ((f55 - f57) * 3.0f - f53 + f59) * 0.006f;
                float f64 = f60 * 2.0f + f62;
                float f65 = f61 * 2.0f + f63;
                float f66 = (f54 - f52) * 0.3f + f60 + f62 * 0.166667f;
                float f67 = (f55 - f53) * 0.3f + f61 + f63 * 0.166667f;
                f68 = f52;
                float f69 = (float)Math.sqrt(f66 * f66 + f67 * f67);
                arr_f8[0] = f69;
                float f70 = f69;
                f71 = f53;
                for(int v21 = 1; v21 < 8; ++v21) {
                    f66 += f64;
                    f67 += f65;
                    f64 += f62;
                    f65 += f63;
                    f70 += (float)Math.sqrt(f66 * f66 + f67 * f67);
                    arr_f8[v21] = f70;
                }
                float f72 = f66 + f64;
                float f73 = f67 + f65;
                float f74 = f70 + ((float)Math.sqrt(f72 * f72 + f73 * f73));
                arr_f8[8] = f74;
                float f75 = f72 + (f64 + f62);
                float f76 = f73 + (f65 + f63);
                float f77 = ((float)Math.sqrt(f75 * f75 + f76 * f76)) + f74;
                arr_f8[9] = f77;
                f78 = f77;
                f40 = f55;
                v22 = v19;
                f41 = f56;
                f42 = f57;
                f43 = f58;
                f39 = f54;
                v16 = 0;
                f79 = f59;
            }
            float f80 = f50 * f78;
            while(true) {
                f81 = arr_f8[v16];
                if(f80 <= f81) {
                    break;
                }
                ++v16;
            }
            if(v16 == 0) {
                f82 = f80 / f81;
            }
            else {
                float f83 = arr_f8[v16 - 1];
                f82 = (f80 - f83) / (f81 - f83) + ((float)v16);
            }
            arr_f9 = arr_f8;
            v20 = v17;
            this.addCurvePosition(f82 * 0.1f, f68, f71, f39, f40, f41, f42, f43, f79, arr_f1, v18, z || v17 > 0 && f46 < 0.00001f);
            f45 = f78;
            f37 = f68;
            f38 = f71;
            f44 = f79;
            v15 = v22;
            v14 = v19;
        label_263:
            v17 = v20 + 1;
            v18 += 3;
            arr_f8 = arr_f9;
            f = f47;
        }
        return arr_f1;
    }

    public Array getBones() {
        return this.bones;
    }

    public PathConstraintData getData() {
        return this.data;
    }

    public float getMixRotate() {
        return this.mixRotate;
    }

    public float getMixX() {
        return this.mixX;
    }

    public float getMixY() {
        return this.mixY;
    }

    public float getPosition() {
        return this.position;
    }

    public float getSpacing() {
        return this.spacing;
    }

    public Slot getTarget() {
        return this.target;
    }

    @Override  // com.esotericsoftware.spine.Updatable
    public boolean isActive() {
        return this.active;
    }

    public void setMixRotate(float f) {
        this.mixRotate = f;
    }

    public void setMixX(float f) {
        this.mixX = f;
    }

    public void setMixY(float f) {
        this.mixY = f;
    }

    public void setPosition(float f) {
        this.position = f;
    }

    public void setSpacing(float f) {
        this.spacing = f;
    }

    public void setTarget(Slot slot0) {
        if(slot0 == null) {
            throw new IllegalArgumentException("target cannot be null.");
        }
        this.target = slot0;
    }

    public void setToSetupPose() {
        this.position = this.data.position;
        this.spacing = this.data.spacing;
        this.mixRotate = this.data.mixRotate;
        this.mixX = this.data.mixX;
        this.mixY = this.data.mixY;
    }

    @Override
    public String toString() {
        return this.data.name;
    }

    @Override  // com.esotericsoftware.spine.Updatable
    public void update() {
        float f41;
        int v16;
        float[] arr_f5;
        float[] arr_f4;
        float f37;
        float[] arr_f3;
        boolean z3;
        int v15;
        int v14;
        Object[] arr_object1;
        float f31;
        int v13;
        int v5;
        float f12;
        int v4;
        float f8;
        float f6;
        float f4;
        float f19;
        float f18;
        int v8;
        Attachment attachment0 = this.target.attachment;
        if(!(attachment0 instanceof PathAttachment)) {
            return;
        }
        float f = this.mixRotate;
        float f1 = this.mixX;
        float f2 = this.mixY;
        if(f == 0.0f && f1 == 0.0f && f2 == 0.0f) {
            return;
        }
        PathConstraintData pathConstraintData0 = this.data;
        boolean z = pathConstraintData0.rotateMode == RotateMode.tangent;
        int v = pathConstraintData0.rotateMode == RotateMode.chainScale ? 1 : 0;
        int v1 = this.bones.size;
        int v2 = z ? v1 : v1 + 1;
        Object[] arr_object = this.bones.items;
        float[] arr_f = this.spaces.setSize(v2);
        float[] arr_f1 = v == 0 ? null : this.lengths.setSize(v1);
        float f3 = this.spacing;
        switch(com.esotericsoftware.spine.PathConstraint.1.$SwitchMap$com$esotericsoftware$spine$PathConstraintData$SpacingMode[pathConstraintData0.spacingMode.ordinal()]) {
            case 1: {
                f4 = f;
                f6 = f2;
                if(v != 0) {
                    int v6 = v2 - 1;
                    int v7 = 0;
                    while(v7 < v6) {
                        Bone bone1 = (Bone)arr_object[v7];
                        float f13 = bone1.data.length;
                        if(f13 < 0.00001f) {
                            arr_f1[v7] = 0.0f;
                            v8 = v6;
                        }
                        else {
                            v8 = v6;
                            float f14 = bone1.a * f13;
                            float f15 = f13 * bone1.c;
                            arr_f1[v7] = (float)Math.sqrt(f14 * f14 + f15 * f15);
                        }
                        ++v7;
                        v6 = v8;
                    }
                }
                Arrays.fill(arr_f, 1, v2, f3);
                f12 = f1;
                v5 = v1;
                break;
            }
            case 2: {
                int v9 = 0;
                float f16 = 0.0f;
                while(v9 < v2 - 1) {
                    Bone bone2 = (Bone)arr_object[v9];
                    float f17 = bone2.data.length;
                    if(f17 < 0.00001f) {
                        if(v != 0) {
                            arr_f1[v9] = 0.0f;
                        }
                        ++v9;
                        arr_f[v9] = f3;
                        f18 = f1;
                        f19 = f2;
                    }
                    else {
                        f19 = f2;
                        float f20 = bone2.a * f17;
                        float f21 = f17 * bone2.c;
                        f18 = f1;
                        float f22 = (float)Math.sqrt(f20 * f20 + f21 * f21);
                        if(v != 0) {
                            arr_f1[v9] = f22;
                        }
                        ++v9;
                        arr_f[v9] = f22;
                        f16 += f22;
                    }
                    f1 = f18;
                    f2 = f19;
                }
                f4 = f;
                f6 = f2;
                if(f16 > 0.0f) {
                    for(int v10 = 1; v10 < v2; ++v10) {
                        arr_f[v10] *= ((float)v2) / f16 * f3;
                    }
                }
                f12 = f1;
                v5 = v1;
                break;
            }
            default: {
                f4 = f;
                float f5 = f1;
                f6 = f2;
                boolean z1 = pathConstraintData0.spacingMode == SpacingMode.length;
                int v3 = 0;
                while(v3 < v2 - 1) {
                    Bone bone0 = (Bone)arr_object[v3];
                    float f7 = bone0.data.length;
                    if(f7 < 0.00001f) {
                        if(v != 0) {
                            arr_f1[v3] = 0.0f;
                        }
                        ++v3;
                        arr_f[v3] = f3;
                        f8 = f5;
                        v4 = v1;
                    }
                    else {
                        f8 = f5;
                        float f9 = bone0.a * f7;
                        float f10 = bone0.c * f7;
                        v4 = v1;
                        float f11 = (float)Math.sqrt(f9 * f9 + f10 * f10);
                        if(v != 0) {
                            arr_f1[v3] = f11;
                        }
                        ++v3;
                        arr_f[v3] = (z1 ? f7 + f3 : f3) * f11 / f7;
                    }
                    v1 = v4;
                    f5 = f8;
                }
                f12 = f5;
                v5 = v1;
            }
        }
        float[] arr_f2 = this.computeWorldPositions(((PathAttachment)attachment0), v2, z);
        int v11 = 0;
        float f23 = arr_f2[0];
        boolean z2 = true;
        float f24 = arr_f2[1];
        float f25 = pathConstraintData0.offsetRotation;
        if(f25 != 0.0f) {
            f25 *= (this.target.bone.a * this.target.bone.d - this.target.bone.b * this.target.bone.c > 0.0f ? 0.017453f : -0.017453f);
            z2 = false;
        }
        else if(pathConstraintData0.rotateMode != RotateMode.chain) {
            z2 = false;
        }
        int v12 = 3;
        while(v11 < v5) {
            Bone bone3 = (Bone)arr_object[v11];
            bone3.worldX += (f23 - bone3.worldX) * f12;
            bone3.worldY += (f24 - bone3.worldY) * f6;
            float f26 = arr_f2[v12];
            float f27 = arr_f2[v12 + 1];
            float f28 = f26 - f23;
            float f29 = f27 - f24;
            if(v == 0) {
                v13 = v5;
                f31 = f27;
                arr_object1 = arr_object;
            }
            else {
                float f30 = arr_f1[v11];
                if(f30 >= 0.00001f) {
                    v13 = v5;
                    f31 = f27;
                    arr_object1 = arr_object;
                    float f32 = (((float)Math.sqrt(f28 * f28 + f29 * f29)) / f30 - 1.0f) * f4 + 1.0f;
                    bone3.a *= f32;
                    bone3.c *= f32;
                }
                else {
                    v13 = v5;
                    f31 = f27;
                    arr_object1 = arr_object;
                }
            }
            if(f4 > 0.0f) {
                float f33 = bone3.a;
                float f34 = bone3.b;
                float f35 = bone3.c;
                v14 = v;
                float f36 = bone3.d;
                if(z) {
                    v15 = v12;
                    z3 = true;
                    arr_f3 = arr_f1;
                    f37 = arr_f2[v12 - 1];
                    arr_f4 = arr_f;
                }
                else if(arr_f[v11 + 1] < 0.00001f) {
                    v15 = v12;
                    z3 = false;
                    arr_f3 = arr_f1;
                    f37 = arr_f2[v12 + 2];
                    arr_f4 = arr_f;
                }
                else {
                    arr_f4 = arr_f;
                    arr_f3 = arr_f1;
                    v15 = v12;
                    z3 = false;
                    f37 = (float)Math.atan2(f29, f28);
                }
                arr_f5 = arr_f2;
                v16 = v11;
                float f38 = f37 - ((float)Math.atan2(f35, f33));
                if(z2) {
                    float f39 = (float)Math.cos(f38);
                    float f40 = (float)Math.sin(f38);
                    f26 += ((f39 * f33 - f40 * f35) * bone3.data.length - f28) * f4;
                    f41 = f31 + (bone3.data.length * (f40 * f33 + f39 * f35) - f29) * f4;
                }
                else {
                    f38 += f25;
                    f41 = f31;
                }
                if(f38 > 3.141593f) {
                    f38 -= 6.283185f;
                }
                else if(f38 < -3.141593f) {
                    f38 += 6.283185f;
                }
                double f42 = (double)(f38 * f4);
                float f43 = (float)Math.cos(f42);
                float f44 = (float)Math.sin(f42);
                bone3.a = f43 * f33 - f44 * f35;
                bone3.b = f43 * f34 - f44 * f36;
                bone3.c = f33 * f44 + f35 * f43;
                bone3.d = f44 * f34 + f43 * f36;
                f23 = f26;
                f24 = f41;
            }
            else {
                v16 = v11;
                v15 = v12;
                z3 = z;
                v14 = v;
                arr_f4 = arr_f;
                arr_f3 = arr_f1;
                arr_f5 = arr_f2;
                f23 = f26;
                f24 = f31;
            }
            bone3.updateAppliedTransform();
            v11 = v16 + 1;
            v12 = v15 + 3;
            arr_f2 = arr_f5;
            v = v14;
            arr_object = arr_object1;
            v5 = v13;
            arr_f = arr_f4;
            arr_f1 = arr_f3;
            z = z3;
        }
    }

    class com.esotericsoftware.spine.PathConstraint.1 {
        static final int[] $SwitchMap$com$esotericsoftware$spine$PathConstraintData$SpacingMode;

        static {
            com.esotericsoftware.spine.PathConstraint.1.$SwitchMap$com$esotericsoftware$spine$PathConstraintData$SpacingMode = new int[SpacingMode.values().length];
            try {
                com.esotericsoftware.spine.PathConstraint.1.$SwitchMap$com$esotericsoftware$spine$PathConstraintData$SpacingMode[SpacingMode.percent.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.esotericsoftware.spine.PathConstraint.1.$SwitchMap$com$esotericsoftware$spine$PathConstraintData$SpacingMode[SpacingMode.proportional.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

