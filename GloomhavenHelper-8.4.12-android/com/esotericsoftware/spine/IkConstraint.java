package com.esotericsoftware.spine;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.utils.SpineUtils;

public class IkConstraint implements Updatable {
    boolean active;
    int bendDirection;
    final Array bones;
    boolean compress;
    final IkConstraintData data;
    float mix;
    float softness;
    boolean stretch;
    Bone target;

    public IkConstraint(IkConstraint ikConstraint0, Skeleton skeleton0) {
        this.mix = 1.0f;
        if(ikConstraint0 == null) {
            throw new IllegalArgumentException("constraint cannot be null.");
        }
        if(skeleton0 == null) {
            throw new IllegalArgumentException("skeleton cannot be null.");
        }
        this.data = ikConstraint0.data;
        this.bones = new Array(ikConstraint0.bones.size);
        for(Object object0: ikConstraint0.bones) {
            Object object1 = skeleton0.bones.get(((Bone)object0).data.index);
            this.bones.add(object1);
        }
        this.target = (Bone)skeleton0.bones.get(ikConstraint0.target.data.index);
        this.mix = ikConstraint0.mix;
        this.softness = ikConstraint0.softness;
        this.bendDirection = ikConstraint0.bendDirection;
        this.compress = ikConstraint0.compress;
        this.stretch = ikConstraint0.stretch;
    }

    public IkConstraint(IkConstraintData ikConstraintData0, Skeleton skeleton0) {
        this.mix = 1.0f;
        if(ikConstraintData0 == null) {
            throw new IllegalArgumentException("data cannot be null.");
        }
        if(skeleton0 == null) {
            throw new IllegalArgumentException("skeleton cannot be null.");
        }
        this.data = ikConstraintData0;
        this.mix = ikConstraintData0.mix;
        this.softness = ikConstraintData0.softness;
        this.bendDirection = ikConstraintData0.bendDirection;
        this.compress = ikConstraintData0.compress;
        this.stretch = ikConstraintData0.stretch;
        this.bones = new Array(ikConstraintData0.bones.size);
        for(Object object0: ikConstraintData0.bones) {
            Object object1 = skeleton0.bones.get(((BoneData)object0).index);
            this.bones.add(object1);
        }
        this.target = (Bone)skeleton0.bones.get(ikConstraintData0.target.index);
    }

    public static void apply(Bone bone0, float f, float f1, boolean z, boolean z1, boolean z2, float f2) {
        float f26;
        float f25;
        float f9;
        float f8;
        float f11;
        float f10;
        if(bone0 == null) {
            throw new IllegalArgumentException("bone cannot be null.");
        }
        Bone bone1 = bone0.parent;
        float f3 = bone1.a;
        float f4 = bone1.b;
        float f5 = bone1.c;
        float f6 = bone1.d;
        float f7 = -bone0.ashearX - bone0.arotation;
        switch(com.esotericsoftware.spine.IkConstraint.1.$SwitchMap$com$esotericsoftware$spine$BoneData$TransformMode[bone0.data.transformMode.ordinal()]) {
            case 1: {
                f10 = f - bone0.worldX;
                f11 = f1 - bone0.worldY;
                break;
            }
            case 2: {
                float f12 = Math.abs(f6 * f3 - f4 * f5) / (f3 * f3 + f5 * f5);
                float f13 = f3 / bone0.skeleton.scaleX;
                float f14 = f5 / bone0.skeleton.scaleY;
                f8 = -f14 * f12 * bone0.skeleton.scaleX;
                f9 = f12 * f13 * bone0.skeleton.scaleY;
                f7 += SpineUtils.atan2(f14, f13) * 57.295776f;
            label_20:
                float f15 = f - bone1.worldX;
                float f16 = f1 - bone1.worldY;
                float f17 = f3 * f9 - f8 * f5;
                float f18 = (f9 * f15 - f8 * f16) / f17 - bone0.ax;
                f11 = (f16 * f3 - f15 * f5) / f17 - bone0.ay;
                f10 = f18;
                break;
            }
            default: {
                f8 = f4;
                f9 = f6;
                goto label_20;
            }
        }
        float f19 = bone0.ascaleX < 0.0f ? f7 + SpineUtils.atan2(f11, f10) * 57.295776f + 180.0f : f7 + SpineUtils.atan2(f11, f10) * 57.295776f;
        if(f19 > 180.0f) {
            f19 -= 360.0f;
        }
        else if(f19 < -180.0f) {
            f19 += 360.0f;
        }
        float f20 = bone0.ascaleX;
        float f21 = bone0.ascaleY;
        if(!z && !z1) {
            f25 = f21;
            f26 = f20;
        }
        else {
            switch(com.esotericsoftware.spine.IkConstraint.1.$SwitchMap$com$esotericsoftware$spine$BoneData$TransformMode[bone0.data.transformMode.ordinal()]) {
                case 3: 
                case 4: {
                    f10 = f - bone0.worldX;
                    f11 = f1 - bone0.worldY;
                }
            }
            float f22 = bone0.data.length * f20;
            float f23 = (float)Math.sqrt(f10 * f10 + f11 * f11);
            if((!z || f23 >= f22) && (!z1 || f23 <= f22 || f22 <= 0.0001f)) {
                f25 = f21;
                f26 = f20;
            }
            else {
                float f24 = (f23 / f22 - 1.0f) * f2 + 1.0f;
                if(z2) {
                    f21 *= f24;
                }
                f25 = f21;
                f26 = f20 * f24;
            }
        }
        bone0.updateWorldTransform(bone0.ax, bone0.ay, bone0.arotation + f19 * f2, f26, f25, bone0.ashearX, bone0.ashearY);
    }

