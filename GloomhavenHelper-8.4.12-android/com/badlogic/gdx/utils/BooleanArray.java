package com.badlogic.gdx.utils;

import com.badlogic.gdx.math.MathUtils;

public class BooleanArray {
    public boolean[] items;
    public boolean ordered;
    public int size;

    public BooleanArray() {
        this(true, 16);
    }

    public BooleanArray(int v) {
        this(true, v);
    }

    public BooleanArray(BooleanArray booleanArray0) {
        this.ordered = booleanArray0.ordered;
        this.size = booleanArray0.size;
        int v = this.size;
        this.items = new boolean[v];
        System.arraycopy(booleanArray0.items, 0, this.items, 0, v);
    }

    public BooleanArray(boolean z, int v) {
        this.ordered = z;
        this.items = new boolean[v];
    }

    public BooleanArray(boolean z, boolean[] arr_z, int v, int v1) {
        this(z, v1);
        this.size = v1;
        System.arraycopy(arr_z, v, this.items, 0, v1);
    }

    public BooleanArray(boolean[] arr_z) {
        this(true, arr_z, 0, arr_z.length);
    }

    public void add(boolean z) {
        boolean[] arr_z = this.items;
        int v = this.size;
        if(v == arr_z.length) {
            arr_z = this.resize(Math.max(8, ((int)(((float)v) * 1.75f))));
        }
        int v1 = this.size;
        this.size = v1 + 1;
        arr_z[v1] = z;
    }

    public void add(boolean z, boolean z1) {
        boolean[] arr_z = this.items;
        int v = this.size;
        if(v + 1 >= arr_z.length) {
            arr_z = this.resize(Math.max(8, ((int)(((float)v) * 1.75f))));
        }
        int v1 = this.size;
        arr_z[v1] = z;
        arr_z[v1 + 1] = z1;
        this.size = v1 + 2;
    }

    public void add(boolean z, boolean z1, boolean z2) {
        boolean[] arr_z = this.items;
        int v = this.size;
        if(v + 2 >= arr_z.length) {
            arr_z = this.resize(Math.max(8, ((int)(((float)v) * 1.75f))));
        }
        int v1 = this.size;
        arr_z[v1] = z;
        arr_z[v1 + 1] = z1;
        arr_z[v1 + 2] = z2;
        this.size = v1 + 3;
    }

    public void add(boolean z, boolean z1, boolean z2, boolean z3) {
        boolean[] arr_z = this.items;
        int v = this.size;
        if(v + 3 >= arr_z.length) {
            arr_z = this.resize(Math.max(8, ((int)(((float)v) * 1.8f))));
        }
        int v1 = this.size;
        arr_z[v1] = z;
        arr_z[v1 + 1] = z1;
        arr_z[v1 + 2] = z2;
        arr_z[v1 + 3] = z3;
        this.size = v1 + 4;
    }

    public void addAll(BooleanArray booleanArray0) {
        this.addAll(booleanArray0.items, 0, booleanArray0.size);
    }

    public void addAll(BooleanArray booleanArray0, int v, int v1) {
        if(v + v1 > booleanArray0.size) {
            throw new IllegalArgumentException("offset + length must be <= size: " + v + " + " + v1 + " <= " + booleanArray0.size);
        }
        this.addAll(booleanArray0.items, v, v1);
    }

    public void addAll(boolean[] arr_z) {
        this.addAll(arr_z, 0, arr_z.length);
    }

    public void addAll(boolean[] arr_z, int v, int v1) {
        boolean[] arr_z1 = this.items;
        int v2 = this.size + v1;
        if(v2 > arr_z1.length) {
            arr_z1 = this.resize(Math.max(Math.max(8, v2), ((int)(((float)this.size) * 1.75f))));
        }
        System.arraycopy(arr_z, v, arr_z1, this.size, v1);
        this.size += v1;
    }

    public void clear() {
        this.size = 0;
    }

    public boolean[] ensureCapacity(int v) {
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
        if(!(object0 instanceof BooleanArray)) {
            return false;
        }
        if(!((BooleanArray)object0).ordered) {
            return false;
        }
        int v = this.size;
        if(v != ((BooleanArray)object0).size) {
            return false;
        }
        boolean[] arr_z = this.items;
        boolean[] arr_z1 = ((BooleanArray)object0).items;
        for(int v1 = 0; v1 < v; ++v1) {
            if(arr_z[v1] != arr_z1[v1]) {
                return false;
            }
        }
        return true;
    }

    public boolean first() {
        if(this.size == 0) {
            throw new IllegalStateException("Array is empty.");
        }
        return this.items[0];
    }

    public boolean get(int v) {
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
        boolean[] arr_z = this.items;
        int v1 = this.size;
        int v2 = 1;
        for(int v = 0; v < v1; ++v) {
            v2 = v2 * 0x1F + (arr_z[v] ? 0x4CF : 0x4D5);
        }
        return v2;
    }

