package com.esotericsoftware.spine.attachments;

import com.badlogic.gdx.graphics.Color;
import com.esotericsoftware.spine.utils.SpineUtils;

public class PathAttachment extends VertexAttachment {
    boolean closed;
    final Color color;
    boolean constantSpeed;
    float[] lengths;

    protected PathAttachment(PathAttachment pathAttachment0) {
        super(pathAttachment0);
        this.color = new Color(1.0f, 0.5f, 0.0f, 1.0f);
        this.lengths = new float[pathAttachment0.lengths.length];
        SpineUtils.arraycopy(pathAttachment0.lengths, 0, this.lengths, 0, this.lengths.length);
        this.closed = pathAttachment0.closed;
        this.constantSpeed = pathAttachment0.constantSpeed;
        this.color.set(pathAttachment0.color);
    }

    public PathAttachment(String s) {
        super(s);
        this.color = new Color(1.0f, 0.5f, 0.0f, 1.0f);
    }

    @Override  // com.esotericsoftware.spine.attachments.Attachment
    public Attachment copy() {
        return this.copy();
    }

    public PathAttachment copy() {
        return new PathAttachment(this);
    }

    public boolean getClosed() {
        return this.closed;
    }

    public Color getColor() {
        return this.color;
    }

    public boolean getConstantSpeed() {
        return this.constantSpeed;
    }

    public float[] getLengths() {
        return this.lengths;
    }

    public void setClosed(boolean z) {
        this.closed = z;
    }

    public void setConstantSpeed(boolean z) {
        this.constantSpeed = z;
    }

    public void setLengths(float[] arr_f) {
        this.lengths = arr_f;
    }
}

