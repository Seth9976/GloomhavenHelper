package com.badlogic.gdx.utils;

public class PooledLinkedList {
    static final class Item {
        public Item next;
        public Object payload;
        public Item prev;

    }

    private Item curr;
    private Item head;
    private Item iter;
    private final Pool pool;
    private int size;
    private Item tail;

    public PooledLinkedList(int v) {
        this.size = 0;
        this.pool = new Pool(16, v) {
            protected Item newObject() {
                return new Item();
            }

            @Override  // com.badlogic.gdx.utils.Pool
            protected Object newObject() {
                return this.newObject();
            }
        };
    }

    public void add(Object object0) {
        Item pooledLinkedList$Item0 = (Item)this.pool.obtain();
        pooledLinkedList$Item0.payload = object0;
        pooledLinkedList$Item0.next = null;
        pooledLinkedList$Item0.prev = null;
        if(this.head == null) {
            this.head = pooledLinkedList$Item0;
            this.tail = pooledLinkedList$Item0;
            ++this.size;
            return;
        }
        pooledLinkedList$Item0.prev = this.tail;
        this.tail.next = pooledLinkedList$Item0;
        this.tail = pooledLinkedList$Item0;
        ++this.size;
    }

    public void addFirst(Object object0) {
        Item pooledLinkedList$Item0 = (Item)this.pool.obtain();
        pooledLinkedList$Item0.payload = object0;
        Item pooledLinkedList$Item1 = this.head;
        pooledLinkedList$Item0.next = pooledLinkedList$Item1;
        pooledLinkedList$Item0.prev = null;
        if(pooledLinkedList$Item1 == null) {
            this.tail = pooledLinkedList$Item0;
        }
        else {
            pooledLinkedList$Item1.prev = pooledLinkedList$Item0;
        }
        this.head = pooledLinkedList$Item0;
        ++this.size;
    }

    public void clear() {
        this.iter();
        while(this.next() != null) {
            this.remove();
        }
    }

    public void iter() {
        this.iter = this.head;
    }

    public void iterReverse() {
        this.iter = this.tail;
    }

    @Null
    public Object next() {
        Item pooledLinkedList$Item0 = this.iter;
        if(pooledLinkedList$Item0 == null) {
            return null;
        }
        this.curr = this.iter;
        this.iter = this.iter.next;
        return pooledLinkedList$Item0.payload;
    }

    @Null
    public Object previous() {
        Item pooledLinkedList$Item0 = this.iter;
        if(pooledLinkedList$Item0 == null) {
            return null;
        }
        this.curr = this.iter;
        this.iter = this.iter.prev;
        return pooledLinkedList$Item0.payload;
    }

    public void remove() {
        Item pooledLinkedList$Item0 = this.curr;
        if(pooledLinkedList$Item0 == null) {
            return;
        }
        --this.size;
        Item pooledLinkedList$Item1 = pooledLinkedList$Item0.next;
        Item pooledLinkedList$Item2 = this.curr.prev;
        this.pool.free(this.curr);
        this.curr = null;
        if(this.size == 0) {
            this.head = null;
            this.tail = null;
            return;
        }
        if(pooledLinkedList$Item0 == this.head) {
            pooledLinkedList$Item1.prev = null;
            this.head = pooledLinkedList$Item1;
            return;
        }
        if(pooledLinkedList$Item0 == this.tail) {
            pooledLinkedList$Item2.next = null;
            this.tail = pooledLinkedList$Item2;
            return;
        }
        pooledLinkedList$Item2.next = pooledLinkedList$Item1;
        pooledLinkedList$Item1.prev = pooledLinkedList$Item2;
    }

    @Null
    public Object removeLast() {
        Item pooledLinkedList$Item0 = this.tail;
        if(pooledLinkedList$Item0 == null) {
            return null;
        }
        Object object0 = pooledLinkedList$Item0.payload;
        --this.size;
        Item pooledLinkedList$Item1 = this.tail.prev;
        this.pool.free(this.tail);
        if(this.size == 0) {
            this.head = null;
            this.tail = null;
            return object0;
        }
        this.tail = pooledLinkedList$Item1;
        this.tail.next = null;
        return object0;
    }

    public int size() {
        return this.size;
    }
}

