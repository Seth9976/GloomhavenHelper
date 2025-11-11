package com.esotericsoftware.spine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.spine.attachments.Attachment;
import com.esotericsoftware.spine.attachments.MeshAttachment;
import com.esotericsoftware.spine.attachments.PathAttachment;
import com.esotericsoftware.spine.attachments.RegionAttachment;
import com.esotericsoftware.spine.utils.SpineUtils;

public class Skeleton {
    final Array bones;
    final Color color;
    final SkeletonData data;
    Array drawOrder;
    final Array ikConstraints;
    final Array pathConstraints;
    final Array physicsConstraints;
    float scaleX;
    float scaleY;
    @Null
    Skin skin;
    final Array slots;
    final Array transformConstraints;
    final Array updateCache;
    float x;
    float y;

    public Skeleton(Skeleton skeleton0) {
        Bone bone1;
        this.updateCache = new Array();
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        if(skeleton0 == null) {
            throw new IllegalArgumentException("skeleton cannot be null.");
        }
        this.data = skeleton0.data;
        this.bones = new Array(skeleton0.bones.size);
        for(Object object0: skeleton0.bones) {
            Bone bone0 = (Bone)object0;
            if(bone0.parent == null) {
                bone1 = new Bone(bone0, this, null);
            }
            else {
                Bone bone2 = (Bone)this.bones.get(bone0.parent.data.index);
                Bone bone3 = new Bone(bone0, this, bone2);
                bone2.children.add(bone3);
                bone1 = bone3;
            }
            this.bones.add(bone1);
        }
        this.slots = new Array(skeleton0.slots.size);
        for(Object object1: skeleton0.slots) {
            Slot slot0 = new Slot(((Slot)object1), ((Bone)this.bones.get(((Slot)object1).bone.data.index)));
            this.slots.add(slot0);
        }
        this.drawOrder = new Array(this.slots.size);
        for(Object object2: skeleton0.drawOrder) {
            this.drawOrder.add(this.slots.get(((Slot)object2).data.index));
        }
        this.ikConstraints = new Array(skeleton0.ikConstraints.size);
        for(Object object3: skeleton0.ikConstraints) {
            IkConstraint ikConstraint0 = new IkConstraint(((IkConstraint)object3), this);
            this.ikConstraints.add(ikConstraint0);
        }
        this.transformConstraints = new Array(skeleton0.transformConstraints.size);
        for(Object object4: skeleton0.transformConstraints) {
            TransformConstraint transformConstraint0 = new TransformConstraint(((TransformConstraint)object4), this);
            this.transformConstraints.add(transformConstraint0);
        }
        this.pathConstraints = new Array(skeleton0.pathConstraints.size);
        for(Object object5: skeleton0.pathConstraints) {
            PathConstraint pathConstraint0 = new PathConstraint(((PathConstraint)object5), this);
            this.pathConstraints.add(pathConstraint0);
        }
        this.physicsConstraints = new Array(skeleton0.physicsConstraints.size);
        for(Object object6: skeleton0.physicsConstraints) {
            PhysicsConstraint physicsConstraint0 = new PhysicsConstraint(((PhysicsConstraint)object6), this);
            this.physicsConstraints.add(physicsConstraint0);
        }
        this.skin = skeleton0.skin;
        this.color = new Color(skeleton0.color);
        this.scaleX = skeleton0.scaleX;
        this.scaleY = skeleton0.scaleY;
        this.updateCache();
    }

