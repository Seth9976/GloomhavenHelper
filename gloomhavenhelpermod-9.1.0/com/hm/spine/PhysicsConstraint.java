package com.hm.spine;

import com.badlogic.gdx.utils.Array;

public class PhysicsConstraint implements Updatable {
   final PhysicsConstraintData data;
   final Array nodes;
   final Array springs;
   float mix;
   float length;
   float strength;
   float damping;
   float gravity;
   float wind;
   boolean active;

   public PhysicsConstraint(PhysicsConstraintData data, Skeleton skeleton) {
      if (data == null) {
         throw new IllegalArgumentException("data cannot be null.");
      } else if (skeleton == null) {
         throw new IllegalArgumentException("skeleton cannot be null.");
      } else {
         this.data = data;
         this.nodes = new Array(data.nodes.size);

         for (PhysicsConstraintData.NodeData nodeData : data.nodes) {
            this.nodes.add(new PhysicsConstraintData.Node(nodeData, skeleton));
         }

         this.springs = new Array(data.springs.size);

         for (PhysicsConstraintData.SpringData springData : data.springs) {
            this.springs.add(new PhysicsConstraintData.Spring(springData, this, skeleton));
         }

         this.mix = data.mix;
         this.length = data.length;
         this.strength = data.strength;
         this.damping = data.damping;
         this.gravity = data.gravity;
         this.wind = data.wind;
      }
   }

   public PhysicsConstraint(PhysicsConstraint constraint, Skeleton skeleton) {
      if (constraint == null) {
         throw new IllegalArgumentException("constraint cannot be null.");
      } else if (skeleton == null) {
         throw new IllegalArgumentException("skeleton cannot be null.");
      } else {
         this.data = constraint.data;
         this.nodes = new Array(constraint.nodes.size);

         for (PhysicsConstraintData.Node node : constraint.nodes) {
            this.nodes.add(new PhysicsConstraintData.Node(node));
         }

         this.springs = new Array(constraint.springs.size);

         for (PhysicsConstraintData.Spring spring : constraint.springs) {
            this.springs.add(new PhysicsConstraintData.Spring(spring, this));
         }

         this.mix = constraint.mix;
         this.length = constraint.length;
         this.strength = constraint.strength;
         this.damping = constraint.damping;
         this.gravity = constraint.gravity;
         this.wind = constraint.wind;
      }
   }

   public void setToSetupPose() {
      Object[] nodes = this.nodes.items;
      int i = 0;

      for (int n = this.nodes.size; i < n; i++) {
         ((PhysicsConstraintData.Node)nodes[i]).setToSetupPose();
      }

      Object[] springs = this.springs.items;
      int j = 0;

      for (int k = this.springs.size; j < k; j++) {
         ((PhysicsConstraintData.Spring)springs[j]).setToSetupPose();
      }

      PhysicsConstraintData data = this.data;
      this.mix = data.mix;
      this.length = data.length;
      this.strength = data.strength;
      this.damping = data.damping;
      this.gravity = data.gravity;
      this.wind = data.wind;
   }

   @Override
   public void update() {
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

   @Override
   public boolean isActive() {
      return this.active;
   }

   public PhysicsConstraintData getData() {
      return this.data;
   }

   @Override
   public String toString() {
      return this.data.name;
   }
}
