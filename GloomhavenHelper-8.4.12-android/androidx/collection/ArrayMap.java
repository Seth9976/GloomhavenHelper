package androidx.collection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;

public class ArrayMap extends SimpleArrayMap implements Map {
    @Nullable
    MapCollections mCollections;

    public ArrayMap() {
    }

    public ArrayMap(int v) {
        super(v);
    }

    public ArrayMap(SimpleArrayMap simpleArrayMap0) {
        super(simpleArrayMap0);
    }

    public boolean containsAll(@NonNull Collection collection0) {
        return MapCollections.containsAllHelper(this, collection0);
    }

    @Override
    public Set entrySet() {
        return this.getCollection().getEntrySet();
    }

    private MapCollections getCollection() {
        if(this.mCollections == null) {
            this.mCollections = new MapCollections() {
                @Override  // androidx.collection.MapCollections
                protected void colClear() {
                    ArrayMap.this.clear();
                }

                @Override  // androidx.collection.MapCollections
                protected Object colGetEntry(int v, int v1) {
                    return ArrayMap.this.mArray[(v << 1) + v1];
                }

                @Override  // androidx.collection.MapCollections
                protected Map colGetMap() {
                    return ArrayMap.this;
                }

                @Override  // androidx.collection.MapCollections
                protected int colGetSize() {
                    return ArrayMap.this.mSize;
                }

                @Override  // androidx.collection.MapCollections
                protected int colIndexOfKey(Object object0) {
                    return ArrayMap.this.indexOfKey(object0);
                }

                @Override  // androidx.collection.MapCollections
                protected int colIndexOfValue(Object object0) {
                    return ArrayMap.this.indexOfValue(object0);
                }

                @Override  // androidx.collection.MapCollections
                protected void colPut(Object object0, Object object1) {
                    ArrayMap.this.put(object0, object1);
                }

                @Override  // androidx.collection.MapCollections
                protected void colRemoveAt(int v) {
                    ArrayMap.this.removeAt(v);
                }

                @Override  // androidx.collection.MapCollections
                protected Object colSetValue(int v, Object object0) {
                    return ArrayMap.this.setValueAt(v, object0);
                }
            };
        }
        return this.mCollections;
    }

    @Override
    public Set keySet() {
        return this.getCollection().getKeySet();
    }

    @Override
    public void putAll(Map map0) {
        this.ensureCapacity(this.mSize + map0.size());
        for(Object object0: map0.entrySet()) {
            this.put(((Map.Entry)object0).getKey(), ((Map.Entry)object0).getValue());
        }
    }

    public boolean removeAll(@NonNull Collection collection0) {
        return MapCollections.removeAllHelper(this, collection0);
    }

    public boolean retainAll(@NonNull Collection collection0) {
        return MapCollections.retainAllHelper(this, collection0);
    }

    @Override
    public Collection values() {
        return this.getCollection().getValues();
    }
}