    public Skeleton(SkeletonData skeletonData0) {
        Bone bone0;
        this.updateCache = new Array();
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        if(skeletonData0 == null) {
            throw new IllegalArgumentException("data cannot be null.");
        }
        this.data = skeletonData0;
        this.bones = new Array(skeletonData0.bones.size);
        Object[] arr_object = this.bones.items;
        for(Object object0: skeletonData0.bones) {
            BoneData boneData0 = (BoneData)object0;
            if(boneData0.parent == null) {
                bone0 = new Bone(boneData0, this, null);
            }
            else {
                Bone bone1 = (Bone)arr_object[boneData0.parent.index];
                Bone bone2 = new Bone(boneData0, this, bone1);
                bone1.children.add(bone2);
                bone0 = bone2;
            }
            this.bones.add(bone0);
        }
        this.slots = new Array(skeletonData0.slots.size);
        this.drawOrder = new Array(skeletonData0.slots.size);
        for(Object object1: skeletonData0.slots) {
            Slot slot0 = new Slot(((SlotData)object1), ((Bone)arr_object[((SlotData)object1).boneData.index]));
            this.slots.add(slot0);
            this.drawOrder.add(slot0);
        }
        this.ikConstraints = new Array(skeletonData0.ikConstraints.size);
        for(Object object2: skeletonData0.ikConstraints) {
            IkConstraint ikConstraint0 = new IkConstraint(((IkConstraintData)object2), this);
            this.ikConstraints.add(ikConstraint0);
        }
        this.transformConstraints = new Array(skeletonData0.transformConstraints.size);
        for(Object object3: skeletonData0.transformConstraints) {
            TransformConstraint transformConstraint0 = new TransformConstraint(((TransformConstraintData)object3), this);
            this.transformConstraints.add(transformConstraint0);
        }
        this.pathConstraints = new Array(skeletonData0.pathConstraints.size);
        for(Object object4: skeletonData0.pathConstraints) {
            PathConstraint pathConstraint0 = new PathConstraint(((PathConstraintData)object4), this);
            this.pathConstraints.add(pathConstraint0);
        }
        this.physicsConstraints = new Array(skeletonData0.physicsConstraints.size);
        for(Object object5: skeletonData0.physicsConstraints) {
            PhysicsConstraint physicsConstraint0 = new PhysicsConstraint(((PhysicsConstraintData)object5), this);
            this.physicsConstraints.add(physicsConstraint0);
        }
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.updateCache();
    }

    @Null
    public Bone findBone(String s) {
        if(s == null) {
            throw new IllegalArgumentException("boneName cannot be null.");
        }
        Object[] arr_object = this.bones.items;
        int v1 = this.bones.size;
        for(int v = 0; v < v1; ++v) {
            Bone bone0 = (Bone)arr_object[v];
            if(bone0.data.name.equals(s)) {
                return bone0;
            }
        }
        return null;
    }

    @Null
    public IkConstraint findIkConstraint(String s) {
        if(s == null) {
            throw new IllegalArgumentException("constraintName cannot be null.");
        }
        Object[] arr_object = this.ikConstraints.items;
        int v1 = this.ikConstraints.size;
        for(int v = 0; v < v1; ++v) {
            IkConstraint ikConstraint0 = (IkConstraint)arr_object[v];
            if(ikConstraint0.data.name.equals(s)) {
                return ikConstraint0;
            }
        }
        return null;
    }

    @Null
    public PathConstraint findPathConstraint(String s) {
        if(s == null) {
            throw new IllegalArgumentException("constraintName cannot be null.");
        }
        Object[] arr_object = this.pathConstraints.items;
        int v1 = this.pathConstraints.size;
        for(int v = 0; v < v1; ++v) {
            PathConstraint pathConstraint0 = (PathConstraint)arr_object[v];
            if(pathConstraint0.data.name.equals(s)) {
                return pathConstraint0;
            }
        }
        return null;
    }

    @Null
    public PhysicsConstraint findPhysicsConstraint(String s) {
        if(s == null) {
            throw new IllegalArgumentException("constraintName cannot be null.");
        }
        Object[] arr_object = this.physicsConstraints.items;
        int v1 = this.physicsConstraints.size;
        for(int v = 0; v < v1; ++v) {
            PhysicsConstraint physicsConstraint0 = (PhysicsConstraint)arr_object[v];
            if(physicsConstraint0.data.name.equals(s)) {
                return physicsConstraint0;
            }
        }
        return null;
    }

    @Null
    public Slot findSlot(String s) {
        if(s == null) {
            throw new IllegalArgumentException("slotName cannot be null.");
        }
        Object[] arr_object = this.slots.items;
        int v1 = this.slots.size;
        for(int v = 0; v < v1; ++v) {
            Slot slot0 = (Slot)arr_object[v];
            if(slot0.data.name.equals(s)) {
                return slot0;
            }
        }
        return null;
    }

