package com.badlogic.gdx.utils;

import com.badlogic.gdx.math.MathUtils;
import java.util.Arrays;

public class ShortArray {
    public short[] items;
    public boolean ordered;
    public int size;

    public ShortArray() {
        this(true, 16);
    }

    public ShortArray(int v) {
        this(true, v);
    }

    public ShortArray(ShortArray shortArray0) {
        this.ordered = shortArray0.ordered;
        this.size = shortArray0.size;
        int v = this.size;
        this.items = new short[v];
        System.arraycopy(shortArray0.items, 0, this.items, 0, v);
    }

    public ShortArray(boolean z, int v) {
        this.ordered = z;
        this.items = new short[v];
    }

    public ShortArray(boolean z, short[] arr_v, int v, int v1) {
        this(z, v1);
        this.size = v1;
        System.arraycopy(arr_v, v, this.items, 0, v1);
    }

    public ShortArray(short[] arr_v) {
        this(true, arr_v, 0, arr_v.length);
    }

    public void add(int v) {
        short[] arr_v = this.items;
        int v1 = this.size;
        if(v1 == arr_v.length) {
            arr_v = this.resize(Math.max(8, ((int)(((float)v1) * 1.75f))));
        }
        int v2 = this.size;
        this.size = v2 + 1;
        arr_v[v2] = (short)v;
    }

    public void add(short v) {
        short[] arr_v = this.items;
        int v1 = this.size;
        if(v1 == arr_v.length) {
            arr_v = this.resize(Math.max(8, ((int)(((float)v1) * 1.75f))));
        }
        int v2 = this.size;
        this.size = v2 + 1;
        arr_v[v2] = v;
    }

    public void add(short v, short v1) {
        short[] arr_v = this.items;
        int v2 = this.size;
        if(v2 + 1 >= arr_v.length) {
            arr_v = this.resize(Math.max(8, ((int)(((float)v2) * 1.75f))));
        }
        int v3 = this.size;
        arr_v[v3] = v;
        arr_v[v3 + 1] = v1;
        this.size = v3 + 2;
    }

    public void add(short v, short v1, short v2) {
        short[] arr_v = this.items;
        int v3 = this.size;
        if(v3 + 2 >= arr_v.length) {
            arr_v = this.resize(Math.max(8, ((int)(((float)v3) * 1.75f))));
        }
        int v4 = this.size;
        arr_v[v4] = v;
        arr_v[v4 + 1] = v1;
        arr_v[v4 + 2] = v2;
        this.size = v4 + 3;
    }

    public void add(short v, short v1, short v2, short v3) {
        short[] arr_v = this.items;
        int v4 = this.size;
        if(v4 + 3 >= arr_v.length) {
            arr_v = this.resize(Math.max(8, ((int)(((float)v4) * 1.8f))));
        }
        int v5 = this.size;
        arr_v[v5] = v;
        arr_v[v5 + 1] = v1;
        arr_v[v5 + 2] = v2;
        arr_v[v5 + 3] = v3;
        this.size = v5 + 4;
    }

    public void addAll(ShortArray shortArray0) {
        this.addAll(shortArray0.items, 0, shortArray0.size);
    }

    public void addAll(ShortArray shortArray0, int v, int v1) {
        if(v + v1 > shortArray0.size) {
            throw new IllegalArgumentException("offset + length must be <= size: " + v + " + " + v1 + " <= " + shortArray0.size);
        }
        this.addAll(shortArray0.items, v, v1);
    }

    public void addAll(short[] arr_v) {
        this.addAll(arr_v, 0, arr_v.length);
    }

    public void addAll(short[] arr_v, int v, int v1) {
        short[] arr_v1 = this.items;
        int v2 = this.size + v1;
        if(v2 > arr_v1.length) {
            arr_v1 = this.resize(Math.max(Math.max(8, v2), ((int)(((float)this.size) * 1.75f))));
        }
        System.arraycopy(arr_v, v, arr_v1, this.size, v1);
        this.size += v1;
    }

    public void clear() {
        this.size = 0;
    }

    public boolean contains(short v) {
        int v1 = this.size - 1;
        short[] arr_v = this.items;
        while(v1 >= 0) {
            if(arr_v[v1] == v) {
                return true;
            }
            --v1;
        }
        return false;
    }

