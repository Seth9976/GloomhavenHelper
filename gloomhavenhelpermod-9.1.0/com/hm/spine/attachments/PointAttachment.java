package com.hm.spine.attachments;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.hm.spine.Bone;

public class PointAttachment extends Attachment {
   float x;
   float y;
   float rotation;
   final Color color = new Color(0.9451F, 0.9451F, 0.0F, 1.0F);

   public PointAttachment(String name) {
      super(name);
   }

   protected PointAttachment(PointAttachment other) {
      super(other);
      this.x = other.x;
      this.y = other.y;
      this.rotation = other.rotation;
      this.color.set(other.color);
   }

   public float getX() {
      return this.x;
   }

   public void setX(float x) {
      this.x = x;
   }

   public float getY() {
      return this.y;
   }

   public void setY(float y) {
      this.y = y;
   }

   public float getRotation() {
      return this.rotation;
   }

   public void setRotation(float rotation) {
      this.rotation = rotation;
   }

   public Color getColor() {
      return this.color;
   }

   public Vector2 computeWorldPosition(Bone bone, Vector2 point) {
      point.x = this.x * bone.getA() + this.y * bone.getB() + bone.getWorldX();
      point.y = this.x * bone.getC() + this.y * bone.getD() + bone.getWorldY();
      return point;
   }

   public float computeWorldRotation(Bone bone) {
      float cos = MathUtils.cosDeg(this.rotation);
      float sin = MathUtils.sinDeg(this.rotation);
      float x = cos * bone.getA() + sin * bone.getB();
      float y = cos * bone.getC() + sin * bone.getD();
      return (float)Math.atan2(y, x) * (180.0F / (float)Math.PI);
   }

   public PointAttachment copy() {
      return new PointAttachment(this);
   }
}