    public static void apply(Bone bone0, Bone bone1, float f, float f1, int v, boolean z, boolean z1, float f2, float f3) {
        float f79;
        float f78;
        float f77;
        float f76;
        float f72;
        float f51;
        float f50;
        float f49;
        float f45;
        float f44;
        float f18;
        float f17;
        float f16;
        int v4;
        float f10;
        int v3;
        int v2;
        float f9;
        if(bone0 == null) {
            throw new IllegalArgumentException("parent cannot be null.");
        }
        if(bone1 == null) {
            throw new IllegalArgumentException("child cannot be null.");
        }
        float f4 = bone0.ax;
        float f5 = bone0.ay;
        float f6 = bone0.ascaleX;
        float f7 = bone0.ascaleY;
        float f8 = bone1.ascaleX;
        int v1 = 180;
        if(f6 < 0.0f) {
            f9 = -f6;
            v2 = -1;
            v3 = 180;
        }
        else {
            f9 = f6;
            v2 = 1;
            v3 = 0;
        }
        if(f7 < 0.0f) {
            f10 = -f7;
            v2 = -v2;
        }
        else {
            f10 = f7;
        }
        if(f8 < 0.0f) {
            f8 = -f8;
        }
        else {
            v1 = 0;
        }
        float f11 = bone1.ax;
        float f12 = bone0.a;
        float f13 = bone0.b;
        float f14 = bone0.c;
        float f15 = bone0.d;
        boolean z2 = Math.abs(f9 - f10) <= 0.0001f;
        if(!z2 || z) {
            v4 = v2;
            f17 = f12 * f11 + bone0.worldX;
            f18 = f14 * f11 + bone0.worldY;
            f16 = 0.0f;
        }
        else {
            v4 = v2;
            f16 = bone1.ay;
            f17 = f12 * f11 + f13 * f16 + bone0.worldX;
            f18 = f14 * f11 + f15 * f16 + bone0.worldY;
        }
        Bone bone2 = bone0.parent;
        float f19 = bone2.a;
        float f20 = bone2.b;
        float f21 = bone2.c;
        float f22 = f7;
        float f23 = bone2.d;
        float f24 = f6;
        float f25 = 1.0f / (f19 * f23 - f20 * f21);
        float f26 = f17 - bone2.worldX;
        float f27 = f18 - bone2.worldY;
        float f28 = (f26 * f23 - f27 * f20) * f25 - f4;
        float f29 = (f27 * f19 - f26 * f21) * f25 - f5;
        float f30 = (float)Math.sqrt(f28 * f28 + f29 * f29);
        float f31 = bone1.data.length * f8;
        if(f30 < 0.0001f) {
            IkConstraint.apply(bone0, f, f1, false, z, false, f3);
            bone1.updateWorldTransform(f11, f16, 0.0f, bone1.ascaleX, bone1.ascaleY, bone1.ashearX, bone1.ashearY);
            return;
        }
        float f32 = f - bone2.worldX;
        float f33 = f1 - bone2.worldY;
        float f34 = (f23 * f32 - f20 * f33) * f25 - f4;
        float f35 = (f33 * f19 - f32 * f21) * f25 - f5;
        float f36 = f34 * f34 + f35 * f35;
        if(f2 != 0.0f) {
            float f37 = (f8 + 1.0f) * f9 * 0.5f * f2;
            float f38 = (float)Math.sqrt(f36);
            float f39 = f38 - f30 - f31 * f9 + f37;
            if(f39 > 0.0f) {
                float f40 = Math.min(1.0f, f39 / (f37 * 2.0f));
                float f41 = (f39 - f37 * (1.0f - (f40 - 1.0f) * (f40 - 1.0f))) / f38;
                f34 -= f41 * f34;
                f35 -= f41 * f35;
                f36 = f34 * f34 + f35 * f35;
            }
        }
        if(z2) {
            float f42 = f31 * f9;
            float f43 = (f36 - f30 * f30 - f42 * f42) / (2.0f * f30 * f42);
            if(f43 < -1.0f) {
                f44 = ((float)v) * 3.141593f;
                f45 = -1.0f;
            }
            else if(f43 <= 1.0f) {
                f44 = ((float)Math.acos(f43)) * ((float)v);
                f45 = f43;
            }
            else if(z) {
                float f46 = (((float)Math.sqrt(f36)) / (f30 + f42) - 1.0f) * f3 + 1.0f;
                if(z1) {
                    f22 *= f46;
                }
                f24 *= f46;
                f44 = 0.0f;
                f45 = 1.0f;
            }
            else {
                f44 = 0.0f;
                f45 = 1.0f;
            }
            float f47 = f30 + f45 * f42;
            float f48 = SpineUtils.sin(f44);
            f49 = SpineUtils.atan2(f35 * f47 - f34 * (f42 * f48), f34 * f47 + f35 * (f42 * f48));
            f50 = f5;
            f51 = f22;
        }
        else {
            float f52 = f9 * f31;
            float f53 = f10 * f31;
            float f54 = f52 * f52;
            float f55 = f53 * f53;
            float f56 = SpineUtils.atan2(f35, f34);
            float f57 = f55 * f30 * f30 + f54 * f36 - f54 * f55;
            float f58 = -2.0f * f55 * f30;
            float f59 = f55 - f54;
            float f60 = f58 * f58 - 4.0f * f59 * f57;
            float f61 = 0.0f;
            if(f60 >= 0.0f) {
                float f62 = -(f58 + (f58 < 0.0f ? -((float)Math.sqrt(f60)) : ((float)Math.sqrt(f60)))) * 0.5f;
                float f63 = f62 / f59;
                float f64 = f57 / f62;
                if(Math.abs(f63) >= Math.abs(f64)) {
                    f63 = f64;
                }
                float f65 = f63 * f63;
                if(f65 <= f36) {
                    float f66 = ((float)Math.sqrt(f36 - f65)) * ((float)v);
                    f49 = f56 - SpineUtils.atan2(f66, f63);
                    f44 = SpineUtils.atan2(f66 / f10, (f63 - f30) / f9);
                    f50 = f5;
                    f51 = f22;
                    goto label_167;
                }
                goto label_125;
            }
            else {
            label_125:
                float f67 = f30 - f52;
                float f68 = f67 * f67;
                float f69 = f30 + f52;
                float f70 = f69 * f69;
                float f71 = -f52 * f30 / (f54 - f55);
                if(f71 < -1.0f || f71 > 1.0f) {
                    f50 = f5;
                    f76 = f67;
                    f79 = f68;
                    f72 = 0.0f;
                    f77 = 3.141593f;
                    f78 = 0.0f;
                }
                else {
                    f50 = f5;
                    f72 = (float)Math.acos(f71);
                    float f73 = f30 + f52 * SpineUtils.cos(f72);
                    float f74 = SpineUtils.sin(f72) * f53;
                    float f75 = f73 * f73 + f74 * f74;
                    if(f75 < f68) {
                        f76 = f73;
                        f77 = f72;
                        f78 = f74;
                        f79 = f75;
                    }
                    else {
                        f76 = f67;
                        f79 = f68;
                        f77 = 3.141593f;
                        f78 = 0.0f;
                    }
                    if(f75 > f70) {
                        f69 = f73;
                        f61 = f74;
                        f70 = f75;
                    }
                    else {
                        f72 = 0.0f;
                    }
                }
                if(f36 <= (f79 + f70) * 0.5f) {
                    f49 = f56 - SpineUtils.atan2(f78 * ((float)v), f76);
                    f44 = f77 * ((float)v);
                }
                else {
                    f49 = f56 - SpineUtils.atan2(f61 * ((float)v), f69);
                    f44 = f72 * ((float)v);
                }
                f51 = f22;
            }
        }
    label_167:
        float f80 = SpineUtils.atan2(f16, f11) * ((float)v4);
        float f81 = bone0.arotation;
        float f82 = (f49 - f80) * 57.295776f + ((float)v3) - f81;
        if(f82 > 180.0f) {
            f82 -= 360.0f;
        }
        else if(f82 < -180.0f) {
            f82 += 360.0f;
        }
        bone0.updateWorldTransform(f4, f50, f81 + f82 * f3, f24, f51, 0.0f, 0.0f);
        float f83 = bone1.arotation;
        float f84 = ((f44 + f80) * 57.295776f - bone1.ashearX) * ((float)v4) + ((float)v1) - f83;
        if(f84 > 180.0f) {
            f84 -= 360.0f;
        }
        else if(f84 < -180.0f) {
            f84 += 360.0f;
        }
        bone1.updateWorldTransform(f11, f16, f83 + f84 * f3, bone1.ascaleX, bone1.ascaleY, bone1.ashearX, bone1.ashearY);
    }

