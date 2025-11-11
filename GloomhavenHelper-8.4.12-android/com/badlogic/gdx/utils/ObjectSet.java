package com.badlogic.gdx.utils;

import com.badlogic.gdx.math.MathUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ObjectSet implements Iterable {
    public static class ObjectSetIterator implements Iterable, Iterator {
        int currentIndex;
        public boolean hasNext;
        int nextIndex;
        final ObjectSet set;
        boolean valid;

        public ObjectSetIterator(ObjectSet objectSet0) {
            this.valid = true;
            this.set = objectSet0;
            this.reset();
        }

        private void findNextIndex() {
            Object[] arr_object = this.set.keyTable;
            while(true) {
                int v = this.nextIndex + 1;
                this.nextIndex = v;
                if(v >= this.set.keyTable.length) {
                    break;
                }
                if(arr_object[this.nextIndex] != null) {
                    this.hasNext = true;
                    return;
                }
            }
            this.hasNext = false;
        }

        @Override
        public boolean hasNext() {
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            return this.hasNext;
        }

        public ObjectSetIterator iterator() [...] // Inlined contents

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
            int v = this.nextIndex;
            Object object0 = this.set.keyTable[v];
            this.currentIndex = v;
            this.findNextIndex();
            return object0;
        }

        @Override
        public void remove() {
            int v = this.currentIndex;
            if(v < 0) {
                throw new IllegalStateException("next must be called before remove.");
            }
            Object[] arr_object = this.set.keyTable;
            int v1 = this.set.mask;
            int v2 = v + 1 & v1;
            Object object0;
            while((object0 = arr_object[v2]) != null) {
                int v3 = this.set.place(object0);
                if((v2 - v3 & v1) > (v - v3 & v1)) {
                    arr_object[v] = object0;
                    v = v2;
                }
                v2 = v2 + 1 & v1;
            }
            arr_object[v] = null;
            --this.set.size;
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

        public Array toArray() {
            return this.toArray(new Array(true, this.set.size));
        }

        public Array toArray(Array array0) {
            while(this.hasNext) {
                array0.add(this.next());
            }
            return array0;
        }
    }

    private transient ObjectSetIterator iterator1;
    private transient ObjectSetIterator iterator2;
    Object[] keyTable;
    float loadFactor;
    protected int mask;
    protected int shift;
    public int size;
    int threshold;

    public ObjectSet() {
        this(51, 0.8f);
    }

    public ObjectSet(int v) {
        this(v, 0.8f);
    }

    public ObjectSet(int v, float f) {
        if(f <= 0.0f || f >= 1.0f) {
            throw new IllegalArgumentException("loadFactor must be > 0 and < 1: " + f);
        }
        this.loadFactor = f;
        int v1 = ObjectSet.tableSize(v, f);
        this.threshold = (int)(((float)v1) * f);
        this.mask = v1 - 1;
        this.shift = Long.numberOfLeadingZeros(this.mask);
        this.keyTable = new Object[v1];
    }

    public ObjectSet(ObjectSet objectSet0) {
        this(((int)(((float)objectSet0.keyTable.length) * objectSet0.loadFactor)), objectSet0.loadFactor);
        System.arraycopy(objectSet0.keyTable, 0, this.keyTable, 0, objectSet0.keyTable.length);
        this.size = objectSet0.size;
    }

    public boolean add(Object object0) {
        int v = this.locateKey(object0);
        if(v >= 0) {
            return false;
        }
        Object[] arr_object = this.keyTable;
        arr_object[-(v + 1)] = object0;
        int v1 = this.size + 1;
        this.size = v1;
        if(v1 >= this.threshold) {
            this.resize(arr_object.length << 1);
        }
        return true;
    }

    public void addAll(Array array0) {
        this.addAll(array0.items, 0, array0.size);
    }

    public void addAll(Array array0, int v, int v1) {
        if(v + v1 > array0.size) {
            throw new IllegalArgumentException("offset + length must be <= size: " + v + " + " + v1 + " <= " + array0.size);
        }
        this.addAll(array0.items, v, v1);
    }

    public void addAll(ObjectSet objectSet0) {
        this.ensureCapacity(objectSet0.size);
        Object[] arr_object = objectSet0.keyTable;
        for(int v = 0; v < arr_object.length; ++v) {
            Object object0 = arr_object[v];
            if(object0 != null) {
                this.add(object0);
            }
        }
    }

    public boolean addAll(Object[] arr_object) {
        return this.addAll(arr_object, 0, arr_object.length);
    }

    public boolean addAll(Object[] arr_object, int v, int v1) {
        this.ensureCapacity(v1);
        int v2 = this.size;
        int v3 = v1 + v;
        while(v < v3) {
            this.add(arr_object[v]);
            ++v;
        }
        return v2 != this.size;
    }

    private void addResize(Object object0) {
        Object[] arr_object = this.keyTable;
        int v;
        for(v = this.place(object0); arr_object[v] != null; v = v + 1 & this.mask) {
        }
        arr_object[v] = object0;
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

    public boolean contains(Object object0) {
        return this.locateKey(object0) >= 0;
    }

    public void ensureCapacity(int v) {
        int v1 = ObjectSet.tableSize(this.size + v, this.loadFactor);
        if(this.keyTable.length < v1) {
            this.resize(v1);
        }
    }

    @Override
    public boolean equals(Object object0) {
        if(!(object0 instanceof ObjectSet)) {
            return false;
        }
        if(((ObjectSet)object0).size != this.size) {
            return false;
        }
        Object[] arr_object = this.keyTable;
        for(int v = 0; v < arr_object.length; ++v) {
            if(arr_object[v] != null && !((ObjectSet)object0).contains(arr_object[v])) {
                return false;
            }
        }
        return true;
    }

    public Object first() {
        Object[] arr_object = this.keyTable;
        for(int v = 0; v < arr_object.length; ++v) {
            if(arr_object[v] != null) {
                return arr_object[v];
            }
        }
        throw new IllegalStateException("ObjectSet is empty.");
    }

    @Null
    public Object get(Object object0) {
        int v = this.locateKey(object0);
        return v >= 0 ? this.keyTable[v] : null;
    }

    @Override
    public int hashCode() {
        int v = this.size;
        Object[] arr_object = this.keyTable;
        for(int v1 = 0; v1 < arr_object.length; ++v1) {
            Object object0 = arr_object[v1];
            if(object0 != null) {
                v += object0.hashCode();
            }
        }
        return v;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public ObjectSetIterator iterator() {
        if(Collections.allocateIterators) {
            return new ObjectSetIterator(this);
        }
        if(this.iterator1 == null) {
            this.iterator1 = new ObjectSetIterator(this);
            this.iterator2 = new ObjectSetIterator(this);
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

    @Override
    public Iterator iterator() {
        return this.iterator();
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

    public boolean remove(Object object0) {
        int v = this.locateKey(object0);
        if(v < 0) {
            return false;
        }
        Object[] arr_object = this.keyTable;
        int v1 = this.mask;
        int v2 = v + 1 & v1;
        Object object1;
        while((object1 = arr_object[v2]) != null) {
            int v3 = this.place(object1);
            if((v2 - v3 & v1) > (v - v3 & v1)) {
                arr_object[v] = object1;
                v = v2;
            }
            v2 = v2 + 1 & v1;
        }
        arr_object[v] = null;
        --this.size;
        return true;
    }

    private void resize(int v) {
        int v1 = this.keyTable.length;
        this.threshold = (int)(((float)v) * this.loadFactor);
        this.mask = v - 1;
        this.shift = Long.numberOfLeadingZeros(this.mask);
        Object[] arr_object = this.keyTable;
        this.keyTable = new Object[v];
        if(this.size > 0) {
            for(int v2 = 0; v2 < v1; ++v2) {
                Object object0 = arr_object[v2];
                if(object0 != null) {
                    this.addResize(object0);
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

    static int tableSize(int v, float f) {
        if(v < 0) {
            throw new IllegalArgumentException("capacity must be >= 0: " + v);
        }
        int v1 = MathUtils.nextPowerOfTwo(Math.max(2, ((int)Math.ceil(((float)v) / f))));
        if(v1 > 0x40000000) {
            throw new IllegalArgumentException("The required capacity is too large: " + v);
        }
        return v1;
    }

    @Override
    public String toString() {
        return '{' + this.toString(", ") + '}';
    }

    public String toString(String s) {
        int v1;
        if(this.size == 0) {
            return "";
        }
        StringBuilder stringBuilder0 = new StringBuilder(0x20);
        Object[] arr_object = this.keyTable;
        for(int v = arr_object.length; true; v = v1) {
            v1 = v - 1;
            if(v <= 0) {
                break;
            }
            String s1 = arr_object[v1];
            if(s1 != null) {
                if(s1 == this) {
                    s1 = "(this)";
                }
                stringBuilder0.append(s1);
                break;
            }
        }
        while(v1 > 0) {
            String s2 = arr_object[v1 - 1];
            if(s2 != null) {
                stringBuilder0.append(s);
                if(s2 == this) {
                    s2 = "(this)";
                }
                stringBuilder0.append(s2);
            }
            --v1;
        }
        return stringBuilder0.toString();
    }

    public static ObjectSet with(Object[] arr_object) {
        ObjectSet objectSet0 = new ObjectSet();
        objectSet0.addAll(arr_object);
        return objectSet0;
    }
}

