package com.esotericsoftware.spine.attachments;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.spine.Bone;

public class PointAttachment extends Attachment {
    final Color color;
    float rotation;
    float x;
    float y;

    protected PointAttachment(PointAttachment pointAttachment0) {
        super(pointAttachment0);
        this.color = new Color(0.9451f, 0.9451f, 0.0f, 1.0f);
        this.x = pointAttachment0.x;
        this.y = pointAttachment0.y;
        this.rotation = pointAttachment0.rotation;
        this.color.set(pointAttachment0.color);
    }

    public PointAttachment(String s) {
        super(s);
        this.color = new Color(0.9451f, 0.9451f, 0.0f, 1.0f);
    }

    public Vector2 computeWorldPosition(Bone bone0, Vector2 vector20) {
        vector20.x = this.x * bone0.getA() + this.y * bone0.getB() + bone0.getWorldX();
        vector20.y = this.x * bone0.getC() + this.y * bone0.getD() + bone0.getWorldY();
        return vector20;
    }

    public float computeWorldRotation(Bone bone0) {
        float f = MathUtils.cosDeg(this.rotation);
        float f1 = MathUtils.sinDeg(this.rotation);
        return ((float)Math.atan2(f * bone0.getC() + f1 * bone0.getD(), bone0.getA() * f + bone0.getB() * f1)) * 57.295776f;
    }

    @Override  // com.esotericsoftware.spine.attachments.Attachment
    public Attachment copy() {
        return this.copy();
    }

    public PointAttachment copy() {
        return new PointAttachment(this);
    }

    public Color getColor() {
        return this.color;
    }

    public float getRotation() {
        return this.rotation;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setRotation(float f) {
        this.rotation = f;
    }

    public void setX(float f) {
        this.x = f;
    }

    public void setY(float f) {
        this.y = f;
    }
}

