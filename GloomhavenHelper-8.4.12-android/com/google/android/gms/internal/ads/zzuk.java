package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.zzb;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "AdSizeParcelCreator")
@Reserved({1})
public final class zzuk extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 3)
    public final int height;
    @Field(id = 4)
    public final int heightPixels;
    @Field(id = 6)
    public final int width;
    @Field(id = 7)
    public final int widthPixels;
    @Field(id = 2)
    public final String zzabk;
    @Field(id = 9)
    public final boolean zzbml;
    @Field(id = 5)
    public final boolean zzcdc;
    @Field(id = 8)
    public final zzuk[] zzcdd;
    @Field(id = 10)
    public final boolean zzcde;
    @Field(id = 11)
    public boolean zzcdf;
    @Field(id = 12)
    public boolean zzcdg;
    @Field(id = 13)
    private boolean zzcdh;
    @Field(id = 14)
    public boolean zzcdi;
    @Field(id = 15)
    public boolean zzcdj;

    static {
        zzuk.CREATOR = new zzun();
    }

    public zzuk() {
        this("interstitial_mb", 0, 0, true, 0, 0, null, false, false, false, false, false, false, false);
    }

    public zzuk(Context context0, AdSize adSize0) {
        this(context0, new AdSize[]{adSize0});
    }

    public zzuk(Context context0, AdSize[] arr_adSize) {
        int v;
        AdSize adSize0 = arr_adSize[0];
        this.zzcdc = false;
        this.zzcde = adSize0.isFluid();
        this.zzcdi = zzb.zza(adSize0);
        this.zzcdj = zzb.zzb(adSize0);
        if(this.zzcde) {
            this.width = AdSize.BANNER.getWidth();
            this.height = AdSize.BANNER.getHeight();
        }
        else if(this.zzcdj) {
            this.width = adSize0.getWidth();
            this.height = zzb.zzc(adSize0);
        }
        else {
            this.width = adSize0.getWidth();
            this.height = adSize0.getHeight();
        }
        boolean z = this.width == -1;
        boolean z1 = this.height == -2;
        DisplayMetrics displayMetrics0 = context0.getResources().getDisplayMetrics();
        if(z) {
            this.widthPixels = !zzayx.zzbo(context0) || !zzayx.zzbp(context0) ? displayMetrics0.widthPixels : displayMetrics0.widthPixels - zzayx.zzbq(context0);
            double f = (double)(((float)this.widthPixels) / displayMetrics0.density);
            v = (int)f;
            if(f - ((double)v) >= 0.01) {
                ++v;
            }
        }
        else {
            v = this.width;
            this.widthPixels = zzayx.zza(displayMetrics0, this.width);
        }
        int v1 = z1 ? zzuk.zzd(displayMetrics0) : this.height;
        this.heightPixels = zzayx.zza(displayMetrics0, v1);
        if(z || z1) {
            this.zzabk = v + "x" + v1 + "_as";
        }
        else if(this.zzcdj) {
            this.zzabk = this.width + "x" + this.height + "_as";
        }
        else if(this.zzcde) {
            this.zzabk = "320x50_mb";
        }
        else {
            this.zzabk = adSize0.toString();
        }
        if(arr_adSize.length > 1) {
            this.zzcdd = new zzuk[arr_adSize.length];
            for(int v2 = 0; v2 < arr_adSize.length; ++v2) {
                this.zzcdd[v2] = new zzuk(context0, arr_adSize[v2]);
            }
        }
        else {
            this.zzcdd = null;
        }
        this.zzbml = false;
        this.zzcdf = false;
    }

    @Constructor
    zzuk(@Param(id = 2) String s, @Param(id = 3) int v, @Param(id = 4) int v1, @Param(id = 5) boolean z, @Param(id = 6) int v2, @Param(id = 7) int v3, @Param(id = 8) zzuk[] arr_zzuk, @Param(id = 9) boolean z1, @Param(id = 10) boolean z2, @Param(id = 11) boolean z3, @Param(id = 12) boolean z4, @Param(id = 13) boolean z5, @Param(id = 14) boolean z6, @Param(id = 15) boolean z7) {
        this.zzabk = s;
        this.height = v;
        this.heightPixels = v1;
        this.zzcdc = z;
        this.width = v2;
        this.widthPixels = v3;
        this.zzcdd = arr_zzuk;
        this.zzbml = z1;
        this.zzcde = z2;
        this.zzcdf = z3;
        this.zzcdg = z4;
        this.zzcdh = z5;
        this.zzcdi = z6;
        this.zzcdj = z7;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 2, this.zzabk, false);
        SafeParcelWriter.writeInt(parcel0, 3, this.height);
        SafeParcelWriter.writeInt(parcel0, 4, this.heightPixels);
        SafeParcelWriter.writeBoolean(parcel0, 5, this.zzcdc);
        SafeParcelWriter.writeInt(parcel0, 6, this.width);
        SafeParcelWriter.writeInt(parcel0, 7, this.widthPixels);
        SafeParcelWriter.writeTypedArray(parcel0, 8, this.zzcdd, v, false);
        SafeParcelWriter.writeBoolean(parcel0, 9, this.zzbml);
        SafeParcelWriter.writeBoolean(parcel0, 10, this.zzcde);
        SafeParcelWriter.writeBoolean(parcel0, 11, this.zzcdf);
        SafeParcelWriter.writeBoolean(parcel0, 12, this.zzcdg);
        SafeParcelWriter.writeBoolean(parcel0, 13, this.zzcdh);
        SafeParcelWriter.writeBoolean(parcel0, 14, this.zzcdi);
        SafeParcelWriter.writeBoolean(parcel0, 15, this.zzcdj);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public static int zzb(DisplayMetrics displayMetrics0) {
        return displayMetrics0.widthPixels;
    }

    public static int zzc(DisplayMetrics displayMetrics0) {
        return (int)(((float)zzuk.zzd(displayMetrics0)) * displayMetrics0.density);
    }

    private static int zzd(DisplayMetrics displayMetrics0) {
        int v = (int)(((float)displayMetrics0.heightPixels) / displayMetrics0.density);
        if(v <= 400) {
            return 0x20;
        }
        return v > 720 ? 90 : 50;
    }

    public static zzuk zzh(Context context0) {
        return new zzuk("320x50_mb", 0, 0, false, 0, 0, null, true, false, false, false, false, false, false);
    }

    public static zzuk zzoq() {
        return new zzuk("reward_mb", 0, 0, true, 0, 0, null, false, false, false, false, false, false, false);
    }

    public static zzuk zzor() {
        return new zzuk("interstitial_mb", 0, 0, false, 0, 0, null, false, false, false, false, true, false, false);
    }

    public static zzuk zzos() {
        return new zzuk("invalid", 0, 0, false, 0, 0, null, false, false, false, true, false, false, false);
    }

    public final AdSize zzot() {
        return zzb.zza(this.width, this.height, this.zzabk);
    }
}

