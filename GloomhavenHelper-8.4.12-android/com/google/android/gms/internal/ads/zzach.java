package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "NativeAdOptionsParcelCreator")
public final class zzach extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 1)
    public final int versionCode;
    @Field(id = 3)
    public final int zzbke;
    @Field(id = 8)
    public final int zzbkf;
    @Field(id = 4)
    public final boolean zzbkg;
    @Field(id = 5)
    public final int zzbkh;
    @Field(id = 7)
    public final boolean zzbkj;
    @Field(id = 2)
    public final boolean zzcws;
    @Nullable
    @Field(id = 6)
    public final zzzc zzcwt;

    static {
        zzach.CREATOR = new zzacg();
    }

    @Constructor
    public zzach(@Param(id = 1) int v, @Param(id = 2) boolean z, @Param(id = 3) int v1, @Param(id = 4) boolean z1, @Param(id = 5) int v2, @Param(id = 6) zzzc zzzc0, @Param(id = 7) boolean z2, @Param(id = 8) int v3) {
        this.versionCode = v;
        this.zzcws = z;
        this.zzbke = v1;
        this.zzbkg = z1;
        this.zzbkh = v2;
        this.zzcwt = zzzc0;
        this.zzbkj = z2;
        this.zzbkf = v3;
    }

    public zzach(NativeAdOptions nativeAdOptions0) {
        this(4, nativeAdOptions0.shouldReturnUrlsForImageAssets(), nativeAdOptions0.getImageOrientation(), nativeAdOptions0.shouldRequestMultipleImages(), nativeAdOptions0.getAdChoicesPlacement(), (nativeAdOptions0.getVideoOptions() == null ? null : new zzzc(nativeAdOptions0.getVideoOptions())), nativeAdOptions0.zzjp(), nativeAdOptions0.getMediaAspectRatio());
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.versionCode);
        SafeParcelWriter.writeBoolean(parcel0, 2, this.zzcws);
        SafeParcelWriter.writeInt(parcel0, 3, this.zzbke);
        SafeParcelWriter.writeBoolean(parcel0, 4, this.zzbkg);
        SafeParcelWriter.writeInt(parcel0, 5, this.zzbkh);
        SafeParcelWriter.writeParcelable(parcel0, 6, this.zzcwt, v, false);
        SafeParcelWriter.writeBoolean(parcel0, 7, this.zzbkj);
        SafeParcelWriter.writeInt(parcel0, 8, this.zzbkf);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

