package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "HttpResponseParcelCreator")
public final class zzagn extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 4)
    public final byte[] data;
    @Field(id = 3)
    public final int statusCode;
    @Field(id = 7)
    public final boolean zzac;
    @Field(id = 8)
    public final long zzad;
    @Field(id = 5)
    public final String[] zzcyy;
    @Field(id = 6)
    public final String[] zzcyz;
    @Field(id = 1)
    public final boolean zzcza;
    @Field(id = 2)
    public final String zzczb;

    static {
        zzagn.CREATOR = new zzagm();
    }

    @Constructor
    zzagn(@Param(id = 1) boolean z, @Param(id = 2) String s, @Param(id = 3) int v, @Param(id = 4) byte[] arr_b, @Param(id = 5) String[] arr_s, @Param(id = 6) String[] arr_s1, @Param(id = 7) boolean z1, @Param(id = 8) long v1) {
        this.zzcza = z;
        this.zzczb = s;
        this.statusCode = v;
        this.data = arr_b;
        this.zzcyy = arr_s;
        this.zzcyz = arr_s1;
        this.zzac = z1;
        this.zzad = v1;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeBoolean(parcel0, 1, this.zzcza);
        SafeParcelWriter.writeString(parcel0, 2, this.zzczb, false);
        SafeParcelWriter.writeInt(parcel0, 3, this.statusCode);
        SafeParcelWriter.writeByteArray(parcel0, 4, this.data, false);
        SafeParcelWriter.writeStringArray(parcel0, 5, this.zzcyy, false);
        SafeParcelWriter.writeStringArray(parcel0, 6, this.zzcyz, false);
        SafeParcelWriter.writeBoolean(parcel0, 7, this.zzac);
        SafeParcelWriter.writeLong(parcel0, 8, this.zzad);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

