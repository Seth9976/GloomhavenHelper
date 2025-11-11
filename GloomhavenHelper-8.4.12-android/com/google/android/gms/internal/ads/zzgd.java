package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import java.util.ArrayList;

public class zzgd {
    private static final ClassLoader zzaba;

    static {
        zzgd.zzaba = zzgd.class.getClassLoader();
    }

    public static void writeBoolean(Parcel parcel0, boolean z) {
        parcel0.writeInt(((int)z));
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

    public static boolean zza(Parcel parcel0) {
        return parcel0.readInt() != 0;
    }

    public static ArrayList zzb(Parcel parcel0) {
        return parcel0.readArrayList(zzgd.zzaba);
    }

    public static void zzb(Parcel parcel0, Parcelable parcelable0) {
        if(parcelable0 == null) {
            parcel0.writeInt(0);
            return;
        }
        parcel0.writeInt(1);
        parcelable0.writeToParcel(parcel0, 1);
    }
}

