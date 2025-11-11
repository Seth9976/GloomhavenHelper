package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.model.NodeAnimation;
import com.badlogic.gdx.graphics.g3d.model.NodeKeyframe;
import com.badlogic.gdx.graphics.g3d.model.NodePart;
import com.badlogic.gdx.graphics.g3d.model.data.ModelAnimation;
import com.badlogic.gdx.graphics.g3d.model.data.ModelData;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMaterial;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMesh;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMeshPart;
import com.badlogic.gdx.graphics.g3d.model.data.ModelNode;
import com.badlogic.gdx.graphics.g3d.model.data.ModelNodeAnimation;
import com.badlogic.gdx.graphics.g3d.model.data.ModelNodeKeyframe;
import com.badlogic.gdx.graphics.g3d.model.data.ModelNodePart;
import com.badlogic.gdx.graphics.g3d.model.data.ModelTexture;
import com.badlogic.gdx.graphics.g3d.utils.TextureDescriptor;
import com.badlogic.gdx.graphics.g3d.utils.TextureProvider.FileTextureProvider;
import com.badlogic.gdx.graphics.g3d.utils.TextureProvider;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap.Entry;
import com.badlogic.gdx.utils.ObjectMap;

public class Model implements Disposable {
    public final Array animations;
    protected final Array disposables;
    public final Array materials;
    public final Array meshParts;
    public final Array meshes;
    private ObjectMap nodePartBones;
    public final Array nodes;

    public Model() {
        this.materials = new Array();
        this.nodes = new Array();
        this.animations = new Array();
        this.meshes = new Array();
        this.meshParts = new Array();
        this.disposables = new Array();
        this.nodePartBones = new ObjectMap();
    }

    public Model(ModelData modelData0) {
        this(modelData0, new FileTextureProvider());
    }