    @Null
    public TransformConstraint findTransformConstraint(String s) {
        if(s == null) {
            throw new IllegalArgumentException("constraintName cannot be null.");
        }
        Object[] arr_object = this.transformConstraints.items;
        int v1 = this.transformConstraints.size;
        for(int v = 0; v < v1; ++v) {
            TransformConstraint transformConstraint0 = (TransformConstraint)arr_object[v];
            if(transformConstraint0.data.name.equals(s)) {
                return transformConstraint0;
            }
        }
        return null;
    }

    @Null
    public Attachment getAttachment(int v, String s) {
        if(s == null) {
            throw new IllegalArgumentException("attachmentName cannot be null.");
        }
        Skin skin0 = this.skin;
        if(skin0 != null) {
            Attachment attachment0 = skin0.getAttachment(v, s);
            if(attachment0 != null) {
                return attachment0;
            }
        }
        return this.data.defaultSkin == null ? null : this.data.defaultSkin.getAttachment(v, s);
    }

    @Null
    public Attachment getAttachment(String s, String s1) {
        SlotData slotData0 = this.data.findSlot(s);
        if(slotData0 == null) {
            throw new IllegalArgumentException("Slot not found: " + s);
        }
        return this.getAttachment(slotData0.getIndex(), s1);
    }

    public Array getBones() {
        return this.bones;
    }

    public void getBounds(Vector2 vector20, Vector2 vector21, FloatArray floatArray0) {
        int v2;
        float[] arr_f;
        if(vector20 == null) {
            throw new IllegalArgumentException("offset cannot be null.");
        }
        if(vector21 == null) {
            throw new IllegalArgumentException("size cannot be null.");
        }
        if(floatArray0 == null) {
            throw new IllegalArgumentException("temp cannot be null.");
        }
        Object[] arr_object = this.drawOrder.items;
        int v = this.drawOrder.size;
        float f = 2147483648.0f;
        float f1 = 2147483648.0f;
        float f2 = -2147483648.0f;
        float f3 = -2147483648.0f;
        for(int v1 = 0; v1 < v; ++v1) {
            Slot slot0 = (Slot)arr_object[v1];
            if(slot0.bone.active) {
                Attachment attachment0 = slot0.attachment;
                if(attachment0 instanceof RegionAttachment) {
                    arr_f = floatArray0.setSize(8);
                    ((RegionAttachment)attachment0).computeWorldVertices(slot0, arr_f, 0, 2);
                    v2 = 8;
                }
                else if(attachment0 instanceof MeshAttachment) {
                    v2 = ((MeshAttachment)attachment0).getWorldVerticesLength();
                    float[] arr_f1 = floatArray0.setSize(v2);
                    ((MeshAttachment)attachment0).computeWorldVertices(slot0, 0, v2, arr_f1, 0, 2);
                    arr_f = arr_f1;
                }
                else {
                    v2 = 0;
                    arr_f = null;
                }
                if(arr_f != null) {
                    float f4 = f3;
                    float f5 = f2;
                    float f6 = f1;
                    float f7 = f;
                    for(int v3 = 0; v3 < v2; v3 += 2) {
                        float f8 = arr_f[v3];
                        float f9 = arr_f[v3 + 1];
                        f7 = Math.min(f7, f8);
                        f6 = Math.min(f6, f9);
                        f5 = Math.max(f5, f8);
                        f4 = Math.max(f4, f9);
                    }
                    f = f7;
                    f1 = f6;
                    f2 = f5;
                    f3 = f4;
                }
            }
        }
        vector20.set(f, f1);
        vector21.set(f2 - f, f3 - f1);
    }

    public Color getColor() {
        return this.color;
    }

    public SkeletonData getData() {
        return this.data;
    }

    public Array getDrawOrder() {
        return this.drawOrder;
    }

    public Array getIkConstraints() {
        return this.ikConstraints;
    }

    public Array getPathConstraints() {
        return this.pathConstraints;
    }

    public Array getPhysicsConstraints() {
        return this.physicsConstraints;
    }

    public Bone getRootBone() {
        return this.bones.size == 0 ? null : ((Bone)this.bones.first());
    }

    public float getScaleX() {
        return this.scaleX;
    }

