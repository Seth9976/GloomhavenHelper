package com.badlogic.gdx.graphics.g3d.model;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class Node {
    private final Array children;
    public final Matrix4 globalTransform;
    public String id;
    public boolean inheritTransform;
    public boolean isAnimated;
    public final Matrix4 localTransform;
    protected Node parent;
    public Array parts;
    public final Quaternion rotation;
    public final Vector3 scale;
    public final Vector3 translation;

    public Node() {
        this.inheritTransform = true;
        this.translation = new Vector3();
        this.rotation = new Quaternion(0.0f, 0.0f, 0.0f, 1.0f);
        this.scale = new Vector3(1.0f, 1.0f, 1.0f);
        this.localTransform = new Matrix4();
        this.globalTransform = new Matrix4();
        this.parts = new Array(2);
        this.children = new Array(2);
    }

    public int addChild(Node node0) {
        return this.insertChild(-1, node0);
    }

    public int addChildren(Iterable iterable0) {
        return this.insertChildren(-1, iterable0);
    }

    public void attachTo(Node node0) {
        node0.addChild(this);
    }

    public void calculateBoneTransforms(boolean z) {
        for(Object object0: this.parts) {
            NodePart nodePart0 = (NodePart)object0;
            if(nodePart0.invBoneBindTransforms != null && nodePart0.bones != null && nodePart0.invBoneBindTransforms.size == nodePart0.bones.length) {
                int v = nodePart0.invBoneBindTransforms.size;
                for(int v1 = 0; v1 < v; ++v1) {
                    nodePart0.bones[v1].set(((Node[])nodePart0.invBoneBindTransforms.keys)[v1].globalTransform).mul(((Matrix4[])nodePart0.invBoneBindTransforms.values)[v1]);
                }
            }
        }
        if(z) {
            for(Object object1: this.children) {
                ((Node)object1).calculateBoneTransforms(true);
            }
        }
    }

    public BoundingBox calculateBoundingBox(BoundingBox boundingBox0) {
        boundingBox0.inf();
        return this.extendBoundingBox(boundingBox0);
    }

    public BoundingBox calculateBoundingBox(BoundingBox boundingBox0, boolean z) {
        boundingBox0.inf();
        return this.extendBoundingBox(boundingBox0, z);
    }

    public Matrix4 calculateLocalTransform() {
        if(!this.isAnimated) {
            this.localTransform.set(this.translation, this.rotation, this.scale);
        }
        return this.localTransform;
    }

    public void calculateTransforms(boolean z) {
        this.calculateLocalTransform();
        this.calculateWorldTransform();
        if(z) {
            for(Object object0: this.children) {
                ((Node)object0).calculateTransforms(true);
            }
        }
    }

    public Matrix4 calculateWorldTransform() {
        if(this.inheritTransform) {
            Node node0 = this.parent;
            if(node0 != null) {
                this.globalTransform.set(node0.globalTransform).mul(this.localTransform);
                return this.globalTransform;
            }
        }
        this.globalTransform.set(this.localTransform);
        return this.globalTransform;
    }

    public Node copy() {
        return new Node().set(this);
    }

    public void detach() {
        Node node0 = this.parent;
        if(node0 != null) {
            node0.removeChild(this);
            this.parent = null;
        }
    }

    public BoundingBox extendBoundingBox(BoundingBox boundingBox0) {
        return this.extendBoundingBox(boundingBox0, true);
    }

    public BoundingBox extendBoundingBox(BoundingBox boundingBox0, boolean z) {
        int v = this.parts.size;
        for(int v2 = 0; v2 < v; ++v2) {
            NodePart nodePart0 = (NodePart)this.parts.get(v2);
            if(nodePart0.enabled) {
                MeshPart meshPart0 = nodePart0.meshPart;
                if(z) {
                    meshPart0.mesh.extendBoundingBox(boundingBox0, meshPart0.offset, meshPart0.size, this.globalTransform);
                }
                else {
                    meshPart0.mesh.extendBoundingBox(boundingBox0, meshPart0.offset, meshPart0.size);
                }
            }
        }
        int v3 = this.children.size;
        for(int v1 = 0; v1 < v3; ++v1) {
            ((Node)this.children.get(v1)).extendBoundingBox(boundingBox0);
        }
        return boundingBox0;
    }

    public Node getChild(int v) {
        return (Node)this.children.get(v);
    }

    public Node getChild(String s, boolean z, boolean z1) {
        return Node.getNode(this.children, s, z, z1);
    }

    public int getChildCount() {
        return this.children.size;
    }

    public Iterable getChildren() {
        return this.children;
    }

    public static Node getNode(Array array0, String s, boolean z, boolean z1) {
        int v = array0.size;
        if(z1) {
            for(int v2 = 0; v2 < v; ++v2) {
                Node node0 = (Node)array0.get(v2);
                if(node0.id.equalsIgnoreCase(s)) {
                    return node0;
                }
            }
        }
        else {
            for(int v3 = 0; v3 < v; ++v3) {
                Node node1 = (Node)array0.get(v3);
                if(node1.id.equals(s)) {
                    return node1;
                }
            }
        }
        if(z) {
            for(int v1 = 0; v1 < v; ++v1) {
                Node node2 = Node.getNode(((Node)array0.get(v1)).children, s, true, z1);
                if(node2 != null) {
                    return node2;
                }
            }
        }
        return null;
    }

    public Node getParent() {
        return this.parent;
    }

    public boolean hasChildren() {
        return this.children != null && this.children.size > 0;
    }

    public boolean hasParent() {
        return this.parent != null;
    }

    public int insertChild(int v, Node node0) {
        for(Node node1 = this; node1 != null; node1 = node1.getParent()) {
            if(node1 == node0) {
                throw new GdxRuntimeException("Cannot add a parent as a child");
            }
        }
        Node node2 = node0.getParent();
        if(node2 != null && !node2.removeChild(node0)) {
            throw new GdxRuntimeException("Could not remove child from its current parent");
        }
        if(v < 0 || v >= this.children.size) {
            v = this.children.size;
            this.children.add(node0);
        }
        else {
            this.children.insert(v, node0);
        }
        node0.parent = this;
        return v;
    }

    public int insertChildren(int v, Iterable iterable0) {
        if(v < 0 || v > this.children.size) {
            v = this.children.size;
        }
        int v1 = v;
        for(Object object0: iterable0) {
            this.insertChild(v1, ((Node)object0));
            ++v1;
        }
        return v;
    }

    public boolean removeChild(Node node0) {
        if(!this.children.removeValue(node0, true)) {
            return false;
        }
        node0.parent = null;
        return true;
    }

    protected Node set(Node node0) {
        this.detach();
        this.id = node0.id;
        this.isAnimated = node0.isAnimated;
        this.inheritTransform = node0.inheritTransform;
        this.translation.set(node0.translation);
        this.rotation.set(node0.rotation);
        this.scale.set(node0.scale);
        this.localTransform.set(node0.localTransform);
        this.globalTransform.set(node0.globalTransform);
        this.parts.clear();
        for(Object object0: node0.parts) {
            this.parts.add(((NodePart)object0).copy());
        }
        this.children.clear();
        for(Object object1: node0.getChildren()) {
            this.addChild(((Node)object1).copy());
        }
        return this;
    }
}

