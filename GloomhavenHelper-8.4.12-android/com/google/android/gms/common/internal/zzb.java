package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "ConnectionInfoCreator")
public final class zzb extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 1)
    Bundle zzda;
    @Field(id = 2)
    Feature[] zzdb;

    static {
        zzb.CREATOR = new zzc();
    }

    public zzb() {
    }

    @Constructor
    zzb(@Param(id = 1) Bundle bundle0, @Param(id = 2) Feature[] arr_feature) {
        this.zzda = bundle0;
        this.zzdb = arr_feature;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeBundle(parcel0, 1, this.zzda, false);
        SafeParcelWriter.writeTypedArray(parcel0, 2, this.zzdb, v, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

