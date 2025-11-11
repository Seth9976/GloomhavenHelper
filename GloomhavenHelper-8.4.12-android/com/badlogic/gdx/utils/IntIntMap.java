package com.badlogic.gdx.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntIntMap implements Iterable {
    public static class Entries extends MapIterator implements Iterable, Iterator {
        private final Entry entry;

        public Entries(IntIntMap intIntMap0) {
            super(intIntMap0);
            this.entry = new Entry();
        }

        @Override
        public boolean hasNext() {
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            return this.hasNext;
        }

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
            int[] arr_v = this.map.keyTable;
            if(this.nextIndex == -1) {
                this.entry.key = 0;
                this.entry.value = this.map.zeroValue;
            }
            else {
                this.entry.key = arr_v[this.nextIndex];
                this.entry.value = this.map.valueTable[this.nextIndex];
            }
            this.currentIndex = this.nextIndex;
            this.findNextIndex();
            return this.entry;
        }

        @Override
        public Object next() {
            return this.next();
        }

        @Override  // com.badlogic.gdx.utils.IntIntMap$MapIterator
        public void remove() {
            super.remove();
        }

        @Override  // com.badlogic.gdx.utils.IntIntMap$MapIterator
        public void reset() {
            super.reset();
        }
    }

    public static class Entry {
        public int key;
        public int value;

        @Override
        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    public static class Keys extends MapIterator {
        public Keys(IntIntMap intIntMap0) {
            super(intIntMap0);
        }

        public int next() {
            if(!this.hasNext) {
                throw new NoSuchElementException();
            }
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            int v = this.nextIndex == -1 ? 0 : this.map.keyTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            this.findNextIndex();
            return v;
        }

        @Override  // com.badlogic.gdx.utils.IntIntMap$MapIterator
        public void remove() {
            super.remove();
        }

        @Override  // com.badlogic.gdx.utils.IntIntMap$MapIterator
        public void reset() {
            super.reset();
        }

        public IntArray toArray() {
            IntArray intArray0 = new IntArray(true, this.map.size);
            while(this.hasNext) {
                intArray0.add(this.next());
            }
            return intArray0;
        }

        public IntArray toArray(IntArray intArray0) {
            while(this.hasNext) {
                intArray0.add(this.next());
            }
            return intArray0;
        }
    }

    static class MapIterator {
        private static final int INDEX_ILLEGAL = -2;
        static final int INDEX_ZERO = -1;
        int currentIndex;
        public boolean hasNext;
        final IntIntMap map;
        int nextIndex;
        boolean valid;

        public MapIterator(IntIntMap intIntMap0) {
            this.valid = true;
            this.map = intIntMap0;
            this.reset();
        }

        void findNextIndex() {
            int[] arr_v = this.map.keyTable;
            while(true) {
                int v = this.nextIndex + 1;
                this.nextIndex = v;
                if(v >= arr_v.length) {
                    break;
                }
                if(arr_v[this.nextIndex] != 0) {
                    this.hasNext = true;
                    return;
                }
            }
            this.hasNext = false;
        }

        public void remove() {
            int v = this.currentIndex;
            if(v == -1 && this.map.hasZeroValue) {
                this.map.hasZeroValue = false;
            }
            else if(v >= 0) {
                int[] arr_v = this.map.keyTable;
                int[] arr_v1 = this.map.valueTable;
                int v1 = this.map.mask;
                int v2 = v + 1 & v1;
                int v3;
                while((v3 = arr_v[v2]) != 0) {
                    int v4 = this.map.place(v3);
                    if((v2 - v4 & v1) > (v - v4 & v1)) {
                        arr_v[v] = v3;
                        arr_v1[v] = arr_v1[v2];
                        v = v2;
                    }
                    v2 = v2 + 1 & v1;
                }
                arr_v[v] = 0;
                if(v != this.currentIndex) {
                    --this.nextIndex;
                }
            }
            else {
                throw new IllegalStateException("next must be called before remove.");
            }
            this.currentIndex = -2;
            --this.map.size;
        }

        public void reset() {
            this.currentIndex = -2;
            this.nextIndex = -1;
            if(this.map.hasZeroValue) {
                this.hasNext = true;
                return;
            }
            this.findNextIndex();
        }
    }

    public static class Values extends MapIterator {
        public Values(IntIntMap intIntMap0) {
            super(intIntMap0);
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

        public int next() {
            if(!this.hasNext) {
                throw new NoSuchElementException();
            }
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            int v = this.nextIndex == -1 ? this.map.zeroValue : this.map.valueTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            this.findNextIndex();
            return v;
        }

        @Override  // com.badlogic.gdx.utils.IntIntMap$MapIterator
        public void remove() {
            super.remove();
        }

        @Override  // com.badlogic.gdx.utils.IntIntMap$MapIterator
        public void reset() {
            super.reset();
        }

        public IntArray toArray() {
            IntArray intArray0 = new IntArray(true, this.map.size);
            while(this.hasNext) {
                intArray0.add(this.next());
            }
            return intArray0;
        }

        public IntArray toArray(IntArray intArray0) {
            while(this.hasNext) {
                intArray0.add(this.next());
            }
            return intArray0;
        }
    }

    private transient Entries entries1;
    private transient Entries entries2;
    boolean hasZeroValue;
    int[] keyTable;
    private transient Keys keys1;
    private transient Keys keys2;
    private final float loadFactor;
    protected int mask;
    protected int shift;
    public int size;
    private int threshold;
    int[] valueTable;
    private transient Values values1;
    private transient Values values2;
    int zeroValue;

    public IntIntMap() {
        this(51, 0.8f);
    }

    public IntIntMap(int v) {
        this(v, 0.8f);
    }

    public IntIntMap(int v, float f) {
        if(f <= 0.0f || f >= 1.0f) {
            throw new IllegalArgumentException("loadFactor must be > 0 and < 1: " + f);
        }
        this.loadFactor = f;
        int v1 = ObjectSet.tableSize(v, f);
        this.threshold = (int)(((float)v1) * f);
        this.mask = v1 - 1;
        this.shift = Long.numberOfLeadingZeros(this.mask);
        this.keyTable = new int[v1];
        this.valueTable = new int[v1];
    }

    public IntIntMap(IntIntMap intIntMap0) {
        this(((int)(((float)intIntMap0.keyTable.length) * intIntMap0.loadFactor)), intIntMap0.loadFactor);
        System.arraycopy(intIntMap0.keyTable, 0, this.keyTable, 0, intIntMap0.keyTable.length);
        System.arraycopy(intIntMap0.valueTable, 0, this.valueTable, 0, intIntMap0.valueTable.length);
        this.size = intIntMap0.size;
        this.zeroValue = intIntMap0.zeroValue;
        this.hasZeroValue = intIntMap0.hasZeroValue;
    }

    public void clear() {
        if(this.size == 0) {
            return;
        }
        Arrays.fill(this.keyTable, 0);
        this.size = 0;
        this.hasZeroValue = false;
    }

    public void clear(int v) {
        int v1 = ObjectSet.tableSize(v, this.loadFactor);
        if(this.keyTable.length <= v1) {
            this.clear();
            return;
        }
        this.size = 0;
        this.hasZeroValue = false;
        this.resize(v1);
    }

    public boolean containsKey(int v) {
        return v == 0 ? this.hasZeroValue : this.locateKey(v) >= 0;
    }

    public boolean containsValue(int v) {
        if(this.hasZeroValue && this.zeroValue == v) {
            return true;
        }
        int[] arr_v = this.keyTable;
        int[] arr_v1 = this.valueTable;
        for(int v1 = arr_v1.length - 1; v1 >= 0; --v1) {
            if(arr_v[v1] != 0 && arr_v1[v1] == v) {
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
        if(!(object0 instanceof IntIntMap)) {
            return false;
        }
        if(((IntIntMap)object0).size != this.size) {
            return false;
        }
        boolean z = this.hasZeroValue;
        if(((IntIntMap)object0).hasZeroValue != z) {
            return false;
        }
        if(z && ((IntIntMap)object0).zeroValue != this.zeroValue) {
            return false;
        }
        int[] arr_v = this.keyTable;
        int[] arr_v1 = this.valueTable;
        for(int v = 0; v < arr_v.length; ++v) {
            int v1 = arr_v[v];
            if(v1 != 0) {
                int v2 = ((IntIntMap)object0).get(v1, 0);
                if(v2 == 0 && !((IntIntMap)object0).containsKey(v1)) {
                    return false;
                }
                if(v2 != arr_v1[v]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int findKey(int v, int v1) {
        if(this.hasZeroValue && this.zeroValue == v) {
            return 0;
        }
        int[] arr_v = this.keyTable;
        int[] arr_v1 = this.valueTable;
        for(int v2 = arr_v1.length - 1; v2 >= 0; --v2) {
            int v3 = arr_v[v2];
            if(v3 != 0 && arr_v1[v2] == v) {
                return v3;
            }
        }
        return v1;
    }

    public int get(int v, int v1) {
        if(v == 0) {
            return this.hasZeroValue ? this.zeroValue : v1;
        }
        int v2 = this.locateKey(v);
        return v2 < 0 ? v1 : this.valueTable[v2];
    }

    public int getAndIncrement(int v, int v1, int v2) {
        if(v == 0) {
            if(!this.hasZeroValue) {
                this.hasZeroValue = true;
                this.zeroValue = v2 + v1;
                ++this.size;
                return v1;
            }
            int v3 = this.zeroValue;
            this.zeroValue = v2 + v3;
            return v3;
        }
        int v4 = this.locateKey(v);
        if(v4 >= 0) {
            int[] arr_v = this.valueTable;
            int v5 = arr_v[v4];
            arr_v[v4] += v2;
            return v5;
        }
        int[] arr_v1 = this.keyTable;
        arr_v1[-(v4 + 1)] = v;
        this.valueTable[-(v4 + 1)] = v2 + v1;
        int v6 = this.size + 1;
        this.size = v6;
        if(v6 >= this.threshold) {
            this.resize(arr_v1.length << 1);
        }
        return v1;
    }

    @Override
    public int hashCode() {
        int v = this.hasZeroValue ? this.size + this.zeroValue : this.size;
        int[] arr_v = this.keyTable;
        int[] arr_v1 = this.valueTable;
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            int v2 = arr_v[v1];
            if(v2 != 0) {
                v += v2 * 0x1F + arr_v1[v1];
            }
        }
        return v;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator iterator() {
        return this.entries();
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

    private int locateKey(int v) {
        int[] arr_v = this.keyTable;
        for(int v1 = this.place(v); true; v1 = v1 + 1 & this.mask) {
            int v2 = arr_v[v1];
            if(v2 == 0) {
                return -(v1 + 1);
            }
            if(v2 == v) {
                return v1;
            }
        }
    }

    public boolean notEmpty() {
        return this.size > 0;
    }

    protected int place(int v) {
        return (int)(((long)v) * 0x9E3779B97F4A7C15L >>> this.shift);
    }

    public int put(int v, int v1, int v2) {
        if(v == 0) {
            int v3 = this.zeroValue;
            this.zeroValue = v1;
            if(!this.hasZeroValue) {
                this.hasZeroValue = true;
                ++this.size;
                return v2;
            }
            return v3;
        }
        int v4 = this.locateKey(v);
        if(v4 >= 0) {
            int[] arr_v = this.valueTable;
            int v5 = arr_v[v4];
            arr_v[v4] = v1;
            return v5;
        }
        int[] arr_v1 = this.keyTable;
        arr_v1[-(v4 + 1)] = v;
        this.valueTable[-(v4 + 1)] = v1;
        int v6 = this.size + 1;
        this.size = v6;
        if(v6 >= this.threshold) {
            this.resize(arr_v1.length << 1);
        }
        return v2;
    }

    public void put(int v, int v1) {
        if(v == 0) {
            this.zeroValue = v1;
            if(!this.hasZeroValue) {
                this.hasZeroValue = true;
                ++this.size;
            }
            return;
        }
        int v2 = this.locateKey(v);
        if(v2 >= 0) {
            this.valueTable[v2] = v1;
            return;
        }
        int[] arr_v = this.keyTable;
        arr_v[-(v2 + 1)] = v;
        this.valueTable[-(v2 + 1)] = v1;
        int v3 = this.size + 1;
        this.size = v3;
        if(v3 >= this.threshold) {
            this.resize(arr_v.length << 1);
        }
    }

    public void putAll(IntIntMap intIntMap0) {
        this.ensureCapacity(intIntMap0.size);
        if(intIntMap0.hasZeroValue) {
            this.put(0, intIntMap0.zeroValue);
        }
        int[] arr_v = intIntMap0.keyTable;
        int[] arr_v1 = intIntMap0.valueTable;
        for(int v = 0; v < arr_v.length; ++v) {
            int v1 = arr_v[v];
            if(v1 != 0) {
                this.put(v1, arr_v1[v]);
            }
        }
    }

    private void putResize(int v, int v1) {
        int[] arr_v = this.keyTable;
        int v2;
        for(v2 = this.place(v); arr_v[v2] != 0; v2 = v2 + 1 & this.mask) {
        }
        arr_v[v2] = v;
        this.valueTable[v2] = v1;
    }

    public int remove(int v, int v1) {
        if(v == 0) {
            if(!this.hasZeroValue) {
                return v1;
            }
            this.hasZeroValue = false;
            --this.size;
            return this.zeroValue;
        }
        int v2 = this.locateKey(v);
        if(v2 < 0) {
            return v1;
        }
        int[] arr_v = this.keyTable;
        int[] arr_v1 = this.valueTable;
        int v3 = arr_v1[v2];
        int v4 = this.mask;
        int v5 = v2 + 1 & v4;
        int v6;
        while((v6 = arr_v[v5]) != 0) {
            int v7 = this.place(v6);
            if((v5 - v7 & v4) > (v2 - v7 & v4)) {
                arr_v[v2] = v6;
                arr_v1[v2] = arr_v1[v5];
                v2 = v5;
            }
            v5 = v5 + 1 & v4;
        }
        arr_v[v2] = 0;
        --this.size;
        return v3;
    }

    private void resize(int v) {
        int v1 = this.keyTable.length;
        this.threshold = (int)(((float)v) * this.loadFactor);
        this.mask = v - 1;
        this.shift = Long.numberOfLeadingZeros(this.mask);
        int[] arr_v = this.keyTable;
        int[] arr_v1 = this.valueTable;
        this.keyTable = new int[v];
        this.valueTable = new int[v];
        if(this.size > 0) {
            for(int v2 = 0; v2 < v1; ++v2) {
                int v3 = arr_v[v2];
                if(v3 != 0) {
                    this.putResize(v3, arr_v1[v2]);
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

    @Override
    public String toString() {
        int v1;
        if(this.size == 0) {
            return "[]";
        }
        StringBuilder stringBuilder0 = new StringBuilder(0x20);
        stringBuilder0.append('[');
        int[] arr_v = this.keyTable;
        int[] arr_v1 = this.valueTable;
        int v = arr_v.length;
        if(this.hasZeroValue) {
            stringBuilder0.append("0=");
            stringBuilder0.append(this.zeroValue);
            goto label_22;
        }
        else {
            while(true) {
                v1 = v - 1;
                if(v <= 0) {
                    break;
                }
                int v2 = arr_v[v1];
                if(v2 != 0) {
                    stringBuilder0.append(v2);
                    stringBuilder0.append('=');
                    stringBuilder0.append(arr_v1[v1]);
                    break;
                }
                v = v1;
            }
        }
        while(true) {
            v = v1;
        label_22:
            v1 = v - 1;
            if(v <= 0) {
                break;
            }
            int v3 = arr_v[v1];
            if(v3 != 0) {
                stringBuilder0.append(", ");
                stringBuilder0.append(v3);
                stringBuilder0.append('=');
                stringBuilder0.append(arr_v1[v1]);
            }
        }
        stringBuilder0.append(']');
        return stringBuilder0.toString();
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

