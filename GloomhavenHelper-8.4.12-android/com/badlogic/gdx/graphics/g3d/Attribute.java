package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.utils.Array;

public abstract class Attribute implements Comparable {
    public final long type;
    private final int typeBit;
    private static final Array types;

    static {
        Attribute.types = new Array();
    }

    protected Attribute(long v) {
        this.type = v;
        this.typeBit = Long.numberOfTrailingZeros(v);
    }

    public abstract Attribute copy();

    protected boolean equals(Attribute attribute0) {
        return attribute0.hashCode() == this.hashCode();
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == null) {
            return false;
        }
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof Attribute)) {
            return false;
        }
        return this.type == ((Attribute)object0).type ? this.equals(((Attribute)object0)) : false;
    }

    public static final String getAttributeAlias(long v) {
        int v1 = -1;
        while(v != 0L) {
            ++v1;
            if(v1 >= 0x3F || (v >> v1 & 1L) != 0L) {
                break;
            }
        }
        return v1 < 0 || v1 >= Attribute.types.size ? null : ((String)Attribute.types.get(v1));
    }

    public static final long getAttributeType(String s) {
        for(int v = 0; v < Attribute.types.size; ++v) {
            if(((String)Attribute.types.get(v)).compareTo(s) == 0) {
                return 1L << v;
            }
        }
        return 0L;
    }

    @Override
    public int hashCode() {
        return this.typeBit * 7489;
    }

    protected static final long register(String s) {
        long v = Attribute.getAttributeType(s);
        if(v > 0L) {
            return v;
        }
        Attribute.types.add(s);
        return 1L << Attribute.types.size - 1;
    }

    @Override
    public String toString() {
        return Attribute.getAttributeAlias(this.type);
    }
}

