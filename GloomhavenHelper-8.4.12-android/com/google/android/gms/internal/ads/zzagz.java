package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "AdapterStatusParcelCreator")
public final class zzagz extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 4)
    public final String description;
    @Field(id = 1)
    public final String zzczf;
    @Field(id = 2)
    public final boolean zzczg;
    @Field(id = 3)
    public final int zzczh;

    static {
        zzagz.CREATOR = new zzagy();
    }

    @Constructor
    public zzagz(@Param(id = 1) String s, @Param(id = 2) boolean z, @Param(id = 3) int v, @Param(id = 4) String s1) {
        this.zzczf = s;
        this.zzczg = z;
        this.zzczh = v;
        this.description = s1;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 1, this.zzczf, false);
        SafeParcelWriter.writeBoolean(parcel0, 2, this.zzczg);
        SafeParcelWriter.writeInt(parcel0, 3, this.zzczh);
        SafeParcelWriter.writeString(parcel0, 4, this.description, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

