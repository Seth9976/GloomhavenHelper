package com.esotericsoftware.spine;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.OrderedSet;
import com.esotericsoftware.spine.attachments.Attachment;
import com.esotericsoftware.spine.attachments.MeshAttachment;

public class Skin {
    public static class SkinEntry {
        @Null
        Attachment attachment;
        private int hashCode;
        String name;
        int slotIndex;

        SkinEntry(int v, String s, @Null Attachment attachment0) {
            this.set(v, s);
            this.attachment = attachment0;
        }

        @Override
        public boolean equals(Object object0) {
            if(object0 == null) {
                return false;
            }
            return this.slotIndex == ((SkinEntry)object0).slotIndex ? this.name.equals(((SkinEntry)object0).name) : false;
        }

        public Attachment getAttachment() {
            return this.attachment;
        }

        public String getName() {
            return this.name;
        }

        public int getSlotIndex() {
            return this.slotIndex;
        }

        @Override
        public int hashCode() {
            return this.hashCode;
        }

        void set(int v, String s) {
            if(v < 0) {
                throw new IllegalArgumentException("slotIndex must be >= 0.");
            }
            if(s == null) {
                throw new IllegalArgumentException("name cannot be null.");
            }
            this.slotIndex = v;
            this.name = s;
            this.hashCode = s.hashCode() + v * 37;
        }

        @Override
        public String toString() {
            return this.slotIndex + ":" + this.name;
        }
    }

    final OrderedSet attachments;
    final Array bones;
    final Array constraints;
    private final SkinEntry lookup;
    final String name;

    public Skin(String s) {
        this.attachments = new OrderedSet();
        this.bones = new Array(0);
        this.constraints = new Array(0);
        this.lookup = new SkinEntry(0, "", null);
        if(s == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        this.name = s;
        this.attachments.orderedItems().ordered = false;
    }

    public void addSkin(Skin skin0) {
        if(skin0 == null) {
            throw new IllegalArgumentException("skin cannot be null.");
        }
        for(Object object0: skin0.bones) {
            BoneData boneData0 = (BoneData)object0;
            if(!this.bones.contains(boneData0, true)) {
                this.bones.add(boneData0);
            }
        }
        for(Object object1: skin0.constraints) {
            ConstraintData constraintData0 = (ConstraintData)object1;
            if(!this.constraints.contains(constraintData0, true)) {
                this.constraints.add(constraintData0);
            }
        }
        for(Object object2: skin0.attachments.orderedItems()) {
            this.setAttachment(((SkinEntry)object2).slotIndex, ((SkinEntry)object2).name, ((SkinEntry)object2).attachment);
        }
    }

    void attachAll(Skeleton skeleton0, Skin skin0) {
        Object[] arr_object = skeleton0.slots.items;
        for(Object object0: skin0.attachments.orderedItems()) {
            SkinEntry skin$SkinEntry0 = (SkinEntry)object0;
            int v = skin$SkinEntry0.slotIndex;
            Slot slot0 = (Slot)arr_object[v];
            if(slot0.attachment == skin$SkinEntry0.attachment) {
                Attachment attachment0 = this.getAttachment(v, skin$SkinEntry0.name);
                if(attachment0 != null) {
                    slot0.setAttachment(attachment0);
                }
            }
        }
    }

    public void clear() {
        this.attachments.clear(0x400);
        this.bones.clear();
        this.constraints.clear();
    }

    public void copySkin(Skin skin0) {
        if(skin0 == null) {
            throw new IllegalArgumentException("skin cannot be null.");
        }
        for(Object object0: skin0.bones) {
            BoneData boneData0 = (BoneData)object0;
            if(!this.bones.contains(boneData0, true)) {
                this.bones.add(boneData0);
            }
        }
        for(Object object1: skin0.constraints) {
            ConstraintData constraintData0 = (ConstraintData)object1;
            if(!this.constraints.contains(constraintData0, true)) {
                this.constraints.add(constraintData0);
            }
        }
        for(Object object2: skin0.attachments.orderedItems()) {
            SkinEntry skin$SkinEntry0 = (SkinEntry)object2;
            if(skin$SkinEntry0.attachment instanceof MeshAttachment) {
                this.setAttachment(skin$SkinEntry0.slotIndex, skin$SkinEntry0.name, ((MeshAttachment)skin$SkinEntry0.attachment).newLinkedMesh());
            }
            else {
                this.setAttachment(skin$SkinEntry0.slotIndex, skin$SkinEntry0.name, (skin$SkinEntry0.attachment == null ? null : skin$SkinEntry0.attachment.copy()));
            }
        }
    }

    @Null
    public Attachment getAttachment(int v, String s) {
        this.lookup.set(v, s);
        SkinEntry skin$SkinEntry0 = (SkinEntry)this.attachments.get(this.lookup);
        return skin$SkinEntry0 == null ? null : skin$SkinEntry0.attachment;
    }

    public Array getAttachments() {
        return this.attachments.orderedItems();
    }

    public void getAttachments(int v, Array array0) {
        if(v < 0) {
            throw new IllegalArgumentException("slotIndex must be >= 0.");
        }
        if(array0 == null) {
            throw new IllegalArgumentException("attachments cannot be null.");
        }
        for(Object object0: this.attachments.orderedItems()) {
            SkinEntry skin$SkinEntry0 = (SkinEntry)object0;
            if(skin$SkinEntry0.slotIndex == v) {
                array0.add(skin$SkinEntry0);
            }
        }
    }

    public Array getBones() {
        return this.bones;
    }

    public Array getConstraints() {
        return this.constraints;
    }

    public String getName() {
        return this.name;
    }

    public void removeAttachment(int v, String s) {
        this.lookup.set(v, s);
        this.attachments.remove(this.lookup);
    }

    public void setAttachment(int v, String s, Attachment attachment0) {
        if(attachment0 == null) {
            throw new IllegalArgumentException("attachment cannot be null.");
        }
        SkinEntry skin$SkinEntry0 = new SkinEntry(v, s, attachment0);
        if(!this.attachments.add(skin$SkinEntry0)) {
            ((SkinEntry)this.attachments.get(skin$SkinEntry0)).attachment = attachment0;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}

