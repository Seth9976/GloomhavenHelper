package com.esotericsoftware.spine;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.utils.SpineUtils;

public class TransformConstraint implements Updatable {
    boolean active;
    final Array bones;
    final TransformConstraintData data;
    float mixRotate;
    float mixScaleX;
    float mixScaleY;
    float mixShearY;
    float mixX;
    float mixY;
    Bone target;
    final Vector2 temp;

    public TransformConstraint(TransformConstraint transformConstraint0, Skeleton skeleton0) {
        this.temp = new Vector2();
        if(transformConstraint0 == null) {
            throw new IllegalArgumentException("constraint cannot be null.");
        }
        if(skeleton0 == null) {
            throw new IllegalArgumentException("skeleton cannot be null.");
        }
        this.data = transformConstraint0.data;
        this.bones = new Array(transformConstraint0.bones.size);
        for(Object object0: transformConstraint0.bones) {
            Object object1 = skeleton0.bones.get(((Bone)object0).data.index);
            this.bones.add(object1);
        }
        this.target = (Bone)skeleton0.bones.get(transformConstraint0.target.data.index);
        this.mixRotate = transformConstraint0.mixRotate;
        this.mixX = transformConstraint0.mixX;
        this.mixY = transformConstraint0.mixY;
        this.mixScaleX = transformConstraint0.mixScaleX;
        this.mixScaleY = transformConstraint0.mixScaleY;
        this.mixShearY = transformConstraint0.mixShearY;
    }

    public TransformConstraint(TransformConstraintData transformConstraintData0, Skeleton skeleton0) {
        this.temp = new Vector2();
        if(transformConstraintData0 == null) {
            throw new IllegalArgumentException("data cannot be null.");
        }
        if(skeleton0 == null) {
            throw new IllegalArgumentException("skeleton cannot be null.");
        }
        this.data = transformConstraintData0;
        this.mixRotate = transformConstraintData0.mixRotate;
        this.mixX = transformConstraintData0.mixX;
        this.mixY = transformConstraintData0.mixY;
        this.mixScaleX = transformConstraintData0.mixScaleX;
        this.mixScaleY = transformConstraintData0.mixScaleY;
        this.mixShearY = transformConstraintData0.mixShearY;
        this.bones = new Array(transformConstraintData0.bones.size);
        for(Object object0: transformConstraintData0.bones) {
            Object object1 = skeleton0.bones.get(((BoneData)object0).index);
            this.bones.add(object1);
        }
        this.target = (Bone)skeleton0.bones.get(transformConstraintData0.target.index);
    }

    private void applyAbsoluteLocal() {
        float f16;
        float f8;
        float f = this.mixRotate;
        float f1 = this.mixX;
        float f2 = this.mixY;
        float f3 = this.mixScaleX;
        float f4 = this.mixScaleY;
        float f5 = this.mixShearY;
        Bone bone0 = this.target;
        Object[] arr_object = this.bones.items;
        int v = this.bones.size;
        for(int v1 = 0; v1 < v; ++v1) {
            Bone bone1 = (Bone)arr_object[v1];
            float f6 = bone1.arotation;
            if(f == 0.0f) {
                f8 = f6;
            }
            else {
                float f7 = bone0.arotation - f6 + this.data.offsetRotation;
                f8 = f6 + (f7 - ((float)((0x4000 - ((int)(16384.5 - ((double)(f7 / 360.0f))))) * 360))) * f;
            }
            float f9 = bone1.ax + (bone0.ax - bone1.ax + this.data.offsetX) * f1;
            float f10 = bone1.ay + (bone0.ay - bone1.ay + this.data.offsetY) * f2;
            float f11 = bone1.ascaleY;
            float f12 = f3 == 0.0f || bone1.ascaleX == 0.0f ? bone1.ascaleX : ((bone0.ascaleX - bone1.ascaleX + this.data.offsetScaleX) * f3 + bone1.ascaleX) / bone1.ascaleX;
            float f13 = f4 == 0.0f || f11 == 0.0f ? f11 : ((bone0.ascaleY - f11 + this.data.offsetScaleY) * f4 + f11) / f11;
            float f14 = bone1.ashearY;
            if(f5 == 0.0f) {
                f16 = f14;
            }
            else {
                float f15 = bone0.ashearY - f14 + this.data.offsetShearY;
                f16 = f14 + (f15 - ((float)((0x4000 - ((int)(16384.5 - ((double)(f15 / 360.0f))))) * 360))) * f5;
            }
            bone1.updateWorldTransform(f9, f10, f8, f12, f13, bone1.ashearX, f16);
        }
    }

