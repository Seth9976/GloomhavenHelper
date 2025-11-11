package com.esotericsoftware.spine.attachments;

import com.badlogic.gdx.graphics.Color;

public class BoundingBoxAttachment extends VertexAttachment {
    final Color color;

    protected BoundingBoxAttachment(BoundingBoxAttachment boundingBoxAttachment0) {
        super(boundingBoxAttachment0);
        this.color = new Color(0.38f, 0.94f, 0.0f, 1.0f);
        this.color.set(boundingBoxAttachment0.color);
    }

    public BoundingBoxAttachment(String s) {
        super(s);
        this.color = new Color(0.38f, 0.94f, 0.0f, 1.0f);
    }

    @Override  // com.esotericsoftware.spine.attachments.Attachment
    public Attachment copy() {
        return this.copy();
    }

    public BoundingBoxAttachment copy() {
        return new BoundingBoxAttachment(this);
    }

    public Color getColor() {
        return this.color;
    }
}

