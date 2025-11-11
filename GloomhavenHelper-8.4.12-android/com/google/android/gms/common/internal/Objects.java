package com.google.android.gms.common.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@KeepForSdk
public final class Objects {
    @KeepForSdk
    public static final class ToStringHelper {
        private final List zzer;
        private final Object zzes;

        private ToStringHelper(Object object0) {
            this.zzes = Preconditions.checkNotNull(object0);
            this.zzer = new ArrayList();
        }

        ToStringHelper(Object object0, zzq zzq0) {
            this(object0);
        }

        @KeepForSdk
        public final ToStringHelper add(String s, @Nullable Object object0) {
            String s1 = (String)Preconditions.checkNotNull(s);
            this.zzer.add(s1 + "=" + object0);
            return this;
        }

        @Override
        @KeepForSdk
        public final String toString() {
            StringBuilder stringBuilder0 = new StringBuilder(100);
            stringBuilder0.append(this.zzes.getClass().getSimpleName());
            stringBuilder0.append('{');
            int v = this.zzer.size();
            for(int v1 = 0; v1 < v; ++v1) {
                stringBuilder0.append(((String)this.zzer.get(v1)));
                if(v1 < v - 1) {
                    stringBuilder0.append(", ");
                }
            }
            stringBuilder0.append('}');
            return stringBuilder0.toString();
        }
    }

    private Objects() {
        throw new AssertionError("Uninstantiable");
    }

    // 去混淆评级： 低(20)
    @KeepForSdk
    public static boolean equal(@Nullable Object object0, @Nullable Object object1) {
        return object0 == object1 || object0 != null && object0.equals(object1);
    }

    @KeepForSdk
    public static int hashCode(Object[] arr_object) {
        return Arrays.hashCode(arr_object);
    }

    @KeepForSdk
    public static ToStringHelper toStringHelper(Object object0) {
        return new ToStringHelper(object0, null);
    }
}

