package androidx.collection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class ArraySet implements Collection, Set {
    private static final int BASE_SIZE = 4;
    private static final int CACHE_SIZE = 10;
    private static final boolean DEBUG = false;
    private static final int[] INT = null;
    private static final Object[] OBJECT = null;
    private static final String TAG = "ArraySet";
    Object[] mArray;
    private MapCollections mCollections;
    private int[] mHashes;
    int mSize;
    @Nullable
    private static Object[] sBaseCache;
    private static int sBaseCacheSize;
    @Nullable
    private static Object[] sTwiceBaseCache;
    private static int sTwiceBaseCacheSize;

    static {
        ArraySet.INT = new int[0];
        ArraySet.OBJECT = new Object[0];
    }

    public ArraySet() {
        this(0);
    }

    public ArraySet(int v) {
        if(v == 0) {
            this.mHashes = ArraySet.INT;
            this.mArray = ArraySet.OBJECT;
        }
        else {
            this.allocArrays(v);
        }
        this.mSize = 0;
    }

    public ArraySet(@Nullable ArraySet arraySet0) {
        if(arraySet0 != null) {
            this.addAll(arraySet0);
        }
    }

    public ArraySet(@Nullable Collection collection0) {
        if(collection0 != null) {
            this.addAll(collection0);
        }
    }

    @Override
    public boolean add(@Nullable Object object0) {
        int v1;
        int v;
        if(object0 == null) {
            v = this.indexOfNull();
            v1 = 0;
        }
        else {
            int v2 = object0.hashCode();
            v1 = v2;
            v = this.indexOf(object0, v2);
        }
        if(v >= 0) {
            return false;
        }
        int v3 = 4;
        int v4 = this.mSize;
        if(v4 >= this.mHashes.length) {
            if(v4 >= 8) {
                v3 = (v4 >> 1) + v4;
            }
            else if(v4 >= 4) {
                v3 = 8;
            }
            int[] arr_v = this.mHashes;
            Object[] arr_object = this.mArray;
            this.allocArrays(v3);
            int[] arr_v1 = this.mHashes;
            if(arr_v1.length > 0) {
                System.arraycopy(arr_v, 0, arr_v1, 0, arr_v.length);
                System.arraycopy(arr_object, 0, this.mArray, 0, arr_object.length);
            }
            ArraySet.freeArrays(arr_v, arr_object, this.mSize);
        }
        int v5 = this.mSize;
        if(~v < v5) {
            System.arraycopy(this.mHashes, ~v, this.mHashes, -v, v5 - ~v);
            System.arraycopy(this.mArray, ~v, this.mArray, -v, this.mSize - ~v);
        }
        this.mHashes[~v] = v1;
        this.mArray[~v] = object0;
        ++this.mSize;
        return true;
    }

    public void addAll(@NonNull ArraySet arraySet0) {
        int v = arraySet0.mSize;
        this.ensureCapacity(this.mSize + v);
        if(this.mSize != 0) {
            for(int v1 = 0; v1 < v; ++v1) {
                this.add(arraySet0.valueAt(v1));
            }
        }
        else if(v > 0) {
            System.arraycopy(arraySet0.mHashes, 0, this.mHashes, 0, v);
            System.arraycopy(arraySet0.mArray, 0, this.mArray, 0, v);
            this.mSize = v;
        }
    }

    @Override
    public boolean addAll(@NonNull Collection collection0) {
        this.ensureCapacity(this.mSize + collection0.size());
        boolean z = false;
        for(Object object0: collection0) {
            z |= this.add(object0);
        }
        return z;
    }

    private void allocArrays(int v) {
        switch(v) {
            case 4: {
                Class class0 = ArraySet.class;
                synchronized(class0) {
                    if(ArraySet.sBaseCache != null) {
                        Object[] arr_object = ArraySet.sBaseCache;
                        this.mArray = arr_object;
                        ArraySet.sBaseCache = (Object[])arr_object[0];
                        this.mHashes = (int[])arr_object[1];
                        arr_object[1] = null;
                        arr_object[0] = null;
                        --ArraySet.sBaseCacheSize;
                        return;
                    }
                }
                break;
            }
            case 8: {
                Class class1 = ArraySet.class;
                synchronized(class1) {
                    if(ArraySet.sTwiceBaseCache != null) {
                        Object[] arr_object1 = ArraySet.sTwiceBaseCache;
                        this.mArray = arr_object1;
                        ArraySet.sTwiceBaseCache = (Object[])arr_object1[0];
                        this.mHashes = (int[])arr_object1[1];
                        arr_object1[1] = null;
                        arr_object1[0] = null;
                        --ArraySet.sTwiceBaseCacheSize;
                        return;
                    }
                }
            }
        }
        this.mHashes = new int[v];
        this.mArray = new Object[v];
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void append(Object object0) {
        int v = this.mSize;
        int v1 = object0 == null ? 0 : object0.hashCode();
        int[] arr_v = this.mHashes;
        if(v >= arr_v.length) {
            throw new IllegalStateException("Array is full");
        }
        if(v > 0 && arr_v[v - 1] > v1) {
            this.add(object0);
            return;
        }
        this.mSize = v + 1;
        this.mHashes[v] = v1;
        this.mArray[v] = object0;
    }

    @Override
    public void clear() {
        int v = this.mSize;
        if(v != 0) {
            ArraySet.freeArrays(this.mHashes, this.mArray, v);
            this.mHashes = ArraySet.INT;
            this.mArray = ArraySet.OBJECT;
            this.mSize = 0;
        }
    }

    @Override
    public boolean contains(@Nullable Object object0) {
        return this.indexOf(object0) >= 0;
    }

    @Override
    public boolean containsAll(@NonNull Collection collection0) {
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

    public void ensureCapacity(int v) {
        int[] arr_v = this.mHashes;
        if(arr_v.length < v) {
            Object[] arr_object = this.mArray;
            this.allocArrays(v);
            int v1 = this.mSize;
            if(v1 > 0) {
                System.arraycopy(arr_v, 0, this.mHashes, 0, v1);
                System.arraycopy(arr_object, 0, this.mArray, 0, this.mSize);
            }
            ArraySet.freeArrays(arr_v, arr_object, this.mSize);
        }
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(object0 instanceof Set) {
            Set set0 = (Set)object0;
            if(this.size() != set0.size()) {
                return false;
            }
            for(int v = 0; true; ++v) {
                try {
                    if(v >= this.mSize) {
                        return true;
                    }
                    if(!set0.contains(this.valueAt(v))) {
                        return false;
                    }
                    continue;
                }
                catch(NullPointerException unused_ex) {
                }
                catch(ClassCastException unused_ex) {
                }
                return false;
            }
        }
        return false;
    }

    private static void freeArrays(int[] arr_v, Object[] arr_object, int v) {
        switch(arr_v.length) {
            case 4: {
                Class class0 = ArraySet.class;
                synchronized(class0) {
                    if(ArraySet.sBaseCacheSize < 10) {
                        arr_object[0] = ArraySet.sBaseCache;
                        arr_object[1] = arr_v;
                        for(int v2 = v - 1; v2 >= 2; --v2) {
                            arr_object[v2] = null;
                        }
                        ArraySet.sBaseCache = arr_object;
                        ++ArraySet.sBaseCacheSize;
                    }
                }
                return;
            }
            case 8: {
                Class class1 = ArraySet.class;
                synchronized(class1) {
                    if(ArraySet.sTwiceBaseCacheSize < 10) {
                        arr_object[0] = ArraySet.sTwiceBaseCache;
                        arr_object[1] = arr_v;
                        for(int v4 = v - 1; v4 >= 2; --v4) {
                            arr_object[v4] = null;
                        }
                        ArraySet.sTwiceBaseCache = arr_object;
                        ++ArraySet.sTwiceBaseCacheSize;
                    }
                }
            }
        }
    }

    private MapCollections getCollection() {
        if(this.mCollections == null) {
            this.mCollections = new MapCollections() {
                @Override  // androidx.collection.MapCollections
                protected void colClear() {
                    ArraySet.this.clear();
                }

                @Override  // androidx.collection.MapCollections
                protected Object colGetEntry(int v, int v1) {
                    return ArraySet.this.mArray[v];
                }

                @Override  // androidx.collection.MapCollections
                protected Map colGetMap() {
                    throw new UnsupportedOperationException("not a map");
                }

                @Override  // androidx.collection.MapCollections
                protected int colGetSize() {
                    return ArraySet.this.mSize;
                }

                @Override  // androidx.collection.MapCollections
                protected int colIndexOfKey(Object object0) {
                    return ArraySet.this.indexOf(object0);
                }

                @Override  // androidx.collection.MapCollections
                protected int colIndexOfValue(Object object0) {
                    return ArraySet.this.indexOf(object0);
                }

                @Override  // androidx.collection.MapCollections
                protected void colPut(Object object0, Object object1) {
                    ArraySet.this.add(object0);
                }

                @Override  // androidx.collection.MapCollections
                protected void colRemoveAt(int v) {
                    ArraySet.this.removeAt(v);
                }

                @Override  // androidx.collection.MapCollections
                protected Object colSetValue(int v, Object object0) {
                    throw new UnsupportedOperationException("not a map");
                }
            };
        }
        return this.mCollections;
    }

    @Override
    public int hashCode() {
        int[] arr_v = this.mHashes;
        int v = this.mSize;
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            v2 += arr_v[v1];
        }
        return v2;
    }

    private int indexOf(Object object0, int v) {
        int v1 = this.mSize;
        if(v1 == 0) {
            return -1;
        }
        int v2 = ContainerHelpers.binarySearch(this.mHashes, v1, v);
        if(v2 < 0) {
            return v2;
        }
        if(object0.equals(this.mArray[v2])) {
            return v2;
        }
        int v3;
        for(v3 = v2 + 1; v3 < v1 && this.mHashes[v3] == v; ++v3) {
            if(object0.equals(this.mArray[v3])) {
                return v3;
            }
        }
        for(int v4 = v2 - 1; v4 >= 0 && this.mHashes[v4] == v; --v4) {
            if(object0.equals(this.mArray[v4])) {
                return v4;
            }
        }
        return ~v3;
    }

    public int indexOf(@Nullable Object object0) {
        return object0 == null ? this.indexOfNull() : this.indexOf(object0, object0.hashCode());
    }

    private int indexOfNull() {
        int v = this.mSize;
        if(v == 0) {
            return -1;
        }
        int v1 = ContainerHelpers.binarySearch(this.mHashes, v, 0);
        if(v1 < 0) {
            return v1;
        }
        if(this.mArray[v1] == null) {
            return v1;
        }
        int v2;
        for(v2 = v1 + 1; v2 < v && this.mHashes[v2] == 0; ++v2) {
            if(this.mArray[v2] == null) {
                return v2;
            }
        }
        for(int v3 = v1 - 1; v3 >= 0 && this.mHashes[v3] == 0; --v3) {
            if(this.mArray[v3] == null) {
                return v3;
            }
        }
        return ~v2;
    }

    @Override
    public boolean isEmpty() {
        return this.mSize <= 0;
    }

    @Override
    public Iterator iterator() {
        return this.getCollection().getKeySet().iterator();
    }

    @Override
    public boolean remove(@Nullable Object object0) {
        int v = this.indexOf(object0);
        if(v >= 0) {
            this.removeAt(v);
            return true;
        }
        return false;
    }

    public boolean removeAll(@NonNull ArraySet arraySet0) {
        int v = arraySet0.mSize;
        int v1 = this.mSize;
        for(int v2 = 0; v2 < v; ++v2) {
            this.remove(arraySet0.valueAt(v2));
        }
        return v1 != this.mSize;
    }

    @Override
    public boolean removeAll(@NonNull Collection collection0) {
        boolean z = false;
        for(Object object0: collection0) {
            z |= this.remove(object0);
        }
        return z;
    }

    public Object removeAt(int v) {
        int v1 = 8;
        Object[] arr_object = this.mArray;
        Object object0 = arr_object[v];
        int v2 = this.mSize;
        if(v2 <= 1) {
            ArraySet.freeArrays(this.mHashes, arr_object, v2);
            this.mHashes = ArraySet.INT;
            this.mArray = ArraySet.OBJECT;
            this.mSize = 0;
            return object0;
        }
        if(this.mHashes.length <= 8 || v2 >= this.mHashes.length / 3) {
            --this.mSize;
            int v4 = this.mSize;
            if(v < v4) {
                System.arraycopy(this.mHashes, v + 1, this.mHashes, v, v4 - v);
                System.arraycopy(this.mArray, v + 1, this.mArray, v, this.mSize - v);
            }
            this.mArray[this.mSize] = null;
        }
        else {
            if(v2 > 8) {
                v1 = v2 + (v2 >> 1);
            }
            int[] arr_v = this.mHashes;
            Object[] arr_object1 = this.mArray;
            this.allocArrays(v1);
            --this.mSize;
            if(v > 0) {
                System.arraycopy(arr_v, 0, this.mHashes, 0, v);
                System.arraycopy(arr_object1, 0, this.mArray, 0, v);
            }
            int v3 = this.mSize;
            if(v < v3) {
                System.arraycopy(arr_v, v + 1, this.mHashes, v, v3 - v);
                System.arraycopy(arr_object1, v + 1, this.mArray, v, this.mSize - v);
                return object0;
            }
        }
        return object0;
    }

    @Override
    public boolean retainAll(@NonNull Collection collection0) {
        int v = this.mSize - 1;
        boolean z = false;
        while(v >= 0) {
            if(!collection0.contains(this.mArray[v])) {
                this.removeAt(v);
                z = true;
            }
            --v;
        }
        return z;
    }

    @Override
    public int size() {
        return this.mSize;
    }

    @Override
    @NonNull
    public Object[] toArray() {
        int v = this.mSize;
        Object[] arr_object = new Object[v];
        System.arraycopy(this.mArray, 0, arr_object, 0, v);
        return arr_object;
    }

    @Override
    @NonNull
    public Object[] toArray(@NonNull Object[] arr_object) {
        if(arr_object.length < this.mSize) {
            arr_object = (Object[])Array.newInstance(arr_object.getClass().getComponentType(), this.mSize);
        }
        System.arraycopy(this.mArray, 0, arr_object, 0, this.mSize);
        int v = this.mSize;
        if(arr_object.length > v) {
            arr_object[v] = null;
        }
        return arr_object;
    }

    @Override
    public String toString() {
        if(this.isEmpty()) {
            return "{}";
        }
        StringBuilder stringBuilder0 = new StringBuilder(this.mSize * 14);
        stringBuilder0.append('{');
        for(int v = 0; v < this.mSize; ++v) {
            if(v > 0) {
                stringBuilder0.append(", ");
            }
            Object object0 = this.valueAt(v);
            if(object0 == this) {
                stringBuilder0.append("(this Set)");
            }
            else {
                stringBuilder0.append(object0);
            }
        }
        stringBuilder0.append('}');
        return stringBuilder0.toString();
    }

    @Nullable
    public Object valueAt(int v) {
        return this.mArray[v];
    }
}

