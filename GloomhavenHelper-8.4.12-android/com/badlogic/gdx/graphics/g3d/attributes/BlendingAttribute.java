package com.badlogic.gdx.graphics.g3d.attributes;

import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.NumberUtils;

public class BlendingAttribute extends Attribute {
    public static final String Alias = "blended";
    public static final long Type;
    public boolean blended;
    public int destFunction;
    public float opacity;
    public int sourceFunction;

    static {
        BlendingAttribute.Type = BlendingAttribute.register("blended");
    }

    public BlendingAttribute() {
        this(null);
    }

    public BlendingAttribute(float f) {
        this(true, f);
    }

    public BlendingAttribute(int v, int v1) {
        this(v, v1, 1.0f);
    }

    public BlendingAttribute(int v, int v1, float f) {
        this(true, v, v1, f);
    }

    // 去混淆评级： 低(20)
    public BlendingAttribute(BlendingAttribute blendingAttribute0) {
        this(blendingAttribute0 == null || blendingAttribute0.blended, (blendingAttribute0 == null ? 770 : blendingAttribute0.sourceFunction), (blendingAttribute0 == null ? 0x303 : blendingAttribute0.destFunction), (blendingAttribute0 == null ? 1.0f : blendingAttribute0.opacity));
    }

    public BlendingAttribute(boolean z, float f) {
        this(z, 770, 0x303, f);
    }

    public BlendingAttribute(boolean z, int v, int v1, float f) {
        super(BlendingAttribute.Type);
        this.blended = z;
        this.sourceFunction = v;
        this.destFunction = v1;
        this.opacity = f;
    }

    public int compareTo(Attribute attribute0) {
        if(this.type != attribute0.type) {
            return (int)(this.type - attribute0.type);
        }
        boolean z = this.blended;
        if(z != ((BlendingAttribute)attribute0).blended) {
            return z ? 1 : -1;
        }
        int v = this.sourceFunction;
        int v1 = ((BlendingAttribute)attribute0).sourceFunction;
        if(v != v1) {
            return v - v1;
        }
        int v2 = this.destFunction;
        int v3 = ((BlendingAttribute)attribute0).destFunction;
        if(v2 != v3) {
            return v2 - v3;
        }
        if(MathUtils.isEqual(this.opacity, ((BlendingAttribute)attribute0).opacity)) {
            return 0;
        }
        return this.opacity < ((BlendingAttribute)attribute0).opacity ? 1 : -1;
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((Attribute)object0));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Attribute
    public Attribute copy() {
        return this.copy();
    }

    public BlendingAttribute copy() {
        return new BlendingAttribute(this);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Attribute
    public int hashCode() {
        int v = NumberUtils.floatToRawIntBits(this.opacity);
        return (((super.hashCode() * 947 + this.blended) * 947 + this.sourceFunction) * 947 + this.destFunction) * 947 + v;
    }

    public static final boolean is(long v) {
        return (BlendingAttribute.Type & v) == v;
    }
}

