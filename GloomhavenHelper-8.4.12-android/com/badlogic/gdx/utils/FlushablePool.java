package com.badlogic.gdx.utils;

public abstract class FlushablePool extends Pool {
    protected Array obtained;

    public FlushablePool() {
        this.obtained = new Array();
    }

    public FlushablePool(int v) {
        super(v);
        this.obtained = new Array();
    }

    public FlushablePool(int v, int v1) {
        super(v, v1);
        this.obtained = new Array();
    }

    public void flush() {
        super.freeAll(this.obtained);
        this.obtained.clear();
    }

    @Override  // com.badlogic.gdx.utils.Pool
    public void free(Object object0) {
        this.obtained.removeValue(object0, true);
        super.free(object0);
    }

    @Override  // com.badlogic.gdx.utils.Pool
    public void freeAll(Array array0) {
        this.obtained.removeAll(array0, true);
        super.freeAll(array0);
    }

    @Override  // com.badlogic.gdx.utils.Pool
    public Object obtain() {
        Object object0 = super.obtain();
        this.obtained.add(object0);
        return object0;
    }
}

