package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.model.NodeAnimation;
import com.badlogic.gdx.graphics.g3d.model.NodeKeyframe;
import com.badlogic.gdx.graphics.g3d.model.NodePart;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Pool;

public class ModelInstance implements RenderableProvider {
    public final Array animations;
    public static boolean defaultShareKeyframes = true;
    public final Array materials;
    public final Model model;
    public final Array nodes;
    public Matrix4 transform;
    public Object userData;

    static {
    }

    public ModelInstance(Model model0) {
        this(model0, null);
    }

    public ModelInstance(Model model0, float f, float f1, float f2) {
        this(model0);
        this.transform.setToTranslation(f, f1, f2);
    }

    public ModelInstance(Model model0, Matrix4 matrix40) {
        this(model0, matrix40, null);
    }

    public ModelInstance(Model model0, Matrix4 matrix40, Array array0) {
        this(model0, matrix40, array0, ModelInstance.defaultShareKeyframes);
    }

    public ModelInstance(Model model0, Matrix4 matrix40, Array array0, boolean z) {
        this.materials = new Array();
        this.nodes = new Array();
        this.animations = new Array();
        this.model = model0;
        if(matrix40 == null) {
            matrix40 = new Matrix4();
        }
        this.transform = matrix40;
        this.copyNodes(model0.nodes, array0);
        this.copyAnimations(model0.animations, z);
        this.calculateTransforms();
    }

    public ModelInstance(Model model0, Matrix4 matrix40, String s, boolean z) {
        this(model0, matrix40, s, false, false, z);
    }

    public ModelInstance(Model model0, Matrix4 matrix40, String s, boolean z, boolean z1) {
        this(model0, matrix40, s, true, z, z1);
    }

    public ModelInstance(Model model0, Matrix4 matrix40, String s, boolean z, boolean z1, boolean z2) {
        this(model0, matrix40, s, z, z1, z2, ModelInstance.defaultShareKeyframes);
    }

    public ModelInstance(Model model0, Matrix4 matrix40, String s, boolean z, boolean z1, boolean z2, boolean z3) {
        this.materials = new Array();
        this.nodes = new Array();
        this.animations = new Array();
        this.model = model0;
        if(matrix40 == null) {
            matrix40 = new Matrix4();
        }
        this.transform = matrix40;
        Node node0 = model0.getNode(s, z);
        Node node1 = node0.copy();
        this.nodes.add(node1);
        if(z2) {
            this.transform.mul((z1 ? node0.globalTransform : node0.localTransform));
            node1.translation.set(0.0f, 0.0f, 0.0f);
            node1.rotation.idt();
            node1.scale.set(1.0f, 1.0f, 1.0f);
        }
        else if(z1 && node1.hasParent()) {
            this.transform.mul(node0.getParent().globalTransform);
        }
        this.invalidate();
        this.copyAnimations(model0.animations, z3);
        this.calculateTransforms();
    }

    public ModelInstance(Model model0, Matrix4 matrix40, String[] arr_s) {
        this.materials = new Array();
        this.nodes = new Array();
        this.animations = new Array();
        this.model = model0;
        if(matrix40 == null) {
            matrix40 = new Matrix4();
        }
        this.transform = matrix40;
        if(arr_s == null) {
            this.copyNodes(model0.nodes);
        }
        else {
            this.copyNodes(model0.nodes, arr_s);
        }
        this.copyAnimations(model0.animations, ModelInstance.defaultShareKeyframes);
        this.calculateTransforms();
    }

    public ModelInstance(Model model0, Vector3 vector30) {
        this(model0);
        this.transform.setToTranslation(vector30);
    }

    public ModelInstance(Model model0, Array array0) {
        this(model0, null, array0);
    }

    public ModelInstance(Model model0, String s, boolean z) {
        this(model0, null, s, false, false, z);
    }

    public ModelInstance(Model model0, String s, boolean z, boolean z1) {
        this(model0, null, s, true, z, z1);
    }

    public ModelInstance(Model model0, String s, boolean z, boolean z1, boolean z2) {
        this(model0, null, s, z, z1, z2);
    }

    public ModelInstance(Model model0, String[] arr_s) {
        this(model0, null, arr_s);
    }

    public ModelInstance(ModelInstance modelInstance0) {
        this(modelInstance0, modelInstance0.transform.cpy());
    }

    public ModelInstance(ModelInstance modelInstance0, Matrix4 matrix40) {
        this(modelInstance0, matrix40, ModelInstance.defaultShareKeyframes);
    }