    private void applyAbsoluteWorld() {
        float f29;
        float f28;
        float f17;
        float f15;
        int v2;
        float f = this.mixRotate;
        float f1 = this.mixX;
        float f2 = this.mixY;
        float f3 = this.mixScaleX;
        float f4 = this.mixScaleY;
        float f5 = this.mixShearY;
        Bone bone0 = this.target;
        float f6 = bone0.a;
        float f7 = bone0.b;
        float f8 = bone0.c;
        float f9 = bone0.d;
        float f10 = f6 * f9 - f7 * f8 > 0.0f ? 0.017453f : -0.017453f;
        float f11 = this.data.offsetRotation * f10;
        Object[] arr_object = this.bones.items;
        float f12 = this.data.offsetShearY * f10;
        int v = this.bones.size;
        int v1 = 0;
        while(v1 < v) {
            Bone bone1 = (Bone)arr_object[v1];
            if(f == 0.0f) {
                v2 = v1;
                f17 = f7;
                f15 = f9;
            }
            else {
                float f13 = bone1.a;
                v2 = v1;
                float f14 = bone1.b;
                f15 = f9;
                float f16 = bone1.c;
                f17 = f7;
                float f18 = bone1.d;
                float f19 = SpineUtils.atan2(f8, f6) - SpineUtils.atan2(f16, f13) + f11;
                if(f19 > 3.141593f) {
                    f19 -= 6.283185f;
                }
                else if(f19 < -3.141593f) {
                    f19 += 6.283185f;
                }
                float f20 = f19 * f;
                float f21 = SpineUtils.cos(f20);
                float f22 = SpineUtils.sin(f20);
                bone1.a = f21 * f13 - f22 * f16;
                bone1.b = f21 * f14 - f22 * f18;
                bone1.c = f13 * f22 + f16 * f21;
                bone1.d = f22 * f14 + f21 * f18;
            }
            if(f1 != 0.0f || f2 != 0.0f) {
                bone0.localToWorld(this.temp.set(this.data.offsetX, this.data.offsetY));
                bone1.worldX += (this.temp.x - bone1.worldX) * f1;
                bone1.worldY += (this.temp.y - bone1.worldY) * f2;
            }
            if(f3 != 0.0f) {
                float f23 = (float)Math.sqrt(bone1.a * bone1.a + bone1.c * bone1.c);
                f23 = f23 == 0.0f ? ((float)Math.sqrt(bone1.a * bone1.a + bone1.c * bone1.c)) : ((((float)Math.sqrt(f6 * f6 + f8 * f8)) - f23 + this.data.offsetScaleX) * f3 + f23) / f23;
                bone1.a *= f23;
                bone1.c *= f23;
            }
            if(f4 != 0.0f) {
                float f24 = (float)Math.sqrt(bone1.b * bone1.b + bone1.d * bone1.d);
                f24 = f24 == 0.0f ? ((float)Math.sqrt(bone1.b * bone1.b + bone1.d * bone1.d)) : ((((float)Math.sqrt(f17 * f17 + f15 * f15)) - f24 + this.data.offsetScaleY) * f4 + f24) / f24;
                bone1.b *= f24;
                bone1.d *= f24;
            }
            if(f5 > 0.0f) {
                float f25 = bone1.b;
                float f26 = bone1.d;
                float f27 = SpineUtils.atan2(f26, f25);
                f28 = f17;
                f29 = f15;
                float f30 = SpineUtils.atan2(f15, f28) - SpineUtils.atan2(f8, f6) - (f27 - SpineUtils.atan2(bone1.c, bone1.a));
                if(f30 > 3.141593f) {
                    f30 -= 6.283185f;
                }
                else if(f30 < -3.141593f) {
                    f30 += 6.283185f;
                }
                float f31 = f27 + (f30 + f12) * f5;
                float f32 = (float)Math.sqrt(f25 * f25 + f26 * f26);
                bone1.b = SpineUtils.cos(f31) * f32;
                bone1.d = SpineUtils.sin(f31) * f32;
            }
            else {
                f28 = f17;
                f29 = f15;
            }
            bone1.updateAppliedTransform();
            v1 = v2 + 1;
            f7 = f28;
            f9 = f29;
        }
    }

