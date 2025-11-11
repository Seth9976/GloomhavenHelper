package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "MediationConfigurationParcelCreator")
public final class zzahj extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 2)
    public final Bundle extras;
    @Field(id = 1)
    public final String zzczk;

    static {
        zzahj.CREATOR = new zzahi();
    }

    @Constructor
    public zzahj(@Param(id = 1) String s, @Param(id = 2) Bundle bundle0) {
        this.zzczk = s;
        this.extras = bundle0;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 1, this.zzczk, false);
        SafeParcelWriter.writeBundle(parcel0, 2, this.extras, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