    public ModelInstance(ModelInstance modelInstance0, Matrix4 matrix40, boolean z) {
        this.materials = new Array();
        this.nodes = new Array();
        this.animations = new Array();
        this.model = modelInstance0.model;
        if(matrix40 == null) {
            matrix40 = new Matrix4();
        }
        this.transform = matrix40;
        this.copyNodes(modelInstance0.nodes);
        this.copyAnimations(modelInstance0.animations, z);
        this.calculateTransforms();
    }

    public BoundingBox calculateBoundingBox(BoundingBox boundingBox0) {
        boundingBox0.inf();
        return this.extendBoundingBox(boundingBox0);
    }

    public void calculateTransforms() {
        int v = this.nodes.size;
        for(int v2 = 0; v2 < v; ++v2) {
            ((Node)this.nodes.get(v2)).calculateTransforms(true);
        }
        for(int v1 = 0; v1 < v; ++v1) {
            ((Node)this.nodes.get(v1)).calculateBoneTransforms(true);
        }
    }

    public ModelInstance copy() {
        return new ModelInstance(this);
    }

    public void copyAnimation(Animation animation0) {
        this.copyAnimation(animation0, ModelInstance.defaultShareKeyframes);
    }

    public void copyAnimation(Animation animation0, boolean z) {
        Animation animation1 = new Animation();
        animation1.id = animation0.id;
        animation1.duration = animation0.duration;
        for(Object object0: animation0.nodeAnimations) {
            NodeAnimation nodeAnimation0 = (NodeAnimation)object0;
            Node node0 = this.getNode(nodeAnimation0.node.id);
            if(node0 != null) {
                NodeAnimation nodeAnimation1 = new NodeAnimation();
                nodeAnimation1.node = node0;
                if(z) {
                    nodeAnimation1.translation = nodeAnimation0.translation;
                    nodeAnimation1.rotation = nodeAnimation0.rotation;
                    nodeAnimation1.scaling = nodeAnimation0.scaling;
                }
                else {
                    if(nodeAnimation0.translation != null) {
                        nodeAnimation1.translation = new Array();
                        for(Object object1: nodeAnimation0.translation) {
                            nodeAnimation1.translation.add(new NodeKeyframe(((NodeKeyframe)object1).keytime, ((NodeKeyframe)object1).value));
                        }
                    }
                    if(nodeAnimation0.rotation != null) {
                        nodeAnimation1.rotation = new Array();
                        for(Object object2: nodeAnimation0.rotation) {
                            nodeAnimation1.rotation.add(new NodeKeyframe(((NodeKeyframe)object2).keytime, ((NodeKeyframe)object2).value));
                        }
                    }
                    if(nodeAnimation0.scaling != null) {
                        nodeAnimation1.scaling = new Array();
                        for(Object object3: nodeAnimation0.scaling) {
                            nodeAnimation1.scaling.add(new NodeKeyframe(((NodeKeyframe)object3).keytime, ((NodeKeyframe)object3).value));
                        }
                    }
                }
                if(nodeAnimation1.translation != null || nodeAnimation1.rotation != null || nodeAnimation1.scaling != null) {
                    animation1.nodeAnimations.add(nodeAnimation1);
                }
            }
        }
        if(animation1.nodeAnimations.size > 0) {
            this.animations.add(animation1);
        }
    }

    public void copyAnimations(Iterable iterable0) {
        for(Object object0: iterable0) {
            this.copyAnimation(((Animation)object0), ModelInstance.defaultShareKeyframes);
        }
    }

    public void copyAnimations(Iterable iterable0, boolean z) {
        for(Object object0: iterable0) {
            this.copyAnimation(((Animation)object0), z);
        }
    }

