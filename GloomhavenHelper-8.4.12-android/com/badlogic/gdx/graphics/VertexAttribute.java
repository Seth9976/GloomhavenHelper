package com.badlogic.gdx.graphics;

public final class VertexAttribute {
    public String alias;
    public final boolean normalized;
    public final int numComponents;
    public int offset;
    public final int type;
    public int unit;
    public final int usage;
    private final int usageIndex;

    public VertexAttribute(int v, int v1, int v2, boolean z, String s) {
        this(v, v1, v2, z, s, 0);
    }

    public VertexAttribute(int v, int v1, int v2, boolean z, String s, int v3) {
        this.usage = v;
        this.numComponents = v1;
        this.type = v2;
        this.normalized = z;
        this.alias = s;
        this.unit = v3;
        this.usageIndex = Integer.numberOfTrailingZeros(v);
    }

    public VertexAttribute(int v, int v1, String s) {
        this(v, v1, s, 0);
    }

    public VertexAttribute(int v, int v1, String s, int v2) {
        this(v, v1, (v == 4 ? 0x1401 : 0x1406), v == 4, s, v2);
    }

    public static VertexAttribute Binormal() {
        return new VertexAttribute(0x100, 3, "a_binormal");
    }

    public static VertexAttribute BoneWeight(int v) {
        return new VertexAttribute(0x40, 2, "a_boneWeight" + v, v);
    }

    public static VertexAttribute ColorPacked() {
        return new VertexAttribute(4, 4, 0x1401, true, "a_color");
    }

    public static VertexAttribute ColorUnpacked() {
        return new VertexAttribute(2, 4, 0x1406, false, "a_color");
    }

    public static VertexAttribute Normal() {
        return new VertexAttribute(8, 3, "a_normal");
    }

    public static VertexAttribute Position() {
        return new VertexAttribute(1, 3, "a_position");
    }

    public static VertexAttribute Tangent() {
        return new VertexAttribute(0x80, 3, "a_tangent");
    }

    public static VertexAttribute TexCoords(int v) {
        return new VertexAttribute(16, 2, "a_texCoord" + v, v);
    }

    public VertexAttribute copy() {
        return new VertexAttribute(this.usage, this.numComponents, this.type, this.normalized, this.alias, this.unit);
    }

    public boolean equals(VertexAttribute vertexAttribute0) {
        return vertexAttribute0 != null && this.usage == vertexAttribute0.usage && this.numComponents == vertexAttribute0.numComponents && this.type == vertexAttribute0.type && this.normalized == vertexAttribute0.normalized && this.alias.equals(vertexAttribute0.alias) && this.unit == vertexAttribute0.unit;
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof VertexAttribute ? this.equals(((VertexAttribute)object0)) : false;
    }

    public int getKey() {
        return (this.usageIndex << 8) + (this.unit & 0xFF);
    }

    public int getSizeInBytes() {
        int v = this.type;
        if(v != 0x1406 && v != 0x140C) {
            switch(v) {
                case 0x1400: 
                case 0x1401: {
                    return this.numComponents;
                }
                case 0x1402: 
                case 0x1403: {
                    return this.numComponents * 2;
                }
                default: {
                    return 0;
                }
            }
        }
        return this.numComponents * 4;
    }

    @Override
    public int hashCode() {
        return (this.getKey() * 541 + this.numComponents) * 541 + this.alias.hashCode();
    }
}

