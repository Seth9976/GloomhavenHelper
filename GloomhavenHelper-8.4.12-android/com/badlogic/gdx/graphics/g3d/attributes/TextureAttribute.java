package com.badlogic.gdx.graphics.g3d.attributes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.utils.TextureDescriptor;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.NumberUtils;

public class TextureAttribute extends Attribute {
    public static final long Ambient = 0L;
    public static final String AmbientAlias = "ambientTexture";
    public static final long Bump = 0L;
    public static final String BumpAlias = "bumpTexture";
    public static final long Diffuse = 0L;
    public static final String DiffuseAlias = "diffuseTexture";
    public static final long Emissive = 0L;
    public static final String EmissiveAlias = "emissiveTexture";
    protected static long Mask = 0L;
    public static final long Normal = 0L;
    public static final String NormalAlias = "normalTexture";
    public static final long Reflection = 0L;
    public static final String ReflectionAlias = "reflectionTexture";
    public static final long Specular = 0L;
    public static final String SpecularAlias = "specularTexture";
    public float offsetU;
    public float offsetV;
    public float scaleU;
    public float scaleV;
    public final TextureDescriptor textureDescription;
    public int uvIndex;

    static {
        TextureAttribute.Diffuse = TextureAttribute.register("diffuseTexture");
        TextureAttribute.Specular = TextureAttribute.register("specularTexture");
        TextureAttribute.Bump = TextureAttribute.register("bumpTexture");
        TextureAttribute.Normal = TextureAttribute.register("normalTexture");
        TextureAttribute.Ambient = TextureAttribute.register("ambientTexture");
        TextureAttribute.Emissive = TextureAttribute.register("emissiveTexture");
        TextureAttribute.Reflection = TextureAttribute.register("reflectionTexture");
        TextureAttribute.Mask = TextureAttribute.Diffuse | TextureAttribute.Specular | TextureAttribute.Bump | TextureAttribute.Normal | TextureAttribute.Ambient | TextureAttribute.Emissive | TextureAttribute.Reflection;
    }

    public TextureAttribute(long v) {
        super(v);
        this.offsetU = 0.0f;
        this.offsetV = 0.0f;
        this.scaleU = 1.0f;
        this.scaleV = 1.0f;
        this.uvIndex = 0;
        if(!TextureAttribute.is(v)) {
            throw new GdxRuntimeException("Invalid type specified");
        }
        this.textureDescription = new TextureDescriptor();
    }

    public TextureAttribute(long v, Texture texture0) {
        this(v);
        this.textureDescription.texture = texture0;
    }

    public TextureAttribute(long v, TextureRegion textureRegion0) {
        this(v);
        this.set(textureRegion0);
    }

    public TextureAttribute(long v, TextureDescriptor textureDescriptor0) {
        this(v);
        this.textureDescription.set(textureDescriptor0);
    }

    public TextureAttribute(long v, TextureDescriptor textureDescriptor0, float f, float f1, float f2, float f3) {
        this(v, textureDescriptor0, f, f1, f2, f3, 0);
    }

    public TextureAttribute(long v, TextureDescriptor textureDescriptor0, float f, float f1, float f2, float f3, int v1) {
        this(v, textureDescriptor0);
        this.offsetU = f;
        this.offsetV = f1;
        this.scaleU = f2;
        this.scaleV = f3;
        this.uvIndex = v1;
    }

    public TextureAttribute(TextureAttribute textureAttribute0) {
        this(textureAttribute0.type, textureAttribute0.textureDescription, textureAttribute0.offsetU, textureAttribute0.offsetV, textureAttribute0.scaleU, textureAttribute0.scaleV, textureAttribute0.uvIndex);
    }

