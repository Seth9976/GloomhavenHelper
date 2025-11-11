package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import androidx.annotation.Nullable;

public final class zzciq implements zzeej {
    private final zzeew zzelc;
    private final zzeew zzfxt;

    private zzciq(zzeew zzeew0, zzeew zzeew1) {
        this.zzelc = zzeew0;
        this.zzfxt = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    @Nullable
    public final Object get() {
        return zzcig.zza(((Context)this.zzelc.get()), ((ApplicationInfo)this.zzfxt.get()));
    }

    public static zzciq zzaj(zzeew zzeew0, zzeew zzeew1) {
        return new zzciq(zzeew0, zzeew1);
    }
}

