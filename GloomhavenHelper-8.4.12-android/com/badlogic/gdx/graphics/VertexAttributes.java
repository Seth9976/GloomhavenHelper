package com.badlogic.gdx.graphics;

import com.badlogic.gdx.utils.Collections;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class VertexAttributes implements Comparable, Iterable {
    static class ReadonlyIterable implements Iterable {
        private final Object[] array;
        private ReadonlyIterator iterator1;
        private ReadonlyIterator iterator2;

        public ReadonlyIterable(Object[] arr_object) {
            this.array = arr_object;
        }

        @Override
        public Iterator iterator() {
            if(Collections.allocateIterators) {
                return new ReadonlyIterator(this.array);
            }
            if(this.iterator1 == null) {
                this.iterator1 = new ReadonlyIterator(this.array);
                this.iterator2 = new ReadonlyIterator(this.array);
            }
            if(!this.iterator1.valid) {
                this.iterator1.index = 0;
                this.iterator1.valid = true;
                this.iterator2.valid = false;
                return this.iterator1;
            }
            this.iterator2.index = 0;
            this.iterator2.valid = true;
            this.iterator1.valid = false;
            return this.iterator2;
        }
    }

    static class ReadonlyIterator implements Iterable, Iterator {
        private final Object[] array;
        int index;
        boolean valid;

        public ReadonlyIterator(Object[] arr_object) {
            this.valid = true;
            this.array = arr_object;
        }

        @Override
        public boolean hasNext() {
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            return this.index < this.array.length;
        }

        @Override
        public Iterator iterator() {
            return this;
        }

        @Override
        public Object next() {
            int v = this.index;
            Object[] arr_object = this.array;
            if(v >= arr_object.length) {
                throw new NoSuchElementException(String.valueOf(v));
            }
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            this.index = v + 1;
            return arr_object[v];
        }

        @Override
        public void remove() {
            throw new GdxRuntimeException("Remove not allowed.");
        }

        public void reset() {
            this.index = 0;
        }
    }

    public static final class Usage {
        public static final int BiNormal = 0x100;
        public static final int BoneWeight = 0x40;
        public static final int ColorPacked = 4;
        public static final int ColorUnpacked = 2;
        public static final int Generic = 0x20;
        public static final int Normal = 8;
        public static final int Position = 1;
        public static final int Tangent = 0x80;
        public static final int TextureCoordinates = 16;

    }

    private final VertexAttribute[] attributes;
    private ReadonlyIterable iterable;
    private long mask;
    public final int vertexSize;

    public VertexAttributes(VertexAttribute[] arr_vertexAttribute) {
        this.mask = -1L;
        if(arr_vertexAttribute.length == 0) {
            throw new IllegalArgumentException("attributes must be >= 1");
        }
        VertexAttribute[] arr_vertexAttribute1 = new VertexAttribute[arr_vertexAttribute.length];
        for(int v = 0; v < arr_vertexAttribute.length; ++v) {
            arr_vertexAttribute1[v] = arr_vertexAttribute[v];
        }
        this.attributes = arr_vertexAttribute1;
        this.vertexSize = this.calculateOffsets();
    }

    private int calculateOffsets() {
        int v1 = 0;
        for(int v = 0; true; ++v) {
            VertexAttribute[] arr_vertexAttribute = this.attributes;
            if(v >= arr_vertexAttribute.length) {
                break;
            }
            VertexAttribute vertexAttribute0 = arr_vertexAttribute[v];
            vertexAttribute0.offset = v1;
            v1 += vertexAttribute0.getSizeInBytes();
        }
        return v1;
    }

    public int compareTo(VertexAttributes vertexAttributes0) {
        VertexAttribute[] arr_vertexAttribute = this.attributes;
        VertexAttribute[] arr_vertexAttribute1 = vertexAttributes0.attributes;
        if(arr_vertexAttribute.length != arr_vertexAttribute1.length) {
            return arr_vertexAttribute.length - arr_vertexAttribute1.length;
        }
        long v = this.getMask();
        long v1 = vertexAttributes0.getMask();
        if(v != v1) {
            return v < v1 ? -1 : 1;
        }
        for(int v2 = this.attributes.length - 1; v2 >= 0; --v2) {
            VertexAttribute vertexAttribute0 = this.attributes[v2];
            VertexAttribute vertexAttribute1 = vertexAttributes0.attributes[v2];
            if(vertexAttribute0.usage != vertexAttribute1.usage) {
                return vertexAttribute0.usage - vertexAttribute1.usage;
            }
            if(vertexAttribute0.unit != vertexAttribute1.unit) {
                return vertexAttribute0.unit - vertexAttribute1.unit;
            }
            if(vertexAttribute0.numComponents != vertexAttribute1.numComponents) {
                return vertexAttribute0.numComponents - vertexAttribute1.numComponents;
            }
            if(vertexAttribute0.normalized != vertexAttribute1.normalized) {
                return vertexAttribute0.normalized ? 1 : -1;
            }
            if(vertexAttribute0.type != vertexAttribute1.type) {
                return vertexAttribute0.type - vertexAttribute1.type;
            }
        }
        return 0;
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((VertexAttributes)object0));
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof VertexAttributes)) {
            return false;
        }
        if(this.attributes.length != ((VertexAttributes)object0).attributes.length) {
            return false;
        }
        for(int v = 0; true; ++v) {
            VertexAttribute[] arr_vertexAttribute = this.attributes;
            if(v >= arr_vertexAttribute.length) {
                break;
            }
            if(!arr_vertexAttribute[v].equals(((VertexAttributes)object0).attributes[v])) {
                return false;
            }
        }
        return true;
    }

    public VertexAttribute findByUsage(int v) {
        int v1 = this.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            if(this.get(v2).usage == v) {
                return this.get(v2);
            }
        }
        return null;
    }

    public VertexAttribute get(int v) {
        return this.attributes[v];
    }

    public long getMask() {
        if(this.mask == -1L) {
            long v = 0L;
            for(int v1 = 0; true; ++v1) {
                VertexAttribute[] arr_vertexAttribute = this.attributes;
                if(v1 >= arr_vertexAttribute.length) {
                    break;
                }
                v |= (long)arr_vertexAttribute[v1].usage;
            }
            this.mask = v;
        }
        return this.mask;
    }

    public long getMaskWithSizePacked() {
        return this.getMask() | ((long)this.attributes.length) << 0x20;
    }

    public int getOffset(int v) {
        return this.getOffset(v, 0);
    }

    public int getOffset(int v, int v1) {
        VertexAttribute vertexAttribute0 = this.findByUsage(v);
        return vertexAttribute0 == null ? v1 : vertexAttribute0.offset / 4;
    }

    @Override
    public int hashCode() {
        long v = (long)(this.attributes.length * 61);
        for(int v1 = 0; true; ++v1) {
            VertexAttribute[] arr_vertexAttribute = this.attributes;
            if(v1 >= arr_vertexAttribute.length) {
                break;
            }
            v = v * 61L + ((long)arr_vertexAttribute[v1].hashCode());
        }
        return (int)(v ^ v >> 0x20);
    }

    @Override
    public Iterator iterator() {
        if(this.iterable == null) {
            this.iterable = new ReadonlyIterable(this.attributes);
        }
        return this.iterable.iterator();
    }

    public int size() {
        return this.attributes.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("[");
        for(int v = 0; v < this.attributes.length; ++v) {
            stringBuilder0.append("(");
            stringBuilder0.append(this.attributes[v].alias);
            stringBuilder0.append(", ");
            stringBuilder0.append(this.attributes[v].usage);
            stringBuilder0.append(", ");
            stringBuilder0.append(this.attributes[v].numComponents);
            stringBuilder0.append(", ");
            stringBuilder0.append(this.attributes[v].offset);
            stringBuilder0.append(")");
            stringBuilder0.append("\n");
        }
        stringBuilder0.append("]");
        return stringBuilder0.toString();
    }
}

