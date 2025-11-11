package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.rewarded.ServerSideVerificationOptions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import javax.annotation.ParametersAreNonnullByDefault;

@Class(creator = "ServerSideVerificationOptionsParcelCreator")
@ParametersAreNonnullByDefault
public final class zzato extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 1)
    public final String zzdpa;
    @Field(id = 2)
    public final String zzdpb;

    static {
        zzato.CREATOR = new zzatn();
    }

    public zzato(ServerSideVerificationOptions serverSideVerificationOptions0) {
        this(serverSideVerificationOptions0.getUserId(), serverSideVerificationOptions0.getCustomData());
    }

    @Constructor
    public zzato(@Param(id = 1) String s, @Param(id = 2) String s1) {
        this.zzdpa = s;
        this.zzdpb = s1;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 1, this.zzdpa, false);
        SafeParcelWriter.writeString(parcel0, 2, this.zzdpb, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

