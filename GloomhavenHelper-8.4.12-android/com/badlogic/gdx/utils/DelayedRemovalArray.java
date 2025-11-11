package com.badlogic.gdx.utils;

import java.util.Comparator;

public class DelayedRemovalArray extends Array {
    private int clear;
    private int iterating;
    private IntArray remove;

    public DelayedRemovalArray() {
        this.remove = new IntArray(0);
    }

    public DelayedRemovalArray(int v) {
        super(v);
        this.remove = new IntArray(0);
    }

    public DelayedRemovalArray(Array array0) {
        super(array0);
        this.remove = new IntArray(0);
    }

    public DelayedRemovalArray(Class class0) {
        super(class0);
        this.remove = new IntArray(0);
    }

    public DelayedRemovalArray(boolean z, int v) {
        super(z, v);
        this.remove = new IntArray(0);
    }

    public DelayedRemovalArray(boolean z, int v, Class class0) {
        super(z, v, class0);
        this.remove = new IntArray(0);
    }

    public DelayedRemovalArray(boolean z, Object[] arr_object, int v, int v1) {
        super(z, arr_object, v, v1);
        this.remove = new IntArray(0);
    }

    public DelayedRemovalArray(Object[] arr_object) {
        super(arr_object);
        this.remove = new IntArray(0);
    }

    public void begin() {
        ++this.iterating;
    }

    @Override  // com.badlogic.gdx.utils.Array
    public void clear() {
        if(this.iterating > 0) {
            this.clear = this.size;
            return;
        }
        super.clear();
    }

    public void end() {
        int v = this.iterating;
        if(v == 0) {
            throw new IllegalStateException("begin must be called before end.");
        }
        this.iterating = v - 1;
        if(this.iterating == 0) {
            if(this.clear <= 0 || this.clear != this.size) {
                int v1 = this.remove.size;
                for(int v2 = 0; v2 < v1; ++v2) {
                    int v3 = this.remove.pop();
                    if(v3 >= this.clear) {
                        this.removeIndex(v3);
                    }
                }
                for(int v4 = this.clear - 1; v4 >= 0; --v4) {
                    this.removeIndex(v4);
                }
            }
            else {
                this.remove.clear();
                this.clear();
            }
            this.clear = 0;
        }
    }

    @Override  // com.badlogic.gdx.utils.Array
    public void insert(int v, Object object0) {
        if(this.iterating > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        super.insert(v, object0);
    }

    @Override  // com.badlogic.gdx.utils.Array
    public void insertRange(int v, int v1) {
        if(this.iterating > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        super.insertRange(v, v1);
    }

    @Override  // com.badlogic.gdx.utils.Array
    public Object pop() {
        if(this.iterating > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        return super.pop();
    }

    private void remove(int v) {
        if(v < this.clear) {
            return;
        }
        int v2 = this.remove.size;
        for(int v1 = 0; v1 < v2; ++v1) {
            int v3 = this.remove.get(v1);
            if(v == v3) {
                return;
            }
            if(v < v3) {
                this.remove.insert(v1, v);
                return;
            }
        }
        this.remove.add(v);
    }

    @Override  // com.badlogic.gdx.utils.Array
    public Object removeIndex(int v) {
        if(this.iterating > 0) {
            this.remove(v);
            return this.get(v);
        }
        return super.removeIndex(v);
    }

    @Override  // com.badlogic.gdx.utils.Array
    public void removeRange(int v, int v1) {
        if(this.iterating > 0) {
            while(v1 >= v) {
                this.remove(v1);
                --v1;
            }
            return;
        }
        super.removeRange(v, v1);
    }

    @Override  // com.badlogic.gdx.utils.Array
    public boolean removeValue(Object object0, boolean z) {
        if(this.iterating > 0) {
            int v = this.indexOf(object0, z);
            if(v == -1) {
                return false;
            }
            this.remove(v);
            return true;
        }
        return super.removeValue(object0, z);
    }

    @Override  // com.badlogic.gdx.utils.Array
    public void reverse() {
        if(this.iterating > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        super.reverse();
    }

    @Override  // com.badlogic.gdx.utils.Array
    public void set(int v, Object object0) {
        if(this.iterating > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        super.set(v, object0);
    }

    @Override  // com.badlogic.gdx.utils.Array
    public Object[] setSize(int v) {
        if(this.iterating > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        return super.setSize(v);
    }

    @Override  // com.badlogic.gdx.utils.Array
    public void shuffle() {
        if(this.iterating > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        super.shuffle();
    }

    @Override  // com.badlogic.gdx.utils.Array
    public void sort() {
        if(this.iterating > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        super.sort();
    }

    @Override  // com.badlogic.gdx.utils.Array
    public void sort(Comparator comparator0) {
        if(this.iterating > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        super.sort(comparator0);
    }

    @Override  // com.badlogic.gdx.utils.Array
    public void swap(int v, int v1) {
        if(this.iterating > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        super.swap(v, v1);
    }

    @Override  // com.badlogic.gdx.utils.Array
    public void truncate(int v) {
        if(this.iterating > 0) {
            throw new IllegalStateException("Invalid between begin/end.");
        }
        super.truncate(v);
    }

    public static DelayedRemovalArray with(Object[] arr_object) {
        return new DelayedRemovalArray(arr_object);
    }
}

