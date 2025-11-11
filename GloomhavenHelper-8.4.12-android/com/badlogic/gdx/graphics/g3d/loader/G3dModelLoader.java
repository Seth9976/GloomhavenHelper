package com.badlogic.gdx.graphics.g3d.loader;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.ModelLoader.ModelParameters;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttribute;
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
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.BaseJsonReader;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.JsonValue;

public class G3dModelLoader extends ModelLoader {
    public static final short VERSION_HI = 0;
    public static final short VERSION_LO = 1;
    protected final BaseJsonReader reader;
    protected final Quaternion tempQ;

    public G3dModelLoader(BaseJsonReader baseJsonReader0) {
        this(baseJsonReader0, null);
    }

    public G3dModelLoader(BaseJsonReader baseJsonReader0, FileHandleResolver fileHandleResolver0) {
        super(fileHandleResolver0);
        this.tempQ = new Quaternion();
        this.reader = baseJsonReader0;
    }

    @Override  // com.badlogic.gdx.assets.loaders.ModelLoader
    public ModelData loadModelData(FileHandle fileHandle0, ModelParameters modelLoader$ModelParameters0) {
        return this.parseModel(fileHandle0);
    }

    protected void parseAnimations(ModelData modelData0, JsonValue jsonValue0) {
        JsonValue jsonValue1 = jsonValue0.get("animations");
        if(jsonValue1 == null) {
            return;
        }
        modelData0.animations.ensureCapacity(jsonValue1.size);
        for(JsonValue jsonValue2 = jsonValue1.child; jsonValue2 != null; jsonValue2 = jsonValue2.next) {
            JsonValue jsonValue3 = jsonValue2.get("bones");
            if(jsonValue3 != null) {
                ModelAnimation modelAnimation0 = new ModelAnimation();
                modelData0.animations.add(modelAnimation0);
                modelAnimation0.nodeAnimations.ensureCapacity(jsonValue3.size);
                modelAnimation0.id = jsonValue2.getString("id");
                for(JsonValue jsonValue4 = jsonValue3.child; jsonValue4 != null; jsonValue4 = jsonValue4.next) {
                    ModelNodeAnimation modelNodeAnimation0 = new ModelNodeAnimation();
                    modelAnimation0.nodeAnimations.add(modelNodeAnimation0);
                    modelNodeAnimation0.nodeId = jsonValue4.getString("boneId");
                    JsonValue jsonValue5 = jsonValue4.get("keyframes");
                    if(jsonValue5 == null || !jsonValue5.isArray()) {
                        JsonValue jsonValue10 = jsonValue4.get("translation");
                        if(jsonValue10 != null && jsonValue10.isArray()) {
                            modelNodeAnimation0.translation = new Array();
                            modelNodeAnimation0.translation.ensureCapacity(jsonValue10.size);
                            for(JsonValue jsonValue11 = jsonValue10.child; jsonValue11 != null; jsonValue11 = jsonValue11.next) {
                                ModelNodeKeyframe modelNodeKeyframe3 = new ModelNodeKeyframe();
                                modelNodeAnimation0.translation.add(modelNodeKeyframe3);
                                modelNodeKeyframe3.keytime = jsonValue11.getFloat("keytime", 0.0f) / 1000.0f;
                                JsonValue jsonValue12 = jsonValue11.get("value");
                                if(jsonValue12 != null && jsonValue12.size >= 3) {
                                    modelNodeKeyframe3.value = new Vector3(jsonValue12.getFloat(0), jsonValue12.getFloat(1), jsonValue12.getFloat(2));
                                }
                            }
                        }
                        JsonValue jsonValue13 = jsonValue4.get("rotation");
                        if(jsonValue13 != null && jsonValue13.isArray()) {
                            modelNodeAnimation0.rotation = new Array();
                            modelNodeAnimation0.rotation.ensureCapacity(jsonValue13.size);
                            for(JsonValue jsonValue14 = jsonValue13.child; jsonValue14 != null; jsonValue14 = jsonValue14.next) {
                                ModelNodeKeyframe modelNodeKeyframe4 = new ModelNodeKeyframe();
                                modelNodeAnimation0.rotation.add(modelNodeKeyframe4);
                                modelNodeKeyframe4.keytime = jsonValue14.getFloat("keytime", 0.0f) / 1000.0f;
                                JsonValue jsonValue15 = jsonValue14.get("value");
                                if(jsonValue15 != null && jsonValue15.size >= 4) {
                                    modelNodeKeyframe4.value = new Quaternion(jsonValue15.getFloat(0), jsonValue15.getFloat(1), jsonValue15.getFloat(2), jsonValue15.getFloat(3));
                                }
                            }
                        }
                        JsonValue jsonValue16 = jsonValue4.get("scaling");
                        if(jsonValue16 != null && jsonValue16.isArray()) {
                            modelNodeAnimation0.scaling = new Array();
                            modelNodeAnimation0.scaling.ensureCapacity(jsonValue16.size);
                            for(JsonValue jsonValue17 = jsonValue16.child; jsonValue17 != null; jsonValue17 = jsonValue17.next) {
                                ModelNodeKeyframe modelNodeKeyframe5 = new ModelNodeKeyframe();
                                modelNodeAnimation0.scaling.add(modelNodeKeyframe5);
                                modelNodeKeyframe5.keytime = jsonValue17.getFloat("keytime", 0.0f) / 1000.0f;
                                JsonValue jsonValue18 = jsonValue17.get("value");
                                if(jsonValue18 != null && jsonValue18.size >= 3) {
                                    modelNodeKeyframe5.value = new Vector3(jsonValue18.getFloat(0), jsonValue18.getFloat(1), jsonValue18.getFloat(2));
                                }
                            }
                        }
                    }
                    else {
                        for(JsonValue jsonValue6 = jsonValue5.child; jsonValue6 != null; jsonValue6 = jsonValue6.next) {
                            float f = jsonValue6.getFloat("keytime", 0.0f) / 1000.0f;
                            JsonValue jsonValue7 = jsonValue6.get("translation");
                            if(jsonValue7 != null && jsonValue7.size == 3) {
                                if(modelNodeAnimation0.translation == null) {
                                    modelNodeAnimation0.translation = new Array();
                                }
                                ModelNodeKeyframe modelNodeKeyframe0 = new ModelNodeKeyframe();
                                modelNodeKeyframe0.keytime = f;
                                modelNodeKeyframe0.value = new Vector3(jsonValue7.getFloat(0), jsonValue7.getFloat(1), jsonValue7.getFloat(2));
                                modelNodeAnimation0.translation.add(modelNodeKeyframe0);
                            }
                            JsonValue jsonValue8 = jsonValue6.get("rotation");
                            if(jsonValue8 != null && jsonValue8.size == 4) {
                                if(modelNodeAnimation0.rotation == null) {
                                    modelNodeAnimation0.rotation = new Array();
                                }
                                ModelNodeKeyframe modelNodeKeyframe1 = new ModelNodeKeyframe();
                                modelNodeKeyframe1.keytime = f;
                                modelNodeKeyframe1.value = new Quaternion(jsonValue8.getFloat(0), jsonValue8.getFloat(1), jsonValue8.getFloat(2), jsonValue8.getFloat(3));
                                modelNodeAnimation0.rotation.add(modelNodeKeyframe1);
                            }
                            JsonValue jsonValue9 = jsonValue6.get("scale");
                            if(jsonValue9 != null && jsonValue9.size == 3) {
                                if(modelNodeAnimation0.scaling == null) {
                                    modelNodeAnimation0.scaling = new Array();
                                }
                                ModelNodeKeyframe modelNodeKeyframe2 = new ModelNodeKeyframe();
                                modelNodeKeyframe2.keytime = f;
                                modelNodeKeyframe2.value = new Vector3(jsonValue9.getFloat(0), jsonValue9.getFloat(1), jsonValue9.getFloat(2));
                                modelNodeAnimation0.scaling.add(modelNodeKeyframe2);
                            }
                        }
                    }
                }
            }
        }
    }

