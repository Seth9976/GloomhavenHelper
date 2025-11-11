package com.google.android.gms.internal.play_billing;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;

public final class zzg {
    private static final ClassLoader zza;

    static {
        zzg.zza = zzg.class.getClassLoader();
    }

    public static Parcelable zza(Parcel parcel0, Parcelable.Creator parcelable$Creator0) {
        return parcel0.readInt() == 0 ? null : ((Parcelable)parcelable$Creator0.createFromParcel(parcel0));
    }

    public static void zzb(Parcel parcel0, Parcelable parcelable0) {
        parcel0.writeInt(1);
        parcelable0.writeToParcel(parcel0, 0);
    }
}

