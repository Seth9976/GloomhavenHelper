package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "InitializationParamsCreator")
public final class zzv extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 1)
    public final long zza;
    @Field(id = 2)
    public final long zzb;
    @Field(id = 3)
    public final boolean zzc;
    @Field(id = 4)
    public final String zzd;
    @Field(id = 5)
    public final String zze;
    @Field(id = 6)
    public final String zzf;
    @Field(id = 7)
    public final Bundle zzg;

    static {
        zzv.CREATOR = new zzy();
    }

    @Constructor
    public zzv(@Param(id = 1) long v, @Param(id = 2) long v1, @Param(id = 3) boolean z, @Param(id = 4) String s, @Param(id = 5) String s1, @Param(id = 6) String s2, @Param(id = 7) Bundle bundle0) {
        this.zza = v;
        this.zzb = v1;
        this.zzc = z;
        this.zzd = s;
        this.zze = s1;
        this.zzf = s2;
        this.zzg = bundle0;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeLong(parcel0, 1, this.zza);
        SafeParcelWriter.writeLong(parcel0, 2, this.zzb);
        SafeParcelWriter.writeBoolean(parcel0, 3, this.zzc);
        SafeParcelWriter.writeString(parcel0, 4, this.zzd, false);
        SafeParcelWriter.writeString(parcel0, 5, this.zze, false);
        SafeParcelWriter.writeString(parcel0, 6, this.zzf, false);
        SafeParcelWriter.writeBundle(parcel0, 7, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

