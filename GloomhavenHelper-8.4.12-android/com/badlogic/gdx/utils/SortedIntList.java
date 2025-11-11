package com.badlogic.gdx.utils;

public class SortedIntList implements Iterable {
    public class Iterator implements java.util.Iterator {
        private Node position;
        private Node previousPosition;

        @Override
        public boolean hasNext() {
            return this.position != null;
        }

        public Node next() {
            this.previousPosition = this.position;
            this.position = this.position.n;
            return this.previousPosition;
        }

        @Override
        public Object next() {
            return this.next();
        }

        @Override
        public void remove() {
            Node sortedIntList$Node0 = this.previousPosition;
            if(sortedIntList$Node0 != null) {
                if(sortedIntList$Node0 == SortedIntList.this.first) {
                    SortedIntList.this.first = this.position;
                }
                else {
                    Node sortedIntList$Node1 = this.position;
                    this.previousPosition.p.n = sortedIntList$Node1;
                    if(sortedIntList$Node1 != null) {
                        sortedIntList$Node1.p = this.previousPosition.p;
                    }
                }
                --SortedIntList.this.size;
            }
        }

        public Iterator reset() {
            this.position = SortedIntList.this.first;
            this.previousPosition = null;
            return this;
        }
    }

    public static class Node {
        public int index;
        protected Node n;
        protected Node p;
        public Object value;

    }

    static class NodePool extends Pool {
        protected Node newObject() {
            return new Node();
        }

        @Override  // com.badlogic.gdx.utils.Pool
        protected Object newObject() {
            return this.newObject();
        }

        public Node obtain(Node sortedIntList$Node0, Node sortedIntList$Node1, Object object0, int v) {
            Node sortedIntList$Node2 = (Node)super.obtain();
            sortedIntList$Node2.p = sortedIntList$Node0;
            sortedIntList$Node2.n = sortedIntList$Node1;
            sortedIntList$Node2.value = object0;
            sortedIntList$Node2.index = v;
            return sortedIntList$Node2;
        }
    }

    Node first;
    private transient Iterator iterator;
    private NodePool nodePool;
    int size;

    public SortedIntList() {
        this.nodePool = new NodePool();
        this.size = 0;
    }

    public void clear() {
        Node sortedIntList$Node0;
        while((sortedIntList$Node0 = this.first) != null) {
            this.nodePool.free(sortedIntList$Node0);
            this.first = this.first.n;
        }
        this.size = 0;
    }

    public Object get(int v) {
        Node sortedIntList$Node0 = this.first;
        if(sortedIntList$Node0 != null) {
            while(sortedIntList$Node0.n != null && sortedIntList$Node0.index < v) {
                sortedIntList$Node0 = sortedIntList$Node0.n;
            }
            return sortedIntList$Node0.index == v ? sortedIntList$Node0.value : null;
        }
        return null;
    }

    @Null
    public Object insert(int v, Object object0) {
        Node sortedIntList$Node0 = this.first;
        if(sortedIntList$Node0 != null) {
            while(sortedIntList$Node0.n != null && sortedIntList$Node0.n.index <= v) {
                sortedIntList$Node0 = sortedIntList$Node0.n;
            }
            if(v > sortedIntList$Node0.index) {
                sortedIntList$Node0.n = this.nodePool.obtain(sortedIntList$Node0, sortedIntList$Node0.n, object0, v);
                if(sortedIntList$Node0.n.n != null) {
                    sortedIntList$Node0.n.n.p = sortedIntList$Node0.n;
                }
                ++this.size;
                return null;
            }
            if(v < sortedIntList$Node0.index) {
                Node sortedIntList$Node1 = this.nodePool.obtain(null, this.first, object0, v);
                this.first.p = sortedIntList$Node1;
                this.first = sortedIntList$Node1;
                ++this.size;
                return null;
            }
            sortedIntList$Node0.value = object0;
            return null;
        }
        this.first = this.nodePool.obtain(null, null, object0, v);
        ++this.size;
        return null;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public java.util.Iterator iterator() {
        if(Collections.allocateIterators) {
            return new Iterator(this);
        }
        if(this.iterator == null) {
            this.iterator = new Iterator(this);
        }
        return this.iterator.reset();
    }

    public boolean notEmpty() {
        return this.size > 0;
    }

    public int size() {
        return this.size;
    }
}

