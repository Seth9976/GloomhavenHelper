package com.google.android.gms.ads.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "InterstitialAdParameterParcelCreator")
@Reserved({1})
public final class zzg extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 2)
    public final boolean zzblg;
    @Field(id = 3)
    public final boolean zzblh;
    @Field(id = 4)
    private final String zzbli;
    @Field(id = 5)
    public final boolean zzblj;
    @Field(id = 6)
    public final float zzblk;
    @Field(id = 7)
    public final int zzbll;
    @Field(id = 8)
    public final boolean zzblm;
    @Field(id = 9)
    public final boolean zzbln;
    @Field(id = 10)
    public final boolean zzblo;

    static {
        zzg.CREATOR = new zzj();
    }

    @Constructor
    zzg(@Param(id = 2) boolean z, @Param(id = 3) boolean z1, @Param(id = 4) String s, @Param(id = 5) boolean z2, @Param(id = 6) float f, @Param(id = 7) int v, @Param(id = 8) boolean z3, @Param(id = 9) boolean z4, @Param(id = 10) boolean z5) {
        this.zzblg = z;
        this.zzblh = z1;
        this.zzbli = s;
        this.zzblj = z2;
        this.zzblk = f;
        this.zzbll = v;
        this.zzblm = z3;
        this.zzbln = z4;
        this.zzblo = z5;
    }

    public zzg(boolean z, boolean z1, boolean z2, float f, int v, boolean z3, boolean z4, boolean z5) {
        this(false, z1, null, false, 0.0f, -1, z3, z4, z5);
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeBoolean(parcel0, 2, this.zzblg);
        SafeParcelWriter.writeBoolean(parcel0, 3, this.zzblh);
        SafeParcelWriter.writeString(parcel0, 4, this.zzbli, false);
        SafeParcelWriter.writeBoolean(parcel0, 5, this.zzblj);
        SafeParcelWriter.writeFloat(parcel0, 6, this.zzblk);
        SafeParcelWriter.writeInt(parcel0, 7, this.zzbll);
        SafeParcelWriter.writeBoolean(parcel0, 8, this.zzblm);
        SafeParcelWriter.writeBoolean(parcel0, 9, this.zzbln);
        SafeParcelWriter.writeBoolean(parcel0, 10, this.zzblo);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