    public int compareTo(Attribute attribute0) {
        if(this.type != attribute0.type) {
            return this.type < attribute0.type ? -1 : 1;
        }
        int v = this.textureDescription.compareTo(((TextureAttribute)attribute0).textureDescription);
        if(v != 0) {
            return v;
        }
        int v1 = this.uvIndex;
        int v2 = ((TextureAttribute)attribute0).uvIndex;
        if(v1 != v2) {
            return v1 - v2;
        }
        if(!MathUtils.isEqual(this.scaleU, ((TextureAttribute)attribute0).scaleU)) {
            return this.scaleU > ((TextureAttribute)attribute0).scaleU ? 1 : -1;
        }
        if(!MathUtils.isEqual(this.scaleV, ((TextureAttribute)attribute0).scaleV)) {
            return this.scaleV > ((TextureAttribute)attribute0).scaleV ? 1 : -1;
        }
        if(!MathUtils.isEqual(this.offsetU, ((TextureAttribute)attribute0).offsetU)) {
            return this.offsetU > ((TextureAttribute)attribute0).offsetU ? 1 : -1;
        }
        if(!MathUtils.isEqual(this.offsetV, ((TextureAttribute)attribute0).offsetV)) {
            return this.offsetV > ((TextureAttribute)attribute0).offsetV ? 1 : -1;
        }
        return 0;
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((Attribute)object0));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Attribute
    public Attribute copy() {
        return new TextureAttribute(this);
    }

    public static TextureAttribute createAmbient(Texture texture0) {
        return new TextureAttribute(TextureAttribute.Ambient, texture0);
    }

    public static TextureAttribute createAmbient(TextureRegion textureRegion0) {
        return new TextureAttribute(TextureAttribute.Ambient, textureRegion0);
    }

    public static TextureAttribute createBump(Texture texture0) {
        return new TextureAttribute(TextureAttribute.Bump, texture0);
    }

    public static TextureAttribute createBump(TextureRegion textureRegion0) {
        return new TextureAttribute(TextureAttribute.Bump, textureRegion0);
    }

    public static TextureAttribute createDiffuse(Texture texture0) {
        return new TextureAttribute(TextureAttribute.Diffuse, texture0);
    }

    public static TextureAttribute createDiffuse(TextureRegion textureRegion0) {
        return new TextureAttribute(TextureAttribute.Diffuse, textureRegion0);
    }

    public static TextureAttribute createEmissive(Texture texture0) {
        return new TextureAttribute(TextureAttribute.Emissive, texture0);
    }

    public static TextureAttribute createEmissive(TextureRegion textureRegion0) {
        return new TextureAttribute(TextureAttribute.Emissive, textureRegion0);
    }

    public static TextureAttribute createNormal(Texture texture0) {
        return new TextureAttribute(TextureAttribute.Normal, texture0);
    }

    public static TextureAttribute createNormal(TextureRegion textureRegion0) {
        return new TextureAttribute(TextureAttribute.Normal, textureRegion0);
    }

    public static TextureAttribute createReflection(Texture texture0) {
        return new TextureAttribute(TextureAttribute.Reflection, texture0);
    }

    public static TextureAttribute createReflection(TextureRegion textureRegion0) {
        return new TextureAttribute(TextureAttribute.Reflection, textureRegion0);
    }

    public static TextureAttribute createSpecular(Texture texture0) {
        return new TextureAttribute(TextureAttribute.Specular, texture0);
    }

    public static TextureAttribute createSpecular(TextureRegion textureRegion0) {
        return new TextureAttribute(TextureAttribute.Specular, textureRegion0);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Attribute
    public int hashCode() {
        int v = NumberUtils.floatToRawIntBits(this.offsetU);
        int v1 = NumberUtils.floatToRawIntBits(this.offsetV);
        int v2 = NumberUtils.floatToRawIntBits(this.scaleU);
        int v3 = NumberUtils.floatToRawIntBits(this.scaleV);
        return (((((super.hashCode() * 0x3DF + this.textureDescription.hashCode()) * 0x3DF + v) * 0x3DF + v1) * 0x3DF + v2) * 0x3DF + v3) * 0x3DF + this.uvIndex;
    }

    public static final boolean is(long v) {
        return (v & TextureAttribute.Mask) != 0L;
    }

    public void set(TextureRegion textureRegion0) {
        this.textureDescription.texture = textureRegion0.getTexture();
        this.offsetU = textureRegion0.getU();
        this.offsetV = textureRegion0.getV();
        this.scaleU = textureRegion0.getU2() - this.offsetU;
        this.scaleV = textureRegion0.getV2() - this.offsetV;
    }
}

