package com.badlogic.gdx.graphics.g3d.attributes;

import com.badlogic.gdx.graphics.Cubemap;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.utils.TextureDescriptor;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class CubemapAttribute extends Attribute {
    public static final long EnvironmentMap = 0L;
    public static final String EnvironmentMapAlias = "environmentCubemap";
    protected static long Mask;
    public final TextureDescriptor textureDescription;

    static {
        CubemapAttribute.EnvironmentMap = CubemapAttribute.register("environmentCubemap");
        CubemapAttribute.Mask = CubemapAttribute.EnvironmentMap;
    }

    public CubemapAttribute(long v) {
        super(v);
        if(!CubemapAttribute.is(v)) {
            throw new GdxRuntimeException("Invalid type specified");
        }
        this.textureDescription = new TextureDescriptor();
    }

    public CubemapAttribute(long v, Cubemap cubemap0) {
        this(v);
        this.textureDescription.texture = cubemap0;
    }

    public CubemapAttribute(long v, TextureDescriptor textureDescriptor0) {
        this(v);
        this.textureDescription.set(textureDescriptor0);
    }

    public CubemapAttribute(CubemapAttribute cubemapAttribute0) {
        this(cubemapAttribute0.type, cubemapAttribute0.textureDescription);
    }

    public int compareTo(Attribute attribute0) {
        return this.type == attribute0.type ? this.textureDescription.compareTo(((CubemapAttribute)attribute0).textureDescription) : ((int)(this.type - attribute0.type));
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((Attribute)object0));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Attribute
    public Attribute copy() {
        return new CubemapAttribute(this);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Attribute
    public int hashCode() {
        return super.hashCode() * 967 + this.textureDescription.hashCode();
    }

    public static final boolean is(long v) {
        return (v & CubemapAttribute.Mask) != 0L;
    }
}

