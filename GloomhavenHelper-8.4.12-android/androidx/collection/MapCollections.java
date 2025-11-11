package androidx.collection;

import androidx.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

abstract class MapCollections {
    final class ArrayIterator implements Iterator {
        boolean mCanRemove;
        int mIndex;
        final int mOffset;
        int mSize;

        ArrayIterator(int v) {
            this.mCanRemove = false;
            this.mOffset = v;
            this.mSize = mapCollections0.colGetSize();
        }

        @Override
        public boolean hasNext() {
            return this.mIndex < this.mSize;
        }

        @Override
        public Object next() {
            if(!this.hasNext()) {
                throw new NoSuchElementException();
            }
            Object object0 = MapCollections.this.colGetEntry(this.mIndex, this.mOffset);
            ++this.mIndex;
            this.mCanRemove = true;
            return object0;
        }

        @Override
        public void remove() {
            if(!this.mCanRemove) {
                throw new IllegalStateException();
            }
            --this.mIndex;
            --this.mSize;
            this.mCanRemove = false;
            MapCollections.this.colRemoveAt(this.mIndex);
        }
    }

    final class EntrySet implements Set {
        @Override
        public boolean add(Object object0) {
            return this.add(((Map.Entry)object0));
        }

        public boolean add(Map.Entry map$Entry0) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(Collection collection0) {
            int v = MapCollections.this.colGetSize();
            for(Object object0: collection0) {
                Object object1 = ((Map.Entry)object0).getKey();
                Object object2 = ((Map.Entry)object0).getValue();
                MapCollections.this.colPut(object1, object2);
            }
            return v != MapCollections.this.colGetSize();
        }

        @Override
        public void clear() {
            MapCollections.this.colClear();
        }

        @Override
        public boolean contains(Object object0) {
            if(!(object0 instanceof Map.Entry)) {
                return false;
            }
            Object object1 = ((Map.Entry)object0).getKey();
            int v = MapCollections.this.colIndexOfKey(object1);
            return v >= 0 ? ContainerHelpers.equal(MapCollections.this.colGetEntry(v, 1), ((Map.Entry)object0).getValue()) : false;
        }

        @Override
        public boolean containsAll(Collection collection0) {
            for(Object object0: collection0) {
                if(!this.contains(object0)) {
                    return false;
                }
                if(false) {
                    break;
                }
            }
            return true;
        }

        @Override
        public boolean equals(Object object0) {
            return MapCollections.equalsSetHelper(this, object0);
        }

        @Override
        public int hashCode() {
            int v = MapCollections.this.colGetSize() - 1;
            int v1 = 0;
            while(v >= 0) {
                Object object0 = MapCollections.this.colGetEntry(v, 0);
                Object object1 = MapCollections.this.colGetEntry(v, 1);
                v1 += (object0 == null ? 0 : object0.hashCode()) ^ (object1 == null ? 0 : object1.hashCode());
                --v;
            }
            return v1;
        }

        @Override
        public boolean isEmpty() {
            return MapCollections.this.colGetSize() == 0;
        }

        @Override
        public Iterator iterator() {
            return new MapIterator(MapCollections.this);
        }

        @Override
        public boolean remove(Object object0) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean removeAll(Collection collection0) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean retainAll(Collection collection0) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int size() {
            return MapCollections.this.colGetSize();
        }

        @Override
        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object[] toArray(Object[] arr_object) {
            throw new UnsupportedOperationException();
        }
    }

    final class KeySet implements Set {
        @Override
        public boolean add(Object object0) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(Collection collection0) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            MapCollections.this.colClear();
        }

        @Override
        public boolean contains(Object object0) {
            return MapCollections.this.colIndexOfKey(object0) >= 0;
        }

        @Override
        public boolean containsAll(Collection collection0) {
            return MapCollections.containsAllHelper(MapCollections.this.colGetMap(), collection0);
        }

        @Override
        public boolean equals(Object object0) {
            return MapCollections.equalsSetHelper(this, object0);
        }

        @Override
        public int hashCode() {
            int v = MapCollections.this.colGetSize() - 1;
            int v1 = 0;
            while(v >= 0) {
                Object object0 = MapCollections.this.colGetEntry(v, 0);
                v1 += (object0 == null ? 0 : object0.hashCode());
                --v;
            }
            return v1;
        }

