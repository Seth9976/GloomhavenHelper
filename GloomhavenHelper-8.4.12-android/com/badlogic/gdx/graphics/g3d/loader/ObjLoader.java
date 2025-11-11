package com.badlogic.gdx.graphics.g3d.loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.ModelLoader.ModelParameters;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.model.data.ModelData;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMaterial;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMesh;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMeshPart;
import com.badlogic.gdx.graphics.g3d.model.data.ModelNode;
import com.badlogic.gdx.graphics.g3d.model.data.ModelNodePart;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ObjLoader extends ModelLoader {
    class Group {
        Array faces;
        boolean hasNorms;
        boolean hasUVs;
        Material mat;
        String materialName;
        final String name;
        int numFaces;

        Group(String s) {
            this.name = s;
            this.faces = new Array(200);
            this.numFaces = 0;
            this.mat = new Material("");
            this.materialName = "default";
        }
    }

    public static class ObjLoaderParameters extends ModelParameters {
        public boolean flipV;

        public ObjLoaderParameters() {
        }

        public ObjLoaderParameters(boolean z) {
            this.flipV = z;
        }
    }

    final Array groups;
    public static boolean logWarning = false;
    final FloatArray norms;
    final FloatArray uvs;
    final FloatArray verts;

    static {
    }

    public ObjLoader() {
        this(null);
    }

    public ObjLoader(FileHandleResolver fileHandleResolver0) {
        super(fileHandleResolver0);
        this.verts = new FloatArray(300);
        this.norms = new FloatArray(300);
        this.uvs = new FloatArray(200);
        this.groups = new Array(10);
    }

    private int getIndex(String s, int v) {
        if(s != null && s.length() != 0) {
            int v1 = Integer.parseInt(s);
            return v1 >= 0 ? v1 - 1 : v + v1;
        }
        return 0;
    }

    public Model loadModel(FileHandle fileHandle0, boolean z) {
        return this.loadModel(fileHandle0, new ObjLoaderParameters(z));
    }

    @Override  // com.badlogic.gdx.assets.loaders.ModelLoader
    public ModelData loadModelData(FileHandle fileHandle0, ModelParameters modelLoader$ModelParameters0) {
        return this.loadModelData(fileHandle0, ((ObjLoaderParameters)modelLoader$ModelParameters0));
    }

    public ModelData loadModelData(FileHandle fileHandle0, ObjLoaderParameters objLoader$ObjLoaderParameters0) {
        return objLoader$ObjLoaderParameters0 == null || !objLoader$ObjLoaderParameters0.flipV ? this.loadModelData(fileHandle0, false) : this.loadModelData(fileHandle0, true);
    }

    protected ModelData loadModelData(FileHandle fileHandle0, boolean z) {
        if(ObjLoader.logWarning) {
            Gdx.app.error("ObjLoader", "Wavefront (OBJ) is not fully supported, consult the documentation for more information");
        }
        MtlLoader mtlLoader0 = new MtlLoader();
        Group objLoader$Group0 = new Group(this, "default");
        this.groups.add(objLoader$Group0);
        BufferedReader bufferedReader0 = new BufferedReader(new InputStreamReader(fileHandle0.read()), 0x1000);
        try {
            String s;
            while((s = bufferedReader0.readLine()) != null) {
                String[] arr_s = s.split("\\s+");
                if(arr_s.length < 1) {
                    break;
                }
                if(arr_s[0].length() != 0) {
                    switch(arr_s[0].toLowerCase().charAt(0)) {
                        case 35: {
                            continue;
                        }
                        case 102: {
                            goto label_44;
                        }
                        case 103: 
                        case 0x6F: {
                            goto label_73;
                        }
                        case 0x76: {
                            goto label_21;
                        }
                    }
                    if(arr_s[0].equals("mtllib")) {
                        mtlLoader0.load(fileHandle0.parent().child(arr_s[1]));
                    }
                    else {
                        if(!arr_s[0].equals("usemtl")) {
                            continue;
                        }
                        objLoader$Group0.materialName = arr_s.length == 1 ? "default" : arr_s[1].replace('.', '_');
                    }
                    continue;
                label_21:
                    if(arr_s[0].length() == 1) {
                        float f = Float.parseFloat(arr_s[1]);
                        this.verts.add(f);
                        float f1 = Float.parseFloat(arr_s[2]);
                        this.verts.add(f1);
                        float f2 = Float.parseFloat(arr_s[3]);
                        this.verts.add(f2);
                    }
                    else {
                        switch(arr_s[0].charAt(1)) {
                            case 110: {
                                float f3 = Float.parseFloat(arr_s[1]);
                                this.norms.add(f3);
                                float f4 = Float.parseFloat(arr_s[2]);
                                this.norms.add(f4);
                                float f5 = Float.parseFloat(arr_s[3]);
                                this.norms.add(f5);
                                break;
                            }
                            case 0x74: {
                                float f6 = Float.parseFloat(arr_s[1]);
                                this.uvs.add(f6);
                                float f7 = z ? 1.0f - Float.parseFloat(arr_s[2]) : Float.parseFloat(arr_s[2]);
                                this.uvs.add(f7);
                            }
                        }
                    }
                    continue;
                label_44:
                    Array array0 = objLoader$Group0.faces;
                    for(int v = 1; v < arr_s.length - 2; ++v) {
                        String[] arr_s1 = arr_s[1].split("/");
                        array0.add(this.getIndex(arr_s1[0], this.verts.size));
                        if(arr_s1.length > 2) {
                            if(v == 1) {
                                objLoader$Group0.hasNorms = true;
                            }
                            array0.add(this.getIndex(arr_s1[2], this.norms.size));
                        }
                        if(arr_s1.length > 1 && arr_s1[1].length() > 0) {
                            if(v == 1) {
                                objLoader$Group0.hasUVs = true;
                            }
                            array0.add(this.getIndex(arr_s1[1], this.uvs.size));
                        }
                        String[] arr_s2 = arr_s[v + 1].split("/");
                        array0.add(this.getIndex(arr_s2[0], this.verts.size));
                        if(arr_s2.length > 2) {
                            array0.add(this.getIndex(arr_s2[2], this.norms.size));
                        }
                        if(arr_s2.length > 1 && arr_s2[1].length() > 0) {
                            array0.add(this.getIndex(arr_s2[1], this.uvs.size));
                        }
                        String[] arr_s3 = arr_s[v + 2].split("/");
                        array0.add(this.getIndex(arr_s3[0], this.verts.size));
                        if(arr_s3.length > 2) {
                            array0.add(this.getIndex(arr_s3[2], this.norms.size));
                        }
                        if(arr_s3.length > 1 && arr_s3[1].length() > 0) {
                            array0.add(this.getIndex(arr_s3[1], this.uvs.size));
                        }
                        ++objLoader$Group0.numFaces;
                    }
                    continue;
                label_73:
                    objLoader$Group0 = arr_s.length > 1 ? this.setActiveGroup(arr_s[1]) : this.setActiveGroup("default");
                }
            }
            bufferedReader0.close();
        }
        catch(IOException unused_ex) {
            return null;
        }
        for(int v1 = 0; v1 < this.groups.size; ++v1) {
            if(((Group)this.groups.get(v1)).numFaces < 1) {
                this.groups.removeIndex(v1);
                --v1;
            }
        }
        if(this.groups.size < 1) {
            return null;
        }
        int v2 = this.groups.size;
        ModelData modelData0 = new ModelData();
        int v4 = 0;
        for(int v3 = 0; v3 < v2; ++v3) {
            Group objLoader$Group1 = (Group)this.groups.get(v3);
            Array array1 = objLoader$Group1.faces;
            int v5 = array1.size;
            boolean z1 = objLoader$Group1.hasNorms;
            boolean z2 = objLoader$Group1.hasUVs;
            int v6 = objLoader$Group1.numFaces * 3;
            float[] arr_f = new float[v6 * ((z1 ? 3 : 0) + 3 + (z2 ? 2 : 0))];
            int v7 = 0;
            for(int v8 = 0; v7 < v5; v8 = v12) {
                int v9 = v7 + 1;
                int v10 = (int)(((Integer)array1.get(v7)));
                int v11 = v10 * 3 + 1;
                arr_f[v8] = this.verts.get(v10 * 3);
                arr_f[v8 + 1] = this.verts.get(v11);
                int v12 = v8 + 3;
                arr_f[v8 + 2] = this.verts.get(v11 + 1);
                if(z1) {
                    int v13 = (int)(((Integer)array1.get(v9)));
                    int v14 = v13 * 3 + 1;
                    arr_f[v12] = this.norms.get(v13 * 3);
                    arr_f[v12 + 1] = this.norms.get(v14);
                    arr_f[v12 + 2] = this.norms.get(v14 + 1);
                    ++v9;
                    v12 += 3;
                }
                if(z2) {
                    int v15 = (int)(((Integer)array1.get(v9)));
                    int v16 = v12 + 1;
                    arr_f[v12] = this.uvs.get(v15 * 2);
                    v12 = v16 + 1;
                    arr_f[v16] = this.uvs.get(v15 * 2 + 1);
                    v7 = v9 + 1;
                }
                else {
                    v7 = v9;
                }
            }
            if(v6 >= 0x7FFF) {
                v6 = 0;
            }
            short[] arr_v = new short[v6];
            if(v6 > 0) {
                for(int v17 = 0; v17 < v6; ++v17) {
                    arr_v[v17] = (short)v17;
                }
            }
            Array array2 = new Array();
            array2.add(new VertexAttribute(1, 3, "a_position"));
            if(z1) {
                array2.add(new VertexAttribute(8, 3, "a_normal"));
            }
            if(z2) {
                array2.add(new VertexAttribute(16, 2, "a_texCoord0"));
            }
            ++v4;
            String s1 = Integer.toString(v4);
            String s2 = "default".equals(objLoader$Group1.name) ? "mesh" + s1 : objLoader$Group1.name;
            String s3 = "default".equals(objLoader$Group1.name) ? "part" + s1 : objLoader$Group1.name;
            ModelNode modelNode0 = new ModelNode();
            modelNode0.id = "default".equals(objLoader$Group1.name) ? "node" + s1 : objLoader$Group1.name;
            modelNode0.meshId = s2;
            modelNode0.scale = new Vector3(1.0f, 1.0f, 1.0f);
            modelNode0.translation = new Vector3();
            modelNode0.rotation = new Quaternion();
            ModelNodePart modelNodePart0 = new ModelNodePart();
            modelNodePart0.meshPartId = s3;
            modelNodePart0.materialId = objLoader$Group1.materialName;
            modelNode0.parts = new ModelNodePart[]{modelNodePart0};
            ModelMeshPart modelMeshPart0 = new ModelMeshPart();
            modelMeshPart0.id = s3;
            modelMeshPart0.indices = arr_v;
            modelMeshPart0.primitiveType = 4;
            ModelMesh modelMesh0 = new ModelMesh();
            modelMesh0.id = s2;
            modelMesh0.attributes = (VertexAttribute[])array2.toArray(VertexAttribute.class);
            modelMesh0.vertices = arr_f;
            modelMesh0.parts = new ModelMeshPart[]{modelMeshPart0};
            modelData0.nodes.add(modelNode0);
            modelData0.meshes.add(modelMesh0);
            ModelMaterial modelMaterial0 = mtlLoader0.getMaterial(objLoader$Group1.materialName);
            modelData0.materials.add(modelMaterial0);
        }
        if(this.verts.size > 0) {
            this.verts.clear();
        }
        if(this.norms.size > 0) {
            this.norms.clear();
        }
        if(this.uvs.size > 0) {
            this.uvs.clear();
        }
        if(this.groups.size > 0) {
            this.groups.clear();
        }
        return modelData0;
    }

    private Group setActiveGroup(String s) {
        for(Object object0: this.groups) {
            Group objLoader$Group0 = (Group)object0;
            if(objLoader$Group0.name.equals(s)) {
                return objLoader$Group0;
            }
            if(false) {
                break;
            }
        }
        Group objLoader$Group1 = new Group(this, s);
        this.groups.add(objLoader$Group1);
        return objLoader$Group1;
    }
}

