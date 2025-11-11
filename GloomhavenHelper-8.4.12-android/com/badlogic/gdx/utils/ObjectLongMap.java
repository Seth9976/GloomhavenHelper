package com.badlogic.gdx.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ObjectLongMap implements Iterable {
    public static class Entries extends MapIterator implements Iterable, Iterator {
        Entry entry;

        public Entries(ObjectLongMap objectLongMap0) {
            super(objectLongMap0);
            this.entry = new Entry();
        }

        @Override
        public boolean hasNext() {
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            return this.hasNext;
        }

        public Entries iterator() [...] // Inlined contents

        @Override
        public Iterator iterator() {
            return this;
        }

        public Entry next() {
            if(!this.hasNext) {
                throw new NoSuchElementException();
            }
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            this.entry.key = this.map.keyTable[this.nextIndex];
            this.entry.value = this.map.valueTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            this.findNextIndex();
            return this.entry;
        }

        @Override
        public Object next() {
            return this.next();
        }

        @Override  // com.badlogic.gdx.utils.ObjectLongMap$MapIterator
        public void remove() {
            super.remove();
        }

        @Override  // com.badlogic.gdx.utils.ObjectLongMap$MapIterator
        public void reset() {
            super.reset();
        }
    }

    public static class Entry {
        public Object key;
        public long value;

        @Override
        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    public static class Keys extends MapIterator implements Iterable, Iterator {
        public Keys(ObjectLongMap objectLongMap0) {
            super(objectLongMap0);
        }

        @Override
        public boolean hasNext() {
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            return this.hasNext;
        }

        public Keys iterator() [...] // Inlined contents

        @Override
        public Iterator iterator() {
            return this;
        }

        @Override
        public Object next() {
            if(!this.hasNext) {
                throw new NoSuchElementException();
            }
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            Object object0 = this.map.keyTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            this.findNextIndex();
            return object0;
        }

        @Override  // com.badlogic.gdx.utils.ObjectLongMap$MapIterator
        public void remove() {
            super.remove();
        }

        @Override  // com.badlogic.gdx.utils.ObjectLongMap$MapIterator
        public void reset() {
            super.reset();
        }

        public Array toArray() {
            return this.toArray(new Array(true, this.map.size));
        }

        public Array toArray(Array array0) {
            while(this.hasNext) {
                array0.add(this.next());
            }
            return array0;
        }
    }

    static class MapIterator {
        int currentIndex;
        public boolean hasNext;
        final ObjectLongMap map;
        int nextIndex;
        boolean valid;

        public MapIterator(ObjectLongMap objectLongMap0) {
            this.valid = true;
            this.map = objectLongMap0;
            this.reset();
        }

        void findNextIndex() {
            Object[] arr_object = this.map.keyTable;
            while(true) {
                int v = this.nextIndex + 1;
                this.nextIndex = v;
                if(v >= arr_object.length) {
                    break;
                }
                if(arr_object[this.nextIndex] != null) {
                    this.hasNext = true;
                    return;
                }
            }
            this.hasNext = false;
        }

        public void remove() {
            int v = this.currentIndex;
            if(v < 0) {
                throw new IllegalStateException("next must be called before remove.");
            }
            Object[] arr_object = this.map.keyTable;
            long[] arr_v = this.map.valueTable;
            int v1 = this.map.mask;
            int v2 = v + 1 & v1;
            Object object0;
            while((object0 = arr_object[v2]) != null) {
                int v3 = this.map.place(object0);
                if((v2 - v3 & v1) > (v - v3 & v1)) {
                    arr_object[v] = object0;
                    arr_v[v] = arr_v[v2];
                    v = v2;
                }
                v2 = v2 + 1 & v1;
            }
            arr_object[v] = null;
            --this.map.size;
            if(v != this.currentIndex) {
                --this.nextIndex;
            }
            this.currentIndex = -1;
        }

        public void reset() {
            this.currentIndex = -1;
            this.nextIndex = -1;
            this.findNextIndex();
        }
    }

    public static class Values extends MapIterator {
        public Values(ObjectLongMap objectLongMap0) {
            super(objectLongMap0);
        }

        public boolean hasNext() {
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            return this.hasNext;
        }

        public Values iterator() {
            return this;
        }

        public long next() {
            if(!this.hasNext) {
                throw new NoSuchElementException();
            }
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            long v = this.map.valueTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            this.findNextIndex();
            return v;
        }

        @Override  // com.badlogic.gdx.utils.ObjectLongMap$MapIterator
        public void remove() {
            super.remove();
        }

        @Override  // com.badlogic.gdx.utils.ObjectLongMap$MapIterator
        public void reset() {
            super.reset();
        }

        public LongArray toArray() {
            LongArray longArray0 = new LongArray(true, this.map.size);
            while(this.hasNext) {
                longArray0.add(this.next());
            }
            return longArray0;
        }

        public LongArray toArray(LongArray longArray0) {
            while(this.hasNext) {
                longArray0.add(this.next());
            }
            return longArray0;
        }
    }

    transient Entries entries1;
    transient Entries entries2;
    Object[] keyTable;
    transient Keys keys1;
    transient Keys keys2;
    float loadFactor;
    protected int mask;
    protected int shift;
    public int size;
    int threshold;
    long[] valueTable;
    transient Values values1;
    transient Values values2;

    public ObjectLongMap() {
        this(51, 0.8f);
    }

    public ObjectLongMap(int v) {
        this(v, 0.8f);
    }

    public ObjectLongMap(int v, float f) {
        if(f <= 0.0f || f >= 1.0f) {
            throw new IllegalArgumentException("loadFactor must be > 0 and < 1: " + f);
        }
        this.loadFactor = f;
        int v1 = ObjectSet.tableSize(v, f);
        this.threshold = (int)(((float)v1) * f);
        this.mask = v1 - 1;
        this.shift = Long.numberOfLeadingZeros(this.mask);
        this.keyTable = new Object[v1];
        this.valueTable = new long[v1];
    }

    public ObjectLongMap(ObjectLongMap objectLongMap0) {
        this(((int)(((float)objectLongMap0.keyTable.length) * objectLongMap0.loadFactor)), objectLongMap0.loadFactor);
        System.arraycopy(objectLongMap0.keyTable, 0, this.keyTable, 0, objectLongMap0.keyTable.length);
        System.arraycopy(objectLongMap0.valueTable, 0, this.valueTable, 0, objectLongMap0.valueTable.length);
        this.size = objectLongMap0.size;
    }

    public void clear() {
        if(this.size == 0) {
            return;
        }
        this.size = 0;
        Arrays.fill(this.keyTable, null);
    }

    public void clear(int v) {
        int v1 = ObjectSet.tableSize(v, this.loadFactor);
        if(this.keyTable.length <= v1) {
            this.clear();
            return;
        }
        this.size = 0;
        this.resize(v1);
    }

    public boolean containsKey(Object object0) {
        return this.locateKey(object0) >= 0;
    }

    public boolean containsValue(long v) {
        Object[] arr_object = this.keyTable;
        long[] arr_v = this.valueTable;
        for(int v1 = arr_v.length - 1; v1 >= 0; --v1) {
            if(arr_object[v1] != null && arr_v[v1] == v) {
                return true;
            }
        }
        return false;
    }

    public void ensureCapacity(int v) {
        int v1 = ObjectSet.tableSize(this.size + v, this.loadFactor);
        if(this.keyTable.length < v1) {
            this.resize(v1);
        }
    }

    public Entries entries() {
        if(Collections.allocateIterators) {
            return new Entries(this);
        }
        if(this.entries1 == null) {
            this.entries1 = new Entries(this);
            this.entries2 = new Entries(this);
        }
        if(!this.entries1.valid) {
            this.entries1.reset();
            this.entries1.valid = true;
            this.entries2.valid = false;
            return this.entries1;
        }
        this.entries2.reset();
        this.entries2.valid = true;
        this.entries1.valid = false;
        return this.entries2;
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof ObjectLongMap)) {
            return false;
        }
        if(((ObjectLongMap)object0).size != this.size) {
            return false;
        }
        Object[] arr_object = this.keyTable;
        long[] arr_v = this.valueTable;
        for(int v = 0; v < arr_object.length; ++v) {
            Object object1 = arr_object[v];
            if(object1 != null) {
                long v1 = ((ObjectLongMap)object0).get(object1, 0L);
                if(v1 == 0L && !((ObjectLongMap)object0).containsKey(object1)) {
                    return false;
                }
                if(v1 != arr_v[v]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Null
    public Object findKey(long v) {
        Object[] arr_object = this.keyTable;
        long[] arr_v = this.valueTable;
        for(int v1 = arr_v.length - 1; v1 >= 0; --v1) {
            Object object0 = arr_object[v1];
            if(object0 != null && arr_v[v1] == v) {
                return object0;
            }
        }
        return null;
    }

    public long get(Object object0, long v) {
        int v1 = this.locateKey(object0);
        return v1 >= 0 ? this.valueTable[v1] : v;
    }

    public long getAndIncrement(Object object0, long v, long v1) {
        int v2 = this.locateKey(object0);
        if(v2 >= 0) {
            long[] arr_v = this.valueTable;
            long v3 = arr_v[v2];
            arr_v[v2] += v1;
            return v3;
        }
        Object[] arr_object = this.keyTable;
        arr_object[-(v2 + 1)] = object0;
        this.valueTable[-(v2 + 1)] = v1 + v;
        int v4 = this.size + 1;
        this.size = v4;
        if(v4 >= this.threshold) {
            this.resize(arr_object.length << 1);
        }
        return v;
    }

    @Override
    public int hashCode() {
        int v = this.size;
        Object[] arr_object = this.keyTable;
        long[] arr_v = this.valueTable;
        for(int v1 = 0; v1 < arr_object.length; ++v1) {
            Object object0 = arr_object[v1];
            if(object0 != null) {
                v = (int)(((long)v) + (((long)object0.hashCode()) + arr_v[v1]));
            }
        }
        return v;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public Entries iterator() {
        return this.entries();
    }

    @Override
    public Iterator iterator() {
        return this.iterator();
    }

    public Keys keys() {
        if(Collections.allocateIterators) {
            return new Keys(this);
        }
        if(this.keys1 == null) {
            this.keys1 = new Keys(this);
            this.keys2 = new Keys(this);
        }
        if(!this.keys1.valid) {
            this.keys1.reset();
            this.keys1.valid = true;
            this.keys2.valid = false;
            return this.keys1;
        }
        this.keys2.reset();
        this.keys2.valid = true;
        this.keys1.valid = false;
        return this.keys2;
    }

    int locateKey(Object object0) {
        if(object0 != null) {
            Object[] arr_object = this.keyTable;
            for(int v = this.place(object0); true; v = v + 1 & this.mask) {
                Object object1 = arr_object[v];
                if(object1 == null) {
                    return -(v + 1);
                }
                if(object1.equals(object0)) {
                    return v;
                }
            }
        }
        throw new IllegalArgumentException("key cannot be null.");
    }

    public boolean notEmpty() {
        return this.size > 0;
    }

    protected int place(Object object0) {
        return (int)(((long)object0.hashCode()) * 0x9E3779B97F4A7C15L >>> this.shift);
    }

    public long put(Object object0, long v, long v1) {
        int v2 = this.locateKey(object0);
        if(v2 >= 0) {
            long[] arr_v = this.valueTable;
            long v3 = arr_v[v2];
            arr_v[v2] = v;
            return v3;
        }
        Object[] arr_object = this.keyTable;
        arr_object[-(v2 + 1)] = object0;
        this.valueTable[-(v2 + 1)] = v;
        int v4 = this.size + 1;
        this.size = v4;
        if(v4 >= this.threshold) {
            this.resize(arr_object.length << 1);
        }
        return v1;
    }

    public void put(Object object0, long v) {
        int v1 = this.locateKey(object0);
        if(v1 >= 0) {
            this.valueTable[v1] = v;
            return;
        }
        Object[] arr_object = this.keyTable;
        arr_object[-(v1 + 1)] = object0;
        this.valueTable[-(v1 + 1)] = v;
        int v2 = this.size + 1;
        this.size = v2;
        if(v2 >= this.threshold) {
            this.resize(arr_object.length << 1);
        }
    }

    public void putAll(ObjectLongMap objectLongMap0) {
        this.ensureCapacity(objectLongMap0.size);
        Object[] arr_object = objectLongMap0.keyTable;
        long[] arr_v = objectLongMap0.valueTable;
        for(int v = 0; v < arr_object.length; ++v) {
            Object object0 = arr_object[v];
            if(object0 != null) {
                this.put(object0, arr_v[v]);
            }
        }
    }

    private void putResize(Object object0, long v) {
        Object[] arr_object = this.keyTable;
        int v1;
        for(v1 = this.place(object0); arr_object[v1] != null; v1 = v1 + 1 & this.mask) {
        }
        arr_object[v1] = object0;
        this.valueTable[v1] = v;
    }

    public long remove(Object object0, long v) {
        int v1 = this.locateKey(object0);
        if(v1 < 0) {
            return v;
        }
        Object[] arr_object = this.keyTable;
        long[] arr_v = this.valueTable;
        long v2 = arr_v[v1];
        int v3 = this.mask;
        int v4 = v1 + 1 & v3;
        Object object1;
        while((object1 = arr_object[v4]) != null) {
            int v5 = this.place(object1);
            if((v4 - v5 & v3) > (v1 - v5 & v3)) {
                arr_object[v1] = object1;
                arr_v[v1] = arr_v[v4];
                v1 = v4;
            }
            v4 = v4 + 1 & v3;
        }
        arr_object[v1] = null;
        --this.size;
        return v2;
    }

    final void resize(int v) {
        int v1 = this.keyTable.length;
        this.threshold = (int)(((float)v) * this.loadFactor);
        this.mask = v - 1;
        this.shift = Long.numberOfLeadingZeros(this.mask);
        Object[] arr_object = this.keyTable;
        long[] arr_v = this.valueTable;
        this.keyTable = new Object[v];
        this.valueTable = new long[v];
        if(this.size > 0) {
            for(int v2 = 0; v2 < v1; ++v2) {
                Object object0 = arr_object[v2];
                if(object0 != null) {
                    this.putResize(object0, arr_v[v2]);
                }
            }
        }
    }

    public void shrink(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("maximumCapacity must be >= 0: " + v);
        }
        int v1 = ObjectSet.tableSize(v, this.loadFactor);
        if(this.keyTable.length > v1) {
            this.resize(v1);
        }
    }

    private String toString(String s, boolean z) {
        int v1;
        if(this.size == 0) {
            return z ? "{}" : "";
        }
        StringBuilder stringBuilder0 = new StringBuilder(0x20);
        if(z) {
            stringBuilder0.append('{');
        }
        Object[] arr_object = this.keyTable;
        long[] arr_v = this.valueTable;
        for(int v = arr_object.length; true; v = v1) {
            v1 = v - 1;
            if(v <= 0) {
                break;
            }
            Object object0 = arr_object[v1];
            if(object0 != null) {
                stringBuilder0.append(object0);
                stringBuilder0.append('=');
                stringBuilder0.append(arr_v[v1]);
                break;
            }
        }
        while(v1 > 0) {
            Object object1 = arr_object[v1 - 1];
            if(object1 != null) {
                stringBuilder0.append(s);
                stringBuilder0.append(object1);
                stringBuilder0.append('=');
                stringBuilder0.append(arr_v[v1 - 1]);
            }
            --v1;
        }
        if(z) {
            stringBuilder0.append('}');
        }
        return stringBuilder0.toString();
    }

    @Override
    public String toString() {
        return this.toString(", ", true);
    }

    public String toString(String s) {
        return this.toString(s, false);
    }

    public Values values() {
        if(Collections.allocateIterators) {
            return new Values(this);
        }
        if(this.values1 == null) {
            this.values1 = new Values(this);
            this.values2 = new Values(this);
        }
        if(!this.values1.valid) {
            this.values1.reset();
            this.values1.valid = true;
            this.values2.valid = false;
            return this.values1;
        }
        this.values2.reset();
        this.values2.valid = true;
        this.values1.valid = false;
        return this.values2;
    }
}