    public float getScaleY() {
        return this.scaleY;
    }

    @Null
    public Skin getSkin() {
        return this.skin;
    }

    public Array getSlots() {
        return this.slots;
    }

    public Array getTransformConstraints() {
        return this.transformConstraints;
    }

    public Array getUpdateCache() {
        return this.updateCache;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setAttachment(String s, @Null String s1) {
        if(s == null) {
            throw new IllegalArgumentException("slotName cannot be null.");
        }
        Attachment attachment0 = null;
        Slot slot0 = this.findSlot(s);
        if(slot0 == null) {
            throw new IllegalArgumentException("Slot not found: " + s);
        }
        if(s1 != null) {
            attachment0 = this.getAttachment(slot0.data.index, s1);
            if(attachment0 == null) {
                throw new IllegalArgumentException("Attachment not found: " + s1 + ", for slot: " + s);
            }
        }
        slot0.setAttachment(attachment0);
    }

    public void setBonesToSetupPose() {
        Object[] arr_object = this.bones.items;
        int v = this.bones.size;
        for(int v2 = 0; v2 < v; ++v2) {
            ((Bone)arr_object[v2]).setToSetupPose();
        }
        Object[] arr_object1 = this.ikConstraints.items;
        int v3 = this.ikConstraints.size;
        for(int v4 = 0; v4 < v3; ++v4) {
            ((IkConstraint)arr_object1[v4]).setToSetupPose();
        }
        Object[] arr_object2 = this.transformConstraints.items;
        int v5 = this.transformConstraints.size;
        for(int v6 = 0; v6 < v5; ++v6) {
            ((TransformConstraint)arr_object2[v6]).setToSetupPose();
        }
        Object[] arr_object3 = this.pathConstraints.items;
        int v7 = this.pathConstraints.size;
        for(int v8 = 0; v8 < v7; ++v8) {
            ((PathConstraint)arr_object3[v8]).setToSetupPose();
        }
        Object[] arr_object4 = this.physicsConstraints.items;
        int v9 = this.physicsConstraints.size;
        for(int v1 = 0; v1 < v9; ++v1) {
            ((PhysicsConstraint)arr_object4[v1]).setToSetupPose();
        }
    }

    public void setColor(float f, float f1, float f2, float f3) {
        this.color.set(f, f1, f2, f3);
    }

    public void setColor(Color color0) {
        if(color0 == null) {
            throw new IllegalArgumentException("color cannot be null.");
        }
        this.color.set(color0);
    }

    public void setDrawOrder(Array array0) {
        if(array0 == null) {
            throw new IllegalArgumentException("drawOrder cannot be null.");
        }
        this.drawOrder = array0;
    }

    public void setPosition(float f, float f1) {
        this.x = f;
        this.y = f1;
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

    public void setSkin(@Null Skin skin0) {
        Skin skin1 = this.skin;
        if(skin0 == skin1) {
            return;
        }
        if(skin0 != null) {
            if(skin1 == null) {
                Object[] arr_object = this.slots.items;
                int v1 = this.slots.size;
                for(int v = 0; v < v1; ++v) {
                    Slot slot0 = (Slot)arr_object[v];
                    String s = slot0.data.attachmentName;
                    if(s != null) {
                        Attachment attachment0 = skin0.getAttachment(v, s);
                        if(attachment0 != null) {
                            slot0.setAttachment(attachment0);
                        }
                    }
                }
            }
            else {
                skin0.attachAll(this, skin1);
            }
        }
        this.skin = skin0;
        this.updateCache();
    }

    public void setSkin(String s) {
        Skin skin0 = this.data.findSkin(s);
        if(skin0 == null) {
            throw new IllegalArgumentException("Skin not found: " + s);
        }
        this.setSkin(skin0);
    }

    public void setSlotsToSetupPose() {
        Object[] arr_object = this.slots.items;
        int v = this.slots.size;
        SpineUtils.arraycopy(arr_object, 0, this.drawOrder.items, 0, v);
        for(int v1 = 0; v1 < v; ++v1) {
            ((Slot)arr_object[v1]).setToSetupPose();
        }
    }

    public void setToSetupPose() {
        this.setBonesToSetupPose();
        this.setSlotsToSetupPose();
    }

    public void setX(float f) {
        this.x = f;
    }

    public void setY(float f) {
        this.y = f;
    }

    private void sortBone(Bone bone0) {
        if(bone0.sorted) {
            return;
        }
        Bone bone1 = bone0.parent;
        if(bone1 != null) {
            this.sortBone(bone1);
        }
        bone0.sorted = true;
        this.updateCache.add(bone0);
    }

    private void sortIkConstraint(IkConstraint ikConstraint0) {
        ikConstraint0.active = ikConstraint0.target.active && (!ikConstraint0.data.skinRequired || this.skin != null && this.skin.constraints.contains(ikConstraint0.data, true));
        if(!ikConstraint0.active) {
            return;
        }
        this.sortBone(ikConstraint0.target);
        Array array0 = ikConstraint0.bones;
        Bone bone0 = (Bone)array0.first();
        this.sortBone(bone0);
        if(array0.size == 1) {
            this.updateCache.add(ikConstraint0);
            this.sortReset(bone0.children);
            return;
        }
        Bone bone1 = (Bone)array0.peek();
        this.sortBone(bone1);
        this.updateCache.add(ikConstraint0);
        this.sortReset(bone0.children);
        bone1.sorted = true;
    }

    private void sortPathConstraint(PathConstraint pathConstraint0) {
        pathConstraint0.active = pathConstraint0.target.bone.active && (!pathConstraint0.data.skinRequired || this.skin != null && this.skin.constraints.contains(pathConstraint0.data, true));
        if(!pathConstraint0.active) {
            return;
        }
        Slot slot0 = pathConstraint0.target;
        int v1 = slot0.getData().index;
        Bone bone0 = slot0.bone;
        Skin skin0 = this.skin;
        if(skin0 != null) {
            this.sortPathConstraintAttachment(skin0, v1, bone0);
        }
        if(this.data.defaultSkin != null && this.data.defaultSkin != this.skin) {
            this.sortPathConstraintAttachment(this.data.defaultSkin, v1, bone0);
        }
        Attachment attachment0 = slot0.attachment;
        if(attachment0 instanceof PathAttachment) {
            this.sortPathConstraintAttachment(attachment0, bone0);
        }
        Object[] arr_object = pathConstraint0.bones.items;
        int v2 = pathConstraint0.bones.size;
        for(int v3 = 0; v3 < v2; ++v3) {
            this.sortBone(((Bone)arr_object[v3]));
        }
        this.updateCache.add(pathConstraint0);
        for(int v4 = 0; v4 < v2; ++v4) {
            this.sortReset(((Bone)arr_object[v4]).children);
        }
        for(int v = 0; v < v2; ++v) {
            ((Bone)arr_object[v]).sorted = true;
        }
    }

    private void sortPathConstraintAttachment(Skin skin0, int v, Bone bone0) {
        Object[] arr_object = skin0.attachments.orderedItems().items;
        int v1 = skin0.attachments.size;
        for(int v2 = 0; v2 < v1; ++v2) {
            SkinEntry skin$SkinEntry0 = (SkinEntry)arr_object[v2];
            if(skin$SkinEntry0.slotIndex == v) {
                this.sortPathConstraintAttachment(skin$SkinEntry0.attachment, bone0);
            }
        }
    }

    private void sortPathConstraintAttachment(Attachment attachment0, Bone bone0) {
        if(!(attachment0 instanceof PathAttachment)) {
            return;
        }
        int[] arr_v = ((PathAttachment)attachment0).getBones();
        if(arr_v == null) {
            this.sortBone(bone0);
            return;
        }
        Object[] arr_object = this.bones.items;
        for(int v = 0; v < arr_v.length; v = v1) {
            int v1 = v + 1;
            int v2 = arr_v[v] + v1;
            while(v1 < v2) {
                this.sortBone(((Bone)arr_object[arr_v[v1]]));
                ++v1;
            }
        }
    }

    private void sortPhysicsConstraint(PhysicsConstraint physicsConstraint0) {
        physicsConstraint0.active = !physicsConstraint0.data.skinRequired || this.skin != null && this.skin.constraints.contains(physicsConstraint0.data, true);
        if(!physicsConstraint0.active) {
            return;
        }
        Object[] arr_object = physicsConstraint0.nodes.items;
        int v = physicsConstraint0.nodes.size;
        for(int v1 = 0; v1 < v; ++v1) {
            Node physicsConstraintData$Node0 = (Node)arr_object[v1];
            if(physicsConstraintData$Node0.parentBone != null) {
                this.sortBone(physicsConstraintData$Node0.parentBone);
            }
            Bone[] arr_bone = physicsConstraintData$Node0.bones;
            for(int v2 = 0; v2 < arr_bone.length; ++v2) {
                this.sortBone(arr_bone[v2]);
            }
        }
        this.updateCache.add(physicsConstraint0);
        for(int v3 = 0; v3 < v; ++v3) {
            Node physicsConstraintData$Node1 = (Node)arr_object[v3];
            if(physicsConstraintData$Node1.parentBone != null) {
                this.sortReset(physicsConstraintData$Node1.parentBone.children);
            }
            Bone[] arr_bone1 = physicsConstraintData$Node1.bones;
            for(int v4 = 0; v4 < arr_bone1.length; ++v4) {
                this.sortReset(arr_bone1[v4].children);
            }
        }
        for(int v5 = 0; v5 < v; ++v5) {
            Node physicsConstraintData$Node2 = (Node)arr_object[v5];
            if(physicsConstraintData$Node2.parentBone != null) {
                physicsConstraintData$Node2.parentBone.sorted = true;
            }
            Bone[] arr_bone2 = physicsConstraintData$Node2.bones;
            for(int v6 = 0; v6 < arr_bone2.length; ++v6) {
                arr_bone2[v6].sorted = true;
            }
        }
    }

    private void sortReset(Array array0) {
        Object[] arr_object = array0.items;
        int v = array0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            Bone bone0 = (Bone)arr_object[v1];
            if(bone0.active) {
                if(bone0.sorted) {
                    this.sortReset(bone0.children);
                }
                bone0.sorted = false;
            }
        }
    }

    private void sortTransformConstraint(TransformConstraint transformConstraint0) {
        transformConstraint0.active = transformConstraint0.target.active && (!transformConstraint0.data.skinRequired || this.skin != null && this.skin.constraints.contains(transformConstraint0.data, true));
        if(!transformConstraint0.active) {
            return;
        }
        this.sortBone(transformConstraint0.target);
        Object[] arr_object = transformConstraint0.bones.items;
        int v1 = transformConstraint0.bones.size;
        if(transformConstraint0.data.local) {
            for(int v2 = 0; v2 < v1; ++v2) {
                Bone bone0 = (Bone)arr_object[v2];
                this.sortBone(bone0.parent);
                this.sortBone(bone0);
            }
        }
        else {
            for(int v3 = 0; v3 < v1; ++v3) {
                this.sortBone(((Bone)arr_object[v3]));
            }
        }
        this.updateCache.add(transformConstraint0);
        for(int v4 = 0; v4 < v1; ++v4) {
            this.sortReset(((Bone)arr_object[v4]).children);
        }
        for(int v = 0; v < v1; ++v) {
            ((Bone)arr_object[v]).sorted = true;
        }
    }

    @Override
    public String toString() {
        return this.data.name == null ? super.toString() : this.data.name;
    }

    public void updateCache() {
        this.updateCache.clear();
        int v = this.bones.size;
        Object[] arr_object = this.bones.items;
        for(int v1 = 0; v1 < v; ++v1) {
            Bone bone0 = (Bone)arr_object[v1];
            bone0.sorted = bone0.data.skinRequired;
            bone0.active = true ^ bone0.sorted;
        }
        Skin skin0 = this.skin;
        if(skin0 != null) {
            Object[] arr_object1 = skin0.bones.items;
            int v2 = this.skin.bones.size;
            for(int v3 = 0; v3 < v2; ++v3) {
                Bone bone1 = (Bone)arr_object[((BoneData)arr_object1[v3]).index];
                do {
                    bone1.sorted = false;
                    bone1.active = true;
                    bone1 = bone1.parent;
                }
                while(bone1 != null);
            }
        }
        int v4 = this.ikConstraints.size;
        int v5 = this.transformConstraints.size;
        int v6 = this.pathConstraints.size;
        int v7 = this.physicsConstraints.size;
        Object[] arr_object2 = this.ikConstraints.items;
        Object[] arr_object3 = this.transformConstraints.items;
        Object[] arr_object4 = this.pathConstraints.items;
        Object[] arr_object5 = this.physicsConstraints.items;
    alab1:
        for(int v8 = 0; v8 < v4 + v5 + v6 + v7; ++v8) {
            int v9 = 0;
            while(v9 < v4) {
                IkConstraint ikConstraint0 = (IkConstraint)arr_object2[v9];
                if(ikConstraint0.data.order == v8) {
                    this.sortIkConstraint(ikConstraint0);
                    continue alab1;
                }
                ++v9;
            }
            int v10 = 0;
            while(v10 < v5) {
                TransformConstraint transformConstraint0 = (TransformConstraint)arr_object3[v10];
                if(transformConstraint0.data.order == v8) {
                    this.sortTransformConstraint(transformConstraint0);
                    continue alab1;
                }
                ++v10;
            }
            int v11 = 0;
            while(v11 < v6) {
                PathConstraint pathConstraint0 = (PathConstraint)arr_object4[v11];
                if(pathConstraint0.data.order == v8) {
                    this.sortPathConstraint(pathConstraint0);
                    continue alab1;
                }
                ++v11;
            }
            for(int v12 = 0; v12 < v7; ++v12) {
                PhysicsConstraint physicsConstraint0 = (PhysicsConstraint)arr_object5[v12];
                if(physicsConstraint0.data.order == v8) {
                    this.sortPhysicsConstraint(physicsConstraint0);
                    break;
                }
            }
        }
        for(int v13 = 0; v13 < v; ++v13) {
            this.sortBone(((Bone)arr_object[v13]));
        }
    }

    public void updateWorldTransform() {
        Object[] arr_object = this.bones.items;
        int v = this.bones.size;
        for(int v2 = 0; v2 < v; ++v2) {
            Bone bone0 = (Bone)arr_object[v2];
            bone0.ax = bone0.x;
            bone0.ay = bone0.y;
            bone0.arotation = bone0.rotation;
            bone0.ascaleX = bone0.scaleX;
            bone0.ascaleY = bone0.scaleY;
            bone0.ashearX = bone0.shearX;
            bone0.ashearY = bone0.shearY;
        }
        Object[] arr_object1 = this.updateCache.items;
        int v3 = this.updateCache.size;
        for(int v1 = 0; v1 < v3; ++v1) {
            ((Updatable)arr_object1[v1]).update();
        }
    }

    public void updateWorldTransform(Bone bone0) {
        if(bone0 == null) {
            throw new IllegalArgumentException("parent cannot be null.");
        }
        Bone bone1 = this.getRootBone();
        float f = bone0.a;
        float f1 = bone0.c;
        bone1.worldX = this.x * f + this.y * bone0.b + bone0.worldX;
        bone1.worldY = this.x * f1 + this.y * bone0.d + bone0.worldY;
        float f2 = bone1.rotation + 90.0f + bone1.shearY;
        float f3 = SpineUtils.cosDeg(bone1.rotation + bone1.shearX) * bone1.scaleX;
        float f4 = SpineUtils.cosDeg(f2) * bone1.scaleY;
        float f5 = SpineUtils.sinDeg(bone1.rotation + bone1.shearX) * bone1.scaleX;
        float f6 = SpineUtils.sinDeg(f2) * bone1.scaleY;
        bone1.a = (f * f3 + bone0.b * f5) * this.scaleX;
        bone1.b = (f * f4 + bone0.b * f6) * this.scaleX;
        bone1.c = (f3 * f1 + f5 * bone0.d) * this.scaleY;
        bone1.d = (f1 * f4 + bone0.d * f6) * this.scaleY;
        Object[] arr_object = this.updateCache.items;
        int v1 = this.updateCache.size;
        for(int v = 0; v < v1; ++v) {
            Updatable updatable0 = (Updatable)arr_object[v];
            if(updatable0 != bone1) {
                updatable0.update();
            }
        }
    }
}

