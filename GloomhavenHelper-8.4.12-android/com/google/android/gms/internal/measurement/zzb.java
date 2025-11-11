package com.google.android.gms.internal.measurement;

import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import java.util.HashMap;

public class zzb {
    private static final ClassLoader zza;

    static {
        zzb.zza = zzb.class.getClassLoader();
    }

    public static Parcelable zza(Parcel parcel0, Parcelable.Creator parcelable$Creator0) {
        return parcel0.readInt() == 0 ? null : ((Parcelable)parcelable$Creator0.createFromParcel(parcel0));
    }

    public static void zza(Parcel parcel0, IInterface iInterface0) {
        if(iInterface0 == null) {
            parcel0.writeStrongBinder(null);
            return;
        }
        parcel0.writeStrongBinder(iInterface0.asBinder());
    }

    public static void zza(Parcel parcel0, Parcelable parcelable0) {
        if(parcelable0 == null) {
            parcel0.writeInt(0);
            return;
        }
        parcel0.writeInt(1);
        parcelable0.writeToParcel(parcel0, 0);
    }

    public static void zza(Parcel parcel0, boolean z) {
        parcel0.writeInt(((int)z));
    }

    public static boolean zza(Parcel parcel0) {
        return parcel0.readInt() != 0;
    }

    public static HashMap zzb(Parcel parcel0) {
        return parcel0.readHashMap(zzb.zza);
    }
}