    public short[] ensureCapacity(int v) {
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
        if(!(object0 instanceof ShortArray)) {
            return false;
        }
        if(!((ShortArray)object0).ordered) {
            return false;
        }
        int v = this.size;
        if(v != ((ShortArray)object0).size) {
            return false;
        }
        short[] arr_v = this.items;
        short[] arr_v1 = ((ShortArray)object0).items;
        for(int v1 = 0; v1 < v; ++v1) {
            if(arr_v[v1] != arr_v1[v1]) {
                return false;
            }
        }
        return true;
    }

    public short first() {
        if(this.size == 0) {
            throw new IllegalStateException("Array is empty.");
        }
        return this.items[0];
    }

    public short get(int v) {
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
        short[] arr_v = this.items;
        int v1 = this.size;
        int v2 = 1;
        for(int v = 0; v < v1; ++v) {
            v2 = v2 * 0x1F + arr_v[v];
        }
        return v2;
    }

    public void incr(int v, short v1) {
        if(v >= this.size) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        this.items[v] = (short)(this.items[v] + v1);
    }

    public void incr(short v) {
        short[] arr_v = this.items;
        int v1 = this.size;
        for(int v2 = 0; v2 < v1; ++v2) {
            arr_v[v2] = (short)(arr_v[v2] + v);
        }
    }

    public int indexOf(short v) {
        short[] arr_v = this.items;
        int v1 = this.size;
        for(int v2 = 0; v2 < v1; ++v2) {
            if(arr_v[v2] == v) {
                return v2;
            }
        }
        return -1;
    }

    public void insert(int v, short v1) {
        int v2 = this.size;
        if(v > v2) {
            throw new IndexOutOfBoundsException("index can\'t be > size: " + v + " > " + this.size);
        }
        short[] arr_v = v2 == this.items.length ? this.resize(Math.max(8, ((int)(((float)v2) * 1.75f)))) : this.items;
        if(this.ordered) {
            System.arraycopy(arr_v, v, arr_v, v + 1, this.size - v);
        }
        else {
            arr_v[this.size] = arr_v[v];
        }
        ++this.size;
        arr_v[v] = v1;
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

    public int lastIndexOf(char c) {
        short[] arr_v = this.items;
        for(int v = this.size - 1; v >= 0; --v) {
            if(arr_v[v] == c) {
                return v;
            }
        }
        return -1;
    }

    public void mul(int v, short v1) {
        if(v >= this.size) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        this.items[v] = (short)(this.items[v] * v1);
    }

    public void mul(short v) {
        short[] arr_v = this.items;
        int v1 = this.size;
        for(int v2 = 0; v2 < v1; ++v2) {
            arr_v[v2] = (short)(arr_v[v2] * v);
        }
    }

    public boolean notEmpty() {
        return this.size > 0;
    }

    public short peek() {
        return this.items[this.size - 1];
    }

    public short pop() {
        int v = this.size - 1;
        this.size = v;
        return this.items[v];
    }

    public short random() {
        int v = this.size;
        if(v == 0) {
            return 0;
        }
        short[] arr_v = this.items;
        return arr_v[MathUtils.random(0, v - 1)];
    }

    public boolean removeAll(ShortArray shortArray0) {
        int v = this.size;
        short[] arr_v = this.items;
        int v1 = shortArray0.size;
        int v2 = v;
        for(int v3 = 0; v3 < v1; ++v3) {
            int v4 = shortArray0.get(v3);
            for(int v5 = 0; v5 < v2; ++v5) {
                if(v4 == arr_v[v5]) {
                    this.removeIndex(v5);
                    --v2;
                    break;
                }
            }
        }
        return v2 != v;
    }

    public short removeIndex(int v) {
        int v1 = this.size;
        if(v >= v1) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        short[] arr_v = this.items;
        short v2 = arr_v[v];
        this.size = v1 - 1;
        if(this.ordered) {
            System.arraycopy(arr_v, v + 1, arr_v, v, this.size - v);
            return v2;
        }
        arr_v[v] = arr_v[this.size];
        return v2;
    }

    public void removeRange(int v, int v1) {
        int v2 = this.size;
        if(v1 >= v2) {
            throw new IndexOutOfBoundsException("end can\'t be >= size: " + v1 + " >= " + this.size);
        }
        if(v > v1) {
            throw new IndexOutOfBoundsException("start can\'t be > end: " + v + " > " + v1);
        }
        int v3 = v1 - v + 1;
        int v4 = v2 - v3;
        if(this.ordered) {
            System.arraycopy(this.items, v3 + v, this.items, v, v2 - (v3 + v));
        }
        else {
            int v5 = Math.max(v4, v1 + 1);
            System.arraycopy(this.items, v5, this.items, v, v2 - v5);
        }
        this.size = v4;
    }

    public boolean removeValue(short v) {
        short[] arr_v = this.items;
        int v1 = this.size;
        for(int v2 = 0; v2 < v1; ++v2) {
            if(arr_v[v2] == v) {
                this.removeIndex(v2);
                return true;
            }
        }
        return false;
    }

    protected short[] resize(int v) {
        short[] arr_v = new short[v];
        System.arraycopy(this.items, 0, arr_v, 0, Math.min(this.size, arr_v.length));
        this.items = arr_v;
        return arr_v;
    }

    public void reverse() {
        short[] arr_v = this.items;
        int v = this.size - 1;
        int v1 = this.size / 2;
        for(int v2 = 0; v2 < v1; ++v2) {
            int v3 = v - v2;
            short v4 = arr_v[v2];
            arr_v[v2] = arr_v[v3];
            arr_v[v3] = v4;
        }
    }

    public void set(int v, short v1) {
        if(v >= this.size) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        this.items[v] = v1;
    }

    public short[] setSize(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("newSize must be >= 0: " + v);
        }
        if(v > this.items.length) {
            this.resize(Math.max(8, v));
        }
        this.size = v;
        return this.items;
    }

