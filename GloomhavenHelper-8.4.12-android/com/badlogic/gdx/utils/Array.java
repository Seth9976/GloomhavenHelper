package com.badlogic.gdx.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.reflect.ArrayReflection;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Array implements Iterable {
    public static class ArrayIterable implements Iterable {
        private final boolean allowRemove;
        private final Array array;
        private ArrayIterator iterator1;
        private ArrayIterator iterator2;

        public ArrayIterable(Array array0) {
            this(array0, true);
        }

        public ArrayIterable(Array array0, boolean z) {
            this.array = array0;
            this.allowRemove = z;
        }

        public ArrayIterator iterator() {
            if(Collections.allocateIterators) {
                return new ArrayIterator(this.array, this.allowRemove);
            }
            if(this.iterator1 == null) {
                this.iterator1 = new ArrayIterator(this.array, this.allowRemove);
                this.iterator2 = new ArrayIterator(this.array, this.allowRemove);
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

        @Override
        public Iterator iterator() {
            return this.iterator();
        }
    }

    public static class ArrayIterator implements Iterable, Iterator {
        private final boolean allowRemove;
        private final Array array;
        int index;
        boolean valid;

        public ArrayIterator(Array array0) {
            this(array0, true);
        }

        public ArrayIterator(Array array0, boolean z) {
            this.valid = true;
            this.array = array0;
            this.allowRemove = z;
        }

        @Override
        public boolean hasNext() {
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            return this.index < this.array.size;
        }

        public ArrayIterator iterator() [...] // Inlined contents

        @Override
        public Iterator iterator() {
            return this;
        }

        @Override
        public Object next() {
            if(this.index >= this.array.size) {
                throw new NoSuchElementException(String.valueOf(this.index));
            }
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            int v = this.index;
            this.index = v + 1;
            return this.array.items[v];
        }

        @Override
        public void remove() {
            if(!this.allowRemove) {
                throw new GdxRuntimeException("Remove not allowed.");
            }
            --this.index;
            this.array.removeIndex(this.index);
        }

        public void reset() {
            this.index = 0;
        }
    }

    public Object[] items;
    private ArrayIterable iterable;
    public boolean ordered;
    private PredicateIterable predicateIterable;
    public int size;

    public Array() {
        this(true, 16);
    }

    public Array(int v) {
        this(true, v);
    }

    public Array(Array array0) {
        this(array0.ordered, array0.size, array0.items.getClass().getComponentType());
        this.size = array0.size;
        System.arraycopy(array0.items, 0, this.items, 0, this.size);
    }

    public Array(Class class0) {
        this(true, 16, class0);
    }

    public Array(boolean z, int v) {
        this.ordered = z;
        this.items = new Object[v];
    }

    public Array(boolean z, int v, Class class0) {
        this.ordered = z;
        this.items = (Object[])ArrayReflection.newInstance(class0, v);
    }

    public Array(boolean z, Object[] arr_object, int v, int v1) {
        this(z, v1, arr_object.getClass().getComponentType());
        this.size = v1;
        System.arraycopy(arr_object, v, this.items, 0, this.size);
    }

    public Array(Object[] arr_object) {
        this(true, arr_object, 0, arr_object.length);
    }

    public void add(Object object0) {
        Object[] arr_object = this.items;
        int v = this.size;
        if(v == arr_object.length) {
            arr_object = this.resize(Math.max(8, ((int)(((float)v) * 1.75f))));
        }
        int v1 = this.size;
        this.size = v1 + 1;
        arr_object[v1] = object0;
    }

    public void add(Object object0, Object object1) {
        Object[] arr_object = this.items;
        int v = this.size;
        if(v + 1 >= arr_object.length) {
            arr_object = this.resize(Math.max(8, ((int)(((float)v) * 1.75f))));
        }
        int v1 = this.size;
        arr_object[v1] = object0;
        arr_object[v1 + 1] = object1;
        this.size = v1 + 2;
    }

    public void add(Object object0, Object object1, Object object2) {
        Object[] arr_object = this.items;
        int v = this.size;
        if(v + 2 >= arr_object.length) {
            arr_object = this.resize(Math.max(8, ((int)(((float)v) * 1.75f))));
        }
        int v1 = this.size;
        arr_object[v1] = object0;
        arr_object[v1 + 1] = object1;
        arr_object[v1 + 2] = object2;
        this.size = v1 + 3;
    }

    public void add(Object object0, Object object1, Object object2, Object object3) {
        Object[] arr_object = this.items;
        int v = this.size;
        if(v + 3 >= arr_object.length) {
            arr_object = this.resize(Math.max(8, ((int)(((float)v) * 1.8f))));
        }
        int v1 = this.size;
        arr_object[v1] = object0;
        arr_object[v1 + 1] = object1;
        arr_object[v1 + 2] = object2;
        arr_object[v1 + 3] = object3;
        this.size = v1 + 4;
    }

    public void addAll(Array array0) {
        this.addAll(array0.items, 0, array0.size);
    }

    public void addAll(Array array0, int v, int v1) {
        if(v + v1 > array0.size) {
            throw new IllegalArgumentException("start + count must be <= size: " + v + " + " + v1 + " <= " + array0.size);
        }
        this.addAll(array0.items, v, v1);
    }

    public void addAll(Object[] arr_object) {
        this.addAll(arr_object, 0, arr_object.length);
    }

    public void addAll(Object[] arr_object, int v, int v1) {
        Object[] arr_object1 = this.items;
        int v2 = this.size + v1;
        if(v2 > arr_object1.length) {
            arr_object1 = this.resize(Math.max(Math.max(8, v2), ((int)(((float)this.size) * 1.75f))));
        }
        System.arraycopy(arr_object, v, arr_object1, this.size, v1);
        this.size = v2;
    }

    public void clear() {
        Arrays.fill(this.items, 0, this.size, null);
        this.size = 0;
    }

    public boolean contains(@Null Object object0, boolean z) {
        Object[] arr_object = this.items;
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

    public boolean containsAll(Array array0, boolean z) {
        Object[] arr_object = array0.items;
        int v = array0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(!this.contains(arr_object[v1], z)) {
                return false;
            }
        }
        return true;
    }

    public boolean containsAny(Array array0, boolean z) {
        Object[] arr_object = array0.items;
        int v = array0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(this.contains(arr_object[v1], z)) {
                return true;
            }
        }
        return false;
    }

    public Object[] ensureCapacity(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("additionalCapacity must be >= 0: " + v);
        }
        int v1 = this.size + v;
        if(v1 > this.items.length) {
            this.resize(Math.max(Math.max(8, v1), ((int)(((float)this.size) * 1.75f))));
        }
        return this.items;
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!this.ordered) {
            return false;
        }
        if(!(object0 instanceof Array)) {
            return false;
        }
        if(!((Array)object0).ordered) {
            return false;
        }
        int v = this.size;
        if(v != ((Array)object0).size) {
            return false;
        }
        Object[] arr_object = this.items;
        Object[] arr_object1 = ((Array)object0).items;
        for(int v1 = 0; v1 < v; ++v1) {
            Object object1 = arr_object[v1];
            Object object2 = arr_object1[v1];
            if(object1 != null) {
                if(!object1.equals(object2)) {
                    return false;
                }
            }
            else if(object2 == null) {
            }
            else {
                return false;
            }
        }
        return true;
    }

    public boolean equalsIdentity(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!this.ordered) {
            return false;
        }
        if(!(object0 instanceof Array)) {
            return false;
        }
        if(!((Array)object0).ordered) {
            return false;
        }
        int v = this.size;
        if(v != ((Array)object0).size) {
            return false;
        }
        Object[] arr_object = this.items;
        Object[] arr_object1 = ((Array)object0).items;
        for(int v1 = 0; v1 < v; ++v1) {
            if(arr_object[v1] != arr_object1[v1]) {
                return false;
            }
        }
        return true;
    }

    public Object first() {
        if(this.size == 0) {
            throw new IllegalStateException("Array is empty.");
        }
        return this.items[0];
    }

    public Object get(int v) {
        if(v >= this.size) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        return this.items[v];
    }

    @Override
    public int hashCode() {
        if(!this.ordered) {
            return super.hashCode();
        }
        Object[] arr_object = this.items;
        int v1 = this.size;
        int v2 = 1;
        for(int v = 0; v < v1; ++v) {
            v2 *= 0x1F;
            Object object0 = arr_object[v];
            if(object0 != null) {
                v2 += object0.hashCode();
            }
        }
        return v2;
    }

    public int indexOf(@Null Object object0, boolean z) {
        Object[] arr_object = this.items;
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

    public void insert(int v, Object object0) {
        int v1 = this.size;
        if(v > v1) {
            throw new IndexOutOfBoundsException("index can\'t be > size: " + v + " > " + this.size);
        }
        Object[] arr_object = v1 == this.items.length ? this.resize(Math.max(8, ((int)(((float)v1) * 1.75f)))) : this.items;
        if(this.ordered) {
            System.arraycopy(arr_object, v, arr_object, v + 1, this.size - v);
        }
        else {
            arr_object[this.size] = arr_object[v];
        }
        ++this.size;
        arr_object[v] = object0;
    }

    public void insertRange(int v, int v1) {
        int v2 = this.size;
        if(v > v2) {
            throw new IndexOutOfBoundsException("index can\'t be > size: " + v + " > " + this.size);
        }
        int v3 = v2 + v1;
        if(v3 > this.items.length) {
            this.items = this.resize(Math.max(Math.max(8, v3), ((int)(((float)this.size) * 1.75f))));
        }
        System.arraycopy(this.items, v, this.items, v1 + v, this.size - v);
        this.size = v3;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public ArrayIterator iterator() {
        if(Collections.allocateIterators) {
            return new ArrayIterator(this, true);
        }
        if(this.iterable == null) {
            this.iterable = new ArrayIterable(this);
        }
        return this.iterable.iterator();
    }

    @Override
    public Iterator iterator() {
        return this.iterator();
    }

    public int lastIndexOf(@Null Object object0, boolean z) {
        Object[] arr_object = this.items;
        if(!z && object0 != null) {
            for(int v = this.size - 1; v >= 0; --v) {
                if(object0.equals(arr_object[v])) {
                    return v;
                }
            }
            return -1;
        }
        for(int v1 = this.size - 1; v1 >= 0; --v1) {
            if(arr_object[v1] == object0) {
                return v1;
            }
        }
        return -1;
    }

    public boolean notEmpty() {
        return this.size > 0;
    }

    public static Array of(Class class0) {
        return new Array(class0);
    }

    public static Array of(boolean z, int v, Class class0) {
        return new Array(z, v, class0);
    }

    public Object peek() {
        int v = this.size;
        if(v == 0) {
            throw new IllegalStateException("Array is empty.");
        }
        return this.items[v - 1];
    }

    public Object pop() {
        int v = this.size;
        if(v == 0) {
            throw new IllegalStateException("Array is empty.");
        }
        this.size = v - 1;
        Object[] arr_object = this.items;
        int v1 = this.size;
        Object object0 = arr_object[v1];
        arr_object[v1] = null;
        return object0;
    }

    @Null
    public Object random() {
        int v = this.size;
        if(v == 0) {
            return null;
        }
        Object[] arr_object = this.items;
        return arr_object[MathUtils.random(0, v - 1)];
    }

    public boolean removeAll(Array array0, boolean z) {
        int v2;
        int v = this.size;
        Object[] arr_object = this.items;
        if(z) {
            int v1 = array0.size;
            v2 = v;
            for(int v3 = 0; v3 < v1; ++v3) {
                Object object0 = array0.get(v3);
                for(int v4 = 0; v4 < v2; ++v4) {
                    if(object0 == arr_object[v4]) {
                        this.removeIndex(v4);
                        --v2;
                        break;
                    }
                }
            }
            return v2 != v;
        }
        int v5 = array0.size;
        v2 = v;
        for(int v6 = 0; v6 < v5; ++v6) {
            Object object1 = array0.get(v6);
            for(int v7 = 0; v7 < v2; ++v7) {
                if(object1.equals(arr_object[v7])) {
                    this.removeIndex(v7);
                    --v2;
                    break;
                }
            }
        }
        return v2 != v;
    }

    public Object removeIndex(int v) {
        int v1 = this.size;
        if(v >= v1) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        Object[] arr_object = this.items;
        Object object0 = arr_object[v];
        this.size = v1 - 1;
        if(this.ordered) {
            System.arraycopy(arr_object, v + 1, arr_object, v, this.size - v);
        }
        else {
            arr_object[v] = arr_object[this.size];
        }
        arr_object[this.size] = null;
        return object0;
    }

    public void removeRange(int v, int v1) {
        int v2 = this.size;
        if(v1 >= v2) {
            throw new IndexOutOfBoundsException("end can\'t be >= size: " + v1 + " >= " + this.size);
        }
        if(v > v1) {
            throw new IndexOutOfBoundsException("start can\'t be > end: " + v + " > " + v1);
        }
        Object[] arr_object = this.items;
        int v3 = v1 - v + 1;
        int v4 = v2 - v3;
        if(this.ordered) {
            System.arraycopy(arr_object, v3 + v, arr_object, v, v2 - (v3 + v));
        }
        else {
            int v5 = Math.max(v4, v1 + 1);
            System.arraycopy(arr_object, v5, arr_object, v, v2 - v5);
        }
        for(int v6 = v4; v6 < v2; ++v6) {
            arr_object[v6] = null;
        }
        this.size = v4;
    }

    public boolean removeValue(@Null Object object0, boolean z) {
        Object[] arr_object = this.items;
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

    protected Object[] resize(int v) {
        Object[] arr_object = this.items;
        Object[] arr_object1 = (Object[])ArrayReflection.newInstance(arr_object.getClass().getComponentType(), v);
        System.arraycopy(arr_object, 0, arr_object1, 0, Math.min(this.size, arr_object1.length));
        this.items = arr_object1;
        return arr_object1;
    }

    public void reverse() {
        Object[] arr_object = this.items;
        int v = this.size - 1;
        int v1 = this.size / 2;
        for(int v2 = 0; v2 < v1; ++v2) {
            int v3 = v - v2;
            Object object0 = arr_object[v2];
            arr_object[v2] = arr_object[v3];
            arr_object[v3] = object0;
        }
    }

    public Iterable select(Predicate predicate0) {
        if(Collections.allocateIterators) {
            return new PredicateIterable(this, predicate0);
        }
        PredicateIterable predicate$PredicateIterable0 = this.predicateIterable;
        if(predicate$PredicateIterable0 == null) {
            this.predicateIterable = new PredicateIterable(this, predicate0);
            return this.predicateIterable;
        }
        predicate$PredicateIterable0.set(this, predicate0);
        return this.predicateIterable;
    }

    public Object selectRanked(Comparator comparator0, int v) {
        if(v < 1) {
            throw new GdxRuntimeException("nth_lowest must be greater than 0, 1 = first, 2 = second...");
        }
        return Select.instance().select(this.items, comparator0, v, this.size);
    }

    public int selectRankedIndex(Comparator comparator0, int v) {
        if(v < 1) {
            throw new GdxRuntimeException("nth_lowest must be greater than 0, 1 = first, 2 = second...");
        }
        return Select.instance().selectIndex(this.items, comparator0, v, this.size);
    }

    public void set(int v, Object object0) {
        if(v >= this.size) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        this.items[v] = object0;
    }

    public Object[] setSize(int v) {
        this.truncate(v);
        if(v > this.items.length) {
            this.resize(Math.max(8, v));
        }
        this.size = v;
        return this.items;
    }

    public Object[] shrink() {
        int v = this.size;
        if(this.items.length != v) {
            this.resize(v);
        }
        return this.items;
    }

    public void shuffle() {
        Object[] arr_object = this.items;
        for(int v = this.size - 1; v >= 0; --v) {
            int v1 = MathUtils.random(v);
            Object object0 = arr_object[v];
            arr_object[v] = arr_object[v1];
            arr_object[v1] = object0;
        }
    }

    public void sort() {
        Sort.instance().sort(this.items, 0, this.size);
    }

    public void sort(Comparator comparator0) {
        Sort.instance().sort(this.items, comparator0, 0, this.size);
    }

    public void swap(int v, int v1) {
        int v2 = this.size;
        if(v >= v2) {
            throw new IndexOutOfBoundsException("first can\'t be >= size: " + v + " >= " + this.size);
        }
        if(v1 >= v2) {
            throw new IndexOutOfBoundsException("second can\'t be >= size: " + v1 + " >= " + this.size);
        }
        Object[] arr_object = this.items;
        Object object0 = arr_object[v];
        arr_object[v] = arr_object[v1];
        arr_object[v1] = object0;
    }

    public Object[] toArray() {
        return this.toArray(this.items.getClass().getComponentType());
    }

    public Object[] toArray(Class class0) {
        Object[] arr_object = (Object[])ArrayReflection.newInstance(class0, this.size);
        System.arraycopy(this.items, 0, arr_object, 0, this.size);
        return arr_object;
    }

    @Override
    public String toString() {
        if(this.size == 0) {
            return "[]";
        }
        Object[] arr_object = this.items;
        StringBuilder stringBuilder0 = new StringBuilder(0x20);
        stringBuilder0.append('[');
        stringBuilder0.append(arr_object[0]);
        for(int v = 1; v < this.size; ++v) {
            stringBuilder0.append(", ");
            stringBuilder0.append(arr_object[v]);
        }
        stringBuilder0.append(']');
        return "";
    }

    public String toString(String s) {
        if(this.size == 0) {
            return "";
        }
        Object[] arr_object = this.items;
        StringBuilder stringBuilder0 = new StringBuilder(0x20);
        stringBuilder0.append(arr_object[0]);
        for(int v = 1; v < this.size; ++v) {
            stringBuilder0.append(s);
            stringBuilder0.append(arr_object[v]);
        }
        return "";
    }

    public void truncate(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("newSize must be >= 0: " + v);
        }
        if(this.size <= v) {
            return;
        }
        for(int v1 = v; v1 < this.size; ++v1) {
            this.items[v1] = null;
        }
        this.size = v;
    }

    public static Array with(Object[] arr_object) [...] // Inlined contents
}