    private void applyRelativeLocal() {
        float f = this.mixRotate;
        float f1 = this.mixX;
        float f2 = this.mixY;
        float f3 = this.mixScaleX;
        float f4 = this.mixScaleY;
        float f5 = this.mixShearY;
        Bone bone0 = this.target;
        Object[] arr_object = this.bones.items;
        int v = this.bones.size;
        for(int v1 = 0; v1 < v; ++v1) {
            Bone bone1 = (Bone)arr_object[v1];
            bone1.updateWorldTransform((bone0.ax + this.data.offsetX) * f1 + bone1.ax, (bone0.ay + this.data.offsetY) * f2 + bone1.ay, bone1.arotation + (bone0.arotation + this.data.offsetRotation) * f, bone1.ascaleX * ((bone0.ascaleX - 1.0f + this.data.offsetScaleX) * f3 + 1.0f), bone1.ascaleY * ((bone0.ascaleY - 1.0f + this.data.offsetScaleY) * f4 + 1.0f), bone1.ashearX, bone1.ashearY + (bone0.ashearY + this.data.offsetShearY) * f5);
        }
    }

    private void applyRelativeWorld() {
        float f26;
        float f25;
        float f17;
        float f15;
        int v2;
        float f = this.mixRotate;
        float f1 = this.mixX;
        float f2 = this.mixY;
        float f3 = this.mixScaleX;
        float f4 = this.mixScaleY;
        float f5 = this.mixShearY;
        Bone bone0 = this.target;
        float f6 = bone0.a;
        float f7 = bone0.b;
        float f8 = bone0.c;
        float f9 = bone0.d;
        float f10 = f6 * f9 - f7 * f8 > 0.0f ? 0.017453f : -0.017453f;
        float f11 = this.data.offsetRotation * f10;
        Object[] arr_object = this.bones.items;
        float f12 = this.data.offsetShearY * f10;
        int v = this.bones.size;
        for(int v1 = 0; v1 < v; v1 = v2 + 1) {
            Bone bone1 = (Bone)arr_object[v1];
            if(f == 0.0f) {
                v2 = v1;
                f17 = f7;
                f15 = f9;
            }
            else {
                float f13 = bone1.a;
                v2 = v1;
                float f14 = bone1.b;
                f15 = f9;
                float f16 = bone1.c;
                f17 = f7;
                float f18 = bone1.d;
                float f19 = SpineUtils.atan2(f8, f6) + f11;
                if(f19 > 3.141593f) {
                    f19 -= 6.283185f;
                }
                else if(f19 < -3.141593f) {
                    f19 += 6.283185f;
                }
                float f20 = f19 * f;
                float f21 = SpineUtils.cos(f20);
                float f22 = SpineUtils.sin(f20);
                bone1.a = f21 * f13 - f22 * f16;
                bone1.b = f21 * f14 - f22 * f18;
                bone1.c = f13 * f22 + f16 * f21;
                bone1.d = f22 * f14 + f21 * f18;
            }
            if(f1 != 0.0f || f2 != 0.0f) {
                bone0.localToWorld(this.temp.set(this.data.offsetX, this.data.offsetY));
                bone1.worldX += this.temp.x * f1;
                bone1.worldY += this.temp.y * f2;
            }
            if(f3 != 0.0f) {
                float f23 = (((float)Math.sqrt(f6 * f6 + f8 * f8)) - 1.0f + this.data.offsetScaleX) * f3 + 1.0f;
                bone1.a *= f23;
                bone1.c *= f23;
            }
            if(f4 != 0.0f) {
                float f24 = (((float)Math.sqrt(f17 * f17 + f15 * f15)) - 1.0f + this.data.offsetScaleY) * f4 + 1.0f;
                bone1.b *= f24;
                bone1.d *= f24;
            }
            if(f5 > 0.0f) {
                f25 = f15;
                f26 = f17;
                float f27 = SpineUtils.atan2(f25, f26) - SpineUtils.atan2(f8, f6);
                if(f27 > 3.141593f) {
                    f27 -= 6.283185f;
                }
                else if(f27 < -3.141593f) {
                    f27 += 6.283185f;
                }
                float f28 = SpineUtils.atan2(bone1.d, bone1.b) + (f27 - 1.570796f + f12) * f5;
                float f29 = (float)Math.sqrt(bone1.b * bone1.b + bone1.d * bone1.d);
                bone1.b = SpineUtils.cos(f28) * f29;
                bone1.d = SpineUtils.sin(f28) * f29;
            }
            else {
                f25 = f15;
                f26 = f17;
            }
            bone1.updateAppliedTransform();
            f9 = f25;
            f7 = f26;
        }
    }

