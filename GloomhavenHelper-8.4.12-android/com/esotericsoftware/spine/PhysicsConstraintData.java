package com.esotericsoftware.spine;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.spine.utils.SpineUtils;

public class PhysicsConstraintData extends ConstraintData {
    public static class Node {
        public float ax;
        public float ay;
        public final Bone[] bones;
        public final NodeData data;
        @Null
        public final Bone parentBone;
        public float px;
        public float py;
        public float x;
        public float y;

        public Node(Node physicsConstraintData$Node0) {
            this.data = physicsConstraintData$Node0.data;
            this.parentBone = physicsConstraintData$Node0.parentBone;
            this.bones = new Bone[physicsConstraintData$Node0.bones.length];
            SpineUtils.arraycopy(physicsConstraintData$Node0.bones, 0, this.bones, 0, this.bones.length);
            this.x = physicsConstraintData$Node0.x;
            this.y = physicsConstraintData$Node0.y;
            this.px = physicsConstraintData$Node0.px;
            this.py = physicsConstraintData$Node0.py;
            this.ax = physicsConstraintData$Node0.ax;
            this.ay = physicsConstraintData$Node0.ay;
        }

        public Node(NodeData physicsConstraintData$NodeData0, Skeleton skeleton0) {
            this.data = physicsConstraintData$NodeData0;
            this.parentBone = physicsConstraintData$NodeData0.parentBone == -1 ? null : ((Bone)skeleton0.bones.get(physicsConstraintData$NodeData0.parentBone));
            this.bones = new Bone[physicsConstraintData$NodeData0.bones.length];
            for(int v = 0; v < this.bones.length; ++v) {
                this.bones[v] = (Bone)skeleton0.bones.get(physicsConstraintData$NodeData0.bones[v]);
            }
            this.setToSetupPose();
        }

        public void setToSetupPose() {
            this.x = this.data.x;
            this.y = this.data.y;
            this.px = this.x;
            this.py = this.y;
            this.ax = 0.0f;
            this.ay = 0.0f;
        }
    }

    public static class NodeData {
        public int[] bones;
        public int parentBone;
        public float x;
        public float y;

        public NodeData() {
            this.parentBone = -1;
        }
    }

    public static class Spring {
        public final Bone[] bones;
        public float damping;
        public final SpringData data;
        public float length;
        public final Node node1;
        public final Node node2;
        public float strength;

        public Spring(Spring physicsConstraintData$Spring0, PhysicsConstraint physicsConstraint0) {
            this.data = physicsConstraintData$Spring0.data;
            this.node1 = (Node)physicsConstraint0.nodes.get(this.data.node1);
            this.node2 = (Node)physicsConstraint0.nodes.get(this.data.node2);
            this.bones = new Bone[physicsConstraintData$Spring0.bones.length];
            SpineUtils.arraycopy(physicsConstraintData$Spring0.bones, 0, this.bones, 0, this.bones.length);
            this.length = physicsConstraintData$Spring0.length;
            this.strength = physicsConstraintData$Spring0.strength;
            this.damping = physicsConstraintData$Spring0.damping;
        }

        public Spring(SpringData physicsConstraintData$SpringData0, PhysicsConstraint physicsConstraint0, Skeleton skeleton0) {
            this.data = physicsConstraintData$SpringData0;
            this.node1 = (Node)physicsConstraint0.nodes.get(physicsConstraintData$SpringData0.node1);
            this.node2 = (Node)physicsConstraint0.nodes.get(physicsConstraintData$SpringData0.node2);
            this.bones = new Bone[physicsConstraintData$SpringData0.bones.length];
            for(int v = 0; v < this.bones.length; ++v) {
                this.bones[v] = (Bone)skeleton0.bones.get(physicsConstraintData$SpringData0.bones[v]);
            }
            this.setToSetupPose();
        }

        public void setToSetupPose() {
            this.length = this.data.length;
            this.strength = this.data.strength;
            this.damping = this.data.damping;
        }
    }

    public static class SpringData {
        public int[] bones;
        public float damping;
        public float length;
        public int node1;
        public int node2;
        public boolean rope;
        public float strength;
        public boolean stretch;

    }

    float damping;
    float gravity;
    float length;
    float mix;
    final Array nodes;
    final Array springs;
    float strength;
    float wind;

    public PhysicsConstraintData(String s) {
        super(s);
        this.nodes = new Array();
        this.springs = new Array();
    }

    public float getDamping() {
        return this.damping;
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

    public void setWind(float f) {
        this.wind = f;
    }
}

