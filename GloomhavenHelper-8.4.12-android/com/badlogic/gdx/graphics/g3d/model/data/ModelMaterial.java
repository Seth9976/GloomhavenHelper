package com.badlogic.gdx.graphics.g3d.model.data;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

public class ModelMaterial {
    public static enum MaterialType {
        Lambert,
        Phong;

    }

    public Color ambient;
    public Color diffuse;
    public Color emissive;
    public String id;
    public float opacity;
    public Color reflection;
    public float shininess;
    public Color specular;
    public Array textures;
    public MaterialType type;

    public ModelMaterial() {
        this.opacity = 1.0f;
    }
}

