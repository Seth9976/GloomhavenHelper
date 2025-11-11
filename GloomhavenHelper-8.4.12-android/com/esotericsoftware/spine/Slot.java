package com.esotericsoftware.spine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.spine.attachments.Attachment;
import com.esotericsoftware.spine.attachments.VertexAttachment;

public class Slot {
    @Null
    Attachment attachment;
    int attachmentState;
    final Bone bone;
    final Color color;
    @Null
    final Color darkColor;
    final SlotData data;
    FloatArray deform;
    int sequenceIndex;

    public Slot(Slot slot0, Bone bone0) {
        this.color = new Color();
        this.deform = new FloatArray();
        if(slot0 == null) {
            throw new IllegalArgumentException("slot cannot be null.");
        }
        if(bone0 == null) {
            throw new IllegalArgumentException("bone cannot be null.");
        }
        this.data = slot0.data;
        this.bone = bone0;
        this.color.set(slot0.color);
        this.darkColor = slot0.darkColor == null ? null : new Color(slot0.darkColor);
        this.attachment = slot0.attachment;
        this.sequenceIndex = slot0.sequenceIndex;
        this.deform.addAll(slot0.deform);
    }

    public Slot(SlotData slotData0, Bone bone0) {
        this.color = new Color();
        this.deform = new FloatArray();
        if(slotData0 == null) {
            throw new IllegalArgumentException("data cannot be null.");
        }
        if(bone0 == null) {
            throw new IllegalArgumentException("bone cannot be null.");
        }
        this.data = slotData0;
        this.bone = bone0;
        this.darkColor = slotData0.darkColor == null ? null : new Color();
        this.setToSetupPose();
    }

    @Null
    public Attachment getAttachment() {
        return this.attachment;
    }

    public Bone getBone() {
        return this.bone;
    }

    public Color getColor() {
        return this.color;
    }

    @Null
    public Color getDarkColor() {
        return this.darkColor;
    }

    public SlotData getData() {
        return this.data;
    }

    public FloatArray getDeform() {
        return this.deform;
    }

    public int getSequenceIndex() {
        return this.sequenceIndex;
    }

    public Skeleton getSkeleton() {
        return this.bone.skeleton;
    }

    public void setAttachment(@Null Attachment attachment0) {
        Attachment attachment1 = this.attachment;
        if(attachment1 == attachment0) {
            return;
        }
        if(!(attachment0 instanceof VertexAttachment) || !(attachment1 instanceof VertexAttachment) || ((VertexAttachment)attachment0).getTimelineAttachment() != ((VertexAttachment)this.attachment).getTimelineAttachment()) {
            this.deform.clear();
        }
        this.attachment = attachment0;
        this.sequenceIndex = -1;
    }

    public void setDeform(FloatArray floatArray0) {
        if(floatArray0 == null) {
            throw new IllegalArgumentException("deform cannot be null.");
        }
        this.deform = floatArray0;
    }

    public void setSequenceIndex(int v) {
        this.sequenceIndex = v;
    }

    public void setToSetupPose() {
        this.color.set(this.data.color);
        Color color0 = this.darkColor;
        if(color0 != null) {
            color0.set(this.data.darkColor);
        }
        if(this.data.attachmentName == null) {
            this.setAttachment(null);
            return;
        }
        this.attachment = null;
        this.setAttachment(this.bone.skeleton.getAttachment(this.data.index, this.data.attachmentName));
    }

    @Override
    public String toString() {
        return this.data.name;
    }
}

