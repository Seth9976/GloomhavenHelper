package com.badlogic.gdx.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.reflect.ArrayReflection;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayMap implements Iterable {
    public static class Entries implements Iterable, Iterator {
        Entry entry;
        int index;
        private final ArrayMap map;
        boolean valid;

        public Entries(ArrayMap arrayMap0) {
            this.entry = new Entry();
            this.valid = true;
            this.map = arrayMap0;
        }

        @Override
        public boolean hasNext() {
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            return this.index < this.map.size;
        }

        @Override
        public Iterator iterator() {
            return this;
        }

        public Entry next() {
            if(this.index >= this.map.size) {
                throw new NoSuchElementException(String.valueOf(this.index));
            }
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            this.entry.key = this.map.keys[this.index];
            int v = this.index;
            this.index = v + 1;
            this.entry.value = this.map.values[v];
            return this.entry;
        }

        @Override
        public Object next() {
            return this.next();
        }

        @Override
        public void remove() {
            --this.index;
            this.map.removeIndex(this.index);
        }

        public void reset() {
            this.index = 0;
        }
    }

    public static class Keys implements Iterable, Iterator {
        int index;
        private final ArrayMap map;
        boolean valid;

        public Keys(ArrayMap arrayMap0) {
            this.valid = true;
            this.map = arrayMap0;
        }

        @Override
        public boolean hasNext() {
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            return this.index < this.map.size;
        }

        @Override
        public Iterator iterator() {
            return this;
        }

        @Override
        public Object next() {
            if(this.index >= this.map.size) {
                throw new NoSuchElementException(String.valueOf(this.index));
            }
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            int v = this.index;
            this.index = v + 1;
            return this.map.keys[v];
        }

        @Override
        public void remove() {
            --this.index;
            this.map.removeIndex(this.index);
        }

        public void reset() {
            this.index = 0;
        }

        public Array toArray() {
            return new Array(true, this.map.keys, this.index, this.map.size - this.index);
        }

        public Array toArray(Array array0) {
            array0.addAll(this.map.keys, this.index, this.map.size - this.index);
            return array0;
        }
    }

    public static class Values implements Iterable, Iterator {
        int index;
        private final ArrayMap map;
        boolean valid;

        public Values(ArrayMap arrayMap0) {
            this.valid = true;
            this.map = arrayMap0;
        }

        @Override
        public boolean hasNext() {
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            return this.index < this.map.size;
        }

        @Override
        public Iterator iterator() {
            return this;
        }

        @Override
        public Object next() {
            if(this.index >= this.map.size) {
                throw new NoSuchElementException(String.valueOf(this.index));
            }
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            int v = this.index;
            this.index = v + 1;
            return this.map.values[v];
        }

        @Override
        public void remove() {
            --this.index;
            this.map.removeIndex(this.index);
        }

        public void reset() {
            this.index = 0;
        }

        public Array toArray() {
            return new Array(true, this.map.values, this.index, this.map.size - this.index);
        }

        public Array toArray(Array array0) {
            array0.addAll(this.map.values, this.index, this.map.size - this.index);
            return array0;
        }
    }

    private transient Entries entries1;
    private transient Entries entries2;
    public Object[] keys;
    private transient Keys keys1;
    private transient Keys keys2;
    public boolean ordered;
    public int size;
    public Object[] values;
    private transient Values values1;
    private transient Values values2;

    public ArrayMap() {
        this(true, 16);
    }

    public ArrayMap(int v) {
        this(true, v);
    }

    public ArrayMap(ArrayMap arrayMap0) {
        this(arrayMap0.ordered, arrayMap0.size, arrayMap0.keys.getClass().getComponentType(), arrayMap0.values.getClass().getComponentType());
        this.size = arrayMap0.size;
        System.arraycopy(arrayMap0.keys, 0, this.keys, 0, this.size);
        System.arraycopy(arrayMap0.values, 0, this.values, 0, this.size);
    }

    public ArrayMap(Class class0, Class class1) {
        this(false, 16, class0, class1);
    }

    public ArrayMap(boolean z, int v) {
        this.ordered = z;
        this.keys = new Object[v];
        this.values = new Object[v];
    }

    public ArrayMap(boolean z, int v, Class class0, Class class1) {
        this.ordered = z;
        this.keys = (Object[])ArrayReflection.newInstance(class0, v);
        this.values = (Object[])ArrayReflection.newInstance(class1, v);
    }

    public void clear() {
        Arrays.fill(this.keys, 0, this.size, null);
        Arrays.fill(this.values, 0, this.size, null);
        this.size = 0;
    }

    public void clear(int v) {
        if(this.keys.length <= v) {
            this.clear();
            return;
        }
        this.size = 0;
        this.resize(v);
    }

    public boolean containsKey(Object object0) {
        Object[] arr_object = this.keys;
        int v = this.size - 1;
        if(object0 == null) {
            while(v >= 0) {
                if(arr_object[v] == null) {
                    return true;
                }
                --v;
            }
            return false;
        }
        while(v >= 0) {
            if(object0.equals(arr_object[v])) {
                return true;
            }
            --v;
        }
        return false;
    }

    public boolean containsValue(Object object0, boolean z) {
        Object[] arr_object = this.values;
        int v = this.size - 1;
        if(!z && object0 != null) {
            while(v >= 0) {
                if(object0.equals(arr_object[v])) {
                    return true;
                }
                --v;
            }
            return false;
        }
        while(v >= 0) {
            if(arr_object[v] == object0) {
                return true;
            }
            --v;
        }
        return false;
    }

    public void ensureCapacity(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("additionalCapacity must be >= 0: " + v);
        }
        int v1 = this.size + v;
        if(v1 > this.keys.length) {
            this.resize(Math.max(Math.max(8, v1), ((int)(((float)this.size) * 1.75f))));
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
            this.entries1.index = 0;
            this.entries1.valid = true;
            this.entries2.valid = false;
            return this.entries1;
        }
        this.entries2.index = 0;
        this.entries2.valid = true;
        this.entries1.valid = false;
        return this.entries2;
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof ArrayMap)) {
            return false;
        }
        int v = this.size;
        if(((ArrayMap)object0).size != v) {
            return false;
        }
        Object[] arr_object = this.keys;
        Object[] arr_object1 = this.values;
        for(int v1 = 0; v1 < v; ++v1) {
            Object object1 = arr_object[v1];
            Object object2 = arr_object1[v1];
            if(object2 == null) {
                if(((ArrayMap)object0).get(object1, ObjectMap.dummy) != null) {
                    return false;
                }
            }
            else if(!object2.equals(((ArrayMap)object0).get(object1))) {
                return false;
            }
        }
        return true;
    }

    public boolean equalsIdentity(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof ArrayMap)) {
            return false;
        }
        int v = this.size;
        if(((ArrayMap)object0).size != v) {
            return false;
        }
        Object[] arr_object = this.keys;
        Object[] arr_object1 = this.values;
        for(int v1 = 0; v1 < v; ++v1) {
            if(arr_object1[v1] != ((ArrayMap)object0).get(arr_object[v1], ObjectMap.dummy)) {
                return false;
            }
        }
        return true;
    }

    public Object firstKey() {
        if(this.size == 0) {
            throw new IllegalStateException("Map is empty.");
        }
        return this.keys[0];
    }

    public Object firstValue() {
        if(this.size == 0) {
            throw new IllegalStateException("Map is empty.");
        }
        return this.values[0];
    }

    @Null
    public Object get(Object object0) {
        return this.get(object0, null);
    }

    @Null
    public Object get(Object object0, @Null Object object1) {
        Object[] arr_object = this.keys;
        int v = this.size - 1;
        if(object0 == null) {
            while(v >= 0) {
                if(arr_object[v] == null) {
                    return this.values[v];
                }
                --v;
            }
            return object1;
        }
        while(v >= 0) {
            if(object0.equals(arr_object[v])) {
                return this.values[v];
            }
            --v;
        }
        return object1;
    }

    @Null
    public Object getKey(Object object0, boolean z) {
        Object[] arr_object = this.values;
        int v = this.size - 1;
        if(!z && object0 != null) {
            while(v >= 0) {
                if(object0.equals(arr_object[v])) {
                    return this.keys[v];
                }
                --v;
            }
            return null;
        }
        while(v >= 0) {
            if(arr_object[v] == object0) {
                return this.keys[v];
            }
            --v;
        }
        return null;
    }

    public Object getKeyAt(int v) {
        if(v >= this.size) {
            throw new IndexOutOfBoundsException(String.valueOf(v));
        }
        return this.keys[v];
    }

    public Object getValueAt(int v) {
        if(v >= this.size) {
            throw new IndexOutOfBoundsException(String.valueOf(v));
        }
        return this.values[v];
    }

    @Override
    public int hashCode() {
        Object[] arr_object = this.keys;
        Object[] arr_object1 = this.values;
        int v = this.size;
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            Object object0 = arr_object[v1];
            Object object1 = arr_object1[v1];
            if(object0 != null) {
                v2 += object0.hashCode() * 0x1F;
            }
            if(object1 != null) {
                v2 += object1.hashCode();
            }
        }
        return v2;
    }

    public int indexOfKey(Object object0) {
        Object[] arr_object = this.keys;
        int v = 0;
        if(object0 == null) {
            int v1 = this.size;
            while(v < v1) {
                if(arr_object[v] == null) {
                    return v;
                }
                ++v;
            }
            return -1;
        }
        int v2 = this.size;
        while(v < v2) {
            if(object0.equals(arr_object[v])) {
                return v;
            }
            ++v;
        }
        return -1;
    }

    public int indexOfValue(Object object0, boolean z) {
        Object[] arr_object = this.values;
        int v = 0;
        if(!z && object0 != null) {
            int v1 = this.size;
            while(v < v1) {
                if(object0.equals(arr_object[v])) {
                    return v;
                }
                ++v;
            }
            return -1;
        }
        int v2 = this.size;
        while(v < v2) {
            if(arr_object[v] == object0) {
                return v;
            }
            ++v;
        }
        return -1;
    }

    public void insert(int v, Object object0, Object object1) {
        int v1 = this.size;
        if(v > v1) {
            throw new IndexOutOfBoundsException(String.valueOf(v));
        }
        if(v1 == this.keys.length) {
            this.resize(Math.max(8, ((int)(((float)v1) * 1.75f))));
        }
        if(this.ordered) {
            System.arraycopy(this.keys, v, this.keys, v + 1, this.size - v);
            System.arraycopy(this.values, v, this.values, v + 1, this.size - v);
        }
        else {
            int v2 = this.size;
            this.keys[v2] = this.keys[v];
            this.values[v2] = this.values[v];
        }
        ++this.size;
        this.keys[v] = object0;
        this.values[v] = object1;
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
            this.keys1.index = 0;
            this.keys1.valid = true;
            this.keys2.valid = false;
            return this.keys1;
        }
        this.keys2.index = 0;
        this.keys2.valid = true;
        this.keys1.valid = false;
        return this.keys2;
    }

    public boolean notEmpty() {
        return this.size > 0;
    }

    public Object peekKey() {
        return this.keys[this.size - 1];
    }

    public Object peekValue() {
        return this.values[this.size - 1];
    }

    public int put(Object object0, Object object1) {
        int v = this.indexOfKey(object0);
        if(v == -1) {
            int v1 = this.size;
            if(v1 == this.keys.length) {
                this.resize(Math.max(8, ((int)(((float)v1) * 1.75f))));
            }
            v = this.size;
            this.size = v + 1;
        }
        this.keys[v] = object0;
        this.values[v] = object1;
        return v;
    }

    public int put(Object object0, Object object1, int v) {
        int v1 = this.indexOfKey(object0);
        if(v1 == -1) {
            int v2 = this.size;
            if(v2 == this.keys.length) {
                this.resize(Math.max(8, ((int)(((float)v2) * 1.75f))));
            }
        }
        else {
            this.removeIndex(v1);
        }
        System.arraycopy(this.keys, v, this.keys, v + 1, this.size - v);
        System.arraycopy(this.values, v, this.values, v + 1, this.size - v);
        this.keys[v] = object0;
        this.values[v] = object1;
        ++this.size;
        return v;
    }

    public void putAll(ArrayMap arrayMap0) {
        this.putAll(arrayMap0, 0, arrayMap0.size);
    }

    public void putAll(ArrayMap arrayMap0, int v, int v1) {
        if(v + v1 > arrayMap0.size) {
            throw new IllegalArgumentException("offset + length must be <= size: " + v + " + " + v1 + " <= " + arrayMap0.size);
        }
        int v2 = this.size + v1 - v;
        if(v2 >= this.keys.length) {
            this.resize(Math.max(8, ((int)(((float)v2) * 1.75f))));
        }
        System.arraycopy(arrayMap0.keys, v, this.keys, this.size, v1);
        System.arraycopy(arrayMap0.values, v, this.values, this.size, v1);
        this.size += v1;
    }

    public void removeIndex(int v) {
        int v1 = this.size;
        if(v >= v1) {
            throw new IndexOutOfBoundsException(String.valueOf(v));
        }
        Object[] arr_object = this.keys;
        this.size = v1 - 1;
        if(this.ordered) {
            System.arraycopy(arr_object, v + 1, arr_object, v, this.size - v);
            System.arraycopy(this.values, v + 1, this.values, v, this.size - v);
        }
        else {
            int v2 = this.size;
            arr_object[v] = arr_object[v2];
            this.values[v] = this.values[v2];
        }
        int v3 = this.size;
        arr_object[v3] = null;
        this.values[v3] = null;
    }

    @Null
    public Object removeKey(Object object0) {
        Object[] arr_object = this.keys;
        int v = 0;
        if(object0 == null) {
            int v1 = this.size;
            while(v < v1) {
                if(arr_object[v] == null) {
                    Object object1 = this.values[v];
                    this.removeIndex(v);
                    return object1;
                }
                ++v;
            }
            return null;
        }
        int v2 = this.size;
        while(v < v2) {
            if(object0.equals(arr_object[v])) {
                Object object2 = this.values[v];
                this.removeIndex(v);
                return object2;
            }
            ++v;
        }
        return null;
    }

    public boolean removeValue(Object object0, boolean z) {
        Object[] arr_object = this.values;
        if(!z && object0 != null) {
            int v = this.size;
            for(int v1 = 0; v1 < v; ++v1) {
                if(object0.equals(arr_object[v1])) {
                    this.removeIndex(v1);
                    return true;
                }
            }
            return false;
        }
        int v2 = this.size;
        for(int v3 = 0; v3 < v2; ++v3) {
            if(arr_object[v3] == object0) {
                this.removeIndex(v3);
                return true;
            }
        }
        return false;
    }

    protected void resize(int v) {
        Object[] arr_object = (Object[])ArrayReflection.newInstance(this.keys.getClass().getComponentType(), v);
        System.arraycopy(this.keys, 0, arr_object, 0, Math.min(this.size, arr_object.length));
        this.keys = arr_object;
        Object[] arr_object1 = (Object[])ArrayReflection.newInstance(this.values.getClass().getComponentType(), v);
        System.arraycopy(this.values, 0, arr_object1, 0, Math.min(this.size, arr_object1.length));
        this.values = arr_object1;
    }

    public void reverse() {
        int v = this.size - 1;
        int v1 = this.size / 2;
        for(int v2 = 0; v2 < v1; ++v2) {
            int v3 = v - v2;
            Object[] arr_object = this.keys;
            Object object0 = arr_object[v2];
            arr_object[v2] = arr_object[v3];
            arr_object[v3] = object0;
            Object[] arr_object1 = this.values;
            Object object1 = arr_object1[v2];
            arr_object1[v2] = arr_object1[v3];
            arr_object1[v3] = object1;
        }
    }

    public void setKey(int v, Object object0) {
        if(v >= this.size) {
            throw new IndexOutOfBoundsException(String.valueOf(v));
        }
        this.keys[v] = object0;
    }

    public void setValue(int v, Object object0) {
        if(v >= this.size) {
            throw new IndexOutOfBoundsException(String.valueOf(v));
        }
        this.values[v] = object0;
    }

    public void shrink() {
        int v = this.size;
        if(this.keys.length == v) {
            return;
        }
        this.resize(v);
    }

    public void shuffle() {
        for(int v = this.size - 1; v >= 0; --v) {
            int v1 = MathUtils.random(v);
            Object[] arr_object = this.keys;
            Object object0 = arr_object[v];
            arr_object[v] = arr_object[v1];
            arr_object[v1] = object0;
            Object[] arr_object1 = this.values;
            Object object1 = arr_object1[v];
            arr_object1[v] = arr_object1[v1];
            arr_object1[v1] = object1;
        }
    }

    @Override
    public String toString() {
        if(this.size == 0) {
            return "{}";
        }
        Object[] arr_object = this.keys;
        Object[] arr_object1 = this.values;
        StringBuilder stringBuilder0 = new StringBuilder(0x20);
        stringBuilder0.append('{');
        stringBuilder0.append(arr_object[0]);
        stringBuilder0.append('=');
        stringBuilder0.append(arr_object1[0]);
        for(int v = 1; v < this.size; ++v) {
            stringBuilder0.append(", ");
            stringBuilder0.append(arr_object[v]);
            stringBuilder0.append('=');
            stringBuilder0.append(arr_object1[v]);
        }
        stringBuilder0.append('}');
        return "";
    }

    public void truncate(int v) {
        if(this.size <= v) {
            return;
        }
        for(int v1 = v; v1 < this.size; ++v1) {
            this.keys[v1] = null;
            this.values[v1] = null;
        }
        this.size = v;
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
            this.values1.index = 0;
            this.values1.valid = true;
            this.values2.valid = false;
            return this.values1;
        }
        this.values2.index = 0;
        this.values2.valid = true;
        this.values1.valid = false;
        return this.values2;
    }
}

