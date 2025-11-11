package com.badlogic.gdx.utils;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class IntSet {
    public static class IntSetIterator {
        private static final int INDEX_ILLEGAL = -2;
        private static final int INDEX_ZERO = -1;
        int currentIndex;
        public boolean hasNext;
        int nextIndex;
        final IntSet set;
        boolean valid;

        public IntSetIterator(IntSet intSet0) {
            this.valid = true;
            this.set = intSet0;
            this.reset();
        }

        void findNextIndex() {
            int[] arr_v = this.set.keyTable;
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

        public int next() {
            if(!this.hasNext) {
                throw new NoSuchElementException();
            }
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            int v = this.nextIndex == -1 ? 0 : this.set.keyTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            this.findNextIndex();
            return v;
        }

        public void remove() {
            int v = this.currentIndex;
            if(v == -1 && this.set.hasZeroValue) {
                this.set.hasZeroValue = false;
            }
            else if(v >= 0) {
                int[] arr_v = this.set.keyTable;
                int v1 = this.set.mask;
                int v2 = v + 1 & v1;
                int v3;
                while((v3 = arr_v[v2]) != 0) {
                    int v4 = this.set.place(v3);
                    if((v2 - v4 & v1) > (v - v4 & v1)) {
                        arr_v[v] = v3;
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
            --this.set.size;
        }

        public void reset() {
            this.currentIndex = -2;
            this.nextIndex = -1;
            if(this.set.hasZeroValue) {
                this.hasNext = true;
                return;
            }
            this.findNextIndex();
        }

        public IntArray toArray() {
            IntArray intArray0 = new IntArray(true, this.set.size);
            while(this.hasNext) {
                intArray0.add(this.next());
            }
            return intArray0;
        }
    }

    boolean hasZeroValue;
    private transient IntSetIterator iterator1;
    private transient IntSetIterator iterator2;
    int[] keyTable;
    private final float loadFactor;
    protected int mask;
    protected int shift;
    public int size;
    private int threshold;

    public IntSet() {
        this(51, 0.8f);
    }

    public IntSet(int v) {
        this(v, 0.8f);
    }

    public IntSet(int v, float f) {
        if(f <= 0.0f || f >= 1.0f) {
            throw new IllegalArgumentException("loadFactor must be > 0 and < 1: " + f);
        }
        this.loadFactor = f;
        int v1 = ObjectSet.tableSize(v, f);
        this.threshold = (int)(((float)v1) * f);
        this.mask = v1 - 1;
        this.shift = Long.numberOfLeadingZeros(this.mask);
        this.keyTable = new int[v1];
    }

    public IntSet(IntSet intSet0) {
        this(((int)(((float)intSet0.keyTable.length) * intSet0.loadFactor)), intSet0.loadFactor);
        System.arraycopy(intSet0.keyTable, 0, this.keyTable, 0, intSet0.keyTable.length);
        this.size = intSet0.size;
        this.hasZeroValue = intSet0.hasZeroValue;
    }

    public boolean add(int v) {
        if(v == 0) {
            if(this.hasZeroValue) {
                return false;
            }
            this.hasZeroValue = true;
            ++this.size;
            return true;
        }
        int v1 = this.locateKey(v);
        if(v1 >= 0) {
            return false;
        }
        int[] arr_v = this.keyTable;
        arr_v[-(v1 + 1)] = v;
        int v2 = this.size + 1;
        this.size = v2;
        if(v2 >= this.threshold) {
            this.resize(arr_v.length << 1);
        }
        return true;
    }

    public void addAll(IntArray intArray0) {
        this.addAll(intArray0.items, 0, intArray0.size);
    }

    public void addAll(IntArray intArray0, int v, int v1) {
        if(v + v1 > intArray0.size) {
            throw new IllegalArgumentException("offset + length must be <= size: " + v + " + " + v1 + " <= " + intArray0.size);
        }
        this.addAll(intArray0.items, v, v1);
    }

    public void addAll(IntSet intSet0) {
        this.ensureCapacity(intSet0.size);
        if(intSet0.hasZeroValue) {
            this.add(0);
        }
        int[] arr_v = intSet0.keyTable;
        for(int v = 0; v < arr_v.length; ++v) {
            int v1 = arr_v[v];
            if(v1 != 0) {
                this.add(v1);
            }
        }
    }

    public void addAll(int[] arr_v) {
        this.addAll(arr_v, 0, arr_v.length);
    }

    public void addAll(int[] arr_v, int v, int v1) {
        this.ensureCapacity(v1);
        int v2 = v1 + v;
        while(v < v2) {
            this.add(arr_v[v]);
            ++v;
        }
    }

    private void addResize(int v) {
        int[] arr_v = this.keyTable;
        int v1;
        for(v1 = this.place(v); arr_v[v1] != 0; v1 = v1 + 1 & this.mask) {
        }
        arr_v[v1] = v;
    }

    public void clear() {
        if(this.size == 0) {
            return;
        }
        this.size = 0;
        Arrays.fill(this.keyTable, 0);
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

    public boolean contains(int v) {
        return v == 0 ? this.hasZeroValue : this.locateKey(v) >= 0;
    }

    public void ensureCapacity(int v) {
        int v1 = ObjectSet.tableSize(this.size + v, this.loadFactor);
        if(this.keyTable.length < v1) {
            this.resize(v1);
        }
    }

    @Override
    public boolean equals(Object object0) {
        if(!(object0 instanceof IntSet)) {
            return false;
        }
        if(((IntSet)object0).size != this.size) {
            return false;
        }
        if(((IntSet)object0).hasZeroValue != this.hasZeroValue) {
            return false;
        }
        int[] arr_v = this.keyTable;
        for(int v = 0; v < arr_v.length; ++v) {
            if(arr_v[v] != 0 && !((IntSet)object0).contains(arr_v[v])) {
                return false;
            }
        }
        return true;
    }

    public int first() {
        if(this.hasZeroValue) {
            return 0;
        }
        int[] arr_v = this.keyTable;
        for(int v = 0; v < arr_v.length; ++v) {
            if(arr_v[v] != 0) {
                return arr_v[v];
            }
        }
        throw new IllegalStateException("IntSet is empty.");
    }

    @Override
    public int hashCode() {
        int v = this.size;
        int[] arr_v = this.keyTable;
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            int v2 = arr_v[v1];
            if(v2 != 0) {
                v += v2;
            }
        }
        return v;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public IntSetIterator iterator() {
        if(Collections.allocateIterators) {
            return new IntSetIterator(this);
        }
        if(this.iterator1 == null) {
            this.iterator1 = new IntSetIterator(this);
            this.iterator2 = new IntSetIterator(this);
        }
        if(!this.iterator1.valid) {
            this.iterator1.reset();
            this.iterator1.valid = true;
            this.iterator2.valid = false;
            return this.iterator1;
        }
        this.iterator2.reset();
        this.iterator2.valid = true;
        this.iterator1.valid = false;
        return this.iterator2;
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

    public boolean remove(int v) {
        if(v == 0) {
            if(!this.hasZeroValue) {
                return false;
            }
            this.hasZeroValue = false;
            --this.size;
            return true;
        }
        int v1 = this.locateKey(v);
        if(v1 < 0) {
            return false;
        }
        int[] arr_v = this.keyTable;
        int v2 = this.mask;
        int v3 = v1 + 1 & v2;
        int v4;
        while((v4 = arr_v[v3]) != 0) {
            int v5 = this.place(v4);
            if((v3 - v5 & v2) > (v1 - v5 & v2)) {
                arr_v[v1] = v4;
                v1 = v3;
            }
            v3 = v3 + 1 & v2;
        }
        arr_v[v1] = 0;
        --this.size;
        return true;
    }

    private void resize(int v) {
        int v1 = this.keyTable.length;
        this.threshold = (int)(((float)v) * this.loadFactor);
        this.mask = v - 1;
        this.shift = Long.numberOfLeadingZeros(this.mask);
        int[] arr_v = this.keyTable;
        this.keyTable = new int[v];
        if(this.size > 0) {
            for(int v2 = 0; v2 < v1; ++v2) {
                int v3 = arr_v[v2];
                if(v3 != 0) {
                    this.addResize(v3);
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
        int v = arr_v.length;
        if(this.hasZeroValue) {
            stringBuilder0.append("0");
            goto label_18;
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
                    break;
                }
                v = v1;
            }
        }
        while(true) {
            v = v1;
        label_18:
            v1 = v - 1;
            if(v <= 0) {
                break;
            }
            int v3 = arr_v[v1];
            if(v3 != 0) {
                stringBuilder0.append(", ");
                stringBuilder0.append(v3);
            }
        }
        stringBuilder0.append(']');
        return stringBuilder0.toString();
    }

    public static IntSet with(int[] arr_v) {
        IntSet intSet0 = new IntSet();
        intSet0.addAll(arr_v);
        return intSet0;
    }
}

