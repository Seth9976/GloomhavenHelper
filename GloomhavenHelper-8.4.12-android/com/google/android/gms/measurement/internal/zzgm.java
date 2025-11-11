package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import androidx.annotation.NonNull;

public final class zzgm {
    public static Object zza(@NonNull Bundle bundle0, String s, Class class0, Object object0) {
        Object object1 = bundle0.get(s);
        if(object1 == null) {
            return object0;
        }
        if(!class0.isAssignableFrom(object1.getClass())) {
            throw new IllegalStateException(String.format("Invalid conditional user property field type. \'%s\' expected [%s] but was [%s]", s, class0.getCanonicalName(), object1.getClass().getCanonicalName()));
        }
        return object1;
    }

    public static void zza(@NonNull Bundle bundle0, @NonNull Object object0) {
        if(object0 instanceof Double) {
            bundle0.putDouble("value", ((double)(((Double)object0))));
            return;
        }
        if(object0 instanceof Long) {
            bundle0.putLong("value", ((long)(((Long)object0))));
            return;
        }
        bundle0.putString("value", object0.toString());
    }
}

