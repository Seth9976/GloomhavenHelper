package com.badlogic.gdx.graphics.g3d.loader;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMaterial;
import com.badlogic.gdx.graphics.g3d.model.data.ModelTexture;
import com.badlogic.gdx.utils.Array;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class MtlLoader {
    public Array materials;

    MtlLoader() {
        this.materials = new Array();
    }

    public ModelMaterial getMaterial(String s) {
        for(Object object0: this.materials) {
            ModelMaterial modelMaterial0 = (ModelMaterial)object0;
            if(modelMaterial0.id.equals(s)) {
                return modelMaterial0;
            }
            if(false) {
                break;
            }
        }
        ModelMaterial modelMaterial1 = new ModelMaterial();
        modelMaterial1.id = s;
        modelMaterial1.diffuse = new Color(Color.WHITE);
        this.materials.add(modelMaterial1);
        return modelMaterial1;
    }

    public void load(FileHandle fileHandle0) {
        String s = "default";
        Color color0 = Color.WHITE;
        Color color1 = Color.WHITE;
        if(fileHandle0 != null && fileHandle0.exists()) {
            BufferedReader bufferedReader0 = new BufferedReader(new InputStreamReader(fileHandle0.read()), 0x1000);
            String s1 = null;
            float f = 1.0f;
            float f1 = 0.0f;
            try {
                String s2;
                while((s2 = bufferedReader0.readLine()) != null) {
                    if(s2.length() > 0 && s2.charAt(0) == 9) {
                        s2 = s2.substring(1).trim();
                    }
                    String[] arr_s = s2.split("\\s+");
                    if(arr_s[0].length() != 0 && arr_s[0].charAt(0) != 35) {
                        String s3 = arr_s[0].toLowerCase();
                        if(s3.equals("newmtl")) {
                            ModelMaterial modelMaterial0 = new ModelMaterial();
                            modelMaterial0.id = s;
                            modelMaterial0.diffuse = new Color(color0);
                            modelMaterial0.specular = new Color(color1);
                            modelMaterial0.opacity = f;
                            modelMaterial0.shininess = f1;
                            if(s1 != null) {
                                ModelTexture modelTexture0 = new ModelTexture();
                                modelTexture0.usage = 2;
                                modelTexture0.fileName = new String(s1);
                                if(modelMaterial0.textures == null) {
                                    modelMaterial0.textures = new Array(1);
                                }
                                modelMaterial0.textures.add(modelTexture0);
                            }
                            this.materials.add(modelMaterial0);
                            s = arr_s.length <= 1 ? "default" : arr_s[1].replace('.', '_');
                            color0 = Color.WHITE;
                            color1 = Color.WHITE;
                            f = 1.0f;
                            f1 = 0.0f;
                        }
                        else if(s3.equals("kd") || s3.equals("ks")) {
                            float f2 = Float.parseFloat(arr_s[1]);
                            float f3 = Float.parseFloat(arr_s[2]);
                            float f4 = Float.parseFloat(arr_s[3]);
                            float f5 = arr_s.length <= 4 ? 1.0f : Float.parseFloat(arr_s[4]);
                            if(arr_s[0].toLowerCase().equals("kd")) {
                                color0 = new Color();
                                color0.set(f2, f3, f4, f5);
                            }
                            else {
                                color1 = new Color();
                                color1.set(f2, f3, f4, f5);
                            }
                        }
                        else if(s3.equals("tr") || s3.equals("d")) {
                            f = Float.parseFloat(arr_s[1]);
                        }
                        else if(s3.equals("ns")) {
                            f1 = Float.parseFloat(arr_s[1]);
                        }
                        else {
                            if(!s3.equals("map_kd")) {
                                continue;
                            }
                            s1 = fileHandle0.parent().child(arr_s[1]).path();
                        }
                    }
                }
                bufferedReader0.close();
            }
            catch(IOException unused_ex) {
                return;
            }
            ModelMaterial modelMaterial1 = new ModelMaterial();
            modelMaterial1.id = s;
            modelMaterial1.diffuse = new Color(color0);
            modelMaterial1.specular = new Color(color1);
            modelMaterial1.opacity = f;
            modelMaterial1.shininess = f1;
            if(s1 != null) {
                ModelTexture modelTexture1 = new ModelTexture();
                modelTexture1.usage = 2;
                modelTexture1.fileName = new String(s1);
                if(modelMaterial1.textures == null) {
                    modelMaterial1.textures = new Array(1);
                }
                modelMaterial1.textures.add(modelTexture1);
            }
            this.materials.add(modelMaterial1);
        }
    }
}