    public Model(ModelData modelData0, TextureProvider textureProvider0) {
        this.materials = new Array();
        this.nodes = new Array();
        this.animations = new Array();
        this.meshes = new Array();
        this.meshParts = new Array();
        this.disposables = new Array();
        this.nodePartBones = new ObjectMap();
        this.load(modelData0, textureProvider0);
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

    protected Material convertMaterial(ModelMaterial modelMaterial0, TextureProvider textureProvider0) {
        Texture texture0;
        Material material0 = new Material();
        material0.id = modelMaterial0.id;
        if(modelMaterial0.ambient != null) {
            material0.set(new ColorAttribute(ColorAttribute.Ambient, modelMaterial0.ambient));
        }
        if(modelMaterial0.diffuse != null) {
            material0.set(new ColorAttribute(ColorAttribute.Diffuse, modelMaterial0.diffuse));
        }
        if(modelMaterial0.specular != null) {
            material0.set(new ColorAttribute(ColorAttribute.Specular, modelMaterial0.specular));
        }
        if(modelMaterial0.emissive != null) {
            material0.set(new ColorAttribute(ColorAttribute.Emissive, modelMaterial0.emissive));
        }
        if(modelMaterial0.reflection != null) {
            material0.set(new ColorAttribute(ColorAttribute.Reflection, modelMaterial0.reflection));
        }
        if(modelMaterial0.shininess > 0.0f) {
            material0.set(new FloatAttribute(FloatAttribute.Shininess, modelMaterial0.shininess));
        }
        if(modelMaterial0.opacity != 1.0f) {
            material0.set(new BlendingAttribute(770, 0x303, modelMaterial0.opacity));
        }
        ObjectMap objectMap0 = new ObjectMap();
        if(modelMaterial0.textures != null) {
            for(Object object0: modelMaterial0.textures) {
                ModelTexture modelTexture0 = (ModelTexture)object0;
                if(objectMap0.containsKey(modelTexture0.fileName)) {
                    texture0 = (Texture)objectMap0.get(modelTexture0.fileName);
                }
                else {
                    texture0 = textureProvider0.load(modelTexture0.fileName);
                    objectMap0.put(modelTexture0.fileName, texture0);
                    this.disposables.add(texture0);
                }
                TextureDescriptor textureDescriptor0 = new TextureDescriptor(texture0);
                textureDescriptor0.minFilter = texture0.getMinFilter();
                textureDescriptor0.magFilter = texture0.getMagFilter();
                textureDescriptor0.uWrap = texture0.getUWrap();
                textureDescriptor0.vWrap = texture0.getVWrap();
                float f = modelTexture0.uvTranslation == null ? 0.0f : modelTexture0.uvTranslation.x;
                float f1 = modelTexture0.uvTranslation == null ? 0.0f : modelTexture0.uvTranslation.y;
                float f2 = modelTexture0.uvScaling == null ? 1.0f : modelTexture0.uvScaling.x;
                float f3 = modelTexture0.uvScaling == null ? 1.0f : modelTexture0.uvScaling.y;
                switch(modelTexture0.usage) {
                    case 2: {
                        material0.set(new TextureAttribute(TextureAttribute.Diffuse, textureDescriptor0, f, f1, f2, f3));
                        break;
                    }
                    case 3: {
                        material0.set(new TextureAttribute(TextureAttribute.Emissive, textureDescriptor0, f, f1, f2, f3));
                        break;
                    }
                    case 4: {
                        material0.set(new TextureAttribute(TextureAttribute.Ambient, textureDescriptor0, f, f1, f2, f3));
                        break;
                    }
                    case 5: {
                        material0.set(new TextureAttribute(TextureAttribute.Specular, textureDescriptor0, f, f1, f2, f3));
                        break;
                    }
                    case 7: {
                        material0.set(new TextureAttribute(TextureAttribute.Normal, textureDescriptor0, f, f1, f2, f3));
                        break;
                    }
                    case 8: {
                        material0.set(new TextureAttribute(TextureAttribute.Bump, textureDescriptor0, f, f1, f2, f3));
                        break;
                    }
                    case 10: {
                        material0.set(new TextureAttribute(TextureAttribute.Reflection, textureDescriptor0, f, f1, f2, f3));
                    }
                }
            }
        }
        return material0;
    }

    protected void convertMesh(ModelMesh modelMesh0) {
        ModelMeshPart[] arr_modelMeshPart = modelMesh0.parts;
        int v1 = 0;
        for(int v = 0; v < arr_modelMeshPart.length; ++v) {
            v1 += arr_modelMeshPart[v].indices.length;
        }
        VertexAttributes vertexAttributes0 = new VertexAttributes(modelMesh0.attributes);
        int v2 = modelMesh0.vertices.length / (vertexAttributes0.vertexSize / 4);
        Mesh mesh0 = new Mesh(true, v2, v1, vertexAttributes0);
        this.meshes.add(mesh0);
        this.disposables.add(mesh0);
        BufferUtils.copy(modelMesh0.vertices, mesh0.getVerticesBuffer(), modelMesh0.vertices.length, 0);
        mesh0.getIndicesBuffer().clear();
        ModelMeshPart[] arr_modelMeshPart1 = modelMesh0.parts;
        int v4 = 0;
        for(int v3 = 0; v3 < arr_modelMeshPart1.length; ++v3) {
            ModelMeshPart modelMeshPart0 = arr_modelMeshPart1[v3];
            MeshPart meshPart0 = new MeshPart();
            meshPart0.id = modelMeshPart0.id;
            meshPart0.primitiveType = modelMeshPart0.primitiveType;
            meshPart0.offset = v4;
            meshPart0.size = v1 <= 0 ? modelMeshPart0.indices.length : v2;
            meshPart0.mesh = mesh0;
            if(v1 > 0) {
                mesh0.getIndicesBuffer().put(modelMeshPart0.indices);
            }
            v4 += meshPart0.size;
            this.meshParts.add(meshPart0);
        }
        mesh0.getIndicesBuffer().position(0);
        for(Object object0: this.meshParts) {
            ((MeshPart)object0).update();
        }
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        for(Object object0: this.disposables) {
            ((Disposable)object0).dispose();
        }
    }

    public BoundingBox extendBoundingBox(BoundingBox boundingBox0) {
        int v = this.nodes.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((Node)this.nodes.get(v1)).extendBoundingBox(boundingBox0);
        }
        return boundingBox0;
    }

