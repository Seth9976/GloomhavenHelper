package com.badlogic.gdx.utils;

import com.badlogic.gdx.math.MathUtils;
import java.util.Arrays;

public class ByteArray {
    public byte[] items;
    public boolean ordered;
    public int size;

    public ByteArray() {
        this(true, 16);
    }

    public ByteArray(int v) {
        this(true, v);
    }

    public ByteArray(ByteArray byteArray0) {
        this.ordered = byteArray0.ordered;
        this.size = byteArray0.size;
        int v = this.size;
        this.items = new byte[v];
        System.arraycopy(byteArray0.items, 0, this.items, 0, v);
    }

    public ByteArray(boolean z, int v) {
        this.ordered = z;
        this.items = new byte[v];
    }

    public ByteArray(boolean z, byte[] arr_b, int v, int v1) {
        this(z, v1);
        this.size = v1;
        System.arraycopy(arr_b, v, this.items, 0, v1);
    }

    public ByteArray(byte[] arr_b) {
        this(true, arr_b, 0, arr_b.length);
    }

    public void add(byte b) {
        byte[] arr_b = this.items;
        int v = this.size;
        if(v == arr_b.length) {
            arr_b = this.resize(Math.max(8, ((int)(((float)v) * 1.75f))));
        }
        int v1 = this.size;
        this.size = v1 + 1;
        arr_b[v1] = b;
    }

    public void add(byte b, byte b1) {
        byte[] arr_b = this.items;
        int v = this.size;
        if(v + 1 >= arr_b.length) {
            arr_b = this.resize(Math.max(8, ((int)(((float)v) * 1.75f))));
        }
        int v1 = this.size;
        arr_b[v1] = b;
        arr_b[v1 + 1] = b1;
        this.size = v1 + 2;
    }

    public void add(byte b, byte b1, byte b2) {
        byte[] arr_b = this.items;
        int v = this.size;
        if(v + 2 >= arr_b.length) {
            arr_b = this.resize(Math.max(8, ((int)(((float)v) * 1.75f))));
        }
        int v1 = this.size;
        arr_b[v1] = b;
        arr_b[v1 + 1] = b1;
        arr_b[v1 + 2] = b2;
        this.size = v1 + 3;
    }

    public void add(byte b, byte b1, byte b2, byte b3) {
        byte[] arr_b = this.items;
        int v = this.size;
        if(v + 3 >= arr_b.length) {
            arr_b = this.resize(Math.max(8, ((int)(((float)v) * 1.8f))));
        }
        int v1 = this.size;
        arr_b[v1] = b;
        arr_b[v1 + 1] = b1;
        arr_b[v1 + 2] = b2;
        arr_b[v1 + 3] = b3;
        this.size = v1 + 4;
    }

    public void addAll(ByteArray byteArray0) {
        this.addAll(byteArray0.items, 0, byteArray0.size);
    }

    public void addAll(ByteArray byteArray0, int v, int v1) {
        if(v + v1 > byteArray0.size) {
            throw new IllegalArgumentException("offset + length must be <= size: " + v + " + " + v1 + " <= " + byteArray0.size);
        }
        this.addAll(byteArray0.items, v, v1);
    }

    public void addAll(byte[] arr_b) {
        this.addAll(arr_b, 0, arr_b.length);
    }

    public void addAll(byte[] arr_b, int v, int v1) {
        byte[] arr_b1 = this.items;
        int v2 = this.size + v1;
        if(v2 > arr_b1.length) {
            arr_b1 = this.resize(Math.max(Math.max(8, v2), ((int)(((float)this.size) * 1.75f))));
        }
        System.arraycopy(arr_b, v, arr_b1, this.size, v1);
        this.size += v1;
    }

    public void clear() {
        this.size = 0;
    }

    public boolean contains(byte b) {
        int v = this.size - 1;
        byte[] arr_b = this.items;
        while(v >= 0) {
            if(arr_b[v] == b) {
                return true;
            }
            --v;
        }
        return false;
    }

    public byte[] ensureCapacity(int v) {
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
        if(!(object0 instanceof ByteArray)) {
            return false;
        }
        if(!((ByteArray)object0).ordered) {
            return false;
        }
        int v = this.size;
        if(v != ((ByteArray)object0).size) {
            return false;
        }
        byte[] arr_b = this.items;
        byte[] arr_b1 = ((ByteArray)object0).items;
        for(int v1 = 0; v1 < v; ++v1) {
            if(arr_b[v1] != arr_b1[v1]) {
                return false;
            }
        }
        return true;
    }

    public byte first() {
        if(this.size == 0) {
            throw new IllegalStateException("Array is empty.");
        }
        return this.items[0];
    }

    public byte get(int v) {
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
        byte[] arr_b = this.items;
        int v1 = this.size;
        int v2 = 1;
        for(int v = 0; v < v1; ++v) {
            v2 = v2 * 0x1F + arr_b[v];
        }
        return v2;
    }

    public void incr(byte b) {
        byte[] arr_b = this.items;
        int v = this.size;
        for(int v1 = 0; v1 < v; ++v1) {
            arr_b[v1] = (byte)(arr_b[v1] + b);
        }
    }

    public void incr(int v, byte b) {
        if(v >= this.size) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        this.items[v] = (byte)(this.items[v] + b);
    }

