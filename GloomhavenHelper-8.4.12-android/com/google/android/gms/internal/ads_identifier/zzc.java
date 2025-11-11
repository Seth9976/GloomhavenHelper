package com.google.android.gms.internal.ads_identifier;

import android.os.Parcel;

public class zzc {
    private static final ClassLoader zzd;

    static {
        zzc.zzd = zzc.class.getClassLoader();
    }

    public static void zza(Parcel parcel0, boolean z) {
        parcel0.writeInt(1);
    }

    public static boolean zza(Parcel parcel0) {
        return parcel0.readInt() != 0;
    }
}

