package androidx.collection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SparseArrayCompat implements Cloneable {
    private static final Object DELETED;
    private boolean mGarbage;
    private int[] mKeys;
    private int mSize;
    private Object[] mValues;

    static {
        SparseArrayCompat.DELETED = new Object();
    }

    public SparseArrayCompat() {
        this(10);
    }

    public SparseArrayCompat(int v) {
        this.mGarbage = false;
        if(v == 0) {
            this.mKeys = ContainerHelpers.EMPTY_INTS;
            this.mValues = ContainerHelpers.EMPTY_OBJECTS;
        }
        else {
            int v1 = ContainerHelpers.idealIntArraySize(v);
            this.mKeys = new int[v1];
            this.mValues = new Object[v1];
        }
        this.mSize = 0;
    }

    public void append(int v, Object object0) {
        if(this.mSize != 0 && v <= this.mKeys[this.mSize - 1]) {
            this.put(v, object0);
            return;
        }
        if(this.mGarbage && this.mSize >= this.mKeys.length) {
            this.gc();
        }
        int v1 = this.mSize;
        if(v1 >= this.mKeys.length) {
            int v2 = ContainerHelpers.idealIntArraySize(v1 + 1);
            int[] arr_v = new int[v2];
            Object[] arr_object = new Object[v2];
            System.arraycopy(this.mKeys, 0, arr_v, 0, this.mKeys.length);
            System.arraycopy(this.mValues, 0, arr_object, 0, this.mValues.length);
            this.mKeys = arr_v;
            this.mValues = arr_object;
        }
        this.mKeys[v1] = v;
        this.mValues[v1] = object0;
        this.mSize = v1 + 1;
    }

    public void clear() {
        int v = this.mSize;
        Object[] arr_object = this.mValues;
        for(int v1 = 0; v1 < v; ++v1) {
            arr_object[v1] = null;
        }
        this.mSize = 0;
        this.mGarbage = false;
    }

    public SparseArrayCompat clone() {
        try {
            SparseArrayCompat sparseArrayCompat0 = (SparseArrayCompat)super.clone();
            sparseArrayCompat0.mKeys = (int[])this.mKeys.clone();
            sparseArrayCompat0.mValues = (Object[])this.mValues.clone();
            return sparseArrayCompat0;
        }
        catch(CloneNotSupportedException cloneNotSupportedException0) {
            throw new AssertionError(cloneNotSupportedException0);
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    public boolean containsKey(int v) {
        return this.indexOfKey(v) >= 0;
    }

    public boolean containsValue(Object object0) {
        return this.indexOfValue(object0) >= 0;
    }

    public void delete(int v) {
        int v1 = ContainerHelpers.binarySearch(this.mKeys, this.mSize, v);
        if(v1 >= 0) {
            Object[] arr_object = this.mValues;
            Object object0 = SparseArrayCompat.DELETED;
            if(arr_object[v1] != object0) {
                arr_object[v1] = object0;
                this.mGarbage = true;
            }
        }
    }

    private void gc() {
        int v = this.mSize;
        int[] arr_v = this.mKeys;
        Object[] arr_object = this.mValues;
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            Object object0 = arr_object[v1];
            if(object0 != SparseArrayCompat.DELETED) {
                if(v1 != v2) {
                    arr_v[v2] = arr_v[v1];
                    arr_object[v2] = object0;
                    arr_object[v1] = null;
                }
                ++v2;
            }
        }
        this.mGarbage = false;
        this.mSize = v2;
    }

    @Nullable
    public Object get(int v) {
        return this.get(v, null);
    }

    public Object get(int v, Object object0) {
        int v1 = ContainerHelpers.binarySearch(this.mKeys, this.mSize, v);
        if(v1 >= 0) {
            return this.mValues[v1] == SparseArrayCompat.DELETED ? object0 : this.mValues[v1];
        }
        return object0;
    }

    public int indexOfKey(int v) {
        if(this.mGarbage) {
            this.gc();
        }
        return ContainerHelpers.binarySearch(this.mKeys, this.mSize, v);
    }

    public int indexOfValue(Object object0) {
        if(this.mGarbage) {
            this.gc();
        }
        for(int v = 0; v < this.mSize; ++v) {
            if(this.mValues[v] == object0) {
                return v;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int keyAt(int v) {
        if(this.mGarbage) {
            this.gc();
        }
        return this.mKeys[v];
    }

    public void put(int v, Object object0) {
        int v1 = ContainerHelpers.binarySearch(this.mKeys, this.mSize, v);
        if(v1 >= 0) {
            this.mValues[v1] = object0;
            return;
        }
        int v2 = ~v1;
        if(v2 < this.mSize) {
            Object[] arr_object = this.mValues;
            if(arr_object[v2] == SparseArrayCompat.DELETED) {
                this.mKeys[v2] = v;
                arr_object[v2] = object0;
                return;
            }
        }
        if(this.mGarbage && this.mSize >= this.mKeys.length) {
            this.gc();
            v2 = ~ContainerHelpers.binarySearch(this.mKeys, this.mSize, v);
        }
        int v3 = this.mSize;
        if(v3 >= this.mKeys.length) {
            int v4 = ContainerHelpers.idealIntArraySize(v3 + 1);
            int[] arr_v = new int[v4];
            Object[] arr_object1 = new Object[v4];
            System.arraycopy(this.mKeys, 0, arr_v, 0, this.mKeys.length);
            System.arraycopy(this.mValues, 0, arr_object1, 0, this.mValues.length);
            this.mKeys = arr_v;
            this.mValues = arr_object1;
        }
        int v5 = this.mSize;
        if(v5 - v2 != 0) {
            System.arraycopy(this.mKeys, v2, this.mKeys, v2 + 1, v5 - v2);
            System.arraycopy(this.mValues, v2, this.mValues, v2 + 1, this.mSize - v2);
        }
        this.mKeys[v2] = v;
        this.mValues[v2] = object0;
        ++this.mSize;
    }

    public void putAll(@NonNull SparseArrayCompat sparseArrayCompat0) {
        int v = sparseArrayCompat0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            this.put(sparseArrayCompat0.keyAt(v1), sparseArrayCompat0.valueAt(v1));
        }
    }

    public void remove(int v) {
        this.delete(v);
    }

    public void removeAt(int v) {
        Object[] arr_object = this.mValues;
        Object object0 = SparseArrayCompat.DELETED;
        if(arr_object[v] != object0) {
            arr_object[v] = object0;
            this.mGarbage = true;
        }
    }

    public void removeAtRange(int v, int v1) {
        int v2 = Math.min(this.mSize, v1 + v);
        while(v < v2) {
            this.removeAt(v);
            ++v;
        }
    }

    public void setValueAt(int v, Object object0) {
        if(this.mGarbage) {
            this.gc();
        }
        this.mValues[v] = object0;
    }

    public int size() {
        if(this.mGarbage) {
            this.gc();
        }
        return this.mSize;
    }

    @Override
    public String toString() {
        if(this.size() <= 0) {
            return "{}";
        }
        StringBuilder stringBuilder0 = new StringBuilder(this.mSize * 28);
        stringBuilder0.append('{');
        for(int v = 0; v < this.mSize; ++v) {
            if(v > 0) {
                stringBuilder0.append(", ");
            }
            stringBuilder0.append(this.keyAt(v));
            stringBuilder0.append('=');
            Object object0 = this.valueAt(v);
            if(object0 == this) {
                stringBuilder0.append("(this Map)");
            }
            else {
                stringBuilder0.append(object0);
            }
        }
        stringBuilder0.append('}');
        return stringBuilder0.toString();
    }

    public Object valueAt(int v) {
        if(this.mGarbage) {
            this.gc();
        }
        return this.mValues[v];
    }
}

