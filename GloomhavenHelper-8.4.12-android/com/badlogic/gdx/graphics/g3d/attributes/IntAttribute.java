package com.badlogic.gdx.graphics.g3d.attributes;

import com.badlogic.gdx.graphics.g3d.Attribute;

public class IntAttribute extends Attribute {
    public static final long CullFace = 0L;
    public static final String CullFaceAlias = "cullface";
    public int value;

    static {
        IntAttribute.CullFace = IntAttribute.register("cullface");
    }

    public IntAttribute(long v) {
        super(v);
    }

    public IntAttribute(long v, int v1) {
        super(v);
        this.value = v1;
    }

    public int compareTo(Attribute attribute0) {
        return this.type == attribute0.type ? this.value - ((IntAttribute)attribute0).value : ((int)(this.type - attribute0.type));
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((Attribute)object0));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Attribute
    public Attribute copy() {
        return new IntAttribute(this.type, this.value);
    }

    public static IntAttribute createCullFace(int v) {
        return new IntAttribute(IntAttribute.CullFace, v);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Attribute
    public int hashCode() {
        return super.hashCode() * 983 + this.value;
    }
}

