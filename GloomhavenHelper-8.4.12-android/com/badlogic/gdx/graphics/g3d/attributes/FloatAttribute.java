package com.badlogic.gdx.graphics.g3d.attributes;

import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.NumberUtils;

public class FloatAttribute extends Attribute {
    public static final long AlphaTest = 0L;
    public static final String AlphaTestAlias = "alphaTest";
    public static final long Shininess = 0L;
    public static final String ShininessAlias = "shininess";
    public float value;

    static {
        FloatAttribute.Shininess = FloatAttribute.register("shininess");
        FloatAttribute.AlphaTest = FloatAttribute.register("alphaTest");
    }

    public FloatAttribute(long v) {
        super(v);
    }

    public FloatAttribute(long v, float f) {
        super(v);
        this.value = f;
    }

    public int compareTo(Attribute attribute0) {
        if(this.type != attribute0.type) {
            return (int)(this.type - attribute0.type);
        }
        float f = ((FloatAttribute)attribute0).value;
        if(MathUtils.isEqual(this.value, f)) {
            return 0;
        }
        return this.value < f ? -1 : 1;
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((Attribute)object0));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Attribute
    public Attribute copy() {
        return new FloatAttribute(this.type, this.value);
    }

    public static FloatAttribute createAlphaTest(float f) {
        return new FloatAttribute(FloatAttribute.AlphaTest, f);
    }

    public static FloatAttribute createShininess(float f) {
        return new FloatAttribute(FloatAttribute.Shininess, f);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Attribute
    public int hashCode() {
        int v = NumberUtils.floatToRawIntBits(this.value);
        return super.hashCode() * 977 + v;
    }
}