    protected VertexAttribute[] parseAttributes(JsonValue jsonValue0) {
        Array array0 = new Array();
        JsonValue jsonValue1 = jsonValue0.child;
        int v = 0;
        int v1 = 0;
        while(jsonValue1 != null) {
            String s = jsonValue1.asString();
            if(s.equals("POSITION")) {
                array0.add(VertexAttribute.Position());
            }
            else if(s.equals("NORMAL")) {
                array0.add(VertexAttribute.Normal());
            }
            else if(s.equals("COLOR")) {
                array0.add(VertexAttribute.ColorUnpacked());
            }
            else if(s.equals("COLORPACKED")) {
                array0.add(VertexAttribute.ColorPacked());
            }
            else if(s.equals("TANGENT")) {
                array0.add(VertexAttribute.Tangent());
            }
            else if(s.equals("BINORMAL")) {
                array0.add(VertexAttribute.Binormal());
            }
            else if(s.startsWith("TEXCOORD")) {
                array0.add(VertexAttribute.TexCoords(v));
                ++v;
            }
            else {
                if(!s.startsWith("BLENDWEIGHT")) {
                    throw new GdxRuntimeException("Unknown vertex attribute \'" + s + "\', should be one of position, normal, uv, tangent or binormal");
                }
                array0.add(VertexAttribute.BoneWeight(v1));
                ++v1;
            }
            jsonValue1 = jsonValue1.next;
        }
        return (VertexAttribute[])array0.toArray(VertexAttribute.class);
    }