    private void copyNodes(Array array0) {
        int v = array0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            Node node0 = ((Node)array0.get(v1)).copy();
            this.nodes.add(node0);
        }
        this.invalidate();
    }

    private void copyNodes(Array array0, Array array1) {
        int v = array0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            Node node0 = (Node)array0.get(v1);
            for(Object object0: array1) {
                if(((String)object0).equals(node0.id)) {
                    Node node1 = node0.copy();
                    this.nodes.add(node1);
                    break;
                }
                if(false) {
                    break;
                }
            }
        }
        this.invalidate();
    }

    private void copyNodes(Array array0, String[] arr_s) {
        int v = array0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            Node node0 = (Node)array0.get(v1);
            for(int v2 = 0; v2 < arr_s.length; ++v2) {
                if(arr_s[v2].equals(node0.id)) {
                    Node node1 = node0.copy();
                    this.nodes.add(node1);
                    break;
                }
            }
        }
        this.invalidate();
    }

    public BoundingBox extendBoundingBox(BoundingBox boundingBox0) {
        int v = this.nodes.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((Node)this.nodes.get(v1)).extendBoundingBox(boundingBox0);
        }
        return boundingBox0;
    }

    public Animation getAnimation(String s) {
        return this.getAnimation(s, false);
    }

    public Animation getAnimation(String s, boolean z) {
        int v = this.animations.size;
        int v1 = 0;
        if(z) {
            while(v1 < v) {
                Animation animation0 = (Animation)this.animations.get(v1);
                if(animation0.id.equalsIgnoreCase(s)) {
                    return animation0;
                }
                ++v1;
            }
            return null;
        }
        while(v1 < v) {
            Animation animation1 = (Animation)this.animations.get(v1);
            if(animation1.id.equals(s)) {
                return animation1;
            }
            ++v1;
        }
        return null;
    }

    public Material getMaterial(String s) {
        return this.getMaterial(s, true);
    }

    public Material getMaterial(String s, boolean z) {
        int v = this.materials.size;
        int v1 = 0;
        if(z) {
            while(v1 < v) {
                Material material0 = (Material)this.materials.get(v1);
                if(material0.id.equalsIgnoreCase(s)) {
                    return material0;
                }
                ++v1;
            }
            return null;
        }
        while(v1 < v) {
            Material material1 = (Material)this.materials.get(v1);
            if(material1.id.equals(s)) {
                return material1;
            }
            ++v1;
        }
        return null;
    }

    public Node getNode(String s) {
        return this.getNode(s, true);
    }

    public Node getNode(String s, boolean z) {
        return this.getNode(s, z, false);
    }

    public Node getNode(String s, boolean z, boolean z1) {
        return Node.getNode(this.nodes, s, z, z1);
    }

    public Renderable getRenderable(Renderable renderable0) {
        return this.getRenderable(renderable0, ((Node)this.nodes.get(0)));
    }

    public Renderable getRenderable(Renderable renderable0, Node node0) {
        return this.getRenderable(renderable0, node0, ((NodePart)node0.parts.get(0)));
    }

    public Renderable getRenderable(Renderable renderable0, Node node0, NodePart nodePart0) {
        nodePart0.setRenderable(renderable0);
        if(nodePart0.bones == null && this.transform != null) {
            renderable0.worldTransform.set(this.transform).mul(node0.globalTransform);
        }
        else if(this.transform == null) {
            renderable0.worldTransform.idt();
        }
        else {
            renderable0.worldTransform.set(this.transform);
        }
        renderable0.userData = this.userData;
        return renderable0;
    }

    protected void getRenderables(Node node0, Array array0, Pool pool0) {
        if(node0.parts.size > 0) {
            for(Object object0: node0.parts) {
                NodePart nodePart0 = (NodePart)object0;
                if(nodePart0.enabled) {
                    array0.add(this.getRenderable(((Renderable)pool0.obtain()), node0, nodePart0));
                }
            }
        }
        for(Object object1: node0.getChildren()) {
            this.getRenderables(((Node)object1), array0, pool0);
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.RenderableProvider
    public void getRenderables(Array array0, Pool pool0) {
        for(Object object0: this.nodes) {
            this.getRenderables(((Node)object0), array0, pool0);
        }
    }

    private void invalidate() {
        int v = this.nodes.size;
        for(int v1 = 0; v1 < v; ++v1) {
            this.invalidate(((Node)this.nodes.get(v1)));
        }
    }

    private void invalidate(Node node0) {
        int v = node0.parts.size;
        for(int v2 = 0; v2 < v; ++v2) {
            NodePart nodePart0 = (NodePart)node0.parts.get(v2);
            ArrayMap arrayMap0 = nodePart0.invBoneBindTransforms;
            if(arrayMap0 != null) {
                for(int v3 = 0; v3 < arrayMap0.size; ++v3) {
                    Node[] arr_node = (Node[])arrayMap0.keys;
                    arr_node[v3] = this.getNode(((Node[])arrayMap0.keys)[v3].id);
                }
            }
            if(!this.materials.contains(nodePart0.material, true)) {
                int v4 = this.materials.indexOf(nodePart0.material, false);
                if(v4 < 0) {
                    Material material0 = nodePart0.material.copy();
                    nodePart0.material = material0;
                    this.materials.add(material0);
                }
                else {
                    nodePart0.material = (Material)this.materials.get(v4);
                }
            }
        }
        int v5 = node0.getChildCount();
        for(int v1 = 0; v1 < v5; ++v1) {
            this.invalidate(node0.getChild(v1));
        }
    }
}

