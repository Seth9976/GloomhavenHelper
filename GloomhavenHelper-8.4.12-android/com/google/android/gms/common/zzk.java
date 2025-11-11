package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.Nullable;

@Class(creator = "GoogleCertificatesQueryCreator")
public final class zzk extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(getter = "getAllowTestKeys", id = 3)
    private final boolean zzaa;
    @Field(defaultValue = "false", getter = "getForbidTestKeys", id = 4)
    private final boolean zzab;
    @Field(getter = "getCallingPackage", id = 1)
    private final String zzy;
    @Field(getter = "getCallingCertificateBinder", id = 2, type = "android.os.IBinder")
    @Nullable
    private final zze zzz;

    static {
        zzk.CREATOR = new zzl();
    }

    @Constructor
    zzk(@Param(id = 1) String s, @Param(id = 2) @Nullable IBinder iBinder0, @Param(id = 3) boolean z, @Param(id = 4) boolean z1) {
        this.zzy = s;
        this.zzz = zzk.zza(iBinder0);
        this.zzaa = z;
        this.zzab = z1;
    }

    zzk(String s, @Nullable zze zze0, boolean z, boolean z1) {
        this.zzy = s;
        this.zzz = zze0;
        this.zzaa = z;
        this.zzab = z1;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        IBinder iBinder0;
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 1, this.zzy, false);
        zze zze0 = this.zzz;
        if(zze0 == null) {
            Log.w("GoogleCertificatesQuery", "certificate binder is null");
            iBinder0 = null;
        }
        else {
            iBinder0 = zze0;
        }
        SafeParcelWriter.writeIBinder(parcel0, 2, iBinder0, false);
        SafeParcelWriter.writeBoolean(parcel0, 3, this.zzaa);
        SafeParcelWriter.writeBoolean(parcel0, 4, this.zzab);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    @Nullable
    private static zze zza(@Nullable IBinder iBinder0) {
        IObjectWrapper iObjectWrapper0;
        if(iBinder0 == null) {
            return null;
        }
        try {
            iObjectWrapper0 = zzj.zzb(iBinder0).zzb();
        }
        catch(RemoteException remoteException0) {
            Log.e("GoogleCertificatesQuery", "Could not unwrap certificate", remoteException0);
            return null;
        }
        byte[] arr_b = iObjectWrapper0 == null ? null : ((byte[])ObjectWrapper.unwrap(iObjectWrapper0));
        if(arr_b != null) {
            return new zzf(arr_b);
        }
        Log.e("GoogleCertificatesQuery", "Could not unwrap certificate");
        return null;
    }
}

