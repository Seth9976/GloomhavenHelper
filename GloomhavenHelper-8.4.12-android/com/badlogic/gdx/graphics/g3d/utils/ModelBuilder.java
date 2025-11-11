package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.model.NodePart;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class ModelBuilder {
    private Array builders;
    private Model model;
    private Node node;
    private Matrix4 tmpTransform;

    public ModelBuilder() {
        this.builders = new Array();
        this.tmpTransform = new Matrix4();
    }

    public void begin() {
        if(this.model != null) {
            throw new GdxRuntimeException("Call end() first");
        }
        this.node = null;
        this.model = new Model();
        this.builders.clear();
    }

    public Model createArrow(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, int v, int v1, Material material0, long v2) {
        this.begin();
        this.part("arrow", v1, v2, material0).arrow(f, f1, f2, f3, f4, f5, f6, f7, v);
        return this.end();
    }

    public Model createArrow(Vector3 vector30, Vector3 vector31, Material material0, long v) {
        return this.createArrow(vector30.x, vector30.y, vector30.z, vector31.x, vector31.y, vector31.z, 0.1f, 0.1f, 5, 4, material0, v);
    }

    public Model createBox(float f, float f1, float f2, int v, Material material0, long v1) {
        this.begin();
        this.part("box", v, v1, material0).box(f, f1, f2);
        return this.end();
    }

    public Model createBox(float f, float f1, float f2, Material material0, long v) {
        return this.createBox(f, f1, f2, 4, material0, v);
    }

    public Model createCapsule(float f, float f1, int v, int v1, Material material0, long v2) {
        this.begin();
        this.part("capsule", v1, v2, material0).capsule(f, f1, v);
        return this.end();
    }

    public Model createCapsule(float f, float f1, int v, Material material0, long v1) {
        return this.createCapsule(f, f1, v, 4, material0, v1);
    }

    public Model createCone(float f, float f1, float f2, int v, int v1, Material material0, long v2) {
        return this.createCone(f, f1, f2, v, v1, material0, v2, 0.0f, 360.0f);
    }

    public Model createCone(float f, float f1, float f2, int v, int v1, Material material0, long v2, float f3, float f4) {
        this.begin();
        this.part("cone", v1, v2, material0).cone(f, f1, f2, v, f3, f4);
        return this.end();
    }

    public Model createCone(float f, float f1, float f2, int v, Material material0, long v1) {
        return this.createCone(f, f1, f2, v, 4, material0, v1);
    }

    public Model createCone(float f, float f1, float f2, int v, Material material0, long v1, float f3, float f4) {
        return this.createCone(f, f1, f2, v, 4, material0, v1, f3, f4);
    }

    public Model createCylinder(float f, float f1, float f2, int v, int v1, Material material0, long v2) {
        return this.createCylinder(f, f1, f2, v, v1, material0, v2, 0.0f, 360.0f);
    }

    public Model createCylinder(float f, float f1, float f2, int v, int v1, Material material0, long v2, float f3, float f4) {
        this.begin();
        this.part("cylinder", v1, v2, material0).cylinder(f, f1, f2, v, f3, f4);
        return this.end();
    }

    public Model createCylinder(float f, float f1, float f2, int v, Material material0, long v1) {
        return this.createCylinder(f, f1, f2, v, 4, material0, v1);
    }

    public Model createCylinder(float f, float f1, float f2, int v, Material material0, long v1, float f3, float f4) {
        return this.createCylinder(f, f1, f2, v, 4, material0, v1, f3, f4);
    }

    public Model createLineGrid(int v, int v1, float f, float f1, Material material0, long v2) {
        this.begin();
        MeshPartBuilder meshPartBuilder0 = this.part("lines", 1, v2, material0);
        float f2 = ((float)v) * f / 2.0f;
        float f3 = ((float)v1) * f1 / 2.0f;
        float f4 = -f3;
        float f5 = -f2;
        float f6 = f5;
        for(int v4 = 0; v4 <= v; ++v4) {
            meshPartBuilder0.line(f5, 0.0f, f3, f6, 0.0f, f4);
            f5 += f;
            f6 += f;
        }
        float f7 = f4;
        for(int v3 = 0; v3 <= v1; ++v3) {
            meshPartBuilder0.line(-f2, 0.0f, f4, f2, 0.0f, f7);
            f4 += f1;
            f7 += f1;
        }
        return this.end();
    }

    public Model createRect(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, int v, Material material0, long v1) {
        this.begin();
        this.part("rect", v, v1, material0).rect(f, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14);
        return this.end();
    }

    public Model createRect(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, Material material0, long v) {
        return this.createRect(f, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, 4, material0, v);
    }

    public Model createSphere(float f, float f1, float f2, int v, int v1, int v2, Material material0, long v3) {
        return this.createSphere(f, f1, f2, v, v1, v2, material0, v3, 0.0f, 360.0f, 0.0f, 180.0f);
    }

    public Model createSphere(float f, float f1, float f2, int v, int v1, int v2, Material material0, long v3, float f3, float f4, float f5, float f6) {
        this.begin();
        this.part("sphere", v2, v3, material0).sphere(f, f1, f2, v, v1, f3, f4, f5, f6);
        return this.end();
    }

    public Model createSphere(float f, float f1, float f2, int v, int v1, Material material0, long v2) {
        return this.createSphere(f, f1, f2, v, v1, 4, material0, v2);
    }

    public Model createSphere(float f, float f1, float f2, int v, int v1, Material material0, long v2, float f3, float f4, float f5, float f6) {
        return this.createSphere(f, f1, f2, v, v1, 4, material0, v2, f3, f4, f5, f6);
    }

    public Model createXYZCoordinates(float f, float f1, float f2, int v, int v1, Material material0, long v2) {
        this.begin();
        this.node();
        MeshPartBuilder meshPartBuilder0 = this.part("xyz", v1, v2, material0);
        meshPartBuilder0.setColor(Color.RED);
        meshPartBuilder0.arrow(0.0f, 0.0f, 0.0f, f, 0.0f, 0.0f, f1, f2, v);
        meshPartBuilder0.setColor(Color.GREEN);
        meshPartBuilder0.arrow(0.0f, 0.0f, 0.0f, 0.0f, f, 0.0f, f1, f2, v);
        meshPartBuilder0.setColor(Color.BLUE);
        meshPartBuilder0.arrow(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f, f1, f2, v);
        return this.end();
    }

    public Model createXYZCoordinates(float f, Material material0, long v) {
        return this.createXYZCoordinates(f, 0.1f, 0.1f, 5, 4, material0, v);
    }

    public Model end() {
        Model model0 = this.model;
        if(model0 == null) {
            throw new GdxRuntimeException("Call begin() first");
        }
        this.endnode();
        this.model = null;
        for(Object object0: this.builders) {
            ((MeshBuilder)object0).end();
        }
        this.builders.clear();
        ModelBuilder.rebuildReferences(model0);
        return model0;
    }

    private void endnode() {
        if(this.node != null) {
            this.node = null;
        }
    }

    private MeshBuilder getBuilder(VertexAttributes vertexAttributes0) {
        for(Object object0: this.builders) {
            MeshBuilder meshBuilder0 = (MeshBuilder)object0;
            if(meshBuilder0.getAttributes().equals(vertexAttributes0) && meshBuilder0.lastIndex() < 0x3FFF) {
                return meshBuilder0;
            }
            if(false) {
                break;
            }
        }
        MeshBuilder meshBuilder1 = new MeshBuilder();
        meshBuilder1.begin(vertexAttributes0);
        this.builders.add(meshBuilder1);
        return meshBuilder1;
    }

    public void manage(Disposable disposable0) {
        Model model0 = this.model;
        if(model0 == null) {
            throw new GdxRuntimeException("Call begin() first");
        }
        model0.manageDisposable(disposable0);
    }

    public Node node() {
        Node node0 = new Node();
        this.node(node0);
        node0.id = "node" + this.model.nodes.size;
        return node0;
    }

    protected Node node(Node node0) {
        if(this.model == null) {
            throw new GdxRuntimeException("Call begin() first");
        }
        this.endnode();
        this.model.nodes.add(node0);
        this.node = node0;
        return node0;
    }

    public Node node(String s, Model model0) {
        Node node0 = new Node();
        node0.id = s;
        node0.addChildren(model0.nodes);
        this.node(node0);
        for(Object object0: model0.getManagedDisposables()) {
            this.manage(((Disposable)object0));
        }
        return node0;
    }

    public MeshPart part(String s, Mesh mesh0, int v, int v1, int v2, Material material0) {
        MeshPart meshPart0 = new MeshPart();
        meshPart0.id = s;
        meshPart0.primitiveType = v;
        meshPart0.mesh = mesh0;
        meshPart0.offset = v1;
        meshPart0.size = v2;
        this.part(meshPart0, material0);
        return meshPart0;
    }

    public MeshPart part(String s, Mesh mesh0, int v, Material material0) {
        return this.part(s, mesh0, v, 0, mesh0.getNumIndices(), material0);
    }

    public MeshPartBuilder part(String s, int v, long v1, Material material0) {
        return this.part(s, v, MeshBuilder.createAttributes(v1), material0);
    }

    public MeshPartBuilder part(String s, int v, VertexAttributes vertexAttributes0, Material material0) {
        MeshPartBuilder meshPartBuilder0 = this.getBuilder(vertexAttributes0);
        this.part(((MeshBuilder)meshPartBuilder0).part(s, v), material0);
        return meshPartBuilder0;
    }

    public void part(MeshPart meshPart0, Material material0) {
        if(this.node == null) {
            this.node();
        }
        this.node.parts.add(new NodePart(meshPart0, material0));
    }

    public static void rebuildReferences(Model model0) {
        model0.materials.clear();
        model0.meshes.clear();
        model0.meshParts.clear();
        for(Object object0: model0.nodes) {
            ModelBuilder.rebuildReferences(model0, ((Node)object0));
        }
    }

    private static void rebuildReferences(Model model0, Node node0) {
        for(Object object0: node0.parts) {
            NodePart nodePart0 = (NodePart)object0;
            if(!model0.materials.contains(nodePart0.material, true)) {
                model0.materials.add(nodePart0.material);
            }
            if(!model0.meshParts.contains(nodePart0.meshPart, true)) {
                model0.meshParts.add(nodePart0.meshPart);
                if(!model0.meshes.contains(nodePart0.meshPart.mesh, true)) {
                    model0.meshes.add(nodePart0.meshPart.mesh);
                }
                model0.manageDisposable(nodePart0.meshPart.mesh);
            }
        }
        for(Object object1: node0.getChildren()) {
            ModelBuilder.rebuildReferences(model0, ((Node)object1));
        }
    }
}

