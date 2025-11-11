package com.badlogic.gdx.utils;

public abstract class Pool {
    public interface Poolable {
        void reset();
    }

    private final Array freeObjects;
    public final int max;
    public int peak;

    public Pool() {
        this(16, 0x7FFFFFFF);
    }

    public Pool(int v) {
        this(v, 0x7FFFFFFF);
    }

    public Pool(int v, int v1) {
        this.freeObjects = new Array(false, v);
        this.max = v1;
    }

    public void clear() {
        for(int v = 0; v < this.freeObjects.size; ++v) {
            this.discard(this.freeObjects.pop());
        }
    }

    protected void discard(Object object0) {
    }

    public void fill(int v) {
        for(int v1 = 0; v1 < v; ++v1) {
            if(this.freeObjects.size < this.max) {
                Object object0 = this.newObject();
                this.freeObjects.add(object0);
            }
        }
        this.peak = Math.max(this.peak, this.freeObjects.size);
    }

    public void free(Object object0) {
        if(object0 == null) {
            throw new IllegalArgumentException("object cannot be null.");
        }
        if(this.freeObjects.size < this.max) {
            this.freeObjects.add(object0);
            this.peak = Math.max(this.peak, this.freeObjects.size);
            this.reset(object0);
        }
    }

    public void freeAll(Array array0) {
        if(array0 == null) {
            throw new IllegalArgumentException("objects cannot be null.");
        }
        Array array1 = this.freeObjects;
        int v = this.max;
        int v2 = array0.size;
        for(int v1 = 0; v1 < v2; ++v1) {
            Object object0 = array0.get(v1);
            if(object0 != null && array1.size < v) {
                array1.add(object0);
                this.reset(object0);
            }
        }
        this.peak = Math.max(this.peak, array1.size);
    }

    public int getFree() {
        return this.freeObjects.size;
    }

    protected abstract Object newObject();

    public Object obtain() {
        return this.freeObjects.size == 0 ? this.newObject() : this.freeObjects.pop();
    }

    protected void reset(Object object0) {
        if(object0 instanceof Poolable) {
            ((Poolable)object0).reset();
        }
    }
}

