package androidx.collection;

public final class CircularIntArray {
    private int mCapacityBitmask;
    private int[] mElements;
    private int mHead;
    private int mTail;

    public CircularIntArray() {
        this(8);
    }

    public CircularIntArray(int v) {
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
        this.mElements = new int[v];
    }

    public void addFirst(int v) {
        this.mHead = this.mHead - 1 & this.mCapacityBitmask;
        int v1 = this.mHead;
        this.mElements[v1] = v;
        if(v1 == this.mTail) {
            this.doubleCapacity();
        }
    }

    public void addLast(int v) {
        int v1 = this.mTail;
        this.mElements[v1] = v;
        this.mTail = this.mCapacityBitmask & v1 + 1;
        if(this.mTail == this.mHead) {
            this.doubleCapacity();
        }
    }

    public void clear() {
        this.mTail = this.mHead;
    }

    private void doubleCapacity() {
        int[] arr_v = this.mElements;
        int v = this.mHead;
        int v1 = arr_v.length - v;
        int v2 = arr_v.length << 1;
        if(v2 < 0) {
            throw new RuntimeException("Max array capacity exceeded");
        }
        int[] arr_v1 = new int[v2];
        System.arraycopy(arr_v, v, arr_v1, 0, v1);
        System.arraycopy(this.mElements, 0, arr_v1, v1, this.mHead);
        this.mElements = arr_v1;
        this.mHead = 0;
        this.mTail = arr_v.length;
        this.mCapacityBitmask = v2 - 1;
    }

    public int get(int v) {
        if(v < 0 || v >= this.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.mElements[this.mCapacityBitmask & this.mHead + v];
    }

    public int getFirst() {
        int v = this.mHead;
        if(v == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.mElements[v];
    }

    public int getLast() {
        int v = this.mTail;
        if(this.mHead == v) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.mElements[v - 1 & this.mCapacityBitmask];
    }

    public boolean isEmpty() {
        return this.mHead == this.mTail;
    }

    public int popFirst() {
        int v = this.mHead;
        if(v == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.mHead = v + 1 & this.mCapacityBitmask;
        return this.mElements[v];
    }

    public int popLast() {
        int v = this.mTail;
        if(this.mHead == v) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int v1 = this.mCapacityBitmask & v - 1;
        this.mTail = v1;
        return this.mElements[v1];
    }

    public void removeFromEnd(int v) {
        if(v <= 0) {
            return;
        }
        if(v > this.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.mTail = this.mCapacityBitmask & this.mTail - v;
    }

    public void removeFromStart(int v) {
        if(v <= 0) {
            return;
        }
        if(v > this.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.mHead = this.mCapacityBitmask & this.mHead + v;
    }

    public int size() {
        return this.mTail - this.mHead & this.mCapacityBitmask;
    }
}

