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

@Class(creator = "GassEventParcelCreator")
public final class zzdjq extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @VersionField(id = 1)
    private final int versionCode;
    @Field(id = 2)
    private final byte[] zzgxt;

    static {
        zzdjq.CREATOR = new zzdjt();
    }

    @Constructor
    zzdjq(@Param(id = 1) int v, @Param(id = 2) byte[] arr_b) {
        this.versionCode = v;
        this.zzgxt = arr_b;
    }

    public zzdjq(byte[] arr_b) {
        this(1, arr_b);
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.versionCode);
        SafeParcelWriter.writeByteArray(parcel0, 2, this.zzgxt, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

