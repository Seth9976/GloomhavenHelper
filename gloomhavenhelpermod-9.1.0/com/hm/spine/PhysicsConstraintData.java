package com.hm.spine;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.hm.spine.utils.SpineUtils;

public class PhysicsConstraintData extends ConstraintData {
   final Array nodes = new Array();
   final Array springs = new Array();
   float mix;
   float length;
   float strength;
   float damping;
   float gravity;
   float wind;

   public PhysicsConstraintData(String name) {
      super(name);
   }

   public Array getNodes() {
      return this.nodes;
   }

   public Array getSprings() {
      return this.springs;
   }

   public float getMix() {
      return this.mix;
   }

   public void setMix(float mix) {
      this.mix = mix;
   }

   public float getLength() {
      return this.length;
   }

   public void setLength(float length) {
      this.length = length;
   }

   public float getStrength() {
      return this.strength;
   }

   public void setStrength(float strength) {
      this.strength = strength;
   }

   public float getDamping() {
      return this.damping;
   }

   public void setDamping(float damping) {
      this.damping = damping;
   }

   public float getGravity() {
      return this.gravity;
   }

   public void setGravity(float gravity) {
      this.gravity = gravity;
   }

   public float getWind() {
      return this.wind;
   }

   public void setWind(float wind) {
      this.wind = wind;
   }

   public static class Node {
      public final PhysicsConstraintData.NodeData data;
      @Null
      public final Bone parentBone;
      public final Bone[] bones;
      public float x;
      public float y;
      public float px;
      public float py;
      public float ax;
      public float ay;

      public Node(PhysicsConstraintData.NodeData data, Skeleton skeleton) {
         this.data = data;
         this.parentBone = data.parentBone == -1 ? null : (Bone)skeleton.bones.get(data.parentBone);
         this.bones = new Bone[data.bones.length];
         int i = 0;

         for (int n = this.bones.length; i < n; i++) {
            this.bones[i] = (Bone)skeleton.bones.get(data.bones[i]);
         }

         this.setToSetupPose();
      }

      public Node(PhysicsConstraintData.Node node) {
         this.data = node.data;
         this.parentBone = node.parentBone;
         this.bones = new Bone[node.bones.length];
         SpineUtils.arraycopy(node.bones, 0, this.bones, 0, this.bones.length);
         this.x = node.x;
         this.y = node.y;
         this.px = node.px;
         this.py = node.py;
         this.ax = node.ax;
         this.ay = node.ay;
      }

      public void setToSetupPose() {
         this.x = this.data.x;
         this.y = this.data.y;
         this.px = this.x;
         this.py = this.y;
         this.ax = 0.0F;
         this.ay = 0.0F;
      }
   }

   public static class NodeData {
      public int parentBone = -1;
      public int[] bones;
      public float x;
      public float y;
   }

   public static class Spring {
      public final PhysicsConstraintData.SpringData data;
      public final PhysicsConstraintData.Node node1;
      public final PhysicsConstraintData.Node node2;
      public final Bone[] bones;
      public float length;
      public float strength;
      public float damping;

      public Spring(PhysicsConstraintData.SpringData data, PhysicsConstraint constraint, Skeleton skeleton) {
         this.data = data;
         this.node1 = (PhysicsConstraintData.Node)constraint.nodes.get(data.node1);
         this.node2 = (PhysicsConstraintData.Node)constraint.nodes.get(data.node2);
         this.bones = new Bone[data.bones.length];
         int i = 0;

         for (int n = this.bones.length; i < n; i++) {
            this.bones[i] = (Bone)skeleton.bones.get(data.bones[i]);
         }

         this.setToSetupPose();
      }

      public Spring(PhysicsConstraintData.Spring spring, PhysicsConstraint constraint) {
         this.data = spring.data;
         this.node1 = (PhysicsConstraintData.Node)constraint.nodes.get(this.data.node1);
         this.node2 = (PhysicsConstraintData.Node)constraint.nodes.get(this.data.node2);
         this.bones = new Bone[spring.bones.length];
         SpineUtils.arraycopy(spring.bones, 0, this.bones, 0, this.bones.length);
         this.length = spring.length;
         this.strength = spring.strength;
         this.damping = spring.damping;
      }

      public void setToSetupPose() {
         this.length = this.data.length;
         this.strength = this.data.strength;
         this.damping = this.data.damping;
      }
   }

   public static class SpringData {
      public int node1;
      public int node2;
      public int[] bones;
      public float length;
      public float strength;
      public float damping;
      public boolean rope;
      public boolean stretch;
   }
}
