package com.esotericsoftware.spine;

import com.badlogic.gdx.utils.Array;

public class PhysicsConstraint implements Updatable {
    boolean active;
    float damping;
    final PhysicsConstraintData data;
    float gravity;
    float length;
    float mix;
    final Array nodes;
    final Array springs;
    float strength;
    float wind;

    public PhysicsConstraint(PhysicsConstraint physicsConstraint0, Skeleton skeleton0) {
        if(physicsConstraint0 == null) {
            throw new IllegalArgumentException("constraint cannot be null.");
        }
        if(skeleton0 == null) {
            throw new IllegalArgumentException("skeleton cannot be null.");
        }
        this.data = physicsConstraint0.data;
        this.nodes = new Array(physicsConstraint0.nodes.size);
        for(Object object0: physicsConstraint0.nodes) {
            Node physicsConstraintData$Node0 = new Node(((Node)object0));
            this.nodes.add(physicsConstraintData$Node0);
        }
        this.springs = new Array(physicsConstraint0.springs.size);
        for(Object object1: physicsConstraint0.springs) {
            Spring physicsConstraintData$Spring0 = new Spring(((Spring)object1), this);
            this.springs.add(physicsConstraintData$Spring0);
        }
        this.mix = physicsConstraint0.mix;
        this.length = physicsConstraint0.length;
        this.strength = physicsConstraint0.strength;
        this.damping = physicsConstraint0.damping;
        this.gravity = physicsConstraint0.gravity;
        this.wind = physicsConstraint0.wind;
    }

    public PhysicsConstraint(PhysicsConstraintData physicsConstraintData0, Skeleton skeleton0) {
        if(physicsConstraintData0 == null) {
            throw new IllegalArgumentException("data cannot be null.");
        }
        if(skeleton0 == null) {
            throw new IllegalArgumentException("skeleton cannot be null.");
        }
        this.data = physicsConstraintData0;
        this.nodes = new Array(physicsConstraintData0.nodes.size);
        for(Object object0: physicsConstraintData0.nodes) {
            Node physicsConstraintData$Node0 = new Node(((NodeData)object0), skeleton0);
            this.nodes.add(physicsConstraintData$Node0);
        }
        this.springs = new Array(physicsConstraintData0.springs.size);
        for(Object object1: physicsConstraintData0.springs) {
            Spring physicsConstraintData$Spring0 = new Spring(((SpringData)object1), this, skeleton0);
            this.springs.add(physicsConstraintData$Spring0);
        }
        this.mix = physicsConstraintData0.mix;
        this.length = physicsConstraintData0.length;
        this.strength = physicsConstraintData0.strength;
        this.damping = physicsConstraintData0.damping;
        this.gravity = physicsConstraintData0.gravity;
        this.wind = physicsConstraintData0.wind;
    }

    public float getDamping() {
        return this.damping;
    }

    public PhysicsConstraintData getData() {
        return this.data;
    }

    public float getGravity() {
        return this.gravity;
    }

    public float getLength() {
        return this.length;
    }

    public float getMix() {
        return this.mix;
    }

    public Array getNodes() {
        return this.nodes;
    }

    public Array getSprings() {
        return this.springs;
    }

    public float getStrength() {
        return this.strength;
    }

    public float getWind() {
        return this.wind;
    }

    @Override  // com.esotericsoftware.spine.Updatable
    public boolean isActive() {
        return this.active;
    }

    public void setDamping(float f) {
        this.damping = f;
    }

    public void setGravity(float f) {
        this.gravity = f;
    }

    public void setLength(float f) {
        this.length = f;
    }

    public void setMix(float f) {
        this.mix = f;
    }

    public void setStrength(float f) {
        this.strength = f;
    }

    public void setToSetupPose() {
        Object[] arr_object = this.nodes.items;
        int v = this.nodes.size;
        for(int v2 = 0; v2 < v; ++v2) {
            ((Node)arr_object[v2]).setToSetupPose();
        }
        Object[] arr_object1 = this.springs.items;
        int v3 = this.springs.size;
        for(int v1 = 0; v1 < v3; ++v1) {
            ((Spring)arr_object1[v1]).setToSetupPose();
        }
        this.mix = this.data.mix;
        this.length = this.data.length;
        this.strength = this.data.strength;
        this.damping = this.data.damping;
        this.gravity = this.data.gravity;
        this.wind = this.data.wind;
    }

    public void setWind(float f) {
        this.wind = f;
    }

    @Override
    public String toString() {
        return this.data.name;
    }

    @Override  // com.esotericsoftware.spine.Updatable
    public void update() {
    }
}