    public void insert(int v, boolean z) {
        int v1 = this.size;
        if(v > v1) {
            throw new IndexOutOfBoundsException("index can\'t be > size: " + v + " > " + this.size);
        }
        boolean[] arr_z = v1 == this.items.length ? this.resize(Math.max(8, ((int)(((float)v1) * 1.75f)))) : this.items;
        if(this.ordered) {
            System.arraycopy(arr_z, v, arr_z, v + 1, this.size - v);
        }
        else {
            arr_z[this.size] = arr_z[v];
        }
        ++this.size;
        arr_z[v] = z;
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

    public boolean notEmpty() {
        return this.size > 0;
    }

    public boolean peek() {
        return this.items[this.size - 1];
    }

    public boolean pop() {
        int v = this.size - 1;
        this.size = v;
        return this.items[v];
    }

    public boolean random() {
        int v = this.size;
        if(v == 0) {
            return false;
        }
        boolean[] arr_z = this.items;
        return arr_z[MathUtils.random(0, v - 1)];
    }

    public boolean removeAll(BooleanArray booleanArray0) {
        int v = this.size;
        boolean[] arr_z = this.items;
        int v1 = booleanArray0.size;
        int v2 = v;
        for(int v3 = 0; v3 < v1; ++v3) {
            boolean z = booleanArray0.get(v3);
            for(int v4 = 0; v4 < v2; ++v4) {
                if(z == arr_z[v4]) {
                    this.removeIndex(v4);
                    --v2;
                    break;
                }
            }
        }
        return v2 != v;
    }

    public boolean removeIndex(int v) {
        int v1 = this.size;
        if(v >= v1) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        boolean[] arr_z = this.items;
        boolean z = arr_z[v];
        this.size = v1 - 1;
        if(this.ordered) {
            System.arraycopy(arr_z, v + 1, arr_z, v, this.size - v);
            return z;
        }
        arr_z[v] = arr_z[this.size];
        return z;
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

    protected boolean[] resize(int v) {
        boolean[] arr_z = new boolean[v];
        System.arraycopy(this.items, 0, arr_z, 0, Math.min(this.size, arr_z.length));
        this.items = arr_z;
        return arr_z;
    }

    public void reverse() {
        boolean[] arr_z = this.items;
        int v = this.size - 1;
        int v1 = this.size / 2;
        for(int v2 = 0; v2 < v1; ++v2) {
            int v3 = v - v2;
            boolean z = arr_z[v2];
            arr_z[v2] = arr_z[v3];
            arr_z[v3] = z;
        }
    }

    public void set(int v, boolean z) {
        if(v >= this.size) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        this.items[v] = z;
    }

    public boolean[] setSize(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("newSize must be >= 0: " + v);
        }
        if(v > this.items.length) {
            this.resize(Math.max(8, v));
        }
        this.size = v;
        return this.items;
    }

    public boolean[] shrink() {
        int v = this.size;
        if(this.items.length != v) {
            this.resize(v);
        }
        return this.items;
    }

    public void shuffle() {
        boolean[] arr_z = this.items;
        for(int v = this.size - 1; v >= 0; --v) {
            int v1 = MathUtils.random(v);
            boolean z = arr_z[v];
            arr_z[v] = arr_z[v1];
            arr_z[v1] = z;
        }
    }

    public void swap(int v, int v1) {
        int v2 = this.size;
        if(v >= v2) {
            throw new IndexOutOfBoundsException("first can\'t be >= size: " + v + " >= " + this.size);
        }
        if(v1 >= v2) {
            throw new IndexOutOfBoundsException("second can\'t be >= size: " + v1 + " >= " + this.size);
        }
        boolean[] arr_z = this.items;
        boolean z = arr_z[v];
        arr_z[v] = arr_z[v1];
        arr_z[v1] = z;
    }

    public boolean[] toArray() {
        int v = this.size;
        boolean[] arr_z = new boolean[v];
        System.arraycopy(this.items, 0, arr_z, 0, v);
        return arr_z;
    }

    @Override
    public String toString() {
        if(this.size == 0) {
            return "[]";
        }
        boolean[] arr_z = this.items;
        StringBuilder stringBuilder0 = new StringBuilder(0x20);
        stringBuilder0.append('[');
        stringBuilder0.append(arr_z[0]);
        for(int v = 1; v < this.size; ++v) {
            stringBuilder0.append(", ");
            stringBuilder0.append(arr_z[v]);
        }
        stringBuilder0.append(']');
        return "";
    }

    public String toString(String s) {
        if(this.size == 0) {
            return "";
        }
        boolean[] arr_z = this.items;
        StringBuilder stringBuilder0 = new StringBuilder(0x20);
        stringBuilder0.append(arr_z[0]);
        for(int v = 1; v < this.size; ++v) {
            stringBuilder0.append(s);
            stringBuilder0.append(arr_z[v]);
        }
        return "";
    }

    public void truncate(int v) {
        if(this.size > v) {
            this.size = v;
        }
    }

    public static BooleanArray with(boolean[] arr_z) {
        return new BooleanArray(arr_z);
    }
}