    protected Color parseColor(JsonValue jsonValue0) {
        if(jsonValue0.size < 3) {
            throw new GdxRuntimeException("Expected Color values <> than three.");
        }
        return new Color(jsonValue0.getFloat(0), jsonValue0.getFloat(1), jsonValue0.getFloat(2), 1.0f);
    }

    protected void parseMaterials(ModelData modelData0, JsonValue jsonValue0, String s) {
        JsonValue jsonValue1 = jsonValue0.get("materials");
        if(jsonValue1 != null) {
            modelData0.materials.ensureCapacity(jsonValue1.size);
            for(JsonValue jsonValue2 = jsonValue1.child; jsonValue2 != null; jsonValue2 = jsonValue2.next) {
                ModelMaterial modelMaterial0 = new ModelMaterial();
                String s1 = jsonValue2.getString("id", null);
                if(s1 == null) {
                    throw new GdxRuntimeException("Material needs an id.");
                }
                modelMaterial0.id = s1;
                JsonValue jsonValue3 = jsonValue2.get("diffuse");
                if(jsonValue3 != null) {
                    modelMaterial0.diffuse = this.parseColor(jsonValue3);
                }
                JsonValue jsonValue4 = jsonValue2.get("ambient");
                if(jsonValue4 != null) {
                    modelMaterial0.ambient = this.parseColor(jsonValue4);
                }
                JsonValue jsonValue5 = jsonValue2.get("emissive");
                if(jsonValue5 != null) {
                    modelMaterial0.emissive = this.parseColor(jsonValue5);
                }
                JsonValue jsonValue6 = jsonValue2.get("specular");
                if(jsonValue6 != null) {
                    modelMaterial0.specular = this.parseColor(jsonValue6);
                }
                JsonValue jsonValue7 = jsonValue2.get("reflection");
                if(jsonValue7 != null) {
                    modelMaterial0.reflection = this.parseColor(jsonValue7);
                }
                modelMaterial0.shininess = jsonValue2.getFloat("shininess", 0.0f);
                modelMaterial0.opacity = jsonValue2.getFloat("opacity", 1.0f);
                JsonValue jsonValue8 = jsonValue2.get("textures");
                if(jsonValue8 != null) {
                    for(JsonValue jsonValue9 = jsonValue8.child; jsonValue9 != null; jsonValue9 = jsonValue9.next) {
                        ModelTexture modelTexture0 = new ModelTexture();
                        String s2 = jsonValue9.getString("id", null);
                        if(s2 == null) {
                            throw new GdxRuntimeException("Texture has no id.");
                        }
                        modelTexture0.id = s2;
                        String s3 = jsonValue9.getString("filename", null);
                        if(s3 == null) {
                            throw new GdxRuntimeException("Texture needs filename.");
                        }
                        modelTexture0.fileName = s + (s.length() == 0 || s.endsWith("/") ? "" : "/") + s3;
                        modelTexture0.uvTranslation = this.readVector2(jsonValue9.get("uvTranslation"), 0.0f, 0.0f);
                        modelTexture0.uvScaling = this.readVector2(jsonValue9.get("uvScaling"), 1.0f, 1.0f);
                        String s4 = jsonValue9.getString("type", null);
                        if(s4 == null) {
                            throw new GdxRuntimeException("Texture needs type.");
                        }
                        modelTexture0.usage = this.parseTextureUsage(s4);
                        if(modelMaterial0.textures == null) {
                            modelMaterial0.textures = new Array();
                        }
                        modelMaterial0.textures.add(modelTexture0);
                    }
                }
                modelData0.materials.add(modelMaterial0);
            }
        }
    }

