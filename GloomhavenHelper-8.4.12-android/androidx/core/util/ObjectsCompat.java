package androidx.core.util;

import android.os.Build.VERSION;
import androidx.annotation.Nullable;
import java.util.Arrays;
import java.util.Objects;

public class ObjectsCompat {
    // 去混淆评级： 低(20)
    public static boolean equals(@Nullable Object object0, @Nullable Object object1) {
        return Build.VERSION.SDK_INT < 19 ? object0 == object1 || object0 != null && object0.equals(object1) : Objects.equals(object0, object1);
    }

    public static int hash(@Nullable Object[] arr_object) {
        return Build.VERSION.SDK_INT < 19 ? Arrays.hashCode(arr_object) : Objects.hash(arr_object);
    }

    public static int hashCode(@Nullable Object object0) {
        return object0 == null ? 0 : object0.hashCode();
    }
}

