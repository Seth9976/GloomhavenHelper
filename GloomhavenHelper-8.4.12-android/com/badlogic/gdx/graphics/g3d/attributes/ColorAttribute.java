package com.badlogic.gdx.graphics.g3d.attributes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class ColorAttribute extends Attribute {
    public static final long Ambient = 0L;
    public static final String AmbientAlias = "ambientColor";
    public static final long AmbientLight = 0L;
    public static final String AmbientLightAlias = "ambientLightColor";
    public static final long Diffuse = 0L;
    public static final String DiffuseAlias = "diffuseColor";
    public static final long Emissive = 0L;
    public static final String EmissiveAlias = "emissiveColor";
    public static final long Fog = 0L;
    public static final String FogAlias = "fogColor";
    protected static long Mask = 0L;
    public static final long Reflection = 0L;
    public static final String ReflectionAlias = "reflectionColor";
    public static final long Specular = 0L;
    public static final String SpecularAlias = "specularColor";
    public final Color color;

    static {
        ColorAttribute.Diffuse = ColorAttribute.register("diffuseColor");
        ColorAttribute.Specular = ColorAttribute.register("specularColor");
        ColorAttribute.Ambient = ColorAttribute.register("ambientColor");
        ColorAttribute.Emissive = ColorAttribute.register("emissiveColor");
        ColorAttribute.Reflection = ColorAttribute.register("reflectionColor");
        ColorAttribute.AmbientLight = ColorAttribute.register("ambientLightColor");
        ColorAttribute.Fog = ColorAttribute.register("fogColor");
        ColorAttribute.Mask = ColorAttribute.Ambient | ColorAttribute.Diffuse | ColorAttribute.Specular | ColorAttribute.Emissive | ColorAttribute.Reflection | ColorAttribute.AmbientLight | ColorAttribute.Fog;
    }

    public ColorAttribute(long v) {
        super(v);
        this.color = new Color();
        if(!ColorAttribute.is(v)) {
            throw new GdxRuntimeException("Invalid type specified");
        }
    }

    public ColorAttribute(long v, float f, float f1, float f2, float f3) {
        this(v);
        this.color.set(f, f1, f2, f3);
    }

    public ColorAttribute(long v, Color color0) {
        this(v);
        if(color0 != null) {
            this.color.set(color0);
        }
    }

    public ColorAttribute(ColorAttribute colorAttribute0) {
        this(colorAttribute0.type, colorAttribute0.color);
    }

    public int compareTo(Attribute attribute0) {
        return this.type == attribute0.type ? ((ColorAttribute)attribute0).color.toIntBits() - this.color.toIntBits() : ((int)(this.type - attribute0.type));
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((Attribute)object0));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Attribute
    public Attribute copy() {
        return new ColorAttribute(this);
    }

    public static final ColorAttribute createAmbient(float f, float f1, float f2, float f3) {
        return new ColorAttribute(ColorAttribute.Ambient, f, f1, f2, f3);
    }

    public static final ColorAttribute createAmbient(Color color0) {
        return new ColorAttribute(ColorAttribute.Ambient, color0);
    }

    public static final ColorAttribute createAmbientLight(float f, float f1, float f2, float f3) {
        return new ColorAttribute(ColorAttribute.AmbientLight, f, f1, f2, f3);
    }

    public static final ColorAttribute createAmbientLight(Color color0) {
        return new ColorAttribute(ColorAttribute.AmbientLight, color0);
    }

    public static final ColorAttribute createDiffuse(float f, float f1, float f2, float f3) {
        return new ColorAttribute(ColorAttribute.Diffuse, f, f1, f2, f3);
    }

    public static final ColorAttribute createDiffuse(Color color0) {
        return new ColorAttribute(ColorAttribute.Diffuse, color0);
    }

    public static final ColorAttribute createEmissive(float f, float f1, float f2, float f3) {
        return new ColorAttribute(ColorAttribute.Emissive, f, f1, f2, f3);
    }

    public static final ColorAttribute createEmissive(Color color0) {
        return new ColorAttribute(ColorAttribute.Emissive, color0);
    }

    public static final ColorAttribute createFog(float f, float f1, float f2, float f3) {
        return new ColorAttribute(ColorAttribute.Fog, f, f1, f2, f3);
    }

    public static final ColorAttribute createFog(Color color0) {
        return new ColorAttribute(ColorAttribute.Fog, color0);
    }

    public static final ColorAttribute createReflection(float f, float f1, float f2, float f3) {
        return new ColorAttribute(ColorAttribute.Reflection, f, f1, f2, f3);
    }

    public static final ColorAttribute createReflection(Color color0) {
        return new ColorAttribute(ColorAttribute.Reflection, color0);
    }

    public static final ColorAttribute createSpecular(float f, float f1, float f2, float f3) {
        return new ColorAttribute(ColorAttribute.Specular, f, f1, f2, f3);
    }

    public static final ColorAttribute createSpecular(Color color0) {
        return new ColorAttribute(ColorAttribute.Specular, color0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Attribute
    public int hashCode() {
        return super.hashCode() * 953 + this.color.toIntBits();
    }

    public static final boolean is(long v) {
        return (v & ColorAttribute.Mask) != 0L;
    }
}