    public Array getBones() {
        return this.bones;
    }

    public TransformConstraintData getData() {
        return this.data;
    }

    public float getMixRotate() {
        return this.mixRotate;
    }

    public float getMixScaleX() {
        return this.mixScaleX;
    }

    public float getMixScaleY() {
        return this.mixScaleY;
    }

    public float getMixShearY() {
        return this.mixShearY;
    }

    public float getMixX() {
        return this.mixX;
    }

    public float getMixY() {
        return this.mixY;
    }

    public Bone getTarget() {
        return this.target;
    }

    @Override  // com.esotericsoftware.spine.Updatable
    public boolean isActive() {
        return this.active;
    }

    public void setMixRotate(float f) {
        this.mixRotate = f;
    }

    public void setMixScaleX(float f) {
        this.mixScaleX = f;
    }

    public void setMixScaleY(float f) {
        this.mixScaleY = f;
    }

    public void setMixShearY(float f) {
        this.mixShearY = f;
    }

    public void setMixX(float f) {
        this.mixX = f;
    }

    public void setMixY(float f) {
        this.mixY = f;
    }

    public void setTarget(Bone bone0) {
        if(bone0 == null) {
            throw new IllegalArgumentException("target cannot be null.");
        }
        this.target = bone0;
    }

    public void setToSetupPose() {
        this.mixRotate = this.data.mixRotate;
        this.mixX = this.data.mixX;
        this.mixY = this.data.mixY;
        this.mixScaleX = this.data.mixScaleX;
        this.mixScaleY = this.data.mixScaleY;
        this.mixShearY = this.data.mixShearY;
    }

    @Override
    public String toString() {
        return this.data.name;
    }

    @Override  // com.esotericsoftware.spine.Updatable
    public void update() {
        if(this.mixRotate == 0.0f && this.mixX == 0.0f && this.mixY == 0.0f && this.mixScaleX == 0.0f && this.mixShearY == 0.0f) {
            return;
        }
        if(this.data.local) {
            if(this.data.relative) {
                this.applyRelativeLocal();
                return;
            }
            this.applyAbsoluteLocal();
            return;
        }
        if(this.data.relative) {
            this.applyRelativeWorld();
            return;
        }
        this.applyAbsoluteWorld();
    }
}

