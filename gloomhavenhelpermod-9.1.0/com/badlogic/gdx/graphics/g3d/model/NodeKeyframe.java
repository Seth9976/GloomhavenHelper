package com.badlogic.gdx.graphics.g3d.model;

public class NodeKeyframe {
   public float keytime;
   public final Object value;

   public NodeKeyframe(float t, Object v) {
      this.keytime = t;
      this.value = v;
   }
}