    public short[] shrink() {
        int v = this.size;
        if(this.items.length != v) {
            this.resize(v);
        }
        return this.items;
    }

    public void shuffle() {
        short[] arr_v = this.items;
        for(int v = this.size - 1; v >= 0; --v) {
            int v1 = MathUtils.random(v);
            short v2 = arr_v[v];
            arr_v[v] = arr_v[v1];
            arr_v[v1] = v2;
        }
    }

    public void sort() {
        Arrays.sort(this.items, 0, this.size);
    }

    public void swap(int v, int v1) {
        int v2 = this.size;
        if(v >= v2) {
            throw new IndexOutOfBoundsException("first can\'t be >= size: " + v + " >= " + this.size);
        }
        if(v1 >= v2) {
            throw new IndexOutOfBoundsException("second can\'t be >= size: " + v1 + " >= " + this.size);
        }
        short[] arr_v = this.items;
        short v3 = arr_v[v];
        arr_v[v] = arr_v[v1];
        arr_v[v1] = v3;
    }

    public short[] toArray() {
        int v = this.size;
        short[] arr_v = new short[v];
        System.arraycopy(this.items, 0, arr_v, 0, v);
        return arr_v;
    }

    @Override
    public String toString() {
        if(this.size == 0) {
            return "[]";
        }
        short[] arr_v = this.items;
        StringBuilder stringBuilder0 = new StringBuilder(0x20);
        stringBuilder0.append('[');
        stringBuilder0.append(((int)arr_v[0]));
        for(int v = 1; v < this.size; ++v) {
            stringBuilder0.append(", ");
            stringBuilder0.append(((int)arr_v[v]));
        }
        stringBuilder0.append(']');
        return "";
    }

    public String toString(String s) {
        if(this.size == 0) {
            return "";
        }
        short[] arr_v = this.items;
        StringBuilder stringBuilder0 = new StringBuilder(0x20);
        stringBuilder0.append(((int)arr_v[0]));
        for(int v = 1; v < this.size; ++v) {
            stringBuilder0.append(s);
            stringBuilder0.append(((int)arr_v[v]));
        }
        return "";
    }

    public void truncate(int v) {
        if(this.size > v) {
            this.size = v;
        }
    }

    public static ShortArray with(short[] arr_v) {
        return new ShortArray(arr_v);
    }
}

