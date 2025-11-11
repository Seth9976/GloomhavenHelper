package com.esotericsoftware.spine;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.spine.utils.SpineUtils;

public class Bone implements Updatable {
    float a;
    boolean active;
    float arotation;
    float ascaleX;
    float ascaleY;
    float ashearX;
    float ashearY;
    float ax;
    float ay;
    float b;
    float c;
    final Array children;
    float d;
    final BoneData data;
    @Null
    final Bone parent;
    float rotation;
    float scaleX;
    float scaleY;
    float shearX;
    float shearY;
    final Skeleton skeleton;
    boolean sorted;
    float worldX;
    float worldY;
    float x;
    float y;

    public Bone(Bone bone0, Skeleton skeleton0, @Null Bone bone1) {
        this.children = new Array();
        if(bone0 == null) {
            throw new IllegalArgumentException("bone cannot be null.");
        }
        if(skeleton0 == null) {
            throw new IllegalArgumentException("skeleton cannot be null.");
        }
        this.skeleton = skeleton0;
        this.parent = bone1;
        this.data = bone0.data;
        this.x = bone0.x;
        this.y = bone0.y;
        this.rotation = bone0.rotation;
        this.scaleX = bone0.scaleX;
        this.scaleY = bone0.scaleY;
        this.shearX = bone0.shearX;
        this.shearY = bone0.shearY;
    }

    public Bone(BoneData boneData0, Skeleton skeleton0, @Null Bone bone0) {
        this.children = new Array();
        if(boneData0 == null) {
            throw new IllegalArgumentException("data cannot be null.");
        }
        if(skeleton0 == null) {
            throw new IllegalArgumentException("skeleton cannot be null.");
        }
        this.data = boneData0;
        this.skeleton = skeleton0;
        this.parent = bone0;
        this.setToSetupPose();
    }

    public float getA() {
        return this.a;
    }

    public float getARotation() {
        return this.arotation;
    }

    public float getAScaleX() {
        return this.ascaleX;
    }

    public float getAScaleY() {
        return this.ascaleY;
    }

    public float getAShearX() {
        return this.ashearX;
    }

    public float getAShearY() {
        return this.ashearY;
    }

    public float getAX() {
        return this.ax;
    }

    public float getAY() {
        return this.ay;
    }

    public float getB() {
        return this.b;
    }

    public float getC() {
        return this.c;
    }

    public Array getChildren() {
        return this.children;
    }

    public float getD() {
        return this.d;
    }

    public BoneData getData() {
        return this.data;
    }

    @Null
    public Bone getParent() {
        return this.parent;
    }

    public float getRotation() {
        return this.rotation;
    }

    public float getScaleX() {
        return this.scaleX;
    }

    public float getScaleY() {
        return this.scaleY;
    }

    public float getShearX() {
        return this.shearX;
    }

    public float getShearY() {
        return this.shearY;
    }

    public Skeleton getSkeleton() {
        return this.skeleton;
    }

    public float getWorldRotationX() {
        return SpineUtils.atan2(this.c, this.a) * 57.295776f;
    }

    public float getWorldRotationY() {
        return SpineUtils.atan2(this.d, this.b) * 57.295776f;
    }

    public float getWorldScaleX() {
        return (float)Math.sqrt(this.a * this.a + this.c * this.c);
    }

    public float getWorldScaleY() {
        return (float)Math.sqrt(this.b * this.b + this.d * this.d);
    }

    public Matrix3 getWorldTransform(Matrix3 matrix30) {
        if(matrix30 == null) {
            throw new IllegalArgumentException("worldTransform cannot be null.");
        }
        float[] arr_f = matrix30.val;
        arr_f[0] = this.a;
        arr_f[3] = this.b;
        arr_f[1] = this.c;
        arr_f[4] = this.d;
        arr_f[6] = this.worldX;
        arr_f[7] = this.worldY;
        arr_f[2] = 0.0f;
        arr_f[5] = 0.0f;
        arr_f[8] = 1.0f;
        return matrix30;
    }

    public float getWorldX() {
        return this.worldX;
    }

