package androidx.collection;

public final class CircularArray {
    private int mCapacityBitmask;
    private Object[] mElements;
    private int mHead;
    private int mTail;

    public CircularArray() {
        this(8);
    }

    public CircularArray(int v) {
        if(v < 1) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }
        if(v > 0x40000000) {
            throw new IllegalArgumentException("capacity must be <= 2^30");
        }
        if(Integer.bitCount(v) != 1) {
            v = Integer.highestOneBit(v - 1) << 1;
        }
        this.mCapacityBitmask = v - 1;
        this.mElements = new Object[v];
    }

    public void addFirst(Object object0) {
        this.mHead = this.mHead - 1 & this.mCapacityBitmask;
        int v = this.mHead;
        this.mElements[v] = object0;
        if(v == this.mTail) {
            this.doubleCapacity();
        }
    }

    public void addLast(Object object0) {
        int v = this.mTail;
        this.mElements[v] = object0;
        this.mTail = this.mCapacityBitmask & v + 1;
        if(this.mTail == this.mHead) {
            this.doubleCapacity();
        }
    }

    public void clear() {
        this.removeFromStart(this.size());
    }

    private void doubleCapacity() {
        Object[] arr_object = this.mElements;
        int v = this.mHead;
        int v1 = arr_object.length - v;
        int v2 = arr_object.length << 1;
        if(v2 < 0) {
            throw new RuntimeException("Max array capacity exceeded");
        }
        Object[] arr_object1 = new Object[v2];
        System.arraycopy(arr_object, v, arr_object1, 0, v1);
        System.arraycopy(this.mElements, 0, arr_object1, v1, this.mHead);
        this.mElements = arr_object1;
        this.mHead = 0;
        this.mTail = arr_object.length;
        this.mCapacityBitmask = v2 - 1;
    }

    public Object get(int v) {
        if(v < 0 || v >= this.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.mElements[this.mCapacityBitmask & this.mHead + v];
    }

    public Object getFirst() {
        int v = this.mHead;
        if(v == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.mElements[v];
    }

    public Object getLast() {
        int v = this.mTail;
        if(this.mHead == v) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.mElements[v - 1 & this.mCapacityBitmask];
    }

    public boolean isEmpty() {
        return this.mHead == this.mTail;
    }

    public Object popFirst() {
        int v = this.mHead;
        if(v == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Object[] arr_object = this.mElements;
        Object object0 = arr_object[v];
        arr_object[v] = null;
        this.mHead = v + 1 & this.mCapacityBitmask;
        return object0;
    }

    public Object popLast() {
        int v = this.mTail;
        if(this.mHead == v) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int v1 = this.mCapacityBitmask & v - 1;
        Object[] arr_object = this.mElements;
        Object object0 = arr_object[v1];
        arr_object[v1] = null;
        this.mTail = v1;
        return object0;
    }

    public void removeFromEnd(int v) {
        int v4;
        if(v <= 0) {
            return;
        }
        if(v > this.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int v1 = 0;
        int v2 = this.mTail;
        if(v < v2) {
            v1 = v2 - v;
        }
        for(int v3 = v1; true; ++v3) {
            v4 = this.mTail;
            if(v3 >= v4) {
                break;
            }
            this.mElements[v3] = null;
        }
        int v5 = v4 - v1;
        int v6 = v - v5;
        this.mTail = v4 - v5;
        if(v6 > 0) {
            this.mTail = this.mElements.length;
            int v7 = this.mTail - v6;
            for(int v8 = v7; v8 < this.mTail; ++v8) {
                this.mElements[v8] = null;
            }
            this.mTail = v7;
        }
    }

    public void removeFromStart(int v) {
        if(v <= 0) {
            return;
        }
        if(v > this.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int v1 = this.mElements.length;
        int v2 = this.mHead;
        if(v < v1 - v2) {
            v1 = v2 + v;
        }
        for(int v3 = this.mHead; v3 < v1; ++v3) {
            this.mElements[v3] = null;
        }
        int v4 = v1 - this.mHead;
        int v5 = v - v4;
        this.mHead = this.mCapacityBitmask & this.mHead + v4;
        if(v5 > 0) {
            for(int v6 = 0; v6 < v5; ++v6) {
                this.mElements[v6] = null;
            }
            this.mHead = v5;
        }
    }

    public int size() {
        return this.mTail - this.mHead & this.mCapacityBitmask;
    }
}

