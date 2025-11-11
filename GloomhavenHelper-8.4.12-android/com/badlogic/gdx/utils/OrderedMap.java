package com.badlogic.gdx.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedMap extends ObjectMap {
    public static class OrderedMapEntries extends Entries {
        private Array keys;

        public OrderedMapEntries(OrderedMap orderedMap0) {
            super(orderedMap0);
            this.keys = orderedMap0.keys;
        }

        @Override  // com.badlogic.gdx.utils.ObjectMap$Entries
        public Entry next() {
            if(!this.hasNext) {
                throw new NoSuchElementException();
            }
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            this.currentIndex = this.nextIndex;
            Entry objectMap$Entry0 = this.entry;
            objectMap$Entry0.key = this.keys.get(this.nextIndex);
            Entry objectMap$Entry1 = this.entry;
            objectMap$Entry1.value = this.map.get(this.entry.key);
            boolean z = true;
            ++this.nextIndex;
            if(this.nextIndex >= this.map.size) {
                z = false;
            }
            this.hasNext = z;
            return this.entry;
        }

        @Override  // com.badlogic.gdx.utils.ObjectMap$Entries
        public Object next() {
            return this.next();
        }

        @Override  // com.badlogic.gdx.utils.ObjectMap$Entries
        public void remove() {
            if(this.currentIndex < 0) {
                throw new IllegalStateException("next must be called before remove.");
            }
            this.map.remove(this.entry.key);
            --this.nextIndex;
            this.currentIndex = -1;
        }

        @Override  // com.badlogic.gdx.utils.ObjectMap$Entries
        public void reset() {
            this.currentIndex = -1;
            boolean z = false;
            this.nextIndex = 0;
            if(this.map.size > 0) {
                z = true;
            }
            this.hasNext = z;
        }
    }

    public static class OrderedMapKeys extends Keys {
        private Array keys;

        public OrderedMapKeys(OrderedMap orderedMap0) {
            super(orderedMap0);
            this.keys = orderedMap0.keys;
        }

        @Override  // com.badlogic.gdx.utils.ObjectMap$Keys
        public Object next() {
            if(!this.hasNext) {
                throw new NoSuchElementException();
            }
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            Object object0 = this.keys.get(this.nextIndex);
            this.currentIndex = this.nextIndex;
            boolean z = true;
            ++this.nextIndex;
            if(this.nextIndex >= this.map.size) {
                z = false;
            }
            this.hasNext = z;
            return object0;
        }

        @Override  // com.badlogic.gdx.utils.ObjectMap$Keys
        public void remove() {
            if(this.currentIndex < 0) {
                throw new IllegalStateException("next must be called before remove.");
            }
            ((OrderedMap)this.map).removeIndex(this.currentIndex);
            this.nextIndex = this.currentIndex;
            this.currentIndex = -1;
        }

        @Override  // com.badlogic.gdx.utils.ObjectMap$Keys
        public void reset() {
            this.currentIndex = -1;
            boolean z = false;
            this.nextIndex = 0;
            if(this.map.size > 0) {
                z = true;
            }
            this.hasNext = z;
        }

        @Override  // com.badlogic.gdx.utils.ObjectMap$Keys
        public Array toArray() {
            return this.toArray(new Array(true, this.keys.size - this.nextIndex));
        }

        @Override  // com.badlogic.gdx.utils.ObjectMap$Keys
        public Array toArray(Array array0) {
            array0.addAll(this.keys, this.nextIndex, this.keys.size - this.nextIndex);
            this.nextIndex = this.keys.size;
            this.hasNext = false;
            return array0;
        }
    }

    public static class OrderedMapValues extends Values {
        private Array keys;

        public OrderedMapValues(OrderedMap orderedMap0) {
            super(orderedMap0);
            this.keys = orderedMap0.keys;
        }

        @Override  // com.badlogic.gdx.utils.ObjectMap$Values
        public Object next() {
            if(!this.hasNext) {
                throw new NoSuchElementException();
            }
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            Object object0 = this.map.get(this.keys.get(this.nextIndex));
            this.currentIndex = this.nextIndex;
            boolean z = true;
            ++this.nextIndex;
            if(this.nextIndex >= this.map.size) {
                z = false;
            }
            this.hasNext = z;
            return object0;
        }

        @Override  // com.badlogic.gdx.utils.ObjectMap$Values
        public void remove() {
            if(this.currentIndex < 0) {
                throw new IllegalStateException("next must be called before remove.");
            }
            ((OrderedMap)this.map).removeIndex(this.currentIndex);
            this.nextIndex = this.currentIndex;
            this.currentIndex = -1;
        }

        @Override  // com.badlogic.gdx.utils.ObjectMap$Values
        public void reset() {
            this.currentIndex = -1;
            boolean z = false;
            this.nextIndex = 0;
            if(this.map.size > 0) {
                z = true;
            }
            this.hasNext = z;
        }

        @Override  // com.badlogic.gdx.utils.ObjectMap$Values
        public Array toArray() {
            return this.toArray(new Array(true, this.keys.size - this.nextIndex));
        }

        @Override  // com.badlogic.gdx.utils.ObjectMap$Values
        public Array toArray(Array array0) {
            int v = this.keys.size;
            array0.ensureCapacity(v - this.nextIndex);
            Object[] arr_object = this.keys.items;
            for(int v1 = this.nextIndex; v1 < v; ++v1) {
                array0.add(this.map.get(arr_object[v1]));
            }
            this.currentIndex = v - 1;
            this.nextIndex = v;
            this.hasNext = false;
            return array0;
        }
    }

    final Array keys;

    public OrderedMap() {
        this.keys = new Array();
    }

    public OrderedMap(int v) {
        super(v);
        this.keys = new Array(v);
    }

    public OrderedMap(int v, float f) {
        super(v, f);
        this.keys = new Array(v);
    }

    public OrderedMap(OrderedMap orderedMap0) {
        super(orderedMap0);
        this.keys = new Array(orderedMap0.keys);
    }

    public boolean alter(Object object0, Object object1) {
        if(this.containsKey(object1)) {
            return false;
        }
        int v = this.keys.indexOf(object0, false);
        if(v == -1) {
            return false;
        }
        super.put(object1, super.remove(object0));
        this.keys.set(v, object1);
        return true;
    }

    public boolean alterIndex(int v, Object object0) {
        if(v >= 0 && v < this.size && !this.containsKey(object0)) {
            super.put(object0, super.remove(this.keys.get(v)));
            this.keys.set(v, object0);
            return true;
        }
        return false;
    }

    @Override  // com.badlogic.gdx.utils.ObjectMap
    public void clear() {
        this.keys.clear();
        super.clear();
    }

    @Override  // com.badlogic.gdx.utils.ObjectMap
    public void clear(int v) {
        this.keys.clear();
        super.clear(v);
    }

    @Override  // com.badlogic.gdx.utils.ObjectMap
    public Entries entries() {
        if(Collections.allocateIterators) {
            return new OrderedMapEntries(this);
        }
        if(this.entries1 == null) {
            this.entries1 = new OrderedMapEntries(this);
            this.entries2 = new OrderedMapEntries(this);
        }
        if(!this.entries1.valid) {
            this.entries1.reset();
            this.entries1.valid = true;
            this.entries2.valid = false;
            return this.entries1;
        }
        this.entries2.reset();
        this.entries2.valid = true;
        this.entries1.valid = false;
        return this.entries2;
    }

    @Override  // com.badlogic.gdx.utils.ObjectMap
    public Entries iterator() {
        return this.entries();
    }

    @Override  // com.badlogic.gdx.utils.ObjectMap
    public Iterator iterator() {
        return this.iterator();
    }

    @Override  // com.badlogic.gdx.utils.ObjectMap
    public Keys keys() {
        if(Collections.allocateIterators) {
            return new OrderedMapKeys(this);
        }
        if(this.keys1 == null) {
            this.keys1 = new OrderedMapKeys(this);
            this.keys2 = new OrderedMapKeys(this);
        }
        if(!this.keys1.valid) {
            this.keys1.reset();
            this.keys1.valid = true;
            this.keys2.valid = false;
            return this.keys1;
        }
        this.keys2.reset();
        this.keys2.valid = true;
        this.keys1.valid = false;
        return this.keys2;
    }

    public Array orderedKeys() {
        return this.keys;
    }

    @Override  // com.badlogic.gdx.utils.ObjectMap
    public Object put(Object object0, Object object1) {
        int v = this.locateKey(object0);
        if(v >= 0) {
            Object object2 = this.valueTable[v];
            this.valueTable[v] = object1;
            return object2;
        }
        this.keyTable[-(v + 1)] = object0;
        this.valueTable[-(v + 1)] = object1;
        this.keys.add(object0);
        int v1 = this.size + 1;
        this.size = v1;
        if(v1 >= this.threshold) {
            this.resize(this.keyTable.length << 1);
        }
        return null;
    }

    public void putAll(OrderedMap orderedMap0) {
        this.ensureCapacity(orderedMap0.size);
        Object[] arr_object = orderedMap0.keys.items;
        int v = orderedMap0.keys.size;
        for(int v1 = 0; v1 < v; ++v1) {
            Object object0 = arr_object[v1];
            this.put(object0, orderedMap0.get(object0));
        }
    }

    @Override  // com.badlogic.gdx.utils.ObjectMap
    public Object remove(Object object0) {
        this.keys.removeValue(object0, false);
        return super.remove(object0);
    }

    public Object removeIndex(int v) {
        return super.remove(this.keys.removeIndex(v));
    }

    @Override  // com.badlogic.gdx.utils.ObjectMap
    protected String toString(String s, boolean z) {
        if(this.size == 0) {
            return z ? "{}" : "";
        }
        StringBuilder stringBuilder0 = new StringBuilder(0x20);
        if(z) {
            stringBuilder0.append('{');
        }
        Array array0 = this.keys;
        int v1 = array0.size;
        for(int v = 0; v < v1; ++v) {
            Object object0 = array0.get(v);
            if(v > 0) {
                stringBuilder0.append(s);
            }
            String s1 = object0 == this ? "(this)" : object0;
            stringBuilder0.append(s1);
            stringBuilder0.append('=');
            String s2 = this.get(object0);
            if(s2 == this) {
                s2 = "(this)";
            }
            stringBuilder0.append(s2);
        }
        if(z) {
            stringBuilder0.append('}');
        }
        return stringBuilder0.toString();
    }

    @Override  // com.badlogic.gdx.utils.ObjectMap
    public Values values() {
        if(Collections.allocateIterators) {
            return new OrderedMapValues(this);
        }
        if(this.values1 == null) {
            this.values1 = new OrderedMapValues(this);
            this.values2 = new OrderedMapValues(this);
        }
        if(!this.values1.valid) {
            this.values1.reset();
            this.values1.valid = true;
            this.values2.valid = false;
            return this.values1;
        }
        this.values2.reset();
        this.values2.valid = true;
        this.values1.valid = false;
        return this.values2;
    }
}