    public int getBendDirection() {
        return this.bendDirection;
    }

    public Array getBones() {
        return this.bones;
    }

    public boolean getCompress() {
        return this.compress;
    }

    public IkConstraintData getData() {
        return this.data;
    }

    public float getMix() {
        return this.mix;
    }

    public float getSoftness() {
        return this.softness;
    }

    public boolean getStretch() {
        return this.stretch;
    }

    public Bone getTarget() {
        return this.target;
    }

    @Override  // com.esotericsoftware.spine.Updatable
    public boolean isActive() {
        return this.active;
    }

    public void setBendDirection(int v) {
        this.bendDirection = v;
    }

    public void setCompress(boolean z) {
        this.compress = z;
    }

    public void setMix(float f) {
        this.mix = f;
    }

    public void setSoftness(float f) {
        this.softness = f;
    }

    public void setStretch(boolean z) {
        this.stretch = z;
    }

    public void setTarget(Bone bone0) {
        if(bone0 == null) {
            throw new IllegalArgumentException("target cannot be null.");
        }
        this.target = bone0;
    }

    public void setToSetupPose() {
        this.mix = this.data.mix;
        this.softness = this.data.softness;
        this.bendDirection = this.data.bendDirection;
        this.compress = this.data.compress;
        this.stretch = this.data.stretch;
    }

    @Override
    public String toString() {
        return this.data.name;
    }

    @Override  // com.esotericsoftware.spine.Updatable
    public void update() {
        if(this.mix == 0.0f) {
            return;
        }
        Bone bone0 = this.target;
        Object[] arr_object = this.bones.items;
        switch(this.bones.size) {
            case 1: {
                IkConstraint.apply(((Bone)arr_object[0]), bone0.worldX, bone0.worldY, this.compress, this.stretch, this.data.uniform, this.mix);
                return;
            }
            case 2: {
                IkConstraint.apply(((Bone)arr_object[0]), ((Bone)arr_object[1]), bone0.worldX, bone0.worldY, this.bendDirection, this.stretch, this.data.uniform, this.softness, this.mix);
            }
        }
    }
}