    protected void parseMeshes(ModelData modelData0, JsonValue jsonValue0) {
        JsonValue jsonValue1 = jsonValue0.get("meshes");
        if(jsonValue1 != null) {
            modelData0.meshes.ensureCapacity(jsonValue1.size);
            for(JsonValue jsonValue2 = jsonValue1.child; jsonValue2 != null; jsonValue2 = jsonValue2.next) {
                ModelMesh modelMesh0 = new ModelMesh();
                modelMesh0.id = jsonValue2.getString("id", "");
                modelMesh0.attributes = this.parseAttributes(jsonValue2.require("attributes"));
                modelMesh0.vertices = jsonValue2.require("vertices").asFloatArray();
                JsonValue jsonValue3 = jsonValue2.require("parts");
                Array array0 = new Array();
                for(JsonValue jsonValue4 = jsonValue3.child; jsonValue4 != null; jsonValue4 = jsonValue4.next) {
                    ModelMeshPart modelMeshPart0 = new ModelMeshPart();
                    String s = jsonValue4.getString("id", null);
                    if(s == null) {
                        throw new GdxRuntimeException("Not id given for mesh part");
                    }
                    for(Object object0: array0) {
                        if(((ModelMeshPart)object0).id.equals(s)) {
                            throw new GdxRuntimeException("Mesh part with id \'" + s + "\' already in defined");
                        }
                        if(false) {
                            break;
                        }
                    }
                    modelMeshPart0.id = s;
                    String s1 = jsonValue4.getString("type", null);
                    if(s1 == null) {
                        throw new GdxRuntimeException("No primitive type given for mesh part \'" + s + "\'");
                    }
                    modelMeshPart0.primitiveType = this.parseType(s1);
                    modelMeshPart0.indices = jsonValue4.require("indices").asShortArray();
                    array0.add(modelMeshPart0);
                }
                modelMesh0.parts = (ModelMeshPart[])array0.toArray(ModelMeshPart.class);
                modelData0.meshes.add(modelMesh0);
            }
        }
    }