    public float getWorldY() {
        return this.worldY;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    @Override  // com.esotericsoftware.spine.Updatable
    public boolean isActive() {
        return this.active;
    }

    public Vector2 localToWorld(Vector2 vector20) {
        if(vector20 == null) {
            throw new IllegalArgumentException("local cannot be null.");
        }
        float f = vector20.x;
        vector20.x = this.a * f + this.b * vector20.y + this.worldX;
        vector20.y = f * this.c + vector20.y * this.d + this.worldY;
        return vector20;
    }

    public float localToWorldRotation(float f) {
        float f1 = f - (this.rotation - this.shearX);
        float f2 = SpineUtils.sinDeg(f1);
        float f3 = SpineUtils.cosDeg(f1);
        return SpineUtils.atan2(this.c * f3 + this.d * f2, f3 * this.a + f2 * this.b) * 57.295776f;
    }

    public void rotateWorld(float f) {
        float f1 = SpineUtils.cosDeg(f);
        float f2 = SpineUtils.sinDeg(f);
        this.a = this.a * f1 - f2 * this.c;
        this.b = this.b * f1 - f2 * this.d;
        this.c = this.a * f2 + this.c * f1;
        this.d = f2 * this.b + f1 * this.d;
    }

    public void setA(float f) {
        this.a = f;
    }

    public void setARotation(float f) {
        this.arotation = f;
    }

    public void setAScaleX(float f) {
        this.ascaleX = f;
    }

    public void setAScaleY(float f) {
        this.ascaleY = f;
    }

    public void setAShearX(float f) {
        this.ashearX = f;
    }

    public void setAShearY(float f) {
        this.ashearY = f;
    }

    public void setAX(float f) {
        this.ax = f;
    }

    public void setAY(float f) {
        this.ay = f;
    }

    public void setB(float f) {
        this.b = f;
    }

    public void setC(float f) {
        this.c = f;
    }

    public void setD(float f) {
        this.d = f;
    }

    public void setPosition(float f, float f1) {
        this.x = f;
        this.y = f1;
    }

    public void setRotation(float f) {
        this.rotation = f;
    }

    public void setScale(float f) {
        this.scaleX = f;
        this.scaleY = f;
    }

    public void setScale(float f, float f1) {
        this.scaleX = f;
        this.scaleY = f1;
    }

    public void setScaleX(float f) {
        this.scaleX = f;
    }

    public void setScaleY(float f) {
        this.scaleY = f;
    }

    public void setShearX(float f) {
        this.shearX = f;
    }

    public void setShearY(float f) {
        this.shearY = f;
    }

    public void setToSetupPose() {
        this.x = this.data.x;
        this.y = this.data.y;
        this.rotation = this.data.rotation;
        this.scaleX = this.data.scaleX;
        this.scaleY = this.data.scaleY;
        this.shearX = this.data.shearX;
        this.shearY = this.data.shearY;
    }

    public void setWorldX(float f) {
        this.worldX = f;
    }

    public void setWorldY(float f) {
        this.worldY = f;
    }

    public void setX(float f) {
        this.x = f;
    }

    public void setY(float f) {
        this.y = f;
    }

    @Override
    public String toString() {
        return this.data.name;
    }

    @Override  // com.esotericsoftware.spine.Updatable
    public void update() {
        this.updateWorldTransform(this.ax, this.ay, this.arotation, this.ascaleX, this.ascaleY, this.ashearX, this.ashearY);
    }

    public void updateAppliedTransform() {
        Bone bone0 = this.parent;
        if(bone0 == null) {
            this.ax = this.worldX - this.skeleton.x;
            this.ay = this.worldY - this.skeleton.y;
            this.arotation = SpineUtils.atan2(this.c, this.a) * 57.295776f;
            this.ascaleX = (float)Math.sqrt(this.a * this.a + this.c * this.c);
            this.ascaleY = (float)Math.sqrt(this.b * this.b + this.d * this.d);
            this.ashearX = 0.0f;
            this.ashearY = SpineUtils.atan2(this.a * this.b + this.c * this.d, this.a * this.d - this.b * this.c) * 57.295776f;
            return;
        }
        float f = bone0.a;
        float f1 = bone0.b;
        float f2 = bone0.c;
        float f3 = bone0.d;
        float f4 = 1.0f / (f * f3 - f1 * f2);
        float f5 = this.worldX - bone0.worldX;
        float f6 = this.worldY - bone0.worldY;
        this.ax = f5 * f3 * f4 - f6 * f1 * f4;
        this.ay = f6 * f * f4 - f5 * f2 * f4;
        float f7 = f3 * f4;
        float f8 = f * f4;
        float f9 = f1 * f4;
        float f10 = f4 * f2;
        float f11 = f7 * this.a - f9 * this.c;
        float f12 = f7 * this.b - f9 * this.d;
        float f13 = this.c * f8 - this.a * f10;
        float f14 = f8 * this.d - f10 * this.b;
        this.ashearX = 0.0f;
        this.ascaleX = (float)Math.sqrt(f11 * f11 + f13 * f13);
        float f15 = this.ascaleX;
        if(f15 > 0.0001f) {
            float f16 = f11 * f14 - f12 * f13;
            this.ascaleY = f16 / f15;
            this.ashearY = SpineUtils.atan2(f12 * f11 + f14 * f13, f16) * 57.295776f;
            this.arotation = SpineUtils.atan2(f13, f11) * 57.295776f;
            return;
        }
        this.ascaleX = 0.0f;
        this.ascaleY = (float)Math.sqrt(f12 * f12 + f14 * f14);
        this.ashearY = 0.0f;
        this.arotation = 90.0f - SpineUtils.atan2(f14, f12) * 57.295776f;
    }

    public void updateWorldTransform() {
        this.updateWorldTransform(this.x, this.y, this.rotation, this.scaleX, this.scaleY, this.shearX, this.shearY);
    }

    public void updateWorldTransform(float f, float f1, float f2, float f3, float f4, float f5, float f6) {
        float f26;
        float f25;
        this.ax = f;
        this.ay = f1;
        this.arotation = f2;
        this.ascaleX = f3;
        this.ascaleY = f4;
        this.ashearX = f5;
        this.ashearY = f6;
        Bone bone0 = this.parent;
        if(bone0 == null) {
            float f7 = f2 + 90.0f + f6;
            float f8 = f2 + f5;
            this.a = SpineUtils.cosDeg(f8) * f3 * this.skeleton.scaleX;
            this.b = SpineUtils.cosDeg(f7) * f4 * this.skeleton.scaleX;
            this.c = SpineUtils.sinDeg(f8) * f3 * this.skeleton.scaleY;
            this.d = SpineUtils.sinDeg(f7) * f4 * this.skeleton.scaleY;
            this.worldX = f * this.skeleton.scaleX + this.skeleton.x;
            this.worldY = f1 * this.skeleton.scaleY + this.skeleton.y;
            return;
        }
        float f9 = bone0.a;
        float f10 = bone0.b;
        float f11 = bone0.c;
        float f12 = bone0.d;
        this.worldX = f9 * f + f10 * f1 + bone0.worldX;
        this.worldY = f * f11 + f1 * f12 + bone0.worldY;
        float f13 = 0.0f;
        switch(com.esotericsoftware.spine.Bone.1.$SwitchMap$com$esotericsoftware$spine$BoneData$TransformMode[this.data.transformMode.ordinal()]) {
            case 1: {
                float f14 = f2 + 90.0f + f6;
                float f15 = f2 + f5;
                float f16 = SpineUtils.cosDeg(f15) * f3;
                float f17 = SpineUtils.cosDeg(f14) * f4;
                float f18 = SpineUtils.sinDeg(f15) * f3;
                float f19 = SpineUtils.sinDeg(f14) * f4;
                this.a = f9 * f16 + f10 * f18;
                this.b = f9 * f17 + f10 * f19;
                this.c = f16 * f11 + f18 * f12;
                this.d = f11 * f17 + f12 * f19;
                return;
            }
            case 2: {
                float f20 = f2 + 90.0f + f6;
                float f21 = f2 + f5;
                this.a = SpineUtils.cosDeg(f21) * f3;
                this.b = SpineUtils.cosDeg(f20) * f4;
                this.c = SpineUtils.sinDeg(f21) * f3;
                this.d = SpineUtils.sinDeg(f20) * f4;
                break;
            }
            case 3: {
                float f22 = f9 * f9 + f11 * f11;
                if(f22 > 0.0001f) {
                    float f23 = Math.abs(f12 * f9 - f10 * f11) / f22;
                    float f24 = f9 / this.skeleton.scaleX;
                    f25 = f11 / this.skeleton.scaleY;
                    f10 = f25 * f23;
                    f12 = f24 * f23;
                    f13 = f24;
                    f26 = SpineUtils.atan2(f25, f24) * 57.295776f;
                }
                else {
                    f26 = 90.0f - SpineUtils.atan2(f12, f10) * 57.295776f;
                    f25 = 0.0f;
                }
                float f27 = f5 + f2 - f26;
                float f28 = f2 + f6 - f26 + 90.0f;
                float f29 = SpineUtils.cosDeg(f27) * f3;
                float f30 = SpineUtils.cosDeg(f28) * f4;
                float f31 = SpineUtils.sinDeg(f27) * f3;
                float f32 = SpineUtils.sinDeg(f28) * f4;
                this.a = f13 * f29 - f10 * f31;
                this.b = f13 * f30 - f10 * f32;
                this.c = f29 * f25 + f31 * f12;
                this.d = f25 * f30 + f12 * f32;
                break;
            }
            case 4: 
            case 5: {
                float f33 = SpineUtils.cosDeg(f2);
                float f34 = SpineUtils.sinDeg(f2);
                float f35 = (f9 * f33 + f10 * f34) / this.skeleton.scaleX;
                float f36 = (f33 * f11 + f34 * f12) / this.skeleton.scaleY;
                float f37 = (float)Math.sqrt(f35 * f35 + f36 * f36);
                f37 = f37 > 0.00001f ? 1.0f / f37 : ((float)Math.sqrt(f35 * f35 + f36 * f36));
                int v = 1;
                float f38 = f35 * f37;
                float f39 = f36 * f37;
                float f40 = (float)Math.sqrt(f38 * f38 + f39 * f39);
                if(this.data.transformMode == TransformMode.noScale) {
                    if((this.skeleton.scaleX < 0.0f ? 1 : 0) == (this.skeleton.scaleY < 0.0f ? 1 : 0)) {
                        v = 0;
                    }
                    if((f9 * f12 - f10 * f11 < 0.0f ? 1 : 0) != v) {
                        f40 = -f40;
                    }
                }
                float f41 = SpineUtils.atan2(f39, f38);
                float f42 = SpineUtils.cos(f41 + 1.570796f) * f40;
                float f43 = SpineUtils.sin(f41 + 1.570796f) * f40;
                float f44 = SpineUtils.cosDeg(f5) * f3;
                float f45 = SpineUtils.cosDeg(f6 + 90.0f) * f4;
                float f46 = SpineUtils.sinDeg(f5) * f3;
                float f47 = SpineUtils.sinDeg(f6 + 90.0f) * f4;
                this.a = f38 * f44 + f42 * f46;
                this.b = f38 * f45 + f42 * f47;
                this.c = f44 * f39 + f46 * f43;
                this.d = f39 * f45 + f43 * f47;
            }
        }
        this.a *= this.skeleton.scaleX;
        this.b *= this.skeleton.scaleX;
        this.c *= this.skeleton.scaleY;
        this.d *= this.skeleton.scaleY;
    }

    public Vector2 worldToLocal(Vector2 vector20) {
        if(vector20 == null) {
            throw new IllegalArgumentException("world cannot be null.");
        }
        float f = this.a * this.d - this.b * this.c;
        float f1 = vector20.x - this.worldX;
        float f2 = vector20.y - this.worldY;
        vector20.x = (this.d * f1 - this.b * f2) / f;
        vector20.y = (f2 * this.a - f1 * this.c) / f;
        return vector20;
    }

    public float worldToLocalRotation(float f) {
        float f1 = SpineUtils.sinDeg(f);
        float f2 = SpineUtils.cosDeg(f);
        return SpineUtils.atan2(this.a * f1 - this.c * f2, this.d * f2 - this.b * f1) * 57.295776f + this.rotation - this.shearX;
    }
}

