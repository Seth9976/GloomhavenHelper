package com.badlogic.gdx.utils;

import com.badlogic.gdx.math.MathUtils;
import java.util.Arrays;

public class FloatArray {
    public float[] items;
    public boolean ordered;
    public int size;

    public FloatArray() {
        this(true, 16);
    }

    public FloatArray(int v) {
        this(true, v);
    }

    public FloatArray(FloatArray floatArray0) {
        this.ordered = floatArray0.ordered;
        this.size = floatArray0.size;
        int v = this.size;
        this.items = new float[v];
        System.arraycopy(floatArray0.items, 0, this.items, 0, v);
    }

    public FloatArray(boolean z, int v) {
        this.ordered = z;
        this.items = new float[v];
    }

    public FloatArray(boolean z, float[] arr_f, int v, int v1) {
        this(z, v1);
        this.size = v1;
        System.arraycopy(arr_f, v, this.items, 0, v1);
    }

    public FloatArray(float[] arr_f) {
        this(true, arr_f, 0, arr_f.length);
    }

    public void add(float f) {
        float[] arr_f = this.items;
        int v = this.size;
        if(v == arr_f.length) {
            arr_f = this.resize(Math.max(8, ((int)(((float)v) * 1.75f))));
        }
        int v1 = this.size;
        this.size = v1 + 1;
        arr_f[v1] = f;
    }

    public void add(float f, float f1) {
        float[] arr_f = this.items;
        int v = this.size;
        if(v + 1 >= arr_f.length) {
            arr_f = this.resize(Math.max(8, ((int)(((float)v) * 1.75f))));
        }
        int v1 = this.size;
        arr_f[v1] = f;
        arr_f[v1 + 1] = f1;
        this.size = v1 + 2;
    }

    public void add(float f, float f1, float f2) {
        float[] arr_f = this.items;
        int v = this.size;
        if(v + 2 >= arr_f.length) {
            arr_f = this.resize(Math.max(8, ((int)(((float)v) * 1.75f))));
        }
        int v1 = this.size;
        arr_f[v1] = f;
        arr_f[v1 + 1] = f1;
        arr_f[v1 + 2] = f2;
        this.size = v1 + 3;
    }

    public void add(float f, float f1, float f2, float f3) {
        float[] arr_f = this.items;
        int v = this.size;
        if(v + 3 >= arr_f.length) {
            arr_f = this.resize(Math.max(8, ((int)(((float)v) * 1.8f))));
        }
        int v1 = this.size;
        arr_f[v1] = f;
        arr_f[v1 + 1] = f1;
        arr_f[v1 + 2] = f2;
        arr_f[v1 + 3] = f3;
        this.size = v1 + 4;
    }

    public void addAll(FloatArray floatArray0) {
        this.addAll(floatArray0.items, 0, floatArray0.size);
    }

    public void addAll(FloatArray floatArray0, int v, int v1) {
        if(v + v1 > floatArray0.size) {
            throw new IllegalArgumentException("offset + length must be <= size: " + v + " + " + v1 + " <= " + floatArray0.size);
        }
        this.addAll(floatArray0.items, v, v1);
    }

    public void addAll(float[] arr_f) {
        this.addAll(arr_f, 0, arr_f.length);
    }

    public void addAll(float[] arr_f, int v, int v1) {
        float[] arr_f1 = this.items;
        int v2 = this.size + v1;
        if(v2 > arr_f1.length) {
            arr_f1 = this.resize(Math.max(Math.max(8, v2), ((int)(((float)this.size) * 1.75f))));
        }
        System.arraycopy(arr_f, v, arr_f1, this.size, v1);
        this.size += v1;
    }

    public void clear() {
        this.size = 0;
    }

    public boolean contains(float f) {
        int v = this.size - 1;
        float[] arr_f = this.items;
        while(v >= 0) {
            if(arr_f[v] == f) {
                return true;
            }
            --v;
        }
        return false;
    }

