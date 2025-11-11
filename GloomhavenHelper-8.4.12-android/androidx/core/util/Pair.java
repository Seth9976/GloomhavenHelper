package androidx.core.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Pair {
    @Nullable
    public final Object first;
    @Nullable
    public final Object second;

    public Pair(@Nullable Object object0, @Nullable Object object1) {
        this.first = object0;
        this.second = object1;
    }

    @NonNull
    public static Pair create(@Nullable Object object0, @Nullable Object object1) {
        return new Pair(object0, object1);
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof Pair ? ObjectsCompat.equals(((Pair)object0).first, this.first) && ObjectsCompat.equals(((Pair)object0).second, this.second) : false;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = this.first == null ? 0 : this.first.hashCode();
        Object object0 = this.second;
        if(object0 != null) {
            v = object0.hashCode();
        }
        return v1 ^ v;
    }

    @Override
    public String toString() {
        return "Pair{" + this.first + " " + this.second + "}";
    }
}