    public ModelData parseModel(FileHandle fileHandle0) {
        JsonValue jsonValue0 = this.reader.parse(fileHandle0);
        ModelData modelData0 = new ModelData();
        JsonValue jsonValue1 = jsonValue0.require("version");
        modelData0.version[0] = jsonValue1.getShort(0);
        modelData0.version[1] = jsonValue1.getShort(1);
        if(modelData0.version[0] != 0 || modelData0.version[1] != 1) {
            throw new GdxRuntimeException("Model version not supported");
        }
        modelData0.id = jsonValue0.getString("id", "");
        this.parseMeshes(modelData0, jsonValue0);
        this.parseMaterials(modelData0, jsonValue0, fileHandle0.parent().path());
        this.parseNodes(modelData0, jsonValue0);
        this.parseAnimations(modelData0, jsonValue0);
        return modelData0;
    }

    protected Array parseNodes(ModelData modelData0, JsonValue jsonValue0) {
        JsonValue jsonValue1 = jsonValue0.get("nodes");
        if(jsonValue1 != null) {
            modelData0.nodes.ensureCapacity(jsonValue1.size);
            for(JsonValue jsonValue2 = jsonValue1.child; jsonValue2 != null; jsonValue2 = jsonValue2.next) {
                ModelNode modelNode0 = this.parseNodesRecursively(jsonValue2);
                modelData0.nodes.add(modelNode0);
            }
        }
        return modelData0.nodes;
    }

    protected ModelNode parseNodesRecursively(JsonValue jsonValue0) {
        int v1;
        ModelNode modelNode0 = new ModelNode();
        String s = jsonValue0.getString("id", null);
        if(s == null) {
            throw new GdxRuntimeException("Node id missing.");
        }
        modelNode0.id = s;
        JsonValue jsonValue1 = jsonValue0.get("translation");
        if(jsonValue1 != null && jsonValue1.size != 3) {
            throw new GdxRuntimeException("Node translation incomplete");
        }
        modelNode0.translation = jsonValue1 == null ? null : new Vector3(jsonValue1.getFloat(0), jsonValue1.getFloat(1), jsonValue1.getFloat(2));
        JsonValue jsonValue2 = jsonValue0.get("rotation");
        if(jsonValue2 != null && jsonValue2.size != 4) {
            throw new GdxRuntimeException("Node rotation incomplete");
        }
        modelNode0.rotation = jsonValue2 == null ? null : new Quaternion(jsonValue2.getFloat(0), jsonValue2.getFloat(1), jsonValue2.getFloat(2), jsonValue2.getFloat(3));
        JsonValue jsonValue3 = jsonValue0.get("scale");
        if(jsonValue3 != null && jsonValue3.size != 3) {
            throw new GdxRuntimeException("Node scale incomplete");
        }
        modelNode0.scale = jsonValue3 == null ? null : new Vector3(jsonValue3.getFloat(0), jsonValue3.getFloat(1), jsonValue3.getFloat(2));
        String s1 = jsonValue0.getString("mesh", null);
        if(s1 != null) {
            modelNode0.meshId = s1;
        }
        JsonValue jsonValue4 = jsonValue0.get("parts");
        if(jsonValue4 != null) {
            modelNode0.parts = new ModelNodePart[jsonValue4.size];
            JsonValue jsonValue5 = jsonValue4.child;
            for(int v = 0; jsonValue5 != null; ++v) {
                ModelNodePart modelNodePart0 = new ModelNodePart();
                String s2 = jsonValue5.getString("meshpartid", null);
                String s3 = jsonValue5.getString("materialid", null);
                if(s2 == null || s3 == null) {
                    throw new GdxRuntimeException("Node " + s + " part is missing meshPartId or materialId");
                }
                modelNodePart0.materialId = s3;
                modelNodePart0.meshPartId = s2;
                JsonValue jsonValue6 = jsonValue5.get("bones");
                if(jsonValue6 != null) {
                    modelNodePart0.bones = new ArrayMap(true, jsonValue6.size, String.class, Matrix4.class);
                    for(JsonValue jsonValue7 = jsonValue6.child; jsonValue7 != null; jsonValue7 = jsonValue7.next) {
                        String s4 = jsonValue7.getString("node", null);
                        if(s4 == null) {
                            throw new GdxRuntimeException("Bone node ID missing");
                        }
                        Matrix4 matrix40 = new Matrix4();
                        JsonValue jsonValue8 = jsonValue7.get("translation");
                        if(jsonValue8 != null && jsonValue8.size >= 3) {
                            matrix40.translate(jsonValue8.getFloat(0), jsonValue8.getFloat(1), jsonValue8.getFloat(2));
                        }
                        JsonValue jsonValue9 = jsonValue7.get("rotation");
                        if(jsonValue9 != null && jsonValue9.size >= 4) {
                            float f = jsonValue9.getFloat(0);
                            float f1 = jsonValue9.getFloat(1);
                            float f2 = jsonValue9.getFloat(2);
                            float f3 = jsonValue9.getFloat(3);
                            matrix40.rotate(this.tempQ.set(f, f1, f2, f3));
                        }
                        JsonValue jsonValue10 = jsonValue7.get("scale");
                        if(jsonValue10 != null && jsonValue10.size >= 3) {
                            matrix40.scale(jsonValue10.getFloat(0), jsonValue10.getFloat(1), jsonValue10.getFloat(2));
                        }
                        modelNodePart0.bones.put(s4, matrix40);
                    }
                }
                modelNode0.parts[v] = modelNodePart0;
                jsonValue5 = jsonValue5.next;
            }
        }
        v1 = 0;
        JsonValue jsonValue11 = jsonValue0.get("children");
        if(jsonValue11 != null) {
            modelNode0.children = new ModelNode[jsonValue11.size];
            JsonValue jsonValue12 = jsonValue11.child;
            while(jsonValue12 != null) {
                ModelNode[] arr_modelNode = modelNode0.children;
                arr_modelNode[v1] = this.parseNodesRecursively(jsonValue12);
                jsonValue12 = jsonValue12.next;
                ++v1;
            }
        }
        return modelNode0;
    }

