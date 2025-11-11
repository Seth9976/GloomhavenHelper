package com.google.android.gms.internal.ads;

import android.content.Context;

public final class zzcvn implements zzeej {
    private final zzeew zzfev;
    private final zzeew zzfjj;

    private zzcvn(zzeew zzeew0, zzeew zzeew1) {
        this.zzfjj = zzeew0;
        this.zzfev = zzeew1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeew
    public final Object get() {
        return zzcvn.zza(((Context)this.zzfjj.get()), ((zzdoe)this.zzfev.get()));
    }

    public static zzcvj zza(Context context0, zzdoe zzdoe0) {
        return new zzcvj(context0, zzdoe0);
    }

    public static zzcvn zzat(zzeew zzeew0, zzeew zzeew1) {
        return new zzcvn(zzeew0, zzeew1);
    }
}

