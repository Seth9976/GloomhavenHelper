package com.badlogic.gdx.utils;

import java.util.Comparator;

public class SnapshotArray extends Array {
    private Object[] recycled;
    private Object[] snapshot;
    private int snapshots;

    public SnapshotArray() {
    }

    public SnapshotArray(int v) {
        super(v);
    }

    public SnapshotArray(Array array0) {
        super(array0);
    }

    public SnapshotArray(Class class0) {
        super(class0);
    }

    public SnapshotArray(boolean z, int v) {
        super(z, v);
    }

    public SnapshotArray(boolean z, int v, Class class0) {
        super(z, v, class0);
    }

    public SnapshotArray(boolean z, Object[] arr_object, int v, int v1) {
        super(z, arr_object, v, v1);
    }

    public SnapshotArray(Object[] arr_object) {
        super(arr_object);
    }

    public Object[] begin() {
        this.modified();
        this.snapshot = this.items;
        ++this.snapshots;
        return this.items;
    }

    @Override  // com.badlogic.gdx.utils.Array
    public void clear() {
        this.modified();
        super.clear();
    }

    public void end() {
        this.snapshots = Math.max(0, this.snapshots - 1);
        Object[] arr_object = this.snapshot;
        if(arr_object == null) {
            return;
        }
        if(arr_object != this.items && this.snapshots == 0) {
            this.recycled = this.snapshot;
            for(int v = 0; v < this.recycled.length; ++v) {
                this.recycled[v] = null;
            }
        }
        this.snapshot = null;
    }

    @Override  // com.badlogic.gdx.utils.Array
    public void insert(int v, Object object0) {
        this.modified();
        super.insert(v, object0);
    }

    @Override  // com.badlogic.gdx.utils.Array
    public void insertRange(int v, int v1) {
        this.modified();
        super.insertRange(v, v1);
    }

    private void modified() {
        if(this.snapshot != null && this.snapshot == this.items) {
            if(this.recycled != null && this.recycled.length >= this.size) {
                System.arraycopy(this.items, 0, this.recycled, 0, this.size);
                this.items = this.recycled;
                this.recycled = null;
                return;
            }
            this.resize(this.items.length);
        }
    }

    @Override  // com.badlogic.gdx.utils.Array
    public Object pop() {
        this.modified();
        return super.pop();
    }

    @Override  // com.badlogic.gdx.utils.Array
    public boolean removeAll(Array array0, boolean z) {
        this.modified();
        return super.removeAll(array0, z);
    }

    @Override  // com.badlogic.gdx.utils.Array
    public Object removeIndex(int v) {
        this.modified();
        return super.removeIndex(v);
    }

    @Override  // com.badlogic.gdx.utils.Array
    public void removeRange(int v, int v1) {
        this.modified();
        super.removeRange(v, v1);
    }

    @Override  // com.badlogic.gdx.utils.Array
    public boolean removeValue(Object object0, boolean z) {
        this.modified();
        return super.removeValue(object0, z);
    }

    @Override  // com.badlogic.gdx.utils.Array
    public void reverse() {
        this.modified();
        super.reverse();
    }

    @Override  // com.badlogic.gdx.utils.Array
    public void set(int v, Object object0) {
        this.modified();
        super.set(v, object0);
    }

    @Override  // com.badlogic.gdx.utils.Array
    public Object[] setSize(int v) {
        this.modified();
        return super.setSize(v);
    }

    @Override  // com.badlogic.gdx.utils.Array
    public void shuffle() {
        this.modified();
        super.shuffle();
    }

    @Override  // com.badlogic.gdx.utils.Array
    public void sort() {
        this.modified();
        super.sort();
    }

    @Override  // com.badlogic.gdx.utils.Array
    public void sort(Comparator comparator0) {
        this.modified();
        super.sort(comparator0);
    }

    @Override  // com.badlogic.gdx.utils.Array
    public void swap(int v, int v1) {
        this.modified();
        super.swap(v, v1);
    }

    @Override  // com.badlogic.gdx.utils.Array
    public void truncate(int v) {
        this.modified();
        super.truncate(v);
    }

    public static SnapshotArray with(Object[] arr_object) {
        return new SnapshotArray(arr_object);
    }
}