    protected int parseTextureUsage(String s) {
        if(s.equalsIgnoreCase("AMBIENT")) {
            return 4;
        }
        if(s.equalsIgnoreCase("BUMP")) {
            return 8;
        }
        if(s.equalsIgnoreCase("DIFFUSE")) {
            return 2;
        }
        if(s.equalsIgnoreCase("EMISSIVE")) {
            return 3;
        }
        if(s.equalsIgnoreCase("NONE")) {
            return 1;
        }
        if(s.equalsIgnoreCase("NORMAL")) {
            return 7;
        }
        if(s.equalsIgnoreCase("REFLECTION")) {
            return 10;
        }
        if(s.equalsIgnoreCase("SHININESS")) {
            return 6;
        }
        if(s.equalsIgnoreCase("SPECULAR")) {
            return 5;
        }
        return s.equalsIgnoreCase("TRANSPARENCY") ? 9 : 0;
    }

    protected int parseType(String s) {
        if(s.equals("TRIANGLES")) {
            return 4;
        }
        if(s.equals("LINES")) {
            return 1;
        }
        if(s.equals("POINTS")) {
            return 0;
        }
        if(s.equals("TRIANGLE_STRIP")) {
            return 5;
        }
        if(!s.equals("LINE_STRIP")) {
            throw new GdxRuntimeException("Unknown primitive type \'" + s + "\', should be one of triangle, trianglestrip, line, linestrip, lineloop or point");
        }
        return 3;
    }

    protected Vector2 readVector2(JsonValue jsonValue0, float f, float f1) {
        if(jsonValue0 == null) {
            return new Vector2(f, f1);
        }
        if(jsonValue0.size != 2) {
            throw new GdxRuntimeException("Expected Vector2 values <> than two.");
        }
        return new Vector2(jsonValue0.getFloat(0), jsonValue0.getFloat(1));
    }
}

