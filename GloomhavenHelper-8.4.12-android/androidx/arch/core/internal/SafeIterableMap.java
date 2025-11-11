package androidx.arch.core.internal;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.WeakHashMap;

@RestrictTo({Scope.LIBRARY_GROUP})
public class SafeIterableMap implements Iterable {
    static class AscendingIterator extends ListIterator {
        AscendingIterator(Entry safeIterableMap$Entry0, Entry safeIterableMap$Entry1) {
            super(safeIterableMap$Entry0, safeIterableMap$Entry1);
        }

        @Override  // androidx.arch.core.internal.SafeIterableMap$ListIterator
        Entry backward(Entry safeIterableMap$Entry0) {
            return safeIterableMap$Entry0.mPrevious;
        }

        @Override  // androidx.arch.core.internal.SafeIterableMap$ListIterator
        Entry forward(Entry safeIterableMap$Entry0) {
            return safeIterableMap$Entry0.mNext;
        }
    }

    static class DescendingIterator extends ListIterator {
        DescendingIterator(Entry safeIterableMap$Entry0, Entry safeIterableMap$Entry1) {
            super(safeIterableMap$Entry0, safeIterableMap$Entry1);
        }

        @Override  // androidx.arch.core.internal.SafeIterableMap$ListIterator
        Entry backward(Entry safeIterableMap$Entry0) {
            return safeIterableMap$Entry0.mNext;
        }

        @Override  // androidx.arch.core.internal.SafeIterableMap$ListIterator
        Entry forward(Entry safeIterableMap$Entry0) {
            return safeIterableMap$Entry0.mPrevious;
        }
    }

    static class Entry implements Map.Entry {
        @NonNull
        final Object mKey;
        Entry mNext;
        Entry mPrevious;
        @NonNull
        final Object mValue;

        Entry(@NonNull Object object0, @NonNull Object object1) {
            this.mKey = object0;
            this.mValue = object1;
        }

        @Override
        public boolean equals(Object object0) {
            if(object0 == this) {
                return true;
            }
            return object0 instanceof Entry ? this.mKey.equals(((Entry)object0).mKey) && this.mValue.equals(((Entry)object0).mValue) : false;
        }

        @Override
        @NonNull
        public Object getKey() {
            return this.mKey;
        }

        @Override
        @NonNull
        public Object getValue() {
            return this.mValue;
        }

        @Override
        public int hashCode() {
            return this.mKey.hashCode() ^ this.mValue.hashCode();
        }

        @Override
        public Object setValue(Object object0) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        @Override
        public String toString() {
            return this.mKey + "=" + this.mValue;
        }
    }

    class IteratorWithAdditions implements SupportRemove, Iterator {
        private boolean mBeforeStart;
        private Entry mCurrent;

        IteratorWithAdditions() {
            this.mBeforeStart = true;
        }

        // 去混淆评级： 低(30)
        @Override
        public boolean hasNext() {
            return this.mBeforeStart ? SafeIterableMap.this.mStart != null : this.mCurrent != null && this.mCurrent.mNext != null;
        }

        @Override
        public Object next() {
            return this.next();
        }

        public Map.Entry next() {
            if(this.mBeforeStart) {
                this.mBeforeStart = false;
                this.mCurrent = SafeIterableMap.this.mStart;
                return this.mCurrent;
            }
            this.mCurrent = this.mCurrent == null ? null : this.mCurrent.mNext;
            return this.mCurrent;
        }

        @Override  // androidx.arch.core.internal.SafeIterableMap$SupportRemove
        public void supportRemove(@NonNull Entry safeIterableMap$Entry0) {
            Entry safeIterableMap$Entry1 = this.mCurrent;
            if(safeIterableMap$Entry0 == safeIterableMap$Entry1) {
                this.mCurrent = safeIterableMap$Entry1.mPrevious;
                this.mBeforeStart = this.mCurrent == null;
            }
        }
    }

    static abstract class ListIterator implements SupportRemove, Iterator {
        Entry mExpectedEnd;
        Entry mNext;

        ListIterator(Entry safeIterableMap$Entry0, Entry safeIterableMap$Entry1) {
            this.mExpectedEnd = safeIterableMap$Entry1;
            this.mNext = safeIterableMap$Entry0;
        }

        abstract Entry backward(Entry arg1);

        abstract Entry forward(Entry arg1);

        @Override
        public boolean hasNext() {
            return this.mNext != null;
        }

        @Override
        public Object next() {
            return this.next();
        }

        public Map.Entry next() {
            Map.Entry map$Entry0 = this.mNext;
            this.mNext = this.nextNode();
            return map$Entry0;
        }

        private Entry nextNode() {
            return this.mNext == this.mExpectedEnd || this.mExpectedEnd == null ? null : this.forward(this.mNext);
        }

        @Override  // androidx.arch.core.internal.SafeIterableMap$SupportRemove
        public void supportRemove(@NonNull Entry safeIterableMap$Entry0) {
            if(this.mExpectedEnd == safeIterableMap$Entry0 && safeIterableMap$Entry0 == this.mNext) {
                this.mNext = null;
                this.mExpectedEnd = null;
            }
            Entry safeIterableMap$Entry1 = this.mExpectedEnd;
            if(safeIterableMap$Entry1 == safeIterableMap$Entry0) {
                this.mExpectedEnd = this.backward(safeIterableMap$Entry1);
            }
            if(this.mNext == safeIterableMap$Entry0) {
                this.mNext = this.nextNode();
            }
        }
    }

