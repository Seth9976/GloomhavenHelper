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

@Class(creator = "VersionInfoParcelCreator")
@Reserved({1})
public final class zzazo extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 2)
    public String zzbmj;
    @Field(id = 3)
    public int zzdxf;
    @Field(id = 4)
    public int zzdxg;
    @Field(id = 5)
    public boolean zzdxh;
    @Field(id = 6)
    private boolean zzdxi;

    static {
        zzazo.CREATOR = new zzazn();
    }

    public zzazo(int v, int v1, boolean z) {
        this(v, v1, z, false, false);
    }

    public zzazo(int v, int v1, boolean z, boolean z1) {
        this(v, v1, true, false, false);
    }

    private zzazo(int v, int v1, boolean z, boolean z1, boolean z2) {
        this("afma-sdk-a-v" + v + "." + v1 + "." + (z ? "0" : "1"), v, v1, z, false);
    }

    @Constructor
    zzazo(@Param(id = 2) String s, @Param(id = 3) int v, @Param(id = 4) int v1, @Param(id = 5) boolean z, @Param(id = 6) boolean z1) {
        this.zzbmj = s;
        this.zzdxf = v;
        this.zzdxg = v1;
        this.zzdxh = z;
        this.zzdxi = z1;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 2, this.zzbmj, false);
        SafeParcelWriter.writeInt(parcel0, 3, this.zzdxf);
        SafeParcelWriter.writeInt(parcel0, 4, this.zzdxg);
        SafeParcelWriter.writeBoolean(parcel0, 5, this.zzdxh);
        SafeParcelWriter.writeBoolean(parcel0, 6, this.zzdxi);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public static zzazo zzxr() {
        return new zzazo(12451009, 12451009, true);
    }
}

