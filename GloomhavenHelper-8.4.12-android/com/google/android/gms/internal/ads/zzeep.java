package com.google.android.gms.internal.ads;

public final class zzeep {
    public static Object checkNotNull(Object object0) {
        if(object0 == null) {
            throw new NullPointerException();
        }
        return object0;
    }

    public static Object zza(Object object0, String s) [...] // Inlined contents

    public static void zza(Object object0, Class class0) {
        if(object0 == null) {
            throw new IllegalStateException(class0.getCanonicalName() + " must be set");
        }
    }
}