    public int indexOf(byte b) {
        byte[] arr_b = this.items;
        int v = this.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(arr_b[v1] == b) {
                return v1;
            }
        }
        return -1;
    }

    public void insert(int v, byte b) {
        int v1 = this.size;
        if(v > v1) {
            throw new IndexOutOfBoundsException("index can\'t be > size: " + v + " > " + this.size);
        }
        byte[] arr_b = v1 == this.items.length ? this.resize(Math.max(8, ((int)(((float)v1) * 1.75f)))) : this.items;
        if(this.ordered) {
            System.arraycopy(arr_b, v, arr_b, v + 1, this.size - v);
        }
        else {
            arr_b[this.size] = arr_b[v];
        }
        ++this.size;
        arr_b[v] = b;
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

    public int lastIndexOf(byte b) {
        byte[] arr_b = this.items;
        for(int v = this.size - 1; v >= 0; --v) {
            if(arr_b[v] == b) {
                return v;
            }
        }
        return -1;
    }

    public void mul(byte b) {
        byte[] arr_b = this.items;
        int v = this.size;
        for(int v1 = 0; v1 < v; ++v1) {
            arr_b[v1] = (byte)(arr_b[v1] * b);
        }
    }

    public void mul(int v, byte b) {
        if(v >= this.size) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        this.items[v] = (byte)(this.items[v] * b);
    }

    public boolean notEmpty() {
        return this.size > 0;
    }

    public byte peek() {
        return this.items[this.size - 1];
    }

    public byte pop() {
        int v = this.size - 1;
        this.size = v;
        return this.items[v];
    }

    public byte random() {
        int v = this.size;
        if(v == 0) {
            return 0;
        }
        byte[] arr_b = this.items;
        return arr_b[MathUtils.random(0, v - 1)];
    }

    public boolean removeAll(ByteArray byteArray0) {
        int v = this.size;
        byte[] arr_b = this.items;
        int v1 = byteArray0.size;
        int v2 = v;
        for(int v3 = 0; v3 < v1; ++v3) {
            int v4 = byteArray0.get(v3);
            for(int v5 = 0; v5 < v2; ++v5) {
                if(v4 == arr_b[v5]) {
                    this.removeIndex(v5);
                    --v2;
                    break;
                }
            }
        }
        return v2 != v;
    }

    public int removeIndex(int v) {
        int v1 = this.size;
        if(v >= v1) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        byte[] arr_b = this.items;
        int v2 = arr_b[v];
        this.size = v1 - 1;
        if(this.ordered) {
            System.arraycopy(arr_b, v + 1, arr_b, v, this.size - v);
            return v2;
        }
        arr_b[v] = arr_b[this.size];
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

    public boolean removeValue(byte b) {
        byte[] arr_b = this.items;
        int v = this.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(arr_b[v1] == b) {
                this.removeIndex(v1);
                return true;
            }
        }
        return false;
    }

    protected byte[] resize(int v) {
        byte[] arr_b = new byte[v];
        System.arraycopy(this.items, 0, arr_b, 0, Math.min(this.size, arr_b.length));
        this.items = arr_b;
        return arr_b;
    }

    public void reverse() {
        byte[] arr_b = this.items;
        int v = this.size - 1;
        int v1 = this.size / 2;
        for(int v2 = 0; v2 < v1; ++v2) {
            int v3 = v - v2;
            byte b = arr_b[v2];
            arr_b[v2] = arr_b[v3];
            arr_b[v3] = b;
        }
    }

    public void set(int v, byte b) {
        if(v >= this.size) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        this.items[v] = b;
    }

    public byte[] setSize(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("newSize must be >= 0: " + v);
        }
        if(v > this.items.length) {
            this.resize(Math.max(8, v));
        }
        this.size = v;
        return this.items;
    }

    public byte[] shrink() {
        int v = this.size;
        if(this.items.length != v) {
            this.resize(v);
        }
        return this.items;
    }

    public void shuffle() {
        byte[] arr_b = this.items;
        for(int v = this.size - 1; v >= 0; --v) {
            int v1 = MathUtils.random(v);
            byte b = arr_b[v];
            arr_b[v] = arr_b[v1];
            arr_b[v1] = b;
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
        byte[] arr_b = this.items;
        byte b = arr_b[v];
        arr_b[v] = arr_b[v1];
        arr_b[v1] = b;
    }

    public byte[] toArray() {
        int v = this.size;
        byte[] arr_b = new byte[v];
        System.arraycopy(this.items, 0, arr_b, 0, v);
        return arr_b;
    }

    @Override
    public String toString() {
        if(this.size == 0) {
            return "[]";
        }
        byte[] arr_b = this.items;
        StringBuilder stringBuilder0 = new StringBuilder(0x20);
        stringBuilder0.append('[');
        stringBuilder0.append(((int)arr_b[0]));
        for(int v = 1; v < this.size; ++v) {
            stringBuilder0.append(", ");
            stringBuilder0.append(((int)arr_b[v]));
        }
        stringBuilder0.append(']');
        return "";
    }

    public String toString(String s) {
        if(this.size == 0) {
            return "";
        }
        byte[] arr_b = this.items;
        StringBuilder stringBuilder0 = new StringBuilder(0x20);
        stringBuilder0.append(((int)arr_b[0]));
        for(int v = 1; v < this.size; ++v) {
            stringBuilder0.append(s);
            stringBuilder0.append(((int)arr_b[v]));
        }
        return "";
    }

    public void truncate(int v) {
        if(this.size > v) {
            this.size = v;
        }
    }

    public static ByteArray with(byte[] arr_b) {
        return new ByteArray(arr_b);
    }
}

