package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import javax.annotation.ParametersAreNonnullByDefault;

@Class(creator = "SignalConfigurationParcelCreator")
@ParametersAreNonnullByDefault
public final class zzavh extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 2)
    public final String zzbmg;
    @Field(id = 1)
    @Deprecated
    public final String zzbri;
    @Field(id = 3)
    @Deprecated
    public final zzuk zzdqz;
    @Field(id = 4)
    public final zzuh zzdra;

    static {
        zzavh.CREATOR = new zzavk();
    }

    @Constructor
    public zzavh(@Param(id = 1) String s, @Param(id = 2) String s1, @Param(id = 3) zzuk zzuk0, @Param(id = 4) zzuh zzuh0) {
        this.zzbri = s;
        this.zzbmg = s1;
        this.zzdqz = zzuk0;
        this.zzdra = zzuh0;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 1, this.zzbri, false);
        SafeParcelWriter.writeString(parcel0, 2, this.zzbmg, false);
        SafeParcelWriter.writeParcelable(parcel0, 3, this.zzdqz, v, false);
        SafeParcelWriter.writeParcelable(parcel0, 4, this.zzdra, v, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

