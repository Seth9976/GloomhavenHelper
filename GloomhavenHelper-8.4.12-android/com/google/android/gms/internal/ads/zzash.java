package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "RewardedVideoAdRequestParcelCreator")
@Reserved({1})
public final class zzash extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 3)
    public final String zzbri;
    @Field(id = 2)
    public final zzuh zzdjt;

    static {
        zzash.CREATOR = new zzask();
    }

    @Constructor
    public zzash(@Param(id = 2) zzuh zzuh0, @Param(id = 3) String s) {
        this.zzdjt = zzuh0;
        this.zzbri = s;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeParcelable(parcel0, 2, this.zzdjt, v, false);
        SafeParcelWriter.writeString(parcel0, 3, this.zzbri, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