    interface SupportRemove {
        void supportRemove(@NonNull Entry arg1);
    }

    private Entry mEnd;
    private WeakHashMap mIterators;
    private int mSize;
    Entry mStart;

    public SafeIterableMap() {
        this.mIterators = new WeakHashMap();
        this.mSize = 0;
    }

    public Iterator descendingIterator() {
        Iterator iterator0 = new DescendingIterator(this.mEnd, this.mStart);
        this.mIterators.put(iterator0, Boolean.FALSE);
        return iterator0;
    }

    public Map.Entry eldest() {
        return this.mStart;
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof SafeIterableMap)) {
            return false;
        }
        if(this.size() != ((SafeIterableMap)object0).size()) {
            return false;
        }
        Iterator iterator0 = this.iterator();
        Iterator iterator1 = ((SafeIterableMap)object0).iterator();
        while(iterator0.hasNext() && iterator1.hasNext()) {
            Object object1 = iterator0.next();
            Map.Entry map$Entry0 = (Map.Entry)object1;
            Object object2 = iterator1.next();
            if(map$Entry0 == null && object2 != null || map$Entry0 != null && !map$Entry0.equals(object2)) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return !iterator0.hasNext() && !iterator1.hasNext();
    }

    protected Entry get(Object object0) {
        Entry safeIterableMap$Entry0;
        for(safeIterableMap$Entry0 = this.mStart; safeIterableMap$Entry0 != null && !safeIterableMap$Entry0.mKey.equals(object0); safeIterableMap$Entry0 = safeIterableMap$Entry0.mNext) {
        }
        return safeIterableMap$Entry0;
    }

    @Override
    public int hashCode() {
        int v = 0;
        for(Object object0: this) {
            v += ((Map.Entry)object0).hashCode();
        }
        return v;
    }

    @Override
    @NonNull
    public Iterator iterator() {
        Iterator iterator0 = new AscendingIterator(this.mStart, this.mEnd);
        this.mIterators.put(iterator0, Boolean.FALSE);
        return iterator0;
    }

    public IteratorWithAdditions iteratorWithAdditions() {
        IteratorWithAdditions safeIterableMap$IteratorWithAdditions0 = new IteratorWithAdditions(this);
        this.mIterators.put(safeIterableMap$IteratorWithAdditions0, Boolean.FALSE);
        return safeIterableMap$IteratorWithAdditions0;
    }

    public Map.Entry newest() {
        return this.mEnd;
    }

    protected Entry put(@NonNull Object object0, @NonNull Object object1) {
        Entry safeIterableMap$Entry0 = new Entry(object0, object1);
        ++this.mSize;
        Entry safeIterableMap$Entry1 = this.mEnd;
        if(safeIterableMap$Entry1 == null) {
            this.mStart = safeIterableMap$Entry0;
            this.mEnd = this.mStart;
            return safeIterableMap$Entry0;
        }
        safeIterableMap$Entry1.mNext = safeIterableMap$Entry0;
        safeIterableMap$Entry0.mPrevious = safeIterableMap$Entry1;
        this.mEnd = safeIterableMap$Entry0;
        return safeIterableMap$Entry0;
    }

    public Object putIfAbsent(@NonNull Object object0, @NonNull Object object1) {
        Entry safeIterableMap$Entry0 = this.get(object0);
        if(safeIterableMap$Entry0 != null) {
            return safeIterableMap$Entry0.mValue;
        }
        this.put(object0, object1);
        return null;
    }

    public Object remove(@NonNull Object object0) {
        Entry safeIterableMap$Entry0 = this.get(object0);
        if(safeIterableMap$Entry0 == null) {
            return null;
        }
        --this.mSize;
        if(!this.mIterators.isEmpty()) {
            for(Object object1: this.mIterators.keySet()) {
                ((SupportRemove)object1).supportRemove(safeIterableMap$Entry0);
            }
        }
        if(safeIterableMap$Entry0.mPrevious == null) {
            this.mStart = safeIterableMap$Entry0.mNext;
        }
        else {
            safeIterableMap$Entry0.mPrevious.mNext = safeIterableMap$Entry0.mNext;
        }
        if(safeIterableMap$Entry0.mNext == null) {
            this.mEnd = safeIterableMap$Entry0.mPrevious;
        }
        else {
            safeIterableMap$Entry0.mNext.mPrevious = safeIterableMap$Entry0.mPrevious;
        }
        safeIterableMap$Entry0.mNext = null;
        safeIterableMap$Entry0.mPrevious = null;
        return safeIterableMap$Entry0.mValue;
    }

    public int size() {
        return this.mSize;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("[");
        Iterator iterator0 = this.iterator();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            stringBuilder0.append(((Map.Entry)object0).toString());
            if(iterator0.hasNext()) {
                stringBuilder0.append(", ");
            }
        }
        stringBuilder0.append("]");
        return stringBuilder0.toString();
    }
}

