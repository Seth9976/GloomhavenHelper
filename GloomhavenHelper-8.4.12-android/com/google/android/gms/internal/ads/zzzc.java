package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "VideoOptionsParcelCreator")
@Reserved({1})
public final class zzzc extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 2)
    public final boolean zzacd;
    @Field(id = 3)
    public final boolean zzace;
    @Field(id = 4)
    public final boolean zzacf;

    static {
        zzzc.CREATOR = new zzzf();
    }

    public zzzc(VideoOptions videoOptions0) {
        this(videoOptions0.getStartMuted(), videoOptions0.getCustomControlsRequested(), videoOptions0.getClickToExpandRequested());
    }

    @Constructor
    public zzzc(@Param(id = 2) boolean z, @Param(id = 3) boolean z1, @Param(id = 4) boolean z2) {
        this.zzacd = z;
        this.zzace = z1;
        this.zzacf = z2;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeBoolean(parcel0, 2, this.zzacd);
        SafeParcelWriter.writeBoolean(parcel0, 3, this.zzace);
        SafeParcelWriter.writeBoolean(parcel0, 4, this.zzacf);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

