package androidx.arch.core.internal;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import java.util.HashMap;
import java.util.Map.Entry;

@RestrictTo({Scope.LIBRARY_GROUP})
public class FastSafeIterableMap extends SafeIterableMap {
    private HashMap mHashMap;

    public FastSafeIterableMap() {
        this.mHashMap = new HashMap();
    }

    public Map.Entry ceil(Object object0) {
        return this.contains(object0) ? ((Entry)this.mHashMap.get(object0)).mPrevious : null;
    }

    public boolean contains(Object object0) {
        return this.mHashMap.containsKey(object0);
    }

    @Override  // androidx.arch.core.internal.SafeIterableMap
    protected Entry get(Object object0) {
        return (Entry)this.mHashMap.get(object0);
    }

    @Override  // androidx.arch.core.internal.SafeIterableMap
    public Object putIfAbsent(@NonNull Object object0, @NonNull Object object1) {
        Entry safeIterableMap$Entry0 = this.get(object0);
        if(safeIterableMap$Entry0 != null) {
            return safeIterableMap$Entry0.mValue;
        }
        this.mHashMap.put(object0, this.put(object0, object1));
        return null;
    }

    @Override  // androidx.arch.core.internal.SafeIterableMap
    public Object remove(@NonNull Object object0) {
        Object object1 = super.remove(object0);
        this.mHashMap.remove(object0);
        return object1;
    }
}

