package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "RequestConfigurationParcelCreator")
public final class zzyw extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 1)
    private final int zzabv;
    @Field(id = 2)
    private final int zzabw;

    static {
        zzyw.CREATOR = new zzyz();
    }

    @Constructor
    public zzyw(@Param(id = 1) int v, @Param(id = 2) int v1) {
        this.zzabv = v;
        this.zzabw = v1;
    }

    public zzyw(RequestConfiguration requestConfiguration0) {
        this.zzabv = requestConfiguration0.getTagForChildDirectedTreatment();
        this.zzabw = requestConfiguration0.getTagForUnderAgeOfConsent();
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zzabv);
        SafeParcelWriter.writeInt(parcel0, 2, this.zzabw);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

