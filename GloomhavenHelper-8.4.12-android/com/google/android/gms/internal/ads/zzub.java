package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "AdDataParcelCreator")
public final class zzub extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 1)
    public final String zzcci;
    @Field(id = 2)
    public final String zzccj;

    static {
        zzub.CREATOR = new zzua();
    }

    @Constructor
    public zzub(@Param(id = 1) String s, @Param(id = 2) String s1) {
        this.zzcci = s;
        this.zzccj = s1;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 1, this.zzcci, false);
        SafeParcelWriter.writeString(parcel0, 2, this.zzccj, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

