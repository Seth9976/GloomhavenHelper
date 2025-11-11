package com.badlogic.gdx.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LongMap implements Iterable {
    public static class Entries extends MapIterator implements Iterable, Iterator {
        private final Entry entry;

        public Entries(LongMap longMap0) {
            super(longMap0);
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
            long[] arr_v = this.map.keyTable;
            if(this.nextIndex == -1) {
                this.entry.key = 0L;
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

        @Override  // com.badlogic.gdx.utils.LongMap$MapIterator
        public void remove() {
            super.remove();
        }

        @Override  // com.badlogic.gdx.utils.LongMap$MapIterator
        public void reset() {
            super.reset();
        }
    }

    public static class Entry {
        public long key;
        @Null
        public Object value;

        @Override
        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    public static class Keys extends MapIterator {
        public Keys(LongMap longMap0) {
            super(longMap0);
        }

        public long next() {
            if(!this.hasNext) {
                throw new NoSuchElementException();
            }
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            long v = this.nextIndex == -1 ? 0L : this.map.keyTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            this.findNextIndex();
            return v;
        }

        @Override  // com.badlogic.gdx.utils.LongMap$MapIterator
        public void remove() {
            super.remove();
        }

        @Override  // com.badlogic.gdx.utils.LongMap$MapIterator
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

    static class MapIterator {
        private static final int INDEX_ILLEGAL = -2;
        static final int INDEX_ZERO = -1;
        int currentIndex;
        public boolean hasNext;
        final LongMap map;
        int nextIndex;
        boolean valid;

        public MapIterator(LongMap longMap0) {
            this.valid = true;
            this.map = longMap0;
            this.reset();
        }

        void findNextIndex() {
            long[] arr_v = this.map.keyTable;
            while(true) {
                int v = this.nextIndex + 1;
                this.nextIndex = v;
                if(v >= arr_v.length) {
                    break;
                }
                if(arr_v[this.nextIndex] != 0L) {
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
                this.map.zeroValue = null;
            }
            else if(v >= 0) {
                long[] arr_v = this.map.keyTable;
                Object[] arr_object = this.map.valueTable;
                int v1 = this.map.mask;
                int v2 = v + 1 & v1;
                long v3;
                while((v3 = arr_v[v2]) != 0L) {
                    int v4 = this.map.place(v3);
                    if((v2 - v4 & v1) > (v - v4 & v1)) {
                        arr_v[v] = v3;
                        arr_object[v] = arr_object[v2];
                        v = v2;
                    }
                    v2 = v2 + 1 & v1;
                }
                arr_v[v] = 0L;
                arr_object[v] = null;
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

    public static class Values extends MapIterator implements Iterable, Iterator {
        public Values(LongMap longMap0) {
            super(longMap0);
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

        @Override
        @Null
        public Object next() {
            if(!this.hasNext) {
                throw new NoSuchElementException();
            }
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            Object object0 = this.nextIndex == -1 ? this.map.zeroValue : this.map.valueTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            this.findNextIndex();
            return object0;
        }

        @Override  // com.badlogic.gdx.utils.LongMap$MapIterator
        public void remove() {
            super.remove();
        }

        @Override  // com.badlogic.gdx.utils.LongMap$MapIterator
        public void reset() {
            super.reset();
        }

        public Array toArray() {
            Array array0 = new Array(true, this.map.size);
            while(this.hasNext) {
                array0.add(this.next());
            }
            return array0;
        }
    }

    private transient Entries entries1;
    private transient Entries entries2;
    boolean hasZeroValue;
    long[] keyTable;
    private transient Keys keys1;
    private transient Keys keys2;
    private final float loadFactor;
    protected int mask;
    protected int shift;
    public int size;
    private int threshold;
    Object[] valueTable;
    private transient Values values1;
    private transient Values values2;
    Object zeroValue;

    public LongMap() {
        this(51, 0.8f);
    }

    public LongMap(int v) {
        this(v, 0.8f);
    }

    public LongMap(int v, float f) {
        if(f <= 0.0f || f >= 1.0f) {
            throw new IllegalArgumentException("loadFactor must be > 0 and < 1: " + f);
        }
        this.loadFactor = f;
        int v1 = ObjectSet.tableSize(v, f);
        this.threshold = (int)(((float)v1) * f);
        this.mask = v1 - 1;
        this.shift = Long.numberOfLeadingZeros(this.mask);
        this.keyTable = new long[v1];
        this.valueTable = new Object[v1];
    }

    public LongMap(LongMap longMap0) {
        this(((int)(((float)longMap0.keyTable.length) * longMap0.loadFactor)), longMap0.loadFactor);
        System.arraycopy(longMap0.keyTable, 0, this.keyTable, 0, longMap0.keyTable.length);
        System.arraycopy(longMap0.valueTable, 0, this.valueTable, 0, longMap0.valueTable.length);
        this.size = longMap0.size;
        this.zeroValue = longMap0.zeroValue;
        this.hasZeroValue = longMap0.hasZeroValue;
    }

    public void clear() {
        if(this.size == 0) {
            return;
        }
        this.size = 0;
        Arrays.fill(this.keyTable, 0L);
        Arrays.fill(this.valueTable, null);
        this.zeroValue = null;
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
        this.zeroValue = null;
        this.resize(v1);
    }

    public boolean containsKey(long v) {
        return v == 0L ? this.hasZeroValue : this.locateKey(v) >= 0;
    }

    public boolean containsValue(@Null Object object0, boolean z) {
        Object[] arr_object = this.valueTable;
        if(object0 == null) {
            if(this.hasZeroValue && this.zeroValue == null) {
                return true;
            }
            long[] arr_v = this.keyTable;
            for(int v = arr_object.length - 1; v >= 0; --v) {
                if(arr_v[v] != 0L && arr_object[v] == null) {
                    return true;
                }
            }
            return false;
        }
        if(z) {
            if(object0 == this.zeroValue) {
                return true;
            }
            for(int v1 = arr_object.length - 1; v1 >= 0; --v1) {
                if(arr_object[v1] == object0) {
                    return true;
                }
            }
            return false;
        }
        if(this.hasZeroValue && object0.equals(this.zeroValue)) {
            return true;
        }
        for(int v2 = arr_object.length - 1; v2 >= 0; --v2) {
            if(object0.equals(arr_object[v2])) {
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
        if(!(object0 instanceof LongMap)) {
            return false;
        }
        if(((LongMap)object0).size != this.size) {
            return false;
        }
        boolean z = this.hasZeroValue;
        if(((LongMap)object0).hasZeroValue != z) {
            return false;
        }
        if(z) {
            Object object1 = ((LongMap)object0).zeroValue;
            if(object1 == null) {
                if(this.zeroValue != null) {
                    return false;
                }
            }
            else if(!object1.equals(this.zeroValue)) {
                return false;
            }
        }
        long[] arr_v = this.keyTable;
        Object[] arr_object = this.valueTable;
        for(int v = 0; v < arr_v.length; ++v) {
            long v1 = arr_v[v];
            if(v1 != 0L) {
                Object object2 = arr_object[v];
                if(object2 == null) {
                    if(((LongMap)object0).get(v1, ObjectMap.dummy) != null) {
                        return false;
                    }
                }
                else if(!object2.equals(((LongMap)object0).get(v1))) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean equalsIdentity(@Null Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof LongMap)) {
            return false;
        }
        if(((LongMap)object0).size != this.size) {
            return false;
        }
        boolean z = this.hasZeroValue;
        if(((LongMap)object0).hasZeroValue != z) {
            return false;
        }
        if(z && this.zeroValue != ((LongMap)object0).zeroValue) {
            return false;
        }
        long[] arr_v = this.keyTable;
        Object[] arr_object = this.valueTable;
        for(int v = 0; v < arr_v.length; ++v) {
            long v1 = arr_v[v];
            if(v1 != 0L && arr_object[v] != ((LongMap)object0).get(v1, ObjectMap.dummy)) {
                return false;
            }
        }
        return true;
    }

    public long findKey(@Null Object object0, boolean z, long v) {
        Object[] arr_object = this.valueTable;
        if(object0 == null) {
            if(this.hasZeroValue && this.zeroValue == null) {
                return 0L;
            }
            long[] arr_v = this.keyTable;
            for(int v1 = arr_object.length - 1; v1 >= 0; --v1) {
                if(arr_v[v1] != 0L && arr_object[v1] == null) {
                    return arr_v[v1];
                }
            }
            return v;
        }
        if(z) {
            if(object0 == this.zeroValue) {
                return 0L;
            }
            for(int v2 = arr_object.length - 1; v2 >= 0; --v2) {
                if(arr_object[v2] == object0) {
                    return this.keyTable[v2];
                }
            }
            return v;
        }
        if(this.hasZeroValue && object0.equals(this.zeroValue)) {
            return 0L;
        }
        for(int v3 = arr_object.length - 1; v3 >= 0; --v3) {
            if(object0.equals(arr_object[v3])) {
                return this.keyTable[v3];
            }
        }
        return v;
    }

    @Null
    public Object get(long v) {
        if(v == 0L) {
            return this.hasZeroValue ? this.zeroValue : null;
        }
        int v1 = this.locateKey(v);
        return v1 < 0 ? null : this.valueTable[v1];
    }

    public Object get(long v, @Null Object object0) {
        if(v == 0L) {
            return this.hasZeroValue ? this.zeroValue : object0;
        }
        int v1 = this.locateKey(v);
        return v1 < 0 ? object0 : this.valueTable[v1];
    }

    @Override
    public int hashCode() {
        int v = this.size;
        if(this.hasZeroValue) {
            Object object0 = this.zeroValue;
            if(object0 != null) {
                v += object0.hashCode();
            }
        }
        long[] arr_v = this.keyTable;
        Object[] arr_object = this.valueTable;
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            long v2 = arr_v[v1];
            if(v2 != 0L) {
                v = (int)(((long)v) + v2 * 0x1FL);
                Object object1 = arr_object[v1];
                if(object1 != null) {
                    v += object1.hashCode();
                }
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

    private int locateKey(long v) {
        long[] arr_v = this.keyTable;
        for(int v1 = this.place(v); true; v1 = v1 + 1 & this.mask) {
            long v2 = arr_v[v1];
            if(v2 == 0L) {
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

    protected int place(long v) {
        return (int)((v ^ v >>> 0x20) * 0x9E3779B97F4A7C15L >>> this.shift);
    }

    @Null
    public Object put(long v, @Null Object object0) {
        if(v == 0L) {
            Object object1 = this.zeroValue;
            this.zeroValue = object0;
            if(!this.hasZeroValue) {
                this.hasZeroValue = true;
                ++this.size;
            }
            return object1;
        }
        int v1 = this.locateKey(v);
        if(v1 >= 0) {
            Object[] arr_object = this.valueTable;
            Object object2 = arr_object[v1];
            arr_object[v1] = object0;
            return object2;
        }
        long[] arr_v = this.keyTable;
        arr_v[-(v1 + 1)] = v;
        this.valueTable[-(v1 + 1)] = object0;
        int v2 = this.size + 1;
        this.size = v2;
        if(v2 >= this.threshold) {
            this.resize(arr_v.length << 1);
        }
        return null;
    }

    public void putAll(LongMap longMap0) {
        this.ensureCapacity(longMap0.size);
        if(longMap0.hasZeroValue) {
            this.put(0L, longMap0.zeroValue);
        }
        long[] arr_v = longMap0.keyTable;
        Object[] arr_object = longMap0.valueTable;
        for(int v = 0; v < arr_v.length; ++v) {
            long v1 = arr_v[v];
            if(v1 != 0L) {
                this.put(v1, arr_object[v]);
            }
        }
    }

    private void putResize(long v, @Null Object object0) {
        long[] arr_v = this.keyTable;
        int v1;
        for(v1 = this.place(v); arr_v[v1] != 0L; v1 = v1 + 1 & this.mask) {
        }
        arr_v[v1] = v;
        this.valueTable[v1] = object0;
    }

    @Null
    public Object remove(long v) {
        if(v == 0L) {
            if(!this.hasZeroValue) {
                return null;
            }
            this.hasZeroValue = false;
            Object object0 = this.zeroValue;
            this.zeroValue = null;
            --this.size;
            return object0;
        }
        int v1 = this.locateKey(v);
        if(v1 < 0) {
            return null;
        }
        long[] arr_v = this.keyTable;
        Object[] arr_object = this.valueTable;
        Object object1 = arr_object[v1];
        int v2 = this.mask;
        int v3 = v1 + 1 & v2;
        long v4;
        while((v4 = arr_v[v3]) != 0L) {
            int v5 = this.place(v4);
            if((v3 - v5 & v2) > (v1 - v5 & v2)) {
                arr_v[v1] = v4;
                arr_object[v1] = arr_object[v3];
                v1 = v3;
            }
            v3 = v3 + 1 & v2;
        }
        arr_v[v1] = 0L;
        arr_object[v1] = null;
        --this.size;
        return object1;
    }

    private void resize(int v) {
        int v1 = this.keyTable.length;
        this.threshold = (int)(((float)v) * this.loadFactor);
        this.mask = v - 1;
        this.shift = Long.numberOfLeadingZeros(this.mask);
        long[] arr_v = this.keyTable;
        Object[] arr_object = this.valueTable;
        this.keyTable = new long[v];
        this.valueTable = new Object[v];
        if(this.size > 0) {
            for(int v2 = 0; v2 < v1; ++v2) {
                long v3 = arr_v[v2];
                if(v3 != 0L) {
                    this.putResize(v3, arr_object[v2]);
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
        long[] arr_v = this.keyTable;
        Object[] arr_object = this.valueTable;
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
                long v2 = arr_v[v1];
                if(v2 != 0L) {
                    stringBuilder0.append(v2);
                    stringBuilder0.append('=');
                    stringBuilder0.append(arr_object[v1]);
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
            long v3 = arr_v[v1];
            if(v3 != 0L) {
                stringBuilder0.append(", ");
                stringBuilder0.append(v3);
                stringBuilder0.append('=');
                stringBuilder0.append(arr_object[v1]);
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

