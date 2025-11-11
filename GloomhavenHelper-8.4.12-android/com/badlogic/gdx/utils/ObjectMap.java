package com.badlogic.gdx.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ObjectMap implements Iterable {
    public static class Entries extends MapIterator {
        Entry entry;

        public Entries(ObjectMap objectMap0) {
            super(objectMap0);
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

        @Override  // com.badlogic.gdx.utils.ObjectMap$MapIterator
        public void remove() {
            super.remove();
        }

        @Override  // com.badlogic.gdx.utils.ObjectMap$MapIterator
        public void reset() {
            super.reset();
        }
    }

    public static class Entry {
        public Object key;
        @Null
        public Object value;

        @Override
        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    public static class Keys extends MapIterator {
        public Keys(ObjectMap objectMap0) {
            super(objectMap0);
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

        @Override  // com.badlogic.gdx.utils.ObjectMap$MapIterator
        public void remove() {
            super.remove();
        }

        @Override  // com.badlogic.gdx.utils.ObjectMap$MapIterator
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

    static abstract class MapIterator implements Iterable, Iterator {
        int currentIndex;
        public boolean hasNext;
        final ObjectMap map;
        int nextIndex;
        boolean valid;

        public MapIterator(ObjectMap objectMap0) {
            this.valid = true;
            this.map = objectMap0;
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

        @Override
        public void remove() {
            int v = this.currentIndex;
            if(v < 0) {
                throw new IllegalStateException("next must be called before remove.");
            }
            Object[] arr_object = this.map.keyTable;
            Object[] arr_object1 = this.map.valueTable;
            int v1 = this.map.mask;
            int v2 = v + 1 & v1;
            Object object0;
            while((object0 = arr_object[v2]) != null) {
                int v3 = this.map.place(object0);
                if((v2 - v3 & v1) > (v - v3 & v1)) {
                    arr_object[v] = object0;
                    arr_object1[v] = arr_object1[v2];
                    v = v2;
                }
                v2 = v2 + 1 & v1;
            }
            arr_object[v] = null;
            arr_object1[v] = null;
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
        public Values(ObjectMap objectMap0) {
            super(objectMap0);
        }

        @Override
        public boolean hasNext() {
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            return this.hasNext;
        }

        public Values iterator() [...] // Inlined contents

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
            Object object0 = this.map.valueTable[this.nextIndex];
            this.currentIndex = this.nextIndex;
            this.findNextIndex();
            return object0;
        }

        @Override  // com.badlogic.gdx.utils.ObjectMap$MapIterator
        public void remove() {
            super.remove();
        }

        @Override  // com.badlogic.gdx.utils.ObjectMap$MapIterator
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

    static final Object dummy;
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
    Object[] valueTable;
    transient Values values1;
    transient Values values2;

    static {
        ObjectMap.dummy = new Object();
    }

    public ObjectMap() {
        this(51, 0.8f);
    }

    public ObjectMap(int v) {
        this(v, 0.8f);
    }

    public ObjectMap(int v, float f) {
        if(f <= 0.0f || f >= 1.0f) {
            throw new IllegalArgumentException("loadFactor must be > 0 and < 1: " + f);
        }
        this.loadFactor = f;
        int v1 = ObjectSet.tableSize(v, f);
        this.threshold = (int)(((float)v1) * f);
        this.mask = v1 - 1;
        this.shift = Long.numberOfLeadingZeros(this.mask);
        this.keyTable = new Object[v1];
        this.valueTable = new Object[v1];
    }

    public ObjectMap(ObjectMap objectMap0) {
        this(((int)(((float)objectMap0.keyTable.length) * objectMap0.loadFactor)), objectMap0.loadFactor);
        System.arraycopy(objectMap0.keyTable, 0, this.keyTable, 0, objectMap0.keyTable.length);
        System.arraycopy(objectMap0.valueTable, 0, this.valueTable, 0, objectMap0.valueTable.length);
        this.size = objectMap0.size;
    }

    public void clear() {
        if(this.size == 0) {
            return;
        }
        this.size = 0;
        Arrays.fill(this.keyTable, null);
        Arrays.fill(this.valueTable, null);
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

    public boolean containsValue(@Null Object object0, boolean z) {
        Object[] arr_object = this.valueTable;
        if(object0 == null) {
            Object[] arr_object1 = this.keyTable;
            for(int v = arr_object.length - 1; v >= 0; --v) {
                if(arr_object1[v] != null && arr_object[v] == null) {
                    return true;
                }
            }
            return false;
        }
        if(z) {
            for(int v1 = arr_object.length - 1; v1 >= 0; --v1) {
                if(arr_object[v1] == object0) {
                    return true;
                }
            }
            return false;
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
        if(!(object0 instanceof ObjectMap)) {
            return false;
        }
        if(((ObjectMap)object0).size != this.size) {
            return false;
        }
        Object[] arr_object = this.keyTable;
        Object[] arr_object1 = this.valueTable;
        for(int v = 0; v < arr_object.length; ++v) {
            Object object1 = arr_object[v];
            if(object1 != null) {
                Object object2 = arr_object1[v];
                if(object2 == null) {
                    if(((ObjectMap)object0).get(object1, ObjectMap.dummy) != null) {
                        return false;
                    }
                }
                else if(!object2.equals(((ObjectMap)object0).get(object1))) {
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
        if(!(object0 instanceof ObjectMap)) {
            return false;
        }
        if(((ObjectMap)object0).size != this.size) {
            return false;
        }
        Object[] arr_object = this.keyTable;
        Object[] arr_object1 = this.valueTable;
        for(int v = 0; v < arr_object.length; ++v) {
            Object object1 = arr_object[v];
            if(object1 != null && arr_object1[v] != ((ObjectMap)object0).get(object1, ObjectMap.dummy)) {
                return false;
            }
        }
        return true;
    }

    @Null
    public Object findKey(@Null Object object0, boolean z) {
        Object[] arr_object = this.valueTable;
        if(object0 == null) {
            Object[] arr_object1 = this.keyTable;
            for(int v = arr_object.length - 1; v >= 0; --v) {
                if(arr_object1[v] != null && arr_object[v] == null) {
                    return arr_object1[v];
                }
            }
            return null;
        }
        if(z) {
            for(int v1 = arr_object.length - 1; v1 >= 0; --v1) {
                if(arr_object[v1] == object0) {
                    return this.keyTable[v1];
                }
            }
            return null;
        }
        for(int v2 = arr_object.length - 1; v2 >= 0; --v2) {
            if(object0.equals(arr_object[v2])) {
                return this.keyTable[v2];
            }
        }
        return null;
    }

    @Null
    public Object get(Object object0) {
        int v = this.locateKey(object0);
        return v >= 0 ? this.valueTable[v] : null;
    }

    public Object get(Object object0, @Null Object object1) {
        int v = this.locateKey(object0);
        return v < 0 ? object1 : this.valueTable[v];
    }

    @Override
    public int hashCode() {
        int v = this.size;
        Object[] arr_object = this.keyTable;
        Object[] arr_object1 = this.valueTable;
        for(int v1 = 0; v1 < arr_object.length; ++v1) {
            Object object0 = arr_object[v1];
            if(object0 != null) {
                v += object0.hashCode();
                Object object1 = arr_object1[v1];
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

    @Null
    public Object put(Object object0, @Null Object object1) {
        int v = this.locateKey(object0);
        if(v >= 0) {
            Object[] arr_object = this.valueTable;
            Object object2 = arr_object[v];
            arr_object[v] = object1;
            return object2;
        }
        Object[] arr_object1 = this.keyTable;
        arr_object1[-(v + 1)] = object0;
        this.valueTable[-(v + 1)] = object1;
        int v1 = this.size + 1;
        this.size = v1;
        if(v1 >= this.threshold) {
            this.resize(arr_object1.length << 1);
        }
        return null;
    }

    public void putAll(ObjectMap objectMap0) {
        this.ensureCapacity(objectMap0.size);
        Object[] arr_object = objectMap0.keyTable;
        Object[] arr_object1 = objectMap0.valueTable;
        for(int v = 0; v < arr_object.length; ++v) {
            Object object0 = arr_object[v];
            if(object0 != null) {
                this.put(object0, arr_object1[v]);
            }
        }
    }

    private void putResize(Object object0, @Null Object object1) {
        Object[] arr_object = this.keyTable;
        int v;
        for(v = this.place(object0); arr_object[v] != null; v = v + 1 & this.mask) {
        }
        arr_object[v] = object0;
        this.valueTable[v] = object1;
    }

    @Null
    public Object remove(Object object0) {
        int v = this.locateKey(object0);
        if(v < 0) {
            return null;
        }
        Object[] arr_object = this.keyTable;
        Object[] arr_object1 = this.valueTable;
        Object object1 = arr_object1[v];
        int v1 = this.mask;
        int v2 = v + 1 & v1;
        Object object2;
        while((object2 = arr_object[v2]) != null) {
            int v3 = this.place(object2);
            if((v2 - v3 & v1) > (v - v3 & v1)) {
                arr_object[v] = object2;
                arr_object1[v] = arr_object1[v2];
                v = v2;
            }
            v2 = v2 + 1 & v1;
        }
        arr_object[v] = null;
        arr_object1[v] = null;
        --this.size;
        return object1;
    }

    final void resize(int v) {
        int v1 = this.keyTable.length;
        this.threshold = (int)(((float)v) * this.loadFactor);
        this.mask = v - 1;
        this.shift = Long.numberOfLeadingZeros(this.mask);
        Object[] arr_object = this.keyTable;
        Object[] arr_object1 = this.valueTable;
        this.keyTable = new Object[v];
        this.valueTable = new Object[v];
        if(this.size > 0) {
            for(int v2 = 0; v2 < v1; ++v2) {
                Object object0 = arr_object[v2];
                if(object0 != null) {
                    this.putResize(object0, arr_object1[v2]);
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
        return this.toString(", ", true);
    }

    public String toString(String s) {
        return this.toString(s, false);
    }

    protected String toString(String s, boolean z) {
        int v1;
        if(this.size == 0) {
            return z ? "{}" : "";
        }
        StringBuilder stringBuilder0 = new StringBuilder(0x20);
        if(z) {
            stringBuilder0.append('{');
        }
        Object[] arr_object = this.keyTable;
        Object[] arr_object1 = this.valueTable;
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
                stringBuilder0.append('=');
                String s2 = arr_object1[v1];
                if(s2 == this) {
                    s2 = "(this)";
                }
                stringBuilder0.append(s2);
                break;
            }
        }
        while(v1 > 0) {
            String s3 = arr_object[v1 - 1];
            if(s3 != null) {
                stringBuilder0.append(s);
                if(s3 == this) {
                    s3 = "(this)";
                }
                stringBuilder0.append(s3);
                stringBuilder0.append('=');
                String s4 = arr_object1[v1 - 1];
                if(s4 == this) {
                    s4 = "(this)";
                }
                stringBuilder0.append(s4);
            }
            --v1;
        }
        if(z) {
            stringBuilder0.append('}');
        }
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

