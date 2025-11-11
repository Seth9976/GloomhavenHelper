package com.badlogic.gdx.utils;

import com.badlogic.gdx.utils.reflect.ArrayReflection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue implements Iterable {
    public static class QueueIterable implements Iterable {
        private final boolean allowRemove;
        private QueueIterator iterator1;
        private QueueIterator iterator2;
        private final Queue queue;

        public QueueIterable(Queue queue0) {
            this(queue0, true);
        }

        public QueueIterable(Queue queue0, boolean z) {
            this.queue = queue0;
            this.allowRemove = z;
        }

        @Override
        public Iterator iterator() {
            if(Collections.allocateIterators) {
                return new QueueIterator(this.queue, this.allowRemove);
            }
            if(this.iterator1 == null) {
                this.iterator1 = new QueueIterator(this.queue, this.allowRemove);
                this.iterator2 = new QueueIterator(this.queue, this.allowRemove);
            }
            if(!this.iterator1.valid) {
                this.iterator1.index = 0;
                this.iterator1.valid = true;
                this.iterator2.valid = false;
                return this.iterator1;
            }
            this.iterator2.index = 0;
            this.iterator2.valid = true;
            this.iterator1.valid = false;
            return this.iterator2;
        }
    }

    public static class QueueIterator implements Iterable, Iterator {
        private final boolean allowRemove;
        int index;
        private final Queue queue;
        boolean valid;

        public QueueIterator(Queue queue0) {
            this(queue0, true);
        }

        public QueueIterator(Queue queue0, boolean z) {
            this.valid = true;
            this.queue = queue0;
            this.allowRemove = z;
        }

        @Override
        public boolean hasNext() {
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            return this.index < this.queue.size;
        }

        @Override
        public Iterator iterator() {
            return this;
        }

        @Override
        public Object next() {
            if(this.index >= this.queue.size) {
                throw new NoSuchElementException(String.valueOf(this.index));
            }
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }
            int v = this.index;
            this.index = v + 1;
            return this.queue.get(v);
        }

        @Override
        public void remove() {
            if(!this.allowRemove) {
                throw new GdxRuntimeException("Remove not allowed.");
            }
            --this.index;
            this.queue.removeIndex(this.index);
        }

        public void reset() {
            this.index = 0;
        }
    }

    protected int head;
    private transient QueueIterable iterable;
    public int size;
    protected int tail;
    protected Object[] values;

    public Queue() {
        this(16);
    }

    public Queue(int v) {
        this.head = 0;
        this.tail = 0;
        this.size = 0;
        this.values = new Object[v];
    }

    public Queue(int v, Class class0) {
        this.head = 0;
        this.tail = 0;
        this.size = 0;
        this.values = (Object[])ArrayReflection.newInstance(class0, v);
    }

    public void addFirst(@Null Object object0) {
        Object[] arr_object = this.values;
        if(this.size == arr_object.length) {
            this.resize(arr_object.length << 1);
            arr_object = this.values;
        }
        int v = this.head - 1 == -1 ? arr_object.length - 1 : this.head - 1;
        arr_object[v] = object0;
        this.head = v;
        ++this.size;
    }

    public void addLast(@Null Object object0) {
        Object[] arr_object = this.values;
        if(this.size == arr_object.length) {
            this.resize(arr_object.length << 1);
            arr_object = this.values;
        }
        int v = this.tail;
        this.tail = v + 1;
        arr_object[v] = object0;
        if(this.tail == arr_object.length) {
            this.tail = 0;
        }
        ++this.size;
    }

    public void clear() {
        if(this.size == 0) {
            return;
        }
        Object[] arr_object = this.values;
        int v = this.head;
        int v1 = this.tail;
        if(v < v1) {
            while(v < v1) {
                arr_object[v] = null;
                ++v;
            }
        }
        else {
            while(v < arr_object.length) {
                arr_object[v] = null;
                ++v;
            }
            for(int v2 = 0; v2 < v1; ++v2) {
                arr_object[v2] = null;
            }
        }
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    public void ensureCapacity(int v) {
        int v1 = this.size + v;
        if(this.values.length < v1) {
            this.resize(v1);
        }
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(object0 != null && object0 instanceof Queue) {
            int v = this.size;
            if(((Queue)object0).size != v) {
                return false;
            }
            Object[] arr_object = this.values;
            Object[] arr_object1 = ((Queue)object0).values;
            int v1 = this.head;
            int v2 = ((Queue)object0).head;
            for(int v3 = 0; v3 < v; ++v3) {
                Object object1 = arr_object[v1];
                Object object2 = arr_object1[v2];
                if(object1 != null) {
                    if(!object1.equals(object2)) {
                        return false;
                    }
                }
                else if(object2 == null) {
                }
                else {
                    return false;
                }
                ++v1;
                ++v2;
                if(v1 == arr_object.length) {
                    v1 = 0;
                }
                if(v2 == arr_object1.length) {
                    v2 = 0;
                }
            }
            return true;
        }
        return false;
    }

    public boolean equalsIdentity(Object object0) {
        if(this == object0) {
            return true;
        }
        if(object0 != null && object0 instanceof Queue) {
            int v = this.size;
            if(((Queue)object0).size != v) {
                return false;
            }
            Object[] arr_object = this.values;
            Object[] arr_object1 = ((Queue)object0).values;
            int v1 = this.head;
            int v2 = ((Queue)object0).head;
            for(int v3 = 0; v3 < v; ++v3) {
                if(arr_object[v1] != arr_object1[v2]) {
                    return false;
                }
                ++v1;
                ++v2;
                if(v1 == arr_object.length) {
                    v1 = 0;
                }
                if(v2 == arr_object1.length) {
                    v2 = 0;
                }
            }
            return true;
        }
        return false;
    }

    public Object first() {
        if(this.size == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return this.values[this.head];
    }

    public Object get(int v) {
        if(v < 0) {
            throw new IndexOutOfBoundsException("index can\'t be < 0: " + v);
        }
        if(v >= this.size) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        int v1 = this.head + v;
        return this.values[(v1 < this.values.length ? this.head + v : v1 - this.values.length)];
    }

    @Override
    public int hashCode() {
        int v = this.size;
        Object[] arr_object = this.values;
        int v1 = v + 1;
        int v2 = this.head;
        for(int v3 = 0; v3 < v; ++v3) {
            Object object0 = arr_object[v2];
            v1 *= 0x1F;
            v1 = object0 == null ? v1 * 0x1F : v1 + object0.hashCode();
            ++v2;
            v2 = v2 == arr_object.length ? 0 : v2 + 1;
        }
        return v1;
    }

    public int indexOf(Object object0, boolean z) {
        if(this.size == 0) {
            return -1;
        }
        Object[] arr_object = this.values;
        int v = this.head;
        int v1 = this.tail;
        int v2 = 0;
        if(!z && object0 != null) {
            if(v < v1) {
                for(int v3 = v; v3 < v1; ++v3) {
                    if(object0.equals(arr_object[v3])) {
                        return v3 - v;
                    }
                }
                return -1;
            }
            for(int v4 = v; v4 < arr_object.length; ++v4) {
                if(object0.equals(arr_object[v4])) {
                    return v4 - v;
                }
            }
            while(v2 < v1) {
                if(object0.equals(arr_object[v2])) {
                    return v2 + arr_object.length - v;
                }
                ++v2;
            }
            return -1;
        }
        if(v < v1) {
            for(int v5 = v; v5 < v1; ++v5) {
                if(arr_object[v5] == object0) {
                    return v5 - v;
                }
            }
            return -1;
        }
        for(int v6 = v; v6 < arr_object.length; ++v6) {
            if(arr_object[v6] == object0) {
                return v6 - v;
            }
        }
        while(v2 < v1) {
            if(arr_object[v2] == object0) {
                return v2 + arr_object.length - v;
            }
            ++v2;
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator iterator() {
        if(Collections.allocateIterators) {
            return new QueueIterator(this, true);
        }
        if(this.iterable == null) {
            this.iterable = new QueueIterable(this);
        }
        return this.iterable.iterator();
    }

    public Object last() {
        if(this.size == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return this.values[(this.tail - 1 == -1 ? this.values.length - 1 : this.tail - 1)];
    }

    public boolean notEmpty() {
        return this.size > 0;
    }

    public Object removeFirst() {
        if(this.size == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }
        Object[] arr_object = this.values;
        int v = this.head;
        Object object0 = arr_object[v];
        arr_object[v] = null;
        this.head = v + 1;
        if(this.head == arr_object.length) {
            this.head = 0;
        }
        --this.size;
        return object0;
    }

    public Object removeIndex(int v) {
        Object object0;
        if(v < 0) {
            throw new IndexOutOfBoundsException("index can\'t be < 0: " + v);
        }
        if(v >= this.size) {
            throw new IndexOutOfBoundsException("index can\'t be >= size: " + v + " >= " + this.size);
        }
        Object[] arr_object = this.values;
        int v1 = this.head;
        int v2 = this.tail;
        int v3 = v + v1;
        if(v1 < v2) {
            object0 = arr_object[v3];
            System.arraycopy(arr_object, v3 + 1, arr_object, v3, v2 - v3);
            arr_object[v2] = null;
            --this.tail;
        }
        else if(v3 >= arr_object.length) {
            int v4 = v3 - arr_object.length;
            object0 = arr_object[v4];
            System.arraycopy(arr_object, v4 + 1, arr_object, v4, v2 - v4);
            --this.tail;
        }
        else {
            Object object1 = arr_object[v3];
            System.arraycopy(arr_object, v1, arr_object, v1 + 1, v3 - v1);
            arr_object[v1] = null;
            ++this.head;
            if(this.head == arr_object.length) {
                this.head = 0;
            }
            object0 = object1;
        }
        --this.size;
        return object0;
    }

    public Object removeLast() {
        if(this.size == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }
        Object[] arr_object = this.values;
        int v = this.tail - 1 == -1 ? arr_object.length - 1 : this.tail - 1;
        Object object0 = arr_object[v];
        arr_object[v] = null;
        this.tail = v;
        --this.size;
        return object0;
    }

    public boolean removeValue(Object object0, boolean z) {
        int v = this.indexOf(object0, z);
        if(v == -1) {
            return false;
        }
        this.removeIndex(v);
        return true;
    }

    protected void resize(int v) {
        Object[] arr_object = this.values;
        int v1 = this.head;
        int v2 = this.tail;
        Object[] arr_object1 = (Object[])ArrayReflection.newInstance(arr_object.getClass().getComponentType(), v);
        if(v1 < v2) {
            System.arraycopy(arr_object, v1, arr_object1, 0, v2 - v1);
        }
        else if(this.size > 0) {
            int v3 = arr_object.length - v1;
            System.arraycopy(arr_object, v1, arr_object1, 0, v3);
            System.arraycopy(arr_object, 0, arr_object1, v3, v2);
        }
        this.values = arr_object1;
        this.head = 0;
        this.tail = this.size;
    }

    @Override
    public String toString() {
        if(this.size == 0) {
            return "[]";
        }
        Object[] arr_object = this.values;
        int v = this.head;
        int v1 = this.tail;
        StringBuilder stringBuilder0 = new StringBuilder(0x40);
        stringBuilder0.append('[');
        stringBuilder0.append(arr_object[v]);
        while(true) {
            v = (v + 1) % arr_object.length;
            if(v == v1) {
                break;
            }
            stringBuilder0.append(", ").append(arr_object[v]);
        }
        stringBuilder0.append(']');
        return "";
    }

    public String toString(String s) {
        if(this.size == 0) {
            return "";
        }
        Object[] arr_object = this.values;
        int v = this.head;
        int v1 = this.tail;
        StringBuilder stringBuilder0 = new StringBuilder(0x40);
        stringBuilder0.append(arr_object[v]);
        while(true) {
            v = (v + 1) % arr_object.length;
            if(v == v1) {
                break;
            }
            stringBuilder0.append(s).append(arr_object[v]);
        }
        return "";
    }
}

