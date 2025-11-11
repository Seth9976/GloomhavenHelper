package com.badlogic.gdx.graphics.g3d.attributes;

import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.NumberUtils;

public class DepthTestAttribute extends Attribute {
    public static final String Alias = "depthStencil";
    protected static long Mask;
    public static final long Type;
    public int depthFunc;
    public boolean depthMask;
    public float depthRangeFar;
    public float depthRangeNear;

    static {
        DepthTestAttribute.Type = DepthTestAttribute.register("depthStencil");
        DepthTestAttribute.Mask = DepthTestAttribute.Type;
    }

    public DepthTestAttribute() {
        this(0x203);
    }

    public DepthTestAttribute(int v) {
        this(v, true);
    }

    public DepthTestAttribute(int v, float f, float f1) {
        this(v, f, f1, true);
    }

    public DepthTestAttribute(int v, float f, float f1, boolean z) {
        this(DepthTestAttribute.Type, v, f, f1, z);
    }

    public DepthTestAttribute(int v, boolean z) {
        this(v, 0.0f, 1.0f, z);
    }

    public DepthTestAttribute(long v, int v1, float f, float f1, boolean z) {
        super(v);
        if(!DepthTestAttribute.is(v)) {
            throw new GdxRuntimeException("Invalid type specified");
        }
        this.depthFunc = v1;
        this.depthRangeNear = f;
        this.depthRangeFar = f1;
        this.depthMask = z;
    }

    public DepthTestAttribute(DepthTestAttribute depthTestAttribute0) {
        this(depthTestAttribute0.type, depthTestAttribute0.depthFunc, depthTestAttribute0.depthRangeNear, depthTestAttribute0.depthRangeFar, depthTestAttribute0.depthMask);
    }

    public DepthTestAttribute(boolean z) {
        this(0x203, z);
    }

    public int compareTo(Attribute attribute0) {
        if(this.type != attribute0.type) {
            return (int)(this.type - attribute0.type);
        }
        int v = this.depthFunc;
        int v1 = ((DepthTestAttribute)attribute0).depthFunc;
        if(v != v1) {
            return v - v1;
        }
        boolean z = this.depthMask;
        if(z != ((DepthTestAttribute)attribute0).depthMask) {
            return z ? -1 : 1;
        }
        if(!MathUtils.isEqual(this.depthRangeNear, ((DepthTestAttribute)attribute0).depthRangeNear)) {
            return this.depthRangeNear < ((DepthTestAttribute)attribute0).depthRangeNear ? -1 : 1;
        }
        if(!MathUtils.isEqual(this.depthRangeFar, ((DepthTestAttribute)attribute0).depthRangeFar)) {
            return this.depthRangeFar < ((DepthTestAttribute)attribute0).depthRangeFar ? -1 : 1;
        }
        return 0;
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((Attribute)object0));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Attribute
    public Attribute copy() {
        return new DepthTestAttribute(this);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Attribute
    public int hashCode() {
        int v = NumberUtils.floatToRawIntBits(this.depthRangeNear);
        int v1 = NumberUtils.floatToRawIntBits(this.depthRangeFar);
        return (((super.hashCode() * 971 + this.depthFunc) * 971 + v) * 971 + v1) * 971 + this.depthMask;
    }

    public static final boolean is(long v) {
        return (v & DepthTestAttribute.Mask) != 0L;
    }
}

