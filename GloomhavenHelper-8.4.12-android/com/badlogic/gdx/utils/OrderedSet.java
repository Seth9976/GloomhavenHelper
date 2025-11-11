package com.badlogic.gdx.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedSet extends ObjectSet {
    public static class OrderedSetIterator extends ObjectSetIterator {
        private Array items;

        public OrderedSetIterator(OrderedSet orderedSet0) {
            super(orderedSet0);
            this.items = orderedSet0.items;
        }

        @Override  // com.badlogic.gdx.utils.ObjectSet$ObjectSetIterator
        public Object next() {
            if(!this.hasNext) {
                throw new NoSuchElementException();
            }
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            Object object0 = this.items.get(this.nextIndex);
            boolean z = true;
            ++this.nextIndex;
            if(this.nextIndex >= this.set.size) {
                z = false;
            }
            this.hasNext = z;
            return object0;
        }

        @Override  // com.badlogic.gdx.utils.ObjectSet$ObjectSetIterator
        public void remove() {
            if(this.nextIndex < 0) {
                throw new IllegalStateException("next must be called before remove.");
            }
            --this.nextIndex;
            ((OrderedSet)this.set).removeIndex(this.nextIndex);
        }

        @Override  // com.badlogic.gdx.utils.ObjectSet$ObjectSetIterator
        public void reset() {
            boolean z = false;
            this.nextIndex = 0;
            if(this.set.size > 0) {
                z = true;
            }
            this.hasNext = z;
        }

        @Override  // com.badlogic.gdx.utils.ObjectSet$ObjectSetIterator
        public Array toArray() {
            return this.toArray(new Array(true, this.set.size - this.nextIndex));
        }

        @Override  // com.badlogic.gdx.utils.ObjectSet$ObjectSetIterator
        public Array toArray(Array array0) {
            array0.addAll(this.items, this.nextIndex, this.items.size - this.nextIndex);
            this.nextIndex = this.items.size;
            this.hasNext = false;
            return array0;
        }
    }

    final Array items;
    transient OrderedSetIterator iterator1;
    transient OrderedSetIterator iterator2;

    public OrderedSet() {
        this.items = new Array();
    }

    public OrderedSet(int v) {
        super(v);
        this.items = new Array(v);
    }

    public OrderedSet(int v, float f) {
        super(v, f);
        this.items = new Array(v);
    }

    public OrderedSet(OrderedSet orderedSet0) {
        super(orderedSet0);
        this.items = new Array(orderedSet0.items);
    }

    @Override  // com.badlogic.gdx.utils.ObjectSet
    public boolean add(Object object0) {
        if(!super.add(object0)) {
            return false;
        }
        this.items.add(object0);
        return true;
    }

    public boolean add(Object object0, int v) {
        if(!super.add(object0)) {
            int v1 = this.items.indexOf(object0, true);
            if(v1 != v) {
                Object object1 = this.items.removeIndex(v1);
                this.items.insert(v, object1);
            }
            return false;
        }
        this.items.insert(v, object0);
        return true;
    }

    public void addAll(OrderedSet orderedSet0) {
        this.ensureCapacity(orderedSet0.size);
        Object[] arr_object = orderedSet0.items.items;
        int v = orderedSet0.items.size;
        for(int v1 = 0; v1 < v; ++v1) {
            this.add(arr_object[v1]);
        }
    }

    public boolean alter(Object object0, Object object1) {
        if(this.contains(object1)) {
            return false;
        }
        if(!super.remove(object0)) {
            return false;
        }
        super.add(object1);
        int v = this.items.indexOf(object0, false);
        this.items.set(v, object1);
        return true;
    }

    public boolean alterIndex(int v, Object object0) {
        if(v >= 0 && v < this.size && !this.contains(object0)) {
            super.remove(this.items.get(v));
            super.add(object0);
            this.items.set(v, object0);
            return true;
        }
        return false;
    }

    @Override  // com.badlogic.gdx.utils.ObjectSet
    public void clear() {
        this.items.clear();
        super.clear();
    }

    @Override  // com.badlogic.gdx.utils.ObjectSet
    public void clear(int v) {
        this.items.clear();
        super.clear(v);
    }

    @Override  // com.badlogic.gdx.utils.ObjectSet
    public ObjectSetIterator iterator() {
        return this.iterator();
    }

    public OrderedSetIterator iterator() {
        if(Collections.allocateIterators) {
            return new OrderedSetIterator(this);
        }
        if(this.iterator1 == null) {
            this.iterator1 = new OrderedSetIterator(this);
            this.iterator2 = new OrderedSetIterator(this);
        }
        if(!this.iterator1.valid) {
            this.iterator1.reset();
            this.iterator1.valid = true;
            this.iterator2.valid = false;
            return this.iterator1;
        }
        this.iterator2.reset();
        this.iterator2.valid = true;
        this.iterator1.valid = false;
        return this.iterator2;
    }

    @Override  // com.badlogic.gdx.utils.ObjectSet
    public Iterator iterator() {
        return this.iterator();
    }

    public Array orderedItems() {
        return this.items;
    }

    @Override  // com.badlogic.gdx.utils.ObjectSet
    public boolean remove(Object object0) {
        if(!super.remove(object0)) {
            return false;
        }
        this.items.removeValue(object0, false);
        return true;
    }

    public Object removeIndex(int v) {
        Object object0 = this.items.removeIndex(v);
        super.remove(object0);
        return object0;
    }

    @Override  // com.badlogic.gdx.utils.ObjectSet
    public String toString() {
        if(this.size == 0) {
            return "{}";
        }
        Object[] arr_object = this.items.items;
        StringBuilder stringBuilder0 = new StringBuilder(0x20);
        stringBuilder0.append('{');
        stringBuilder0.append(arr_object[0]);
        for(int v = 1; v < this.size; ++v) {
            stringBuilder0.append(", ");
            stringBuilder0.append(arr_object[v]);
        }
        stringBuilder0.append('}');
        return stringBuilder0.toString();
    }

    @Override  // com.badlogic.gdx.utils.ObjectSet
    public String toString(String s) {
        return this.items.toString(s);
    }

    public static OrderedSet with(Object[] arr_object) {
        OrderedSet orderedSet0 = new OrderedSet();
        orderedSet0.addAll(arr_object);
        return orderedSet0;
    }
}

