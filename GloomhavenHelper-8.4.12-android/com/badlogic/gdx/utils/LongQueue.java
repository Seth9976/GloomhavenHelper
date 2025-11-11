package com.badlogic.gdx.utils;

import java.util.NoSuchElementException;

public class LongQueue {
    protected int head;
    public int size;
    protected int tail;
    protected long[] values;

    public LongQueue() {
        this(16);
    }

    public LongQueue(int v) {
        this.head = 0;
        this.tail = 0;
        this.size = 0;
        this.values = new long[v];
    }

    public void addFirst(long v) {
        long[] arr_v = this.values;
        if(this.size == arr_v.length) {
            this.resize(arr_v.length << 1);
            arr_v = this.values;
        }
        int v1 = this.head - 1 == -1 ? arr_v.length - 1 : this.head - 1;
        arr_v[v1] = v;
        this.head = v1;
        ++this.size;
    }

    public void addLast(long v) {
        long[] arr_v = this.values;
        if(this.size == arr_v.length) {
            this.resize(arr_v.length << 1);
            arr_v = this.values;
        }
        int v1 = this.tail;
        this.tail = v1 + 1;
        arr_v[v1] = v;
        if(this.tail == arr_v.length) {
            this.tail = 0;
        }
        ++this.size;
    }

    public void clear() {
        if(this.size == 0) {
            return;
        }
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    public void ensureCapacity(int v) {
        int v1 = this.size + v;
        if(this.values.length < v1) {
            this.resize(v1);
        }
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(object0 != null && object0 instanceof LongQueue) {
            int v = this.size;
            if(((LongQueue)object0).size != v) {
                return false;
            }
            long[] arr_v = this.values;
            long[] arr_v1 = ((LongQueue)object0).values;
            int v1 = this.head;
            int v2 = ((LongQueue)object0).head;
            for(int v3 = 0; v3 < v; ++v3) {
                if(arr_v[v1] != arr_v1[v2]) {
                    return false;
                }
                ++v1;
                ++v2;
                if(v1 == arr_v.length) {
                    v1 = 0;
                }
                if(v2 == arr_v1.length) {
                    v2 = 0;
                }
            }
            return true;
        }
        return false;
    }

    public long first() {
        if(this.size == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return this.values[this.head];
    }

    public long get(int v) {
        if(v < 0) {
            throw new IndexOutOfBoundsException("index can\'t be < 0: " + v);
        }
        if(v >= this.size) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        int v1 = this.head + v;
        return this.values[(v1 < this.values.length ? this.head + v : v1 - this.values.length)];
    }

    @Override
    public int hashCode() {
        int v = this.size;
        long[] arr_v = this.values;
        int v1 = v + 1;
        int v2 = this.head;
        for(int v3 = 0; v3 < v; ++v3) {
            long v4 = arr_v[v2];
            v1 += ((int)(v4 ^ v4 >>> 0x20)) * 0x1F;
            ++v2;
            v2 = v2 == arr_v.length ? 0 : v2 + 1;
        }
        return v1;
    }

    public int indexOf(long v) {
        if(this.size == 0) {
            return -1;
        }
        long[] arr_v = this.values;
        int v1 = this.head;
        int v2 = this.tail;
        if(v1 < v2) {
            for(int v3 = v1; v3 < v2; ++v3) {
                if(arr_v[v3] == v) {
                    return v3 - v1;
                }
            }
            return -1;
        }
        for(int v4 = v1; v4 < arr_v.length; ++v4) {
            if(arr_v[v4] == v) {
                return v4 - v1;
            }
        }
        for(int v5 = 0; v5 < v2; ++v5) {
            if(arr_v[v5] == v) {
                return v5 + arr_v.length - v1;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public long last() {
        if(this.size == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return this.values[(this.tail - 1 == -1 ? this.values.length - 1 : this.tail - 1)];
    }

    public boolean notEmpty() {
        return this.size > 0;
    }

    public long removeFirst() {
        if(this.size == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }
        long[] arr_v = this.values;
        int v = this.head;
        long v1 = arr_v[v];
        this.head = v + 1;
        if(this.head == arr_v.length) {
            this.head = 0;
        }
        --this.size;
        return v1;
    }

    public long removeIndex(int v) {
        long v4;
        if(v < 0) {
            throw new IndexOutOfBoundsException("index can\'t be < 0: " + v);
        }
        if(v >= this.size) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        long[] arr_v = this.values;
        int v1 = this.head;
        int v2 = this.tail;
        int v3 = v + v1;
        if(v1 < v2) {
            v4 = arr_v[v3];
            System.arraycopy(arr_v, v3 + 1, arr_v, v3, v2 - v3);
            --this.tail;
        }
        else if(v3 >= arr_v.length) {
            int v5 = v3 - arr_v.length;
            v4 = arr_v[v5];
            System.arraycopy(arr_v, v5 + 1, arr_v, v5, v2 - v5);
            --this.tail;
        }
        else {
            v4 = arr_v[v3];
            System.arraycopy(arr_v, v1, arr_v, v1 + 1, v3 - v1);
            ++this.head;
            if(this.head == arr_v.length) {
                this.head = 0;
            }
        }
        --this.size;
        return v4;
    }

    public long removeLast() {
        if(this.size == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }
        int v = this.tail - 1 == -1 ? this.values.length - 1 : this.tail - 1;
        this.tail = v;
        --this.size;
        return this.values[v];
    }

    public boolean removeValue(long v) {
        int v1 = this.indexOf(v);
        if(v1 == -1) {
            return false;
        }
        this.removeIndex(v1);
        return true;
    }

    protected void resize(int v) {
        long[] arr_v = this.values;
        int v1 = this.head;
        int v2 = this.tail;
        long[] arr_v1 = new long[v];
        if(v1 < v2) {
            System.arraycopy(arr_v, v1, arr_v1, 0, v2 - v1);
        }
        else if(this.size > 0) {
            int v3 = arr_v.length - v1;
            System.arraycopy(arr_v, v1, arr_v1, 0, v3);
            System.arraycopy(arr_v, 0, arr_v1, v3, v2);
        }
        this.values = arr_v1;
        this.head = 0;
        this.tail = this.size;
    }

    @Override
    public String toString() {
        if(this.size == 0) {
            return "[]";
        }
        long[] arr_v = this.values;
        int v = this.head;
        int v1 = this.tail;
        StringBuilder stringBuilder0 = new StringBuilder(0x40);
        stringBuilder0.append('[');
        stringBuilder0.append(arr_v[v]);
        while(true) {
            v = (v + 1) % arr_v.length;
            if(v == v1) {
                break;
            }
            stringBuilder0.append(", ").append(arr_v[v]);
        }
        stringBuilder0.append(']');
        return "";
    }

    public String toString(String s) {
        if(this.size == 0) {
            return "";
        }
        long[] arr_v = this.values;
        int v = this.head;
        int v1 = this.tail;
        StringBuilder stringBuilder0 = new StringBuilder(0x40);
        stringBuilder0.append(arr_v[v]);
        while(true) {
            v = (v + 1) % arr_v.length;
            if(v == v1) {
                break;
            }
            stringBuilder0.append(s).append(arr_v[v]);
        }
        return "";
    }
}

