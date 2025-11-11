package com.badlogic.gdx.utils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class AtomicQueue {
    private final AtomicReferenceArray queue;
    private final AtomicInteger readIndex;
    private final AtomicInteger writeIndex;

    public AtomicQueue(int v) {
        this.writeIndex = new AtomicInteger();
        this.readIndex = new AtomicInteger();
        this.queue = new AtomicReferenceArray(v);
    }

    private int next(int v) {
        return (v + 1) % this.queue.length();
    }

    @Null
    public Object poll() {
        int v = this.readIndex.get();
        if(v == this.writeIndex.get()) {
            return null;
        }
        Object object0 = this.queue.get(v);
        int v1 = this.next(v);
        this.readIndex.set(v1);
        return object0;
    }

    public boolean put(@Null Object object0) {
        int v = this.writeIndex.get();
        int v1 = this.readIndex.get();
        int v2 = this.next(v);
        if(v2 == v1) {
            return false;
        }
        this.queue.set(v, object0);
        this.writeIndex.set(v2);
        return true;
    }
}