    public Animation getAnimation(String s) {
        return this.getAnimation(s, true);
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

    public Iterable getManagedDisposables() {
        return this.disposables;
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

    protected void load(ModelData modelData0, TextureProvider textureProvider0) {
        this.loadMeshes(modelData0.meshes);
        this.loadMaterials(modelData0.materials, textureProvider0);
        this.loadNodes(modelData0.nodes);
        this.loadAnimations(modelData0.animations);
        this.calculateTransforms();
    }

    protected void loadAnimations(Iterable iterable0) {
        for(Object object0: iterable0) {
            Animation animation0 = new Animation();
            animation0.id = ((ModelAnimation)object0).id;
            for(Object object1: ((ModelAnimation)object0).nodeAnimations) {
                ModelNodeAnimation modelNodeAnimation0 = (ModelNodeAnimation)object1;
                Node node0 = this.getNode(modelNodeAnimation0.nodeId);
                if(node0 != null) {
                    NodeAnimation nodeAnimation0 = new NodeAnimation();
                    nodeAnimation0.node = node0;
                    if(modelNodeAnimation0.translation != null) {
                        nodeAnimation0.translation = new Array();
                        nodeAnimation0.translation.ensureCapacity(modelNodeAnimation0.translation.size);
                        for(Object object2: modelNodeAnimation0.translation) {
                            ModelNodeKeyframe modelNodeKeyframe0 = (ModelNodeKeyframe)object2;
                            if(modelNodeKeyframe0.keytime > animation0.duration) {
                                animation0.duration = modelNodeKeyframe0.keytime;
                            }
                            nodeAnimation0.translation.add(new NodeKeyframe(modelNodeKeyframe0.keytime, new Vector3((modelNodeKeyframe0.value == null ? node0.translation : ((Vector3)modelNodeKeyframe0.value)))));
                        }
                    }
                    if(modelNodeAnimation0.rotation != null) {
                        nodeAnimation0.rotation = new Array();
                        nodeAnimation0.rotation.ensureCapacity(modelNodeAnimation0.rotation.size);
                        for(Object object3: modelNodeAnimation0.rotation) {
                            ModelNodeKeyframe modelNodeKeyframe1 = (ModelNodeKeyframe)object3;
                            if(modelNodeKeyframe1.keytime > animation0.duration) {
                                animation0.duration = modelNodeKeyframe1.keytime;
                            }
                            nodeAnimation0.rotation.add(new NodeKeyframe(modelNodeKeyframe1.keytime, new Quaternion((modelNodeKeyframe1.value == null ? node0.rotation : ((Quaternion)modelNodeKeyframe1.value)))));
                        }
                    }
                    if(modelNodeAnimation0.scaling != null) {
                        nodeAnimation0.scaling = new Array();
                        nodeAnimation0.scaling.ensureCapacity(modelNodeAnimation0.scaling.size);
                        for(Object object4: modelNodeAnimation0.scaling) {
                            ModelNodeKeyframe modelNodeKeyframe2 = (ModelNodeKeyframe)object4;
                            if(modelNodeKeyframe2.keytime > animation0.duration) {
                                animation0.duration = modelNodeKeyframe2.keytime;
                            }
                            nodeAnimation0.scaling.add(new NodeKeyframe(modelNodeKeyframe2.keytime, new Vector3((modelNodeKeyframe2.value == null ? node0.scale : ((Vector3)modelNodeKeyframe2.value)))));
                        }
                    }
                    if(nodeAnimation0.translation != null && nodeAnimation0.translation.size > 0 || nodeAnimation0.rotation != null && nodeAnimation0.rotation.size > 0 || nodeAnimation0.scaling != null && nodeAnimation0.scaling.size > 0) {
                        animation0.nodeAnimations.add(nodeAnimation0);
                    }
                }
            }
            if(animation0.nodeAnimations.size > 0) {
                this.animations.add(animation0);
            }
        }
    }

    protected void loadMaterials(Iterable iterable0, TextureProvider textureProvider0) {
        for(Object object0: iterable0) {
            Material material0 = this.convertMaterial(((ModelMaterial)object0), textureProvider0);
            this.materials.add(material0);
        }
    }

    protected void loadMeshes(Iterable iterable0) {
        for(Object object0: iterable0) {
            this.convertMesh(((ModelMesh)object0));
        }
    }

    protected Node loadNode(ModelNode modelNode0) {
        MeshPart meshPart0;
        Node node0 = new Node();
        node0.id = modelNode0.id;
        if(modelNode0.translation != null) {
            node0.translation.set(modelNode0.translation);
        }
        if(modelNode0.rotation != null) {
            node0.rotation.set(modelNode0.rotation);
        }
        if(modelNode0.scale != null) {
            node0.scale.set(modelNode0.scale);
        }
        if(modelNode0.parts != null) {
            ModelNodePart[] arr_modelNodePart = modelNode0.parts;
            int v1 = 0;
            while(v1 < arr_modelNodePart.length) {
                ModelNodePart modelNodePart0 = arr_modelNodePart[v1];
                Material material0 = null;
                if(modelNodePart0.meshPartId != null) {
                    for(Object object0: this.meshParts) {
                        meshPart0 = (MeshPart)object0;
                        if(!modelNodePart0.meshPartId.equals(meshPart0.id)) {
                            continue;
                        }
                        goto label_23;
                    }
                }
                meshPart0 = null;
            label_23:
                if(modelNodePart0.materialId != null) {
                    for(Object object1: this.materials) {
                        Material material1 = (Material)object1;
                        if(modelNodePart0.materialId.equals(material1.id)) {
                            material0 = material1;
                            break;
                        }
                        if(false) {
                            break;
                        }
                    }
                }
                if(meshPart0 == null || material0 == null) {
                    throw new GdxRuntimeException("Invalid node: " + node0.id);
                }
                NodePart nodePart0 = new NodePart();
                nodePart0.meshPart = meshPart0;
                nodePart0.material = material0;
                node0.parts.add(nodePart0);
                if(modelNodePart0.bones != null) {
                    this.nodePartBones.put(nodePart0, modelNodePart0.bones);
                }
                ++v1;
            }
        }
        if(modelNode0.children != null) {
            ModelNode[] arr_modelNode = modelNode0.children;
            for(int v = 0; v < arr_modelNode.length; ++v) {
                node0.addChild(this.loadNode(arr_modelNode[v]));
            }
        }
        return node0;
    }

    protected void loadNodes(Iterable iterable0) {
        this.nodePartBones.clear();
        for(Object object0: iterable0) {
            Node node0 = this.loadNode(((ModelNode)object0));
            this.nodes.add(node0);
        }
        for(Object object1: this.nodePartBones.entries()) {
            Entry objectMap$Entry0 = (Entry)object1;
            if(((NodePart)objectMap$Entry0.key).invBoneBindTransforms == null) {
                NodePart nodePart0 = (NodePart)objectMap$Entry0.key;
                nodePart0.invBoneBindTransforms = new ArrayMap(Node.class, Matrix4.class);
            }
            ((NodePart)objectMap$Entry0.key).invBoneBindTransforms.clear();
            for(Object object2: ((ArrayMap)objectMap$Entry0.value).entries()) {
                ((NodePart)objectMap$Entry0.key).invBoneBindTransforms.put(this.getNode(((String)((Entry)object2).key)), new Matrix4(((Matrix4)((Entry)object2).value)).inv());
            }
        }
    }

    public void manageDisposable(Disposable disposable0) {
        if(!this.disposables.contains(disposable0, true)) {
            this.disposables.add(disposable0);
        }
    }
}

