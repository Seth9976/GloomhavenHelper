package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.List;

public final class zzlh implements Parcelable {
    public interface zza extends Parcelable {
    }

    public static final Parcelable.Creator CREATOR;
    private final zza[] zzazx;

    static {
        zzlh.CREATOR = new zzlg();
    }

    zzlh(Parcel parcel0) {
        this.zzazx = new zza[parcel0.readInt()];
        for(int v = 0; true; ++v) {
            zza[] arr_zzlh$zza = this.zzazx;
            if(v >= arr_zzlh$zza.length) {
                break;
            }
            arr_zzlh$zza[v] = (zza)parcel0.readParcelable(zza.class.getClassLoader());
        }
    }

    public zzlh(List list0) {
        this.zzazx = new zza[list0.size()];
        list0.toArray(this.zzazx);
    }

    @Override  // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 == null || this.getClass() != object0.getClass() ? false : Arrays.equals(this.zzazx, ((zzlh)object0).zzazx);
    }

    @Override
    public final int hashCode() {
        return Arrays.hashCode(this.zzazx);
    }

    public final int length() {
        return this.zzazx.length;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeInt(this.zzazx.length);
        zza[] arr_zzlh$zza = this.zzazx;
        for(int v1 = 0; v1 < arr_zzlh$zza.length; ++v1) {
            parcel0.writeParcelable(arr_zzlh$zza[v1], 0);
        }
    }

    public final zza zzas(int v) {
        return this.zzazx[v];
    }
}