        @Override
        public boolean isEmpty() {
            return MapCollections.this.colGetSize() == 0;
        }

        @Override
        public Iterator iterator() {
            return new ArrayIterator(MapCollections.this, 0);
        }

        @Override
        public boolean remove(Object object0) {
            int v = MapCollections.this.colIndexOfKey(object0);
            if(v >= 0) {
                MapCollections.this.colRemoveAt(v);
                return true;
            }
            return false;
        }

        @Override
        public boolean removeAll(Collection collection0) {
            return MapCollections.removeAllHelper(MapCollections.this.colGetMap(), collection0);
        }

        @Override
        public boolean retainAll(Collection collection0) {
            return MapCollections.retainAllHelper(MapCollections.this.colGetMap(), collection0);
        }

        @Override
        public int size() {
            return MapCollections.this.colGetSize();
        }

        @Override
        public Object[] toArray() {
            return MapCollections.this.toArrayHelper(0);
        }

        @Override
        public Object[] toArray(Object[] arr_object) {
            return MapCollections.this.toArrayHelper(arr_object, 0);
        }
    }

    final class MapIterator implements Iterator, Map.Entry {
        int mEnd;
        boolean mEntryValid;
        int mIndex;

        MapIterator() {
            this.mEntryValid = false;
            this.mEnd = mapCollections0.colGetSize() - 1;
            this.mIndex = -1;
        }

        @Override
        public boolean equals(Object object0) {
            if(!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            return object0 instanceof Map.Entry ? ContainerHelpers.equal(((Map.Entry)object0).getKey(), MapCollections.this.colGetEntry(this.mIndex, 0)) && ContainerHelpers.equal(((Map.Entry)object0).getValue(), MapCollections.this.colGetEntry(this.mIndex, 1)) : false;
        }

        @Override
        public Object getKey() {
            if(!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            return MapCollections.this.colGetEntry(this.mIndex, 0);
        }

        @Override
        public Object getValue() {
            if(!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            return MapCollections.this.colGetEntry(this.mIndex, 1);
        }

        @Override
        public boolean hasNext() {
            return this.mIndex < this.mEnd;
        }

        @Override
        public int hashCode() {
            if(!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            int v = 0;
            Object object0 = MapCollections.this.colGetEntry(this.mIndex, 0);
            Object object1 = MapCollections.this.colGetEntry(this.mIndex, 1);
            int v1 = object0 == null ? 0 : object0.hashCode();
            if(object1 != null) {
                v = object1.hashCode();
            }
            return v1 ^ v;
        }

        @Override
        public Object next() {
            return this.next();
        }

        public Map.Entry next() {
            if(!this.hasNext()) {
                throw new NoSuchElementException();
            }
            ++this.mIndex;
            this.mEntryValid = true;
            return this;
        }

        @Override
        public void remove() {
            if(!this.mEntryValid) {
                throw new IllegalStateException();
            }
            MapCollections.this.colRemoveAt(this.mIndex);
            --this.mIndex;
            --this.mEnd;
            this.mEntryValid = false;
        }

        @Override
        public Object setValue(Object object0) {
            if(!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            return MapCollections.this.colSetValue(this.mIndex, object0);
        }

        @Override
        public String toString() {
            return this.getKey() + "=" + this.getValue();
        }
    }

    final class ValuesCollection implements Collection {
        @Override
        public boolean add(Object object0) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(Collection collection0) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            MapCollections.this.colClear();
        }

        @Override
        public boolean contains(Object object0) {
            return MapCollections.this.colIndexOfValue(object0) >= 0;
        }

        @Override
        public boolean containsAll(Collection collection0) {
            for(Object object0: collection0) {
                if(!this.contains(object0)) {
                    return false;
                }
                if(false) {
                    break;
                }
            }
            return true;
        }

        @Override
        public boolean isEmpty() {
            return MapCollections.this.colGetSize() == 0;
        }

        @Override
        public Iterator iterator() {
            return new ArrayIterator(MapCollections.this, 1);
        }

        @Override
        public boolean remove(Object object0) {
            int v = MapCollections.this.colIndexOfValue(object0);
            if(v >= 0) {
                MapCollections.this.colRemoveAt(v);
                return true;
            }
            return false;
        }

        @Override
        public boolean removeAll(Collection collection0) {
            int v = MapCollections.this.colGetSize();
            boolean z = false;
            for(int v1 = 0; v1 < v; ++v1) {
                if(collection0.contains(MapCollections.this.colGetEntry(v1, 1))) {
                    MapCollections.this.colRemoveAt(v1);
                    --v1;
                    --v;
                    z = true;
                }
            }
            return z;
        }

        @Override
        public boolean retainAll(Collection collection0) {
            int v = MapCollections.this.colGetSize();
            boolean z = false;
            for(int v1 = 0; v1 < v; ++v1) {
                if(!collection0.contains(MapCollections.this.colGetEntry(v1, 1))) {
                    MapCollections.this.colRemoveAt(v1);
                    --v1;
                    --v;
                    z = true;
                }
            }
            return z;
        }

        @Override
        public int size() {
            return MapCollections.this.colGetSize();
        }

        @Override
        public Object[] toArray() {
            return MapCollections.this.toArrayHelper(1);
        }

        @Override
        public Object[] toArray(Object[] arr_object) {
            return MapCollections.this.toArrayHelper(arr_object, 1);
        }
    }

    @Nullable
    EntrySet mEntrySet;
    @Nullable
    KeySet mKeySet;
    @Nullable
    ValuesCollection mValues;

    protected abstract void colClear();

    protected abstract Object colGetEntry(int arg1, int arg2);

    protected abstract Map colGetMap();

    protected abstract int colGetSize();

    protected abstract int colIndexOfKey(Object arg1);

    protected abstract int colIndexOfValue(Object arg1);

    protected abstract void colPut(Object arg1, Object arg2);

    protected abstract void colRemoveAt(int arg1);

    protected abstract Object colSetValue(int arg1, Object arg2);

    public static boolean containsAllHelper(Map map0, Collection collection0) {
        for(Object object0: collection0) {
            if(!map0.containsKey(object0)) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    public static boolean equalsSetHelper(Set set0, Object object0) {
        if(set0 == object0) {
            return true;
        }
        if(object0 instanceof Set) {
            Set set1 = (Set)object0;
            try {
                return set0.size() != set1.size() || !set0.containsAll(set1) ? false : true;
            }
            catch(NullPointerException unused_ex) {
                return false;
            }
            catch(ClassCastException unused_ex) {
                return false;
            }
        }
        return false;
    }

    public Set getEntrySet() {
        if(this.mEntrySet == null) {
            this.mEntrySet = new EntrySet(this);
        }
        return this.mEntrySet;
    }

    public Set getKeySet() {
        if(this.mKeySet == null) {
            this.mKeySet = new KeySet(this);
        }
        return this.mKeySet;
    }

    public Collection getValues() {
        if(this.mValues == null) {
            this.mValues = new ValuesCollection(this);
        }
        return this.mValues;
    }

    public static boolean removeAllHelper(Map map0, Collection collection0) {
        int v = map0.size();
        for(Object object0: collection0) {
            map0.remove(object0);
        }
        return v != map0.size();
    }

    public static boolean retainAllHelper(Map map0, Collection collection0) {
        int v = map0.size();
        Iterator iterator0 = map0.keySet().iterator();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            if(!collection0.contains(object0)) {
                iterator0.remove();
            }
        }
        return v != map0.size();
    }

    public Object[] toArrayHelper(int v) {
        int v1 = this.colGetSize();
        Object[] arr_object = new Object[v1];
        for(int v2 = 0; v2 < v1; ++v2) {
            arr_object[v2] = this.colGetEntry(v2, v);
        }
        return arr_object;
    }

    public Object[] toArrayHelper(Object[] arr_object, int v) {
        int v1 = this.colGetSize();
        if(arr_object.length < v1) {
            arr_object = (Object[])Array.newInstance(arr_object.getClass().getComponentType(), v1);
        }
        for(int v2 = 0; v2 < v1; ++v2) {
            arr_object[v2] = this.colGetEntry(v2, v);
        }
        if(arr_object.length > v1) {
            arr_object[v1] = null;
        }
        return arr_object;
    }
}

