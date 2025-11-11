package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.Arrays;

@Class(creator = "ProgramResponseCreator")
public final class zzdke extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 3)
    private final int status;
    @VersionField(id = 1)
    private final int versionCode;
    @Field(id = 2)
    public final byte[] zzgye;

    static {
        zzdke.CREATOR = new zzdkh();
    }

    @Constructor
    zzdke(@Param(id = 1) int v, @Param(id = 2) byte[] arr_b, @Param(id = 3) int v1) {
        this.versionCode = v;
        this.zzgye = arr_b == null ? null : Arrays.copyOf(arr_b, arr_b.length);
        this.status = v1;
    }

    public zzdke(byte[] arr_b, int v) {
        this(1, null, 1);
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.versionCode);
        SafeParcelWriter.writeByteArray(parcel0, 2, this.zzgye, false);
        SafeParcelWriter.writeInt(parcel0, 3, this.status);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

