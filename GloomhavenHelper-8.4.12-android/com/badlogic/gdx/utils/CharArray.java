package com.badlogic.gdx.utils;

import com.badlogic.gdx.math.MathUtils;
import java.util.Arrays;

public class CharArray {
    public char[] items;
    public boolean ordered;
    public int size;

    public CharArray() {
        this(true, 16);
    }

    public CharArray(int v) {
        this(true, v);
    }

    public CharArray(CharArray charArray0) {
        this.ordered = charArray0.ordered;
        this.size = charArray0.size;
        int v = this.size;
        this.items = new char[v];
        System.arraycopy(charArray0.items, 0, this.items, 0, v);
    }

    public CharArray(boolean z, int v) {
        this.ordered = z;
        this.items = new char[v];
    }

    public CharArray(boolean z, char[] arr_c, int v, int v1) {
        this(z, v1);
        this.size = v1;
        System.arraycopy(arr_c, v, this.items, 0, v1);
    }

    public CharArray(char[] arr_c) {
        this(true, arr_c, 0, arr_c.length);
    }

    public void add(char c) {
        char[] arr_c = this.items;
        int v = this.size;
        if(v == arr_c.length) {
            arr_c = this.resize(Math.max(8, ((int)(((float)v) * 1.75f))));
        }
        int v1 = this.size;
        this.size = v1 + 1;
        arr_c[v1] = c;
    }

    public void add(char c, char c1) {
        char[] arr_c = this.items;
        int v = this.size;
        if(v + 1 >= arr_c.length) {
            arr_c = this.resize(Math.max(8, ((int)(((float)v) * 1.75f))));
        }
        int v1 = this.size;
        arr_c[v1] = c;
        arr_c[v1 + 1] = c1;
        this.size = v1 + 2;
    }

    public void add(char c, char c1, char c2) {
        char[] arr_c = this.items;
        int v = this.size;
        if(v + 2 >= arr_c.length) {
            arr_c = this.resize(Math.max(8, ((int)(((float)v) * 1.75f))));
        }
        int v1 = this.size;
        arr_c[v1] = c;
        arr_c[v1 + 1] = c1;
        arr_c[v1 + 2] = c2;
        this.size = v1 + 3;
    }

    public void add(char c, char c1, char c2, char c3) {
        char[] arr_c = this.items;
        int v = this.size;
        if(v + 3 >= arr_c.length) {
            arr_c = this.resize(Math.max(8, ((int)(((float)v) * 1.8f))));
        }
        int v1 = this.size;
        arr_c[v1] = c;
        arr_c[v1 + 1] = c1;
        arr_c[v1 + 2] = c2;
        arr_c[v1 + 3] = c3;
        this.size = v1 + 4;
    }

    public void addAll(CharArray charArray0) {
        this.addAll(charArray0.items, 0, charArray0.size);
    }

    public void addAll(CharArray charArray0, int v, int v1) {
        if(v + v1 > charArray0.size) {
            throw new IllegalArgumentException("offset + length must be <= size: " + v + " + " + v1 + " <= " + charArray0.size);
        }
        this.addAll(charArray0.items, v, v1);
    }

    public void addAll(char[] arr_c) {
        this.addAll(arr_c, 0, arr_c.length);
    }

    public void addAll(char[] arr_c, int v, int v1) {
        char[] arr_c1 = this.items;
        int v2 = this.size + v1;
        if(v2 > arr_c1.length) {
            arr_c1 = this.resize(Math.max(Math.max(8, v2), ((int)(((float)this.size) * 1.75f))));
        }
        System.arraycopy(arr_c, v, arr_c1, this.size, v1);
        this.size += v1;
    }

    public void clear() {
        this.size = 0;
    }

    public boolean contains(char c) {
        int v = this.size - 1;
        char[] arr_c = this.items;
        while(v >= 0) {
            if(arr_c[v] == c) {
                return true;
            }
            --v;
        }
        return false;
    }

    public char[] ensureCapacity(int v) {
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
        if(!(object0 instanceof CharArray)) {
            return false;
        }
        if(!((CharArray)object0).ordered) {
            return false;
        }
        int v = this.size;
        if(v != ((CharArray)object0).size) {
            return false;
        }
        char[] arr_c = this.items;
        char[] arr_c1 = ((CharArray)object0).items;
        for(int v1 = 0; v1 < v; ++v1) {
            if(arr_c[v1] != arr_c1[v1]) {
                return false;
            }
        }
        return true;
    }

    public char first() {
        if(this.size == 0) {
            throw new IllegalStateException("Array is empty.");
        }
        return this.items[0];
    }

    public char get(int v) {
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
        char[] arr_c = this.items;
        int v1 = this.size;
        int v2 = 1;
        for(int v = 0; v < v1; ++v) {
            v2 = v2 * 0x1F + arr_c[v];
        }
        return v2;
    }

    public void incr(char c) {
        char[] arr_c = this.items;
        int v = this.size;
        for(int v1 = 0; v1 < v; ++v1) {
            arr_c[v1] = (char)(arr_c[v1] + c);
        }
    }

    public void incr(int v, char c) {
        if(v >= this.size) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        this.items[v] = (char)(this.items[v] + c);
    }

