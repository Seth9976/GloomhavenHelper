package com.esotericsoftware.spine.attachments;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.spine.SlotData;

public class ClippingAttachment extends VertexAttachment {
    final Color color;
    @Null
    SlotData endSlot;

    protected ClippingAttachment(ClippingAttachment clippingAttachment0) {
        super(clippingAttachment0);
        this.color = new Color(0.2275f, 0.2275f, 0.8078f, 1.0f);
        this.endSlot = clippingAttachment0.endSlot;
        this.color.set(clippingAttachment0.color);
    }

    public ClippingAttachment(String s) {
        super(s);
        this.color = new Color(0.2275f, 0.2275f, 0.8078f, 1.0f);
    }

    @Override  // com.esotericsoftware.spine.attachments.Attachment
    public Attachment copy() {
        return this.copy();
    }

    public ClippingAttachment copy() {
        return new ClippingAttachment(this);
    }

    public Color getColor() {
        return this.color;
    }

    @Null
    public SlotData getEndSlot() {
        return this.endSlot;
    }

    public void setEndSlot(@Null SlotData slotData0) {
        this.endSlot = slotData0;
    }
}