    public float[] ensureCapacity(int v) {
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
        if(!(object0 instanceof FloatArray)) {
            return false;
        }
        if(!((FloatArray)object0).ordered) {
            return false;
        }
        int v = this.size;
        if(v != ((FloatArray)object0).size) {
            return false;
        }
        float[] arr_f = this.items;
        float[] arr_f1 = ((FloatArray)object0).items;
        for(int v1 = 0; v1 < v; ++v1) {
            if(arr_f[v1] != arr_f1[v1]) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object0, float f) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof FloatArray)) {
            return false;
        }
        int v = this.size;
        if(v != ((FloatArray)object0).size) {
            return false;
        }
        if(!this.ordered) {
            return false;
        }
        if(!((FloatArray)object0).ordered) {
            return false;
        }
        float[] arr_f = this.items;
        float[] arr_f1 = ((FloatArray)object0).items;
        for(int v1 = 0; v1 < v; ++v1) {
            if(Math.abs(arr_f[v1] - arr_f1[v1]) > f) {
                return false;
            }
        }
        return true;
    }

    public float first() {
        if(this.size == 0) {
            throw new IllegalStateException("Array is empty.");
        }
        return this.items[0];
    }

    public float get(int v) {
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
        float[] arr_f = this.items;
        int v1 = this.size;
        int v2 = 1;
        for(int v = 0; v < v1; ++v) {
            v2 = v2 * 0x1F + NumberUtils.floatToRawIntBits(arr_f[v]);
        }
        return v2;
    }

    public void incr(float f) {
        float[] arr_f = this.items;
        int v = this.size;
        for(int v1 = 0; v1 < v; ++v1) {
            arr_f[v1] += f;
        }
    }

    public void incr(int v, float f) {
        if(v >= this.size) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        this.items[v] += f;
    }

    public int indexOf(float f) {
        float[] arr_f = this.items;
        int v = this.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(arr_f[v1] == f) {
                return v1;
            }
        }
        return -1;
    }

    public void insert(int v, float f) {
        int v1 = this.size;
        if(v > v1) {
            throw new IndexOutOfBoundsException("index can\'t be > size: " + v + " > " + this.size);
        }
        float[] arr_f = v1 == this.items.length ? this.resize(Math.max(8, ((int)(((float)v1) * 1.75f)))) : this.items;
        if(this.ordered) {
            System.arraycopy(arr_f, v, arr_f, v + 1, this.size - v);
        }
        else {
            arr_f[this.size] = arr_f[v];
        }
        ++this.size;
        arr_f[v] = f;
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

    public int lastIndexOf(float f) {
        float[] arr_f = this.items;
        for(int v = this.size - 1; v >= 0; --v) {
            if(arr_f[v] == f) {
                return v;
            }
        }
        return -1;
    }

    public void mul(float f) {
        float[] arr_f = this.items;
        int v = this.size;
        for(int v1 = 0; v1 < v; ++v1) {
            arr_f[v1] *= f;
        }
    }

    public void mul(int v, float f) {
        if(v >= this.size) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        this.items[v] *= f;
    }

    public boolean notEmpty() {
        return this.size > 0;
    }

    public float peek() {
        return this.items[this.size - 1];
    }

    public float pop() {
        int v = this.size - 1;
        this.size = v;
        return this.items[v];
    }

    public float random() {
        int v = this.size;
        if(v == 0) {
            return 0.0f;
        }
        float[] arr_f = this.items;
        return arr_f[MathUtils.random(0, v - 1)];
    }

    public boolean removeAll(FloatArray floatArray0) {
        int v = this.size;
        float[] arr_f = this.items;
        int v1 = floatArray0.size;
        int v2 = v;
        for(int v3 = 0; v3 < v1; ++v3) {
            float f = floatArray0.get(v3);
            for(int v4 = 0; v4 < v2; ++v4) {
                if(f == arr_f[v4]) {
                    this.removeIndex(v4);
                    --v2;
                    break;
                }
            }
        }
        return v2 != v;
    }

    public float removeIndex(int v) {
        int v1 = this.size;
        if(v >= v1) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        float[] arr_f = this.items;
        float f = arr_f[v];
        this.size = v1 - 1;
        if(this.ordered) {
            System.arraycopy(arr_f, v + 1, arr_f, v, this.size - v);
            return f;
        }
        arr_f[v] = arr_f[this.size];
        return f;
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

    public boolean removeValue(float f) {
        float[] arr_f = this.items;
        int v = this.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(arr_f[v1] == f) {
                this.removeIndex(v1);
                return true;
            }
        }
        return false;
    }

    protected float[] resize(int v) {
        float[] arr_f = new float[v];
        System.arraycopy(this.items, 0, arr_f, 0, Math.min(this.size, arr_f.length));
        this.items = arr_f;
        return arr_f;
    }

    public void reverse() {
        float[] arr_f = this.items;
        int v = this.size - 1;
        int v1 = this.size / 2;
        for(int v2 = 0; v2 < v1; ++v2) {
            int v3 = v - v2;
            float f = arr_f[v2];
            arr_f[v2] = arr_f[v3];
            arr_f[v3] = f;
        }
    }

    public void set(int v, float f) {
        if(v >= this.size) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        this.items[v] = f;
    }

    public float[] setSize(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("newSize must be >= 0: " + v);
        }
        if(v > this.items.length) {
            this.resize(Math.max(8, v));
        }
        this.size = v;
        return this.items;
    }

    public float[] shrink() {
        int v = this.size;
        if(this.items.length != v) {
            this.resize(v);
        }
        return this.items;
    }

    public void shuffle() {
        float[] arr_f = this.items;
        for(int v = this.size - 1; v >= 0; --v) {
            int v1 = MathUtils.random(v);
            float f = arr_f[v];
            arr_f[v] = arr_f[v1];
            arr_f[v1] = f;
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
        float[] arr_f = this.items;
        float f = arr_f[v];
        arr_f[v] = arr_f[v1];
        arr_f[v1] = f;
    }

    public float[] toArray() {
        int v = this.size;
        float[] arr_f = new float[v];
        System.arraycopy(this.items, 0, arr_f, 0, v);
        return arr_f;
    }

    @Override
    public String toString() {
        if(this.size == 0) {
            return "[]";
        }
        float[] arr_f = this.items;
        StringBuilder stringBuilder0 = new StringBuilder(0x20);
        stringBuilder0.append('[');
        stringBuilder0.append(arr_f[0]);
        for(int v = 1; v < this.size; ++v) {
            stringBuilder0.append(", ");
            stringBuilder0.append(arr_f[v]);
        }
        stringBuilder0.append(']');
        return "";
    }

    public String toString(String s) {
        if(this.size == 0) {
            return "";
        }
        float[] arr_f = this.items;
        StringBuilder stringBuilder0 = new StringBuilder(0x20);
        stringBuilder0.append(arr_f[0]);
        for(int v = 1; v < this.size; ++v) {
            stringBuilder0.append(s);
            stringBuilder0.append(arr_f[v]);
        }
        return "";
    }

    public void truncate(int v) {
        if(this.size > v) {
            this.size = v;
        }
    }

    public static FloatArray with(float[] arr_f) {
        return new FloatArray(arr_f);
    }
}