    public int indexOf(char c) {
        char[] arr_c = this.items;
        int v = this.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(arr_c[v1] == c) {
                return v1;
            }
        }
        return -1;
    }

    public void insert(int v, char c) {
        int v1 = this.size;
        if(v > v1) {
            throw new IndexOutOfBoundsException("index can\'t be > size: " + v + " > " + this.size);
        }
        char[] arr_c = v1 == this.items.length ? this.resize(Math.max(8, ((int)(((float)v1) * 1.75f)))) : this.items;
        if(this.ordered) {
            System.arraycopy(arr_c, v, arr_c, v + 1, this.size - v);
        }
        else {
            arr_c[this.size] = arr_c[v];
        }
        ++this.size;
        arr_c[v] = c;
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
        char[] arr_c = this.items;
        for(int v = this.size - 1; v >= 0; --v) {
            if(arr_c[v] == c) {
                return v;
            }
        }
        return -1;
    }

    public void mul(char c) {
        char[] arr_c = this.items;
        int v = this.size;
        for(int v1 = 0; v1 < v; ++v1) {
            arr_c[v1] = (char)(arr_c[v1] * c);
        }
    }

    public void mul(int v, char c) {
        if(v >= this.size) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        this.items[v] = (char)(this.items[v] * c);
    }

    public boolean notEmpty() {
        return this.size > 0;
    }

    public char peek() {
        return this.items[this.size - 1];
    }

    public char pop() {
        int v = this.size - 1;
        this.size = v;
        return this.items[v];
    }

    public char random() {
        int v = this.size;
        if(v == 0) {
            return '\u0000';
        }
        char[] arr_c = this.items;
        return arr_c[MathUtils.random(0, v - 1)];
    }

    public boolean removeAll(CharArray charArray0) {
        int v = this.size;
        char[] arr_c = this.items;
        int v1 = charArray0.size;
        int v2 = v;
        for(int v3 = 0; v3 < v1; ++v3) {
            int v4 = charArray0.get(v3);
            for(int v5 = 0; v5 < v2; ++v5) {
                if(v4 == arr_c[v5]) {
                    this.removeIndex(v5);
                    --v2;
                    break;
                }
            }
        }
        return v2 != v;
    }

    public char removeIndex(int v) {
        int v1 = this.size;
        if(v >= v1) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        char[] arr_c = this.items;
        char c = arr_c[v];
        this.size = v1 - 1;
        if(this.ordered) {
            System.arraycopy(arr_c, v + 1, arr_c, v, this.size - v);
            return c;
        }
        arr_c[v] = arr_c[this.size];
        return c;
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

    public boolean removeValue(char c) {
        char[] arr_c = this.items;
        int v = this.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(arr_c[v1] == c) {
                this.removeIndex(v1);
                return true;
            }
        }
        return false;
    }

    protected char[] resize(int v) {
        char[] arr_c = new char[v];
        System.arraycopy(this.items, 0, arr_c, 0, Math.min(this.size, arr_c.length));
        this.items = arr_c;
        return arr_c;
    }

    public void reverse() {
        char[] arr_c = this.items;
        int v = this.size - 1;
        int v1 = this.size / 2;
        for(int v2 = 0; v2 < v1; ++v2) {
            int v3 = v - v2;
            char c = arr_c[v2];
            arr_c[v2] = arr_c[v3];
            arr_c[v3] = c;
        }
    }

    public void set(int v, char c) {
        if(v >= this.size) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        this.items[v] = c;
    }

    public char[] setSize(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("newSize must be >= 0: " + v);
        }
        if(v > this.items.length) {
            this.resize(Math.max(8, v));
        }
        this.size = v;
        return this.items;
    }

    public char[] shrink() {
        int v = this.size;
        if(this.items.length != v) {
            this.resize(v);
        }
        return this.items;
    }

    public void shuffle() {
        char[] arr_c = this.items;
        for(int v = this.size - 1; v >= 0; --v) {
            int v1 = MathUtils.random(v);
            char c = arr_c[v];
            arr_c[v] = arr_c[v1];
            arr_c[v1] = c;
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
        char[] arr_c = this.items;
        char c = arr_c[v];
        arr_c[v] = arr_c[v1];
        arr_c[v1] = c;
    }

    public char[] toArray() {
        int v = this.size;
        char[] arr_c = new char[v];
        System.arraycopy(this.items, 0, arr_c, 0, v);
        return arr_c;
    }

    @Override
    public String toString() {
        if(this.size == 0) {
            return "[]";
        }
        char[] arr_c = this.items;
        StringBuilder stringBuilder0 = new StringBuilder(0x20);
        stringBuilder0.append('[');
        stringBuilder0.append(arr_c[0]);
        for(int v = 1; v < this.size; ++v) {
            stringBuilder0.append(", ");
            stringBuilder0.append(arr_c[v]);
        }
        stringBuilder0.append(']');
        return "";
    }

    public String toString(String s) {
        if(this.size == 0) {
            return "";
        }
        char[] arr_c = this.items;
        StringBuilder stringBuilder0 = new StringBuilder(0x20);
        stringBuilder0.append(arr_c[0]);
        for(int v = 1; v < this.size; ++v) {
            stringBuilder0.append(s);
            stringBuilder0.append(arr_c[v]);
        }
        return "";
    }

    public void truncate(int v) {
        if(this.size > v) {
            this.size = v;
        }
    }

    public static CharArray with(char[] arr_c) {
        return new CharArray(arr_c);
    }
}

